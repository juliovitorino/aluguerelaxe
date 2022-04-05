package br.com.jcv.backend;

import java.util.List;

import junit.framework.TestCase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ModoPublicidadeService;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeDTO;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeServiceImpl;

public class ModoPublicidadeTest extends TestCase {

	public void testListarRegistros() {
		try {
			
			ModoPublicidadeService<ModoPublicidadeDTO> mps = new ModoPublicidadeServiceImpl();
			List<? extends ModoPublicidadeDTO> lst = mps.listarRegistros();
			assertNotNull(lst);
			
		} catch (AlugueRelaxeException e) {
			fail(e.getMensagem());
		}
	}

}
