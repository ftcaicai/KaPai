/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.notification.message;

import com.phoenix.protobuf.ExternalCommonProtocol.NoteProto;
import com.phoenixli.server.actor.Human;
import com.phoenixli.server.message.messageBuilder.ServerToClientMessageBuilder;

/**
 *
 * @author rachel
 */
public abstract class NotificationMessage {
    public final int type;    // 通知消息类型

    public NotificationMessage(int type) {
        this.type = type;
    }

    public abstract NoteProto buildNoteProto();

    public void handle(Human human) {
        human.sendMessage(ServerToClientMessageBuilder.buildNotification(buildNoteProto()));
    }
}
