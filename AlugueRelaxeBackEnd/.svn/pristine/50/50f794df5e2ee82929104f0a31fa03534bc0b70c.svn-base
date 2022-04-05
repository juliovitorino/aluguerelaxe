package br.com.jcv.backend.imovel.tabelapreco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.TabelaPrecoDAO;

public class FirebirdTabelaPrecoDAO implements TabelaPrecoDAO<TabelaPrecoDTO> {

	private static final String INS_TABELA_PRECO_IMOVEL = "insert into TABELA_PRECO (" +
		   "TAPR_CD_TABELA_PRECO, " +
		   "IMOV_CD_IMOVEL, " +
		   "TAPR_TX_PERIODO, " +
		   "TAPR_DT_INICIO, " +
		   "TAPR_DT_DATA_FIM, " +
		   "TAPR_VL_TABELA, " +
		   "TAPR_TX_MINIMO_DE, " +
		   "TAPR_TX_OBSERVACAO " +
		   ") values (?,?,?,?,?,?,?,?)";

	private static final String SQL_BASE_TABELA_PRECO_IMOVEL = "select " +
	   "TABELA_PRECO.TAPR_CD_TABELA_PRECO, " +
	   "TABELA_PRECO.TAPR_TX_PERIODO, " +
	   "TABELA_PRECO.TAPR_DT_INICIO, " +
	   "TABELA_PRECO.TAPR_DT_DATA_FIM, " +
	   "TABELA_PRECO.TAPR_VL_TABELA, " +
	   "TABELA_PRECO.TAPR_TX_MINIMO_DE, " +
	   "TABELA_PRECO.TAPR_TX_OBSERVACAO ";
	
	private static final String SQL_LISTA_TABELA_PRECO_IMOVEL = SQL_BASE_TABELA_PRECO_IMOVEL +
	   "from TABELA_PRECO " +
	   "where TABELA_PRECO.IMOV_CD_IMOVEL = ?";

	private static final String DEL_TABELA_PRECO_IMOVEL = "delete from TABELA_PRECO " +
	   "where IMOV_CD_IMOVEL = ?";

	private DAOFactory daofactory = null;
	
	public FirebirdTabelaPrecoDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public void delete(TabelaPrecoDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	public TabelaPrecoDTO insert(TabelaPrecoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TabelaPrecoDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TabelaPrecoDTO> list(int start) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public TabelaPrecoDTO load(TabelaPrecoDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public TabelaPrecoDTO update(TabelaPrecoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TabelaPrecoDTO> buscarTabelaPrecoImovel(long codigoImovel)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTA_TABELA_PRECO_IMOVEL);
		qry.setLong(0, codigoImovel);
		List resultado = qry.list();
		List<TabelaPrecoDTO> lstTabelaPreco = null;
		if (resultado.size() > 0) {
			lstTabelaPreco = new ArrayList<TabelaPrecoDTO>();
			
			for (int j = 0; j < resultado.size(); j++) {
				Object dto[] = (Object[])resultado.get(j);
				
				int i = -1;
				TabelaPrecoDTO tpdto = new TabelaPrecoDTO();
				tpdto.id = Long.valueOf(dto[++i].toString());
				tpdto.textoPeriodo = (dto[++i] == null ? null : dto[i].toString());
				tpdto.dataInicio = (dto[++i] == null ? null : (Date) dto[i]);
				tpdto.dataFim = (dto[++i] == null ? null : (Date) dto[i]);
				tpdto.valorTabela = (dto[++i] == null ? 0 : Double.valueOf(dto[i].toString()));
				tpdto.textoMinimoDe = (dto[++i] == null ? null : dto[i].toString());
				tpdto.observacao = (dto[++i] == null ? null : dto[i].toString());
				lstTabelaPreco.add(tpdto);
			}
			
		}
		return lstTabelaPreco;
	}

	public TabelaPrecoDTO insert(long codigoImovel, TabelaPrecoDTO dto)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_TABELA_PRECO_IMOVEL);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setLong(i++, codigoImovel);
		qry.setString(i++, dto.textoPeriodo);
		qry.setDate(i++, dto.dataInicio);
		qry.setDate(i++, dto.dataFim);
		qry.setDouble(i++, dto.valorTabela );
		qry.setString(i++, dto.textoMinimoDe);
		qry.setString(i++, dto.observacao);
		qry.executeUpdate();
		return dto;
	}

	public void delete(long codigoImovel)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(DEL_TABELA_PRECO_IMOVEL);
		int i = 0;
		qry.setLong(i++, codigoImovel);
		qry.executeUpdate();
	}

}
