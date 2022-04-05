package br.com.jcv.aluguerelaxe.client.componente.dialog;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ConfirmarDialogModal extends DialogBox {

	public static final int TIPO_MODAL_PERGUNTA_OK_CANCELAR = 0;
	public static final int TIPO_MODAL_PERGUNTA_SIM_NAO = 1;
	public static final int TIPO_MODAL_AVISO = 2;
	public static final int TIPO_MODAL_ERRO = 3;
	public static final int TIPO_MODAL_INFO = 4;
	
	private static final String PATH_IMAGEM = "images/48x48/";
	private static final String PATH_IMAGEM_BOTOES = "images/botoes/";
	
	private String titulo;
	private String texto;
	private List<ConfirmarDialogModalListener> listeners;
	private int tipoDialogo = TIPO_MODAL_INFO;
	
	public ConfirmarDialogModal(String titulo, String texto, int tipo) {
		super(false, true);
		this.setStylePrimaryName("gwt-ConfirmarDialogModal");
		this.titulo = titulo;
		this.texto = texto;
		this.tipoDialogo = tipo;
		this.setText(titulo);
		this.setWidget(renderDialog());
		this.setGlassEnabled(true);
		this.center();
	}
	
	private Widget renderDialog() {
		DockPanel dp = new DockPanel();
		dp.add(renderBotoes(), DockPanel.SOUTH);
		dp.add(render(), DockPanel.CENTER);
		return dp;
	}

	private Image criarBotaoOK() {
		//------------------------------------------------
		// Cria o botao de OK e seu respectivo evento
		//------------------------------------------------
		Image btn = new Image(PATH_IMAGEM_BOTOES +"ok.png");
		btn.addClickHandler(new ClickHandler() {
			public void  onClick(ClickEvent event) {
				if (ConfirmarDialogModal.this.listeners != null) {
					for ( ConfirmarDialogModalListener cdml :ConfirmarDialogModal.this.listeners) {
						cdml.onOkClick();
					}
				}
				ConfirmarDialogModal.this.hide();
			}
		});
		return btn;
	}

	private Image criarBotaoCancelar() {
		//------------------------------------------------
		// Cria o botao de Cancelar e seu respectivo evento
		//------------------------------------------------
		Image btn = new Image(PATH_IMAGEM_BOTOES + "cancelar.png");
		btn.addClickHandler(new ClickHandler() {
			public void  onClick(ClickEvent event) {
				if (ConfirmarDialogModal.this.listeners != null) {
					for ( ConfirmarDialogModalListener cdml :ConfirmarDialogModal.this.listeners) {
						cdml.onCancelarClick();
					}
				}
				ConfirmarDialogModal.this.hide();
			}
		});
		return btn;
	}

	private Image criarBotaoSIM() {
		//------------------------------------------------
		// Cria o botao de SIM e seu respectivo evento
		//------------------------------------------------
		Image btn = new Image(PATH_IMAGEM_BOTOES + "sim.png");
		btn.addClickHandler(new ClickHandler() {
			public void  onClick(ClickEvent event) {
				if (ConfirmarDialogModal.this.listeners != null) {
					for ( ConfirmarDialogModalListener cdml :ConfirmarDialogModal.this.listeners) {
						cdml.onSimClick();
					}
				}
				ConfirmarDialogModal.this.hide();
			}
		});
		
		return btn;
	}

	private Image criarBotaoNAO() {
		//------------------------------------------------
		// Cria o botao de NAO e seu respectivo evento
		//------------------------------------------------
		Image btn = new Image(PATH_IMAGEM_BOTOES + "nao.png");
		btn.addClickHandler(new ClickHandler() {
			public void  onClick(ClickEvent event) {
				if (ConfirmarDialogModal.this.listeners != null) {
					for ( ConfirmarDialogModalListener cdml :ConfirmarDialogModal.this.listeners) {
						cdml.onNaoClick();
					}
				}
				ConfirmarDialogModal.this.hide();
			}
		});
		return btn;
	}
	
	protected Widget renderBotoes() {
		HorizontalPanel hp = new HorizontalPanel();
		Image btnOK;
		Image btnCancelar;
		Image btnSIM;
		Image btnNAO;
		
		switch(tipoDialogo) {
			case TIPO_MODAL_PERGUNTA_OK_CANCELAR:
				btnOK = criarBotaoOK();
				btnCancelar = criarBotaoCancelar();
				hp.add(btnOK);
				hp.add(btnCancelar);
				hp.setCellHorizontalAlignment(btnOK, HasHorizontalAlignment.ALIGN_CENTER);
				hp.setCellHorizontalAlignment(btnCancelar, HasHorizontalAlignment.ALIGN_CENTER);
				break;
			case TIPO_MODAL_PERGUNTA_SIM_NAO:
				btnSIM = criarBotaoSIM();
				btnNAO = criarBotaoNAO();
				hp.add(btnSIM);
				hp.add(btnNAO);
				hp.setCellHorizontalAlignment(btnSIM, HasHorizontalAlignment.ALIGN_CENTER);
				hp.setCellHorizontalAlignment(btnNAO, HasHorizontalAlignment.ALIGN_CENTER);
				break;
			case TIPO_MODAL_AVISO:
				btnOK = criarBotaoOK();
				hp.add(btnOK);
				hp.setCellHorizontalAlignment(btnOK, HasHorizontalAlignment.ALIGN_CENTER);
				break;
			case TIPO_MODAL_ERRO:
				btnOK = criarBotaoOK();
				hp.add(btnOK);
				hp.setCellHorizontalAlignment(btnOK, HasHorizontalAlignment.ALIGN_CENTER);
				break;
			case TIPO_MODAL_INFO:
				btnOK = criarBotaoOK();
				hp.add(btnOK);
				hp.setCellHorizontalAlignment(btnOK, HasHorizontalAlignment.ALIGN_CENTER);
				break;
		}

		return hp;
	}
	
	
	protected Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		String img = null;
		
		switch(tipoDialogo) {
		case TIPO_MODAL_PERGUNTA_OK_CANCELAR: 
				img = "question_and_answer.png";
				break;
		case TIPO_MODAL_PERGUNTA_SIM_NAO:
				img = "question_and_answer.png";
				break;
			case TIPO_MODAL_AVISO:
				img = "warning.png";
				break;
			case TIPO_MODAL_ERRO:
				img = "error.png";
				break;
			case TIPO_MODAL_INFO:
				img = "about.png";
				break;
		}
		hp.add(new Image(PATH_IMAGEM + img));
		hp.add(new Label(this.texto));
		return hp;
	}
	
	public void addConfirmarDialogModalListener(ConfirmarDialogModalListener listener) {
		if (this.listeners == null) {
			this.listeners = new ArrayList<ConfirmarDialogModalListener>();
		}
		this.listeners.add(listener);
	}
	
	
}
