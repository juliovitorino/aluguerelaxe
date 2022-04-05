package br.com.jcv.aluguerelaxe.client.imovel.thread;

import br.com.jcv.aluguerelaxe.client.componente.chat.ChatFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ThreadImovelAdminFormComposite extends FormComposite<ContatoAnuncianteThreadVO> {

	private static final String PATH_FOTO = "/stream/ar/";

	private ChatFormComposite threadfc;

	@Override
	public ContatoAnuncianteThreadVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(ContatoClienteVO ccvo, ContatoAnuncianteThreadVO vo) {
		ChatVO chatvo = new ChatVO();
		chatvo.chat = vo.threadEditada;
		chatvo.titulo = "ADMINISTRADOR, diz:";
		chatvo.dataPost = vo.dataCadastroStr;
		chatvo.urlFoto = PATH_FOTO + "foto-admin-chat.jpg"; 
		threadfc.update(chatvo);
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
		vp.add(threadfc);
		return vp;
	}

	@Override
	public void init() {
		threadfc = new ChatFormComposite();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
