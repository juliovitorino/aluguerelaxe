package br.com.jcv.aluguerelaxe.client.novaconta;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelwizard.ContribuicaoPagtoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.proprietario.UploadFotoPerfilDialogModal;
import br.com.jcv.aluguerelaxe.client.administracao.console.publicidades.RealizarPagtoFormComposite;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteRPCAsync;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.upload.AbstractUploadFotoFormCompositeListener;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.RootPanel;

public class NovaConta implements EntryPoint, 
									WizardListener, 
									AsyncCallback<ClienteVO>,
									AbstractUploadFotoFormCompositeListener {
	
	public static final String GWT_NOVA_CONTA = "gwt-nova-conta";

	ClienteNovaContaWizard cncw;
	UploadFotoPerfilDialogModal ufpdm;
	
	public void onVoltarClick() {
		//Não aplicado neste conexto
	}

	public void onProximoClick() {
		//Não aplicado neste conexto
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

	public void onModuleLoad() {
		cncw = new ClienteNovaContaWizard();
		cncw.addWizardListener(this);
		RootPanel.get(GWT_NOVA_CONTA).add(cncw);

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
		if (result != null){
			ufpdm = new UploadFotoPerfilDialogModal();
			ufpdm.updateHandler(this);
			ufpdm.update(result);
		}
	}
	
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;

	public void onCancelarClick() {
		ufpdm.hide();
		this.apresentaInstrucoesFinais();
		
		
	}

	public void onUploadFinished() {
		//redirect("/arweb/JXControllerSmartInterface?cmd=dtgLogin");
		ufpdm.hide();
		this.apresentaInstrucoesFinais();
	}
	
	public void apresentaInstrucoesFinais() {
			InstrucoesFinaisNovaContaFormComposite ifncfc = new InstrucoesFinaisNovaContaFormComposite();
			cncw.showCompositeFinal(ifncfc, cncw.montaHeaderPasso("flag_checkered.png", "Instru\u00e7\u00f5es Finais da Nova Conta"));
	}

}
	