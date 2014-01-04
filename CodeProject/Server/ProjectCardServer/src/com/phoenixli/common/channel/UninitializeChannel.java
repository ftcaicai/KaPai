/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.common.channel;

import com.phoenixli.server.ProjectCardServer;
import org.jboss.netty.channel.Channel;

/**
 *
 * @author rachel
 */
public class UninitializeChannel {
    public long createTime;
    public Channel channel;

    public UninitializeChannel(Channel channel) {
        this.channel = channel;
        createTime = ProjectCardServer.INSTANCE.getCurrentTime();
    }
}
