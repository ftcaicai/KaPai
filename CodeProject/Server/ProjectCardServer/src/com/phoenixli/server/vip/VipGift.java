/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.vip;

import com.phoenixli.config.VipGiftConfig;
import com.phoenixli.server.actor.Human;
import com.phoenixli.server.message.messageBuilder.ServerToClientMessageBuilder;
import com.phoenixli.utils.Consts;

/**
 *
 * @author rachel
 */
public class VipGift {
    public final Human human;
    public int receiveFlag;              //是否领取，第0位表示vip1的奖励是否领取

    public VipGift(Human human, int receiveFlag) {
        this.human = human;
        this.receiveFlag = receiveFlag;
    }

    public void receive(int vip) {
        if (vip > 0 && vip <= human.vip.level) {
            if ((receiveFlag & (1 << (vip - 1))) == 0) {
                human.digestCertainReward(VipGiftConfig.INSTANCE.rewardInfos[vip - 1], Consts.SOUL_CHANGE_LOG_TYPE_VIP_REWARD, vip);
                receiveFlag |= (1 << (vip - 1));
                human.sendMessage(ServerToClientMessageBuilder.buildVipReceived(receiveFlag));
            } else {
                System.err.println("Player[" + human.charId + "] can't get receive vip gift[" + vip + "] reward because has received.");
            }
        } else {
            System.err.println("Player[" + human.charId + "] can't get receive vip gift[" + vip + "] reward because of error vip.");
        }
    }
}
