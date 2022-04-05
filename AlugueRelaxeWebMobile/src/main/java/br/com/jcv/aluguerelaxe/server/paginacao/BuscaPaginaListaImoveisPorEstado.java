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
 * <h2>BuscaPaginaListaImoveisPorEstado</h2>
 * <p>Classe respons�vel por contatar o backEndServices para buscar uma por��o dos registros
 * por UF e a contagem total para ser usada na pagina��o de registros de im�veis. 
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
