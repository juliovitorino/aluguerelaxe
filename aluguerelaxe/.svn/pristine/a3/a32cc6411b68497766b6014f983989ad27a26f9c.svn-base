package br.com.jcv.aluguerelaxe.client.administracao.console.geolocalizacaowizard;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PassoaPassoGoogleMapsFormComposite extends FormComposite<VOPadrao> {

	private static final String PATH_IMAGEM = "images/";
	
	private static final String HTML_PASSO_1 = "<h3>1) Abrir browser</h3>" +
	"<p>Abra um novo browser e digite a URL do Google Maps, conforme a imagem abaixo:<br/><b>http://maps.google.com.br</b></p>";
	
	private static final String HTML_PASSO_2 = "<h3>2) Digitar o endere\u00e7o</h3>" +
	"<p>Digite o endere\u00e7o do seu im\u00f3vel de temporada no Google Maps conforme a imagem abaixo. </p>";

	private static final String HTML_PASSO_3 = "<h3>3) Assinalar Geo-Localiza\u00e7\u00e3o</h3>" +
	"<p><ul>Siga estes passos:</ul>" +
	"<li>Movimente o mouse at\u00e9 uma posi\u00e7\u00e3o aproximada do seu im\u00f3vel</li>" +
	"<li>Clique com o bot\u00e3o direito nesta posi\u00e7\u00e3o, o Google Maps lhe mostrar\u00e1 um menu de op\u00e7\u00f5es</li>" +
	"<li>Clique na op\u00e7\u00e3o <b>O que h\u00e1 aqui?</b></li>" +
	"<li>V\u00e1 para o passo 4</li>" +
	"</p>";

	private static final String HTML_PASSO_4 = "<h3>Copiar coordenadas</h3>" +
	"<p>Copie as coordenadas apresentadas pelo Google Maps do seu im\u00f3vel de temporada e clique em <b>Avan\u00e7ar</b>, veja o exemplo abaixo:</p>";
	
	@Override
	public VOPadrao getVO() {
		// Nao Aplicavel
		return null;
	}

	@Override
	public void update(VOPadrao vo) {
		//Nao aplicavel
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(new HTML(HTML_PASSO_1));
		vp.add(new Image(PATH_IMAGEM + "gmaps_passo1.png"));

		vp.add(new HTML(HTML_PASSO_2));
		vp.add(new Image(PATH_IMAGEM + "gmaps_passo2.png"));

		vp.add(new HTML(HTML_PASSO_3));
		vp.add(new Image(PATH_IMAGEM + "gmaps_passo3.png"));

		vp.add(new HTML(HTML_PASSO_4));
		vp.add(new Image(PATH_IMAGEM + "gmaps_passo4.png"));

		return vp;
	}

	@Override
	public void init() {
		//Nao Aplicavel
	}

	@Override
	public void clear() {
		//Nao aplicavel
	}

	@Override
	public void notifier(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}

}
