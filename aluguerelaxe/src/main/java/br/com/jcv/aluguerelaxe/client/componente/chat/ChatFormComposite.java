package br.com.jcv.aluguerelaxe.client.componente.chat;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
/**
 *<h2>Chat</h2>
 *<p>Classe responsável por um componente Chat de conversa.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-Chat {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */

public class ChatFormComposite extends FormComposite<ChatVO> {
	
	private Image img;
	private HTML html;
	private Image imgBorderChat;
	private Label dataPost;
	private Label titulo;
	private VerticalPanel vpRodapeFoto;
	private Panel vpRodapeChat;
	
	private final static String PATH_IMAGEM = "images/"; 
	
	public ChatFormComposite() {
		super();
		this.setStylePrimaryName("gwt-Chat");
	}
	
	public void setWidgetRodapeFoto(Widget widget) {
		vpRodapeFoto.add(widget);
	}

	public void setWidgetRodapeChat(Widget widget) {
		vpRodapeChat.add(widget);
	}

	@Override
	public ChatVO getVO() {
		// Nao aplicavel
		return null;
	}

	@Override
	public void update(ChatVO vo) {
		this.img.setUrl(vo.urlFoto);
		this.html.setHTML(vo.chat);
		this.dataPost.setText("Postado em " + vo.dataPost);
		this.titulo.setText(vo.titulo);
	}

	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		
		hp.add( montaFoto() );
		hp.add( montaChat() );
		
		return hp;
	}

	private Widget montaChat() {
		VerticalPanel vp = new VerticalPanel();
		
		HorizontalPanel sp = new HorizontalPanel();
		sp.setStylePrimaryName("gwt-chat-panel");
		sp.add(this.imgBorderChat);
		sp.add(this.html);
		
		vp.add(this.titulo);
		vp.add(sp);
		vp.add(this.dataPost);
		vp.add(vpRodapeChat);
		return vp;
	}

	private Widget montaFoto() {
		VerticalPanel sp = new VerticalPanel();
		sp.setStylePrimaryName("gwt-chat-img-panel");
		sp.add(this.img);
		sp.add(this.vpRodapeFoto);
		return sp;
	}

	@Override
	public void init() {
		this.img = new Image();
		this.html = new HTML();
		this.imgBorderChat = new Image(PATH_IMAGEM + "chat-part.png");
		this.dataPost = new Label();
		this.titulo = new Label();
		this.vpRodapeFoto = new VerticalPanel();
		this.vpRodapeChat = new VerticalPanel();
		
		// CSS
		this.img.setStylePrimaryName("gwt-chat-img");
		this.html.setStylePrimaryName("gwt-chat-html");
		this.imgBorderChat.setStylePrimaryName("gwt-chat-imgborder");
		this.dataPost.setStylePrimaryName("gwt-chat-data-post");
		this.titulo.setStylePrimaryName("gwt-chat-titulo");
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(ChatVO vo) {
		// TODO Auto-generated method stub
		
	}

}
