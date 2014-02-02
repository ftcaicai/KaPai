/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.quest;

import com.phoenixli.server.reward.CertainRewardsInfo;

/**
 *
 * @author rachel
 */
public class QuestStaticInfo {
    public int id;                          // 任务id
    public int type;                        // 任务类型（1过关 2升级）
    public boolean isBigStage;              // 是否精英关卡（当类型为过关任务是有意义）
    public int value1;                      // 值1（过关任务表示关卡号、升级任务表示等级）

    public CertainRewardsInfo reward;       // 任务奖品
    public int[] nextQuests;                // 后续任务id
}
