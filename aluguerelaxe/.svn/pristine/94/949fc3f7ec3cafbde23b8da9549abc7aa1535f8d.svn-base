package br.com.jcv.aluguerelaxe.client.administracao.console.imovelwizard;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.PublicidadeImovelVO;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class ContribuicaoPagtoFormComposite extends FormComposite<PublicidadeImovelVO> {

	private static final String TEXTO_FINALIZACAO = "Seu pedido foi processado e encontra-se liberado. " + 
													"Clique no bot\u00e3o abaixo do nosso parceiro para " +
													"realizar UMA CONTRIBUI\u00c7\u00c3O PARA NOSSO PROJETO dentro de um ambiente seguro e protegido. " +
													"Essa contribui\u00e7\u00e3o n\u00e3o \u00e9 obrigat\u00f3ria, por\u00e9m ajuda a manter nossas atividades e pagamento de custos." +
													"CLIQUE EM MEUS IM\u00d3VEIS PARA ACOMPANHAR SUA CARTEIRA.";
	
	private HTML linkPagSeguro;

	@Override
	public PublicidadeImovelVO getVO() {
		return null;
	}

	@Override
	public void update(PublicidadeImovelVO pvo) {
		linkPagSeguro.setHTML(pvo.plano.htmlBtnPagseguro);
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
