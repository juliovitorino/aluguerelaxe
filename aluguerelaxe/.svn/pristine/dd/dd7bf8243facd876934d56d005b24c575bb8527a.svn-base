package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.charter.CharterVO;
import br.com.jcv.aluguerelaxe.client.uf.UFRPC;
import br.com.jcv.aluguerelaxe.client.vo.CidadeVO;
import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.UFService;
import br.com.jcv.backend.uf.UFServiceImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UFRPCImpl extends RemoteServiceServlet implements UFRPC {

	private static final long serialVersionUID = 8962197767715041094L;

	@SuppressWarnings("unchecked")
	public List<CidadeVO> listarCidadesDaUF(String uf) {
		List<CidadeVO> lstvo = null;
		try {
			
			UFService ufs = new UFServiceImpl();
			List<CidadeDTO> lstdto = ufs.listarCidadesDaUF(uf);
			
			if (lstdto.size() > 0) {
				lstvo = new ArrayList<CidadeVO>();
				for (CidadeDTO cidadedto : lstdto) {
					CidadeVO cidadevo = new CidadeVO();
					cidadevo.id = cidadedto.id;
					cidadevo.nome = cidadedto.nome;
					lstvo.add(cidadevo);
				}
			}
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
		
		return lstvo;

	}

	@SuppressWarnings("unchecked")
	public List<CidadeVO> listarCidadesDaUFComImoveis(String uf) {
		List<CidadeVO> lstvo = null;
		try {
			
			UFService ufs = new UFServiceImpl();
			List<CidadeDTO> lstdto = ufs.listarCidadesDaUFComImoveis(uf);
			
			if (lstdto.size() > 0) {
				lstvo = new ArrayList<CidadeVO>();
				for (CidadeDTO cidadedto : lstdto) {
					CidadeVO cidadevo = new CidadeVO();
					cidadevo.id = cidadedto.id;
					cidadevo.nome = cidadedto.nome;
					lstvo.add(cidadevo);
				}
			}
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
		
		return lstvo;

	}


	public CharterVO charterSumarizadoImoveisUF() {
		CharterVO cht = null;
		try {
			
			cht = new CharterVO();
			UFService ufs = new UFServiceImpl();
			cht.url = ufs.charterSumarizadoImoveisUF();
			
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
		
		return cht;

	}

}

