package br.com.jcv.aluguerelaxe.client.administracao.console.tarefaspendentes;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

public class TarefasPendentesEditPanel extends
		AbstractFormEditPanel<NenhumaToolbar , WindowPanel, VOPadrao> 
		implements NenhumaToolbarListener {
		
	private static final int ORDEM_TAREFAS_PENDENTES = 0;
	
	private TarefasPendentesFormComposite tpfc;

	private static final String RESERVA_FORM_TEMA = "orkut";
	private SessaoVO sessaovo = null;

	public TarefasPendentesEditPanel(SessaoVO sessao, NenhumaToolbar toolbar) {
		super(toolbar);
		
		this.sessaovo = sessao;

		WindowPanel wp = new WindowPanel("Tarefas Pendentes",RESERVA_FORM_TEMA,false,false,false);
		tpfc = new TarefasPendentesFormComposite(sessao);
		wp.setComponenteCenter(tpfc);

		addObjetoCompositeToPanel(wp);
	}
 
	@Override
	public ReservaVO getVO(List<WindowPanel> composite) {
		return null;
	}

	@Override
	public ReservaVO getVO(WindowPanel composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
