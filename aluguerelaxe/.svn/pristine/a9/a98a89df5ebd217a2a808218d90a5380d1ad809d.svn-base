package br.com.jcv.aluguerelaxe.client.chat;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.avisos.AvisosRPCAsync;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatVO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class ChatPortalPublico implements EntryPoint, AsyncCallback<ChatVO> {

	private ChatFormComposite cfc = null;
	
	public void onModuleLoad() {
		this.init();
		RootPanel.get("gwt-chat-portal-publico").add(this.cfc);
		this.update();
	}
	
	private void init() {
		this.cfc = new ChatFormComposite();
	}
	
	private void update() {
		// Obtem uma interface RPC
		AvisosRPCAsync rpc = ServicosRPC.getAvisoRPC();
		AsyncCallback<ChatVO> callback = this;
		rpc.getChatAtivo("PP", callback);
	}

	public void onFailure(Throwable caught) {
		  //TODO Tratar Erro
	}

	public void onSuccess(ChatVO result) {
		this.cfc.update(result);
	}
	

}
