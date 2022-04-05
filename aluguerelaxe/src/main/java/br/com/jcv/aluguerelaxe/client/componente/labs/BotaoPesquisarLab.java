package br.com.jcv.aluguerelaxe.client.componente.labs;


import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractButtonFormPesquisa;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractFormPesquisaListener;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.RegDataGridVO;
import br.com.jcv.aluguerelaxe.client.formpesquisa.ClienteFormPesquisa;
import br.com.jcv.aluguerelaxe.client.search.PesquisarClienteButtonFormPesquisa;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class BotaoPesquisarLab implements EntryPoint, AbstractFormPesquisaListener {
	
	private AbstractButtonFormPesquisa<ClienteFormPesquisa> qafc;

	public void onModuleLoad() {
		qafc = new PesquisarClienteButtonFormPesquisa();
		RootPanel.get("gwt-form-imovel").add(qafc);
		qafc.addListener(this);
		//qafc.show();
	}

	public void onSelecionarClick(RegDataGridVO registro) {
		// TODO Auto-generated method stub
		
	}

	public void onSelecionarClick(List<RegDataGridVO> lstRegistros) {
		// TODO Auto-generated method stub
		
	}

	public void onCancelarClick() {
		qafc.getDialog().hide();
	}


}
