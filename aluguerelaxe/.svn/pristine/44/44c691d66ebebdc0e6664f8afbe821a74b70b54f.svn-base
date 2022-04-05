package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModalListener;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.form.EnderecoFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.foto.FotoMICheckbox;
import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetratoListener;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.AdicionarRemoverToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.ManterToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.ManterToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.UploadToolbar;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoFaturaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class FichaImovelEditPanel extends AbstractFormEditPanel<ManterToolbar, WindowPanel, ImovelFichaCompletaVO> 
		implements ManterToolbarListener, PortaRetratoListener{

	private static final String CLIENTE_FORM_TEMA = "orkut";
	
	private static final int ORDEM_GALERIA = 0;
	private static final int ORDEM_TRACKER_SITUACAO_FINANCEIRA = 1;
	private static final int ORDEM_SITUACAO_FINANCEIRA_ANUNCIO = 2;
	private static final int ORDEM_IMOVEL = 3;
	private static final int ORDEM_ENDERECO = 4;
	private static final int ORDEM_CARAC_IMOVEL = 5;
	private static final int ORDEM_CARAC_CONDOMINIO = 6;
	private static final int ORDEM_TABELA_PRECO = 7;
	private static final int ORDEM_TABELA_FOTOS = 8;
	private static final int ORDEM_LISTA_UPLOAD = 9;
	
	private ImovelFichaCompletaVO ifcvo = null;
	private ImovelPlanoFaturaVO ipfvo = null;
	private boolean callbackFicha = false;
	private boolean callbackFatura = false;
	
	public FichaImovelEditPanel(SessaoVO sessaovo, ManterToolbar toolbar) {
		super(toolbar);
		
		WindowPanel wpGaleria = new WindowPanel("Selecione o im\u00f3vel para manipular a carteira",CLIENTE_FORM_TEMA);
		wpGaleria.setSize("960px", "190px");

		SimplePanel sp = new SimplePanel();
		sp.add(new GaleriaImovel(sessaovo.clientevo.id, this));
		wpGaleria.setComponenteCenter(sp);

		WindowPanel wpTrackerSF = new WindowPanel("Acompanhamento da Compra do An\u00fancio",CLIENTE_FORM_TEMA,true,true,true);
		wpTrackerSF.setWidth("960px");
		wpTrackerSF.setComponenteCenter(new TrackerSituacaoFinanceiraAnuncioFormComposite());

		WindowPanel wpSituacaoFinanceira = new WindowPanel("Situa\u00e7\u00e3o Financeira do An\u00fancio",CLIENTE_FORM_TEMA,true,true,true);
		wpSituacaoFinanceira.setWidth("960px");
		wpSituacaoFinanceira.setComponenteCenter(new SituacaoFinanceiraAnuncioFormComposite());

		WindowPanel wpImovel = new WindowPanel("Dados Cadastrais",CLIENTE_FORM_TEMA,true,true,true);
		wpImovel.setWidth("960px");
		wpImovel.setComponenteCenter(new ImovelPropriedadeFormComposite(sessaovo));

		WindowPanel wpEC = new WindowPanel("Endere\u00e7o Cadastral",CLIENTE_FORM_TEMA,true,true,true);
		wpEC.setSize("960px", "200px");
		wpEC.setComponenteCenter(new EnderecoFormComposite());

		WindowPanel wpCaracImovel = new WindowPanel("Caracter\u00edsticas do im\u00f3vel",CLIENTE_FORM_TEMA,true,true,true);
		wpCaracImovel.setWidth("960px");
		wpCaracImovel.setComponenteCenter(new CaracteristicaFormComposite());
		
		WindowPanel wpCaracCondominio = new WindowPanel("Caracter\u00edsticas dentro condom\u00ednio",CLIENTE_FORM_TEMA,true,true,true);
		wpCaracCondominio.setWidth("960px");
		wpCaracCondominio.setComponenteCenter(new CaracteristicaFormComposite());

		WindowPanel wpTP = new WindowPanel("Tabela de Pre\u00e7o",CLIENTE_FORM_TEMA,true,true,true);
		wpTP.setWidth("960px");
		wpTP.setComponenteCenter(new TabelaPrecoEditPanel(new AdicionarRemoverToolbar()));

		WindowPanel wpFotos = new WindowPanel("Fotos deste im\u00f3vel",CLIENTE_FORM_TEMA,true,true,true);
		wpFotos.setWidth("960px");
		wpFotos.setComponenteCenter(new GaleriaLadoLadoFotoMIEditPanel(new NenhumaToolbar()));

		WindowPanel wpUpload = new WindowPanel("Enviar novas imagens",CLIENTE_FORM_TEMA,true,true,true);
		wpUpload.setWidth("960px");
		wpUpload.setComponenteCenter(new UploadEditPanel(new UploadToolbar()));
		
		addObjetoCompositeToPanel(wpGaleria);
		addObjetoCompositeToPanel(wpTrackerSF);
		addObjetoCompositeToPanel(wpSituacaoFinanceira);
		addObjetoCompositeToPanel(wpImovel);
		addObjetoCompositeToPanel(wpEC);
		addObjetoCompositeToPanel(wpCaracImovel);
		addObjetoCompositeToPanel(wpCaracCondominio);
		addObjetoCompositeToPanel(wpTP);
		addObjetoCompositeToPanel(wpFotos);
		addObjetoCompositeToPanel(wpUpload);
		
		esconderPainel(wpImovel);
		esconderPainel(wpTrackerSF);
		esconderPainel(wpSituacaoFinanceira);
		esconderPainel(wpEC);
		esconderPainel(wpCaracImovel);
		esconderPainel(wpCaracCondominio);
		esconderPainel(wpTP);
		esconderPainel(wpFotos);
		esconderPainel(wpUpload);

		update();

	}
	
	private void esconderPainel(Widget painel) {
		painel.setVisible(false);
	}

	private void mostrarPainel(Widget painel) {
		painel.setVisible(true);
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
		WindowPanel wpImovel = getListObjetoComposite().get(ORDEM_IMOVEL);
		ImovelPropriedadeFormComposite ipfc = (ImovelPropriedadeFormComposite) wpImovel.getComponenteCenter();

		WindowPanel wpEC = getListObjetoComposite().get(ORDEM_ENDERECO);
		EnderecoFormComposite efc = (EnderecoFormComposite) wpEC.getComponenteCenter();

		WindowPanel wpCI = getListObjetoComposite().get(ORDEM_CARAC_IMOVEL);
		CaracteristicaFormComposite cfcci = (CaracteristicaFormComposite) wpCI.getComponenteCenter();

		WindowPanel wpCC = getListObjetoComposite().get(ORDEM_CARAC_CONDOMINIO);
		CaracteristicaFormComposite cfcc = (CaracteristicaFormComposite) wpCC.getComponenteCenter();

		WindowPanel wpTP = getListObjetoComposite().get(ORDEM_TABELA_PRECO);
		TabelaPrecoEditPanel tpep = (TabelaPrecoEditPanel) wpTP.getComponenteCenter();

		WindowPanel wpFotos = getListObjetoComposite().get(ORDEM_TABELA_FOTOS);
		GaleriaLadoLadoFotoMIEditPanel gllf = (GaleriaLadoLadoFotoMIEditPanel) wpFotos.getComponenteCenter();

		WindowPanel wpUpload = getListObjetoComposite().get(ORDEM_LISTA_UPLOAD);
		UploadEditPanel uep = (UploadEditPanel) wpUpload.getComponenteCenter();

		ipfc.update(vo.imovel);
		efc.update(vo.imovel.endereco);
		
		if (vo.caracteristicaImovel != null){
			cfcci.update(vo.caracteristicaImovel);
		}
		if (vo.caracteristicaCondominio != null){
			cfcc.update(vo.caracteristicaCondominio);
		}
		if (vo.tabelaPreco != null){
			tpep.update(vo.tabelaPreco);
		}
		if (vo.imagensImovelMI != null) {
			gllf.update(vo.imagensImovelMI);
		} else {
			List<GaleriaLadoLadoFotoMIFormComposite> lstGaleria = gllf.getListObjetoComposite();
			if (lstGaleria != null) {
				for (GaleriaLadoLadoFotoMIFormComposite galeria : lstGaleria) {
					galeria.clear();
				}
			}
		}
		uep.update(vo.imovel,vo.cliente);
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


	public void onNovoClick() {
		//--------------------------------------------------
		// Modifica o estado do formulario
		//--------------------------------------------------
		setEstado(AbstractEditPanel.ESTADO_ADICIONAR);

		//--------------------------------------------------
		// Obtem os FormComposites
		//--------------------------------------------------
		WindowPanel wpImovel = getListObjetoComposite().get(ORDEM_IMOVEL);
		ImovelPropriedadeFormComposite ipfc = (ImovelPropriedadeFormComposite) wpImovel.getComponenteCenter();

		WindowPanel wpEC = getListObjetoComposite().get(ORDEM_ENDERECO);
		EnderecoFormComposite efc = (EnderecoFormComposite) wpEC.getComponenteCenter();

		WindowPanel wpCI = getListObjetoComposite().get(ORDEM_CARAC_IMOVEL);
		CaracteristicaFormComposite cfcci = (CaracteristicaFormComposite) wpCI.getComponenteCenter();

		WindowPanel wpCC = getListObjetoComposite().get(ORDEM_CARAC_CONDOMINIO);
		CaracteristicaFormComposite cfcc = (CaracteristicaFormComposite) wpCC.getComponenteCenter();

		WindowPanel wpTP = getListObjetoComposite().get(ORDEM_TABELA_PRECO);
		TabelaPrecoEditPanel tpep = (TabelaPrecoEditPanel) wpTP.getComponenteCenter();

		WindowPanel wpUpload = getListObjetoComposite().get(ORDEM_LISTA_UPLOAD);
		UploadEditPanel upep = (UploadEditPanel) wpUpload.getComponenteCenter();
		
		WindowPanel wpFotos = getListObjetoComposite().get(ORDEM_TABELA_FOTOS);
		GaleriaLadoLadoFotoMIEditPanel gllf = (GaleriaLadoLadoFotoMIEditPanel) wpFotos.getComponenteCenter();

		WindowPanel wpGaleria = getListObjetoComposite().get(ORDEM_GALERIA);

		//--------------------------------------------------
		// desativa alguns FormEdit
		//--------------------------------------------------
		wpUpload.setVisible(false);
		wpTP.setVisible(false);
		wpFotos.setVisible(false);
		wpGaleria.setVisible(false);
		
		//--------------------------------------------------
		// Limpa os FormComposite
		//--------------------------------------------------
		
		ipfc.clear();
		efc.clear();
		cfcci.clear();
		cfcc.clear();
		tpep.removeTodosObjetoCompositeFromPanel();
		upep.removeTodosObjetoCompositeFromPanel();
		gllf.removeTodosObjetoCompositeFromPanel();
		
		getAreaNotificacao().setMensagem("Entre com as informa\u00e7\u00f5es do novo im\u00f3vel na ficha abaixo" , AreaNotificacao.NOTIFICACAO_INFO);
		
	}

	public void onSalvarClick() {
		ImovelFichaCompletaVO vo = new ImovelFichaCompletaVO();
		
		WindowPanel wpImovel = getListObjetoComposite().get(ORDEM_IMOVEL);
		ImovelPropriedadeFormComposite ipfc = (ImovelPropriedadeFormComposite) wpImovel.getComponenteCenter();

		WindowPanel wpEC = getListObjetoComposite().get(ORDEM_ENDERECO);
		EnderecoFormComposite efc = (EnderecoFormComposite) wpEC.getComponenteCenter();

		WindowPanel wpCI = getListObjetoComposite().get(ORDEM_CARAC_IMOVEL);
		CaracteristicaFormComposite cfcci = (CaracteristicaFormComposite) wpCI.getComponenteCenter();

		WindowPanel wpCC = getListObjetoComposite().get(ORDEM_CARAC_CONDOMINIO);
		CaracteristicaFormComposite cfcc = (CaracteristicaFormComposite) wpCC.getComponenteCenter();

		WindowPanel wpTP = getListObjetoComposite().get(ORDEM_TABELA_PRECO);
		TabelaPrecoEditPanel tpep = (TabelaPrecoEditPanel) wpTP.getComponenteCenter();
		
		WindowPanel wpFotos = getListObjetoComposite().get(ORDEM_TABELA_FOTOS);
		GaleriaLadoLadoFotoMIEditPanel gllf = (GaleriaLadoLadoFotoMIEditPanel) wpFotos.getComponenteCenter();
		List<GaleriaLadoLadoFotoMIFormComposite> lstImagens = gllf.getListObjetoComposite();
		for (GaleriaLadoLadoFotoMIFormComposite item : lstImagens) {
			List<FotoMICheckbox> lstImgsChk = item.getListWidgets();
			if (lstImgsChk != null){
				vo.imagensImovelMI = new ArrayList<ImovelImagemVideoVO>();
				for (FotoMICheckbox itemfoto : lstImgsChk) {
					if (itemfoto.getCheckDeletar().getValue()) {
						vo.imagensImovelMI.add(itemfoto.getVO());
					}
				}
			}
		}
		vo.imovel = ipfc.getVO();
		vo.imovel.endereco = efc.getVO();
		vo.caracteristicaImovel = cfcci.getListImovel();
		vo.caracteristicaCondominio = cfcc.getListCondominio();
		vo.tabelaPreco = tpep.getVO();
		
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ImovelFichaCompletaVO> callback = new AsyncCallback<ImovelFichaCompletaVO>() {

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

			public void onSuccess(ImovelFichaCompletaVO result) {
				getAreaNotificacao().setMensagem(result.msgcodeString, AreaNotificacao.NOTIFICACAO_INFO);
				setEstado(AbstractEditPanel.ESTADO_ATUALIZAR);
			}
		};
		if (getEstado() == AbstractEditPanel.ESTADO_ATUALIZAR) {
			rpc.atualizarImovelCarteira(vo, callback);
		} else {
			rpc.adicionarImovelCarteira(vo, callback);
		}
	}

	public void onRemoverClick() {
		// Solicita confirmacao para realizar o procedimento
		ConfirmarDialogModal cdm = new ConfirmarDialogModal("Confirmar remo\u00e7\u00e3o do An\u00fancio", "Voc\u00ea deseja remover a veicula\u00e7\u00e3o do an\u00fancio deste im\u00f3vel?", ConfirmarDialogModal.TIPO_MODAL_PERGUNTA_SIM_NAO);
		cdm.addConfirmarDialogModalListener(new ConfirmarDialogModalListener() {
			
			public void onSimClick() {
				FichaImovelEditPanel.this.inativarAnuncio();
			}
			
			public void onOkClick() {
			}
			
			public void onNaoClick() {
			}
			
			public void onCancelarClick() {
			}
		});
		
	}
	
	private void inativarAnuncio() {
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>() {
			
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
			public void onSuccess(VOPadrao result) {
				getAreaNotificacao().setMensagem(result.msgcodeString,
							AreaNotificacao.NOTIFICACAO_INFO);
			}
		};
		rpc.inativarAnuncio(this.ifcvo.imovel.id, callback);
		
	}

	public void onSairClick() {
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
		WindowPanel wpImovel = getListObjetoComposite().get(ORDEM_IMOVEL);
		WindowPanel wpEC = getListObjetoComposite().get(ORDEM_ENDERECO);
		WindowPanel wpCI = getListObjetoComposite().get(ORDEM_CARAC_IMOVEL);
		WindowPanel wpCC = getListObjetoComposite().get(ORDEM_CARAC_CONDOMINIO);
		WindowPanel wpTP = getListObjetoComposite().get(ORDEM_TABELA_PRECO);
		WindowPanel wpFotos = getListObjetoComposite().get(ORDEM_TABELA_FOTOS);
		WindowPanel wpUpload = getListObjetoComposite().get(ORDEM_LISTA_UPLOAD);
		
		mostrarPainel(wpGaleria);
		mostrarPainel(wpTrackerSF);
		mostrarPainel(wpSituacaoFinanceira);
		mostrarPainel(wpImovel);
		mostrarPainel(wpEC);
		mostrarPainel(wpCI);
		mostrarPainel(wpCC);
		//mostrarPainel(wpTP);
		mostrarPainel(wpFotos);
		//mostrarPainel(wpUpload);

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
				FichaImovelEditPanel.this.callbackFicha = true;
				FichaImovelEditPanel.this.ifcvo = result;
				update(result);
				update(FichaImovelEditPanel.this.ifcvo, FichaImovelEditPanel.this.ipfvo);
			}
		};
		rpc.pesquisarFichaCompletaImovel(vo.imovel.id, callback);
		
		AsyncCallback<ImovelPlanoFaturaVO> callbackfatura = new AsyncCallback<ImovelPlanoFaturaVO>() {
			
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
			public void onSuccess(ImovelPlanoFaturaVO result) {
				FichaImovelEditPanel.this.callbackFatura = true;
				FichaImovelEditPanel.this.ipfvo = result;
				update(result);
				update(FichaImovelEditPanel.this.ifcvo, FichaImovelEditPanel.this.ipfvo);
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
