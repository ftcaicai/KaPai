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
public class CharNumMessage extends InternalPlayerMessage {
    public final int charNum;

    public CharNumMessage(int playerId, int charNum) {
        super(MessageType.MAP_CHAR_NUM, playerId);

        this.charNum = charNum;
    }
    
}
