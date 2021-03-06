insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('LINK_PRE_RESERVA','Link pr?-reserva','http://www.aluguerelaxe.com.br/arweb/prereserva.svlt?token=${token}&action=${action}');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('TELEFONES_CENTRAL_RESERVA','Telefones da Central de Reserva','(37)8412-7817 / (41)9667-5276 / (11)96290-0040 / (24)9275-3073');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PATH_CONTRATO_LOCATARIO','Path completo do contrato com locat?rio','/home/aluguerelaxe/www/contratos/');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('LINK_CONTRATO_LOCATARIO','Url do contrato com locat?rio','http://www.aluguerelaxe.com.br/contratos/');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PERC_COMISSAO_PADRAO_TEMPORADA','Percentual de Comiss?o Padr?o Temporada','0.15');

INSERT INTO MENSAGEM VALUES ('MSG-0046', 'N?o ? permitido a digita??o de e-mail dentro do campo ${1}.');
INSERT INTO MENSAGEM VALUES ('MSG-0047', 'N?o ? permitido a digita??o de url dentro do campo ${1}.');
INSERT INTO MENSAGEM VALUES ('MSG-0048', 'N?o ? permitido a digita??o de telefone dentro do campo ${1}.');
INSERT INTO MENSAGEM VALUES ('MSG-0049', 'Pr?-reserva de im?vel de temporada realizada com sucesso.');
INSERT INTO MENSAGEM VALUES ('MSG-0050', 'Pr?-reserva indicada j? foi validada anteriormente');
INSERT INTO MENSAGEM VALUES ('MSG-0051', 'Valida??o da Pr?-reserva realizada com sucesso');
INSERT INTO MENSAGEM VALUES ('MSG-0052', 'Informa??es de pagamento do token ${1} j? foram validadas. Token Inv?lido.');
INSERT INTO MENSAGEM VALUES ('MSG-0053', 'Confirma??o de pagamento realizada com sucesso');
INSERT INTO MENSAGEM VALUES ('MSG-0054', 'Pr?-reserva ${1} cancelada com sucesso');
INSERT INTO MENSAGEM VALUES ('MSG-0055', 'Reserva ${1} foi cancelada em ${2}');
INSERT INTO MENSAGEM VALUES ('MSG-0056', 'Pagamento da reserva foi confirmada em ${1}');
INSERT INTO MENSAGEM VALUES ('MSG-0057', 'A pr?-reserva ${1} ainda n?o foi confirmada. O Comando n?o foi executado.');
INSERT INTO MENSAGEM VALUES ('MSG-0058', 'A identifica??o do solicitante #${1} ? inv?lida. O Comando n?o foi executado.');
INSERT INTO MENSAGEM VALUES ('MSG-0059', 'As informa??es de Banco, Ag?ncia e Conta Corrente do solicitante #${1} est? imcompleta. Por favor, verifique seu cadastro.');
INSERT INTO MENSAGEM VALUES ('MSG-0060', 'A libera??o de pagamento para o voucher #${1} j? foi solicitada em ${2}.');
INSERT INTO MENSAGEM VALUES ('MSG-0061', 'A libera??o de pagamento foi processada com sucesso. Em at? 24 horas o AlugueRelaxe estar? providenciando a transfer?ncia do dep?sito em conta corrente.');
INSERT INTO MENSAGEM VALUES ('MSG-0062', 'Voucher inv?lido. O comando n?o foi executado.');
INSERT INTO MENSAGEM VALUES ('MSG-0063', 'C?digo de tracking inv?lido. O comando n?o foi executado.');
INSERT INTO MENSAGEM VALUES ('MSG-0064', 'O pagamento da reserva n?o foi confirmado at? o momento. O comando n?o foi executado.');
INSERT INTO MENSAGEM VALUES ('MSG-0065', 'O pedido de libera??o de pagamento s? est? autorizado a partir do dia ${1}.');
INSERT INTO MENSAGEM VALUES ('MSG-0066', 'A fase de pr?-reserva n?o est? conclu?da.');
INSERT INTO MENSAGEM VALUES ('MSG-0067', 'A fase de aprova??o n?o est? conclu?da.');
INSERT INTO MENSAGEM VALUES ('MSG-0068', 'A fase de confirma??o do dep?sito n?o est? conclu?da.');
INSERT INTO MENSAGEM VALUES ('MSG-0069', 'A fase de Libera??o do dep?sito n?o est? conclu?da.');
INSERT INTO MENSAGEM VALUES ('MSG-0070', 'A fase de transfer?ncia do dep?sito n?o est? conclu?da.');
INSERT INTO MENSAGEM VALUES ('MSG-0071', 'Transfer?ncia de dep?sito Tracker# ${1} realizado com sucesso.');
INSERT INTO MENSAGEM VALUES ('MSG-0072', 'A Transfer?ncia de dep?sito Tracker# ${1} j? foi realizada em ${2}. Comando n?o executado.');
INSERT INTO MENSAGEM VALUES ('MSG-0073', 'Erro no preenchimento do formul?rio de reserva. Comando n?o executado.');
INSERT INTO MENSAGEM VALUES ('MSG-0074', 'Para a forma de pagamento ${2} era esperado valor previsto de dep?sito R$ ${1}.');
INSERT INTO MENSAGEM VALUES ('MSG-0075', 'Campo de valor est? com valor negativo ${1}. Verifique.');
commit work;
