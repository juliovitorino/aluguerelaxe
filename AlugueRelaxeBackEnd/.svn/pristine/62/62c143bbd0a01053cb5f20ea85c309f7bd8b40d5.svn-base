package br.com.jcv.backend.cliente;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.interfacesdao.ClienteDAO;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeDTO;
import br.com.jcv.backend.portal.NovoCodigoAcessoDTO;
import br.com.jcv.backend.portal.login.LoginDTO;

/**
 * @author julio
 *
 */
public class FirebirdClienteDAO implements ClienteDAO<ClienteDTO> {

	private static final String SQL_SELECT_CLIENTE_ID = "Select " + 
		"CLIE_CD_CLIENTE ";
		
	private static final String SQL_SELECT_CLIENTE = "Select " + 
		"CLIE_CD_CLIENTE," +
		"CLIE_NM_CLIENTE," +
		"CLIE_TX_CPF," +
		"CLIE_TX_CGC," +
		"CLIE_DT_NASCIMENTO," +
	    "CLIE_CD_NOVO_ACESSO, " +
		"CLIE_TX_EMAIL," +
		"CLIE_TX_SENHA," +
		"CLIE_DT_CADASTRO," +
		"CLIE_DT_ATUALIZACAO," +
		"CLIE_IN_STATUS," +
		"CLIE_TX_URL," +
		"CLIE_IN_AUTORIZA_NOTIFICACAO," +
		"CLIE_TX_MSN," +
		"CLIE_TX_SKYPE," +
		"CLIE_TX_GOOGLE_TALK," +
		"UFCI_CD_UF_CIDADE_ITEM," +
		"CLIE_TX_ENDERECO," +
		"CLIE_TX_NUMERO," +
		"CLIE_TX_COMPLEMENTO," +
		"CLIE_TX_BAIRRO," +
		"CLIE_TX_CEP," +
		"CLIE_IN_TIPO_ANUNCIANTE," +
		"CLIE_TX_BANCO," +
		"CLIE_TX_AGENCIA," +
		"CLIE_TX_CONTA_CORRENTE," +
		"CLIE_TX_FOTO_PERFIL," +
		"CLIE_TX_FOTO_CHAT," +
		"CLIE_NU_PERG,"+
		"CLIE_NU_RESP," +
		"CLIE_IN_VERIFICADO " ;
		
	private static final String SQL_SELECT_COUNT_ANUNCIANTES_IMOVEL_PLANO = "select count(*) " +
		"from cliente a " +
	    "inner join imovel b on a.clie_cd_cliente = b.clie_cd_cliente " +
	    "inner join imovel_plano c on b.imov_cd_imovel = c.imov_cd_imovel " +
		"where c.plan_cd_plano = ? " +
		"and   a.clie_cd_cliente = ?";
	
	private static final String DEL_CLIENTE = "delete from CLIENTE ";
	
	private static final String DEL_CLIENTE_ID =  DEL_CLIENTE +
		"where CLIE_CD_CLIENTE = ?";

	private static final String UPD_INC_PERGUNTAS = "update CLIENTE set " +
		"CLIE_NU_PERG = CLIE_NU_PERG + 1 " +
		"where CLIE_CD_CLIENTE = ?";

	private static final String UPD_INC_RESPOSTAS = "update CLIENTE set " +
		"CLIE_NU_RESP = CLIE_NU_RESP + 1 " +
		"where CLIE_CD_CLIENTE = ?";
	
	private static final String UPD_CLIENTE = "update CLIENTE set " + 
		"CLIE_NM_CLIENTE = ?," +
		"CLIE_TX_CPF = ?," +
		"CLIE_TX_CGC = ?," +
		"CLIE_DT_NASCIMENTO = ?," +
		"CLIE_TX_EMAIL = ?," +
		"CLIE_DT_ATUALIZACAO = CURRENT_TIMESTAMP," +
		"CLIE_TX_URL = ?," +
		"CLIE_TX_MSN = ?," +
		"CLIE_TX_SKYPE = ?," +
		"CLIE_TX_GOOGLE_TALK = ?," +
		"UFCI_CD_UF_CIDADE_ITEM = ?," +
		"CLIE_TX_ENDERECO = ?," +
		"CLIE_TX_NUMERO = ?," +
		"CLIE_TX_COMPLEMENTO = ?," +
		"CLIE_TX_BAIRRO = ?," +
		"CLIE_TX_CEP = ?," +
		"CLIE_TX_BANCO = ?," +
		"CLIE_TX_AGENCIA = ?," +
		"CLIE_TX_CONTA_CORRENTE = ? " +
		"where CLIE_CD_CLIENTE = ?";

	private static final String UPD_NOVO_CODIGO_ACESSO = "update CLIENTE set " + 
	"CLIE_CD_NOVO_ACESSO = ? " +
	"where CLIE_TX_EMAIL = ?";

	private static final String UPD_TROCA_SENHA = "update CLIENTE set " + 
	"CLIE_DT_ATUALIZACAO = CURRENT_TIMESTAMP," +
	"CLIE_TX_SENHA = ? " +
	"where CLIE_CD_CLIENTE = ?";
	
	public static final String UPD_ENQUETE_MODO_PUBLICIDADE = "update CLIENTE set " +
	"MOPU_CD_MODO_PUBLICIDADE = ? " +
	"where CLIE_CD_CLIENTE = ?";

	public static final String UPD_FOTO_CHAT = "update CLIENTE set " +
	"CLIE_TX_FOTO_CHAT = ? " +
	"where CLIE_CD_CLIENTE = ?";

	public static final String UPD_FOTO_PERFIL = "update CLIENTE set " +
	"CLIE_TX_FOTO_PERFIL = ? " +
	"where CLIE_CD_CLIENTE = ?";
	
	private static final String DEL_CLIENTE_TELEFONES = "delete from TELEFONE where CLIE_CD_CLIENTE = ?";

	private static final String INS_CLIENTE_TELEFONES = "insert into TELEFONE (" +
		"TELE_CD_TELEFONE," +
		"CLIE_CD_CLIENTE," + 
		"TELE_NM_CONTATO," + 
		"TELE_TX_DDD," + 
		"TELE_TX_TELEFONE," + 
		"TELE_IN_PERMITE_EXIBIR," +
		"TELE_IN_TIPO_TELEFONE " +
		") values (GEN_ID(SEQ_TELE_CD_TELEFONE,1),?,?,?,?,?,?)";

	
	private static final String INS_CRIAR_NOVA_CONTA = "insert into cliente (" +
	   "CLIENTE.CLIE_CD_CLIENTE, " +
	   "CLIENTE.CLIE_NM_CLIENTE, " +
	   "CLIENTE.CLIE_TX_CPF, " +
	   "CLIENTE.CLIE_TX_CGC, " +
	   "CLIENTE.CLIE_DT_NASCIMENTO, " +
	   "CLIENTE.CLIE_TX_EMAIL, " +
	   "CLIENTE.CLIE_TX_SENHA, " +
	   "CLIENTE.CLIE_TX_URL, " +
	   "CLIENTE.CLIE_IN_AUTORIZA_NOTIFICACAO, " +
	   "CLIENTE.CLIE_TX_MSN, " +
	   "CLIENTE.CLIE_TX_SKYPE, " +
	   "CLIENTE.CLIE_TX_GOOGLE_TALK, " +
	   "CLIENTE.CLIE_TX_CODIGO_ATIVACAO, " +
	   "CLIENTE.UFCI_CD_UF_CIDADE_ITEM, " +
	   "CLIENTE.CLIE_IN_TIPO_ANUNCIANTE, " +
	   "CLIENTE.CLIE_TX_ENDERECO, " +
	   "CLIENTE.CLIE_TX_NUMERO, " +
	   "CLIENTE.CLIE_TX_COMPLEMENTO, " +
	   "CLIENTE.CLIE_TX_BAIRRO, " +
	   "CLIENTE.CLIE_TX_CEP, " +
	   "MOPU_CD_MODO_PUBLICIDADE " +
	   " ) values ( " +
	   "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String UPD_ATIVAR_NOVA_CONTA = "update cliente " +
	"set CLIE_IN_STATUS = ? " +
	"where CLIE_TX_CODIGO_ATIVACAO = ?";

	private static final String SQL_OBTER_STATUS_PELO_HASH = "select " +
	   "CLIENTE.CLIE_CD_CLIENTE, " +
	   "CLIENTE.CLIE_NM_CLIENTE, " +
	   "CLIENTE.CLIE_TX_CPF, " +
	   "CLIENTE.CLIE_TX_CGC, " +
	   "CLIENTE.CLIE_DT_NASCIMENTO, " +
	   "CLIENTE.CLIE_TX_EMAIL, " +
	   "CLIENTE.CLIE_DT_CADASTRO, " +
	   "CLIENTE.CLIE_DT_ATUALIZACAO, " +
	   "CLIENTE.CLIE_IN_STATUS, " +
	   "CLIENTE.CLIE_TX_URL, " +
	   "CLIENTE.CLIE_IN_AUTORIZA_NOTIFICACAO, " +
	   "CLIENTE.CLIE_TX_MSN, " +
	   "CLIENTE.CLIE_TX_SKYPE, " +
	   "CLIENTE.CLIE_TX_GOOGLE_TALK, " +
	   "CLIENTE.CLIE_IN_TIPO_ANUNCIANTE, " +
	   "CLIENTE.CLIE_TX_CODIGO_ATIVACAO, " +
		"CLIENTE.CLIE_TX_BANCO," +
		"CLIENTE.CLIE_TX_AGENCIA," +
		"CLIENTE.CLIE_TX_CONTA_CORRENTE," +
		"CLIENTE.CLIE_TX_FOTO_PERFIL," +
		"CLIENTE.CLIE_TX_FOTO_CHAT," +
		"CLIENTE.CLIE_NU_PERG,"+
		"CLIENTE.CLIE_NU_RESP," +		
		"CLIENTE.CLIE_IN_VERIFICADO " +
	   "from CLIENTE " +
	   "where CLIENTE.CLIE_TX_CODIGO_ATIVACAO = ?";
	
	private static final String SQL_OBTER_CLIENTE_PELO_EMAIL = SQL_SELECT_CLIENTE +
		" from CLIENTE " +
		"where CLIENTE.CLIE_TX_EMAIL = ?";
		
	private static final String SQL_CONTA_PENDENTE_ATIVACAO = "select " +
	   "     CLIENTE.CLIE_CD_CLIENTE, " +
	   "     CLIENTE.CLIE_NM_CLIENTE, " +
	   "     CLIENTE.CLIE_TX_CPF, " +
	   "     CLIENTE.CLIE_TX_CGC, " +
	   "     CLIENTE.CLIE_DT_NASCIMENTO, " +
	   "     CLIENTE.CLIE_TX_EMAIL, " +
	   "     CLIENTE.CLIE_DT_CADASTRO, " +
	   "     CLIENTE.CLIE_DT_ATUALIZACAO, " +
	   "     CLIENTE.CLIE_IN_STATUS, " +
	   "     CLIENTE.CLIE_TX_URL, " +
	   "     CLIENTE.CLIE_IN_AUTORIZA_NOTIFICACAO, " +
	   "     CLIENTE.CLIE_TX_MSN, " +
	   "     CLIENTE.CLIE_TX_SKYPE, " +
	   "     CLIENTE.CLIE_TX_GOOGLE_TALK, " +
	   "     CLIENTE.CLIE_IN_TIPO_ANUNCIANTE, " +
	   "     CLIENTE.CLIE_TX_CODIGO_ATIVACAO " +
	   "from CLIENTE " +
	   "where CLIENTE.CLIE_IN_STATUS = ?";

	private static final String SQL_CLIENTE = "select " +
	   "     CLIENTE.CLIE_CD_CLIENTE, " +
	   "     CLIENTE.CLIE_NM_CLIENTE, " +
	   "     CLIENTE.CLIE_TX_CPF, " +
	   "     CLIENTE.CLIE_TX_CGC, " +
	   "     CLIENTE.CLIE_DT_NASCIMENTO, " +
	   "     CLIENTE.CLIE_CD_NOVO_ACESSO, " +
	   "     CLIENTE.CLIE_TX_EMAIL, " +
	   "     CLIENTE.CLIE_DT_CADASTRO, " +
	   "     CLIENTE.CLIE_DT_ATUALIZACAO, " +
	   "     CLIENTE.CLIE_IN_STATUS, " +
	   "     CLIENTE.CLIE_TX_URL, " +
	   "     CLIENTE.CLIE_IN_AUTORIZA_NOTIFICACAO, " +
	   "     CLIENTE.CLIE_TX_MSN, " +
	   "     CLIENTE.CLIE_TX_SKYPE, " +
	   "     CLIENTE.CLIE_TX_GOOGLE_TALK, " +
	   "     CLIENTE.CLIE_IN_TIPO_ANUNCIANTE, " +
	   "     CLIENTE.CLIE_TX_ENDERECO, " +
	   "     CLIENTE.CLIE_TX_NUMERO, " +
	   "     CLIENTE.CLIE_TX_COMPLEMENTO, " +
	   "     CLIENTE.CLIE_TX_BAIRRO, " +
	   "     CLIENTE.CLIE_TX_CEP, " +
	   "     UF_CIDADE_ITEM.UF_CD_ESTADO, " +
	   "     CIDADE.CIDA_NM_CIDADE, " +
	   "     CLIENTE.UFCI_CD_UF_CIDADE_ITEM, " +
		"CLIENTE.CLIE_TX_BANCO," +
		"CLIENTE.CLIE_TX_AGENCIA," +
		"CLIENTE.CLIE_TX_CONTA_CORRENTE, " +
		"CLIENTE.MOPU_CD_MODO_PUBLICIDADE, " +
		"CLIENTE.CLIE_TX_CODIGO_ATIVACAO," +
		"CLIENTE.CLIE_TX_FOTO_PERFIL," +
		"CLIENTE.CLIE_TX_FOTO_CHAT," +
		"CLIENTE.CLIE_NU_PERG,"+
		"CLIENTE.CLIE_NU_RESP, " +		
		"CLIENTE.CLIE_IN_VERIFICADO " +
	   "from CLIENTE " +
	   "     Inner Join UF_CIDADE_ITEM on CLIENTE.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM " +
	   "     Inner Join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE " +
	   "where CLIENTE.CLIE_CD_CLIENTE = ?";
	
	private static final String SQL_CLIENTE_TELEFONES = "select " + 
	"TELE_NM_CONTATO, " +
	"TELE_TX_DDD, " +
	"TELE_TX_TELEFONE, " +
	"TELE_IN_PERMITE_EXIBIR, " +
	"TELE_IN_TIPO_TELEFONE " +
	"from TELEFONE " +
	"where CLIE_CD_CLIENTE = ?";
	
	private static final String SQL_BUSCAR_CONTATOS_ANUNCIANTE = "select " +
	    "a.IMCA_CD_IMOVEL_CONTATO_ANU, " +
	    "a.IMOV_CD_IMOVEL, " +
	    "a.IMCA_NM_PROPOSTO, " +
	    "a.IMCA_TX_EMAIL_PROPOSTO, " +
	    "a.IMCA_TX_DDD, " +
	    "a.IMCA_TX_TELEFONE, " +
	    "a.IMCA_TX_CIDADE, " +
	    "a.IMCA_TX_UF, " +
	    "a.IMCA_DT_PREV_CHEGADA, " +
	    "a.IMCA_DT_CADASTRO " +
		"from IMOVEL_CONTATO_ANUNCIANTE a " +
		"inner join IMOVEL b on a.IMOV_CD_IMOVEL = b.IMOV_CD_IMOVEL " +
		"inner join CLIENTE c on b.CLIE_CD_CLIENTE = c.CLIE_CD_CLIENTE " +
		"where c.CLIE_CD_CLIENTE = ? " +
		"and   a.IMCA_IN_CTO_REALIZADO = ?";
		
	private static final String SQL_STATUS_ID = SQL_SELECT_CLIENTE_ID + 
		"from CLIENTE " +
		"where CLIE_IN_STATUS = ?"; 
	
	private DAOFactory daofactory = null;
	
	public FirebirdClienteDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public void delete(ClienteDTO dto) throws AlugueRelaxeException {

		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(DEL_CLIENTE_ID);
		int i = -1;
		qry.setLong(++i, dto.id);
		qry.executeUpdate();
	}
	
	public void incPerguntas(long idCliente) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_INC_PERGUNTAS);
		int i = -1;
		qry.setLong(++i, idCliente);
		qry.executeUpdate();
	}

	public void incRespostas(long idCliente) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_INC_RESPOSTAS);
		int i = -1;
		qry.setLong(++i, idCliente);
		qry.executeUpdate();
	}
	
	/**
	 * Método sobrescrito.
	 * @see br.com.jcv.backend.interfacesdao.DAO#insert(java.lang.Object)
	 */
	public ClienteDTO insert(ClienteDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_CRIAR_NOVA_CONTA);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setString(i++, dto.nome);
		qry.setString(i++, dto.cpf);
		qry.setString(i++, dto.cgc);
		qry.setDate(i++, dto.dataNascimento);
		qry.setString(i++, dto.email);
		qry.setString(i++, ((ClienteContraSenhaDTO)dto).senhaHash);
		qry.setString(i++, dto.urlwww);
		qry.setString(i++, dto.indicadorAutorizaNotificacao);
		qry.setString(i++, dto.msn);
		qry.setString(i++, dto.skype);
		qry.setString(i++, dto.googleTalk);
		qry.setString(i++, dto.codigoHashAtivacao);
		qry.setLong(i++, dto.endereco.codigoUfCidadeItem);
		qry.setString(i++, dto.indicadorTipoAnunciante);
		qry.setString(i++, dto.endereco.nome);
		qry.setString(i++, dto.endereco.numero );
		qry.setString(i++, dto.endereco.complemento);
		qry.setString(i++, dto.endereco.bairro);
		qry.setString(i++, dto.endereco.cep);
		qry.setLong(i++, dto.modoPublicidade.id);
		qry.executeUpdate();
		criarTelefonesCliente(dto.id, dto.telefones, session);

		return dto;
	}

	public List<ClienteDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ClienteDTO> list(int start) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Long> list(String status) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_STATUS_ID);
		qry.setString(0, status);
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

	public ClienteDTO load(ClienteDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_CLIENTE);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		ClienteDTO clientedto = null;
		if (resultado.size() > 0) {
			Object dtor[] = (Object[])resultado.get(0);
			
			int i = -1;
			clientedto = new ClienteDTO();
			clientedto.id = Long.valueOf(dtor[++i].toString());
			clientedto.nome = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.cpf = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.cgc = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.dataNascimento = (dtor[++i] == null ? null : (Date) dtor[i]);
			clientedto.codigoAcesso = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.email = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.dataCadastro = (dtor[++i] == null ? null : (Timestamp) dtor[i]);
			clientedto.dataAtualizacao = (dtor[++i] == null ? null : (Timestamp) dtor[i]);
			clientedto.indicadorStatus = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.urlwww = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.indicadorAutorizaNotificacao = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.msn = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.skype = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.googleTalk = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.indicadorTipoAnunciante = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco = new EnderecoDTO();
			clientedto.endereco.nome = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco.numero = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco.complemento = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco.bairro = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco.cep = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco.uf = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco.cidade = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco.codigoUfCidadeItem = Long.valueOf(dtor[++i].toString());
			clientedto.banco = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.agencia = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.contacorrente = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.modoPublicidade = new ModoPublicidadeDTO();
			clientedto.modoPublicidade.id = (dtor[++i] == null ? -1 : Long.valueOf(dtor[i].toString()));
			clientedto.codigoHashAtivacao = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.fotoPerfil = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.fotoChat = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.totalPergunta = Double.valueOf(dtor[++i].toString());
			clientedto.totalResposta = Double.valueOf(dtor[++i].toString());
			clientedto.indicadorVerificado = (dtor[++i] == null ? null : dtor[i].toString());

		}
		return clientedto;
	}
	/**
	 * Atualiza os dados do cliente e se foram atualizados os telefones do mesmo, estes serão apagados e reinseridos
	 */
	public void updateCodigoAcesso(NovoCodigoAcessoDTO ncadto) throws AlugueRelaxeException {

		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_NOVO_CODIGO_ACESSO);
		int i = -1;
		qry.setString(++i, ncadto.codigoHash);
		qry.setString(++i, ncadto.email);
		qry.executeUpdate();
	}

	/**
	 * Atualiza os dados do cliente e se foram atualizados os telefones do mesmo, estes serão apagados e reinseridos
	 */
	public ClienteDTO update(ClienteDTO dto) throws AlugueRelaxeException {

		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_CLIENTE);
		int i = -1;
		qry.setString(++i, dto.nome);
		qry.setString(++i, dto.cpf);
		qry.setString(++i, dto.cgc);
		qry.setDate(++i, dto.dataNascimento);
		qry.setString(++i, dto.email );
		qry.setString(++i, dto.urlwww);
		qry.setString(++i, dto.msn);
		qry.setString(++i, dto.skype);
		qry.setString(++i, dto.googleTalk);
		qry.setLong(++i, dto.endereco.codigoUfCidadeItem);
		qry.setString(++i, dto.endereco.nome);
		qry.setString(++i, dto.endereco.numero);
		qry.setString(++i, dto.endereco.complemento);
		qry.setString(++i, dto.endereco.bairro);
		qry.setString(++i, dto.endereco.cep);
		qry.setString(++i, dto.banco);
		qry.setString(++i, dto.agencia);
		qry.setString(++i, dto.contacorrente);
		qry.setLong(++i, dto.id);
		qry.executeUpdate();
		
		criarTelefonesCliente(dto.id, dto.telefones, session);
		return dto;
	}

	public void criarTelefonesCliente(long idCliente, List<TelefoneDTO> lst, Session session)
	throws AlugueRelaxeException {
		if (lst != null) {
			if (lst.size() > 0) {
				Query qryDelFone = session.createSQLQuery(DEL_CLIENTE_TELEFONES);
				qryDelFone.setLong(0, idCliente);
				qryDelFone.executeUpdate();
				
				for (TelefoneDTO teldto : lst) {
					Query qryInsFone = session.createSQLQuery(INS_CLIENTE_TELEFONES);
					int j = -1;
					qryInsFone.setLong(++j, idCliente );
					qryInsFone.setString(++j, teldto.nomeContato );
					qryInsFone.setString(++j, teldto.ddd );
					qryInsFone.setString(++j, teldto.telefone);
					qryInsFone.setString(++j, teldto.indPermiteExibir);
					qryInsFone.setString(++j, teldto.indTipoTelefone);
					qryInsFone.executeUpdate();
				}
			}
		}		
	}
	

	public List<TelefoneDTO> obtemTelefonesCliente(long idCliente)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_CLIENTE_TELEFONES);
		qry.setLong(0, idCliente);
		List resultado = qry.list();
		List<TelefoneDTO> lstfone = null;
		if (resultado.size() > 0) {
			lstfone = new ArrayList<TelefoneDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor[] = (Object[])resultado.get(i);
				
				int j = -1;
				TelefoneDTO fone = new TelefoneDTO();
				fone.nomeContato = (dtor[++j] == null ? null : dtor[j].toString());
				fone.ddd = (dtor[++j] == null ? null : dtor[j].toString());
				fone.telefone = (dtor[++j] == null ? null : dtor[j].toString());
				fone.indPermiteExibir = (dtor[++j] == null ? null : dtor[j].toString());
				fone.indTipoTelefone = (dtor[++j] == null ? null : dtor[j].toString());
				lstfone.add(fone);
			}
		}
		return lstfone;
	}

	public List<ClienteDTO> listarNovasContasPendentesAtivacao()
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_CONTA_PENDENTE_ATIVACAO);
		qry.setString(0, "P");
		List resultado = qry.list();
		List<ClienteDTO> lst = null;
		if (resultado.size() > 0) {
			lst = new ArrayList<ClienteDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor[] = (Object[])resultado.get(i);
				
				int j = -1;
				ClienteDTO clientedto = new ClienteDTO();
				clientedto.id = Long.valueOf(dtor[++j].toString());
				clientedto.nome = (dtor[++j] == null ? null : dtor[j].toString());
				clientedto.cpf = (dtor[++j] == null ? null : dtor[j].toString());
				clientedto.cgc = (dtor[++j] == null ? null : dtor[j].toString());
				clientedto.dataNascimento = (dtor[++j] == null ? null : (Date) dtor[j]);
				clientedto.email = (dtor[++j] == null ? null : dtor[j].toString());
				clientedto.dataCadastro = (dtor[++j] == null ? null : (Timestamp) dtor[j]);
				clientedto.dataAtualizacao = (dtor[++j] == null ? null : (Timestamp) dtor[j]);
				clientedto.indicadorStatus = (dtor[++j] == null ? null : dtor[j].toString());
				clientedto.urlwww = (dtor[++j] == null ? null : dtor[j].toString());
				clientedto.indicadorAutorizaNotificacao = (dtor[++j] == null ? null : dtor[j].toString());
				clientedto.msn = (dtor[++j] == null ? null : dtor[j].toString());
				clientedto.skype = (dtor[++j] == null ? null : dtor[j].toString());
				clientedto.googleTalk = (dtor[++j] == null ? null : dtor[j].toString());
				clientedto.indicadorTipoAnunciante = (dtor[++j] == null ? null : dtor[j].toString());
				clientedto.codigoHashAtivacao = (dtor[++j] == null ? null : dtor[j].toString());
				lst.add(clientedto);
			}
		}
		return lst;
	}

	public void ativarNovaConta(String hash) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_ATIVAR_NOVA_CONTA);
		qry.setString(0, "A");
		qry.setString(1, hash);
		qry.executeUpdate();
	}

	public ClienteDTO getStatus(String hash) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_OBTER_STATUS_PELO_HASH);
		qry.setString(0, hash);
		List resultado = qry.list();
		ClienteDTO clientedto = null;
		if (resultado.size() > 0) {
			Object dtor[] = (Object[])resultado.get(0);
			
			int j = -1;
			clientedto = new ClienteDTO();
			clientedto.id = Long.valueOf(dtor[++j].toString());
			clientedto.nome = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.cpf = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.cgc = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.dataNascimento = (dtor[++j] == null ? null : (Date) dtor[j]);
			clientedto.email = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.dataCadastro = (dtor[++j] == null ? null : (Timestamp) dtor[j]);
			clientedto.dataAtualizacao = (dtor[++j] == null ? null : (Timestamp) dtor[j]);
			clientedto.indicadorStatus = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.urlwww = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.indicadorAutorizaNotificacao = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.msn = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.skype = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.googleTalk = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.indicadorTipoAnunciante = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.codigoHashAtivacao = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.banco = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.agencia = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.contacorrente = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.fotoPerfil = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.fotoChat = (dtor[++j] == null ? null : dtor[j].toString());
			clientedto.totalPergunta = Double.valueOf(dtor[++j].toString());
			clientedto.totalResposta = Double.valueOf(dtor[++j].toString());
			clientedto.indicadorVerificado = (dtor[++j] == null ? null : dtor[j].toString());
		}
		return clientedto;
	}

	public ClienteDTO load(LoginDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_OBTER_CLIENTE_PELO_EMAIL);
		qry.setString(0, dto.email);
		List resultado = qry.list();
		ClienteDTO clientedto = null;
		if (resultado.size() > 0) {
			Object dtor[] = (Object[])resultado.get(0);
			
			int i = -1;
			/*
			clientedto = new ClienteDTO();
			clientedto.id = Long.valueOf(dtor[++i].toString());
			clientedto.nome = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.cpf = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.cgc = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.dataNascimento = (dtor[++i] == null ? null : (Date) dtor[i]);
			clientedto.codigoAcesso = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.email = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.senha = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.dataCadastro = (dtor[++i] == null ? null : (Timestamp) dtor[i]);
			clientedto.dataAtualizacao = (dtor[++i] == null ? null : (Timestamp) dtor[i]);
			clientedto.indicadorStatus = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.urlwww = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.indicadorAutorizaNotificacao = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.msn = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.skype = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.googleTalk = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.banco = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.agencia = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.contacorrente = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.fotoPerfil = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.fotoChat = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.totalPergunta = Double.valueOf(dtor[++i].toString());
			clientedto.totalResposta = Double.valueOf(dtor[++i].toString());
			clientedto.indicadorVerificado = (dtor[++i] == null ? null : dtor[i].toString());
*/
			clientedto = new ClienteDTO();
			clientedto.id = Long.valueOf(dtor[++i].toString());
			clientedto.nome = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.cpf = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.cgc = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.dataNascimento = (dtor[++i] == null ? null : (Date) dtor[i]);
			clientedto.codigoAcesso = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.email = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.senha = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.dataCadastro = (dtor[++i] == null ? null : (Timestamp) dtor[i]);
			clientedto.dataAtualizacao = (dtor[++i] == null ? null : (Timestamp) dtor[i]);
			clientedto.indicadorStatus = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.urlwww = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.indicadorAutorizaNotificacao = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.msn = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.skype = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.googleTalk = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco = new EnderecoDTO();
			clientedto.endereco.codigoUfCidadeItem = Long.valueOf(dtor[++i].toString());
			clientedto.endereco.nome = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco.numero = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco.complemento = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco.bairro = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.endereco.cep = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.indicadorTipoAnunciante = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.banco = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.agencia = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.contacorrente = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.fotoPerfil = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.fotoChat = (dtor[++i] == null ? null : dtor[i].toString());
			clientedto.totalPergunta = Double.valueOf(dtor[++i].toString());
			clientedto.totalResposta = Double.valueOf(dtor[++i].toString());
			clientedto.indicadorVerificado = (dtor[++i] == null ? null : dtor[i].toString());
		}
		return clientedto;
	}

	public void updateSenha(long idCliente, String novaSenha)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_TROCA_SENHA);
		int i = -1;
		qry.setString(++i, novaSenha);
		qry.setLong(++i, idCliente);
		qry.executeUpdate();
	}

	public void updateLimparCodigoAcesso(String email)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_NOVO_CODIGO_ACESSO);
		int i = -1;
		qry.setString(++i, " ");
		qry.setString(++i, email);
		qry.executeUpdate();
	}

	public List<ContatoClienteDTO> listarContatosAnunciante(long idCliente)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BUSCAR_CONTATOS_ANUNCIANTE);
		int k = 0;
		qry.setLong(k++, idCliente);
		qry.setString(k++, "N");
		List resultado = qry.list();
		List<ContatoClienteDTO> lst = null;
		if (resultado.size() > 0) {
			lst = new ArrayList<ContatoClienteDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor[] = (Object[])resultado.get(i);
				
				int j = -1;
				ContatoClienteDTO contato = new ContatoClienteDTO();
				contato.id = (dtor[++j] == null ? null : dtor[j].toString());
				contato.idImovel = Long.valueOf(dtor[++j].toString());
				contato.proposto = (dtor[++j] == null ? null : dtor[j].toString());
				contato.email = (dtor[++j] == null ? null : dtor[j].toString());
				contato.ddd = (dtor[++j] == null ? null : dtor[j].toString());
				contato.telefone = (dtor[++j] == null ? null : dtor[j].toString());
				contato.uf = (dtor[++j] == null ? null : dtor[j].toString());
				contato.cidade = (dtor[++j] == null ? null : dtor[j].toString());
				contato.chegadaPrevista = (dtor[++j] == null ? null : (Date) dtor[j]);
				contato.dataCadastro = (dtor[++j] == null ? null : (Timestamp) dtor[j]);
				lst.add(contato);
			}
		}
		return lst;
	}

	public long countImoveisAnunciante(long idCliente, long idPlano)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SELECT_COUNT_ANUNCIANTES_IMOVEL_PLANO);
		qry.setLong(0, idPlano);
		qry.setLong(1, idCliente);
		List resultado = qry.list();
		long qtdRetorno = 0;
		if (resultado.size() > 0) {
			Integer dtor = (Integer)resultado.get(0);
			qtdRetorno = dtor.longValue();
		}
		return qtdRetorno;
	}

	public void updateEnqueteModoPublicidade(ClienteDTO dto)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_ENQUETE_MODO_PUBLICIDADE);
		int i = -1;
		qry.setLong(++i, dto.modoPublicidade.id);
		qry.setLong(++i, dto.id);
		qry.executeUpdate();
	}

	public void updateFotoChat(long id, String arquivo)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_FOTO_CHAT);
		int i = -1;
		qry.setString(++i, arquivo);
		qry.setLong(++i, id);
		qry.executeUpdate();
	}

	public void updateFotoPerfil(long id, String arquivo)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_FOTO_PERFIL);
		int i = -1;
		qry.setString(++i, arquivo);
		qry.setLong(++i, id);
		qry.executeUpdate();
	}

}
