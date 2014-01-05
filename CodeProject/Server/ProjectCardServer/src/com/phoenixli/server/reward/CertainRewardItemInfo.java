/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.reward;

import com.phoenix.protobuf.ExternalCommonProtocol.RewardItemProto;
import com.phoenixli.utils.Consts;

/**
 *
 * @author rachel
 */
public class CertainRewardItemInfo {
    public int itemType;                // 奖品类型（0：卡片 1：物品）
    public int itemId;                  // 奖品id
    public int level;                   // 等级（当类型为卡片时有效）
    public int num;                     // 数量
    
    public CertainRewardItemInfo() {

    }

    public CertainRewardItemInfo(RewardItemProto itemProto) {
        itemType = itemProto.getType();
        itemId = itemProto.getId();
        level = itemProto.getLevel();
        num = itemProto.getNum();
    }
    
    public RewardItemProto buildRewardItemProto() {
        RewardItemProto.Builder builder = RewardItemProto.newBuilder();

        builder.setType(itemType);

        switch (itemType) {
            case Consts.REWARD_ITEM_TYPE_CARD: {
                builder.setId(itemId);
                builder.setLevel(level);
                break;
            }
            case Consts.REWARD_ITEM_TYPE_ITEM: {
                builder.setId(itemId);
                builder.setNum(num);
                break;
            }
            case Consts.REWARD_ITEM_TYPE_GOLD:
            case Consts.REWARD_ITEM_TYPE_SILVER: 
            case Consts.REWARD_ITEM_TYPE_DIAMOND:
            case Consts.REWARD_ITEM_TYPE_POPULARITY:
            case Consts.REWARD_ITEM_TYPE_EXPERIENCE:
            case Consts.REWARD_ITEM_TYPE_ENERGY: {
                builder.setNum(num);
                break;
            }
        }

        return builder.build();
    }    
}
