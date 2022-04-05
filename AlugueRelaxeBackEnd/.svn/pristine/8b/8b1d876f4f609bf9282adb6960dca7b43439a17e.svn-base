package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.uf.UFDTO;

/**
 * <h1>UFBusiness</h1>
 * <p>Interface responśavel por contratos que a Implementação de 
 * negócio de Mensagem deve realizar.
 * </p>
 * @author julio
 * @version 1.0
 */
public interface UFBusiness<DTO> extends BusinessObject<DTO> {
	/**
	 * <h2>listarMaioresQtdeImoveisPorEstado()</h2>
	 * Contrato com a finalidade de totalizar os imoveis por estado.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @return List - Contendo os dados que foram totalizados.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	List<DTO> listarMaioresQtdeImoveisPorEstado(DAOFactory daofactory) throws AlugueRelaxeException;
	/**
	 * <h2>listarEstadosMaisVisitados()</h2>
	 * Contrato com a finalidade de retornar até os 4 estados com maior 
	 * número de visitas aos imóveis.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @return List - Contendo os dados que foram totalizados.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	List<UFDTO> listarEstadosMaisVisitados(DAOFactory daofactory) throws AlugueRelaxeException;
	
	List<UFDTO> listarSumarizadoImoveisUF(DAOFactory daofactory) throws AlugueRelaxeException;
	String charterSumarizadoImoveisUF(DAOFactory daofactory) throws AlugueRelaxeException;
	
	List<CidadeDTO> listarCidadesDaUF(DAOFactory daofactory, String uf) throws AlugueRelaxeException;
	List<CidadeDTO> listarCidadesDaUFComImoveis(DAOFactory daofactory, String uf) throws AlugueRelaxeException;
	
	CidadeUFDTO ProcurarUFCidadeItem(DAOFactory daofactory, long idUfCidadeItem) throws AlugueRelaxeException;
}
