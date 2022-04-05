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
 * <h2>BuscaPaginaListaImoveisPorEstado</h2>
 * <p>Classe responsável por contatar o backEndServices para buscar uma porção dos registros
 * por UF e a contagem total para ser usada na paginação de registros de imóveis. 
 * </p>
 @author Julio Vitorino
 */
public class BuscaPaginaListaImoveisPorEstado implements BuscaPagina<ImovelFichaCompletaDTO> {

	public EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> execute(Map<String,String> param, int indice, int qtdePorPagina) {
		EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> envelope = null;
		ImovelService is = new ImovelServiceImpl();
		try {
			String uf = param.get(Constantes.CONTEXTO_BP_UF);
			List<ImovelFichaCompletaDTO> lst = is.listaImoveisPorEstado(uf, indice, qtdePorPagina);
			long totalImoveisPorEstado = is.contarImoveisPorEstado(uf);
			
			// Envelopa o resultado para o retorno
			envelope = new EnvelopePaginacaoDTO<ImovelFichaCompletaDTO>();
			envelope.lst = lst;
			envelope.totalRegistros = totalImoveisPorEstado;
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
