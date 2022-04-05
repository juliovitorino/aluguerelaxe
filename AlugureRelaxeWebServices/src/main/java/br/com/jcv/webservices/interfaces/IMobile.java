package br.com.jcv.webservices.interfaces;

import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.jcv.backend.buscapagina.EnvelopePaginacaoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IMobile {
	
	/**
	 * <h2>listCidadesDaUfComImoves</h2>
	 * <p>Método responsável por listar as cidades da UF com pelo
	 * menos um imovel</p>
	 * @param String
	 * @return String
	 * @throws AlugueRelaxeException
	 */
	@WebMethod
	String listCidadesDaUfComImoves(String uf);
	
	/**
	 * <h2>listaImoveisPorEstado</h2>
	 * <p>Método responsável por listar os imóveis por UF de forma paginada</p>
	 * @param String
	 * @param int
	 * @param int
	 * @return String
	 * @throws AlugueRelaxeException
	 */
	@WebMethod
	String listaImoveisPorEstado(String uf, int pagina, int regPagina) throws AlugueRelaxeException;
	/**
	 * <h2>listaImoveisPorCidade</h2>
	 * <p>Método responsável por listar os imóveis por cidade de forma paginada</p>
	 * @param int
	 * @param int
	 * @param int
	 * @return String
	 * @throws AlugueRelaxeException
	 */
	@WebMethod
	String listaImoveisPorCidade(int cidade, int pagina, int regPagina) throws AlugueRelaxeException;
	/**
	 * <h2>apresentarFichaCompletaImovel</h2>
	 * <p>Método responsável por localizar a ficha completa do imóvel e
	 * atualizar o contador de visitas.</p>
	 * @param codigoImovel
	 * @return ImovelFichaCompletaDTO
	 * @throws AlugueRelaxeException
	 */
	@WebMethod
	String apresentarFichaCompletaImovel(long codigoImovel, String origemAcesso) throws AlugueRelaxeException;
	
	/**
	 * <h2>getPainelPublicidade</h2>
	 * <p>Busca publicidades de primeira pagina no cache</p>
	 * @return String
	 */
	@WebMethod
	String getPainelPublicidade();

	/**
	 * <h2>getPainelPublicidadeDestaque</h2>
	 * <p>Busca publicidades de painel destaque no cache</p>
	 * @return String
	 */
	@WebMethod
	String getPainelPublicidadeDestaque();

}
