package br.com.jcv.backend.workers.interfaces;

import br.com.jcv.backend.alerta24h.AlertaDTO;

public interface IAlerta24H {
	void executar(AlertaDTO dto);
}