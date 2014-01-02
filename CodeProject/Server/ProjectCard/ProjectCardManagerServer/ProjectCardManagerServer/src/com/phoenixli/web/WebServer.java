/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.web;

import com.phoenixli.database.DBHandler;
import com.phoenixli.manager.ProjectCardManagerServer;
import com.phoenixli.utils.Consts;
import com.phoenixli.utils.RandomGenerator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author rachel
 */
public class WebServer {
    private HashMap<String, String> playersToSessMap;            //PlayerInfo => sessionid
    private TreeMap<String, PlayerInfo> sessToPlayerInfoMap;        //sessionid => PlayerInfo
    public int maxCid;
    
    public boolean init(DBHandler dbHandler){
        playersToSessMap = new HashMap<String, String>();
        sessToPlayerInfoMap = new TreeMap<String, PlayerInfo>();
        return dbHandler.getMaxCid(this);
    }
    
    synchronized public PlayerInfo GetPlayerInfoByPassport( String thirdPartyUserId) {
        String sessionId = playersToSessMap.get(thirdPartyUserId);
        if(sessionId != null){
            PlayerInfo info = sessToPlayerInfoMap.get(sessionId);
            if(info != null ){
                if(info.time >= System.currentTimeMillis()){
                    info.time = System.currentTimeMillis() + ProjectCardManagerServer.INSTANCE.sessionPeriod;
                    return info;
                }
            } else {
                System.err.println("playersToSessMap contanis but sessToPlayerInfoMap not contains sesionid " + sessionId);
            }
        }
        return null;
    }

    synchronized public PlayerInfo newPlayerInfo(DBHandler dbHandler, String passport) {
        PlayerInfo playerInfo = dbHandler.getPlayerInfoFromDB(passport);
        if(playerInfo == null){
            if(dbHandler.insertPlayerInfo(passport, maxCid)){
                playerInfo = new PlayerInfo( passport, maxCid, 0, 0);
                maxCid ++;
            }
        }
        if(playerInfo != null){
            long now = System.currentTimeMillis();
            playerInfo.time = now + ProjectCardManagerServer.INSTANCE.sessionPeriod;
            String sessionId = getSessionId();
            int retry = 0;
            while(retry++ < Consts.PRODUCE_KEY_MAX_RETRY_TIMES){
                if(!sessToPlayerInfoMap.containsKey(sessionId)){
                    playerInfo.sessionId = sessionId;
                    playersToSessMap.put(playerInfo.userPassportId, playerInfo.sessionId);
                    sessToPlayerInfoMap.put(playerInfo.sessionId, playerInfo);
                    if(sessToPlayerInfoMap.size() >= ProjectCardManagerServer.INSTANCE.maxSessionNum) {
                        for (Iterator<PlayerInfo> it = sessToPlayerInfoMap.values().iterator(); it.hasNext(); ) {
                            PlayerInfo pi = it.next();
                            if (pi.time <= now) {
                                playersToSessMap.remove(pi.userPassportId);
                                it.remove();
                            }
                        }
                    }
                    return playerInfo;
                }
                sessionId = getSessionId();
            }
        }
        return null;
    }

    synchronized public PlayerInfo GetPlayerInfoBySessinoId(String sessionid) {
        PlayerInfo info = sessToPlayerInfoMap.get(sessionid);
        if (info != null) {
            if (info.time >= System.currentTimeMillis()){
                return info;
            } else {
                System.err.println("[" + new Date(System.currentTimeMillis())  + "] session [" + sessionid + "] out of time");
            }
        } else {
            System.err.println("[" + new Date(System.currentTimeMillis())  + "] session [" + sessionid + "] not found");
        }
        return null;
    }

    synchronized public void logout(String passport) {
        String sessionId = playersToSessMap.get(passport);
        if(sessionId != null){
            sessToPlayerInfoMap.remove(sessionId);
        }
        playersToSessMap.remove(passport);
    }
    
    private String getSessionId() {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = RandomGenerator.INSTANCE.generator;
        for (int i = 0; i < 12; i++) {
            sb.append(base.charAt(random.nextInt(base.length())));
        }
        return sb.toString();
    }
}
