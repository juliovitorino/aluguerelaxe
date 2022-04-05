package br.com.jcv.aluguerelaxe.client.administracao.console.videos;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SimplePanel;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.migrarplano.AssistenteMigrarPlano;
import br.com.jcv.aluguerelaxe.client.administracao.console.migrarplano.SituacaoFinanceiraMigrarPlanoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetratoListener;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoFaturaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

public class VideosEditPanel extends AbstractFormEditPanel<NenhumaToolbar, WindowPanel, ImovelFichaCompletaVO> implements
		NenhumaToolbarListener, PortaRetratoListener {

	private static final String CLIENTE_FORM_TEMA = "orkut";
	
	private static final int ORDEM_GALERIA = 0;
	private static final int ORDEM_ASSISTENTE_MIGRAR_PLANO = 1;
	
	private ImovelFichaCompletaVO ifcvo = null;	
	private AssistenteVideoImovel avi = null;
	
	public VideosEditPanel(SessaoVO sessaovo, NenhumaToolbar toolbar) {
		super(toolbar);
		
		WindowPanel wpGaleria = new WindowPanel("Selecione o im\u00f3vel que deseja publicar o v\u00eddeo de demonstra\u00e7\u00e3o",CLIENTE_FORM_TEMA);
		wpGaleria.setSize("960px", "190px");

		SimplePanel sp = new SimplePanel();
		sp.add(new GaleriaImovel(sessaovo.clientevo.id, this));
		wpGaleria.setComponenteCenter(sp);

		WindowPanel wpMigrarPlano = new WindowPanel("Assistente de Publica\u00e7\u00e3o de V\u00eddeo",CLIENTE_FORM_TEMA,true,true,true);
		wpMigrarPlano.setWidth("960px");
		avi = new AssistenteVideoImovel();
		wpMigrarPlano.setComponenteCenter(avi);

		
		addObjetoCompositeToPanel(wpGaleria);
		addObjetoCompositeToPanel(wpMigrarPlano);
		
		esconderPainel(wpMigrarPlano);

		update();

	}

	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		
		WindowPanel wpMigrarPlano = getListObjetoComposite().get(ORDEM_ASSISTENTE_MIGRAR_PLANO);
		mostrarPainel(wpMigrarPlano);

		//-----------------------------------------------------
		// Popula os FormComposite
		//-----------------------------------------------------
		ImovelFichaCompletaVO vo = portaRetrato.getFichaImovelPortaRetrato();
		this.ifcvo = portaRetrato.getFichaImovelPortaRetrato();
		
		//-----------------------------------------------------
		// Emite 1 chamadas assincronas
		//-----------------------------------------------------
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
		// TODO Auto-generated method stub
		
	}

	public void update(ImovelFichaCompletaVO vo) {
		this.ifcvo = vo;
		this.avi.update(vo);
	}
	
}
