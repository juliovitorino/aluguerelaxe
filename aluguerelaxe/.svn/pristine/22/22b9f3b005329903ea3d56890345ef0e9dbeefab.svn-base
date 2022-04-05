package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.componente.chat.ChatFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatVO;
import br.com.jcv.aluguerelaxe.client.componente.classificador.Classificador;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ContatoAnuncianteThreadVO;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ThreadComentarioImovelVisitanteFormComposite 
	extends FormComposite<ContatoAnuncianteThreadVO> {

	private static final String PATH_IMAGEM = "images/";
	private static final String PATH_IMAGEM_48x48 = PATH_IMAGEM + "48x48/";
	private static final String PATH_FOTO_THREAD = "/stream/thread/";
	private static final String PATH_FOTO_STREAM_AR = "/stream/ar/";
	private static final String PATH_FOTO_ANUNCIANTES = "/stream/fotos/";
	private static final String PATH_BOTOES = PATH_IMAGEM + "botoes/";
	
	private AreaNotificacao an;
	private ChatFormComposite threadfc;
	
	@Override
	public ContatoAnuncianteThreadVO getVO() {
		// nada a fazer
		return null;
	}

	@Override
	public void update(ContatoAnuncianteThreadVO vo) {
	}
	
	public void update(ContatoAnuncianteThreadVO vo, ContatoClienteVO ccvo) {
		//-----------------------------------------------------------------------------
		// Monta notas do imovel e do9 anfitriao
		//-----------------------------------------------------------------------------
		Classificador notaImovel = new Classificador("Im\u00f3vel");
		Classificador notaAnfitriao = new Classificador("Anfitri\u00e3o");
		
		notaImovel.setNotaClassificador(ccvo.reserva.notaImovel);
		notaAnfitriao.setNotaClassificador(ccvo.reserva.notaAnfitriao);
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(new Image(PATH_IMAGEM_48x48 + "house.png"));
		hp.add(notaImovel);
		hp.add(new Image(PATH_IMAGEM_48x48 + "user1_edit.png"));
		hp.add(notaAnfitriao);
		
		//-----------------------------------------------------------------------------
		// Popula as informacoes para o chat
		//-----------------------------------------------------------------------------
		ChatVO chatvo = new ChatVO();
		chatvo.chat = vo.threadEditada + "<p>" + hp.toString();
		chatvo.titulo = ccvo.reserva.locatario.nome.toUpperCase() 
			+ " alugou no per\u00edodo de "
			+ ccvo.reserva.dataCheckinStr 
			+ " at\u00e9 " 
			+ ccvo.reserva.dataCheckoutStr
			+ " e postou um testemunho:";
		chatvo.dataPost = vo.dataCadastroStr;
		
		
		//-----------------------------------------------------------------------------
		// Adiciona botao de inclusao de imagem do visitante
		//-----------------------------------------------------------------------------
		if ((ccvo.reserva.imgVisitante == null) || (ccvo.reserva.imgVisitante.length() == 0)) {
			chatvo.urlFoto = PATH_IMAGEM + "noface.png";
		} else {
				if ((ccvo.reserva.imgVisitante == null) || (ccvo.reserva.imgVisitante.length() == 0)){
					chatvo.urlFoto = PATH_IMAGEM + "noface.png";
				} else {
					if (ccvo.reserva.imgVisitante.indexOf(PATH_FOTO_THREAD) > -1){
						chatvo.urlFoto = ccvo.reserva.imgVisitante;
					} else {
						chatvo.urlFoto = PATH_FOTO_THREAD + ccvo.reserva.idContato + "/" + ccvo.reserva.imgVisitante;
					}
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
				//chatvofilho.titulo = vo.reserva.contatoCliente.ifcvo.cliente.primeiroNome.toUpperCase() + ", diz:";
				chatvofilho.dataPost = vo.threadfilho.dataCadastroStr;
				if ((ccvo.ifcvo.cliente.fotoChat != null) && (ccvo.ifcvo.cliente.fotoChat.length() > 0)) {
					chatvofilho.urlFoto = PATH_FOTO_ANUNCIANTES + ccvo.ifcvo.cliente.id + "/" + ccvo.ifcvo.cliente.fotoChat;
				} else {
					chatvofilho.urlFoto = PATH_IMAGEM + "/faceless.png";
				}
			} else {
				chatvofilho.chat = "O anunciante do im\u00f3vel j\u00e1 respondeu sua pergunta. A resposta do anunciante est\u00e1 passando por analise de cumprimento dos termos de uso do portal.";
				chatvofilho.titulo = "MODERADOR, diz:";
				chatvofilho.dataPost = vo.threadfilho.dataCadastroStr;
				chatvofilho.urlFoto = PATH_FOTO_STREAM_AR + "/picture_jcv_64x64.jpg"; 
			}
			
			threadfcfilho.update(chatvofilho);
			threadfc.setWidgetRodapeChat(threadfcfilho);
		}
	}

	@Override
	public void notifier(ContatoAnuncianteThreadVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(threadfc);
		return vp;
	}

	@Override
	public void init() {
		an = new AreaNotificacao();
		threadfc = new ChatFormComposite();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
}
