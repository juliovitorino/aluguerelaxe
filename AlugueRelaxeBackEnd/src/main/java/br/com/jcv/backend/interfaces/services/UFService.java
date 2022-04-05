package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.uf.UFDTO;


/**
 * @author Julio
 * @version 1.0
 * @created 06-nov-2009 22:26:04
 */
public interface UFService<DTO> extends ApplicationService<DTO> {
	/**
	 * <h2>listarMaioresQtdeImoveisPorEstado()</h2>
	 * <p>Contrato com a reponsabilidade de totalizar todos os imoveis por estado 
	 * @return List - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	List<DTO> listarMaioresQtdeImoveisPorEstado() throws AlugueRelaxeException;
	/**
	 * <h2>listarEstadosMaisVisitados()</h2>
	 * <p>Contrato com a reponsabilidade de totalizar todos os imoveis por estado 
	 * @return List - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	List<UFDTO> listarEstadosMaisVisitados() throws AlugueRelaxeException;
	
	/**
	 * <h2>listarCidadesDaUF()</h2>
	 * <p>Listar as cidades de uma UF fornecida</p>
	 * @return List
	 * @exception AlugueRelaxeException
	 */
	List<CidadeDTO> listarCidadesDaUF(String uf) throws AlugueRelaxeException;
	/**
	 * <h2>listarCidadesDaUFComImoveis()</h2>
	 * <p>Listar as cidades de uma UF fornecida</p>
	 * @return List
	 * @exception AlugueRelaxeException
	 */
	List<CidadeDTO> listarCidadesDaUFComImoveis(String uf) throws AlugueRelaxeException;
	/**
	 * <h2>listarSumarizadoImoveisUF()</h2>
	 * <p>Listar os totais de imoveis das UFs</p>
	 * @return List
	 * @exception AlugueRelaxeException
	 */
	List<UFDTO> listarSumarizadoImoveisUF() throws AlugueRelaxeException;
	/**
	 * <h2>charterSumarizadoImoveisUF()</h2>
	 * <p>Listar os totais de imoveis das UFs</p>
	 * @return List
	 * @exception AlugueRelaxeException
	 */
	String charterSumarizadoImoveisUF() throws AlugueRelaxeException;
	/**
	 * <h2>ProcurarUFCidadeItem()</h2>
	 * <p>Retornar uma uf x cidade item</p>
	 * @return CidadeUFDTO
	 * @exception AlugueRelaxeException
	 */
	CidadeUFDTO ProcurarUFCidadeItem(long idUfCidadeItem) throws AlugueRelaxeException;

}