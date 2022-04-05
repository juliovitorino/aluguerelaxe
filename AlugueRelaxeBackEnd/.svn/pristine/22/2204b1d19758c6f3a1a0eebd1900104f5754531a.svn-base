package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.filasms.FilaSMSDTO;

public interface FilaSMSService extends ApplicationService<FilaSMSDTO> {
	List<FilaSMSDTO> listarSMSPendentes(String modo) throws AlugueRelaxeException;
	List<FilaSMSDTO> listarSMSPendentes(String modo, int prioridade) throws AlugueRelaxeException;
	void atualizarStatusEnvioFila(FilaSMSDTO dto, String status) throws AlugueRelaxeException;
	void atualizarDataEnvioGatewaySMS(FilaSMSDTO dto) throws AlugueRelaxeException;
}