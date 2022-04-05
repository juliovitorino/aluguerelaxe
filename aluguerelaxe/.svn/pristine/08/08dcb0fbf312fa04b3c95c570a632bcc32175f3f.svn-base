package br.com.jcv.aluguerelaxe.client.componente.crud;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractFormPesquisa;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractCRUD<F extends AbstractFormPesquisa<?,?>,	
									X extends AbstractFormEditPanel<?,?,?>,
									Z extends AbstractToolbar<?>> extends Composite {

	private TabPanel tpcrud;
	
	public abstract F getInstanceFormPesquisa();
	public abstract Z getInstanceToolbar();
	public abstract X getInstanceEditPanel();
	
	public AbstractCRUD() {
		init();
		initWidget(render());
		//this.setStyleName(STYLE);
	}
	
	private void init() {
		// inicializa componente UI TabLayoutPanel
		tpcrud = new TabPanel();

		//tpcrud = new TabLayoutPanel(2.5, Unit.EM);
		//tpcrud.getElement().getStyle().setMarginBottom(10.0, Unit.PX);
	}
	
	private Widget render() {
		DockPanel dp = new DockPanel();
		
		// Insere form de pesquisa dentro de uma aba da TabLayoutPanel
		tpcrud.add(this.getInstanceFormPesquisa(),"Pesquisar");
		
		dp.add(this.getInstanceToolbar(), DockPanel.NORTH);
		dp.add(tpcrud, DockPanel.CENTER);
		return dp;
	}
	
	public void createTab(X editpanel, String titulo) {
		// Insere uma instancia de EditPanel dentro de uma widget da TabLayoutPanel
		tpcrud.add(editpanel,titulo);
	}
	
	protected void closeTab() {
	
		// Obtem a tab que esta selecionada
		int idx = tpcrud.getTabBar().getSelectedTab();
		//int idx = tpcrud.getSelectedIndex();
		
		// NUNCA elimina a tab de pesquisa
		if (idx > 0) {
			tpcrud.remove(idx);
		}
	}
	
	
}