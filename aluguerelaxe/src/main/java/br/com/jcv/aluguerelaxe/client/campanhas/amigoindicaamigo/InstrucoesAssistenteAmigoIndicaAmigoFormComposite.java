package br.com.jcv.aluguerelaxe.client.campanhas.amigoindicaamigo;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *<h2>InstrucoesAssistenteAmigoIndicaAmigoFormComposite</h2>
 *<p>FormComposite para cria\u00e7\u00e3o da campanha.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AssistenteAmigoIndicaAmigo {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
public class InstrucoesAssistenteAmigoIndicaAmigoFormComposite extends FormComposite<VOPadrao> {
	
	private static final String INSTR_HTML = "<h3>Bem vindo a Promo\u00e7\u00e3o Amigo Indica Amigo</h3>" +
	"<p>Este assistente o ajudar\u00e1 a participar desta campanha no AlugueRelaxe. " +
	"Para GANHAR UM LINDO TABLET, CADASTRE-SE E INDIQUE AMIGOS para ganhar pr\u00eamios gratuitos juntamente com voc\u00ea. <b>\u00c9 de gra\u00e7a!</b></p>" +
	"<p><a href=\"javascript: void(0)\" onclick=\"window.open('/arweb/JXControllerSmartInterface?cmd=dtgRegP1Ani'); return false;\">Veja o regulamento de participa\u00e7\u00e3o</a></p>" + 
	"<p><b>Para continuar, clique em Avan\u00e7ar.</b></p>";
	
	
	
	public InstrucoesAssistenteAmigoIndicaAmigoFormComposite() {
		super();
		//this.setStylePrimaryName("gwt-AssistenteAmigoIndicaAmigo");
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
