
package br.com.jcv.aluguerelaxe.client.mobile.componente.listbox;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.mobile.uf.UFRPCAsync;
import br.com.jcv.aluguerelaxe.client.mobile.vo.CidadeVO;
import br.com.jcv.ui.client.componente.listbox.ComponenteListBox;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CidadesListComImoveisBox extends ComponenteListBox 
							implements 	ChangeHandler,
										AsyncCallback<List<CidadeVO>>{

	private boolean filtro = false;
	private boolean atualizaIdx = false; 
	private String cidade;

	public CidadesListComImoveisBox() {
		this(false);
	}
	
	public CidadesListComImoveisBox(boolean filtro) {
		super();
		this.filtro = filtro;
		this.setWidth("180px");
	}
	
	public void onChange(ChangeEvent event) {
		atualizaIdx = false; 
		UFRPCAsync rpc = ServicosRPC.getUFRPC();
		AsyncCallback<List<CidadeVO>> callback = this;
		UFListBox ufdisparador = (UFListBox) event.getSource();
		rpc.listarCidadesDaUFComImoveis(ufdisparador.getValue(ufdisparador.getSelectedIndex()), callback);
	}
	
	public void onChange(UFListBox uf, String cidade){
		atualizaIdx = true; 
		this.cidade = cidade;
		UFRPCAsync rpc = ServicosRPC.getUFRPC();
		AsyncCallback<List<CidadeVO>> callback = this;
		rpc.listarCidadesDaUFComImoveis(uf.getValue(uf.getSelectedIndex()), callback);
	}

	public void onFailure(Throwable caught) {
		this.clear();
	}

	public void onSuccess(List<CidadeVO> result) {
		if (result == null){
			this.clear();
			this.addItem("Todas", String.valueOf(-1));
		} else {
			if (result.size() > 0) { 
				this.clear();
				if (this.filtro){
					this.addItem("Todas", String.valueOf(-1));
				}
				for (CidadeVO cidadeVO : result) {
					this.addItem(cidadeVO.nome, String.valueOf(cidadeVO.id));
				}
				
				if (atualizaIdx){
					this.setSelectedIndex(this.getSelectedItemText(cidade));
				}
			}
		}
	}
}

