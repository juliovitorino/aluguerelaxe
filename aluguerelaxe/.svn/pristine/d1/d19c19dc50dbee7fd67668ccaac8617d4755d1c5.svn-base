package br.com.jcv.aluguerelaxe.client.componente.dataentry;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TelefoneDataEntry extends Composite {
	
	private TextBox ddd = new TextBox();
	private TextBox telefone = new TextBox();
	
	public TelefoneDataEntry() {
		initWidget(montaTelefoneDataEntry());
	}

	private Widget montaTelefoneDataEntry() {
		HorizontalPanel hp = new HorizontalPanel();
		
		ddd.setWidth("25px");
		telefone.setWidth("120px");

		hp.add(new Label("("));
		hp.add(ddd);
		hp.add(new Label(")"));
		hp.add(telefone);
		return hp;
	}
	
	public String getDDD() {
		return ddd.getText();
	}
	
	public String getTelefone() {
		return telefone.getText();
	}
	
	public void setDDD(String ddd) {
		this.ddd.setText(ddd);
	}

	public void setTelefone(String telefone) {
		this.telefone.setText(telefone);
	}

}
