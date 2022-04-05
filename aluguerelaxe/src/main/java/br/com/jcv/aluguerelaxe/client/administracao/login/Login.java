package br.com.jcv.aluguerelaxe.client.administracao.login;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Login implements EntryPoint,ClickHandler,AsyncCallback<SessaoVO> {

	private static final String PATH_BOTOES = "images/botoes/";
	private static final String IMG_ENTRAR = "entrar.png";
	private static final String IMG_LIMPAR = "limpar.png";
	
	private LoginFormComposite lfc;
	
	public void onModuleLoad() {
		RootPanel.get("gwt-tela-login").add(montaTelaLogin());
	}

	private Widget montaTelaLogin() {
		DockPanel dc = new DockPanel();
		HorizontalPanel hpbtn = new HorizontalPanel();

		this.lfc = new LoginFormComposite();
		Image btnLimpar = new Image(PATH_BOTOES + IMG_LIMPAR);
		Image btnEntrar = new Image(PATH_BOTOES + IMG_ENTRAR);
		
		btnLimpar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				lfc.clear();
			}
		});
		
		btnEntrar.addClickHandler(this);
		
		hpbtn.add(btnEntrar);
		hpbtn.add(btnLimpar);
		
		dc.add(hpbtn, DockPanel.SOUTH);
		dc.add(lfc, DockPanel.CENTER);
		
		return dc;
	}
	
	public void onClick(ClickEvent event) {
		LoginRPCAsync rpc = ServicosRPC.getLoginRPC();
		AsyncCallback<SessaoVO> callback = this;
		LoginVO vo = lfc.getVO();
		try {
			rpc.login(vo.email, vo.senha, callback);
		} catch (AlugueRelaxeFrontException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void onFailure(Throwable caught) {
		try {
			throw caught;
		} catch (IncompatibleRemoteServiceException e) {
			lfc.update(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (InvocationException e) {
			lfc.update(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (AlugueRelaxeFrontException e) {
			if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
				lfc.update(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
			} else {
				lfc.update(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
			}
		} catch (Throwable e) {
			lfc.update(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		}
	 }

	public void onSuccess(SessaoVO result) {
		redirect("/arweb/Console.html?sid=" + result.sessionid);
	}	
	
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;
	
}

