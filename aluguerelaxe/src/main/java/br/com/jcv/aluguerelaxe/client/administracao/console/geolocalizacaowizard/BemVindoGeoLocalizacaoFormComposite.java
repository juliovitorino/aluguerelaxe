package br.com.jcv.aluguerelaxe.client.administracao.console.geolocalizacaowizard;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *<h2>InstrucoesAssistenteNovoImovelFormComposite</h2>
 *<p>FormComposite para cria\u00e7\u00e3o de nova conta.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AssistenteNovoImovel {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
public class BemVindoGeoLocalizacaoFormComposite extends FormComposite<ImovelFichaCompletaVO> {
	
	private static final String INSTR_HTML = "<h3>Assistente de Geo-Localiza\u00e7\u00e3o</h3>" +
	"<p>Este assistente o ajudar\u00e1 a registrar seu im\u00f3vel de temporada no Google Maps, ensinando-o a descobrir a coordenadas geogr\u00e1ficas do seu im\u00f3vel.</p> " +
	"<p>Ap\u00f3s realizar todos os passos do assistente, seu im\u00f3vel estar\u00e1 sendo apresentado para o cliente na ficha do seu im\u00f3vel.</p>" +
	"<p><b>Para continuar, clique em Avan\u00e7ar.</b>.</p>";

	private static final String INSTR_HTML_GEOLOCALIZADO = "<span>Im\u00f3vel a ser Geo-Localizado</span>";

	private HTML tituloImovel;
	
	public BemVindoGeoLocalizacaoFormComposite() {
		super();
		this.setStylePrimaryName("gwt-BemVindoGeoLocalizacaoFormComposite");
	}

	@Override
	public ImovelFichaCompletaVO getVO() {
		// Nao Aplicada neste contexto
		return null;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		tituloImovel.setHTML(vo.imovel.descricaoTituloAnuncio);
	}

	@Override
	public Widget render() {
		DockPanel dp = new DockPanel();
		VerticalPanel vp = new VerticalPanel();
		vp.add(new HTML(INSTR_HTML));
		vp.add(new HTML(INSTR_HTML_GEOLOCALIZADO));
		vp.add(tituloImovel);

		dp.add(vp, DockPanel.CENTER);
		dp.add(new Image("images/maps_logo.gif"), DockPanel.WEST);
		return dp;
	}

	@Override
	public void init() {
		tituloImovel = new HTML();
	}

	@Override
	public void clear() {
		// Nao Aplicada neste contexto
	}

	@Override
	public void notifier(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
