/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.dbHandler;

import com.google.protobuf.CodedInputStream;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.phoenix.protobuf.ExternalCommonProtocol.CreateCharProto;
import com.phoenixli.common.messageQueue.ServerMessageQueue;
import com.phoenixli.server.message.messageBuilder.ServerMessageBuilder;
import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author rachel
 */
public class MapDBHandler {
    /**
     * 获取帐号角色数
     * @param connection
     * @param playerId
     * @return false 表示数据库已断连
     */
    public static boolean handleGetCharNum(Connection connection, int playerId) {
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareCall("{call Get_Char_Num(?)}");

            stmt.setInt(1, playerId);
            rs = stmt.executeQuery();

            int charNum = -1;
            if (rs.next()) {
                charNum = rs.getInt(1);
            }

            ServerMessageQueue.queue().offer(ServerMessageBuilder.buildCharNumMessage(playerId, charNum));
        } catch (MySQLNonTransientConnectionException ex) {
            // 数据库断线
            System.err.println("DB Get Char Num MySQLNonTransientConnectionException Error: " + ex.getMessage());
            return false;
        } catch (SQLException ex) {
            // 其他异常
            System.err.println("DB Get Char Num SQLException Error: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println("DB Get Char Num ResultSet Close SQLException Error: " + ex.getMessage());
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.err.println("DB Get Char Num Statement Close SQLException Error: " + ex.getMessage());
                }
            }
        }

        return true;
    }
    
    /**
     * 创建角色
     * @param connection
     * @param playerId
     * @param createCharInfo
     * @return false表示数据库断连
     */
    public static boolean handleCreateChar(Connection connection, int playerId, CreateCharProto createCharInfo) {
        CallableStatement stmt = null;
        try {
            stmt = connection.prepareCall("{call Create_Char(?,?,?)}");
            stmt.setInt(1, playerId);                   //mid
            stmt.setString(2, createCharInfo.getName());    //name
            int roleId = 0;
            //int leaderCardId = MapConfig.INSTANCE.initCardIds[createCharInfo.getJob()];
            stmt.setInt(3, roleId);     //leader card
            
            stmt.executeUpdate();

            // 创建角色成功
            ServerMessageQueue.queue().offer(ServerMessageBuilder.buildCreateCharRetMessage(playerId, 1));
        } catch (MySQLNonTransientConnectionException ex) {
            // 数据库断线
            System.err.println("DB Create Char MySQLNonTransientConnectionException Error: " + ex.getMessage());
            return false;
        } catch (SQLException ex) {
            System.err.println("DB Create Char SQLException Error: " + ex.getMessage());

            // 创建角色失败
            ServerMessageQueue.queue().offer(ServerMessageBuilder.buildCreateCharRetMessage(playerId, -1));
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.err.println("DB Create Char Statement Close Error: " + ex.getMessage());
                }
            }
        }

        return true;
    }
    
    /**
     * 获取角色详细信息
     * @param connection
     * @param playerId
     * @return false表示数据库断连
     */
    public static boolean handleGetCharDetail(Connection connection, int playerId) {
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareCall("{call Get_Char_Detail(?)}");
            stmt.setInt(1, playerId);
            rs = stmt.executeQuery();
            
            //CharDetailInfo charInfo = new CharDetailInfo();
            if (rs.next()) {
            /*    charInfo.mid = rs.getInt(1);
                charInfo.name = rs.getString(2);
                charInfo.gold1 = rs.getInt(3);
                charInfo.gold2 = rs.getInt(4);
                charInfo.silver = rs.getLong(5);
                charInfo.energy = rs.getInt(6);
                charInfo.token = rs.getInt(7);
                charInfo.level = rs.getInt(8);
                charInfo.experience = rs.getInt(9);
                charInfo.rankLevel = rs.getInt(10);
                charInfo.rankExperience = rs.getInt(11);
                charInfo.leaderCardId = rs.getInt(12);
                charInfo.leaderCardLevel = rs.getInt(13);
                charInfo.vipLevel = rs.getInt(14);
                charInfo.vipExperience = rs.getInt(15);
                charInfo.maxPower = rs.getInt(16);

                Blob charDetailBlob = rs.getBlob(17);
                if (charDetailBlob != null) {
                    try {
                        charInfo.detail = PlayerDetailProto.getDefaultInstance().newBuilderForType().mergeFrom(CodedInputStream.newInstance(charDetailBlob.getBinaryStream())).build();
                    } catch (IOException ex) {
                        GameLogger.getlogger().log(GameLogMessageBuilder.buildFileDBErrorGameLogMessage(StackTraceUtil.getStackTrace(ex)));

                        // TODO: 获取信息失败
                    }
                }

                Blob mailBlob = rs.getBlob(18);
                if (mailBlob != null) {
                    try {
                        charInfo.mailInfo = DBMailsProto.getDefaultInstance().newBuilderForType().mergeFrom(CodedInputStream.newInstance(mailBlob.getBinaryStream())).build();
                    } catch (IOException ex) {
                        GameLogger.getlogger().log(GameLogMessageBuilder.buildFileDBErrorGameLogMessage(StackTraceUtil.getStackTrace(ex)));

                        // TODO: 获取信息失败
                    }
                }

                charInfo.totalOnlineTime = rs.getLong(19);

                Timestamp timestamp = rs.getTimestamp(20);
                charInfo.leaveTime = (timestamp != null) ? timestamp.getTime() : GameServer.INSTANCE.getCurrentTime();

                ServerMessageQueue.queue().offer(ServerMessageBuilder.buildGetCharDetailRetMessage(charInfo.mid, charInfo));
                */
            } else {
                System.err.println("Player[" + playerId + "] not found in database.");
            }
        } catch (MySQLNonTransientConnectionException ex) {
            // 数据库断线
            System.err.println("DB Get Char Detail MySQLNonTransientConnectionException Error: " + ex.getMessage());
            return false;
        } catch (SQLException ex) {
            // TODO: 统计DB出错次数,当连续出错次数达到限值时,表示数据库故障
            System.err.println("DB Get Char Detail SQLException Error: " + ex.getMessage());

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println("DB Get Char Detail ResultSet Close Error: " + ex.getMessage());
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.err.println("DB Get Char Detail Statement Close Error: " + ex.getMessage());
                }
            }
        }

        return true;
    }
}
