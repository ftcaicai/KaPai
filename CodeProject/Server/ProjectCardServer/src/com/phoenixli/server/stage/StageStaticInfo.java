/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.stage;

import com.phoenixli.server.reward.RewardInfo;

/**
 *
 * @author rachel
 */
public class StageStaticInfo {
    public int id;                      // 关卡模板ID（注意：不同类型关卡id空间独立，按数组下标从0开始编号）
    public int type;                    // 关卡类型（0：普通 1：精英 2：活动）
    public int energyCost;              // 体力消耗
    public int[] battleIds;             // 场景带的战斗
    public RewardInfo reward;           // 关卡奖励
    public int levelLimit;              // 等级限制
    public int normalId;                // 前置普通关卡(关卡类型是精英关卡时有效)
}
