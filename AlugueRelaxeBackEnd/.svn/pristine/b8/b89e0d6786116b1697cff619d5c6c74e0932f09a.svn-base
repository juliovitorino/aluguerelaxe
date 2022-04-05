package br.com.jcv.backend;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.PaginacaoService;
import br.com.jcv.backend.paginacao.CondicaoDTO;
import br.com.jcv.backend.paginacao.EnvelopePaginacaoDataGridDTO;
import br.com.jcv.backend.paginacao.PaginacaoServiceImpl;

public class PaginacaoTest extends TestCase {

	public void testListarClientePorFiltro() {
		try {
			String tabela = "CLIENTE";
			List<String> campos = new ArrayList<String>();
			campos.add("CLIE_CD_CLIENTE");
			campos.add("CLIE_NM_CLIENTE");
			campos.add("CLIE_DT_CADASTRO");
			
			List<CondicaoDTO> condicoes = null;
			int indice = 1;
			int qtdp = 3;
			
			PaginacaoService ps = new PaginacaoServiceImpl();
			
			
			EnvelopePaginacaoDataGridDTO envelope = ps.listarPorFiltro(tabela, campos, condicoes,null, indice, qtdp);
			
			assertNotNull(envelope);
			
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		} catch (Exception e) {
			fail("Erro");
		}
	}

	public void testListarClientePorFiltroPag2() {
		try {
			String tabela = "CLIENTE";
			List<String> campos = new ArrayList<String>();
			campos.add("CLIE_CD_CLIENTE");
			campos.add("CLIE_NM_CLIENTE");
			campos.add("CLIE_DT_CADASTRO");
			campos.add("CLIE_TX_EMAIL");
			
			List<CondicaoDTO> condicoes = null;
			int indice = 2;
			int qtdp = 4;
			
			PaginacaoService ps = new PaginacaoServiceImpl();
			
			
			EnvelopePaginacaoDataGridDTO envelope = ps.listarPorFiltro(tabela, campos, condicoes, null,indice, qtdp);
			
			assertNotNull(envelope);
			
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		} catch (Exception e) {
			fail("Erro");
		}
	}

	public void testListarClientePorFiltroCondicaoAtivo() {
		try {
			String tabela = "CLIENTE";
			List<String> campos = new ArrayList<String>();
			campos.add("CLIE_CD_CLIENTE");
			campos.add("CLIE_NM_CLIENTE");
			campos.add("CLIE_DT_CADASTRO");
			campos.add("CLIE_TX_EMAIL");
			
			List<CondicaoDTO> lstcnd = new ArrayList<CondicaoDTO>();
			CondicaoDTO c1 = new CondicaoDTO();
			c1.campo = "CLIE_IN_STATUS";
			c1.condicao = "=";
			c1.conteudo = "'A'";
			
			lstcnd.add(c1);
			
			int indice = 2;
			int qtdp = 4;
			
			PaginacaoService ps = new PaginacaoServiceImpl();
			
			
			EnvelopePaginacaoDataGridDTO envelope = ps.listarPorFiltro(tabela, campos, lstcnd,null, indice, qtdp);
			
			assertNotNull(envelope);
			
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		} catch (Exception e) {
			fail("Erro");
		}
	}

	public void testListarClientePorFiltroCondicaoInativo() {
		try {
			String tabela = "CLIENTE";
			List<String> campos = new ArrayList<String>();
			campos.add("CLIE_CD_CLIENTE");
			campos.add("CLIE_NM_CLIENTE");
			campos.add("CLIE_DT_CADASTRO");
			campos.add("CLIE_TX_EMAIL");
			
			List<CondicaoDTO> lstcnd = new ArrayList<CondicaoDTO>();
			CondicaoDTO c1 = new CondicaoDTO();
			c1.campo = "CLIE_IN_STATUS";
			c1.condicao = "=";
			c1.conteudo = "'I'";
			
			lstcnd.add(c1);
			
			int indice = 1;
			int qtdp = 50;
			
			PaginacaoService ps = new PaginacaoServiceImpl();
			
			
			EnvelopePaginacaoDataGridDTO envelope = ps.listarPorFiltro(tabela, campos, lstcnd,null, indice, qtdp);
			
			assertNotNull(envelope);
			
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		} catch (Exception e) {
			fail("Erro");
		}
	}


	public void testListarClientePorFiltroCondicaoAND() {
		try {
			String tabela = "CLIENTE";
			List<String> campos = new ArrayList<String>();
			campos.add("CLIE_CD_CLIENTE");
			campos.add("CLIE_NM_CLIENTE");
			campos.add("CLIE_DT_CADASTRO");
			campos.add("CLIE_TX_EMAIL");
			
			List<CondicaoDTO> lstcnd = new ArrayList<CondicaoDTO>();
			CondicaoDTO c1 = new CondicaoDTO();
			c1.campo = "CLIE_IN_STATUS";
			c1.condicao = "=";
			c1.conteudo = "'A'";
			c1.operadorLogico = "E";
			
			lstcnd.add(c1);

			CondicaoDTO c2 = new CondicaoDTO();
			c2.campo = "CLIE_CD_CLIENTE";
			c2.condicao = ">";
			c2.conteudo = "30";
			
			lstcnd.add(c2);
			
			
			int indice = 1;
			int qtdp = 50;
			
			PaginacaoService ps = new PaginacaoServiceImpl();
			
			
			EnvelopePaginacaoDataGridDTO envelope = ps.listarPorFiltro(tabela, campos, lstcnd, null, indice, qtdp);
			
			assertNotNull(envelope);
			
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		} catch (Exception e) {
			fail("Erro");
		}
	}


}
