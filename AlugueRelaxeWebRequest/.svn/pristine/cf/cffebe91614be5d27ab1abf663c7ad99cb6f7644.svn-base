package br.com.jcv.request.filtro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.jcv.backend.buscapagina.BuscaPagina;
import br.com.jcv.backend.buscapagina.BuscaPaginaFactory;
import br.com.jcv.backend.buscapagina.EnvelopePaginacaoDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.html.properta.PropertaPaginacaoChain;
import br.com.jcv.backend.html.properta.PropertaPaginacaoCofR;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
 * Servlet implementation class FiltroGeral
 */
public class FiltroGeral extends HttpServlet {
       
	private static final long serialVersionUID = -3062639621929921610L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public FiltroGeral() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String localizacao = "";
		long idCidadeUf = -1;
		HttpSession session = request.getSession();
		//------------------------------------------------------
		// Busca a pagina para geracao do template paginado
		// MAPA_TAG contem a indicacao da classe que a vai
		// resolver o filtro pela BuscaPaginaFactory
		//------------------------------------------------------
		Map<String,String> mapa = new HashMap<String,String>();
		mapa.put(Constantes.MAPA_TAG, Constantes.MAPA_FILTRO_GERAL);
		
		//---------------------------------------------------
		// Obtem os parametros do filtro
		// 1o. filtro vem da pagina index
		// 2o. filtro vem da navegacao do contador de pagina
		//---------------------------------------------------
		String pag = request.getParameter("p_pagina");
		localizacao = request.getParameter("p_filtro");
		
		//---------------------------------------------------
		// Verifica se esta e a primeira chamada
		//---------------------------------------------------
		if (localizacao != null){
			idCidadeUf = this.getCodigoCidadeUf(localizacao);
			if (mapa.containsKey(Constantes.CONTEXTO_BP_ID_CIDADE_UF)) {
				mapa.remove(Constantes.CONTEXTO_BP_ID_CIDADE_UF);
			}
			mapa.put(Constantes.CONTEXTO_BP_ID_CIDADE_UF, String.valueOf(idCidadeUf));
			session.setAttribute("mapasessao", mapa);
		}
		//---------------------------------------------------
		// Abre uma sessao no servidor de aplicacao
		// para armazenar filtro para proximas buscas
		//---------------------------------------------------

		if (! session.isNew()){
			mapa = (Map) session.getAttribute("mapasessao");
		}
		
		String jsid = session.getId();
		//---------------------------------------------------------------------
		// Executa a busca paginada de acordo filtro no mapa e seus parametros
		//---------------------------------------------------------------------
		BuscaPagina<ImovelFichaCompletaDTO> bp = BuscaPaginaFactory.getInstance(mapa);
		EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> epdto = bp.execute(mapa, Integer.valueOf(pag), 10);
		epdto.pagina = Integer.valueOf(pag);
		epdto.jsid = jsid.toLowerCase();
		
		
		//---------------------------------------------------
		// Invoca Chain para executar todos os passos
		// da criacao da paginacao
		//---------------------------------------------------
		PropertaPaginacaoCofR pgcofr = new PropertaPaginacaoCofR();
		pgcofr.setContext(epdto);
		pgcofr.addChain(new PropertaPaginacaoChain());
		pgcofr.execute();
		
		//---------------------------------------------------
		// Redireciona browser para a URL apontada onde
		// foi gerada a paginacao desta secao
		//---------------------------------------------------
		//RequestDispatcher d= request.getRequestDispatcher(epdto.urlPaginacao);  
		//d.forward(request,response); 
		
		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TAGC_TEMPLATE_REDIRECT, epdto.urlPaginacao);
		Template redirecionador = null;
		try {
			redirecionador = TemplateFactory.getInstance(conteudo,TemplateConstantes.TEMPLATE_REDIRECT);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(redirecionador.getHtmlTemplate());
		out.flush();
	}
	
	private long getCodigoCidadeUf(String localizacao) {
		// Formato a desmembrar "Cidade, UF (2323)"
		// Retirar o número de dentro do parenteses
		int p1 = localizacao.indexOf("(");
		int p2 = localizacao.indexOf(")");
		
		long id = -1;
		if ((p1 > 0) && (p2 > 0)){
			try {
				id = Long.valueOf(localizacao.substring(p1 + 1, p2)).longValue();
			} catch (NumberFormatException e) {
				id = -1;
			}
		}
		return id; 
	}

}
