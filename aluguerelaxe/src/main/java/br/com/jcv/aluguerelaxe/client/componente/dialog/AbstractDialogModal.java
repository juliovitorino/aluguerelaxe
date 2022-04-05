package br.com.jcv.aluguerelaxe.client.componente.dialog;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractDialogModal<W extends Widget> extends DialogBox {
	protected List<AbstractDialogModalListener<?>> listeners;
	
	// metodo para implementacao na classe concreta, pode lancar os eventos de AbstractDialogModalListener
	public abstract W render();
	public abstract void init();
	
	public AbstractDialogModal() {
		super(false, true);
		this.setStylePrimaryName("gwt-AbstractDialogModal");
		//this.setText(titulo);
		this.init();
		this.setWidget(render());
		this.setGlassEnabled(true);
		this.center();
	}
	
	public void addDialogModalListener(AbstractDialogModalListener<?> listener) {
		if (this.listeners == null) {
			this.listeners = new ArrayList<AbstractDialogModalListener<?>>();
		}
		this.listeners.add(listener);
	}
	
}
