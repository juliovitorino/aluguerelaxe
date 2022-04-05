package br.com.jcv.backend.chat;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.ChatBusiness;
import br.com.jcv.backend.interfaces.services.ChatService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class ChatServiceImpl implements ChatService<ChatDTO> {

	private static Logger logger = Logger.getLogger(ChatServiceImpl.class); 

	public ChatDTO getChatAtivo(String sessao) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ChatDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ChatBusiness<ChatDTO> bo = new ChatBusinessImpl();
			dtoretorno = bo.procurar(daoFactory, sessao);
			daoFactory.commit();
			
			if (dtoretorno == null){
				dtoretorno = new  ChatDTO();
				dtoretorno.id = -1;
				dtoretorno.status = "A";
				dtoretorno.sessao = sessao;
				dtoretorno.chat = VariavelCache.getInstance().getValor(VariavelConstantes.CHAT_NAO_ENCONTRADO_TXT_MSG);
				dtoretorno.dataCadastro = new Timestamp(System.currentTimeMillis());
				dtoretorno.urlImagem = VariavelCache.getInstance().getValor(VariavelConstantes.CHAT_NAO_ENCONTRADO_URL_IMAGE);
				dtoretorno.titulo = "CAIXA DE ENTRADA VAZIA";

			}
			
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);

		} catch (AlugueRelaxeException he) {
			throw he;
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	public ChatDTO getChatPrivado(String sessao, long idCliente)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ChatDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ChatBusiness<ChatDTO> bo = new ChatBusinessImpl();
			dtoretorno = bo.procurar(daoFactory, sessao, idCliente);
			daoFactory.commit();

			if (dtoretorno == null){
				dtoretorno = new  ChatDTO();
				dtoretorno.id = -1;
				dtoretorno.status = "A";
				dtoretorno.sessao = sessao;
				dtoretorno.chat = VariavelCache.getInstance().getValor(VariavelConstantes.CHAT_NAO_ENCONTRADO_TXT_MSG);
				dtoretorno.dataCadastro = new Timestamp(System.currentTimeMillis());
				dtoretorno.urlImagem = VariavelCache.getInstance().getValor(VariavelConstantes.CHAT_NAO_ENCONTRADO_URL_IMAGE);
				dtoretorno.titulo = "CAIXA DE ENTRADA VAZIA";
				dtoretorno.idCliente = idCliente;
			}
			
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);

		} catch (AlugueRelaxeException he) {
			throw he;
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	
	public ChatDTO gravarRegistro(ChatDTO dto) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ChatDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ChatBusiness<ChatDTO> bo = new ChatBusinessImpl();
			dtoretorno = bo.incluir(daoFactory, dto);
			daoFactory.commit();
			
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);

		} catch (AlugueRelaxeException he) {
			throw he;
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	public ChatDTO excluirRegistro(ChatDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ChatDTO atualizarRegistro(ChatDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ChatDTO pesquisarRegistro(ChatDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends ChatDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

}
