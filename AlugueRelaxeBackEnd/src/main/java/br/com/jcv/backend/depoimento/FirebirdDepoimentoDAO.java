package br.com.jcv.backend.depoimento;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.DepoimentoDAO;

public class FirebirdDepoimentoDAO implements DepoimentoDAO<DepoimentoDTO> {

	private static String SQL_SELECT_BASICO_PAGINADO = "select FIRST ? " +
	  											"DEPO_CD_DEPOIMENTO," +
	  											"DEPO_NM_PESSOA," +
	  											"DEPO_TX_DEPOIMENTO, " +
	  											"DEPO_DT_CADASTRO " +
												"FROM DEPOIMENTO "  +
												"WHERE DEPO_IN_STATUS = ? " +
												"ORDER BY 1 DESC";
												
	private static String INS_NOVO_DEPOIMENTO =
		"insert into DEPOIMENTO " +
		"(DEPO_CD_DEPOIMENTO,DEPO_NM_PESSOA,DEPO_TX_DEPOIMENTO) " +
		"values (?,?,?)";
	
	private static String UPD_STATUS_DEPOIMENTO = "update DEPOIMENTO " +
	"set DEPO_IN_STATUS = ? " +
	"where DEPO_CD_DEPOIMENTO = ?";
	
	private static String SQL_NEXT_DEPOIMENTO = "select " +
	  											"DEPO_CD_DEPOIMENTO," +
	  											"DEPO_NM_PESSOA," +
	  											"DEPO_TX_DEPOIMENTO " +
												"FROM DEPOIMENTO "  +
												"where DEPO_CD_DEPOIMENTO = (select MIN(DEPO_CD_DEPOIMENTO) " +
												"            from DEPOIMENTO " +
												"            where DEPO_CD_DEPOIMENTO > ? and DEPO_IN_STATUS = ?)";

	private static String SQL_PREV_DEPOIMENTO = "select " +
		"DEPO_CD_DEPOIMENTO," +
		"DEPO_NM_PESSOA," +
		"DEPO_TX_DEPOIMENTO " +
	"FROM DEPOIMENTO "  +
	"where DEPO_CD_DEPOIMENTO = (select MAX(DEPO_CD_DEPOIMENTO) " +
	"            from DEPOIMENTO " +
	"            where DEPO_CD_DEPOIMENTO < ? and DEPO_IN_STATUS = ?)";

	private DAOFactory daofactory = null;
	
	public FirebirdDepoimentoDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public void delete(DepoimentoDTO dto) throws AlugueRelaxeException {
	}

	public DepoimentoDTO insert(DepoimentoDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_NOVO_DEPOIMENTO);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setString(i++, dto.nome);
		qry.setString(i++, dto.depoimento);
		qry.executeUpdate();
		session.flush();
		return dto;
	}

	public DepoimentoDTO update(DepoimentoDTO dto) throws AlugueRelaxeException {
		return null;
	}

	public DepoimentoDTO load(DepoimentoDTO dto) throws AlugueRelaxeException {
		return null;
	}

	public List<DepoimentoDTO> list() throws AlugueRelaxeException {
		return null;	
	}

	@Deprecated
	public DepoimentoDTO nextDepoimento(Long id) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		
		Query qry = session.createSQLQuery(SQL_NEXT_DEPOIMENTO);
		qry.setLong(0, id);
		qry.setString(1, Constantes.DEPO_STATUS_LIBERADO);
		List resultado = qry.list();
		
		DepoimentoDTO ifcdto = null;
		if (resultado.size() > 0) {
			Object dto[] = (Object[])resultado.get(0);
			
			int j = -1;
			ifcdto = new DepoimentoDTO();
			ifcdto.id = (dto[++j] == null ? -1 : Long.valueOf(dto[j].toString()));
			ifcdto.nome = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.depoimento = (dto[++j] == null ? null : dto[j].toString());
		}
		
		return ifcdto;
		
	}

	@Deprecated
	public DepoimentoDTO prevDepoimento(Long id) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		
		Query qry = session.createSQLQuery(SQL_PREV_DEPOIMENTO);
		qry.setLong(0, id);
		qry.setString(1, Constantes.DEPO_STATUS_LIBERADO);
		List resultado = qry.list();
		
		DepoimentoDTO ifcdto = null;
		if (resultado.size() > 0) {
			Object dto[] = (Object[])resultado.get(0);
			
			int j = -1;
			ifcdto = new DepoimentoDTO();
			ifcdto.id = (dto[++j] == null ? -1 : Long.valueOf(dto[j].toString()));
			ifcdto.nome = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.depoimento = (dto[++j] == null ? null : dto[j].toString());
		}
		
		return ifcdto;
		
	}

	
	public List<DepoimentoDTO> list(int start) throws AlugueRelaxeException {
		List<DepoimentoDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SELECT_BASICO_PAGINADO);
		int j = 0;
		qry.setInteger(j++, start);
		qry.setString(j++, Constantes.DEPO_STATUS_LIBERADO);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<DepoimentoDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				DepoimentoDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}

	public void atualizarStatus(long id, String acao) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_STATUS_DEPOIMENTO);
		qry.setString(0, acao);
		qry.setLong(1, id);
		int n = qry.executeUpdate();
		session.flush();
	}

	private DepoimentoDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		DepoimentoDTO dto = new DepoimentoDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.nome = (registro[++j] == null ? null : registro[j].toString());
		dto.depoimento = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		return dto;
	}

}
