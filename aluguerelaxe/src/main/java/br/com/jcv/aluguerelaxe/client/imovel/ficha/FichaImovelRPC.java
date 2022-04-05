package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.administracao.console.geolocalizacaowizard.GeoLocalizacaoVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.publicidades.VendaPublicidadeVO;
import br.com.jcv.aluguerelaxe.client.charter.CharterVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoFaturaVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.imovel.local.ClassificacaoVO;
import br.com.jcv.aluguerelaxe.client.imovel.local.LocalItemVO;
import br.com.jcv.aluguerelaxe.client.imovel.local.LocalVO;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ContatoAnuncianteThreadVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * <h1>FichaImovelRPC</h1>
 * <p>Interface para os métodos que estarão expostos para
 * chamadas RPC para o painel de publicidade de destaque. 
 * </p>
 * @author julio
 * 
 */
@RemoteServiceRelativePath("fichaimovelrpc")
public interface FichaImovelRPC extends RemoteService {
	/**
	 * <h2>listarComentarioImovel</h2>
	 * <p>Listar os comentarios realizados para o imovel</p>
	 * 
	 * @param long
	 * @return List
	 */
	public List<ContatoClienteVO> listarComentarioImovel(long idImovel)  throws AlugueRelaxeFrontException;
	/**
	 * <h2>pesquisarFichaCompletaImovel</h2>
	 * <p>Recupera a ficha completa do imovel no backend</p>
	 * 
	 * @param long
	 * @return ImovelFichaCompletaVO
	 */
	public ImovelFichaCompletaVO pesquisarFichaCompletaImovel(long codigoImovel);
	/**
	 * <h2>apresentarFichaCompletaImovel</h2>
	 * <p>Recupera a ficha completa do imovel no backend</p>
	 * 
	 * @param long
	 * @return ImovelFichaCompletaVO
	 */
	@Deprecated
	public ImovelFichaCompletaVO apresentarFichaCompletaImovel(long codigoImovel);
	
	/**
	 * <h2>apresentarFichaCompletaImovel</h2>
	 * <p>Recupera a ficha completa do imovel no backend</p>
	 * 
	 * @param long
	 * @return ImovelFichaCompletaVO
	 */
	public ImovelFichaCompletaVO apresentarFichaCompletaImovel(long codigoImovel, String origemAcesso);
	
	/**
	 * Implementação do método para o cliente entrar em contato com o anunciante
	 * para saber mais informações sobre o imóvel.
	 *
	 * @param ccvo
	 * @return Comentar aqui.
	 */
	public ContatoClienteVO entrarEmContatoComAnunciante(ContatoClienteVO ccvo) throws AlugueRelaxeFrontException;
	
	/**
	 * Contrato de implementação para a inclusão de um novo imóvel 
	 *
	 * @param ifcvo
	 * @return ImovelFichaCompletaVO
	 * @throws AlugueRelaxeFrontException
	 */
	public ImovelFichaCompletaVO adicionarImovelCarteira(ImovelFichaCompletaVO ifcvo) throws AlugueRelaxeFrontException;

	/**
	 * Atualizar a ficha do imovel 
	 *
	 * @param ifcvo
	 * @return ImovelFichaCompletaVO
	 * @throws AlugueRelaxeFrontException
	 */
	public ImovelFichaCompletaVO atualizarImovelCarteira(ImovelFichaCompletaVO ifcvo) throws AlugueRelaxeFrontException;
	/**
	 * Atualizar a Geo-Localizacao do imovel 
	 *
	 * @param ifcvo
	 * @return VOPadrao
	 * @throws AlugueRelaxeFrontException
	 */
	public VOPadrao atualizarGeoLocalizacaoImovel(ImovelFichaCompletaVO ifcvo) throws AlugueRelaxeFrontException;
	/**
	 * Atualizar a Geo-Localizacao do imovel 
	 *
	 * @param ifcvo
	 * @return VOPadrao
	 * @throws AlugueRelaxeFrontException
	 */
	public VOPadrao notificacaoGaleriaFotoVazia(ImovelFichaCompletaVO ifcvo) throws AlugueRelaxeFrontException;
	/**
	 * Indicar imovel a um amigo 
	 *
	 * @param ifcvo
	 * @return VOPadrao
	 * @throws AlugueRelaxeFrontException
	 */
	public VOPadrao indicarImovelAmigo(ImovelFichaCompletaVO ifcvo, IndicarAmigoVO iavo) throws AlugueRelaxeFrontException;
	/**
	 * Enviar imagens para lixeira 
	 *
	 * @param ifcvo
	 * @return VOPadrao
	 * @throws AlugueRelaxeFrontException
	 */
	public VOPadrao enviarImagensLixeira(long idImovel, List<ImovelImagemVideoVO> iivvo) throws AlugueRelaxeFrontException;
	/**
	 * Atualizar tarifas do imovel
	 *
	 * @param ifcvo
	 * @return VOPadrao
	 * @throws AlugueRelaxeFrontException
	 */
	VOPadrao atualizarTarifaImovel(ImovelFichaCompletaVO ifcvo) throws AlugueRelaxeFrontException;
	/**
	 * Charter com a distribuicao de visitas ao imovel em determinado ano
	 *
	 * @param ImovelFichaCompletaVO
	 * @param int
	 * @return CharterVO
	 * @throws AlugueRelaxeFrontException
	 */
	CharterVO charterDistribuicaoVisitasImovel(ImovelFichaCompletaVO ifcvo, int ano) throws AlugueRelaxeFrontException;

	/**
	 * <h2>avaliarAnuncio</h2>
	 * <p>Avisa ao anunciante sobre uma avaliacao do seu imovel</p>
	 * @throws AlugueRelaxeFrontException
	 */	
	VOPadrao avaliarAnuncio(AvaliacaoAnuncioImovelVO avaliacao) throws AlugueRelaxeFrontException;
	/**
	 * <h2>pesquisarUltimaFatura</h2>
	 * <p>Método responsável por localizar o registro da ultima fatura de um imovel em determinado plano</p>
	 * @param long - Codigo do imovel
	 * @param String - Tipo do plano - pode ser: (N)ormal; (P)ublicidade Primeira Pagina; Publicidade (D)estaque e (E) Especial IMOBILIARIAS
	 * @return ImovelPlanoFaturaVO
	 * @throws AlugueRelaxeException
	 */
	ImovelPlanoFaturaVO pesquisarUltimaFatura(long codigoImovel, String tipo) throws AlugueRelaxeFrontException;	 
	/**
	 * <h2>pesquisarFatura</h2>
	 * <p>Método responsável por localizar o registro da fatura de um imovel em determinado plano</p>
	 * @param long - Codigo da fatura
	 * @param String - Tipo do plano - pode ser: (N)ormal; (P)ublicidade Primeira Pagina; Publicidade (D)estaque e (E) Especial IMOBILIARIAS
	 * @return ImovelPlanoFaturaVO
	 * @throws AlugueRelaxeException
	 */
	ImovelPlanoFaturaVO pesquisarFatura(long idFatura) throws AlugueRelaxeFrontException;	 
	/**
	 * <h2>LiquidarFatura</h2>
	 * <p>Método responsável por liquidar a fatura de um plano</p>
	 * @param long
	 * @param String 
	 * @return ImovelPlanoFaturaVO
	 * @throws AlugueRelaxeException
	 */
	ImovelPlanoFaturaVO liquidarFatura(long idFatura, String tipo) throws AlugueRelaxeFrontException;	 
	/**
	 * <h2>listarLocal</h2>
	 * <p>Método responsável por listar as possibilidades de locais aos redor do imovel</p>
	 * @return LocalVO
	 * @throws AlugueRelaxeException
	 */
	List<LocalVO> listarLocal() throws AlugueRelaxeFrontException;	 
	/**
	 * <h2>listarClassificacaoLocal</h2>
	 * <p>Método responsável por listar as possibilidades de locais por classificacao aos redor do imovel</p>
	 * @return LocalVO
	 * @throws AlugueRelaxeException
	 */
	List<ClassificacaoVO> listarClassificacaoLocal() throws AlugueRelaxeFrontException;	 
	/**
	 * <h2>listarLocalItem</h2>
	 * <p>Método responsável por listar varios LocalItem</p>
	 * @return List<LocalItemVO>
	 * @throws AlugueRelaxeException
	 */
	List<LocalItemVO> listarLocalItem(long idUfCidade, List<LocalVO> lstlocal, List<ClassificacaoVO> lstclass, GeoLocalizacaoVO geolocalizacao) throws AlugueRelaxeFrontException;	 
	
	/**
	 * <h2>migrarPlanoImovel</h2>
	 * <p>Método responsável por realizar a migracao de um plano de anuncio</p>
	 * @param codImovel
	 * @param codNovoPlano
	 * @return VOPadrao
	 * @throws AlugueRelaxeException
	 */
	VOPadrao migrarPlanoImovel(long codImovel, long codNovoPlano) throws AlugueRelaxeFrontException;
	
	/**
	 * <h2>adicionarVideoImovel</h2>
	 * <p>Método responsável por realizar a inclusao de um video para o imovel</p>
	 * @param ImovelImagemVideoDTO
	 * @return VOPadrao
	 * @throws AlugueRelaxeException
	 */
	VOPadrao adicionarVideoImovel(ImovelImagemVideoVO vo)	throws AlugueRelaxeFrontException;

	/**
	 * <h2>renovarPlano</h2>
	 * <p>Método responsável por realizar a renovacao de um plano de imovel</p>
	 * @param long
	 * @return ImovelPlanoFaturaVO
	 * @throws AlugueRelaxeFrontException
	 */
	ImovelPlanoFaturaVO renovarPlano(long idImovel) throws AlugueRelaxeFrontException;
	
	/**
	 * <h2>criarPublicidade</h2>
	 * <p>Método responsável por realizar a criacao de uma publicidade</p>
	 * @param VendaPublicidadeVO
	 * @return PublicidadeImovelVO
	 * @throws AlugueRelaxeFrontException
	 */
	PublicidadeImovelVO criarPublicidade(VendaPublicidadeVO vo) throws AlugueRelaxeFrontException;
	
	/**
	 * <h2>inativarAnuncio</h2>
	 * <p>Método responsável por realizar inativacao da publicidade</p>
	 * @param long
	 * @return VOPadrao
	 * @throws AlugueRelaxeFrontException
	 */
	VOPadrao inativarAnuncio(long idImovel)  throws AlugueRelaxeFrontException;
	
	/**
	 * <h2>pesquisarFichaCompletaImovel</h2>
	 * <p>Recupera a ficha completa do contato com anunciante</p>
	 * 
	 * @param long
	 * @return ContatoClienteVO
	 */
	ContatoClienteVO pesquisarContatoAnunciante(String idhash) throws AlugueRelaxeFrontException;

	/**
	 * <h2>enviarNovaPergunta</h2>
	 * <p>Envia uma nova pergunta</p>
	 * 
	 * @param ContatoClienteVO
	 * @param ContatoAnuncianteThreadVO
	 * @return ContatoAnuncianteThreadVO
	 */
	ContatoAnuncianteThreadVO enviarNovaPergunta(ContatoClienteVO ccvo, ContatoAnuncianteThreadVO vo) throws AlugueRelaxeFrontException;
	/**
	 * <h2>enviarResposta</h2>
	 * <p>Envia uma resposta para a thread pendente</p>
	 * 
	 * @param ContatoClienteVO
	 * @param ContatoAnuncianteThreadVO
	 * @return ContatoAnuncianteThreadVO
	 */
	ContatoAnuncianteThreadVO enviarResposta(ContatoClienteVO ccvo, ContatoAnuncianteThreadVO vo) throws AlugueRelaxeFrontException;
	/**
	 * <h2>prepararRealizarPagamento</h2>
	 * <p>Retorna informacoes completas para preparacao da realizacao de pagamento</p>
	 * 
	 * @param long
	 * @return PublicidadeImovelVO
	 */
	PublicidadeImovelVO prepararRealizarPagamento(long idImovel) throws AlugueRelaxeFrontException;
		
	
	public static class Util {

		public static FichaImovelRPCAsync getInstance() {

			return GWT.create(FichaImovelRPC.class);
		}
	}
	

}
