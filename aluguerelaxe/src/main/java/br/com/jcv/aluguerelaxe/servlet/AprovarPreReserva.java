package br.com.jcv.aluguerelaxe.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.jcv.backend.assinantes.AssinantesDTO;
import br.com.jcv.backend.assinantes.AssinantesServiceImpl;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.AssinantesService;
import br.com.jcv.backend.interfaces.services.ReservaService;
import br.com.jcv.backend.reserva.ReservaDTO;
import br.com.jcv.backend.reserva.ReservaServiceImpl;

public class AprovarPreReserva extends HttpServlet {

	private static final long serialVersionUID = 7377510229678400667L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		
		// Obtem os parametros para validacao 
		String hash = request.getParameter("token");
		
		if (hash == null) {
			throw new ServletException("token inválido.");
		}

		String action = request.getParameter("action");
		ReservaDTO dto = null;
		
		if (action == null) {
			throw new ServletException("Código de Ativação inválido.");
		} else {
			if (action.equals(Constantes.APROVAR)) {
				ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
				try {
					dto = rs.aprovarPreReserva(hash);
				} catch (AlugueRelaxeException e) {
					throw new ServletException(e.getMensagem());
				}
			} else if (action.equals(Constantes.REPROVAR)) {
				ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
				try {
					dto = rs.reprovarPreReserva(hash);
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
		out.println("<meta http-equiv=\"refresh\" content=\"5;url=/arweb/JXControllerSmartInterface?cmd=dtgStartAlugueRelaxe\">");
		out.println("</head>");
		out.println("<body>");
		out.println( dto.msgcodeString);
		out.println("</body>");
		out.println("</html>");
	}
}
