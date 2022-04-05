package br.com.jcv.backend.modopublicidadevisitas;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.ModoPublicidadeVisitasDAO;



public class FirebirdModoPublicidadeVisitasDAO implements ModoPublicidadeVisitasDAO<ModoPublicidadeVisitasDTO> {

	public static final String INS_BASICO = "insert into MODO_PUBL_VISITAS (" +
	"MOPV_CD_MODO_PUBL_VISITAS, " +
	"MOPU_CD_MODO_PUBLICIDADE " +
	") values (?,?)";
	
	private static final String SQL_BASICO =	"select " +
												"MOPV_CD_MODO_PUBL_VISITAS, " +
												"MOPU_CD_MODO_PUBLICIDADE, " +
												"MOPV_DT_RESPOSTA, " +
												"MOPV_QT_RESPOSTA, " +
												"MOPV_DT_CADASTRO ";
	
	private static final String SQL_BUSCA_PK = SQL_BASICO +
		"from MODO_PUBL_VISITAS " +
		"where MOPV_CD_MODO_PUBL_VISITAS = ?";
	
	private static final String SQL_BUSCA_UNIQUE = SQL_BASICO +
		"from MODO_PUBL_VISITAS " +
		"where MOPV_DT_RESPOSTA = ? " +
		"and MOPU_CD_MODO_PUBLICIDADE = ?";
		
	private static final String UPD_INCREMENTA_VISITA_MODO_PUBLICIDADE = "update MODO_PUBL_VISITAS set " +
		"MOPV_QT_RESPOSTA = MOPV_QT_RESPOSTA + 1 " +
		"where MOPV_DT_RESPOSTA = ? " +
		"and MOPU_CD_MODO_PUBLICIDADE = ?";
	
	private DAOFactory daofactory = null;
	
	public FirebirdModoPublicidadeVisitasDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public ModoPublicidadeVisitasDTO insert(ModoPublicidadeVisitasDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_BASICO);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setLong(i++, dto.idModoPublicidade);
		qry.executeUpdate();
		return dto;
	}

	public void updateModoPublicidadeVisita(Date d, long idModoPublicidade) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_INCREMENTA_VISITA_MODO_PUBLICIDADE);
		int i = 0;
		qry.setDate(i++, d);
		qry.setLong(i++, idModoPublicidade);
		qry.executeUpdate();
	}

	public ModoPublicidadeVisitasDTO load(Date d, long idModoPublicidade) throws AlugueRelaxeException {
		ModoPublicidadeVisitasDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCA_UNIQUE);
		qry.setDate(0, d);
		qry.setLong(1, idModoPublicidade);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}


	public ModoPublicidadeVisitasDTO load(ModoPublicidadeVisitasDTO dto) throws AlugueRelaxeException {
		ModoPublicidadeVisitasDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCA_PK);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	private ModoPublicidadeVisitasDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		ModoPublicidadeVisitasDTO dto = new ModoPublicidadeVisitasDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.idModoPublicidade = Long.valueOf(registro[++j].toString());
		dto.dataResposta = (registro[++j] == null ? null : (Date) registro[j]);
		dto.qtdeResposta = Long.valueOf(registro[++j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		return dto;
	}

	public ModoPublicidadeVisitasDTO update(ModoPublicidadeVisitasDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(ModoPublicidadeVisitasDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<ModoPublicidadeVisitasDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ModoPublicidadeVisitasDTO> list(int pagina)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
}

