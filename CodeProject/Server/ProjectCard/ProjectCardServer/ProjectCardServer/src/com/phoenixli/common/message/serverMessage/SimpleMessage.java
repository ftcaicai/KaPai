/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.common.message.serverMessage;

/**
 *
 * @author phoenix
 */
public class SimpleMessage implements Message{
    
    public final MessageType type; // The type of receive message

    public SimpleMessage(MessageType type) {
        this.type = type;
    }

    @Override
    public final MessageType getType() {
        return type;
    }
}
