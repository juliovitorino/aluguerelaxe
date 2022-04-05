INSERT INTO PROMOCAO_TICKET(PRTI_NM_PROMOCAO,PRTI_NR_TICKET) VALUES ('PROMOÇÃO DE PRÊMIOS ALUGUERELAXE', 0);
COMMIT WORK;

insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('TEMPLATE_PROMOCAO','Identificador do template promocional','30');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('INDICADOR_SUPER_BANNER_PP','Indicador uso super banner em PP','ON');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('INDICADOR_SUPER_BANNER_DD','Indicador uso super banner em DD','ON');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_FOTOS','Pontuação negativa para imóvel sem fotos','3000');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_TAB_PRECO','Pontuação negativa para imóvel sem tabela de preços ','1');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_UPD_3_MESES','Pontuação negativa para imóvel sem atualizacao','1');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_UPD_FOTOS','Pontuação negativa para imóvel sem atualização fotos','5');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_MENOS_10_FOTOS','Pontuação negativa para imovel com menos de 10 fotos','1');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_VIDEO','Pontuação negativa para imóvel sem vídeo','3');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_BAIXA_DESCRICAO','Pontuação negativa para imóvel com descrição baixa','1');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_MAPA','Pontuação negativa para imóvel sem mapa','5');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_NAO_VERIFICADO','Pontuação negativa para imóvel não verificado','3000');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_CARACTERISTICAS','Pontuação negativa para imóvel sem características','1');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_IMOVEL_SEM_TELEFONE','Pontuação negativa para imóvel sem telefone','1');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PNEG_SOMATORIO','Somatorio de Pontuação negativa','25');
commit work;

insert into PLANO (PLAN_CD_PLANO,PLAN_NM_PLANO,PLAN_TX_DESCRICAO,PLAN_VL_PLANO,PLAN_VL_DESCONTO,PLAN_IN_STATUS,PLAN_IN_TIPO_PERIODICIDADE,PLAN_NR_DIAS,PLAN_DT_ATIVACAO,PLAN_IN_TIPO_COMPRA) VALUES (4,'PUBLICIDADE SUPER BANNER', 'PUBLICIDADE SUPER BANNER',5.90,0.00,'A','P',1,CURRENT_TIMESTAMP,'S');
commit work;

INSERT INTO MENSAGEM VALUES ('MSG-0083', 'O plano ${1} não está mais disponivel para comercialização. Por favor, use a opção migrar para outro plano no menu FINANCEIRO >> MIGRAR PLANO');
INSERT INTO MENSAGEM VALUES ('MSG-0084', 'Anuncio dentro do prazo de veiculação. A data de vencimento do anúncio será em ${1}.');
INSERT INTO MENSAGEM VALUES ('MSG-0085', 'Fatura #${1} do anuncio encontra-se quitada em ${2}.');
INSERT INTO MENSAGEM VALUES ('MSG-0086', 'O pagamento da fatura está PENDENTE e a data de vencimento venceu em ${1}.');
INSERT INTO MENSAGEM VALUES ('MSG-0087', 'Não é permitido renovação anuncio de plano gratuito vencida. Por favor, use a opção migrar para outro plano no menu FINANCEIRO >> MIGRAR PLANO.');
INSERT INTO MENSAGEM VALUES ('MSG-0088', 'O pagamento da fatura está PENDENTE e aguardando pagamento até ${1} para liberar veiculação do anúncio.');
commit work;

UPDATE VARIAVEL 
SET VARI_TX_DESCRICAO = 'Promoção do AlugueRelaxe EU QUERO!',
    VARI_TX_VALOR_CONTEUDO = 'PROMOÇÃO DE PRÊMIOS ALUGUERELAXE'
where 	VARI_CD_VARIAVEL = 'PROMOCAO_ATIVA';
COMMIT WORK;

UPDATE VARIAVEL 
SET VARI_TX_VALOR_CONTEUDO = '1a Quarta-Feira após completar 2000 Curtir Facebook - Loterial Federal'
where 	VARI_CD_VARIAVEL = 'PROMOCAO_ATIVA_DATA_SORTEIO';
COMMIT WORK;


UPDATE VARIAVEL 
SET VARI_TX_DESCRICAO = 'Tempo máximo em dias de pendência de confirmação',
    VARI_TX_VALOR_CONTEUDO = '3'
where 	VARI_CD_VARIAVEL = 'TEMPO_CONTA_NOVA_PENDENTE';
COMMIT WORK;
