/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.message.networkSendThreadMessage;

import com.phoenixli.common.channel.ChannelContext;
import com.phoenixli.common.message.networkSendThreadMessage.SimpleNetworkSendThreadMessage;

/**
 *
 * @author rachel
 */
public class AddChannelContextNSTMessage extends SimpleNetworkSendThreadMessage {

    public final ChannelContext channelContext;

    public AddChannelContextNSTMessage(ChannelContext channelContext) {
        super(NetworkSendThreadMessageType.NETWORK_SEND_MESSAGE_ADD_CHANNEL_CONTEXT);

        this.channelContext = channelContext;
    }
}
