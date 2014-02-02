/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.config;

import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.quest.QuestStaticInfo;
import com.phoenixli.server.quest.QuestsInfo;
import com.phoenixli.utils.Consts;
import com.phoenixli.utils.JSONHelper;

/**
 *
 * @author rachel
 */
public class QuestsConfig {    
    public QuestStaticInfo[] questStaticInfos;
    
    public static QuestsConfig INSTANCE = JSONHelper.parseFileNoException(ProjectCardServer.INSTANCE.serverId + "/" + Consts.QUESTS_CONFIG_FILEPATH, QuestsConfig.class);
    
    private QuestsConfig() {        
    }
    
    public void buildQuestsConfig() {
        if ((questStaticInfos != null) && (questStaticInfos.length > 0)) {
            for (QuestStaticInfo staticInfo : questStaticInfos) {
                QuestsInfo.INSTANCE.addQuestStaticInfo(staticInfo);
            }
        }
    }
}
