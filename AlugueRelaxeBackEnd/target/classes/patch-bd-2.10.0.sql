ALTER TABLE FILA_SMS
ADD FISM_NR_PRIORIDADE INTEGER
DEFAULT 0 
NOT NULL 
CHECK (FISM_NR_PRIORIDADE IN (0,1)) ;

UPDATE FILA_SMS
SET FISM_NR_PRIORIDADE = 0;

commit work;



