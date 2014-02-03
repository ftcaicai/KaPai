/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phoenixli.server.card;

/**
 *
 * @author rachel
 */
public class CardStaticInfo {
    public int id;                  // 卡片id
    public int camp;                // 阵营（蜀国、吴国、魏国、群雄）
    public int type;                // 兵种（用于相克计算）
    public int star1;               // 星级
    public int star2;               // 转级
    public int maxLv;               // 最高等级
    public int STR;                 // 武力
    public int WIS;                 // 智力
    public int STA;                 // 统帅
    public int ATKBase;             // 攻击基础
    public float ATKGrow;           // 攻击成长
    public int DEXBase;             // 机动基础
    public float DEXGrow;           // 机动成长
    public int HPBase;              // 生命基础
    public float HPGrow;            // 生命成长
    public int HITBase;             // 命中基础（千分比）
    public int DODBase;             // 闪避基础（千分比）
    public int CRIBase;             // 暴击基础（千分比）
    public int TENBase;             // 韧性基础（千分比）
    public float cureUpBase;        // 基础治疗效率
    public float damResPBase;       // 基础物伤减免
    public float damResMBase;       // 基础魔伤减免
    public int leadPoint;           // 领导力
    public int normalSkillId;       // 普通技能号
    public int specialSkillId;      // 特殊技能号
    public int specialSkillPrepare; // 特殊技能准备时间
    public int specialSkillCycle;   // 特殊技能周期
    public int leaderTalentId;      // 主将天赋号（没有时为-1）
    public int soldPriceBase;       // 出售价格基础
    public int soldPriceGrow;       // 出售价格成长
}
