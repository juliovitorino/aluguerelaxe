package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

public interface ImovelCaracteristicaBusiness<DTO> extends BusinessObject<DTO> {
	
	List<DTO> buscarCaracteristicasImovel(DAOFactory daofactory, long codigoImovel) throws AlugueRelaxeException;
	List<DTO> buscarCaracteristicasCondominio(DAOFactory daofactory, long codigoImovel) throws AlugueRelaxeException;
	

}
