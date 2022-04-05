package br.com.jcv.aluguerelaxe.client.campanhas.amigoindicaamigo;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.EmailVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;

import com.google.gwt.user.client.ui.Composite;

public class AmigosParticipantesFormEditPanel extends
		AbstractFormEditPanel<NenhumaToolbar, Composite, List<EmailVO>> {

	AmigosFormComposite amigo1fc;
	AmigosFormComposite amigo2fc;
	AmigosFormComposite amigo3fc;

	public AmigosParticipantesFormEditPanel(NenhumaToolbar toolbar) {
		super(toolbar);
		
		amigo1fc = new AmigosFormComposite();
		amigo2fc = new AmigosFormComposite();
		amigo3fc = new AmigosFormComposite();
		
		addObjetoCompositeToPanel(amigo1fc);
		addObjetoCompositeToPanel(amigo2fc);
		addObjetoCompositeToPanel(amigo3fc);
	}

	@Override
	public List<EmailVO> getVO(Composite composite) {
		List<EmailVO> lst = new ArrayList<EmailVO>();
		lst.add(amigo1fc.getVO());
		lst.add(amigo2fc.getVO());
		lst.add(amigo3fc.getVO());
		
		return lst;
	}

	@Override
	public List<EmailVO> getVO(List<Composite> composite) {
		// Nada a fazer
		return null;
	}

	@Override
	public void update() {
		// Nada a fazer
		
	}

	

}
