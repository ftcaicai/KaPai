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
public class HumanLevelsConfig {
    public int maxLevel;    // 最大等级数
    public HumanLevelTemplate[] levelTemplates;  // 等级配置
    
    public static final HumanLevelsConfig INSTANCE = JSONHelper.parseFileNoException(ProjectCardServer.INSTANCE.serverId + "/" + Consts.HUMAN_LEVELS_CONFIG_FILEPATH, HumanLevelsConfig.class);
    
    private HumanLevelsConfig() {
    }
}
