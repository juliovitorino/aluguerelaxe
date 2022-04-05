package br.com.jcv.aluguerelaxe.client.administracao.console.liberarpgto;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.ToolbarListener;

/**
 * Interface base para toolbar com ações de manutenção de formulários
 * 
 * @author julio
 *
 */
public interface LiberarPgtoToolbarListener extends ToolbarListener {
	void onLiberarPgtoClick();
	void onPesquisarNovoTracking();
}
