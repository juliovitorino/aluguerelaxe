package br.com.jcv.aluguerelaxe.client.administracao.console.andamento;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.administracao.console.negociacao.TrackerNegociacaoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaViewFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerCodigoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

public class AndamentoEditPanel extends
		AbstractFormEditPanel<NenhumaToolbar, WindowPanel, VOPadrao>
		implements NenhumaToolbarListener {
	
	private static final String ANDAMENTO_TEMA = "orkut";

	private SessaoVO sessaovo = null;
	private TrackerNegociacaoFormComposite tnfc = new TrackerNegociacaoFormComposite();
	private TrackerCodigoFormComposite tcfc = new TrackerCodigoFormComposite();
	private ReservaViewFormComposite rvfc = new ReservaViewFormComposite();

	public AndamentoEditPanel(SessaoVO sessao, NenhumaToolbar toolbar){
		super(toolbar);
		this.sessaovo = sessao;
		
		WindowPanel wpCodTracking = new WindowPanel("Rastreamento",ANDAMENTO_TEMA,false,false,false);
		wpCodTracking.setSize("960px", "0px");
		wpCodTracking.setComponenteCenter( tcfc );

		WindowPanel wpTracking = new WindowPanel("Tracking da Negocia\u00e7\u00e3o",ANDAMENTO_TEMA,false,false,false);
		wpTracking.setSize("960px", "0px");
		wpTracking.setComponenteCenter( tnfc );

		WindowPanel wpReservaView = new WindowPanel("Detalhes da Negocia\u00e7\u00e3o",ANDAMENTO_TEMA,false,false,false);
		wpReservaView.setSize("960px", "0px");
		wpReservaView.setComponenteCenter( rvfc );

		addObjetoCompositeToPanel(wpCodTracking);
		addObjetoCompositeToPanel(wpTracking);
		addObjetoCompositeToPanel(wpReservaView);
		
		// Registra os ouvintes de TrackerCodigoFormComposite
		this.tcfc.addListener(tnfc);
		this.tcfc.addListener(rvfc);
		
	}

	@Override
	public VOPadrao getVO(WindowPanel composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VOPadrao getVO(List<WindowPanel> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

}
