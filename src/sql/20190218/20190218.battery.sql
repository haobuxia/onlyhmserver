CREATE TABLE `t_helmetbattery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clientId` varchar(100) NOT NULL,
  `neUsername` varchar(50) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `battery` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_helmetbattery_clientid` (`clientId`),
  KEY `idx_helmetbattery_createtime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8