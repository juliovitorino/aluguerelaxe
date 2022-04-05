
package br.com.jcv.aluguerelaxe.client.componente.listbox;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeRPCAsync;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class ModoPublicidadeListBox extends ComponenteListBox 
							implements 	AsyncCallback<List<ModoPublicidadeVO>>{

	public ModoPublicidadeListBox() {
		this(false);
	}
	
	public ModoPublicidadeListBox(boolean filtro) {
		super();
		this.setWidth("260px");
		update();
	}
	
	public void update() {
		ModoPublicidadeRPCAsync rpc = ServicosRPC.getModoPublicidadeRPC();
		AsyncCallback<List<ModoPublicidadeVO>> callback = this;
		rpc.listarModoPublicidade(callback);
	}

	public void onFailure(Throwable caught) {
		this.clear();
	}

	public void onSuccess(List<ModoPublicidadeVO> result) {
		if (result.size() > 0) { 
			this.clear();
			this.addItem("Selecione uma op\u00e7\u00e3o", "-1");
			for (ModoPublicidadeVO vo : result) {
				this.addItem(vo.descricao, String.valueOf(vo.id));
			}
		}
	}
}

