package br.com.jcv.aluguerelaxe.server.paginacao;

import java.util.ArrayList;
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
 * <h2>BuscaPaginaIDImovel</h2>
 * <p>Classe responsável por contatar o backEndServices para buscar um envelope dos registros
 * por ID.
 * </p>
 @author Julio Vitorino
 */
public class BuscaPaginaIDImovel implements BuscaPagina<ImovelFichaCompletaDTO> {

	public EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> execute(Map<String,String> param, int indice, int qtdePorPagina) {
		EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> envelope = null;
		ImovelService is = new ImovelServiceImpl();
		try {
			long id = Long.valueOf(param.get(Constantes.CONTEXTO_ID_IMOVEL));
			ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(id);
			
			List<ImovelFichaCompletaDTO> lst = new ArrayList<ImovelFichaCompletaDTO>();
			lst.add(ifcdto);
			
			// Envelopa o resultado para o retorno
			envelope = new EnvelopePaginacaoDTO<ImovelFichaCompletaDTO>();
			envelope.lst = lst;
			envelope.totalRegistros = 1;
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
