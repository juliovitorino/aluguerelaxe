package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.jcv.aluguerelaxe.client.painel.PainelUfCidadesRPC;
import br.com.jcv.aluguerelaxe.client.painel.PainelUfCidadesVO;
import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.cidade.CidadeServiceImpl;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.CidadeService;
import br.com.jcv.backend.interfaces.services.UFService;
import br.com.jcv.backend.uf.UFDTO;
import br.com.jcv.backend.uf.UFServiceImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PainelUfCidadesRPCImpl extends RemoteServiceServlet implements PainelUfCidadesRPC {
	/**
	 * <h2>logger</h2>
	 * <p>Variável responsável pelo log</p> 
	 */
	private static final Logger logger = Logger.getLogger(PainelUfCidadesRPCImpl.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 6501807032887886923L;

	public List<PainelUfCidadesVO> listarMaioresQtdeImoveisPorEstado() {
		logger.debug("listando Maiores Qtde Imoveis Por Estado");

		List<PainelUfCidadesVO> retorno = null;
		try {
			//---------------------------------------------------------
			// Busca a lista dos 4 estados com maior numero de imoveis
			//---------------------------------------------------------
			UFService<UFDTO> as = new UFServiceImpl();
			List<UFDTO> dtoList = as.listarMaioresQtdeImoveisPorEstado();
			retorno = new ArrayList<PainelUfCidadesVO>();
			
			//---------------------------------------------------------
			// Itera cada UF 
			//---------------------------------------------------------
			int contadorUfs = 1;
			for (UFDTO dto : dtoList) {
				PainelUfCidadesVO vo = new PainelUfCidadesVO();
				vo.setId(dto.getId());
				vo.setDescricao(dto.getDescricao());
				vo.setQuantidade(dto.getQuantidade());
				
				logger.debug("        id:" + vo.id);
				logger.debug(" descrição:" + vo.descricao);
				logger.debug("quantidade:" + vo.quantidade);
				//---------------------------------------------------------
				// Obtem as cidades com maior quantidade de imoveis 
				//---------------------------------------------------------
				vo.listaCidades = this.listarCidadesDaUf(vo.getId());
				
				retorno.add(vo);
				contadorUfs++;
			}
			
			//-------------------------------------------------------------
			// Completa de contadorUfs até a 4a cidade
			//-------------------------------------------------------------
			for (int i = contadorUfs; i < 5; i++) {
				PainelUfCidadesVO vo = new PainelUfCidadesVO();
				vo.setId("#");
				vo.setDescricao("Temos vaga para Top4");
				vo.setQuantidade(0);
				retorno.add(vo);
			}
			
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}

	public List<PainelUfCidadesVO> listarCidadesDaUf(String uf) {
		logger.debug("Listando cidades da uf:" + uf);
		
		List<PainelUfCidadesVO> retorno = null;
		try {
			CidadeService<CidadeDTO> as = new CidadeServiceImpl();
			List<CidadeDTO> dtoList = as.listCidadesDaUfComImoves(uf);
			retorno = new ArrayList<PainelUfCidadesVO>();
			for (CidadeDTO dto : dtoList) {
				PainelUfCidadesVO vo = new PainelUfCidadesVO();
				vo.setId(String.valueOf(dto.getId().longValue()));
				vo.setDescricao(dto.getNome());
				retorno.add(vo);

				logger.debug("       id:" + vo.id);
				logger.debug("descrição:" + vo.descricao);
			}
			
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}

	private List<PainelUfCidadesVO> listarCidadesDaUfMaisVisitadas(String uf) {
		logger.debug("Listando cidades da uf:" + uf);

		List<PainelUfCidadesVO> retorno = null;
		try {
			CidadeService<CidadeDTO> as = new CidadeServiceImpl();
			List<CidadeDTO> dtoList = as.listCidadesDaUfComImovesMaisVisitadas(uf);
			retorno = new ArrayList<PainelUfCidadesVO>();
			for (CidadeDTO dto : dtoList) {
				PainelUfCidadesVO vo = new PainelUfCidadesVO();
				vo.setId(String.valueOf(dto.getId().longValue()));
				vo.setDescricao(dto.getNome());
				retorno.add(vo);
				logger.debug("       id:" + vo.id);
				logger.debug("descrição:" + vo.descricao);
			}
			
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}
	
	public List<PainelUfCidadesVO> listarEstadosMaisVisitados() {
		List<PainelUfCidadesVO> retorno = null;
		try {
			//---------------------------------------------------------
			// Busca a lista dos 4 estados com maior numero de visitas
			//---------------------------------------------------------
			UFService<UFDTO> as = new UFServiceImpl();
			List<UFDTO> dtoList = as.listarEstadosMaisVisitados();
			retorno = new ArrayList<PainelUfCidadesVO>();
			
			//---------------------------------------------------------
			// Itera cada UF 
			//---------------------------------------------------------
			int contadorUfs = 1;
			for (UFDTO dto : dtoList) {
				PainelUfCidadesVO vo = new PainelUfCidadesVO();
				vo.setId(dto.getId());
				vo.setDescricao(dto.getDescricao());
				vo.setQuantidade(dto.getQuantidade());

				logger.debug("        id:" + vo.id);
				logger.debug(" descricao:" + vo.descricao);
				logger.debug("quantidade:" + vo.quantidade);
				
				//-----------------------------------------------------------
				// Obtem as cidades com maior numero de imoveis visitados 
				//-----------------------------------------------------------
				vo.listaCidades = this.listarCidadesDaUfMaisVisitadas(vo.getId());
				
				retorno.add(vo);
				contadorUfs++;
			}
			
			//-------------------------------------------------------------
			// Completa de contadorUfs até a 4a cidade
			//-------------------------------------------------------------
			for (int i = contadorUfs; i < 5; i++) {
				PainelUfCidadesVO vo = new PainelUfCidadesVO();
				vo.setId("#");
				vo.setDescricao("Temos vaga para mais visitados");
				vo.setQuantidade(0);
				retorno.add(vo);
			}
			
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}

}
