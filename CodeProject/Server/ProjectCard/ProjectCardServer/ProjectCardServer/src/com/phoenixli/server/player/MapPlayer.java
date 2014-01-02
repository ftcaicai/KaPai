/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.player;

import com.phoenixli.common.channel.ChannelContext;
import com.phoenixli.server.actor.Human;
import com.phoenixli.server.message.serverMessage.LoginMessage;
import com.phoenixli.server.player.state.Login1PlayerState;
import org.jboss.netty.channel.Channel;

/**
 *
 * @author rachel
 */
public class MapPlayer extends AbstractPlayer{
    public final String passport;
    public final ChannelContext channelContext;
    public Human human;
    public boolean auth;
    public int privilege;
    public long endForbidTalkTime;

    public LoginMessage loginMessage;   // 重复登陆时，记录后登陆的消息，等待该MapPlayer对象被删除的时候再处理
    
        public MapPlayer(int id, String passport, boolean auth, int privilege, long endForbidTalkTime, Channel channel) {
        super(id, Login1PlayerState.INSTANCE);
        this.passport = passport;
        this.auth = auth;
        this.privilege = privilege;
        this.endForbidTalkTime = endForbidTalkTime*1000; //秒转成毫秒
        this.channelContext = new ChannelContext();
        this.channelContext.setChannel(channel);
    }
}
