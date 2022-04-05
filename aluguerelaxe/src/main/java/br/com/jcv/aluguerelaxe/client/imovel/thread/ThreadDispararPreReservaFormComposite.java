package br.com.jcv.aluguerelaxe.client.imovel.thread;

import br.com.jcv.aluguerelaxe.client.administracao.console.proprietario.PerfilClienteFormComposite;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class ThreadDispararPreReservaFormComposite extends FormComposite<ContatoClienteVO> {
	
	private static final String PATH_IMAGEM_BOTOES = "images/botoes/";
	private static final String GWT_STYLE = "gwt-tdprfc";

	private PerfilClienteFormComposite pcfc;
	private Image botaoDisparo;
	private ContatoClienteVO ccvo;
	
	public ThreadDispararPreReservaFormComposite() {
		super();
		this.setStyleName(GWT_STYLE);
	}
	
	@Override
	public ContatoClienteVO getVO() {
		return null;
	}

	public void update(ClienteVO vo) {
		pcfc.update(vo);
	}

	@Override
	public void update(ContatoClienteVO vo) {
		this.ccvo = vo;
	}

	@Override
	public void notifier(ContatoClienteVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(pcfc);
		vp.add(botaoDisparo);
		return vp;
	}

	@Override
	public void init() {
		pcfc = new PerfilClienteFormComposite();
		botaoDisparo = new Image(PATH_IMAGEM_BOTOES + "disparo-pre-reserva.png");
		botaoDisparo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				if (com.google.gwt.user.client.Window.Location.getParameter("omc").equals(ThreadDispararPreReservaFormComposite.this.ccvo.codigoOMCThreadVisitante)){
					ThreadDispararPreReservaFormComposite.redirect("/arweb/JXControllerSmartInterface?cmd=dtgPrereserva&hash=" + 
							ThreadDispararPreReservaFormComposite.this.ccvo.id + 
							"&omc=" + ThreadDispararPreReservaFormComposite.this.ccvo.codigoOMCThreadVisitante);
				} else {
					ConfirmarDialogModal cdm = new ConfirmarDialogModal("Fazer Reserva", "Somente um visitante pode fazer a reserva.", ConfirmarDialogModal.TIPO_MODAL_ERRO);
				}
			}
		});
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;



}
