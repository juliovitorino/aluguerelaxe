package br.com.jcv.aluguerelaxe.client.administracao.console.renovacao;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.SituacaoFinanceiraAnuncioFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.TrackerSituacaoFinanceiraAnuncioFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModalListener;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetratoListener;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.ManterToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.ManterToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoFaturaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class RenovacaoEditPanel extends AbstractFormEditPanel<ManterToolbar, WindowPanel, ImovelFichaCompletaVO> 
		implements ManterToolbarListener, PortaRetratoListener{

	private static final String CLIENTE_FORM_TEMA = "orkut";
	
	private static final int ORDEM_GALERIA = 0;
	private static final int ORDEM_TRACKER_SITUACAO_FINANCEIRA = 1;
	private static final int ORDEM_SITUACAO_FINANCEIRA_ANUNCIO = 2;
	
	private ImovelFichaCompletaVO ifcvo = null;
	private ImovelPlanoFaturaVO ipfvo = null;
	private boolean callbackFicha = false;
	private boolean callbackFatura = false;
	
	public RenovacaoEditPanel(SessaoVO sessaovo, ManterToolbar toolbar) {
		super(toolbar);
		
		WindowPanel wpGaleria = new WindowPanel("Selecione o im\u00f3vel para renovar o an\u00fancio",CLIENTE_FORM_TEMA);
		wpGaleria.setSize("960px", "190px");

		SimplePanel sp = new SimplePanel();
		sp.add(new GaleriaImovel(sessaovo.clientevo.id, this));
		wpGaleria.setComponenteCenter(sp);

		WindowPanel wpTrackerSF = new WindowPanel("Acompanhamento da Financeira do An\u00fancio",CLIENTE_FORM_TEMA,true,true,true);
		wpTrackerSF.setWidth("960px");
		wpTrackerSF.setComponenteCenter(new TrackerSituacaoFinanceiraAnuncioFormComposite());

		WindowPanel wpSituacaoFinanceira = new WindowPanel("Situa\u00e7\u00e3o Financeira do An\u00fancio",CLIENTE_FORM_TEMA,true,true,true);
		wpSituacaoFinanceira.setWidth("960px");
		wpSituacaoFinanceira.setComponenteCenter(new SituacaoFinanceiraAnuncioFormComposite());

		
		addObjetoCompositeToPanel(wpGaleria);
		addObjetoCompositeToPanel(wpTrackerSF);
		addObjetoCompositeToPanel(wpSituacaoFinanceira);
		
		esconderPainel(wpTrackerSF);
		esconderPainel(wpSituacaoFinanceira);

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
		// nada a fazer!
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
		// Inicializa controladores de callback
		callbackFicha = false;
		callbackFatura = false;
		
		//-----------------------------------------------------
		// Configura visibildade de alguns FormComposite
		//-----------------------------------------------------

		WindowPanel wpGaleria = getListObjetoComposite().get(ORDEM_GALERIA);
		WindowPanel wpTrackerSF = getListObjetoComposite().get(ORDEM_TRACKER_SITUACAO_FINANCEIRA);
		WindowPanel wpSituacaoFinanceira = getListObjetoComposite().get(ORDEM_SITUACAO_FINANCEIRA_ANUNCIO);
		
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
				RenovacaoEditPanel.this.callbackFicha = true;
				RenovacaoEditPanel.this.ifcvo = result;
				update(result);
				update(RenovacaoEditPanel.this.ifcvo, RenovacaoEditPanel.this.ipfvo);
			}
		};
		rpc.pesquisarFichaCompletaImovel(vo.imovel.id, callback);
		
		AsyncCallback<ImovelPlanoFaturaVO> callbackfatura = new AsyncCallback<ImovelPlanoFaturaVO>() {
			
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
			public void onSuccess(ImovelPlanoFaturaVO result) {
				RenovacaoEditPanel.this.callbackFatura = true;
				RenovacaoEditPanel.this.ipfvo = result;
				update(result);
				update(RenovacaoEditPanel.this.ifcvo, RenovacaoEditPanel.this.ipfvo);
			}
		};
		rpc.pesquisarUltimaFatura(vo.imovel.id, "N", callbackfatura);
		
	}

	public void onRemoverClick() {
		// TODO Auto-generated method stub
	}

	public void onSairClick() {
		// TODO Auto-generated method stub
	}

	public void onSalvarClick() {
		
		ConfirmarDialogModal cdm = new ConfirmarDialogModal("Renovar Plano", "Confirma a renova\u00e7\u00e3o do plano?", ConfirmarDialogModal.TIPO_MODAL_PERGUNTA_SIM_NAO );
		cdm.addConfirmarDialogModalListener(new ConfirmarDialogModalListener() {
			
			public void onSimClick() {
				
				FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
				AsyncCallback<ImovelPlanoFaturaVO> callback = new AsyncCallback<ImovelPlanoFaturaVO>() {

					public void onFailure(Throwable caught) {
						 try {
						       throw caught;
						     } catch (IncompatibleRemoteServiceException e) {
								getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						     } catch (InvocationException e) {
								getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						     } catch (AlugueRelaxeFrontException e) {
						    	 if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
						    		 getAreaNotificacao().setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
						    	 } else {
						    		 getAreaNotificacao().setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
						    	 }
						     } catch (Throwable e) {
								getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						     }			
					}

					public void onSuccess(ImovelPlanoFaturaVO result) {
						getAreaNotificacao().setMensagem(result.msgcodeString, AreaNotificacao.NOTIFICACAO_INFO);
					}
				};
				rpc.renovarPlano(ifcvo.imovel.id, callback);
			}
			
			public void onOkClick() {
				// TODO Auto-generated method stub
				
			}
			
			public void onNaoClick() {
				// TODO Auto-generated method stub
				
			}
			
			public void onCancelarClick() {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public void onRefreshClick() {
		// TODO Auto-generated method stub
		
	}


}
