create table t_customer(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  mobile VARCHAR(11) not null,
  custName varchar(20),
  company varchar(200),
  depart varchar(50),
  address varchar(500),
  saleUserId int,
  createTime DATETIME
);
ALTER TABLE `t_customer` ADD INDEX `idx_customer_mobile` (`mobile`) ;
insert into t_customer(mobile,custName,company,depart,address,createTime) values('13731463079','廉洪武','天远服务部','服务人员','石家庄',now());
insert into t_customer(mobile,custName,company,depart,address,createTime) values('15284350403','秦梓雄','','机手管理人员','邢台',now());
insert into t_customer(mobile,custName,company,depart,address,createTime) values('15200038188','王国宝','','机手','石家庄',now());
insert into t_customer(mobile,custName,company,depart,address,createTime) values('15832121054','范艳波','天远大修厂','大修厂设备维修','石家庄',now());
insert into t_customer(mobile,custName,company,depart,address,createTime) values('15227875792','赵礼峰','天远大修厂','大修厂设备维修','石家庄',now());
insert into t_customer(mobile,custName,company,depart,address,createTime) values('15284350403','秦梓雄','','机手管理人员','邢台',now());

ALTER TABLE hmserver.t_client RENAME TO hmserver.t_helmet;
ALTER TABLE hmserver.t_helmet ADD model varchar(10);
ALTER TABLE hmserver.t_helmet ADD batch varchar(10);
ALTER TABLE hmserver.t_helmet ADD saleState int(2) DEFAULT 0;
ALTER TABLE hmserver.t_helmet ADD customerId int;
ALTER TABLE `t_helmet` ADD INDEX `idx_helmet_saleState` (`saleState`) ;
ALTER TABLE `t_helmet` ADD INDEX `idx_helmet_custid` (`customerId`) ;

update t_helmet set saleState = 1,customerId =1;

ALTER TABLE hmserver.t_client_bindlog RENAME TO hmserver.t_helmet_bindlog;



 -- --- --- 以下sql看情况执行---
update t_helmet set customerId = 2,saleState =1 where clientId='helmet1002';
update t_helmet set customerId = 3,saleState =1 where clientId='helmet1003';
update t_helmet set customerId = 4,saleState =1 where clientId='helmet1007';
update t_helmet set customerId = 5,saleState =1 where clientId='helmet1005';
update t_helmet set customerId = 6,saleState =1 where clientId='helmet1006';
update t_helmet set customerId = 7,saleState =1 where clientId='helmet1010';
update t_helmet set customerId = null,saleState = 0,activeTime = null where clientId like 'helmet%' and clientId not in ('helmet1002','helmet1003','helmet1007','helmet1005','helmet1006','helmet1010');
update t_helmet set customerId = null,saleState = 2,activeTime = null where clientId not like 'helmet%';


