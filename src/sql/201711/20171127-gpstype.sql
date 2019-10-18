ALTER TABLE hmserver.t_helmetgps ADD gpsType TINYINT(2) NULL;
update t_helmetgps set gpsType= 1 ;