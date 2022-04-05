package br.com.jcv.aluguerelaxe.client.administracao.console.avisos;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class AvisosEditPanel extends AbstractFormEditPanel<NenhumaToolbar, WindowPanel, VOPadrao> implements
		NenhumaToolbarListener {

	private ChatFormComposite agcfc = new ChatFormComposite();
	private ChatFormComposite apcfc = new ChatFormComposite();
	private static final String AVISO_FORM_TEMA = "orkut";
	private SessaoVO sessaovo = null;

	public AvisosEditPanel(SessaoVO sessao, NenhumaToolbar toolbar) {
		super(toolbar);
		
		this.sessaovo = sessao;
		WindowPanel wpEC = new WindowPanel("Aviso pra voc\u00ea",AVISO_FORM_TEMA,true,true,true);
		wpEC.setSize("960px", "200px");
		wpEC.setComponenteCenter(apcfc);

		WindowPanel wpAvisoGeral = new WindowPanel("Avisos Gerais",AVISO_FORM_TEMA,true,true,true);
		wpAvisoGeral.setSize("960px", "330px");
		wpAvisoGeral.setComponenteCenter( agcfc );

		addObjetoCompositeToPanel(wpEC);
		addObjetoCompositeToPanel(wpAvisoGeral);
		
		update();
	}
 
	
	@Override
	public VOPadrao getVO(WindowPanel composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VOPadrao getVO(List<WindowPanel> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		this.updateAvisosGerais();
		this.updateAvisosPrivados();
	}
	
	
	private void updateAvisosPrivados() {
		AvisosRPCAsync rpc = ServicosRPC.getAvisoRPC();
		AsyncCallback<ChatVO> callback = new AsyncCallback<ChatVO>() {

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

		 public void onSuccess(ChatVO result) {
			 AvisosEditPanel.this.apcfc.update(result);
		 }
		};
		
		rpc.getChatPrivado("DD", sessaovo.clientevo.id, callback);
		
		
	}
	
	private void updateAvisosGerais() {
		//vo.id = sessaovo.clientevo.id;
		AvisosRPCAsync rpc = ServicosRPC.getAvisoRPC();
		AsyncCallback<ChatVO> callback = new AsyncCallback<ChatVO>() {

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

		 public void onSuccess(ChatVO result) {
			 AvisosEditPanel.this.agcfc.update(result);
		 }
		};
		
		rpc.getChatPrivado("DD", -1, callback);
		
	}

}
