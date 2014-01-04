/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server;

import com.phoenixli.config.ServerConfig;

/**
 *
 * @author rachel
 */
public class Server {
    public ServerConfig serverConfig;
    
    public Server(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }
    
    public int getServerId() {
        return serverConfig.serverId;
    }
}
