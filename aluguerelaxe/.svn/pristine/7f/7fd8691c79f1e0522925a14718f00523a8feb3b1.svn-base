package br.com.jcv.aluguerelaxe.client.componente.listbox;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class UltimosCincoAnosListBox extends ComponenteListBox {
	
	public UltimosCincoAnosListBox() {
		super();
		Date today = new Date();
	    DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy");
	    int ano = Integer.valueOf(fmt.format(today));
	    
	    for (int i = 0; i < 5; i++){
			this.addItem(String.valueOf(ano - i));										
	    }
	}

}
