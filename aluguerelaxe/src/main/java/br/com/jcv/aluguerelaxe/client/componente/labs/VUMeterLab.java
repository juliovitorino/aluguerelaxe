package br.com.jcv.aluguerelaxe.client.componente.labs;

import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.QualidadeAnuncioFormComposite;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class VUMeterLab implements EntryPoint {

	public void onModuleLoad() {
		QualidadeAnuncioFormComposite qafc = new QualidadeAnuncioFormComposite();
		RootPanel.get("gwt-form-imovel").add(qafc);
		qafc.update(100, 95);
	}

}
