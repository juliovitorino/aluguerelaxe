package br.com.jcv.aluguerelaxe.client.campanhas.promocaopadrao;



import br.com.jcv.aluguerelaxe.client.campanhas.amigoindicaamigo.ParticipanteFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;

import com.google.gwt.user.client.ui.Composite;

/**
 *<h2>PromocaoPadraoWizard</h2>
 *<p>Classe concreta para criação da promocao Amigo Indica Amigo.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractWizard {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
 public class PromocaoPadraoWizard extends AbstractWizard<Composite, PromocaoPadraoVO >  {
	 
	private InstrucoesAssistentePromocaoPadraoFormComposite iaaiafc;
	private InstrucoesFinaisPromocaoPadraoFormComposite ifaiafc;
	private ParticipanteFormComposite pfc;
		
	public PromocaoPadraoWizard() {
		super();
		
		addWizard(iaaiafc, montaHeaderPasso("user1_edit.png", "Assistente de Promo\u00e7\u00e3o"));
		addWizard(pfc, montaHeaderPasso("user1_edit.png", "Cadastro do Participante"));
		addWizard(ifaiafc,montaHeaderPasso("information2.png", "Instru\u00e7\u00f5es Finais"));
		
		init();
	}
	
	@Override
	public PromocaoPadraoVO getVO() {
		PromocaoPadraoVO vo = new PromocaoPadraoVO();
		vo.participante = pfc.getVO();
		return vo;
	}

	@Override
	public void initComposites() {
		iaaiafc = new InstrucoesAssistentePromocaoPadraoFormComposite();
		ifaiafc = new InstrucoesFinaisPromocaoPadraoFormComposite();
		pfc = new ParticipanteFormComposite();
	}
}
