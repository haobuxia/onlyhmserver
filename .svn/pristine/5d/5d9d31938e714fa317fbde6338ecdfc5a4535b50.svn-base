CREATE TABLE hmserver.t_tianyuan_user
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username VARCHAR(20),
    password VARCHAR(50),
    regTime DATETIME,
    loginType VARCHAR (1) DEFAULT '1',
    accessToken VARCHAR(1200),
    refreshToken VARCHAR(100),
    tokenType VARCHAR(20),
    expiresIn INT
);
CREATE INDEX idx_tianyuan_user_username ON hmserver.t_tianyuan_user (username);


ALTER TABLE hmserver.t_helmet ADD tianyuanUserId INT NULL;
CREATE INDEX idx_helmet_tyuserid ON hmserver.t_helmet (tianyuanUserId);
CREATE INDEX idx_helmet_neuserid ON hmserver.t_helmet (neUserId);
CREATE INDEX idx_helmet_customerid ON hmserver.t_helmet (customerId);