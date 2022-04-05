package br.com.jcv.aluguerelaxe.client.administracao.console.geolocalizacaowizard;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetratoListener;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SimplePanel;

public class GeoLocalizacaoEditPanel extends AbstractFormEditPanel<NenhumaToolbar, WindowPanel, ImovelFichaCompletaVO> 
		implements NenhumaToolbarListener, PortaRetratoListener {

	private static final String CLIENTE_FORM_TEMA = "orkut";
	
	AssistenteGeoLocalizacao agl = null;
	WindowPanel wpGeoLocalizacao = null;
	SessaoVO sessaovo;
	
	public GeoLocalizacaoEditPanel(SessaoVO sessaovo, NenhumaToolbar toolbar) {
		super(toolbar);
		
		WindowPanel wpGaleria = new WindowPanel("Selecione o im\u00f3vel para aplicar a Geo-Localiza\u00e7\u00e3o",CLIENTE_FORM_TEMA);
		wpGaleria.setSize("960px", "190px");
		
		wpGeoLocalizacao = new WindowPanel(null,CLIENTE_FORM_TEMA);
		wpGeoLocalizacao.setWidth("960px");

		agl = new AssistenteGeoLocalizacao();
		wpGeoLocalizacao.setComponenteCenter(agl);
		wpGeoLocalizacao.setVisible(false);
		
		SimplePanel sp = new SimplePanel();
		sp.add(new GaleriaImovel(sessaovo.clientevo.id, this));
		wpGaleria.setComponenteCenter(sp);

		addObjetoCompositeToPanel(wpGaleria);
		addObjetoCompositeToPanel(wpGeoLocalizacao);
		
		this.sessaovo = sessaovo;
	}
	
	@Override
	public ImovelFichaCompletaVO getVO(WindowPanel composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImovelFichaCompletaVO getVO(List<WindowPanel> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// Nada a Fazer!
	}
	
	public void update(ImovelFichaCompletaVO vo) {
		this.agl.update(vo);
	}

	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		wpGeoLocalizacao.setVisible(true);
		
		//-----------------------------------------------------
		// Popula os FormComposite
		//-----------------------------------------------------
		ImovelFichaCompletaVO vo = portaRetrato.getFichaImovelPortaRetrato();
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ImovelFichaCompletaVO> callback = new AsyncCallback<ImovelFichaCompletaVO>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(ImovelFichaCompletaVO result) {
				update(result);
			}
		};
		rpc.pesquisarFichaCompletaImovel(vo.imovel.id, callback);
	}

}
