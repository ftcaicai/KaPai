/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.common.message.serverMessage;

/**
 *
 * @author phoenix
 */
public interface Message {
    public enum MessageType {
        MAP_CLIENT_CONNECT, MAP_CLIENT_CLOSE,
        MAP_LOGIN, MAP_CHAR_NUM, MAP_CREATE_CHAR_RET, MAP_GET_CHAR_DETAIL_INFO_RET, MAP_CREATE_CHAR, 
        
        MAP_CONTSIGN_CONSECUTIVE_REWARD_RECEIVE, MAP_CONTSIGN_CUMULATIVE_REWARD_RECEIVE,
        
        MAP_VIP_GIFT_RECEIVE, 
        
    }
    
    public MessageType getType();
}
