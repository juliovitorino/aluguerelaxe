package br.com.jcv.aluguerelaxe.client.componente.listbox;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanosRPCAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
* AbstractPlanoListbox - Abstracao para carga do plano de acordo com o tipo de compra do plano
*
*/
public class AbstractPlanoListbox extends ComponenteListBox implements AsyncCallback<List<PlanoVO>>{

	public static final String TIPO_COMPRA_BANNER = "B";
	public static final String TIPO_COMPRA_PP = "P";
	public static final String TIPO_COMPRA_PD = "D";
	public static final String TIPO_COMPRA_EMAIL = "M";
	public static final String TIPO_COMPRA_FB = "X";
	public static final String TIPO_COMPRA_FF = "F";
	public static final String TIPO_COMPRA_PATROCINADOR = "R";
	public static final String TIPO_COMPRA_SB = "W";
	public static final String TIPO_COMPRA_SMS = "Z";
	
	private String tipoCompra;
	
	public AbstractPlanoListbox(String tipoCompra) {
		super();
		init(tipoCompra);
	}
	
	public String getTipoCompra(){
		return this.tipoCompra;
	}
	
	private void init(String tipoCompra) {
		// Realiza chamada RPC para carregar a listbox de acordo com o tipo de compra
		PlanosRPCAsync rpc = ServicosRPC.getPlanosRPC();
		AsyncCallback<List<PlanoVO>> callback = this;
		rpc.listarPlanosPorTipo(tipoCompra, callback);
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
