ALTER TABLE hmserver.t_svc_audio CHANGE userId oprtId VARCHAR(15);
ALTER TABLE hmserver.t_svc_video CHANGE userId oprtId VARCHAR(15);
ALTER TABLE hmserver.t_svc_image CHANGE userId oprtId VARCHAR(15);


CREATE TABLE hmserver.t_svc_fault_brief
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    rwh VARCHAR(15) NOT NULL,
    oprtId VARCHAR(15) NOT NULL,
    psgd VARCHAR(1),
    pscl VARCHAR(1),
    syqk VARCHAR(1),
    jbxx VARCHAR(1),
    gzjc VARCHAR(1),
    gzcl VARCHAR(1),
    sjlc VARCHAR(1),
    yhyj VARCHAR(1)
);
CREATE INDEX idx_svcfault_brief_rwhoprtid ON hmserver.t_svc_fault_brief (rwh,oprtId);