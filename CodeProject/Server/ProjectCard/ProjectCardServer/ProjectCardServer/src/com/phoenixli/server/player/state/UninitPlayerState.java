/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.player.state;

import com.phoenixli.common.message.serverMessage.Message;
import com.phoenixli.common.message.serverMessage.Message.MessageType;
import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.player.MapPlayer;
import com.phoenixli.server.player.Player;

/**
 *
 * @author rachel
 */
public class UninitPlayerState implements PlayerState {
     public final static UninitPlayerState INSTANCE = new UninitPlayerState();

    private UninitPlayerState() {
    }

    @Override
    public boolean handleMessage(Player player, Message message) {
        MapPlayer p = (MapPlayer) player;
        int playerId = p.getId();
        MessageType msgType = message.getType();
        switch (msgType) {
            case MAP_GET_CHAR_DETAIL_INFO_RET: {
                ProjectCardServer.INSTANCE.enterGame(p);
                p.state = NormalPlayerState.INSTANCE;

                return true;
            }
            default: {
                // 消息异常（不作处理）,记录日志
                System.err.println("UninitState handle error message type[" + msgType + "].");
                return false;
            }
        }
    }
}
