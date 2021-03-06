
ALTER TABLE CLIENTE ADD MOPU_CD_MODO_PUBLICIDADE INTEGER;

ALTER TABLE IMOVEL_CONTATO_ANUNCIANTE ADD MOPU_CD_MODO_PUBLICIDADE INTEGER;

/******************************************************************/
/* TABELA MODOS DE PUBLICIDADE                                    */
/******************************************************************/
CREATE TABLE MODO_PUBLICIDADE 
(
    MOPU_CD_MODO_PUBLICIDADE INTEGER  NOT NULL ,
    MOPU_TX_MODO_PUBLICIDADE VARCHAR(100)  NOT NULL ,
    MOPU_IN_STATUS  VARCHAR(1)  DEFAULT 'N' CHECK (MOPU_IN_STATUS IN ('S','N'))  NOT NULL,
    MOPU_DT_CADASTRO  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT PK_MOPU_CD_MODO_PUBLICIDADE
                   PRIMARY KEY(MOPU_CD_MODO_PUBLICIDADE)
);

/******************************************************************/
/* TABELA MODOS DE PUBLICIDADE POR VISITANTES EXTERNOS            */
/******************************************************************/
CREATE TABLE MODO_PUBL_VISITAS
(
    MOPV_CD_MODO_PUBL_VISITAS  INTEGER  NOT NULL ,
    MOPU_CD_MODO_PUBLICIDADE INTEGER  NOT NULL,
    MOPV_DT_RESPOSTA DATE DEFAULT CURRENT_DATE NOT NULL,
    MOPV_QT_RESPOSTA INTEGER DEFAULT 1 NOT NULL,
    MOPV_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
        CONSTRAINT PK_MOPV_CD_IMODO_PUBL_VISITAS
             PRIMARY KEY(MOPV_CD_MODO_PUBL_VISITAS)
);

CREATE  UNIQUE INDEX UN_DT_VISITA_MODO_PUBLICIDADE 
   ON MODO_PUBL_VISITAS(MOPV_DT_RESPOSTA, MOPU_CD_MODO_PUBLICIDADE);

ALTER TABLE CLIENTE
    ADD CONSTRAINT  FK_MODO_PUBLICIDADE_CLIENTE
        FOREIGN KEY (MOPU_CD_MODO_PUBLICIDADE) 
        REFERENCES MODO_PUBLICIDADE(MOPU_CD_MODO_PUBLICIDADE);

ALTER TABLE IMOVEL_CONTATO_ANUNCIANTE
    ADD CONSTRAINT  FK_IMOV_CONTAT_ANUNC_MODO_PUBL
        FOREIGN KEY (MOPU_CD_MODO_PUBLICIDADE) 
        REFERENCES MODO_PUBLICIDADE(MOPU_CD_MODO_PUBLICIDADE);


ALTER TABLE MODO_PUBL_VISITAS
    ADD CONSTRAINT FK_MODO_PUBL_VISITAS 
        FOREIGN KEY (MOPU_CD_MODO_PUBLICIDADE)
            REFERENCES MODO_PUBLICIDADE(MOPU_CD_MODO_PUBLICIDADE);
   
CREATE GENERATOR SEQ_MOPV_CD_MODO_PUBL_VISITAS;
SET    GENERATOR SEQ_MOPV_CD_MODO_PUBL_VISITAS  TO 1;
        
   
