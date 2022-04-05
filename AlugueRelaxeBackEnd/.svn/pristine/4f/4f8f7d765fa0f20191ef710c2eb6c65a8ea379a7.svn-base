package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.filasms.FilaSMSDTO;

public interface FilaSMSBusiness extends BusinessObject<FilaSMSDTO> {
	List<FilaSMSDTO> listarSMSPendentes(DAOFactory daofactory, String modo, int prioridade) throws AlugueRelaxeException;
	void atualizarStatusEnvioFila(DAOFactory daofactory, FilaSMSDTO dto, String status) throws AlugueRelaxeException;
	void atualizarDataEnvioGatewaySMS(DAOFactory daofactory, FilaSMSDTO dto) throws AlugueRelaxeException;

}