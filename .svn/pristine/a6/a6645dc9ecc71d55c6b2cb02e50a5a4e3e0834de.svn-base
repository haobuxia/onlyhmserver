ALTER TABLE hmserver.t_apk_file ADD fileType VARCHAR(20) NULL;
update t_apk_file set fileType='helmet';

DROP INDEX t_apk_update_unik_clientid ON hmserver.t_apk_update;

ALTER TABLE hmserver.t_apk_update ADD apkFileType VARCHAR(20) NULL;
ALTER TABLE hmserver.t_apk_update
  MODIFY COLUMN createUserId INT(11) AFTER apkFileType;
