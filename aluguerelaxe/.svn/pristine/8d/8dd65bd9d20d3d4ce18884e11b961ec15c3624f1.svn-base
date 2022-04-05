
package br.com.jcv.aluguerelaxe.client.componente.listbox;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanosRPCAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class PlanosTipoNormalListBox extends ComponenteListBox 
							implements AsyncCallback<List<PlanoVO>>{

	public PlanosTipoNormalListBox() {
		update();
	}
	
	public void update(){
		PlanosRPCAsync rpc = ServicosRPC.getPlanosRPC();
		AsyncCallback<List<PlanoVO>> callback = this;
		rpc.listarPlanosPorTipo("N", callback);
	}

	public void onFailure(Throwable caught) {
		this.clear();
	}

	public void onSuccess(List<PlanoVO> result) {
		if (result.size() > 0) { 
			this.clear();
			for (PlanoVO vo : result) {
				this.addItem(vo.descricao, String.valueOf(vo.id));
			}
		}
	}
}

