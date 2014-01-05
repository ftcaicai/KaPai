/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.actor;

import com.phoenixli.server.player.MapPlayer;

/**
 *
 * @author rachel
 */
public class Human {
    public MapPlayer mapPlayer;         // Human角色对应的网络实体
    
    public int charId;                  // 玩家id
    public String charName;             // 玩家名字
    public int charLevel;               // 玩家等级
    public int charExp;                 // 玩家经验
    public long goldCoin;               // 黄金（充值）  神格
    public long silverCoin;             // 白银（游戏币）信仰
    public long diamond;                // 钻石         徽记
    public long popularity;             // 声望         神威
    public int energy;                  // 体力
    public int token;                   // 军令
    
    
    
    public void leaveGame() {
        
    }
}
