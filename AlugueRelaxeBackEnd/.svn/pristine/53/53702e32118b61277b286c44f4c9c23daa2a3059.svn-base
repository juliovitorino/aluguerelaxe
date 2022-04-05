package br.com.jcv.backend.imovel.thread;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.interfacesdao.ContatoAnuncianteThreadDAO;


public class FirebirdContatoAnuncianteThreadDAO implements ContatoAnuncianteThreadDAO {
	
	private static final String INS_BASICO = "insert into IMOVEL_CONTATO_THREAD (" +
	"IMCT_ID, " +
	"IMCT_ID_PARENT, " +
	"IMCA_CD_IMOVEL_CONTATO_ANU, " +
	"IMCT_IN_ORIGEM, " +
	"IMCT_IN_STATUS, " +
	"IMCT_IN_MODO, " +
	"IMCT_TX_HASH, " +
	"IMCT_TX_THREAD, " +
	"IMCT_TX_THREAD_EDITADA " +
	") VALUES (?,?,?,?,?,?,?,?,?)";


	private static final String SQL_BASICO = "select " +
	"IMCT_ID, " +
	"IMCT_ID_PARENT, " +
	"IMCA_CD_IMOVEL_CONTATO_ANU, " +
	"IMCT_IN_ORIGEM, " +
	"IMCT_IN_STATUS, " +
	"IMCT_IN_MODO, " +
	"IMCT_DT_CADASTRO, " +
	"IMCT_TX_HASH, " +
	"IMCT_TX_THREAD, " +
	"IMCT_TX_THREAD_EDITADA ";
												
	private static final String SQL_BUSCA_CHILD = SQL_BASICO +
	"from IMOVEL_CONTATO_THREAD " +
	"where IMCT_ID_PARENT = ?";
												
	private static final String SQL_BUSCA_PK = SQL_BASICO +
		"from IMOVEL_CONTATO_THREAD " +
		"where IMCT_ID = ?";

	private static final String SQL_BUSCA_HASH = SQL_BASICO +
		"from IMOVEL_CONTATO_THREAD " +
		"where IMCT_TX_HASH = ?";


	private static final String SQL_LISTAR_THREADS_POR_CONTATO = SQL_BASICO +
	"from IMOVEL_CONTATO_THREAD " +
	"where IMCA_CD_IMOVEL_CONTATO_ANU = ? " +
	"and IMCT_IN_STATUS = ? " +
	"and IMCT_IN_MODO = ? " +
	"and IMCT_ID_PARENT = ? " +
	"order by IMCT_ID";

	private static final String SQL_LISTAR_THREADS_COMENTARIO_IMOVEL = "select " +
	"a.IMCT_ID, "+
	"a.IMCT_ID_PARENT, " +
	"a.IMCA_CD_IMOVEL_CONTATO_ANU, " +
	"a.IMCT_IN_ORIGEM, " +
	"a.IMCT_IN_STATUS, " +
	"a.IMCT_IN_MODO, " +
	"a.IMCT_DT_CADASTRO, " +
	"a.IMCT_TX_HASH, " +
	"a.IMCT_TX_THREAD, " +
	"a.IMCT_TX_THREAD_EDITADA " +
	"from IMOVEL_CONTATO_THREAD a " +
	"inner join IMOVEL_CONTATO_ANUNCIANTE b on a.IMCA_CD_IMOVEL_CONTATO_ANU = b.IMCA_CD_IMOVEL_CONTATO_ANU " +
	"where b.IMOV_CD_IMOVEL = ? " +
	"and a.IMCT_IN_STATUS = ? " +
	"and a.IMCT_IN_MODO = ? " +
	"and a.IMCT_ID_PARENT = ? " +
	"order by a.IMCT_ID";

	private static final String UPD_STATUS = "update IMOVEL_CONTATO_THREAD " +
	"set IMCT_IN_STATUS = ? " +
	"where IMCT_ID = ? " ;
	
	private DAOFactory daofactory = null;
	
	public FirebirdContatoAnuncianteThreadDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}
	
	public List<ContatoAnuncianteThreadDTO> listThreads(String hash, String modo) throws AlugueRelaxeException {
		List<ContatoAnuncianteThreadDTO> lst = null;
		Session session = daofactory.getSession();
		
		Query qry = session.createSQLQuery(SQL_LISTAR_THREADS_POR_CONTATO);
		int  i = 0;
		qry.setString(i++, hash);
		qry.setString(i++, Constantes.ATIVO);
		qry.setString(i++, modo);
		qry.setLong(i++, -1);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<ContatoAnuncianteThreadDTO>();
			for (int j = 0; j < resultado.size(); j++) {
				ContatoAnuncianteThreadDTO dto = getDTO((Object[]) resultado.get(j));
				lst.add(dto);
			}
		}
		return lst;
	}
	
	
	public List<ContatoAnuncianteThreadDTO> listComentarios(long idImovel) throws AlugueRelaxeException {
		List<ContatoAnuncianteThreadDTO> lst = null;
		Session session = daofactory.getSession();
		
		Query qry = session.createSQLQuery(SQL_LISTAR_THREADS_COMENTARIO_IMOVEL);
		int  i = 0;
		qry.setLong(i++, idImovel);
		qry.setString(i++, Constantes.ATIVO);
		qry.setString(i++, "CO");
		qry.setLong(i++, -1);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<ContatoAnuncianteThreadDTO>();
			for (int j = 0; j < resultado.size(); j++) {
				ContatoAnuncianteThreadDTO dto = getDTO((Object[]) resultado.get(j));
				lst.add(dto);
			}
		}
		return lst;
	}
	
	
	public ContatoAnuncianteThreadDTO insert(ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_BASICO);
		int i = 0;
		qry.setLong(i++, catdto.id);
		qry.setLong(i++, catdto.idParent);
		qry.setString(i++, ccdto.id);
		qry.setString(i++, catdto.origem);
		qry.setString(i++, catdto.status);
		qry.setString(i++, catdto.modo);
		qry.setString(i++, catdto.hash);
		qry.setString(i++, catdto.thread);
		qry.setString(i++, catdto.thread);
		qry.executeUpdate();
		return catdto;
	}

	public ContatoAnuncianteThreadDTO load(ContatoAnuncianteThreadDTO dto) throws AlugueRelaxeException {
		ContatoAnuncianteThreadDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCA_PK);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}
	
	public ContatoAnuncianteThreadDTO load(String hash)
			throws AlugueRelaxeException {
		ContatoAnuncianteThreadDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCA_HASH);
		qry.setString(0, hash);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;

	}
	
	public ContatoAnuncianteThreadDTO load(long idParent)
			throws AlugueRelaxeException {
		ContatoAnuncianteThreadDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCA_CHILD);
		int i = 0;
		qry.setLong(i++, idParent);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;

	}


	private ContatoAnuncianteThreadDTO getDTO(Object[] registro) throws AlugueRelaxeException {
	
		ContatoAnuncianteThreadDTO dto = new ContatoAnuncianteThreadDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.idParent = Long.valueOf(registro[++j].toString());
		dto.hashParent = (registro[++j] == null ? null : registro[j].toString());
		dto.origem = (registro[++j] == null ? null : registro[j].toString());
		dto.status = (registro[++j] == null ? null : registro[j].toString());
		dto.modo = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.hash = (registro[++j] == null ? null : registro[j].toString());
		dto.thread = (registro[++j] == null ? null : registro[j].toString());
		dto.threadEditada = (registro[++j] == null ? null : registro[j].toString());
		return dto;
	}

	public ContatoAnuncianteThreadDTO update(ContatoAnuncianteThreadDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(ContatoAnuncianteThreadDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<ContatoAnuncianteThreadDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ContatoAnuncianteThreadDTO> list(int pagina)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateStatus(long id, String status)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_STATUS);
		int i = 0;
		qry.setString(i++, status);
		qry.setLong(i++, id);
		qry.executeUpdate();
	}

	public ContatoAnuncianteThreadDTO insert(ContatoAnuncianteThreadDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}



}

