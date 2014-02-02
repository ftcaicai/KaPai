/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.player.state;

import com.phoenixli.common.message.serverMessage.Message;
import com.phoenixli.common.message.serverMessage.Message.MessageType;
import com.phoenixli.server.actor.Human;
import com.phoenixli.server.message.serverMessage.ContSignConsecutiveRewardReceiveMessage;
import com.phoenixli.server.message.serverMessage.VipGiftReceiveMessage;
import com.phoenixli.server.player.MapPlayer;
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
        MapPlayer p = (MapPlayer) player;
        int playerId = p.getId();
        Human human = p.human;

        MessageType msgType = message.getType();
        switch (msgType) {
            case MAP_CONTSIGN_CONSECUTIVE_REWARD_RECEIVE: {
                human.contSign.receiveCumulativeSignReward();
                return true;
            }
            case MAP_CONTSIGN_CUMULATIVE_REWARD_RECEIVE: {
                ContSignConsecutiveRewardReceiveMessage contSignReceiveConsecutiveSignRewardMessage = (ContSignConsecutiveRewardReceiveMessage) message;
                human.contSign.receiveConsecutiveSignReward(contSignReceiveConsecutiveSignRewardMessage.day);
                return true;
            }
            case MAP_VIP_GIFT_RECEIVE: {
                VipGiftReceiveMessage vipGiftReceiveMessage = (VipGiftReceiveMessage) message;
                human.vipGift.receive(vipGiftReceiveMessage.vip);
                return true;
            }
        }
        return true;
    }
}
