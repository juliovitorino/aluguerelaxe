insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('CELULAR_ADMIN_GATEWAY','Celular administrador AlugueRelaxe no formato SMS Gateway','2498340040');
insert into VARIAVEL (VARI_CD_VARIAVEL,VARI_TX_DESCRICAO,VARI_TX_VALOR_CONTEUDO) VALUES ('PERMITIR_ENVIO_NOTIFICACAO_ADMIN_SMS','Envia copia da notificacao SMS para Administrador','OFF');
commit work;

INSERT INTO MENSAGEM VALUES ('MSG-0097', 'Voce tem ${1} contatos com anunciantes aguardando sua aprovacao. Verifique seu email. :-D #AlugueRelaxe');
commit work;
