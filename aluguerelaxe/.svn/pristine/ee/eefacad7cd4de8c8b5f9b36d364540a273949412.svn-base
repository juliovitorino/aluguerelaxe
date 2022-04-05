package br.com.jcv.aluguerelaxe.client.componente.date;

import java.util.Date;

import br.com.jcv.aluguerelaxe.client.componente.listbox.ListaNumeradaListBox;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

@Deprecated
public class DateWidget extends Composite {
	
	private ListaNumeradaListBox lstdia = new ListaNumeradaListBox(1, 31, false);
	private ListaNumeradaListBox lstmes = new ListaNumeradaListBox(1, 12, false);
	private ListaNumeradaListBox lstano = new ListaNumeradaListBox(1930, 2050, false);
	
	public DateWidget() {
		initWidget(montaDateWidget());
	}
	
	private Widget montaDateWidget() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(lstdia);
		hp.add(new Label("/"));
		hp.add(lstmes);
		hp.add(new Label("/"));
		hp.add(lstano);
		return hp;
		
	}
	
	public void setDate(Date data) {
		int dia = data.getDate();
		int mes = data.getMonth();
		int ano = data.getYear();
		
		lstdia.setSelectedIndex(dia - 1);
		//lstdia.setSelectedIndex(lstdia.getSelectedItemText(String.valueOf(dia)));
		lstmes.setSelectedIndex(lstmes.getSelectedItemText(String.valueOf(mes + 1)));
		lstano.setSelectedIndex(lstano.getSelectedItemText(String.valueOf(ano + 1900)));
	}
	
	public Date getDate() {
		//int dia = Integer.valueOf(lstdia.getItemText(lstdia.getSelectedIndex()));
		int dia = lstdia.getSelectedIndex() + 2;
		int mes = Integer.valueOf(lstmes.getItemText(lstmes.getSelectedIndex()));
		int ano = Integer.valueOf(lstano.getItemText(lstano.getSelectedIndex()));
		return new Date(ano-1900,mes-1,dia);
	}

}
