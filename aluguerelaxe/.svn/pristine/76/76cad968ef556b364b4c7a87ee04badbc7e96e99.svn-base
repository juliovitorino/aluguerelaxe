
package br.com.jcv.aluguerelaxe.client.componente.checkbox;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class LabeledCheckBox extends Composite {
	
	private static final String GWT_LABELED_CHECK_BOX = "gwt-labeledCheckBox";
	private CheckBox checkbox = null;
	private long id = -1;

	public LabeledCheckBox(long id, String texto){
		this.id = id;
		initWidget(montaComponente(texto));
	}

	public long getId() {
		return this.id;
	}
	
	public CheckBox getCheckBox() {
		return this.checkbox;
	}

	private HorizontalPanel montaComponente(String texto) {
		HorizontalPanel hp = new HorizontalPanel();
		hp.setStylePrimaryName(GWT_LABELED_CHECK_BOX);
		checkbox = new CheckBox();
		checkbox.setStylePrimaryName(GWT_LABELED_CHECK_BOX);
		checkbox.addStyleName("gwt-CheckBox");
		hp.add(checkbox);

		Label lbl = new Label(texto);
		lbl.setStylePrimaryName(GWT_LABELED_CHECK_BOX);
		lbl.addStyleName("gwt-Label");

		hp.add(lbl);
		return hp;
	}
	

}

