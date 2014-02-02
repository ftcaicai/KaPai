/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.quest;

import com.phoenixli.server.actor.Human;
import java.util.TreeMap;

/**
 *
 * @author rachel
 */
public class Quests {
    public final Human human;
    
    // 已接过关任务列表
    public TreeMap<Integer, Quest> stageQuests;
    // 已接升级任务列表
    public TreeMap<Integer, Quest> levelQuests;
    
    public Quests(Human human, QuestsProto questsProto) {
        this.human = human;
    }
}
