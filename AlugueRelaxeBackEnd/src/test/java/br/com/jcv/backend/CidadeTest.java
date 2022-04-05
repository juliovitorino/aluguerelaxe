/**
 * 
 */
package br.com.jcv.backend;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.cidade.CidadeServiceImpl;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.ValidadorFactory;
import br.com.jcv.backend.interfaces.services.ApplicationService;
import br.com.jcv.backend.validador.Validador;

/**
 * @author Julio
 *
 */
public class CidadeTest extends TestCase {

	private static Logger logger = Logger.getLogger(CidadeTest.class.getName());

	@SuppressWarnings("unchecked")
	public void testValidadorDtoCidadeComErros() {
		Validador validador = ValidadorFactory.getInstanceCidade();
		CidadeDTO dto = new CidadeDTO();
		List<String> erros = validador.execute(dto);
		if (erros == null) {
			fail("Objeto deveria apresentar um dto com a lista de erros");
		} else {
			assertNotNull(erros);
		}
	}
	public void testListarCidadesUf() {
		try {
			CidadeServiceImpl cs = new CidadeServiceImpl();
			List<CidadeDTO> list = cs.listCidadesDaUfComImoveis("RJ");
			assertNotNull(list);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void testListarCidadesUfMaisVisitadas() {
		try {
			CidadeServiceImpl cs = new CidadeServiceImpl();
			List<CidadeDTO> list = cs.listCidadesDaUfComImovesMaisVisitadas("RJ");
			assertNotNull(list);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void testAtualizarRegistro() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			ApplicationService<CidadeDTO> bs = new CidadeServiceImpl();
			
			//Insere um novo registro
			CidadeDTO dtoInserir = new CidadeDTO();
			dtoInserir.setNome("Cidade Test - " + System.currentTimeMillis());
			CidadeDTO dto = bs.gravarRegistro(dtoInserir);
			
			// Apresenta os dados no console
			logger.info("Dados inseridos para atualizaééo");
			logger.info("código da cidade --> " + dto.getId());
			logger.info("Nome             --> " + dto.getNome());
			
			// Modifica os dados para atualizacao
			dto.setNome("Cidade Alterado - " + System.currentTimeMillis());
			CidadeDTO dtoAtualizado = bs.atualizarRegistro(dto);

			// Apresenta os dados no console
			logger.info("Dados atualizados");
			logger.info("código --> " + dtoAtualizado.getId());
			logger.info("Nome   --> " + dtoAtualizado.getNome());
			
			assertEquals(codigoEsperado, dtoAtualizado.msgcode);
		} catch (AlugueRelaxeException e) {
			logger.error("Erro:" + e.getMessage());
			fail("Néo conseguiu criar objeto Depoimento");
		}
	}


}
