package br.com.jcv.backend.depoimento;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.factory.ValidadorFactory;
import br.com.jcv.backend.interfaces.business.BusinessObject;
import br.com.jcv.backend.interfaces.business.DepoimentoBusiness;
import br.com.jcv.backend.interfaces.services.DepoimentoService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.portal.faleconosco.FaleConoscoDTO;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.utility.Introspeccao;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.utility.ValidadorAnotacaoObrigatorio;
import br.com.jcv.backend.validador.Validador;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

public class DepoimentoServiceImpl implements DepoimentoService<DepoimentoDTO> {

	private static Logger logger = Logger.getLogger(DepoimentoServiceImpl.class);
	
	public DepoimentoServiceImpl() throws AlugueRelaxeException {
		super();
	}

	/* (non-Javadoc)
	 * @see br.com.jcv.backend.interfaces.BusinessService#gravarRegistro(java.lang.Object)
	 */
	public DepoimentoDTO gravarRegistro(DepoimentoDTO dto) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DepoimentoDTO dtoretorno = null;

		Validador<DepoimentoDTO> validador = ValidadorFactory.getInstanceDepoimento();
		List<String> lstErros = validador.execute(dto);
		if ((lstErros != null) && (lstErros.size() > 0)) {
			throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
					MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
					lstErros );
		}

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			BusinessObject<DepoimentoDTO> bo = new DepoimentoBusinessImpl();
			dtoretorno = bo.incluir(daoFactory, dto);
			
			daoFactory.commit();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			logger.info(dtoretorno.msgcode + "-" + dtoretorno.msgcodeString);
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	public DepoimentoDTO atualizarRegistro(DepoimentoDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DepoimentoDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			BusinessObject<DepoimentoDTO> bo = new DepoimentoBusinessImpl();
			dtoretorno = bo.atualizar(daoFactory, dto);
			
			daoFactory.commit();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			logger.info(dtoretorno.msgcode + "-" + dtoretorno.msgcodeString);
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	public DepoimentoDTO excluirRegistro(DepoimentoDTO dto) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DepoimentoDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<DepoimentoDTO> bo = new DepoimentoBusinessImpl();
			bo.excluir(daoFactory, dto);
			daoFactory.commit();
			dtoretorno = new DepoimentoDTO();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			logger.info(dtoretorno.msgcode + "-" + dtoretorno.msgcodeString);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	public DepoimentoDTO pesquisarRegistro(DepoimentoDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DepoimentoDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<DepoimentoDTO> bo = new DepoimentoBusinessImpl();
			DepoimentoDTO dtoPesquisa = bo.procurar(daoFactory, dto);
			
			dtoretorno = new DepoimentoDTO();
			dtoretorno.setId(dtoPesquisa.getId());
			dtoretorno.setNome(dtoPesquisa.getNome());
			dtoretorno.setDepoimento(dtoPesquisa.getDepoimento());
			
			// Apresenta os dados no console
			logger.debug("Dados localizados");
			logger.debug("código --> " + dtoPesquisa.getId());
			logger.debug("Nome   --> " + dtoPesquisa.getNome());
			logger.debug("Depoimento --> " + dtoPesquisa.getDepoimento());
			
			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	public List<DepoimentoDTO> listarRegistros() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<DepoimentoDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<DepoimentoDTO> bo = new DepoimentoBusinessImpl();
			list = bo.buscarTodas(daoFactory);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return list;
	}

	public List<DepoimentoDTO> listarRegistros(int start) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<DepoimentoDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<DepoimentoDTO> bo = new DepoimentoBusinessImpl();
			list = bo.buscarTodas(daoFactory, start);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return list;
	}
	
	public DepoimentoDTO getProximoDepoimento(Long id) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DepoimentoDTO dto = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			DepoimentoBusinessImpl bo = new DepoimentoBusinessImpl();
			dto = bo.proximoDepoimento(daoFactory, id);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dto;
		
	}

	public DepoimentoDTO getPrevDepoimento(Long id) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DepoimentoDTO dto = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			DepoimentoBusiness<DepoimentoDTO> bo = new DepoimentoBusinessImpl();
			dto = bo.prevDepoimento(daoFactory, id);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dto;
		
	}
	
	public DepoimentoDTO adicionarDepoimento(DepoimentoDTO dto) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DepoimentoDTO dtopadrao = null;
		
		Validador<DepoimentoDTO> validador = ValidadorFactory.getInstanceDepoimento();
		List<String> lstErros = validador.execute(dto);
		if ((lstErros != null) && (lstErros.size() > 0)) {
			throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
					MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
					lstErros );
		}
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			DepoimentoBusiness<DepoimentoDTO> bo = new DepoimentoBusinessImpl();
			dto = bo.adicionarDepoimento(daoFactory, dto);
			daoFactory.commit();
			
			// Enviar E-mail solicitando verificação do conteudo do depoimento
			String linkAtivacaoAprovar = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_ATIVACAO_DEPOIMENTO);
			linkAtivacaoAprovar = StringUtil.replaceStringNew(linkAtivacaoAprovar, "${id}", String.valueOf(dto.id));
			linkAtivacaoAprovar = StringUtil.replaceStringNew(linkAtivacaoAprovar, "${action}", Constantes.APROVAR);

			String linkAtivacaoReprovar = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_ATIVACAO_DEPOIMENTO);
			linkAtivacaoReprovar = StringUtil.replaceStringNew(linkAtivacaoReprovar, "${id}", String.valueOf(dto.id));
			linkAtivacaoReprovar = StringUtil.replaceStringNew(linkAtivacaoReprovar, "${action}", Constantes.REPROVAR);
			
			Map<String,String> conteudo = new HashMap<String, String>();
			conteudo.put(TemplateConstantes.TCD_TAG_DEPOIMENTO, dto.depoimento);
			conteudo.put(TemplateConstantes.TCD_TAG_NOME_PROPOSTO, dto.nome);
			conteudo.put(TemplateConstantes.TCD_TAG_LINK_LIBERAR_DEPOIMENTO, linkAtivacaoAprovar);
			conteudo.put(TemplateConstantes.TCD_TAG_LINK_REPROVAR_DEPOIMENTO, linkAtivacaoReprovar);
			
			Email email = EmailFactory.getInstanceEmailConteudoDepoimento(conteudo);
			
			ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
			lst.add(new EmailRecord(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE),
			"Administrador"));
			
			email.enviar(lst, null, "AlugueRelaxe - Verificar depoimento com conteúdo impróprio.", null);
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}

	public DTOPadrao liberarDepoimento(String id, String acao) 
								throws AlugueRelaxeException {

		if ((id == null) || (acao == null)) {
			Map<String,String> parametros = new HashMap<String, String>();
			parametros.put(Constantes.P1, "id e acao");
			throw AlugueRelaxeException.getInstance(MSGCODE.CAMPO_VAZIO,
					MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_VAZIO), 
					null);
		}

		if ( (! acao.equals(Constantes.APROVAR)) &&  
		     (! acao.equals(Constantes.REPROVAR))   ) {
			throw AlugueRelaxeException.getInstance(MSGCODE.ACAO_NAO_PERMITIDA_PARA_LIBERACAO,
					MensagemCache.getInstance().getMensagem(MSGCODE.ACAO_NAO_PERMITIDA_PARA_LIBERACAO), 
					null);
		}

		if ( id == null ) {
				Map<String,String> parametros = new HashMap<String, String>();
				parametros.put(Constantes.P1, "id");
				throw AlugueRelaxeException.getInstance(MSGCODE.CAMPO_VAZIO,
						MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_VAZIO,parametros), 
						null);
		}
		
		DAOFactory daoFactory =  null;
		DTOPadrao dtopadrao = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			DepoimentoBusiness<DepoimentoDTO> bo = new DepoimentoBusinessImpl();
			bo.liberarDepoimento(daoFactory, id, acao);
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

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dtopadrao;
	}
	
	/**
	 * retorna no backend services uma pagina com um certo numero
	 * definido por uma variavel configurada
	 */ 
	public List<DepoimentoDTO> ListarPaginaDepoimentos() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<DepoimentoDTO> lst = null;
		
		try {
			String maximoPaginas = VariavelCache.getInstance().getValor(VariavelConstantes.TAMANHO_PAGINA_DEPOIMENTO);
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			DepoimentoBusiness<DepoimentoDTO> bo = new DepoimentoBusinessImpl();
			lst = bo.ListarPaginaDepoimentos(daoFactory, Integer.valueOf(maximoPaginas));
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
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
	
	
	
}
