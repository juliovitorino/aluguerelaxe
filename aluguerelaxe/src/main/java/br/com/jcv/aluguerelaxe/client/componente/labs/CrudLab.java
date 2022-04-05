package br.com.jcv.aluguerelaxe.client.componente.labs;

import br.com.jcv.aluguerelaxe.client.componente.crud.AbstractCRUD;
import br.com.jcv.aluguerelaxe.client.crud.ClienteCRUD;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public class CrudLab implements EntryPoint {
	public void onModuleLoad() {
		AbstractCRUD<?,?,?> crud = new ClienteCRUD();
		
		RootPanel.get("gwt-datagrid").add(crud);
		
	}
}