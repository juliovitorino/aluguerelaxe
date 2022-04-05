package br.com.jcv.aluguerelaxe.server;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.faleconosco.FaleConoscoRPC;
import br.com.jcv.aluguerelaxe.client.faleconosco.FaleConoscoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.PortalService;
import br.com.jcv.backend.portal.PortalServiceImpl;
import br.com.jcv.backend.portal.faleconosco.FaleConoscoDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;



/**
 *<h2>FaleConoscoRPCImpl</h2>
 *<p>Implementação das funcionalidades de fale conosco
 *</p>
 */
public class FaleConoscoRPCImpl extends RemoteServiceServlet implements FaleConoscoRPC {

	public VOPadrao execute(FaleConoscoVO vo) throws AlugueRelaxeFrontException {
		VOPadrao voretorno = null;
		try {
			FaleConoscoDTO dto = new FaleConoscoDTO();
			dto.nome = vo.nome;
			dto.email = vo.email;
			dto.assunto = vo.assunto;
			dto.topico = vo.topico;
			dto.mensagem = vo.mensagem;

			PortalService ps = new PortalServiceImpl();
			DTOPadrao dtoretorno = ps.executeFaleConosco(dto);

			voretorno = new VOPadrao();
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;

		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem());
		}
		
		return voretorno;
	
	}
}
