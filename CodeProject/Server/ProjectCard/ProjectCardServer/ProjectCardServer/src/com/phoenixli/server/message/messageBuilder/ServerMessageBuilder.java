/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.message.messageBuilder;

import com.phoenixli.common.message.serverMessage.ExternalPlayerMessage;
import com.phoenixli.common.message.serverMessage.Message;
import com.phoenixli.common.message.serverMessage.Message.MessageType;
import com.phoenixli.server.message.serverMessage.CharNumMessage;
import com.phoenixli.server.message.serverMessage.CreateCharRetMessage;
import com.phoenixli.server.message.serverMessage.LoginMessage;
import org.jboss.netty.channel.Channel;

/**
 *
 * @author phoenix
 */
public class ServerMessageBuilder {
    public static Message buildClientConnectMessage(Channel channel) {
        return new ExternalPlayerMessage(MessageType.MAP_CLIENT_CONNECT,channel);
    }
    
    public static Message buildClientCloseMessage(Channel channel) {
        return new ExternalPlayerMessage(MessageType.MAP_CLIENT_CLOSE, channel);
    }
    
    public static Message buildLoginMessage(Channel channel, int playerId, String passport, boolean auth, int privilege,int endForbidTalkTime) {
        return new LoginMessage(channel, playerId, passport, auth, privilege, endForbidTalkTime);
    }
    
    public static Message buildCharNumMessage(int playerId, int charNum) {
        return new CharNumMessage(playerId, charNum);
    }
    
     public static Message buildCreateCharRetMessage(int playerId, int result) {
        return new CreateCharRetMessage(playerId, result);
    }
}
