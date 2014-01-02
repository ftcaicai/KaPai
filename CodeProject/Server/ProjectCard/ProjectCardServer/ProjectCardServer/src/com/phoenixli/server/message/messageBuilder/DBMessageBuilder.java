/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.message.messageBuilder;

import com.phoenix.protobuf.ExternalCommonProtocol.CreateCharProto;
import com.phoenixli.server.message.dbMessage.CreateCharDBMessage;
import com.phoenixli.server.message.dbMessage.GetCharDetailDBMessage;
import com.phoenixli.server.message.dbMessage.GetCharNumDBMessage;

/**
 *
 * @author rachel
 */
public class DBMessageBuilder {
    public static GetCharNumDBMessage buildGetCharNumDBMessage(int playerId) {
        return new GetCharNumDBMessage(playerId);
    }
    
    public static CreateCharDBMessage buildCreateCharDBMessage(int playerId, CreateCharProto charInfo) {
        return new CreateCharDBMessage(playerId, charInfo);
    }
    
    public static GetCharDetailDBMessage buildGetCharDetailDBMessage(int playerId) {
        return new GetCharDetailDBMessage(playerId);
    }
}
