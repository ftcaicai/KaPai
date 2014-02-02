/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.config;

import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.stage.StageStaticInfo;
import com.phoenixli.utils.Consts;
import com.phoenixli.utils.JSONHelper;

/**
 *
 * @author rachel
 */
public class StagesConfig {
    public StageStaticInfo[] normalStageStaticInfos;
    public StageStaticInfo[] bigStageStaticInfos;
    public StageStaticInfo[] activityStageStaticInfos;
    
    public static final StagesConfig INSTANCE = JSONHelper.parseFileNoException(ProjectCardServer.INSTANCE.serverId + "/" + Consts.STAGES_CONFIG_FILEPATH, StagesConfig.class);
    
    private StagesConfig() {        
    }
}
