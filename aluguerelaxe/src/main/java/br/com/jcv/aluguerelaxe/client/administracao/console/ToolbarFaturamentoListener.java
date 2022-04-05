package br.com.jcv.aluguerelaxe.client.administracao.console;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.ToolbarListener;

public interface ToolbarFaturamentoListener extends ToolbarListener {
	void onReceberFaturaClick();
	void onReceberPublicidadeClick();
}