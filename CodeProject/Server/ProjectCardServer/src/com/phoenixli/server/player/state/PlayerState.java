/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.player.state;

import com.phoenixli.common.message.serverMessage.Message;
import com.phoenixli.server.player.Player;

/**
 *
 * @author rachel
 */
public interface PlayerState {
    public boolean handleMessage(Player player, Message message);
}

/****
 * 登录 -> Login1PlayerState -> 角色数量为0 -> Login2PlayerState -> 收到创建角色请求  -> CreateingCharPlayerState -> 创建角色 -> UninitPlayerState -> 获取角色信息 -> NormalPlayerState
 *                              角色数量为1 -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> ->UninitPlayerState -> 获取角色信息 -> NormalPlayerState
 * 
 * 
 * 
 * 
 */