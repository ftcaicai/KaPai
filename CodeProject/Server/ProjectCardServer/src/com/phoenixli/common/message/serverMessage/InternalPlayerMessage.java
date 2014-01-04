/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.common.message.serverMessage;

/**
 *
 * @author rachel
 */
public class InternalPlayerMessage extends SimpleMessage {

    public final int playerId;  // The player's id

    public InternalPlayerMessage(MessageType type, int playerId) {
        super(type);
        this.playerId = playerId;
    }
}
