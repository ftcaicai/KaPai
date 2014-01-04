/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.common.messageQueue;

import com.phoenixli.common.message.serverMessage.Message;
import java.util.concurrent.LinkedTransferQueue;

/**
 *
 * @author phoenix
 */
public class ServerMessageQueue {
    private static final LinkedTransferQueue<Message> messageQueue = new LinkedTransferQueue<Message>();
    
    public static LinkedTransferQueue<Message> queue() {
        return messageQueue;
    }
}
