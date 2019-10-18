ALTER TABLE hmserver.t_tag ADD groupId INT DEFAULT 0 NULL;
ALTER TABLE hmserver.t_tag DROP resCount;

CREATE TABLE hmserver.t_tag_group
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    groupName VARCHAR(20),
    createTime TIMESTAMP
);
ALTER TABLE `t_tag_group`
MODIFY COLUMN `groupName`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `id`;


insert into t_tag_group(id,groupName, createTime) values(1,'默认分组',now());
insert into t_tag_group(id,groupName, createTime) values(2,'服务数据',now());

update t_tag set groupId = 1;

insert into t_tag(tagName, createTime,groupId) values('环境',now(),2) on DUPLICATE KEY UPDATE groupId = 2;
insert into t_tag(tagName, createTime,groupId) values('车辆',now(),2) on DUPLICATE KEY UPDATE groupId = 2;
insert into t_tag(tagName, createTime,groupId) values('故障',now(),2) on DUPLICATE KEY UPDATE groupId = 2;
insert into t_tag(tagName, createTime,groupId) values('零件',now(),2) on DUPLICATE KEY UPDATE groupId = 2;
insert into t_tag(tagName, createTime,groupId) values('保养',now(),2) on DUPLICATE KEY UPDATE groupId = 2;
insert into t_tag(tagName, createTime,groupId) values('异常',now(),2) on DUPLICATE KEY UPDATE groupId = 2;
insert into t_tag(tagName, createTime,groupId) values('评估',now(),2) on DUPLICATE KEY UPDATE groupId = 2;
insert into t_tag(tagName, createTime,groupId) values('其他',now(),2) on DUPLICATE KEY UPDATE groupId = 2;