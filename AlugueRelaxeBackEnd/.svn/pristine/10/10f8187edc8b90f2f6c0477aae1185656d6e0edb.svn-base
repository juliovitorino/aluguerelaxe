package br.com.jcv.backend.mensagem;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.BusinessObject;
import br.com.jcv.backend.interfaces.business.MensagemBusiness;
import br.com.jcv.backend.interfaces.services.MensagemService;

public class MensagemServiceImpl implements MensagemService<MensagemDTO> {

	private static Logger logger = Logger.getLogger(br.com.jcv.backend.mensagem.MensagemServiceImpl.class);

	public MensagemServiceImpl() throws AlugueRelaxeException {
		super();
		// TODO Auto-generated constructor stub
	}

	public MensagemDTO atualizarRegistro(MensagemDTO dto) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		MensagemDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			BusinessObject<MensagemDTO> bo = new MensagemBusinessImpl();
			dtoretorno = bo.atualizar(daoFactory, dto);
			
			daoFactory.commit();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			logger.info(dtoretorno.msgcode + "-" + dtoretorno.msgcodeString);

			this.notificarMensagemCache();

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

	public MensagemDTO excluirRegistro(MensagemDTO dto) throws AlugueRelaxeException {
		MensagemDTO dtoretorno = null;
		DAOFactory daoFactory =  null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<MensagemDTO> bo = new MensagemBusinessImpl();
			bo.excluir(daoFactory, dto);
			daoFactory.commit();
			dtoretorno = new MensagemDTO();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			logger.info(dtoretorno.msgcode + "-" + dtoretorno.msgcodeString);
			this.notificarMensagemCache();
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

	public MensagemDTO gravarRegistro(MensagemDTO dto) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		MensagemDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<MensagemDTO> bo = new MensagemBusinessImpl();
			dtoretorno = bo.incluir(daoFactory, dto);
			daoFactory.commit();
			this.notificarMensagemCache();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(dtoretorno.msgcode);
			logger.info(dtoretorno.msgcode + "-" + dtoretorno.msgcodeString);
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are){
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

	public MensagemDTO pesquisarRegistro(MensagemDTO dto) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		MensagemDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<MensagemDTO> bo = new MensagemBusinessImpl();
			MensagemDTO dtoPesquisa = bo.procurar(daoFactory, dto);
			
			dtoretorno = new MensagemDTO();
			dtoretorno.setId(dtoPesquisa.getId());
			dtoretorno.setMensagem(dtoPesquisa.getMensagem());
			
			// Apresenta os dados no console
			logger.debug("Dados localizados");
			logger.debug("código --> " + dtoPesquisa.getId());
			logger.debug("Mensagem --> " + dtoPesquisa.getMensagem());
			
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
	
	private void notificarMensagemCache() throws AlugueRelaxeException {
		MensagemObserver mo = new MensagemObserver();
		mo.addMensagemListener(MensagemCache.getInstance());
		mo.notificaAssinantes();
	}
/*
	public List<MensagemDTO> listarRegistros() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<MensagemDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<MensagemDTO> bo = new MensagemBusinessImpl();
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
*/

	public List<MensagemDTO> listarRegistros() throws AlugueRelaxeException {
		return this.listarRegistros("MSG-%");
	}

	public List<MensagemDTO> listarRegistros(String sufixo) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<MensagemDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			MensagemBusiness<MensagemDTO> bo = new MensagemBusinessImpl();
			list = bo.buscarTodas(daoFactory, sufixo);
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


}
