package br.com.jcv.backend.alerta24h;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.AlertaBusiness;
import br.com.jcv.backend.interfaces.services.AlertaService;

public class AlertaServiceImpl implements AlertaService {

	/**
	 * <h2>EnfileirarAlerta</h2>
	 * <p>Enfileirar os alertas a serem emitidos</p>
	 * @param AlertaDTO
	 * @throws AlugueRelaxeException
	 */
	public void enfileirarAlerta(AlertaDTO dto) throws AlugueRelaxeException{
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			// Cria um registro na fila
			AlertaBusiness ab = new AlertaBusinessImpl();
			ab.enfileirarAlerta(daoFactory, dto);
			
			daoFactory.commit();
			
		} catch (HibernateException he) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
	}


	/**
	 * <h2>EmitirAlerta</h2>
	 * <p>Emitir e enviar os alertas na fila</p>
	 * @throws AlugueRelaxeException
	 */
	public void emitirAlerta() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			// Cria a instancia do class que vai fazer o trabalho
			// de acordo com o tipo do altera (factory)
			
			// Marca "S" item da fila de alerta como emitido

			daoFactory.commit();
			
		} catch (HibernateException he) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
	}


	public AlertaDTO gravarRegistro(AlertaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


	public AlertaDTO excluirRegistro(AlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


	public AlertaDTO atualizarRegistro(AlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


	public AlertaDTO pesquisarRegistro(AlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<? extends AlertaDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<AlertaDTO> listarAlertasPendentes()
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<AlertaDTO> lst = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			// Cria um registro na fila
			AlertaBusiness ab = new AlertaBusinessImpl();
			lst = ab.listarAlertasPendentes(daoFactory);
			
			daoFactory.commit();
			
		} catch (HibernateException he) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
		return lst;
	}


	public void atualizarStatusEmitido(AlertaDTO dto, String status) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			// Cria um registro na fila
			AlertaBusiness ab = new AlertaBusinessImpl();
			ab.atualizarStatusEmitido(daoFactory, dto, status);
			
			daoFactory.commit();
			
		} catch (HibernateException he) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
	}
	
}