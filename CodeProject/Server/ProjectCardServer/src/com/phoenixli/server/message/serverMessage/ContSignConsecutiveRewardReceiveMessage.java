/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.message.serverMessage;

import com.phoenixli.common.message.serverMessage.InternalPlayerMessage;

/**
 *
 * @author rachel
 */
public class ContSignConsecutiveRewardReceiveMessage extends InternalPlayerMessage{

    public final int day;

    public ContSignConsecutiveRewardReceiveMessage(int playerId, int day) {
        super(MessageType.MAP_CONTSIGN_CONSECUTIVE_REWARD_RECEIVE, playerId);
        this.day = day;
    }
    
}
