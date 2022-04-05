
package br.com.jcv.aluguerelaxe.client.mobile.main;

import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.mobile.componente.anuncio.AbstractAnuncio;
import br.com.jcv.aluguerelaxe.client.mobile.componente.anuncio.AnuncioGrid;
import br.com.jcv.aluguerelaxe.client.mobile.componente.anuncio.AnuncioGridColaboradorOficial;
import br.com.jcv.aluguerelaxe.client.mobile.componente.anuncio.AnuncioGridPatrocinadorOficial;
import br.com.jcv.aluguerelaxe.client.mobile.componente.anuncio.AnuncioListaPaginadaNaoEncontrado;
import br.com.jcv.aluguerelaxe.client.mobile.componente.anuncio.AnuncioListener;
import br.com.jcv.aluguerelaxe.client.mobile.componente.paginacao.AbstractListPaginada;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelFichaCompletaVO;
import br.com.jcv.ui.client.componente.filtro.AbstractFiltro;
import br.com.jcv.ui.client.componente.filtro.FiltroListener;

import com.google.gwt.user.client.ui.Widget;

public class FichaImovelListPaginadaCidade extends AbstractListPaginada<ImovelFichaCompletaVO, AbstractAnuncio> 
	implements AnuncioListener, FiltroListener<ImovelFichaCompletaVO> {
	
	private static final int PAGINA_INICIAL = 1;
	private int cidade;

	public FichaImovelListPaginadaCidade(int cidade) {
		super();
		this.cidade = cidade;
		init();
		update(PAGINA_INICIAL);
	}
	
	@Override
	protected AbstractAnuncio renderItemLista(ImovelFichaCompletaVO vo) {
		AbstractAnuncio ag = null;
		
		//Seleciona o anuncio de acordo com o tipo
		if (vo.tipoColaboracao.indicadorTipoColaboracao.equals("N")) {
			ag = new AnuncioGrid(vo);
		} else if (vo.tipoColaboracao.indicadorTipoColaboracao.equals("P")){
			ag = new AnuncioGridPatrocinadorOficial(vo);
		} else if (vo.tipoColaboracao.indicadorTipoColaboracao.equals("C")){
			ag = new AnuncioGridColaboradorOficial(vo);
		} else {
			ag = new AnuncioGrid(vo);
		}		ag.addAnuncioListener(this);
		this.updateTrilhaDePao( vo.imovel.endereco.cidade + "(" + vo.imovel.endereco.uf + ")");
		return ag;
	}
	
	@Override
	protected Widget renderItemNaoEncontrado() {
		return new AnuncioListaPaginadaNaoEncontrado(null);
	}
	
	
	private void init() {
		this.param.clear();
		this.param.put(Constantes.MAPA_TAG, Constantes.MAPA_LISTA_IMOVEIS_CIDADE);
		this.param.put(Constantes.CONTEXTO_BP_CIDADE, String.valueOf(cidade));
	}

	public void onAnuncioClick(AbstractAnuncio anuncio) {
		redirect("/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id="+anuncio.getImovelFichaCompletaVO().imovel.id + "&o=LI");
	}

	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;

	public void onFiltroAplicar(AbstractFiltro filtroImpl, ImovelFichaCompletaVO vofiltro) {
		
	}


}

