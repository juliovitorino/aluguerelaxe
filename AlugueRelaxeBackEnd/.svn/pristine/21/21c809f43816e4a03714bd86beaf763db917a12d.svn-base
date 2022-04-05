package br.com.jcv.backend.contatoanunciante;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.interfacesdao.ContatoAnuncianteDAO;

public class FirebirdContatoAnuncianteDAO implements ContatoAnuncianteDAO {

	private static final String UPD_STATUS_FILA_ALERTA_24H = "update IMOVEL_CONTATO_ANUNCIANTE set " +
	"IMCA_IN_QUEUED_24H = ?, " +
	"IMCA_DT_QUEUED = CURRENT_TIMESTAMP " +
	"where IMCA_CD_IMOVEL_CONTATO_ANU = ?";
	
	private static final String UPD_SEQ_OFERECIMENTO = "update IMOVEL_CONTATO_ANUNCIANTE set " +
	"IMCA_SQ_OFERECIMENTO = ? " +
	"where IMCA_CD_IMOVEL_CONTATO_ANU = ?";

	private static final String UPD_FOTO_VISITANTE_THREAD = "update IMOVEL_CONTATO_ANUNCIANTE set " +
	"IMCA_TX_IMG_VISITANTE = ? " +
	"where IMCA_CD_IMOVEL_CONTATO_ANU = ?";
	
	private static final String UPD_THREAD_STATUS = "update IMOVEL_CONTATO_ANUNCIANTE set " +
	"IMCA_IN_THREAD_STATUS = ? " +
	"where IMCA_CD_IMOVEL_CONTATO_ANU = ?";
	
	private static final String SQL_BASICO = "Select " +
	"IMCA_CD_IMOVEL_CONTATO_ANU," +
	"IMOV_CD_IMOVEL," +
	"IMCA_NM_PROPOSTO," +
	"IMCA_TX_EMAIL_PROPOSTO," +
	"IMCA_TX_DDD," +
	"IMCA_TX_TELEFONE," +
	"IMCA_QT_ADULTOS," +
	"IMCA_QT_CRIANCAS," +
	"IMCA_TX_ADICIONAL," +
	"IMCA_TX_CIDADE," +
	"IMCA_TX_UF," +
	"IMCA_DT_PREV_CHEGADA," +
	"IMCA_DT_PREV_PARTIDA," +
	"IMCA_TX_PAIS," +
	"IMCA_DT_CADASTRO, " +
	"IMCA_TX_IMG_VISITANTE, " +
	"IMCA_CD_OMC_ANUNCIANTE, " +
	"IMCA_CD_OMC_VISITANTE, " +
	"IMCA_CD_OMC_ADMIN," +
	"IMCA_IN_THREAD_STATUS ";
	
	
	private static final String SQL_PROXIMO_OFERECIMENTO = SQL_BASICO +
	"from IMOVEL_CONTATO_ANUNCIANTE " +
	"where IMCA_SQ_OFERECIMENTO = ( select MIN(IMCA_SQ_OFERECIMENTO) " +
	"                               from IMOVEL_CONTATO_ANUNCIANTE" +
	"							   )";
	
	
	private static final String SQL_BUSCA_PK = SQL_BASICO +
	"from IMOVEL_CONTATO_ANUNCIANTE " +
	"where IMCA_CD_IMOVEL_CONTATO_ANU = ?";
	
	private static final String SQL_PENDENTES_ALERTA_24H = SQL_BASICO +
	"from IMOVEL_CONTATO_ANUNCIANTE " +
	"where IMCA_IN_ALERTA24H = ? " +
	"and IMCA_IN_QUEUED_24H = ? " +
	"and IMCA_IN_STATUS IN (?,?)";
	
	private static final String SQL_STATUS = SQL_BASICO +
	"from IMOVEL_CONTATO_ANUNCIANTE " +
	"where IMCA_IN_STATUS = ? ";
	
	/**
	 * <h2>Atributo daofactory</h2> 
	 * <p>Objeto DAOFactory que contém um encapsulamento para uma sesséo Hibernate</p>
	 */
	private DAOFactory daofactory = null;
	
	public FirebirdContatoAnuncianteDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public ContatoClienteDTO insert(ContatoClienteDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ContatoClienteDTO update(ContatoClienteDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updateFotoThread(String hashParent, String foto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_FOTO_VISITANTE_THREAD);
		int i = 0;
		qry.setString(i++, foto);
		qry.setString(i++, hashParent);
		qry.executeUpdate();
	}

	public void updateStatusThread(String idContato, String status) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_THREAD_STATUS);
		int i = 0;
		qry.setString(i++, status);
		qry.setString(i++, idContato);
		qry.executeUpdate();
	}

	public void updateOferecimento(String id, long seq)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_SEQ_OFERECIMENTO);
		int i = 0;
		qry.setLong(i++, seq);
		qry.setString(i++, id);
		qry.executeUpdate();
	}

	public void updateFilaAlerta24h(String hashID, String status) 
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_STATUS_FILA_ALERTA_24H);
		int i = 0;
		qry.setString(i++, status);
		qry.setString(i++, hashID);
		qry.executeUpdate();
	}

	public void delete(ContatoClienteDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ContatoClienteDTO load(ContatoClienteDTO dto)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCA_PK);
		qry.setString(0, dto.id);
		List resultado = qry.list();
		ContatoClienteDTO clientedto = null;
		if (resultado.size() > 0) {
			clientedto = getDTO((Object[]) resultado.get(0));
		}
		return clientedto;
	}

	
	public List<ContatoClienteDTO> listContatosPendentesAlerta24h() throws AlugueRelaxeException {
		List<ContatoClienteDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_PENDENTES_ALERTA_24H);
		qry.setString(0, "S");
		qry.setString(1, "N");
		qry.setString(2, "L");
		qry.setString(3, "E");
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<ContatoClienteDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				ContatoClienteDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}
	
	public List<ContatoClienteDTO> listContatosAnuncianteStatus(String status) throws AlugueRelaxeException {
		List<ContatoClienteDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_STATUS); 
		qry.setString(0, status);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<ContatoClienteDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				ContatoClienteDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}


	public List<ContatoClienteDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ContatoClienteDTO> list(int pagina)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ContatoClienteDTO> loadProximoOferecimento()
			throws AlugueRelaxeException {
		List<ContatoClienteDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_PROXIMO_OFERECIMENTO);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<ContatoClienteDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				ContatoClienteDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;
	}


	private ContatoClienteDTO getDTO(Object[] dtor) throws AlugueRelaxeException {
		ContatoClienteDTO clientedto = new ContatoClienteDTO();
		int i = -1;
		clientedto.id = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.idImovel = Long.valueOf(dtor[++i].toString());
		clientedto.proposto = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.email = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.ddd = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.telefone = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.qtdeAdultos = Integer.valueOf(dtor[++i].toString());
		clientedto.qtdeCriancas = Integer.valueOf(dtor[++i].toString());
		clientedto.informacoesAdicionais = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.cidade = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.uf = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.chegadaPrevista = (dtor[++i] == null ? null : (Date) dtor[i]);
		clientedto.partidaPrevista = (dtor[++i] == null ? null : (Date) dtor[i]);
		clientedto.pais = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.dataCadastro = (dtor[++i] == null ? null : (Timestamp) dtor[i]);
		clientedto.imgVisitante = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.codigoOMCThreadAnunciante = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.codigoOMCThreadVisitante = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.codigoOMCThreadAdmin = (dtor[++i] == null ? null : dtor[i].toString());
		clientedto.threadStatus = (dtor[++i] == null ? null : dtor[i].toString());
		return clientedto;
	}

}
