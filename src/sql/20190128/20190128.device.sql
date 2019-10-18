ALTER TABLE `hmserver`.`t_device`
ADD COLUMN `possessorId` INT(11) NULL DEFAULT NULL;
ALTER TABLE `hmserver`.`t_device_history`
ADD COLUMN `possessorId` VARCHAR(50) NULL DEFAULT NULL;
DELETE FROM `hmserver`.`t_device_status` WHERE (`id` = '2');
DELETE FROM `hmserver`.`t_device_status` WHERE (`id` = '5');
DELETE FROM `hmserver`.`t_device_status` WHERE (`id` = '6');
