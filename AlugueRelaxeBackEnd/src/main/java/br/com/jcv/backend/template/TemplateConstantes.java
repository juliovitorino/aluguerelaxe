package br.com.jcv.backend.template;


public class TemplateConstantes {
	
	// Identificadores para TemplateFactory
	public static final int TEMPLATE_CADASTRO_CLIENTE_IMCOMPLETO = 1;
	public static final int TEMPLATE_CONTATO_ANUNCIANTE_PENDENTE = 2;
	public static final int TEMPLATE_CONTATO_ANUNCIANTE_LIBERADO = 3;
	public static final int TEMPLATE_ATIVAR_NOVA_CONTA = 4;
	public static final int TEMPLATE_CONTA_ATIVADA = 5;
	public static final int TEMPLATE_CONTEUDO_DEPOIMENTO = 6;
	public static final int TEMPLATE_CONTEUDO_UPLOAD = 7;
	public static final int TEMPLATE_GALERIA_FOTOS_VAZIA = 8;
	public static final int TEMPLATE_INDICAR_AMIGO = 9;
	public static final int TEMPLATE_FALE_CONOSCO = 10;
	public static final int TEMPLATE_NOVO_CODIGO_ACESSO = 11;
	public static final int TEMPLATE_COMUNICADO_SORTEIO = 12;
	public static final int TEMPLATE_AVALIACAO_IMOVEL = 13;
	public static final int TEMPLATE_ASSINANTES = 14;
	public static final int TEMPLATE_AMIGO_INDICA_AMIGO = 15;
	public static final int TEMPLATE_PROMOCAO_ATIVADA_SUCESSO = 16;
	public static final int TEMPLATE_IMOVEL_PENDENTE_PGTO = 17;
	public static final int TEMPLATE_NOTIFICACAO_ALUGUERELAXE = 18;
	public static final int TEMPLATE_CONTATO_ANUNCIANTE_LIBERADO_SIMPLES = 19;
	public static final int TEMPLATE_VALIDAR_PRE_RESERVA = 20;
	public static final int TEMPLATE_PRE_RESERVA_APROVADA = 21;
	public static final int TEMPLATE_CTR_LOCACAO_TEMPORADA_LOCADOR = 22;
	public static final int TEMPLATE_CTR_LOCACAO_TEMPORADA_LOCATARIO = 23;
	public static final int TEMPLATE_CONFIRMACAO_PGTO_RESERVA = 24;
	public static final int TEMPLATE_VOUCHER = 25;
	public static final int TEMPLATE_CTR_LOCACAO_TEMPORADA_LOCADOR_SEM_CAUCAO = 26;
	public static final int TEMPLATE_INSTRUCOES_CAPTAR_AUTORIZACAO = 27;
	public static final int TEMPLATE_EXECUTAR_TRANSFERENCIA_DEPOSITO = 28;
	public static final int TEMPLATE_DEPOSITO_TRANSFERIDO = 29;
	public static final int TEMPLATE_PROMOCAO_PADRAO = 30;
	public static final int TEMPLATE_DESKTOP = 31;
	public static final int TEMPLATE_NOTIFICACAO_ANUNCIOS_A_VENCER = 32;
	public static final int TEMPLATE_NOTIFICA_PLANO_RENOVADO = 33;
	public static final int TEMPLATE_NOTIFICA_PLANO_BLOQUEADO = 34;
	public static final int TEMPLATE_NOTIFICA_PLANO_RENOVADO_PENDENTE = 35;
	public static final int TEMPLATE_NOTIFICACAO_INATIVACAO = 36;
	public static final int TEMPLATE_ALERTA_24H = 37;
	public static final int TEMPLATE_OFERECIMENTO = 38;
	public static final int TEMPLATE_THREAD_CRIADA = 39;
	public static final int TEMPLATE_THREAD_ANUNCIANTE = 40;
	public static final int TEMPLATE_THREAD_PENDENCIA_APROVACAO = 41;
	public static final int TEMPLATE_THREAD_EDITADA = 42;
	public static final int TEMPLATE_THREAD_VISITANTE = 43;
	public static final int TEMPLATE_THREAD_ANUNCIANTE_RESPOSTA = 44;	
	public static final int TEMPLATE_THREAD_VISITANTE_RESPOSTA = 45;
	public static final int TEMPLATE_PGTO_PAGSEGURO = 46;
	public static final int TEMPLATE_VALIDAR_PRE_RESERVA_VISITANTE = 47;
	public static final int TEMPLATE_THREAD_BOAS_VINDAS = 48;
	public static final int TEMPLATE_THREAD_COMENTARIO_ANUNCIANTE = 49;
	public static final int TEMPLATE_ORIENTACOES_RESGATE_RESERVA = 50;
	public static final int TEMPLATE_AVALIACAO_RESERVA = 51;
	public static final int TEMPLATE_OFERTA_PUBLICIDADE_PP = 52;
	public static final int TEMPLATE_OFERTA_PUBLICIDADE_PD = 53;
	//public static final int TEMPLATE_FICHA_IMOVEL_MOBILE = 54;
	//public static final int TEMPLATE_FICHA_IMOVEL_WWW = 55;
	public static final int TEMPLATE_PROPERTA_INDEX = 56;
	public static final int TEMPLATE_PROPERTA_FICHA_IMOVEL = 57;
	public static final int TEMPLATE_PROPERTA_PROPRIEDADES_CLIENTE = 58;
	public static final int TEMPLATE_PROPERTA_HEADER_TOP = 59;
	public static final int TEMPLATE_PROPERTA_FOOTER_WRAPPER = 60;
	public static final int TEMPLATE_PROPERTA_FOOTER_JS = 61;
	public static final int TEMPLATE_PROPERTA_SECAO_HEAD = 62;
	public static final int TEMPLATE_PROPERTA_PAGINACAO = 63;
	public static final int TEMPLATE_REDIRECT = 64;
	public static final int TEMPLATE_ATALHO_FICHA_IMOVEL = 65;
	
	
	//Tags Comuns
	public static final String TAGC_ANUNCIO_INFOWINDOW = "${ANUNCIO_INFOWINDOW}";
	public static final String TAGC_LATITUDE = "${LATITUDE}";
	public static final String TAGC_LONGITUDE = "${LONGITUDE}";
	public static final String TAGC_FOTOS = "${FOTOS}";
	public static final String TAGC_FOTOS_SLIDER = "${FOTOS_SLIDE}";
	public static final String TAGC_URL_FOTO_CLIENTE = "${URL_FOTO_CLIENTE}";
	public static final String TAGC_NOME_CLIENTE = "${NOME_CLIENTE}";
	public static final String TAGC_EMAIL_CLIENTE = "${EMAIL_CLIENTE}";
	public static final String TAGC_ID_IMOVEL = "${ID_IMOVEL}";
	public static final String TAGC_TITULO_ANUNCIO = "${TITULO_ANUNCIO}";
	public static final String TAGC_DATA_VENCIMENTO_ANUNCIO = "${DATA_VENCIMENTO_ANUNCIO}";
	public static final String TAGC_CD_IMOVEL_PLANO_FATURA = "${CD_IMOVEL_PLANO_FATURA}";
	public static final String TAGC_DESCRICAO_PLANO = "${DESCRICAO_PLANO}";
	public static final String TAGC_DESCRICAO_DO_PLANO = "${DESCRICAO_DO_PLANO}";
	public static final String TAGC_CODIGO_PLANO = "${CODIGO_PLANO}";
	public static final String TAGC_VALOR_PLANO = "${VALOR_PLANO}";
	public static final String TAGC_VALOR_TOTAL = "${VALOR_TOTAL}";
	public static final String TAGC_DATA_CADASTRO = "${DATA_CADASTRO}";
	public static final String TAGC_DATA_PAGAMENTO = "${DATA_PAGAMENTO}";
	public static final String TAGC_TELEFONES_CENTRAL_RESERVA = "${TELEFONES_CENTRAL_RESERVA}";
	public static final String TAGC_NOME_ANUNCIANTE = "${NOME_ANUNCIANTE}";
	public static final String TAGC_DATA_VISITA = "${DATA_VISITA}";
	public static final String TAGC_CIDADE = "${CIDADE}";
	public static final String TAGC_UF = "${UF}";
	public static final String TAGC_TITULO_IMOVEL_VISITADO = "${TITULO_IMOVEL_VISITADO}";
	public static final String TAGC_NOME_VISITANTE = "${NOME_VISITANTE}";
	public static final String TAGC_TITULO_IMOVEL_ANUNCIANTE = "${TITULO_IMOVEL_ANUNCIANTE}";
	public static final String TAGC_EMAIL_VISITANTE = "${EMAIL_VISITANTE}";
	public static final String TAGC_TELEFONE_VISITANTE = "${TELEFONE_VISITANTE}";
	public static final String TAGC_CARACTERISTICAS_IMOVEL = "${CARACTERISTICAS_IMOVEL}";
	public static final String TAGC_DESCRICAO_GERAL_IMOVEL = "${DESCRICAO_GERAL_IMOVEL}";
	public static final String TAGC_IMAGEM_IMOVEL = "${IMAGEM_IMOVEL}";
	public static final String TAGC_LINK_IMOVEL = "${LINK_IMOVEL}";
	public static final String TAGC_TITULO_IMOVEL_ALERTA24H = "${TITULO_IMOVEL_ALERTA24H}";
	public static final String TAGC_THREAD = "${THREAD}";
	public static final String TAGC_THREAD_ID = "${THREAD_ID}";
	public static final String TAGC_ID_FATURA = "${ID_FATURA}";
	public static final String TAGC_DATA_DUVIDA = "${DATA_DUVIDA}";
	public static final String TAGC_LINK = "${LINK}";
	public static final String TAGC_LINK_EDITADO = "${LINK_EDITADO}";
	public static final String TAGC_THREAD_EDITADA = "${THREAD_EDITADA}";
	public static final String TAGC_NOME = "${NOME}";
	public static final String TAGC_LINK_ACOMPANHAMENTO_PORTAL = "${LINK_ACOMPANHAMENTO_PORTAL}"; 
	public static final String TAGC_LINK_APROVAR = "${LINK_APROVAR}";
	public static final String TAGC_LINK_APROVAR_EDITADO = "${LINK_APROVAR_EDITADO}";
	public static final String TAGC_EMAIL_ALUGUE_RELAXE = "${EMAIL_ALUGUE_RELAXE}";
	public static final String TAGC_TIPO_PUBLICIDADE = "${TIPO_PUBLICIDADE}";
	public static final String TAGC_DATA_INICIO_PUBLICIDADE = "${DATA_INICIO_PUBLICIDADE}";
	public static final String TAGC_DATA_FIM_PUBLICIDADE = "${DATA_FIM_PUBLICIDADE}";
	public static final String TAGC_DATA_LIMITE = "${DATA_LIMITE}";
	public static final String TAGC_BOTAO_PAGSEGURO = "${BOTAO_PAGSEGURO}";
	public static final String TAGC_DIAS = "${DIAS}";
	public static final String TAGC_HOME = "${HOME}";
	public static final String TAGC_BANNER_FICHA = "${BANNER_FICHA}";
	public static final String TAGC_PROPERTA_IMOVEL_PP_ITEM = "${PROPERTA_IMOVEL_PP_ITEM}";
	public static final String TAGC_PROPERTA_IMOVEL_PD_ITEM = "${PROPERTA_IMOVEL_PD_ITEM}";
	public static final String TAGC_TITULO_HTML = "${TITULO_HTML}";
	public static final String TAGC_BANHEIROS = "${BANHEIROS}";
	public static final String TAGC_QUARTOS = "${QUARTOS}";
	public static final String TAGC_SUITES = "${SUITES}";
	public static final String TAGC_CAPACIDADE_MAX = "${CAPACIDADE_MAX}";
	public static final String TAGC_VALOR_DIARIA = "${VALOR_DIARIA}";
	public static final String TAGC_MOEDA = "${MOEDA}";
	public static final String TAGC_OBJETIVO_IMOVEL = "${OBJETIVO_IMOVEL}";
	public static final String TAGC_LANGUAGE = "${LANGUAGE}";
	public static final String TAGC_PROPERTA_ZOOM = "${PROPERTA_ZOOM}";
	public static final String TAGC_PROPERTA_LATI_CENTER = "${PROPERTA_LATI_CENTER}";
	public static final String TAGC_PROPERTA_LONG_CENTER = "${PROPERTA_LONG_CENTER}";
	public static final String TAGC_PROPERTA_LAT_LONG = "${PROPERTA_LAT_LONG}";
	public static final String TAGC_PROPERTA_TIPOS = "${PROPERTA_TIPOS}";
	public static final String TAGC_PROPERTA_INFOBOX_PUSH = "${PROPERTA_INFOBOX_PUSH}";
	public static final String TAGC_HOME_PROPERTA = "${HOME_PROPERTA}";
	public static final String TAGC_DESC_ARREDORES = "${DESC_ARREDORES}";
	public static final String TAGC_DESCRICAO_QUARTOS = "${DESCRICAO_QUARTOS}";
	public static final String TAGC_OBSERVACAO = "${OBSERVACAO}";
	public static final String TAGC_ENDERECO_IMOVEL = "${ENDERECO_IMOVEL}";
	public static final String TAGC_PROPERTA_IMOVEL_RECENTE_ITEM = "${IMOVEIS_RECENTES}";
	public static final String TAGC_URL_HOME_PROPERTA = "${URL_HOME_PROPERTA}";			
	public static final String TAGC_FOTO_PREVIEW = "${FOTO_PREVIEW}";			
	public static final String TAGC_FOTOS_ITEM = "${FOTOS_ITEM}";			
	public static final String TAGC_URL_PROPRIEDADES_CLIENTE = "${URL_PROPRIEDADES_CLIENTE}";
	public static final String TAGC_CARACTERISTICAS = "${CARACTERISTICAS}";
	public static final String TAGC_COPYRIGHT = "${COPYRIGHT}";
	public static final String TAGC_TABELA_PRECO = "${TABELA_PRECO}";
	public static final String TAGC_NUMERO_PROPRIEDADES = "${NUMERO_PROPRIEDADES}";
	public static final String TAGC_TAXA_RESPOSTA = "${TAXA_RESPOSTA}";
	public static final String TAGC_FOTOS_CAROUSEL = "${FOTOS_CAROUSEL}";
	public static final String TAGC_MEMBRO_VERIFICADO = "${MEMBRO_VERIFICADO}";
	public static final String TAGC_TELEFONES_CONTATO = "${TELEFONES_CONTATO}";
	public static final String TAGC_CIDADES_UF = "${CIDADES_UF}";
	public static final String TAGC_URL_GATEWAY_SERVICES = "${URL_GATEWAY_SERVICES}";
	public static final String TAGC_PROPERTA_HEADER_TOP = "${PROPERTA_HEADER_TOP}";
	public static final String TAGC_PROPERTA_FOOTER_WRAPPER = "${PROPERTA_FOOTER_WRAPPER}";
	public static final String TAGC_PROPERTA_FOOTER_JAVASCRIPT = "${PROPERTA_FOOTER_JAVASCRIPT}";
	public static final String TAGC_SECAO_HEAD = "${SECAO_HEAD}";
	public static final String TAGC_COLECAO_PROPRIEDADES = "${COLECAO_PROPRIEDADES}";
	public static final String TAGC_TEMPLATE_REDIRECT = "${TEMPLATE_REDIRECT}";
	public static final String TAGC_URL = "${URL}";
	public static final String TAGC_NAVEGADOR_PAGINAS = "${NAVEGADOR_PAGINAS}";


	// Constantes para I18N dentro do template
	public static final String TAGC_I18N_REGISTRO = "${I18N_REGISTRO}";
	public static final String TAGC_I18N_LOGIN = "${I18N_LOGIN}";
	public static final String TAGC_I18N_CONTATO = "${I18N_CONTATO}";

	
	// TNCA - TemplateNovoCodigoAcesso
	public static final String TNCA_TAG_CODIGO_ACESSO = "${NOVO_CODIGO_ACESSO}";
	
	// TFC - TemplateFaleConosco
	public static final String TFC_TAG_NOME = "${NOME}";
	public static final String TFC_TAG_EMAIL = "${EMAIL}";
	public static final String TFC_TAG_ASSUNTO = "${ASSUNTO}";
	public static final String TFC_TAG_TOPICO = "${TOPICO}";
	public static final String TFC_TAG_MENSAGEM = "${MENSAGEM}";

	// TNGFV - TemplateIndicarImovelAmigo
	public static final String TIIA_TAG_NOME_AMIGO = "${NOME_AMIGO}"; 
	public static final String TIIA_TAG_SEU_AMIGO = "${SEU_AMIGO}"; 
	public static final String TIIA_TAG_ID_IMOVEL = "${ID_IMOVEL}"; 
	public static final String TIIA_TAG_TITULO_IMOVEL = "${TITULO_IMOVEL}"; 
	public static final String TIIA_TAG_LINK_DO_IMOVEL = "${LINK_DO_IMOVEL}"; 
	public static final String TIIA_TAG_MENSAGEM = "${MENSAGEM}"; 

	// TNGFV - TemplateNotiticacaoGaleriaFotosVazia
	public static final String TNGFV_TAG_CLIENTE = "${CLIENTE}"; 
	public static final String TNGFV_TAG_ID_CLIENTE = "${ID_IMOVEL}"; 
	public static final String TNGFV_TAG_TITULO_IMOVEL = "${TITULO_IMOVEL}"; 
	
	// TCCI - TemplateCadastroClienteImcompleto
	public static final String TCCI_TAG_DATA_CADASTRO = "${DATA_CADASTRO_IMCOMPLETO}"; 

	// TCD - TemplateContatoAnunciantePendente
	public static final String TCD_TAG_NOME_PROPOSTO = "${NOME_PROPOSTO}";
	public static final String TCD_TAG_DEPOIMENTO = "${DEPOIMENTO}";
	public static final String TCD_TAG_LINK_LIBERAR_DEPOIMENTO = "${LINK_LIBERAR_DEPOIMENTO}";
	public static final String TCD_TAG_LINK_REPROVAR_DEPOIMENTO = "${LINK_REPROVAR_DEPOIMENTO}";
	
	// TCAP - TemplateContatoAnunciantePendente e TemplateContatoLiberacao
	public static final String TCAP_TAG_DATA_CONTATO = "${DATA_CONTATO}";
	public static final String TCAP_TAG_ID_IMOVEL = "${ID_IMOVEL}";
	public static final String TCAP_TAG_NOME_PROPOSTO = "${NOME_PROPOSTO}";
	public static final String TCAP_TAG_NOME_ANUNCIANTE = "${NOME_ANUNCIANTE}";
	public static final String TCAP_TAG_CIDADE_PROPOSTO = "${CIDADE_PROPOSTO}";
	public static final String TCAP_TAG_UF_PROPOSTO = "${UF_PROPOSTO}";
	public static final String TCAP_TAG_EMAIL_PROPOSTO = "${EMAIL_PROPOSTO}";
	public static final String TCAP_TAG_DDD_PROPOSTO = "${DDD_PROPOSTO}";
	public static final String TCAP_TAG_FONE_PROPOSTO = "${FONE_PROPOSTO}";
	public static final String TCAP_TAG_CHEGADA_PREVISTA_PROPOSTO = "${CHEGADA_PREVISTA_PROPOSTO}";
	public static final String TCAP_TAG_PARTIDA_PREVISTA_PROPOSTO = "${PARTIDA_PREVISTA_PROPOSTO}";
	public static final String TCAP_TAG_ADULTOS_PROPOSTO = "${ADULTOS_PROPOSTO}";
	public static final String TCAP_TAG_CRIANCAS_PROPOSTO = "${CRIANCAS_PROPOSTO}";
	public static final String TCAP_TAG_INFO_ADICIONAL_PROPOSTO = "${INFO_ADICIONAL_PROPOSTO}";
	public static final String TCAP_TAG_LINK_LIBERAR_PARA_CONTATO = "${LINK_LIBERAR_PARA_CONTATO}";
	public static final String TCAP_TAG_LINK_REPROVAR_PARA_CONTATO = "${LINK_REPROVAR_PARA_CONTATO}";

	// TCA - TemplateContaAtivada
	public static final String TCA_TAG_NOME_CLIENTE = "${NOME_CLIENTE}";
	public static final String TCA_TAG_EMAIL_NOVO_CLIENTE = "${EMAIL_NOVO_CLIENTE}";

	// TANC - TemplateAtivarNovaConta
	public static final String TANC_TAG_NOME_NOVO_CLIENTE = "${NOME_NOVO_CLIENTE}";
	public static final String TANC_TAG_EMAIL_NOVO_CLIENTE = "${EMAIL_NOVO_CLIENTE}";
	public static final String TANC_TAG_CGC_CPF_NOVO_CLIENTE = "${CGC_CPF_NOVO_CLIENTE}";
	public static final String TANC_TAG_LINK_ATIVACAO_NOVO_CLIENTE = "${LINK_ATIVACAO_NOVO_CLIENTE}";
	
	// TCAL - TemplateContatoAnuncianteLiberado
	public static final String TCAL_TAG_ID_IMOVEL = "${ID_IMOVEL}";
	public static final String TCAL_TAG_NOME_ANUNCIANTE = "${NOME_ANUNCIANTE}";
	public static final String TCAL_TAG_NOME_PROPOSTO = "${NOME_PROPOSTO}";
	public static final String TCAL_TAG_CIDADE_PROPOSTO = "${CIDADE_PROPOSTO}";
	public static final String TCAL_TAG_UF_PROPOSTO = "${UF_PROPOSTO}";
	public static final String TCAL_TAG_EMAIL_PROPOSTO = "${EMAIL_PROPOSTO}";
	public static final String TCAL_TAG_DDD_PROPOSTO = "${DDD_PROPOSTO}";
	public static final String TCAL_TAG_FONE_PROPOSTO = "${FONE_PROPOSTO}";
	public static final String TCAL_TAG_CHEGADA_PREVISTA_PROPOSTO = "${CHEGADA_PREVISTA_PROPOSTO}";
	public static final String TCAL_TAG_PARTIDA_PREVISTA_PROPOSTO = "${PARTIDA_PREVISTA_PROPOSTO}";
	public static final String TCAL_TAG_ADULTOS_PROPOSTO = "${ADULTOS_PROPOSTO}";
	public static final String TCAL_TAG_CRIANCAS_PROPOSTO = "${CRIANCAS_PROPOSTO}";
	public static final String TCAL_TAG_INFO_ADICIONAL_PROPOSTO = "${INFO_ADICIONAL_PROPOSTO}";
	
	//TCU - TemplateConteudoUpload
	public static final String TCU_TAG_URL_UPLOAD_VERIFICAR = "${URL_UPLOAD_VERIFICAR}";
	public static final String TCU_TAG_LINK_REPROVAR_UPLOAD = "${LINK_REPROVAR_UPLOAD}";
	
	//TCS - TemplateComunicadoSorteio
	public static final String TCS_TAG_NOME_CLIENTE = "${NOME_CLIENTE}";
	public static final String TCS_TAG_ID_IMOVEL = "${ID_IMOVEL}";
	public static final String TCS_TAG_TITULO_ANUNCIO = "${TITULO_ANUNCIO}";
	public static final String TCS_TAG_SECAO_PUBLICIDADE = "${SECAO_PUBLICIDADE}";
	public static final String TCS_TAG_DATA_INICIO_PUBLICIDADE = "${DATA_INICIO_DE_PUBLICIDADE}";
	public static final String TCS_TAG_DATA_FINAL_DE_PUBLICIDADE = "${DATA_FINAL_DE_PUBLICIDADE}";
	
	//TAIA - TemplateAvaliacaoImovelAnunciante
	public static final String TAIA_TAG_NOME_CLIENTE = "${CLIENTE}";
	public static final String TAIA_TAG_ID_IMOVEL = "${ID_IMOVEL}";
	public static final String TAIA_TAG_TITULO_ANUNCIO = "${TITULO_IMOVEL}";
	public static final String TAIA_TAG_TIT_AVAL_FOTOGRAFIA = "${TIT_AVALIACAO_FOTOGRAFIA}";
	public static final String TAIA_TAG_NOTA_AVAL_FOTOGRAFIA = "${NOTA_AVALIACAO_FOTOGRAFIA}";
	public static final String TAIA_TAG_TEXTO_AVAL_FOTOGRAFIA = "${TEXTO_AVALIACAO_FOTOGRAFIA}";
	public static final String TAIA_TAG_TIT_AVAL_QUALIDADE_TEXTO = "${TIT_AVAL_QUALIDADE_TEXTO}";
	public static final String TAIA_TAG_NOTA_AVAL_QUALIDADE_TEXTO = "${NOTA_AVAL_QUALIDADE_TEXTO}";
	public static final String TAIA_TAG_TEXTO_AVAL_QUALIDADE_TEXTO = "${TEXTO_AVAL_QUALIDADE_TEXTO}";
	public static final String TAIA_TAG_TIT_AVAL_INFORMACAO_RELEVANTE = "${TIT_AVAL_INFORMACAO_RELEVANTE}";
	public static final String TAIA_TAG_NOTA_AVAL_INFORMACAO_RELEVANTE = "${NOTA_AVAL_INFORMACAO_RELEVANTE}";
	public static final String TAIA_TAG_TEXTO_AVAL_INFORMACAO_RELEVANTE = "${TEXTO_AVAL_INFORMACAO_RELEVANTE}";
	
	//TNAP - TemplateNovoAssinantePromocao
	public static final String TNAP_TAG_NOVO_ASSINANTE_PROMOCAO = "${NOVO_ASSINANTE_PROMOCAO}";
	public static final String TNAP_TAG_PROMOCAO_ATIVA = "${PROMOCAO_ATIVA}";
	public static final String TNAP_TAG_LINK_ATIVA_PROMOCAO = "${LINK_ATIVA_PROMOCAO}";
	public static final String TNAP_TAG_LINK_REGULAMENTO = "${LINK_REGULAMENTO}";
	
	//TPAIA - TemplateAmigoIndicaAmigo
	public static final String TPAIA_TAG_NOVO_ASSINANTE_AMIGO = "${NOVO_ASSINANTE_AMIGO}";
	public static final String TPAIA_TAG_NOVO_ASSINANTE_PROMOCAO = "${NOVO_ASSINANTE_PROMOCAO}";
	public static final String TPAIA_TAG_PROMOCAO_ATIVA = "${PROMOCAO_ATIVA}";
	public static final String TPAIA_TAG_NOVO_ASSINANTE_PROMOCAO_EMAIL = "${NOVO_ASSINANTE_PROMOCAO_EMAIL}";
	public static final String TPAIA_TAG_LINK_ATIVA_PROMOCAO = "${LINK_ATIVA_PROMOCAO}";
	public static final String TPAIA_TAG_LINK_REGULAMENTO = "${LINK_REGULAMENTO}";
	
	//TPAS - TemplatePromocaoAtivadaSucesso
	public static final String TPAS_TAG_NOVO_ASSINANTE_AMIGO = "${NOVO_ASSINANTE_AMIGO}";
	public static final String TPAS_TAG_PROMOCAO_ATIVA = "${PROMOCAO_ATIVA}";
	public static final String TPAS_TAG_NUMERO_DA_SORTE_1 = "${NUMERO_DA_SORTE_1}";
	public static final String TPAS_TAG_DATA_SORTEIO_PROMOCAO = "${DATA_SORTEIO_PROMOCAO}";
	public static final String TPAS_TAG_LINK_REGULAMENTO = "${LINK_REGULAMENTO}";
	
	//TIPP - TemplateImovelPendentePgto
	public static final String TIPP_TAG_NOME_ANUNCIANTE_IMOVEL = "${TIPP_TAG_NOME_ANUNCIANTE_IMOVEL}";
	public static final String TIPP_TAG_TITULO_IMOVEL = "${TIPP_TAG_TITULO_IMOVEL}";
	public static final String TIPP_TAG_DATA_CADASTRO_IMOVEL = "${TIPP_TAG_DATA_CADASTRO_IMOVEL}";
	public static final String TIPP_TAG_NOME_PLANO_IMOVEL = "${TIPP_TAG_NOME_PLANO_IMOVEL}";
	public static final String TIPP_TAG_DESCRICAO_PLANO_IMOVEL = "${TIPP_TAG_DESCRICAO_PLANO_IMOVEL}";
	public static final String TIPP_TAG_VALOR_PLANO_IMOVEL = "${TIPP_TAG_VALOR_PLANO_IMOVEL}";
	public static final String TIPP_TAG_DATA_VALIDADE_ANUNCIO_IMOVEL = "${TIPP_TAG_DATA_VALIDADE_ANUNCIO_IMOVEL}";
	
	//TNCAI - TemplateNotificarCadastroAlteracaoImovel
	public static final String TNCAI_TAG_CODIGO_IMOVEL = "${TNCAI_TAG_CODIGO_IMOVEL}";
	public static final String TNCAI_TAG_CODIGO_CLIENTE = "${TNCAI_TAG_CODIGO_CLIENTE}";
	public static final String TNCAI_TAG_QTDE_QUARTOS = "${TNCAI_TAG_QTDE_QUARTOS}";
	public static final String TNCAI_TAG_QTDE_SUITES = "${TNCAI_TAG_QTDE_SUITES}";
	public static final String TNCAI_TAG_QTDE_BANHEIROS = "${TNCAI_TAG_QTDE_BANHEIROS}";
	public static final String TNCAI_TAG_QTDE_CAPACIDADE_MAX = "${TNCAI_TAG_QTDE_CAPACIDADE_MAX}";
	public static final String TNCAI_TAG_DESC_GERAL_IMOVEL = "${TNCAI_TAG_DESC_GERAL_IMOVEL}";
	public static final String TNCAI_TAG_DESC_QUARTOS = "${TNCAI_TAG_DESC_QUARTOS}";
	public static final String TNCAI_TAG_DESC_ARREDORES = "${TNCAI_TAG_DESC_ARREDORES}";
	public static final String TNCAI_TAG_DESC_TITULO_ANUNCIO = "${TNCAI_TAG_DESC_TITULO_ANUNCIO}";
	public static final String TNCAI_TAG_STATUS_IMOVEL = "${TNCAI_TAG_STATUS_IMOVEL}";
	public static final String TNCAI_TAG_OBSERVACOES = "${TNCAI_TAG_OBSERVACOES}";
	public static final String TNCAI_TAG_FLAG_MOSTRA_TAB_PRECO = "${TNCAI_TAG_FLAG_MOSTRA_TAB_PRECO}";
	public static final String TNCAI_TAG_FLAG_CONDOMINIO = "${TNCAI_TAG_FLAG_CONDOMINIO}";
	public static final String TNCAI_TAG_DATA_ULTIMA_VISITA = "${TNCAI_TAG_DATA_ULTIMA_VISITA}";
	public static final String TNCAI_TAG_QTDE_VISITAS = "${TNCAI_TAG_QTDE_VISITAS}";
	public static final String TNCAI_TAG_FLAG_PERMITE_ALUGAR_FESTA = "${TNCAI_TAG_FLAG_PERMITE_ALUGAR_FESTA}";
	public static final String TNCAI_TAG_FLAG_TIPO_PROPRIEDADE = "${TNCAI_TAG_FLAG_TIPO_PROPRIEDADE}";
	public static final String TNCAI_TAG_ENDERECO_IMOVEL = "${TNCAI_TAG_ENDERECO_IMOVEL}";
	public static final String TNCAI_TAG_ENDERECO_NUMERO = "${TNCAI_TAG_ENDERECO_NUMERO}";
	public static final String TNCAI_TAG_ENDERECO_COMPLEMENTO = "${TNCAI_TAG_ENDERECO_COMPLEMENTO}";
	public static final String TNCAI_TAG_ENDERECO_BAIRRO = "${TNCAI_TAG_ENDERECO_BAIRRO}";
	public static final String TNCAI_TAG_ENDERECO_CEP = "${TNCAI_TAG_ENDERECO_CEP}";
	public static final String TNCAI_TAG_ENDERECO_CIDADE = "${TNCAI_TAG_ENDERECO_CIDADE}";
	public static final String TNCAI_TAG_ENDERECO_UF = "${TNCAI_TAG_ENDERECO_UF}";
	public static final String TNCAI_TAG_IMAGEM_PREFERIDA_ID = "${TNCAI_TAG_IMAGEM_PREFERIDA_ID}";
	
	//TVPR - TemplateValidarPreReserva
	public static final String TVPR_TAG_NOME_PROPOSTO = "${NOME_PROPOSTO}";
	public static final String TVPR_TAG_CPF_PROPOSTO = "${CPF_PROPOSTO}";
	public static final String TVPR_TAG_CODIGO_TRACKING = "${CODIGO_TRACKING}";
	public static final String TVPR_TAG_CODIGO_IMOVEL = "${CODIGO_IMOVEL}";
	public static final String TVPR_TAG_TITULO_IMOVEL = "${TITULO_IMOVEL}";
	public static final String TVPR_TAG_VALOR_NEGOCIADO = "${VALOR_NEGOCIADO}";
	public static final String TVPR_TAG_DATA_CHECKIN = "${DATA_CHECKIN}";
	public static final String TVPR_TAG_DATA_CHECKOUT = "${DATA_CHECKOUT}";
	public static final String TVPR_TAG_DATA_PREVISTA_DEPOSITO = "${DATA_PREVISTA_DEPOSITO}";
	public static final String TVPR_TAG_VALOR_PREVISTO_DEPOSITO = "${VALOR_PREVISTO_DEPOSITO}";
	public static final String TVPR_TAG_DATA_CADASTRO = "${DATA_CADASTRO}";
	public static final String TVPR_TAG_LINK_LIBERAR_RESERVA = "${LINK_LIBERAR_RESERVA}";
	public static final String TVPR_TAG_LINK_REPROVAR_RESERVA = "${LINK_REPROVAR_RESERVA}";
	public static final String TVPR_TAG_VALOR_CAUCAO = "${VALOR_CAUCAO}"; 
	public static final String TVPR_TAG_VALOR_PAGAR_NA_CHAVE = "${VALOR_PAGAR_NA_CHAVE}"; 
	public static final String TVPR_TAG_LINK_RESERVA = "${LINK_RESERVA}"; 
	
	//TAPPR - TemplateAguadarPgtoPreReserva
	public static final String TAPPR_TAG_CODIGO_RESERVA = "${CODIGO_RESERVA}";
	public static final String TAPPR_TAG_NOME_PROPOSTO = "${NOME_PROPOSTO}";
	public static final String TAPPR_TAG_CODIGO_IMOVEL = "${CODIGO_IMOVEL}";
	public static final String TAPPR_TAG_TITULO_IMOVEL = "${TITULO_IMOVEL}";
	public static final String TAPPR_TAG_DATA_VALIDACAO_PRE_RESERVA = "${DATA_VALIDACAO_PRE_RESERVA}";
	public static final String TAPPR_TAG_CODIGO_TRACKING = "${CODIGO_TRACKING}";
	public static final String TAPPR_TAG_VALOR_NEGOCIADO = "${VALOR_NEGOCIADO}";
	public static final String TAPPR_TAG_DATA_CHECKIN = "${DATA_CHECKIN}";
	public static final String TAPPR_TAG_DATA_CHECKOUT = "${DATA_CHECKOUT}";
	public static final String TAPPR_TAG_DATA_CADASTRO = "${DATA_CADASTRO}";
	public static final String TAPPR_TAG_EMAIL_CONTATO = "${EMAIL_CONTATO}";
	public static final String TAPPR_TAG_TOKEN = "${TOKEN}";
	public static final String TAPPR_TAG_CPF_PROPOSTO = "${CPF_PROPOSTO}";
	public static final String TAPPR_TAG_FORMA_DE_PAGAMENTO = "${FORMA_DE_PAGAMENTO}";
	public static final String TAPPR_TAG_DATA_PREVISTA_DEPOSITO = "${DATA_PREVISTA_DEPOSITO}";
	public static final String TAPPR_TAG_VALOR_PREVISTO_DEPOSITO = "${VALOR_PREVISTO_DEPOSITO}";
	public static final String TAPPR_TAG_TELEFONES_CENTRAL_RESERVA = "${TELEFONES_CENTRAL_RESERVA}";
	public static final String TAPPR_TAG_VALOR_POR_EXTENSO = "${VALOR_POR_EXTENSO}";
	public static final String TAPPR_TAG_VALOR_CAUCAO = "${VALOR_CAUCAO}";
	public static final String TAPPR_TAG_VALOR_CAUCAO_POR_EXTENSO = "${VALOR_CAUCAO_POR_EXTENSO}";
	public static final String TAPPR_TAG_VALOR_TOTAL_DEPOSITO = "${VALOR_TOTAL_DEPOSITO}";
	public static final String TAPPR_TAG_VALOR_TOTAL_DEPOSITO_POR_EXTENSO = "${VALOR_TOTAL_DEPOSITO_POR_EXTENSO}";
	
	//TCTL - TemplateContratoTemporadaLocatario
	public static final String TCTL_TAG_CODIGO_RESERVA = "${NUMERO_CONTROLE_RESERVA}";
	public static final String TCTL_TAG_NOME_PROPOSTO = "${NOME_DO_LOCATARIO}";
	public static final String TCTL_TAG_CODIGO_IMOVEL = "${CODIGO_IMOVEL}";
	public static final String TCTL_TAG_TITULO_IMOVEL = "${TITULO_IMOVEL}";
	public static final String TCTL_TAG_DATA_VALIDACAO_PRE_RESERVA = "${DATA_VALIDACAO_PRE_RESERVA}";
	public static final String TCTL_TAG_CODIGO_TRACKING = "${CODIGO_TRACKING}";
	public static final String TCTL_TAG_VALOR_NEGOCIADO = "${VALOR_NEGOCIADO}";
	public static final String TCTL_TAG_DATA_CHECKIN = "${DATA_CHECKIN}";
	public static final String TCTL_TAG_DATA_CHECKOUT = "${DATA_CHECKOUT}";
	public static final String TCTL_TAG_DATA_CADASTRO = "${DATA_CADASTRO}";
	public static final String TCTL_TAG_EMAIL_CONTATO = "${EMAIL_CONTATO}";
	public static final String TCTL_TAG_TOKEN = "${TOKEN}";
	public static final String TCTL_TAG_CPF_PROPOSTO = "${CPF_LOCATARIO}";
	public static final String TCTL_TAG_RG_PROPOSTO = "${RG_LOCATARIO}";
	public static final String TCTL_TAG_FORMA_DE_PAGAMENTO = "${FORMA_DE_PAGAMENTO}";
	public static final String TCTL_TAG_DATA_PREVISTA_DEPOSITO = "${DATA_PREVISTA_DEPOSITO}";
	public static final String TCTL_TAG_VALOR_PREVISTO_DEPOSITO = "${VALOR_PREVISTO_DEPOSITO}";
	public static final String TCTL_TAG_VALOR_PREVISTO_DEPOSITO_EXTENSO = "${VALOR_PREVISTO_DEPOSITO_EXTENSO}";
	public static final String TCTL_TAG_TELEFONES_CENTRAL_RESERVA = "${TELEFONES_CENTRAL_RESERVA}";
	public static final String TCTL_TAG_VALOR_POR_EXTENSO = "${VALOR_NEGOCIADO_POR_EXTENSO}";
	public static final String TCTL_TAG_NOME_DO_LOCADOR = "${NOME_DO_LOCADOR}";
	public static final String TCTL_TAG_CPF_LOCADOR = "${CPF_LOCADOR}";
	public static final String TCTL_TAG_ENDERECO_COMPLETO_LOCADOR = "${ENDERECO_COMPLETO_LOCADOR}";
	public static final String TCTL_TAG_ENDERECO_COMPLETO_LOCATARIO = "${ENDERECO_COMPLETO_LOCATARIO}";
	public static final String TCTL_TAG_VALOR_DA_CAUCAO = "${VALOR_DA_CAUCAO}";
	public static final String TCTL_TAG_VALOR_DA_CAUCAO_POR_EXTENSO = "${VALOR_DA_CAUCAO_POR_EXTENSO}";
	public static final String TCTL_TAG_PERCENTUAL_COMISSAO_RESERVA = "${PERCENTUAL_COMISSAO_RESERVA}";
	public static final String TCTL_TAG_PERCENTUAL_COMISSAO_RESERVA_POR_EXTENSO = "${PERCENTUAL_COMISSAO_RESERVA_POR_EXTENSO}";
	public static final String TCTL_TAG_QTDE_PESSOAS_IMOVEL = "${QTDE_PESSOAS_IMOVEL}";
	public static final String TCTL_TAG_QTDE_PESSOAS_IMOVEL_EXTENSO = "${QTDE_PESSOAS_IMOVEL_EXTENSO}";
	public static final String TCTL_TAG_DATA_INICIO_LOCACAO_EXTENSO = "${DATA_INICIO_LOCACAO_EXTENSO}";
	public static final String TCTL_TAG_DATA_FIM_LOCACAO_EXTENSO = "${DATA_FIM_LOCACAO_EXTENSO}";
	public static final String TCTL_TAG_DATA_DO_CONTRATO_POR_EXTENSO = "${DATA_DO_CONTRATO_POR_EXTENSO}";
	public static final String TCTL_TAG_ENDERECO_COMPLETO_IMOVEL = "${ENDERECO_COMPLETO_IMOVEL}";
	
	//TCPR - TemplateConfirmacaoPgtoReserva
	public static final String TCPR_TAG_CODIGO_RESERVA = "${CODIGO_RESERVA}";
	public static final String TCPR_TAG_NOME_PROPOSTO = "${NOME_PROPOSTO}";
	public static final String TCPR_TAG_CODIGO_IMOVEL = "${CODIGO_IMOVEL}";
	public static final String TCPR_TAG_TITULO_IMOVEL = "${TITULO_IMOVEL}";
	public static final String TCPR_TAG_DATA_VALIDACAO_PRE_RESERVA = "${DATA_VALIDACAO_PRE_RESERVA}";
	public static final String TCPR_TAG_CODIGO_TRACKING = "${CODIGO_TRACKING}";
	public static final String TCPR_TAG_VALOR_NEGOCIADO = "${VALOR_NEGOCIADO}";
	public static final String TCPR_TAG_DATA_CHECKIN = "${DATA_CHECKIN}";
	public static final String TCPR_TAG_DATA_CHECKOUT = "${DATA_CHECKOUT}";
	public static final String TCPR_TAG_DATA_CADASTRO = "${DATA_CADASTRO}";
	public static final String TCPR_TAG_EMAIL_CONTATO = "${EMAIL_CONTATO}";
	public static final String TCPR_TAG_TOKEN = "${TOKEN}";
	public static final String TCPR_TAG_CPF_PROPOSTO = "${CPF_PROPOSTO}";
	public static final String TCPR_TAG_RG_PROPOSTO = "${RG_PROPOSTO}";
	public static final String TCPR_TAG_FORMA_DE_PAGAMENTO = "${FORMA_DE_PAGAMENTO}";
	public static final String TCPR_TAG_DATA_PREVISTA_DEPOSITO = "${DATA_PREVISTA_DEPOSITO}";
	public static final String TCPR_TAG_VALOR_PREVISTO_DEPOSITO = "${VALOR_PREVISTO_DEPOSITO}";
	public static final String TCPR_TAG_VALOR_PREVISTO_DEPOSITO_EXTENSO = "${VALOR_PREVISTO_DEPOSITO_EXTENSO}";
	public static final String TCPR_TAG_TELEFONES_CENTRAL_RESERVA = "${TELEFONES_CENTRAL_RESERVA}";
	public static final String TCPR_TAG_VALOR_POR_EXTENSO = "${VALOR_POR_EXTENSO}";
	public static final String TCPR_TAG_DATA_VALIDACAO_PGTO_PRE_RESERVA = "${DATA_VALIDACAO_PGTO_PRE_RESERVA}";
	public static final String TCPR_TAG_VALOR_VALIDACAO_PGTO_PRE_RESERVA = "${VALOR_VALIDACAO_PGTO_PRE_RESERVA}";
	public static final String TCPR_TAG_LINK_CONTRATO_LOCATARIO = "${LINK_CONTRATO_LOCATARIO}";
	public static final String TCPR_TAG_VALOR_CAUCAO = "${VALOR_CAUCAO}";
	public static final String TCPR_TAG_VALOR_PAGAR_NA_ENTREGA_CHAVE = "${VALOR_PAGAR_NA_ENTREGA_CHAVE}";
	public static final String TCPR_TAG_VALOR_PAGAR_NA_ENTREGA_CHAVE_POR_EXTENSO = "${VALOR_PAGAR_NA_ENTREGA_CHAVE_POR_EXTENSO}";
	

	// TV - TemplateVoucher
	public static final String TV_TAG_CODIGO_RESERVA = "${CODIGO_RESERVA}";
	public static final String TV_TAG_NOME_PROPOSTO = "${NOME_PROPOSTO}";
	public static final String TV_TAG_CODIGO_IMOVEL = "${CODIGO_IMOVEL}";
	public static final String TV_TAG_TITULO_IMOVEL = "${TITULO_IMOVEL}";
	public static final String TV_TAG_DATA_VALIDACAO_PRE_RESERVA = "${DATA_VALIDACAO_PRE_RESERVA}";
	public static final String TV_TAG_CODIGO_VOUCHER = "${CODIGO_VOUCHER}";
	public static final String TV_TAG_VALOR_NEGOCIADO = "${VALOR_NEGOCIADO}";
	public static final String TV_TAG_DATA_CHECKIN = "${DATA_CHECKIN}";
	public static final String TV_TAG_DATA_CHECKOUT = "${DATA_CHECKOUT}";
	public static final String TV_TAG_DATA_CADASTRO = "${DATA_CADASTRO}";
	public static final String TV_TAG_EMAIL_CONTATO = "${EMAIL_CONTATO}";
	public static final String TV_TAG_TOKEN = "${TOKEN}";
	public static final String TV_TAG_CPF_PROPOSTO = "${CPF_PROPOSTO}";
	public static final String TV_TAG_RG_PROPOSTO = "${RG_LOCATARIO}";
	public static final String TV_TAG_FORMA_DE_PAGAMENTO = "${FORMA_DE_PAGAMENTO}";
	public static final String TV_TAG_DATA_PREVISTA_DEPOSITO = "${DATA_PREVISTA_DEPOSITO}";
	public static final String TV_TAG_VALOR_PREVISTO_DEPOSITO = "${VALOR_PREVISTO_DEPOSITO}";
	public static final String TV_TAG_TELEFONES_CENTRAL_RESERVA = "${TELEFONES_CENTRAL_RESERVA}";
	public static final String TV_TAG_VALOR_POR_EXTENSO = "${VALOR_NEGOCIADO_POR_EXTENSO}";
	public static final String TV_TAG_NOME_DO_LOCADOR = "${NOME_DO_LOCADOR}";
	public static final String TV_TAG_CPF_LOCADOR = "${CPF_LOCADOR}";
	public static final String TV_TAG_ENDERECO_COMPLETO_LOCADOR = "${ENDERECO_COMPLETO_LOCADOR}";
	public static final String TV_TAG_ENDERECO_COMPLETO_LOCATARIO = "${ENDERECO_COMPLETO_LOCATARIO}";
	public static final String TV_TAG_VALOR_DA_CAUCAO = "${VALOR_DA_CAUCAO}";
	public static final String TV_TAG_VALOR_DA_CAUCAO_POR_EXTENSO = "${VALOR_DA_CAUCAO_POR_EXTENSO}";
	public static final String TV_TAG_PERCENTUAL_COMISSAO_RESERVA = "${PERCENTUAL_COMISSAO_RESERVA}";
	public static final String TV_TAG_PERCENTUAL_COMISSAO_RESERVA_POR_EXTENSO = "${PERCENTUAL_COMISSAO_RESERVA_POR_EXTENSO}";
	public static final String TV_TAG_QTDE_PESSOAS_IMOVEL = "${QTDE_PESSOAS_IMOVEL}";
	public static final String TV_TAG_QTDE_PESSOAS_IMOVEL_EXTENSO = "${QTDE_PESSOAS_IMOVEL_EXTENSO}";
	public static final String TV_TAG_VALOR_REAL_DEPOSITO = "${VALOR_REAL_DEPOSITO}";
	public static final String TV_TAG_CODIGO_TRACKING = "${CODIGO_TRACKING}";
	public static final String TV_TAG_VALOR_PAGAR_ENTREGA_CHAVE = "${VALOR_ENTREGA_CHAVE}";
	
	//TICA - TemplateInstrucoesCaptarAutorizacao
	public static final String TICA_TAG_NOME_PROPRIETARIO = "${NOME_PROPRIETARIO}";
	public static final String TICA_TAG_PROPRIETARIO_TELEFONE = "${PROPRIETARIO_TELEFONE}";
	public static final String TICA_TAG_TITULO_IMOVEL = "${TITULO_IMOVEL}";
	public static final String TICA_TAG_ENDERECO_COMPLETO_IMOVEL = "${ENDERECO_COMPLETO_IMOVEL}";
	public static final String TICA_TAG_NOME_CONTATO = "${NOME_CONTATO}";
	public static final String TICA_TAG_CIDADE_CONTATO = "${CIDADE_CONTATO}";
	public static final String TICA_TAG_UF_CONTATO = "${UF_CONTATO}";
	public static final String TICA_TAG_DATA_CHECKIN_CONTATO = "${DATA_CHECKIN_CONTATO}";
	public static final String TICA_TAG_DATA_CHECKOUT_CONTATO = "${DATA_CHECKOUT_CONTATO}";
	public static final String TICA_TAG_TELEFONE = "${TELEFONE_CONTATO}";
	public static final String TICA_TAG_EMAIL_CONTATO = "${EMAIL_CONTATO}";
	
	//TETD - TemplateExecutarTransferenciaDeposito e TemplateDepositoTransferido
	public static final String TETD_TAG_CODIGO_TRACKING = "${CODIGO_TRACKING}";
	public static final String TETD_TAG_NOME_BANCO = "${NOME_BANCO}";
	public static final String TETD_TAG_NOME_AGENCIA = "${NOME_AGENCIA}";
	public static final String TETD_TAG_CONTA_CORRENTE = "${CONTA_CORRENTE}";
	public static final String TETD_TAG_NOME_LOCADOR = "${NOME_LOCADOR}";
	public static final String TETD_TAG_VALOR_NEGOCIADO_TEMPORADA = "${VALOR_NEGOCIADO_TEMPORADA}";
	public static final String TETD_TAG_FORMA_PGTO_ESCOLHIDA = "${FORMA_PGTO_ESCOLHIDA}";
	public static final String TETD_TAG_VALOR_DEPOSITADO_CUSTODIA = "${VALOR_DEPOSITADO_CUSTODIA}";
	public static final String TETD_TAG_VALOR_DEPOSITO_CAUCAO = "${VALOR_DEPOSITO_CAUCAO}";
	public static final String TETD_TAG_VALOR_A_SER_PAGO_NA_CHAVE = "${VALOR_A_SER_PAGO_NA_CHAVE}";
	public static final String TETD_TAG_PERC_COMISSAO_ALUGUE_RELAXE = "${PERC_COMISSAO_ALUGUE_RELAXE}";
	public static final String TETD_TAG_VALOR_COMISSAO_ALUGUE_RELAXE = "${VALOR_COMISSAO_ALUGUE_RELAXE}";
	public static final String TETD_TAG_VALOR_A_SER_LIBERADO = "${VALOR_A_SER_LIBERADO}";
	public static final String TETD_TAG_DATA_TRANSFERENCIA_VALOR_A_SER_LIBERADO = "${DATA_TRANSFERENCIA_VALOR_A_SER_LIBERADO}";
}

