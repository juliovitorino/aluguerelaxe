package br.com.jcv.backend;

import java.util.List;

import junit.framework.TestCase;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.PlanoService;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.plano.PlanoServicesImpl;

public class PlanoTest extends TestCase {
	
	public void testListarRegistros() {
		try {
			PlanoService<PlanoDTO> ps = new PlanoServicesImpl();
			List lst = ps.listarRegistros();
			assertNotNull(lst);
			
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	public void testListarRegistrosTipoNormal() {
		try {
			PlanoService<PlanoDTO> ps = new PlanoServicesImpl();
			List lst = ps.listarRegistros(Constantes.TIPO_PLANO_NORMAL);
			assertNotNull(lst);
			
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
