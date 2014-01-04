/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.message.dbMessage;

import com.phoenixli.common.message.dbMessage.SimpleDBMessage;

/**
 *
 * @author rachel
 */
public class GetCharDetailDBMessage extends SimpleDBMessage {

    public final int playerId;

    public GetCharDetailDBMessage(int playerId) {
        super(DBMessageType.DB_MESSAGE_GET_CHAR_DETAIL);
        this.playerId = playerId;
    }
}
