package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.aprovacao;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.ToolbarListener;

/**
 * Interface base para toolbar com a��es de manuten��o de formul�rios
 * 
 * @author julio
 *
 */
public interface AprovacaoPreReservaToolbarListener extends ToolbarListener {
	void onAprovarClick();
	void onReprovarClick();
	void onPesquisarNovoTrackingClick();
}
