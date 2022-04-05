package br.com.jcv.aluguerelaxe.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.jcv.backend.assinantes.AssinantesDTO;
import br.com.jcv.backend.assinantes.AssinantesServiceImpl;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.AssinantesService;

public class AtivarPromocao extends HttpServlet {

	private static final long serialVersionUID = 7377510229678400667L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		
		// Obtem os parametros para validacao 
		String hash = request.getParameter("k");
		
		if (hash == null) {
			throw new ServletException("Código de Ativação inválido.");
		}
		
		AssinantesService<AssinantesDTO> cs = new AssinantesServiceImpl();
		try {
			cs.ativarInscricao(hash);
		} catch (AlugueRelaxeException e) {
			throw new ServletException(e.getMessage());
		}
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"refresh\" content=\"5;url=/arweb/JXControllerSmartInterface?cmd=dtgStartAlugueRelaxe\">");
		out.println("</head>");
		out.println("<body>");
		out.println("Sua participação na promoção foi ativada com sucesso! Verifique sua caixa de email e veja seus números da sorte. Aguarde, redirecionando...");
		out.println("</body>");
		out.println("</html>");
	}
}
