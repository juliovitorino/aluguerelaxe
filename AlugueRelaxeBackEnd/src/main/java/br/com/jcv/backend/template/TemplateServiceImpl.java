package br.com.jcv.backend.template;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.TemplateBusiness;
import br.com.jcv.backend.interfaces.services.TemplateService;

public class TemplateServiceImpl implements TemplateService {

	public List<TemplateDTO> listarRegistros(String status)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<TemplateDTO> lst = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			// Cria um registro na fila
			TemplateBusiness ab = new TemplateBusinessImpl();
			lst = ab.buscarTodas(daoFactory, status);
			
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

	public TemplateDTO gravarRegistro(TemplateDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public TemplateDTO excluirRegistro(TemplateDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public TemplateDTO atualizarRegistro(TemplateDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public TemplateDTO pesquisarRegistro(TemplateDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends TemplateDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
}