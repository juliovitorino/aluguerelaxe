package br.com.jcv.backend;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import junit.framework.TestCase;
import br.com.jcv.backend.cache.UltimosImoveisCadastradosCache;
import br.com.jcv.backend.caracteristicas.CaracteristicaDTO;
import br.com.jcv.backend.cliente.ClienteBusinessImpl;
import br.com.jcv.backend.cliente.ClienteContraSenhaDTO;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.ClienteServiceImpl;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelPlanoDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;
import br.com.jcv.backend.interfaces.business.ImovelBusiness;
import br.com.jcv.backend.interfaces.services.ClienteService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class ImovelTest extends TestCase {
	
	public static final long CODIGO_IMOVEL_PESQUISA = 2;


	public void testGetEmailInTextObs() {
		try {
			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 100;
			imovel1.cliente.id = dtor.id;
			
			// Coloca um e-mail invalido dentro da Descricao Quartos
			imovel1.imovel.observacoes = new String(imovel1.imovel.observacoes) + " julio.vitorino@ig.net.br";
			
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			fail("Erro...");
		} catch (AlugueRelaxeException e) {
			String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}
	
	public void testGetUrlInTextObs() {
		try {
			

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 100;
			imovel1.cliente.id = dtor.id;
			
			// Coloca um e-mail invalido dentro da Descricao Quartos
			imovel1.imovel.observacoes = new String(imovel1.imovel.observacoes) + " http://mail.aluguerelaxe.com.br/bugzilla/show_bug.cgi?id=3";
			
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			fail("Erro...");
		} catch (AlugueRelaxeException e) {
			String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}
	

	public void testGetFoneInTextObs() {
		try {
			

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 100;
			imovel1.cliente.id = dtor.id;
			
			// Coloca um e-mail invalido dentro da Descricao Quartos
			imovel1.imovel.observacoes = new String(imovel1.imovel.observacoes) + " (24) 5647-9788";
			
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			fail("Erro...");
		} catch (AlugueRelaxeException e) {
			String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}
	

	public void testGetEmailInTextDescricaoTitulo() {
		try {
			

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 100;
			imovel1.cliente.id = dtor.id;
			
			// Coloca um e-mail invalido dentro da Descricao Quartos
			imovel1.imovel.descricaoTituloAnuncio = new String(imovel1.imovel.descricaoTituloAnuncio) + " julio.vitorino@ig.net.br";
			
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			fail("Erro...");
		} catch (AlugueRelaxeException e) {
			String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}
	
	public void testGetUrlInTextDescricaoTitulo() {
		try {
			

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 100;
			imovel1.cliente.id = dtor.id;
			
			// Coloca um e-mail invalido dentro da Descricao Quartos
			imovel1.imovel.descricaoTituloAnuncio = new String(imovel1.imovel.descricaoTituloAnuncio) + " http://mail.aluguerelaxe.com.br/bugzilla/show_bug.cgi?id=3";
			
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			fail("Erro...");
		} catch (AlugueRelaxeException e) {
			String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}
	
	

	public void testGetEmailInTextDescricaoArredores() {
		try {
			

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 100;
			imovel1.cliente.id = dtor.id;
			
			// Coloca um e-mail invalido dentro da Descricao Quartos
			imovel1.imovel.descricaoArredores = new String(imovel1.imovel.descricaoArredores) + " julio.vitorino@ig.net.br";
			
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			fail("Erro...");
		} catch (AlugueRelaxeException e) {
			String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}


	public void testGetUrlInTextDescricaoArredores() {
		try {
			

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 100;
			imovel1.cliente.id = dtor.id;
			
			// Coloca um e-mail invalido dentro da Descricao Quartos
			imovel1.imovel.descricaoArredores = new String(imovel1.imovel.descricaoArredores) + " http://mail.aluguerelaxe.com.br/bugzilla/show_bug.cgi?id=3";
			
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			fail("Erro...");
		} catch (AlugueRelaxeException e) {
			String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}
	

	public void testGetEmailInTextDescricaoQuartos() {
		try {
			

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 100;
			imovel1.cliente.id = dtor.id;
			
			// Coloca um e-mail invalido dentro da Descricao Quartos
			imovel1.imovel.descricaoQuartos = new String(imovel1.imovel.descricaoQuartos) + " julio.vitorino@ig.net.br";
			
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			fail("Erro...");
		} catch (AlugueRelaxeException e) {
			String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}
	

	public void testGetUrlInTextDescricaoQuartos() {
		try {
			

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 100;
			imovel1.cliente.id = dtor.id;
			
			// Coloca um e-mail invalido dentro da Descricao Quartos
			imovel1.imovel.descricaoQuartos = new String(imovel1.imovel.descricaoQuartos) +  " http://mail.aluguerelaxe.com.br/bugzilla/show_bug.cgi?id=3";
			
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			fail("Erro...");
		} catch (AlugueRelaxeException e) {
			String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}
	
	public void testGetEmailInTextDescricaoGeral() {
		try {
			

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 100;
			imovel1.cliente.id = dtor.id;
			
			// Coloca um e-mail invalido dentro da Descricao Geral
			imovel1.imovel.descricaoGeral = new String(imovel1.imovel.descricaoGeral) + " julio.vitorino@ig.net.br";
			
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			fail("Erro...");
		} catch (AlugueRelaxeException e) {
			String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}


	public void testGetUrlInTextDescricaoGeral() {
		try {
			

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 100;
			imovel1.cliente.id = dtor.id;
			
			// Coloca um e-mail invalido dentro da Descricao Geral
			imovel1.imovel.descricaoGeral = new String(imovel1.imovel.descricaoGeral) + " http://mail.aluguerelaxe.com.br/bugzilla/show_bug.cgi?id=3";
			
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			fail("Erro...");
		} catch (AlugueRelaxeException e) {
			String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}
	
	public void testUltimosImoveisCadastradosCache() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			Date dtStart = new Date();
			ImovelService is = new ImovelServiceImpl();
			List<ImovelFichaCompletaDTO> lst = is.listarUltimosImoveisCadastrados();
			System.out.println("Ordem original");
			
			for (ImovelFichaCompletaDTO dto : lst){
				System.out.println("#" + String.valueOf(dto.imovel.id));
			}
			
			dtStart = new Date();
			UltimosImoveisCadastradosCache uicc = UltimosImoveisCadastradosCache.getInstance();
			lst = uicc.getCache();
			System.out.println(dtStart);
			System.out.println(new Date().toString());
			
			System.out.println("Ordem cache");
			for (ImovelFichaCompletaDTO dto : lst){
				System.out.println("#" + String.valueOf(dto.imovel.id));
			}
			
			dtStart = new Date();
			UltimosImoveisCadastradosCache uicct2 = UltimosImoveisCadastradosCache.getInstance();
			List<ImovelFichaCompletaDTO> lst2 = uicc.getCache();
			System.out.println(dtStart);
			System.out.println(new Date().toString());
			
			for (ImovelFichaCompletaDTO dto : lst2){
				System.out.println("#" + String.valueOf(dto.imovel.id));
			}
			
			//assertTrue("Retornou registros", i > 0);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testIncluirUltimosImoveisCadastradosCache() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			Date dtStart = new Date();
			UltimosImoveisCadastradosCache uicc = UltimosImoveisCadastradosCache.getInstance();
			List<ImovelFichaCompletaDTO> lst = uicc.getCache();
			System.out.println(dtStart);
			System.out.println(new Date().toString());
			
			for (ImovelFichaCompletaDTO dto : lst){
				System.out.println("#" + String.valueOf(dto.imovel.id));
			}
			
			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is1 = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is1.adicionarImovelCarteira(imovel1);
			
			dtStart = new Date();
			UltimosImoveisCadastradosCache uicct2 = UltimosImoveisCadastradosCache.getInstance();
			List<ImovelFichaCompletaDTO> lst2 = uicc.getCache();
			System.out.println(dtStart);
			System.out.println(new Date().toString());
			
			for (ImovelFichaCompletaDTO dto2 : lst2){
				System.out.println("#" + String.valueOf(dto2.imovel.id));
			}
			
			//assertTrue("Retornou registros", i > 0);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testContarImoveisRJ() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			long i = is.contarImoveisPorEstado("RJ");
			assertTrue("Retornou registros", i > 0);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testListarGaleriaFotosImoveisPatrocinador() {
		try {
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			List<ImovelFichaCompletaDTO> lst = is.listarGaleriaFotosImoveis("P"); 
			assertNotNull(lst);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	public void testAtualizarFichaImovelDescricaoTituloAnuncio() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = is.pesquisarFichaCompletaImovel(CODIGO_IMOVEL_PESQUISA);
			imovel1.imovel.descricaoTituloAnuncio = this.getIpsumLorem(200);
			DTOPadrao dtoretorno = is.atualizarImovelCarteira(imovel1);
			
			assertEquals(codigoEsperado, dtoretorno.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testAtualizarFichaImovelDescricaoArredores() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = is.pesquisarFichaCompletaImovel(CODIGO_IMOVEL_PESQUISA);
			imovel1.imovel.descricaoArredores = this.getIpsumLorem(200);
			DTOPadrao dtoretorno = is.atualizarImovelCarteira(imovel1);
			
			assertEquals(codigoEsperado, dtoretorno.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testAtualizarFichaImovelDescricaoGeral() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = is.pesquisarFichaCompletaImovel(CODIGO_IMOVEL_PESQUISA);
			imovel1.imovel.descricaoGeral = this.getIpsumLorem(200);
			DTOPadrao dtoretorno = is.atualizarImovelCarteira(imovel1);
			
			assertEquals(codigoEsperado, dtoretorno.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	public void testValidarFichaImovelNula() {
		try {
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			DTOPadrao dtoretorno = is.adicionarImovelCarteira(null);
			fail("Erro no validador do método adicionarImovelCarteira");
		} catch (AlugueRelaxeException e) {
			List<String> lst = (List<String>)e.getObjeto();
			assertNotNull(lst);
		}
	}

	public void testlistarImoveisParaNegativacao() {
		ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		try {
			List<Long> ifcdto = is.listarImoveisParaNegativacao();
			assertNotNull(ifcdto);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail("Lista retornou alguma coisa -- verificar imoveis XX");
		}
	}


	public void testListaImoveisPorEstadoErrado() {
		ImovelService is = new ImovelServiceImpl();
		try {
			List<ImovelFichaCompletaDTO> ifcdto = is.listaImoveisPorEstado("XX", 1, 10);
			assertNull(ifcdto);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail("Lista retornou alguma coisa -- verificar imoveis XX");
		}
	}

	public void testListaImoveisPorEstadoPagina1() {
		ImovelService is = new ImovelServiceImpl();
		try {
			List<ImovelFichaCompletaDTO> ifcdto = is.listaImoveisPorEstado("RJ", 1, 10);
			assertNotNull(ifcdto);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail("Lista retornou nulo -- verificar imoveis RJ");
		}
	}

	public void testListaImoveisPorEstadoPagina2() {
		ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		try {
			List<ImovelFichaCompletaDTO> ifcdto = is.listaImoveisPorEstado("RJ", 2, 10);
			assertNotNull(ifcdto);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail("Lista retornou nulo -- verificar imoveis RJ");
		}
	}
	
	public void testListarQtdeImoveisCidadeUF() {
		ImovelService is = new ImovelServiceImpl();
		try {
			List<CidadeUFDTO> ifcdto = is.listarQtdeImoveisCidadeUF();
			assertTrue(ifcdto.size() > 0);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		} catch(Exception e) {
			e.printStackTrace();
			fail("Outro erro ocorreu");
		}
		
	}
	
	public void testEntrarEmContatoComAnunciante() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		ImovelService is = new ImovelServiceImpl();
		try {
			
			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			ContatoClienteDTO ccdto = new ContatoClienteDTO();
			String i = String.valueOf(System.currentTimeMillis());
			ccdto.idImovel = imovel1.imovel.id;
			ccdto.proposto = "Julio Cesar Vitorino";
			ccdto.email = "julio.vitorino@ig.com.br";
			ccdto.ddd = "21";
			ccdto.telefone = "98340040";
			ccdto.cidade = "Volta Redonda";
			ccdto.uf = "Rio de Janeiro";
			ccdto.pais = "Brasil";
			ccdto.chegadaPrevista = new GregorianCalendar(16,2,2014).getTime();
			ccdto.partidaPrevista = new GregorianCalendar(16,3,2014).getTime();
			ccdto.qtdeAdultos = 2;
			ccdto.qtdeCriancas = 3;
			ccdto.informacoesAdicionais = "Ola, manda mais informacao." + i;

			ContatoClienteDTO ifcdto = is.entrarEmContatoComAnunciante(ccdto);
			assertEquals(codigoEsperado, ifcdto.msgcode);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}
	
	public void testliberarEmailContatoAnuncianteAprovar() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		ImovelService is = new ImovelServiceImpl();
		try {

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);

			ContatoClienteDTO ccdto = new ContatoClienteDTO();
			String i = String.valueOf(System.currentTimeMillis());
			ccdto.idImovel = imovel1.imovel.id;
			ccdto.proposto = "Julio Cesar Vitorino";
			ccdto.email = "julio.vitorino@ig.com.br";
			ccdto.ddd = "61";
			ccdto.telefone = "06069898";
			ccdto.cidade = "Volta Redonda";
			ccdto.uf = "Rio de Janeiro";
			ccdto.pais = "Brasil";
			ccdto.chegadaPrevista = new GregorianCalendar(16,2,2010).getTime();
			ccdto.partidaPrevista = new GregorianCalendar(16,3,2010).getTime();
			ccdto.qtdeAdultos = 2;
			ccdto.qtdeCriancas = 3;
			ccdto.informacoesAdicionais = "(TESTE) OlÃ¡, manda mais informacao. " + i;
			ContatoClienteDTO ifcdto = is.entrarEmContatoComAnunciante(ccdto);
			
			System.out.println("Pendencia criada --> "+ifcdto.id);

			DTOPadrao dtop = is.liberarEmailContatoAnunciante(ifcdto.id, Constantes.APROVAR);
			assertEquals(codigoEsperado, dtop.msgcode);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	
	public void testliberarEmailContatoAnunciantePagoAprovar() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		ImovelService is = new ImovelServiceImpl();
		try {

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor, 100);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);

			ContatoClienteDTO ccdto = new ContatoClienteDTO();
			String i = String.valueOf(System.currentTimeMillis());
			ccdto.idImovel = imovel1.imovel.id;
			ccdto.proposto = "Julio Cesar Vitorino";
			ccdto.email = "julio.vitorino@ig.com.br";
			ccdto.ddd = "61";
			ccdto.telefone = "06069898";
			ccdto.cidade = "Volta Redonda";
			ccdto.uf = "Rio de Janeiro";
			ccdto.pais = "Brasil";
			ccdto.chegadaPrevista = new GregorianCalendar(16,2,2010).getTime();
			ccdto.partidaPrevista = new GregorianCalendar(16,3,2010).getTime();
			ccdto.qtdeAdultos = 2;
			ccdto.qtdeCriancas = 3;
			ccdto.informacoesAdicionais = "(TESTE) OlÃ¡, manda mais informacao. " + i;
			ContatoClienteDTO ifcdto = is.entrarEmContatoComAnunciante(ccdto);
			
			System.out.println("Pendencia criada --> "+ifcdto.id);

			DTOPadrao dtop = is.liberarEmailContatoAnunciante(ifcdto.id, Constantes.APROVAR);
			assertEquals(codigoEsperado, dtop.msgcode);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	
	public void testliberarEmailContatoAnuncianteReprovar() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		ImovelService is = new ImovelServiceImpl();
		try {


			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);

			ContatoClienteDTO ccdto = new ContatoClienteDTO();
			String i = String.valueOf(System.currentTimeMillis());
			ccdto.idImovel = imovel1.imovel.id;
			ccdto.proposto = "Julio Cesar Vitorino";
			ccdto.email = "julio.vitorino@ig.com.br";
			ccdto.ddd = "61";
			ccdto.telefone = "06069898";
			ccdto.cidade = "Volta Redonda";
			ccdto.uf = "Rio de Janeiro";
			ccdto.pais = "Brasil";
			ccdto.chegadaPrevista = new GregorianCalendar(16,2,2010).getTime();
			ccdto.partidaPrevista = new GregorianCalendar(16,3,2010).getTime();
			ccdto.qtdeAdultos = 2;
			ccdto.qtdeCriancas = 3;
			ccdto.informacoesAdicionais = "(TESTE) Filho da puta! Vai tomar no cu! Seu merda!. " + i;
			ContatoClienteDTO ifcdto = is.entrarEmContatoComAnunciante(ccdto);
			
			System.out.println("Pendencia criada para reprovar --> "+ifcdto.id);

			DTOPadrao dtop = is.liberarEmailContatoAnunciante(ifcdto.id, Constantes.REPROVAR);
			assertEquals(codigoEsperado, dtop.msgcode);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	
	public void testPesquisarFichaCompletaImovel() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(2);
			if (ifcdto == null){
				fail("Resultado ImovelFichaCompletaDTO nulo!");
			}
			assertEquals(codigoEsperado, ifcdto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testApresentarFCI_Origem_LI() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO ifcdto = is.apresentarFichaCompletaImovel(2, "LI");
			if (ifcdto == null){
				fail("Resultado ImovelFichaCompletaDTO nulo!");
			}
			assertEquals(codigoEsperado, ifcdto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	public void testApresentarFCI_Origem_PD() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO ifcdto = is.apresentarFichaCompletaImovel(2, "PD");
			if (ifcdto == null){
				fail("Resultado ImovelFichaCompletaDTO nulo!");
			}
			assertEquals(codigoEsperado, ifcdto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	public void testApresentarFCI_Origem_PP() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO ifcdto = is.apresentarFichaCompletaImovel(2, "PP");
			if (ifcdto == null){
				fail("Resultado ImovelFichaCompletaDTO nulo!");
			}
			assertEquals(codigoEsperado, ifcdto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	public void testApresentarFCI_Origem_IP() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO ifcdto = is.apresentarFichaCompletaImovel(2, "IP");
			if (ifcdto == null){
				fail("Resultado ImovelFichaCompletaDTO nulo!");
			}
			assertEquals(codigoEsperado, ifcdto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	public void testApresentarFCI_Origem_LD() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO ifcdto = is.apresentarFichaCompletaImovel(2, "LD");
			if (ifcdto == null){
				fail("Resultado ImovelFichaCompletaDTO nulo!");
			}
			assertEquals(codigoEsperado, ifcdto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void testListarGaleriaFotosImovel(){
		try {
			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			ImovelFichaCompletaDTO imovel2 = getImovelDTO(dtor);
			is.adicionarImovelCarteira(imovel1);
			is.adicionarImovelCarteira(imovel2);
			List<ImovelFichaCompletaDTO> ifcdto = is.listarGaleriaFotosImoveis(dtor.id);
			if (ifcdto != null) {
				System.out.println("Galeria de Fotos do Imovel do cliente ID: " + dtor.id);
				if (ifcdto.size() > 0){
					for (ImovelFichaCompletaDTO ficha : ifcdto) {
						System.out.println(ficha.imovel.descricaoTituloAnuncio);
						if (ficha.imagensImovelTB != null){
							if (ficha.imagensImovelTB.size() > 0) {
								for (ImovelImagemVideoDTO tb : ficha.imagensImovelTB) {
									System.out.println(tb.nome);
								}
							}
						}
						
					}
				}
			}
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMensagem());
		}
		
	}

	@SuppressWarnings("unchecked")
	public void testListarGaleriaFotosImovelCliente08(){
		try {
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			List<ImovelFichaCompletaDTO> ifcdto = is.listarGaleriaFotosImoveis(8);
			if (ifcdto != null) {
				System.out.println("Galeria de Fotos do Imovel do cliente ID: 8");
				if (ifcdto.size() > 0){
					for (ImovelFichaCompletaDTO ficha : ifcdto) {
						System.out.println(ficha.imovel.descricaoTituloAnuncio);
						if (ficha.imagensImovelTB != null){
							if (ficha.imagensImovelTB.size() > 0) {
								for (ImovelImagemVideoDTO tb : ficha.imagensImovelTB) {
									System.out.println(tb.nome);
								}
							}
						}
						
					}
				}
			}
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMensagem());
		}
		
	}
	
	public void testAdicionarImovelCarteira() {
		try {
			String codigoEsperado = MSGCODE.IMOVEL_CADASTRADO_COM_SUCESSO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			assertEquals(codigoEsperado, dtopadrao1.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	public void testAdicionarImovelCarteiraComPlanoMensal() {
		try {
			String codigoEsperado = MSGCODE.IMOVEL_CADASTRADO_COM_SUCESSO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 100;
			imovel1.cliente.id = dtor.id;
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			assertEquals(codigoEsperado, dtopadrao1.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testAdicionarImovelCarteiraComPlanoTrimestral() {
		try {
			String codigoEsperado = MSGCODE.IMOVEL_CADASTRADO_COM_SUCESSO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 101;
			imovel1.cliente.id = dtor.id;
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			assertEquals(codigoEsperado, dtopadrao1.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testAdicionarImovelCarteiraComPlanoSemestral() {
		try {
			String codigoEsperado = MSGCODE.IMOVEL_CADASTRADO_COM_SUCESSO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 102;
			imovel1.cliente.id = dtor.id;
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			assertEquals(codigoEsperado, dtopadrao1.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	public void testAdicionarImovelCarteiraComPlanoAnual() {
		try {
			String codigoEsperado = MSGCODE.IMOVEL_CADASTRADO_COM_SUCESSO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 103;
			imovel1.cliente.id = dtor.id;
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			assertEquals(codigoEsperado, dtopadrao1.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testImovelComPlanoGratuito() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 1;
			imovel1.cliente.id = dtor.id;
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(imovel1.imovel.id);
			assertNotNull(ifcdto);
			assertTrue(ifcdto.imovelPlano.plano.id == Constantes.CODIGO_ESPECIAL_PLANO_GRATUITO);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testAdicionarImovelCarteiraComPlanoGratuito() {
		try {
			String codigoEsperado = MSGCODE.IMOVEL_CADASTRADO_COM_SUCESSO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			imovel1.imovelPlano = new ImovelPlanoDTO();
			imovel1.imovelPlano.plano = new PlanoDTO();
			imovel1.imovelPlano.plano.id = 1;
			imovel1.cliente.id = dtor.id;
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			assertEquals(codigoEsperado, dtopadrao1.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testAdicionarImovelCarteiraComPlanoGratuitoEmExcesso() {
		try {
			String codigoEsperado = MSGCODE.MAXIMO_IMOVEL_PERMITIDO_ULTRAPASSADO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			
			for (int i = 0; i < 3; i++){
				
				ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
				
				imovel1.imovelPlano = new ImovelPlanoDTO();
				imovel1.imovelPlano.plano = new PlanoDTO();
				imovel1.imovelPlano.plano.id = Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PLANO_ADESAO_GRATUITO)); 
				DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			}
			
			fail("*** Erro");
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			assertEquals(MSGCODE.MAXIMO_IMOVEL_PERMITIDO_ULTRAPASSADO, e.getCodigoErro());
		}
	}


	public void testAdicionarImovelCarteiraSemPlano() {
		try {
			String codigoEsperado = MSGCODE.INCLUIR_IMOVEL_SEM_PLANO_DEFINIDO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			
			//forçar o erro
			imovel1.imovelPlano = null;
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			fail("*** Erro.");
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			assertEquals(MSGCODE.INCLUIR_IMOVEL_SEM_PLANO_DEFINIDO, e.getCodigoErro());
		}
	}


	public void testApresentarImovelOrigemEmail() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			ImovelFichaCompletaDTO ifcdto = is.apresentarFichaCompletaImovel(imovel1.imovel.id, "EM");
			
			assertEquals(codigoEsperado, ifcdto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	public void testTrocarTarifaImovelCarteira() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			TabelaPrecoDTO tpvo = (TabelaPrecoDTO)imovel1.tabelaPreco.get(0);
			tpvo.valorTabela = 1591;
			
			imovel1.tabelaPreco.add(tpvo);
			
			dtopadrao1 = is.atualizarTarifaImovel(imovel1);
			
			assertEquals(codigoEsperado, dtopadrao1.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	public void testAdicionarImovelCarteiraCaracteristicaImovel() {
		try {
			String codigoEsperado = MSGCODE.IMOVEL_CADASTRADO_COM_SUCESSO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			assertEquals(codigoEsperado, dtopadrao1.msgcode);
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
	}

	public void testAdicionarCargaImovelCarteira() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			for (int i = 0; i < 1; i++){
				ImovelFichaCompletaDTO imovel = getImovelDTO(dtor);
				DTOPadrao dtopadrao = is.adicionarImovelCarteira(imovel);
			}
			
			//assertEquals(codigoEsperado, dtopadrao.msgcode);
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
	}
	
	public void testDistribuicaoVisitasImovel() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			ImovelService is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO dto = is.pesquisarFichaCompletaImovel(16534);
			String url = is.charterDistribuicaoVisitasImovel(dto, 2011);
			System.out.println(url);
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
	}
	
	//-----------------------------------------------------------------
	// metodos de apoio
	//-----------------------------------------------------------------
	private ClienteContraSenhaDTO getDTO() {
		ClienteContraSenhaDTO dto = new ClienteContraSenhaDTO();
		String i = String.valueOf(System.currentTimeMillis());
		dto.nome = "Julio Vitorino - " + i;
		dto.cpf = i.substring(0,11);
		dto.dataNascimento = new Date();
		dto.senha = "rcanet";
		dto.contraSenha = "rcanet";
		dto.email = i+"@ig.com.br";
		dto.urlwww = "www." + i + "com.br";
		dto.indicadorAutorizaNotificacao = Constantes.SIM;
		dto.indicadorTipoAnunciante = "P";
		dto.indicadorStatus = ClienteBusinessImpl.CONTA_STATUS_ATIVA;
		dto.endereco = new EnderecoDTO();
		dto.endereco.codigoUfCidadeItem = 1;
		dto.endereco.nome = "Rua " + i;
		dto.endereco.numero = "123";
		dto.endereco.complemento = "xpto";
		dto.endereco.bairro = "B:" + i;
		dto.endereco.cep = "27189098";
		dto.endereco.cidade = "Sao Paulo";
		return dto;
	}
	
	private ImovelFichaCompletaDTO getImovelDTO(ClienteDTO clientedto) throws AlugueRelaxeException {
		
		// Monta uma composicao aleatoria para trabalhar com imoveis diferentes
		Random rndgen = new Random();
		int randomcodigoUfCidadeItem = rndgen.nextInt(5000);
		int randomBanheiro = rndgen.nextInt(20);
		int randomQuartos = rndgen.nextInt(20);
		int randomSuites = rndgen.nextInt(20);
		int randomCapacidade = rndgen.nextInt(20);
		int randomCliente = rndgen.nextInt((int)clientedto.id);
		
		int randomTipoProp = rndgen.nextInt(9);
		String tipoProp = "CAHXFZSLP".substring(randomTipoProp,randomTipoProp+1);
		
		ImovelFichaCompletaDTO dto = new ImovelFichaCompletaDTO();
		
		String i = String.valueOf(System.currentTimeMillis());
		dto.cliente = clientedto;
		
		randomCliente = (randomCliente < 2 ? 2 : randomCliente);
		dto.imovel = new ImovelDTO();
		// Se quiser codigo do cliente randomico apenas tire o comentário
		//dto.imovel.idCliente = (long) randomCliente;
		
		dto.imovel.idCliente = clientedto.id;

		dto.imovel.qtdeQuartos = randomQuartos;
		dto.imovel.qtdeSuites = randomSuites;
		dto.imovel.qtdeBanheiros = randomBanheiro;
		dto.imovel.qtdeCapacidade = randomCapacidade;
		dto.imovel.indicadorStatus = ImovelBusiness.STATUS_ATIVO;
		dto.imovel.indicadorMostraTabelaPreco = Constantes.SIM;
		dto.imovel.indicadorPermiteAlugarFestas = Constantes.NAO;
		dto.imovel.descricaoTituloAnuncio = "Casa de praia dos sonhos!" + this.calculaHash().substring(0, 6);
		dto.imovel.descricaoGeral = "Casa na praia maravilhosa " + getIpsumLorem(250);
		dto.imovel.descricaoQuartos = "Quartos fantasticos " + getIpsumLorem(250);
		dto.imovel.indicadorCondominio = "S";
		dto.imovel.descricaoArredores = "Perto de tudo " + getIpsumLorem(250);
		dto.imovel.observacoes = "Simplesmente demais";
		dto.imovel.indicadorTipoPropriedade = tipoProp;
		dto.imovel.qtdeVisitas = 0;
		dto.imovel.endereco = new EnderecoDTO();
		dto.imovel.endereco.codigoUfCidadeItem = randomcodigoUfCidadeItem;
		dto.imovel.endereco.nome = "Rua " + i;
		dto.imovel.endereco.numero = "123";
		dto.imovel.endereco.bairro = "B:" + i;
		dto.imovel.endereco.complemento = "C:" + i;
		dto.imovel.endereco.cep = "27189098";
		// popula caracteristicas do imovel
		dto.caracteristicaImovel = carregaCaracteristicas();
		dto.caracteristicaCondominio = carregaCaracteristicas();
		dto.tabelaPreco = carregaTabelaPreco();
		
		// popula imovel em plano gratuito
		dto.imovelPlano = new ImovelPlanoDTO();
		dto.imovelPlano.plano = new PlanoDTO();
		dto.imovelPlano.plano.id = Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PLANO_ADESAO_GRATUITO)); 
		
		return dto;
	}

	private ImovelFichaCompletaDTO getImovelDTO(ClienteDTO clientedto, long idPlano) throws AlugueRelaxeException {
		
		ImovelFichaCompletaDTO ifcdto = this.getImovelDTO(clientedto);
		
		// popula imovel em plano parametrizado
		ifcdto.imovelPlano.plano.id = idPlano; 
		
		return ifcdto;
	}

	
	private List<ImovelCaracteristicaDTO> carregaCaracteristicas() {
		List<ImovelCaracteristicaDTO> lst = new ArrayList<ImovelCaracteristicaDTO>();
		for (long i = 1; i < 20; i++){
			ImovelCaracteristicaDTO icdto = new ImovelCaracteristicaDTO();
			icdto.caracteristica = new CaracteristicaDTO();
			icdto.caracteristica.id = i;
			lst.add(icdto);
		}
		return lst;

	}
	
	private List<TabelaPrecoDTO> carregaTabelaPreco() {
		List<TabelaPrecoDTO> lst = new ArrayList<TabelaPrecoDTO>();
		for (int j = 0; j < 4; j++) {
			String i = String.valueOf(System.currentTimeMillis());
			TabelaPrecoDTO tpdto = new TabelaPrecoDTO();
			tpdto.dataInicio = new Date();
			tpdto.dataFim = new Date();
			tpdto.observacao = "observacao " + i;
			tpdto.textoMinimoDe = "dias " + i;
			tpdto.textoPeriodo = "P:" + i;
			tpdto.valorTabela = j + 420;
			lst.add(tpdto);
		}
		
		
		return lst;
	}

	private String getIpsumLorem(int tamanho) {
		
		String str =  "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "+
		"Etiam eget ligula eu lectus lobortis condimentum. "+
		"Aliquam nonummy auctor massa. Pellentesque habitant morbi tristique senectus et netus et "+
		"malesuada fames ac turpis egestas. Nulla at risus. Quisque purus magna, auctor et, "+
		"sagittis ac, posuere eu, lectus. Nam mattis, felis ut adipiscing.";
		

		return str.substring(0, tamanho-1);
	}
	

	private String calculaHash() {
		String retorno = "";
		StringBuilder sb = new StringBuilder();
		sb.append(getIpsumLorem(250) );
		sb.append(String.valueOf(System.currentTimeMillis()));

		BaseFactorySecurity sha1 = BaseFactorySecurity.getInstance(0);
		try {
			retorno = sha1.criptografar(sb.toString());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	
}
