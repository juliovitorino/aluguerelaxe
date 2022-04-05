package br.com.jcv.aluguerelaxe.client;

public class Constantes {
	/**
	 * <h2>ROOT_FOTOS</h2>
	 * <p>diretório base onde se encontram as imagens para serem usadas no portal de publicidades.
	 * </p>
	 * <p><strong>Observação:</strong><br>
	 * Este rótulo abaixo deve estar definido em uma tag <code>param-name</code> dentro do web.xml. 
	 */
	public static final String ROOT_FOTOS = "root_fotos";
	
	public static final String MAPA_TAG = "mapa";
	
	// Constantes para Mapa de buscas no backend das classes concretas de AbstractListPaginada
	/**
	 * <h2>MAPA_LISTA_IMOVEIS</h2>
	 * <p>Tag que identifica qual factory da classe de implementação BuscaPagina será criada.
	 * para ligar o Server-side com os serviços de backend.
	 * </p>
	 * <p>A notação desta string é uma identificação do tipo <strong>Classe::Método</strong></p>
	 */
	public static final String MAPA_LISTA_IMOVEIS = "ImovelServicesImpl::listaImoveisPorEstado";
	public static final String MAPA_LISTA_IMOVEIS_CIDADE = "ImovelServicesImpl::listaImoveisPorCidade";
	public static final String MAPA_LISTA_ID_IMOVEL = "ImovelServicesImpl::pesquisarFichaCompletaImovel";
	public static final String MAPA_FILTRO_IMOVEL = "ImovelServicesImpl::listaImoveisPorFiltro";
	public static final String MAPA_DATA_GRID = "PaginacaoServiceImpl::listarPorFiltro";
	
	// Variaveis de contexto de busca pagina
	// NOTA: Algumas destas constantes podem existir no Constantes.java do BackEndServices
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
	
	//Codigo especial do plano gratuito
	public static final int CODIGO_ESPECIAL_PLANO_GRATUITO = 1;
	
	public static final String FORMA_PGTO_PARCIAL = "P";
	public static final String FORMA_PGTO_TOTAL = "T";
	public static final String FORMA_PGTO_PARCIAL_STR = "SINAL DE 50% NA PR\u00c9-RESERVA + 50% DIRETAMENTE AO LOCADOR ";
	public static final String FORMA_PGTO_TOTAL_STR = "SINAL DE 100% NA PR\u00c9-RESERVA";
	
	// Constantes para troca do formulario do pagseguro
	public static final String PS_EMAIL_ALUGUE_RELAXE = "${EMAIL_ALUGUE_RELAXE}";
	public static final String PS_CODIGO_PLANO = "${CODIGO_PLANO}";
	public static final String PS_DESCRICAO_DO_PLANO = "${DESCRICAO_DO_PLANO}";
	public static final String PS_VALOR_TOTAL = "${VALOR_TOTAL}";
	public static final String PS_ID_FATURA = "${ID_FATURA}";
	public static final String PS_NOME_CLIENTE = "${NOME_CLIENTE}";
	public static final String PS_EMAIL_CLIENTE = "${EMAIL_CLIENTE}";

}
