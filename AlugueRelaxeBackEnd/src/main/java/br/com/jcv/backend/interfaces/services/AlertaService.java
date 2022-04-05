package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.alerta24h.AlertaDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface AlertaService extends ApplicationService<AlertaDTO> {
	void enfileirarAlerta(AlertaDTO dto) throws AlugueRelaxeException;
	void emitirAlerta() throws AlugueRelaxeException;
	List<AlertaDTO> listarAlertasPendentes() throws AlugueRelaxeException;
	void atualizarStatusEmitido(AlertaDTO dto, String status) throws AlugueRelaxeException;
}
