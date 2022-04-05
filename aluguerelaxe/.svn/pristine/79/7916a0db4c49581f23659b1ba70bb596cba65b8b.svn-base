package br.com.jcv.aluguerelaxe.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.jcv.backend.cliente.ClienteServiceImpl;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ClienteService;

public class ConfirmaEmail extends HttpServlet {

	private static final long serialVersionUID = -5035837851813942362L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		
		// Obtem os parametros para validacao 
		String hash = request.getParameter("ativacao");
		
		if (hash == null) {
			throw new ServletException("Email ou Código de Ativação inválidos.");
		}
		
		ClienteService cs = new ClienteServiceImpl();
		try {
			cs.ativarNovaConta(hash);
		} catch (AlugueRelaxeException e) {
			throw new ServletException(e.getMessage());
		}
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"refresh\" content=\"5;url=/arweb/JXControllerSmartInterface?cmd=dtgLogin\">");
		out.println("</head>");
		out.println("<body>");
		out.println("Sua conta foi ativada com sucesso! Redirecionando para Login Administrativo. Aguarde...");
		out.println("</body>");
		out.println("</html>");
	}
}
