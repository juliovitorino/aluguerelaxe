package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

/**
 * <h1>BusinessObject</h1> 
 * <p>Interface com Métodos para Implementação dos métodos de negócio.
 * </p>
 * @author Júlio Vitorino
 * @version 1.10
 * @since 07 May 2009
 */
public interface BusinessObject<DTO> {
	
	void setDados(DTO dto) throws AlugueRelaxeException;
	DTO getDados() throws AlugueRelaxeException;
	void validarCamposObrigatorios(DTO dto) throws AlugueRelaxeException;
	DTO incluir(DAOFactory daofactory, DTO dto) throws AlugueRelaxeException;
	DTO atualizar(DAOFactory daofactory, DTO dto) throws AlugueRelaxeException;
	void excluir(DAOFactory daofactory, DTO dto) throws AlugueRelaxeException;
	DTO procurar(DAOFactory daofactory, DTO dto) throws AlugueRelaxeException;
	List<DTO> buscarTodas(DAOFactory daofactory) throws AlugueRelaxeException;
	List<DTO> buscarTodas(DAOFactory daofactory, int start) throws AlugueRelaxeException;
}
