package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.transferirdeposito;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.ToolbarListener;

/**
 * Interface base para toolbar com ações de manutenção de formulários
 * 
 * @author julio
 *
 */
public interface TransferirDepositoToolbarListener extends ToolbarListener {
	void onTransferirDepositoClick();
	void onPesquisarNovoTracking();
}
