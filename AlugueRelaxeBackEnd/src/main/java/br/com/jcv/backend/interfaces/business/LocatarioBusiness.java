package br.com.jcv.backend.interfaces.business;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

public interface LocatarioBusiness<DTO> extends BusinessObject<DTO> {
	DTO procurar(DAOFactory daoFactory, String email) throws AlugueRelaxeException;
}
