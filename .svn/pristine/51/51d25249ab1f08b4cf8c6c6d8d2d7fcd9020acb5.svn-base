CREATE TABLE hmserver.t_order_form
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  orderNo VARCHAR(15) NOT NULL,
  machineCode VARCHAR(15) NOT NULL,
  status int NOT NULL DEFAULT 0,
  startTime datetime,
  startLonLat varchar(20),
  endTime datetime,
  endLonLat varchar(20),
  serviceStartTime datetime,
  serviceStartLonLat varchar(20),
  serviceEndTime datetime,
  serviceEndLonLat varchar(20),
  miles int,
  workHour int,
  supportType varchar(20),
  serviceDesc VARCHAR(200),
  clientResult VARCHAR(20),
  orderResult VARCHAR(20),
  orderNotReason VARCHAR(200),
  isCharge VARCHAR(10),
  partFee1 FLOAT(8,2),
  partFee2 FLOAT(8,2),
  repairFee1 FLOAT(8,2),
  repairFee2 FLOAT(8,2),
  diagnosisFee1 FLOAT(8,2),
  diagnosisFee2 FLOAT(8,2)
);
CREATE UNIQUE INDEX idx_order_form_orderno ON hmserver.t_order_form (orderNo);


CREATE TABLE hmserver.t_order_res
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  orderNo VARCHAR(15) NOT NULL,
  segment INT NOT NULL,
  resType INT NOT NULL,
  resId INT NOT NULL
);
CREATE INDEX idx_order_res_orderno ON hmserver.t_order_res (orderNo);

CREATE TABLE hmserver.t_order_part
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  orderNo VARCHAR(15) NOT NULL,
  systemOne VARCHAR(30) NOT NULL,
  systemTwo VARCHAR(30) NOT NULL,
  partSn VARCHAR(30),
  partNo VARCHAR(30) NOT NULL,
  partName VARCHAR(50) NOT NULL,
  partPrice float(10,2) NOT NULL,
  partQty int NOT NULL
);
CREATE INDEX idx_order_part_orderno ON hmserver.t_order_part (orderNo);
CREATE UNIQUE INDEX idx_order_part_partsn ON hmserver.t_order_part (partSn);


ALTER TABLE hmserver.t_tag MODIFY tagName VARCHAR(40) NOT NULL;

