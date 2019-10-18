ALTER TABLE hmserver.t_video ADD videoType VARCHAR(10) NULL;
ALTER TABLE hmserver.t_image ADD imageType VARCHAR(10) NULL;

--
update t_video a set a. videoType='recirc' WHERE exists(
  select 1 from t_tag_res b ,t_tag c where a.id = b.resId and b.resType = 1 and b.tagId =c.id and c.tagName = '二手机'
);
update t_video set videoType='chat' where src='netease' and videoType is null;

update t_image a set a. imageType='recirc_jh' WHERE exists(
  select 1 from t_tag_res b ,t_tag c where a.id = b.resId and b.resType = 2 and b.tagId =c.id and c.tagName = '二手机'
);
