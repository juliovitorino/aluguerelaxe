package br.com.jcv.aluguerelaxe.client.faleconosco;

import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;

public class FaleConoscoAssistente extends
		AbstractWizard<FaleConoscoFormComposite, FaleConoscoVO> {
	
	private FaleConoscoFormComposite fcfc ;
	
	public FaleConoscoAssistente() {
		super();
		this.setStylePrimaryName("gwt-FaleConoscoAssistente");
		addWizard(fcfc, montaHeaderPasso("user1_edit.png", "Fale Conosco"));
		init();
	}

	@Override
	public FaleConoscoVO getVO() {
		return fcfc.getVO();
	}

	@Override
	public void initComposites() {
		fcfc = new FaleConoscoFormComposite();
	}

}
