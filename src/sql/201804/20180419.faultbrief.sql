ALTER TABLE `t_svc_fault_brief`
CHANGE COLUMN `psgd` `siteVideo`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `oprtId`,
CHANGE COLUMN `pscl` `diggerVideo`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `siteVideo`,
CHANGE COLUMN `syqk` `faultCheckVideo`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `diggerVideo`,
CHANGE COLUMN `jbxx` `faultRepairVideo`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `faultCheckVideo`,
CHANGE COLUMN `gzjc` `jhPic`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `faultRepairVideo`,
CHANGE COLUMN `gzcl` `timeMile`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `jhPic`,
CHANGE COLUMN `sjlc` `usage`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `timeMile`,
CHANGE COLUMN `yhyj` `fault`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `usage`,
ADD COLUMN `faultResove`  varchar(1) default '' AFTER `fault`,
ADD COLUMN `faultHandle`  varchar(1) default '' AFTER `faultResove`,
ADD COLUMN `faultRepair`  varchar(1) default '' AFTER `faultHandle`,
ADD COLUMN `opinion`  varchar(1) default '' AFTER `faultRepair`;

ALTER TABLE `t_svc_fault_brief`
CHANGE COLUMN `usage` `usageStat`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `timeMile`;


