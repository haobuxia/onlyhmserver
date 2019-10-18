ALTER TABLE hmserver.t_userinfo RENAME TO hmserver.t_netease_user;
ALTER TABLE hmserver.t_netease_user ADD userType TINYINT NULL DEFAULT 1;
ALTER TABLE hmserver.t_netease_user ADD nickName varchar(50);
update t_netease_user set nickName = username;

create table t_wx_user(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  wxId varchar(40),
  nickname VARCHAR(200),
  wxSex tinyint,
  area varchar(100),
  subscribeTime datetime,
  appUserId int,
  bindAppTime datetime
);
ALTER TABLE `t_wx_user` ADD INDEX `idx_wxuser_wxId` (`wxId`) ;
ALTER TABLE `t_wx_user` ADD INDEX `idx_wxuser_appUserId` (`appUserId`) ;

create table t_tianyi_user(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  username varchar(20),
  password VARCHAR(40),
  regtime datetime,
  mobile VARCHAR(11),
  `name` varchar(20),
  userSex tinyint default 2,
  company varchar(200),
  wxid varchar(40),
  neUsername VARCHAR(20),
  bindWxTime datetime
);
ALTER TABLE `t_tianyi_user` ADD INDEX `idx_tyuser_username` (`username`) ;
ALTER TABLE `t_tianyi_user` ADD INDEX `idx_tyuser_neusername` (`neUsername`) ;
ALTER TABLE `t_tianyi_user` ADD INDEX `idx_tyuser_mobile` (`mobile`) ;

create table t_tianyi_userrole (
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  userid int,
  roleCode varchar(20),
  createTime DATETIME
);
ALTER TABLE `t_tianyi_userrole` ADD INDEX `idx_tyusercode_userid` (`userid`) ;
ALTER TABLE `t_tianyi_userrole` ADD INDEX `idx_tyusercode_rolecd` (`roleCode`) ;


insert into t_tianyi_user(username,password,regtime,name,usersex,company) values('hantian','hantian',now(),'韩田',1,'田一科技');
insert into t_tianyi_user(username,password,regtime,name,usersex,company) values('maoyi','maoyi',now(),'毛轶',1,'田一科技');
insert into t_tianyi_user(username,password,regtime,name,usersex,company) values('zhangxin','zhangxin',now(),'张鑫',1,'田一科技');
insert into t_tianyi_user(username,password,regtime,name,usersex,company) values('zhangjy','zhangjy',now(),'张建禹',1,'田一科技');
insert into t_tianyi_user(username,password,regtime,name,usersex,company) values('yangjch','yangjch',now(),'杨俊超',1,'田一科技');
insert into t_tianyi_user(username,password,regtime,name,usersex,company) values('shiyl','shiyl',now(),'石义玲',0,'田一科技');
insert into t_tianyi_user(username,password,regtime,name,usersex,company) values('liuhan','liuhan',now(),'刘寒',1,'田一科技');
update t_tianyi_user set neUsername = username;

insert into t_tianyi_userrole(userid,rolecode,createTime) values(1,'admin',now());
insert into t_tianyi_userrole(userid,rolecode,createTime) values(2,'admin',now());
insert into t_tianyi_userrole(userid,rolecode,createTime) values(3,'admin',now());
insert into t_tianyi_userrole(userid,rolecode,createTime) values(4,'admin',now());
insert into t_tianyi_userrole(userid,rolecode,createTime) values(5,'admin',now());
insert into t_tianyi_userrole(userid,rolecode,createTime) values(6,'admin',now());
insert into t_tianyi_userrole(userid,rolecode,createTime) values(7,'admin',now());

-- 张鑫和杨俊超具备销售身份
insert into t_tianyi_userrole(userid,rolecode) values(3,'sales');
insert into t_tianyi_userrole(userid,rolecode) values(5,'sales');

update t_helmet set model='V1.0' ,batch='20171201';