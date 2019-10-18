drop table t_site;
CREATE TABLE hmserver.t_site
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(20),
    lon FLOAT,
    lat FLOAT,
    area FLOAT,
    lonLats text,
    address VARCHAR(500),
    createTime DATETIME,
    province VARCHAR(20),
    city VARCHAR(50),
    district VARCHAR(100),
    township VARCHAR(100),
    street VARCHAR(100)
);
CREATE INDEX idx_site_lonlat ON hmserver.t_site (lon, lat);


ALTER TABLE hmserver.t_video ADD siteId INT NULL DEFAULT 0;
CREATE INDEX idx_video_siteId ON hmserver.t_video (siteId);


ALTER TABLE hmserver.t_image ADD siteId INT NULL DEFAULT 0;
CREATE INDEX idx_image_siteId ON hmserver.t_image (siteId);

