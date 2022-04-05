package br.com.jcv.backend.constantes;

public class Constantes {
	
	public static final String MAPA_TAG = "mapa";
	public static final String MAPA_FILTRO_GERAL = "MAPA_FILTRO_GERAL";
	public static final String CONTEXTO_BP_ID_CIDADE_UF = "id_uf_cidade_item";

	
	public static final int FOTO_CHAT = 0;
	public static final int FOTO_PERFIL = 1;
	
	public static final int SMS_PRIORIDADE_NORMAL = 0;
	public static final int SMS_PRIORIDADE_ALTA = 1;
	
	public static final String FORMA_PGTO_PARCIAL = "P";
	public static final String FORMA_PGTO_TOTAL = "T";
	public static final String FORMA_PGTO_PARCIAL_STR = "SINAL DE 50% NA PRE-RESERVA + 50% DIRETAMENTE AO LOCADOR ";
	public static final String FORMA_PGTO_TOTAL_STR = "SINAL DE 100% NA PRE-RESERVA";
	
	public static final String FURAFILA = "C";
	public static final String PATROCINADOR = "P";
	
	public static final String SMS_MODO_ENVIO_GATEWAY = "G";
	public static final String SMS_MODO_ENVIO_MODEM3G = "M";
	
	public static final String APROVAR = "APROVAR";
	public static final String APROVAR_COM_EDICAO = "APROVAR_EDITADA";
	public static final String REPROVAR = "REPROVAR";
	
	public static final String APROVADO = "A";
	public static final String PENDENTE = "P";
	public static final String LIBERADO = "L";
	public static final String INATIVO = "I";
	public static final String ATIVO = "A";

	public static final String PERGUNTA_RESPOSTA = "PR";
	public static final String COMENTARIO = "CO";
	public static final String ORIGEM_VISITANTE = "V";
	public static final String ORIGEM_ANUNCIANTE = "A";
	
	public static final String TIPO_COMPRA_BANNER = "B";
	public static final String TIPO_COMPRA_PP = "P";
	public static final String TIPO_COMPRA_PD = "D";
	public static final String TIPO_COMPRA_EMAIL = "M";
	public static final String TIPO_COMPRA_FB = "X";
	public static final String TIPO_COMPRA_FF = "F";
	public static final String TIPO_COMPRA_PATROCINADOR = "R";
	public static final String TIPO_COMPRA_SB = "W";
	public static final String TIPO_COMPRA_SMS = "Z";
	
	public static final String IMOV_TIPO_PROPRIEDADE_CASA = "C";
	public static final String IMOV_TIPO_PROPRIEDADE_CASA_STR = "Casa";
	public static final String IMOV_TIPO_PROPRIEDADE_APTO = "A";
	public static final String IMOV_TIPO_PROPRIEDADE_APTO_STR = "Apartamento";
	public static final String IMOV_TIPO_PROPRIEDADE_HOTEL = "H";
	public static final String IMOV_TIPO_PROPRIEDADE_HOTEL_STR = "Hotel";
	public static final String IMOV_TIPO_PROPRIEDADE_CHACARA = "X";
	public static final String IMOV_TIPO_PROPRIEDADE_CHACARA_STR = "Chacara";
	public static final String IMOV_TIPO_PROPRIEDADE_FLAT = "F";
	public static final String IMOV_TIPO_PROPRIEDADE_FLAT_STR = "Flat";
	public static final String IMOV_TIPO_PROPRIEDADE_FAZENDA = "Z";
	public static final String IMOV_TIPO_PROPRIEDADE_FAZENDA_STR = "Fazenda";
	public static final String IMOV_TIPO_PROPRIEDADE_SITIO = "S";
	public static final String IMOV_TIPO_PROPRIEDADE_SITIO_STR = "Sitio";
	public static final String IMOV_TIPO_PROPRIEDADE_CHALE = "L";
	public static final String IMOV_TIPO_PROPRIEDADE_CHALE_STR = "Chale";
	public static final String IMOV_TIPO_PROPRIEDADE_POUSADA = "L";
	public static final String IMOV_TIPO_PROPRIEDADE_POUSADA_STR = "Pousada";
	
	public static final String IMCA_STATUS_PENDENTE = "P";
	public static final String IMCA_STATUS_LIBERADO = "L";
	public static final String IMCA_STATUS_ENVIADO = "E";
	public static final String IMCA_STATUS_REPROVADO = "R";

	public static final String IMPF_STATUS_PENDENTE = "P";
	public static final String IMPF_STATUS_LIBERADO = "L";
	public static final String IMPF_STATUS_REPROVADO = "R";
	public static final String IMPF_STATUS_VENCIDO = "V";
	public static final String IMPF_STATUS_CANCELADO = "C";

	public static final String CLIE_STATUS_PENDENTE = "P";
	public static final String CLIE_STATUS_LIBERADO = "A";
	public static final String CLIE_STATUS_REPROVADO = "R";
	public static final String CLIE_STATUS_BLOQUEADO = "B";
	public static final String CLIE_STATUS_INATIVO = "I";
	public static final String CLIE_STATUS_VERIFICANDO = "V";
	
	public static final String DEPO_STATUS_PENDENTE = "P";
	public static final String DEPO_STATUS_LIBERADO = "L";
	public static final String DEPO_STATUS_REPROVADO = "R";
	
	public static final String IMOVEL_STATUS_ATIVO = "A";
	public static final String IMOVEL_STATUS_INATIVO = "I";
	public static final String IMOVEL_STATUS_PENDENTE_PGTO = "P";
	
	public static final String SIM = "S";
	public static final String NAO = "N";
	
	public static final String ON = "ON";
	public static final String OFF = "OFF";
	
	//Constantes de parametros
	public static final String P1 = "${1}";
	public static final String P2 = "${2}";
	public static final String P3 = "${3}";
	public static final String P4 = "${4}";
	public static final String P5 = "${5}";
	public static final String P6 = "${6}";
	public static final String P7 = "${7}";
	public static final String P8 = "${8}";
	
	//Codigo especial do plano gratuito
	public static final int CODIGO_ESPECIAL_PLANO_GRATUITO = 1;

	//===========================================
	// constantes para Abstract DAO Factory
	//===========================================
	public static final int DATABASE_ORACLE = 0;
	public static final int DATABASE_INTERBASE = 1;
	public static final int DATABASE_SQLSERVER = 2;
	public static final int DATABASE_FIREBIRD = 3;
	public static final int DATABASE_POSTGRESQL = 4;
	public static final int DATABASE_DB2 = 5;
	public static final int DATABASE_MYSQL = 6;
	public static final int DATABASE_INFORMIX = 7;
	
	// Constantes para banco de dados usados para DAOFactory
	public static final String DATABASE_NAME_ORACLE = "ORACLE";
	public static final String DATABASE_NAME_INTERBASE = "INTERBASE";
	public static final String DATABASE_NAME_SQLSERVER = "SQLSERVER";
	public static final String DATABASE_NAME_FIREBIRD = "FIREBIRD";
	public static final String DATABASE_NAME_POSTGRESQL = "POSTGRESQL";
	public static final String DATABASE_NAME_DB2 = "DB2";
	public static final String DATABASE_NAME_MYSQL = "MYSQL";
	public static final String DATABASE_NAME_INFORMIX = "INFORMIX";
	
	public static final int TAMANHO_MAXIMO_MENSAGEM = 1000;

	public static final int TAMANHO_MAXIMO_ID_VARIAVEL = 100;
	public static final int TAMANHO_MAXIMO_DESCRICAO_VARIAVEL = 500;
	
	public static final int MAX_RETORNO_REGISTRO = 20; 

	/**
	 * <h2>IND_TIPO_IMAGEM_TB</h2>
	 * <p>Indicador de tipo de imagem para tamanho thumbnails.
	 * </p>
	 */
	public static final String IND_TIPO_IMAGEM_TB = "TB";
	/**
	 * <h2>IND_TIPO_IMAGEM_XG</h2>
	 * <p>Indicador de tipo de imagem para tamanho XG.
	 * </p>
	 */
	public static final String IND_TIPO_IMAGEM_XG = "XG";
	/**
	 * <h2>IND_TIPO_IMAGEM_PP</h2>
	 * <p>Indicador de tipo de imagem para tamanho da primeira pagina.
	 * </p>
	 */
	public static final String IND_TIPO_IMAGEM_PP = "PP";
	/**
	 * <h2>IND_TIPO_IMAGEM_PD</h2>
	 * <p>Indicador de tipo de imagem para tamanho da pagina de destaques.
	 * </p>
	 */
	public static final String IND_TIPO_IMAGEM_PD = "PD";
	/**
	 * <h2>IND_TIPO_IMAGEM_VD</h2>
	 * <p>Indicador de tipo de imagem para video do imovel.
	 * </p>
	 */
	public static final String IND_TIPO_IMAGEM_VD = "VD";
	/**
	 * <h2>IND_TIPO_IMAGEM_VP</h2>
	 * <p>Indicador de tipo de imagem para video do imovel da primeira pagina
	 * </p>
	 */
	public static final String IND_TIPO_IMAGEM_VP = "VP";
	/**
	 * <h2>IND_TIPO_PUBLICIDADE_SB</h2>
	 * <p>Indicador de tipo de publicidade para super banner
	 * </p>
	 */
	public static final String IND_TIPO_PUBLICIDADE_SB = "SB";
	/**
	 * <h2>IND_TIPO_PUBLICIDADE_PP</h2>
	 * <p>Indicador de tipo de publicidade para primeira pagina
	 * </p>
	 */
	public static final String IND_TIPO_PUBLICIDADE_PP = "PP";
	/**
	 * <h2>IND_TIPO_PUBLICIDADE_PD</h2>
	 * <p>Indicador de tipo de publicidade para painel de destaque
	 * </p>
	 */
	public static final String IND_TIPO_PUBLICIDADE_PD = "PD";
	/**
	 * <h2>IND_TIPO_PUBLICIDADE_EM</h2>
	 * <p>Indicador de tipo de publicidade para painel de destaque
	 * </p>
	 */
	public static final String IND_TIPO_PUBLICIDADE_EMAIL = "EM";
	/**
	 * <h2>IND_TIPO_PUBLICIDADE_SMS</h2>
	 * <p>Indicador de tipo de publicidade para painel de destaque
	 * </p>
	 */
	public static final String IND_TIPO_PUBLICIDADE_SMS = "SM";
	/**
	 * <h2>IND_TIPO_PUBLICIDADE_BANNER</h2>
	 * <p>Indicador de tipo de publicidade para painel de destaque
	 * </p>
	 */
	public static final String IND_TIPO_PUBLICIDADE_BANNER = "BN";
	/**
	 * <h2>IND_TIPO_PUBLICIDADE_FACEBOOK</h2>
	 * <p>Indicador de tipo de publicidade para painel de destaque
	 * </p>
	 */
	public static final String IND_TIPO_PUBLICIDADE_FACEBOOK = "FB";
	/**
	 * <h2>IND_TIPO_PUBLICIDADE_FF</h2>
	 * <p>Indicador de tipo de publicidade para painel de destaque
	 * </p>
	 */
	public static final String IND_TIPO_PUBLICIDADE_FF = "FF";
	/**
	 * <h2>IND_TIPO_PUBLICIDADE_PATROCINADOR</h2>
	 * <p>Indicador de tipo de publicidade para painel de destaque
	 * </p>
	 */
	public static final String IND_TIPO_PUBLICIDADE_PATROCINADOR = "PA";

	/**
	 * Contas Google para gerenciamento do google maps
	 */
	public static final String CONTA_GOOGLE_MAP_USUARIO = "CONTA_GOOGLE_MAP_USUARIO";
	/**
	 * Contas Google para gerenciamento do google maps
	 */
	public static final String CONTA_GOOGLE_MAP_PASSWD = "CONTA_GOOGLE_MAP_PASSWD";
	/**
	 * Tipos de plano de pagamento
	 */
	public static final String TIPO_PLANO_NORMAL = "N";
	public static final String TIPO_PLANO_PUBLICIDADE_PP = "P";
	public static final String TIPO_PLANO_PUBLICIDADE_PD = "D";
	public static final String TIPO_PLANO_ESPECIAL = "E"; /* Imobiliarias */

	// Variaveis de contexto de busca pagina
	// NOTA: Algumas destas constantes podem existir no Constantes.java 
	// do projeto frontend AlugueRelaxe
	public static final String CONTEXTO_BP_UF = "uf";
	public static final String CONTEXTO_BP_CIDADE = "cidade";
	public static final String CONTEXTO_ID_IMOVEL = "idimovel";
	public static final String CONTEXTO_TOTAL_REGISTROS = "total_registros";
	public static final String CONTEXTO_PROPRIEDADE = "propriedade";                  
	public static final String CONTEXTO_QUARTO = "quartos";     
	public static final String CONTEXTO_SUITE = "suites";
	public static final String CONTEXTO_BANHEIRO = "banheiros";
	public static final String CONTEXTO_CAPACIDADE = "capacidade";
	public static final String CONTEXTO_CONDOMINIO = "condominio";
	public static final String CONTEXTO_ALUGA_FESTA = "alugafesta";
	
	public static final String LANGUAGE_PT_BR = "pt_BR";

}
