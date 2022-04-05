package br.com.jcv.aluguerelaxe.client.administracao.console.publicidades;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.PublicidadeImovelVO;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class RealizarPagtoFormComposite extends FormComposite<PublicidadeImovelVO> {

	private static final String TEXTO_FINALIZACAO = "Seu pedido foi processado e encontra-se pendente de pagamento. " + 
													"Clique no bot\u00e3o abaixo do nosso parceiro para " +
													"realizar o pagamento dentro de um ambiente seguro e protegido. " +
													"Ap\u00f3s a confirma\u00e7\u00e3o do pagamento pela financeira " +
													"produto ser\u00e1 liberado para veicula\u00e7\u00e3o.";
	
	private HTML linkPagSeguro;

	@Override
	public PublicidadeImovelVO getVO() {
		return null;
	}

	@Override
	public void update(PublicidadeImovelVO pvo) {
		if (pvo.fatura.linkPagamento != null){
			linkPagSeguro.setHTML(pvo.fatura.linkPagamento);
		} else {
			linkPagSeguro.setHTML(pvo.plano.htmlBtnPagseguro);
		}
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
	public void notifier(PublicidadeImovelVO vo) {
		
	}

}
