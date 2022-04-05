package br.com.jcv.backend.reserva;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.interfacesdao.ReservaDAO;
import br.com.jcv.backend.locatario.LocatarioDTO;

public class FirebirdReservaDAO implements ReservaDAO<ReservaDTO> {

	public static final String UPD_TRANSFERENCIA_DEPOSITO = "update RESERVA set " +
	"RESE_DT_TRANS_DEPOSITO = CURRENT_TIMESTAMP, " +
	"RESE_VL_TRANS_DEPOSITO = ? " +
	"where RESE_CD_RESERVA = ?";

	public static final String UPD_APROVACAO_PRE_RESERVA = "update RESERVA set " +
	"RESE_DT_VALIDACAO_RESERVA = CURRENT_TIMESTAMP " +
	"where RESE_TX_TOKEN = ?";

	public static final String UPD_REPROVACAO_PRE_RESERVA = "update RESERVA set " +
	"RESE_DT_REPROVACAO_RESERVA = CURRENT_TIMESTAMP " +
	"where RESE_TX_TOKEN = ?";

	public static final String UPD_APROVACAO_PRE_RESERVA_TRACKER = "update RESERVA set " +
	"RESE_DT_VALIDACAO_RESERVA = CURRENT_TIMESTAMP " +
	"where RESE_TX_CHAVE_TRACKER = ?";

	public static final String UPD_REPROVACAO_PRE_RESERVA_TRACKER = "update RESERVA set " +
	"RESE_DT_REPROVACAO_RESERVA = CURRENT_TIMESTAMP " +
	"where RESE_TX_CHAVE_TRACKER = ?";


	public static final String UPD_LIBERACAO_DEPOSITO = "update RESERVA set " +
	"RESE_DT_CHAVE_VALIDACAO_CHECK = CURRENT_TIMESTAMP, " +
	"RESE_TX_CHAVE_VALIDACAO_CHECK = ? " +
	"where RESE_TX_CHAVE_TRACKER = ?";

	public static final String UPD_PGTO_RESERVA = "update RESERVA set " +
		"RESE_DT_REAL_DEPOSITO = CURRENT_TIMESTAMP," +
		"RESE_VL_REAL_DEPOSITO = ?, " + 
		"RESE_TX_URL_CTR_LOCADOR = ?, " + 
		"RESE_TX_URL_CTR_LOCATARIO = ? " + 
		"where RESE_TX_CHAVE_TRACKER = ?";

	public static final String UPD_AVALIACAO_RESERVA = "update RESERVA set " +
	"RESE_DT_AVALIACAO = CURRENT_TIMESTAMP, " +
	"RESE_NU_NOTA_IMOVEL = ?, " +
	"RESE_NU_NOTA_ANFITRIAO = ?  " +
	"where RESE_CD_RESERVA = ?";
	
	public static final String INS_RESERVA = "insert into RESERVA (" +
		"RESE_CD_RESERVA," + 
		"LOTE_CD_CLIENTE," + 
		"RESE_DT_CHECKIN," +
		"RESE_DT_CHECKOUT," +
		"IMOV_CD_IMOVEL," +
		"RESE_DT_PREV_DEPOSITO," +
		"RESE_VL_PREV_DEPOSITO," +
		"RESE_TX_TOKEN," +
		"RESE_TX_CHAVE_LIBERACAO," +
		"RESE_TX_CHAVE_TRACKER," +
		"RESE_VL_ALUGUEL_NEGOCIADO," +
		"RESE_TX_TITULO_ANUNCIO," +
		"RESE_IN_FORMA_PGTO," +
		"RESE_VL_CAUCAO," +
		"RESE_PC_COMISSAO_RESERVA," +
		"RESE_VL_TAXA_SERVICO," +
		"IMCA_CD_IMOVEL_CONTATO_ANU," +
		"RESE_TX_IMG_VISITANTE" +
		") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String SQL_BASICO = "select " +
		"RESE_CD_RESERVA," +
		"LOTE_CD_CLIENTE," +
		"RESE_DT_CHECKIN," +
		"RESE_DT_CHECKOUT," +
		"IMOV_CD_IMOVEL," +
		"RESE_DT_PREV_DEPOSITO," +
		"RESE_VL_PREV_DEPOSITO," +
		"RESE_DT_CADASTRO," +
		"RESE_TX_TOKEN," +
		"RESE_TX_CHAVE_LIBERACAO," +
		"RESE_DT_EMAIL_LIBERACAO," +
		"RESE_DT_VALIDACAO_RESERVA," +
		"RESE_DT_REAL_DEPOSITO," +
		"RESE_VL_REAL_DEPOSITO," +
		"RESE_TX_URL_CTR_LOCADOR," +
		"RESE_TX_URL_CTR_LOCATARIO," +
		"RESE_TX_CHAVE_VALIDACAO_CHECK," +
		"RESE_DT_CHAVE_VALIDACAO_CHECK," +
		"RESE_DT_TRANS_DEPOSITO," +
		"RESE_VL_TRANS_DEPOSITO," +
		"RESE_TX_CHAVE_TRACKER," +
		"RESE_VL_ALUGUEL_NEGOCIADO," +
		"RESE_TX_TITULO_ANUNCIO," +
		"RESE_IN_FORMA_PGTO," + 
		"RESE_DT_REPROVACAO_RESERVA," +
		"RESE_VL_CAUCAO," +
		"RESE_PC_COMISSAO_RESERVA," +
		"RESE_DT_AVALIACAO," +
		"RESE_VL_TAXA_SERVICO," +
		"IMCA_CD_IMOVEL_CONTATO_ANU," +
		"RESE_TX_IMG_VISITANTE," +
		"RESE_NU_NOTA_IMOVEL," +
		"RESE_NU_NOTA_ANFITRIAO " ;

	
	private static final String SQL_RESERVA_BUSCA_PK = SQL_BASICO +
		" from RESERVA " +
		" where RESE_CD_RESERVA = ?";
		
	private static final String SQL_RESERVA_BUSCA_TOKEN = SQL_BASICO +
		" from RESERVA " +
		" where RESE_TX_TOKEN = ?";
	
	private static final String SQL_RESERVA_BUSCA_CHAVE_TRACKER = SQL_BASICO +
	" from RESERVA " +
	" where RESE_TX_CHAVE_TRACKER = ?";
	
	private static final String SQL_RESERVA_BUSCA_HASH_CONTATO = SQL_BASICO +
	" from RESERVA " +
	" where IMCA_CD_IMOVEL_CONTATO_ANU = ?";
	
	private static final String SQL_TAREFAS_PENDENTES_APROVACAO = SQL_BASICO + 
	" from RESERVA " +
	" where RESE_DT_CADASTRO is not null " +
	" and RESE_DT_VALIDACAO_RESERVA is null";

	private static final String SQL_TAREFAS_PENDENTES_CONFIRMACAO_DEPOSITO = SQL_BASICO + 
	" from RESERVA " +
	" where RESE_DT_VALIDACAO_RESERVA is not null" +
	" and   RESE_DT_REAL_DEPOSITO is null";
	
	private static final String SQL_TAREFAS_PENDENTES_LIBERACAO = SQL_BASICO + 
	" from RESERVA " +
	" where RESE_DT_REAL_DEPOSITO is not null" +
	" and   RESE_DT_CHAVE_VALIDACAO_CHECK is null";
	
	private static final String SQL_TAREFAS_PENDENTES_TRANSFERENCIA = SQL_BASICO + 
	" from RESERVA " +
	" where RESE_DT_CHAVE_VALIDACAO_CHECK is not null" +
	" and   RESE_DT_TRANS_DEPOSITO is null";
	
	private static final String SQL_TAREFAS_PENDENTES_AVALIACAO = SQL_BASICO + 
	" from RESERVA " +
	" where RESE_DT_TRANS_DEPOSITO is not null" +
	" and   RESE_DT_AVALIACAO is null";
	
	private DAOFactory daofactory = null;
	
	public FirebirdReservaDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}
	
	public ReservaDTO insert(ReservaDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_RESERVA);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setLong(i++, dto.locatario.id);
		qry.setTimestamp(i++, dto.dataCheckin);
		qry.setTimestamp(i++, dto.dataCheckout);
		qry.setLong(i++, dto.ifcdto.imovel.id);
		qry.setDate(i++, dto.dataPrevistaDeposito);
		qry.setDouble(i++, dto.valorPrevistoDeposito);
		qry.setString(i++, dto.token);
		qry.setString(i++, dto.chaveLiberacaoDeposito);
		qry.setString(i++, dto.chaveTracker);
		qry.setDouble(i++, dto.valorAluguelNegociado);
		qry.setString(i++, dto.ifcdto.imovel.descricaoTituloAnuncio);
		qry.setString(i++, dto.formaPagamento);
		qry.setDouble(i++, dto.valorCaucao);
		qry.setDouble(i++, dto.percentualComissao);
		qry.setDouble(i++, dto.valorTaxaServico);
		qry.setString(i++, dto.idContato);
		qry.setString(i++, dto.imgVisitante);
		qry.executeUpdate();
		return dto;
	}
	
	public ReservaDTO load(ReservaDTO dto) throws AlugueRelaxeException {
		ReservaDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_RESERVA_BUSCA_PK);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}
	
	public ReservaDTO loadToken(String token) throws AlugueRelaxeException {
		ReservaDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_RESERVA_BUSCA_TOKEN);
		qry.setString(0, token);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}
	
	public ReservaDTO loadChaveTracker(String ct) throws AlugueRelaxeException {
		ReservaDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_RESERVA_BUSCA_CHAVE_TRACKER);
		qry.setString(0, ct);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}
	
	public void updateLiberacaoDeposito(String tracking, String voucher)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_LIBERACAO_DEPOSITO);
		int i = 0;
		qry.setString(i++, voucher);
		qry.setString(i++, tracking);
		qry.executeUpdate();
	}

	
	public void updateAprovacaoPreReserva(String token)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_APROVACAO_PRE_RESERVA);
		int i = 0;
		qry.setString(i++, token);
		qry.executeUpdate();
	}

	public void updateAprovacaoPreReservaTracker(String chave)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_APROVACAO_PRE_RESERVA_TRACKER);
		int i = 0;
		qry.setString(i++, chave);
		qry.executeUpdate();
	}


	public void updateReprovacaoPreReserva(String token)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_REPROVACAO_PRE_RESERVA);
		int i = 0;
		qry.setString(i++, token);
		qry.executeUpdate();
	}


	public void updateReprovacaoPreReservaTracker(String chave)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_REPROVACAO_PRE_RESERVA_TRACKER);
		int i = 0;
		qry.setString(i++, chave);
		qry.executeUpdate();
	}



	public void updatePagamentoReserva(String token, 
			Date dataRealDeposito,
			double valorRealDeposito,
			String urlContratoLocador,
			String urlContratoLocatario) throws AlugueRelaxeException { 
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_PGTO_RESERVA);
		int i = 0;
		qry.setDouble(i++, valorRealDeposito);
		qry.setString(i++, urlContratoLocador);
		qry.setString(i++, urlContratoLocatario);
		qry.setString(i++, token);
		qry.executeUpdate();
	}



	private ReservaDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		ReservaDTO dto = new ReservaDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.locatario = new LocatarioDTO();
		dto.locatario.id = Long.valueOf(registro[++j].toString());
		dto.dataCheckin = (registro[++j] == null ? null : (Date) registro[j]);
		dto.dataCheckout = (registro[++j] == null ? null : (Date) registro[j]);
		dto.ifcdto = new ImovelFichaCompletaDTO();
		dto.ifcdto.imovel = new ImovelDTO();
		dto.ifcdto.imovel.id = Long.valueOf(registro[++j].toString());
		dto.dataPrevistaDeposito = (registro[++j] == null ? null : (Date) registro[j]);
		dto.valorPrevistoDeposito = (registro[++j] == null ? 0 : Double.valueOf(registro[j].toString()));
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.token = (registro[++j] == null ? null : registro[j].toString());
		dto.chaveLiberacaoDeposito = (registro[++j] == null ? null : registro[j].toString());
		dto.dataEmailLiberacao = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.dataValidacaoPreReserva = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.dataRealDeposito = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.valorRealDeposito = (registro[++j] == null ? 0 : Double.valueOf(registro[j].toString()));
		dto.urlContratoLocador = (registro[++j] == null ? null : registro[j].toString());
		dto.urlContratoLocatario = (registro[++j] == null ? null : registro[j].toString());
		dto.chaveLiberacaoCheck = (registro[++j] == null ? null : registro[j].toString());
		dto.dataChaveLiberacaoCheck = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.dataTranferenciaDeposito = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.valorTransferenciaDeposito = (registro[++j] == null ? 0 : Double.valueOf(registro[j].toString()));
		dto.chaveTracker = (registro[++j] == null ? null : registro[j].toString());
		dto.valorAluguelNegociado = (registro[++j] == null ? 0 : Double.valueOf(registro[j].toString()));
		dto.ifcdto.imovel.descricaoTituloAnuncio = (registro[++j] == null ? null : registro[j].toString());
		dto.formaPagamento = (registro[++j] == null ? null : registro[j].toString());
		dto.dataReprovacao = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.valorCaucao = (registro[++j] == null ? 0 : Double.valueOf(registro[j].toString()));
		dto.percentualComissao = (registro[++j] == null ? 0 : Double.valueOf(registro[j].toString()));
		dto.dataAvaliacao = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.valorTaxaServico = (registro[++j] == null ? 0 : Double.valueOf(registro[j].toString()));
		dto.idContato = (registro[++j] == null ? null : registro[j].toString());
		dto.imgVisitante = (registro[++j] == null ? null : registro[j].toString());
		dto.notaImovel = (registro[++j] == null ? 0 : Integer.valueOf(registro[j].toString()));
		dto.notaAnfitriao = (registro[++j] == null ? 0 : Integer.valueOf(registro[j].toString()));
		return dto;
	}

	public ReservaDTO update(ReservaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(ReservaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<ReservaDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ReservaDTO> list(int pagina) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateTransferenciaDeposito(long idReserva,
			double vlTransferencia) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_TRANSFERENCIA_DEPOSITO);
		int i = 0;
		qry.setDouble(i++, vlTransferencia);
		qry.setLong(i++, idReserva);
		qry.executeUpdate();
	}

	public List<ReservaDTO> listTarefasPendentes(int fase)
			throws AlugueRelaxeException {
		List<ReservaDTO> lst = null;
		Session session = daofactory.getSession();
		
		// Seleciona a query
		Query qry = null;
		
		switch (fase) {
		case ReservaBusinessImpl.FASE_APROVACAO:
			qry = session.createSQLQuery(SQL_TAREFAS_PENDENTES_APROVACAO);
			break;
		case ReservaBusinessImpl.FASE_CONFIRMACAO_DEPOSITO:
			qry = session.createSQLQuery(SQL_TAREFAS_PENDENTES_CONFIRMACAO_DEPOSITO);
			break;
		case ReservaBusinessImpl.FASE_AVALIACAO:
			qry = session.createSQLQuery(SQL_TAREFAS_PENDENTES_AVALIACAO);
			break;
		case ReservaBusinessImpl.FASE_LIBERACAO:
			qry = session.createSQLQuery(SQL_TAREFAS_PENDENTES_LIBERACAO);
			break;
		case ReservaBusinessImpl.FASE_TRANSFERENCIA:
			qry = session.createSQLQuery(SQL_TAREFAS_PENDENTES_TRANSFERENCIA);
			break;

		default:
			break;
		}
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<ReservaDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				ReservaDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;

	}

	public ReservaDTO loadReserva(String hashContatoAnunciante)
			throws AlugueRelaxeException {
		ReservaDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_RESERVA_BUSCA_HASH_CONTATO);
		qry.setString(0, hashContatoAnunciante);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	public void updateComentario(AvaliacaoReservaDTO dto)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_AVALIACAO_RESERVA);
		int i = 0;
		qry.setInteger(i++, dto.reserva.notaImovel);
		qry.setInteger(i++, dto.reserva.notaAnfitriao);
		qry.setLong(i++, dto.reserva.id);
		qry.executeUpdate();
	}


}