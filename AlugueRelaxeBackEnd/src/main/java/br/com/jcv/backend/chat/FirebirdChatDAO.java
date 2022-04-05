package br.com.jcv.backend.chat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.ChatDAO;

public class FirebirdChatDAO implements ChatDAO<ChatDTO> {
	
	private static final String INS_BASICO =	"insert into CHAT ("+
	"CHAT_CD_CHAT, " +
	"CHAT_IN_SESSAO, " +
	"CHAT_TX_TITULO, " +
	"CHAT_TX_URL_IMAGEM, " +
	"CHAT_TX_CHAT, " +
	"CHAT_DT_VALIDADE_INICIAL, " + 
	"CHAT_DT_VALIDADE_FINAL, " +
	"CLIE_CD_CLIENTE " +
	") values (?,?,?,?,?,?,?,?)";
	
	private static final String SQL_BASICO =	"select " +
	"CHAT_CD_CHAT, " +
	"CHAT_IN_STATUS, " +
	"CHAT_IN_SESSAO, " +
	"CHAT_TX_CHAT, " +
	"CHAT_DT_CADASTRO, " +
	"CLIE_CD_CLIENTE, " +
	"CHAT_TX_TITULO, " +
	"CHAT_TX_URL_IMAGEM ";
	
	private static final String SQL_CHAT_BUSCA_PK = SQL_BASICO +
	"from CHAT " +
	"where CHAT_CD_CHAT = ?";
	
	private static final String SQL_BASICO_ULTIMO_CHAT = SQL_BASICO +
	"from CHAT " +
	"where   CHAT_CD_CHAT = ( " +
	                            "select MAX(c.CHAT_CD_CHAT) " +
	                            "from chat c " +
	                            "where c.CHAT_IN_SESSAO = ? " +
	                            "and   c.CHAT_IN_STATUS = ? " +
	                            "and   ? BETWEEN c.CHAT_DT_VALIDADE_INICIAL AND c.CHAT_DT_VALIDADE_FINAL " +
	                        ")";
	
	private static final String SQL_BASICO_ULTIMO_CHAT_PRIVADO = SQL_BASICO +
	"from CHAT " +
	"where   CHAT_CD_CHAT = ( " +
	                            "select MAX(c.CHAT_CD_CHAT) " +
	                            "from chat c " +
	                            "where c.CHAT_IN_SESSAO = ? " +
	                            "and   c.CHAT_IN_STATUS = ? " +
	                            "and   c.CLIE_CD_CLIENTE IS NULL " +
	                            "and   ? BETWEEN c.CHAT_DT_VALIDADE_INICIAL AND c.CHAT_DT_VALIDADE_FINAL " +
	                        ")";
	
	private static final String SQL_BASICO_ULTIMO_CHAT_PRIVADO_CLIENTE = SQL_BASICO +
	"from CHAT " +
	"where   CHAT_CD_CHAT = ( " +
	                            "select MAX(c.CHAT_CD_CHAT) " +
	                            "from chat c " +
	                            "where c.CHAT_IN_SESSAO = ? " +
	                            "and   c.CHAT_IN_STATUS = ? " +
	                            "and   c.CLIE_CD_CLIENTE = ? " +
	                            "and   ? BETWEEN c.CHAT_DT_VALIDADE_INICIAL AND c.CHAT_DT_VALIDADE_FINAL " +
	                        ")";
	
	
	private DAOFactory daofactory = null;
	
	public FirebirdChatDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public ChatDTO insert(ChatDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_BASICO);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setString(i++, dto.sessao);
		qry.setString(i++, dto.titulo);
		qry.setString(i++, dto.urlImagem);
		qry.setString(i++, dto.chat);
		qry.setDate(i++, dto.dataInicio);
		qry.setDate(i++, dto.dataFinal);
		qry.setLong(i++, dto.idCliente);
		int n = qry.executeUpdate();
		return dto;
	}

	public ChatDTO update(ChatDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(ChatDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub

	}

	public ChatDTO load(ChatDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ChatDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ChatDTO> list(int pagina) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ChatDTO load(String sessao, String status)
			throws AlugueRelaxeException {
		ChatDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BASICO_ULTIMO_CHAT);
		qry.setString(0, sessao);
		qry.setString(1, status);
		qry.setDate(2, new Date());
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;	
	}

	public ChatDTO load(String sessao, long idCliente, String status)
			throws AlugueRelaxeException {
		ChatDTO registroDto = null;
		Session session = daofactory.getSession();
		
		
		Query qry = null;
		if (idCliente == -1) {
			qry = session.createSQLQuery(SQL_BASICO_ULTIMO_CHAT_PRIVADO);
			qry.setString(0, sessao);
			qry.setString(1, status);
			qry.setDate(2, new Date());
		} else {
			qry = session.createSQLQuery(SQL_BASICO_ULTIMO_CHAT_PRIVADO_CLIENTE);
			qry.setString(0, sessao);
			qry.setString(1, status);
			qry.setLong(2, idCliente);
			qry.setDate(3, new Date());
		}
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;	
	}


	private ChatDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		ChatDTO dto = new ChatDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.status = (registro[++j] == null ? null : registro[j].toString());
		dto.sessao = (registro[++j] == null ? null : registro[j].toString());
		dto.chat = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.idCliente = (registro[++j] == null ? 0 :  Long.valueOf(registro[j].toString()));
		dto.titulo = (registro[++j] == null ? null : registro[j].toString());
		dto.urlImagem = (registro[++j] == null ? null : registro[j].toString());
		return dto;
	}
	

}
