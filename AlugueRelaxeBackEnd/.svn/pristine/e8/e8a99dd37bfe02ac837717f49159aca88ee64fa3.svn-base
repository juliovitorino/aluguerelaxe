/**
 * 
 */
package br.com.jcv.backend;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import br.com.jcv.backend.caracteristicas.CaracteristicaDTO;
import br.com.jcv.backend.caracteristicas.CaracteristicaServiceImpl;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ApplicationService;
import br.com.jcv.backend.interfaces.services.CaracteristicaService;
import br.com.jcv.backend.utility.Introspeccao;

/**
 * @author Julio
 *
 */
public class CaracteristicaTest extends TestCase {

	private static Logger logger = Logger.getLogger(CaracteristicaTest.class.getName());

	public void testAtualizarRegistro() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			ApplicationService<CaracteristicaDTO> bs = new CaracteristicaServiceImpl();
			
			//Insere um novo registro
			CaracteristicaDTO dtoInserir = new CaracteristicaDTO();
			dtoInserir.setNome("[NOVO] - Caracteristica Test - " + System.currentTimeMillis());
			dtoInserir.setDescricaoAnuncio("Descrição Anéncio Test - " + System.currentTimeMillis());
			CaracteristicaDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos para atualização");
			Introspeccao.debugdto(logger, dto);
			
			// Modifica os dados para atualizacao
			dto.setNome("[ALTERADO] " + System.currentTimeMillis());
			CaracteristicaDTO dtoAtualizado = bs.atualizarRegistro(dto);

			// Apresenta os dados no console
			logger.info("Dados atualizados");
			Introspeccao.debugdto(logger, dtoAtualizado);
			
			assertEquals(codigoEsperado, dtoAtualizado.msgcode);
		} catch (AlugueRelaxeException e) {
			logger.error(e.getMessage());
			fail("Néo conseguiu criar objeto Depoimento");
		}
	}

	public void testExcluirRegistro() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			ApplicationService<CaracteristicaDTO> bs = new CaracteristicaServiceImpl();
			
			//Insere um novo registro
			CaracteristicaDTO dtoInserir = new CaracteristicaDTO();
			dtoInserir.setNome("[EXCLUIR] - Caracteristica Test - " + System.currentTimeMillis());
			dtoInserir.setDescricaoAnuncio("Descrição Anéncio Test - " + System.currentTimeMillis());
			CaracteristicaDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos para exclusão");
			Introspeccao.debugdto(logger, dto);
			
			// Modifica os dados para atualizacao
			CaracteristicaDTO dtoAtualizado = bs.excluirRegistro(dto);

			// Apresenta os dados no console
			logger.info("Dados excluédos");
			Introspeccao.debugdto(logger, dtoAtualizado);
			
			assertEquals(codigoEsperado, dtoAtualizado.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail("Não conseguiu excluir o registro");
		}
	}

	public void testGravarRegistro() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			ApplicationService<CaracteristicaDTO> bs = new CaracteristicaServiceImpl();
			
			//Insere um novo registro
			CaracteristicaDTO dtoInserir = new CaracteristicaDTO();
			dtoInserir.setNome("[NOVO REGISTRO] - Test - " + System.currentTimeMillis());
			dtoInserir.setDescricaoAnuncio("Descrição Anéncio Test - " + System.currentTimeMillis());
			CaracteristicaDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos");
			Introspeccao.debugdto(logger, dto);
			
			assertEquals(codigoEsperado, dto.msgcode);
		} catch (AlugueRelaxeException e) {
			logger.error(e.getMessage());
			fail("Néo conseguiu criar objeto Depoimento");
		}
	}

	public void testInserirNomeDuplicado() {
		String codigoEsperado = MSGCODE.ERRO_GENERICO_BD;
		try {
			ApplicationService<CaracteristicaDTO> bs = new CaracteristicaServiceImpl();
			
			//Insere um novo registro
			CaracteristicaDTO dtoInserir = new CaracteristicaDTO();
			dtoInserir.setNome("[NOVO REGISTRO] - Test - " + System.currentTimeMillis());
			dtoInserir.setDescricaoAnuncio("Descricao Anuncio Test - " + System.currentTimeMillis());
			CaracteristicaDTO dto = bs.gravarRegistro(dtoInserir);

			// Apresenta os dados no console
			logger.info("Dados inseridos");
			Introspeccao.debugdto(logger, dto);
			
			//força a duplicação
			dto = bs.gravarRegistro(dtoInserir);

			fail("conseguiu inserir duplicado");
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}

	public void testInserirCodigoDuplicado() {
		String codigoEsperado = MSGCODE.ERRO_GENERICO_BD;
		try {
			ApplicationService<CaracteristicaDTO> bs = new CaracteristicaServiceImpl();
			
			//Insere um novo registro
			CaracteristicaDTO dtoInserir = new CaracteristicaDTO();
			dtoInserir.setNome("[NOVO REGISTRO] - Test - " + System.currentTimeMillis());
			dtoInserir.setDescricaoAnuncio("Descrição Anúncio Test - " + System.currentTimeMillis());
			CaracteristicaDTO dto = bs.gravarRegistro(dtoInserir);

			// Apresenta os dados no console
			logger.info("Dados inseridos");
			Introspeccao.debugdto(logger, dto);
			
			//força a duplicação
			dto = bs.gravarRegistro(dto);

			fail("conseguiu inserir duplicado");
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
 	}

	public void testListarRegistros() {
		try {
			CaracteristicaService<CaracteristicaDTO> is = new CaracteristicaServiceImpl();
			List<CaracteristicaDTO> lst = is.listarRegistros();
			for (CaracteristicaDTO dto : lst) {
				System.out.println(dto.id + " - " + dto.nome);
			}
			assertNotNull("Caracteristicas Populadas", lst);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail("Erro em testListarRegistros");
		}

	}

}
