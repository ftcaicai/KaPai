/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.card;

import com.phoenix.protobuf.ExternalCommonProtocol.CardProto;
import com.phoenixli.server.actor.Human;

/**
 *
 * @author rachel
 */
public class Card {
    public final Human human;
    public final int id;                    // 卡片实例id
    public CardStaticInfo staticInfo;
    public int level;                       // 卡片等级
    public int experience;                  // 卡片当前等级经验

    public int place;                       // 卡片所在位置（0无 1阵型中 ...）
    
     public Card(Human human, CardStaticInfo staticInfo, int id, int level, int experience) {
        this.human = human;
        this.staticInfo = staticInfo;
        this.id = id;
        this.level = level;
        this.experience = experience;
    }

    public CardProto buildCardProto() {
        CardProto.Builder builder = CardProto.newBuilder();
        builder.setId(staticInfo.id);
        builder.setInstId(id);
        builder.setLevel(level);
        builder.setExperience(experience);

        return builder.build();
    }
}
