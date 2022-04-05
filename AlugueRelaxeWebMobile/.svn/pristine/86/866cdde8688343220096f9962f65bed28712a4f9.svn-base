
package br.com.jcv.aluguerelaxe.client.mobile.componente.anuncio;

import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelImagemVideoVO;
import br.com.jcv.ui.client.componente.windowpanel.WindowPanel;

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
public class AnuncioGridPatrocinadorOficial extends AbstractAnuncio {
	
	public static final String PATH_IMAGEM = "images/";
	public static final String PATH_IMAGEM_48 = "images/48x48/";
	private static final String TEMA_HEADER = "orkut";
	
	public AnuncioGridPatrocinadorOficial(ImovelFichaCompletaVO ifcvo) {
		super(ifcvo);
		this.setStylePrimaryName("gwt-AnuncioGridPatrocinadorOficial");
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
		vp.setStylePrimaryName("gwt-agpo-container");

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
		
		WindowPanel wpPoficial = new WindowPanel(null,TEMA_HEADER,false,false,false);
		
		if ((ifcvo.imovel.valorTarifaBasica != null) && (ifcvo.imovel.valorTarifaBasica.length() > 0)) {
			String str = "Di\u00e1ria partir de:<br/>";
			HTML htmltarifa = new HTML(str + "<h1> R$" + ifcvo.imovel.valorTarifaBasica + "</h1>");
			htmltarifa.setStyleName("gwt-tarifa");
			hp.add(htmltarifa);
		} else {
			Image imgpo = new Image(PATH_IMAGEM + "patroficial.png");
			imgpo.setStylePrimaryName("gwt-imgpo");
			wpPoficial.setComponenteCenter(imgpo);
			hp.add(wpPoficial);
			
		}

		
		StringBuilder sb = new StringBuilder();

		if (ifcvo.imovel.indicadorTipoPropriedade.equals("C")) {
			sb.append("Casa, ");
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("A")) {
			sb.append("Apartamento, ");
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("H")) {
			sb.append("Hotel, ");
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("X")) {
			sb.append("Ch\u00e1cara, ");
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("F")) {
			sb.append("Flat, ");
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("F")) {
			sb.append("Fazenda, ");
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("S")) {
			sb.append("S\u00edtio, ");
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("L")) {
			sb.append("Chal\u00e9, ");
		} else if (ifcvo.imovel.indicadorTipoPropriedade.equals("P")) {
			sb.append("Pousada, ");
		}
		
		if(ifcvo.imovel.qtdeQuartos == 1){
			sb.append(String.valueOf(ifcvo.imovel.qtdeQuartos)).append(" quarto, ");
		} else if(ifcvo.imovel.qtdeQuartos > 1) {
			sb.append(String.valueOf(ifcvo.imovel.qtdeQuartos)).append(" quartos, ");
		}
		
		if (ifcvo.imovel.qtdeBanheiros == 1) {
			sb.append(String.valueOf(ifcvo.imovel.qtdeBanheiros)).append(" banheiro, ");
		} else if (ifcvo.imovel.qtdeBanheiros > 1) {
			sb.append(String.valueOf(ifcvo.imovel.qtdeBanheiros)).append(" banheiros, ");
		}
		
		if (ifcvo.imovel.qtdeSuites == 1) {
			sb.append(String.valueOf(ifcvo.imovel.qtdeSuites)).append(" su\u00edte, ");
		} else if (ifcvo.imovel.qtdeSuites > 1) {
			sb.append(String.valueOf(ifcvo.imovel.qtdeSuites)).append(" su\u00edtes, ");
		}
		
		sb.append("M\u00e1ximo de ").append(String.valueOf(ifcvo.imovel.qtdeCapacidade)).append(" pessoa(s), ");
		
		if (ifcvo.imovel.indicadorCondominio.equals("S")){
			sb.append("fica em Condom\u00ednio, ");
		} else {
			sb.append("n\u00e3o fica em Condom\u00ednio, ");
		}
		if (ifcvo.imovel.indicadorPermiteAlugarFestas.equals("S")){
			sb.append("permite festas e eventos. ");
		} else {
			sb.append("n\u00e3o permite festas e eventos. ");
		}
		
		// Monta o painel de informacoes
		Image imginfo = new Image(PATH_IMAGEM_48 + "information2.png");
		imginfo.setStylePrimaryName("gwt-imginfo");
		
		Label lblinfo = new Label(sb.toString());
		HorizontalPanel hpinfo = new HorizontalPanel();
		hpinfo.setStylePrimaryName("gwt-hpinfo");
		hpinfo.add(imginfo);
		hpinfo.add(lblinfo);
		
		WindowPanel wpinfo = new WindowPanel("Resumo",TEMA_HEADER,false,false,false);
		wpinfo.setWidth("520px");
		wpinfo.setComponenteCenter(hpinfo);
		
		hp.add(wpinfo);
		
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
		return vp;
	}

	private Widget montaHeader(ImovelFichaCompletaVO ifcvo) {
		HorizontalPanel hp = new HorizontalPanel();
		//hp.setStylePrimaryName("gwt-hp-header");
		
		// 1a parte do header
		WindowPanel wpTitulo = new WindowPanel(ifcvo.imovel.endereco.cidade + "/" + ifcvo.imovel.endereco.uf + " :: Propriedade #" + String.valueOf(ifcvo.imovel.id),TEMA_HEADER,false,false,false);
		wpTitulo.setWidth("520px");
		//wpTitulo.setStylePrimaryName("gwt-windowpanel-header");
		HorizontalPanel hpTitulo = new HorizontalPanel();
		Image imghome = new Image(PATH_IMAGEM_48 + "home.png");
		imghome.setStylePrimaryName("gwt-imghome");
		hpTitulo.add(imghome);
		
		VerticalPanel vpTitulo = new VerticalPanel();
		//String local = ifcvo.imovel.endereco.cidade + "/" + ifcvo.imovel.endereco.uf;
		
		//Label lblLocal = new Label(local);
		//lblLocal.addClickHandler(this);
		//vpTitulo.add(lblLocal);
		
		Label lblAnuncio = new Label(ifcvo.imovel.descricaoTituloAnuncio);
		lblAnuncio.setStylePrimaryName("gwt-lblAnuncio");
		lblAnuncio.addClickHandler(this);
		vpTitulo.add(lblAnuncio);
		
		hpTitulo.add(vpTitulo);

		wpTitulo.setComponenteCenter(hpTitulo);
		
		// 2a parte do header
		WindowPanel wpInfo = new WindowPanel("Cliente desde",TEMA_HEADER,false,false,false);
		wpInfo.setWidth("170px");
		VerticalPanel vpInfo = new VerticalPanel();
		Label lblDesde = new Label(ifcvo.imovel.dataCadastroStr);
		vpInfo.add(lblDesde);
		
		Label lblVisitas = new Label("com " + ifcvo.imovel.qtdeVisitas + " visita(s)");
		vpInfo.add(lblVisitas);

		wpTitulo.setComponenteCenter(vpTitulo);
		wpInfo.setComponenteCenter(vpInfo);
		
		// Termina Montagem do Header
		hp.add(wpTitulo);
		hp.add(wpInfo);
		return hp;
	}

	private Widget montaDescritivoAnuncio(ImovelFichaCompletaVO ifcvo) {
		VerticalPanel vpDescritivo = new VerticalPanel();
		vpDescritivo.setStylePrimaryName("gwt-agpo-descritivo");
		
		Label lblDescricaoGeral = new Label();
		if (ifcvo.imovel.descricaoGeral.length() > 500) {
			lblDescricaoGeral.setText(ifcvo.imovel.descricaoGeral.substring(0, 500) + "...");
		} else {
			lblDescricaoGeral.setText(ifcvo.imovel.descricaoGeral);
		}
		
		Image imgDetalhes = new Image(PATH_IMAGEM + "gostou.png");
		imgDetalhes.addClickHandler(this);
		
		vpDescritivo.add(lblDescricaoGeral);
		vpDescritivo.add(imgDetalhes);

		//HTML recomendarfb = new HTML();
		//recomendarfb.setHTML("<iframe src=\"http://www.facebook.com/plugins/like.php?href=http://www.aluguerelaxe.com.br/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id="+String.valueOf(ifcvo.imovel.id)+"&o=FB&;layout=standard&show_faces=false&width=380&action=recommend&colorscheme=light&height=25&locale=pt_BR\" scrolling=\"no\" frameborder=\"0\" style=\"border:none; overflow:hidden; width:380px; height:25px;\" allowTransparency=\"true\"></iframe>");
		//vpDescritivo.add(recomendarfb);

		return vpDescritivo;
	}
	
	
}

