CREATE TABLE `t_helmetuniversal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `selectType` int(11) NOT NULL,
  `customer` varchar(20) NOT NULL,
  `taskType` varchar(20) NOT NULL,
  `finishType` varchar(20) NOT NULL,
  `project` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8

ALTER TABLE `hmserver_new`.`t_role`
ADD COLUMN `companyId` INT(11) NULL,
ADD COLUMN `universalId` INT(11) NULL;

ALTER TABLE `hmserver_new`.`t_audio`
ADD COLUMN `orderNo` VARCHAR(20) NULL;

ALTER TABLE `hmserver_new`.`t_helmetuniversal`
CHANGE COLUMN `project` `project` VARCHAR(200) NULL DEFAULT NULL COMMENT '0常州小松工厂1租赁2大修厂3智能服务';

UPDATE `hmserver_new`.`t_helmetuniversal` SET `project` = '常州工厂' WHERE (`id` = '0');
UPDATE `hmserver_new`.`t_helmetuniversal` SET `project` = '租赁车检' WHERE (`id` = '1');
UPDATE `hmserver_new`.`t_helmetuniversal` SET `project` = '大修厂' WHERE (`id` = '2');
UPDATE `hmserver_new`.`t_helmetuniversal` SET `project` = '智能服务' WHERE (`id` = '3');

ALTER TABLE `hmserver_new`.`t_video`
ADD COLUMN `caller` VARCHAR(45) NULL,
ADD COLUMN `called` VARCHAR(45) NULL,
ADD COLUMN `source` VARCHAR(45) NULL;

ALTER TABLE `hmserver_new`.`t_helmetonlinestatus`
ADD COLUMN `uid` VARCHAR(45) NULL AFTER `userName`;

CREATE TABLE `t_helmetcharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `clientId` varchar(50) DEFAULT NULL,
  `neUsername` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `onlineTime` timestamp NULL DEFAULT NULL,
  `offlineTime` timestamp NULL DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(45) DEFAULT NULL,
  `uid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `t_helmetconfig` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(350) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `config0` varchar(250) DEFAULT NULL,
  `config1` varchar(250) DEFAULT NULL,
  `config2` varchar(250) DEFAULT NULL,
  `config3` varchar(250) DEFAULT NULL,
  `config4` varchar(250) DEFAULT NULL,
  `config5` varchar(250) DEFAULT NULL,
  `config6` varchar(250) DEFAULT NULL,
  `config7` varchar(250) DEFAULT NULL,
  `config8` varchar(250) DEFAULT NULL,
  `config9` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE `hmserver_new`.`t_helmetconfig`
ADD COLUMN `pid` INT(11) NULL AFTER `config9`;

ALTER TABLE `hmserver_new`.`t_helmetconfig`
ADD COLUMN `uid` INT(11) NULL AFTER `pid`;

CREATE TABLE `t_helmetuniversalurl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL comment '通用项目ID',
  `urltype` varchar(100) NOT NULL comment '服务地址类型',
  `url` varchar(200) NOT NULL COMMENT '业务系统服务地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('1', 'cars', 'http://192.168.30.65:19065/helmet/cars');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('1', 'works', 'http://192.168.30.65:19065/helmet/works');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('1', 'startcar', 'http://192.168.30.65:19065/helmet/startcar');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('1', 'endcar', 'http://192.168.30.65:19065/helmet/endcar');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('1', 'startwork', 'http://192.168.30.65:19065/helmet/startwork');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('1', 'endwork', 'http://192.168.30.65:19065/helmet/endwork');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('1', 'starttask', 'http://192.168.30.65:19065/helmet/starttask');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('1', 'endtask', 'http://192.168.30.65:19065/helmet/endtask');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('1', 'contact', 'http://192.168.30.65:19065/helmet/contack');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('0', 'cars', 'http://192.168.30.226:19065/helmet/cars');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('0', 'works', 'http://192.168.30.226:19065/helmet/works');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('0', 'startcar', 'http://192.168.30.226:19065/helmet/startcar');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('0', 'endcar', 'http://192.168.30.226:19065/helmet/endcar');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('0', 'startwork', 'http://192.168.30.226:19065/helmet/startwork');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('0', 'endwork', 'http://192.168.30.226:19065/helmet/endwork');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('0', 'starttask', 'http://192.168.30.226:19065/helmet/starttask');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('0', 'endtask', 'http://192.168.30.226:19065/helmet/endtask');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('0', 'contact', 'http://192.168.30.226:19065/helmet/contack');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('2', 'cars', 'http://111.11.4.69:19065/helmet/cars');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('2', 'works', 'http://111.11.4.69:19065/helmet/works');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('2', 'startcar', 'http://111.11.4.69:19065/helmet/startcar');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('2', 'endcar', 'http://111.11.4.69:19065/helmet/endcar');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('2', 'startwork', 'http://111.11.4.69:19065/helmet/startwork');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('2', 'endwork', 'http://111.11.4.69:19065/helmet/endwork');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('2', 'starttask', 'http://111.11.4.69:19065/helmet/starttask');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('2', 'endtask', 'http://111.11.4.69:19065/helmet/endtask');
INSERT INTO `hmserver_new`.`t_helmetuniversalurl` (`uid`, `urltype`, `url`) VALUES ('2', 'contact', 'http://111.11.4.69:19065/helmet/contack');

CREATE TABLE `t_video_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `videoId` int(11) DEFAULT NULL COMMENT '视频ID',
  `routingKey` varchar(50) DEFAULT NULL COMMENT '模型ID',
  `orderId` varchar(50) DEFAULT NULL COMMENT '来源',
  `deviceNumber` varchar(50) DEFAULT NULL COMMENT '类别编号',
  `createTime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信令消息'

CREATE TABLE `t_bluebox` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mac` varchar(20) NOT NULL,
  `deviceId` int(11),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE `hmserver_new`.`t_helmetuniversal`
ADD COLUMN `avprovider` VARCHAR(100) NULL;

update t_netease_user set company='angelcomm';
UPDATE `hmserver_new`.`t_helmetuniversal` SET `avprovider` = 'angelcomm' WHERE (`id` = '0');
UPDATE `hmserver_new`.`t_helmetuniversal` SET `avprovider` = 'angelcomm' WHERE (`id` = '1');
UPDATE `hmserver_new`.`t_helmetuniversal` SET `avprovider` = 'angelcomm' WHERE (`id` = '2');
UPDATE `hmserver_new`.`t_helmetuniversal` SET `avprovider` = 'angelcomm' WHERE (`id` = '3');
UPDATE `hmserver_new`.`t_helmetuniversal` SET `avprovider` = 'angelcomm' WHERE (`id` = '4');
UPDATE `hmserver_new`.`t_helmetuniversal` SET `avprovider` = 'angelcomm' WHERE (`id` = '5');

CREATE TABLE `t_helmetuniversalcontact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '通用项目ID',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8