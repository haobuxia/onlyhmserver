ALTER TABLE hmserver.t_video ADD lon FLOAT DEFAULT 0 NULL;
ALTER TABLE hmserver.t_video ADD lat FLOAT DEFAULT 0 NULL;

ALTER TABLE hmserver.t_image ADD lon FLOAT DEFAULT 0 NULL;
ALTER TABLE hmserver.t_image ADD lat FLOAT DEFAULT 0 NULL;


CREATE INDEX idx_video_lon ON hmserver.t_video (lon);
CREATE INDEX idx_video_lat ON hmserver.t_video (lat);

CREATE INDEX idx_image_lon ON hmserver.t_image (lon);
CREATE INDEX idx_image_lat ON hmserver.t_image (lat);