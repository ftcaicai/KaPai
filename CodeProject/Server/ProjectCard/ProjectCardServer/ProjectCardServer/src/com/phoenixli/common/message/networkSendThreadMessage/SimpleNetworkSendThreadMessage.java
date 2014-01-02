/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.common.message.networkSendThreadMessage;

/**
 *
 * @author rachel
 */
public class SimpleNetworkSendThreadMessage implements NetworkSendThreadMessage {
    private final NetworkSendThreadMessageType type;

    public SimpleNetworkSendThreadMessage(NetworkSendThreadMessageType type) {
        this.type = type;
    }

    @Override
    public NetworkSendThreadMessageType getType() {
        return type;
    }
}
