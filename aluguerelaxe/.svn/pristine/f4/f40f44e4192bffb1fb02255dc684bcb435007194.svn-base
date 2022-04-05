
package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.caracteristica.CaracteristicaRPC;
import br.com.jcv.aluguerelaxe.client.caracteristica.CaracteristicaVO;
import br.com.jcv.backend.caracteristicas.CaracteristicaDTO;
import br.com.jcv.backend.caracteristicas.CaracteristicaServiceImpl;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.CaracteristicaService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CaracteristicaRPCImpl extends RemoteServiceServlet implements CaracteristicaRPC {

	private static final long serialVersionUID = -4061553565879366875L;

	public List<CaracteristicaVO> listarCaracteristicas() throws AlugueRelaxeFrontException {
		List<CaracteristicaVO> lst = null;
		try {
			
			CaracteristicaService<CaracteristicaDTO> cs = new CaracteristicaServiceImpl();
			List<CaracteristicaDTO> lstdto = cs.listarRegistros();
			
			if (lstdto != null) {
				if (lstdto.size() > 0) {
					lst = new ArrayList<CaracteristicaVO>();
					for (CaracteristicaDTO dto : lstdto) {
						
						CaracteristicaVO vo = new CaracteristicaVO();
						vo.id = dto.id;
						vo.nome = dto.nome;
						vo.descricaoAnuncio = dto.descricaoAnuncio;
						lst.add(vo);
					}
				}
			}
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			throw new AlugueRelaxeFrontException(e.getMensagem());
		}
		return lst;
	}
	

}

