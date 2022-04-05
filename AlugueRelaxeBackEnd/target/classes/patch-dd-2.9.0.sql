insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('LIBERA_ALERTA24H_IMOVEL_GRATUITO','Libera o alerta 24H para ser enviado aos imoveis com plano gratuito','ON');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('URL_IMAGEM_CHAT','Imagem padrão para Chat','/stream/ar/picture_jcv_128x128.jpg');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PERMITIR_ENVIO_EMAILS','Chave de ativação para permissão de envio de e-mails','ON');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('OFERECIMENTO_PLANOS_GRATUITOS','Chave de ativação para oferecimento para planos gratuitos','ON');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('SMTP_PORT_EXTRANET','Porta Servico SMTP extranet provedor','587');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('LINK_OFERECIMENTO','Link de Oferecimento','http://www.aluguerelaxe.com.br/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id=${id}&o=EM');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('CREDENCIAL_MOBILE_PRONTO','Credencial serviço SMS Gateway Mobile Pronto','A8FF4013F4D09B79D88A03269EEB435ED980032A');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PROJETO_MOBILE_PRONTO','Nome do Projeto (MAIN_USER) serviço SMS Gateway Mobile Pronto','ALUGUERELAXE');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('AUX_USER_MOBILE_PRONTO','Usuário (AUX_USER) serviço SMS Gateway Mobile Pronto','JULIO');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('URL_CONNECTION_MOBILE_PRONTO','URL de conexão do serviço SMS Gateway Mobile Pronto','https://www.mpgateway.com/v_2_00/smspush/enviasms.aspx?CREDENCIAL=${CREDENCIAL}&PRINCIPAL_USER=${PRINCIPAL_USER}&AUX_USER=${AUX_USER}&MOBILE=${MOBILE}&SEND_PROJECT=${SEND_PROJECT}&MESSAGE=${MESSAGE}');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('FACTORY_GATEWAY_SMS_ATIVO_EMPRESA','Serviço de SMS Gateway ','Mobile Pronto');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('FACTORY_GATEWAY_SMS_ATIVO','Implementacao do serviço de SMS Gateway ','0');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PERMITIR_ENVIO_EMAILS_CONTA_TESTE','Chave de ativação de envio de e-mail para conta de teste','OFF');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('EMAIL_CONTA_TESTE','Email da conta de teste','julio.vitorino@ig.com.br');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('EMAIL_CONTA_TESTE_NOME','Email da conta de teste nome do proprietário','Julio Vitorino');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PERMITIR_ENVIO_SMS_GATEWAY','Chave de ativação para permissão de envio de SMS usando gateway','ON');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PERMITIR_ENVIO_SMS_MODEM_3G','Chave de ativação para permissão de envio de SMS usando modem 3G','ON');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('CREDENCIAL_SMSEMPRESA','Credencial serviço SMS Gateway SMSEmpresa','630RJ4BY3RO8ZGYL');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('URL_CONNECTION_SMSEMPRESA','URL de conexão do serviço SMS Gateway SMSEmpresa','http://api.smsempresa.com.br/send?key=${CREDENCIAL}&msg=${MESSAGE}&type=0&number=${MOBILE}&encode=0');
commit work;

/* Tipo de alerta 24h */
insert into TIPO_ALERTA24H (TIAL_ID,TIAL_NM_TIPO_ALERTA,TIAL_NM_WORKER) values (1,'SMS','br.com.jcv.backend.workers.Alerta24HSenderSMS');
insert into TIPO_ALERTA24H (TIAL_ID,TIAL_NM_TIPO_ALERTA,TIAL_NM_WORKER) values (2,'Chat','br.com.jcv.backend.workers.Alerta24HSenderChat');
insert into TIPO_ALERTA24H (TIAL_ID,TIAL_NM_TIPO_ALERTA,TIAL_NM_WORKER) values (3,'Email','br.com.jcv.backend.workers.Alerta24HSenderEmail');
insert into TIPO_ALERTA24H (TIAL_ID,TIAL_NM_TIPO_ALERTA,TIAL_NM_WORKER) values (4,'Gateway SMS','br.com.jcv.backend.workers.Alerta24HSenderGatewaySMS');
commit work;

INSERT INTO MENSAGEM VALUES ('MSG-0095', 'Alerta24H:${4} quer alugar no imovel ${1}-${2}.Detalhes email ${3}.#Aluguerelaxe.');
INSERT INTO MENSAGEM VALUES ('MSG-0096', 'O Visitante ${3} em ${8}, visitou um imóvel em ${6} / ${5}. Ofereça seu imóvel entrando em contato no telefone ${2} ou pelo e-mail ${1}.');
COMMIT WORK;
