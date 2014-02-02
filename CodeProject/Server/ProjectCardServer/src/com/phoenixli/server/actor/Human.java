/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.actor;

import com.phoenix.protobuf.ExternalCommonProtocol.EnterGameCharProto;
import com.phoenix.protobuf.ExternalCommonProtocol.TalkProto;
import com.phoenix.protobuf.InternalCommonProtocol.DBPlayerDetailProto;
import com.phoenixli.common.protobufMessage.ProtobufMessage;
import com.phoenixli.config.HumanLevelsConfig;
import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.contSign.ContSign;
import com.phoenixli.server.message.messageBuilder.ServerToClientMessageBuilder;
import com.phoenixli.server.player.MapPlayer;
import com.phoenixli.server.reward.CertainRewardItemInfo;
import com.phoenixli.server.reward.CertainRewardsInfo;
import com.phoenixli.server.stage.Stages;
import com.phoenixli.server.vip.Vip;
import com.phoenixli.server.vip.VipGift;
import com.phoenixli.utils.Consts;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rachel
 */
public class Human {

    public MapPlayer mapPlayer;         // Human角色对应的网络实体
    public int charId;                  // 玩家id
    public String charName;             // 玩家名字
    public int charLevel;               // 玩家等级
    public int charExp;                 // 玩家经验
    
    public long goldCoin;               // 黄金（充值）  神格
    public long silverCoin;             // 白银（游戏币）信仰
    public long diamond;                // 钻石         徽记
    public long popularity;             // 声望         神威
    public int energy;                  // 体力
    public int token;                   // 军令
    
    public Vip vip;                     // vip
    public VipGift vipGift;             // vip赠礼
    
    public ContSign contSign;           // 登录签到奖励

    public Stages stages;               // 关卡相关
    
    public int ip;                      // 玩家登陆ip
    public long enterTime;              // 最近一次登陆游戏时间
    public long leaveTime;              // 最后一次离开游戏时间
    public long totalOnlineTime;        // 累计游戏时间
    public long nextWorldTalkTime;      // 下次允许世界频道发言时间
    
    public boolean inGame = false;      // 玩家是否在线标志
    
    public DBPlayerDetailProto buildPlayerDetail() {
            DBPlayerDetailProto.Builder builder = DBPlayerDetailProto.newBuilder();
            /*
            builder.setCards(cards.buildCardsProto()); // 卡片信息
            builder.setItems(items.buildItemsProto()); // 物品信息
            builder.setStages(stages.buildStagesProto()); // 关卡信息

            builder.setSandbox(sandbox.buildSandboxProto());
            builder.setBuyEnergyCount(buyEnergyCount);
            builder.setLastBuyEnergyDay(lastBuyEnergyDay);
            builder.setNormalActivity(normalActivity.buildDBNormalActivityProto());
            builder.setQuests(quests.buildQuestsProto());
            builder.setVipLastDayExp(vip.lastDayExp);
            builder.setViplastExpDay(vip.lastExpDay);
            builder.setVipGift(vipGift.receiveFlag);
            
            builder.setContSign(contSign.buildDBContSignProto());

            builder.setGuideStep(guideStep);
            */
            return builder.build();
        }
    
    public EnterGameCharProto buildEnterGameCharProto() {
        EnterGameCharProto.Builder builder = EnterGameCharProto.newBuilder();

        builder.setCharId(this.charId);
        builder.setCharName(this.charName);
        builder.setGoldCoin(this.goldCoin);
        builder.setSilverCoin(this.silverCoin);
        builder.setPopularity(this.popularity);
        builder.setDiamond(this.diamond);
        builder.setEnergy(energy);
        builder.setToken(token);
        builder.setCharLevel(this.charLevel);
        builder.setCharExp(this.charExp);

        builder.setVip(vip.buildVipProto());
        builder.setVipGift(vipGift.receiveFlag);

        builder.setContSign(contSign.buildContSignProto());

        builder.setEndForbidTalkTime(mapPlayer.endForbidTalkTime);

        return builder.build();
    }

    
    public void enterGame() {
        // 返回进入游戏角色信息给客户端
        sendMessage(ServerToClientMessageBuilder.buildEnterGameRet(buildEnterGameCharProto()));
    }
    
    public void leaveGame() {
    }
    
    public void talk(int channelType, String msg, int recvPlayerId) {
        if (mapPlayer.endForbidTalkTime <= 0 || ProjectCardServer.INSTANCE.getCurrentTime() >= mapPlayer.endForbidTalkTime) {
            TalkProto.Builder talkProtoResp = TalkProto.newBuilder();
            talkProtoResp.setChannelType(channelType);
            talkProtoResp.setMsg(msg);
            talkProtoResp.setSendPlayerId(this.charId);
            talkProtoResp.setSendPlayerName(this.charName);
            if (channelType == Consts.CHAT_WORLD_CHANNEL) {
                if ((this.charLevel >= Consts.ALLOW_TALK_LEVEL) && (ProjectCardServer.INSTANCE.getCurrentTime() >= nextWorldTalkTime)) {
                    ProjectCardServer.INSTANCE.broadcast(ServerToClientMessageBuilder.buildTalk(talkProtoResp.build()));
                    nextWorldTalkTime = ProjectCardServer.INSTANCE.getCurrentTime() + Consts.MILSECOND_1SECOND;
                }
            } else if (channelType == Consts.CHAT_PRIVATE_CHANNEL) {
                // 查看对话目标是否在黑名单中
                if (this.charId == recvPlayerId) {
                    // 与自己聊天 - 作为GM工具的入口
                    
                } else {
                    MapPlayer recvPlayer = ProjectCardServer.INSTANCE.players.get(recvPlayerId);
                    if (recvPlayer != null && recvPlayer.human != null) {
                        talkProtoResp.setRecvPlayerId(recvPlayerId);
                        talkProtoResp.setRecvPlayerName(recvPlayer.human.charName);
                        List<Integer> charIds = new ArrayList<Integer>(2);
                        charIds.add(this.charId);
                        charIds.add(recvPlayerId);
                        ProjectCardServer.INSTANCE.broadcast(charIds, ServerToClientMessageBuilder.buildTalk(talkProtoResp.build()), true);
                    }
                }
            } else {
                System.err.println("Player[" + this.charId + "] can't talk in channel[" + channelType + "]");
            }
        }
    }
    
    public void increaseGoldCoin(int num, int type, int subtype, boolean needSend) {
        if (num > 0) {
            if ((Long.MAX_VALUE - this.goldCoin) > num) {
                this.goldCoin += num;
            }
            vip.increaseVipExp(num, needSend);

            // 修改充值活动状态
            //normalActivity.chargeGold(num1);

            if (needSend) {
                sendMessage(ServerToClientMessageBuilder.buildGoldCoinChange(this.goldCoin));
            }
        }
    }

    // isConsume 是否为消费，为活动使用
    public void decreaseGoldCoin(int num, int type, int subtype, boolean needSend, boolean isConsume) {
        if (num > 0) {
            if (this.goldCoin > num) {
                this.goldCoin -= num;
            } else {
                this.goldCoin = 0;
            }

            if (needSend) {
                sendMessage(ServerToClientMessageBuilder.buildGoldCoinChange(this.goldCoin));
            }
        }
    }

    public void increaseSilverCoin(int num, int type, int subtype, boolean needSend) {
        if (num > 0) {
            if ((Long.MAX_VALUE - this.silverCoin) > num) {
                this.silverCoin += num;
            }

            if (needSend) {
                sendMessage(ServerToClientMessageBuilder.buildSilverCoinChange(this.silverCoin));
            }
        }
    }

    public void decreaseSilverCoin(int num, int type, int subtype, boolean needSend) {
        if (num > 0) {
            if (this.silverCoin > num) {
                this.silverCoin -= num;
            } else {
                this.silverCoin = 0;
            }

            if (needSend) {
                sendMessage(ServerToClientMessageBuilder.buildSilverCoinChange(this.silverCoin));
            }
        }
    }

    public void increaseDiamond(int num, int type, int subtype, boolean needSend) {
        if (num > 0) {
            if ((Long.MAX_VALUE - this.diamond) > num) {
                this.diamond += num;
            }

            if (needSend) {
                sendMessage(ServerToClientMessageBuilder.buildDiamondChange(this.diamond));
            }
        }
    }

    public void decreaseDiamond(int num, int type, int subtype, boolean needSend) {
        if (num > 0) {
            if (this.diamond > num) {
                this.diamond -= num;
            } else {
                this.diamond = 0;
            }

            if (needSend) {
                sendMessage(ServerToClientMessageBuilder.buildDiamondChange(this.diamond));
            }
        }
    }

    public void increasePopularity(int num, int type, int subtype, boolean needSend) {
        if (num > 0) {
            if ((Long.MAX_VALUE - this.popularity) > num) {
                this.popularity += num;
            }

            if (needSend) {
                sendMessage(ServerToClientMessageBuilder.buildPopularityChange(this.popularity));
            }
        }
    }

    public void decreasePopularity(int num, int type, int subtype, boolean needSend) {
        if (num > 0) {
            if (this.popularity > num) {
                this.popularity -= num;
            } else {
                this.popularity = 0;
            }

            if (needSend) {
                sendMessage(ServerToClientMessageBuilder.buildPopularityChange(this.popularity));
            }
        }
    }

    public int getHumanLevelupExperience(int level) {
        return (level < HumanLevelsConfig.INSTANCE.maxLevel) ? HumanLevelsConfig.INSTANCE.levelTemplates[level - 1].experience : -1;
    }

    public void levelup(int upLevel, boolean needSend) {
        /*
        int oldLv = this.charLevel;
        this.charLevel += upLevel;

        // 补满体力
        increaseEnergy(Integer.MAX_VALUE, needSend);
        
        ProjectCardServer.INSTANCE.briefPlayerInfos.levelupPlayer(this.charId, this.charLevel);

        quests.levelup(lv);

        if (needSend) {
            sendMessage(ServerToClientMessageBuilder.buildLevelup(this.charLevel));
        }

        // 调整等级列表数据
        PlayerIdsOfLevels.INSTANCE.removePlayerId(oldLv, this.charId);
        PlayerIdsOfLevels.INSTANCE.addPlayerId(this.charLevel, this.charId);

        // 更新服中最大玩家等级
        if (this.charLevel > ProjectCardServer.INSTANCE.maxHumanLevel) {
            ProjectCardServer.INSTANCE.maxHumanLevel = this.charLevel;
        }

        // 通知朋友等级改变
        broadcastToFriends(ServerToClientMessageBuilder.buildFriendLevelChange(this.charId, this.charLevel));
       */
    }

    public void increaseExperience(int num, int type, int subtype, boolean needSend) {
        if (num > 0) {
            int maxLevel = HumanLevelsConfig.INSTANCE.maxLevel;
            if (this.charLevel < maxLevel) {
                int upLevel = 0;
                this.charExp += num;

                int levelupExperience = getHumanLevelupExperience(this.charLevel);
                while ((levelupExperience != -1) && (this.charExp >= levelupExperience) && (this.charLevel + upLevel < maxLevel)) {
                    upLevel++;
                    this.charExp -= levelupExperience;
                    levelupExperience = getHumanLevelupExperience(this.charLevel + upLevel);
                }

                if (this.charLevel + upLevel >= maxLevel) {
                    this.charExp = 0;
                }

                if (upLevel > 0) {
                    levelup(upLevel, needSend);
                }
                if (needSend) {
                    sendMessage(ServerToClientMessageBuilder.buildExperienceChange(this.charExp));
                }
            }
        }
    }

    public void increaseEnergy(int num, boolean needSend) {
        if (num > 0) {
            int maxEnergy = HumanLevelsConfig.INSTANCE.levelTemplates[this.charLevel-1].maxEnergy;
            if (this.energy < maxEnergy) {
                this.energy += num;
                if ((this.energy <= 0) || (this.energy > maxEnergy)) {
                    this.energy = maxEnergy;
                }
                if (needSend) {
                    sendMessage(ServerToClientMessageBuilder.buildEnergyChange(this.energy));
                }
            }
        }
    }

    public void decreaseEnergy(int num, boolean needSend) {
        if (num > 0) {
            energy -= num;
            if (needSend) {
                sendMessage(ServerToClientMessageBuilder.buildEnergyChange(this.energy));
            }
        }
    }

    public void digestCertainReward(CertainRewardsInfo rewardsInfo, int type, int subType) {
        if (rewardsInfo.items != null) {
            for (CertainRewardItemInfo rewardInfo : rewardsInfo.items) {
                switch (rewardInfo.itemType) {
                    case Consts.REWARD_ITEM_TYPE_CARD: {
                        break;
                    }
                    case Consts.REWARD_ITEM_TYPE_ITEM: {
                        break;
                    }
                    case Consts.REWARD_ITEM_TYPE_GOLD: {
                        increaseGoldCoin(rewardInfo.num, type, subType, true);
                        break;
                    }
                    case Consts.REWARD_ITEM_TYPE_SILVER: {
                        increaseSilverCoin(rewardInfo.num, type, subType, true);
                        break;
                    }
                    case Consts.REWARD_ITEM_TYPE_DIAMOND: {
                        increaseDiamond(rewardInfo.num, type, subType, true);
                        break;
                    }
                    case Consts.REWARD_ITEM_TYPE_POPULARITY: {
                        increasePopularity(rewardInfo.num, type, subType, true);
                        break;
                    }
                    case Consts.REWARD_ITEM_TYPE_EXPERIENCE: {
                        increaseExperience(rewardInfo.num, type, subType, true);
                        break;
                    }
                    case Consts.REWARD_ITEM_TYPE_ENERGY: {
                        increaseEnergy(rewardInfo.num, true);
                        break;
                    }
                }
            }
        }
    }

    public void sendMessage(ProtobufMessage message) {
        if ((mapPlayer != null) && (mapPlayer.channelContext != null)) {
            mapPlayer.channelContext.write(message);
        }
    }
}
