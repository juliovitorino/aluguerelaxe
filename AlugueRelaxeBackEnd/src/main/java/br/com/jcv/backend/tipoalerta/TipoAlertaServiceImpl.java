package br.com.jcv.backend.tipoalerta;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.TipoAlertaBusiness;
import br.com.jcv.backend.interfaces.services.TipoAlertaService;

public class TipoAlertaServiceImpl implements TipoAlertaService {
	public List<TipoAlertaDTO> listarTiposAlerta() throws AlugueRelaxeException {
		List<TipoAlertaDTO> lst = null;
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			TipoAlertaBusiness fsb = new TipoAlertaBusinessImpl();
			lst = fsb.listarTiposAlerta(daoFactory, "A");
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

	public TipoAlertaDTO gravarRegistro(TipoAlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public TipoAlertaDTO excluirRegistro(TipoAlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public TipoAlertaDTO atualizarRegistro(TipoAlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public TipoAlertaDTO pesquisarRegistro(TipoAlertaDTO dto)
			throws AlugueRelaxeException {
		TipoAlertaDTO retorno = null;
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			TipoAlertaBusiness fsb = new TipoAlertaBusinessImpl();
			retorno = fsb.procurar(daoFactory, dto);
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
		return retorno;
	}

	public List<? extends TipoAlertaDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

}