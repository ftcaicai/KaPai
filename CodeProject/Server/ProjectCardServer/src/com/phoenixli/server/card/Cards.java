/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.card;

import com.phoenix.protobuf.ExternalCommonProtocol.CardProto;
import com.phoenix.protobuf.ExternalCommonProtocol.CardsProto;
import com.phoenixli.server.actor.Human;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author rachel
 */
public class Cards {
    public final Human human;
    public final TreeMap<Integer, Card> cards = new TreeMap<Integer, Card>();
    public int nextCardInstId;

    public Cards(Human human, CardsProto cardsProto) {
        this.human = human;

        if (cardsProto != null) {
            List<CardProto> cardProtos = cardsProto.getCardsList();
            for (CardProto cardProto : cardProtos) {
                cards.put(cardProto.getInstId(), CardFactories.INSTANCE.getCardFactory(cardProto.getId()).buildCard(human, cardProto.getInstId(), cardProto.getLevel(), cardProto.getExperience()));
            }

            this.nextCardInstId = cardsProto.getNextCardInstId();
        } else {
            this.nextCardInstId = 1;
        }
    }

    public CardsProto buildCardsProto() {
        CardsProto.Builder builder1 = CardsProto.newBuilder();

        builder1.setNextCardInstId(nextCardInstId);

        for (Card card : cards.values()) {
            builder1.addCards(card.buildCardProto());
        }

        return builder1.build();
    }
    
    
}
