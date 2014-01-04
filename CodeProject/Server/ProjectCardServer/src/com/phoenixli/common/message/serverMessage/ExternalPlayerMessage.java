/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.common.message.serverMessage;

import org.jboss.netty.channel.Channel;

/**
 * 从游戏客户端来的消息
 * @author phoenix
 */
public class ExternalPlayerMessage extends SimpleMessage {

    // The channel which message is received from
    public final Channel channel;     

    public ExternalPlayerMessage(MessageType type, Channel channel) {
        super(type);
        this.channel = channel;
    }
}
