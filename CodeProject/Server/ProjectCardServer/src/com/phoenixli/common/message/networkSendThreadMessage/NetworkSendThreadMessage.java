/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.common.message.networkSendThreadMessage;

/**
 *
 * @author rachel
 */
public interface NetworkSendThreadMessage {
    /**
     * 消息类型(前缀表示消息所在服务器)   -- 消息说明
     *
     */
    public enum NetworkSendThreadMessageType {
        NETWORK_SEND_MESSAGE_ADD_CHANNEL_CONTEXT     // 加入新channel
    }

    public NetworkSendThreadMessageType getType();
}
