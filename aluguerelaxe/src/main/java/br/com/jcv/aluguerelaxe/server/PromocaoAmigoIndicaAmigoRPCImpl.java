package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.EmailVO;
import br.com.jcv.aluguerelaxe.client.campanhas.amigoindicaamigo.PromocaoAmigoIndicaAmigoRPC;
import br.com.jcv.aluguerelaxe.client.campanhas.amigoindicaamigo.PromocaoAmigoIndicaAmigoVO;
import br.com.jcv.aluguerelaxe.client.campanhas.promocaopadrao.PromocaoPadraoVO;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;
import br.com.jcv.backend.assinantes.AssinantesDTO;
import br.com.jcv.backend.assinantes.AssinantesServiceImpl;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.AssinantesService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PromocaoAmigoIndicaAmigoRPCImpl extends RemoteServiceServlet
		implements PromocaoAmigoIndicaAmigoRPC {

	private static final long serialVersionUID = 7105317835387465840L;

	public VOPadrao realizarInscricao(PromocaoAmigoIndicaAmigoVO vo)
			throws AlugueRelaxeFrontException {
		VOPadrao vopadrao = null;
		try {
			
			// Repassa objeto do frontend para encaminhar ao backend
			AssinantesDTO dto = new AssinantesDTO();
			dto.email = vo.participante.email;
			dto.nome = vo.participante.nome;
			
			List<AssinantesDTO> amigos = new ArrayList<AssinantesDTO>();
			for (EmailVO emailvo : vo.lst){
				AssinantesDTO amigo = new AssinantesDTO();
				amigo.email = emailvo.email;
				amigo.nome = emailvo.nome;
				amigos.add(amigo);
			}

			// Invoca backend
			AssinantesService<AssinantesDTO> asvc = new AssinantesServiceImpl();
			DTOPadrao dtopadrao = asvc.inscricao(dto, amigos);
			
			
			vopadrao = new ClienteVO();
			vopadrao.msgcode = dtopadrao.msgcode;
			vopadrao.msgcodeString = dtopadrao.msgcodeString;
			
			
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		
		return vopadrao;
	}


	public VOPadrao realizarInscricao(PromocaoPadraoVO vo)
			throws AlugueRelaxeFrontException {
		VOPadrao vopadrao = null;
		try {
			
			// Repassa objeto do frontend para encaminhar ao backend
			AssinantesDTO dto = new AssinantesDTO();
			dto.email = vo.participante.email;
			dto.nome = vo.participante.nome;
			
			// Invoca backend
			AssinantesService<AssinantesDTO> asvc = new AssinantesServiceImpl();
			DTOPadrao dtopadrao = asvc.inscricao(dto);
			
			vopadrao = new ClienteVO();
			vopadrao.msgcode = dtopadrao.msgcode;
			vopadrao.msgcodeString = dtopadrao.msgcodeString;
			
			
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		
		return vopadrao;
	}
	
}
