package br.com.jcv.aluguerelaxe.client.administracao.console.tarefaspendentes;

import br.com.jcv.aluguerelaxe.client.administracao.console.liberarpgto.LiberarPgtoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.liberarpgto.LiberarPgtoToolbar;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.aprovacao.AprovacaoPreReservaEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.aprovacao.AprovacaoPreReservaToolbar;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.deposito.ConfirmarDepositoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.deposito.ConfirmarDepositoToolbar;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.transferirdeposito.TransferirDepositoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.transferirdeposito.TransferirDepositoToolbar;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ViewTarefasPendentesFormComposite extends FormComposite<TarefasPendentesTreeVO> 
	implements TarefasPendentesTreeFormCompositeListener {
	
	private SessaoVO sessao = null;
	private Widget containerView;
	private DockPanel dp;
	
	public void init() {
		 containerView = new HorizontalPanel();
	}

	public Widget render() {
		dp = new DockPanel();
		dp.add(containerView, DockPanel.CENTER);
		return dp;
	}

	@Override
	public TarefasPendentesTreeVO getVO() {
		return null;
	}
	
	@Override
	public void update(TarefasPendentesTreeVO vo){
		if(vo.secaoTreeview.equals(TarefasPendentesTreeFormComposite.ROOT_APROVACAO)) {
			dp.remove(containerView);
			containerView = new AprovacaoPreReservaEditPanel(vo, new AprovacaoPreReservaToolbar());
			dp.add(containerView, DockPanel.CENTER);
		} else if(vo.secaoTreeview.equals(TarefasPendentesTreeFormComposite.ROOT_CONFIRMACAO_DEPOSITO)) {
			dp.remove(containerView);
			containerView = new ConfirmarDepositoEditPanel(vo, new ConfirmarDepositoToolbar());
			dp.add(containerView, DockPanel.CENTER);
		} else if(vo.secaoTreeview.equals(TarefasPendentesTreeFormComposite.ROOT_LIBERACAO_DEPOSITO)) {
			dp.remove(containerView);
			containerView = new LiberarPgtoEditPanel(vo, new LiberarPgtoToolbar());
			dp.add(containerView, DockPanel.CENTER);
		} else if(vo.secaoTreeview.equals(TarefasPendentesTreeFormComposite.ROOT_TRANSFERENCIA_DEPOSITO)) {
			dp.remove(containerView);
			containerView = new TransferirDepositoEditPanel(vo, new TransferirDepositoToolbar());
			dp.add(containerView, DockPanel.CENTER);
		}
	}

	@Override
	public void clear() {
	}

	@Override
	public void notifier(TarefasPendentesTreeVO vo) {
	}

	public void onNodeTarefasPendentesClick(TarefasPendentesTreeVO vo) {
		update(vo);
		
	}


}
