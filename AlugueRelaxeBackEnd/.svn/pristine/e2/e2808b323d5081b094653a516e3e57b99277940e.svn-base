
package br.com.jcv.backend.portal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.cliente.ClienteBusinessImpl;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.factory.ValidadorFactory;
import br.com.jcv.backend.filasms.FilaSMSDTO;
import br.com.jcv.backend.interfaces.business.ClienteBusiness;
import br.com.jcv.backend.interfaces.services.PortalService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.portal.faleconosco.FaleConoscoDTO;
import br.com.jcv.backend.portal.login.LoginDTO;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.validador.Validador;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class PortalServiceImpl implements PortalService {
	
	private static Logger logger = Logger.getLogger(PortalServiceImpl.class);

	public DTOPadrao login(LoginDTO dto) throws AlugueRelaxeException {
		
		//ValidadorAnotacaoObrigatorio.validar(dto);
		Validador<LoginDTO> validador = ValidadorFactory.getInstanceLoginPortal();
		List<String> lstErros = validador.execute(dto);
		if ((lstErros != null) && (lstErros.size() > 0)) {
			throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
					MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
					lstErros );
		}
		
		DAOFactory daoFactory =  null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ClienteBusiness<ClienteDTO> bo = new ClienteBusinessImpl();
			ClienteDTO clientedto = bo.validaContaUsuario(daoFactory, dto);
			daoFactory.commit();
			
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			dto.objeto = clientedto;
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

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
	/**
	* Envia um e-mail para o AlugueRelaxe com as informações do fale conosco
	*/
	public DTOPadrao executeFaleConosco(FaleConoscoDTO fcdto) throws AlugueRelaxeException {
		
		Validador<FaleConoscoDTO> validador = ValidadorFactory.getInstanceFaleConoscoPortal();
		List<String> lstErros = validador.execute(fcdto);
		if ((lstErros != null) && (lstErros.size() > 0)) {
			throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
					MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
					lstErros );
		}
		
		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TFC_TAG_NOME, fcdto.nome);
		conteudo.put(TemplateConstantes.TFC_TAG_EMAIL, fcdto.email);
		conteudo.put(TemplateConstantes.TFC_TAG_ASSUNTO,fcdto.assunto);
		conteudo.put(TemplateConstantes.TFC_TAG_TOPICO, fcdto.topico);
		conteudo.put(TemplateConstantes.TFC_TAG_MENSAGEM, fcdto.mensagem);
		
		Email email = EmailFactory.getInstanceEmailFaleConosco(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE),
				"Administrador"));
		
		email.enviar(lst, null, "[FALE CONOSCO] - Temos um contato via fale conosco", null);
		
		// Envia SMS para Administrador Prioridade normal
		FilaSMSDTO fsmsdto = new FilaSMSDTO();
		Map<String,String> param = new HashMap<String, String>();
		param.put(Constantes.P1, fcdto.nome);
		param.put(Constantes.P2, fcdto.assunto);
		fsmsdto.celular = VariavelCache.getInstance().getValor(VariavelConstantes.CELULAR_ADMIN_GATEWAY);
		fsmsdto.msg = MensagemCache.getInstance().getMensagem(MSGCODE.SMS_FALE_CONOSCO, param);
		fsmsdto.modo = VariavelCache.getInstance().getValor(Constantes.SMS_MODO_ENVIO_GATEWAY);;
		fsmsdto.prioridade = Constantes.SMS_PRIORIDADE_NORMAL;
			
		DTOPadrao dto = new DTOPadrao();
		dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
		return dto;
	}
	
	/**
	* Gerar um novo codigo de acesso a area administrativa para o solicitante
	* 
	* Procedimentos:
	* 1) Recebido o e-mail, o mesmo e encaminhado para geracaoCodigoAcesso (*veja regras)
	* 2) Enviamos para o correio do solicitante um codigo de acesso 
	*/
	public DTOPadrao solicitarNovoCodigoAcesso(NovoCodigoAcessoDTO dto) throws AlugueRelaxeException {

		//---------------------------------------------------------------------
		// Valida o conteudo do e-mail
		//---------------------------------------------------------------------
		Validador<NovoCodigoAcessoDTO> validador = ValidadorFactory.getInstanceNovoCodigoAcesso();
		List<String> lstErros = validador.execute(dto);
		if ((lstErros != null) && (lstErros.size() > 0)) {
			throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
					MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
					lstErros );
		}

		//---------------------------------------------------------------------
		// Gera o novo codigo de acesso
		//---------------------------------------------------------------------
		DAOFactory daoFactory =  null;
		NovoCodigoAcessoDTO ncadto = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ClienteBusiness<ClienteDTO> bo = new ClienteBusinessImpl();
			ncadto = bo.gerarNovoCodigoAcesso(daoFactory, dto);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		//---------------------------------------------------------------------
		// Envia e-mail com novo codigo de acesso para o solicitante
		//---------------------------------------------------------------------
		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TNCA_TAG_CODIGO_ACESSO, ncadto.codigoAcesso);
		
		Email email = EmailFactory.getInstanceEmailNovoCodigoAcesso(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(ncadto.email,ncadto.email));
		
		email.enviar(lst, null, "[CODIGO ACESSO] - Novo Código de Acesso Solicitado", null);
			
		DTOPadrao dtopadrao = new DTOPadrao();
		dtopadrao.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		dtopadrao.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
		return dtopadrao;
	}
}

