package br.com.jcv.backend.filasms;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.FilaSMSDAO;

public class FirebirdFilaSMSDAO implements FilaSMSDAO {
	
	private static final String INS_BASICO = "insert into FILA_SMS (" +
	"FISM_ID," +
	"FISM_TX_CELULAR," +
	"FISM_TX_MSG," +
	"FISM_IN_MODO," +
	"FISM_NR_PRIORIDADE " +
	") VALUES (?,?,?,?,?)";
	
	private static final String SQL_BASICO =	"select " +
												"FISM_ID, " +
												"FISM_TX_CELULAR, " +
												"FISM_TX_MSG, " +
												"FISM_IN_QUEUED, " +
												"FISM_DT_QUEUED, " +
												"FISM_IN_MODO, " +
												"FISM_NR_PRIORIDADE, " +
												"FISM_DT_ENVIO_GATEWAY, " +
												"FISM_DT_CADASTRO " ;
												
	private static final String UPD_STATUS_ENVIO = "update FILA_SMS set " +
		"FISM_IN_QUEUED = ?, " +
		"FISM_DT_QUEUED = CURRENT_TIMESTAMP " +
		"where FISM_ID = ?";

	private static final String UPD_DATA_ENVIO_GATEWAY = "update FILA_SMS set " +
	"FISM_DT_ENVIO_GATEWAY = CURRENT_TIMESTAMP " +
	"where FISM_ID = ?";
											
												
	private static final String SQL_PLANO_BUSCA_PK = SQL_BASICO +
		"from FILA_SMS " +
		"where FISM_ID = ?";
		
	private static final String SQL_NAO_ENFILEIRADO = SQL_BASICO +
	"from FILA_SMS " +
	"where ";
	
	private DAOFactory daofactory = null;
	
	public FirebirdFilaSMSDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public void updateStatusEnvioFila(FilaSMSDTO dto, String status) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_STATUS_ENVIO);
		int i = 0;
		qry.setString(i++, status);
		qry.setLong(i++, dto.id);
		qry.executeUpdate();
	}

	public void updateDataEnvioGatewaySMS(FilaSMSDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_DATA_ENVIO_GATEWAY);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.executeUpdate();
	}

/* APAGAR LINHAS
	@Deprecated
	public List<FilaSMSDTO> list(String statusQueue) throws AlugueRelaxeException {
		List<FilaSMSDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_NAO_ENFILEIRADO);
		qry.setString(0, statusQueue);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<FilaSMSDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				FilaSMSDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}
*/	
	public List<FilaSMSDTO> list(String modo) throws AlugueRelaxeException {
		List<FilaSMSDTO> lst = null;
		Session session = daofactory.getSession();
		StringBuilder sql = new StringBuilder();
		sql.append(SQL_NAO_ENFILEIRADO);
		sql.append(" FISM_IN_MODO = ? ");		
		if (modo.equals("M")){
			sql.append(" and FISM_DT_QUEUED is null ");		
		} else if (modo.equals("G")){
			sql.append(" and FISM_DT_ENVIO_GATEWAY is null");		
		}
		Query qry = session.createSQLQuery(sql.toString());
		qry.setString(0, modo);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<FilaSMSDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				FilaSMSDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}
	
	public List<FilaSMSDTO> list(String modo, int prioridade) throws AlugueRelaxeException {
		List<FilaSMSDTO> lst = null;
		Session session = daofactory.getSession();
		StringBuilder sql = new StringBuilder();
		sql.append(SQL_NAO_ENFILEIRADO);
		sql.append(" FISM_IN_MODO = ? ");		
		sql.append(" and FISM_NR_PRIORIDADE = ? ");		
		if (modo.equals("M")){
			sql.append(" and FISM_DT_QUEUED is null ");		
		} else if (modo.equals("G")){
			sql.append(" and FISM_DT_ENVIO_GATEWAY is null");		
		}
		Query qry = session.createSQLQuery(sql.toString());
		int idx = 0;
		qry.setString(idx++, modo);
		qry.setInteger(idx++, prioridade);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<FilaSMSDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				FilaSMSDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}
	
	public FilaSMSDTO insert(FilaSMSDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_BASICO);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setString(i++, dto.celular);
		qry.setString(i++, dto.msg);
		qry.setString(i++, dto.modo == null ? "M" : dto.modo );
		qry.setInteger(i++, dto.prioridade );
		qry.executeUpdate();
		return dto;
	}

	public FilaSMSDTO load(FilaSMSDTO dto) throws AlugueRelaxeException {
		FilaSMSDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_PLANO_BUSCA_PK);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	private FilaSMSDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		FilaSMSDTO dto = new FilaSMSDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.celular = (registro[++j] == null ? null : registro[j].toString());
		dto.msg = (registro[++j] == null ? null : registro[j].toString());
		dto.queued = (registro[++j] == null ? false : registro[j].toString().equals("S"));
		dto.dataQueued = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.modo = (registro[++j] == null ? null : registro[j].toString());
		dto.prioridade = Integer.valueOf(registro[++j].toString());
		dto.dataEnvioGateway = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		return dto;
	}

	public FilaSMSDTO update(FilaSMSDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(FilaSMSDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<FilaSMSDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FilaSMSDTO> list(int pagina) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
}
