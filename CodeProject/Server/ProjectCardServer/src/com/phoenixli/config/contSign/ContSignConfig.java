/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.config.contSign;

import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.reward.CertainRewardsInfo;
import com.phoenixli.utils.Consts;
import com.phoenixli.utils.JSONHelper;

/**
 *
 * @author rachel
 */
public class ContSignConfig {
    public CertainRewardsInfo[] cumulativeRewards;          // 累计登陆奖励
    public CertainRewardsInfo[] consecutiveRewards;         // 连续登陆奖励
    //public LivenessRewardTemplate[] livenessRewards;        // 活跃度奖励
    //public LivenessTargetTemplate livenessTarget;           // 活跃度目标

    public static ContSignConfig INSTANCE = JSONHelper.parseFileNoException(ProjectCardServer.INSTANCE.serverId + "/" + Consts.CONT_SIGN_CONFIG_FILEPATH, ContSignConfig.class);
}
