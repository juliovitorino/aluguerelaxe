package br.com.jcv.aluguerelaxe.client.imovel.reserva;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.publicidades.RealizarPagtoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AssistenteReservaImovelFormComposite 
	extends	FormComposite<ContatoClienteVO> 
		implements WizardListener {

	private AssistenteReservaImovel ari;
	
	public AssistenteReservaImovelFormComposite(){
		super();
	}

	@Override
	public ContatoClienteVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ContatoClienteVO vo) {
		this.ari.update(vo);
	}

	@Override
	public void notifier(ContatoClienteVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(this.ari);
		return vp;
	}

	@Override
	public void init() {
		ari = new AssistenteReservaImovel();
		ari.addWizardListener(this);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public void onVoltarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onProximoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onConcluirClick() {
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<ReservaVO> callback = new AsyncCallback<ReservaVO>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(ReservaVO result) {
				AssistenteReservaImovelFormComposite.this.finalizarPreReserva(result);
			}
		};
		rpc.criarPreReserva(ari.getVO(), callback);
	}
	
	private void finalizarPreReserva(ReservaVO vo){
		PreReservaPagtoFormComposite rpfc = new PreReservaPagtoFormComposite();
		ari.showCompositeFinal(rpfc, ari.montaHeaderPasso("cashier.png", "Realizar Pagamento"));
		rpfc.update(vo);
	}
	

	

}
