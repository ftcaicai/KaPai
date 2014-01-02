/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.listenerServer;

/**
 *
 * @author rachel
 */
public class PlayerConnectServer extends AbstractServer{
    //Singleton
    public static PlayerConnectServer INSTANCE = new PlayerConnectServer();

    private PlayerConnectServer() {
    }
}
