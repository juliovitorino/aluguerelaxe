package br.com.jcv.aluguerelaxe.client.componente.anuncio;

import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AnuncioUltimoCadastrado extends AbstractAnuncio {
	
	private static final String PATH_IMAGEM = "images/";

	public AnuncioUltimoCadastrado(ImovelFichaCompletaVO ifcvo) {
		super(ifcvo);
		this.setStylePrimaryName("gwt-AnuncioUltimoCadastrado");
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
	@Override
	protected Widget render() {
		VerticalPanel vp = new VerticalPanel();
		
		DockPanel dp = new DockPanel();
		ImovelFichaCompletaVO ifcvo = getImovelFichaCompletaVO(); 
		
		// Monta o anuncio
		if (ifcvo.imagensImovelMI == null){
			ImovelImagemVideoVO iivvo = new ImovelImagemVideoVO();
			iivvo.nome = PATH_IMAGEM + "no-photo.jpg";
			PortaRetrato fotomi = new PortaRetrato(ifcvo, this);
			fotomi.hideDescricaoPortaRetrato();
			dp.add(fotomi, DockPanel.WEST);
			
		} else {
			if (ifcvo.imagensImovelMI.size() > 0) {
				PortaRetrato fotomi = new PortaRetrato(ifcvo, this);
				fotomi.hideDescricaoPortaRetrato();
				dp.add(fotomi, DockPanel.WEST);
			}
			
		}
		VerticalPanel vpDescritivo = new VerticalPanel();
		Label lblAnuncio = new Label(ifcvo.imovel.descricaoTituloAnuncio.toUpperCase());
		Label lblLocal = new Label("Local:" + ifcvo.imovel.endereco.cidade + "/" + ifcvo.imovel.endereco.uf);
		Image imgDetalhes = new Image(PATH_IMAGEM + "detalhes.png");
		
		Label lblTrecho = new Label();
		if (ifcvo.imovel.descricaoGeral.length() > 100) {
			lblTrecho.setText(ifcvo.imovel.descricaoGeral.substring(0, 100) + "...");
		} else {
			lblTrecho.setText(ifcvo.imovel.descricaoGeral);
		}
		
		lblAnuncio.addClickHandler(this);
		lblLocal.addClickHandler(this);
		imgDetalhes.addClickHandler(this);
		lblTrecho.addClickHandler(this);

		vpDescritivo.add(lblAnuncio);
		vpDescritivo.add(lblLocal);
		vpDescritivo.add(lblTrecho);
		vpDescritivo.add(imgDetalhes);
		vpDescritivo.add(new Label(ifcvo.imovel.valorTarifaBasica));

		dp.add(vpDescritivo,DockPanel.CENTER);
		
		vp.add(dp);
		return vp;
	}
}
