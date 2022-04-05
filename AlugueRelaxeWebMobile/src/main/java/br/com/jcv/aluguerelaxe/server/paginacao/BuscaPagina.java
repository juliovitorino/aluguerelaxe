package br.com.jcv.aluguerelaxe.server.paginacao;

import java.util.List;
import java.util.Map;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.paginacao.CondicaoDTO;
import br.com.jcv.backend.paginacao.EnvelopePaginacaoDataGridDTO;

/**
 * <h2></h2>
 */
public interface BuscaPagina<V extends DTOPadrao> {

	EnvelopePaginacaoDTO<V> execute(Map<String,String> param, int indice, int qtdePorPagina);
	EnvelopePaginacaoDataGridDTO execute(String tabelaView, 
			List<String> campos, 
			List<CondicaoDTO> param, 
			List<String> lstOrderBy, 
			int indice, 
			int qtdePorPagina);
}
