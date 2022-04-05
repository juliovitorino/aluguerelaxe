package br.com.jcv.backend.locatario;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.LocatarioDAO;

/**
 * @author julio
 *
 */
public class FirebirdLocatarioDAO implements LocatarioDAO<LocatarioDTO> {

	private static final String INS_CRIAR_NOVA_CONTA = "insert into LOCATARIO_TEMPORADA (" +
	   "LOTE_CD_CLIENTE, " +
	   "LOTE_NM_CLIENTE, " +
	   "LOTE_TX_CPF, " +
	   "LOTE_DT_NASCIMENTO, " +
	   "LOTE_TX_EMAIL, " +
	   "LOTE_TX_DDD, " +
	   "LOTE_TX_TELEFONE, " +
	   "UFCI_CD_UF_CIDADE_ITEM, " +
	   "LOTE_TX_ENDERECO, " +
	   "LOTE_TX_NUMERO, " +
	   "LOTE_TX_COMPLEMENTO, " +
	   "LOTE_TX_BAIRRO, " +
	   "LOTE_TX_CEP " +
	   " ) values ( " +
	   "?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_LOCATARIO = "select " +
	   "     a.LOTE_CD_CLIENTE, " +
	   "     a.LOTE_NM_CLIENTE, " +
	   "     a.LOTE_TX_CPF, " +
	   "     a.LOTE_DT_NASCIMENTO, " +
	   "     a.LOTE_TX_EMAIL, " +
	   "     a.LOTE_DT_CADASTRO, " +
	   "     a.LOTE_TX_DDD, " +
	   "     a.LOTE_TX_TELEFONE, " +
	   "     a.LOTE_TX_ENDERECO, " +
	   "     a.LOTE_TX_NUMERO, " +
	   "     a.LOTE_TX_COMPLEMENTO, " +
	   "     a.LOTE_TX_BAIRRO, " +
	   "     a.LOTE_TX_CEP, " +
	   "     UF_CIDADE_ITEM.UF_CD_ESTADO, " +
	   "     CIDADE.CIDA_NM_CIDADE, " +
	   "     a.UFCI_CD_UF_CIDADE_ITEM " +
	   "from LOCATARIO_TEMPORADA a " +
	   "     Inner Join UF_CIDADE_ITEM on a.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM " +
	   "     Inner Join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE " +
	   "where a.LOTE_TX_EMAIL = ?";

	private static final String SQL_LOCATARIO_PK = "select " +
	   "     a.LOTE_CD_CLIENTE, " +
	   "     a.LOTE_NM_CLIENTE, " +
	   "     a.LOTE_TX_CPF, " +
	   "     a.LOTE_DT_NASCIMENTO, " +
	   "     a.LOTE_TX_EMAIL, " +
	   "     a.LOTE_DT_CADASTRO, " +
	   "     a.LOTE_TX_DDD, " +
	   "     a.LOTE_TX_TELEFONE, " +
	   "     a.LOTE_TX_ENDERECO, " +
	   "     a.LOTE_TX_NUMERO, " +
	   "     a.LOTE_TX_COMPLEMENTO, " +
	   "     a.LOTE_TX_BAIRRO, " +
	   "     a.LOTE_TX_CEP, " +
	   "     UF_CIDADE_ITEM.UF_CD_ESTADO, " +
	   "     CIDADE.CIDA_NM_CIDADE, " +
	   "     a.UFCI_CD_UF_CIDADE_ITEM " +
	   "from LOCATARIO_TEMPORADA a " +
	   "     Inner Join UF_CIDADE_ITEM on a.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM " +
	   "     Inner Join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE " +
	   "where a.LOTE_CD_CLIENTE = ?";
	
	
	private DAOFactory daofactory = null;
	
	public FirebirdLocatarioDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public void delete(LocatarioDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Método sobrescrito.
	 * @see br.com.jcv.backend.interfacesdao.DAO#insert(java.lang.Object)
	 */
	public LocatarioDTO insert(LocatarioDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_CRIAR_NOVA_CONTA);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setString(i++, dto.nome);
		qry.setString(i++, dto.cpf);
		qry.setDate(i++, dto.dataNascimento);
		qry.setString(i++, dto.email);
		qry.setString(i++, dto.telefone.ddd);
		qry.setString(i++, dto.telefone.telefone);
		qry.setLong(i++, dto.endereco.codigoUfCidadeItem);
		qry.setString(i++, dto.endereco.nome);
		qry.setString(i++, dto.endereco.numero );
		qry.setString(i++, dto.endereco.complemento);
		qry.setString(i++, dto.endereco.bairro);
		qry.setString(i++, dto.endereco.cep);
		qry.executeUpdate();
		return dto;
	}

	public LocatarioDTO load(LocatarioDTO dto) throws AlugueRelaxeException {
		LocatarioDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LOCATARIO);
		qry.setString(0, dto.email);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}
	
	public LocatarioDTO load(long id) throws AlugueRelaxeException {
		LocatarioDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LOCATARIO_PK);
		qry.setLong(0, id);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	public LocatarioDTO load(String email) throws AlugueRelaxeException {
		LocatarioDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LOCATARIO);
		qry.setString(0, email);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}

	
	private LocatarioDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		LocatarioDTO dto = new LocatarioDTO();
		int i = -1;
		dto.id = Long.valueOf(registro[++i].toString());
		dto.nome = (registro[++i] == null ? null : registro[i].toString());
		dto.cpf = (registro[++i] == null ? null : registro[i].toString());
		dto.dataNascimento = (registro[++i] == null ? null : (Date) registro[i]);
		dto.email = (registro[++i] == null ? null : registro[i].toString());
		dto.dataCadastro = (registro[++i] == null ? null : (Timestamp) registro[i]);
		dto.telefone = new TelefoneDTO();
		dto.telefone.ddd = (registro[++i] == null ? null : registro[i].toString());
		dto.telefone.telefone = (registro[++i] == null ? null : registro[i].toString());
		dto.endereco = new EnderecoDTO();
		dto.endereco.nome = (registro[++i] == null ? null : registro[i].toString());
		dto.endereco.numero = (registro[++i] == null ? null : registro[i].toString());
		dto.endereco.complemento = (registro[++i] == null ? null : registro[i].toString());
		dto.endereco.bairro = (registro[++i] == null ? null : registro[i].toString());
		dto.endereco.cep = (registro[++i] == null ? null : registro[i].toString());
		dto.endereco.uf = (registro[++i] == null ? null : registro[i].toString());
		dto.endereco.cidade = (registro[++i] == null ? null : registro[i].toString());
		dto.endereco.codigoUfCidadeItem = Long.valueOf(registro[++i].toString());
		return dto;
	}

	public LocatarioDTO update(LocatarioDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LocatarioDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LocatarioDTO> list(int pagina) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
}
