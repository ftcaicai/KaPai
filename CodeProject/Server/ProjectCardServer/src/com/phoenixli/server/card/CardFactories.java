/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.card;

import java.util.HashMap;

/**
 *
 * @author rachel
 */
public class CardFactories {
    public final HashMap<Integer, CardFactory> factories = new HashMap<Integer, CardFactory>();

    public static final CardFactories INSTANCE = new CardFactories();

    private CardFactories() {
    }

    public void addCardFactory(CardFactory cardFactory) {
        assert (!factories.containsKey(cardFactory.staticInfo.id));
        factories.put(cardFactory.staticInfo.id, cardFactory);
    }

    public CardFactory getCardFactory(int id) {
        CardFactory cardFactory = factories.get(id);
        if (cardFactory == null) {
            System.err.println("Card[" + id + "] info not found.");
        }
        return cardFactory;
    }
}
