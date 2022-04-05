package br.com.jcv.backend.contatoanunciante;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.business.ContatoAnuncianteBusiness;
import br.com.jcv.backend.interfaces.services.ContatoAnuncianteService;
import br.com.jcv.backend.interfaces.services.ImovelService;

public class ContatoAnuncianteServiceImpl implements ContatoAnuncianteService {

	public ContatoClienteDTO pesquisarProximoOferecimento()
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ContatoClienteDTO dto = null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ContatoAnuncianteBusiness ib = new ContatoAnuncianteBusinessImpl();
			dto = ib.procurarProximoOferecimento(daoFactory);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

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
		return dto;
	}

	public List<ContatoClienteDTO> listarContatosAnuncianteStatus(String status) throws AlugueRelaxeException {
		List<ContatoClienteDTO> lst = null;
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			ContatoAnuncianteBusiness fsb = new ContatoAnuncianteBusinessImpl();
			lst = fsb.listarContatosAnuncianteStatus(daoFactory, status);
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


	public List<ContatoClienteDTO> listarContatosPendentesAlerta24h() throws AlugueRelaxeException {
		List<ContatoClienteDTO> lst = null;
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			ContatoAnuncianteBusiness fsb = new ContatoAnuncianteBusinessImpl();
			lst = fsb.listarContatosPendentesAlerta24h(daoFactory);
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

	public void atualizarFilaAlerta24h(String hashID, String status) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			ContatoAnuncianteBusiness fsb = new ContatoAnuncianteBusinessImpl();
			fsb.atualizarFilaAlerta24h(daoFactory, hashID, status);
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

	public ContatoClienteDTO gravarRegistro(ContatoClienteDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ContatoClienteDTO excluirRegistro(ContatoClienteDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ContatoClienteDTO atualizarRegistro(ContatoClienteDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ContatoClienteDTO pesquisarRegistro(ContatoClienteDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ContatoClienteDTO retorno = null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ContatoAnuncianteBusiness ib = new ContatoAnuncianteBusinessImpl();
			retorno = ib.procurar(daoFactory, dto);
			daoFactory.commit();
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			retorno.ifcdto = is.pesquisarFichaCompletaImovel(retorno.idImovel);
			
		} catch (HibernateException he) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

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


	public ContatoClienteDTO pesquisarRegistro(String idhash)
			throws AlugueRelaxeException {
		ContatoClienteDTO ccdto = new ContatoClienteDTO();
		ccdto.id = idhash;
		return this.pesquisarRegistro(ccdto);
	}

	
	public List<? extends ContatoClienteDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void atualizarOferecimento(String id) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ContatoAnuncianteBusiness ib = new ContatoAnuncianteBusinessImpl();
			ib.atualizarOferecimento(daoFactory, id);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

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