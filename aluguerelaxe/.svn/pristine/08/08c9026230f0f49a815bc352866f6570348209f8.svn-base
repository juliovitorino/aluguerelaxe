package br.com.jcv.aluguerelaxe.client.componente.date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.DateBox;

public class DateBoxWidget extends DateBox {

	private static final String FORMATO_DEFAULT = "dd/MM/yyyy";
	
	private DateTimeFormat fmt = DateTimeFormat.getFormat(FORMATO_DEFAULT);
    

	public DateBoxWidget() {
		super();
		this.setStylePrimaryName("gwt-DateBoxWidget");
		this.setFormat(new DateBox.DefaultFormat(fmt));
	}
	
}
