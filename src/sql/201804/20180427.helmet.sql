ALTER TABLE hmserver.t_helmet ADD neUsername VARCHAR(20) NULL;
ALTER TABLE hmserver.t_helmet MODIFY COLUMN tianyuanUserId INT(11) AFTER neUsername;

update t_helmet set neUsername = (select username from t_netease_user where t_helmet.neUserId = t_netease_user.id);