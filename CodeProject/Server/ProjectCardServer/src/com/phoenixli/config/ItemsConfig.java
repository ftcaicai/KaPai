/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.config;

import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.item.ItemStaticInfo;
import com.phoenixli.server.item.ItemsInfo;
import com.phoenixli.utils.Consts;
import com.phoenixli.utils.JSONHelper;

/**
 *
 * @author rachel
 */
public class ItemsConfig {
    public ItemStaticInfo[] itemStaticInfos;

    public static ItemsConfig INSTANCE = JSONHelper.parseFileNoException(ProjectCardServer.INSTANCE.serverId + "/" + Consts.ITEMS_CONFIG_FILEPATH, ItemsConfig.class);
    
    public void buildItemsConfig() {
        if ((itemStaticInfos != null) && (itemStaticInfos.length > 0)) {
            for (ItemStaticInfo staticInfo : itemStaticInfos) {
                ItemsInfo.INSTANCE.addItemStaticInfo(staticInfo);
            }
        }
    }
}
