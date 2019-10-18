create table t_file(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  clientId VARCHAR (50) not null,
  createTime TIMESTAMP not null,
  fileName VARCHAR (100) ,
  fileType VARCHAR (10) ,
  sizeKb int,
  ossPath VARCHAR (100)
);
ALTER TABLE `t_file` ADD INDEX `idx_file_clientid` (`clientId`) ;
ALTER TABLE `t_file` ADD INDEX `idx_file_createtime` (`createTime`) ;
ALTER TABLE `t_file` ADD INDEX `idx_file_filetype` (`fileType`) ;