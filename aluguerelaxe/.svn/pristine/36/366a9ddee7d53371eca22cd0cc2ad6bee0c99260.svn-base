package br.com.jcv.aluguerelaxe.client.componente.foto;

import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <p>Abstração de uma fotografia</p>
 * CSS Style: gwt-AbstractFoto
 * 
 * @author julio
 *
 */
public abstract class AbstractFoto extends Composite {

	private Panel widgetTituloImagem;
	private Panel widgetRodapeImagem;
	private ImovelImagemVideoVO vo;
	private Image img = null;
	
	public AbstractFoto(ImovelImagemVideoVO iivvo) {
		this.vo = iivvo;
		initWidget(montaAbstractFoto());
		this.setStylePrimaryName("gwt-AbstractFoto");
	}
	
	public ImovelImagemVideoVO getVO() {
		return this.vo;
	}
	
	public void addTitulo(HTML html){
		widgetTituloImagem.clear();
		widgetTituloImagem.add(html);
	}

	public void addRodape(HTML html){
		widgetRodapeImagem.add(html);
	}
	
	private Widget montaAbstractFoto() {
		VerticalPanel vp = new VerticalPanel();
		widgetRodapeImagem = montaRodape();
		widgetTituloImagem = montaTitulo();
		vp.add(widgetTituloImagem);
		vp.add(montaFoto());
		vp.add(widgetRodapeImagem);

		return vp;
	}

	private Widget montaFoto() {
		SimplePanel sp = new SimplePanel();
		img = new Image(this.vo.nome);
		sp.add(img);
		return sp;
	}

	/**
	 * Uma possibilidade de colocar um widget qualquer em cima da imagem.
	 * Pode ser sobreescrito nas classes concretas.
	 * 
	 * @return widget
	 */
	protected Panel montaTitulo() {
		return new SimplePanel();
	}
	
	/**
	 * Uma possibilidade de colocar um widget qualquer em baixo da imagem.
	 * Pode ser sobreescrito nas classes concretas
	 * 
	 * @return widget
	 */
	protected Panel montaRodape() {
		return new SimplePanel();
	}

	public Widget getWidgetTituloImagem() {
		return widgetTituloImagem;
	}

	public Widget getWidgetRodapeImagem() {
		return widgetRodapeImagem;
	}

	public Image getImg() {
		return img;
	}
	
}
