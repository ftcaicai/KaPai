/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.quest;

import com.phoenix.protobuf.ExternalCommonProtocol.QuestProto;
import com.phoenix.protobuf.ExternalCommonProtocol.QuestsProto;
import com.phoenixli.config.MapConfig;
import com.phoenixli.server.actor.Human;
import com.phoenixli.server.message.messageBuilder.ServerToClientMessageBuilder;
import com.phoenixli.utils.Consts;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author rachel
 */
public class Quests {

    public final Human human;
    // 已接过关任务列表
    public TreeMap<Integer, Quest> stageQuests;
    // 已接升级任务列表
    public TreeMap<Integer, Quest> levelQuests;

    public Quests(Human human, QuestsProto questsProto) {
        this.human = human;

        if (questsProto != null) {
            List<QuestProto> questProtos = questsProto.getQuestsList();
            for (QuestProto questProto : questProtos) {
                int questId = questProto.getQuestId();
                QuestStaticInfo staticInfo = QuestsInfo.INSTANCE.getQuestStaticInfo(questId);
                if (staticInfo != null) {
                    Quest quest = new Quest(human, staticInfo);
                    quest.finished = questProto.getFinished();
                    addAppliedQuest(quest);
                }
            }
        } else {
            QuestStaticInfo staticInfo = QuestsInfo.INSTANCE.getQuestStaticInfo(MapConfig.INSTANCE.initQuestId);
            Quest quest = new Quest(human, staticInfo);
            addAppliedQuest(quest);
        }
    }

    private void addAppliedQuest(Quest quest) {
        QuestStaticInfo staticInfo = quest.staticInfo;
        switch (staticInfo.type) {
            case Consts.QUEST_TYPE_STAGE: {
                if (stageQuests == null) {
                    stageQuests = new TreeMap<Integer, Quest>();
                }

                // 若关卡已经通过则将任务设为已完成
                if ((!staticInfo.isBigStage && (human.stages.currentNormalStageId > staticInfo.value1))
                        || (staticInfo.isBigStage && (human.stages.currentBigStageId > staticInfo.value1))) {
                    quest.finished = true;
                }

                stageQuests.put(staticInfo.id, quest);
                break;
            }
            case Consts.QUEST_TYPE_LEVEL: {
                if (levelQuests == null) {
                    levelQuests = new TreeMap<Integer, Quest>();
                }

                // 若等级已达到任务目标等级，将任务状态改为已完成
                if (human.charLevel >= staticInfo.value1) {
                    quest.finished = true;
                }

                levelQuests.put(staticInfo.id, quest);
                break;
            }
            default: {
                System.err.println("Player[" + human.charId + "] can't init Quest[" + staticInfo.id + "] because unknown quest type[" + staticInfo.type + "].");
            }
        }
    }

    private Quest getAppliedQuest(QuestStaticInfo questStaticInfo) {
        switch (questStaticInfo.type) {
            case Consts.QUEST_TYPE_STAGE: {
                if (stageQuests != null) {
                    return stageQuests.get(questStaticInfo.id);
                }
                break;
            }
            case Consts.QUEST_TYPE_LEVEL: {
                if (levelQuests != null) {
                    return levelQuests.get(questStaticInfo.id);
                }
                break;
            }
            default: {
                System.err.println("Unknown quest[" + questStaticInfo.id + "] type[" + questStaticInfo.type + "].");
                break;
            }
        }
        return null;
    }

    // 注意：在调用removeAppliedQuest前已经能确定quest存在
    private void removeAppliedQuest(Quest quest) {
        QuestStaticInfo staticInfo = quest.staticInfo;
        switch (staticInfo.type) {
            case Consts.QUEST_TYPE_STAGE: {
                stageQuests.remove(staticInfo.id);

                if (stageQuests.isEmpty()) {
                    stageQuests = null;
                }
                break;
            }
            case Consts.QUEST_TYPE_LEVEL: {
                levelQuests.remove(staticInfo.id);

                if (levelQuests.isEmpty()) {
                    levelQuests = null;
                }
                break;
            }
            default: {
                System.err.println("Unknown quest[" + staticInfo.id + "] type[" + staticInfo.type + "].");
                break;
            }
        }
    }

    // 提交任务
    public void submitQuest(int questId) {
        // 判断任务是否已接取
        QuestStaticInfo questStaticInfo = QuestsInfo.INSTANCE.getQuestStaticInfo(questId);
        if (questStaticInfo != null) {
            Quest quest = getAppliedQuest(questStaticInfo);
            if (quest != null) {
                // 判断任务是否已完成
                if (quest.finished) {
                    // 领取任务奖励
                    human.digestCertainReward(questStaticInfo.reward, Consts.SOUL_CHANGE_LOG_TYPE_QUEST, questId);

                    // 交任务
                    removeAppliedQuest(quest);

                    human.sendMessage(ServerToClientMessageBuilder.buildQuestSubmitted(questId));

                    // 开启后续任务
                    if ((questStaticInfo.nextQuests != null) && (questStaticInfo.nextQuests.length > 0)) {
                        LinkedList<Quest> newQuests = new LinkedList<Quest>();
                        for (int nextQuestId : questStaticInfo.nextQuests) {
                            QuestStaticInfo newQuestStaticInfo = QuestsInfo.INSTANCE.getQuestStaticInfo(nextQuestId);
                            Quest newQuest = new Quest(human, newQuestStaticInfo);
                            addAppliedQuest(newQuest);

                            newQuests.add(newQuest);
                        }

                        human.sendMessage(ServerToClientMessageBuilder.buildQuestAddList(newQuests));
                    }
                } else {
                    System.err.println("Player[" + human.charId + "] can't submit Quest[" + questId + "] because it isn't finished.");
                }
            } else {
                System.err.println("Player[" + human.charId + "] can't submit Quest[" + questId + "] because haven't applied.");
            }
        } else {
            System.err.println("Player[" + human.charId + "] can't submit Quest[" + questId + "] because quest info not found.");
        }
    }

    // 角色升级
    public void levelUp(int level) {
        if (levelQuests != null) {
            for (Quest quest : levelQuests.values()) {
                if (quest.staticInfo.type == Consts.QUEST_TYPE_LEVEL) {
                    quest.levelUp(level);
                } else {
                    System.err.println("Player[" + human.charId + "] level up quests list contain wrong quest[" + quest.staticInfo.id + "].");
                }
            }
        }
    }
    
    // 过关
    public void finishStage(int stageId, boolean isBigStage, boolean needSend) {
        if (stageQuests != null) {
            for (Quest quest : stageQuests.values()) {
                if (quest.staticInfo.type == Consts.QUEST_TYPE_STAGE) {
                    quest.finishStage(stageId, isBigStage, needSend);
                } else {
                    System.err.println("Player[" + human.charId + "] finish stage quests list contain wrong quest[" + quest.staticInfo.id + "].");
                }
            }
        }
    }
}
