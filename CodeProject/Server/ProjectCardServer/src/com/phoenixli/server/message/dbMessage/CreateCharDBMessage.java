/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.message.dbMessage;

import com.phoenix.protobuf.ExternalCommonProtocol.CreateCharProto;
import com.phoenixli.common.message.dbMessage.SimpleDBMessage;

/**
 *
 * @author rachel
 */
public class CreateCharDBMessage extends SimpleDBMessage {
    public final int playerId;
    public final CreateCharProto createCharInfo;

    public CreateCharDBMessage(int playerId, CreateCharProto createCharInfo) {
        super(DBMessageType.DB_MESSAGE_CREATE_CHAR);
        this.playerId = playerId;
        this.createCharInfo = createCharInfo;
    }    
}
