package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.filasms.FilaSMSDTO;

public interface FilaSMSDAO extends DAO<FilaSMSDTO> {
	List<FilaSMSDTO> list(String modo, int prioridade) throws AlugueRelaxeException;
	void updateStatusEnvioFila(FilaSMSDTO dto, String status) throws AlugueRelaxeException;
	void updateDataEnvioGatewaySMS(FilaSMSDTO dto) throws AlugueRelaxeException;
}