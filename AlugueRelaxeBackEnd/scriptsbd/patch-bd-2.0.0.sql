/* inclui novos campos em cliente referentes a info bancarias */

	ALTER TABLE CLIENTE ADD CLIE_TX_BANCO  VARCHAR(20);
	ALTER TABLE CLIENTE ADD CLIE_TX_AGENCIA  VARCHAR(10);
	ALTER TABLE CLIENTE ADD CLIE_TX_CONTA_CORRENTE  VARCHAR(20);
	
	
ALTER TABLE IMOVEL_VISITAS DROP CONSTRAINT INTEG_178;
alter table IMOVEL_VISITAS
add constraint INTEG_178
CHECK (IMVI_IN_ORIGEM_VISITA IN ('LI','PP','PD','LD','IP','EM','FB','TW','BL','RA'));
	

/******************************************************************/
/* LOCATARIO_TEMPORADA                                            */
/******************************************************************/

CREATE TABLE LOCATARIO_TEMPORADA
(
	LOTE_CD_CLIENTE  INTEGER  NOT NULL ,
	LOTE_NM_CLIENTE  VARCHAR(60)  NOT NULL ,
	LOTE_TX_CPF  VARCHAR(11)   ,
	LOTE_DT_NASCIMENTO  DATE   ,
	LOTE_TX_EMAIL  VARCHAR(100)  NOT NULL ,
	LOTE_DT_CADASTRO  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	LOTE_TX_DDD  VARCHAR(2)  NOT NULL ,
	LOTE_TX_TELEFONE  VARCHAR(10)  NOT NULL ,
	UFCI_CD_UF_CIDADE_ITEM  INTEGER NOT NULL ,
	LOTE_TX_ENDERECO  VARCHAR(120)  NOT NULL ,
	LOTE_TX_NUMERO  VARCHAR(10)   NOT NULL ,
	LOTE_TX_COMPLEMENTO  VARCHAR(20)  ,
	LOTE_TX_BAIRRO  VARCHAR(30)  NOT NULL ,
	LOTE_TX_CEP  VARCHAR(8)  NOT NULL ,
	CONSTRAINT PK_LOTE_CD_CLIENTE
                   PRIMARY KEY(LOTE_CD_CLIENTE)
);

CREATE  UNIQUE INDEX UN_LOTE_TX_EMAIL
        ON LOCATARIO_TEMPORADA(LOTE_TX_EMAIL);

/******************************************************************/
/* TABELA DE RESERVAS                                             */
/******************************************************************/
/* RESE_IN_FORMA_PGTO - Forma de pgto da pre-reserva              */
/*                                                                */
/* P = Parcial (50% de adiantamento)                              */
/* T = Total (100% de adiantamento)                               */
/******************************************************************/
/* RESE_IN_AVALIACAO - Indicador da avaliacao                     */
/*                                                                */
/* P = Positiva                                                   */
/* N = Negativa                                                   */
/******************************************************************/
CREATE TABLE RESERVA
(
	RESE_CD_RESERVA INTEGER  NOT NULL ,
	LOTE_CD_CLIENTE  INTEGER  NOT NULL ,
	RESE_DT_CHECKIN  DATE DEFAULT CURRENT_DATE NOT NULL,
	RESE_DT_CHECKOUT  DATE DEFAULT CURRENT_DATE NOT NULL,
	IMOV_CD_IMOVEL INTEGER NOT NULL,
	RESE_TX_TITULO_ANUNCIO  VARCHAR(200) NOT NULL ,
	RESE_VL_ALUGUEL_NEGOCIADO NUMERIC(10,2) CHECK (RESE_VL_ALUGUEL_NEGOCIADO > 0) NOT NULL,
	RESE_DT_PREV_DEPOSITO DATE NOT NULL,
	RESE_VL_PREV_DEPOSITO NUMERIC(10,2) CHECK (RESE_VL_PREV_DEPOSITO > 0) NOT NULL,
	RESE_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	RESE_TX_TOKEN VARCHAR(40)  NOT NULL ,
	RESE_TX_CHAVE_LIBERACAO VARCHAR(10) NOT NULL ,
	RESE_DT_EMAIL_LIBERACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	RESE_TX_CHAVE_TRACKER VARCHAR(10) NOT NULL,
	
	RESE_DT_VALIDACAO_RESERVA  TIMESTAMP,
	RESE_DT_REPROVACAO_RESERVA  TIMESTAMP,
	
	RESE_DT_REAL_DEPOSITO  TIMESTAMP,
	RESE_VL_REAL_DEPOSITO  NUMERIC(10,2) CHECK (RESE_VL_REAL_DEPOSITO > 0),

	RESE_TX_URL_CTR_LOCADOR  VARCHAR(2000),
	RESE_TX_URL_CTR_LOCATARIO  VARCHAR(2000),
	
	RESE_TX_CHAVE_VALIDACAO_CHECK VARCHAR(10) ,
	RESE_DT_CHAVE_VALIDACAO_CHECK TIMESTAMP,
	RESE_DT_TRANS_DEPOSITO TIMESTAMP,
	RESE_VL_TRANS_DEPOSITO NUMERIC(10,2) CHECK (RESE_VL_TRANS_DEPOSITO > 0),
	RESE_IN_FORMA_PGTO VARCHAR(1) DEFAULT 'P' CHECK (RESE_IN_FORMA_PGTO IN ('P','T')) NOT NULL,
	RESE_VL_CAUCAO NUMERIC(10,2) DEFAULT 0 CHECK (RESE_VL_CAUCAO > -1),
	RESE_PC_COMISSAO_RESERVA NUMERIC(4,2) DEFAULT 0 CHECK (RESE_PC_COMISSAO_RESERVA > 0),
	RESE_TX_AVALIACAO  VARCHAR(2000),
	RESE_DT_AVALIACAO TIMESTAMP,
	RESE_IN_AVALIACAO VARCHAR(1) CHECK (RESE_IN_AVALIACAO IN ('P','N')),
	CONSTRAINT PK_RESE_CD_RESERVA
                   PRIMARY KEY(RESE_CD_RESERVA)
);

CREATE UNIQUE INDEX UN_RESE_TX_TOKEN 
	ON RESERVA(RESE_TX_TOKEN);

CREATE UNIQUE INDEX UN_RESE_TX_CHAVE_TRACKER 
	ON RESERVA(RESE_TX_CHAVE_TRACKER);


ALTER TABLE RESERVA
	ADD CONSTRAINT FK_RESE_IMOV_CD_IMOVEL
		FOREIGN KEY (IMOV_CD_IMOVEL) 
			REFERENCES IMOVEL(IMOV_CD_IMOVEL) ON DELETE CASCADE;

ALTER TABLE RESERVA
	ADD CONSTRAINT FK_RESE_LOTE_CD_CLIENTE
		FOREIGN KEY (LOTE_CD_CLIENTE) 
			REFERENCES LOCATARIO_TEMPORADA(LOTE_CD_CLIENTE);

ALTER TABLE LOCATARIO_TEMPORADA
	ADD CONSTRAINT  FK_LOTE_UFCI_ITEM_CLIENTE 
        FOREIGN KEY (UFCI_CD_UF_CIDADE_ITEM) 
        REFERENCES UF_CIDADE_ITEM(UFCI_CD_UF_CIDADE_ITEM);

CREATE GENERATOR SEQ_RESE_CD_RESERVA;
SET    GENERATOR SEQ_RESE_CD_RESERVA  TO 1;

CREATE GENERATOR SEQ_LOTE_CD_CLIENTE;
SET    GENERATOR SEQ_LOTE_CD_CLIENTE  TO 1;
		