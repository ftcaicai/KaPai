/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.config;

import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.card.CardFactories;
import com.phoenixli.server.card.CardFactory;
import com.phoenixli.server.card.CardStaticInfo;
import com.phoenixli.utils.Consts;
import com.phoenixli.utils.JSONHelper;

/**
 *
 * @author rachel
 */
public class CardsConfig {
    public CardStaticInfo[] cardStaticInfos;

    public static CardsConfig INSTANCE =  JSONHelper.parseFileNoException(ProjectCardServer.INSTANCE.serverId + "/" + Consts.CARDS_CONFIG_FILEPATH, CardsConfig.class);
    
    public void buildCardFactories() {
        CardFactories cardFactories = CardFactories.INSTANCE;
        for (CardStaticInfo cardStaticInfo : cardStaticInfos) {
            CardFactory factory = new CardFactory(cardStaticInfo);

            cardFactories.addCardFactory(factory);
        }
    }
}
