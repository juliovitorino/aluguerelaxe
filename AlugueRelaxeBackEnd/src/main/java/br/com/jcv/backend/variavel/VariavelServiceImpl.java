package br.com.jcv.backend.variavel;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.BusinessObject;
import br.com.jcv.backend.interfaces.services.VariavelService;
import br.com.jcv.backend.mensagem.MensagemCache;

public class VariavelServiceImpl implements VariavelService<VariavelDTO>{
	
	private static Logger logger = Logger.getLogger(VariavelServiceImpl.class);

	public VariavelDTO atualizarRegistro(VariavelDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		VariavelDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			BusinessObject<VariavelDTO> bo = new VariavelBusinessImpl();
			dtoretorno = bo.atualizar(daoFactory, dto);
			
			daoFactory.commit();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	public VariavelDTO excluirRegistro(VariavelDTO dto) throws AlugueRelaxeException {
		VariavelDTO dtoretorno = null;
		DAOFactory daoFactory =  null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<VariavelDTO> bo = new VariavelBusinessImpl();
			bo.excluir(daoFactory, dto);
			daoFactory.commit();
			dtoretorno = new VariavelDTO();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	public VariavelDTO gravarRegistro(VariavelDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		VariavelDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<VariavelDTO> bo = new VariavelBusinessImpl();
			dtoretorno = bo.incluir(daoFactory, dto);
			daoFactory.commit();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(dtoretorno.msgcode);
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);
		} catch (AlugueRelaxeException are) {
			throw (are);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e); 
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;	
	}

	public List<VariavelDTO> listarRegistros() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<VariavelDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<VariavelDTO> bo = new VariavelBusinessImpl();
			list = bo.buscarTodas(daoFactory);
			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return list;
	}

	public VariavelDTO pesquisarRegistro(VariavelDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		VariavelDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<VariavelDTO> bo = new VariavelBusinessImpl();
			VariavelDTO dtoPesquisa = bo.procurar(daoFactory, dto);
			
			dtoretorno = new VariavelDTO();
			dtoretorno.setId(dtoPesquisa.getId());
			dtoretorno.setDescricao(dtoPesquisa.getDescricao());
			dtoretorno.setValor(dtoPesquisa.getValor());
			
			// Apresenta os dados no console
			logger.debug("Dados localizados");
			logger.debug("código --> " + dtoPesquisa.getId());
			logger.debug("Descricao --> " + dtoPesquisa.getDescricao());
			logger.debug("Valor --> " + dtoPesquisa.getValor());
			
			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

}
