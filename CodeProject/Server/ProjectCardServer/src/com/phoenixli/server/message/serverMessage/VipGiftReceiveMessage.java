/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.message.serverMessage;

import com.phoenixli.common.message.serverMessage.InternalPlayerMessage;
import com.phoenixli.common.message.serverMessage.Message.MessageType;

/**
 *
 * @author rachel
 */
public class VipGiftReceiveMessage extends InternalPlayerMessage {
    public final int vip;

    public VipGiftReceiveMessage(int playerId,int vip) {
        super(MessageType.MAP_VIP_GIFT_RECEIVE, playerId);
        this.vip = vip;
    }
}
