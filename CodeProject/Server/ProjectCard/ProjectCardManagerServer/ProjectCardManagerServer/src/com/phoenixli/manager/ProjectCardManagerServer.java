/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.manager;

import com.phoenixli.config.ServerConfig;
import com.phoenixli.database.DBHandler;
import com.phoenixli.pipelineFactory.HttpServerToWebPipelineFactory;
import com.phoenixli.server.Server;
import com.phoenixli.utils.CommonUtil;
import com.phoenixli.web.WebServer;
import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 *
 * @author rachel
 */
public class ProjectCardManagerServer implements Runnable {

    //Singleton，没有多线程同步的需要，只是方便全局访问
    public static ProjectCardManagerServer INSTANCE = new ProjectCardManagerServer();
    
    private ConcurrentHashMap<Integer, Server> servers;
    public String webHost;              // 启动web服务的ip
    public int webPort;                 // 启动web服务的端口
    public String dbHost;               // Manager Server 数据库地址
    public String dbName;               // Manager Server 数据库名
    public WebServer webServer;

    public int maxSessionNum = 100000;
    public int sessionPeriod = 3600000;
    
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
        // parse private host
        String webHostString = parseArg(args, "-webHost");
        if (webHostString != null) {
            if (CommonUtil.ValidateIPAddress(webHostString)) {
                if (CommonUtil.ValidateLocalAddress(webHostString)) {
                    webHost = webHostString;
                } else {
                    System.err.println("Value of \"-webHost\" param must be a ip of the local machine which run this manager.");
                    return false;
                }
            } else {
                System.err.println("Value of \"-webHost\" param must be a ip address.");
                return false;
            }
        } else {
            System.err.println("Lack \"-webHost XXX\" param");
            return false;
        }

        // parse web port
        String webPortStr = parseArg(args, "-webPort");
        if (webPortStr != null) {
            Integer webPortI = Integer.valueOf(webPortStr);
            if (webPortI != null) {
                webPort = webPortI;
            } else {
                System.err.println("Value of \"-webPort\" param must be integer.");
                return false;
            }
        } else {
            System.err.println("Lack \"-webPort XXX\" param");
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
        return true;
    }

    public void initServers(LinkedList<ServerConfig> serverConfigs) {
        servers = new ConcurrentHashMap<Integer, Server>();
        for (ServerConfig serverConfig : serverConfigs) {
            Server server = new Server(serverConfig);
            servers.put(server.serverConfig.serverId, server);
        }
    }

    private boolean initServers(DBHandler dbHandler) {
        LinkedList<ServerConfig> serverConfigs = dbHandler.getServerConfigs();
        if (serverConfigs.isEmpty()) {
            return false;
        }
        initServers(serverConfigs);
        return true;
    }

    private boolean initWebServer(DBHandler dbHandler) {
        webServer = new WebServer();
        return webServer.init(dbHandler);
    }

    public boolean init() {
        DBHandler dbHandler = new DBHandler(dbHost, dbName);
        if (!dbHandler.connectToDB()) {
            System.out.println("Can't Connect to Database[" + dbHost + ":" + dbName + "].");
            dbHandler.close();
            return false;
        }

        if (!initServers(dbHandler)) {
            System.err.println("Can't Init Servers.");
            dbHandler.close();
            return false;
        }

        if (!initWebServer(dbHandler)) {
            System.err.println("Can't init web server.");
            dbHandler.close();
            return false;
        }

        dbHandler.close();
        return true;
    }

    public void start() {
        // 启动Web服务
        ServerBootstrap webBootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()));
        webBootstrap.setPipelineFactory(new HttpServerToWebPipelineFactory());
        webBootstrap.bind(new InetSocketAddress(webPort));
        System.out.println("Manager Server Running....");
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                // handle message
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.err.println("Manager Thread Error: " + ex.getMessage());
                }
            } catch (Exception ex) {
                System.err.println("Manager Thread Error: " + ex.getMessage());
            }
        }
    }

    public Server getServer(int serverId) {
        return servers.get(serverId);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // 从配置文件读取配置信息
        if (!ProjectCardManagerServer.INSTANCE.parseArgs(args)) {
            return;
        }

        if (!ProjectCardManagerServer.INSTANCE.init()) {
            return;
        }

        ProjectCardManagerServer.INSTANCE.start();
    }
}
