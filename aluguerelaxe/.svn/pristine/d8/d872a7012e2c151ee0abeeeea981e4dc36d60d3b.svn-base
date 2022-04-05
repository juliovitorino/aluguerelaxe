package br.com.jcv.aluguerelaxe.client.campanhas.amigoindicaamigo;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.RootPanel;

public class PromocaoAmigoIndicaAmigo implements EntryPoint,
		AsyncCallback<VOPadrao>, WizardListener {

	public static final String GWT_PROMO_AMIGO_INDICA_AMIGO = "gwt-paia";

	PromocaoAmigoIndicaAmigoWizard paiawzrd;
	

	public void onModuleLoad() {
		paiawzrd = new PromocaoAmigoIndicaAmigoWizard();
		paiawzrd.addWizardListener(this);
		RootPanel.get(GWT_PROMO_AMIGO_INDICA_AMIGO).add(paiawzrd);	
	}

	public void onVoltarClick() {
		// TODO Auto-generated method stub

	}

	public void onProximoClick() {
		// TODO Auto-generated method stub

	}

	public void onConcluirClick() {

		// Obtem os dados que o participante inseriu no formulario
		PromocaoAmigoIndicaAmigoVO vo = paiawzrd.getVO();
		
		// Chama o processo assincrono para realizar a inscricao do participante
		PromocaoAmigoIndicaAmigoRPCAsync rpc = ServicosRPC.getPromocaoAmigoIndicaAmigoRPC();
		AsyncCallback<VOPadrao> callback = this;
		paiawzrd.setMensagem("Enviando ...", AreaNotificacao.NOTIFICACAO_WARNING);
		rpc.realizarInscricao(vo, callback);
		

	}

	public void onFailure(Throwable caught) {
		try {
			throw caught;
		} catch (IncompatibleRemoteServiceException e) {
			paiawzrd.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (InvocationException e) {
			paiawzrd.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (AlugueRelaxeFrontException e) {
			if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
				paiawzrd.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
			} else {
				paiawzrd.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
			}
		} catch (Throwable e) {
			paiawzrd.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		}
	}

	public void onSuccess(VOPadrao result) {
		if (result != null){
			paiawzrd.setMensagem(result.msgcodeString, AreaNotificacao.NOTIFICACAO_INFO);
			redirect("/arweb/JXControllerSmartInterface?cmd=dtgStartAlugueRelaxe");

			
		}
	}
	
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;



}
