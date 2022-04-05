package br.com.jcv.backend;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.depoimento.DepoimentoDTO;
import br.com.jcv.backend.depoimento.DepoimentoServiceImpl;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ApplicationService;
import br.com.jcv.backend.interfaces.services.DepoimentoService;

/**
 * @author Julio
 *
 */
public class DepoimentoTest extends TestCase {
	
	private static Logger logger =  Logger.getLogger(DepoimentoTest.class);
	
	/**
	 * 
	 */
	public void testGravarRegistro() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			ApplicationService<DepoimentoDTO> bs = new DepoimentoServiceImpl();
			DepoimentoDTO dtoInserir = new DepoimentoDTO();
			dtoInserir.setNome("Julio Cesar Vitorino - " + this.hashCode());
			dtoInserir.setDepoimento("Depoimento - " + this.hashCode());
			DepoimentoDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos para atualizaééo");
			logger.info("código --> " + dto.getId());
			logger.info("Nome   --> " + dto.getNome());
			logger.info("Depoimento --> " + dto.getDepoimento());

			assertEquals(codigoEsperado, dto.msgcode);
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Néo conseguiu criar objeto Depoimento");
		}
	}
	
	public void testListarPaginasDepoimento() {
		try {
			DepoimentoService<DepoimentoDTO> bs = new DepoimentoServiceImpl();
			List<DepoimentoDTO> list = bs.ListarPaginaDepoimentos();
			logger.info("--> Total Registros = " + list.size());
			
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Néo conseguiu criar objeto Mensagem");
		}
	}
	
	public void testListar10Registros() {
		try {
			DepoimentoServiceImpl bs = new DepoimentoServiceImpl();
			List<DepoimentoDTO> list = bs.listarRegistros(10);
			logger.info("--> Total Registros = " + list.size());
			
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Néo conseguiu criar objeto Mensagem");
		}
	}
	
	public void testAdicionarDepoimento() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			DepoimentoDTO dto = new DepoimentoDTO();
			String id = String.valueOf(System.currentTimeMillis());
			dto.nome = "Julio Cesar Vitorino " + id;
			dto.depoimento = "Nunca desista do seu sonho! " + id;
			
			DepoimentoService<DepoimentoDTO> ds = new DepoimentoServiceImpl();
			DTOPadrao dtoretorno = ds.adicionarDepoimento(dto);
			
			assertEquals(codigoEsperado, dtoretorno.msgcode);
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	public void testCargaAdicionarDepoimento() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			DepoimentoDTO dto = new DepoimentoDTO();
			String id = String.valueOf(System.currentTimeMillis());
			dto.nome = "Julio Cesar Vitorino " + id;
			dto.depoimento = "Nunca desista do seu sonho! " + id;
			
			DepoimentoService<DepoimentoDTO> ds = new DepoimentoServiceImpl();
			DTOPadrao dtoretorno = null;
			for (int i = 0; i < 500; i++){
				dtoretorno = ds.adicionarDepoimento(getDTO());
			}
			
			assertEquals(codigoEsperado, dtoretorno.msgcode);
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testAdicionarDepoimentoImproprio() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			DepoimentoDTO dto = new DepoimentoDTO();
			String id = String.valueOf(System.currentTimeMillis());
			dto.nome = "Bunda mole da silas " + id;
			dto.depoimento = "Filho da puta! Viado! Seu merda! " + id;
			
			DepoimentoService<DepoimentoDTO> ds = new DepoimentoServiceImpl();
			DTOPadrao dtoretorno = ds.adicionarDepoimento(dto);
			
			assertEquals(codigoEsperado, dtoretorno.msgcode);
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testAdicionarAcentuacao() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			DepoimentoDTO dto = new DepoimentoDTO();
			String id = String.valueOf(System.currentTimeMillis());
			dto.nome = "Vovó eliás" + id;
			dto.depoimento = "Eu préciso de acéntuáção do depoimento" + id;
			
			DepoimentoService<DepoimentoDTO> ds = new DepoimentoServiceImpl();
			DTOPadrao dtoretorno = ds.adicionarDepoimento(dto);
			
			assertEquals(codigoEsperado, dtoretorno.msgcode);
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testLiberarDepoimentoAcoesIncorretas() {
		String codigoEsperado = MSGCODE.ACAO_NAO_PERMITIDA_PARA_LIBERACAO;
		try {
			DepoimentoDTO dto = new DepoimentoDTO();
			String id = String.valueOf(System.currentTimeMillis());
			dto.nome = "Euclides da Cunha " + id;
			dto.depoimento = "Site Memoravel meu amigo " + id;
			
			DepoimentoService<DepoimentoDTO> ds = new DepoimentoServiceImpl();
			dto = ds.adicionarDepoimento(dto);
			
			DTOPadrao dtoretorno = ds.liberarDepoimento(String.valueOf(dto.id), "INVALIDAR");
			
			fail("Epa! Conseguiu executar método com opção inválida!");
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}

	
	
	public void testAprovarDepoimento() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			DepoimentoDTO dto = new DepoimentoDTO();
			String id = String.valueOf(System.currentTimeMillis());
			dto.nome = "Julio Cesar Vitorino " + id;
			dto.depoimento = "Este site é sucesso garantido! " + id;
			
			DepoimentoService<DepoimentoDTO> ds = new DepoimentoServiceImpl();
			dto = ds.adicionarDepoimento(dto);
			
			DTOPadrao dtoretorno = ds.liberarDepoimento(String.valueOf(dto.id), Constantes.APROVAR);
			
			assertEquals(codigoEsperado, dtoretorno.msgcode);
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testReprovarDepoimento() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			DepoimentoDTO dto = new DepoimentoDTO();
			String id = String.valueOf(System.currentTimeMillis());
			dto.nome = "Julio Cesar Vitorino " + id;
			dto.depoimento = "Puta que pariu! Filho de uma egua! " + id;
			
			DepoimentoService<DepoimentoDTO> ds = new DepoimentoServiceImpl();
			dto = ds.adicionarDepoimento(dto);
			
			DTOPadrao dtoretorno = ds.liberarDepoimento(String.valueOf(dto.id), Constantes.REPROVAR);
			
			assertEquals(codigoEsperado, dtoretorno.msgcode);
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	private DepoimentoDTO getDTO() {
		String ipsum = "Lorem Ipsum is simply dummy text of the printing and typesetting "+
		"industry. Lorem Ipsum has been the industry's standard dummy text ever since " +
		"the 1500s, when an unknown printer took a galley of type and scrambled it to "+
		"make a type specimen book. It has survived not only five centuries, but also the "+
		"leap into electronic typesetting, remaining essentially unchanged. It was "+
		"popularised in the 1960s with the release of Letraset sheets containing Lorem "+
		"Ipsum passages, and more recently with desktop publishing software like Aldus "+
		"PageMaker including versions of Lorem Ipsum.";
		
		DepoimentoDTO dto = new DepoimentoDTO();
		dto.nome = "Julio Vitorino - " + String.valueOf(System.currentTimeMillis());
		dto.depoimento = String.valueOf(System.currentTimeMillis()) + ipsum; 
		dto.depoimento = dto.depoimento.substring(1, 500);
		
		return dto;
	}

}
