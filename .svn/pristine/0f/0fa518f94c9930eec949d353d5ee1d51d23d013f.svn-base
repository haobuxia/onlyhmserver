ALTER TABLE `t_video_keyword`
ADD COLUMN `perspeech` VARCHAR(2048) NULL DEFAULT NULL AFTER `keywordId`,
ADD COLUMN `timeseconds` INT(11) NULL DEFAULT NULL AFTER `perspeech`,
ADD COLUMN `keywordName` VARCHAR(100) NULL DEFAULT NULL AFTER `timeseconds`;

ALTER TABLE `hmserver_new`.`t_keyword`
ADD COLUMN `participle` VARCHAR(45) NOT NULL AFTER `keywordName`;

