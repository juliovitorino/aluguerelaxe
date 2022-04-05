package br.com.jcv.backend.imovel.faturamento;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.ImovelPlanoFaturaDAO;
import br.com.jcv.backend.plano.PlanoDTO;

public class FirebirdImovelPlanoFaturaDAO implements ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> {
	
	private static final String UPD_STATUS_FATURA = "update IMOVEL_PLANO_FATURA set " +
	"IMPF_IN_STATUS = ? " +
	"where IMPF_CD_IMOVEL_PLANO_FATURA = ?";

	public static final String INS_PLANO = "insert into IMOVEL_PLANO_FATURA (" +
	"IMPF_CD_IMOVEL_PLANO_FATURA, " +
	"IMPL_CD_IMOVEL_PLANO, " +
	"IMPF_VL_FATURA, " +
	"IMPF_VL_DESCONTO, " +
	"IMPF_DT_VENCIMENTO " +
	") values (?,?,?,?,?)";
	
	/* listar todas as faturas de anunciantes pagas e anuncio vencido = bloquear anuncio */
	private static final String SQL_FATURAS_VENCIDAS = "select " +
	"b.IMOV_CD_IMOVEL " +
	"from IMOVEL_PLANO_FATURA a " +
	"inner join IMOVEL_PLANO b on a.IMPL_CD_IMOVEL_PLANO = b.IMPL_CD_IMOVEL_PLANO " +
	"inner join PLANO c on b.PLAN_CD_PLANO = c.PLAN_CD_PLANO " +
	"inner join IMOVEL d on b.IMOV_CD_IMOVEL = d.IMOV_CD_IMOVEL " +
	"where 	a.IMPF_DT_PGTO is not null " +
	"and 	a.IMPF_IN_STATUS = ? " +
	"and 	CURRENT_DATE > a.IMPF_DT_VENCIMENTO " +
	"and 	b.PLAN_CD_PLANO <> ? " +
	"and 	c.PLAN_IN_TIPO_COMPRA = ? " +
	"and 	d.IMOV_IN_STATUS = ?";

	/* listar todas as faturas de anunciantes pendentes e nao pagas e anuncio vencido = bloquear anuncio */
	private static final String SQL_FATURAS_PENDENTES_VENCIDAS = "select " +
	"b.IMOV_CD_IMOVEL " +
	"from IMOVEL_PLANO_FATURA a " +
	"inner join IMOVEL_PLANO b on a.IMPL_CD_IMOVEL_PLANO = b.IMPL_CD_IMOVEL_PLANO " +
	"inner join PLANO c on b.PLAN_CD_PLANO = c.PLAN_CD_PLANO " +
	"where 	a.IMPF_DT_PGTO is null " +
	"and 	a.IMPF_IN_STATUS = ? " +
	"and 	CURRENT_DATE > a.IMPF_DT_VENCIMENTO " +
	"and 	c.PLAN_IN_TIPO_COMPRA = ? ";

	/* listar todas as faturas de anunciantes gratuitas e anuncio vencido = bloquear anuncio */
	private static final String SQL_FATURAS_GRATUITAS_VENCIDAS = "select " +
	"b.IMOV_CD_IMOVEL " +
	"from IMOVEL_PLANO_FATURA a " +
	"inner join IMOVEL_PLANO b on a.IMPL_CD_IMOVEL_PLANO = b.IMPL_CD_IMOVEL_PLANO " +
	"inner join PLANO c on b.PLAN_CD_PLANO = c.PLAN_CD_PLANO " +
	"inner join IMOVEL d on b.IMOV_CD_IMOVEL = d.IMOV_CD_IMOVEL " +
	"where 	a.IMPF_DT_PGTO is not null " +
	"and 	a.IMPF_IN_STATUS = ? " +
	"and 	CURRENT_DATE > a.IMPF_DT_VENCIMENTO " +
	"and 	b.PLAN_CD_PLANO = ? " +
	"and 	c.PLAN_IN_TIPO_COMPRA = ? " +
	"and 	d.IMOV_IN_STATUS = ?";

	/* listar todas as faturas de planos pagos de anunciantes quitadas a vencer anuncio = reiniciar fura-fila*/
	private static final String SQL_FATURAS_A_VENCER = "select " +
	"b.IMOV_CD_IMOVEL " +
	"from IMOVEL_PLANO_FATURA a " +
	"inner join IMOVEL_PLANO b on a.IMPL_CD_IMOVEL_PLANO = b.IMPL_CD_IMOVEL_PLANO " +
	"inner join PLANO c on b.PLAN_CD_PLANO = c.PLAN_CD_PLANO " +
	"inner join IMOVEL d on b.IMOV_CD_IMOVEL = d.IMOV_CD_IMOVEL " +
	"where 	a.IMPF_DT_PGTO is not null " +
	"and 	a.IMPF_IN_STATUS = ? " +
	"and 	CURRENT_DATE < a.IMPF_DT_VENCIMENTO " +
	"and 	b.PLAN_CD_PLANO <> ? " +
	"and 	c.PLAN_IN_TIPO_COMPRA = ? " +
	"and 	d.IMOV_IN_STATUS = ?";


	/* listar todas as faturas de planos pagos de anunciantes quitadas a vencer anuncio dentro do 7 dias */
	private static final String SQL_FATURAS_A_VENCER_7DIAS = "select " +
	"b.IMOV_CD_IMOVEL " +
	"from IMOVEL_PLANO_FATURA a " +
	"inner join IMOVEL_PLANO b on a.IMPL_CD_IMOVEL_PLANO = b.IMPL_CD_IMOVEL_PLANO " +
	"inner join PLANO c on b.PLAN_CD_PLANO = c.PLAN_CD_PLANO " +
	"inner join IMOVEL d on b.IMOV_CD_IMOVEL = d.IMOV_CD_IMOVEL " +
	"where 	a.IMPF_DT_PGTO is not null " +
	"and 	a.IMPF_IN_STATUS = ? " +
	"and 	CURRENT_DATE between a.IMPF_DT_VENCIMENTO - 7 and a.IMPF_DT_VENCIMENTO " +
	"and 	b.PLAN_CD_PLANO <> ? " +
	"and 	c.PLAN_IN_TIPO_COMPRA = ? " +
	"and 	d.IMOV_IN_STATUS = ?";

	/* listar todas as faturas de planos GRATUITOS de anunciantes quitadas a vencer anuncio dentro do 7 dias */
	private static final String SQL_FATURAS_GRATUITAS_A_VENCER_7DIAS = "select " +
	"b.IMOV_CD_IMOVEL " +
	"from IMOVEL_PLANO_FATURA a " +
	"inner join IMOVEL_PLANO b on a.IMPL_CD_IMOVEL_PLANO = b.IMPL_CD_IMOVEL_PLANO " +
	"inner join PLANO c on b.PLAN_CD_PLANO = c.PLAN_CD_PLANO " +
	"inner join IMOVEL d on b.IMOV_CD_IMOVEL = d.IMOV_CD_IMOVEL " +
	"where 	a.IMPF_DT_PGTO is not null " +
	"and 	a.IMPF_IN_STATUS = ? " +
	"and 	CURRENT_DATE between a.IMPF_DT_VENCIMENTO - 7 and a.IMPF_DT_VENCIMENTO " +
	"and 	b.PLAN_CD_PLANO = ? " +
	"and 	c.PLAN_IN_TIPO_COMPRA = ? " +
	"and 	d.IMOV_IN_STATUS = ?";

	
	private static final String SQL_BASICO =	"select " +
												"z.IMPF_CD_IMOVEL_PLANO_FATURA, " +
												"z.IMPL_CD_IMOVEL_PLANO, " +
												"z.IMPF_IN_STATUS, " +
												"z.IMPF_VL_FATURA, " +
												"z.IMPF_VL_DESCONTO, " +
												"z.IMPF_DT_VENCIMENTO, " + 
												"z.IMPF_DT_PGTO, " +
												"z.IMPF_DT_CADASTRO, " +
												"p.PLAN_CD_PLANO, " +
												"p.PLAN_NM_PLANO, " +
												"p.PLAN_TX_DESCRICAO," +
												"p.PLAN_TX_BTNPGTO_PAGSEGURO," +
												"p.PLAN_TX_BTNPGTO_PAYPAL ";
	
	private static final String SQL_IMOVEL_PLANO_FATURA_BUSCA_PK = SQL_BASICO +
		"from IMOVEL_PLANO_FATURA z " +
		"     inner join IMOVEL_PLANO i on i.IMPL_CD_IMOVEL_PLANO = z.IMPL_CD_IMOVEL_PLANO " +
		"     inner join PLANO p on p.PLAN_CD_PLANO = i.PLAN_CD_PLANO " +
		"where z.IMPF_CD_IMOVEL_PLANO_FATURA = ?";
	
	// Busca a ultima fatura de um determinado imovel/tipo do plano e.g. ( Imv = 2;  tipo = Normal)
	private static final String SQL_IMOVEL_PLANO_FATURA_BUSCA_ULTIMA_FATURA = SQL_BASICO +
	  "from IMOVEL_PLANO_FATURA z " +                                                                                 
      "inner join IMOVEL_PLANO i on i.IMPL_CD_IMOVEL_PLANO = z.IMPL_CD_IMOVEL_PLANO " +                      
      "inner join PLANO p on p.PLAN_CD_PLANO = i.PLAN_CD_PLANO " +                                           
      "where z.IMPF_CD_IMOVEL_PLANO_FATURA = ( " +                              
										"select MAX(c.IMPF_CD_IMOVEL_PLANO_FATURA) " +
										"from IMOVEL_PLANO_FATURA c " +
										"where c.IMPL_CD_IMOVEL_PLANO = ( " +
																		"select MAX(IMPL_CD_IMOVEL_PLANO) AS COD_IMPL_CD_IMOVEL_PLANO " +
																		"from IMOVEL_PLANO a " +
																					"inner join PLANO b on b.PLAN_CD_PLANO = a.PLAN_CD_PLANO " +
																		"where a.IMOV_CD_IMOVEL = ? " +
																		"and b.PLAN_IN_TIPO_COMPRA = ? " +
																		")" +
                                ")"  ;  
	
	private DAOFactory daofactory = null;
	
	public FirebirdImovelPlanoFaturaDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public ImovelPlanoFaturaDTO insert(ImovelPlanoFaturaDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_PLANO);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setLong(i++, dto.idImovelPlano);
		qry.setDouble(i++, dto.valorFatura);
		qry.setDouble(i++, dto.valorDesconto);
		qry.setDate(i++, dto.dataVencimento);
		qry.executeUpdate();
		return dto;
	}

	public ImovelPlanoFaturaDTO load(ImovelPlanoFaturaDTO dto) throws AlugueRelaxeException {
		return null;
	}
	
	public ImovelPlanoFaturaDTO loadUltimaFatura(long codigoImovel, String tipo) throws AlugueRelaxeException {
		ImovelPlanoFaturaDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_PLANO_FATURA_BUSCA_ULTIMA_FATURA); 
		qry.setLong(0, codigoImovel);
		qry.setString(1, tipo);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	public ImovelPlanoFaturaDTO loadFatura(long idFatura) throws AlugueRelaxeException {
		ImovelPlanoFaturaDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_PLANO_FATURA_BUSCA_PK); 
		qry.setLong(0, idFatura);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}


	public List<Long> listPlanosPagosAVencer() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_FATURAS_A_VENCER_7DIAS);
		qry.setString(0, "L");
		qry.setLong(1, 1);
		qry.setString(2, "N");
		qry.setString(3, "A");
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());

				lst.add(lngId);
			}
		}
		return lst;
	}

	public List<Long> listPlanosPagosNaoVencidos() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_FATURAS_A_VENCER); 
		qry.setString(0, "L");
		qry.setLong(1, 1);
		qry.setString(2, "N");
		qry.setString(3, "A");
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());

				lst.add(lngId);
			}
		}
		return lst;
	}
		
	public List<Long> listPlanosGratuitosVencidos() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_FATURAS_GRATUITAS_VENCIDAS);
		qry.setString(0, "L");
		qry.setLong(1, 1);
		qry.setString(2, "N");
		qry.setString(3, "A");
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());

				lst.add(lngId);
			}
		}
		return lst;
	}
	
	public List<Long> listPlanosPagosVencidos() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_FATURAS_VENCIDAS);
		qry.setString(0, "L");
		qry.setLong(1, 1);
		qry.setString(2, "N");
		qry.setString(3, "A");
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());

				lst.add(lngId);
			}
		}
		return lst;
	}

	
	public List<Long> listPlanosPendentesVencidos() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_FATURAS_PENDENTES_VENCIDAS);
		qry.setString(0, "P");
		qry.setString(1, "N");
		List resultado = qry.list();
		List<Long> lst = null;
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());

				lst.add(lngId);
			}
		}
		return lst;
	}

	public List<Long> listPlanosGratuitosAVencer() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_FATURAS_GRATUITAS_A_VENCER_7DIAS);
		qry.setString(0, "L");
		qry.setLong(1, 1);
		qry.setString(2, "N");
		qry.setString(3, "A");
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());

				lst.add(lngId);
			}
		}
		return lst;
	}



	private ImovelPlanoFaturaDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		ImovelPlanoFaturaDTO dto = new ImovelPlanoFaturaDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.idImovelPlano = Long.valueOf(registro[++j].toString());
		dto.indicadorStatus = (registro[++j] == null ? null : registro[j].toString());
		dto.valorFatura = Double.valueOf(registro[++j].toString());
		dto.valorDesconto = Double.valueOf(registro[++j].toString());
		dto.dataVencimento = (registro[++j] == null ? null : (Date) registro[j]);
		dto.dataPagamento = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.plano = new PlanoDTO();
		dto.plano.id = Long.valueOf(registro[++j].toString());
		dto.plano.nome = (registro[++j] == null ? null : registro[j].toString());
		dto.plano.descricao = (registro[++j] == null ? null : registro[j].toString());
		dto.plano.htmlBtnPagseguro = (registro[++j] == null ? null : registro[j].toString());
		dto.plano.htmlBtnPayPal = (registro[++j] == null ? null : registro[j].toString());
		
		return dto;
	}

	public ImovelPlanoFaturaDTO update(ImovelPlanoFaturaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(ImovelPlanoFaturaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<ImovelPlanoFaturaDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelPlanoFaturaDTO> list(int pagina)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateStatusFatura(long idFatura,
			String status) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_STATUS_FATURA);
		int i = 0;
		qry.setString(i++, status);
		qry.setLong(i++, idFatura);
		qry.executeUpdate();
	}
	
}

