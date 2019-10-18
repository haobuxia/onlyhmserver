ALTER TABLE hmserver.t_video ADD orderNo VARCHAR(20) NULL;
ALTER TABLE hmserver.t_image ADD orderNo VARCHAR(20) NULL;


ALTER TABLE hmserver.t_workorder ADD tags VARCHAR(50) NULL;
ALTER TABLE hmserver.t_workorder ADD remark VARCHAR(500) NULL;
ALTER TABLE hmserver.t_workorder
  MODIFY COLUMN remark VARCHAR(500) AFTER orderState,
  MODIFY COLUMN tags VARCHAR(50) AFTER remark;

