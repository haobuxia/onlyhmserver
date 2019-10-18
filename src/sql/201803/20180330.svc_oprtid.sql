ALTER TABLE `t_svc_image` MODIFY COLUMN `userId`  varchar(15) NULL DEFAULT NULL AFTER `clientId`;
ALTER TABLE `t_svc_video` MODIFY COLUMN `userId`  varchar(15) NULL DEFAULT NULL AFTER `clientId`;
ALTER TABLE `t_svc_audio` MODIFY COLUMN `userId`  varchar(15) NULL DEFAULT NULL AFTER `clientId`;

ALTER TABLE `t_tianyuan_user`
ADD COLUMN `oprtId`  varchar(15) NULL AFTER `refreshTime`,
ADD COLUMN `oprtName`  varchar(20) NULL AFTER `oprtId`;
ALTER TABLE `t_tianyuan_user` ADD COLUMN `accountId` varchar(15) NULL ;
