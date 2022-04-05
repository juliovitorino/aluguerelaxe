package br.com.jcv.aluguerelaxe.client.imovel.reserva;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class PreReservaPagtoFormComposite extends FormComposite<ReservaVO> {

	private static final String TEXTO_FINALIZACAO = "Seu pedido foi processado e encontra-se pendente de pagamento. " + 
													"Clique no bot\u00e3o abaixo do nosso parceiro para " +
													"realizar o pagamento dentro de um ambiente seguro e protegido. " +
													"Ap\u00f3s a confirma\u00e7\u00e3o do pagamento pela financeira " +
													"produto ser\u00e1 liberado para veicula\u00e7\u00e3o.";
	
	private HTML linkPagSeguro;

	@Override
	public ReservaVO getVO() {
		return null;
	}

	@Override
	public void update(ReservaVO pvo) {
		linkPagSeguro.setHTML(pvo.htmlFormPagtoPagseguro);
	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label(TEXTO_FINALIZACAO));
		vp.add(linkPagSeguro);
		return vp;
	}

	@Override
	public void init() {
		linkPagSeguro = new HTML();
	}
	
	@Override
	public void clear() {

	}

	@Override
	public void notifier(ReservaVO vo) {
		
	}

}
