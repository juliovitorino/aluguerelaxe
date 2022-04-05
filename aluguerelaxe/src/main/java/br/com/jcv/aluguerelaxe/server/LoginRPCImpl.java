package br.com.jcv.aluguerelaxe.server;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.administracao.login.LoginRPC;
import br.com.jcv.aluguerelaxe.client.administracao.login.NovoCodigoAcessoVO;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.PortalService;
import br.com.jcv.backend.portal.NovoCodigoAcessoDTO;
import br.com.jcv.backend.portal.PortalServiceImpl;
import br.com.jcv.backend.portal.login.LoginDTO;

public class LoginRPCImpl extends AbstractBaseServlet implements LoginRPC {
	
	private static Logger logger = Logger.getLogger(LoginRPCImpl.class);

	private static final long serialVersionUID = 3309344689528719468L;
	
	public SessaoVO login(String email, String senha) throws AlugueRelaxeFrontException {
		SessaoVO sessaovo = null;
		try {
			LoginDTO logindto = new LoginDTO();
			logindto.email = email;
			logindto.senha = senha;
			
			PortalService ps = new PortalServiceImpl();
			DTOPadrao dtoretorno = ps.login(logindto);
			
			// Cria uma nova sessao no servidor
			HttpServletRequest hsr = this.getThreadLocalRequest();
			HttpSession session = hsr.getSession(true);
			
			session.setAttribute("clientedto", dtoretorno.objeto);
			sessaovo = new SessaoVO();
			
			String jsid = session.getId();
			sessaovo.sessionid = aplicaHashSessionId(jsid);
			sessaovo.msgcode = dtoretorno.msgcode;
			sessaovo.msgcodeString = dtoretorno.msgcodeString;

			if (logger.isInfoEnabled()) {
				logger.info("Login " + email + "logado com sucesso as " + (new Date()).toString());
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("jsid = " + jsid);
				logger.debug("hash sessionid = " + sessaovo.sessionid);
			}
			
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem());
		}
		return sessaovo;
	}

	public VOPadrao solicitarNovoCodigoAcesso(NovoCodigoAcessoVO vo)
			throws AlugueRelaxeFrontException {
		VOPadrao voretorno = null;
		try {
			NovoCodigoAcessoDTO dto = new NovoCodigoAcessoDTO();
			dto.email = vo.email;
			
			PortalService ps = new PortalServiceImpl();
			DTOPadrao dtoretorno = ps.solicitarNovoCodigoAcesso(dto);
			
			voretorno = new VOPadrao();
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;

		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem());
		}
		return voretorno;
	}

	public VOPadrao encerrarSessao() throws AlugueRelaxeFrontException {
		try {
			this.forcarInvalidate(this.getSession());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

