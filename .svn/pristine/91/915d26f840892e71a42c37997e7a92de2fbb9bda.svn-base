alter table t_audio add column src VARCHAR (10);
alter table t_video add column src VARCHAR (10);

update t_audio set src='netease' where audioName like '%.aac';
update t_audio set src='upload' where audioName not like '%.aac';

update t_video set src='netease' where description like '网易抄送视频%';
update t_video set src='upload' where description not like '网易抄送视频%';