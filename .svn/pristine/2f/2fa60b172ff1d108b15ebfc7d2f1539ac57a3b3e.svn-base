create table t_imei(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  imei VARCHAR(20) not null,
  createTime DATETIME
);
ALTER TABLE `t_imei` ADD INDEX `idx_imei_imei` (`imei`) ;


insert into t_imei(imei)
  select DISTINCT imei from t_gpsdata
  union
  select DISTINCT imei from t_gpsaction
  union
  select DISTINCT imei from t_gpsgyro
  union
  select DISTINCT imei from t_gpslocation
;

update t_imei set t_imei.createTime = now();