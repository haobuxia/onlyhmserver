
ALTER TABLE hmserver.t_video MODIFY createTime DATETIME DEFAULT null;
ALTER TABLE hmserver.t_image MODIFY createTime DATETIME DEFAULT null;
ALTER TABLE hmserver.t_audio MODIFY createTime DATETIME DEFAULT null;
ALTER TABLE hmserver.t_file MODIFY createTime DATETIME DEFAULT null;
ALTER TABLE hmserver.t_sensor MODIFY createTime DATETIME DEFAULT null;
ALTER TABLE hmserver.t_helmet_bindlog MODIFY bindtime DATETIME DEFAULT null;
ALTER TABLE hmserver.t_gpsaction MODIFY createTime DATETIME DEFAULT null;
ALTER TABLE hmserver.t_gpsdata MODIFY createTime DATETIME DEFAULT null;
ALTER TABLE hmserver.t_gpsgyro MODIFY createTime DATETIME DEFAULT null;
ALTER TABLE hmserver.t_gpslocation MODIFY createTime DATETIME DEFAULT null;
ALTER TABLE hmserver.t_netease_msg MODIFY time DATETIME  DEFAULT null;
