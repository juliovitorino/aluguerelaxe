package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.alerta24h.AlertaDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface AlertaDAO extends DAO<AlertaDTO> {
	List<AlertaDTO> list(String status) throws AlugueRelaxeException;
	List<AlertaDTO> listAlertasPendentes() throws AlugueRelaxeException;
	void updateStatusEmitido(AlertaDTO dto, String status) throws AlugueRelaxeException;
}
