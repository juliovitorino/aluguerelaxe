package br.com.jcv.aluguerelaxe.client.depoimento;

import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;

public class DepoimentoAssistente extends
		AbstractWizard<DepoimentoFormComposite, DepoimentoVO> {
	
	private DepoimentoFormComposite fcfc ;
	
	public DepoimentoAssistente() {
		super();
		this.setStylePrimaryName("gwt-DepoimentoFormComposite");
		addWizard(fcfc, montaHeaderPasso("user1_edit.png", "Enviar Depoimento"));
		init();
	}

	@Override
	public DepoimentoVO getVO() {
		return fcfc.getVO();
	}

	@Override
	public void initComposites() {
		fcfc = new DepoimentoFormComposite();
	}

}
