package br.com.jcv.aluguerelaxe.client.administracao.login;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h1>LoginComposite</h1>
 * <p>Monta o formulário de login da área administrativa do usuário</p>
 * 
 * @author julio
 * 
 */
public class LoginComposite extends Composite implements ClickHandler {

	TextBox txtnome = null;
	PasswordTextBox txtpasswd = null;

	/**
	 * <h1>LoginComposite</h1>
	 * <p>Construtor da composição da tela de login</p>
	 */
	public LoginComposite() {
			initWidget(montaLogin());
	}
	
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;

	/**
	 * <h1>montaLogin</h1>
	 * <p>Método principal para a montagem da tela de login</p>
	 * @return Widget
	 */
	public VerticalPanel montaLogin() {
		VerticalPanel sp = new VerticalPanel();
		sp.getElement().setId("ar-login-main");
		
		sp.add(montaContainerLogin());
		return sp;
	}
		
	/**
	 * <h1>montaContainerLogin</h1>
	 * <p>Container da tela de login</p>
	 * @return Widget
	 */
	public Widget montaContainerLogin() {
		VerticalPanel vp = new VerticalPanel();
		vp.setStyleName("ar-login");
		
		vp.add(montaTitulo());
		vp.add(montaSubtitulo());
		vp.add(montaLoginDataEntry());
		vp.add(montaMensagemRetornoDataEntry());
		
		return vp;
	}

	private Widget montaMensagemRetornoDataEntry() {
		VerticalPanel vp = new VerticalPanel();
		vp.setStyleName("ar-login-msg-retorno-data-entry");
		
		return vp;
	}


	/**
	 * <h1>montaLoginDataEntry</h1>
	 * <p>Monta campos de entrada de dados de login</p>
	 * @return Widget
	 */
	private Widget montaLoginDataEntry() {
		VerticalPanel vp = new VerticalPanel();
		vp.setStyleName("ar-login-data-entry");
		
		Grid grd = new Grid(4,2);
		Label lblnome = new Label("E-mail:");
		Label lblpasswd = new Label("Senha:");
		txtnome = new TextBox();
		txtpasswd = new PasswordTextBox();
		Label lblEsqueceuPwd = new Label("Esqueceu sua senha?");
		
		Button btn = new Button("Login");
		btn.addClickHandler(this);
		
		//---------------------------------------------------------
		// adiciona campos na grid
		//---------------------------------------------------------
		grd.setWidget(0, 0, lblnome);
		grd.setWidget(1, 0, lblpasswd);
		grd.setWidget(0, 1, txtnome);
		grd.setWidget(1, 1, txtpasswd);
		grd.setWidget(2, 1, lblEsqueceuPwd);
		grd.setWidget(3, 1, btn);
		
		vp.add(grd);
		
		return vp;
	}

	private Widget montaSubtitulo() {
		Label lblSubTitulo = new Label("usando sua conta");
		return lblSubTitulo;
	}

	private Widget montaTitulo() {
		Label lblTitulo = new Label("Acesso ao painel administrativo");
		return lblTitulo;
	}

	public void onClick(ClickEvent event) {
		LoginRPCAsync rpc = ServicosRPC.getLoginRPC();
		AsyncCallback<SessaoVO> callback = new AsyncCallback<SessaoVO>() {

			public void onFailure(Throwable caught) {
			}

			public void onSuccess(SessaoVO result) {
				System.out.println("/arweb/Console.html?sid=" + result.sessionid);
				redirect("/arweb/Console.html?sid=" + result.sessionid);
			}
		};
		
		//rpc.login(txtnome.getText(), txtpasswd.getText(), callback);
		
	}
	
	
	
}
