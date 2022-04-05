package br.com.jcv.backend.interfaces.business;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

public interface ChatBusiness<DTO> extends BusinessObject<DTO> {

	DTO procurar(DAOFactory daofactory, String sessao) throws AlugueRelaxeException;
	DTO procurar(DAOFactory daofactory, String sessao, long idCliente) throws AlugueRelaxeException;
}
