/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.message.messageBuilder;

import com.phoenix.protobuf.ExternalCommonProtocol.ContSignProto;
import com.phoenix.protobuf.ExternalCommonProtocol.IntValueProto;
import com.phoenix.protobuf.ExternalCommonProtocol.LongValueProto;
import com.phoenixli.common.protobufMessage.ProtobufMessage;
import com.phoenixli.common.protobufMessage.ProtobufMessageType;

/**
 *
 * @author phoenix
 */
public class ServerToClientMessageBuilder {

    public static ProtobufMessage buildRealTime(long realTime) {
        LongValueProto.Builder builder = LongValueProto.newBuilder();
        builder.setValue(realTime);
        return new ProtobufMessage(ProtobufMessageType.S2C_REALTIME, builder.build().toByteArray());
    }

    public static ProtobufMessage buildLoginFail() {
        return new ProtobufMessage(ProtobufMessageType.S2C_LOGINERROR, null);
    }

    public static ProtobufMessage buildLoginRetNoChar() {
        return new ProtobufMessage(ProtobufMessageType.S2C_NOCHARRET, null);
    }

    public static ProtobufMessage buildCreateCharError() {
        return new ProtobufMessage(ProtobufMessageType.S2C_CREATECHARERROR, null);
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
}
