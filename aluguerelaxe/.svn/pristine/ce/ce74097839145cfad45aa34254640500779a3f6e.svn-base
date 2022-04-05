package br.com.jcv.aluguerelaxe.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelService;

public class LiberarContatoAnunciante extends HttpServlet {

	private static final long serialVersionUID = 4228234487285700107L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		
		// Obtem os parametros para validacao 
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		
		if ((id == null) || (action == null)) {
			throw new ServletException("Faltam parametros válidos.");
		}
		
		ImovelService cs = new ImovelServiceImpl();
		try {
			cs.liberarEmailContatoAnunciante(id, action);
		} catch (AlugueRelaxeException e) {
			throw new ServletException(e.getMessage());
		}
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("Comando foi realizado com sucesso!");
		out.println("");
		out.println("");
		out.println("");
		out.println("</body>");
		out.println("</html>");
		
		
		
		
		
	}
}
