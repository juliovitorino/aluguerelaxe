package br.com.jcv.backend.template;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.TemplateDAO;

public class FirebirdTemplateDAO implements TemplateDAO {

	private static final String SQL_BASICO =	"select " +
												"TEMP_ID, " +
												"TEMP_TX_DESCRICAO, " +
												"TEMP_TX_CLASSE, " +
												"TEMP_TX_PATH, " +
												"TEMP_TX_TEMPLATE, " +
												"TEMP_IN_STATUS, " +
												"TEMP_DT_CADASTRO " ;
	
	private static final String SQL_BUSCA_PK = SQL_BASICO +
		"from TEMPLATE " +
		"where TEMP_ID = ?";

	private static final String SQL_FULL_STATUS = SQL_BASICO +
	"from TEMPLATE " +
	"where TEMP_IN_STATUS = ?";		
		
		
	private DAOFactory daofactory = null;
	
	public FirebirdTemplateDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public List<TemplateDTO> list(String status) throws AlugueRelaxeException {
		List<TemplateDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_FULL_STATUS);
		qry.setString(0, status);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<TemplateDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				TemplateDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}

	public TemplateDTO load(TemplateDTO dto) throws AlugueRelaxeException {
		TemplateDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCA_PK);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	private TemplateDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		TemplateDTO dto = new TemplateDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.descricao = (registro[++j] == null ? null : registro[j].toString());
		dto.classe = (registro[++j] == null ? null : registro[j].toString());
		dto.path = (registro[++j] == null ? null : registro[j].toString());
		dto.template = (registro[++j] == null ? null : registro[j].toString());
		dto.status = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		return dto;
	}

	public TemplateDTO insert(TemplateDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public TemplateDTO update(TemplateDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(TemplateDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<TemplateDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TemplateDTO> list(int pagina) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
}
