package br.com.jcv.aluguerelaxe.server;

import java.text.ParseException;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.administracao.console.avisos.AvisosRPC;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatVO;
import br.com.jcv.backend.chat.ChatDTO;
import br.com.jcv.backend.chat.ChatServiceImpl;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ChatService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AvisosRPCImpl extends RemoteServiceServlet implements AvisosRPC {

	private static final long serialVersionUID = -6079781449905358421L;

	public ChatVO getChatAtivo(String sessao) throws AlugueRelaxeFrontException {
		ChatVO vo = null;
		try {
			ChatService<ChatDTO> ds = new ChatServiceImpl();
			ChatDTO dto = ds.getChatAtivo(sessao);
			if (dto != null){
				vo = getVO(dto);
			}
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem());
		}
		
		return vo;
	}

	public ChatVO getChatPrivado(String sessao, long idCliente)
			throws AlugueRelaxeFrontException {
		ChatVO vo = null;
		try {
			ChatService<ChatDTO> ds = new ChatServiceImpl();
			ChatDTO dto = ds.getChatPrivado(sessao, idCliente);
			if (dto != null){
				vo = getVO(dto);
			}
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem());
		}
		
		return vo;
	}
	
	private ChatVO getVO(ChatDTO dto) {
		ChatVO vo = new ChatVO();
		vo.chat = dto.chat;
		vo.urlFoto = dto.urlImagem;
		vo.titulo = dto.titulo;
		
		DateManagerBase dt = DateManagerBase.getDateManagerInstance(dto.dataCadastro);
		try {
			vo.dataPost = dt.getDateCustom("dd/MM/yyyy HH:mm");
		} catch (ParseException e) {
			vo.dataPost = dt.getDate().toString();
		}
		return vo;
	}

}
