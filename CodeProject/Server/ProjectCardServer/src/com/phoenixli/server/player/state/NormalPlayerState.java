/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.player.state;

import com.phoenixli.common.message.serverMessage.Message;
import com.phoenixli.server.player.Player;

/**
 *
 * @author rachel
 */
public class NormalPlayerState implements PlayerState {
     public final static NormalPlayerState INSTANCE = new NormalPlayerState();

    private NormalPlayerState() {
    }

    @Override
    public boolean handleMessage(Player player, Message message) {
        return true;
    }
}
