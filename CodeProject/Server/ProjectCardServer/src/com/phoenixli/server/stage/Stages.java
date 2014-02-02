/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.stage;

import com.phoenix.protobuf.ExternalCommonProtocol.StagesProto;
import com.phoenix.protobuf.ExternalCommonProtocol.VariableValueProto;
import com.phoenixli.config.MapConfig;
import com.phoenixli.server.ProjectCardServer;
import com.phoenixli.server.actor.Human;
import com.phoenixli.utils.Consts;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author rachel
 */
public class Stages {

    public final Human human;
    
    public int currentNormalStageId;            // 当前普通关卡
    public int currentBigStageId;               // 当前精英关卡
    
    public int lastBigStageDay;                 // 最后一次通过精英副本的日期

    public final TreeMap<Integer, Integer> lastBigStages = new TreeMap<Integer, Integer>();     // key为在lastBigStageDay所在的一天里通过的精英副本，value为次数
    
    public Stages(Human human, StagesProto stagesProto) {
        this.human = human;
        if (stagesProto != null) {
            
             int currDay = (int) ((ProjectCardServer.INSTANCE.getCurrentTime() + Consts.JET_LAG) / Consts.MILSECOND_ONE_DAY);
            
            this.currentNormalStageId = stagesProto.getCurrentNormalStageId();
            this.currentBigStageId = stagesProto.getCurrentBigStageId();
            
            if (currDay != stagesProto.getLastBigStageDay()) {
                lastBigStageDay = 0;
            } else {
                lastBigStageDay = currDay;
                List<VariableValueProto> lastBigStagesList = stagesProto.getLastBigStagesList();
                if (!lastBigStagesList.isEmpty()) {
                    for (VariableValueProto lastBigStage : lastBigStagesList) {
                        lastBigStages.put(lastBigStage.getId(), (int)lastBigStage.getValue());
                    }
                }
            }
        }
    }
    
     public StagesProto buildStagesProto() {
        StagesProto.Builder builder1 = StagesProto.newBuilder();

        builder1.setCurrentNormalStageId(currentNormalStageId);
        builder1.setCurrentBigStageId(currentBigStageId);
        
        int currDay = (int) ((ProjectCardServer.INSTANCE.getCurrentTime() + Consts.JET_LAG) / Consts.MILSECOND_ONE_DAY);
        
        if (lastBigStageDay == currDay) {
            builder1.setLastBigStageDay(lastBigStageDay);
            for (Iterator<Map.Entry<Integer, Integer>> iter = lastBigStages.entrySet().iterator(); iter.hasNext(); ) {
                Map.Entry<Integer, Integer> entry = iter.next();
                VariableValueProto.Builder builder2 = VariableValueProto.newBuilder();
                builder2.setId(entry.getKey());
                builder2.setValue(entry.getValue());

                builder1.addLastBigStages(builder2);
            }
        }

        return builder1.build();
    }

    public boolean isStagePassed(int stageId, boolean isBigStage) {
        if (isBigStage) {
            return stageId < currentBigStageId;
        } else {
            return stageId < currentNormalStageId;
        }
    }
    
    public boolean canEnterBigStage(int bigStageId) {
        int currDay = (int) ((ProjectCardServer.INSTANCE.getCurrentTime() + Consts.JET_LAG) / Consts.MILSECOND_ONE_DAY);

        if (lastBigStageDay != currDay) {
            return true;
        } else {
            Integer enterNum = lastBigStages.get(bigStageId);

            return (enterNum == null) || (enterNum < MapConfig.INSTANCE.bigStageEnterNumPerDay);
        }
    }
}
