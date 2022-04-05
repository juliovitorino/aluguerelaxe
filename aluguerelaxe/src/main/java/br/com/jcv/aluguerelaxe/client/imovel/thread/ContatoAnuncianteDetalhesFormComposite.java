package br.com.jcv.aluguerelaxe.client.imovel.thread;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ContatoAnuncianteDetalhesFormComposite extends
		FormComposite<ContatoClienteVO> {
	
	private HTML html;
	private static final String GWT_STYLE = "gwt-cadfc";

	public ContatoAnuncianteDetalhesFormComposite() {
		super();
		this.setStyleName(GWT_STYLE);
	}

	@Override
	public ContatoClienteVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ContatoClienteVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>");
		sb.append("Visita de " + vo.primeiroNome);
		sb.append(", " + vo.cidade + " - " + vo.uf + ".");
		sb.append(" Pretende alugar o im\u00f3vel por <h1>" + vo.diasTemporada + " dias</h1> no per\u00edodo de <h1>" + vo.chegadaPrevistaStr +" at\u00e9 " + vo.partidaPrevistaStr + "</h1>");
		sb.append("<br/>Adultos: " + String.valueOf(vo.qtdeAdultos));
		sb.append(" Crian\u00e7as: " + String.valueOf(vo.qtdeCriancas));
		sb.append("</p>");
		html.setHTML(sb.toString());
	}

	@Override
	public void notifier(ContatoClienteVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		return html;
	}

	@Override
	public void init() {
		html = new HTML();
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
