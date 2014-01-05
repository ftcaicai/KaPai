/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.reward;

/**
 * 不确定奖励项：按一定概率掉落处于最小和最大数量间的数量的指定物品
 * @author rachel
 */
public class UncertainRewardItemInfo {
    public CertainRewardItemInfo itemInfo;  // 掉落信息
    public int propability;          // 掉落概率(上限)
}
