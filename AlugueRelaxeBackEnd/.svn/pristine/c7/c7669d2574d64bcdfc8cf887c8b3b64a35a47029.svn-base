package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.depoimento.DepoimentoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

/**
 * <h1>DepoimentoBusiness</h1>
 * <p>Interface responśavel por contratos que a Implementação de 
 * negócio de Depoimento deve realizar.
 * </p>
 * @author julio
 * @version 1.0
 */
public interface DepoimentoBusiness<DTO> extends BusinessObject<DTO> {
	
	DTO proximoDepoimento(DAOFactory daoFactory, Long id) throws AlugueRelaxeException;
	DTO prevDepoimento(DAOFactory daoFactory, Long id) throws AlugueRelaxeException;
	DTO adicionarDepoimento(DAOFactory daoFactory, DepoimentoDTO dto) throws AlugueRelaxeException;
	void liberarDepoimento(DAOFactory daoFactory, String id, String acao) throws AlugueRelaxeException;
	List<DTO> ListarPaginaDepoimentos(DAOFactory daoFactory, int tamanhoPagina) throws AlugueRelaxeException;
}
