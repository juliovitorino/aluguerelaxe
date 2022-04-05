package br.com.jcv.aluguerelaxe.server;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.criptografia.BaseFactorySecurity;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public abstract class AbstractBaseServlet extends RemoteServiceServlet {

	private static final long serialVersionUID = 3598636464903978986L;
	
	protected HttpSession getSession(){
		HttpServletRequest hsr = this.getThreadLocalRequest();
		HttpSession session = hsr.getSession();
		return session;
	}
	
	public SessaoVO validarSessao(String sid){
		SessaoVO sessaovo = null;

		// Obtem a sessao no container
		HttpSession session = this.getSession();
		
		// Verifica se a sessão está ativa por mais de 1 dia ou acessada 1 hora depois
		try {
			if (this.invalidate(session)){
				return null;
			}
		} catch (ServletException e) {
			e.printStackTrace();
		}

		String jsid = session.getId();
		String hash = aplicaHashSessionId(jsid);

//		if (logger.isDebugEnabled()) {
//			logger.debug("sid via URL = " + sid);
//			logger.debug("jsid da sessao = " + jsid);
//			logger.debug("hash da sessao = " + hash);
//		}
//		
		// Verifica se hash calculado eh igual ao que acaba de chegar
		if (sid.equals(hash)) {
			ClienteDTO clientedto = (ClienteDTO) session.getAttribute("clientedto");
			sessaovo = new SessaoVO();
			sessaovo.clientevo = new ClienteVO();
			sessaovo.clientevo.id = clientedto.id;
			sessaovo.clientevo.nome = clientedto.nome;
			sessaovo.clientevo.email = clientedto.email;
		}
		return sessaovo;
	}

	protected String aplicaHashSessionId(String jsid) {
		String hash = null;
		BaseFactorySecurity bfs = BaseFactorySecurity.getInstance(BaseFactorySecurity.SHA1);
		
		try {
			hash = bfs.criptografar(jsid);
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
		
		return hash;
	}
	
	public boolean invalidate(HttpSession session) throws ServletException {
		boolean expirada = false;

		// Invalida a sessao se esta esiver mais de 1 dia ativa ou
		// inativa por mais de 1 hora
	    if (!session.isNew()) { 
			Date dayAgo = new Date(System.currentTimeMillis()  - 24 * 60 * 60 * 1000);
			Date hourAgo = new Date(System.currentTimeMillis() - 60 * 60 * 1000);
			Date created = new Date(session.getCreationTime());
			Date accessed = new Date(session.getLastAccessedTime());

	      if (created.before(dayAgo) || accessed.before(hourAgo)) {
	        session.invalidate();
			expirada = true;
	      }
	    }
		return expirada;
	  }
	  
	  public void forcarInvalidate(HttpSession session) throws ServletException {
		session.invalidate();
	  }
	
	
		  

}
