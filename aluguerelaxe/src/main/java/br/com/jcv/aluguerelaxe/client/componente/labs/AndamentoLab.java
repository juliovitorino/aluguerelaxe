package br.com.jcv.aluguerelaxe.client.componente.labs;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva.ReservaEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva.ReservaToolbar;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public class AndamentoLab implements EntryPoint {
	public void onModuleLoad() {
		SessaoVO sessaovo = new SessaoVO();
		sessaovo.clientevo = new ClienteVO();
		sessaovo.clientevo.id = 2;

		//RootPanel.get("gwt-andamento").add(new AndamentoEditPanel(sessaovo, new NenhumaToolbar()));
		//RootPanel.get("gwt-andamento").add(new ConfirmarDepositoEditPanel(sessaovo, new ConfirmarDepositoToolbar()));
		//RootPanel.get("gwt-andamento").add(new LiberarPgtoEditPanel(sessaovo, new LiberarPgtoToolbar()));
		//RootPanel.get("gwt-andamento").add(new ProprietarioEditPanel(sessaovo, new ManterClienteToolbar()));
		RootPanel.get("gwt-andamento").add(new ReservaEditPanel(sessaovo, new ReservaToolbar()));
		//RootPanel.get("gwt-andamento").add(new AprovacaoPreReservaEditPanel(sessaovo, new AprovacaoPreReservaToolbar()));
		//RootPanel.get("gwt-andamento").add(new LiberarPgtoEditPanel(sessaovo, new LiberarPgtoToolbar()));
		//RootPanel.get("gwt-andamento").add(new TransferirDepositoEditPanel(sessaovo, new TransferirDepositoToolbar()));
		
	}
}