package br.com.jcv.aluguerelaxe.client.administracao.console.publicidades;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanosRPCAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PagamentoPublicidadeFormComposite extends FormComposite<PlanoVO> implements AsyncCallback<PlanoVO> {

	private HTML btnPagamento;

	@Override
	public PlanoVO getVO() {
		return null;
	}

	@Override
	public void update(PlanoVO pvo) {
		btnPagamento.setHTML(pvo.htmlBtnPagseguro);
	}

	public void update(long idPlano) {
		this.onPesquisarPlanoClick(idPlano);
	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();

		vp.add(btnPagamento);
		
		return vp;
	}

	@Override
	public void init() {
		btnPagamento = new HTML();
	}
	
	@Override
	public void clear() {

	}

	@Override
	public void notifier(PlanoVO vo) {
		
	}
	
	void onPesquisarPlanoClick(long idPlano) {
		// Realiza chamada RPC para carregar a listbox de acordo com o tipo de compra
		PlanosRPCAsync rpc = ServicosRPC.getPlanosRPC();
		AsyncCallback<PlanoVO> callback = this;
		rpc.pesquisarPlano(idPlano, callback);
	}

	public void onFailure(Throwable caught) {
		// nada a fazer!
	}

	public void onSuccess(PlanoVO result) {
		if (result != null) { 
			this.update(result);
		}
	}	

}
