package br.com.jcv.aluguerelaxe.client.crud;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.administracao.console.proprietario.ProprietarioEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.crud.AbstractCRUD;
import br.com.jcv.aluguerelaxe.client.componente.crud.CRUDToolbar;
import br.com.jcv.aluguerelaxe.client.componente.crud.CRUDToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractFormPesquisaListener;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.RegDataGridVO;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.ManterClienteToolbar;
import br.com.jcv.aluguerelaxe.client.formpesquisa.ClienteFormPesquisa;

public class ClienteCRUD extends AbstractCRUD<ClienteFormPesquisa,	
									ProprietarioEditPanel,
									CRUDToolbar> 
						implements CRUDToolbarListener, AbstractFormPesquisaListener {

	
	public ClienteCRUD() {
		super();
	}
	
	public ClienteFormPesquisa getInstanceFormPesquisa() {
		ClienteFormPesquisa cfp = new ClienteFormPesquisa();
		cfp.addListener(this);
		return cfp;
	}

	public CRUDToolbar getInstanceToolbar(){
		CRUDToolbar tb = new CRUDToolbar();
		tb.addToolbarListener(this);
		return tb;
	}

	public ProprietarioEditPanel getInstanceEditPanel(){
		SessaoVO sessaovo = new SessaoVO();
		sessaovo.clientevo = new ClienteVO();
		sessaovo.clientevo.id = 2;
		sessaovo.clientevo.email = "julio.vitorino@gmail.com";
		return new ProprietarioEditPanel(sessaovo, new ManterClienteToolbar());
	}
	
	public void onNovoClick(){
		createTab(this.getInstanceEditPanel(),"Novo Cliente");
	}
	
	public void onAlterarClick() {
	}

	public void onFecharClick() {
		this.closeTab();
	}
	
	public void onSelecionarClick(RegDataGridVO registro){
	}
	
	public void onSelecionarClick(List<RegDataGridVO> lstRegistros){
	}
	
	public void onCancelarClick() {
	}
	
	
	
	
}