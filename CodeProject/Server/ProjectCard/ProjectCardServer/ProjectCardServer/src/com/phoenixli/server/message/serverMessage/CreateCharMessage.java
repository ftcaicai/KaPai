/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.message.serverMessage;

import com.phoenix.protobuf.ExternalCommonProtocol.CreateCharProto;
import com.phoenixli.common.message.serverMessage.InternalPlayerMessage;

/**
 *
 * @author rachel
 */
public class CreateCharMessage extends InternalPlayerMessage{

    public final CreateCharProto charInfo;

    public CreateCharMessage(int playerId, CreateCharProto charInfo) {
        super(MessageType.MAP_CREATE_CHAR, playerId);

        this.charInfo = charInfo;
    }
}
