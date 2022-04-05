package br.com.jcv.backend.mensagem;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.MensagemDAO;

public class FirebirdMensagemDAO implements MensagemDAO<MensagemDTO> {
	
	private static final String SQL_BASICO =	"select " +
	"MENS_CD_MENSAGEM," +
	"MENS_TX_MENSAGEM ";
	
	private static final String SQL_MSG =	SQL_BASICO +
	" from MENSAGEM " +
	" where MENS_CD_MENSAGEM like ?";
	
	
	private DAOFactory daofactory = null;
	
	public FirebirdMensagemDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public void delete(MensagemDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		session.delete(dto);
	}

	public MensagemDTO insert(MensagemDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		session.save(dto);
		return dto;
	}

	public MensagemDTO update(MensagemDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		session.update(dto);
		return dto;
	}

	public MensagemDTO load(MensagemDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		String id = dto.getId();
		MensagemDTO dtoMensagem = (MensagemDTO) session.load(MensagemDTO.class, id);
		return dtoMensagem;
		
	}


	public List<MensagemDTO> list() throws AlugueRelaxeException {
		return this.list("MSG-%");
	}

	public List<MensagemDTO> list(String prefixo) throws AlugueRelaxeException {
		List<MensagemDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_MSG);
		int j = 0;
		qry.setString(j++, prefixo);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<MensagemDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				MensagemDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}
	
	public List<MensagemDTO> list(int start) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query q = session.createQuery("from MensagemDTO");
		q.setFirstResult(start-1);
		q.setMaxResults(Constantes.MAX_RETORNO_REGISTRO);
		List<MensagemDTO> result = q.list();
		return result;
	}

	private MensagemDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		MensagemDTO dto = new MensagemDTO();
		int j = -1;
		dto.id = (registro[++j] == null ? null : registro[j].toString());
		dto.mensagem = (registro[++j] == null ? null : registro[j].toString());
		return dto;
	}

}