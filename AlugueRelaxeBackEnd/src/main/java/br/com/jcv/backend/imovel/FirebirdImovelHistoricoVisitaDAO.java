package br.com.jcv.backend.imovel;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.ImovelHistoricoVisitaDAO;

public class FirebirdImovelHistoricoVisitaDAO implements ImovelHistoricoVisitaDAO<ImovelHistoricoVisitaDTO> {

	private static final String SQL_BASICO = "Select " +
	"IMVI_CD_IMOVEL_VISITAS," +
	"IMOV_CD_IMOVEL," +
	"IMVI_NR_ANO," +
	"IMVI_NR_MES," +
	"IMVI_NR_DIA," +
	"IMVI_QT_VISITA,"+
	"IMVI_IN_ORIGEM_VISITA ";
	
	private static final String SQL_BUSCA_PK = SQL_BASICO +
	"from IMOVEL_VISITAS " +
	"where IMVI_CD_IMOVEL_VISITAS = ?";

	@Deprecated
	private static final String SQL_BUSCA_IMOV_ANO_MES = SQL_BASICO +
	"from IMOVEL_VISITAS " +
	"where IMOV_CD_IMOVEL = ?" +
	"  and IMVI_NR_ANO = ?" +
	"  and IMVI_NR_MES = ?" +
	"  and IMVI_NR_DIA = ?";

	private static final String SQL_BUSCA_IMOV_ANO_MES_ORIGEM = SQL_BASICO +
	"from IMOVEL_VISITAS " +
	"where IMOV_CD_IMOVEL = ?" +
	"  and IMVI_NR_ANO = ?" +
	"  and IMVI_NR_MES = ?" +
	"  and IMVI_NR_DIA = ?" +
	"  and IMVI_IN_ORIGEM_VISITA = ?";

	private static final String INS_REGISTRO_HISTORICO = "insert into IMOVEL_VISITAS (" +
	"IMVI_CD_IMOVEL_VISITAS," +
	"IMOV_CD_IMOVEL," +
	"IMVI_NR_ANO," +
	"IMVI_NR_MES, " +
	"IMVI_NR_DIA," +
	"IMVI_IN_ORIGEM_VISITA) " +
	"values (?,?,?,?,?,?)";

	private static final String UPD_INCREMENTO_HISTORICO = "update IMOVEL_VISITAS set " +
	"IMVI_QT_VISITA = IMVI_QT_VISITA + 1" +
	"where IMVI_CD_IMOVEL_VISITAS = ?";
	
	private DAOFactory daofactory = null;
	
	public FirebirdImovelHistoricoVisitaDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}
	
	public ImovelHistoricoVisitaDTO load(ImovelHistoricoVisitaDTO dto) throws AlugueRelaxeException {
		ImovelHistoricoVisitaDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCA_PK);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}
	
	@Deprecated
	public ImovelHistoricoVisitaDTO load(long codigoImovel, int ano, int mes, int dia) throws AlugueRelaxeException {
		ImovelHistoricoVisitaDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCA_IMOV_ANO_MES);
		int i = 0;
		qry.setLong(i++, codigoImovel);
		qry.setInteger(i++, ano);
		qry.setInteger(i++, mes);
		qry.setInteger(i++, dia);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}
	
	public ImovelHistoricoVisitaDTO load(long codigoImovel, int ano, int mes, int dia, String origemAcesso) throws AlugueRelaxeException {
		ImovelHistoricoVisitaDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCA_IMOV_ANO_MES_ORIGEM);
		int i = 0;
		qry.setLong(i++, codigoImovel);
		qry.setInteger(i++, ano);
		qry.setInteger(i++, mes);
		qry.setInteger(i++, dia);
		qry.setString(i++, origemAcesso);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}
	
	public ImovelHistoricoVisitaDTO insert(ImovelHistoricoVisitaDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_REGISTRO_HISTORICO);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setLong(i++, dto.idImovel);
		qry.setInteger(i++, dto.ano);
		qry.setInteger(i++, dto.mes);
		qry.setInteger(i++, dto.dia);
		qry.setString(i++, dto.origemAcesso);
		qry.executeUpdate();
		return dto;
	}

	public void incrementaImovelHistoricoVisita(long id) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_INCREMENTO_HISTORICO);
		int i = 0;
		qry.setLong(i++, id);
		qry.executeUpdate();
	}

	private ImovelHistoricoVisitaDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		ImovelHistoricoVisitaDTO dto = new ImovelHistoricoVisitaDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.idImovel = Long.valueOf(registro[++j].toString());
		dto.ano = Integer.valueOf(registro[++j].toString());
		dto.mes = Integer.valueOf(registro[++j].toString());
		dto.dia = Integer.valueOf(registro[++j].toString());
		dto.qtdeVisitas = Long.valueOf(registro[++j].toString());
		return dto;
	}

	public ImovelHistoricoVisitaDTO update(ImovelHistoricoVisitaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(ImovelHistoricoVisitaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<ImovelHistoricoVisitaDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelHistoricoVisitaDTO> list(int pagina)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
}