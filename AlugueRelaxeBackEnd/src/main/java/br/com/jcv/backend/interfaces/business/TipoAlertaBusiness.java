package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.tipoalerta.TipoAlertaDTO;

public interface TipoAlertaBusiness extends BusinessObject<TipoAlertaDTO> {
	List<TipoAlertaDTO> listarTiposAlerta(DAOFactory daofactory, String status) throws AlugueRelaxeException;
}