create table t_apk_file(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  uploadTime DATETIME ,
  fileName VARCHAR(100),
  version varchar(20),
  ossPath VARCHAR(100),
  description VARCHAR (1000),
  uploadUserId int
) DEFAULT CHARSET=utf8 ;

ALTER TABLE t_apk_file ADD INDEX `idx_apkfile_version` (`version`) ;
ALTER TABLE t_apk_file ADD INDEX `idx_apkfile_uploadUserId` (`uploadUserId`) ;
ALTER TABLE t_apk_file ADD INDEX `idx_apkfile_uploadTime` (`uploadTime`) ;

create table t_apk_update(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  createTime DATETIME ,
  clientId VARCHAR(50),
  apkId int,
  createUserId int
) DEFAULT CHARSET=utf8 ;

ALTER TABLE t_apk_update ADD INDEX `idx_apkupload_clientId` (`clientId`) ;
ALTER TABLE t_apk_update ADD INDEX `idx_apkfile_apkId` (`apkId`) ;
ALTER TABLE hmserver.t_apk_update ADD CONSTRAINT t_apk_update_unik_clientid UNIQUE (clientId);