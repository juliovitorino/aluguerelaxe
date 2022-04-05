package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.transferirdeposito;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.ToolbarListener;

/**
 * Interface base para toolbar com a��es de manuten��o de formul�rios
 * 
 * @author julio
 *
 */
public interface TransferirDepositoToolbarListener extends ToolbarListener {
	void onTransferirDepositoClick();
	void onPesquisarNovoTracking();
}
