package br.com.jcv.aluguerelaxe.client.formpesquisa;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractFormPesquisa;
import br.com.jcv.aluguerelaxe.client.datagrid.ClienteDataGrid;
import br.com.jcv.aluguerelaxe.client.parameter.ClienteParameter;
import br.com.jcv.aluguerelaxe.client.parameter.StatusFormComposite;



public class ClienteFormPesquisa extends AbstractFormPesquisa<ClienteParameter, ClienteDataGrid> {
	
	public ClienteFormPesquisa() {
		super(new ClienteParameter(new StatusFormComposite()), new ClienteDataGrid());
	}
	
	@Override
	public void setupGrid(ClienteDataGrid grid) {
		grid.setMaximoLinhas(12);
	}

	@Override
	public String getFormPesquisaWidth() {
		return "860px";
	}

	@Override
	public String getFormPesquisaHeight() {
		return "600px";
	}

}