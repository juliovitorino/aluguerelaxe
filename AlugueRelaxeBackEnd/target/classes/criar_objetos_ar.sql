/******************************************************************/
/* DDL para criação das tabelas do AlugueRelaxe!                  */
/*                                                                */
/* Autor: Julio Cesar Vitorino                                    */
/*                                                                */
/******************************************************************/
/* SQL Manager 2008 Lite for InterBase and Firebird 5.1.0.5       */
/* -------------------------------------------------------------- */
/* Database : C:\Users\Julio\Resources\Dados\aluguerelaxe.fdb     */
/******************************************************************/

/******************************************************************/
/* BANCO DE DADOS                                                 */
/******************************************************************/
set names ISO8859_1;
set sql dialect 3;


CREATE DATABASE 'C:\Users\Julio\Resources\Dados\aluguerelaxe.fdb'
  USER 'aluguerelaxe'
  PASSWORD 'fork3t56'
  PAGE_SIZE 8192
  DEFAULT CHARACTER SET ISO8859_1;

commit;

connect C:\Users\Julio\Resources\Dados\aluguerelaxe.fdb
user 'aluguerelaxe' password 'fork3t56';

/******************************************************************/
/* OBJETOS DE BANCO DE DADOS                                      */
/******************************************************************/

/******************************************************************/
/* ASSINANTES                                                     */
/******************************************************************/
/* Valores para ASSI_IN_STATUS                                    */
/* A = Ativo                                                      */
/* P = Pendente - Email enviado para assinante e aguardando sua   */
/*                confirmação do link. Registro permanece na base */
/*                por 72 horas.                                   */
/* I = Inativado                                                  */
/******************************************************************/

CREATE TABLE ASSINANTES
(
	ASSI_CD_ASSINANTE INTEGER  NOT NULL ,
	ASSI_NM_ASSINANTE  VARCHAR(60)  NOT NULL ,
	ASSI_TX_EMAIL  VARCHAR(100)  NOT NULL ,
	ASSI_TX_HASH   VARCHAR(40)  NOT NULL ,
	ASSI_TX_CAMPANHA  VARCHAR(100)  NOT NULL ,
	ASSI_IN_STATUS  VARCHAR(1)  DEFAULT 'P' CHECK (ASSI_IN_STATUS IN ('A','P','I'))  NOT NULL,
	ASSI_IN_SORTEADO  VARCHAR(1)  DEFAULT 'N' CHECK (ASSI_IN_SORTEADO IN ('S','N'))  NOT NULL,
	ASSI_DT_CADASTRO  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	ASSI_DT_SORTEIO  TIMESTAMP CHECK (ASSI_DT_SORTEIO <= CURRENT_TIMESTAMP),  
	ASSI_CD_ASSINANTE_PARENT INTEGER,
    ASSI_NR_TICKET  INTEGER  DEFAULT 0 NOT NULL ,
	CONSTRAINT PK_ASSI_CD_ASSINANTE
                   PRIMARY KEY(ASSI_CD_ASSINANTE)
);

CREATE  UNIQUE INDEX UN_ASSI_TX_EMAIL
        ON ASSINANTES(ASSI_TX_EMAIL, ASSI_TX_CAMPANHA, ASSI_CD_ASSINANTE_PARENT);

CREATE  UNIQUE INDEX UN_ASSI_TX_HASH
        ON ASSINANTES(ASSI_TX_HASH);
		
/******************************************************************/
/* PROMOCAO_TICKET                                                */
/******************************************************************/

CREATE TABLE PROMOCAO_TICKET
(
    PRTI_NM_PROMOCAO  VARCHAR(60)  NOT NULL ,
    PRTI_NR_TICKET  INTEGER  NOT NULL ,
    CONSTRAINT PK_CD_PROMOCAO_TICKET
                   PRIMARY KEY(PRTI_NM_PROMOCAO)
);

/******************************************************************/
/* CARACTERISTICAS                                                */
/******************************************************************/
CREATE TABLE CARACTERISTICA
(
	CARA_CD_CARACTERISTICA  INTEGER  NOT NULL ,
	CARA_NM_CARACTERISTICA  VARCHAR(50)  NOT NULL ,
	CARA_TX_ANUNCIO  VARCHAR(50)  NOT NULL,
	CONSTRAINT PK_CARA_CD_CARACTERISTICA 
                   PRIMARY KEY(CARA_CD_CARACTERISTICA) 
);

CREATE  UNIQUE INDEX UN_CARA_NM_CARACTERISTICA 
   ON CARACTERISTICA(CARA_NM_CARACTERISTICA);

COMMIT WORK;

/******************************************************************/
/* CIDADES                                                        */
/******************************************************************/

CREATE TABLE CIDADE
(
	CIDA_CD_CIDADE  INTEGER  NOT NULL ,
	CIDA_NM_CIDADE  VARCHAR(50)  NOT NULL,
	CONSTRAINT PK_CIDA_CD_CIDADE 
                   PRIMARY KEY(CIDA_CD_CIDADE)
);
COMMIT WORK;

/******************************************************************/
/* CLIENTES                                                       */
/* Valores para CLIE_IN_STATUS                                    */
/* A = Ativo                                                      */
/* P = Pendente - Email enviado para cliente e aguardando sua     */
/*                confirmação do link. Registro permanece na base */
/*                por 72 horas.                                   */
/* B = Bloqueado - Ex. situação de falta de pgto                  */
/* I = Inativado                                                  */
/* V = Verificar - Cliente confirmou o estado P e fica pendente a */
/*                 verificação dos dados nos campos para conteudo */
/*                 impróprio. Próximo status deverá ser A ou R    */
/* R = Reprovado - Reprovado por conter conteúdo impróprio. Regis-*/
/*                 tro pronto para ser deletado pelo robô.        */
/*----------------------------------------------------------------*/
/******************************************************************/
/* Valores para CLIE_IN_TIPO_ANUNCIANTE                           */
/* P = Proprietário                                               */
/* C = Patrocinador                                               */
/* I = Imobiliária                                                */
/* T = (Preciso descobrir)                                        */
/*----------------------------------------------------------------*/
/******************************************************************/
/* Valores para CLIE_IN_STATUS                                    */
/* A = Ativo                                                      */
/* P = Pendente. Aguardando confirmação do cliente através do     */
/*     email                                                      */
/* B = Bloqueado.                                                 */
/* I = Inativado.                                                 */
/* V = Verificar. Aguardando liberação, após cliente ter dado     */
/*     sua confirmação por e-mail. (ALUGUERELAXE)                 */
/* R = Reprovado. Normalmente por cadastro de conteúdo impróprio  */
/******************************************************************/
/*----------------------------------------------------------------*/
/* Valores para CLIE_IN_VERIFICADO                                */
/* S = O cliente foi verificado                                   */
/* N = O cliente nao foi verificado                               */
/*----------------------------------------------------------------*/
/******************************************************************/

CREATE TABLE CLIENTE
(
	CLIE_CD_CLIENTE  INTEGER  NOT NULL ,
	CLIE_NM_CLIENTE  VARCHAR(60)  NOT NULL ,
	CLIE_TX_CPF  VARCHAR(11)   ,
	CLIE_TX_CGC  VARCHAR(18)   ,
	CLIE_DT_NASCIMENTO  DATE   ,
	CLIE_TX_EMAIL  VARCHAR(100)  NOT NULL ,
	CLIE_TX_SENHA  VARCHAR(40)  NOT NULL ,
	CLIE_CD_NOVO_ACESSO  VARCHAR(40) ,
	CLIE_DT_CADASTRO  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CLIE_DT_ATUALIZACAO  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CLIE_IN_STATUS  VARCHAR(1)  DEFAULT 'P' CHECK (CLIE_IN_STATUS IN ('A','P','B','I','V','R'))  NOT NULL ,
	CLIE_TX_URL  VARCHAR(500) ,
	CLIE_IN_AUTORIZA_NOTIFICACAO  VARCHAR(1)  DEFAULT 'N' CHECK (CLIE_IN_AUTORIZA_NOTIFICACAO IN ('S','N'))  NOT NULL,
	CLIE_TX_MSN  VARCHAR(100)   ,
	CLIE_TX_SKYPE  VARCHAR(100)   ,
	CLIE_TX_GOOGLE_TALK  VARCHAR(100)   ,
	UFCI_CD_UF_CIDADE_ITEM  INTEGER NOT NULL ,
	CLIE_TX_ENDERECO  VARCHAR(120)  NOT NULL ,
	CLIE_TX_NUMERO  VARCHAR(10)   NOT NULL ,
	CLIE_TX_COMPLEMENTO  VARCHAR(20)  ,
	CLIE_TX_BAIRRO  VARCHAR(30)  NOT NULL ,
	CLIE_TX_CODIGO_ATIVACAO VARCHAR(40) NOT NULL,
	CLIE_TX_CEP  VARCHAR(8)  NOT NULL ,
	CLIE_IN_TIPO_ANUNCIANTE  VARCHAR(1)  DEFAULT 'P' CHECK (CLIE_IN_TIPO_ANUNCIANTE IN ('P','C','I','T'))  NOT NULL,
	CLIE_TX_BANCO  VARCHAR(20)  ,
	CLIE_TX_AGENCIA  VARCHAR(10)  ,
	CLIE_TX_CONTA_CORRENTE  VARCHAR(20)  ,
	MOPU_CD_MODO_PUBLICIDADE INTEGER,
	CLIE_NU_PERG INTEGER DEFAULT 0 NOT NULL,
	CLIE_NU_RESP INTEGER DEFAULT 0 NOT NULL,
	CLIE_TX_FOTO_PERFIL VARCHAR(50),
	CLIE_TX_FOTO_CHAT VARCHAR(50),
	CLIE_IN_VERIFICADO  VARCHAR(1)  DEFAULT 'N' CHECK (CLIE_IN_VERIFICADO IN ('S','N'))  NOT NULL,
	CONSTRAINT PK_CLIE_CD_CLIENTE
                   PRIMARY KEY(CLIE_CD_CLIENTE)
);

CREATE  UNIQUE INDEX UN_CLIE_TX_EMAIL
        ON CLIENTE(CLIE_TX_EMAIL);

CREATE  UNIQUE INDEX UN_CLIE_TX_CODIGO_ATIVACAO
        ON CLIENTE(CLIE_TX_CODIGO_ATIVACAO);

COMMIT WORK;

/******************************************************************/
/* DEPOIMENTOS                                                    */
/* P = Pendente de verificacao                                    */
/* L = Depoimento verificado e liberado                           */
/* R = Depoimento reprovado por conteúdo improprio                */
/******************************************************************/

CREATE TABLE DEPOIMENTO
(
	DEPO_CD_DEPOIMENTO  INTEGER  NOT NULL ,
	DEPO_NM_PESSOA  VARCHAR(100)  NOT NULL ,
	DEPO_TX_DEPOIMENTO  VARCHAR(500) NOT NULL ,
	DEPO_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	DEPO_IN_STATUS  VARCHAR(1)  DEFAULT 'P' 
                                 CHECK (DEPO_IN_STATUS IN ('P','L','R'))  NOT NULL,
	CONSTRAINT PK_DEPO_CD_DEPOIMENTO 
                   PRIMARY KEY(DEPO_CD_DEPOIMENTO)
);

/******************************************************************/
/* IMOVEIS                                                        */
/******************************************************************/
/* IMOV_IN_STATUS                                                 */
/*  I - Inativo                                                   */
/*  A - Ativo                                                     */
/*  P - Pendente de pagamento                                     */
/*  V - Anuncio Vencido                                           */
/******************************************************************/
/*                                                                */
/* IMOV_IN_TIPO_PROPRIEDADE                                       */
/*  C - Casa                                                      */
/*  A - Apartamento                                               */
/*  H - Hotel                                                     */
/*  X - Chacara                                                   */
/*  F - Flat                                                      */
/*  Z - Fazenda                                                   */
/*  S - Sitio                                                     */
/*  L - Chale                                                     */
/*  P - Pousada                                                   */
/*                                                                */
/******************************************************************/
/* IMOV_IN_TIPO_COLABORACAO                                       */
/*  N - Não faz nenhuma colaboração                               */
/*  C - Colabora com alguma quantia                               */
/*  P - Patrocinador Oficial                                      */
/*  G - Patrocinador Oficial Categoria Gold                       */
/*  D - Patrocinador Oficial Categoria Diamante                   */
/******************************************************************/
/* IMOV_IN_OBJETIVO                                               */
/*  A - Aluguel mensal                                            */
/*  T - Aluguel de temporada                                      */
/*  V - Venda                                                     */
/******************************************************************/

CREATE TABLE IMOVEL
(
	IMOV_CD_IMOVEL  INTEGER  NOT NULL ,
	CLIE_CD_CLIENTE  INTEGER  NOT NULL ,
	IMOV_DT_CADASTRO  TIMESTAMP  DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	IMOV_DT_ATUALIZACAO  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	IMOV_QT_QUARTOS  INTEGER  NOT NULL ,
	IMOV_QT_SUITE  INTEGER  DEFAULT 0  NOT NULL,
	IMOV_QT_BANHEIROS  INTEGER DEFAULT 0  NOT NULL,
	IMOV_QT_CAPACIDADE  INTEGER DEFAULT 0   NOT NULL,
	IMOV_TX_DESCRICAO_GERAL  VARCHAR(2000)  NOT NULL ,
	IMOV_TX_DESCRICAO_QUARTOS  VARCHAR(2000)  NOT NULL ,
	IMOV_TX_DESCRICAO_ARREDORES  VARCHAR(2000)  NOT NULL ,
	IMOV_TX_TITULO_ANUNCIO  VARCHAR(200)  NOT NULL ,
	IMOV_IN_STATUS  VARCHAR(1)  DEFAULT 'A' 
                        CHECK (IMOV_IN_STATUS IN ('A','I','P','V'))  NOT NULL,
	IMOV_TX_OBSERVACOES  VARCHAR(2000)  ,
	IMOV_IN_MOSTRA_TAB_PRECO  VARCHAR(1)  DEFAULT 'S'
                                  CHECK (IMOV_IN_MOSTRA_TAB_PRECO IN ('S','N'))  NOT NULL,
	IMOV_IN_CONDOMINIO        VARCHAR(1) DEFAULT 'N'
                                  CHECK (IMOV_IN_CONDOMINIO IN ('S','N')) NOT NULL ,
	IMOV_DT_ULTIMA_VISITA  DATE  ,
	IMOV_QT_VISITAS  INTEGER  DEFAULT 0 NOT NULL ,
	IMOV_IN_PERMITE_ALUGAR_FESTAS  VARCHAR(1)  DEFAULT 'N'
                                       CHECK (IMOV_IN_PERMITE_ALUGAR_FESTAS IN ('S','N'))  NOT NULL,
	IMOV_IN_TIPO_PROPRIEDADE  VARCHAR(1)  DEFAULT 'C' 
                                  CHECK (IMOV_IN_TIPO_PROPRIEDADE IN ('C','A','H','X','F','Z','S','L','P'))  NOT NULL,
	IMOV_IN_SORTEIO VARCHAR(1) DEFAULT 'N'
                                  CHECK (IMOV_IN_SORTEIO IN ('S','N')) NOT NULL ,                                 
	IMOV_IN_SORTEIO_PD VARCHAR(1) DEFAULT 'N'
                                  CHECK (IMOV_IN_SORTEIO_PD IN ('S','N')) NOT NULL ,                                 
	IMOV_IN_SORTEIO_SB VARCHAR(1) DEFAULT 'N'
                                  CHECK (IMOV_IN_SORTEIO_SB IN ('S','N')) NOT NULL ,                                 
	IMOV_TX_ENDERECO  VARCHAR(120)  NOT NULL ,
	IMOV_TX_NUMERO  VARCHAR(10)  NOT NULL ,
	IMOV_TX_COMPLEMENTO  VARCHAR(20)   ,
	IMOV_TX_BAIRRO  VARCHAR(30)  NOT NULL ,
	IMOV_TX_CEP  VARCHAR(8)  NOT NULL ,
	UFCI_CD_UF_CIDADE_ITEM  INTEGER NOT NULL,
	IMOV_NR_LATITUDE NUMERIC(10,6) DEFAULT 0 NOT NULL,
	IMOV_NR_LONGITUDE NUMERIC(10,6) DEFAULT 0 NOT NULL,
	IMOV_IN_TIPO_COLABORACAO  VARCHAR(1)  DEFAULT 'N' 
                                  CHECK (IMOV_IN_TIPO_COLABORACAO IN ('N','C','P','G','D'))  NOT NULL,
	IMOV_VL_TIPO_COLABORACAO  NUMERIC(10,2) DEFAULT -1 NOT NULL,
	IMOV_DT_LIMITE_TIPO_C  DATE,
	IMOV_IMAGEM_PREFERIDA INTEGER DEFAULT 0 NOT NULL,
	IMOV_SQ_OFERECIMENTO INTEGER DEFAULT 0,
	IMOV_IN_OBJETIVO  VARCHAR(1)  DEFAULT 'T' 
                        CHECK (IMOV_IN_OBJETIVO IN ('A','T','V'))  NOT NULL,
	IMOV_VL_TARIFA_BASICA INTEGER DEFAULT 0,
	CONSTRAINT PK_IMOV_CD_IMOVEL 
             PRIMARY KEY(IMOV_CD_IMOVEL)
);

CREATE INDEX IX_IMOV_UFCI_CD_UF_CIDADE_ITEM
ON IMOVEL (UFCI_CD_UF_CIDADE_ITEM);

CREATE INDEX IX_IMOV_SQ_OFERECIMENTO 
ON IMOVEL(IMOV_SQ_OFERECIMENTO);

/******************************************************************/
/* IMOVEL CARACTERISTICA                                          */
/******************************************************************/

CREATE TABLE IMOVEL_CARACTERISTICA
(
	IMCA_CD_CARACTERISTICA  INTEGER  NOT NULL ,
	CARA_IN_CONDOMINIO  VARCHAR(1)  DEFAULT 'I' 
                            CHECK (CARA_IN_CONDOMINIO IN ('I','C'))  NOT NULL,
	CARA_CD_CARACTERISTICA  INTEGER  NOT NULL ,
	IMOV_CD_IMOVEL  INTEGER  NOT NULL,
	CONSTRAINT PK_IMCA_CD_CARACTERISTICA 
                   PRIMARY KEY(IMCA_CD_CARACTERISTICA)
);

CREATE  UNIQUE INDEX UN_IMCA_IMOVEL_CARACTERISTICA 
        ON IMOVEL_CARACTERISTICA(IMOV_CD_IMOVEL,CARA_CD_CARACTERISTICA,CARA_IN_CONDOMINIO);

/******************************************************************/
/* IMOVEL PLANO                                                   */
/******************************************************************/

CREATE TABLE IMOVEL_PLANO
(
	IMPL_CD_IMOVEL_PLANO  INTEGER  NOT NULL ,
	IMOV_CD_IMOVEL  INTEGER  NOT NULL ,
	PLAN_CD_PLANO  INTEGER  NOT NULL ,
	IMPL_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	CONSTRAINT PK_IMPL_CD_IMOVEL_PLANO 
                   PRIMARY KEY(IMPL_CD_IMOVEL_PLANO)
);

CREATE INDEX UN_IMPL_CD_IMOVEL_PLANO
        ON IMOVEL_PLANO(IMOV_CD_IMOVEL,PLAN_CD_PLANO);

/******************************************************************/
/* IMOVEL PLANO FATURA                                            */
/*                                                                */
/* IMPF_IN_STATUS                                                 */
/* P = Aguardando liberacao pelo sistema de pagamento pagseguro   */
/* L = Pagamento efetuado e liberado pelo pagseguro               */
/* R = Pagamento reprovador pelo pagseguro                        */
/* C = fatura cancelada                                           */
/* V = fatura vencida                                             */
/*                                                                */
/******************************************************************/
CREATE TABLE IMOVEL_PLANO_FATURA
(
	IMPF_CD_IMOVEL_PLANO_FATURA  INTEGER  NOT NULL ,
	IMPL_CD_IMOVEL_PLANO  INTEGER  NOT NULL ,
	IMPF_IN_STATUS VARCHAR(1) DEFAULT 'P' CHECK (IMPF_IN_STATUS IN ('P','L','R','C','V'))  NOT NULL,
	IMPF_VL_FATURA  NUMERIC(10,2) CHECK (IMPF_VL_FATURA > 0) NOT NULL,
	IMPF_VL_DESCONTO  NUMERIC(10,2) DEFAULT 0 NOT NULL,
	IMPF_DT_VENCIMENTO DATE NOT NULL,
	IMPF_DT_PGTO TIMESTAMP,
	IMPF_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	CONSTRAINT PK_IMPF_CD_IMOVEL_PLANO_FATURA 
                   PRIMARY KEY(IMPF_CD_IMOVEL_PLANO_FATURA)
);

/******************************************************************/
/* IMOVEL_VISITAS                                                 */
/*                                                                */
/* IMVI_IN_ORIGEM_VISITA                                          */
/* origem pode ser:                                               */
/*                                                                */
/* PP = Propaganda Pagina  Principal                              */
/* PD = Propaganda Página Outros Destaques                        */
/* LI = Acesso pela Listagem de imagens                           */
/* LD = Link direto quando enviado por email por exemplo          */
/*      ou pelo proprio painel de outros imoveis                  */
/*      deste anunciante na ficha de imovel                       */
/* IP = Painel Imovel Patrocinador                                */
/* EM = Link proveniente de email marketing                       */
/* FB = Rede social facebook                                      */
/* TW = Rede social twitter                                       */
/* BL = Link pelo Blogger                                         */
/* RA = Rede de afiliados                                         */
/* SB = Super Banner                                              */
/*                                                                */
/******************************************************************/
CREATE TABLE IMOVEL_VISITAS
(
	IMVI_CD_IMOVEL_VISITAS  INTEGER  NOT NULL ,
	IMOV_CD_IMOVEL  INTEGER  NOT NULL,
	IMVI_NR_ANO  NUMERIC(4,0) NOT NULL,
	IMVI_NR_MES  NUMERIC(2,0) NOT NULL,
	IMVI_NR_DIA  NUMERIC(2,0) NOT NULL,
	IMVI_QT_VISITA INTEGER DEFAULT 1 NOT NULL,
	IMVI_IN_ORIGEM_VISITA VARCHAR(2) DEFAULT 'LI' 
		CHECK (IMVI_IN_ORIGEM_VISITA IN ('LI','PP','PD','LD','IP', 'EM','FB','TW','BL','RA','SB'))  NOT NULL,
        CONSTRAINT PK_IMVI_CD_IMOVEL_VISITAS 
             PRIMARY KEY(IMVI_CD_IMOVEL_VISITAS)
);

CREATE  UNIQUE INDEX UN_IMVI_IMOVEL_ANO_MES 
   ON IMOVEL_VISITAS(IMOV_CD_IMOVEL,IMVI_NR_ANO,IMVI_NR_MES,IMVI_NR_DIA,IMVI_IN_ORIGEM_VISITA);

/******************************************************************/
/* MENSAGEM                                                       */
/******************************************************************/

CREATE TABLE MENSAGEM
(
	MENS_CD_MENSAGEM  VARCHAR(8)  NOT NULL ,
	MENS_TX_MENSAGEM  VARCHAR(1000)  NOT NULL  ,
	CONSTRAINT PK_MENS_CD_MENSAGEM 
                   PRIMARY KEY(MENS_CD_MENSAGEM)
);

/******************************************************************/
/* PERIODO DE INDISPONIBILIDADE                                   */
/******************************************************************/
CREATE TABLE PERIODO_INDISPONIBILIDADE
(
	PEIN_CD_PERIODO_INDISPONIBILID  INTEGER  NOT NULL ,
	IMOV_CD_IMOVEL  INTEGER  NOT NULL ,
	PEIN_DT_IMOVEL_LOCADO  DATE  NOT NULL,
	CONSTRAINT PK_PEIN_CD_PERIODO_INDISP PRIMARY KEY(PEIN_CD_PERIODO_INDISPONIBILID)
);

CREATE  UNIQUE INDEX UN_PEIN_IMOV_CD_IMOVEL_DT_IMOV 
        ON PERIODO_INDISPONIBILIDADE(IMOV_CD_IMOVEL,PEIN_DT_IMOVEL_LOCADO);

/******************************************************************/
/* PLANO                                                          */
/*                                                                */
/* PLAN_IN_TIPO_PERIODICIDADE                                     */
/* M = Mensal                                                     */
/* T = Trimestral                                                 */
/* S = Semestral                                                  */
/* A = Anual                                                      */
/* P = Publicidade                                                */
/******************************************************************/
/* PLAN_IN_TIPO_COMPRA                                            */
/* N = Tipo de compra NORMAL                                      */
/* P = Tipo de compra de PUBLICIDADE DE PRIMEIRA PAGINA           */
/* D = Tipo de compra de PUBLICIDADE DE DESTAQUE                  */
/* E = Tipo de compra de ESPECIAL (IMOBILIARIAS)                  */
/* W = Tipo de compra de PUBLICIDADE DE SUPER BANNER              */
/* B = Tipo de compra de PUBLICIDADE DE BANNER (IMAGEM)           */
/* M = Tipo de compra de PUBLICIDADE DE EMAIL MARKETING           */
/* X = Tipo de compra de PUBLICIDADE DE FACEBOOK                  */
/* F = Tipo de compra de PUBLICIDADE DE FURA-FILA                 */
/* R = Tipo de compra de PUBLICIDADE DE PATROCINADOR              */
/* Z = Tipo de compra de PUBLICIDADE DE SMS                       */
/*                                                                */
/******************************************************************/
/* PLAN_IN_RECURSO_XX                                             */
/*                                                                */
/* 1o. caracter indica S = Sim; N=Nao                             */
/* 2o. ao 4o. indicam quantidade que o recurso                    */
/* sera aplicado.                                                 */
/* 5o. significa D = Em dias; X = 1 por mes; U = unidade          */
/*                                                                */
/* PLANO_IN_RECURSO                                               */
/* 01 = RECURSO_VIDEO                                             */
/* 02 = RECURSO_CANECA                                            */
/* 03 = RECURSO_SUPERBANNER                                       */
/* 04 = RECURSO_PP                                                */
/* 05 = RECURSO_PD                                                */
/* 06 = RECURSO_IMOVEL_PATROCINADOR                               */
/* 07 = RECURSO_NEGOCIA_DIRETO                                    */
/* 08 = RECURSO_CAMPANHA_EMAIL                                    */
/* 09 = RECURSO_CAMPANHA_SMS                                      */
/* 10 = RECURSO_APOIO_JURIDICO                                    */
/* 11 = RECURSO_FACEBOOK                                          */
/* 12 = RECURSO_BANNER                                            */
/* 13 = RECURSO_ANUNCIO_NARRADO                                   */
/* 14 = RECURSO_ANUNCIO_DIFERENCIADO                              */
/* 15 = RECURSO_ALERTA_24H                                        */
/* 16 = RECURSO_FURA_FILA                                         */
/* 17 =                                                           */
/* 18 =                                                           */
/* 19 =                                                           */
/* 20 =                                                           */
/* 21 =                                                           */
/* 22 =                                                           */
/* 23 =                                                           */
/* 24 =                                                           */
/* 25 =                                                           */
/* 26 =                                                           */
/* 27 =                                                           */
/* 28 =                                                           */
/* 29 =                                                           */
/* 30 =                                                           */
/******************************************************************/

CREATE TABLE PLANO
(
	PLAN_CD_PLANO  INTEGER  NOT NULL ,
	PLAN_NM_PLANO  VARCHAR(30)  NOT NULL ,
	PLAN_TX_DESCRICAO  VARCHAR(100)  NOT NULL ,
	PLAN_VL_PLANO  NUMERIC(10,2)  NOT NULL ,
	PLAN_VL_DESCONTO  NUMERIC(10,2) DEFAULT 0  NOT NULL ,
	PLAN_IN_STATUS  VARCHAR(1)  DEFAULT 'I' 
                        CHECK (PLAN_IN_STATUS IN ('A','I'))  NOT NULL,
	PLAN_DT_CADASTRO  TIMESTAMP  DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	PLAN_DT_ATIVACAO  TIMESTAMP  ,
	PLAN_DT_CANCELAMENTO  TIMESTAMP  ,
	PLAN_IN_TIPO_PERIODICIDADE  VARCHAR(1)  NOT NULL
                                    CHECK (PLAN_IN_TIPO_PERIODICIDADE IN ('M','T','S','A','P')),
	PLAN_IN_TIPO_COMPRA  VARCHAR(1) DEFAULT 'N'  NOT NULL
                                    CHECK (PLAN_IN_TIPO_COMPRA IN ('N','P','D','E','W','B','M','X','F','R','Z')),
	PLAN_NR_DIAS  INTEGER  NOT NULL,
	PLAN_TX_BTNPGTO_PAGSEGURO  VARCHAR(2000)  NULL ,
	PLAN_TX_BTNPGTO_PAYPAL  VARCHAR(2000)  NULL ,
	PLAN_QT_MAX_FOTO_PERMITIDOS  NUMERIC(2) DEFAULT 3 NOT NULL ,
	PLAN_NU_BYTES_VAULT  NUMERIC(14) DEFAULT 0 CHECK (PLAN_NU_BYTES_VAULT BETWEEN 0 AND 10995116277760) NOT NULL ,
	PLAN_IN_RECURSO_01  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_02  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_03  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_04  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_05  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_06  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_07  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_08  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_09  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_10  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_11  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_12  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_13  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_14  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_15  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_16  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_17  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_18  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_19  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_20  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_21  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_22  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_23  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_24  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_25  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_26  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_27  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_28  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_29  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_30  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_31  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_32  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_33  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_34  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_35  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_36  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_37  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_38  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_39  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	PLAN_IN_RECURSO_40  VARCHAR(5) DEFAULT 'N000X' NOT NULL ,
	CONSTRAINT PK_PLAN_CD_PLANO 
                   PRIMARY KEY(PLAN_CD_PLANO)
);

/******************************************************************/
/* TABELA_PRECO                                                   */
/******************************************************************/
CREATE TABLE TABELA_PRECO
(
	TAPR_CD_TABELA_PRECO INTEGER NOT NULL,
	IMOV_CD_IMOVEL  INTEGER  NOT NULL ,
	TAPR_TX_PERIODO  VARCHAR(50),
	TAPR_DT_INICIO  DATE ,
	TAPR_DT_DATA_FIM  DATE,
	TAPR_VL_TABELA  NUMERIC(10,2)  NOT NULL ,
	TAPR_TX_MINIMO_DE  VARCHAR(18)  ,
	TAPR_TX_OBSERVACAO  VARCHAR(200)   ,
	CONSTRAINT PK_TAPR_CD_TABELA_PRECO
                   PRIMARY KEY(TAPR_CD_TABELA_PRECO)
);

/******************************************************************/
/* TELEFONE                                                       */
/******************************************************************/
CREATE TABLE TELEFONE
(
	TELE_CD_TELEFONE  INTEGER  NOT NULL ,
	CLIE_CD_CLIENTE  INTEGER  NOT NULL ,
	TELE_NM_CONTATO  VARCHAR(20)  NOT NULL ,
	TELE_TX_DDD  VARCHAR(2)  NOT NULL ,
	TELE_TX_TELEFONE  VARCHAR(10)  NOT NULL ,
	TELE_IN_PERMITE_EXIBIR  VARCHAR(1)  DEFAULT 'S' 
                                CHECK (TELE_IN_PERMITE_EXIBIR IN ('S','N'))  NOT NULL,
	TELE_IN_TIPO_TELEFONE  VARCHAR(1)  DEFAULT 'R'
                               CHECK (TELE_IN_TIPO_TELEFONE IN ('R','C','F','L'))  NOT NULL,
	CONSTRAINT PK_TELE_CD_TELEFONE 
                   PRIMARY KEY(TELE_CD_TELEFONE)
);

/******************************************************************/
/* UF                                                             */
/******************************************************************/
CREATE TABLE UF
(
	UF_CD_ESTADO  VARCHAR(2)  NOT NULL ,
	UF_NM_ESTADO  VARCHAR(20)  NOT NULL ,
	CONSTRAINT PK_UF_CD_ESTADO 
                   PRIMARY KEY(UF_CD_ESTADO)
);

/******************************************************************/
/* UF_CIDADE_ITEM                                                 */
/******************************************************************/
CREATE TABLE UF_CIDADE_ITEM
(
	UFCI_CD_UF_CIDADE_ITEM  INTEGER NOT NULL ,
	UFCI_IN_STATUS  VARCHAR(1)   DEFAULT 'A' CHECK (UFCI_IN_STATUS IN ('A','I'))  NOT NULL,
	UFCI_QT_IMOVEIS  INTEGER  DEFAULT 0  NOT NULL,
	CIDA_CD_CIDADE  INTEGER  NOT NULL ,
	UF_CD_ESTADO  VARCHAR(2)  NOT NULL ,
	UFCI_NR_LATITUDE NUMERIC(10,6) DEFAULT 0 NOT NULL,
	UFCI_NR_LONGITUDE NUMERIC(10,6) DEFAULT 0 NOT NULL,
	CONSTRAINT PK_UFCI_CD_UF_CIDADE_ITEM 
                   PRIMARY KEY(UFCI_CD_UF_CIDADE_ITEM)
);

CREATE  UNIQUE INDEX UN_UF_CIDADE_ITEM 
        ON UF_CIDADE_ITEM(UF_CD_ESTADO,CIDA_CD_CIDADE);

/******************************************************************/
/* VARIAVEL                                                       */
/******************************************************************/
CREATE TABLE VARIAVEL
(
	VARI_CD_VARIAVEL  VARCHAR(100)  NOT NULL ,
	VARI_TX_DESCRICAO  VARCHAR(500)  NOT NULL ,
	VARI_TX_VALOR_CONTEUDO  VARCHAR(500)  NOT NULL ,
	CONSTRAINT PK_VARI_CD_VARIAVEL 
                   PRIMARY KEY(VARI_CD_VARIAVEL)
);

/************************************************************************/
/* IMOVEL_IMAGENS_VIDEO                                                 */
/*                                                                      */
/* IMIV_IN_IMAGEM_VIDEO - Indicador de tipo da imagem. Estes podem ser: */
/* TB - Thumbnail                                                       */
/* XG - Imagem grande                                                   */
/* PP - Publicidade pagina principal                                    */
/* PD - Publicidade pagina de destaques                                 */
/* VD - Video do imovel - Fonte YouTube                                 */
/* VP - Video pagina principal                                          */
/* MI - Mini                                                            */
/************************************************************************/
CREATE TABLE IMOVEL_IMAGEM_VIDEO
(
	IMIV_CD_IMOVEL_IMAGEM_VIDEO INTEGER NOT NULL ,
	IMIV_NM_IMAGEM_VIDEO  VARCHAR(500)  NOT NULL ,
	IMIV_IN_IMAGEM_VIDEO  VARCHAR(2) DEFAULT 'TB' CHECK(IMIV_IN_IMAGEM_VIDEO IN ('MI','TB','XG','PP','PD','VD','VP'))  NOT NULL,
	IMOV_CD_IMOVEL  INTEGER  NOT NULL ,
	IMIV_NR_ORDEM  INTEGER  NOT NULL ,
	IMIV_TX_HASH   VARCHAR(40) NOT NULL,
	IMIV_DT_CADASTRO  TIMESTAMP  DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	CONSTRAINT PK_IMIV_CD_IMOVEL_IMAGEM_VIDEO 
                   PRIMARY KEY(IMIV_CD_IMOVEL_IMAGEM_VIDEO)
);

CREATE INDEX IX_IMIV_NR_ORDEM
ON IMOVEL_IMAGEM_VIDEO (IMIV_NR_ORDEM);

CREATE UNIQUE INDEX UN_IMIV_TX_HASH
ON IMOVEL_IMAGEM_VIDEO (IMOV_CD_IMOVEL, IMIV_TX_HASH);

/************************************************************************/
/* PUBLICIDADE                                                          */
/************************************************************************/
/* PUBL_IN_TIPO_PUBLICIDADE - Indicador de tipo de publicidade          */
/* PP = Painel Principal                                                */
/* PD = Painel de destaque                                              */
/* SB = Painel Super Banner                                             */
/* EM - Email                                                           */
/* SM = SMS                                                             */
/* BN = Banner                                                          */
/* FB = Facebook                                                        */
/* FF = Fura-Fila                                                       */
/* PA = Patrocinador                                                    */
/************************************************************************/
/* PUBL_IN_STATUS                                                       */
/* P = Aguardando liberacao de pagamento da fatura                      */
/* L = Pagamento da fatura realizado com sucesso. Publicidade Liberada. */
/* R = Pagamento reprovado  pelo pagseguro. Publicidade Reprovada.      */
/************************************************************************/
CREATE TABLE PUBLICIDADE
(
	PUBL_CD_PUBLICIDADE INTEGER NOT NULL ,
	PUBL_DT_INICIO DATE NOT NULL ,
	PUBL_DT_FIM DATE NOT NULL ,
	PUBL_IN_TIPO_PUBLICIDADE VARCHAR(2) DEFAULT 'PP' CHECK(PUBL_IN_TIPO_PUBLICIDADE IN ('PP','PD','SB','EM','SM','BN','FB','FF','PA'))  NOT NULL,
	PUBL_IN_STATUS VARCHAR(1) DEFAULT 'P' CHECK (PUBL_IN_STATUS IN ('P','L','R'))  NOT NULL,
	IMOV_CD_IMOVEL  INTEGER  NOT NULL ,
	PUBL_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	IMPF_CD_IMOVEL_PLANO_FATURA  INTEGER,
	CONSTRAINT PK_PUBL_CD_PUBLICIDADE 
                   PRIMARY KEY(PUBL_CD_PUBLICIDADE)
);

/************************************************************************/
/* IMOVEL_CONTATO_ANUNCIANTE                                            */
/*                                                                      */
/* IMCA_IN_STATUS - Status em que se encontra o contato entre o proposto*/
/*                  e o anunciante.                                     */
/* P = Pendente de verificação                                          */
/* L = Liberado para envio de e-mail do anunciate                       */
/* E = E-mail enviado ao anunciante                                     */
/* R = Contato Reprovado pelo Administrador                             */
/************************************************************************/
CREATE TABLE IMOVEL_CONTATO_ANUNCIANTE
(
	IMCA_CD_IMOVEL_CONTATO_ANU VARCHAR(40) NOT NULL,
	IMOV_CD_IMOVEL  INTEGER  NOT NULL ,
	IMCA_IN_STATUS VARCHAR(1) DEFAULT 'P' CHECK(IMCA_IN_STATUS IN ('P','L','E','R'))  NOT NULL,
	IMCA_IN_CTO_REALIZADO VARCHAR(1) DEFAULT 'N' CHECK(IMCA_IN_CTO_REALIZADO IN('S','N'))  NOT NULL,	
	IMCA_NM_PROPOSTO VARCHAR(100) NOT NULL,
	IMCA_TX_EMAIL_PROPOSTO VARCHAR(200) NOT NULL, 
	IMCA_TX_DDD VARCHAR(2) NOT NULL,
	IMCA_TX_TELEFONE VARCHAR(10) NOT NULL,
	IMCA_QT_ADULTOS NUMERIC(2,0) DEFAULT 0  NOT NULL,
	IMCA_QT_CRIANCAS NUMERIC(2,0) DEFAULT 0  NOT NULL,
	IMCA_TX_ADICIONAL VARCHAR(2000) NOT NULL,
	IMCA_TX_CIDADE VARCHAR(50) NOT NULL,
	IMCA_TX_UF VARCHAR(30) NOT NULL,
	IMCA_DT_PREV_CHEGADA DATE NOT NULL,
	IMCA_DT_PREV_PARTIDA DATE NOT NULL,
	IMCA_TX_PAIS VARCHAR(30) NOT NULL,
	IMCA_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	MOPU_CD_MODO_PUBLICIDADE INTEGER,
	IMCA_IN_ALERTA24H VARCHAR(1) DEFAULT 'S' CHECK(IMCA_IN_ALERTA24H IN ('S','N'))  NOT NULL,
	IMCA_IN_QUEUED_24H VARCHAR(1) DEFAULT 'N' CHECK(IMCA_IN_QUEUED_24H IN ('S','N'))  NOT NULL,
	IMCA_DT_QUEUED TIMESTAMP,
	IMCA_SQ_OFERECIMENTO INTEGER DEFAULT 0,
	IMCA_TX_IMG_VISITANTE VARCHAR(50),
	IMCA_CD_OMC_ANUNCIANTE VARCHAR(12) NOT NULL,
	IMCA_CD_OMC_VISITANTE VARCHAR(12) NOT NULL,
	IMCA_CD_OMC_ADMIN VARCHAR(12) NOT NULL,
	IMCA_IN_THREAD_STATUS VARCHAR(1) DEFAULT 'I' CHECK(IMCA_IN_THREAD_STATUS IN ('A','I'))  NOT NULL,
	CONSTRAINT PK_IMCA_CD_IMOVEL_CONTATO_ANU
		PRIMARY KEY(IMCA_CD_IMOVEL_CONTATO_ANU)
);

CREATE INDEX IX_IMCA_SQ_OFERECIMENTO ON IMOVEL_CONTATO_ANUNCIANTE(IMCA_SQ_OFERECIMENTO);


/************************************************************************/
/* IMOVEL_CONTATO_THREAD                                                */
/************************************************************************/
/* Valores para IMCT_IN_STATUS                                          */
/* A = Ativo                                                            */
/* P = Pendente - Aguardando aprovacao do administrador(moderador)      */
/************************************************************************/
/* Valores para IMCT_IN_ORIGEM                                          */
/* A = Mensagem foi redigida pelo anunciante                            */
/* V = Mensagem foi redigida pelo visitante                             */
/* M = Mensagem foi redigida pelo Administrador                         */
/************************************************************************/
/* Valores para IMCT_IN_MODO                                            */
/* PR = Pergunta ou Resposta                                            */
/* CO = Comentários                                                     */
/************************************************************************/
CREATE TABLE IMOVEL_CONTATO_THREAD
(
	IMCT_ID INTEGER  NOT NULL ,
	IMCT_ID_PARENT INTEGER,
	IMCA_CD_IMOVEL_CONTATO_ANU VARCHAR(40) NOT NULL,
	IMCT_IN_ORIGEM VARCHAR(1) DEFAULT 'V' CHECK(IMCT_IN_ORIGEM IN ('V','A','M'))  NOT NULL,
	IMCT_IN_STATUS VARCHAR(1) DEFAULT 'P' CHECK(IMCT_IN_STATUS IN ('P','A'))  NOT NULL,
	IMCT_IN_MODO VARCHAR(2) DEFAULT 'PR' CHECK(IMCT_IN_MODO IN ('PR','CO'))  NOT NULL,
	IMCT_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	IMCT_TX_HASH   VARCHAR(40)  NOT NULL,
	IMCT_TX_THREAD VARCHAR(2000) NOT NULL,
	IMCT_TX_THREAD_EDITADA VARCHAR(2000) NOT NULL,
	CONSTRAINT PK_IMCT_ID  
		PRIMARY KEY(IMCT_ID)
);

CREATE INDEX IX_IMCT_ID_IMOVEL_CONTATO_ANU ON IMOVEL_CONTATO_THREAD(IMCA_CD_IMOVEL_CONTATO_ANU);

CREATE UNIQUE INDEX UN_IMCT_TX_HASH ON IMOVEL_CONTATO_THREAD(IMCT_TX_HASH);


/************************************************************************/
/* CHAT                                                                 */
/************************************************************************/
/* CHAT_IN_STATUS - Status em que se encontra o chat                    */
/*                                                                      */
/* A = Ativo                                                            */
/* I = Inativo                                                          */
/************************************************************************/
CREATE TABLE CHAT
(
	CHAT_CD_CHAT  INTEGER  NOT NULL ,
	CHAT_IN_STATUS VARCHAR(1) DEFAULT 'A' CHECK(CHAT_IN_STATUS IN ('A','I'))  NOT NULL,
	CHAT_IN_SESSAO VARCHAR(2) DEFAULT 'PP' CHECK(CHAT_IN_SESSAO IN('PP','DD'))  NOT NULL,	
	CHAT_TX_TITULO VARCHAR(100) NOT NULL,
	CHAT_TX_URL_IMAGEM VARCHAR(2000) NOT NULL,
	CHAT_TX_CHAT VARCHAR(2000) NOT NULL,
	CHAT_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	CHAT_DT_VALIDADE_INICIAL DATE DEFAULT CURRENT_DATE  NOT NULL,
	CHAT_DT_VALIDADE_FINAL DATE DEFAULT CURRENT_DATE  NOT NULL,
	CLIE_CD_CLIENTE INTEGER, 
	CONSTRAINT PK_CHAT_CD_CHAT
		PRIMARY KEY(CHAT_CD_CHAT)
);
CREATE INDEX IX_IN_SESSAO_IN_STATUS
ON CHAT (CHAT_IN_SESSAO, CHAT_IN_STATUS);


/******************************************************************/
/* CLASSIFICACAO                                                  */
/******************************************************************/
CREATE TABLE CLASSIFICACAO
(
    CLAS_CD_CLASSIFICACAO INTEGER NOT NULL ,
    CLAS_TX_CLASSIFICACAO VARCHAR(200) NOT NULL,
    CLAS_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    CONSTRAINT PK_CLAS_CD_CLASSIFICACAO 
                   PRIMARY KEY(CLAS_CD_CLASSIFICACAO)
);

/******************************************************************/
/* LOCAL                                                          */
/******************************************************************/
CREATE TABLE LOCAL
(
    LOCA_CD_LOCAL INTEGER NOT NULL ,
    LOCA_TX_LOCAL VARCHAR(200) NOT NULL,
    LOCA_TX_URL_IMAGEM VARCHAR(2000) NOT NULL,
    LOCA_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    CONSTRAINT PK_LOCA_CD_LOCAL 
                   PRIMARY KEY(LOCA_CD_LOCAL)
);


/******************************************************************/
/* RELACIONAMENTO LOCAL X CLASSIFICACAO                           */
/******************************************************************/
CREATE TABLE LOCAL_CLASSIFICACAO_ITEM
(
    LOCI_CD_LOCAL_CLASSIF_ITEM INTEGER NOT NULL ,
    LOCA_CD_LOCAL INTEGER NOT NULL ,
    CLAS_CD_CLASSIFICACAO INTEGER NOT NULL ,
    LOCI_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    CONSTRAINT PK_LOCI_CD_LOCAL_CLASSIF_ITEM 
                   PRIMARY KEY(LOCI_CD_LOCAL_CLASSIF_ITEM)
);

CREATE  UNIQUE INDEX UN_LOCAL_CLASSIF_ITEM
        ON  LOCAL_CLASSIFICACAO_ITEM(LOCA_CD_LOCAL, CLAS_CD_CLASSIFICACAO);

/******************************************************************/
/* RELACIONAMENTO LOCAL X CIDADE/UF                               */
/******************************************************************/
CREATE TABLE LOCAL_UF_CIDADE_ITEM
(
    LOUC_CD_LOCAL_UF_CIDADE_ITEM INTEGER NOT NULL ,
    LOCA_CD_LOCAL INTEGER NOT NULL ,
    UFCI_CD_UF_CIDADE_ITEM  INTEGER NOT NULL ,
    LOUC_TX_DESCRICAO VARCHAR(2000) ,
    LOUC_NR_LATITUDE NUMERIC(10,6) DEFAULT 0 NOT NULL,
    LOUC_NR_LONGITUDE NUMERIC(10,6) DEFAULT 0 NOT NULL,
    LOUC_TX_URL_ADICIONAL_1 VARCHAR(2000) ,
    LOUC_TX_URL_ADICIONAL_2 VARCHAR(2000) ,
    LOUC_TX_URL_ADICIONAL_3 VARCHAR(2000) ,
    LOUC_TX_URL_ADICIONAL_4 VARCHAR(2000) ,
    LOUC_TX_URL_ADICIONAL_5 VARCHAR(2000) ,
    LOUC_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    LOUC_TX_URL_IMAGEM VARCHAR(2000) NOT NULL,
    CONSTRAINT PK_LOUC_CD_LOCAL_UFCIDADE_ITEM 
                   PRIMARY KEY(LOUC_CD_LOCAL_UF_CIDADE_ITEM)
);

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

CREATE INDEX IX_LOTE_TX_EMAIL
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
/* RESE_NU_NOTA_IMOVEL - Nota de avaliacao do imovel              */
/* RESE_NU_NOTA_ANFITRIAO - Nota de avaliacao do anfitriao        */
/*                                                                */
/* 0 = Não avaliado                                               */
/* 1 = Muito Ruim ate 5 = Excelente                               */
/*                                                                */
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
	RESE_VL_TAXA_SERVICO NUMERIC(10,2) DEFAULT 0 CHECK (RESE_VL_TAXA_SERVICO > -1),
	RESE_TX_AVALIACAO  VARCHAR(2000),
	RESE_DT_AVALIACAO TIMESTAMP,
	RESE_IN_AVALIACAO VARCHAR(1) CHECK (RESE_IN_AVALIACAO IN ('P','N')),
	IMCA_CD_IMOVEL_CONTATO_ANU VARCHAR(40),
	OFIT_ID INTEGER,
	RESE_TX_IMG_VISITANTE VARCHAR(50),
	RESE_NU_NOTA_IMOVEL INTEGER DEFAULT 0 CHECK (RESE_NU_NOTA_IMOVEL IN (0,1,2,3,4,5)),
	RESE_NU_NOTA_ANFITRIAO INTEGER DEFAULT 0 CHECK (RESE_NU_NOTA_ANFITRIAO IN (0,1,2,3,4,5)),

	CONSTRAINT PK_RESE_CD_RESERVA
                   PRIMARY KEY(RESE_CD_RESERVA)
);

CREATE UNIQUE INDEX UN_RESE_TX_TOKEN 
	ON RESERVA(RESE_TX_TOKEN);

CREATE UNIQUE INDEX UN_RESE_TX_CHAVE_TRACKER 
	ON RESERVA(RESE_TX_CHAVE_TRACKER);
	
CREATE INDEX IX_RESE_IMCA_CD_IMOVEL_CONTATO 
	ON RESERVA(IMCA_CD_IMOVEL_CONTATO_ANU);
	
CREATE INDEX IX_RESE_OFIT
    ON RESERVA(OFIT_ID);	


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
   ON MODO_PUBL_VISITAS(MOPV_DT_VISITA, MOPU_CD_MODO_PUBLICIDADE);

/******************************************************************/
/* TABELA FILA DE SMS                                             */
/******************************************************************/
/* FISM_IN_MODO - Modo de envio do SMS                            */
/*                                                                */
/* M = Envio atraves de MODEM 3G                                  */
/* G = Atraves de Servicos de Gateway                             */
/******************************************************************/
CREATE TABLE FILA_SMS
(
	FISM_ID INTEGER NOT NULL,
	FISM_TX_CELULAR VARCHAR(11) NOT NULL,
	FISM_TX_MSG VARCHAR(140) NOT NULL,
	FISM_IN_QUEUED VARCHAR(1) DEFAULT 'N' CHECK(FISM_IN_QUEUED IN ('S','N'))  NOT NULL,
	FISM_DT_QUEUED TIMESTAMP,
	FISM_IN_MODO VARCHAR(1) DEFAULT 'M' CHECK(FISM_IN_MODO IN ('M','G'))  NOT NULL,
	FISM_NR_PRIORIDADE INTEGER DEFAULT 0 CHECK (FISM_NR_PRIORIDADE IN (0,1))  NOT NULL,
	FISM_DT_ENVIO_GATEWAY TIMESTAMP,
	FISM_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	CONSTRAINT PK_FISM_ID
		PRIMARY KEY(FISM_ID)
);
  

/******************************************************************/
/* TABELA TIPO DE ALERTA                                          */
/******************************************************************/
CREATE TABLE TIPO_ALERTA24H
(
	TIAL_ID INTEGER NOT NULL,
	TIAL_NM_TIPO_ALERTA VARCHAR(50) NOT NULL,
	TIAL_NM_WORKER VARCHAR(500) NOT NULL,
	TIAL_IN_STATUS VARCHAR(1) DEFAULT 'A' CHECK(TIAL_IN_STATUS IN ('A','I'))  NOT NULL,
	TIAL_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	CONSTRAINT PK_TIAL_ID
		PRIMARY KEY(TIAL_ID)

);

/******************************************************************/
/* TABELA FILA ALERTA 24H                                         */
/******************************************************************/
CREATE TABLE FILA_ALERTA24H
(
	FIAL_ID INTEGER NOT NULL,
	TIAL_ID INTEGER NOT NULL,
	IMOV_CD_IMOVEL INTEGER NOT NULL,
	IMCA_CD_IMOVEL_CONTATO_ANU VARCHAR(40) NOT NULL,
	FIAL_IN_EMITIDO VARCHAR(1) DEFAULT 'N' CHECK(FIAL_IN_EMITIDO IN ('S','N'))  NOT NULL,
	FIAL_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	CONSTRAINT PK_FIAL_ID
		PRIMARY KEY(FIAL_ID)
);

/******************************************************************/
/* TABELA OFERTA                                                  */
/******************************************************************/
/* OFER_IN_STATUS                                                 */
/* I = INATIVO                                                    */
/* A = ATIVO                                                      */
/******************************************************************/
CREATE TABLE OFERTA
(
	OFER_ID INTEGER NOT NULL,
	IMOV_CD_IMOVEL INTEGER NOT NULL,
	OFER_TX_TITULO VARCHAR(2000) NOT NULL,
	OFER_IN_STATUS VARCHAR(1) DEFAULT 'I' CHECK(OFER_IN_STATUS IN ('I','A'))  NOT NULL,
	OFER_DT_INICIO TIMESTAMP DEFAULT CURRENT_TIMESTAMP CHECK(OFER_DT_INICIO > CURRENT_TIMESTAMP) NOT NULL,
	OFER_DT_FIM TIMESTAMP DEFAULT CURRENT_TIMESTAMP CHECK(OFER_DT_FIM >= OFER_DT_INICIO) NOT NULL,
	OFER_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	CONSTRAINT PK_OFER_ID
		PRIMARY KEY(OFER_ID)
);

/******************************************************************/
/* TABELA OFERTA ITEM                                             */
/******************************************************************/
/* OFIT_IN_STATUS                                                 */
/* I = INATIVO                                                    */
/* A = ATIVO                                                      */
/* E = ESGOTADO                                                   */
/******************************************************************/
CREATE TABLE OFERTA_ITEM
(
	OFIT_ID INTEGER NOT NULL,
	OFER_ID INTEGER NOT NULL,
	OFIT_TX_TITULO VARCHAR(2000) NOT NULL,
	OFIT_IN_STATUS VARCHAR(1) DEFAULT 'I' CHECK(OFIT_IN_STATUS IN ('I','A','E'))  NOT NULL,
	OFIT_NU_VENDIDOS INTEGER DEFAULT 0 NOT NULL,
	OFIT_NU_LIMITE_MINIMO INTEGER DEFAULT 1 CHECK(OFIT_NU_LIMITE_MINIMO > 0)  NOT NULL,
	OFIT_NU_LIMITE_MAXIMO INTEGER DEFAULT 1 CHECK(OFIT_NU_LIMITE_MAXIMO >= OFIT_NU_LIMITE_MINIMO) NOT NULL,
	OFER_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	CONSTRAINT PK_OFER_ID
		PRIMARY KEY(OFER_ID)
);

/******************************************************************/
/* TABELA OFERTA REGULAMENTO                                      */
/******************************************************************/
/* OFIT_IN_STATUS                                                 */
/* I = INATIVO                                                    */
/* A = ATIVO                                                      */
/* E = ESGOTADO                                                   */
/******************************************************************/
CREATE TABLE OFERTA_REGULAMENTO
(
	OFRE_ID INTEGER NOT NULL,
	OFER_ID INTEGER NOT NULL,
	OFRE_TX_REGRA VARCHAR(2000) NOT NULL,
	OFRE_DT_CADASTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
	CONSTRAINT PK_OFER_ID
		PRIMARY KEY(OFER_ID)
);

/******************************************************************/
/* TABELA DE TEMPLATES                                            */
/******************************************************************/
CREATE TABLE TEMPLATE
(
	TEMP_ID INTEGER  NOT NULL ,
	TEMP_TX_DESCRICAO  VARCHAR(2000)  NOT NULL ,
	TEMP_TX_CLASSE  VARCHAR(2000)  NOT NULL ,
	TEMP_TX_PATH   VARCHAR(2000)  NOT NULL ,
	TEMP_TX_TEMPLATE  VARCHAR(100)  NOT NULL ,
	TEMP_IN_STATUS  VARCHAR(1)  DEFAULT 'A' CHECK (TEMP_IN_STATUS IN ('A','I'))  NOT NULL,
	TEMP_DT_CADASTRO  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT PK_TEMP_ID
                   PRIMARY KEY(TEMP_ID)
);

/******************************************************************/
/* CRIACAO DE TODAS AS FKS                                        */
/******************************************************************/

ALTER TABLE CLIENTE
	ADD CONSTRAINT  FK_UF_CIDADE_ITEM_CLIENTE 
        FOREIGN KEY (UFCI_CD_UF_CIDADE_ITEM) 
        REFERENCES UF_CIDADE_ITEM(UFCI_CD_UF_CIDADE_ITEM);

ALTER TABLE CLIENTE
	ADD CONSTRAINT  FK_MODO_PUBLICIDADE_CLIENTE
        FOREIGN KEY (MOPU_CD_MODO_PUBLICIDADE) 
        REFERENCES MODO_PUBLICIDADE(MOPU_CD_MODO_PUBLICIDADE);

ALTER TABLE IMOVEL
	ADD CONSTRAINT  FK_CLIENTE_IMOVEL 
        FOREIGN KEY (CLIE_CD_CLIENTE) 
        REFERENCES CLIENTE(CLIE_CD_CLIENTE);

ALTER TABLE IMOVEL
	ADD CONSTRAINT  FK_UF_CIDADE_ITEM_IMOVEL
        FOREIGN KEY (UFCI_CD_UF_CIDADE_ITEM) 
        REFERENCES UF_CIDADE_ITEM(UFCI_CD_UF_CIDADE_ITEM);

ALTER TABLE IMOVEL_CARACTERISTICA
	ADD CONSTRAINT  FK_CARACTERISTICA_IMOVEL_CARAC 
        FOREIGN KEY (CARA_CD_CARACTERISTICA) 
        REFERENCES CARACTERISTICA(CARA_CD_CARACTERISTICA);

ALTER TABLE IMOVEL_CARACTERISTICA
	ADD CONSTRAINT  FK_IMOVEL_IMOVEL_CARACTERISTIC 
        FOREIGN KEY (IMOV_CD_IMOVEL) 
        REFERENCES IMOVEL(IMOV_CD_IMOVEL) ON DELETE CASCADE;

ALTER TABLE IMOVEL_PLANO
	ADD CONSTRAINT  FK_IMOVEL_IMOVEL_PLANO 
        FOREIGN KEY (IMOV_CD_IMOVEL) 
        REFERENCES IMOVEL(IMOV_CD_IMOVEL) ON DELETE CASCADE;

ALTER TABLE IMOVEL_CONTATO_ANUNCIANTE
	ADD CONSTRAINT  FK_IMOVEL_IMOVEL_CONTATO_ANUNC
        FOREIGN KEY (IMOV_CD_IMOVEL) 
        REFERENCES IMOVEL(IMOV_CD_IMOVEL) ON DELETE CASCADE;

ALTER TABLE IMOVEL_CONTATO_ANUNCIANTE
	ADD CONSTRAINT  FK_IMOV_CONTAT_ANUNC_MODO_PUBL
        FOREIGN KEY (MOPU_CD_MODO_PUBLICIDADE) 
        REFERENCES MODO_PUBLICIDADE(MOPU_CD_MODO_PUBLICIDADE);

ALTER TABLE IMOVEL_PLANO
	ADD CONSTRAINT  FK_PLANO_IMOVEL_PLANO 
        FOREIGN KEY (PLAN_CD_PLANO) 
        REFERENCES PLANO(PLAN_CD_PLANO);

ALTER TABLE PERIODO_INDISPONIBILIDADE
	ADD CONSTRAINT  FK_IMOVEL_PERIODO_INDISPONIBIL 
        FOREIGN KEY (IMOV_CD_IMOVEL) 
        REFERENCES IMOVEL(IMOV_CD_IMOVEL) ON DELETE CASCADE;

ALTER TABLE TABELA_PRECO
	ADD CONSTRAINT  FK_IMOVEL_TABELA_PRECO 
        FOREIGN KEY (IMOV_CD_IMOVEL) 
        REFERENCES IMOVEL(IMOV_CD_IMOVEL) ON DELETE CASCADE;

ALTER TABLE TELEFONE
	ADD CONSTRAINT  FK_TELEFONE_CLIENTE 
        FOREIGN KEY (CLIE_CD_CLIENTE) 
        REFERENCES CLIENTE(CLIE_CD_CLIENTE) ON DELETE CASCADE;

ALTER TABLE UF_CIDADE_ITEM
	ADD CONSTRAINT  FK_CIDADE_UF_CIDADE_ITEM 
        FOREIGN KEY (CIDA_CD_CIDADE) 
        REFERENCES CIDADE(CIDA_CD_CIDADE) ON DELETE CASCADE;

ALTER TABLE UF_CIDADE_ITEM
	ADD CONSTRAINT  FK_UF_UF_CIDADE_ITEM 
        FOREIGN KEY (UF_CD_ESTADO) 
        REFERENCES UF(UF_CD_ESTADO) ON DELETE CASCADE;

ALTER TABLE IMOVEL_IMAGEM_VIDEO
	ADD CONSTRAINT FK_IMOVEL_IMAGEM_VIDEO 
        FOREIGN KEY (IMOV_CD_IMOVEL) 
        REFERENCES IMOVEL(IMOV_CD_IMOVEL) ON DELETE CASCADE;

ALTER TABLE PUBLICIDADE
	ADD CONSTRAINT FK_PUBLICIDADE 
        FOREIGN KEY (IMOV_CD_IMOVEL) 
        REFERENCES IMOVEL(IMOV_CD_IMOVEL) ON DELETE CASCADE;
		
ALTER TABLE PUBLICIDADE
	ADD CONSTRAINT FK_PUBL_IMPF
        FOREIGN KEY (IMPF_CD_IMOVEL_PLANO_FATURA) 
        REFERENCES IMOVEL_PLANO_FATURA(IMPF_CD_IMOVEL_PLANO_FATURA);

ALTER TABLE IMOVEL_VISITAS
	ADD CONSTRAINT  FK_IMOVEL_IMOVEL_VISITAS 
        FOREIGN KEY (IMOV_CD_IMOVEL) 
        REFERENCES IMOVEL(IMOV_CD_IMOVEL) ON DELETE CASCADE;
        
ALTER TABLE IMOVEL_PLANO_FATURA
	ADD CONSTRAINT  FK_IMOVEL_PLANO_FATURA 
        FOREIGN KEY (IMPL_CD_IMOVEL_PLANO) 
        REFERENCES IMOVEL_PLANO(IMPL_CD_IMOVEL_PLANO);        

ALTER TABLE CHAT
	ADD CONSTRAINT FK_CHAT_CLIENTE
		FOREIGN KEY (CLIE_CD_CLIENTE)
		REFERENCES CLIENTE(CLIE_CD_CLIENTE) ON DELETE CASCADE;

ALTER TABLE LOCAL_UF_CIDADE_ITEM
    ADD CONSTRAINT FK_LOCAL_UF_CIDADE_ITEM
        FOREIGN KEY (LOCA_CD_LOCAL)
        REFERENCES LOCAL(LOCA_CD_LOCAL) ON DELETE CASCADE;

ALTER TABLE LOCAL_UF_CIDADE_ITEM
    ADD CONSTRAINT FK_UFCI_CD_UF_CIDADE_ITEM
        FOREIGN KEY (UFCI_CD_UF_CIDADE_ITEM)
        REFERENCES UF_CIDADE_ITEM(UFCI_CD_UF_CIDADE_ITEM) ON DELETE CASCADE;
        
ALTER TABLE LOCAL_CLASSIFICACAO_ITEM
    ADD CONSTRAINT FK_LOCI_LOCA_CD_LOCAL
        FOREIGN KEY (LOCA_CD_LOCAL)
        REFERENCES LOCAL(LOCA_CD_LOCAL) ON DELETE CASCADE;

ALTER TABLE LOCAL_CLASSIFICACAO_ITEM
    ADD CONSTRAINT FK_LOCI_CLAS_CD_CLASSIFICACAO
        FOREIGN KEY (CLAS_CD_CLASSIFICACAO)
        REFERENCES CLASSIFICACAO(CLAS_CD_CLASSIFICACAO) ON DELETE CASCADE;

ALTER TABLE RESERVA
	ADD CONSTRAINT FK_RESE_IMOV_CD_IMOVEL
		FOREIGN KEY (IMOV_CD_IMOVEL) 
			REFERENCES IMOVEL(IMOV_CD_IMOVEL) ON DELETE CASCADE;

ALTER TABLE RESERVA
	ADD CONSTRAINT FK_RESE_LOTE_CD_CLIENTE
		FOREIGN KEY (LOTE_CD_CLIENTE) 
			REFERENCES LOCATARIO_TEMPORADA(LOTE_CD_CLIENTE);

ALTER TABLE RESERVA
	ADD CONSTRAINT FK_RESE_IMCA_CD_IMOVEL_CONTATO 
		FOREIGN KEY (IMCA_CD_IMOVEL_CONTATO_ANU) 
			REFERENCES IMOVEL_CONTATO_ANUNCIANTE(IMCA_CD_IMOVEL_CONTATO_ANU);
			
ALTER TABLE RESERVA
	ADD CONSTRAINT FK_RESE_OFIT 
		FOREIGN KEY (OFIT_ID) 
			REFERENCES OFERTA_ITEM(OFIT_ID);
			
ALTER TABLE LOCATARIO_TEMPORADA
	ADD CONSTRAINT  FK_LOTE_UFCI_ITEM_CLIENTE 
        FOREIGN KEY (UFCI_CD_UF_CIDADE_ITEM) 
        REFERENCES UF_CIDADE_ITEM(UFCI_CD_UF_CIDADE_ITEM);

ALTER TABLE MODO_PUBL_VISITAS
	ADD CONSTRAINT FK_MODO_PUBL_VISITAS 
		FOREIGN KEY (MOPU_CD_MODO_PUBLICIDADE)
			REFERENCES MODO_PUBLICIDADE(MOPU_CD_MODO_PUBLICIDADE);

ALTER TABLE ALERTA24H
	ADD CONSTRAINT FK_ALER_TIAL
		FOREIGN KEY (TIAL_ID) 
			REFERENCES TIPO_ALERTA24H(TIAL_ID) ON DELETE CASCADE;

ALTER TABLE ALERTA24H
	ADD CONSTRAINT FK_ALER_IMCA
		FOREIGN KEY (IMCA_CD_IMOVEL_CONTATO_ANU) 
			REFERENCES IMOVEL_CONTATO_ANUNCIANTE(IMCA_CD_IMOVEL_CONTATO_ANU) ON DELETE CASCADE;

ALTER TABLE ALERTA24H
	ADD CONSTRAINT FK_ALER_CLIE
		FOREIGN KEY (IMOV_CD_IMOVEL) 
			REFERENCES IMOVEL(IMOV_CD_IMOVEL) ON DELETE CASCADE;

ALTER TABLE IMOVEL_CONTATO_THREAD
	ADD CONSTRAINT FK_IMCT_IMCA
		FOREIGN KEY (IMCA_CD_IMOVEL_CONTATO_ANU) 
			REFERENCES IMOVEL_CONTATO_ANUNCIANTE(IMCA_CD_IMOVEL_CONTATO_ANU) ON DELETE CASCADE;

ALTER TABLE OFERTA
	ADD CONSTRAINT FK_OFER_IMOV
		FOREIGN KEY (IMOV_CD_IMOVEL) 
			REFERENCES IMOVEL(IMOV_CD_IMOVEL) ON DELETE CASCADE;

ALTER TABLE OFERTA_ITEM
	ADD CONSTRAINT FK_OFIT_OFER
		FOREIGN KEY (OFER_ID) 
			REFERENCES OFERTA(OFERTA_ID) ON DELETE CASCADE;

ALTER TABLE OFERTA_REGULAMENTO
	ADD CONSTRAINT FK_OFRE_OFER
		FOREIGN KEY (OFER_ID) 
			REFERENCES OFERTA(OFERTA_ID) ON DELETE CASCADE;

			
/******************************************************************/
/* GENERATORS                                                     */
/******************************************************************/
CREATE GENERATOR SEQ_IMPF_CD_IMOVEL_PLANO_FATURA;
SET    GENERATOR SEQ_IMPF_CD_IMOVEL_PLANO_FATURA  TO 1;

CREATE GENERATOR SEQ_IMVI_CD_IMOVEL_VISITAS;
SET    GENERATOR SEQ_IMVI_CD_IMOVEL_VISITAS  TO 1;

CREATE GENERATOR SEQ_IMCA_CD_IMOVEL_CONTATO_ANU;
SET    GENERATOR SEQ_IMCA_CD_IMOVEL_CONTATO_ANU TO 1;

CREATE GENERATOR SEQ_IMIV_NR_ORDEM;
SET    GENERATOR SEQ_IMIV_NR_ORDEM TO 1;

CREATE GENERATOR SEQ_IMIV_CD_IMOVEL_IMAGEM_VIDE;
SET    GENERATOR SEQ_IMIV_CD_IMOVEL_IMAGEM_VIDE TO 1;

CREATE GENERATOR SEQ_PUBL_CD_PUBLICIDADE;
SET    GENERATOR SEQ_PUBL_CD_PUBLICIDADE TO 1;

CREATE GENERATOR SEQ_CARA_CD_CARACTERISTICA;
SET    GENERATOR SEQ_CARA_CD_CARACTERISTICA TO 100;

CREATE GENERATOR SEQ_CIDA_CD_CIDADE;
SET    GENERATOR SEQ_CIDA_CD_CIDADE  TO 6000;

CREATE GENERATOR SEQ_CLIE_CD_CLIENTE;
SET    GENERATOR SEQ_CLIE_CD_CLIENTE  TO 1;

CREATE GENERATOR SEQ_DEPO_CD_DEPOIMENTO;
SET    GENERATOR SEQ_DEPO_CD_DEPOIMENTO  TO 1;

CREATE GENERATOR SEQ_IMCA_CD_CARACTERISTICA;
SET    GENERATOR SEQ_IMCA_CD_CARACTERISTICA  TO 1;

CREATE GENERATOR SEQ_IMOV_CD_IMOVEL;
SET    GENERATOR SEQ_IMOV_CD_IMOVEL  TO 1;

CREATE GENERATOR SEQ_IMPL_CD_IMOVEL_PLANO;
SET    GENERATOR SEQ_IMPL_CD_IMOVEL_PLANO  TO 1;

CREATE GENERATOR SEQ_PEIN_CD_PERIODO_INDISPONIB;
SET    GENERATOR SEQ_PEIN_CD_PERIODO_INDISPONIB TO 1;

CREATE GENERATOR SEQ_PLAN_CD_PLANO;
SET    GENERATOR SEQ_PLAN_CD_PLANO  TO 10;

CREATE GENERATOR SEQ_TELE_CD_TELEFONE;
SET    GENERATOR SEQ_TELE_CD_TELEFONE  TO 1;

CREATE GENERATOR SEQ_UFCI_CD_UF_CIDADE_ITEM;
SET    GENERATOR SEQ_UFCI_CD_UF_CIDADE_ITEM  TO 6000;

CREATE GENERATOR SEQ_TABELA_PRECO;
SET    GENERATOR SEQ_TABELA_PRECO TO 1;﻿

CREATE GENERATOR SEQ_ASSI_CD_ASSINANTE;
SET    GENERATOR SEQ_ASSI_CD_ASSINANTE  TO 1;

CREATE GENERATOR SEQ_CHAT_CD_CHAT;
SET    GENERATOR SEQ_CHAT_CD_CHAT  TO 10000;

CREATE GENERATOR SEQ_LOUC_CD_LOCALUFCIDADE_ITEM;
SET    GENERATOR SEQ_LOUC_CD_LOCALUFCIDADE_ITEM  TO 1;

CREATE GENERATOR SEQ_CLAS_CD_CLASSIFICACAO;
SET    GENERATOR SEQ_CLAS_CD_CLASSIFICACAO  TO 1;

CREATE GENERATOR SEQ_LOCA_CD_LOCAL;
SET    GENERATOR SEQ_LOCA_CD_LOCAL  TO 1;

CREATE GENERATOR SEQ_LOCI_CD_LOCAL_CLASSIF_ITEM;
SET    GENERATOR SEQ_LOCI_CD_LOCAL_CLASSIF_ITEM  TO 1;

CREATE GENERATOR SEQ_RESE_CD_RESERVA;
SET    GENERATOR SEQ_RESE_CD_RESERVA  TO 1;

CREATE GENERATOR SEQ_LOTE_CD_CLIENTE;
SET    GENERATOR SEQ_LOTE_CD_CLIENTE  TO 1;

CREATE GENERATOR SEQ_MOPV_CD_MODO_PUBL_VISITAS;
SET    GENERATOR SEQ_MOPV_CD_MODO_PUBL_VISITAS  TO 1;

CREATE GENERATOR SEQ_FISM_ID;
SET GENERATOR SEQ_FISM_ID TO 1000;

CREATE GENERATOR SEQ_TIAL_ID;
SET GENERATOR SEQ_TIAL_ID TO 1000;

CREATE GENERATOR SEQ_ALER_ID;
SET GENERATOR SEQ_ALER_ID TO 1;

CREATE GENERATOR SEQ_OFERECIMENTO;
SET GENERATOR SEQ_OFERECIMENTO TO 5000;

CREATE GENERATOR SEQ_IMCT_ID ;
SET    GENERATOR SEQ_IMCT_ID TO 1000;

CREATE GENERATOR SEQ_OFIT_ID;
SET    GENERATOR SEQ_OFIT_ID TO 1000;

CREATE GENERATOR SEQ_OFER_ID;
SET    GENERATOR SEQ_OFER_ID TO 1000;

CREATE GENERATOR SEQ_OFRE_ID;
SET    GENERATOR SEQ_OFRE_ID TO 1000;
