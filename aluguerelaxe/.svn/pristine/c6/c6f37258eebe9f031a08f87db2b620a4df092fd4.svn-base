package br.com.jcv.aluguerelaxe.client.componente.menu;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class MenuToolbar<T extends AbstractToolbar> extends TabPanel {

	public MenuToolbar() {
		this.setStylePrimaryName("gwt-AbstractMenu");
	}
	
	public void addMenuItem(String titulo, List<T> toolbars) {
		HorizontalPanel hp = new HorizontalPanel();
		if (toolbars != null) {
			for(T at: toolbars){
				hp.add(at);
			}
			this.add(hp, titulo);
		}
	}
	
	
}
