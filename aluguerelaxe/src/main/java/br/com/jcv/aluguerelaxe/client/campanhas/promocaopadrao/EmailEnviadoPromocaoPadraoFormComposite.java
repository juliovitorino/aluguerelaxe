package br.com.jcv.aluguerelaxe.client.campanhas.promocaopadrao;


import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EmailEnviadoPromocaoPadraoFormComposite extends FormComposite<VOPadrao> {
	
	private static final String INSTR_HTML = "<h3>Bem Vindo a Nossa Promo\u00e7\u00e3o</h3>" +
	"<p>Para concluir com sucesso sua participa\u00e7\u00e3o na promo\u00e7\u00e3o do AlugueRelaxe, voc\u00ea precisa <b>verificar sua caixa de correio</b>.</p>" +
	"<p>Foi enviado para o e-mail informado neste cadastro uma chave de ativa\u00e7\u00e3o da promo\u00e7\u00e3o para que voc\u00ea possa " +
	"ganhar o ticket numerado com seu n\u00famero da sorte.</p>" +
	"<p>Lembramos que este cadastro ficar\u00e1 ativo por somente 72 horas at\u00e9 a confirma\u00e7\u00e3o do e-mail. Portanto, " +
	"<b>n\u00e3o esque\u00e7a de confirmar sua ativa\u00e7\u00e3o</b>.</p>";
	
	public EmailEnviadoPromocaoPadraoFormComposite() {
		super();
	}

	@Override
	public VOPadrao getVO() {
		// Nao Aplicada neste contexto
		return null;
	}

	@Override
	public void update(VOPadrao vo) {
		// Nao Aplicada neste contexto
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(new HTML(INSTR_HTML));
		return vp;
	}

	@Override
	public void init() {
		// Nao Aplicada neste contexto
	}

	@Override
	public void clear() {
		// Nao Aplicada neste contexto
	}

	@Override
	public void notifier(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}

}
