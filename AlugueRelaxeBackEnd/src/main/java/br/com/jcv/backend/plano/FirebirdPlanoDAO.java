
package br.com.jcv.backend.plano;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.PlanoDAO;

public class FirebirdPlanoDAO implements PlanoDAO<PlanoDTO> {

	public static final int MAXIMO_RECURSO = 40;

	public static final String INS_PLANO = "insert into PLANO (" +
	"PLAN_CD_PLANO, " +
	"PLAN_NM_PLANO, " +
	"PLAN_TX_DESCRICAO, " +
	"PLAN_VL_PLANO, " +
	"PLAN_IN_TIPO_PERIODICIDADE, " +
	"PLAN_NR_DIAS " +
	") values (?,?,?,?,?,?)";
	
	private static final String SQL_BASICO =	"select " +
												"PLAN_CD_PLANO, " +
												"PLAN_NM_PLANO, " +
												"PLAN_TX_DESCRICAO, " +
												"PLAN_VL_PLANO, " +
												"PLAN_VL_DESCONTO, " +
												"PLAN_IN_STATUS, " + 
												"PLAN_DT_CADASTRO, " +
												"PLAN_DT_ATIVACAO, " +
												"PLAN_DT_CANCELAMENTO, " +
												"PLAN_IN_TIPO_PERIODICIDADE, " +
												"PLAN_IN_TIPO_COMPRA, " +
												"PLAN_NR_DIAS, " +
												"PLAN_TX_BTNPGTO_PAGSEGURO, " +
												"PLAN_TX_BTNPGTO_PAYPAL, " +
												"PLAN_QT_MAX_FOTO_PERMITIDOS, " +
												"PLAN_IN_TIPO_COMPRA, " +
												"PLAN_NU_BYTES_VAULT, " +
												"PLAN_IN_RECURSO_01, " +
												"PLAN_IN_RECURSO_02, " +
												"PLAN_IN_RECURSO_03, " +
												"PLAN_IN_RECURSO_04, " +
												"PLAN_IN_RECURSO_05, " +
												"PLAN_IN_RECURSO_06, " +
												"PLAN_IN_RECURSO_07, " +
												"PLAN_IN_RECURSO_08, " +
												"PLAN_IN_RECURSO_09, " +
												"PLAN_IN_RECURSO_10, " +
												"PLAN_IN_RECURSO_11, " +
												"PLAN_IN_RECURSO_12, " +
												"PLAN_IN_RECURSO_13, " +
												"PLAN_IN_RECURSO_14, " +
												"PLAN_IN_RECURSO_15, " +
												"PLAN_IN_RECURSO_16, " +
												"PLAN_IN_RECURSO_17, " +
												"PLAN_IN_RECURSO_18, " +
												"PLAN_IN_RECURSO_19, " +
												"PLAN_IN_RECURSO_20, " +
												"PLAN_IN_RECURSO_21, " +
												"PLAN_IN_RECURSO_22, " +
												"PLAN_IN_RECURSO_23, " +
												"PLAN_IN_RECURSO_24, " +
												"PLAN_IN_RECURSO_25, " +
												"PLAN_IN_RECURSO_26, " +
												"PLAN_IN_RECURSO_27, " +
												"PLAN_IN_RECURSO_28, " +
												"PLAN_IN_RECURSO_29, " +
												"PLAN_IN_RECURSO_30, " +
												"PLAN_IN_RECURSO_31, " +
												"PLAN_IN_RECURSO_32, " +
												"PLAN_IN_RECURSO_33, " +
												"PLAN_IN_RECURSO_34, " +
												"PLAN_IN_RECURSO_35, " +
												"PLAN_IN_RECURSO_36, " +
												"PLAN_IN_RECURSO_37, " +
												"PLAN_IN_RECURSO_38, " +
												"PLAN_IN_RECURSO_39, " +
												"PLAN_IN_RECURSO_40  " ;
	
	private static final String SQL_PLANO_BUSCA_PK = SQL_BASICO +
		"from PLANO " +
		"where PLAN_CD_PLANO = ?";
	
	private static final String SQL_FULL_ATIVOS = SQL_BASICO +
	"from PLANO " +
	"where PLAN_IN_STATUS = ?";

	private static final String SQL_POR_TIPO = SQL_BASICO +
	"from PLANO " +
	"where PLAN_IN_TIPO_COMPRA = ? " +
	"and   PLAN_IN_STATUS = ?";
	
	private DAOFactory daofactory = null;
	
	public FirebirdPlanoDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public void delete(PlanoDTO dto) throws AlugueRelaxeException {
	}

	public PlanoDTO insert(PlanoDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_PLANO);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setString(i++, dto.nome);
		qry.setString(i++, dto.descricao);
		qry.setDouble(i++, dto.valor);
		qry.setString(i++, dto.indicadorPeriodicidade);
		qry.setInteger(i++, dto.numeroDiasCalculo);
		qry.executeUpdate();
		return dto;
	}

	public List<PlanoDTO> list() throws AlugueRelaxeException {
		List<PlanoDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_FULL_ATIVOS);
		qry.setString(0, "A");
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<PlanoDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				PlanoDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}

	public List<PlanoDTO> list(String tipo) throws AlugueRelaxeException {
		List<PlanoDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_POR_TIPO);
		int j = 0;
		qry.setString(j++, tipo);
		qry.setString(j++, "A");
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<PlanoDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				PlanoDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}

	public List<PlanoDTO> list(int pagina) throws AlugueRelaxeException {
		return null;
	}

	public PlanoDTO load(PlanoDTO dto) throws AlugueRelaxeException {
		PlanoDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_PLANO_BUSCA_PK);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	public PlanoDTO update(PlanoDTO dto) throws AlugueRelaxeException {
		return null;
	}

	private PlanoDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		PlanoDTO dto = new PlanoDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.nome = (registro[++j] == null ? null : registro[j].toString());
		dto.descricao = (registro[++j] == null ? null : registro[j].toString());
		dto.valor = Double.valueOf(registro[++j].toString());
		dto.valorDesconto = Double.valueOf(registro[++j].toString());
		dto.indicadorStatus = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.dataAtivacao = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.dataCancelamento = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.indicadorPeriodicidade = (registro[++j] == null ? null : registro[j].toString());
		dto.indicadorStatus = (registro[++j] == null ? null : registro[j].toString());
		dto.numeroDiasCalculo = Integer.valueOf(registro[++j].toString());
		dto.htmlBtnPagseguro = (registro[++j] == null ? null : registro[j].toString());
		dto.htmlBtnPayPal = (registro[++j] == null ? null : registro[j].toString());
		dto.limiteFotos = Integer.valueOf(registro[++j].toString());
		dto.indicadorTipoCompra = (registro[++j] == null ? null : registro[j].toString());
		dto.bytesVault = Long.valueOf(registro[++j].toString());
		
		// Insere colunas de recursos do plano
		dto.recurso = new String[MAXIMO_RECURSO];
		for (int idx = 0; idx < MAXIMO_RECURSO; idx++){
			dto.recurso[idx] = (registro[++j] == null ? null : registro[j].toString());
		}
		
		return dto;
	}
	
}

