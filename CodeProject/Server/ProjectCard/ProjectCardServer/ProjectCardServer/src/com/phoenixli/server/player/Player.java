/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.player;

import com.phoenixli.common.message.serverMessage.Message;

/**
 *
 * @author rachel
 */
public interface Player {
    public int getId();
    public long getLastActiveTime();
    public boolean handleMessage(Message message);
}
