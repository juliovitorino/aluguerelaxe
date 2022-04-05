package br.com.jcv.aluguerelaxe.client.componente.field;

import com.google.gwt.user.client.ui.TextArea;

public class FieldCompositeTextArea extends AbstractFieldComposite<TextArea> {

	public FieldCompositeTextArea(String tituloLabel) {
		super(tituloLabel, new TextArea());
	}

}
