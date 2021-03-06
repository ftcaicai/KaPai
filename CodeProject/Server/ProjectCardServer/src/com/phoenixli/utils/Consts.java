/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.utils;

/**
 *
 * @author rachel
 */
public class Consts {
    // 线程池线程数 - 用于处理战斗计算、充值、激活码的事务
    public static final int THREAD_SERVER_THREAD_NUM = 8;  
    
    public static final int MILSECOND_1SECOND = 1000;                       // 1秒的毫秒数
    public static final int MILSECOND_1MINITE = 1 * 60 * 1000;              // 1分钟的毫秒数
    public static final int MILSECOND_5MINITE = 5 * 60 * 1000;              // 5分钟的毫秒数
    public static final int MILSECOND_10MINITE = 10 * 60 * 1000;            // 10分钟的毫秒数
    public static final int MILSECOND_15MINITE = 15 * 60 * 1000;            // 15分钟的毫秒数
    public static final int MILSECOND_30MINITE = 30 * 60 * 1000;            // 30分钟的毫秒数
    public static final int MILSECOND_45MINITE = 45 * 60 * 1000;            // 45分钟的毫秒数
    public static final int MILSECOND_7POINT5MINITE = 75 * 6 * 1000;        // 7.5分钟的毫秒数
    public static final int MILSECOND_1HOUR = 1 * 3600 * 1000;              // 1小时的毫秒数
    public static final int MILSECOND_3HOUR = 3 * 3600 * 1000;              // 3小时的毫秒数（用于防沉迷）
    public static final int MILSECOND_5HOUR = 5 * 3600 * 1000;              // 5小时的毫秒数（用于防沉迷）
    public static final int MILSECOND_8HOUR = 8 * 3600 * 1000;              // 8小时的毫秒数（用于时差）
    public static final int MILSECOND_ONE_DAY = 24 * 3600 * 1000;           // 一天毫秒数
    public static final int MILSECOND_TWO_DAY = 2 * MILSECOND_ONE_DAY;      // 两天的毫秒数
    public static final int JET_LAG = MILSECOND_8HOUR;                      // 时差 - 东8区
    
    public static final int GAME_SLEEP_CONST = 50;                          // Unit ms, that is 0.05 second
    public static final int LOG_SLEEP_CONST = 100;                          // Unit ms, that is 0.05 second
    
    public static final int DB_KEEPALIVE_INTERVAL = 3600000;                // 3600000ms,保证数据库连接不中断
    public static final String DB_KEEPALIVE_TEST_STATEMENT = "SELECT 1 FROM INFORMATION_SCHEMA.VIEWS WHERE table_name IS NULL";
    
    public static final String MAP_CONFIG_FILEPATH = "mapConfig.json";                      // Server常量相关配置
    public static final String VIP_CONFIG_FILEPATH = "vipConfig.json";                      // VIP常量相关配置
    public static final String CONT_SIGN_CONFIG_FILEPATH = "contSignConfig.json";           // 活跃度与登录奖励常量相关配置
    public static final String HUMAN_LEVELS_CONFIG_FILEPATH = "humanLevelsConfig.json";     // 玩家等级相关配置
    public static final String VIP_GIFT_CONFIG_FILEPATH = "vipGiftConfig.json";             // VIP赠礼相关配置
    public static final String STAGES_CONFIG_FILEPATH = "stagesConfig.json";                // 关卡相关配置
    public static final String QUESTS_CONFIG_FILEPATH = "questsConfig.json";                // 任务相关配置
    public static final String ITEMS_CONFIG_FILEPATH = "itemsConfig.json";                  // 物品相关配置
    public static final String CARDS_CONFIG_FILEPATH = "cardsConfig.json";                  // 卡片相关配置
    
    public static final int REWARD_ITEM_TYPE_CARD = 0;                      // 卡片
    public static final int REWARD_ITEM_TYPE_ITEM = 1;                      // 物品
    public static final int REWARD_ITEM_TYPE_GOLD = 2;                      // 黄金
    public static final int REWARD_ITEM_TYPE_SILVER = 3;                    // 白银
    public static final int REWARD_ITEM_TYPE_DIAMOND = 4;                   // 钻石
    public static final int REWARD_ITEM_TYPE_POPULARITY = 5;                // 声望
    public static final int REWARD_ITEM_TYPE_EXPERIENCE = 6;                // 经验值
    public static final int REWARD_ITEM_TYPE_ENERGY = 7;                    // 体力值
    
    
    public static final int SOUL_CHANGE_LOG_TYPE_CUMULATIVE_SIGN_REWARD = 1;      // 累计签到奖
    public static final int SOUL_CHANGE_LOG_TYPE_CONSECUTIVE_SIGN_REWARD = 2;     // 连续签到奖
    public static final int SOUL_CHANGE_LOG_TYPE_VIP_REWARD = 3;                  // vip奖励
    public static final int SOUL_CHANGE_LOG_TYPE_QUEST = 4;                       // 任务
    
    
    public static final int MAX_NOTIFY_CACHE_NUM = 100;                     // 离线消息最大存储数量
    public static final int ALLOW_TALK_LEVEL = 10;                          // 允许世界频道聊天的等级
    
    public static final int CHAT_WORLD_CHANNEL = 0;                         //聊天世界频道
    public static final int CHAT_PRIVATE_CHANNEL= 2 ;                       //聊天私聊频道
    public static final int CHAT_SYSTEM_CHANNEL = 4;                        //聊天系统频道
    
    public static final int QUEST_TYPE_STAGE = 1;                           // 任务类型：过关
    public static final int QUEST_TYPE_LEVEL = 2;                           // 任务类型：升级
}
