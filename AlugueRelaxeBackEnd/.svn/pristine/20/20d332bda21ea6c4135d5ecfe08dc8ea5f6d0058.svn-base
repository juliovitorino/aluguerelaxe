package br.com.jcv.backend.variavel;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.VariavelDAO;

public class FirebirdVariavelDAO implements VariavelDAO<VariavelDTO> {
	
	private DAOFactory daofactory = null;
	
	public FirebirdVariavelDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}
	public void delete(VariavelDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		session.delete(dto);
	}

	public VariavelDTO insert(VariavelDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		session.save(dto);
		return dto;
	}

	@SuppressWarnings("unchecked")
	public List<VariavelDTO> list() throws AlugueRelaxeException {
		Session session =  daofactory.getSession();
		List<VariavelDTO> result = session.createQuery("from VariavelDTO").list();
		return result;	
	}
	
	@SuppressWarnings("unchecked")
	public List<VariavelDTO> list(int start) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query q = session.createQuery("from VariavelDTO");
		q.setFirstResult(start-1);
		q.setMaxResults(Constantes.MAX_RETORNO_REGISTRO);
		List<VariavelDTO> result = q.list();
		return result;
	}

	public VariavelDTO load(VariavelDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		String id = dto.getId();
		VariavelDTO dtoMensagem = (VariavelDTO) session.load(VariavelDTO.class, id);
		return dtoMensagem;
	}

	public VariavelDTO update(VariavelDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		session.update(dto);
		return dto;
	}

}
