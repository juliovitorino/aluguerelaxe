package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeRPC;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeVO;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOModoPublicidadeImpl;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ModoPublicidadeService;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeDTO;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeServiceImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ModoPublicidadeRPCImpl extends RemoteServiceServlet implements
		ModoPublicidadeRPC {

	private static final long serialVersionUID = -4987529405569631616L;

	public List<ModoPublicidadeVO> listarModoPublicidade() {
		
		List<ModoPublicidadeVO> lstretorno = null;
		
		try {
			ModoPublicidadeService<ModoPublicidadeDTO> mps = new ModoPublicidadeServiceImpl();
			List<? extends ModoPublicidadeDTO> lst = mps.listarRegistros(); 
			
			if (lst != null){
				lstretorno = new ArrayList<ModoPublicidadeVO>();
				for (ModoPublicidadeDTO mpdto: lst){
					ModoPublicidadeVO mpvo = (new DTOModoPublicidadeImpl()).getDataTransferObject(mpdto);
					lstretorno.add(mpvo);
				}
			}
		} catch (AlugueRelaxeException e) {
			// TODO verificar
		}
		return lstretorno;
	}

}
