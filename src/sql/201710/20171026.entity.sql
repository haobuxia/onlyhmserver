alter table t_file add column uploadTime datetime;
alter table t_file add column description VARCHAR (250);
alter table t_file add column viewCount int DEFAULT 0;
alter table t_file add column savePath VARCHAR (60);

ALTER TABLE hmserver.t_audio CHANGE termCode clientId VARCHAR(50);
ALTER TABLE hmserver.t_video CHANGE termCode clientId VARCHAR(50);
ALTER TABLE hmserver.t_image CHANGE termCode clientId VARCHAR(50);
