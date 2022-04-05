package br.com.jcv.aluguerelaxe.server.paginacao;

import java.util.List;
import java.util.Map;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.PaginacaoService;
import br.com.jcv.backend.paginacao.CondicaoDTO;
import br.com.jcv.backend.paginacao.EnvelopePaginacaoDataGridDTO;
import br.com.jcv.backend.paginacao.PaginacaoServiceImpl;

/**
 * <h2>BuscaPaginaDataGrid</h2>
 * <p>Classe responsável por contatar o backEndServices para buscar uma porção dos registros
 * com base em um filtro customizado e a contagem total para ser usada na paginação.
 * </p>
 * @param Map
 * @param int
 * @param int
 * @return EnvelopePaginacaoDTO
 * @author Julio Vitorino
 */
public class BuscaPaginaDataGrid implements BuscaPagina<DTOPadrao> {

	public EnvelopePaginacaoDataGridDTO execute(String tabelaView, 
												List<String> campos, 
												List<CondicaoDTO> param, 
												List<String> lstOrderBy, 
												int indice, 
												int qtdePorPagina) {
		EnvelopePaginacaoDataGridDTO envelope = null;
		PaginacaoService is = new PaginacaoServiceImpl();
		try {
			envelope = is.listarPorFiltro(tabelaView, campos, param, lstOrderBy, indice, qtdePorPagina);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return envelope;
	}

	public EnvelopePaginacaoDTO<DTOPadrao> execute(Map<String,String> param, int indice, int qtdePorPagina) {
		return null;
	}
}
