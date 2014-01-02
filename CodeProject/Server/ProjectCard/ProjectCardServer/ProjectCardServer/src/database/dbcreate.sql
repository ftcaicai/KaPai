CREATE DATABASE IF NOT EXISTS `projectcard` DEFAULT CHARACTER SET utf8  COLLATE utf8_general_ci;

GRANT ALL ON projectcard.* TO 'phoenixtest'@'127.0.0.%' IDENTIFIED BY 'acY5qmGKVcRs4nST';
GRANT ALL ON mysql.* TO 'phoenixtest'@'127.0.0.%' IDENTIFIED BY 'acY5qmGKVcRs4nST';

USE `projectcard`;

/*
 * table character
 *
 */
CREATE TABLE `character` (
    `charId` int(11) NOT NULL,
    `charName` varchar(36) NOT NULL,
    `roleId` int(11) NOT NULL,    
    `gold` int(11) NOT NULL DEFAULT '0',    
    `silver` bigint(20) NOT NULL DEFAULT '0',
    `energy` int(11) NOT NULL DEFAULT '0',    
    `level` int(11) NOT NULL DEFAULT '1',
    `experience` int(11) NOT NULL DEFAULT '0',
    `vipLv` int(11) NOT NULL DEFAULT '0',
    `vipExp` int(11) NOT NULL DEFAULT '0',
    `totalOnlineTime` bigint(20) NOT NULL DEFAULT '0',
    `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `leavetime` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`charId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP PROCEDURE IF EXISTS `Get_Char_Num`;
DELIMITER $$
    CREATE PROCEDURE `Get_Char_Num`(
        pCharId	INT(11)
    )
    BEGIN
        SELECT COUNT(*) AS numOfChar
        FROM `character`
        WHERE `charId`=pCharId;
    END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `Create_Char`;
DELIMITER $$
    CREATE PROCEDURE `Create_Char`(
        pCharId	 INT(11),
        pCharName    VARCHAR(36),
        pRoleId     INT(11)
    )
    BEGIN
        INSERT INTO `charactor`(`charId`,`charName`,`roleId`) VALUES(pCharId,pCharName,pRoleId);
    END$$
DELIMITER ;