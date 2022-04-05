package br.com.jcv.aluguerelaxe.server;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.enquete.EnqueteModoPublicidadeRPC;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ModoPublicidadeVisitasService;
import br.com.jcv.backend.modopublicidadevisitas.ModoPublicidadeVisitasDTO;
import br.com.jcv.backend.modopublicidadevisitas.ModoPublicidadeVisitasServiceImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EnqueteModoPublicidadeRPCImpl extends RemoteServiceServlet
		implements EnqueteModoPublicidadeRPC {

	private static final long serialVersionUID = 3357366244735581333L;

	public VOPadrao incrementarModoPublicidadeVisita(long idModoPublicidade)
			throws AlugueRelaxeFrontException {
		VOPadrao vo = null;
		try {
			ModoPublicidadeVisitasService<ModoPublicidadeVisitasDTO> mpvs = new ModoPublicidadeVisitasServiceImpl();
			DTOPadrao dto = mpvs.incrementarModoPublicidadeVisita(idModoPublicidade);
			vo = new VOPadrao();
			vo.msgcode = dto.msgcode;
			vo.msgcodeString = dto.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return vo;
	}

}
