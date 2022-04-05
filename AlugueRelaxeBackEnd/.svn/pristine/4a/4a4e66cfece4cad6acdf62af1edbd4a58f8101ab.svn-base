package br.com.jcv.backend;

import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ApplicationService;
import br.com.jcv.backend.mensagem.MensagemDTO;
import br.com.jcv.backend.mensagem.MensagemServiceImpl;
import br.com.jcv.backend.utility.StringUtil;

/**
 * @author Julio
 *
 */
public class MensagemTest extends TestCase {
	
	private static Logger logger =  Logger.getLogger(MensagemTest.class);
	
	/**
	 * 
	 */
	public void testGravarRegistro() {
		try {
			
			String expected = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			ApplicationService<MensagemDTO> bs = new MensagemServiceImpl();
			MensagemDTO dtoInserir = gerarDTO();
			MensagemDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos para atualização");
			logger.info("código --> " + dto.getId());
			logger.info("Mensagem   --> " + dto.getMensagem());

			assertEquals(expected, dto.msgcode);
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Ops! Néo conseguiu criar objeto Depoimento");
		}
	}

	public void testGravarCodigoEstourado() {
		String expected = MSGCODE.ERRO_GENERICO_BD;
		try {
			
			ApplicationService<MensagemDTO> bs = new MensagemServiceImpl();
			MensagemDTO dtoInserir = gerarDTO();
			dtoInserir.setId("MSTT-97896869");
			MensagemDTO dto = bs.gravarRegistro(dtoInserir);
			
			logger.info("código --> " + dto.getId());
			logger.info("Mensagem   --> " + dto.getMensagem());
			fail("Ops! conseguiu Inserir o código estourado");

		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			assertEquals(expected, e.getCodigoErro());
		}
	}

	public void testGravarMensagemNula() {
		String expected = MSGCODE.CAMPO_VAZIO;
		try {
			
			ApplicationService<MensagemDTO> bs = new MensagemServiceImpl();
			MensagemDTO dtoInserir = gerarDTO();
			dtoInserir.setMensagem(null);
			MensagemDTO dto = bs.gravarRegistro(dtoInserir);
			
			logger.info("código --> " + dto.getId());
			logger.info("Mensagem   --> " + dto.getMensagem());
			fail("Ops! conseguiu Inserir a mensagem nula");

		} catch (AlugueRelaxeException e) {
			logger.info(e.getMensagem());
			assertEquals(expected, e.getCodigoErro());
		}
	}

	public void testEstouroTamanhoMensagem() {
		String expected = MSGCODE.ESTOURO_CAMPO_MENSAGEM;
		try {
			
			ApplicationService<MensagemDTO> bs = new MensagemServiceImpl();
			MensagemDTO dtoInserir = gerarDTO();
			dtoInserir.setMensagem(StringUtil.rPad("NONO", "X", 2000));
			MensagemDTO dto = bs.gravarRegistro(dtoInserir);
			
			logger.info("código --> " + dto.getId());
			logger.info("Mensagem   --> " + dto.getMensagem());
			fail("Ops! conseguiu montar uma mensagem maior que 1000 caracteres.");

		} catch (AlugueRelaxeException e) {
			logger.info(e.getMensagem());
			assertEquals(expected, e.getCodigoErro());
		}
	}

	public void testAtualizarRegistro() {
		try {
			ApplicationService<MensagemDTO> bs = new MensagemServiceImpl();
			
			//Insere um novo registro
			MensagemDTO dtoInserir = gerarDTO();
			MensagemDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos para atualização");
			logger.info("código --> " + dto.getId());
			logger.info("Mensagem   --> " + dto.getMensagem());
			
			// Modifica os dados para atualizacao
			dto.setMensagem("Mensagem Alterada - " + this.hashCode());
			MensagemDTO dtoAtualizado = bs.atualizarRegistro(dto);

			// Apresenta os dados no console
			logger.info("Dados alterados para atualização");
			logger.info("código --> " + dtoAtualizado.getId());
			logger.info("Mensagem   --> " + dtoAtualizado.getMensagem());
			
			assertNotNull(dtoAtualizado);
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Néo conseguiu criar objeto Mensagem");
		}
		
	}
	
	public void testExcluirRegistro() {
		try {
			ApplicationService<MensagemDTO> bs = new MensagemServiceImpl();
			
			//Insere um novo registro
			MensagemDTO dtoInserir = gerarDTO();
			MensagemDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos para exclusão");
			logger.info("código --> " + dto.getId());
			logger.info("Mensagem   --> " + dto.getMensagem());

			
			bs.excluirRegistro(dto);

			assertNotNull(dto);
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Néo conseguiu criar objeto Mensagem");
		}
		
	}

	public void testPesquisarRegistro() {
		try {
			ApplicationService<MensagemDTO> bs = new MensagemServiceImpl();
			
			//Insere um novo registro
			MensagemDTO dtoInserir = gerarDTO();
			MensagemDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos");
			logger.info("código --> " + dto.getId());
			logger.info("Mensagem   --> " + dto.getMensagem());
			
			// Modifica os dados para atualizacao
			MensagemDTO dtoProcurado = bs.pesquisarRegistro(dto);

			// Apresenta os dados no console
			logger.info("Dados Localizados");
			logger.info("código --> " + dtoProcurado.getId());
			logger.info("Mensagem   --> " + dto.getMensagem());
			
			assertEquals(dto.getId(), dtoProcurado.getId());
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Néo conseguiu criar objeto Mensagem");
		}
		
	}

	public void testListarRegistros() {
		try {
			ApplicationService<MensagemDTO> bs = new MensagemServiceImpl();
			List<? extends MensagemDTO> list = bs.listarRegistros();
			logger.info("--> Total Registros = " + list.size());
			
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			fail("Néo conseguiu criar objeto Mensagem");
		}
		
	}

	private MensagemDTO gerarDTO() {
		MensagemDTO dtoInserir = new MensagemDTO();
		Random random = new Random();
		dtoInserir.setId(String.valueOf(System.currentTimeMillis() + random.nextLong()).substring(0,8));
		dtoInserir.setMensagem("Mensagem " + this.hashCode());
		return dtoInserir;
	}

	public void testInserirCodigoDuplicado() {
		String expected =  MSGCODE.ERRO_GENERICO_BD;
		try {
			
			ApplicationService<MensagemDTO> bs = new MensagemServiceImpl();
			MensagemDTO dtoInserir = gerarDTO();
			MensagemDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos para atualização");
			logger.info("código --> " + dto.getId());
			logger.info("Mensagem   --> " + dto.getMensagem());
			
			//Tenta inserir o mesmo dto outra vez
			MensagemDTO dtotemp = bs.gravarRegistro(dtoInserir);
			
			fail("Ops! Néo conseguiu criar objeto Depoimento");
		} catch (AlugueRelaxeException e) {
			logger.debug("Erro:" + e.getMessage());
			assertEquals(expected, e.getCodigoErro());
		}
	}


}
