/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.config;

import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.utils.Consts;
import com.phoenixli.utils.JSONHelper;

/**
 *
 * @author rachel
 */
public class MapConfig {
    public static MapConfig INSTANCE = JSONHelper.parseFileNoException(ProjectCardServer.INSTANCE.serverId + "/" + Consts.MAP_CONFIG_FILEPATH, MapConfig.class);
}
