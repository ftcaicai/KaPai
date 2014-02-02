/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.config;

import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.utils.Consts;
import com.phoenixli.utils.JSONHelper;

/**
 *
 * @author rachel
 */
public class VipConfig {
    public static VipConfig INSTANCE = JSONHelper.parseFileNoException(ProjectCardServer.INSTANCE.serverId + "/" + Consts.VIP_CONFIG_FILEPATH, VipConfig.class);
    
    public int[] vipLevelUpConfig = {100, 500, 1000, 2000, 50000, 10000, 20000, 50000, 100000, 200000, 500000, 1000000};
    public int[] vipCardSlotNums = {30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150};
}
