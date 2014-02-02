/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.contSign;

import com.phoenix.protobuf.ExternalCommonProtocol.ContSignProto;
import com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto;
import com.phoenixli.config.ContSignConfig;
import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.actor.Human;
import com.phoenixli.server.message.messageBuilder.ServerToClientMessageBuilder;
import com.phoenixli.server.reward.CertainRewardsInfo;
import com.phoenixli.utils.Consts;

/**
 * 签到
 *
 * @author rachel
 */
public class ContSign {

    public Human human;
    public int lastOnlineDay;          //最后一天登录时间
    // 累计签到
    public int cumulativeNum;           // 累计天数
    public int cumulativeRewardNum;     // 可领取的奖励天数（客户端发领取时，一次性领取所有）
    // 连续签到
    public int consecutiveNum;          // 连续天数
    public int consecutiveReceiveFlag;  // 连续登陆奖励是否领取标志（位值0表示未领取）

    public ContSign(Human human, DBContSignProto dBContSignProto) {
        this.human = human;

        if (dBContSignProto != null) {
            lastOnlineDay = dBContSignProto.getLastOnlineDay();
            cumulativeNum = dBContSignProto.getCumulativeNum();
            cumulativeRewardNum = dBContSignProto.getCumulativeRewardNum();
            consecutiveNum = dBContSignProto.getConsecutiveNum();
            consecutiveReceiveFlag = dBContSignProto.getConsecutiveReceiveFlag();
        } else {
            lastOnlineDay = (int) ((ProjectCardServer.INSTANCE.getCurrentTime() + Consts.JET_LAG) / Consts.MILSECOND_ONE_DAY);
            cumulativeNum = 1;
            cumulativeRewardNum = 1;
            consecutiveNum = 1;
            consecutiveReceiveFlag = 0;
        }
    }

    // 写入玩家游戏数据库的签到数据
    public DBContSignProto buildDBContSignProto() {
        DBContSignProto.Builder builder = DBContSignProto.newBuilder();
        builder.setLastOnlineDay(lastOnlineDay);
        builder.setCumulativeNum(cumulativeNum);
        builder.setCumulativeRewardNum(cumulativeRewardNum);
        builder.setConsecutiveNum(consecutiveNum);
        builder.setConsecutiveReceiveFlag(consecutiveReceiveFlag);
        return builder.build();
    }

    // 发送给客户端的签到数据
    public ContSignProto buildContSignProto() {
        ContSignProto.Builder builder = ContSignProto.newBuilder();
        builder.setCumulativeNum(cumulativeNum);
        builder.setCumulativeRewardNum(cumulativeRewardNum);
        builder.setConsecutiveNum(consecutiveNum);
        builder.setConsecutiveReceiveFlag(consecutiveReceiveFlag);
        return builder.build();
    }
    
    public void receiveCumulativeSignReward() {
        if (cumulativeRewardNum > 0) {
            for (int i = cumulativeRewardNum - 1; i >= 0; i--) {
                int rewardId = cumulativeNum - i - 1;
                CertainRewardsInfo certainRewardsInfo = ContSignConfig.INSTANCE.cumulativeRewards[rewardId];

                human.digestCertainReward(certainRewardsInfo, Consts.SOUL_CHANGE_LOG_TYPE_CUMULATIVE_SIGN_REWARD, rewardId);
            }

            cumulativeRewardNum = 0;

            human.sendMessage(ServerToClientMessageBuilder.buildCumulativeRewardReceived());
        } else {
            System.err.println("human [" + human.charId + "] can't receive cumulative sign reward because no reward.");
        }
    }

    public void receiveConsecutiveSignReward(int day){
        if(day >= 0 && day < consecutiveNum){
            if ((~consecutiveReceiveFlag & (1 << day)) != 0) {  // 判断领取标志位
                CertainRewardsInfo certainRewardsInfo = ContSignConfig.INSTANCE.consecutiveRewards[day];
                human.digestCertainReward(certainRewardsInfo, Consts.SOUL_CHANGE_LOG_TYPE_CONSECUTIVE_SIGN_REWARD, day);

                // 设置领取标志位
                consecutiveReceiveFlag |= 1 << day;

                human.sendMessage(ServerToClientMessageBuilder.buildReceivedConsecutiveSignReward(day));
            } else {
                 System.err.println("human [" + human.charId + "] can't receive sign reward because of has received or not cont sign");
            }
        }else {
             System.err.println("human [" + human.charId + "] can't receive sign reward because of day outof array bounds");
        }
    }
   
    public void sign(boolean needSend){
        int curDay = (int) ((ProjectCardServer.INSTANCE.getCurrentTime() + Consts.JET_LAG) / Consts.MILSECOND_ONE_DAY);
        // 判断是否今天第一次登陆
        if(curDay > lastOnlineDay){ 
            // 增加累计登陆天数
            if (cumulativeNum < ContSignConfig.INSTANCE.cumulativeRewards.length) {
                cumulativeNum++;        // 累计登陆天数
                cumulativeRewardNum++;  // 当前可领奖天数
            }

            if(curDay > lastOnlineDay + 1){
                // 清除连续登陆
                consecutiveNum = 1;
                consecutiveReceiveFlag = 0;
            } else {
                consecutiveNum++;
                if (consecutiveNum > ContSignConfig.INSTANCE.consecutiveRewards.length) {
                    consecutiveNum = ContSignConfig.INSTANCE.consecutiveRewards.length;
                    consecutiveReceiveFlag = consecutiveReceiveFlag & ~(1 << (consecutiveNum-1));   // 将最后一天的领取标志设置为未领取
                }
            }
            
            lastOnlineDay = curDay;
        }
        if(needSend){
            human.sendMessage(ServerToClientMessageBuilder.buildContSign(buildContSignProto()));
        }
    }
}
