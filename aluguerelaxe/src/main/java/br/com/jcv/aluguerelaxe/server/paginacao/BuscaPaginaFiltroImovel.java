package br.com.jcv.aluguerelaxe.server.paginacao;

import java.util.List;
import java.util.Map;

import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.paginacao.CondicaoDTO;
import br.com.jcv.backend.paginacao.EnvelopePaginacaoDataGridDTO;

/**
 * <h2>BuscaPaginaFiltroImovel</h2>
 * <p>Classe responsável por contatar o backEndServices para buscar uma porção dos registros
 * com base em um filtro customizado e a contagem total para ser usada na paginação de registros de imóveis. 
 * </p>
 * @param Map
 * @param int
 * @param int
 * @return EnvelopePaginacaoDTO
 * @author Julio Vitorino
 */
public class BuscaPaginaFiltroImovel implements BuscaPagina<ImovelFichaCompletaDTO> {

	public EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> execute(Map<String,String> param, int indice, int qtdePorPagina) {
		EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> envelope = null;
		ImovelService is = new ImovelServiceImpl();
		try {
			List<ImovelFichaCompletaDTO> lst = is.listaImoveisPorFiltro(param, indice, qtdePorPagina);
			long totalImoveisPorFiltro = is.contarImoveisPorFiltro(param);
			
			// Envelopa o resultado para o retorno
			envelope = new EnvelopePaginacaoDTO<ImovelFichaCompletaDTO>();
			envelope.lst = lst;
			envelope.totalRegistros = totalImoveisPorFiltro;
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return envelope;
	}

	public EnvelopePaginacaoDataGridDTO execute(String tabelaView,
			List<String> campos, List<CondicaoDTO> param,
			List<String> lstOrderBy, int indice, int qtdePorPagina) {
		// TODO Auto-generated method stub
		return null;
	}

}
