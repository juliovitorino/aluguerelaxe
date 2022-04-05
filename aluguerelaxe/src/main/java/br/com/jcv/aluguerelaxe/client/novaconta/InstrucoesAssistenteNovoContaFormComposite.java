package br.com.jcv.aluguerelaxe.client.novaconta;

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
public class InstrucoesAssistenteNovoContaFormComposite extends FormComposite<VOPadrao> {
	
	private static final String INSTR_HTML = "<h3>Assistente de Abertura de Conta</h3>" +
	"<p>Este assistente o ajudar\u00e1 a incluir uma nova conta de cliente no AlugueRelaxe. " +
	"Com ele ser\u00e1 muito f\u00e1cil dar entrada nas suas informa\u00e7\u00f5es pessoais antes de administrar sua carteira de im\u00f3veis.</p>" +
	"<p>Ap\u00f3s realizar todos os passos do assistente, voc\u00ea poder\u00e1 entrar no Console Administrativo para gerenciar sua carteira.</p>" +
	"<p><b>Para continuar, clique em Avan\u00e7ar.</b></p>";
	
	public InstrucoesAssistenteNovoContaFormComposite() {
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
