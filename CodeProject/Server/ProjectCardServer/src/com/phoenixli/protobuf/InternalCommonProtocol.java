// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/protobuf/InternalCommonProtocol.proto

package com.phoenix.protobuf;

public final class InternalCommonProtocol {
  private InternalCommonProtocol() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public static final class DBPlayerDetailProto extends
      com.google.protobuf.GeneratedMessage {
    // Use DBPlayerDetailProto.newBuilder() to construct.
    private DBPlayerDetailProto() {
      initFields();
    }
    private DBPlayerDetailProto(boolean noInit) {}
    
    private static final DBPlayerDetailProto defaultInstance;
    public static DBPlayerDetailProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public DBPlayerDetailProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.phoenix.protobuf.InternalCommonProtocol.internal_static_myth_DBPlayerDetailProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.phoenix.protobuf.InternalCommonProtocol.internal_static_myth_DBPlayerDetailProto_fieldAccessorTable;
    }
    
    private void initFields() {
    }
    public final boolean isInitialized() {
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    public static com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> {
      private com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto result;
      
      // Construct using com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto.newBuilder()
      private Builder() {}
      
      private static Builder create() {
        Builder builder = new Builder();
        builder.result = new com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto();
        return builder;
      }
      
      protected com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto internalGetResult() {
        return result;
      }
      
      public Builder clear() {
        if (result == null) {
          throw new IllegalStateException(
            "Cannot call clear() after build().");
        }
        result = new com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto();
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(result);
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto.getDescriptor();
      }
      
      public com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto getDefaultInstanceForType() {
        return com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto.getDefaultInstance();
      }
      
      public boolean isInitialized() {
        return result.isInitialized();
      }
      public com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto build() {
        if (result != null && !isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return buildPartial();
      }
      
      private com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        if (!isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return buildPartial();
      }
      
      public com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto buildPartial() {
        if (result == null) {
          throw new IllegalStateException(
            "build() has already been called on this Builder.");
        }
        com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto returnMe = result;
        result = null;
        return returnMe;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto) {
          return mergeFrom((com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto other) {
        if (other == com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto.getDefaultInstance()) return this;
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                return this;
              }
              break;
            }
          }
        }
      }
      
      
      // @@protoc_insertion_point(builder_scope:myth.DBPlayerDetailProto)
    }
    
    static {
      defaultInstance = new DBPlayerDetailProto(true);
      com.phoenix.protobuf.InternalCommonProtocol.internalForceInit();
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:myth.DBPlayerDetailProto)
  }
  
  public static final class DBContSignProto extends
      com.google.protobuf.GeneratedMessage {
    // Use DBContSignProto.newBuilder() to construct.
    private DBContSignProto() {
      initFields();
    }
    private DBContSignProto(boolean noInit) {}
    
    private static final DBContSignProto defaultInstance;
    public static DBContSignProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public DBContSignProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.phoenix.protobuf.InternalCommonProtocol.internal_static_myth_DBContSignProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.phoenix.protobuf.InternalCommonProtocol.internal_static_myth_DBContSignProto_fieldAccessorTable;
    }
    
    // required int32 lastOnlineDay = 1;
    public static final int LASTONLINEDAY_FIELD_NUMBER = 1;
    private boolean hasLastOnlineDay;
    private int lastOnlineDay_ = 0;
    public boolean hasLastOnlineDay() { return hasLastOnlineDay; }
    public int getLastOnlineDay() { return lastOnlineDay_; }
    
    // required int32 cumulativeNum = 2;
    public static final int CUMULATIVENUM_FIELD_NUMBER = 2;
    private boolean hasCumulativeNum;
    private int cumulativeNum_ = 0;
    public boolean hasCumulativeNum() { return hasCumulativeNum; }
    public int getCumulativeNum() { return cumulativeNum_; }
    
    // required int32 cumulativeRewardNum = 3;
    public static final int CUMULATIVEREWARDNUM_FIELD_NUMBER = 3;
    private boolean hasCumulativeRewardNum;
    private int cumulativeRewardNum_ = 0;
    public boolean hasCumulativeRewardNum() { return hasCumulativeRewardNum; }
    public int getCumulativeRewardNum() { return cumulativeRewardNum_; }
    
    // required int32 consecutiveNum = 4;
    public static final int CONSECUTIVENUM_FIELD_NUMBER = 4;
    private boolean hasConsecutiveNum;
    private int consecutiveNum_ = 0;
    public boolean hasConsecutiveNum() { return hasConsecutiveNum; }
    public int getConsecutiveNum() { return consecutiveNum_; }
    
    // required int32 consecutiveReceiveFlag = 5;
    public static final int CONSECUTIVERECEIVEFLAG_FIELD_NUMBER = 5;
    private boolean hasConsecutiveReceiveFlag;
    private int consecutiveReceiveFlag_ = 0;
    public boolean hasConsecutiveReceiveFlag() { return hasConsecutiveReceiveFlag; }
    public int getConsecutiveReceiveFlag() { return consecutiveReceiveFlag_; }
    
    private void initFields() {
    }
    public final boolean isInitialized() {
      if (!hasLastOnlineDay) return false;
      if (!hasCumulativeNum) return false;
      if (!hasCumulativeRewardNum) return false;
      if (!hasConsecutiveNum) return false;
      if (!hasConsecutiveReceiveFlag) return false;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (hasLastOnlineDay()) {
        output.writeInt32(1, getLastOnlineDay());
      }
      if (hasCumulativeNum()) {
        output.writeInt32(2, getCumulativeNum());
      }
      if (hasCumulativeRewardNum()) {
        output.writeInt32(3, getCumulativeRewardNum());
      }
      if (hasConsecutiveNum()) {
        output.writeInt32(4, getConsecutiveNum());
      }
      if (hasConsecutiveReceiveFlag()) {
        output.writeInt32(5, getConsecutiveReceiveFlag());
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (hasLastOnlineDay()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, getLastOnlineDay());
      }
      if (hasCumulativeNum()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, getCumulativeNum());
      }
      if (hasCumulativeRewardNum()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, getCumulativeRewardNum());
      }
      if (hasConsecutiveNum()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, getConsecutiveNum());
      }
      if (hasConsecutiveReceiveFlag()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, getConsecutiveReceiveFlag());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    public static com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> {
      private com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto result;
      
      // Construct using com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto.newBuilder()
      private Builder() {}
      
      private static Builder create() {
        Builder builder = new Builder();
        builder.result = new com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto();
        return builder;
      }
      
      protected com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto internalGetResult() {
        return result;
      }
      
      public Builder clear() {
        if (result == null) {
          throw new IllegalStateException(
            "Cannot call clear() after build().");
        }
        result = new com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto();
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(result);
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto.getDescriptor();
      }
      
      public com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto getDefaultInstanceForType() {
        return com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto.getDefaultInstance();
      }
      
      public boolean isInitialized() {
        return result.isInitialized();
      }
      public com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto build() {
        if (result != null && !isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return buildPartial();
      }
      
      private com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        if (!isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return buildPartial();
      }
      
      public com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto buildPartial() {
        if (result == null) {
          throw new IllegalStateException(
            "build() has already been called on this Builder.");
        }
        com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto returnMe = result;
        result = null;
        return returnMe;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto) {
          return mergeFrom((com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto other) {
        if (other == com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto.getDefaultInstance()) return this;
        if (other.hasLastOnlineDay()) {
          setLastOnlineDay(other.getLastOnlineDay());
        }
        if (other.hasCumulativeNum()) {
          setCumulativeNum(other.getCumulativeNum());
        }
        if (other.hasCumulativeRewardNum()) {
          setCumulativeRewardNum(other.getCumulativeRewardNum());
        }
        if (other.hasConsecutiveNum()) {
          setConsecutiveNum(other.getConsecutiveNum());
        }
        if (other.hasConsecutiveReceiveFlag()) {
          setConsecutiveReceiveFlag(other.getConsecutiveReceiveFlag());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                return this;
              }
              break;
            }
            case 8: {
              setLastOnlineDay(input.readInt32());
              break;
            }
            case 16: {
              setCumulativeNum(input.readInt32());
              break;
            }
            case 24: {
              setCumulativeRewardNum(input.readInt32());
              break;
            }
            case 32: {
              setConsecutiveNum(input.readInt32());
              break;
            }
            case 40: {
              setConsecutiveReceiveFlag(input.readInt32());
              break;
            }
          }
        }
      }
      
      
      // required int32 lastOnlineDay = 1;
      public boolean hasLastOnlineDay() {
        return result.hasLastOnlineDay();
      }
      public int getLastOnlineDay() {
        return result.getLastOnlineDay();
      }
      public Builder setLastOnlineDay(int value) {
        result.hasLastOnlineDay = true;
        result.lastOnlineDay_ = value;
        return this;
      }
      public Builder clearLastOnlineDay() {
        result.hasLastOnlineDay = false;
        result.lastOnlineDay_ = 0;
        return this;
      }
      
      // required int32 cumulativeNum = 2;
      public boolean hasCumulativeNum() {
        return result.hasCumulativeNum();
      }
      public int getCumulativeNum() {
        return result.getCumulativeNum();
      }
      public Builder setCumulativeNum(int value) {
        result.hasCumulativeNum = true;
        result.cumulativeNum_ = value;
        return this;
      }
      public Builder clearCumulativeNum() {
        result.hasCumulativeNum = false;
        result.cumulativeNum_ = 0;
        return this;
      }
      
      // required int32 cumulativeRewardNum = 3;
      public boolean hasCumulativeRewardNum() {
        return result.hasCumulativeRewardNum();
      }
      public int getCumulativeRewardNum() {
        return result.getCumulativeRewardNum();
      }
      public Builder setCumulativeRewardNum(int value) {
        result.hasCumulativeRewardNum = true;
        result.cumulativeRewardNum_ = value;
        return this;
      }
      public Builder clearCumulativeRewardNum() {
        result.hasCumulativeRewardNum = false;
        result.cumulativeRewardNum_ = 0;
        return this;
      }
      
      // required int32 consecutiveNum = 4;
      public boolean hasConsecutiveNum() {
        return result.hasConsecutiveNum();
      }
      public int getConsecutiveNum() {
        return result.getConsecutiveNum();
      }
      public Builder setConsecutiveNum(int value) {
        result.hasConsecutiveNum = true;
        result.consecutiveNum_ = value;
        return this;
      }
      public Builder clearConsecutiveNum() {
        result.hasConsecutiveNum = false;
        result.consecutiveNum_ = 0;
        return this;
      }
      
      // required int32 consecutiveReceiveFlag = 5;
      public boolean hasConsecutiveReceiveFlag() {
        return result.hasConsecutiveReceiveFlag();
      }
      public int getConsecutiveReceiveFlag() {
        return result.getConsecutiveReceiveFlag();
      }
      public Builder setConsecutiveReceiveFlag(int value) {
        result.hasConsecutiveReceiveFlag = true;
        result.consecutiveReceiveFlag_ = value;
        return this;
      }
      public Builder clearConsecutiveReceiveFlag() {
        result.hasConsecutiveReceiveFlag = false;
        result.consecutiveReceiveFlag_ = 0;
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:myth.DBContSignProto)
    }
    
    static {
      defaultInstance = new DBContSignProto(true);
      com.phoenix.protobuf.InternalCommonProtocol.internalForceInit();
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:myth.DBContSignProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_myth_DBPlayerDetailProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_myth_DBPlayerDetailProto_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_myth_DBContSignProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_myth_DBContSignProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n)src/protobuf/InternalCommonProtocol.pr" +
      "oto\022\004myth\032)src/protobuf/ExternalCommonPr" +
      "otocol.proto\"\025\n\023DBPlayerDetailProto\"\224\001\n\017" +
      "DBContSignProto\022\025\n\rlastOnlineDay\030\001 \002(\005\022\025" +
      "\n\rcumulativeNum\030\002 \002(\005\022\033\n\023cumulativeRewar" +
      "dNum\030\003 \002(\005\022\026\n\016consecutiveNum\030\004 \002(\005\022\036\n\026co" +
      "nsecutiveReceiveFlag\030\005 \002(\005B\030\n\024com.phoeni" +
      "x.protobufH\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_myth_DBPlayerDetailProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_myth_DBPlayerDetailProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_myth_DBPlayerDetailProto_descriptor,
              new java.lang.String[] { },
              com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto.class,
              com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto.Builder.class);
          internal_static_myth_DBContSignProto_descriptor =
            getDescriptor().getMessageTypes().get(1);
          internal_static_myth_DBContSignProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_myth_DBContSignProto_descriptor,
              new java.lang.String[] { "LastOnlineDay", "CumulativeNum", "CumulativeRewardNum", "ConsecutiveNum", "ConsecutiveReceiveFlag", },
              com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto.class,
              com.phoenix.protobuf.InternalCommonProtocol.DBContSignProto.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.phoenix.protobuf.ExternalCommonProtocol.getDescriptor(),
        }, assigner);
  }
  
  public static void internalForceInit() {}
  
  // @@protoc_insertion_point(outer_class_scope)
}
