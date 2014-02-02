/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.quest;

import java.util.HashMap;

/**
 *
 * @author rachel
 */
public class QuestsInfo {
    public final HashMap<Integer, QuestStaticInfo> questStaticInfos = new HashMap<>();

    public static final QuestsInfo INSTANCE = new QuestsInfo();

    private QuestsInfo() {
    }
    
    public void addQuestStaticInfo(QuestStaticInfo questStaticInfo) {
        assert (!questStaticInfos.containsKey(questStaticInfo.id));
        questStaticInfos.put(questStaticInfo.id, questStaticInfo);
    }

    public QuestStaticInfo getQuestStaticInfo(int id) {
        QuestStaticInfo questStaticInfo = questStaticInfos.get(id);
        if (questStaticInfo == null) {
            System.err.println("Quest["+id+"] static info not found.");
        }
        return questStaticInfo;
    }
}
