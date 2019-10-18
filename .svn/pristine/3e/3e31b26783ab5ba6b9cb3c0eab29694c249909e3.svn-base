ALTER TABLE hmserver.t_helmet_bindlog ADD helmetId INT NULL;
ALTER TABLE hmserver.t_helmet_bindlog DROP clientId;
delete from t_helmet_bindlog;


ALTER TABLE hmserver.t_helmet ADD neUserId INT NULL;
ALTER TABLE hmserver.t_helmet DROP bindUserId;
ALTER TABLE hmserver.t_helmet DROP clientType;
ALTER TABLE hmserver.t_helmet MODIFY factoryTime DATETIME ;

-- 正式用户
update t_helmet a
set a.neUserId = (select b.id from t_netease_user b where a.clientid = b.username)
where a.saleState = 1;

-- 测试用户
update t_helmet a
set a.neUserId = (select b.id from t_netease_user b where a.clientid = b.username),
     activeTime = factoryTime,
     factoryTime = null
where a.saleState = 2;

update t_helmet a
set a.neUserId = null,
     activeTime = null,
     factoryTime = null
where a.saleState = 0;


CREATE UNIQUE INDEX t_imei_uindex_imei ON hmserver.t_imei (imei);
DROP INDEX idx_imei_imei ON hmserver.t_imei;