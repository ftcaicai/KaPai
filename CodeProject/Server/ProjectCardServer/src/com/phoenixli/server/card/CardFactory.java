/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.card;

import com.phoenixli.server.actor.Human;

/**
 *
 * @author rachel
 */
public class CardFactory {

    public final CardStaticInfo staticInfo;

    public CardFactory(CardStaticInfo staticInfo) {
        this.staticInfo = staticInfo;
    }

    public Card buildCard(Human human, int instId, int level, int experience) {
        return new Card(human, staticInfo, instId, level, experience);
    }
}
