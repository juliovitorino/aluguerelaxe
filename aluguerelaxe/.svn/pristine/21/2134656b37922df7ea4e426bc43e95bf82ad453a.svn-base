package br.com.jcv.aluguerelaxe.client.imovel.thread;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ThreadFazerNovaPerguntaFormComposite extends FormComposite<ContatoAnuncianteThreadVO> {

	private static final String PATH_IMAGEM = "images/";
	private static final String PATH_IMAGEM_BOTOES = PATH_IMAGEM + "botoes/";
	private static final String GWT_STYLE = "gwt-tfnpfc";

	private AreaNotificacao an;
	private TextArea pergunta;
	private ContatoClienteVO ccvo;
	private String originador;
	
	public ThreadFazerNovaPerguntaFormComposite(){
		super();
		this.setStyleName(GWT_STYLE);
	}
	
	@Override
	public ContatoAnuncianteThreadVO getVO() {
		ContatoAnuncianteThreadVO vo = new ContatoAnuncianteThreadVO();
		vo.thread = pergunta.getValue();
		vo.origem = this.originador;
		vo.modo = "PR";
		return vo;
	}
	
	public void update(ContatoClienteVO ccvo, String originador){
		this.ccvo = ccvo;
		this.originador = originador;
	}

	@Override
	public void update(ContatoAnuncianteThreadVO vo) {
		// Nada a fazer
	}

	@Override
	public void notifier(ContatoAnuncianteThreadVO vo) {
		if ((this.listener != null) && (this.listener.size() > 0)){
			for (FormCompositeListener fcl : this.listener){
				((ThreadFazerNovaPerguntaFormCompositeListener) fcl).onEnviarPerguntaClick();
			}
		}
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(this.an);
		vp.add(new HTML("<h2>Fa\u00e7a sua pergunta</h2>"));
		vp.add(this.pergunta);
		
		Image imgEnviar = new Image(PATH_IMAGEM_BOTOES + "enviar.png");
		imgEnviar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ThreadFazerNovaPerguntaFormComposite.this.enviarThreadPergunta();
			}
		});

		Image imgCancelar = new Image(PATH_IMAGEM_BOTOES + "cancelar.png");
		imgCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if ((ThreadFazerNovaPerguntaFormComposite.this.listener != null) && (ThreadFazerNovaPerguntaFormComposite.this.listener.size() > 0)){
					for (FormCompositeListener fcl : ThreadFazerNovaPerguntaFormComposite.this.listener){
						((ThreadFazerNovaPerguntaFormCompositeListener) fcl).onCancelarClick();
					}
				}
			}
		});
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(imgEnviar);
		hp.add(imgCancelar);
		
		vp.add(hp);
		return vp;
	}

	@Override
	public void init() {
		an = new AreaNotificacao();
		pergunta = new TextArea();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	private void enviarThreadPergunta() {
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ContatoAnuncianteThreadVO> callback = new AsyncCallback<ContatoAnuncianteThreadVO>() {

			public void onFailure(Throwable caught) {
						try {
								throw caught;
						} catch (IncompatibleRemoteServiceException e) {
							ThreadFazerNovaPerguntaFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						} catch (InvocationException e) {
							ThreadFazerNovaPerguntaFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						} catch (AlugueRelaxeFrontException e) {
							if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
								ThreadFazerNovaPerguntaFormComposite.this.an.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
							} else {
								ThreadFazerNovaPerguntaFormComposite.this.an.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
							}
						} catch (Throwable e) {
							ThreadFazerNovaPerguntaFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						}					
			}

			public void onSuccess(ContatoAnuncianteThreadVO result) {
				ConfirmarDialogModal cdm = new ConfirmarDialogModal("Pergunta", result.msgcodeString, ConfirmarDialogModal.TIPO_MODAL_INFO );
				ThreadFazerNovaPerguntaFormComposite.this.notifier(null);
				
			}
		};
		rpc.enviarNovaPergunta(this.ccvo, this.getVO(), callback);
	}

}
