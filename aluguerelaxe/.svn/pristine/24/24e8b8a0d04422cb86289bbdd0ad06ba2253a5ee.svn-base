package br.com.jcv.aluguerelaxe.client.administracao.console.migrarplano;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.SituacaoFinanceiraAnuncioFormComposite;
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

public class MigrarPlanoEditPanel extends AbstractFormEditPanel<NenhumaToolbar, WindowPanel, ImovelFichaCompletaVO> 
	implements NenhumaToolbarListener, PortaRetratoListener {

	private static final String CLIENTE_FORM_TEMA = "orkut";
	
	private static final int ORDEM_GALERIA = 0;
	private static final int ORDEM_SITUACAO_FINANCEIRA_ANUNCIO = 1;
	private static final int ORDEM_ASSISTENTE_MIGRAR_PLANO = 2;
	
	private ImovelFichaCompletaVO ifcvo = null;	
	private AssistenteMigrarPlano amp = null;
	
	public MigrarPlanoEditPanel(SessaoVO sessaovo, NenhumaToolbar toolbar) {
		super(toolbar);
		
		WindowPanel wpGaleria = new WindowPanel("Selecione o im\u00f3vel que deseja mudar de plano de an\u00fancio",CLIENTE_FORM_TEMA);
		wpGaleria.setSize("960px", "190px");

		SimplePanel sp = new SimplePanel();
		sp.add(new GaleriaImovel(sessaovo.clientevo.id, this));
		wpGaleria.setComponenteCenter(sp);

		WindowPanel wpSituacaoFinanceira = new WindowPanel("Situa\u00e7\u00e3o Financeira do An\u00fancio Atual",CLIENTE_FORM_TEMA,true,true,true);
		wpSituacaoFinanceira.setWidth("960px");
		wpSituacaoFinanceira.setComponenteCenter(new SituacaoFinanceiraMigrarPlanoFormComposite());

		WindowPanel wpMigrarPlano = new WindowPanel("Assistente  de ajuda para Migrar Plano de An\u00fancio",CLIENTE_FORM_TEMA,true,true,true);
		wpMigrarPlano.setWidth("960px");
		amp = new AssistenteMigrarPlano();
		wpMigrarPlano.setComponenteCenter(amp);

		
		addObjetoCompositeToPanel(wpGaleria);
		addObjetoCompositeToPanel(wpSituacaoFinanceira);
		addObjetoCompositeToPanel(wpMigrarPlano);
		
		esconderPainel(wpSituacaoFinanceira);
		esconderPainel(wpMigrarPlano);

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

	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		
		WindowPanel wpMigrarPlano = getListObjetoComposite().get(ORDEM_ASSISTENTE_MIGRAR_PLANO);
		WindowPanel wpSituacaoFinanceira = getListObjetoComposite().get(ORDEM_SITUACAO_FINANCEIRA_ANUNCIO);
		
		mostrarPainel(wpSituacaoFinanceira);
		mostrarPainel(wpMigrarPlano);

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
				update(result);
			}
		};
		rpc.pesquisarFichaCompletaImovel(vo.imovel.id, callback);
		
		AsyncCallback<ImovelPlanoFaturaVO> callbackfatura = new AsyncCallback<ImovelPlanoFaturaVO>() {
			
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
			public void onSuccess(ImovelPlanoFaturaVO result) {
				update(result);
			}
		};
		rpc.pesquisarUltimaFatura(vo.imovel.id, "N", callbackfatura);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(ImovelFichaCompletaVO vo) {
		this.ifcvo = vo;
		this.amp.update(vo);
	}
	
	public void update(ImovelPlanoFaturaVO vo) {
		WindowPanel wp = getListObjetoComposite().get(ORDEM_SITUACAO_FINANCEIRA_ANUNCIO);
		SituacaoFinanceiraMigrarPlanoFormComposite ipfc = (SituacaoFinanceiraMigrarPlanoFormComposite) wp.getComponenteCenter();
		ipfc.update(vo);
	}


}
