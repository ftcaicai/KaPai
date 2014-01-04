/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.channelHandler;

import com.phoenixli.database.DBHandler;
import com.phoenixli.manager.ProjectCardManagerServer;
import com.phoenixli.server.Server;
import com.phoenixli.utils.Consts;
import com.phoenixli.web.PlayerInfo;
import com.phoenixli.web.WebServer;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import static org.jboss.netty.handler.codec.http.HttpHeaders.is100ContinueExpected;
import org.jboss.netty.handler.codec.http.HttpRequest;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import org.jboss.netty.handler.codec.http.QueryStringDecoder;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author rachel
 */
public class HttpServerHandlerToWeb extends HttpServerHandler{
    public HttpServerHandlerToWeb() {
    }

    @Override
    public void channelConnected(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    }

    @Override
    public void channelClosed(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx,e);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        if (e.getChannel().isOpen()) {
            HttpRequest request = (HttpRequest) e.getMessage();

            if (is100ContinueExpected(request)) {
                sendError(ctx, BAD_REQUEST);
                return;
            }

            String uri = request.getUri();
            try {
                if (uri.equals("/session/get")) {
                    getSession(e.getChannel(), request);
                } else if (uri.equals("/session/check")) {
                    checkSession(e.getChannel(), request);
                } 
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
    }

    private void getSession(Channel channel, HttpRequest request) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder("?" + request.getContent().toString(org.jboss.netty.util.CharsetUtil.UTF_8));
        Map<String, List<String>> queryMap = queryStringDecoder.getParameters();
        String passport = null;
        Integer serverId = null;
        Integer auth = null;

        List<String> passportList = queryMap.get("passport");
        if (passportList != null && passportList.size() == 1) {
            passport = passportList.get(0);
        }
        List<String> serverIdList = queryMap.get("serverid");
        if (serverIdList != null && serverIdList.size() == 1) {
            serverId = Integer.parseInt(serverIdList.get(0));
        }
        List<String> authList = queryMap.get("auth");
        if (authList != null && authList.size() == 1) {
            auth = Integer.parseInt(authList.get(0));
        }

        Server server = null;
        if (serverId != null) {
            server = ProjectCardManagerServer.INSTANCE.getServer((int) (long) serverId);
        }
        if (passport != null && serverId != null && server != null && auth != null) {
            DBHandler dbHandler = new DBHandler(ProjectCardManagerServer.INSTANCE.dbHost, ProjectCardManagerServer.INSTANCE.dbName);
            
            WebServer webServer = ProjectCardManagerServer.INSTANCE.webServer;
            PlayerInfo info = webServer.GetPlayerInfoByPassport( passport);
            if (info == null) {
                info = webServer.newPlayerInfo(dbHandler, passport);
            }
            if (info != null) {
                long now = System.currentTimeMillis();
                if (info.forbiddenEndLoginTime < now/1000) {
                    writeResponse(request, channel, "text/html; charset=UTF-8", "{\"result\":" + 0 + ",\"sessionid\":\"" + info.sessionId + "\",\"expiretime\":" + info.time + ",\"serverip\":\"" + server.serverConfig.externalHost + "\",\"serverport\":" + server.serverConfig.externalPort + "}", null);
                } else {
                    writeResponse(request, channel, "text/html; charset=UTF-8", "{\"result\":" + Consts.GETSESSION_ERR_FORBIDDEN_LOGIN + ",\"error\":\"forbidden login\"}", null);
                }
            } else {
                writeResponse(request, channel, "text/html; charset=UTF-8", "{\"result\":" + Consts.GETSESSION_ERR_SERVER + ",\"error\":\"system error\"}", null);
            }
            
            dbHandler.close();
        } else {
            writeResponse(request, channel, "text/html; charset=UTF-8", "{\"result\":" + Consts.PARAMETER_SERVER + ",\"error\":\"parameter error\"}", null);
        }
    }

    //服务器连接用来校验sessionid
    private void checkSession(Channel channel, HttpRequest request) {
        String contentStr = request.getContent().toString(org.jboss.netty.util.CharsetUtil.UTF_8);
        JSONObject jobj = (JSONObject) JSONValue.parse(contentStr);
        if (jobj != null) {
            String sessionid = (String) jobj.get("sessionId");
            //String clientIp = (String) jobj.get("clientIp");
            if (sessionid != null /*&& clientIp != null*/) {
                WebServer webServer = ProjectCardManagerServer.INSTANCE.webServer;
                PlayerInfo info = webServer.GetPlayerInfoBySessinoId(sessionid);
                if (info != null && info.forbiddenEndLoginTime < System.currentTimeMillis()/1000 ) {
                    writeResponse(request, channel, "text/html; charset=UTF-8", "{\"result\":" + 0 + ",\"id\":" + info.charId + ",\"passport\":\"" + info.userPassportId + "\",\"forbiddenendtalktime\":" + info.forbiddenEndTalkTime + ",\"forbiddenendlogintime\":" + info.forbiddenEndLoginTime + ",\"auth\":" + info.auth + "}", null);
                } else {
                    writeResponse(request, channel, "text/html; charset=UTF-8", "{\"result\":" + Consts.CHECK_SESSION_ERR_SESSION + ",\"error\":\"sessionid error\"}", null);
                }
            } else {
                writeResponse(request, channel, "text/html; charset=UTF-8", "{\"result\":" + Consts.PARAMETER_SERVER + ",\"error\":\"parameter error\"}", null);
            }
        } else {
            writeResponse(request, channel, "text/html; charset=UTF-8", "{\"result\":" + Consts.PARAMETER_SERVER + ",\"error\":\"paramater not json format\"}", null);
        }
    }
}
