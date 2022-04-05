package br.com.jcv.backend.tipoalerta;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.TipoAlertaDAO;

public class FirebirdTipoAlertaDAO implements TipoAlertaDAO {

	private static final String SQL_BASICO =	"select " +
												"TIAL_ID, " +
												"TIAL_NM_TIPO_ALERTA, " +
												"TIAL_NM_WORKER, " +
												"TIAL_IN_STATUS, " +
												"TIAL_DT_CADASTRO " ;
	
	private static final String SQL_PLANO_BUSCA_PK = SQL_BASICO +
		"from TIPO_ALERTA24H " +
		"where TIAL_ID = ?";
	
	private static final String SQL_FULL_STATUS = SQL_BASICO +
	"from TIPO_ALERTA24H " +
	"where TIAL_IN_STATUS = ?";

	private DAOFactory daofactory = null;
	
	public FirebirdTipoAlertaDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}


	public List<TipoAlertaDTO> list() throws AlugueRelaxeException {
		List<TipoAlertaDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_FULL_STATUS);
		qry.setString(0, "A");
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<TipoAlertaDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				TipoAlertaDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}

	public List<TipoAlertaDTO> list(String status) throws AlugueRelaxeException {
		List<TipoAlertaDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_FULL_STATUS);
		qry.setString(0, status);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<TipoAlertaDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				TipoAlertaDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}

	
	public TipoAlertaDTO load(TipoAlertaDTO dto) throws AlugueRelaxeException {
		TipoAlertaDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_PLANO_BUSCA_PK);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	private TipoAlertaDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		TipoAlertaDTO dto = new TipoAlertaDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.nome = (registro[++j] == null ? null : registro[j].toString());
		dto.worker = (registro[++j] == null ? null : registro[j].toString());
		dto.status = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		return dto;
	}


	public TipoAlertaDTO insert(TipoAlertaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


	public TipoAlertaDTO update(TipoAlertaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


	public void delete(TipoAlertaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}


	public List<TipoAlertaDTO> list(int pagina) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


}

