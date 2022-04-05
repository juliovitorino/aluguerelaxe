package br.com.jcv.aluguerelaxe.client.administracao.console;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.ToolbarListener;

public interface ToolbarNegociacaoListener extends ToolbarListener {
	void onAndamentoReservaClick();
	void onLiberarPgtoClick();
}