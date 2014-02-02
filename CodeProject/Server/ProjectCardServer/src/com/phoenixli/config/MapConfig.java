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
public class MapConfig {    
    public int bigStageEnterNumPerDay = 2;          // 每一精英副本每日进入次数
    public int initQuestId = 0;                     // 初始任务号
    
    
    public static MapConfig INSTANCE = JSONHelper.parseFileNoException(ProjectCardServer.INSTANCE.serverId + "/" + Consts.MAP_CONFIG_FILEPATH, MapConfig.class);
}
