/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server;

import com.phoenixli.server.player.PlayerContext;
import com.phoenixli.common.channel.UninitializeChannel;
import com.phoenixli.common.message.serverMessage.ExternalPlayerMessage;
import com.phoenixli.common.message.serverMessage.InternalPlayerMessage;
import com.phoenixli.common.message.serverMessage.Message;
import com.phoenixli.common.messageQueue.DBMessageQueue;
import com.phoenixli.common.messageQueue.NetworkSendThreadMessageQueue;
import com.phoenixli.common.messageQueue.ServerMessageQueue;
import com.phoenixli.common.pipelineFactory.CommonToServerPipelineFactory;
import com.phoenixli.server.actor.Human;
import com.phoenixli.server.listenerServer.PlayerConnectServer;
import com.phoenixli.server.message.messageBuilder.DBMessageBuilder;
import com.phoenixli.server.message.networkSendThreadMessage.AddChannelContextNSTMessage;
import com.phoenixli.server.message.serverMessage.LoginMessage;
import com.phoenixli.server.player.MapPlayer;
import com.phoenixli.server.player.Player;
import com.phoenixli.server.threadHandler.DBThreadHandler;
import com.phoenixli.server.threadHandler.NetworkSendThreadHandler;
import com.phoenixli.server.threadHandler.PlayerConnectHandler;
import com.phoenixli.utils.CommonUtil;
import com.phoenixli.utils.Consts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;

/**
 *
 * @author rachel
 */
public class ProjectCardServer implements Runnable {
    //Singleton，没有多线程同步的需要，只是方便全局访问
    public static final ProjectCardServer INSTANCE = new ProjectCardServer();
    // 全局共享的消息队列
    private final LinkedTransferQueue<Message> messageQueue = ServerMessageQueue.queue();
    // uninitializeChannel保存刚建立连接但未开始交换玩家帐号信息的channel，key为channel id
    private final HashMap<Integer, UninitializeChannel> uninitializeChannels = new HashMap<Integer, UninitializeChannel>();
    public int serverId;                // 服务器id
    private int port;                   // Map Server监听的端口
    private String internalHost;        // 内网网卡ip
    public String dbHost;               // 数据库地址
    public String dbName;               // 数据库名
    private String logDBHost;           // 日志数据库IP
    private String logDBName;           // 日志数据库
    private int managerPort;            // Manager端口
    private int rpcPort;                // rpc端口
    public String loginCheckUrl;        // 登陆校验的Manager Server的URL
    private PlayerConnectServer playerConnectServer; //Game Server
    private Connection dbConnection;    // 数据库连接
    // 数据库线程
    private DBThreadHandler mapDBThreadHandle;
    private Thread dbThread;
    // 网络发包线程
    private NetworkSendThreadHandler networkSendThreadHandle;
    private Thread networkSendThread;
    // 处理低实时要求任务（如：充值、激活码）的线程池
    private ExecutorService ioExecutorService;
    // 处理高实时要求任务的线程池（如：战斗计算）
    private ExecutorService nioExecutorService;
    
    

    // 以channelID作为索引玩家ID
    public final ConcurrentHashMap<Integer, Integer> channelID2PlayerIDMap = new ConcurrentHashMap<Integer, Integer>();
    // players以playerID为索引记录玩家对象（包括正在登录的玩家和已进入游戏的玩家）
    public final HashMap<Integer, MapPlayer> players = new HashMap<Integer, MapPlayer>();
    // playerContexts保存玩家上下文，key为playerId。找不到则创建玩家上下文并异步加载玩家数据
    public final HashMap<Integer, PlayerContext> playerContexts = new HashMap<Integer, PlayerContext>();
    // 简略玩家信息表
    //public BriefPlayerInfos briefPlayerInfos; 
    
    private long realPrevTime;
    // 当前服务器时间
    private volatile long realCurrTime;
    // 主线程Sleep时间
    private int prevSleepTime = 0;
    
    // 标志服务是否正在关闭
    private volatile boolean isShuttingDown = false;
    
    private String parseArg(String[] args, String param) {
        int argsNum = args.length;
        String retVal = null;
        for (int i = 0; i < argsNum; i++) {
            if (args[i].compareTo(param) == 0) {
                if (i + 1 < argsNum) {
                    retVal = args[i + 1];
                }
                break;
            }
        }

        return retVal;
    }

    private boolean parseArgs(String[] args) {
        // parse server id
        String idString = parseArg(args, "-id");
        if (idString != null) {
            Integer id = Integer.valueOf(idString);
            if (id != null) {
                serverId = id;
            } else {
                System.err.println("Value of \"-id\" param must be integer.");
                return false;
            }
        } else {
            System.err.println("Lack \"-id XXX\" param");
            return false;
        }

        // parse client port
        String portString = parseArg(args, "-port");
        if (portString != null) {
            Integer portInteger = Integer.valueOf(portString);
            if (portInteger != null) {
                port = portInteger;
            } else {
                System.err.println("Value of \"-port\" param must be integer.");
                return false;
            }
        } else {
            System.err.println("Lack \"-port XXX\" param");
            return false;
        }

        // parse internal host
        String internalHostString = parseArg(args, "-internalHost");
        if (internalHostString != null) {
            if ((internalHostString.toLowerCase().compareTo("localhost") == 0) || CommonUtil.ValidateIPAddress(internalHostString)) {
                internalHost = internalHostString;
            } else {
                System.err.println("Value of \"-internalHost\" param must be a ip of this machine to the internal network.");
                return false;
            }
        } else {
            System.err.println("Lack \"-internalHost XXX\" param");
            return false;
        }

        // parse private host
        String dbHostString = parseArg(args, "-dbHost");
        if (dbHostString != null) {
            if (dbHostString.compareTo("localhost") == 0 || CommonUtil.ValidateIPAddress(dbHostString)) {
                dbHost = dbHostString;
            } else {
                System.err.println("Value of \"-dbHost\" param must be a ip address.");
                return false;
            }
        } else {
            System.err.println("Lack \"-dbHost XXX\" param");
            return false;
        }

        // parse private host
        dbName = parseArg(args, "-dbName");
        if (dbName == null) {
            System.err.println("Lack \"-dbName XXX\" param");
            return false;
        }

        logDBHost = parseArg(args, "-logDBHost");
        if (logDBHost == null) {
            System.err.println("Lack \"-logDBHost XXX\" param");
            return false;
        }

        logDBName = parseArg(args, "-logDBName");
        if (logDBName == null) {
            System.err.println("Lack \"-logDBName XXX\" param");
            return false;
        }

        // parse manager port
        String managerPortString = parseArg(args, "-managerPort");
        if (managerPortString != null) {
            Integer managerPortInteger = Integer.valueOf(managerPortString);
            if (managerPortInteger != null) {
                managerPort = managerPortInteger;
            } else {
                System.err.println("Value of \"-managerPort\" param must be integer.");
                return false;
            }
        } else {
            System.err.println("Lack \"-managerPort XXX\" param");
            return false;
        }

        // parse rpc port
        String rpcPortString = parseArg(args, "-rpcPort");
        if (rpcPortString != null) {
            Integer rpcPortInteger = Integer.valueOf(rpcPortString);
            if (rpcPortInteger != null) {
                rpcPort = rpcPortInteger;
            } else {
                System.err.println("Value of \"-rpcPort\" param must be integer.");
                return false;
            }
        } else {
            System.err.println("Lack \"-rpcPort XXX\" param");
            return false;
        }

        loginCheckUrl = parseArg(args, "-loginCheckUrl");
        if (loginCheckUrl == null) {
            System.err.println("Lack \"-loginCheckUrl XXX\" param");
            return false;
        }
        return true;
    }

    public boolean isShuttingDown() {
        return isShuttingDown;
    }
    
    public long getCurrentTime() {
        return realCurrTime;
    }
    
    public void init() {
        
    }
    
    public void enterGame(MapPlayer player) {
        
    }
    
    public PlayerContext loadPlayerData(int playerId) {
        // 载入玩家数据
        PlayerContext playerContext = playerContexts.get(playerId);

        if (playerContext == null) {
            playerContext = new PlayerContext();
            playerContext.lastVisitTime = getCurrentTime();
            playerContext.player = players.get(playerId);

            playerContexts.put(playerId, playerContext);
            // 通知DB线程加载玩家数据
            DBMessageQueue.queue().offer(DBMessageBuilder.buildGetCharDetailDBMessage(playerId));
        } else if (playerContext.human != null) {
            System.err.println("load player[" + playerId + "] data fail because already loaded.");
        }

        return playerContext;
    }
    
    public void removePlayer(MapPlayer player) {
        assert (player != null);

        Human human = player.human;
        if (human != null) {
            human.leaveGame();
        }

        PlayerContext playerContext = playerContexts.get(player.getId());
        if (playerContext != null) {
            playerContext.lastVisitTime = getCurrentTime();
            playerContext.player = null;
        }

        // 注意：关闭channel会触发产生CLIENTCLOSE消息处理
        player.channelContext.close();
    }
    
    
    
    private void handleMessages() {
        // handle message from message queue
        // 不处理shuttingDown过程中产生的消息
        Message msg = messageQueue.poll();
        while (msg != null) {
            switch (msg.getType()) {
                case MAP_CLIENT_CONNECT: {
                    Channel clientChannel = ((ExternalPlayerMessage) msg).channel;
                    uninitializeChannels.put(clientChannel.getId(), new UninitializeChannel(clientChannel));
                    break;
                }
                case MAP_CLIENT_CLOSE: {

                    break;
                }
                case MAP_LOGIN: {
                    LoginMessage loginMsg = (LoginMessage) msg;
                    Channel clientChannel = loginMsg.channel;
                    int channelId = clientChannel.getId();
                    int playerId = loginMsg.playerId;
                    // 防止玩家登陆时断线，使上线玩家是个无连接玩家并僵死在线上，并导致玩家始终无法登陆
                    if (clientChannel.isOpen()) {
                        // 如果已经存在玩家对象该如何处理？踢出游戏中玩家并断开正在登录的连接
                        MapPlayer player = players.get(playerId);
                        if (player != null) {
                            // TODO: 若要实现后登陆踢出前登陆进入游戏，可将此处实现改为不断开后登陆连接，将后登陆消息记录在player中，待处理ALL_CLIENTCLOSE消息时重新将后登陆消息放入消息队列
                            // 注意：当有多重登陆时只有最后登陆能进入游戏，中间登陆连接都要断开并删除相关对象
                            //uninitializeChannels.remove(channelId);
                            //clientChannel.close();
                            if (player.loginMessage != null) {
                                Channel channel = player.loginMessage.channel;
                                uninitializeChannels.remove(channel.getId());
                                channel.close();
                            }
                            player.loginMessage = loginMsg;
                            removePlayer(player);
                            System.err.println("Player[" + playerId + "] multilogin -- channel id:" + channelId);
                        } else if (isShuttingDown) {
                            // 正在关服，拒绝登陆
                            uninitializeChannels.remove(channelId);
                            clientChannel.close();
                        } else {
                            // 创建Player上下文
                            // 已经将Player初始化为Login1State
                            player = new MapPlayer(playerId, loginMsg.passport, loginMsg.auth, loginMsg.privilege, loginMsg.endForbidTalkTime, clientChannel);

                            PlayerContext playerContext = playerContexts.get(playerId);
                            if (playerContext != null) {
                                assert (playerContext.player == null);
                                playerContext.lastVisitTime = getCurrentTime();

                                if (playerContext.human != null) {
                                    player.human = playerContext.human;
                                    player.human.mapPlayer = player;
                                }

                                playerContext.player = player;
                            }
                            players.put(playerId, player);
                            channelID2PlayerIDMap.put(channelId, playerId);

                            uninitializeChannels.remove(channelId);

                            // 将Player的channelContext加入网络发送线程
                            NetworkSendThreadMessageQueue.queue().add(new AddChannelContextNSTMessage(player.channelContext));

                            // 向DBApp获取玩家基本角色信息
                            DBMessageQueue.queue().offer(DBMessageBuilder.buildGetCharNumDBMessage(playerId));
                        }
                    } else {
                        System.err.println("Player[" + playerId + "] can't login because channel is closed.");
                    }
                    break;
                }
                default: {
                    // 处理玩家相关消息，转至玩家的handleMessage处理
                    int playerId = ((InternalPlayerMessage) msg).playerId;
                    Player player = players.get(playerId);

                    if (player != null) {
                        player.handleMessage(msg);
                    } else {
                        System.err.println("Can't find player[" + playerId + "] to handle message[" + msg.getType() + "].");
                    }
                    break;
                }
            }
            
            msg = messageQueue.poll();
        }
        
    }
    
    /**
     * 初始化并启动线程
     */
    public void start() {
        // 启动数据库处理线程
        this.mapDBThreadHandle = new DBThreadHandler(dbConnection);
        this.dbThread = new Thread(mapDBThreadHandle, "DBThread");
        this.dbThread.start();

        // 启动发包线程
        this.networkSendThreadHandle = new NetworkSendThreadHandler();
        this.networkSendThread = new Thread(this.networkSendThreadHandle, "NetworkSendThread");
        this.networkSendThread.start();

        // 线程池（用于处理战斗计算、充值、激活码的事务）
        this.ioExecutorService = Executors.newFixedThreadPool(Consts.THREAD_SERVER_THREAD_NUM);
        this.nioExecutorService = Executors.newFixedThreadPool(Consts.THREAD_SERVER_THREAD_NUM);

        // 启动GameServer对Client的监听服务
        // 200 threads max, Memory limitation: 1MB by channel, 1GB global, 1000 ms of timeout 参考http://www.cnblogs.com/baronzhao/p/netty_2.html
        ExecutionHandler executionHandler = new ExecutionHandler(new OrderedMemoryAwareThreadPoolExecutor(500, 1048576, 1073741824, 1000, TimeUnit.MILLISECONDS, Executors.defaultThreadFactory()));
        PlayerConnectHandler playerConnectHandler = new PlayerConnectHandler();
        this.playerConnectServer = PlayerConnectServer.INSTANCE;
        this.playerConnectServer.init(this.port, new CommonToServerPipelineFactory(executionHandler, playerConnectHandler));
        this.playerConnectServer.startServer();
        
        // 启动主线程
        new Thread(this, "GameServerThread").start();
    }

    public boolean connectToDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection("jdbc:mysql://" + dbHost + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8", "phoenixtest", "acY5qmGKVcRs4nST");
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(ProjectCardServer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("connectToDB Error: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("connectToDB Error: " + ex.getMessage());
        }

        return false;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                realCurrTime = System.currentTimeMillis();
                int diffTime = (int) (realCurrTime - realPrevTime);

                if (diffTime < 0) {
                    System.err.println( "MainThread: Difftime=" + diffTime + " Error!");
                }
                
                // 心跳 - 广播到客户端，客户端进行校时
                if (realCurrTime / Consts.MILSECOND_1MINITE > realPrevTime / Consts.MILSECOND_1MINITE) {
                    //broadcast(ClientToMapBuilder.buildRealTime(realCurrTime));
                }
                
                handleMessages();
                
                realPrevTime = realCurrTime;
                
                // 两次执行的时间差（D0) = TICK的执行时间(t0) + Sleep的时间(d0)
                // 下一次的Sleep时间(d1) + TICK的执行时间(t1) = 休眠时间常量(WORLD_SLEEP_CONST)
                // 无法获得下一次的TICK的执行时间，使用当次TICK的执行时间(t0) + 下次Sleep的时间(d1) = 休眠时间常量(WORLD_SLEEP_CONST) 满足需求
                // 即d1 = WORLD_SLEEP_CONST - t0 = WORLD_SLEEP_CONST - (D0 - d0) = WORLD_SLEEP_CONST + d0 - D0
                if (diffTime <= Consts.GAME_SLEEP_CONST + prevSleepTime) {
                    prevSleepTime = Consts.GAME_SLEEP_CONST - (diffTime - prevSleepTime);
                } else {
                    prevSleepTime = 10;
                    System.out.println("MainThread: difftime=" + diffTime);
                }
                try {
                    Thread.sleep(prevSleepTime);
                } catch (InterruptedException ex) {
                    System.err.println("MainThread Sleep Error: " + ex.getMessage());
                }
                
            } catch (Exception ex) {
                System.err.println("MainThread Error: " + ex.getMessage());
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String param = "";
        for (String arg : args) {
            param += arg + " ";
        }
        System.out.println("Server starting with param : " + param);

        try {
            if (!ProjectCardServer.INSTANCE.parseArgs(args)) {
                return;
            }

            if (!ProjectCardServer.INSTANCE.connectToDB()) {
                System.err.println("Can't connect to database[" + ProjectCardServer.INSTANCE.dbHost + ":" + ProjectCardServer.INSTANCE.dbName + "].");
                return;
            }
            
            ProjectCardServer.INSTANCE.init();
            ProjectCardServer.INSTANCE.start();
            
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    
}
