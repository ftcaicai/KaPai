/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.common.protobufMessage;

/**
 *
 * @author phoenix
 */
public class ProtobufMessageType {
    //Client To Server Protocol

    public static final int C2S_LOGIN = 1;              // 登录
    public static final int C2S_CREATECHAR = 2;         // 创建角色
    
    
    public static final int C2S_CONTSIGN_CUMULATIVE_REWARD_RECEIVED = 21;       // 累计登陆奖励领取
    public static final int C2S_CONTSIGN_CONSECUTIVE_REWARD_RECEIVED = 22;      // 连续登陆奖励领取

    public static final int C2S_VIP_GIFT_RECEIVE = 31;  // VIP赠礼信息领取
    
    
    //Server To Client Protocol
    // 通用
    public static final int S2C_REAL_TIME = 1;           // 对时
    // 登录创建角色相关
    public static final int S2C_LOGIN_ERROR = 11;        // 登录失败
    public static final int S2C_NO_CHAR_RET = 12;         // 未创建角色
    public static final int S2C_CREATE_CHAR_ERROR = 13;   // 创建角色失败
    public static final int S2C_ENTER_GAME_RET = 14;      // 进入游戏
    
    public static final int S2C_CONTSIGN_CUMULATIVE_REWARD_RECEIVED = 21;       // 累计登陆奖励领取
    public static final int S2C_CONTSIGN_CONSECUTIVE_REWARD_RECEIVED = 22;      // 连续登陆奖励领取
    public static final int S2C_CONTSIGN = 23;                                  // 签到信息变更
    
    public static final int S2C_VIP_CHANGE = 31;        // VIP信息变更
    public static final int S2C_VIP_GIFT_RECEIVE = 32;  // VIP赠礼信息领取
    
    public static final int S2C_GOLD_COIN_CHANGE = 41;          // 
    public static final int S2C_SILVER_COIN_CHANGE = 42;        //
    public static final int S2C_DIAMOND_CHANGE = 43;            //
    public static final int S2C_POPULARITY_CHANGE = 44;         //
    public static final int S2C_EXPERIENCE_CHANGE = 45;         //
    public static final int S2C_ENERGY_CHANGE = 46;             //
    
    public static final int S2C_LEVEL_UP = 51;                  // 角色升级
        
    public static final int S2C_NOTIFICATION = 61;              // 通知协议
    public static final int S2C_TALK = 62;                      // 聊天通知
    
}
