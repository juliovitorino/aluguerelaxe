package br.com.jcv.aluguerelaxe.client.administracao.login;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class LoginFormComposite extends FormComposite<LoginVO> {
	
	private static final String PATH_IMAGEM = "images/48x48/";
	private static final String HTML_HEADER_LOGIN = "<h2>Acesso ao Console Administrativo</h2>";

	private TextBox txtnome;
	private PasswordTextBox txtpasswd;
	private AreaNotificacao an;

	public LoginFormComposite() {
		super();
		this.setStylePrimaryName("gwt-LoginFormComposite");
	}

	@Override
	public LoginVO getVO() {
		LoginVO vo = new LoginVO();
		vo.email = txtnome.getText();
		vo.senha = txtpasswd.getText();
		return vo;
	}

	@Override
	public void update(LoginVO vo) {
		// Nao Aplicada neste contexto
	}
	
	public void update(String lblMensagem, int tipo) {
		this.an.setMensagem(lblMensagem, tipo);
	}
	
	public void update(List<String> lstErros, int tipo) {
		this.an.setMensagem(lstErros, tipo);
	}
	

	@Override
	public Widget render() {
		DockPanel dp = new DockPanel();
		Label lblems = new Label("Clique aqui se voc\u00ea esqueceu sua senha");
		lblems.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				LoginVO lvo = LoginFormComposite.this.getVO();
				if (lvo.email.trim().length() == 0){
					ConfirmarDialogModal cdm = new ConfirmarDialogModal("Erro", "Voc\u00ea precisa preencher o e-mail!", ConfirmarDialogModal.TIPO_MODAL_ERRO);
				} else {
					NovoCodigoAcessoVO ncavo = new NovoCodigoAcessoVO();
					ncavo.email = lvo.email;
					LoginRPCAsync rpc = ServicosRPC.getLoginRPC();
					AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>() {

						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						public void onSuccess(VOPadrao result) {
							ConfirmarDialogModal cdm = new ConfirmarDialogModal("Sucesso", "As instruções foram enviadas para seu e-mail.", ConfirmarDialogModal.TIPO_MODAL_INFO);
						}
					};
					rpc.solicitarNovoCodigoAcesso(ncavo, callback);
				}
				
			}
		});
		
		Label lblnsc = new Label("N\u00e3o sou cliente. Clique aqui para se cadastrar.");
		lblnsc.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				redirect("/arweb/JXControllerSmartInterface?cmd=dtgNovaConta");
			}
		});
		
		// monta dockpanel
		dp.add(montaHeaderLogin(), DockPanel.NORTH);
		dp.add(lblems, DockPanel.SOUTH);
		dp.add(lblnsc, DockPanel.SOUTH);
		dp.add(montaDataEntryLogin(), DockPanel.CENTER);
		
		return dp;
	}

	@Override
	public void init() {
		this.txtnome = new TextBox();
		this.txtpasswd = new PasswordTextBox();
		this.an = new AreaNotificacao();
	}

	@Override
	public void clear() {
		this.txtnome.setValue("");
		this.txtpasswd.setValue("");
	}
	
	private Widget montaDataEntryLogin() {
		VerticalPanel vp = new VerticalPanel();
		Grid grd = new Grid(2,2);
		grd.setWidget(0, 0, new Label("E-mail:"));
		grd.setWidget(1, 0, new Label("Senha:"));
		grd.setWidget(0, 1, txtnome);
		grd.setWidget(1, 1, txtpasswd);
		
		vp.add(grd);
		vp.add(this.an);

		return vp;
	}
	private Widget montaHeaderLogin() {
		HorizontalPanel hp = new HorizontalPanel();
		Image img = new Image(PATH_IMAGEM + "key1.png");
		HTML hHeader = new HTML(HTML_HEADER_LOGIN);
		hp.add(img);
		hp.add(hHeader);
		return hp;
	}

	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;

	@Override
	public void notifier(LoginVO vo) {
		// TODO Auto-generated method stub
		
	}

}
