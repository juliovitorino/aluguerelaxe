package br.com.jcv.aluguerelaxe.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class BarraProgresso extends HttpServlet {

	private static final long serialVersionUID = 4091366485860148667L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		String path = "";
		boolean terminado = false;
		FileInputStream fin = null;
		byte[] buffer = (new String("0")).getBytes();

		try {
			path = VariavelCache.getInstance().getValor(VariavelConstantes.UPLOAD_PATH);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//-----------------------------------------------------------------------
		// Obtem os parametros que contem o registro percentual de acompanhamento
		// Este pid deve ser IDENTICO ao fornecido a servlet que fez o upload do
		// arquivo.
		//-----------------------------------------------------------------------
		String pid = request.getParameter("pid");
		
		if (pid == null) {
			throw new ServletException("pid invalido");
		}

		try {
			String filename = path.concat(pid).concat(".progress");
			fin = new FileInputStream(filename);
			
			// Copy bytes from URL to output stream
			buffer = new byte[1024];
			int bytes_read;
			bytes_read = fin.read(buffer);
			if (bytes_read == -1){
				terminado = true;
			}
			
		} catch (FileNotFoundException  e) {
			e.printStackTrace();
			buffer = (new String("0")).getBytes();
			
		} catch(IOException e){
			e.printStackTrace();
			buffer = (new String("0")).getBytes();
			
		} finally {
			try {
				if (fin != null)
					fin.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		if (!terminado) {
			// PRODUCAO
			out.println("<meta http-equiv=\"refresh\" content=\"2;url=/arweb/barraprogresso.svlt?pid=" + pid + "\">");

			// HOSTED MODE
			//out.println("<meta http-equiv=\"refresh\" content=\"2;url=/barraprogresso.svlt?pid=" + pid + "\">");
		}
		out.println("</head>");
		out.println("<body>");
		out.println("<table>");
		out.println("  <tr>");
		out.println("    <td width=\"100\" style=\"border: 1px solid silver;padding:none\">");
		out.println("      <hr style=\"color:#c00;background-color:#c00;height:12px; border:none;margin:0;\" align=\"left\" width=\""+ new String(buffer,"UTF8") +"%\" />");
		out.println("<img src=\"/arweb/images/gif/ampulheta.gif\"/><b>(" + new String(buffer,"UTF8") + "%)</b>");
		out.println("    </td>");
		out.println("  </tr>");
		out.println("</table>");
		
		out.println("</body>");
		out.println("</html>");

	}
}
