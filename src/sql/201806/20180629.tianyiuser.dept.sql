ALTER TABLE hmserver.t_tianyi_user ADD dept VARCHAR(20) NULL;
ALTER TABLE hmserver.t_tianyi_user
  MODIFY COLUMN dept VARCHAR(20) AFTER company;