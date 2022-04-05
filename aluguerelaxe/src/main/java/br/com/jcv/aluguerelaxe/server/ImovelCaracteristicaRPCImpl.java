
package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.caracteristica.CaracteristicaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.caracteristica.ImovelCaracteristicaRPC;
import br.com.jcv.aluguerelaxe.client.imovel.caracteristica.ImovelCaracteristicaVO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelBaseDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * <h2>ImovelCaracteristicaRPCImpl</h2>
 *
 *<p>Remote Procedure Call para ImovelCaracteristica</p>
 * @author Julio Vitorino
 *
 */
public class ImovelCaracteristicaRPCImpl extends RemoteServiceServlet implements ImovelCaracteristicaRPC {

	private static final long serialVersionUID = 4752849095955993360L;

	@SuppressWarnings("unchecked")
	public List<ImovelCaracteristicaVO> listarCaracteristicas(long imovelId, String tipo) throws AlugueRelaxeFrontException {
		
		List<ImovelCaracteristicaVO> lst = null;
		try {
			
			ImovelService ics = new ImovelServiceImpl<ImovelBaseDTO>();
			List<ImovelCaracteristicaDTO> lstdto = ics.listarCaracteristicas(imovelId, tipo);
			
			if (lstdto != null){
				if (lstdto.size() > 0) {
					lst = new ArrayList<ImovelCaracteristicaVO>();
					for (ImovelCaracteristicaDTO dto : lstdto) {
						ImovelCaracteristicaVO vo = new ImovelCaracteristicaVO();
						vo.id = dto.id;
						vo.imovel = new ImovelVO();
						vo.imovel.id = imovelId;
						vo.caracteristica = new CaracteristicaVO();
						vo.caracteristica.id = dto.caracteristica.id;
						vo.caracteristica.nome = dto.caracteristica.nome;
						vo.caracteristica.descricaoAnuncio = dto.caracteristica.descricaoAnuncio;
						lst.add(vo);
					}
				}
			}
		} catch (AlugueRelaxeException e) {
			throw new AlugueRelaxeFrontException(e.getMensagem());
		}
		return lst;
	}

}

