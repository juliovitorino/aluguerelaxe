package br.com.jcv.backend.painelcontrole;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.PainelControleDAO;

public class FirebirdPainelControleDAO implements PainelControleDAO {

	// Queries para contagem das entradas de imoveis
	private static final String SQL_SUM_IMOVEL_ENTRADA_GERAL = "select count(*) TOTAL from imovel";

	private static final String SQL_SUM_IMOVEL_ENTRADA_GERAL_ANO = SQL_SUM_IMOVEL_ENTRADA_GERAL + 
	" where EXTRACT(YEAR FROM imov_dt_cadastro) = ?";

	private static final String SQL_SUM_IMOVEL_ENTRADA_GERAL_ANO_MES = SQL_SUM_IMOVEL_ENTRADA_GERAL_ANO + 
	" and EXTRACT(MONTH FROM imov_dt_cadastro) = ?";

	private static final String SQL_SUM_IMOVEL_ENTRADA_GERAL_ANO_MES_DIA = SQL_SUM_IMOVEL_ENTRADA_GERAL_ANO_MES + 
	" and EXTRACT(DAY FROM imov_dt_cadastro) = ?";

	// Queries para contagem das visitas
	private static final String SQL_SUM_IMOVEL_VISITAS_GERAL = "select sum(imvi_qt_visita) TOTAL from imovel_visitas ";

	private static final String SQL_SUM_IMOVEL_VISITAS_GERAL_ANO = SQL_SUM_IMOVEL_VISITAS_GERAL + 
	" where imvi_nr_ano = ? ";

	private static final String SQL_SUM_IMOVEL_VISITAS_GERAL_ANO_MES = SQL_SUM_IMOVEL_VISITAS_GERAL_ANO + 
	" and imvi_nr_mes = ? ";

	private static final String SQL_SUM_IMOVEL_VISITAS_GERAL_ANO_MES_DIA = SQL_SUM_IMOVEL_VISITAS_GERAL_ANO_MES + 
	" and imvi_nr_dia = ? ";
	
	private static final String SQL_SUM_DEPOIMENTOS_PENDENTES = "select count(*) TOTAL from DEPOIMENTO where DEPO_IN_STATUS = ?";

	private static final String SQL_SUM_CONTATOS_PENDENTES = "select count(*) TOTAL from IMOVEL_CONTATO_ANUNCIANTE where IMCA_IN_STATUS = ?";
	
	private static final String SQL_MAX_DATA_PUBLICIDADE = "select max(PUBL_DT_FIM) from PUBLICIDADE " +
	" where PUBL_IN_TIPO_PUBLICIDADE = ? and PUBL_DT_FIM >= current_date";

	/* Lembrete: No futuro pode colocar uma limitacao dos ultimos 5 anos em uma clausula "where a.IMVI_NR_ANO <= <digite o ano>" */
	private static final String SQL_SUM_VISITAS_ANO_A_ANO = "select a.IMVI_NR_ANO,sum(a.imvi_qt_visita) As total, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 1 THEN a.IMVI_QT_VISITA ELSE 0 END) As JANEIRO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 2 THEN a.IMVI_QT_VISITA ELSE 0 END) As FEVEREIRO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 3 THEN a.IMVI_QT_VISITA ELSE 0 END) As MARCO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 4 THEN a.IMVI_QT_VISITA ELSE 0 END) As ABRIL, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 5 THEN a.IMVI_QT_VISITA ELSE 0 END) As MAIO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 6 THEN a.IMVI_QT_VISITA ELSE 0 END) As JUNHO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 7 THEN a.IMVI_QT_VISITA ELSE 0 END) As JULHO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 8 THEN a.IMVI_QT_VISITA ELSE 0 END) As AGOSTO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 9 THEN a.IMVI_QT_VISITA ELSE 0 END) As SETEMBRO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 10 THEN a.IMVI_QT_VISITA ELSE 0 END) As OUTUBRO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 11 THEN a.IMVI_QT_VISITA ELSE 0 END) As NOVEMBRO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 12 THEN a.IMVI_QT_VISITA ELSE 0 END) As DEZEMBRO " +
		"from imovel_visitas a " +
		"group by a.IMVI_NR_ANO";

	private static final String SQL_SUM_VISITAS_ORIGEM_ANO = "select a.imvi_in_origem_visita,sum(a.imvi_qt_visita) total, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 1 THEN a.IMVI_QT_VISITA ELSE 0 END) As JANEIRO,  " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 2 THEN a.IMVI_QT_VISITA ELSE 0 END) As FEVEREIRO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 3 THEN a.IMVI_QT_VISITA ELSE 0 END) As MARCO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 4 THEN a.IMVI_QT_VISITA ELSE 0 END) As ABRIL, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 5 THEN a.IMVI_QT_VISITA ELSE 0 END) As MAIO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 6 THEN a.IMVI_QT_VISITA ELSE 0 END) As JUNHO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 7 THEN a.IMVI_QT_VISITA ELSE 0 END) As JULHO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 8 THEN a.IMVI_QT_VISITA ELSE 0 END) As AGOSTO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 9 THEN a.IMVI_QT_VISITA ELSE 0 END) As SETEMBRO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 10 THEN a.IMVI_QT_VISITA ELSE 0 END) As OUTUBRO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 11 THEN a.IMVI_QT_VISITA ELSE 0 END) As NOVEMBRO, " +
		"SUM(CASE WHEN a.IMVI_NR_MES = 12 THEN a.IMVI_QT_VISITA ELSE 0 END) As DEZEMBRO " +
		"from imovel_visitas a " +
		"where a.IMVI_NR_ANO = ? " +
		"group by a.imvi_in_origem_visita " ;

	private static final String SQL_SUM_VISITAS_DIARIAS = "select 	SUM(a.IMVI_QT_VISITA)  As TOTAL, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 1 THEN a.IMVI_QT_VISITA ELSE 0 END) As D1, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 2 THEN a.IMVI_QT_VISITA ELSE 0 END) As D2, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 3 THEN a.IMVI_QT_VISITA ELSE 0 END) As D3, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 4 THEN a.IMVI_QT_VISITA ELSE 0 END) As D4, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 5 THEN a.IMVI_QT_VISITA ELSE 0 END) As D5, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 6 THEN a.IMVI_QT_VISITA ELSE 0 END) As D6, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 7 THEN a.IMVI_QT_VISITA ELSE 0 END) As D7, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 8 THEN a.IMVI_QT_VISITA ELSE 0 END) As D8, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 9 THEN a.IMVI_QT_VISITA ELSE 0 END) As D9, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 10 THEN a.IMVI_QT_VISITA ELSE 0 END) As D10, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 11 THEN a.IMVI_QT_VISITA ELSE 0 END) As D11, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 12 THEN a.IMVI_QT_VISITA ELSE 0 END) As D12, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 13 THEN a.IMVI_QT_VISITA ELSE 0 END) As D13, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 14 THEN a.IMVI_QT_VISITA ELSE 0 END) As D14, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 15 THEN a.IMVI_QT_VISITA ELSE 0 END) As D15, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 16 THEN a.IMVI_QT_VISITA ELSE 0 END) As D16, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 17 THEN a.IMVI_QT_VISITA ELSE 0 END) As D17, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 18 THEN a.IMVI_QT_VISITA ELSE 0 END) As D18, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 19 THEN a.IMVI_QT_VISITA ELSE 0 END) As D19, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 20 THEN a.IMVI_QT_VISITA ELSE 0 END) As D20, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 21 THEN a.IMVI_QT_VISITA ELSE 0 END) As D21, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 22 THEN a.IMVI_QT_VISITA ELSE 0 END) As D22, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 23 THEN a.IMVI_QT_VISITA ELSE 0 END) As D23, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 24 THEN a.IMVI_QT_VISITA ELSE 0 END) As D24, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 25 THEN a.IMVI_QT_VISITA ELSE 0 END) As D25, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 26 THEN a.IMVI_QT_VISITA ELSE 0 END) As D26, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 27 THEN a.IMVI_QT_VISITA ELSE 0 END) As D27, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 28 THEN a.IMVI_QT_VISITA ELSE 0 END) As D28, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 29 THEN a.IMVI_QT_VISITA ELSE 0 END) As D29, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 30 THEN a.IMVI_QT_VISITA ELSE 0 END) As D30, " +
		"SUM(CASE WHEN a.IMVI_NR_DIA = 31 THEN a.IMVI_QT_VISITA ELSE 0 END) As D31  " +
		"from IMOVEL_VISITAS a " +
		"where a.IMVI_NR_ANO = ? " +
		"and a.IMVI_NR_MES = ?";
		
	private DAOFactory daofactory = null;
	
	public FirebirdPainelControleDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public long loadContatosPendentes() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_CONTATOS_PENDENTES);
		qry.setString(0, "P");
		List resultado = qry.list();
		long retorno = 0;
		if (resultado.size() > 0) {
			Integer result = (Integer) resultado.get(0);
			retorno = Long.valueOf(result.toString());
		}
		return retorno;
	}

	public long loadDepoimentosPendentes() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_DEPOIMENTOS_PENDENTES);
		qry.setString(0, "P");
		List resultado = qry.list();
		long retorno = 0;
		if (resultado.size() > 0) {
			Integer result = (Integer) resultado.get(0);
			retorno = Long.valueOf(result.toString());
		}
		return retorno;
	}

	public long loadSomaImovelGeral() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_IMOVEL_ENTRADA_GERAL);
		List resultado = qry.list();
		long retorno = 0;
		if (resultado.size() > 0) {
			Integer result = (Integer) resultado.get(0);
			retorno = Long.valueOf(result.toString());
		}
		return retorno;
	}

	public long loadSomaImovelGeral(int ano, int mes, int dia)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_IMOVEL_ENTRADA_GERAL_ANO_MES_DIA);
		qry.setInteger(0, ano);
		qry.setInteger(1, mes);
		qry.setInteger(2, dia);
		List resultado = qry.list();
		long retorno = 0;
		if (resultado.size() > 0) {
			Integer result = (Integer) resultado.get(0);
			retorno = Long.valueOf(result.toString());
		}
		return retorno;
	}

	public long loadSomaImovelGeral(int ano, int mes)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_IMOVEL_ENTRADA_GERAL_ANO_MES);
		qry.setInteger(0, ano);
		qry.setInteger(1, mes);
		List resultado = qry.list();
		long retorno = 0;
		if (resultado.size() > 0) {
			Integer result = (Integer) resultado.get(0);
			retorno = Long.valueOf(result.toString());
		}
		return retorno;
	}

	public long loadSomaImovelGeral(int ano) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_IMOVEL_ENTRADA_GERAL_ANO);
		qry.setInteger(0, ano);
		List resultado = qry.list();
		long retorno = 0;
		if (resultado.size() > 0) {
			Integer result = (Integer) resultado.get(0);
			retorno = Long.valueOf(result.toString());
		}
		return retorno;
	}

	public long loadSomaVisitasGeral() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_IMOVEL_VISITAS_GERAL);
		List resultado = qry.list();
		long retorno = 0;
		if (resultado.size() > 0) {
			BigInteger result = (BigInteger) resultado.get(0);
			int i = -1;
			retorno = Long.valueOf(result.toString());
		}
		return retorno;
	}

	public long loadSomaVisitasGeral(int ano, int mes, int dia)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_IMOVEL_VISITAS_GERAL_ANO_MES_DIA);
		qry.setInteger(0, ano);
		qry.setInteger(1, mes);
		qry.setInteger(2, dia);
		List resultado = qry.list();
		long retorno = 0;
		if (resultado.size() > 0) {
			BigInteger result = (BigInteger) resultado.get(0);
			retorno = Long.valueOf(result.toString());
		}
		return retorno;
	}

	public long loadSomaVisitasGeral(int ano, int mes)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_IMOVEL_VISITAS_GERAL_ANO_MES);
		qry.setInteger(0, ano);
		qry.setInteger(1, mes);
		List resultado = qry.list();
		long retorno = 0;
		if (resultado.size() > 0) {
			BigInteger result = (BigInteger) resultado.get(0);
			retorno = Long.valueOf(result.toString());
		}
		return retorno;
	}

	public long loadSomaVisitasGeral(int ano) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_IMOVEL_VISITAS_GERAL_ANO);
		qry.setInteger(0, ano);
		List resultado = qry.list();
		long retorno = 0;
		if (resultado.size() > 0) {
			BigInteger result = (BigInteger) resultado.get(0);
			retorno = Long.valueOf(result.toString());
		}
		return retorno;
	}

	public Date loadMaximaDataPublicidade(String tipo) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_MAX_DATA_PUBLICIDADE);
		qry.setString(0,tipo);
		List resultado = qry.list();
		Date retorno = null;
		if (resultado.size() > 0) {
			Date result = (Date) resultado.get(0);
			retorno = result;
		}
		return retorno;
	}

	public List<EstatisticaVisitasDTO> listEstatisticaVisitantes()  throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_VISITAS_ANO_A_ANO);
		List resultado = qry.list();
		List<EstatisticaVisitasDTO> retorno = null;
		if (resultado.size() > 0) {
			retorno = new ArrayList<EstatisticaVisitasDTO>();
			
			for (int j = 0; j < resultado.size(); j++) {
				Object dto[] = (Object[])resultado.get(j);
				
				int i = -1;
				EstatisticaVisitasDTO tpdto = new EstatisticaVisitasDTO();
				tpdto.ano = Integer.valueOf(dto[++i].toString());
				tpdto.totalAno = Long.valueOf(dto[++i].toString());
				tpdto.totalJan = Long.valueOf(dto[++i].toString());
				tpdto.totalFev = Long.valueOf(dto[++i].toString());
				tpdto.totalMar = Long.valueOf(dto[++i].toString());
				tpdto.totalAbr = Long.valueOf(dto[++i].toString());
				tpdto.totalMai = Long.valueOf(dto[++i].toString());
				tpdto.totalJun = Long.valueOf(dto[++i].toString());
				tpdto.totalJul = Long.valueOf(dto[++i].toString());
				tpdto.totalAgo = Long.valueOf(dto[++i].toString());
				tpdto.totalSet = Long.valueOf(dto[++i].toString());
				tpdto.totalOut = Long.valueOf(dto[++i].toString());
				tpdto.totalNov = Long.valueOf(dto[++i].toString());
				tpdto.totalDez = Long.valueOf(dto[++i].toString());
				retorno.add(tpdto);
			}

		}
		return retorno;
	}

	public List<EstatisticaOrigemVisitaDTO> listEstatisticaOrigemVisita(int ano)  throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_VISITAS_ORIGEM_ANO);
		qry.setInteger(0,ano);
		List resultado = qry.list();
		List<EstatisticaOrigemVisitaDTO> retorno = null;
		if (resultado.size() > 0) {
			retorno = new ArrayList<EstatisticaOrigemVisitaDTO>();
			
			for (int j = 0; j < resultado.size(); j++) {
				Object dto[] = (Object[])resultado.get(j);
				
				int i = -1;
				EstatisticaOrigemVisitaDTO tpdto = new EstatisticaOrigemVisitaDTO();
				tpdto.origem = (dto[++i] == null ? null : dto[i].toString());
				tpdto.totalAno = Long.valueOf(dto[++i].toString());
				tpdto.totalJan = Long.valueOf(dto[++i].toString());
				tpdto.totalFev = Long.valueOf(dto[++i].toString());
				tpdto.totalMar = Long.valueOf(dto[++i].toString());
				tpdto.totalAbr = Long.valueOf(dto[++i].toString());
				tpdto.totalMai = Long.valueOf(dto[++i].toString());
				tpdto.totalJun = Long.valueOf(dto[++i].toString());
				tpdto.totalJul = Long.valueOf(dto[++i].toString());
				tpdto.totalAgo = Long.valueOf(dto[++i].toString());
				tpdto.totalSet = Long.valueOf(dto[++i].toString());
				tpdto.totalOut = Long.valueOf(dto[++i].toString());
				tpdto.totalNov = Long.valueOf(dto[++i].toString());
				tpdto.totalDez = Long.valueOf(dto[++i].toString());
				retorno.add(tpdto);
			}

		}
		return retorno;
	}

	public EstatisticaVisitasDiarioDTO loadEstatisticaVisitantesDiario(int ano, int mes) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_VISITAS_DIARIAS);
		qry.setInteger(0,ano);
		qry.setInteger(1,mes);
		List resultado = qry.list();
		EstatisticaVisitasDiarioDTO retorno = null;
		if (resultado.size() > 0) {
			retorno = new EstatisticaVisitasDiarioDTO();
			
			Object dto[] = (Object[])resultado.get(0);
			retorno.total = Long.valueOf(dto[0].toString());
			
			// Pega o resultado dia a dia
			for (int i = 0; i < 31; i++) {
				retorno.dia[i] = Long.valueOf(dto[i + 1].toString());
			}
			
		}
		return retorno;
	}
	
}
