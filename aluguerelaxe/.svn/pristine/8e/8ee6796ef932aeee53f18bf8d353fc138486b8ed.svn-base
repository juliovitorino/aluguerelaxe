package br.com.jcv.aluguerelaxe.client.administracao.console.enquete;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeVO;

public class EnqueteModoPublicidadeWizard extends
		AbstractWizard<FormComposite<?>, ModoPublicidadeVO> {

	private EnqueteModoPublicidadeFormComposite empfc;
	
	public EnqueteModoPublicidadeWizard() {
		super();
		//this.setStylePrimaryName("gwt-EnqueteModoPublicidadeWizard");
		
		addWizard(empfc, montaHeaderPasso("question_and_answer.png", "Enquete"));
		init();
	}
	
	@Override
	public void initComposites() {
		empfc = new EnqueteModoPublicidadeFormComposite();
		
	}

	@Override
	public ModoPublicidadeVO getVO() {
		return empfc.getVO();
	}

	

}
