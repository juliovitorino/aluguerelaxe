
package br.com.jcv.aluguerelaxe.client.componente.anuncio;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <p>Classe concreta para {@link AbstractAnuncio} que tem a função de desenhar um
 * anúncio de imóvel que será renderizado em uma lista paginada.</p>
 * <p>A class {@link AbstractAnuncio} já implementa a interface {@link ClickHandler}</p>
 *
 * @author elmt
 *
 */
public class AnuncioGrid extends AbstractAnuncio {
	
	public static final String PATH_IMAGEM = "images/";
	
	public AnuncioGrid(ImovelFichaCompletaVO ifcvo) {
		super(ifcvo);
		this.setStylePrimaryName("gwt-AnuncioGrid");
	}

	
	/**
	 * <p>Desenha o Anúncio do imóvel que será apresentando em grid. Para que os
	 * componentes deste <code>render()</code> possam enviar um evento 
	 * <code>onAnuncioClick</code>, você deve
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
		ImovelFichaCompletaVO ifcvo = getImovelFichaCompletaVO(); 
		
		dp.add(montaHeader(ifcvo), DockPanel.NORTH);
		dp.add(montaRodape(ifcvo),DockPanel.SOUTH);
		dp.add(montaFotoImovel(ifcvo), DockPanel.WEST);
		dp.add(montaDescritivoAnuncio(ifcvo),DockPanel.CENTER);
		
		vp.add(dp);
		return vp;
	}

	private Widget montaRodape(ImovelFichaCompletaVO ifcvo) {
		HorizontalPanel hp = new HorizontalPanel();
		hp.setStylePrimaryName("gwt-AnuncioGrid-rodape");
		
		String tipo = "Casa";
		if (ifcvo.imovel.indicadorTipoPropriedade.equals("A")) {
			tipo = "Apto";
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("H")) {
			tipo = "Hotel";
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("X")) {
			tipo = "Ch\u00e1cara";
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("F")) {
			tipo = "Flat";
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("F")) {
			tipo = "Fazenda";
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("S")) {
			tipo = "S\u00edtio";
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("L")) {
			tipo = "Chal\u00e9";
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("P")) {
			tipo = "Pousada";
		}
		
		Label lblTipo = new Label(tipo);
		Label lblQuartos = new Label(String.valueOf(ifcvo.imovel.qtdeQuartos));
		Label lblSuites = new Label(String.valueOf(ifcvo.imovel.qtdeSuites));
		Label lblBanheiros = new Label(String.valueOf(ifcvo.imovel.qtdeBanheiros));
		Label lblCapacidade = new Label(String.valueOf(ifcvo.imovel.qtdeCapacidade));
		Label lblCondominio = new Label((ifcvo.imovel.indicadorCondominio.equals("S") ? "SIM" : "N\u00e3o"));
		Label lblFestasEventos = new Label((ifcvo.imovel.indicadorPermiteAlugarFestas.equals("S") ? "SIM" : "N\u00e3o"));

		// Formato --> Tipo: XXXXXXXXXX | Quartos: 99 | Suites: 99 | Banheiros: 99 | Capacidade: 99 pessoas | Condomínio: XXX | Festas e Eventos: XXX 
		
		hp.add(new Label("Tipo: "));
		hp.add(lblTipo);
		hp.add(new Label(" | Quartos: "));
		hp.add(lblQuartos);
		hp.add(new Label(" | Suites: "));
		hp.add(lblSuites);
		hp.add(new Label(" | Banheiros: "));
		hp.add(lblBanheiros);
		hp.add(new Label(" | Capacidade: "));
		hp.add(lblCapacidade);
		hp.add(new Label("  Pessoas"));
		hp.add(new Label(" | Condom\u00ednio: "));
		hp.add(lblCondominio);
		hp.add(new Label(" | Festas e Eventos: "));
		hp.add(lblFestasEventos);
		
		return hp;
	}
	
	private Widget montaFotoImovel(ImovelFichaCompletaVO ifcvo) {
		VerticalPanel vp = new VerticalPanel();
		Image img = new Image();
		img.addClickHandler(this);
		if (ifcvo.imagensImovelTB == null) {
			img.setUrl(PATH_IMAGEM + "no-photo.jpg");
		} else {
			ImovelImagemVideoVO imgvo = ifcvo.imagensImovelTB.get(0); 
			img.setUrl(imgvo.nome);
		}
		vp.add(img);
		vp.add(montaRodapeMaisDetalhes(ifcvo));
		
		if ((ifcvo.imovel.valorTarifaBasica != null) && (Long.valueOf(ifcvo.imovel.valorTarifaBasica) > 0)) {
			String str = "Di\u00e1ria partir de:<br/>";
			HTML htmltarifa = new HTML(str + "<h1> R$" + ifcvo.imovel.valorTarifaBasica + "</h1>");
			htmltarifa.setStyleName("gwt-tarifa");
			vp.add(htmltarifa);
		}
		
		return vp;
	}

	private Widget montaHeader(ImovelFichaCompletaVO ifcvo) {
		VerticalPanel vp = new VerticalPanel();
		vp.setStylePrimaryName("gwt-AnuncioGrid-header");
		Label lblAnuncio = new Label(ifcvo.imovel.descricaoTituloAnuncio);
		lblAnuncio.addClickHandler(this);

		VerticalPanel vpLocal = new VerticalPanel();
		vpLocal.setStylePrimaryName("gwt-AnuncioGrid-header-local");
		String local = "Propriedade #" + String.valueOf(ifcvo.imovel.id) + " - " +
			ifcvo.imovel.endereco.cidade + "/" + ifcvo.imovel.endereco.uf;
		
		Label lblLocal = new Label(local);
		lblLocal.addClickHandler(this);
		vpLocal.add(lblLocal);
		
		vp.add(lblAnuncio);
		vp.add(vpLocal);
		return vp;
	}

	private Widget montaRodapeMaisDetalhes(ImovelFichaCompletaVO ifcvo) {
		HorizontalPanel vp = new HorizontalPanel();
		vp.setStylePrimaryName("gwt-AnuncioGrid-maisdetalhes");
		Image imgDetalhes = new Image(PATH_IMAGEM + "add.png");
		Label lblDetalhes = new Label("Mais detalhes...");
		imgDetalhes.addClickHandler(this);
		lblDetalhes.addClickHandler(this);
		vp.add(imgDetalhes);
		vp.add(lblDetalhes);
		return vp;
	}
	
	private Widget montaDescritivoAnuncio(ImovelFichaCompletaVO ifcvo) {
		VerticalPanel vpDescritivo = new VerticalPanel();
		vpDescritivo.setStylePrimaryName("gwt-AnuncioGrid-descritivo");
		
		Label lblDescricaoGeral = new Label();
		if (ifcvo.imovel.descricaoGeral.length() > 500) {
			lblDescricaoGeral.setText(ifcvo.imovel.descricaoGeral.substring(0, 500) + "...");
		} else {
			lblDescricaoGeral.setText(ifcvo.imovel.descricaoGeral);
		}
		
		vpDescritivo.add(lblDescricaoGeral);
		//HTML recomendarfb = new HTML();
		//recomendarfb.setHTML("<iframe src=\"http://www.facebook.com/plugins/like.php?href=http://www.aluguerelaxe.com.br/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id="+String.valueOf(ifcvo.imovel.id)+"&o=FB&;layout=standard&show_faces=false&width=380&action=recommend&colorscheme=light&height=25&locale=pt_BR\" scrolling=\"no\" frameborder=\"0\" style=\"border:none; overflow:hidden; width:380px; height:25px;\" allowTransparency=\"true\"></iframe>");
		//vpDescritivo.add(recomendarfb);

		return vpDescritivo;
	}
	
	
}

