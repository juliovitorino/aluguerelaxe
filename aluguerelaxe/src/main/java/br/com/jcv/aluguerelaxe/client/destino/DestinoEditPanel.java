package br.com.jcv.aluguerelaxe.client.destino;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.imovel.listagem.uf.ListaImovelUF;
import br.com.jcv.aluguerelaxe.client.vo.CidadeUFVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class DestinoEditPanel extends AbstractFormEditPanel<NenhumaToolbar, DestinoFormComposite, EnderecoVO> 
	implements ToolbarDestinoListener {

	public DestinoEditPanel(NenhumaToolbar toolbar) {
		super(toolbar);
		this.setStyleName("gwt-DestinoEditPanel");
		addObjetoCompositeToPanel(new DestinoFormComposite());
	}

	@Override
	public EnderecoVO getVO(DestinoFormComposite composite) {
		return composite.getVO();
	}

	@Override
	public EnderecoVO getVO(List<DestinoFormComposite> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public static native void redirect(String url)/*-{
	      $wnd.location = url;
	  }-*/;

	public void onViajarClick() {
		DestinoFormComposite dfc = getListObjetoComposite().get(0);
		EnderecoVO vo = this.getVO(dfc);
		
		if (vo.codigoUfCidadeItem == -1){
			redirect("/arweb/JXControllerSmartInterface?cmd=dtgListaImovelUf&atvd="+ ListaImovelUF.COMANDO_LISTA_PAGINADA_UF + "&uf="+vo.uf+"&cidade=-1");
		} else {
			
			DestinoRPCAsync rpc = ServicosRPC.getDestinoRPC();
			AsyncCallback<CidadeUFVO> callback = new AsyncCallback<CidadeUFVO>(){
				public void onSuccess(CidadeUFVO result) {
					if (result!= null) {
						redirect("/arweb/JXControllerSmartInterface?cmd=dtgListaImovelUf&atvd="+ ListaImovelUF.COMANDO_LISTA_PAGINADA_CIDADE + "&uf=XX&cidade=" + result.cidade.id);
					}
				}
				public void onFailure(Throwable caught) {
					// TODO fazer tratamento dos retornos de falhas
				}
			};
			rpc.ProcurarUFCidadeItem(vo.codigoUfCidadeItem, callback);	
			
		}
		
		
	}

}
