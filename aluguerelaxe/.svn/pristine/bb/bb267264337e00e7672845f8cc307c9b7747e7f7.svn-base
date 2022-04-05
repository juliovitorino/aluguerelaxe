package br.com.jcv.aluguerelaxe.client.centralreserva;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.administracao.console.negociacao.TrackerNegociacaoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaViewFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerCodigoFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.ui.VerticalPanel;

public class AndamentoPublicoEditPanel extends
		AbstractFormEditPanel<NenhumaToolbar, FormComposite<?>, VOPadrao>
		implements NenhumaToolbarListener {
	
	//private SessaoVO sessaovo = null;
	private TrackerNegociacaoFormComposite tnfc = new TrackerNegociacaoFormComposite();
	private TrackerCodigoFormComposite tcfc = new TrackerCodigoFormComposite();
	private ReservaViewFormComposite rvfc = new ReservaViewFormComposite();

	public AndamentoPublicoEditPanel(ReservaVO rvo, NenhumaToolbar toolbar){
		super(toolbar);
		//this.sessaovo = sessao;
		
		VerticalPanel wpCodTracking = new VerticalPanel();
		wpCodTracking.add( tcfc );

		VerticalPanel wpTracking = new VerticalPanel();
		wpTracking.add( tnfc );

		VerticalPanel wpReservaView = new VerticalPanel();
		wpReservaView.add( rvfc );

		addObjetoCompositeToPanel(tcfc);
		addObjetoCompositeToPanel(tnfc);
		addObjetoCompositeToPanel(rvfc);
		
		// Registra os ouvintes de TrackerCodigoFormComposite
		this.tcfc.addListener(tnfc);
		this.tcfc.addListener(rvfc);
		
		// Atualiza o codigo de tracker e pesquisa
		tcfc.update(rvo);
		
	}

	@Override
	public VOPadrao getVO(FormComposite<?> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VOPadrao getVO(List<FormComposite<?>> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

}
