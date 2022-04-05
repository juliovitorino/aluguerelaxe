package br.com.jcv.backend.interfaces.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.AvaliacaoAnuncioImovelDTO;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.IndicarAmigoDTO;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;


/**
 * @author Julio
 * @version 1.0
 * @created 06-nov-2009 22:26:36
 */
public interface ImovelService<DTO> extends ApplicationService<DTO> {

	/**
	 * <h2>contarImoveisCliente</h2>
	 * <p>Método responsável por contar todos os imóveis disponiveis para
	 * um determinado cliente.
	 * </p>
	 * @param int
	 * @return int
	 * @throws AlugueRelaxeException
	 */
	int contarImoveisCliente(long idCliente) throws AlugueRelaxeException;
	/**
	 * <h2>contarGradePublicidadePP</h2>
	 * <p>Método responsável por listar todos os imóveis disponí­veis para
	 * o painel publicidade na primeira página do site.
	 * </p>
	 * @param Date
	 * @param tipo
	 * @return int
	 * @throws AlugueRelaxeException
	 */
	int contarGradePublicidade(Date d, String tipo) throws AlugueRelaxeException;
	/**
	 * <h2>Listar os IDs de imoveis que pertencem a uma determinada uf x cidade</h2>
	 * @param uf
	 * @param cidade
	 * @return
	 * @throws AlugueRelaxeException
	 */
	List<Long> listarIDImoveis(String uf, String cidade) throws AlugueRelaxeException;
	/**
	 * <h2>retorna o imovel com menor ID em IMOV_SQ_OFERECIMENTO para oferecimento de determinada uf x cidade  </h2>
	 * @param uf
	 * @param cidade
	 * @return long
	 * @throws AlugueRelaxeException
	 */
	long ImovelIDProximoOferecimento(String uf, String cidade) throws AlugueRelaxeException;
	
	/**
	 * <h2>atualizarStatusImovel</h2>
	 * <p>Atualizar o status do imovel</p>
	 * @return DTOPadrao
	 * @throws AlugueRelaxeException
	 */
	DTOPadrao atualizarStatusImovel(long codImovel, String novoStatus) throws AlugueRelaxeException;

	/**
	 * <h2>inativarAnuncio</h2>
	 * <p>Inativar o anuncio de um determinado imovel</p>
	 * @return DTOPadrao
	 * @throws AlugueRelaxeException
	 */
	DTOPadrao inativarAnuncio(long idImovel) throws AlugueRelaxeException;

	/**
	 * <h2>listImovelPublicidadePaginaPrincipal</h2>
	 * <p>Método responsável por listar todos os imóveis disponí­veis para
	 * o painel publicidade na primeira página do site.
	 * </p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	List<? extends DTO> listImovelPublicidadePaginaPrincipal() throws AlugueRelaxeException;
	/**
	 * <h2>listImovelSuperBanner</h2>
	 * <p>Método responsável por listar todos os imóveis disponí­veis para
	 * o painel publicidade do SuperBanner.
	 * </p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	List<? extends DTO> listImovelSuperBanner() throws AlugueRelaxeException;

	/**
	 * <h2>listImovelPublicidadePaginaDestaque</h2>
	 * <p>Método responsável por listar todos os imóveis disponí­veis para
	 * o painel publicidade na primeira destaque do site.
	 * </p>
	 * @return List
	 * @throws AlugueRelaxeException 
	 */
	List<? extends DTO> listImovelPublicidadePaginaDestaque() throws AlugueRelaxeException;

	/**
	 * <h2>pesquisarFichaCompletaImovel</h2>
	 * <p>Método responsável por localizar a ficha completa do imóvel</p>
	 * @param codigoImovel
	 * @return ImovelFichaCompletaDTO
	 * @throws AlugueRelaxeException
	 */
	ImovelFichaCompletaDTO pesquisarFichaCompletaImovel(long codigoImovel) throws AlugueRelaxeException;

	/**
	 * <h2>apresentarFichaCompletaImovel</h2>
	 * <p>Método responsável por localizar a ficha completa do imóvel e
	 * atualizar o contador de visitas.</p>
	 * @param codigoImovel
	 * @return ImovelFichaCompletaDTO
	 * @throws AlugueRelaxeException
	 */
	@Deprecated
	ImovelFichaCompletaDTO apresentarFichaCompletaImovel(long codigoImovel) throws AlugueRelaxeException;
	/**
	 * <h2>apresentarFichaCompletaImovel</h2>
	 * <p>Método responsável por localizar a ficha completa do imóvel e
	 * atualizar o contador de visitas.</p>
	 * @param codigoImovel
	 * @return ImovelFichaCompletaDTO
	 * @throws AlugueRelaxeException
	 */
	ImovelFichaCompletaDTO apresentarFichaCompletaImovel(long codigoImovel, String origemAcesso) throws AlugueRelaxeException;

	/**
	 * <h2>listaImoveisPorEstado</h2>
	 * <p>Método responsável por listar os imóveis por UF de forma paginada</p>
	 * @param String
	 * @param int
	 * @param int
	 * @return List<ImovelFichaCompletaDTO>
	 * @throws AlugueRelaxeException
	 */
	List<ImovelFichaCompletaDTO> listaImoveisPorEstado(String uf, int pagina, int regPagina) throws AlugueRelaxeException;
	/**
	 * <h2>listaImoveisPorCidade</h2>
	 * <p>Método responsável por listar os imóveis por cidade de forma paginada</p>
	 * @param int
	 * @param int
	 * @param int
	 * @return List<ImovelFichaCompletaDTO>
	 * @throws AlugueRelaxeException
	 */
	List<ImovelFichaCompletaDTO> listaImoveisPorCidade(int cidade, int pagina, int regPagina) throws AlugueRelaxeException;
	/**
	 * <h2>contarImoveisSorteio</h2>
	 * <p>Contar imoveis pelo tipo de sorteio</p>
	 * @param String
	 * @return long
	 * @throws AlugueRelaxeException
	 */
	long contarImoveisSorteio(String tipoSorteio) throws AlugueRelaxeException;
	/**
	 * <h2>contarImoveisPorEstado</h2>
	 * <p>Método responsável por contar os imóveis por UF</p>
	 * @param String
	 * @return long
	 * @throws AlugueRelaxeException
	 */
	long contarImoveisPorEstado(String uf) throws AlugueRelaxeException;
	/**
	 * <h2>contarImoveisPorCidade</h2>
	 * <p>Método responsável por contar os imóveis por cidade</p>
	 * @param
	 * @return long
	 * @throws AlugueRelaxeException
	 */
	long contarImoveisPorCidade(int cidade) throws AlugueRelaxeException;
	/**
	 * <h2>listaImoveisPorFiltro</h2>
	 * <p>Método responsável por listar os imóveis por filtro customizado de forma paginada</p>
	 * @param Map
	 * @param int
	 * @param int
	 * @return List<ImovelFichaCompletaDTO>
	 * @throws AlugueRelaxeException
	 */
	List<ImovelFichaCompletaDTO> listaImoveisPorFiltro(Map<String,String> param, int pagina, int regPagina) throws AlugueRelaxeException;
	/**
	 * <h2>contarImoveisPorFiltro</h2>
	 * <p>Método responsável por contar os imóveis por filtro customizado</p>
	 * @param Map
	 * @return long
	 * @throws AlugueRelaxeException
	 */
	long contarImoveisPorFiltro(Map<String,String> param) throws AlugueRelaxeException;
	

	
	/**
	 * <h2>listarQtdeImoveisCidadeUF</h2>
	 * <p>Método responsável por listar as quantidades de imóveis 
	 * por Cidade - UF</p>
	 * @param
	 * @return List<CidadeUFDTO>
	 * @throws AlugueRelaxeException
	 */
	List<CidadeUFDTO> listarQtdeImoveisCidadeUF() throws AlugueRelaxeException;
	
	/**
	 * <h2>entrarEmContatoComAnunciante</h2>
	 * <p>Método responsável por permitir o contato entre o proposto e o 
	 * anunciante do imóvel
	 * </p>
	 * @param ccdto
	 * @throws AlugueRelaxeException
	 */
	ContatoClienteDTO entrarEmContatoComAnunciante(ContatoClienteDTO ccdto) throws AlugueRelaxeException;
	
	/**
	 * <h2>listarUltimosImoveisCadastrados</h2>
	 * <p>Listar os últimos imóveis cadastrados no site</p>
	 * @throws AlugueRelaxeException
	 */
	List<ImovelFichaCompletaDTO> listarUltimosImoveisCadastrados() throws AlugueRelaxeException;
	
	DTOPadrao adicionarImagemImovel(ImovelImagemVideoDTO iivdto) throws AlugueRelaxeException;
	List<ContatoClienteDTO> listarContatosAnunciantesPorStatus(String status) throws AlugueRelaxeException;
	List<ContatoClienteDTO> listarContatosAnunciantesPendentes() throws AlugueRelaxeException;
	List<ContatoClienteDTO> listarContatosAnunciantesLiberados() throws AlugueRelaxeException;
	/**
	 * <h2>listarGaleriaFotosImoveis</h2>
	 * <p>Lista a galeria de imagens dos imoveis de um determinado proprietario</p>
	 * @throws AlugueRelaxeException
	 */	List<ImovelFichaCompletaDTO> listarGaleriaFotosImoveis(long clienteId) throws AlugueRelaxeException;
	/**
	 * <h2>adicionarImovelCarteira</h2>
	 * <p>Adiciona um novo imovel na carteira do proprietario</p>
	 * @throws AlugueRelaxeException
	 */
	 ImovelFichaCompletaDTO adicionarImovelCarteira(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException;
	/**
	 * <h2>atualizarImovelCarteira</h2>
	 * <p>Atualiza os dados do imovel</p>
	 * @throws AlugueRelaxeException
	 */
	 ImovelFichaCompletaDTO atualizarImovelCarteira(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException;
	/**
	 * <h2>liberarEmailContatoAnunciante</h2>
	 * <p>Liberar email de contato entre anunciante e cliente apos verificacao de moderacao</p>
	 * @throws AlugueRelaxeException
	 */
	DTOPadrao liberarEmailContatoAnunciante(String id, String acao) throws AlugueRelaxeException;
	List<ImovelCaracteristicaDTO> listarCaracteristicas(long imovelId, String tipo) throws AlugueRelaxeException;
	/**
	 * <h2>atualizarGeoLocalizacaoImovel</h2>
	 * <p>Atualizar coordenadas de latitude e longitude de um imovel</p>
	 * @throws AlugueRelaxeException
	 */
	DTOPadrao atualizarGeoLocalizacaoImovel(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException;
	/**
	 * <h2>emitirNotificacaoGaleriaFotoVazia</h2>
	 * <p>Enviar uma notificacao para o proprietario do imovel sobre imovel sem imagens</p>
	 * @throws AlugueRelaxeException
	 */
	DTOPadrao emitirNotificacaoGaleriaFotoVazia(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException;
	/**
	 * <h2>enviarIndicacaoImovelAmigo</h2>
	 * <p>Enviar uma indicacao de um imovel para um amigo</p>
	 * @throws AlugueRelaxeException
	 */
	DTOPadrao enviarIndicacaoImovelAmigo(ImovelFichaCompletaDTO ifcdto, IndicarAmigoDTO iadto) throws AlugueRelaxeException;
	/**
	 * <h2>enviarImagensLixeira</h2>
	 * <p>Enviar para a lixeira imagens de um determinado imovel</p>
	 * @throws AlugueRelaxeException
	 */
	DTOPadrao enviarImagensLixeira(long idImovel, List<ImovelImagemVideoDTO> lst) throws AlugueRelaxeException;
	/**
	 * <h2>atualizarTarifaImovel</h2>
	 * <p>Atualizar as tarifas de um determinado imovel</p>
	 * @throws AlugueRelaxeException
	 */
	DTOPadrao atualizarTarifaImovel(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException;
	/**
	 * <h2>charterDistribuicaoVisitasImovel</h2>
	 * <p>Recupera um grafico com a distribuicao das visitas ao imovel</p>
	 * @throws AlugueRelaxeException
	 */
	String charterDistribuicaoVisitasImovel(ImovelFichaCompletaDTO ifcdto, int ano) throws AlugueRelaxeException;
	/**
	 * <h2>obterDataInicioPublicidadePP</h2>
	 * <p>Obter a data de inicio da proxima publicidade PP</p>
	 * @throws AlugueRelaxeException
	 */
	Date obterDataInicioPublicidadePP() throws AlugueRelaxeException;

	/**
	 * <h2>obterDataInicioPublicidadePD</h2>
	 * <p>Obter a data de inicio da proxima publicidade PD</p>
	 * @throws AlugueRelaxeException
	 */
	Date obterDataInicioPublicidadePD() throws AlugueRelaxeException;
	
	/**
	 * <h2>listarImoveisSorteados</h2>
	 * <p>Listar os imoveis aptos a sorteio</p>
	 * @throws AlugueRelaxeException
	 */
	List<Long> listarImoveisSorteados() throws AlugueRelaxeException;
	/**
	 * <h2>listarImoveisSorteadosPD</h2>
	 * <p>Listar os imoveis aptos a sorteio</p>
	 * @throws AlugueRelaxeException
	 */
	List<Long> listarImoveisSorteadosPD() throws AlugueRelaxeException;
	/**
	 * <h2>criarPublicidade</h2>
	 * <p>Cria o registro de publicidade</p>
	 * @throws AlugueRelaxeException
	 */
	@Deprecated
	PublicidadeImovelDTO criarPublicidade(PublicidadeImovelDTO pub) throws AlugueRelaxeException;
	/**
	 * <h2>criarPublicidade</h2>
	 * <p>Cria o registro de publicidade</p>
	 * @throws AlugueRelaxeException
	 */
	PublicidadeImovelDTO criarPublicidade(PublicidadeImovelDTO pub, long idPlano) throws AlugueRelaxeException;
	/**
	 * <h2>criarPublicidade</h2>
	 * <p>Cria o registro de publicidade</p>
	 * @throws AlugueRelaxeException
	 */
	PublicidadeImovelDTO criarPublicidade(PublicidadeImovelDTO pub, long idPlano, boolean aplicarDesconto) throws AlugueRelaxeException;
	/**
	 * <h2>liberarPublicidade</h2>
	 * <p>Libera o registro de publicidade</p>
	 * @throws AlugueRelaxeException
	 */
	void liberarPublicidade(PublicidadeImovelDTO idPublicidade) throws AlugueRelaxeException;
	/**
	 * <h2>liberarPublicidade</h2>
	 * <p>Libera o registro de publicidade</p>
	 * @throws AlugueRelaxeException
	 */
	void liberarPublicidade(long idFatura) throws AlugueRelaxeException;
	/**
	 * <h2>atualizarStatusSorteio</h2>
	 * <p>Atualizar o status de sorteio de publicidade para um determinado imovel</p>
	 * @throws AlugueRelaxeException
	 */
	void atualizarStatusSorteio(ImovelFichaCompletaDTO ifcdto, String novoStatus) throws AlugueRelaxeException;
	/**
	 * <h2>atualizarStatusSorteioPD</h2>
	 * <p>Atualizar o status de sorteio de outros destaques para um determinado imovel</p>
	 * @throws AlugueRelaxeException
	 */
	void atualizarStatusSorteioPD(ImovelFichaCompletaDTO ifcdto, String novoStatus) throws AlugueRelaxeException;
	/**
	 * <h2>listarGaleriaFotosImoveis</h2>
	 * <p>Lista a galeria de imagens dos imoveis de um determinado tipo de colaborador</p>
	 * @throws AlugueRelaxeException
	 */	
	 List<ImovelFichaCompletaDTO> listarGaleriaFotosImoveis(String indPatrocinadorColaborador) throws AlugueRelaxeException;
	 
	/**
	 * <h2>avaliarAnuncio</h2>
	 * <p>Avisa ao anunciante sobre uma avaliacao do seu imovel</p>
	 * @throws AlugueRelaxeException
	 */	
	 DTOPadrao avaliarAnuncio(AvaliacaoAnuncioImovelDTO avaliacao) throws AlugueRelaxeException;
	 
	/**
	 * <h2>listarImoveisParaNegativacao</h2>
	 * <p>Listar imoveis para calculo de negativacao do anuncio</p>
	 * @throws AlugueRelaxeException
	 */	
	 List<Long> listarImoveisParaNegativacao() throws AlugueRelaxeException;
	 /**
	 * <h2>negativarImovel</h2>
	 * <p>Negativacao do anuncio</p>
	 * @throws AlugueRelaxeException
	 */	
	 void negativarImovel(long idImovel, double pesoNegativacao) throws AlugueRelaxeException;
	/**
	 * <h2>listarImoveis</h2>
	 * <p>Listar imoveis ativos</p>
	 * @throws AlugueRelaxeException
	 */	
	 List<Long> listarImoveis(String status) throws AlugueRelaxeException;
	/**
	 * <h2>listarImoveisFuraFila</h2>
	 * <p>Listar imoveis em processo de fura-fila ativado</p>
	 * @throws AlugueRelaxeException
	 */	
	 List<Long> listarImoveisFuraFila() throws AlugueRelaxeException;
	/**
	 * <h2>listarImoveisPatrocinadores</h2>
	 * <p>Listar imoveis patrocinadores ativados</p>
	 * @throws AlugueRelaxeException
	 */	
	 List<Long> listarImoveisPatrocinadores() throws AlugueRelaxeException;
 
	/**
	 * <h2>atualizaSaldoFuraFila</h2>
	 * <p>Atualiza saldo de um determinado imovel em processo de fura-fila</p>
	 * @throws AlugueRelaxeException
	 */	
	 DTOPadrao atualizaSaldoFuraFila(long idImovel, double vlr) throws AlugueRelaxeException;
	/**
	 * <h2>listPublicidadeDentroPrazo</h2>
	 * <p>Método responsável por listar todos os imoveis com publicidade dentro do prazo
	 * identificada pelo seu tipo
	 * </p>
	 * @param String
	 * @return List
	 * @throws AlugueRelaxeException 
	 */
	List<PublicidadeImovelDTO> listPublicidadeDentroPrazo(String tipo) throws AlugueRelaxeException;
	
	/**
	 * <h2>atualizarStatusImovelColaborador</h2>
	 * <p>Atualizar o status do imovel</p>
	 * @param long
	 * @param String
	 * @param double
	 * @return DTOPadrao
	 * @throws AlugueRelaxeException
	 */
	DTOPadrao atualizarStatusImovelColaborador(long codImovel, String novoStatus, double valor) throws AlugueRelaxeException;
	
	/**
	 * <h2>listarColaboradorInconsistente</h2>
	 * <p>Lista imoveis colaboradores em estado inconsistente</p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	List<Long> listarColaboradorInconsistente() throws AlugueRelaxeException;
	
	/**
	 * <h2>normalizarImovelColaborador</h2>
	 * <p>Retonar o imovel colaborador ao seu estado inicial</p>
	 * @param long
	 * @return DTOPadrao
	 * @throws AlugueRelaxeException
	 */
	DTOPadrao normalizarImovelColaborador(long codImovel) throws AlugueRelaxeException;
	/**
	 * <h2>atualizarOferecimento</h2>
	 * <p>Atualiza o sequenciador de oferecimento do imovel para visitantes</p>
	 * @param long
	 * @throws AlugueRelaxeException
	 */
	void atualizarOferecimento(long id) throws AlugueRelaxeException;
	/**
	 * <h2>prepararRealizarPagamento</h2>
	 * <p>Busca informacoes para preparacao de pagamento</p>
	 * @param long
	 * @return PublicidadeImovelDTO
	 * @throws AlugueRelaxeException
	 */
	PublicidadeImovelDTO prepararRealizarPagamento(long idImovel) throws AlugueRelaxeException;

	List<ImovelFichaCompletaDTO> listaImoveisPorCidadeUf(long idCidadeUf, int pagina, int regPagina) throws AlugueRelaxeException;
	long contarImoveisPorCidadeUf(long idCidadeUf) throws AlugueRelaxeException;
	
}