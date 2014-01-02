/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.player.state;

import com.phoenixli.common.message.serverMessage.Message;
import com.phoenixli.common.message.serverMessage.Message.MessageType;
import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.message.messageBuilder.ServerToClientMessageBuilder;
import com.phoenixli.server.message.serverMessage.CharNumMessage;
import com.phoenixli.server.player.MapPlayer;
import com.phoenixli.server.player.Player;

/**
 * Login2State是刚验证玩家帐号和密码信息但未获取角色信息的状态
 * 处于Login2State状态下，玩家等待服务器发回基本角色信息
 * @author rachel
 */
public class Login1PlayerState implements PlayerState {
    public final static Login1PlayerState INSTANCE = new Login1PlayerState();

    private Login1PlayerState() {
    }

    @Override
    public boolean handleMessage(Player player, Message message) {
        // 说明：当遇到不该在此状态处理的消息时，说明玩家状态异常，将玩家状态设置为空，
        // 告诉主线程切断玩家连接并清理玩家上下文
        MapPlayer p = (MapPlayer)player;
        int playerId = p.getId();
        MessageType msgType = message.getType();
        switch (msgType) {
            case MAP_CHAR_NUM: {
                CharNumMessage charNumMessage = (CharNumMessage)message;

                if (charNumMessage.charNum == 0) {
                    // 未创建角色
                    p.channelContext.write(ServerToClientMessageBuilder.buildLoginRetNoChar());
                    p.state = Login2PlayerState.INSTANCE;
                } else if (charNumMessage.charNum == 1) {
                    // 角色存在
                    if (p.human != null) { // 玩家数据已关联
                        if (p.human.mapPlayer != null) {
                            ProjectCardServer.INSTANCE.enterGame(p);
                            p.state = NormalPlayerState.INSTANCE;
                        } else {
                            System.err.println("Player["+playerId+"] Login1State human.mapPlayer==null error.");
                        }
                    } else { // 玩家数据未关联
                        ProjectCardServer.INSTANCE.loadPlayerData(playerId);
                        p.state = UninitPlayerState.INSTANCE;
                    }
                } else {
                    System.err.println("Player["+playerId+"] Login1State get char num=="+charNumMessage.charNum+" error.");
                }
                return true;
            }
            default: {
                // 暂时不作处理，记录日志
                System.err.println("Player["+playerId+"] Login2State handle error message type["+msgType+"].");

                return false;
            }
        }
    }
}
