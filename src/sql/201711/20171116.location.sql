ALTER TABLE hmserver.t_gpslocation ADD lat2 FLOAT(11,8) NULL;
ALTER TABLE hmserver.t_gpslocation ADD lon2 FLOAT(11,8) NULL;

update t_gpslocation set lat2 = (lat/10000/60) + ( (lat/10000)%60 + (lat%10000)*0.0001 )/60 ;
update t_gpslocation set lon2 = (lon/10000/60) + ( (lon/10000)%60 + (lon%10000)*0.0001 )/60 ;

ALTER TABLE hmserver.t_gpslocation DROP lat;
ALTER TABLE hmserver.t_gpslocation DROP lon;

ALTER TABLE hmserver.t_gpslocation CHANGE lat2 lat FLOAT(11,8);
ALTER TABLE hmserver.t_gpslocation CHANGE lon2 lon FLOAT(11,8);