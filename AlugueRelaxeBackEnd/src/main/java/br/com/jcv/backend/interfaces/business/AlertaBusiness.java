package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.alerta24h.AlertaDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

public interface AlertaBusiness extends BusinessObject<AlertaDTO> {
	void enfileirarAlerta(DAOFactory daofactory, AlertaDTO dto) throws AlugueRelaxeException;
	List<AlertaDTO> listarAlertasPendentes(DAOFactory daofactory) throws AlugueRelaxeException;
	void atualizarStatusEmitido(DAOFactory daofactory, AlertaDTO dto, String status) throws AlugueRelaxeException;
}