
package br.com.jcv.aluguerelaxe.client.mobile.main;

import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.mobile.componente.anuncio.AbstractAnuncio;
import br.com.jcv.aluguerelaxe.client.mobile.componente.anuncio.AnuncioGrid;
import br.com.jcv.aluguerelaxe.client.mobile.componente.anuncio.AnuncioListaPaginadaNaoEncontrado;
import br.com.jcv.aluguerelaxe.client.mobile.componente.anuncio.AnuncioListener;
import br.com.jcv.aluguerelaxe.client.mobile.componente.paginacao.AbstractListPaginada;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelFichaCompletaVO;
import br.com.jcv.ui.client.componente.filtro.AbstractFiltro;

import com.google.gwt.user.client.ui.Widget;

public class FichaImovelListPaginada extends AbstractListPaginada<ImovelFichaCompletaVO, AbstractAnuncio> 
	implements AnuncioListener {
	
	private static final int PAGINA_INICIAL = 1;
	private String uf;

	public FichaImovelListPaginada(String uf) {
		super();
		this.uf = uf;
		init();
		update(PAGINA_INICIAL);
	}
	
	@Override
	protected AbstractAnuncio renderItemLista(ImovelFichaCompletaVO vo) {
		AbstractAnuncio ag = null;
		
		//Seleciona o anuncio de acordo com o tipo de colaboracao
		//if (vo.tipoColaboracao.indicadorTipoColaboracao.equals("N")) {
			ag = new AnuncioGrid(vo);
		//} else if (vo.tipoColaboracao.indicadorTipoColaboracao.equals("P")){
			//ag = new AnuncioGridPatrocinadorOficial(vo);
		//} else if (vo.tipoColaboracao.indicadorTipoColaboracao.equals("C")){
			//ag = new AnuncioGridColaboradorOficial(vo);
		//} else {
			//ag = new AnuncioGrid(vo);
		//}
		
		ag.addAnuncioListener(this);
		this.updateTrilhaDePao( vo.imovel.endereco.nomeuf);
		return ag;
	}
	
	@Override
	protected Widget renderItemNaoEncontrado() {
		return new AnuncioListaPaginadaNaoEncontrado(null);
	}
	
	
	private void init() {
		this.param.clear();
		this.param.put(Constantes.MAPA_TAG, Constantes.MAPA_LISTA_IMOVEIS);
		this.param.put(Constantes.CONTEXTO_BP_UF, uf);
	}

	public void onAnuncioClick(AbstractAnuncio anuncio) {
		redirect("/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id="+anuncio.getImovelFichaCompletaVO().imovel.id + "&o=LI");
	}

	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;

	public void onFiltroAplicar(AbstractFiltro filtroImpl,
			ImovelFichaCompletaVO vofiltro) {
		// TODO Auto-generated method stub
		
	}


}

