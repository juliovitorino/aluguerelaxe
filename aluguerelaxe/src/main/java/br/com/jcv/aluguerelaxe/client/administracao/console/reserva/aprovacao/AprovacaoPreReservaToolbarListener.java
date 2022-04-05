package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.aprovacao;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.ToolbarListener;

/**
 * Interface base para toolbar com ações de manutenção de formulários
 * 
 * @author julio
 *
 */
public interface AprovacaoPreReservaToolbarListener extends ToolbarListener {
	void onAprovarClick();
	void onReprovarClick();
	void onPesquisarNovoTrackingClick();
}
