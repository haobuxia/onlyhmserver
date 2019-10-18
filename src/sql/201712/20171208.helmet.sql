ALTER TABLE hmserver.t_helmet ADD salerId INT NULL;
ALTER TABLE hmserver.t_helmet
  MODIFY COLUMN neUserId INT(11) AFTER salerId;
CREATE INDEX idx_helmet_salerid ON hmserver.t_helmet (salerId);

update t_helmet set salerId = 3 where customerId in (2,3);
update t_helmet set salerId = 5 where customerId not in (2,3);