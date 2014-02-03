/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.item;

import com.phoenix.protobuf.ExternalCommonProtocol.ItemProto;
import com.phoenix.protobuf.ExternalCommonProtocol.ItemsProto;
import com.phoenixli.server.actor.Human;
import com.phoenixli.server.message.messageBuilder.ServerToClientMessageBuilder;
import com.phoenixli.utils.IdValue;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author rachel
 */
public class Items {
    public Human human;
    public final TreeMap<Integer, Integer> items = new TreeMap<Integer, Integer>();
    
    public Items(Human human, ItemsProto itemsProto) {
        this.human = human;

        if (itemsProto != null) {
            List<ItemProto> itemProtos = itemsProto.getItemsList();
            for (ItemProto itemProto : itemProtos) {
                items.put(itemProto.getId(), itemProto.getNum());
            }
        }
    }

    public ItemsProto buildItemsProto() {
        ItemsProto.Builder builder1 = ItemsProto.newBuilder();

        for (Iterator<Map.Entry<Integer, Integer>> iter = items.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry<Integer, Integer> entry = iter.next();
            ItemProto.Builder builder2 = ItemProto.newBuilder();
            builder2.setId(entry.getKey());
            builder2.setNum(entry.getValue());

            builder1.addItems(builder2);
        }

        return builder1.build();
    }

    public void addItem(int itemId, int addnum, boolean needSend) {
        Integer numI = items.get(itemId);
        int num = ((numI != null)?numI:0) + addnum;
        items.put(itemId, num);

        if (needSend) {
            human.sendMessage(ServerToClientMessageBuilder.buildItemNumChange(itemId, num));
        }
    }

    public int getItemNum(int itemId) {
        Integer numI = items.get(itemId);

        return (numI != null)?numI:0;
    }

    public void consumeItem(IdValue item, boolean needSend) {
        Integer numI = items.get(item.id);
        if (numI != null) {
            int left = numI - item.value;
            if (left > 0) {
                items.put(item.id, left);
            } else {
                if (left < 0) {
                    System.err.println("Player[" + human.charId + "] consume " + ( -left ) + " more item[" + item.id + "] than own.");
                }

                items.remove(item.id);
            }

            human.sendMessage(ServerToClientMessageBuilder.buildItemNumChange(item.id, left));
        }
    }

    public void consumeItems(IdValue[] needItems, boolean needSend) {
        for (IdValue idValue : needItems) {
            consumeItem(idValue, needSend);
        }
    }

    public boolean containItems(IdValue[] checkItems) {
        for (IdValue checkItem : checkItems) {
            Integer numI = items.get(checkItem.id);

            if ((numI == null) || (numI < checkItem.value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 合成itemId对应的物品
     * @param itemId 要合成的物品id
     */
    public void combine(int itemId) {
        ItemStaticInfo itemStaticInfo = ItemsInfo.INSTANCE.getItemStaticInfo(itemId);
        if (itemStaticInfo != null) {
            if ((itemStaticInfo.composeItems != null) && (itemStaticInfo.composeItems.length>0)) {
                if (containItems(itemStaticInfo.composeItems)) {
                    consumeItems(itemStaticInfo.composeItems, true);

                    addItem(itemId, 1, true);

                    human.sendMessage(ServerToClientMessageBuilder.buildItemCombined(itemId));
                } else {
                    System.err.println("Player[" + human.charId + "] can't combine item[" + itemId + "] because not enough materials.");
                }
            } else {
                System.err.println("Player[" + human.charId + "] can't combine item[" + itemId + "] because item is not combinable.");
            }
        } else {
            System.err.println("Player[" + human.charId + "] can't combine item[" + itemId + "] because no such item.");
        }
    }
}
