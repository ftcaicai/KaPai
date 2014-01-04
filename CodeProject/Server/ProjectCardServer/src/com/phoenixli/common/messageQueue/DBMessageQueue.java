/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.common.messageQueue;

import com.phoenixli.common.message.dbMessage.DBMessage;
import java.util.concurrent.LinkedTransferQueue;

/**
 *
 * @author rachel
 */
public class DBMessageQueue {

    private static final LinkedTransferQueue<DBMessage> messageQueue = new LinkedTransferQueue<DBMessage>();

    public static LinkedTransferQueue<DBMessage> queue() {
        return messageQueue;
    }
}
