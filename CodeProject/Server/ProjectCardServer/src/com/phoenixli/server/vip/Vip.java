/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.vip;

import com.phoenix.protobuf.ExternalCommonProtocol.VipProto;
import com.phoenixli.config.VipConfig;
import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.actor.Human;
import com.phoenixli.server.message.messageBuilder.ServerToClientMessageBuilder;
import com.phoenixli.utils.Consts;

/**
 *
 * @author rachel
 */
public class Vip {

    public Human human;
    public int level;
    public int exp;
    public int lastDayExp;          // 最后充值日累计充值
    public int lastExpDay;          // 最后一次充值日期

    public VipProto buildVipProto() {
        VipProto.Builder builder = VipProto.newBuilder();
        builder.setExp(exp);
        builder.setLevel(level);
        builder.setLastDayExp(lastDayExp);
        builder.setLastExpDay(lastExpDay);
        return builder.build();
    }

    public Vip(Human human, int level, int exp, int lastDayExp, int lastExpDay) {
        this.human = human;

        this.exp = exp;
        this.level = level;
        this.lastDayExp = lastDayExp;
        this.lastExpDay = lastExpDay;

        caculateLevel(exp);
    }

    // 根据VIP经验计算VIP等级
    private void caculateLevel(int exp) {
        int[] levelUpExps = VipConfig.INSTANCE.vipLevelUpConfig;
        for (int lv = level; lv < levelUpExps.length; lv++) {
            if (exp < levelUpExps[lv]) {
                level = (level < lv) ? lv : level;
                return;
            }
        }

        level = levelUpExps.length;
    }

    // VIP经验增加
    public void increaseVipExp(int num, boolean needSend) {
        if (num > 0) {
            exp += num;

            int currDay = (int) ((ProjectCardServer.INSTANCE.getCurrentTime() + Consts.JET_LAG) / Consts.MILSECOND_ONE_DAY);
            if (currDay > lastExpDay) {
                lastDayExp = 0;
            }

            lastDayExp += num;
            lastExpDay = currDay;

            caculateLevel(exp);
            if (needSend) {
                human.sendMessage(ServerToClientMessageBuilder.buildVipChange(buildVipProto()));
            }
        }
    }

    // 清空玩家VIP经验
    public void clearVipLevel(boolean needSend) {
        exp = 0;
        level = 0;
        caculateLevel(exp);

        if (needSend) {
            human.sendMessage(ServerToClientMessageBuilder.buildVipChange(buildVipProto()));
        }
    }
}
