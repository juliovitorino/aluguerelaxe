package br.com.jcv.webservices.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import br.com.jcv.backend.buscapagina.BuscaPagina;
import br.com.jcv.backend.buscapagina.BuscaPaginaListaImoveisPorEstado;
import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.cidade.CidadeServiceImpl;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.CidadeService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.webservices.cache.PublicidadeCache;
import br.com.jcv.webservices.cache.PublicidadeDestaqueCache;
import br.com.jcv.webservices.interfaces.IMobile;

import com.google.gson.Gson;

// Marca a classe para um endpoint para uma interface
@WebService(endpointInterface = "br.com.jcv.webservices.interfaces.IMobile")
public class MobileImpl implements IMobile {

	public String listCidadesDaUfComImoves(String uf) {
		List<CidadeDTO> lst = null;
		String retorno = "";
		try {
			CidadeService<CidadeDTO> cs = new CidadeServiceImpl();
			lst = cs.listCidadesDaUfComImoveis(uf);
			retorno = new Gson().toJson(lst);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public String listaImoveisPorEstado(String uf, int pagina, int regPagina)
			throws AlugueRelaxeException {
		Map<String,String> param = new HashMap<String,String>();
		param.put((Constantes.CONTEXTO_BP_UF), uf);
		BuscaPagina<ImovelFichaCompletaDTO> bp = new BuscaPaginaListaImoveisPorEstado();
		return new Gson().toJson(bp.execute(param, pagina, regPagina));
	}

	public String listaImoveisPorCidade(int cidade, int pagina, int regPagina)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public String apresentarFichaCompletaImovel(long codigoImovel,
			String origemAcesso) throws AlugueRelaxeException {
		ImovelFichaCompletaDTO ifcdto = null;
		
		try {
			ImovelService is = new ImovelServiceImpl();
			ifcdto = is.apresentarFichaCompletaImovel(codigoImovel,origemAcesso);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
		}
		
		// Serializa objeto no formato JSON para enviar cliente
		return new Gson().toJson(ifcdto);
	}

	public String getPainelPublicidade() {
		return new Gson().toJson(PublicidadeCache.getInstance().getPublicidadeCache());
	}

	public String getPainelPublicidadeDestaque() {
		return new Gson().toJson(PublicidadeDestaqueCache.getInstance().getPublicidadeCache());
	}

}
