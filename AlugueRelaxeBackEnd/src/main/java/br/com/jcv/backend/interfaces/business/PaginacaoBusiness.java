package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.paginacao.CondicaoDTO;
import br.com.jcv.backend.paginacao.EnvelopePaginacaoDataGridDTO;


public interface PaginacaoBusiness {

	EnvelopePaginacaoDataGridDTO listarPorFiltro(DAOFactory daofactory,
												String tabelaView,
												List<String> campos,
												List<CondicaoDTO> param, 
												List<String> lstOrderBy, 
												int indice, 
												int qtdePorPagina) throws AlugueRelaxeException;
}

