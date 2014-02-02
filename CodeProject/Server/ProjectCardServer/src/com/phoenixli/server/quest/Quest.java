/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.quest;

import com.phoenix.protobuf.ExternalCommonProtocol.QuestProto;
import com.phoenixli.server.actor.Human;
import com.phoenixli.server.message.messageBuilder.ServerToClientMessageBuilder;
import com.phoenixli.utils.Consts;

/**
 *
 * @author rachel
 */
public class Quest {
    public final Human human;
    public final QuestStaticInfo staticInfo;

    public boolean finished;
    
    public Quest(Human human, QuestStaticInfo staticInfo) {
        this.human = human;
        this.staticInfo = staticInfo;
    }
    
    public QuestProto buildQuestProto() {
        QuestProto.Builder builder = QuestProto.newBuilder();
        builder.setQuestId(staticInfo.id);
        builder.setFinished(finished);
        return builder.build();
    }
    
    // 通知有升级任务已完成
    public void levelUp(int level) {
        assert (staticInfo.type == Consts.QUEST_TYPE_LEVEL);
        if (!finished && (level >= staticInfo.value1)) {
            finished = true;
            human.sendMessage(ServerToClientMessageBuilder.buildQuestFinished(staticInfo.id));
        }
    }
    
    // 通知有关卡任务已完成
    public void finishStage(int stageId, boolean isBigStage, boolean needSend) {
        assert (staticInfo.type == Consts.QUEST_TYPE_STAGE);
        if (!finished && (isBigStage == staticInfo.isBigStage) && (stageId >= staticInfo.value1)) {
            finished = true;
            if (needSend) {
                human.sendMessage(ServerToClientMessageBuilder.buildQuestFinished(staticInfo.id));
            }
        }
    }
    
}
