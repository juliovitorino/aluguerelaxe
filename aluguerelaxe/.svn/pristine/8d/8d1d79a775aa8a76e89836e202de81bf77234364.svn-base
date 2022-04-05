package br.com.jcv.aluguerelaxe.client.faleconosco;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModalListener;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.RootPanel;

public class FaleConosco implements EntryPoint, WizardListener, AsyncCallback<VOPadrao> {

	private static final String GWT_FALE_CONOSCO = "gwt-fale-conosco";
	
	private FaleConoscoAssistente fca;

	public void onModuleLoad() {
		fca = new FaleConoscoAssistente();
		fca.addWizardListener(this);
		RootPanel.get(GWT_FALE_CONOSCO).add(fca);
	}

	public void onVoltarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onProximoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onConcluirClick() {
		FaleConoscoRPCAsync rpc = ServicosRPC.getFaleConoscoRPC();
		AsyncCallback<VOPadrao> callback = this;
		try {
			rpc.execute(fca.getVO(), callback);
		} catch (AlugueRelaxeFrontException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onFailure(Throwable caught) {
		try {
			throw caught;
		} catch (IncompatibleRemoteServiceException e) {
			fca.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (InvocationException e) {
			fca.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (AlugueRelaxeFrontException e) {
			if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
				fca.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
			} else {
				fca.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
			}
		} catch (Throwable e) {
			fca.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		}
	}

	public void onSuccess(VOPadrao result) {
		ConfirmarDialogModal cdm = new ConfirmarDialogModal("Obrigado", "Obrigado por entrar contato conosco. Em breve estaremos entrando em contato.", ConfirmarDialogModal.TIPO_MODAL_INFO);
		cdm.addConfirmarDialogModalListener(new ConfirmarDialogModalListener() {
			
			public void onSimClick() {
			}
			
			public void onOkClick() {
				redirect("/arweb");
			}
			
			public void onNaoClick() {
			}
			
			public void onCancelarClick() {
			}
		});
	}
	
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;
}

