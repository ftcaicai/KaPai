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
public class CreateCharRetMessage extends InternalPlayerMessage {
    public final int result;

    public CreateCharRetMessage(int playerId, int result) {
        super(MessageType.MAP_CREATE_CHAR_RET, playerId);

        this.result = result;
    }
}
