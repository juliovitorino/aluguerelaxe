package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

/**
 * <h1>CidadeBusiness</h1>
 * <p>Interface responśavel por contratos que a Implementação de 
 * negócio de Cidades deve realizar.
 * </p>
 * @author julio
 * @version 1.0
 */

public interface CidadeBusiness<DTO> extends BusinessObject<DTO> {
	/**
	 * <h2>listCidadesDaUfComImoveis()</h2>
	 * Contrato com a finalidade de retornar até os 6 cidades com maior 
	 * número de imóveis.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @return List - Contendo os dados que foram totalizados.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<DTO> listCidadesDaUfComImoveis(DAOFactory daofactory, String uf) throws AlugueRelaxeException;
	/**
	 * <h2>listCidadesDaUfComImoveis()</h2>
	 * Contrato com a finalidade de retornar até os 6 cidades com maior 
	 * número de imóveis.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @return List - Contendo os dados que foram totalizados.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<CidadeUFDTO> listCidadesDaUfComImoveis(DAOFactory daofactory) throws AlugueRelaxeException;
	/**
	 * <h2>listCidadesDaUfComImovesMaisVisitadas()</h2>
	 * Contrato com a finalidade de retornar até os 6 cidades com maior 
	 * número de visitas aos imóveis.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @return List - Contendo os dados que foram totalizados.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<DTO> listCidadesDaUfComImovesMaisVisitadas(DAOFactory daofactory, String uf) throws AlugueRelaxeException;
}
