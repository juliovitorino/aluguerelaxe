package br.com.jcv.backend.cliente;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.factory.ValidadorFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.interfaces.business.ClienteBusiness;
import br.com.jcv.backend.interfaces.services.ClienteService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.validador.Validador;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class ClienteServiceImpl implements ClienteService<ClienteDTO> {
	
	private static final Logger logger = Logger.getLogger(ClienteServiceImpl.class);

	public ClienteDTO atualizarRegistro(ClienteDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void atualizarFotoPerfil(long id, String arquivo, int tipoFoto) 
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			ib.atualizarFotoPerfil(daoFactory, id, arquivo, tipoFoto);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
			
	}

	public ClienteDTO excluirRegistro(ClienteDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			ib.excluir(daoFactory, dto);
			daoFactory.commit();
			
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;

	}

	/**
	 * Método sobrescrito.
	 * @see br.com.jcv.backend.interfaces.services.ApplicationService#gravarRegistro(java.lang.Object)
	 * @deprecated Use o método <code>criarNovaConta()</code> ao invés deste.
	 */
	public ClienteDTO gravarRegistro(ClienteDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends ClienteDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Long> listarRegistros(String status)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			lst = ib.buscarTodas(daoFactory, status);
			daoFactory.commit();
			
		} catch (AlugueRelaxeException are) {
			throw are;
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;
	}

	/**
	 * Método sobrescrito.
	 * @see br.com.jcv.backend.interfaces.services.ApplicationService#pesquisarRegistro(java.lang.Object)
	 */
	public ClienteDTO pesquisarRegistro(ClienteDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ClienteDTO clientedto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			clientedto = ib.procurar(daoFactory, dto);
			if (clientedto != null) {
				clientedto.telefones = ib.obtemTelefonesCliente(daoFactory, clientedto.id);
			}
			daoFactory.commit();
			
			clientedto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			clientedto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return clientedto;
	}

	public List<ClienteDTO> listarNovasContasPendentesAtivacao()
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<ClienteDTO> lst = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			lst = ib.listarNovasContasPendentesAtivacao(daoFactory);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;
	}

	public void ativarNovaConta(String hash) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			ClienteDTO clientedto = ib.getStatus(daoFactory, hash);
			boolean enviarEmail = false;
			
			if (clientedto != null){
				if (clientedto.indicadorStatus.equals(ClienteBusiness.CONTA_STATUS_PENDENTE) ) {
					ib.ativarNovaConta(daoFactory, hash);
					enviarEmail = true;
				}
			}
			daoFactory.commit();
			
			if (enviarEmail){
				Map<String, String> conteudo = new HashMap<String,String>();
				conteudo.put(TemplateConstantes.TCA_TAG_EMAIL_NOVO_CLIENTE, clientedto.email);
				conteudo.put(TemplateConstantes.TCA_TAG_NOME_CLIENTE, clientedto.nome);
				
				List<EmailRecord> lstDestinatarios = new ArrayList<EmailRecord>();
				lstDestinatarios.add(new EmailRecord(clientedto.email, clientedto.nome));
				
				Email email = EmailFactory.getInstanceEmailContaAtivada(conteudo);
				email.enviar(lstDestinatarios, null, "AlugueRelaxe - Conta Ativada", null);
			}
			
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
	}

	public ClienteDTO criarNovaConta(ClienteContraSenhaDTO dto) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ClienteDTO clientedto = null;
		try {
			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			ib.validarCamposObrigatorios(dto);
			
			Validador<ClienteContraSenhaDTO> validador = ValidadorFactory.getInstanceClienteContraSenha();
			List<String> lstErros = validador.execute(dto);
			if ((lstErros != null) && (lstErros.size() > 0)) {
				throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
						MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
						lstErros );
			}
			
			calcularHashSenha(dto);
			calcularHashAtivacao(dto);
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			clientedto = ib.incluir(daoFactory, dto);

			daoFactory.commit();
			
			clientedto = this.pesquisarRegistro(clientedto);

			Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, dto.email);
			clientedto.msgcode = MSGCODE.NOVA_CONTA_CLIENTE_CRIADA_COM_SUCESSO;
			clientedto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.NOVA_CONTA_CLIENTE_CRIADA_COM_SUCESSO, parametros);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null) {
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		enviarEmailAtivacaoConta(dto);

		
		return clientedto;
	}

	private void calcularHashAtivacao(ClienteContraSenhaDTO dto) {
		BaseFactorySecurity bfs = BaseFactorySecurity.getInstance(BaseFactorySecurity.SHA1);
		try {
			dto.codigoHashAtivacao = bfs.criptografar(dto.email);
		} catch (InvalidKeyException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		}
	}
	

	/**
	 * Enviar um e-mail com link de ativação da conta 
	 *
	 * @param dto
	 * @throws AlugueRelaxeException
	 */
	private void enviarEmailAtivacaoConta(ClienteContraSenhaDTO dto) throws AlugueRelaxeException {
		String linkAtivacao = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_ATIVACAO);
		linkAtivacao = StringUtil.replaceStringNew(linkAtivacao, "${hash_ativacao}", dto.codigoHashAtivacao);
		
		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TANC_TAG_CGC_CPF_NOVO_CLIENTE, (dto.cgc == null ? dto.cpf : dto.cgc ));
		conteudo.put(TemplateConstantes.TANC_TAG_EMAIL_NOVO_CLIENTE, dto.email);
		conteudo.put(TemplateConstantes.TANC_TAG_LINK_ATIVACAO_NOVO_CLIENTE, linkAtivacao);
		conteudo.put(TemplateConstantes.TANC_TAG_NOME_NOVO_CLIENTE, dto.nome);
		
		Email email = EmailFactory.getInstanceEmailAtivarNovaConta(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(dto.email,dto.nome));
		
		email.enviar(lst, null, "AlugueRelaxe - Ativação de conta", null);
	}


	/**
	 * Enviar um e-mail com link de ativação da conta 
	 *
	 * @param dto
	 * @throws AlugueRelaxeException
	 */
	public void enviarEmailAtivacaoConta(ClienteDTO dto) throws AlugueRelaxeException {
		String linkAtivacao = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_ATIVACAO);
		linkAtivacao = StringUtil.replaceStringNew(linkAtivacao, "${hash_ativacao}", dto.codigoHashAtivacao);
		
		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TANC_TAG_CGC_CPF_NOVO_CLIENTE, (dto.cgc == null ? dto.cpf : dto.cgc ));
		conteudo.put(TemplateConstantes.TANC_TAG_EMAIL_NOVO_CLIENTE, dto.email);
		conteudo.put(TemplateConstantes.TANC_TAG_LINK_ATIVACAO_NOVO_CLIENTE, linkAtivacao);
		conteudo.put(TemplateConstantes.TANC_TAG_NOME_NOVO_CLIENTE, dto.nome);
		
		Email email = EmailFactory.getInstanceEmailAtivarNovaConta(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(dto.email,dto.nome));
		
		email.enviar(lst, null, "AlugueRelaxe - Ativação de conta", null);
	}

	private void calcularHashSenha(ClienteContraSenhaDTO dto) throws AlugueRelaxeException {
		validarSenhas(dto);
		
		BaseFactorySecurity bfs = BaseFactorySecurity.getInstance(BaseFactorySecurity.SHA1);
		try {
			dto.senhaHash = bfs.criptografar(dto.senha);
		} catch (InvalidKeyException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		}
	}

	private void validarSenhas(ClienteContraSenhaDTO dto) throws AlugueRelaxeException {
		if ((dto.senha == null) || (dto.contraSenha == null)) {
			throw AlugueRelaxeException.getInstance(MSGCODE.CAMPO_VAZIO, 
					MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_VAZIO),
					null);
		}

		if ( ! dto.senha.equals(dto.contraSenha)) {
			throw AlugueRelaxeException.getInstance(MSGCODE.SENHA_E_CONTRASENHA_DIFERENTES, 
					MensagemCache.getInstance().getMensagem(MSGCODE.SENHA_E_CONTRASENHA_DIFERENTES),
					null);
		}
	}

	public ClienteDTO pesquisarRegistro(String hash)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ClienteDTO clientedto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			clientedto = ib.getStatus(daoFactory, hash);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return clientedto;
	}

	public DTOPadrao atualizarFichaCadastral(ClienteDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DTOPadrao dtopadrao = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			ib.validarCamposObrigatorios(dto);
			ib.atualizarFichaCadastral(daoFactory,dto); 
			daoFactory.commit();
			
			dtopadrao = new DTOPadrao();
			dtopadrao.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtopadrao.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dtopadrao;
	}

	public DTOPadrao trocarSenha(ClienteContraSenhaDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DTOPadrao clientedto = null;
		try {
			Validador<ClienteContraSenhaDTO> validador = ValidadorFactory.getInstanceClienteContraSenha();
			List<String> lstErros = validador.execute(dto);
			if ((lstErros != null) && (lstErros.size() > 0)) {
				throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
						MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
						lstErros );
			}
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			calcularHashSenha(dto);

			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			ib.trocarSenha(daoFactory, dto.id, dto.senhaHash);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null) {
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		//enviarEmailAviso de troca de senha(dto);

		clientedto = new DTOPadrao();
		clientedto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		clientedto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
		
		return clientedto;
	}

	@SuppressWarnings("rawtypes")
	public List<ContatoClienteDTO> listarContatosAnunciante(long idCliente)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<ContatoClienteDTO> lst = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ClienteBusiness ib = new ClienteBusinessImpl();
			lst = ib.listarContatosAnunciante(daoFactory, idCliente);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;
	}

	public DTOPadrao atualizarEnqueteModoPublicidade(ClienteDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DTOPadrao dtopadrao = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			ib.atualizarEnqueteModoPublicidade(daoFactory, dto);

			daoFactory.commit();
			
			dtopadrao = new DTOPadrao();
			dtopadrao.msgcode = MSGCODE.ENQUETE_MODO_PUBLICIDADE_ATUALIZADO_COM_SUCESSO;
			dtopadrao.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.ENQUETE_MODO_PUBLICIDADE_ATUALIZADO_COM_SUCESSO);
			
		} catch (AlugueRelaxeException are) {
			logger.error(are.getMessage(), are);
			daoFactory.rollback();
			throw are;

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null) {
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return dtopadrao;
	}

}
