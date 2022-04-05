package br.com.jcv.backend.imovel.caracteristica;

import br.com.jcv.backend.caracteristicas.CaracteristicaDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.ImovelCaracteristicaDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class FirebirdImovelCaracteristicaDAO implements
		ImovelCaracteristicaDAO<ImovelCaracteristicaDTO> {

	private static final String SQL_LISTA_CARACTERISTICA_IMOVEL = "select " +
	   "IMOVEL_CARACTERISTICA.IMCA_CD_CARACTERISTICA, " +
	   "IMOVEL_CARACTERISTICA.CARA_CD_CARACTERISTICA, " +
	   "CARACTERISTICA.CARA_NM_CARACTERISTICA, " +
	   "CARACTERISTICA.CARA_TX_ANUNCIO " +
	   "from IMOVEL_CARACTERISTICA " +
	   "  inner join CARACTERISTICA on IMOVEL_CARACTERISTICA.CARA_CD_CARACTERISTICA = CARACTERISTICA.CARA_CD_CARACTERISTICA " +
	   "where IMOVEL_CARACTERISTICA.IMOV_CD_IMOVEL = ? " +
	   "and   IMOVEL_CARACTERISTICA.CARA_IN_CONDOMINIO = ?" ;

	private static final String INS_IMOVEL_CARACTERISTICA = "insert into IMOVEL_CARACTERISTICA (" +
		"IMCA_CD_CARACTERISTICA," +
		"CARA_IN_CONDOMINIO," + 
		"CARA_CD_CARACTERISTICA," +
		"IMOV_CD_IMOVEL)" +
		" VALUES (?,?,?,?)";

	private static final String DEL_CARACTERISTICA = "delete from IMOVEL_CARACTERISTICA " +
	"where IMOV_CD_IMOVEL = ? ";
	
	private static final String DEL_CARACTERISTICA_IMOVEL_CONDOMINIO = DEL_CARACTERISTICA +
	"and CARA_IN_CONDOMINIO = ?";
	

	private DAOFactory daofactory = null;
	
	public FirebirdImovelCaracteristicaDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}


	public void delete(ImovelCaracteristicaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ImovelCaracteristicaDTO insert(ImovelCaracteristicaDTO dto)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_IMOVEL_CARACTERISTICA);
		int i = 0;
		qry.setLong(i++, dto.id );
		qry.setString(i++, dto.indicadorCondominio );
		qry.setLong(i++, dto.caracteristica.id );
		qry.setLong(i++, dto.imovel.id );
		@SuppressWarnings("unused")
		int n = qry.executeUpdate();
		return dto;
	}

	public List<ImovelCaracteristicaDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelCaracteristicaDTO> list(int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelCaracteristicaDTO load(ImovelCaracteristicaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelCaracteristicaDTO update(ImovelCaracteristicaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelCaracteristicaDTO> buscarCaracteristicasImovel(
			long codigoImovel, String indicadorImovelCondominio)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTA_CARACTERISTICA_IMOVEL);
		qry.setLong(0, codigoImovel);
		qry.setString(1, indicadorImovelCondominio);
		List resultado = qry.list();
		List<ImovelCaracteristicaDTO> lstImovelCaracteristica = null;
		if (resultado.size() > 0) {
			lstImovelCaracteristica = new ArrayList<ImovelCaracteristicaDTO>();
			
			for (int j = 0; j < resultado.size(); j++) {
				Object dto[] = (Object[])resultado.get(j);
				
				int i = -1;
				ImovelCaracteristicaDTO icdto = new ImovelCaracteristicaDTO();
				icdto.id = Long.valueOf(dto[++i].toString());
				icdto.caracteristica = new CaracteristicaDTO();
				icdto.caracteristica.id = (dto[++i] == null ? 0 : Long.valueOf(dto[i].toString()));
				icdto.caracteristica.nome = (dto[++i] == null ? null : dto[i].toString());
				icdto.caracteristica.descricaoAnuncio = (dto[++i] == null ? null : dto[i].toString());
				lstImovelCaracteristica.add(icdto);
			}
			
		}
		return lstImovelCaracteristica;
	}


	public void delete(long codigoImovel, String indicadorImovelCondominio)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(DEL_CARACTERISTICA_IMOVEL_CONDOMINIO);
		int i = 0;
		qry.setLong(i++, codigoImovel);
		qry.setString(i++, indicadorImovelCondominio );
		@SuppressWarnings("unused")
		int n = qry.executeUpdate();
	}


}
