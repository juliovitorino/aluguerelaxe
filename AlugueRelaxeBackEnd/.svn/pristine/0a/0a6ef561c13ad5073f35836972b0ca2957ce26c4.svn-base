/**
 * 
 */
package br.com.jcv.backend;

import java.util.List;

import junit.framework.TestCase;
import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.uf.UFDTO;
import br.com.jcv.backend.uf.UFServiceImpl;

/**
 * @author Julio
 *
 */
public class UFTest extends TestCase {

	public void testListarCidadesDaUF() {
		try {
			UFServiceImpl bs = new UFServiceImpl();
			List<CidadeDTO> dto = bs.listarCidadesDaUF("RJ");

			assertTrue(dto.size() > 0);

		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail();
		}
	}
	public void testCharterImoveisUF() {
		try {
			UFServiceImpl bs = new UFServiceImpl();
			String url = bs.charterSumarizadoImoveisUF();
			System.out.println(url);

			assertTrue(url.length() > 0);

		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	

}
