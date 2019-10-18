ALTER TABLE hmserver.t_helmet ADD tianyiUserId INT NULL;
CREATE INDEX idx_helmet_tyuserid2 ON hmserver.t_helmet (tianyiUserId);