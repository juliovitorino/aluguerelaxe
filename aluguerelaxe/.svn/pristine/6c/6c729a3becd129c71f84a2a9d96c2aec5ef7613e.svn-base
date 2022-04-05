package br.com.jcv.aluguerelaxe.client.componente.listbox;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.ToolbarMinhaContaListener;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.QualidadeAnuncioFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteRPCAsync;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModalListener;
import br.com.jcv.aluguerelaxe.client.componente.griddataentry.GridDataEntry;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.faleconosco.FaleConoscoAssistente;
import br.com.jcv.aluguerelaxe.client.novaconta.ClienteCadastroVO;
import br.com.jcv.aluguerelaxe.client.novaconta.ClienteNovaContaWizard;
import br.com.jcv.aluguerelaxe.client.novaconta.TermoUsoVO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.RootPanel;

public class Componentes implements EntryPoint, ToolbarMinhaContaListener, ConfirmarDialogModalListener, WizardListener,AsyncCallback<ClienteVO> {
	ClienteNovaContaWizard cncw;
	FaleConoscoAssistente fca ;
	GridDataEntry<TelefoneVO> gde;
	
	public void onModuleLoad() {
		// Componente de dialogo modal
		//ConfirmarDialogModal cdm = new ConfirmarDialogModal("Confirmar", "Funcionou o componente?", ConfirmarDialogModal.TIPO_MODAL_PERGUNTA_SIM_NAO);
		//cdm.addConfirmarDialogModalListener(this);
		
		
		SessaoVO sessaovo = new SessaoVO();
		sessaovo.clientevo = new ClienteVO();
		sessaovo.clientevo.id = 2;
		
		
//		cncw = new ClienteNovaContaWizard();
//		cncw.addWizardListener(this);
//		RootPanel.get("gwt-form-imovel").add(cncw);
		
//		gde = new TelefoneGridDataEntry();
//		
//		VerticalPanel vp = new VerticalPanel();
//		vp.add(gde);
//		
//		Button bt = new Button("getVO()");
//		bt.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				List<TelefoneVO> lst = gde.getListVO();
//				
//			}
//		});
//		
//		vp.add(bt);
//
//		RootPanel.get("gwt-form-imovel").add(vp);
		
		
		RootPanel.get("gwt-form-imovel").add(new QualidadeAnuncioFormComposite());
		//RootPanel.get("gwt-form-imovel").add(new AvisosEditPanel(sessaovo, new NenhumaToolbar()));
		//RootPanel.get("gwt-form-imovel").add(new TabelaPrecoEditPanel(sessaovo, new NenhumaToolbar()));
		//RootPanel.get("gwt-form-imovel").add(new ProprietarioEditPanel(sessaovo, new ManterClienteToolbar()));
		//RootPanel.get("gwt-form-imovel").add(new FichaImovelEditPanel(sessaovo, new ManterToolbar()));
//		RootPanel.get("gwt-form-imovel").add(new MeuDesktopEditPanel(sessaovo, new NenhumaToolbar()));
		//RootPanel.get("gwt-logo-dinamico").add(new LogoDinamico());
		//RootPanel.get("gwt-form-imovel").add(new GeoLocalizacaoEditPanel(sessaovo, new NenhumaToolbar()));
		//RootPanel.get("gwt-form-imovel").add(new FieldCompositeTextBox("Nome", new TextBox()));
		//RootPanel.get("gwt-form-imovel").add(new UploadImagemEditPanel(sessaovo, new NenhumaToolbar()));
		
//		AssistenteTrocaSenha ats = new AssistenteTrocaSenha();
//		ats.setSessao(sessaovo);
//		RootPanel.get("gwt-form-imovel").add(ats);
		
//		cncw = new ClienteNovaContaWizard();
//		cncw.addWizardListener(this);
//		RootPanel.get("gwt-form-imovel").add(cncw);
		
//		fca = new FaleConoscoAssistente();
//		fca.addWizardListener(this);
//		RootPanel.get("gwt-form-imovel").add(fca);
		
		//AssistenteNovoImovel ani = new AssistenteNovoImovel();
		//ani.setSessao(sessaovo);
		//RootPanel.get("gwt-form-imovel").add(ani);

		
		
		/* Upload  - para usar descomente este trecho */
//		final FormPanel form = new FormPanel();
//		form.setEncoding(FormPanel.ENCODING_MULTIPART);
//		form.setMethod(FormPanel.METHOD_POST);
//		form.setWidth("275px");
//
//		VerticalPanel holder = new VerticalPanel();
//
//		FileUpload upload = new FileUpload();
//		upload.setName("upload");
//		holder.add(upload);
//
//		holder.add(new HTML("<hr />"));
//
//		holder.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);
//		holder.add(new Button("Submit", new ClickHandler()
//		{
//			@Override
//			public void onClick(ClickEvent event) {
//				form.submit();
//				
//			}
//		}));
//
//		form.add(holder);
//
//		form.setAction("/uploader.svlt");
//		
//		form.addSubmitCompleteHandler( new SubmitCompleteHandler() {
//			
//			@Override
//			public void onSubmitComplete(SubmitCompleteEvent event) {
//				Window.alert(event.getResults());
//			}
//		});
//		
//		form.addSubmitHandler(new SubmitHandler() {
//			
//			@Override
//			public void onSubmit(SubmitEvent event) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//
//		RootPanel.get("gwt-form-imovel").add(form);		
		/* fim upload */
	}

	public void onMeusDadosClick() {
		// TODO Auto-generated method stub
		
	}

	public void onInfoUltimoAcessoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onMeusImoveisClick() {
		// TODO Auto-generated method stub
		
	}

	public void onAssistenteNovoImovelClick() {
		// TODO Auto-generated method stub
		
	}

	public void onOkClick() {
		// TODO Auto-generated method stub
		
	}

	public void onCancelarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onSimClick() {
		Window.alert("clicou botão SIM");
	}

	public void onNaoClick() {
		Window.alert("clicou botão NAO");
		
	}

	public void onAssistenteGeoLocalizacaoClick() {
		// TODO Auto-generated method stub
		
	}
	public void onFailure(Throwable caught) {
		try {
			throw caught;
		} catch (IncompatibleRemoteServiceException e) {
			cncw.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (InvocationException e) {
			cncw.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (AlugueRelaxeFrontException e) {
			if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
				cncw.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
			} else {
				cncw.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
			}
		} catch (Throwable e) {
			cncw.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		}
	 }

	public void onSuccess(ClienteVO result) {
		redirect("/arweb/JXControllerSmartInterface?cmd=dtgLogin");
	}
	
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;

	public void onVoltarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onProximoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onConcluirClick() {
		TermoUsoVO tuvo = cncw.getAceiteTermoUso();
		if (tuvo.aceiteTermoUso) {
			ClienteCadastroVO vo = cncw.getVO();
			ClienteRPCAsync rpc = ServicosRPC.getClienteRPC();
			AsyncCallback<ClienteVO> callback = this;
			rpc.criarNovaConta(vo, callback);
		} else {
			cncw.setMensagem("Termo de uso n\u00e3o foi aceito.", AreaNotificacao.NOTIFICACAO_ERRO);
		}
	}

	public void onAssistenteUploadClick() {
		// TODO Auto-generated method stub
		
	}

	public void onTrocaSenhaClick() {
		// TODO Auto-generated method stub
		
	}

	public void onDesktopClick() {
		// TODO Auto-generated method stub
		
	}

	public void onTarifasClick() {
		// TODO Auto-generated method stub
		
	}

	public void onChatClick() {
		// TODO Auto-generated method stub
		
	}

	public void onVideoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onModoPublicidadeClick() {
		// TODO Auto-generated method stub
		
	}

}
