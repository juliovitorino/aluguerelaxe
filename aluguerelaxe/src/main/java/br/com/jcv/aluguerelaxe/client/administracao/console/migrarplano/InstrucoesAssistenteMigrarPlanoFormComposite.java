package br.com.jcv.aluguerelaxe.client.administracao.console.migrarplano;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *<h2>InstrucoesAssistenteNovoImovelFormComposite</h2>
 *<p>FormComposite para cria\u00e7\u00e3o de nova conta.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AssistenteNovoImovel {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
public class InstrucoesAssistenteMigrarPlanoFormComposite extends FormComposite<VOPadrao> {
	
	private static final String INSTR_HTML = "<h3>Assistente de Novo Im\u00f3vel</h3>" +
	"<p>Este assistente o ajudar\u00e1 a incluir um novo im\u00f3vel de temporada na sua carteira de im\u00f3veis no AlugueRelaxe. " +
	"Com ele ser\u00e1 muito f\u00e1cil dar entrada nas informa\u00e7\u00f5es, caracter\u00edsticas do im\u00f3vel e do condom\u00ednio, localiza\u00e7\u00e3o e observa\u00e7\u00f5es gerais.</p>" +
	"<p>Ap\u00f3s realizar todos os passos do assistente, voc\u00ea pode clicar no \u00edcone 'Meus Im\u00f3veis' para gerenciar qualquer modifica\u00e7\u00e3o que desejar.</p>" +
	"<p><b>Para continuar, clique em Avan\u00e7ar.</b>.</p>";
	
	public InstrucoesAssistenteMigrarPlanoFormComposite() {
		super();
		//this.setStylePrimaryName("gwt-AssistenteNovoImovel");
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
