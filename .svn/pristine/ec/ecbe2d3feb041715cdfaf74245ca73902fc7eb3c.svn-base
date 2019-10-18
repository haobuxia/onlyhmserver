/**
 需要在新数据库执行
 */

/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : svclog

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-03-30 17:44:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for svc_attendance
-- ----------------------------
DROP TABLE IF EXISTS `svc_attendance`;
CREATE TABLE `svc_attendance` (
  `SvcA_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcA_Time` datetime DEFAULT NULL,
  `SvcA_OperID` decimal(18,0) DEFAULT NULL,
  `SvcA_OperName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcA_Num` double DEFAULT NULL,
  `SvcA_SetOperID` decimal(18,0) DEFAULT NULL,
  PRIMARY KEY (`SvcA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_audit
-- ----------------------------
DROP TABLE IF EXISTS `svc_audit`;
CREATE TABLE `svc_audit` (
  `SvcA_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcA_TableName_En` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcA_TableName_Cn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcA_OprtID` decimal(18,0) NOT NULL,
  `SvcA_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcA_Result` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcA_Freason` text COLLATE utf8_unicode_ci,
  `SvcA_SendTime` datetime NOT NULL,
  `SvcA_Remark` text COLLATE utf8_unicode_ci,
  `SvcA_SvcM_ID` decimal(18,0) DEFAULT NULL,
  `SvcA_Svc_ID` decimal(18,0) NOT NULL,
  PRIMARY KEY (`SvcA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_auditlogtype_config
-- ----------------------------
DROP TABLE IF EXISTS `svc_auditlogtype_config`;
CREATE TABLE `svc_auditlogtype_config` (
  `SvcAC_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcAC_LogType` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcAC_OprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcAC_OprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcAC_Eff` datetime DEFAULT NULL,
  `SvcAC_Exp` datetime DEFAULT NULL,
  PRIMARY KEY (`SvcAC_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_cbfw_main
-- ----------------------------
DROP TABLE IF EXISTS `svc_cbfw_main`;
CREATE TABLE `svc_cbfw_main` (
  `SvcCM_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCM_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCM_zdl_rh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_zdl_hzdp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_zdl_rcjcxm` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_zdl_fowagl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_zdl_bzqxjfwgd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_zdl_pjdgcx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_zdl_czgl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_zdl_dcdpms` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_zjwg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_fdjzs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_cdwg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_cdsd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_cdyl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_dgwg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_dgsd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_dgyl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_dbwg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_dbsd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_dbyl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_zyzzjx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_ygly` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_hsghh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_xdc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_hzjgyw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_hzzs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_hzyl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_ygklyfz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_zbzfjtyfz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_fsxsw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_fdjpqggj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_srylzlkl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_fspd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_ysjpdzl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_fdjfdsfzc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_sb` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_qdmdsfzc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_fdjxpq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_fdjys` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_hzclrhzgd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_zcdyw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_jzxyw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_lgls` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_lgxt` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_tllmskl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_qdcqkl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_ldbwqbx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_ldblsdl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_zzlmsly` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_ydlmsly` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_zjzzlysx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_xjkl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_ktsfzc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_czgczl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_jkmbgzjl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_gzzzzrxjl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_jcl_xzsd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_SendTime` datetime NOT NULL,
  `SvcCM_Cno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_Ano` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_Sno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by8` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by11` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by12` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by13` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by14` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by15` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by16` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by17` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by18` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by19` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by20` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by21` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by22` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by23` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by24` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by25` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by26` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by27` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by28` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by29` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_by30` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCM_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCM_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCM_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCM_AuditStatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCM_Vcl_No` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCM_GzxsK` double NOT NULL,
  `SvcCM_VclGps_Lon` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCM_VclGps_La` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCM_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_cbfw_sub
-- ----------------------------
DROP TABLE IF EXISTS `svc_cbfw_sub`;
CREATE TABLE `svc_cbfw_sub` (
  `SvcCS_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCS_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCS_fieldNameEn` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_fieldNameCn` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_memo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_SendTime` datetime DEFAULT NULL,
  `SvcCS_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCS_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCS_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_chubao_info
-- ----------------------------
DROP TABLE IF EXISTS `svc_chubao_info`;
CREATE TABLE `svc_chubao_info` (
  `SvcI_DeptID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_SendTime` datetime DEFAULT NULL,
  `svct_sendtime` datetime DEFAULT NULL,
  `SvcI_deptName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_oprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_oprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_pstn` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_Brand` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_VclKind` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_VclType` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_vclno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_EType` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_ENo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_AuditStatus` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_IsOut` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_gzxs` double DEFAULT NULL,
  `SvcI_gzxsk` double DEFAULT NULL,
  `SvcT_sfazpsq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_dataName1` text COLLATE utf8_unicode_ci,
  `SvcI_gzyy` text COLLATE utf8_unicode_ci,
  `SvcCSY_gzzl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_dataName2` text COLLATE utf8_unicode_ci,
  `SvcI_gznr` text COLLATE utf8_unicode_ci,
  `SvcCJ_jgnr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_wwcyy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_sftj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_sfxf` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_yhmc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_lxr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_lxfs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_ksfwsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_jsfwsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_cfsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_fhsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_lc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_svctype` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `SvcI_rwh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_Svc_id` decimal(19,0) NOT NULL,
  `SvcI_SvcCSJ_Lc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_fwrq` date DEFAULT NULL,
  `fwnr` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_claim
-- ----------------------------
DROP TABLE IF EXISTS `svc_claim`;
CREATE TABLE `svc_claim` (
  `SvcCL_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCL_REID` decimal(18,0) NOT NULL,
  `SvcCL_RETableName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCL_ReName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCL_KopenNumber` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_SPT_Type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_AuditStatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SvcCL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_comm_data
-- ----------------------------
DROP TABLE IF EXISTS `svc_comm_data`;
CREATE TABLE `svc_comm_data` (
  `SvcCD_Id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCD_DataTypeID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCD_DataType` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCD_DataCode` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCD_DataName` text COLLATE utf8_unicode_ci NOT NULL,
  `SvcCD_PID` decimal(18,0) DEFAULT NULL,
  `SvcCD_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCD_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCD_DeptID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCD_DeptName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCD_Eff` datetime NOT NULL,
  `SvcCD_Exp` datetime NOT NULL,
  `SvcCD_KopenID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCD_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_comm_data_type
-- ----------------------------
DROP TABLE IF EXISTS `svc_comm_data_type`;
CREATE TABLE `svc_comm_data_type` (
  `SvcCDT_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCDT_dataType` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCDT_IsAllowModify` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCDT_Mark` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDT_Eff` datetime NOT NULL,
  `SvcCDT_Exp` datetime NOT NULL,
  `SvcCDT_OprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDT_OprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDT_DeptID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDT_DeptName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCDT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_comm_dkfx
-- ----------------------------
DROP TABLE IF EXISTS `svc_comm_dkfx`;
CREATE TABLE `svc_comm_dkfx` (
  `SvcCDK_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCDK_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCDK_khkh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCDK_fyxm1mc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_fyxm1ye` decimal(18,2) DEFAULT NULL,
  `SvcCDK_fyxm1kfje` decimal(18,2) DEFAULT NULL,
  `SvcCDK_fyxm2mc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_fyxm2ye` decimal(18,2) DEFAULT NULL,
  `SvcCDK_fyxm2kfje` decimal(18,2) DEFAULT NULL,
  `SvcCDK_SendTime` datetime DEFAULT NULL,
  `SvcCDK_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCDK_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCDK_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_comm_jgnr
-- ----------------------------
DROP TABLE IF EXISTS `svc_comm_jgnr`;
CREATE TABLE `svc_comm_jgnr` (
  `SvcCJ_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCJ_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCJ_jgnr` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCJ_wwcyy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_SendTime` datetime NOT NULL,
  `SvcCJ_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCJ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_comm_lj
-- ----------------------------
DROP TABLE IF EXISTS `svc_comm_lj`;
CREATE TABLE `svc_comm_lj` (
  `SvcCL_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCL_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCL_ly` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCL_Type` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCL_dh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCL_ljmc` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCL_jh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCL_sl` int(11) NOT NULL,
  `SvcCL_jg` decimal(18,2) NOT NULL,
  `SvcCL_SendTime` datetime NOT NULL,
  `SvcCL_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCL_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCL_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_comm_pic
-- ----------------------------
DROP TABLE IF EXISTS `svc_comm_pic`;
CREATE TABLE `svc_comm_pic` (
  `SvcCP_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCP_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCP_fieldNameEn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCP_fieldNameCn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCP_photoUrl` text COLLATE utf8_unicode_ci NOT NULL,
  `SvcCP_photoMemo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCP_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCP_photoName` text COLLATE utf8_unicode_ci NOT NULL,
  `SvcCP_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCP_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCP_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCP_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCP_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCP_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCP_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCP_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCP_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCP_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCP_Svc_ID` decimal(18,0) DEFAULT NULL,
  PRIMARY KEY (`SvcCP_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_comm_sbjbxx
-- ----------------------------
DROP TABLE IF EXISTS `svc_comm_sbjbxx`;
CREATE TABLE `svc_comm_sbjbxx` (
  `SvcCSB_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCSB_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSB_jqszsheng` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSB_jqszshi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSB_jqszx` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSB_jqszz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_jqszc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_fwrq` date NOT NULL,
  `SvcCSB_gzxs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_gzxszp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_sfazpsq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_psqpp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_psqxh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_psqjxs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_SendTime` datetime DEFAULT NULL,
  `SvcCSB_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCSB_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_comm_sjlc
-- ----------------------------
DROP TABLE IF EXISTS `svc_comm_sjlc`;
CREATE TABLE `svc_comm_sjlc` (
  `SvcCSJ_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCSJ_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSJ_tld` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_cfsj` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSJ_ksfwsj` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSJ_jsfwsj` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSJ_fhsj` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSJ_lc` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSJ_SendTime` datetime NOT NULL,
  `SvcCSJ_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCSJ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_comm_syqk
-- ----------------------------
DROP TABLE IF EXISTS `svc_comm_syqk`;
CREATE TABLE `svc_comm_syqk` (
  `SvcCSY_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCSY_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSY_gzzl` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSY_hj` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSY_gztj` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSY_dmtj` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSY_ry` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSY_fjj` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSY_gz` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCSY_SendTime` datetime NOT NULL,
  `SvcCSY_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCSY_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_comm_yhxx
-- ----------------------------
DROP TABLE IF EXISTS `svc_comm_yhxx`;
CREATE TABLE `svc_comm_yhxx` (
  `SvcCY_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCY_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCY_yhmc` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCY_lxr` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCY_lxfs` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCY_SendTime` datetime NOT NULL,
  `SvcCY_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCY_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_comm_yhyj
-- ----------------------------
DROP TABLE IF EXISTS `svc_comm_yhyj`;
CREATE TABLE `svc_comm_yhyj` (
  `SvcCYH_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCYH_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCYH_mycd` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCYH_jyhyj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_qrqz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_SendTime` datetime NOT NULL,
  `SvcCYH_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCYH_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCYH_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_config_web
-- ----------------------------
DROP TABLE IF EXISTS `svc_config_web`;
CREATE TABLE `svc_config_web` (
  `SvcCW_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcCW_Func_ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCW_Func_Name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCW_CNameEn` text COLLATE utf8_unicode_ci,
  `SvcCW_CNameCn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCW_CNType` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCW_Sort` decimal(18,0) NOT NULL,
  `SvcCW_IsDefaultView` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCW_IsView` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCW_IsLink` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcCW_LinkContent` text COLLATE utf8_unicode_ci,
  `SvcCW_Eff` datetime NOT NULL,
  `SvcCW_Exp` datetime NOT NULL,
  `SvcCW_OprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCW_OprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCW_DeptID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCW_DeptName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcCW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_datasplit
-- ----------------------------
DROP TABLE IF EXISTS `svc_datasplit`;
CREATE TABLE `svc_datasplit` (
  `SvcDS_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcDS_Function` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcDS_GUID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcDS_Index` int(11) NOT NULL,
  `SvcDS_End` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcDS_SendTime` datetime NOT NULL,
  `SvcDS_Content` longtext COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SvcDS_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_dqby_sub
-- ----------------------------
DROP TABLE IF EXISTS `svc_dqby_sub`;
CREATE TABLE `svc_dqby_sub` (
  `SvcDS_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcDS_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcDS_fieldNameEn` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_fieldNameCn` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_memo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_SendTime` datetime NOT NULL,
  `SvcDS_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDS_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcDS_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_dqby_workhour
-- ----------------------------
DROP TABLE IF EXISTS `svc_dqby_workhour`;
CREATE TABLE `svc_dqby_workhour` (
  `SvcDW_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcDW_Number` bigint(20) DEFAULT NULL,
  `SvcDW_MinNumber` bigint(20) DEFAULT NULL,
  `SvcDW_ComNumber` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDW_MaxNumber` bigint(20) DEFAULT NULL,
  `SvcDW_OprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDW_Oprt_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDW_Eff` datetime DEFAULT NULL,
  `SvcDW_Exp` datetime DEFAULT NULL,
  `SvcDW_Condtion1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcDW_Condtion2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcDW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_fault_track
-- ----------------------------
DROP TABLE IF EXISTS `svc_fault_track`;
CREATE TABLE `svc_fault_track` (
  `SFT_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SFT_SvcT_ID` decimal(18,0) NOT NULL,
  `SFT_RemoveTime` datetime DEFAULT NULL,
  `SFT_Remove_DeptID` decimal(18,0) DEFAULT NULL,
  `SFT_Remove_DeptName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SFT_Days` int(11) DEFAULT NULL,
  `SFT_Grade` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SFT_gzfsrq` datetime DEFAULT NULL,
  `SFT_sftj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SFT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_fault_track_audit
-- ----------------------------
DROP TABLE IF EXISTS `svc_fault_track_audit`;
CREATE TABLE `svc_fault_track_audit` (
  `SFTA_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SFTA_SFT_ID` decimal(18,0) NOT NULL,
  `SFTA_Oprt_ID` decimal(18,0) NOT NULL,
  `SFTA_Oprt_Name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SFTA_Time` datetime NOT NULL,
  `SFTA_Audit_Content` text COLLATE utf8_unicode_ci,
  `SFTA_Status` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SFTA_Fitem` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SFTA_Sitem` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SFTA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_faulttrack_config
-- ----------------------------
DROP TABLE IF EXISTS `svc_faulttrack_config`;
CREATE TABLE `svc_faulttrack_config` (
  `SvcFTC_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcFTC_Level` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFTC_Oprt_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFTC_Oprt_Name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFTC_FaultLevelMsg_Send` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFTC_Eff` datetime NOT NULL,
  `SvcFTC_Exp` datetime NOT NULL,
  PRIMARY KEY (`SvcFTC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_field_config
-- ----------------------------
DROP TABLE IF EXISTS `svc_field_config`;
CREATE TABLE `svc_field_config` (
  `SvcFC_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcFC_tableNameEn` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFC_tableNameCn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_fieldNameEn` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_fieldNameCn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_filedType` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_filedListID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_filedListName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFC_filedIsView` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_filedSortNo` decimal(10,0) NOT NULL,
  `SvcFC_filedIsMust` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_IsDef` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFC_Eff` datetime DEFAULT NULL,
  `SvcFC_Exp` datetime DEFAULT NULL,
  `SvcFC_OprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFC_OprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFC_DeptID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFC_DeptName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcFC_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_field_config_xzsw
-- ----------------------------
DROP TABLE IF EXISTS `svc_field_config_xzsw`;
CREATE TABLE `svc_field_config_xzsw` (
  `SvcFC_id` decimal(18,0) NOT NULL,
  `SvcFC_tableNameEn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_tableNameCn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_fieldNameEn` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_fieldNameCn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_filedType` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_filedListID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_filedListName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFC_filedIsView` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_filedSortNo` decimal(10,0) NOT NULL,
  `SvcFC_filedIsMust` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcFC_IsDef` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFC_Eff` datetime DEFAULT NULL,
  `SvcFC_Exp` datetime DEFAULT NULL,
  `SvcFC_OprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFC_OprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFC_DeptID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFC_DeptName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_func_table
-- ----------------------------
DROP TABLE IF EXISTS `svc_func_table`;
CREATE TABLE `svc_func_table` (
  `SvcFT_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcFT_Func_ID` decimal(18,0) DEFAULT NULL,
  `SvcFT_Table_Name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcFT_Eff` datetime DEFAULT NULL,
  `SvcFT_Exp` datetime DEFAULT NULL,
  PRIMARY KEY (`SvcFT_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_gcgz_main
-- ----------------------------
DROP TABLE IF EXISTS `svc_gcgz_main`;
CREATE TABLE `svc_gcgz_main` (
  `SvcGCM_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcGCM_rwh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_fwnr` text COLLATE utf8_unicode_ci,
  `SvcGCM_xfzp_zj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_xfzp_jh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_xfzp_xss` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_xfzp_bjbh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_xfzp_ghq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_xfzp_xjjdb` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_xfzp_ghh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_xfzp_qt` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_xfzp_fwbg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_xfzp_fyfpzp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_bw1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_bw2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_bw3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_gzdm` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_jsms` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_wxfy` decimal(18,2) DEFAULT NULL,
  `SvcGCM_SendTime` datetime NOT NULL,
  `SvcGCM_Cno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_Ano` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_Sno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by8` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by11` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by12` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by13` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by14` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by15` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by16` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by17` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by18` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by19` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by20` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by21` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by22` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by23` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by24` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by25` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by26` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by27` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by28` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by29` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_by30` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCM_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGCM_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGCM_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGCM_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGCM_AuditStatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGCM_VclGps_Lon` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGCM_VclGps_La` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGCM_Vcl_No` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGCM_GzxsK` double NOT NULL,
  PRIMARY KEY (`SvcGCM_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_gcgz_wxfy
-- ----------------------------
DROP TABLE IF EXISTS `svc_gcgz_wxfy`;
CREATE TABLE `svc_gcgz_wxfy` (
  `SvcGCW_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcGCW_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGCW_xm` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_je` decimal(18,2) DEFAULT NULL,
  `SvcGCW_SendTime` datetime NOT NULL,
  `SvcGCW_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGCW_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcGCW_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_gsgz_main
-- ----------------------------
DROP TABLE IF EXISTS `svc_gsgz_main`;
CREATE TABLE `svc_gsgz_main` (
  `SvcGSM_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcGSM_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGSM_fwnr` text COLLATE utf8_unicode_ci,
  `SvcGSM_xfzp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_SendTime` datetime NOT NULL,
  `SvcGSM_Cno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_Ano` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_Sno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by8` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by11` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by12` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by13` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by14` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by15` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by16` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by17` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by18` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by19` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by20` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by21` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by22` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by23` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by24` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by25` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by26` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by27` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by28` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by29` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_by30` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGSM_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGSM_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGSM_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGSM_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGSM_AuditStatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGSM_VclGps_Lon` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGSM_VclGps_La` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGSM_Vcl_No` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGSM_GzxsK` double NOT NULL,
  PRIMARY KEY (`SvcGSM_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_gzcl_main
-- ----------------------------
DROP TABLE IF EXISTS `svc_gzcl_main`;
CREATE TABLE `svc_gzcl_main` (
  `SvcGM_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcGM_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGM_zcwcrq` datetime DEFAULT NULL,
  `SvcGM_sfys` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_ysgsf` decimal(10,2) DEFAULT NULL,
  `SvcGM_sftj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gznr` text COLLATE utf8_unicode_ci,
  `SvcGM_gzfsrq` datetime DEFAULT NULL,
  `SvcGM_gzjc_gzyy` text COLLATE utf8_unicode_ci,
  `SvcGM_gzjc_bw1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_bw2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_bw3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_gzdm` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_gzzpjh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_gzzpzj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_gzzpgzyj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_gzzpgzzj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_gzzpgzjj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_gzzpgzjc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_wtrhjj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_ysljf` decimal(10,2) DEFAULT NULL,
  `SvcGM_gzjc_sfjxgzcl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_wjxgzclyy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_ssjcf` decimal(10,2) DEFAULT NULL,
  `SvcGM_gzjc_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_by8` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzjc_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_sfxf` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_wxfyy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_ssljf` decimal(10,2) DEFAULT NULL,
  `SvcGM_gzcl_ssgsf` decimal(10,2) DEFAULT NULL,
  `SvcGM_gzcl_wxfy` decimal(18,2) DEFAULT NULL,
  `SvcGM_gzcl_xfzpxss` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_xfzpjh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_xfzpzj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_xfzpyj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_xfzpzj1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_xfzpjj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_xfzpfyfpzp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_fwryyj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_SendTime` datetime NOT NULL,
  `SvcGM_Cno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_Ano` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_Sno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_GzxsK` double DEFAULT NULL,
  `SvcGM_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGM_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGM_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGM_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGM_AuditStatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGM_VclGps_Lon` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGM_VclGps_La` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGM_Vcl_No` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcGM_gzjc_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcGM_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_gzcl_wxfy
-- ----------------------------
DROP TABLE IF EXISTS `svc_gzcl_wxfy`;
CREATE TABLE `svc_gzcl_wxfy` (
  `SvcGW_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcGW_rwh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_xm` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_je` decimal(18,2) DEFAULT NULL,
  `SvcGW_SendTime` datetime DEFAULT NULL,
  `SvcGW_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGW_Svc_ID` decimal(18,0) DEFAULT NULL,
  PRIMARY KEY (`SvcGW_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_info
-- ----------------------------
DROP TABLE IF EXISTS `svc_info`;
CREATE TABLE `svc_info` (
  `SvcI_DeptID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_SendTime` datetime DEFAULT NULL,
  `svct_sendtime` datetime DEFAULT NULL,
  `SvcI_deptName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_oprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_oprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_pstn` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_Brand` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_VclKind` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_VclType` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_vclno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_EType` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_ENo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_AuditStatus` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_IsOut` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_gzxs` double DEFAULT NULL,
  `SvcI_gzxsk` double DEFAULT NULL,
  `SvcT_sfazpsq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_dataName1` text COLLATE utf8_unicode_ci,
  `SvcI_gzyy` text COLLATE utf8_unicode_ci,
  `SvcCSY_gzzl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_dataName2` text COLLATE utf8_unicode_ci,
  `SvcI_gznr` text COLLATE utf8_unicode_ci,
  `SvcCJ_jgnr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_wwcyy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_sftj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_sfxf` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_yhmc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_lxr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_lxfs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_ksfwsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_jsfwsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_cfsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_fhsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_lc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_svctype` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `SvcI_rwh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_Svc_id` decimal(19,0) NOT NULL,
  `SvcI_SvcCSJ_Lc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSB_fwrq` date DEFAULT NULL,
  `fwnr` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_dmtj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_gztj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `svcT_Remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_ysjcf` decimal(18,0) DEFAULT NULL,
  `SvcT_ysgsf` decimal(18,0) DEFAULT NULL,
  `QuYu` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_info_all
-- ----------------------------
DROP TABLE IF EXISTS `svc_info_all`;
CREATE TABLE `svc_info_all` (
  `SvcI_DeptID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_SendTime` datetime DEFAULT NULL,
  `SvcI_deptName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_oprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_oprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_pstn` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_Brand` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_VclKind` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_VclType` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_vclno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_EType` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_ENo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_AuditStatus` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_IsOut` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_gzxs` double DEFAULT NULL,
  `SvcI_gzxsk` double DEFAULT NULL,
  `SvcT_sfazpsq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_dataName1` text COLLATE utf8_unicode_ci,
  `SvcI_gzyy` text COLLATE utf8_unicode_ci,
  `SvcCSY_gzzl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_dataName2` text COLLATE utf8_unicode_ci,
  `SvcI_gznr` text COLLATE utf8_unicode_ci,
  `SvcCJ_jgnr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCJ_wwcyy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_sftj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_sfxf` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_yhmc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_lxr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCY_lxfs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_ksfwsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_jsfwsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_cfsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_fhsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSJ_lc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_svctype` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `SvcI_rwh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcI_Svc_id` decimal(19,0) NOT NULL,
  `SvcI_SvcCSJ_Lc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcGM_gzcl_ssljf` decimal(10,2) DEFAULT NULL,
  `SvcGM_gzfsrq` datetime DEFAULT NULL,
  `SvcGM_gzjc_gzyy` text COLLATE utf8_unicode_ci,
  `SvcCSY_dmtj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcCSY_gztj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `svcT_Remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_ysjcf` decimal(18,0) DEFAULT NULL,
  `QuYu` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_jjfw_main
-- ----------------------------
DROP TABLE IF EXISTS `svc_jjfw_main`;
CREATE TABLE `svc_jjfw_main` (
  `SvcJM_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcJM_rwh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjqd_bj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjqd_gj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjqd_sj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjqd_qd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_lbjqr_yyjxg_zb` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_lbjqr_yyjxg_zf` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_lbjqr_yyjxg_yg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_lbjqr_yyjxg_md` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_lbjqr_yyjxg_hzjg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_lbjqr_fdjxg_fdj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_lbjqr_fdjxg_cyyx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_lbjqr_fdjxg_jylx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_lbjqr_fdjxg_cylx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_lbjqr_fdjxg_ffslx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_lbjqr_fdjxg_kqlx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_czg_aqsdg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_czg_xzczg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_czg_zczsb` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_czg_yczsb` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_kgjgn_yskg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_kgjgn_ymkzp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_kgjgn_dyq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_kgjgn_dkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_kgjgn_hzsjkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_kgjgn_bjfmqkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_kgjgn_ysblbkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_kgjgn_zsbankg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_kgjgn_jssdkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_kgjgn_bbykg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_bjgn_kgjgn_hzbykg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jbjc_jyyw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jbjc_lqsw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jbjc_byjkq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_zysx_cdbj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_zysx_klds` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_zysx_yyyw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_zysx_ryyw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_zysx_fdjsw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jjtzx_fdjsw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jjtzx_yyyw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jjtzx_jyyl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_xsbf_yrkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_xsbf_gzms` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_xsbf_hzsd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_xsbf_ysjkq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_xsbf_zdjs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_xsbf_xzsd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_xsbf_csjlgn` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_yb_fdjsw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_yb_ryj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_yb_yyywd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_yb_szxskg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_msxzkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_zdjs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_xzsd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_yskg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_ccxdjkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_bykg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_llxzkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_hwkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_xsxxkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_srqrkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_yjjkqkg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_kthnf` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_jkq_jkqkg_syj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_fowa_ry` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_fowa_jy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_fowa_lqs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_fowa_kq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdqjc_jcwgsfylyxx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdqjc_qcfdjdpsrqzwzw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdqjc_jcsfylqlsxx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdqjc_jcybjsqsfyyc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdqjc_jclqyyywjs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdqjc_jcfdjydkndywjy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdqjc_jcryywjy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdqjc_jcysflqzdcjwfs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdqjc_jcyyyxywjy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdqjc_jcdqxl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdqjc_jclbdgn` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qd_zcqd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qd_ltqdfdj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdh_xjqdmh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qdh_njcz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gbfdjh_djqjxxssflyhls` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gbfdjh_gryxjzry` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gbfdjh_jcfdjssfqj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gbfdjh_qcnfzxbctsdnt` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gzzdzysx_jqdyd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gzzdzysx_tzjq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gzzdzysx_jqdzx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gzzdzysx_gzzzdkzhcz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gzzdzysx_gzmsdcz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gzzdzysx_ybxzczsm` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gzzdzysx_tfjq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gzzdzysx_wczyhdjc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gzzdzysx_ss` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_gzzdzysx_ltcz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_jzcz_lyhzlcz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_jzcz_lyxzlcz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_jzcz_lyyygxcmdddcz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_jzcz_lycdxlldcz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_jzcz_lyjqxlldcz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_jzcz_wjydyzdm` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_jzcz_gsxzstrzhczg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qt_rczywch` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qt_hljjgh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qt_cqcf` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_zqcz_qt_bfgzdxx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_SendTime` datetime DEFAULT NULL,
  `SvcJM_Cno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_Ano` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_Sno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by8` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by11` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by12` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by13` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by14` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by15` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by16` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by17` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by18` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by19` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by20` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by21` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by22` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by23` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by24` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by25` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by26` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by27` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by28` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by29` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_by30` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_OprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_OprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_DeptID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_DeptName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_AuditStatus` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_VclGps_Lon` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_VclGps_La` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_Vcl_No` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcJM_GzxsK` double DEFAULT NULL,
  PRIMARY KEY (`SvcJM_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_ljllckd
-- ----------------------------
DROP TABLE IF EXISTS `svc_ljllckd`;
CREATE TABLE `svc_ljllckd` (
  `SvcLLCKD_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcLLCKD_dh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_ckrq` datetime DEFAULT NULL,
  `SvcLLCKD_ljmc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_jh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_sl` double DEFAULT NULL,
  `SvcLLCKD_jg` double DEFAULT NULL,
  `SvcLLCKD_zt` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_Eff` datetime NOT NULL,
  `SvcLLCKD_Exp` datetime NOT NULL,
  `SvcLLCKD_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_OprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLLCKD_OprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcLLCKD_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_ljxs
-- ----------------------------
DROP TABLE IF EXISTS `svc_ljxs`;
CREATE TABLE `svc_ljxs` (
  `SvcL_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcL_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcL_fwnr` text COLLATE utf8_unicode_ci,
  `SvcL_stmd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_stxg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_ljxq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_khxq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_wdmdyy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_cjd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_xsqrdbh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_ddljzl1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_ddljzl2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_ljmc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_ddyy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_jzdsljdj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_ddsl` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_zp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_SendTime` datetime NOT NULL,
  `SvcL_Cno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_Ano` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_Sno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by8` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by11` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by12` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by13` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by14` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by15` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by16` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by17` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by18` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by19` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by20` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by21` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by22` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by23` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by24` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by25` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by26` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by27` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by28` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by29` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_by30` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcL_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcL_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcL_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcL_AuditStatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcL_Vcl_No` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcL_GzxsK` double NOT NULL,
  `SvcL_VclGPS_Lon` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcL_VclGPS_La` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcL_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_ljxsckd
-- ----------------------------
DROP TABLE IF EXISTS `svc_ljxsckd`;
CREATE TABLE `svc_ljxsckd` (
  `SvcLJXSD_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcLJXSD_dh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcLJXSD_ckrq` datetime DEFAULT NULL,
  `SvcLJXSD_ljmc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_jh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcLJXSD_sl` int(11) NOT NULL,
  `SvcLJXSD_jg` decimal(18,2) NOT NULL,
  `SvcLJXSD_zt` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_Eff` datetime NOT NULL,
  `SvcLJXSD_Exp` datetime NOT NULL,
  `SvcLJXSD_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_OprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcLJXSD_OprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcLJXSD_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_mobileloginrecord
-- ----------------------------
DROP TABLE IF EXISTS `svc_mobileloginrecord`;
CREATE TABLE `svc_mobileloginrecord` (
  `SvcMLR_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcMLR_LoginTime` datetime NOT NULL,
  `SvcMLR_Oprt_ID` decimal(18,0) DEFAULT NULL,
  `SvcMLR_IMEI` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcMLR_IMSI` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcMLR_SN` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcMLR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_mobilesn
-- ----------------------------
DROP TABLE IF EXISTS `svc_mobilesn`;
CREATE TABLE `svc_mobilesn` (
  `SvcMSN_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcMSN_SendTime` datetime NOT NULL,
  `SvcMSN_AddTime` datetime DEFAULT NULL,
  `SvcMSN_IMEI` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcMSN_IMSI` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcMSN_SN` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcMSN_DictSNS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SvcMSN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_mobilesn_log
-- ----------------------------
DROP TABLE IF EXISTS `svc_mobilesn_log`;
CREATE TABLE `svc_mobilesn_log` (
  `SvcMSNL_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcMSNL_Oprt_ID` decimal(18,0) DEFAULT NULL,
  `SvcMSNL_Account` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcMSNL_OperTime` datetime DEFAULT NULL,
  `SvcMSNL_IP` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcMSNL_OperType` int(11) DEFAULT NULL,
  `SvcMSNL_SvcMSN_ID` decimal(18,0) NOT NULL,
  `SvcMSNL_SvcMSN_SendTime` datetime DEFAULT NULL,
  `SvcMSNL_SvcMSN_AddTime` datetime DEFAULT NULL,
  `SvcMSNL_SvcMSN_IMEI` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcMSNL_SvcMSN_IMSI` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcMSNL_SvcMSN_SN` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcMSNL_SvcMSN_DictSNS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SvcMSNL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_mobtransfer_log
-- ----------------------------
DROP TABLE IF EXISTS `svc_mobtransfer_log`;
CREATE TABLE `svc_mobtransfer_log` (
  `SvcML_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcML_OprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcML_OprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcML_Method_Name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcML_Parameters` longtext COLLATE utf8_unicode_ci NOT NULL,
  `SvcML_Returns` longtext COLLATE utf8_unicode_ci NOT NULL,
  `SvcML_Exception` longtext COLLATE utf8_unicode_ci,
  `SvcML_IP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcML_TranTime` datetime NOT NULL,
  PRIMARY KEY (`SvcML_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_msg
-- ----------------------------
DROP TABLE IF EXISTS `svc_msg`;
CREATE TABLE `svc_msg` (
  `SvcM_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcM_msg_type` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcM_msg_content` text COLLATE utf8_unicode_ci NOT NULL,
  `SvcM_isread` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcM_SendTime` datetime NOT NULL,
  `SvcM_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcM_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcM_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcM_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcM_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcM_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcM_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcM_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcM_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcM_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcM_Rec_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcM_Rec_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcM_Send_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcM_Send_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SvcM_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_para
-- ----------------------------
DROP TABLE IF EXISTS `svc_para`;
CREATE TABLE `svc_para` (
  `SvcP_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcP_value` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `SvcP_Description` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SvcP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_personal_layout
-- ----------------------------
DROP TABLE IF EXISTS `svc_personal_layout`;
CREATE TABLE `svc_personal_layout` (
  `SvcPL_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcPL_SvcCW_ID` decimal(18,0) NOT NULL,
  `SvcPL_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcPL_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcPL_Func_ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcPL_Func_Name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcPL_CNameEn` text COLLATE utf8_unicode_ci,
  `SvcPL_CNameCn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcPL_Sort` decimal(18,0) NOT NULL,
  `SvcPL_IsDefaultView` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcPL_IsView` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcPL_IsLink` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcPL_LinkContent` text COLLATE utf8_unicode_ci,
  `SvcPL_Eff` datetime NOT NULL,
  `SvcPL_Exp` datetime NOT NULL,
  PRIMARY KEY (`SvcPL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_personal_search
-- ----------------------------
DROP TABLE IF EXISTS `svc_personal_search`;
CREATE TABLE `svc_personal_search` (
  `SvcPS_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcPS_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcPS_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcPS_FuncID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcPS_FuncName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcPS_Condition` longtext COLLATE utf8_unicode_ci NOT NULL,
  `SvcPS_ConditionSql` longtext COLLATE utf8_unicode_ci NOT NULL,
  `SvcPS_ConditionDT` longtext COLLATE utf8_unicode_ci,
  `SvcPS_Eff` datetime NOT NULL,
  `SvcPS_Exp` datetime NOT NULL,
  PRIMARY KEY (`SvcPS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_remindlog
-- ----------------------------
DROP TABLE IF EXISTS `svc_remindlog`;
CREATE TABLE `svc_remindlog` (
  `SvcRL_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcRL_SvcT_ID` decimal(18,0) NOT NULL,
  `SvcRL_DirectTwo` varchar(4) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcRL_HourOne` double DEFAULT NULL,
  `SvcRL_AlertTimeOne` datetime DEFAULT NULL,
  `SvcRL_AlertOprtOne` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcRL_HourTwo` double DEFAULT NULL,
  `SvcRL_AlertTimeTwo` datetime DEFAULT NULL,
  `SvcRL_AlertOprtTwo` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcRL_State` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `SvcRL_Remark` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcRL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_rest
-- ----------------------------
DROP TABLE IF EXISTS `svc_rest`;
CREATE TABLE `svc_rest` (
  `SvcR_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcR_khkh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcR_xmmc` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcR_xmye` double NOT NULL,
  `SvcR_SendTime` datetime NOT NULL,
  `SvcR_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcR_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcR_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcR_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcR_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcR_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcR_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcR_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcR_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcR_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcR_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_sp_type
-- ----------------------------
DROP TABLE IF EXISTS `svc_sp_type`;
CREATE TABLE `svc_sp_type` (
  `SvcSPT_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcSPT_Type` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcSPT_IsToKOPEN` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcSPT_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcSPT_Eff` datetime NOT NULL,
  `SvcSPT_Exp` datetime NOT NULL,
  `SvcSPT_IsToPhoneTSI` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcSPT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_sqgd
-- ----------------------------
DROP TABLE IF EXISTS `svc_sqgd`;
CREATE TABLE `svc_sqgd` (
  `SvcSQ_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcSQ_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcSQ_fwlx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_fwnr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_fwxm` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_gzfsrq` datetime DEFAULT NULL,
  `SvcSQ_yjfwrq` datetime DEFAULT NULL,
  `SvcSQ_jh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_khmc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_fwyy` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_sfzb` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_sftj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_xxly` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_qtsm` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_SendTime` datetime NOT NULL,
  `SvcSQ_Cno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_Ano` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_Sno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by4` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by11` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by12` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by13` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by14` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by15` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by16` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by17` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by18` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by19` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by20` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by21` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by22` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by23` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by24` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by25` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by26` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by27` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by28` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by29` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_by30` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcSQ_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcSQ_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcSQ_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcSQ_AuditStatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcSQ_VclGPS_Lon` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_VclGPS_La` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSQ_Vcl_No` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcSQ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_sqgd_khxx
-- ----------------------------
DROP TABLE IF EXISTS `svc_sqgd_khxx`;
CREATE TABLE `svc_sqgd_khxx` (
  `SvcSK_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcSK_yhjb` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_yhmc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_lxr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_dh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_pp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_jz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_jx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_gzxs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_SendTime` datetime DEFAULT NULL,
  `SvcSK_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSK_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcSK_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_sys_oprtlog
-- ----------------------------
DROP TABLE IF EXISTS `svc_sys_oprtlog`;
CREATE TABLE `svc_sys_oprtlog` (
  `SvcSO_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcSO_Func_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSO_Func_Name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSO_OprtContent` text COLLATE utf8_unicode_ci,
  `SvcSO_OprtTime` datetime DEFAULT NULL,
  `SvcSO_OprtName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSO_OprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSO_IP` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcSO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_sys_personalset
-- ----------------------------
DROP TABLE IF EXISTS `svc_sys_personalset`;
CREATE TABLE `svc_sys_personalset` (
  `SvcSPS_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcSPS_Oprt_ID` decimal(18,0) DEFAULT NULL,
  `SvcSPS_Oprt_Name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSPS_PageRow` int(11) DEFAULT NULL,
  `SvcSPS_Task_ContinueAdd` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcSPS_Eff` datetime DEFAULT NULL,
  `SvcSPS_Exp` datetime DEFAULT NULL,
  PRIMARY KEY (`SvcSPS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_task
-- ----------------------------
DROP TABLE IF EXISTS `svc_task`;
CREATE TABLE `svc_task` (
  `SvcT_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcT_rwh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_jh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_fwlb` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_fwnr` text COLLATE utf8_unicode_ci,
  `SvcT_zcwcsj` datetime DEFAULT NULL,
  `SvcT_sfys` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_ysjcf` decimal(18,0) DEFAULT NULL,
  `SvcT_ysgsf` decimal(18,0) DEFAULT NULL,
  `SvcT_sftj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_gzfsrq` datetime DEFAULT NULL,
  `SvcT_yhmc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_lxr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_lxfs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_jqszsheng` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_jqszshi` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_jqszx` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_jqszz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_jqszc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_jjrq` datetime DEFAULT NULL,
  `SvcT_gzxs` double DEFAULT NULL,
  `SvcT_gzxszp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_sfazpsq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_psqpp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_psqxh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_psqjxs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_khkh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_SendTime` datetime DEFAULT NULL,
  `SvcT_rwzt` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_Oprt_ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcT_Oprt_Name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcT_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcT_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcT_Fwxm` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcT_IsOut` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcT_Gdh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_GZGS` decimal(18,2) DEFAULT NULL,
  `SvcT_ZTGS` decimal(18,2) DEFAULT NULL,
  `SvcT_Status` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcT_NewsNo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_GaiNo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_Brand` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_VclKind` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_VclType` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_fwrq` datetime DEFAULT NULL,
  `SvcT_ENo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_EType` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_xxly` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcT_Exp` datetime DEFAULT NULL,
  `svcT_WeiXinReciever_OprtID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `svcT_WeiXinReciever` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `svcT_vcl_Dept_ID` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `svcT_Remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcT_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_task_pg
-- ----------------------------
DROP TABLE IF EXISTS `svc_task_pg`;
CREATE TABLE `svc_task_pg` (
  `SvcTP_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcTP_OprtID` decimal(18,0) NOT NULL,
  `SvcTP_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcTP_Cotent` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcTP_Type` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcTP_SendTime` datetime NOT NULL,
  `SvcTP_SvcT_id` decimal(18,0) NOT NULL,
  `SvcTP_Remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcTP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_task_yhxx
-- ----------------------------
DROP TABLE IF EXISTS `svc_task_yhxx`;
CREATE TABLE `svc_task_yhxx` (
  `SvcTY_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcTY_jh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_yhmc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_lxr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_lxfs` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_Rwh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTY_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcTY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_tld
-- ----------------------------
DROP TABLE IF EXISTS `svc_tld`;
CREATE TABLE `svc_tld` (
  `SvcTL_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcTL_tld` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_cfsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_ksfwsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_jsfwsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_fhsj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_lc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_SendTime` datetime NOT NULL,
  `SvcTL_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTL_Oprt_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcTL_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_tsi
-- ----------------------------
DROP TABLE IF EXISTS `svc_tsi`;
CREATE TABLE `svc_tsi` (
  `SvcTSI_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcTSI_REID` decimal(18,0) NOT NULL,
  `SvcTSI_RETableName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTSI_ReName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTSI_KopenNumber` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTSI_SP_Type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcTSI_AuditStatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SvcTSI_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_workhour
-- ----------------------------
DROP TABLE IF EXISTS `svc_workhour`;
CREATE TABLE `svc_workhour` (
  `SvcWH_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcWH_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWH_Svc_id` decimal(18,0) DEFAULT NULL,
  `SvcWH_WorkHour` decimal(18,1) NOT NULL,
  `SvcWH_KmHour` decimal(18,1) NOT NULL,
  `SvcWH_AuditTime` datetime NOT NULL,
  `SvcWH_AuditFrom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWH_Eff` datetime NOT NULL,
  `SvcWH_Exp` datetime NOT NULL,
  `SvcWH_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWH_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWH_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWH_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SvcWH_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_workhour_kou
-- ----------------------------
DROP TABLE IF EXISTS `svc_workhour_kou`;
CREATE TABLE `svc_workhour_kou` (
  `SvcWHK_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcWHK_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWHK_Svc_id` decimal(18,0) DEFAULT NULL,
  `SvcWHK_WorkHour` decimal(18,1) NOT NULL,
  `SvcWHK_KmHour` decimal(18,1) NOT NULL,
  `SvcWHK_AuditTime` datetime NOT NULL,
  `SvcWHK_AuditFrom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWHK_Eff` datetime NOT NULL,
  `SvcWHK_Exp` datetime NOT NULL,
  `SvcWHK_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWHK_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWHK_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWHK_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SvcWHK_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_workhour_standard
-- ----------------------------
DROP TABLE IF EXISTS `svc_workhour_standard`;
CREATE TABLE `svc_workhour_standard` (
  `SvcWHS_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcWHS_SvcContent` longtext COLLATE utf8_unicode_ci NOT NULL,
  `SvcWHS_Description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `SvcWHS_Standard` decimal(18,1) NOT NULL,
  `SvcWHS_Eff` datetime NOT NULL,
  `SvcWHS_Exp` datetime NOT NULL,
  `SvcWHS_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWHS_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWHS_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWHS_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SvcWHS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_worktaskgroup
-- ----------------------------
DROP TABLE IF EXISTS `svc_worktaskgroup`;
CREATE TABLE `svc_worktaskgroup` (
  `SvcWTG_ID` int(18) NOT NULL AUTO_INCREMENT,
  `SvcWTG_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWTG_Vcl_No` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWTG_RWNumber` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcWTG_CreateTime` datetime NOT NULL,
  PRIMARY KEY (`SvcWTG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_xcdc
-- ----------------------------
DROP TABLE IF EXISTS `svc_xcdc`;
CREATE TABLE `svc_xcdc` (
  `SvcXC_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcXC_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXC_dcnr` text COLLATE utf8_unicode_ci,
  `SvcXC_dczp_jh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_dczp_zj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_dczp_yj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_dczp_zj1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_dczp_jj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_dczp_jc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_dcjg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_bw1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_bw2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_bw3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_gzdm` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_wtrhjj` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_SendTime` datetime NOT NULL,
  `SvcXC_Cno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_Ano` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_Sno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by8` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by11` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by12` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by13` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by14` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by15` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by16` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by17` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by18` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by19` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by20` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by21` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by22` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by23` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by24` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by25` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by26` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by27` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by28` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by29` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_by30` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXC_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXC_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXC_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXC_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXC_AuditStatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXC_VclGps_Lon` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXC_VclGps_La` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXC_Vcl_No` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXC_GzxsK` double NOT NULL,
  PRIMARY KEY (`SvcXC_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_xzsw
-- ----------------------------
DROP TABLE IF EXISTS `svc_xzsw`;
CREATE TABLE `svc_xzsw` (
  `SvcXZ_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcXZ_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXZ_sjfsrq` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_fwnr` text COLLATE utf8_unicode_ci,
  `SvcXZ_srnr` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_qjts` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_bzsm` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_SendTime` datetime NOT NULL,
  `SvcXZ_Cno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_Ano` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_Sno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by11` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by12` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by13` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by14` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by15` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by16` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by17` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by18` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by19` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by20` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by21` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by22` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by23` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by24` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by25` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by26` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by27` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by28` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by29` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_by30` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXZ_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXZ_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXZ_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXZ_AuditStatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcXZ_Vcl_No` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_VclGPS_Lon` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcXZ_VclGPS_La` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcXZ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_zfjc_sub
-- ----------------------------
DROP TABLE IF EXISTS `svc_zfjc_sub`;
CREATE TABLE `svc_zfjc_sub` (
  `SvcZS_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcZS_rwh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcZS_fieldNameEn` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_fieldNameCn` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_memo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_SendTime` datetime NOT NULL,
  `SvcZS_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_by8` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZS_Svc_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcZS_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for svc_zqsw
-- ----------------------------
DROP TABLE IF EXISTS `svc_zqsw`;
CREATE TABLE `svc_zqsw` (
  `SvcZQ_id` int(18) NOT NULL AUTO_INCREMENT,
  `SvcZQ_rwh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_fwnr` text COLLATE utf8_unicode_ci,
  `SvcZQ_je` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_fph` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_fpqsdpzp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_SendTime` datetime NOT NULL,
  `SvcZQ_Cno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_Ano` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_Sno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_scbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_txbz` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by4` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by5` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by6` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by7` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by8` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by9` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by10` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by11` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by12` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by13` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by14` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by15` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by16` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by17` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by18` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by19` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by20` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by21` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by22` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by23` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by24` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by25` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by26` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by27` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by28` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by29` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_by30` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_OprtID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcZQ_OprtName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcZQ_DeptID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcZQ_DeptName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcZQ_AuditStatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcZQ_Vcl_No` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SvcZQ_GzxsK` double NOT NULL,
  `SvcZQ_VclGps_Lon` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SvcZQ_VclGps_La` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SvcZQ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
