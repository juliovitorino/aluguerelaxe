package br.com.jcv.backend.imovel.plano;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.ImovelPlanoDAO;



public class FirebirdImovelPlanoDAO implements ImovelPlanoDAO<ImovelPlanoRelacaoDTO> {

	public static final String INS_PLANO = "insert into IMOVEL_PLANO (" +
	"IMPL_CD_IMOVEL_PLANO, " +
	"IMOV_CD_IMOVEL, " +
	"PLAN_CD_PLANO " +
	") values (?,?,?)";
	
	private static final String SQL_BASICO =	"select " +
												"z.IMPL_CD_IMOVEL_PLANO, " +
												"z.IMOV_CD_IMOVEL, " +
												"z.PLAN_CD_PLANO, " +
												"z.IMPL_DT_CADASTRO ";
	
	private static final String SQL_IMOVEL_PLANO_BUSCA_PK = SQL_BASICO +
		"from IMOVEL_PLANO z " +
		"where z.IMPL_CD_IMOVEL_PLANO = ?";
	
	private static final String SQL_IMOVEL_PLANO_BUSCA_IMOVEL_PLANO = SQL_BASICO +
		"from IMOVEL_PLANO z " +
		"where z.IMOV_CD_IMOVEL = ? " +
		"and   z.PLAN_CD_PLANO = ?";
	
	private DAOFactory daofactory = null;
	
	public FirebirdImovelPlanoDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public ImovelPlanoRelacaoDTO insert(ImovelPlanoRelacaoDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_PLANO);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setLong(i++, dto.idImovel);
		qry.setLong(i++, dto.idPlano);
		qry.executeUpdate();
		return dto;
	}

	public ImovelPlanoRelacaoDTO load(ImovelPlanoRelacaoDTO dto) throws AlugueRelaxeException {
		ImovelPlanoRelacaoDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_PLANO_BUSCA_PK); 
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	public ImovelPlanoRelacaoDTO load(long idImovel, long idPlano) throws AlugueRelaxeException {
		ImovelPlanoRelacaoDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_PLANO_BUSCA_IMOVEL_PLANO); 
		qry.setLong(0, idImovel);
		qry.setLong(1, idPlano);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}


	private ImovelPlanoRelacaoDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		ImovelPlanoRelacaoDTO dto = new ImovelPlanoRelacaoDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.idImovel = Long.valueOf(registro[++j].toString());
		dto.idPlano = Long.valueOf(registro[++j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		return dto;
	}

	public ImovelPlanoRelacaoDTO update(ImovelPlanoRelacaoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(ImovelPlanoRelacaoDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<ImovelPlanoRelacaoDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelPlanoRelacaoDTO> list(int pagina)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	
}

