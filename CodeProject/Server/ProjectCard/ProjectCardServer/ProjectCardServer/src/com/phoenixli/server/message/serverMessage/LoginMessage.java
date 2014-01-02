/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.message.serverMessage;

import com.phoenixli.common.message.serverMessage.ExternalPlayerMessage;
import org.jboss.netty.channel.Channel;

/**
 *
 * @author phoenix
 */
public class LoginMessage extends ExternalPlayerMessage{
    public final int playerId;
    public final boolean auth;
    public final int privilege;
    public final int endForbidTalkTime;
    public final String passport;

    public LoginMessage(Channel channel, int playerId,String passport,  boolean auth, int privilege, int endForbidTalkTime) {
        super(MessageType.MAP_LOGIN,channel);

        this.playerId = playerId;
        this.passport = passport;
        this.auth = auth;
        this.privilege = privilege;
        this.endForbidTalkTime = endForbidTalkTime;
    }
}
