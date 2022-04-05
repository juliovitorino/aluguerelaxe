package br.com.jcv.backend.interfaces.business;

import java.util.Date;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

public interface ModoPublicidadeVisitasBusiness<DTO> extends BusinessObject<DTO> {
	void incrementarModoPublicidadeVisita(DAOFactory daofactory, Date d, long idModoPublicidade) throws AlugueRelaxeException;
	DTO procurar(DAOFactory daofactory, Date d, long idModoPublicidade) throws AlugueRelaxeException;
}

