package br.com.jcv.backend.interfaces.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.GeoLocalizacaoDTO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelPlanoDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;


/**
 * <h1>ImovelBusiness</h1>
 * <p>Interface responsável por contratos que a Implementação de 
 * negócio de Imovel deve realizar.
 * </p>
 * @author julio
 * @version 1.0
 */
public interface ImovelBusiness<DTO> extends BusinessObject<DTO> {
	
    public static final String TIPO_PROPRIEDADE_CASA = "C";
    public static final String TIPO_PROPRIEDADE_APARTAMENTO = "A";
    public static final String TIPO_PROPRIEDADE_HOTEL = "H";
    public static final String TIPO_PROPRIEDADE_CHACARA = "X";
    public static final String TIPO_PROPRIEDADE_FLAT = "F";
    public static final String TIPO_PROPRIEDADE_FAZENDA = "Z";
    public static final String TIPO_PROPRIEDADE_SITIO = "S";
    public static final String TIPO_PROPRIEDADE_CHALE = "L";
    public static final String TIPO_PROPRIEDADE_POUSADA = "P";

    public static final String STATUS_ATIVO = "A";
    public static final String STATUS_INATIVO = "I";
    public static final String STATUS_PENDENTE = "P";

	long contarImoveisPorEstado(DAOFactory daofactory,String uf) throws AlugueRelaxeException;
	List<ImovelFichaCompletaDTO> listaImoveisPorEstado(DAOFactory daofactory,String uf,int pagina,int regPagina) throws AlugueRelaxeException;
	void atualizaVisitasImovel(DAOFactory daofactory, long codigoImovel, String origemAcesso) throws AlugueRelaxeException;
	List<CidadeUFDTO> listarQtdeImoveisCidadeUF(DAOFactory daofactory) throws AlugueRelaxeException;
	ContatoClienteDTO gravarContatoComAnunciante(DAOFactory daofactory, ContatoClienteDTO ccdto) throws AlugueRelaxeException;
	List<ContatoClienteDTO> listarContatosAnunciantesPorStatus(DAOFactory daofactory, String status) throws AlugueRelaxeException;
	ContatoClienteDTO procurarContatoAnunciante(DAOFactory daofactory, String id) throws AlugueRelaxeException;
	void mudaStatusContatoAnunciante(DAOFactory daoFactory,String id, String acao) throws AlugueRelaxeException;
	void mudaStatusThread(DAOFactory daoFactory,String id, String status) throws AlugueRelaxeException;
	List<ImovelDTO> buscarTodas(DAOFactory daoFactory,ClienteDTO clientedto) throws AlugueRelaxeException;
	ImovelFichaCompletaDTO incluir(DAOFactory daofactory, ImovelFichaCompletaDTO dto) throws AlugueRelaxeException;
	ImovelPlanoDTO incluir(DAOFactory daofactory, long codigoImovel, ImovelPlanoDTO dto) throws AlugueRelaxeException;
	ImovelPlanoDTO incluirAdesao(DAOFactory daofactory, long codigoImovel, ImovelPlanoDTO dto) throws AlugueRelaxeException;
	ImovelPlanoDTO procurarPlanoFinanceiro(DAOFactory daofactory, long codigoImovel) throws AlugueRelaxeException;
	void atualizarFichaImovel(DAOFactory daofactory, ImovelFichaCompletaDTO dto) throws AlugueRelaxeException;
	List<ImovelDTO> listarUltimosImoveisCadastrados(DAOFactory daoFactory, int qtdeAnuncios) throws AlugueRelaxeException;
	void adicionarImagemImovel(DAOFactory daoFactory, ImovelImagemVideoDTO iivdto) throws AlugueRelaxeException;
	long contarImoveisPorFiltro(DAOFactory daofactory,Map<String,String> param) throws AlugueRelaxeException;
	List<ImovelFichaCompletaDTO> listaImoveisPorFiltro(DAOFactory daofactory,Map<String,String> param,int pagina,int regPagina) throws AlugueRelaxeException;
	void atualizarGeoLocalizacaoImovel(DAOFactory daofactory, ImovelFichaCompletaDTO dto) throws AlugueRelaxeException;
	GeoLocalizacaoDTO procurarGeoLocalizacao(DAOFactory daofactory, long codigoImovel) throws AlugueRelaxeException;
	long contarImoveisPorCidade(DAOFactory daofactory,int cidade) throws AlugueRelaxeException;
	List<ImovelFichaCompletaDTO> listaImoveisPorCidade(DAOFactory daofactory,int cidade,int pagina,int regPagina) throws AlugueRelaxeException;
	void enviarImagensLixeira(DAOFactory daofactory, long idImovel, List<ImovelImagemVideoDTO> lst) throws AlugueRelaxeException;
	void atualizarTarifaImovel(DAOFactory daofactory, ImovelFichaCompletaDTO dto) throws AlugueRelaxeException;
	String charterDistribuicaoVisitasImovel(DAOFactory daoFactory, ImovelFichaCompletaDTO ifcdto, int ano)  throws AlugueRelaxeException;
	List<Long> listarImoveisParaSorteio(DAOFactory daoFactory) throws AlugueRelaxeException;
	List<Long> listarImoveisParaSorteioPD(DAOFactory daoFactory) throws AlugueRelaxeException;
	Date obterMaiorDataPublicidade(DAOFactory daoFactory, String tipo) throws AlugueRelaxeException;
	PublicidadeImovelDTO criarPublicidade(DAOFactory daoFactory, PublicidadeImovelDTO pub, long idPlano, boolean aplicaDesconto) throws AlugueRelaxeException;
	void liberarPublicidade(DAOFactory daoFactory, PublicidadeImovelDTO idPublicidade) throws AlugueRelaxeException;
	void liberarPublicidade(DAOFactory daoFactory, long idFatura) throws AlugueRelaxeException;
	void atualizarStatusSorteio(DAOFactory daoFactory, ImovelFichaCompletaDTO ifcdto, String novoStatus) throws AlugueRelaxeException;
	void atualizarStatusSorteioPD(DAOFactory daoFactory, ImovelFichaCompletaDTO ifcdto, String novoStatus) throws AlugueRelaxeException;
	List<ImovelDTO> buscarTodas(DAOFactory daoFactory, String indPatrocinadorColaborador) throws AlugueRelaxeException;
	void atualizarStatusImovel(DAOFactory daoFactory, long codImovel, String novoStatus) throws AlugueRelaxeException;
	List<Long> listarImoveisParaNegativacao(DAOFactory daoFactory) throws AlugueRelaxeException;
	void negativarImovel(DAOFactory daoFactory, long idImovel, double pesoNegativacao) throws AlugueRelaxeException;
	ImovelPlanoDTO pagarFatura(DAOFactory daofactory, long idFatura) throws AlugueRelaxeException;
	List<Long> listarImoveisFuraFila(DAOFactory daoFactory) throws AlugueRelaxeException;
	List<Long> listarImoveisPatrocinadores(DAOFactory daoFactory) throws AlugueRelaxeException;
	void atualizaSaldoFuraFila(DAOFactory daoFactory, long idImovel, double vlr) throws AlugueRelaxeException;
	long contarImoveisSorteio(DAOFactory daofactory,String tipoSorteio) throws AlugueRelaxeException;
	List<Long> listarImoveis(DAOFactory daoFactory, String status) throws AlugueRelaxeException;
	void atualizaIndicadorColaboracao(DAOFactory daofactory, ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException;
	void atualizarStatusImovelColaborador(DAOFactory daoFactory, long codImovel, String novoStatus, double valor) throws AlugueRelaxeException;
	List<Long> listarColaboradorInconsistente(DAOFactory daoFactory) throws AlugueRelaxeException;
	List<Long> listarIDImoveis(DAOFactory daoFactory, String uf, String cidade) throws AlugueRelaxeException;
	long ImovelIDProximoOferecimento(DAOFactory daoFactory, String uf, String cidade) throws AlugueRelaxeException;
	void atualizarOferecimento(DAOFactory daoFactory, long id) throws AlugueRelaxeException;
	int contarImoveisCliente(DAOFactory daoFactory, long idCliente) throws AlugueRelaxeException;
	List<Long> listaImoveisPorCidadeUf(DAOFactory daoFactory, long idCidadeUf, int pagina, int regPagina) throws AlugueRelaxeException;
	long contarImoveisPorCidadeUf(DAOFactory daoFactory, long idCidadeUf) throws AlugueRelaxeException;
	

	@Deprecated
	void criarPublicidade(DAOFactory daoFactory, PublicidadeImovelDTO pub) throws AlugueRelaxeException;
	@Deprecated
	void atualizaVisitasImovel(DAOFactory daofactory, long codigoImovel) throws AlugueRelaxeException;
	
}