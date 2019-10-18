ALTER TABLE hmserver.t_user_log ADD userType VARCHAR(15) NULL;
ALTER TABLE hmserver.t_user_log
  MODIFY COLUMN userType VARCHAR(15) AFTER userId;
update hmserver.t_user_log set userType='tianyi';