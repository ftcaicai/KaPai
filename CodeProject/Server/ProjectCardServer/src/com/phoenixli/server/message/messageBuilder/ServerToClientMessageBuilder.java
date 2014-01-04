/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.message.messageBuilder;

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
}
