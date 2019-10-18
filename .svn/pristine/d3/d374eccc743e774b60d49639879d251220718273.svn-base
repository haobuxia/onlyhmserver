
create table t_helmetgps(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  clientId VARCHAR (50) not null,
  createTime TIMESTAMP not null,
  lat FLOAT,
  lon FLOAT
);
ALTER TABLE `t_helmetgps` ADD INDEX `idx_helmetgps_clientid` (`clientId`) ;
ALTER TABLE `t_helmetgps` ADD INDEX `idx_helmetgps_createtime` (`createTime`) ;

insert into t_helmetgps (clientId, createTime, lat, lon)
    select DISTINCT clientId, createTime, lat, lon
    from t_sensor;

ALTER TABLE hmserver.t_sensor DROP lat;
ALTER TABLE hmserver.t_sensor DROP lon;
ALTER TABLE hmserver.t_sensor RENAME TO hmserver.t_helmetsensor;
