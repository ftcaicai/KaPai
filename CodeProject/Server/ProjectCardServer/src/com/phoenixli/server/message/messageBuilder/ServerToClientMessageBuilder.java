/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.message.messageBuilder;

import com.phoenix.protobuf.ExternalCommonProtocol.ContSignProto;
import com.phoenix.protobuf.ExternalCommonProtocol.EnterGameCharProto;
import com.phoenix.protobuf.ExternalCommonProtocol.EnterGameRetProto;
import com.phoenix.protobuf.ExternalCommonProtocol.IntValueProto;
import com.phoenix.protobuf.ExternalCommonProtocol.LongValueProto;
import com.phoenix.protobuf.ExternalCommonProtocol.NoteProto;
import com.phoenix.protobuf.ExternalCommonProtocol.QuestsProto;
import com.phoenix.protobuf.ExternalCommonProtocol.TalkProto;
import com.phoenix.protobuf.ExternalCommonProtocol.VipProto;
import com.phoenixli.common.protobufMessage.ProtobufMessage;
import com.phoenixli.common.protobufMessage.ProtobufMessageType;
import com.phoenixli.server.quest.Quest;
import java.util.LinkedList;

/**
 *
 * @author phoenix
 */
public class ServerToClientMessageBuilder {

    public static ProtobufMessage buildRealTime(long realTime) {
        LongValueProto.Builder builder = LongValueProto.newBuilder();
        builder.setValue(realTime);
        return new ProtobufMessage(ProtobufMessageType.S2C_REAL_TIME, builder.build().toByteArray());
    }

    public static ProtobufMessage buildLoginFail() {
        return new ProtobufMessage(ProtobufMessageType.S2C_LOGIN_ERROR, null);
    }

    public static ProtobufMessage buildLoginRetNoChar() {
        return new ProtobufMessage(ProtobufMessageType.S2C_NO_CHAR_RET, null);
    }

    public static ProtobufMessage buildCreateCharError() {
        return new ProtobufMessage(ProtobufMessageType.S2C_CREATE_CHAR_ERROR, null);
    }

    public static ProtobufMessage buildCumulativeRewardReceived() {
        return new ProtobufMessage(ProtobufMessageType.S2C_CONTSIGN_CUMULATIVE_REWARD_RECEIVED, null);
    }

    public static ProtobufMessage buildReceivedConsecutiveSignReward(int day) {
        IntValueProto.Builder builder = IntValueProto.newBuilder();
        builder.setValue(day);
        return new ProtobufMessage(ProtobufMessageType.S2C_CONTSIGN_CONSECUTIVE_REWARD_RECEIVED, builder.build());
    }

    public static ProtobufMessage buildContSign(ContSignProto contSignProto) {
        return new ProtobufMessage(ProtobufMessageType.S2C_CONTSIGN, contSignProto);
    }

    public static ProtobufMessage buildVipChange(VipProto vipProto) {
        return new ProtobufMessage(ProtobufMessageType.S2C_VIP_CHANGE, vipProto);
    }

    public static ProtobufMessage buildVipReceived(int receiveFlag) {
        IntValueProto.Builder builder = IntValueProto.newBuilder();
        builder.setValue(receiveFlag);
        return new ProtobufMessage(ProtobufMessageType.S2C_VIP_GIFT_RECEIVE, builder.build());
    }

    public static ProtobufMessage buildGoldCoinChange(long value) {
        LongValueProto.Builder builder = LongValueProto.newBuilder();
        builder.setValue(value);
        return new ProtobufMessage(ProtobufMessageType.S2C_GOLD_COIN_CHANGE, builder.build());
    }

    public static ProtobufMessage buildSilverCoinChange(long value) {
        LongValueProto.Builder builder = LongValueProto.newBuilder();
        builder.setValue(value);
        return new ProtobufMessage(ProtobufMessageType.S2C_SILVER_COIN_CHANGE, builder.build());
    }

    public static ProtobufMessage buildDiamondChange(long value) {
        LongValueProto.Builder builder = LongValueProto.newBuilder();
        builder.setValue(value);
        return new ProtobufMessage(ProtobufMessageType.S2C_DIAMOND_CHANGE, builder.build());
    }

    public static ProtobufMessage buildPopularityChange(long value) {
        LongValueProto.Builder builder = LongValueProto.newBuilder();
        builder.setValue(value);
        return new ProtobufMessage(ProtobufMessageType.S2C_POPULARITY_CHANGE, builder.build());
    }

    public static ProtobufMessage buildExperienceChange(int value) {
        IntValueProto.Builder builder = IntValueProto.newBuilder();
        builder.setValue(value);
        return new ProtobufMessage(ProtobufMessageType.S2C_EXPERIENCE_CHANGE, builder.build());
    }

    public static ProtobufMessage buildEnergyChange(int value) {
        IntValueProto.Builder builder = IntValueProto.newBuilder();
        builder.setValue(value);
        return new ProtobufMessage(ProtobufMessageType.S2C_ENERGY_CHANGE, builder.build());
    }
    
    public static ProtobufMessage buildLevelup(int lv) {
        IntValueProto.Builder builder = IntValueProto.newBuilder();
        builder.setValue(lv);
        return new ProtobufMessage(ProtobufMessageType.S2C_LEVEL_UP, builder.build());
    }   
    
    public static ProtobufMessage buildNotification(NoteProto noteProto) {
        return new ProtobufMessage(ProtobufMessageType.S2C_NOTIFICATION, noteProto);
    }
    
    public static ProtobufMessage buildTalk(TalkProto talkProto) {
        return new ProtobufMessage(ProtobufMessageType.S2C_TALK, talkProto);
    }
    
    public static ProtobufMessage buildEnterGameRet(EnterGameCharProto enterGameCharProto) {
        EnterGameRetProto.Builder builder = EnterGameRetProto.newBuilder();
        builder.setResult(0);
        builder.setEnterGameChar(enterGameCharProto);
        return new ProtobufMessage(ProtobufMessageType.S2C_ENTER_GAME_RET, builder.build());
    }
    
    public static ProtobufMessage buildQuestFinished(int questId) {
        IntValueProto.Builder builder = IntValueProto.newBuilder();
        builder.setValue(questId);
        return new ProtobufMessage(ProtobufMessageType.S2C_QUEST_FINISHED, builder.build());
    }

    public static ProtobufMessage buildQuestSubmitted(int questId) {
        IntValueProto.Builder builder = IntValueProto.newBuilder();
        builder.setValue(questId);
        return new ProtobufMessage(ProtobufMessageType.S2C_QUEST_SUBMITTED, builder.build());
    }
    
    public static ProtobufMessage buildQuestAddList(LinkedList<Quest> quests) {
        QuestsProto.Builder builder = QuestsProto.newBuilder();

        for (Quest quest : quests) {
            builder.addQuests(quest.buildQuestProto());
        }

        return new ProtobufMessage(ProtobufMessageType.S2C_QUEST_ADD_LIST, builder.build());
    }
}
