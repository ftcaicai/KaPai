/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.item;

import java.util.HashMap;

/**
 *
 * @author rachel
 */
public class ItemsInfo {

    public final HashMap<Integer, ItemStaticInfo> itemStaticInfos = new HashMap<Integer, ItemStaticInfo>();
    public static final ItemsInfo INSTANCE = new ItemsInfo();

    private ItemsInfo() {
    }

    public void addItemStaticInfo(ItemStaticInfo itemStaticInfo) {
        assert (!itemStaticInfos.containsKey(itemStaticInfo.id));
        itemStaticInfos.put(itemStaticInfo.id, itemStaticInfo);
    }
    
    public ItemStaticInfo getItemStaticInfo(int id) {
        ItemStaticInfo itemStaticInfo = itemStaticInfos.get(id);
        if (itemStaticInfo == null) {
            System.err.println("Item[" + id + "] static info not found.");
        }
        return itemStaticInfo;
    }
}
