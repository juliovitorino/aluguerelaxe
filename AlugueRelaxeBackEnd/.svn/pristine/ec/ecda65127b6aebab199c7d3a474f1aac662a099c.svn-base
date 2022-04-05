package br.com.jcv.backend.local;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.LocalBusiness;
import br.com.jcv.backend.interfaces.services.LocalService;

public class LocalServicesImpl implements LocalService<LocalItemDTO> {

	/**
	 * <h2>Atributo logger</h2>
	 * <p>Usado para obter uma instância de <code>Logger</code>
	 */
	private static Logger logger = Logger.getLogger(LocalServicesImpl.class); 

	public LocalItemDTO gravarRegistro(LocalItemDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalItemDTO excluirRegistro(LocalItemDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalItemDTO atualizarRegistro(LocalItemDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalItemDTO pesquisarRegistro(LocalItemDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends LocalItemDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LocalItemDTO> listarLocaisUFCidade(long idUfCidadeItem,
			long[] idLocais) throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<LocalItemDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			LocalBusiness<LocalItemDTO> bo = new LocalBusinessImpl();
			list = bo.listarLocaisUFCidade(daoFactory, idUfCidadeItem, idLocais);
			daoFactory.commit();

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
		return list;	
	}

	public List<LocalItemDTO> listarLocaisUFCidadeClassificacao(
			long idUfCidadeItem, long[] idClassificacao)
			throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<LocalItemDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			LocalBusiness<LocalItemDTO> bo = new LocalBusinessImpl();
			list = bo.listarLocaisUFCidadeClassificacao(daoFactory, idUfCidadeItem, idClassificacao);
			daoFactory.commit();

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
		return list;	
	}

	public List<LocalDTO> listarLocal() throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<LocalDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			LocalBusiness<?> bo = new LocalBusinessImpl();
			list = bo.listarLocal(daoFactory);
			daoFactory.commit();

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
		return list;	
	}

	public List<ClassificacaoDTO> listarClassificacaoLocal()
			throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<ClassificacaoDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			LocalBusiness<?> bo = new LocalBusinessImpl();
			list = bo.listarClassificacaoLocal(daoFactory);
			daoFactory.commit();

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
		return list;	
	}

}
