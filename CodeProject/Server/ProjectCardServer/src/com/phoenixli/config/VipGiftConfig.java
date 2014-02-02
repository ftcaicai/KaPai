/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.config;

import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.reward.CertainRewardsInfo;
import com.phoenixli.utils.Consts;
import com.phoenixli.utils.JSONHelper;

/**
 *
 * @author rachel
 */
public class VipGiftConfig {
    public CertainRewardsInfo[] rewardInfos;

    public static VipGiftConfig INSTANCE = JSONHelper.parseFileNoException(ProjectCardServer.INSTANCE.serverId + "/" + Consts.VIP_GIFT_CONFIG_FILEPATH, VipGiftConfig.class);

    private VipGiftConfig(){
    }
}
