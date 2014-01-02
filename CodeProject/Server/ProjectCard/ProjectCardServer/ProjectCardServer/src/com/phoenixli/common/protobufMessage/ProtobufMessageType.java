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
    
    
    
    //Server To Client Protocol
    // 通用
    public static final int S2C_REALTIME = 1;           // 对时
    
    // 登录创建角色相关
    public static final int S2C_LOGINERROR = 11;        // 登录失败
    public static final int S2C_NOCHARRET = 12;         // 未创建角色
    public static final int S2C_CREATECHARERROR = 13;   // 创建角色失败
    public static final int S2C_ENTERGAMERET = 14;      // 进入游戏
}
