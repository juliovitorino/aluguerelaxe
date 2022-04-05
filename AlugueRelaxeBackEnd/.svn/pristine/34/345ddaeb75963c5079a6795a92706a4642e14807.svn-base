package br.com.jcv.backend.buscapagina;

import java.util.List;
import java.util.Map;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.paginacao.CondicaoDTO;
import br.com.jcv.backend.paginacao.EnvelopePaginacaoDataGridDTO;

/**
 * <h2>BuscaPaginaFiltroGeral</h2>
 * <p>Classe responsavel por contatar o backEndServices para buscar uma porção dos registros
 * por cidade e a contagem total para ser usada na paginação de registros de imóveis. 
 * </p>
 * @author Julio Vitorino
 */
public class BuscaPaginaFiltroGeral implements BuscaPagina<ImovelFichaCompletaDTO> {

	public EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> execute(Map<String,String> param, int indice, int qtdePorPagina) {
		EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> envelope = null;
		ImovelService is = new ImovelServiceImpl();
		try {
			long idCidadeUf = Long.valueOf(param.get(Constantes.CONTEXTO_BP_ID_CIDADE_UF));
			List<ImovelFichaCompletaDTO> lst = is.listaImoveisPorCidadeUf(idCidadeUf, indice, qtdePorPagina);
			long totalImoveisPorCidade = is.contarImoveisPorCidadeUf(idCidadeUf);

			
			// Envelopa o resultado para o retorno
			envelope = new EnvelopePaginacaoDTO<ImovelFichaCompletaDTO>();
			envelope.lst = lst;
			envelope.totalRegistros = totalImoveisPorCidade;
			envelope.pagina = indice;
			envelope.totalPaginas = ((int) envelope.totalRegistros / qtdePorPagina);

			int resto = (int) envelope.totalRegistros % qtdePorPagina;
			if (resto > 0) {
				envelope.totalPaginas++;
			}
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
