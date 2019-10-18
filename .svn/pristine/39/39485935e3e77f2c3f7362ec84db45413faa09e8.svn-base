CREATE TABLE hmserver.t_workorder_stg
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    orderNo VARCHAR(20) NOT NULL,
    priority INT DEFAULT 0,
    eventType VARCHAR(20),
    eventVal VARCHAR(50),
    actionType VARCHAR(20),
    actionVal VARCHAR(50)
);

CREATE INDEX idx_workorder_stg_orderNo ON hmserver.t_workorder_stg (orderNo);
