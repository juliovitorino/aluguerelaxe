package br.com.jcv.backend;

import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ApplicationService;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelDTO;
import br.com.jcv.backend.variavel.VariavelServiceImpl;

/**
 * @author Julio
 *
 */
public class VariavelTest extends TestCase {
	
	private static Logger logger =  Logger.getLogger(VariavelTest.class);
	
	public void testGravarRegistro() {
		try {
			
			String expected = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			ApplicationService<VariavelDTO> bs = new VariavelServiceImpl();
			VariavelDTO dtoInserir = gerarDTO();
			VariavelDTO dto = bs.gravarRegistro(dtoInserir); 
			
			// Apresenta os dados no console
			logger.info("Dados inseridos para atualizaééo");
			logger.info("código --> " + dto.getId());
			logger.info("Descricao   --> " + dto.getDescricao());
			logger.info("Valor   --> " + dto.getValor());

			assertEquals(expected, dto.msgcode);
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Ops! Néo conseguiu criar objeto Depoimento");
		}
	}

	public void testGravarCodigoEstourado() {
		String expected = MSGCODE.ESTOURO_CAMPO_ID_VARIAVEL;
		try {
			
			ApplicationService<VariavelDTO> bs = new VariavelServiceImpl();
			
			//Insere um novo registro
			VariavelDTO dtoInserir = gerarDTO();
			dtoInserir.setId(StringUtil.rPad("ESTOURO ", "X", 200));
			VariavelDTO dto = bs.gravarRegistro(dtoInserir);
			
			fail("Ops! conseguiu Inserir o código estourado");

		} catch (AlugueRelaxeException e) {
			assertEquals(expected, e.getCodigoErro());
		}
	}

	public void testGravarCodigoNulo() {
		String expected = MSGCODE.CAMPO_ID_VARIAVEL_NULO;
		try {
			
			ApplicationService<VariavelDTO> bs = new VariavelServiceImpl();
			
			//Insere um novo registro
			VariavelDTO dtoInserir = gerarDTO();
			dtoInserir.setId(null);
			@SuppressWarnings("unused")
			VariavelDTO dto = bs.gravarRegistro(dtoInserir);
			
			fail("Ops! conseguiu Inserir o código nulo");

		} catch (AlugueRelaxeException e) {
			logger.debug(e.getMensagem());
			assertEquals(expected, e.getCodigoErro());
		}
	}
	
	public void testGravarDescricaoNula() {
		String expected = MSGCODE.CAMPO_DESCRICAO_VARIAVEL_NULO;
		try {
			
			ApplicationService<VariavelDTO> bs = new VariavelServiceImpl();
			
			//Insere um novo registro
			VariavelDTO dtoInserir = gerarDTO();
			dtoInserir.setDescricao(null);
			VariavelDTO dto = bs.gravarRegistro(dtoInserir);
			
			fail("Ops! conseguiu Inserir o Descrição nulo");

		} catch (AlugueRelaxeException e) {
			logger.debug(e.getMensagem());
			assertEquals(expected, e.getCodigoErro());
		}
	}

	public void testEstouroDescricaoVariavel() {
		String expected = MSGCODE.ESTOURO_CAMPO_DESCRICAO_VARIAVEL;
		try {
			
			ApplicationService<VariavelDTO> bs = new VariavelServiceImpl();
			VariavelDTO dtoInserir = gerarDTO();
			dtoInserir.setDescricao(StringUtil.rPad("NONO", "X", 5000));
			VariavelDTO dto = bs.gravarRegistro(dtoInserir);
			
			logger.info("código --> " + dto.getId());
			logger.info("Mensagem   --> " + dto.getDescricao());
			fail("Ops! conseguiu montar uma mensagem maior que 1000 caracteres.");

		} catch (AlugueRelaxeException e) {
			logger.info(e.getMensagem());
			assertEquals(expected, e.getCodigoErro());
		}
	}

	public void testAtualizarRegistro() {
		try {
			ApplicationService<VariavelDTO> bs = new VariavelServiceImpl();
			
			//Insere um novo registro
			VariavelDTO dtoInserir = gerarDTO();
			VariavelDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos para atualizaééo");
			logger.info("código --> " + dto.getId());
			logger.info("Descricao   --> " + dto.getDescricao());
			logger.info("Valor   --> " + dto.getValor());
			
			// Modifica os dados para atualizacao
			dto.setDescricao("Descricao Alterada - " + this.hashCode());
			dto.setValor("Valor alterado - " + this.hashCode());
			VariavelDTO dtoAtualizado = bs.atualizarRegistro(dto);

			// Apresenta os dados no console
			logger.info("Dados alterados para atualizaééo");
			logger.info("código --> " + dtoAtualizado.getId());
			logger.info("Descricao   --> " + dtoAtualizado.getDescricao());
			logger.info("Valor   --> " + dtoAtualizado.getValor());
			
			assertNotNull(dtoAtualizado);
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Néo conseguiu criar objeto Mensagem");
		}
		
	}
	
	public void testExcluirRegistro() {
		try {
			ApplicationService<VariavelDTO> bs = new VariavelServiceImpl();
			
			//Insere um novo registro
			VariavelDTO dtoInserir = gerarDTO();
			VariavelDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos para exclusão");
			logger.info("código --> " + dto.getId());
			logger.info("Descricao   --> " + dto.getDescricao());

			bs.excluirRegistro(dto);

			assertNotNull(dto);
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Néo conseguiu criar objeto Mensagem");
		}
		
	}

	public void testPesquisarRegistro() {
		try {
			ApplicationService<VariavelDTO> bs = new VariavelServiceImpl();
			
			//Insere um novo registro
			VariavelDTO dtoInserir = gerarDTO();
			VariavelDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos");
			logger.info("código --> " + dto.getId());
			logger.info("Descricao   --> " + dto.getDescricao());
			logger.info("Valor   --> " + dto.getValor());
			
			// Modifica os dados para atualizacao
			VariavelDTO dtoProcurado = bs.pesquisarRegistro(dto);

			// Apresenta os dados no console
			logger.info("Dados Localizados");
			logger.info("código --> " + dtoProcurado.getId());
			logger.info("Descricao   --> " + dto.getDescricao());
			
			assertEquals(dto.getId(), dtoProcurado.getId());
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Néo conseguiu criar objeto Mensagem");
		}
		
	}

	public void testListarRegistros() {
		try {
			ApplicationService<VariavelDTO> bs = new VariavelServiceImpl();
			List<? extends VariavelDTO> list = bs.listarRegistros();
			logger.info("--> Total Registros = " + list.size());
			
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Néo conseguiu criar objeto Mensagem");
		}
		
	}

	private VariavelDTO gerarDTO() {
		VariavelDTO dtoInserir = new VariavelDTO();
		Random random = new Random();
		dtoInserir.setId(String.valueOf(System.currentTimeMillis() + random.nextLong()));
		dtoInserir.setDescricao("Descricao: " + this.hashCode());
		dtoInserir.setValor("Valor: " + this.hashCode());
		return dtoInserir;
	}

	public void testInserirCodigoDuplicado() {
		String expected = MSGCODE.ERRO_GENERICO_BD;
		try {
			
			ApplicationService<VariavelDTO> bs = new VariavelServiceImpl();
			VariavelDTO dtoInserir = gerarDTO();
			VariavelDTO dto = bs.gravarRegistro(dtoInserir); 
			
			// Apresenta os dados no console
			logger.info("Dados inseridos para atualizaééo");
			logger.info("código --> " + dto.getId());
			logger.info("Descricao   --> " + dto.getDescricao());
			logger.info("Valor   --> " + dto.getValor());

			// Tenta inserir novamente
			dto = bs.gravarRegistro(dtoInserir); 
			fail("Ops! conseguiu criar duplicado");

		} catch (AlugueRelaxeException e) {
			logger.debug(e.getMensagem());
			assertEquals(expected, e.getCodigoErro());
		} catch (Exception e) {
			fail("Ops! ocorreu algum erro, verificar.");
		}	
	}


}
