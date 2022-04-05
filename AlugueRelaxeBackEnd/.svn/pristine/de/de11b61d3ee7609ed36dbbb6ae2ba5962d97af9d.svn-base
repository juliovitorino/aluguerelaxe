package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.paginacao.CondicaoDTO;
import br.com.jcv.backend.paginacao.EnvelopePaginacaoDataGridDTO;

public interface PaginacaoService {
	EnvelopePaginacaoDataGridDTO listarPorFiltro(String tabelaView,
												List<String> campos,
												List<CondicaoDTO> param, 
												List<String> lstOrderBy, 
												int indice, 
												int qtdePorPagina) throws AlugueRelaxeException;
												
}

