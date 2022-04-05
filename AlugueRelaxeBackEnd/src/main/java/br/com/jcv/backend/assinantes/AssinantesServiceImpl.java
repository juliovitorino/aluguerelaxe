package br.com.jcv.backend.assinantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.AssinantesBusiness;
import br.com.jcv.backend.interfaces.services.AssinantesService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;


public class AssinantesServiceImpl implements AssinantesService<AssinantesDTO> {
	
	private static Logger logger = Logger.getLogger(AssinantesServiceImpl.class);

	/**
	 * <h2>inscricao</h2>
	 * <p>Inscrever novo assinante na base de dados</p>
	 */
	public AssinantesDTO inscricao(AssinantesDTO dto) throws AlugueRelaxeException {
	
		verificarDuplicidadePromocao(dto, 0);
		
		DAOFactory daoFactory =  null;
		AssinantesDTO retorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			// Inscreve o novo assinante da campanha
 			AssinantesBusiness<AssinantesDTO> bo = new AssinantesBusinessImpl();
 			retorno = bo.inscrever(daoFactory, dto);
			daoFactory.commit();
			
			enviarEmailAtivacaoAssinatura(retorno);
			
			retorno.msgcode = MSGCODE.INSCRICAO_REALIZADA_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.INSCRICAO_REALIZADA_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return retorno;
	}

	public AssinantesDTO gravarRegistro(AssinantesDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public AssinantesDTO excluirRegistro(AssinantesDTO dto)
			throws AlugueRelaxeException {
	
		DAOFactory daoFactory =  null;
		AssinantesDTO retorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			// excluir assinante da campanha
 			AssinantesBusiness<AssinantesDTO> bo = new AssinantesBusinessImpl();
 			bo.excluir(daoFactory, dto);
			daoFactory.commit();
			
			retorno = dto;
			retorno.msgcode = MSGCODE.INSCRICAO_REALIZADA_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.INSCRICAO_REALIZADA_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return retorno;
	}

	public AssinantesDTO atualizarRegistro(AssinantesDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public AssinantesDTO pesquisarRegistro(AssinantesDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends AssinantesDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DTOPadrao inscricao(AssinantesDTO dto, List<AssinantesDTO> amigos)
			throws AlugueRelaxeException {
		
		// Verifica se a lista de amigos é nula ou não contem elementos
		if ( (amigos == null) || (amigos.size() == 0) ) {
			final Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, dto.nome);
			throw new AlugueRelaxeException(MSGCODE.LISTA_AMIGOS_VAZIA,
					 MensagemCache.getInstance().getMensagem(MSGCODE.LISTA_AMIGOS_VAZIA, parametros),
					null);
		}
		
		// Verifica se algum dos amigos ja foi indicado por outra pessoa
		// na mesma campanha
		verificarIndicacaoAmigo(amigos);
		
		// Realiza a inscrição do principal interessado
		AssinantesDTO principal = this.inscricao(dto);
		
		// realiza a inscricao dos amigos
		
		DAOFactory daoFactory =  null;
		AssinantesDTO retorno = null;
		
		daoFactory = DAOFactory.getDAOFactory();
		
		try {
			
			for (AssinantesDTO amigodto : amigos) {
				daoFactory.open();
				daoFactory.beginTransaction();
			
				// Relaciona o ID_Parent com o amigo
				amigodto.idParent = principal.id;
				
				// Inscreve o novo assinante da campanha
				AssinantesBusiness<AssinantesDTO> bo = new AssinantesBusinessImpl();
				retorno = bo.inscrever(daoFactory, amigodto);
				daoFactory.commit();
				
				// Envia e-mail com link solicitando confirmacao
				String linkAtivacao = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_ATIVA_PROMOCAO);
				linkAtivacao = StringUtil.replaceStringNew(linkAtivacao, "${hash_ativacao}", retorno.hash);
				
				Map<String,String> conteudo = new HashMap<String, String>();
				conteudo.put(TemplateConstantes.TPAIA_TAG_NOVO_ASSINANTE_AMIGO, amigodto.nome);
				conteudo.put(TemplateConstantes.TPAIA_TAG_PROMOCAO_ATIVA, retorno.campanha);
				conteudo.put(TemplateConstantes.TPAIA_TAG_NOVO_ASSINANTE_PROMOCAO, principal.nome);
				conteudo.put(TemplateConstantes.TPAIA_TAG_NOVO_ASSINANTE_PROMOCAO_EMAIL, principal.email);
				conteudo.put(TemplateConstantes.TPAIA_TAG_LINK_ATIVA_PROMOCAO, linkAtivacao);
				conteudo.put(TemplateConstantes.TPAIA_TAG_LINK_REGULAMENTO, VariavelCache.getInstance().getValor(VariavelConstantes.LINK_REGULAMENTO_PROMOCAO));
				
				ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
				lst.add(new EmailRecord(amigodto.email, amigodto.nome));
				
				Email email = EmailFactory.getInstanceEmailPromocaoAmigoIndicaAmigo(conteudo);
				email.enviar(lst, null,"Chave de Ativacao - " + principal.campanha, null);
			}
			
			retorno.msgcode = MSGCODE.INSCRICAO_REALIZADA_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.INSCRICAO_REALIZADA_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return retorno;
	}
	
	public DTOPadrao ativarInscricao(String hash) throws AlugueRelaxeException {
	 
		// Verifica se o hash é nulo
		if ( (hash == null) || (hash.length() == 0) ){
			throw AlugueRelaxeException.getInstance(MSGCODE.CHAVE_ATIVACAO_INVALIDA,
					MensagemCache.getInstance().getMensagem(MSGCODE.CHAVE_ATIVACAO_INVALIDA),
					null);
		}
		
		DAOFactory daoFactory =  null;
		DTOPadrao retorno = null;
		
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
		
			// Ativa a inscricao na campanha pertinente apontada pelo hash
			AssinantesBusiness<AssinantesDTO> bo = new AssinantesBusinessImpl();
			bo.ativarInscricao(daoFactory, hash);
			
			AssinantesDTO amigodto = bo.procurar(daoFactory, hash);
			daoFactory.commit();
			
			// Envia e-mail avisando da confirmacao
			Map<String,String> conteudo = new HashMap<String, String>();
			conteudo.put(TemplateConstantes.TPAS_TAG_NOVO_ASSINANTE_AMIGO, amigodto.nome);
			conteudo.put(TemplateConstantes.TPAS_TAG_PROMOCAO_ATIVA, amigodto.campanha);
			conteudo.put(TemplateConstantes.TPAS_TAG_NUMERO_DA_SORTE_1,StringUtil.lPad( String.valueOf(amigodto.ticket), "0", 6));
			conteudo.put(TemplateConstantes.TPAS_TAG_DATA_SORTEIO_PROMOCAO, VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA_DATA_SORTEIO));
			conteudo.put(TemplateConstantes.TPAS_TAG_LINK_REGULAMENTO, VariavelCache.getInstance().getValor(VariavelConstantes.LINK_REGULAMENTO_PROMOCAO));
			
			ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
			lst.add(new EmailRecord(amigodto.email, amigodto.nome));
			
			Email email = EmailFactory.getInstanceEmailPromocaoAtivadaComSucesso(conteudo);
			email.enviar(lst, null, "Confirmação - " + amigodto.campanha, null);
			
			retorno = new DTOPadrao();
			retorno.msgcode = MSGCODE.INSCRICAO_ATIVADA_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.INSCRICAO_ATIVADA_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return retorno;
	}	
	
	private void verificarDuplicidadePromocao(AssinantesDTO dto, long parent) throws AlugueRelaxeException {
		
		DAOFactory daoFactory =  null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			AssinantesBusiness<AssinantesDTO> bo = new AssinantesBusinessImpl();
			AssinantesDTO retorno = bo.verificarIndicadoViraParticipante(daoFactory, dto);
			daoFactory.commit();
			
			// Confirmacao da indicacao por outra pessoa
			if (retorno != null) {
				final Map<String,String> parametros = new HashMap<String,String>();
				throw new AlugueRelaxeException(MSGCODE.INSCRICAO_JA_REALIZADA,
						 MensagemCache.getInstance().getMensagem(MSGCODE.INSCRICAO_JA_REALIZADA),
						null);
			}
			
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
	}
	
	private void verificarIndicacaoAmigo(List<AssinantesDTO> amigos)
		throws AlugueRelaxeException {
		
		DAOFactory daoFactory =  null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();

			for (AssinantesDTO amigodto : amigos) {
				daoFactory.open();
				daoFactory.beginTransaction();
				
				AssinantesBusiness<AssinantesDTO> bo = new AssinantesBusinessImpl();
				AssinantesDTO retorno = bo.verificarIndicacaoAmigo(daoFactory, amigodto);
				daoFactory.commit();
				
				// Confirmacao da indicacao por outra pessoa
				if (retorno != null) {
					final Map<String,String> parametros = new HashMap<String,String>();
					parametros.put(Constantes.P1, retorno.nome);
					throw new AlugueRelaxeException(MSGCODE.AMIGO_INDICADO_POR_OUTRA_PESSOA,
							 MensagemCache.getInstance().getMensagem(MSGCODE.AMIGO_INDICADO_POR_OUTRA_PESSOA, parametros),
							null);
				}
			}
			
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

	}
	public List<AssinantesDTO> listarRegistros(String status) throws AlugueRelaxeException{
		// Obtem a promocao ativa 
		String promocaoAtiva = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
		return listarRegistros(promocaoAtiva, status);
		
	}
	
	public List<AssinantesDTO> listarRegistros(String campanha, String status) throws AlugueRelaxeException{
		DAOFactory daoFactory =  null;
		List<AssinantesDTO> lst = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			AssinantesBusiness<AssinantesDTO> bo = new AssinantesBusinessImpl();
			lst = bo.listarRegistros(daoFactory, campanha, status);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;
	
	}
	
	public void enviarEmailAtivacaoAssinatura(AssinantesDTO dto) throws AlugueRelaxeException {
		// Envia e-mail com link solicitando confirmacao
		String linkAtivacao = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_ATIVA_PROMOCAO);
		linkAtivacao = StringUtil.replaceStringNew(linkAtivacao, "${hash_ativacao}", dto.hash);
		
		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TNAP_TAG_NOVO_ASSINANTE_PROMOCAO, dto.nome);
		conteudo.put(TemplateConstantes.TNAP_TAG_PROMOCAO_ATIVA, dto.campanha);
		conteudo.put(TemplateConstantes.TNAP_TAG_LINK_ATIVA_PROMOCAO, linkAtivacao);
		conteudo.put(TemplateConstantes.TNAP_TAG_LINK_REGULAMENTO, VariavelCache.getInstance().getValor(VariavelConstantes.LINK_REGULAMENTO_PROMOCAO));

		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(dto.email, dto.nome));
		
		Email email = EmailFactory.getInstanceEmailAssintantes(conteudo);
		email.enviar(lst, null, "Chave de Ativacao - " + dto.campanha, null);
	
	}


}

