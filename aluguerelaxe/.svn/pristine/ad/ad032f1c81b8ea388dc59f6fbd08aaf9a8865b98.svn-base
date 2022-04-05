package br.com.jcv.aluguerelaxe.client.administracao.console;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.andamento.AndamentoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.avisos.AvisosEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.desktop.MeuDesktopEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.enquete.EnqueteModoPublicidadeWizard;
import br.com.jcv.aluguerelaxe.client.administracao.console.faturamento.LiquidarFaturaPlanoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.faturamento.LiquidarFaturaPublicidadeEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.faturamento.LiquidarToolbar;
import br.com.jcv.aluguerelaxe.client.administracao.console.geolocalizacaowizard.GeoLocalizacaoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.FichaImovelEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelwizard.AssistenteNovoImovel;
import br.com.jcv.aluguerelaxe.client.administracao.console.liberarpgto.LiberarPgtoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.liberarpgto.LiberarPgtoToolbar;
import br.com.jcv.aluguerelaxe.client.administracao.console.migrarplano.MigrarPlanoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.pagarplano.PagarPlanoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.proprietario.ProprietarioEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.publicidades.VendaPublicidadeEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.renovacao.RenovacaoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.aprovacao.AprovacaoPreReservaEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.aprovacao.AprovacaoPreReservaToolbar;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.deposito.ConfirmarDepositoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.deposito.ConfirmarDepositoToolbar;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva.ReservaEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva.ReservaToolbar;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.transferirdeposito.TransferirDepositoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.transferirdeposito.TransferirDepositoToolbar;
import br.com.jcv.aluguerelaxe.client.administracao.console.superbanner.DesktopSuperPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.tarefaspendentes.TarefasPendentesEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.tarifawizard.TabelaPrecoEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.trocasenha.AssistenteTrocaSenha;
import br.com.jcv.aluguerelaxe.client.administracao.console.uploadwizard.UploadImagemEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.videos.VideosEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.login.LoginRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteRPCAsync;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModalListener;
import br.com.jcv.aluguerelaxe.client.componente.listbox.AbstractPlanoListbox;
import br.com.jcv.aluguerelaxe.client.componente.menu.MenuToolbar;
import br.com.jcv.aluguerelaxe.client.componente.superpanel.AbstractSuperPanel;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.ManterClienteToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.ManterToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeVO;
import br.com.jcv.aluguerelaxe.client.superpanel.SuperPanelRPCAsync;
import br.com.jcv.aluguerelaxe.client.superpanel.superbanner.SuperBannerVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Console implements EntryPoint,
								ToolbarMinhaContaListener, 
								ToolbarMinhaSessaoListener,
								ToolbarPublicidadeListener,
								ToolbarReservaListener,
								ToolbarNegociacaoListener,
								ToolbarFinanceiroListener,
								ToolbarFaturamentoListener {
	
	private static final String STYLE_CONSOLE_CONTAINER = "console-container";

	private static final String TEMA_HEADER = "orkut";
	private static final String TEMA_FOOTER = "orkut";
	
	private DockPanel dp = new DockPanel();
	private SessaoVO sessaovo = null;
	private Widget widgetCenter = new HorizontalPanel();
	private AbstractSuperPanel<SuperBannerVO> asp; 
	
	public void onModuleLoad() {
		
		//-----------------------------------------------------------------
		// Obtém o sid criptografado enviado como parâmetro na URL (producao)
		//-----------------------------------------------------------------
		RootPanel.get("gwt-console").add(dp);

		String sid = com.google.gwt.user.client.Window.Location.getParameter("sid");
		checarSessao(sid);
		
		//Testes - remover quando colocar em producao
		//try {
		//	loginFake();
		//} catch (AlugueRelaxeFrontException e) {
		//	e.printStackTrace();
		//}
		// fim testes
		
	}

	private void loginFake() throws AlugueRelaxeFrontException {
		sessaovo = new SessaoVO();
		sessaovo.clientevo = new ClienteVO();
		sessaovo.clientevo.id = 2;
		sessaovo.clientevo.email = "julio.vitorino@gmail.com";
		dp.add(montaHeader(), DockPanel.NORTH);
		dp.add(widgetCenter, DockPanel.CENTER);
		this.onChatClick();
		this.checarModoPublicidade(sessaovo);
		this.updateSuperBanner();
	}
	
	private void checarSessao(String sid) {
		LoginRPCAsync rpc = ServicosRPC.getLoginRPC();
		AsyncCallback<SessaoVO> callback = new AsyncCallback<SessaoVO>() {

			public void onFailure(Throwable caught) {
			}

			public void onSuccess(SessaoVO result) {
				if (result != null) {
					sessaovo = result;
					dp.add(montaHeader(), DockPanel.NORTH);
					dp.add(widgetCenter, DockPanel.CENTER);
					Console.this.onChatClick();
					Console.this.checarModoPublicidade(result);
					Console.this.updateSuperBanner();
					
					//dp.add(montaFooter(), DockPanel.SOUTH);
				} else {
					ConfirmarDialogModal cdm = new ConfirmarDialogModal("Tempo Excedido", "Sua sess\u00e3o encontra-se expirada.", ConfirmarDialogModal.TIPO_MODAL_AVISO);
					cdm.addConfirmarDialogModalListener(new ConfirmarDialogModalListener() {
						
						public void onSimClick() {
						}
						
						public void onOkClick() {
							redirect("/arweb/JXControllerSmartInterface?cmd=dtgLogin");
						}
						
						public void onNaoClick() {
						}
						
						public void onCancelarClick() {
						}
					});
				}
				
			}
		};
		
		rpc.validarSessao(sid, callback);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Widget montaHeader() {
		WindowPanel wpmenu = new WindowPanel("Console Administrativo",TEMA_HEADER);
		wpmenu.setWidth("960px");
		wpmenu.setStylePrimaryName("gwt-windowpanel-header");
		
		MenuToolbar am = new MenuToolbar();
		am.addSelectionHandler(new SelectionHandler<Integer>() {
			
			public void onSelection(SelectionEvent<Integer> event) {
				// TODO Auto-generated method stub
				Console.this.dp.remove(widgetCenter);
			}
		});

		//-----------------------------------------------------------------
		// Cria o menu do administrador (HOJE É ATRELADO AO MEU E-MAIL
		// gambiarra - mas eh o que preciso no momento :-(
		//-----------------------------------------------------------------
		if (this.sessaovo.clientevo.email.equals("julio.vitorino@gmail.com")){
			// ----------- RESERVAS -----------
			ToolbarReserva tadm = new ToolbarReserva();
			tadm.addToolbarListener(this);
			
			// empilha lista de toolbars
			List<AbstractToolbar> lsttadm = new ArrayList<AbstractToolbar>();
			lsttadm.add(tadm);
			
			// Cria item o menu
			am.addMenuItem("Reservas", lsttadm);
			
			// ----------- FATURAMENTO -----------
			ToolbarFaturamento tfat = new ToolbarFaturamento();
			tfat.addToolbarListener(this);
			
			// empilha lista de toolbars
			List<AbstractToolbar> lsttfat = new ArrayList<AbstractToolbar>();
			lsttfat.add(tfat);
			
			// Cria item o menu
			am.addMenuItem("Faturamento", lsttfat);
			
			
		}

		//-----------------------------------------------------------------
		// Cria o primeiro item de menu com a toolbar para o MenuMinhaConta
		//-----------------------------------------------------------------
		ToolbarMinhaConta tmc = new ToolbarMinhaConta();
		tmc.addToolbarListener(this);
		
		// empilha lista de toolbars
		List<AbstractToolbar> lsttmc = new ArrayList<AbstractToolbar>();
		lsttmc.add(tmc);
		
		// Cria item o menu
		am.addMenuItem("Minha Conta", lsttmc);
		
		//-----------------------------------------------------------------
		// Cria o terceiro item de menu com a toolbar para o Publicidade
		//-----------------------------------------------------------------
		ToolbarPublicidade tp = new ToolbarPublicidade();
		tp.addToolbarListener(this);
		
		// empilha lista de toolbars
		List<AbstractToolbar> lsttp = new ArrayList<AbstractToolbar>();
		lsttp.add(tp);
		
		// Cria item o menu
		am.addMenuItem("Publicidade", lsttp);
		
		//-----------------------------------------------------------------
		// Cria o menu toolbar para financeiro
		//-----------------------------------------------------------------
		ToolbarFinanceiro tfin = new ToolbarFinanceiro();
		tfin.addToolbarListener(this);
		
		// empilha lista de toolbars
		List<AbstractToolbar> lstfin = new ArrayList<AbstractToolbar>();
		lstfin.add(tfin);
		
		// Cria item o menu
		am.addMenuItem("Financeiro", lstfin);

		//-----------------------------------------------------------------
		// Cria o item de menu com a toolbar para o Negociacao
		//-----------------------------------------------------------------
		ToolbarNegociacao tneg = new ToolbarNegociacao();
		tneg.addToolbarListener(this);
		
		// empilha lista de toolbars
		List<AbstractToolbar> lsttneg = new ArrayList<AbstractToolbar>();
		lsttneg.add(tneg);
		
		// Cria item o menu
		am.addMenuItem("Negocia\u00e7\u00e3o", lsttneg);
		
		//-----------------------------------------------------------------
		// Cria o segundo item de menu com a toolbar para o MenuMinhaConta
		//-----------------------------------------------------------------
		ToolbarMinhaSessao tms = new ToolbarMinhaSessao();
		tms.addToolbarListener(this);
		
		// empilha lista de toolbars
		List<AbstractToolbar> lsttms = new ArrayList<AbstractToolbar>();
		lsttms.add(tms);
		
		// Cria item o menu
		am.addMenuItem("Sess\u00e3o", lsttms);

		
		wpmenu.setComponenteCenter(am);
		
		return wpmenu;
	}

	private Widget montaFooter() {
		VerticalPanel vp = new VerticalPanel();
		vp.setStylePrimaryName(STYLE_CONSOLE_CONTAINER);
		vp.addStyleName("console-footer");
		WindowPanel hp = new WindowPanel(null,TEMA_FOOTER);
		hp.setSize("967px", "80px");
		vp.add(hp);
		return vp;
	}
	

	private void checarModoPublicidade(SessaoVO vo) {
		ClienteRPCAsync rpc = ServicosRPC.getClienteRPC();
		AsyncCallback<ClienteVO> callback = new AsyncCallback<ClienteVO>() {

			public void onFailure(Throwable caught) {
				/*try {
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
				}*/
			}

			public void onSuccess(ClienteVO result) {
				if ((result != null) && (result.modoPublicidade.id < 1)){
					Console.this.sessaovo.clientevo = result;
					Console.this.onModoPublicidadeClick();
				}
			}
		};

		rpc.pesquisaRegistro(this.sessaovo.clientevo, callback);
	}
	

	public void onMeusDadosClick() {
		dp.remove(widgetCenter);
		widgetCenter = new ProprietarioEditPanel(sessaovo, new ManterClienteToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);

		
	}

	public void onMeusImoveisClick() {
		dp.remove(widgetCenter);
		widgetCenter = new FichaImovelEditPanel(sessaovo, new ManterToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
		
	}

	public void onEncerrarSessaoClick() {
		ConfirmarDialogModal cdm = new ConfirmarDialogModal("Encerrar Sess\u00e3o", 
				"Tem certeza que deseja encerrar a sess\u00e3o?", 
				ConfirmarDialogModal.TIPO_MODAL_PERGUNTA_SIM_NAO);
		
		cdm.addConfirmarDialogModalListener(new ConfirmarDialogModalListener() {
			
			public void onSimClick() {
				LoginRPCAsync rpc = ServicosRPC.getLoginRPC();
				AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>() {

					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					public void onSuccess(VOPadrao result) {
						redirect("/arweb");
						
					}
				};
				rpc.encerrarSessao(callback);
			}
			
			public void onOkClick() {
			}
			
			public void onNaoClick() {
			}
			
			public void onCancelarClick() {
			}
		});
		
	}

	public void onInfoSessaoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onAssistenteNovoImovelClick() {
		dp.remove(widgetCenter);
		widgetCenter = new WindowPanel("Assistente de Cria\u00e7\u00e3o de Im\u00f3vel", TEMA_HEADER);
		widgetCenter.setWidth("960px");

		AssistenteNovoImovel ani = new AssistenteNovoImovel();
		ani.setSessao(this.sessaovo);
		((WindowPanel) widgetCenter).setComponenteCenter(ani);
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onAssistenteGeoLocalizacaoClick() {
		dp.remove(widgetCenter);
		widgetCenter = new GeoLocalizacaoEditPanel(this.sessaovo, new NenhumaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onAssistenteUploadClick() {
		dp.remove(widgetCenter);
		widgetCenter = new UploadImagemEditPanel(this.sessaovo, new NenhumaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onTrocaSenhaClick() {
		dp.remove(widgetCenter);
		widgetCenter = new WindowPanel("Assistente de troca senha", TEMA_HEADER);
		widgetCenter.setWidth("960px");

		AssistenteTrocaSenha ani = new AssistenteTrocaSenha();
		ani.setSessao(this.sessaovo);
		((WindowPanel) widgetCenter).setComponenteCenter(ani);
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;

	public void onDesktopClick() {
		dp.remove(widgetCenter);
		widgetCenter = new MeuDesktopEditPanel(this.sessaovo, new NenhumaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onTarifasClick() {
		dp.remove(widgetCenter);
		widgetCenter = new TabelaPrecoEditPanel(this.sessaovo, new NenhumaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onChatClick() {
		dp.remove(widgetCenter);
		widgetCenter = new AvisosEditPanel(this.sessaovo, new NenhumaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onCriarPreReservaClick() {
		dp.remove(widgetCenter);
		widgetCenter = new ReservaEditPanel(this.sessaovo, new ReservaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onConfirmarReservaClick() {
		dp.remove(widgetCenter);
		widgetCenter = new ConfirmarDepositoEditPanel(this.sessaovo, new ConfirmarDepositoToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onAprovarPreReservaClick() {
		dp.remove(widgetCenter);
		widgetCenter = new AprovacaoPreReservaEditPanel(this.sessaovo, new AprovacaoPreReservaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onLiberarTransferenciaDeposito() {
		dp.remove(widgetCenter);
		widgetCenter = new TransferirDepositoEditPanel(this.sessaovo, new TransferirDepositoToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}
	public void onRenovarPlanoClick() {
		dp.remove(widgetCenter);
		widgetCenter = new RenovacaoEditPanel(this.sessaovo, new ManterToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onAndamentoReservaClick() {
		dp.remove(widgetCenter);
		widgetCenter = new AndamentoEditPanel(this.sessaovo, new NenhumaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onLiberarPgtoClick() {
		dp.remove(widgetCenter);
		widgetCenter = new LiberarPgtoEditPanel(this.sessaovo, new LiberarPgtoToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onTarefasPendentesClick() {
		dp.remove(widgetCenter);
		widgetCenter = new TarefasPendentesEditPanel(this.sessaovo, new NenhumaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onMigrarPlanoClick() {
		dp.remove(widgetCenter);
		widgetCenter = new MigrarPlanoEditPanel(this.sessaovo, new NenhumaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onPagarPlanoClick() {
		dp.remove(widgetCenter);
		widgetCenter = new PagarPlanoEditPanel(this.sessaovo, new NenhumaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
		
	}

	public void onPatrocinioClick() {
		dp.remove(widgetCenter);
		widgetCenter = new VendaPublicidadeEditPanel(this.sessaovo, new NenhumaToolbar(),AbstractPlanoListbox.TIPO_COMPRA_PATROCINADOR);
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onFuraFilaClick() {
		dp.remove(widgetCenter);
		widgetCenter = new VendaPublicidadeEditPanel(this.sessaovo, new NenhumaToolbar(),AbstractPlanoListbox.TIPO_COMPRA_FF);
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onPrimeiraPaginaClick() {
		dp.remove(widgetCenter);
		widgetCenter = new VendaPublicidadeEditPanel(this.sessaovo, new NenhumaToolbar(),AbstractPlanoListbox.TIPO_COMPRA_PP);
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onPainelDestaqueClick() {
		dp.remove(widgetCenter);
		widgetCenter = new VendaPublicidadeEditPanel(this.sessaovo, new NenhumaToolbar(),AbstractPlanoListbox.TIPO_COMPRA_PD);
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onBannerClick() {
		dp.remove(widgetCenter);
		widgetCenter = new VendaPublicidadeEditPanel(this.sessaovo, new NenhumaToolbar(),AbstractPlanoListbox.TIPO_COMPRA_BANNER);
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onSMSClick() {
		dp.remove(widgetCenter);
		widgetCenter = new VendaPublicidadeEditPanel(this.sessaovo, new NenhumaToolbar(),AbstractPlanoListbox.TIPO_COMPRA_SMS);
		dp.add(widgetCenter, DockPanel.CENTER);
	}
	
	public void onReceberFaturaClick() {
		dp.remove(widgetCenter);
		widgetCenter = new LiquidarFaturaPlanoEditPanel(new LiquidarToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onReceberPublicidadeClick() {
		dp.remove(widgetCenter);
		widgetCenter = new LiquidarFaturaPublicidadeEditPanel(new LiquidarToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onEmailClick() {
		dp.remove(widgetCenter);
		widgetCenter = new VendaPublicidadeEditPanel(this.sessaovo, new NenhumaToolbar(),AbstractPlanoListbox.TIPO_COMPRA_EMAIL);
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onSuperBannerClick() {
		dp.remove(widgetCenter);
		widgetCenter = new VendaPublicidadeEditPanel(this.sessaovo, new NenhumaToolbar(),AbstractPlanoListbox.TIPO_COMPRA_SB);
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onFacebookClick() {
		dp.remove(widgetCenter);
		widgetCenter = new VendaPublicidadeEditPanel(this.sessaovo, new NenhumaToolbar(),AbstractPlanoListbox.TIPO_COMPRA_FB);
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onMeuDesktopPublicidadeClick() {
		// TODO Auto-generated method stub
		
	}

	public void onVideoClick() {
		dp.remove(widgetCenter);
		widgetCenter = new VideosEditPanel(this.sessaovo, new NenhumaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onModoPublicidadeClick() {
		dp.remove(widgetCenter);
		final EnqueteModoPublicidadeWizard empw = new EnqueteModoPublicidadeWizard();
		empw.addWizardListener(new WizardListener() {
			
			public void onVoltarClick() {
			}
			
			public void onProximoClick() {
			}
			
			public void onConcluirClick() {
				if (empw.getVO().id > -1){
					Console.this.atualizarEnqueteModoPublicidade(empw.getVO());
				} else {
					ConfirmarDialogModal cdm = new ConfirmarDialogModal("Pesquisa", "Por favor selecione uma op\u00e7\u00e3o da lista", ConfirmarDialogModal.TIPO_MODAL_AVISO);
					cdm.addConfirmarDialogModalListener(new ConfirmarDialogModalListener() {
						
						public void onSimClick() {						}
						
						public void onOkClick() {
							Console.this.checarModoPublicidade(sessaovo);
						}
						
						public void onNaoClick() {
						}
						
						public void onCancelarClick() {
						}
					});
				}
				
			}
		});
		
		WindowPanel wp = new WindowPanel("Enquete do AlugueRelaxe", TEMA_HEADER);
		wp.setComponenteCenter(empw);
		widgetCenter = wp;
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	protected void atualizarEnqueteModoPublicidade(ModoPublicidadeVO mpvo) {
		ClienteRPCAsync rpc = ServicosRPC.getClienteRPC();
		AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>() {

			public void onFailure(Throwable caught) {
				/*try {
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
				}*/
			}

			public void onSuccess(VOPadrao result) {
				ConfirmarDialogModal cdm = new ConfirmarDialogModal("Pesquisa", result.msgcodeString, ConfirmarDialogModal.TIPO_MODAL_AVISO);
				cdm.addConfirmarDialogModalListener(new ConfirmarDialogModalListener() {
					
					public void onSimClick() {
					}
					
					public void onOkClick() {
						Console.this.onChatClick();
					}
					
					public void onNaoClick() {
					}
					
					public void onCancelarClick() {
					}
				});
			}
		};

		this.sessaovo.clientevo.modoPublicidade = mpvo;
		rpc.atualizarEnqueteModoPublicidade(this.sessaovo.clientevo, callback);
	}

	
	private void updateSuperBanner() {
		SuperPanelRPCAsync rpc = ServicosRPC.getSuperPanelRPC();
		AsyncCallback<SuperBannerVO> callback = new AsyncCallback<SuperBannerVO>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(SuperBannerVO result) {
				if(result.indSuperBanner){
					Console.this.asp = new DesktopSuperPanel();
					Console.this.asp.setHTML(new HTML(result.html));
					Console.this.asp.center();
				}
			}
		};
		rpc.getSuperBannerDD(callback);
		// TODO Auto-generated method stub
		
	}

}

