package br.com.jcv.aluguerelaxe.client.imovel.listagem.uf;

import br.com.jcv.aluguerelaxe.client.componente.filtro.AbstractFiltro;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 *<h2>ImovelFiltro</h2>
 *<p>Classe com implementação do filtro de busca somente pelo ID do imóvel. Usa uma implementação de @link{FiltroImovelIDComposite}
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractFiltro {estilo primario}</li>
 * </ul>
 * <h2>Filtros habilitados em @link{FiltroImovelIDComposite}</h2>
 * <ul>
 * <li>ID do imovel</li>
 * </ul>
 * @author Julio Vitorino
 */
public class ImovelIDFiltro extends AbstractFiltro<ImovelFichaCompletaVO,FiltroImovelIDComposite> {
	
	private static final String PATH_IMAGEM = "images/48x48/";
	
	public ImovelIDFiltro(FiltroImovelIDComposite fic) {
		super(fic);
		this.setStylePrimaryName("gwt-AbstractFiltro-ImovelIDFiltro");
		this.addHeader(montaHeader());
	}
	
	private Widget montaHeader() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(new Image(PATH_IMAGEM + "find.png"));
		hp.add(new HTML("<h3>Im\u00f3vel</h3>"));
		return hp;
	}
	
	
}