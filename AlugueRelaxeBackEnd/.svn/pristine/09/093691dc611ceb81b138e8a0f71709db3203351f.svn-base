package br.com.jcv.backend.modopublicidade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.ModoPublicidadeDAO;


public class FirebirdModoPublicidadeDAO implements ModoPublicidadeDAO<ModoPublicidadeDTO> {

	public static final String INS_BASICO = "insert into MODO_PUBLICIDADE (" +
	"MOPU_CD_MODO_PUBLICIDADE, " +
	"MOPU_TX_MODO_PUBLICIDADE, " +
	") values (?,?)";
	
	private static final String SQL_BASICO =	"select " +
												"MOPU_CD_MODO_PUBLICIDADE, " +
												"MOPU_TX_MODO_PUBLICIDADE, " +
												"MOPU_DT_CADASTRO ";
	
	private static final String SQL_BUSCA_PK = SQL_BASICO +
		"from MODO_PUBLICIDADE " +
		"where MOPU_CD_MODO_PUBLICIDADE = ?";

	private static final String SQL_BUSCA_FULL = SQL_BASICO +
		"from MODO_PUBLICIDADE " +
		"where MOPU_IN_STATUS = ? " + 
		"Order by MOPU_TX_MODO_PUBLICIDADE";
	
	private DAOFactory daofactory = null;
	
	public FirebirdModoPublicidadeDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public void delete(ModoPublicidadeDTO dto) throws AlugueRelaxeException {
	}

	public ModoPublicidadeDTO insert(ModoPublicidadeDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_BASICO);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setString(i++, dto.descricao);
		qry.executeUpdate();
		return dto;
	}

	public List<ModoPublicidadeDTO> list() throws AlugueRelaxeException {
		List<ModoPublicidadeDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCA_FULL);
		qry.setString(0, "S");
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<ModoPublicidadeDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				ModoPublicidadeDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}

	public ModoPublicidadeDTO load(ModoPublicidadeDTO dto) throws AlugueRelaxeException {
		ModoPublicidadeDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCA_PK);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	private ModoPublicidadeDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		ModoPublicidadeDTO dto = new ModoPublicidadeDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.descricao = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		return dto;
	}

	public ModoPublicidadeDTO update(ModoPublicidadeDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ModoPublicidadeDTO> list(int pagina)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
}

