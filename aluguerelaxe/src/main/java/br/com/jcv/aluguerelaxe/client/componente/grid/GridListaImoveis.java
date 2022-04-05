package br.com.jcv.aluguerelaxe.client.componente.grid;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GridListaImoveis extends Grid {
	
	public GridListaImoveis() {
		super();
	}
	
	public Grid getListaImovel(List<ImovelFichaCompletaVO> lst) {
		
		Grid grdimoveis = new Grid(1,1);
		VerticalPanel vpAnuncioImoveis = new VerticalPanel();

		for (ImovelFichaCompletaVO ifcdto : lst) {
			VerticalPanel vpAnuncioItem = new VerticalPanel();
			
			VerticalPanel vpTitulo = new VerticalPanel();
			vpTitulo.setStyleName("ar-liuf-titulo");
			vpTitulo.add(new Label(ifcdto.imovel.descricaoTituloAnuncio));
			
			HorizontalPanel hp = new HorizontalPanel();
			VerticalPanel vpfoto = new VerticalPanel();
			vpfoto.setStyleName("ar-liuf-pn-foto");
			hp.add(vpfoto);
			
			Image imgfoto = new Image("images/no-photo.jpg");
			if (ifcdto.imagensImovelTB != null){
				ImovelImagemVideoVO imagemvo = (ImovelImagemVideoVO) ifcdto.imagensImovelTB.get(0);
				imgfoto.setUrl(imagemvo.nome);
			}
			vpfoto.add(imgfoto);
			HorizontalPanel hpSinalMaisImoveis = new HorizontalPanel();
			hpSinalMaisImoveis.add(new Image("images/add.png"));
			hpSinalMaisImoveis.add(new Label("Im\u00f3veis em " + ifcdto.imovel.endereco.cidade ));
			
			vpfoto.add(hpSinalMaisImoveis);
			
			VerticalPanel vpinfoimovel = new VerticalPanel();
			vpinfoimovel.add(new Label("Local: " + ifcdto.imovel.endereco.bairro + " - " + ifcdto.imovel.endereco.cidade + " - " + ifcdto.imovel.endereco.uf));
			vpinfoimovel.add(new Label(ifcdto.imovel.descricaoGeral.substring(0, 300).concat("...")));
			

			HTML alnkMaisDetalhes = new HTML();
			alnkMaisDetalhes.setStyleName("ar-liuf-mais-detalhes");
			alnkMaisDetalhes.setHTML("<img src='images/note_view.png'>&nbsp;<a href='/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id="+ifcdto.imovel.id+"'>Veja mais detalhes sobre o im\u00f3vel</a>");
			
			vpinfoimovel.add(alnkMaisDetalhes);
			hp.add(vpinfoimovel);
			
			HorizontalPanel hpSeparador = new HorizontalPanel();
			Image imgseparador = new Image();
			imgseparador.setStyleName("ar-separador-liuf");
			hpSeparador.add(imgseparador);
			
			
			vpAnuncioItem.add(vpTitulo);
			vpAnuncioItem.add(hp);
			vpAnuncioItem.add(hpSeparador);
			
			vpAnuncioImoveis.add(vpAnuncioItem);
		}
		
		grdimoveis.setWidget(0, 0, vpAnuncioImoveis);
		
		return grdimoveis;
	}
	
}
