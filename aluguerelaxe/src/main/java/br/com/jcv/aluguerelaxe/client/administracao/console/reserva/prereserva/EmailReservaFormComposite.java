package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva;

import br.com.jcv.aluguerelaxe.client.EmailVO;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.LocatarioVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextBox;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class EmailReservaFormComposite extends FormComposite<EmailVO> {
	
	private final static String AUTO_AJUDA_EMAIL_CLIENTE = "<B>Email</B>" + 
					"<p>Digite o e-mail de acordo com o exemplo abaixo:<br/><br/>" +
					"joao.silva@novec.com.br</p>" +
					"<p><strong>\u00c9 muito importante que o e-mail esteja correto,<br/>" +
					"pois, ser\u00e1 atrav\u00e9s dele que o proprit\u00e1rio ir\u00e1 <br/>" +
					"receber os contatos dos visitantes ao seu im\u00f3vel.</strong></p>";
	
	private AutoAjudaTextBox txtEmail;
	private Button btn;
	
	public EmailReservaFormComposite() {
		super();
	}
	
	public void init() {
		txtEmail = new AutoAjudaTextBox(AUTO_AJUDA_EMAIL_CLIENTE);
		btn = new Button("Pesquisar");
		
		// Cria handle
		btn.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				EmailReservaFormComposite.this.pesquisarLocatarioReserva();
			}
		});
	}
	
	private void pesquisarLocatarioReserva() {
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<LocatarioVO> callback = new AsyncCallback<LocatarioVO>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(LocatarioVO result) {
				EmailReservaFormComposite.this.notifier(result);
			}
		};
		rpc.pesquisarLocatarioReserva(EmailReservaFormComposite.this.getVO().email, callback);
	}
	
	public Widget render() {
		FlowPanel vp = new FlowPanel();
		
		// Monta os labels
		Label lblEmail = new Label("E-mail:");
		
		// Configura tamanhos
		txtEmail.setWidth("580px");

		// Adiciona a grid ao VerticalPanel
		vp.add(lblEmail);
		vp.add(txtEmail);
		vp.add(btn);
		
		
		return vp;
	}

	@Override
	public EmailVO getVO() {
		EmailVO vo = new EmailVO();
		vo.email = txtEmail.getWidgetUI().getValue();
		return vo;
	}
	
	@Override
	public void update(EmailVO result){
		txtEmail.getWidgetUI().setValue(result.email);
		this.pesquisarLocatarioReserva();
		this.btn.setVisible(false);
	}

	@Override
	public void clear() {
	}

	public void notifier(LocatarioVO vo) {
		// Notifica ouvintes dependentes desta informacao
		if ( (this.listener != null) && (this.listener.size() > 0) ){
			for (FormCompositeListener fcl : this.listener) {
				EmailReservaFormCompositeListener tcfcl = (EmailReservaFormCompositeListener) fcl;
				tcfcl.onPesquisarEmailClick(vo);
			}
		}
		
	}

	@Override
	public void notifier(EmailVO vo) {
	}


}
