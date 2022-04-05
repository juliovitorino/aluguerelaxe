package br.com.jcv.backend.alerta24h;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.interfacesdao.AlertaDAO;
import br.com.jcv.backend.tipoalerta.TipoAlertaDTO;

public class FirebirdAlertaDAO implements AlertaDAO {


	private static final String UPD_STATUS_EMITIDO = "update FILA_ALERTA24H set " +
	"FIAL_IN_EMITIDO = ? " +
	"where FIAL_ID = ?";
	
	private static final String INS_BASICO = "insert into FILA_ALERTA24H (" +
	"FIAL_ID," +
	"TIAL_ID," +
	"IMOV_CD_IMOVEL," +
	"IMCA_CD_IMOVEL_CONTATO_ANU " +
	") VALUES (?,?,?,?)";
	
	private static final String SQL_BASICO =	"select " +
												"FIAL_ID, " +
												"TIAL_ID, " +
												"IMOV_CD_IMOVEL, " +
												"IMCA_CD_IMOVEL_CONTATO_ANU, " +
												"FIAL_IN_EMITIDO, " +
												"FIAL_DT_CADASTRO " ;
												
	private static final String SQL_PLANO_BUSCA_PK = SQL_BASICO +
		"from FILA_ALERTA24H " +
		"where FIAL_ID = ?";
		
	private static final String SQL_FULL_STATUS = SQL_BASICO +
	"from FILA_ALERTA24H " +
	"where FIAL_IN_EMITIDO = ?";		
	
	private DAOFactory daofactory = null;
	
	public FirebirdAlertaDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}
	
	public List<AlertaDTO> list(String status) throws AlugueRelaxeException {
		List<AlertaDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_FULL_STATUS);
		qry.setString(0, status);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<AlertaDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				AlertaDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}
	
	public AlertaDTO insert(AlertaDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_BASICO);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setLong(i++, dto.tipoAlerta.id);
		qry.setLong(i++, dto.ifcdto.imovel.id);
		qry.setString(i++, dto.contato.id);
		qry.executeUpdate();
		return dto;
	}

	public AlertaDTO load(AlertaDTO dto) throws AlugueRelaxeException {
		AlertaDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_PLANO_BUSCA_PK);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	private AlertaDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		AlertaDTO dto = new AlertaDTO();
		dto.ifcdto = new ImovelFichaCompletaDTO();
		dto.ifcdto.imovel = new ImovelDTO();
		dto.contato = new ContatoClienteDTO();
		dto.tipoAlerta = new TipoAlertaDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.tipoAlerta.id = Long.valueOf(registro[++j].toString());
		dto.ifcdto.imovel.id = Long.valueOf(registro[++j].toString());
		dto.contato.id = (registro[++j] == null ? null : registro[j].toString());
		dto.emitido = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		return dto;
	}

	public AlertaDTO update(AlertaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(AlertaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<AlertaDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AlertaDTO> list(int pagina) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AlertaDTO> listAlertasPendentes() throws AlugueRelaxeException {
		List<AlertaDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_FULL_STATUS);
		qry.setString(0, "N");
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<AlertaDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				AlertaDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}

	public void updateStatusEmitido(AlertaDTO dto, String status) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_STATUS_EMITIDO);
		int i = 0;
		qry.setString(i++, status);
		qry.setLong(i++, dto.id);
		qry.executeUpdate();
	}
	
}
