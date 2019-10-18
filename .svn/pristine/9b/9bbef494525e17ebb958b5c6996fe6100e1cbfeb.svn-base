-- ALTER TABLE hmserver.t_video DROP startTime;
-- ALTER TABLE hmserver.t_video DROP endTime;

ALTER TABLE hmserver.t_video ADD imei VARCHAR(16) NULL;
ALTER TABLE hmserver.t_video
  MODIFY COLUMN trackVideoOssPath VARCHAR(100) AFTER imei;

CREATE INDEX idx_video_hasgpsdata ON hmserver.t_video (hasGpsData);
CREATE INDEX idx_video_imei ON hmserver.t_video (imei);

ALTER TABLE hmserver.t_video ADD machineCode VARCHAR(20) NULL;
CREATE INDEX idx_video_machineCode ON hmserver.t_video (machineCode);



select a.id,min(b.imei)
  from t_video a left join t_gpsdata b
      on a.clientId = b.clientId
        and b.createTime>= a.createTime and b.createTime <= a.createTime + interval a.seconds second
  where a.id < 300 and a.hasGpsData is null
group by a.id ;


--   hasGpsData  ---
create table t_temp_video_gpsdata
select a.id,a.clientid,a.createTime,a.seconds,a.createtime + interval a.seconds second as endTime ,count(1) as cnt
from t_video a,t_gpsdata b
where a.hasGpsData is null
and a.clientId = b.clientId
and b.createTime between a.createTime and a.createtime + interval a.seconds second
and a.id < 20
group by a.id,a.clientid,a.createTime,a.seconds ,a.createtime + interval a.seconds second;

insert into t_temp_video_gpsdata
  select a.id,a.clientid,a.createTime,a.seconds,a.createtime + interval a.seconds second as endTime ,count(1) as cnt
  from t_video a,t_gpsdata b
  where a.hasGpsData is null
        and a.clientId = b.clientId
        and b.createTime between a.createTime and a.createtime + interval a.seconds second
        and a.id > 20 and a.id < 100
  group by a.id,a.clientid,a.createTime,a.seconds ,a.createtime + interval a.seconds second;

insert into t_temp_video_gpsdata
  select a.id,a.clientid,a.createTime,a.seconds,a.createtime + interval a.seconds second as endTime ,count(1) as cnt
  from t_video a,t_gpsdata b
  where a.hasGpsData is null
        and a.clientId = b.clientId
        and b.createTime between a.createTime and a.createtime + interval a.seconds second
        and a.id > 100 and a.id < 200
  group by a.id,a.clientid,a.createTime,a.seconds ,a.createtime + interval a.seconds second;

insert into t_temp_video_gpsdata
  select a.id,a.clientid,a.createTime,a.seconds,a.createtime + interval a.seconds second as endTime ,count(1) as cnt
  from t_video a,t_gpsdata b
  where a.hasGpsData is null
        and a.clientId = b.clientId
        and b.createTime between a.createTime and a.createtime + interval a.seconds second
        and a.id > 200 and a.id < 300
  group by a.id,a.clientid,a.createTime,a.seconds ,a.createtime + interval a.seconds second;


select a.id,(select imei from t_gpsdata b where a.clientId = b.clientId and b.createTime between a.createTime and a.createtime + interval a.seconds second limit 0,1) as imei
   from t_video a
     where a.hasGpsData is null;

insert into t_temp_video_gpsdata
  select a.id,a.clientid,a.createTime,a.seconds,a.createtime + interval a.seconds second as endTime ,count(1) as cnt
  from t_video a,t_gpsdata b
  where a.hasGpsData is null
        and a.clientId = b.clientId
        and b.createTime between a.createTime and a.createtime + interval a.seconds second
        and a.id > 300 and a.id < 300
  group by a.id,a.clientid,a.createTime,a.seconds ,a.createtime + interval a.seconds second;


-- hasGpsData
update t_video a
set a.hasGpsData = 1
where a.hasGpsData is null and exists(
    select 1 from t_temp_video_gpsdata b where a.id = b.id and b.cnt > 0
);
update t_video a set a.hasGpsData = 0 where a.hasGpsData is null;

-- drop table t_temp_video_gpsdata;

-- imei
update t_video a
set a.imei = (
  select min(imei)
  from t_gpsdata b
  where a.clientId = b.clientId
        and b.createTime between a.createTime and a.createtime + interval a.seconds second
)
where a.hasGpsData = 1;
