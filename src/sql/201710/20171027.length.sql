ALTER TABLE hmserver.t_video MODIFY picPath VARCHAR(200);
ALTER TABLE hmserver.t_video MODIFY savePath VARCHAR(200);

ALTER TABLE hmserver.t_image MODIFY picPath VARCHAR(200);
ALTER TABLE hmserver.t_image MODIFY savePath VARCHAR(200);

ALTER TABLE hmserver.t_file MODIFY savePath VARCHAR(200);
ALTER TABLE hmserver.t_audio MODIFY savePath VARCHAR(200);