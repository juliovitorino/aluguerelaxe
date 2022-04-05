package br.com.jcv.backend.assinantes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.AssinantesDAO;


public class FirebirdAssinantesDAO implements AssinantesDAO<AssinantesDTO> {

	private static final String DEL_BASICO = "delete from ASSINANTES ";
	
	private static final String DEL_BASICO_PK = DEL_BASICO +
	"where ASSI_CD_ASSINANTE = ?";

	private static final String SQL_TICKET_BASICO =	"select " +
													"PRTI_NM_PROMOCAO, " +
													"PRTI_NR_TICKET " +
													"from PROMOCAO_TICKET " +
													"where PRTI_NM_PROMOCAO = ?" ;

	public static final String UPD_PROMOCAO_TICKET = 	"update PROMOCAO_TICKET set " +
														"PRTI_NR_TICKET = PRTI_NR_TICKET + 1 " +
														"where PRTI_NM_PROMOCAO = ?";

	public static final String UPD_STATUS_PENDENTE_ATIVO = 	"update ASSINANTES set " +
														"ASSI_IN_STATUS = ? " +
														"where ASSI_TX_HASH = ?";

	public static final String INS_ASSINANTES = "insert into ASSINANTES (" +
												"ASSI_CD_ASSINANTE, " +
												"ASSI_NM_ASSINANTE, " +
												"ASSI_TX_EMAIL, " +
												"ASSI_TX_HASH, " +
												"ASSI_TX_CAMPANHA, " +
												"ASSI_CD_ASSINANTE_PARENT, " +
												"ASSI_NR_TICKET " +
												") values (?,?,?,?,?,?,?)";

	private static final String SQL_BASICO =	"select " +
												"ASSI_CD_ASSINANTE, " +
												"ASSI_NM_ASSINANTE, " +
												"ASSI_TX_EMAIL, " +
												"ASSI_TX_HASH, " +
												"ASSI_TX_CAMPANHA, " +
												"ASSI_IN_STATUS, " +
												"ASSI_DT_CADASTRO, " +
												"ASSI_IN_SORTEADO, " +
												"ASSI_DT_SORTEIO, " +
												"ASSI_CD_ASSINANTE_PARENT, " +
												"ASSI_NR_TICKET " ;
	
	private static final String SQL_ASSINANTES_BUSCA_PK = SQL_BASICO +
		"from ASSINANTES " +
		"where ASSI_CD_ASSINANTE = ?";
	
	private static final String SQL_ASSINANTES_BUSCA_HASH = SQL_BASICO +
		"from ASSINANTES " +
		"where ASSI_TX_HASH = ?";
		
	private static final String SQL_ASSINANTES_BUSCA_EMAIL_CAMPANHA = SQL_BASICO + 
	"from ASSINANTES " +
	"where ASSI_TX_EMAIL = ? " +
	"and ASSI_TX_CAMPANHA = ?";

	private static final String SQL_ASSINANTES_BUSCA_PARENT = SQL_BASICO + 
	"from ASSINANTES " +
	"where ASSI_TX_EMAIL = ? " +
	"and ASSI_TX_CAMPANHA = ? " +
	"and ASSI_CD_ASSINANTE_PARENT = ?";
	
	private static final String SQL_ASSINANTES_CAMPANHA_STATUS = SQL_BASICO + 
	"from ASSINANTES " +
	"where ASSI_TX_CAMPANHA = ? " +
	"and   ASSI_IN_STATUS = ?";
	
	
	private DAOFactory daofactory = null;
	
	public FirebirdAssinantesDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}
	
	public List<AssinantesDTO> list(String campanha, String status) throws AlugueRelaxeException {
		List<AssinantesDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_ASSINANTES_CAMPANHA_STATUS);
		int i = -1;
		qry.setString(++i, campanha);
		qry.setString(++i, status);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<AssinantesDTO>();
			for (int j = 0; j < resultado.size(); j++) {
				AssinantesDTO dto = getDTO((Object[]) resultado.get(j));
				lst.add(dto);
			}
		}
		return lst;
	}
	

	public AssinantesDTO insert(AssinantesDTO dto) throws AlugueRelaxeException {
		synchronized (this) {
			
			// Atualiza o ticket
			Session session = daofactory.getSession();
			Query qry = session.createSQLQuery(UPD_PROMOCAO_TICKET);
			int i = 0;
			qry.setString(i++, dto.campanha);
			qry.executeUpdate();
			
			// Obtem o ticket
			qry = session.createSQLQuery(SQL_TICKET_BASICO);
			qry.setString(0, dto.campanha);
			List resultado = qry.list();
			if ((resultado != null) && (resultado.size() > 0)) {
				PromocaoTicketDTO ptdto = getTicketDTO((Object[]) resultado.get(0));
				dto.ticket = ptdto.ticket;
			}

			// Insere a inscricao em modo de pendencia
			 qry = session.createSQLQuery(INS_ASSINANTES);
			 i = 0;
			qry.setLong(i++, dto.id);
			qry.setString(i++, dto.nome);
			qry.setString(i++, dto.email);
			qry.setString(i++, dto.hash);
			qry.setString(i++, dto.campanha);
			qry.setLong(i++, dto.idParent);
			qry.setLong(i++, dto.ticket);
			qry.executeUpdate();
			
		
		}
		
		return dto;
	}
	
	public void updateStatusPendenteParaAtivo(String hash) throws AlugueRelaxeException {
		// Atualiza status para ativo
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_STATUS_PENDENTE_ATIVO); 
		int i = 0;
		qry.setString(i++, "A");
		qry.setString(i++, hash);
		qry.executeUpdate();
	}

	public AssinantesDTO load(AssinantesDTO dto) throws AlugueRelaxeException {
		AssinantesDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_ASSINANTES_BUSCA_PK);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	public AssinantesDTO load(AssinantesDTO dto, String campanha) throws AlugueRelaxeException {
		AssinantesDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_ASSINANTES_BUSCA_EMAIL_CAMPANHA);
		qry.setString(0, dto.email);
		qry.setString(1, campanha);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	public AssinantesDTO load(AssinantesDTO dto, String campanha, long parent) throws AlugueRelaxeException {
		AssinantesDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_ASSINANTES_BUSCA_PARENT);
		qry.setString(0, dto.email);
		qry.setString(1, campanha);
		qry.setLong(2, parent);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	
	public AssinantesDTO loadHash(String hash) throws AlugueRelaxeException {
		AssinantesDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_ASSINANTES_BUSCA_HASH);
		qry.setString(0, hash);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	private AssinantesDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		AssinantesDTO dto = new AssinantesDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.nome = (registro[++j] == null ? null : registro[j].toString());
		dto.email = (registro[++j] == null ? null : registro[j].toString());
		dto.hash = (registro[++j] == null ? null : registro[j].toString());
		dto.campanha = (registro[++j] == null ? null : registro[j].toString());
		dto.status = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.sorteado = (registro[++j] == null ? null : registro[j].toString());
		dto.dataSorteio = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.idParent = Long.valueOf(registro[++j].toString());
		dto.ticket =  Long.valueOf(registro[++j].toString());
		
		return dto;
	}
	
	public AssinantesDTO update(AssinantesDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(AssinantesDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(DEL_BASICO_PK); 
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.executeUpdate();
	}

	public List<AssinantesDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AssinantesDTO> list(int pagina) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	private PromocaoTicketDTO getTicketDTO(Object[] registro) throws AlugueRelaxeException {
		PromocaoTicketDTO dto = new PromocaoTicketDTO();
		int j = -1;
		dto.campanha = (registro[++j] == null ? null : registro[j].toString());
		dto.ticket = Long.valueOf(registro[++j].toString());
		return dto;
	}
	
}

