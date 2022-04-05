package br.com.jcv.aluguerelaxe.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadServiceImpl;
import br.com.jcv.backend.interfaces.services.ContatoAnuncianteThreadService;

public class AprovarThread extends HttpServlet {

	private static final long serialVersionUID = -3553755548537776673L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		
		// Obtem os parametros para validacao 
		String hash = request.getParameter("hid");
		
		if (hash == null) {
			throw new ServletException("token inválido.");
		}

		String action = request.getParameter("action");
		String modo = request.getParameter("modo");
		DTOPadrao dto = null;
		
		if (action == null) {
			throw new ServletException("Código de Ativação inválido.");
		} else {
			if (action.equals(Constantes.APROVAR)) {
				ContatoAnuncianteThreadService cats = new ContatoAnuncianteThreadServiceImpl();
				try {
					dto = cats.aprovarThread(hash, modo, false);
				} catch (AlugueRelaxeException e) {
					throw new ServletException(e.getMensagem());
				}
			} else if (action.equals(Constantes.APROVAR_COM_EDICAO)) {
				ContatoAnuncianteThreadService cats = new ContatoAnuncianteThreadServiceImpl();
				try {
					dto = cats.aprovarThread(hash, modo, true);
				} catch (AlugueRelaxeException e) {
					throw new ServletException(e.getMensagem());
				}
			} else {
				throw new ServletException("Token inválido");
			}
				
		}
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println( dto.msgcodeString);
		out.println("</body>");
		out.println("</html>");
	}
}
