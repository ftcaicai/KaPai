/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.player;

import com.phoenixli.common.message.serverMessage.Message;
import com.phoenixli.server.actor.Human;
import com.phoenixli.server.player.MapPlayer;
import java.util.LinkedList;

/**
 *
 * @author rachel
 */
public class PlayerContext {

    public long lastVisitTime;
    public MapPlayer player;
    public Human human;
    public LinkedList<Message> waitingMessages;

    public PlayerContext() {
    }

    public void addWaitingMessage(Message message) {
        if (waitingMessages == null) {
            waitingMessages = new LinkedList<Message>();
        }

        waitingMessages.add(message);
    }
}
