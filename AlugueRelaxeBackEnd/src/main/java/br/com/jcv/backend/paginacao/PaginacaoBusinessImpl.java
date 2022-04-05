package br.com.jcv.backend.paginacao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.PaginacaoBusiness;
import br.com.jcv.backend.interfacesdao.PaginacaoDAO;



public class PaginacaoBusinessImpl implements PaginacaoBusiness {


	public EnvelopePaginacaoDataGridDTO listarPorFiltro(DAOFactory daofactory,
												String tabelaView,
												List<String> campos,
												List<CondicaoDTO> param, 
												List<String> lstOrderBy, 
												int indice, 
												int qtdePorPagina) throws AlugueRelaxeException {
		PaginacaoDAO dao = daofactory.getPaginacaoDAO(daofactory);
		return dao.listarPorFiltro(tabelaView, campos, param, lstOrderBy, indice, qtdePorPagina);
	}

}
