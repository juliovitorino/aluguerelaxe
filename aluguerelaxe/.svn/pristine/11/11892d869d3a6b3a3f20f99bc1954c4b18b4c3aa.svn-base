package br.com.jcv.aluguerelaxe.client.depoimento;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModalListener;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.RootPanel;

public class PainelDepoimentos implements EntryPoint,
		WizardListener,
		ConfirmarDialogModalListener,
		AsyncCallback<Void> {
	
	private static final String GWT_PAINEL_DEPOIMENTO = "gwt-painel-depoimento";

	private DepoimentoAssistente da; 
	
	public void onModuleLoad() {
		da = new DepoimentoAssistente();
		da.addWizardListener(this);
		RootPanel.get(GWT_PAINEL_DEPOIMENTO).add(da);
	}

	public void onVoltarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onProximoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onConcluirClick() {
		DepoimentoRPCAsync rpc = ServicosRPC.getDepoimentoRPC();
		AsyncCallback<Void> callback = this;
		try {
			rpc.criarNovoDepoimento(da.getVO(), callback);
		} catch (AlugueRelaxeFrontException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onFailure(Throwable caught) {
		try {
			da.forcarVisibilidadeBotao(AbstractWizard.BOTAO_CONCLUIR, true);
			throw caught;
		} catch (IncompatibleRemoteServiceException e) {
			da.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (InvocationException e) {
			da.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (AlugueRelaxeFrontException e) {
			if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
				da.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
			} else {
				da.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
			}
		} catch (Throwable e) {
			da.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		}
	}

	public void onSuccess(Void result) {
		ConfirmarDialogModal cdm = new ConfirmarDialogModal("Depoimento",
				"Obrigado pelo seu depoimento", ConfirmarDialogModal.TIPO_MODAL_INFO);
		cdm.addConfirmarDialogModalListener(this);
		
	}

	public void onOkClick() {
		redirect("/arweb");
	}

	public void onCancelarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onSimClick() {
		// TODO Auto-generated method stub
		
	}

	public void onNaoClick() {
		// TODO Auto-generated method stub
		
	}

	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;
	

}
