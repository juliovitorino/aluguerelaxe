INSERT INTO PROMOCAO_TICKET(PRTI_NM_PROMOCAO,PRTI_NR_TICKET) VALUES ('PROMO��O DE PR�MIOS ALUGUERELAXE', 0);
COMMIT WORK;

insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('TEMPLATE_PROMOCAO','Identificador do template promocional','30');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('INDICADOR_SUPER_BANNER_PP','Indicador uso super banner em PP','ON');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('INDICADOR_SUPER_BANNER_DD','Indicador uso super banner em DD','ON');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_FOTOS','Pontua��o negativa para im�vel sem fotos','3000');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_TAB_PRECO','Pontua��o negativa para im�vel sem tabela de pre�os ','1');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_UPD_3_MESES','Pontua��o negativa para im�vel sem atualizacao','1');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_UPD_FOTOS','Pontua��o negativa para im�vel sem atualiza��o fotos','5');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_MENOS_10_FOTOS','Pontua��o negativa para imovel com menos de 10 fotos','1');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_VIDEO','Pontua��o negativa para im�vel sem v�deo','3');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_BAIXA_DESCRICAO','Pontua��o negativa para im�vel com descri��o baixa','1');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_MAPA','Pontua��o negativa para im�vel sem mapa','5');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_NAO_VERIFICADO','Pontua��o negativa para im�vel n�o verificado','3000');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_CARACTERISTICAS','Pontua��o negativa para im�vel sem caracter�sticas','1');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_TELEFONE','Pontua��o negativa para im�vel sem telefone','1');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_SOMATORIO','Somatorio de Pontua��o negativa','25');
commit work;

insert into PLANO (PLAN_CD_PLANO,PLAN_NM_PLANO,PLAN_TX_DESCRICAO,PLAN_VL_PLANO,PLAN_VL_DESCONTO,PLAN_IN_STATUS,PLAN_IN_TIPO_PERIODICIDADE,PLAN_NR_DIAS,PLAN_DT_ATIVACAO,PLAN_IN_TIPO_COMPRA) VALUES (4,'PUBLICIDADE SUPER BANNER', 'PUBLICIDADE SUPER BANNER',5.90,0.00,'A','P',1,CURRENT_TIMESTAMP,'S');
commit work;

INSERT INTO MENSAGEM VALUES ('MSG-0083', 'O plano ${1} n�o est� mais disponivel para comercializa��o. Por favor, use a op��o migrar para outro plano no menu FINANCEIRO >> MIGRAR PLANO');
INSERT INTO MENSAGEM VALUES ('MSG-0084', 'Anuncio dentro do prazo de veicula��o. A data de vencimento do an�ncio ser� em ${1}.');
INSERT INTO MENSAGEM VALUES ('MSG-0085', 'Fatura #${1} do anuncio encontra-se quitada em ${2}.');
INSERT INTO MENSAGEM VALUES ('MSG-0086', 'O pagamento da fatura est� PENDENTE e a data de vencimento venceu em ${1}.');
INSERT INTO MENSAGEM VALUES ('MSG-0087', 'N�o � permitido renova��o anuncio de plano gratuito vencida. Por favor, use a op��o migrar para outro plano no menu FINANCEIRO >> MIGRAR PLANO.');
INSERT INTO MENSAGEM VALUES ('MSG-0088', 'O pagamento da fatura est� PENDENTE e aguardando pagamento at� ${1} para liberar veicula��o do an�ncio.');
commit work;

UPDATE VARIAVEL 
SET VARI_TX_DESCRICAO = 'Promo��o do AlugueRelaxe EU QUERO!',
    VARI_TX_VALOR_CONTEUDO = 'PROMO��O DE PR�MIOS ALUGUERELAXE'
where 	VARI_CD_VARIAVEL = 'PROMOCAO_ATIVA';
COMMIT WORK;

UPDATE VARIAVEL 
SET VARI_TX_VALOR_CONTEUDO = '1a Quarta-Feira ap�s completar 2000 Curtir Facebook - Loterial Federal'
where 	VARI_CD_VARIAVEL = 'PROMOCAO_ATIVA_DATA_SORTEIO';
COMMIT WORK;


UPDATE VARIAVEL 
SET VARI_TX_DESCRICAO = 'Tempo m�ximo em dias de pend�ncia de confirma��o',
    VARI_TX_VALOR_CONTEUDO = '3'
where 	VARI_CD_VARIAVEL = 'TEMPO_CONTA_NOVA_PENDENTE';
COMMIT WORK;
