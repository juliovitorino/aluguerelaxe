package br.com.jcv.aluguerelaxe.client.imovel.thread;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModalListener;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ThreadResponderFormComposite extends FormComposite<ContatoAnuncianteThreadVO> {

	private static final String PATH_IMAGEM = "images/";
	private static final String PATH_IMAGEM_BOTOES = PATH_IMAGEM + "botoes/";
	private static final String GWT_STYLE = "gwt-trfc";

	private AreaNotificacao an;
	private TextArea pergunta;
	private ContatoClienteVO ccvo;
	private String originador;
	private ContatoAnuncianteThreadVO catparentvo;
	private String modo;
	
	public ThreadResponderFormComposite() {
		super();
		this.setStyleName(GWT_STYLE);
	}
	
	@Override
	public ContatoAnuncianteThreadVO getVO() {
		ContatoAnuncianteThreadVO vo = new ContatoAnuncianteThreadVO();
		vo.thread = pergunta.getValue();
		vo.idParent = catparentvo.id;
		vo.origem = this.originador;
		vo.modo = this.modo;
		return vo;
	}
	
	public void update(ContatoClienteVO ccvo, ContatoAnuncianteThreadVO catparentvo, String originador){
		this.update(ccvo, catparentvo, originador, "PR");
	}

	public void update(ContatoClienteVO ccvo, ContatoAnuncianteThreadVO catparentvo, String originador, String modo){
		this.ccvo = ccvo;
		this.originador = (originador.equals("V") ? "A" : originador);
		this.catparentvo = catparentvo;
		this.modo = modo;
	}

	@Override
	public void update(ContatoAnuncianteThreadVO vo) {
		// Nada a fazer
	}

	@Override
	public void notifier(ContatoAnuncianteThreadVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(this.an);
		vp.add(new HTML("<h2>Escreva sua resposta</h2>"));
		vp.add(this.pergunta);
		
		Image imgEnviar = new Image(PATH_IMAGEM_BOTOES + "enviar.png");
		imgEnviar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ThreadResponderFormComposite.this.enviarThreadResposta();
			}
		});
		vp.add(imgEnviar);
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
	
	private void enviarThreadResposta() {
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ContatoAnuncianteThreadVO> callback = new AsyncCallback<ContatoAnuncianteThreadVO>() {

			public void onFailure(Throwable caught) {
						try {
								throw caught;
						} catch (IncompatibleRemoteServiceException e) {
							ThreadResponderFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						} catch (InvocationException e) {
							ThreadResponderFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						} catch (AlugueRelaxeFrontException e) {
							if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
								ThreadResponderFormComposite.this.an.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
							} else {
								ThreadResponderFormComposite.this.an.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
							}
						} catch (Throwable e) {
							ThreadResponderFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						}					
			}

			public void onSuccess(ContatoAnuncianteThreadVO result) {
				ConfirmarDialogModal cdm = new ConfirmarDialogModal("Resposta", result.msgcodeString, ConfirmarDialogModal.TIPO_MODAL_INFO );
				cdm.addConfirmarDialogModalListener(new ConfirmarDialogModalListener() {
					
					public void onSimClick() {
					}
					
					public void onOkClick() {
						ThreadResponderFormComposite.redirect(com.google.gwt.user.client.Window.Location.getHref());
					}
					
					public void onNaoClick() {
					}
					
					public void onCancelarClick() {
					}
				});
			}
		};
		rpc.enviarResposta(this.ccvo, this.getVO(), callback);
	}
	
	
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;




}
