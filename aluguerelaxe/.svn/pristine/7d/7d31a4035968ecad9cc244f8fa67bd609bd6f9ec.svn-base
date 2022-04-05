package br.com.jcv.aluguerelaxe.client.imovel.thread;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.upload.AbstractUploadFotoFormCompositeListener;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ThreadImovelVisitanteFormComposite 
	extends FormComposite<ContatoAnuncianteThreadVO> 
		implements AbstractUploadFotoFormCompositeListener {

	private static final String PATH_IMAGEM = "images/";
	private static final String PATH_FOTO_THREAD = "/stream/thread/";
	private static final String PATH_FOTO_STREAM_AR = "/stream/ar/";
	private static final String PATH_FOTO_ANUNCIANTES = "/stream/fotos/";
	private static final String PATH_BOTOES = PATH_IMAGEM + "botoes/";
	
	private AreaNotificacao an;
	private ChatFormComposite threadfc;
	private VerticalPanel vpnovamsg;
	private TextArea novamsg;
	private Image imgenviar;
	private ContatoClienteVO ccvo;
	private ContatoAnuncianteThreadVO catvo;
	private String omc;
	private String originador;
	private UploadFotoThreadDialogModal uftdm = null;

	@Override
	public ContatoAnuncianteThreadVO getVO() {
		// faz a copia da thread pai preservada relacionando-a a nova junto com nova mensagem
		ContatoAnuncianteThreadVO vo = new ContatoAnuncianteThreadVO();
		vo.idParent = catvo.id;
		vo.thread = novamsg.getValue();
		vo.origem = "A";
		vo.modo = "PR";
		return vo;
	}

	public void update(ContatoClienteVO ccvo, ContatoAnuncianteThreadVO vo, String omc, String originador) {
		//-----------------------------------------------------------------------------
		// preserva informacoes para uso futuro
		//-----------------------------------------------------------------------------
		this.catvo = vo;
		this.ccvo = ccvo;
		this.omc = omc;
		
		//-----------------------------------------------------------------------------
		// Popula as informacoes para o chat
		//-----------------------------------------------------------------------------
		ChatVO chatvo = new ChatVO();
		chatvo.chat = vo.threadEditada;
		chatvo.titulo = ccvo.primeiroNome.toUpperCase() + ", diz:";
		chatvo.dataPost = vo.dataCadastroStr;
		
		//-----------------------------------------------------------------------------
		// Adiciona botao de inclusao de imagem do visitante
		//-----------------------------------------------------------------------------
		if (((ccvo.imgvisitante == null) || (ccvo.imgvisitante.length() == 0)) && (originador.equals("V"))) {
			chatvo.urlFoto = PATH_IMAGEM + "noface.png";
			Image imgBotaoAddFoto = new Image(PATH_BOTOES + "fotoadd.png");
			imgBotaoAddFoto.addClickHandler(new ClickHandler() {

				public void onClick(ClickEvent event) {
					ThreadImovelVisitanteFormComposite.this.uploadFotoThread();
				}
			});
			
			threadfc.setWidgetRodapeFoto(imgBotaoAddFoto);
		} else {
				if ((ccvo.imgvisitante == null) || (ccvo.imgvisitante.length() == 0)){
					chatvo.urlFoto = PATH_IMAGEM + "noface.png";
				} else {
					chatvo.urlFoto = PATH_FOTO_THREAD + ccvo.id + "/" + ccvo.imgvisitante;
				}
		}
		threadfc.update(chatvo);
		
		if (vo.threadfilho != null){
			ChatFormComposite threadfcfilho = new ChatFormComposite();
			//-----------------------------------------------------------------------------
			// Popula as informacoes para o chat filho
			//-----------------------------------------------------------------------------
			ChatVO chatvofilho = new ChatVO();
			if (vo.threadfilho.status.equals("A")){
				chatvofilho.chat = vo.threadfilho.threadEditada;
				chatvofilho.titulo = ccvo.ifcvo.cliente.primeiroNome.toUpperCase() + ", diz:";
				chatvofilho.dataPost = vo.threadfilho.dataCadastroStr;
				if (ccvo.ifcvo.cliente.fotoChat.indexOf("noface") == -1) {
					chatvofilho.urlFoto = PATH_FOTO_ANUNCIANTES + ccvo.ifcvo.cliente.id + "/" + ccvo.ifcvo.cliente.fotoChat;
				} else {
					chatvofilho.urlFoto = PATH_IMAGEM + "noface.png";
				}
			} else {
				chatvofilho.chat = "O anunciante do im\u00f3vel j\u00e1 respondeu sua pergunta. A resposta do anunciante est\u00e1 passando por analise de cumprimento dos termos de uso do portal.";
				chatvofilho.titulo = "MODERADOR, diz:";
				chatvofilho.dataPost = vo.threadfilho.dataCadastroStr;
				chatvofilho.urlFoto = PATH_FOTO_STREAM_AR + "/picture_jcv_64x64.jpg"; 
			}
			
			threadfcfilho.update(chatvofilho);
			threadfc.setWidgetRodapeChat(threadfcfilho);
		} else {
			//---------------------------------------------------------------------------------------------
			// Somente habilita a resposta por parte do anunicante, se e somente se a thread estiver ativa
			//---------------------------------------------------------------------------------------------
			if (ccvo.threadStatus.equals("A")) {
				if (originador.equals("A")){
					ThreadResponderFormComposite trfc = new ThreadResponderFormComposite();
					trfc.update(ccvo, catvo, originador);
					threadfc.setWidgetRodapeChat(trfc);
				}
			}
		}
	}

	@Override
	public void update(ContatoAnuncianteThreadVO vo) {
		// nada a fazer
	}

	@Override
	public void notifier(ContatoAnuncianteThreadVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		vpnovamsg.setVisible(false);
		vpnovamsg.add(novamsg);
		vpnovamsg.add(imgenviar);
		imgenviar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ThreadImovelVisitanteFormComposite.this.enviarNovaPergunta();
			}
		});
		
		VerticalPanel vp = new VerticalPanel();
		vp.add(threadfc);
		vp.add(vpnovamsg);

		return vp;
	}

	@Override
	public void init() {
		an = new AreaNotificacao();
		threadfc = new ChatFormComposite();
		vpnovamsg = new VerticalPanel();
		novamsg = new TextArea();
		imgenviar = new Image(PATH_BOTOES + "enviar.png");
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	private void enviarNovaPergunta() {
		// Obtem o VO e realiza ajustes necessarios
		ContatoAnuncianteThreadVO catvo = this.getVO();
		
		// Obtem uma interface para acesso ao backend
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ContatoAnuncianteThreadVO> callback = new AsyncCallback<ContatoAnuncianteThreadVO>() {
		
		public void onFailure(Throwable caught) {
		     try {
		       throw caught;
		     } catch (IncompatibleRemoteServiceException e) {
		    	 ThreadImovelVisitanteFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		     } catch (InvocationException e) {
		    	 ThreadImovelVisitanteFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		     } catch (AlugueRelaxeFrontException e) {
		    	 if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
		    		 ThreadImovelVisitanteFormComposite.this.an.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
		    	 } else {
		    		 ThreadImovelVisitanteFormComposite.this.an.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
		    	 }
		     } catch (Throwable e) {
		    	 ThreadImovelVisitanteFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		     }
		 }

		 public void onSuccess(ContatoAnuncianteThreadVO result) {
			 if (result != null){
				// Emitir mensagem que a thread foi enviada ao moderador do site

			 }
		 }
		};
		
		rpc.enviarNovaPergunta(ccvo, catvo, callback);
	}
	
	private void uploadFotoThread() {
		uftdm = new UploadFotoThreadDialogModal();
		uftdm.update(ccvo);
		uftdm.updateHandler(this);
		uftdm.show();
	}

	public void onCancelarClick() {
		uftdm.hide();
	}

	public void onUploadFinished() {
		uftdm.hide();
		redirect(com.google.gwt.user.client.Window.Location.getHref());

	}

	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;




}
