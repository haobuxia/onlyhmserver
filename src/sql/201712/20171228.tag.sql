CREATE TABLE hmserver.t_tag
(
    id INT not null PRIMARY KEY AUTO_INCREMENT,
    tagName VARCHAR(20) NOT NULL,
    createTime DATETIME NOT NULL,
    resCount INT DEFAULT 0
);
CREATE UNIQUE INDEX idx_tag_tagName ON hmserver.t_tag (tagName);

CREATE TABLE hmserver.t_tag_res
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    createTime DATETIME,
    tagId INT NOT NULL,
    resType INT NOT NULL,
    resId INT NOT NULL
);
CREATE INDEX idx_tagres_tagid ON hmserver.t_tag_res (tagId);
CREATE INDEX idx_tagres_restype ON hmserver.t_tag_res (resType);
CREATE INDEX idx_tagres_resid ON hmserver.t_tag_res (resId);
CREATE UNIQUE INDEX idx_tagres_unique ON hmserver.t_tag_res (tagId, resType, resId);