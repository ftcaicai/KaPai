/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.notification;

import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.actor.Human;
import com.phoenixli.server.notification.message.NotificationMessage;
import com.phoenixli.server.player.MapPlayer;
import com.phoenixli.utils.Consts;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rachel
 */
public class NotifySystem {
     public static final NotifySystem INSTANCE = new NotifySystem();
     
     private HashMap<Integer, List<NotificationMessage>> noteProtoListHashMap = new HashMap<Integer, List<NotificationMessage>>();
     
     private NotifySystem() {
         
     }
     
     /**
     * 向指定玩家发通知
     * @param playerId 目标玩家ID
     * @param noteProto 通知内容
     * @param needCache 当玩家不在线时是否需要缓存通知
     */
    public void sendNote(int playerId, NotificationMessage message, boolean needCache) {
        MapPlayer player = ProjectCardServer.INSTANCE.players.get(playerId);
        if ((player != null) && (player.human != null) && (player.human.inGame)) {
            // 直接通知玩家
            message.handle(player.human);
        } else if (needCache) {
            List<NotificationMessage> notesList = noteProtoListHashMap.get(playerId);
            if (notesList == null) {
                notesList = new ArrayList<NotificationMessage>();
                noteProtoListHashMap.put(playerId, notesList);
            }

            if (notesList.size() >= Consts.MAX_NOTIFY_CACHE_NUM) {
                notesList.remove(0);
            }

            notesList.add(message);
        }
    }
    
    /**
     * 此方法在玩家上线时调用，向玩家发送所有堆积的通知
     * @param human 目标玩家
     */
    public void sendAllHumanNotes(Human human) {
        List<NotificationMessage> notesList = noteProtoListHashMap.get(human.charId);

        if (notesList != null) {
            for (NotificationMessage message : notesList) {
                message.handle(human);
            }

            notesList.clear();
            noteProtoListHashMap.remove(human.charId);
        }
    }

}
