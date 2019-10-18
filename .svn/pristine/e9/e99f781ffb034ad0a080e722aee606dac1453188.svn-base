-- 全部值类型数据
select HOUR(createTime),MINUTE(createTime),SECOND(createTime),count(*)
   from t_gpsdata
where clientId='helmet1003'
  and createTime BETWEEN '2017-10-31 10:02:59.000' and '2017-10-31 10:05:59.000'
  group by HOUR(createTime),MINUTE(createTime),SECOND(createTime)
order by HOUR(createTime),MINUTE(createTime),SECOND(createTime);

-- 某个类型的值类型数据
select HOUR(createTime),MINUTE(createTime),SECOND(createTime),count(*)
   from t_gpsdata
where clientId='helmet1003'
  and datatype = 5 and createTime BETWEEN '2017-10-31 10:02:59.000' and '2017-10-31 10:05:59.000'
  group by HOUR(createTime),MINUTE(createTime),SECOND(createTime)
order by HOUR(createTime),MINUTE(createTime),SECOND(createTime);


-- 动作数据
select HOUR(createTime),MINUTE(createTime),SECOND(createTime),count(*)
from t_gpsaction
where clientId='helmet1003'
  and createTime BETWEEN '2017-10-31 10:02:59.000' and '2017-10-31 10:05:59.000'
group by HOUR(createTime),MINUTE(createTime),SECOND(createTime)
order by HOUR(createTime),MINUTE(createTime),SECOND(createTime);

-- 定位数据
select HOUR(createTime),MINUTE(createTime),SECOND(createTime),count(*)
from t_gpslocation
where clientId='helmet1003'
  and createTime BETWEEN '2017-10-31 10:02:59.000' and '2017-10-31 10:05:59.000'
group by HOUR(createTime),MINUTE(createTime),SECOND(createTime)
order by HOUR(createTime),MINUTE(createTime),SECOND(createTime);

-- 陀螺仪数据
select HOUR(createTime),MINUTE(createTime),SECOND(createTime),count(*)
from t_gpsgyro
where clientId='helmet1003'
  and createTime BETWEEN '2017-10-31 10:02:59.000' and '2017-10-31 10:05:59.000'
group by HOUR(createTime),MINUTE(createTime),SECOND(createTime)
order by HOUR(createTime),MINUTE(createTime),SECOND(createTime);

--某分钟的具体数据
select *
from t_gpsaction
where clientId='helmet1004'
  and createTime BETWEEN '2017-12-14 14:19:18.000' and '2017-12-14 14:19:18.999';
