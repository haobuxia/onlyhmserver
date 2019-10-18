ALTER TABLE hmserver.t_svc_video ADD svcId VARCHAR(15) NULL;
ALTER TABLE hmserver.t_svc_video MODIFY COLUMN svcId VARCHAR(15) AFTER rwh;

ALTER TABLE hmserver.t_svc_audio ADD svcId VARCHAR(15) NULL;
ALTER TABLE hmserver.t_svc_audio MODIFY COLUMN svcId VARCHAR(15) AFTER rwh;

ALTER TABLE hmserver.t_svc_image ADD svcId VARCHAR(15) NULL;
ALTER TABLE hmserver.t_svc_image MODIFY COLUMN svcId VARCHAR(15) AFTER rwh;


DROP INDEX idx_svcvideo_userid ON hmserver.t_svc_video;
DROP INDEX idx_svcaudio_userid ON hmserver.t_svc_audio;
DROP INDEX idx_svcimage_userid ON hmserver.t_svc_image;

CREATE INDEX idx_svcvideo_oprtrid ON hmserver.t_svc_video (oprtId);
CREATE INDEX idx_svcaudio_oprtrid ON hmserver.t_svc_audio (oprtId);
CREATE INDEX idx_svcimage_oprtrid ON hmserver.t_svc_image (oprtId);
CREATE INDEX idx_svcvideo_svcid ON hmserver.t_svc_video (svcId);
CREATE INDEX idx_svcaudio_svcid ON hmserver.t_svc_audio (svcId);
CREATE INDEX idx_svcimage_svcid ON hmserver.t_svc_image (svcId);
