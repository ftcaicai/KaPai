/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.common.messageQueue;

import com.phoenixli.common.message.networkSendThreadMessage.NetworkSendThreadMessage;
import java.util.concurrent.LinkedTransferQueue;

/**
 *
 * @author rachel
 */
public class NetworkSendThreadMessageQueue {
    private static final LinkedTransferQueue<NetworkSendThreadMessage> messageQueue = new LinkedTransferQueue<NetworkSendThreadMessage>();

    public static LinkedTransferQueue<NetworkSendThreadMessage> queue() {
        return messageQueue;
    }
}
