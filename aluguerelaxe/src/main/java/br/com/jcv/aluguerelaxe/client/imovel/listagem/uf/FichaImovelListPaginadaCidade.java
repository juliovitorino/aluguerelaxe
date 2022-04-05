
package br.com.jcv.aluguerelaxe.client.imovel.listagem.uf;

import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.componente.anuncio.AbstractAnuncio;
import br.com.jcv.aluguerelaxe.client.componente.anuncio.AnuncioGrid;
import br.com.jcv.aluguerelaxe.client.componente.anuncio.AnuncioGridColaboradorOficial;
import br.com.jcv.aluguerelaxe.client.componente.anuncio.AnuncioGridPatrocinadorOficial;
import br.com.jcv.aluguerelaxe.client.componente.anuncio.AnuncioListaPaginadaNaoEncontrado;
import br.com.jcv.aluguerelaxe.client.componente.anuncio.AnuncioListener;
import br.com.jcv.aluguerelaxe.client.componente.filtro.AbstractFiltro;
import br.com.jcv.aluguerelaxe.client.componente.filtro.FiltroListener;
import br.com.jcv.aluguerelaxe.client.componente.paginacao.AbstractListPaginada;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

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
		
		this.param.clear();
		if (filtroImpl instanceof ImovelIDFiltro) {
			this.param.put(Constantes.MAPA_TAG, Constantes.MAPA_LISTA_ID_IMOVEL);
			this.param.put(Constantes.CONTEXTO_ID_IMOVEL, String.valueOf(vofiltro.imovel.id));
		} else if (filtroImpl instanceof ImovelFiltro){
			this.param.put(Constantes.MAPA_TAG, Constantes.MAPA_FILTRO_IMOVEL);
			this.param.put(Constantes.CONTEXTO_PROPRIEDADE, vofiltro.imovel.indicadorTipoPropriedade);
			this.param.put(Constantes.CONTEXTO_QUARTO, String.valueOf(vofiltro.imovel.qtdeQuartos));
			this.param.put(Constantes.CONTEXTO_SUITE, String.valueOf(vofiltro.imovel.qtdeSuites));
			this.param.put(Constantes.CONTEXTO_BANHEIRO, String.valueOf(vofiltro.imovel.qtdeBanheiros));
			this.param.put(Constantes.CONTEXTO_CAPACIDADE, String.valueOf(vofiltro.imovel.qtdeCapacidade));
			this.param.put(Constantes.CONTEXTO_CONDOMINIO, String.valueOf(vofiltro.imovel.indicadorCondominio));
			this.param.put(Constantes.CONTEXTO_ALUGA_FESTA, String.valueOf(vofiltro.imovel.indicadorPermiteAlugarFestas));
			this.param.put(Constantes.CONTEXTO_BP_UF, String.valueOf(vofiltro.imovel.endereco.uf));
			this.param.put(Constantes.CONTEXTO_BP_CIDADE, String.valueOf(vofiltro.imovel.endereco.codigoUfCidadeItem));
		}
		update(PAGINA_INICIAL);
		
		// Retorna mapeamento anterior
		init();
	}


}

