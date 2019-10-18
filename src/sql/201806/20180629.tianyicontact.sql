CREATE TABLE hmserver.t_tianyi_contact
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    contactId INT NOT NULL,
    addTime TIMESTAMP,
    remark VARCHAR(20)
);
CREATE INDEX idx_tianyi_contact_userid ON hmserver.t_tianyi_contact (userId);
CREATE UNIQUE INDEX idx_tianyi_contact_unique ON hmserver.t_tianyi_contact (userId, contactId);