CREATE TABLE hmserver.t_ali_tradepay
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    outTradeNo VARCHAR(100) NOT NULL,
    helmetImei VARCHAR(20) NOT NULL,
    tyOprtId VARCHAR(20) NOT NULL,
    tyTradeType VARCHAR(50) NOT NULL,
    tyTradeNo VARCHAR(64) NOT NULL,
    tradeNo VARCHAR(64),
    buyerLogonId VARCHAR(100),
    buyerUserId VARCHAR(28),
    totalAmount FLOAT,
    receiptAmount FLOAT,
    buyerPayAmount FLOAT,
    invoiceAmount FLOAT,
    gmtPayment TIMESTAMP,
    status VARCHAR(15) NOT NULL,
    subject VARCHAR(100) NULL
);
CREATE UNIQUE INDEX t_ali_tradepay_outTradeNo_uindex ON hmserver.t_ali_tradepay (outTradeNo);
CREATE INDEX idx_ali_tradepay_oprtId ON hmserver.t_ali_tradepay (tyOprtId);