package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.destino.DestinoRPC;
import br.com.jcv.aluguerelaxe.client.destino.DestinoVO;
import br.com.jcv.aluguerelaxe.client.vo.CidadeUFVO;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOCidadeUFImpl;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ApplicationService;
import br.com.jcv.backend.interfaces.services.UFService;
import br.com.jcv.backend.uf.UFDTO;
import br.com.jcv.backend.uf.UFServiceImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DestinoRPCImpl extends RemoteServiceServlet implements DestinoRPC {

	/**
	 * <h2>logger</h2>
	 * <p>Variável responsável pelo log</p> 
	 */
	private static final Logger logger = Logger.getLogger(DestinoRPCImpl.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = -2217562781251194018L;

	public List<DestinoVO> listDestinos() {
		logger.trace("buscando a lista de destinos");
		List<DestinoVO> retorno = null;
		try {
			ApplicationService<UFDTO> as = new UFServiceImpl();
			List<? extends UFDTO> dtoList = as.listarRegistros();
			retorno = new ArrayList<DestinoVO>();
			
			if (dtoList.size() > 0) {
				logger.debug("foram encontrados " + dtoList.size() + " registro(s)");
			}
			for (UFDTO dto : dtoList) {
				DestinoVO vo = new DestinoVO();
				vo.setId(dto.getId());
				vo.setDescricao(dto.getDescricao());
				retorno.add(vo);
				logger.debug("       id: " + vo.getId());
				logger.debug("descrição: " + vo.getDescricao());
			}
			
			//-----------------------------------------------------
			// Executa a ordenacao das cidades que vieram do BD
			//-----------------------------------------------------
			Collections.sort(retorno, new Comparator<DestinoVO>() {
				public int compare(DestinoVO arg0, DestinoVO arg1) {
					// TODO Auto-generated method stub
					return arg0.getDescricao().compareTo(arg1.getDescricao());
				}
			}
			);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}

	public CidadeUFVO ProcurarUFCidadeItem(long idUfCidadeItem)
			throws AlugueRelaxeFrontException {
		CidadeUFVO retorno = null;
		try {
			UFService<?> as = new UFServiceImpl();
			CidadeUFDTO dto = as.ProcurarUFCidadeItem(idUfCidadeItem);
			retorno = (new DTOCidadeUFImpl()).getDataTransferObject(dto);
			
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}

}
