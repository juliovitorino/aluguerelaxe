package br.com.jcv.backend.variavel;

import java.util.EventListener;

public interface VariavelListener extends EventListener {
	
	void notificacao(VariavelEvent me);

}
