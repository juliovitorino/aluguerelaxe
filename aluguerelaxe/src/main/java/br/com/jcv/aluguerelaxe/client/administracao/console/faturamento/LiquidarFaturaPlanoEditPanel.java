package br.com.jcv.aluguerelaxe.client.administracao.console.faturamento;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.SituacaoFinanceiraAnuncioFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.TrackerSituacaoFinanceiraAnuncioFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractDataGrid;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractFormPesquisaListener;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.RegDataGridVO;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModalListener;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.formpesquisa.PlanosPagosAVencerFormPesquisa;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoFaturaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class LiquidarFaturaPlanoEditPanel extends AbstractFormEditPanel<LiquidarToolbar, WindowPanel, ImovelFichaCompletaVO> 
		implements LiquidarToolbarListener, AbstractFormPesquisaListener {

	private static final String CLIENTE_FORM_TEMA = "orkut";
	
	private static final int ORDEM_FILTRO = 0;
	private static final int ORDEM_TRACKER_SITUACAO_FINANCEIRA = 1;
	private static final int ORDEM_SITUACAO_FINANCEIRA_ANUNCIO = 2;
	
	private ImovelFichaCompletaVO ifcvo = null;
	private ImovelPlanoFaturaVO ipfvo = null;
	private boolean callbackFicha = false;
	private boolean callbackFatura = false;
	
	public LiquidarFaturaPlanoEditPanel(LiquidarToolbar toolbar) {
		super(toolbar);
		
		WindowPanel wpFiltro = new WindowPanel("Selecione a fatura para registrar o pagamento",CLIENTE_FORM_TEMA);
		wpFiltro.setWidth("960px");
		PlanosPagosAVencerFormPesquisa ppavfp = new PlanosPagosAVencerFormPesquisa();
		ppavfp.addListener(this);
		wpFiltro.setComponenteCenter(ppavfp);

		WindowPanel wpSituacaoFinanceira = new WindowPanel("Situa\u00e7\u00e3o Financeira do An\u00fancio",CLIENTE_FORM_TEMA,true,true,true);
		wpSituacaoFinanceira.setWidth("960px");
		wpSituacaoFinanceira.setComponenteCenter(new SituacaoFinanceiraAnuncioFormComposite());
		
		WindowPanel wpTrackerSF = new WindowPanel("Acompanhamento da Compra do An\u00fancio",CLIENTE_FORM_TEMA,true,true,true);
		wpTrackerSF.setWidth("960px");
		wpTrackerSF.setComponenteCenter(new TrackerSituacaoFinanceiraAnuncioFormComposite());
		
		addObjetoCompositeToPanel(wpFiltro);
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

	public void onRefreshClick() {
		if (this.ifcvo != null) {
			update(this.ifcvo);
		}
		
	}
	
	public void onSelecionarClick(RegDataGridVO registro){
		callbackFicha = false;
		callbackFatura = false;

		//-----------------------------------------------------
		// Configura visibildade de alguns FormComposite
		//-----------------------------------------------------

		WindowPanel wpSituacaoFinanceira = getListObjetoComposite().get(ORDEM_SITUACAO_FINANCEIRA_ANUNCIO);
		WindowPanel wpTrackerSF = getListObjetoComposite().get(ORDEM_TRACKER_SITUACAO_FINANCEIRA);

		mostrarPainel(wpTrackerSF);
		mostrarPainel(wpSituacaoFinanceira);

		//-----------------------------------------------------
		// Emite 2 chamadas assincronas
		//-----------------------------------------------------
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ImovelFichaCompletaVO> callback = new AsyncCallback<ImovelFichaCompletaVO>() {
			
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
			public void onSuccess(ImovelFichaCompletaVO result) {
				LiquidarFaturaPlanoEditPanel.this.callbackFicha = true;
				LiquidarFaturaPlanoEditPanel.this.ifcvo = result;
				update(result);
				update(LiquidarFaturaPlanoEditPanel.this.ifcvo, LiquidarFaturaPlanoEditPanel.this.ipfvo);
			}
		};
		rpc.pesquisarFichaCompletaImovel(Long.valueOf(registro.campo[AbstractDataGrid.COLPOS_4]), callback);
		
		AsyncCallback<ImovelPlanoFaturaVO> callbackfatura = new AsyncCallback<ImovelPlanoFaturaVO>() {
			
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
			public void onSuccess(ImovelPlanoFaturaVO result) {
				LiquidarFaturaPlanoEditPanel.this.callbackFatura = true;
				LiquidarFaturaPlanoEditPanel.this.ipfvo = result;
				update(result);
				update(LiquidarFaturaPlanoEditPanel.this.ifcvo, LiquidarFaturaPlanoEditPanel.this.ipfvo);
			}
		};
		rpc.pesquisarFatura(Long.valueOf(registro.campo[AbstractDataGrid.COLPOS_1]), callbackfatura);
		
	}
	
	public void onSelecionarClick(List<RegDataGridVO> lstRegistros){
	}
	
	public void onCancelarClick(){
	}
	
	public void onLiquidarClick() {
		// Solicita confirmacao para realizar o procedimento
		ConfirmarDialogModal cdm = new ConfirmarDialogModal("Confirmar Liquida\u00e7\u00e3o", "Voc\u00ea deseja confirmar a liquida\u00e7\u00e3o da fatura apresentada?", ConfirmarDialogModal.TIPO_MODAL_PERGUNTA_SIM_NAO);
		cdm.addConfirmarDialogModalListener(new ConfirmarDialogModalListener() {
			
			public void onSimClick() {
				LiquidarFaturaPlanoEditPanel.this.executeLiquidar();
			}
			
			public void onOkClick() {
			}
			
			public void onNaoClick() {
			}
			
			public void onCancelarClick() {
			}
		});
		
	}
	
	private void executeLiquidar() {
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ImovelPlanoFaturaVO> callback = new AsyncCallback<ImovelPlanoFaturaVO>() {
			
			public void onFailure(Throwable caught) {
				try {
					throw caught;
				} catch (IncompatibleRemoteServiceException e) {
					getAreaNotificacao().setMensagem(e.getMessage(),
							AreaNotificacao.NOTIFICACAO_ERRO);
				} catch (InvocationException e) {
					getAreaNotificacao().setMensagem(e.getMessage(),
							AreaNotificacao.NOTIFICACAO_ERRO);
				} catch (AlugueRelaxeFrontException e) {
					getAreaNotificacao().setMensagem(e.getMensagem(),
							AreaNotificacao.NOTIFICACAO_ERRO);
				} catch (Throwable e) {
					getAreaNotificacao().setMensagem(e.getMessage(),
							AreaNotificacao.NOTIFICACAO_ERRO);
				}
			}
			public void onSuccess(ImovelPlanoFaturaVO result) {
				getAreaNotificacao().setMensagem(result.msgcodeString,
							AreaNotificacao.NOTIFICACAO_INFO);
			}
		};
		rpc.liquidarFatura(this.ipfvo.id, "N", callback);
	}


}
