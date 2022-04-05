package br.com.jcv.backend.buscapagina;

import java.util.Map;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;

public class BuscaPaginaFactory {

	// Construtor sem acesso
	private BuscaPaginaFactory() {}
	
	public static BuscaPagina<ImovelFichaCompletaDTO> getInstance(Map<String,String> mapa) {
		// Obtem o conteúdo apontado pela chave MAPA_TAG
		String conteudoMapa = mapa.get(Constantes.MAPA_TAG);
		
		// Retorna uma instancia da implementação
		if (conteudoMapa.equals(Constantes.MAPA_FILTRO_GERAL)) {
			return new BuscaPaginaFiltroGeral();
		}
		
		return null;
	}
}
