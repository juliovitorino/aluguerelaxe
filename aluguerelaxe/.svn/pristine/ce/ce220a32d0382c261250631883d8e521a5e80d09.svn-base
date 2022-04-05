package br.com.jcv.aluguerelaxe.client.imovel.listagem.uf;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

import br.com.jcv.aluguerelaxe.client.componente.filtro.AbstractFiltro;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

/**
 *<h2>ImovelFiltro</h2>
 *<p>Classe com implementação do filtro de opções para imóvel. Usa uma implementação de @link{FiltroImovelComposite}
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractFiltro-ImovelFiltro {estilo primario}</li>
 * </ul>
 * <h2>Filtros habilitados em @link{FiltroImovelComposite}</h2>
 * <ul>
 * <li>Quantidade de quartos</li>
 * <li>Quantidade de suites</li>
 * <li>Quantidade de banheiros</li>
 * <li>Capacidade de pessoas</li>
 * <li>Dentro de condominio</li>
 * <li>Permite alugar para festas e eventos</li>
 * <li>Tipo da propriedade</li>
 * <li>UF</li>
 * <li>Cidade</li>
 * </ul>
 * @author Julio Vitorino
 */
public class ImovelFiltro extends AbstractFiltro<ImovelFichaCompletaVO,FiltroImovelComposite> {
	
	public static final String PATH_IMAGEM = "images/48x48/";
	
	public ImovelFiltro(FiltroImovelComposite fic) {
		super(fic);
		this.setStylePrimaryName("gwt-AbstractFiltro-ImovelFiltro");
		this.addHeader(montaHeader());
	}
	
	private Widget montaHeader() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(new Image(PATH_IMAGEM + "funil.png"));
		hp.add(new HTML("<h3>Prefer\u00eancias<br/>na Busca</h3>"));
		return hp;
	}
}
