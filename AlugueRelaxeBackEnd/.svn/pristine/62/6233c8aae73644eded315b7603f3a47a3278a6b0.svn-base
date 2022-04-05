package br.com.jcv.backend.chat;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.ChatBusiness;
import br.com.jcv.backend.interfacesdao.ChatDAO;

public class ChatBusinessImpl implements ChatBusiness<ChatDTO> {

	public ChatDTO procurar(DAOFactory daofactory, String sessao)
			throws AlugueRelaxeException {
		ChatDAO<ChatDTO> dao = daofactory.getChatDAO(daofactory);
		return dao.load(sessao, "A");
	}

	public ChatDTO procurar(DAOFactory daofactory, String sessao, long idCliente)
		throws AlugueRelaxeException {
		ChatDAO<ChatDTO> dao = daofactory.getChatDAO(daofactory);
		return dao.load(sessao, idCliente, "A");
	}


	public void setDados(ChatDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub

	}

	public ChatDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(ChatDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub

	}

	public ChatDTO incluir(DAOFactory daofactory, ChatDTO dto)
			throws AlugueRelaxeException {
		ChatDAO<ChatDTO> dao = daofactory.getChatDAO(daofactory);
		dto.id = daofactory.getNextSequence(daofactory, "SEQ_CHAT_CD_CHAT");
		return dao.insert(dto);
	}

	public ChatDTO atualizar(DAOFactory daofactory, ChatDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, ChatDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub

	}

	public ChatDTO procurar(DAOFactory daofactory, ChatDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ChatDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ChatDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

}
