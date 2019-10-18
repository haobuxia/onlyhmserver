/*
客户端信息表
*/
create table t_client(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  clientId VARCHAR (50) not null,
  clientType VARCHAR (10) not null,
  factoryTime timestamp,
  activeTime datetime,
  bindUserId VARCHAR (20)
) DEFAULT CHARSET=utf8 ;

ALTER TABLE `t_client` ADD INDEX `idx_client_clientId` (`clientId`) ;

insert into t_client(clientId,clientType,factoryTime,activeTime) values('helmet1001','helmet',now(),now());
insert into t_client(clientId,clientType,factoryTime,activeTime) values('helmet1002','helmet',now(),now());
insert into t_client(clientId,clientType,factoryTime,activeTime) values('helmet1003','helmet',now(),now());
insert into t_client(clientId,clientType,factoryTime,activeTime) values('helmet1004','helmet',now(),now());
insert into t_client(clientId,clientType,factoryTime,activeTime) values('helmet1005','helmet',now(),now());
insert into t_client(clientId,clientType,factoryTime,activeTime) values('helmet1006','helmet',now(),now());
insert into t_client(clientId,clientType,factoryTime,activeTime) values('helmet1007','helmet',now(),now());
insert into t_client(clientId,clientType,factoryTime,activeTime) values('helmet1008','helmet',now(),now());
insert into t_client(clientId,clientType,factoryTime,activeTime) values('helmet1009','helmet',now(),now());
insert into t_client(clientId,clientType,factoryTime,activeTime) values('helmet1010','helmet',now(),now());

insert into t_client(clientId,clientType,factoryTime) values('helmet0001','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0002','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0003','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0004','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0005','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0006','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0007','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0008','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0009','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0010','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0011','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0012','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0013','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0014','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0015','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0016','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0017','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0018','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0019','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0020','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0021','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0022','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0023','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0024','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0025','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0026','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0027','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0028','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0029','helmet',now());
insert into t_client(clientId,clientType,factoryTime) values('helmet0030','helmet',now());


/*
 头盔用户绑定日志表
*/
CREATE TABLE t_client_bindlog (
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  clientId VARCHAR (50) not null,
  bindTime timestamp,
  userName VARCHAR(20),
  userPhone VARCHAR(20)
) DEFAULT CHARSET=utf8 ;
ALTER TABLE t_helmet_bindlog ADD INDEX `idx_clientbindlog_clientId` (`clientId`) ;
