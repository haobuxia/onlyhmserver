CREATE TABLE hmserver.t_workorder
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    userRealName VARCHAR(20),
    orderNo VARCHAR(20) NOT NULL,
    subject VARCHAR(20),
    orderType VARCHAR(10),
    customerName VARCHAR(50),
    contactName VARCHAR(20),
    contactPhone VARCHAR(16),
    latLng VARCHAR(20),
    address VARCHAR(80),
    brand VARCHAR(10),
    model VARCHAR(15),
    machineCode VARCHAR(15),
    orderState VARCHAR(10),
    collaborateCnt INT DEFAULT 0,
    orderCnt INT DEFAULT 0,
    planTime datetime,
	  inputTime datetime,
	  startTime datetime,
	  endTime datetime
);
CREATE UNIQUE INDEX idx_workorder_orderNo_uindex ON hmserver.t_workorder (orderNo);
CREATE INDEX idx_workorder_userid ON hmserver.t_workorder (userId);
CREATE INDEX idx_workorder_orderState ON hmserver.t_workorder (orderState);
CREATE INDEX idx_workorder_ordertype ON hmserver.t_workorder (orderType);