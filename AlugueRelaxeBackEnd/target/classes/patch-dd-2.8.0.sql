insert into TIPO_ALERTA24H (TIAL_ID,TIAL_NM_TIPO_ALERTA,TIAL_NM_WORKER) values (1,'SMS','br.com.jcv.backend.workers.Alerta24HSenderSMS');
insert into TIPO_ALERTA24H (TIAL_ID,TIAL_NM_TIPO_ALERTA,TIAL_NM_WORKER) values (2,'Chat','br.com.jcv.backend.workers.Alerta24HSenderChat');
insert into TIPO_ALERTA24H (TIAL_ID,TIAL_NM_TIPO_ALERTA,TIAL_NM_WORKER) values (3,'Email','br.com.jcv.backend.workers.Alerta24HSenderEmail');
insert into TIPO_ALERTA24H (TIAL_ID,TIAL_NM_TIPO_ALERTA,TIAL_NM_WORKER) values (4,'Gateway SMS','br.com.jcv.backend.workers.Alerta24HSenderGatewaySMS');
commit work;

