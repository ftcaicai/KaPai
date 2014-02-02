/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.stage;

import com.phoenixli.server.actor.Human;
import com.phoenixli.server.social.BriefPlayerInfo;

/**
 *
 * @author rachel
 */
public class Stage {
    public final Human human;
    
    public final StageStaticInfo staticInfo;
    public final BriefPlayerInfo helperInfo;
    
    
    public Stage(Human human, StageStaticInfo staticInfo, BriefPlayerInfo helperInfo) {
        this.human = human;
        this.staticInfo = staticInfo;        
        this.helperInfo = helperInfo;
    }
}
