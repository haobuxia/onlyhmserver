/** 
ALTER TABLE t_audio MODIFY createTime DATETIME(3) ;
ALTER TABLE t_file MODIFY createTime DATETIME(3) ;
ALTER TABLE t_image MODIFY createTime DATETIME(3) ;
ALTER TABLE t_video MODIFY createTime DATETIME(3) ;
ALTER TABLE t_gpsline MODIFY baseTime DATETIME(3) ;
**/

ALTER TABLE t_gpsaction MODIFY createTime DATETIME(3) ;
ALTER TABLE t_gpsdata MODIFY createTime DATETIME(3) ;
ALTER TABLE t_gpsgyro MODIFY createTime DATETIME(3) ;
ALTER TABLE t_gpslocation MODIFY createTime DATETIME(3) ;