package br.com.jcv.aluguerelaxe.server.paginacao;

import java.util.Map;

import br.com.jcv.aluguerelaxe.client.Constantes;

public class BuscaPaginaFactory {

	// Construtor sem acesso
	private BuscaPaginaFactory() {}
	
	public static BuscaPagina getInstance(Map<String,String> mapa) {
		// Obtem o conteúdo apontado pela chave MAPA_TAG
		String conteudoMapa = mapa.get(Constantes.MAPA_TAG);
		
		// Retorna uma instancia da implementação
		if (conteudoMapa.equals(Constantes.MAPA_LISTA_IMOVEIS)) {
			return new BuscaPaginaListaImoveisPorEstado();
		} else if (conteudoMapa.equals(Constantes.MAPA_LISTA_IMOVEIS_CIDADE)) {
			return new BuscaPaginaListaImoveisPorCidade();
		} else if (conteudoMapa.equals(Constantes.MAPA_LISTA_ID_IMOVEL)) {
			return new BuscaPaginaIDImovel();
		} else if (conteudoMapa.equals(Constantes.MAPA_FILTRO_IMOVEL)) {
			return new BuscaPaginaFiltroImovel();
		} else if (conteudoMapa.equals(Constantes.MAPA_DATA_GRID)) {
			return new BuscaPaginaDataGrid();
		}
		
		return null;
	}
}
