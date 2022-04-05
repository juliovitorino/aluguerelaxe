package br.com.jcv.aluguerelaxe.client.campanhas.amigoindicaamigo;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;

import com.google.gwt.user.client.ui.Composite;

/**
 *<h2>PromocaoAmigoIndicaAmigoWizard</h2>
 *<p>Classe concreta para criação da promocao Amigo Indica Amigo.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractWizard {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
 public class PromocaoAmigoIndicaAmigoWizard extends AbstractWizard<Composite, PromocaoAmigoIndicaAmigoVO >  {
	 
	private InstrucoesAssistenteAmigoIndicaAmigoFormComposite iaaiafc;
	private InstrucoesFinaisAmigoIndicaAmigoFormComposite ifaiafc;
	private ParticipanteFormComposite pfc;
	private AmigosParticipantesFormEditPanel apfep;
		
	public PromocaoAmigoIndicaAmigoWizard() {
		super();
		
		addWizard(iaaiafc, montaHeaderPasso("user1_edit.png", "Assistente de Promo\u00e7\u00e3o"));
		addWizard(pfc, montaHeaderPasso("user1_edit.png", "Cadastro do Participante"));
		addWizard(apfep, montaHeaderPasso("user1_edit.png", "Cadastro dos Amigos"));
		addWizard(ifaiafc,montaHeaderPasso("information2.png", "Instru\u00e7\u00f5es Finais"));
		
		init();
	}
	
	@Override
	public PromocaoAmigoIndicaAmigoVO getVO() {
		
		PromocaoAmigoIndicaAmigoVO vo = new PromocaoAmigoIndicaAmigoVO();
		vo.participante = pfc.getVO();
		vo.lst = apfep.getVO(apfep);
		return vo;
	}

	@Override
	public void initComposites() {
		iaaiafc = new InstrucoesAssistenteAmigoIndicaAmigoFormComposite();
		ifaiafc = new InstrucoesFinaisAmigoIndicaAmigoFormComposite();
		pfc = new ParticipanteFormComposite();
		apfep = new AmigosParticipantesFormEditPanel(new NenhumaToolbar()) ;
		
		/* REMOVER
		efc = new EnderecoFormComposite();
		nsfc = new NovaSenhaFormComposite();
		iancfc = new InstrucoesAssistenteAmigoIndicaAmigoFormComposite();
		tufc = new TermoUsoFormComposite();
		tncfc = new TelefonesNovaContaFormComposite(); */
	}
}
