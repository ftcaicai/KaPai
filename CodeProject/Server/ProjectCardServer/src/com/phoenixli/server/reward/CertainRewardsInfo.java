/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.reward;

import com.phoenix.protobuf.ExternalCommonProtocol.RewardItemProto;
import com.phoenix.protobuf.ExternalCommonProtocol.RewardProto;
import java.util.List;

/**
 *
 * @author rachel
 */
public class CertainRewardsInfo {

    public CertainRewardItemInfo[] items;      // 掉落物品

    public CertainRewardsInfo() {
    }

    public CertainRewardsInfo(RewardProto rewardProto) {
        int itemNum = rewardProto.getItemsCount();
        if (itemNum > 0) {
            items = new CertainRewardItemInfo[itemNum];
            List<RewardItemProto> itemProtos = rewardProto.getItemsList();
            int i = 0;
            for (RewardItemProto itemProto : itemProtos) {
                items[i] = new CertainRewardItemInfo(itemProto);
                i++;
            }
        }
    }

    public RewardProto buildRewardProto() {
        RewardProto.Builder rewardBuilder = RewardProto.newBuilder();

        if (items != null) {
            for (CertainRewardItemInfo itemInfo : items) {
                RewardItemProto itemBuilder = itemInfo.buildRewardItemProto();
                rewardBuilder.addItems(itemBuilder);
            }
        }

        return rewardBuilder.build();
    }
}
