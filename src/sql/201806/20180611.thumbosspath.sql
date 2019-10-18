update t_video set picPath = null;
ALTER TABLE hmserver.t_video CHANGE picPath thumbOssPath VARCHAR(100);
ALTER TABLE hmserver.t_image CHANGE picPath thumbOssPath VARCHAR(100);

update t_video set thumbOssPath = null;
update t_image set thumbOssPath = null;