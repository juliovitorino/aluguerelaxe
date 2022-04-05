package br.com.jcv.aluguerelaxe.client.administracao.console.pagarplano;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.FichaImovelEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.SituacaoFinanceiraAnuncioFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.TrackerSituacaoFinanceiraAnuncioFormComposite;
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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SimplePanel;

public class PagarPlanoEditPanel extends AbstractFormEditPanel<NenhumaToolbar, WindowPanel, ImovelFichaCompletaVO> 
		implements NenhumaToolbarListener, PortaRetratoListener{

	private static final String CLIENTE_FORM_TEMA = "orkut";
	
	private static final int ORDEM_GALERIA = 0;
	private static final int ORDEM_TRACKER_SITUACAO_FINANCEIRA = 1;
	private static final int ORDEM_SITUACAO_FINANCEIRA_ANUNCIO = 2;
	
	private ImovelFichaCompletaVO ifcvo = null;
	private ImovelPlanoFaturaVO ipfvo = null;
	private boolean callbackFicha = false;
	private boolean callbackFatura = false;
	
	public PagarPlanoEditPanel(SessaoVO sessaovo, NenhumaToolbar toolbar) {
		super(toolbar);
		
		WindowPanel wpGaleria = new WindowPanel("Selecione o im\u00f3vel para realizar o pagamento",CLIENTE_FORM_TEMA);
		wpGaleria.setSize("960px", "190px");

		SimplePanel sp = new SimplePanel();
		sp.add(new GaleriaImovel(sessaovo.clientevo.id, this));
		wpGaleria.setComponenteCenter(sp);

		WindowPanel wpSituacaoFinanceira = new WindowPanel("Situa\u00e7\u00e3o Financeira do An\u00fancio",CLIENTE_FORM_TEMA,true,true,true);
		wpSituacaoFinanceira.setWidth("960px");
		wpSituacaoFinanceira.setComponenteCenter(new SituacaoFinanceiraAnuncioFormComposite());
		
		WindowPanel wpTrackerSF = new WindowPanel("Acompanhamento da Compra do An\u00fancio",CLIENTE_FORM_TEMA,true,true,true);
		wpTrackerSF.setWidth("960px");
		wpTrackerSF.setComponenteCenter(new TrackerSituacaoFinanceiraAnuncioFormComposite());
		
		addObjetoCompositeToPanel(wpGaleria);
		addObjetoCompositeToPanel(wpTrackerSF);
		addObjetoCompositeToPanel(wpSituacaoFinanceira);
		
		esconderPainel(wpSituacaoFinanceira);
		esconderPainel(wpTrackerSF);

		update();

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
		this.ifcvo = vo;
	}
	
	public void update(ImovelPlanoFaturaVO vo) {
		WindowPanel wp = getListObjetoComposite().get(ORDEM_SITUACAO_FINANCEIRA_ANUNCIO);
		SituacaoFinanceiraAnuncioFormComposite ipfc = (SituacaoFinanceiraAnuncioFormComposite) wp.getComponenteCenter();
		ipfc.update(vo);
	}

	public void update(ImovelFichaCompletaVO ifcvo, ImovelPlanoFaturaVO vo) {
		if ((callbackFicha) && (callbackFatura)) {
			WindowPanel wp = getListObjetoComposite().get(ORDEM_TRACKER_SITUACAO_FINANCEIRA);  
			TrackerSituacaoFinanceiraAnuncioFormComposite ipfc = (TrackerSituacaoFinanceiraAnuncioFormComposite) wp.getComponenteCenter();
			ipfc.update(ifcvo, vo);
		}
	}

	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		callbackFicha = false;
		callbackFatura = false;

		//-----------------------------------------------------
		// Configura visibildade de alguns FormComposite
		//-----------------------------------------------------

		WindowPanel wpGaleria = getListObjetoComposite().get(ORDEM_GALERIA);
		WindowPanel wpSituacaoFinanceira = getListObjetoComposite().get(ORDEM_SITUACAO_FINANCEIRA_ANUNCIO);
		WindowPanel wpTrackerSF = getListObjetoComposite().get(ORDEM_TRACKER_SITUACAO_FINANCEIRA);

		
		mostrarPainel(wpGaleria);
		mostrarPainel(wpTrackerSF);
		mostrarPainel(wpSituacaoFinanceira);

		//-----------------------------------------------------
		// Popula os FormComposite
		//-----------------------------------------------------
		ImovelFichaCompletaVO vo = portaRetrato.getFichaImovelPortaRetrato();
		this.ifcvo = portaRetrato.getFichaImovelPortaRetrato();
		
		//-----------------------------------------------------
		// Emite 2 chamadas assincronas
		//-----------------------------------------------------
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ImovelFichaCompletaVO> callback = new AsyncCallback<ImovelFichaCompletaVO>() {
			
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
			public void onSuccess(ImovelFichaCompletaVO result) {
				PagarPlanoEditPanel.this.callbackFicha = true;
				PagarPlanoEditPanel.this.ifcvo = result;
				update(result);
				update(PagarPlanoEditPanel.this.ifcvo, PagarPlanoEditPanel.this.ipfvo);
			}
		};
		rpc.pesquisarFichaCompletaImovel(vo.imovel.id, callback);
		
		AsyncCallback<ImovelPlanoFaturaVO> callbackfatura = new AsyncCallback<ImovelPlanoFaturaVO>() {
			
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
			public void onSuccess(ImovelPlanoFaturaVO result) {
				PagarPlanoEditPanel.this.callbackFatura = true;
				PagarPlanoEditPanel.this.ipfvo = result;
				update(result);
				update(PagarPlanoEditPanel.this.ifcvo, PagarPlanoEditPanel.this.ipfvo);
			}
		};
		rpc.pesquisarUltimaFatura(vo.imovel.id, "N", callbackfatura);
		
	}

	public void onRefreshClick() {
		if (this.ifcvo != null) {
			update(this.ifcvo);
		}
		
	}

}
