
package br.com.jcv.aluguerelaxe.client.componente.anuncio;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <p>Classe concreta para {@link AbstractAnuncio} que tem a funç\u00e3o de desenhar um
 * an\u00fancio de imóvel que ser\u00e1 renderizado em uma lista paginada.</p>
 * <p>A class {@link AbstractAnuncio} j\u00e1 implementa a interface {@link ClickHandler}</p>
 *
 * @author elmt
 *
 */
public class AnuncioListaPaginadaNaoEncontrado extends AbstractAnuncio {
	
	public static final String PATH_IMAGEM = "images/128x128/";
	
	public AnuncioListaPaginadaNaoEncontrado(ImovelFichaCompletaVO ifcvo) {
		super(ifcvo);
		this.setStylePrimaryName("gwt-AnuncioListaPaginadaNaoEncontrado");
	}

	
	/**
	 * <p>Desenha o An\u00fancio do imóvel que ser\u00e1 apresentando em grid. Para que os
	 * componentes deste <code>render()</code> possam enviar um evento 
	 * <code>onAnuncioClick</code>, voc\u00ea deve
	 * em cada componente adicionar um Listener para esta classe.
	 * </p>
	 * <p>Conforme o exemplo abaixo:</p>
	 * <blockquote>
	 * Button lbl = new Button("Clique me");<br/>
	 * lbl.addClickHandler(this);
	 * </blockquote>
	 * @see br.com.jcv.aluguerelaxe.client.componente.anuncio.AbstractAnuncio#render()
	 */
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		
		DockPanel dp = new DockPanel();
		
		dp.add(montaHeader(), DockPanel.NORTH);
		dp.add(montaRodape(),DockPanel.SOUTH);
		dp.add(montaFigura(), DockPanel.WEST);
		dp.add(montaDescritivoAnuncio(),DockPanel.CENTER);
		
		vp.add(dp);
		return vp;
	}

	private Widget montaRodape() {
		VerticalPanel vp = new VerticalPanel();
		vp.setStylePrimaryName("gwt-AnuncioListaPaginadaNaoEncontrado-rodape");
		Label lblDicas = new Label("Dicas de pesquisa");
		Label lblDica1 = new Label("Se voc\u00ea sabe o n\u00famero da propriedade utilize o filtro de propriedade do lado esquerdo.");
		Label lblDica2 = new Label("Use crit\u00e9rios menos precisos, procurando em uma regi\u00e3o ou \u00e1rea mais ampla.");
		Label lblDica3 = new Label("Em seguida, refine a sua pesquisa para limitar os resultados e encontrar a sua casa ideal.");
		vp.add(lblDicas);
		vp.add(lblDica1);
		vp.add(lblDica2);
		vp.add(lblDica3);
		
		return vp;
	}
	
	private Widget montaFigura() {
		VerticalPanel vp = new VerticalPanel();
		Image img = new Image(PATH_IMAGEM + "find_again.png");
		
		vp.add(img);
				
		return vp;
	}

	private Widget montaHeader() {
		VerticalPanel vp = new VerticalPanel();
		vp.setStylePrimaryName("gwt-AnuncioListaPaginadaNaoEncontrado-header");
		Label lbl = new Label("N\u00e3o foram encontrados resultados.");
		vp.add(lbl);
		return vp;
	}
	
	private Widget montaDescritivoAnuncio() {
		VerticalPanel vp = new VerticalPanel();
		vp.setStylePrimaryName("gwt-AnuncioListaPaginadaNaoEncontrado-corpo");
		Label lbl1 = new Label("Desculpe, mas n\u00e3o foram encontrados resultados para a sua pesquisa.");
		Label lbl2 = new Label("Por favor, inicie uma nova busca utilizando as dicas no rodap\u00e9 desta mensagem.");
		
		vp.add(lbl1);
		vp.add(lbl2);
		
		return vp;
		
		
		
	}
	
	
}

