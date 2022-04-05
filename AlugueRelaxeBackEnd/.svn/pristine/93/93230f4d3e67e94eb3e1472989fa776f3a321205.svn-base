package br.com.jcv.backend;

import java.util.List;

import junit.framework.TestCase;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.LocalService;
import br.com.jcv.backend.local.ClassificacaoDTO;
import br.com.jcv.backend.local.LocalDTO;
import br.com.jcv.backend.local.LocalItemDTO;
import br.com.jcv.backend.local.LocalServicesImpl;

public class LocalTest extends TestCase {
	
	public void testListarLocal() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			LocalService<?> is = new LocalServicesImpl();
			List<LocalDTO> lst = is.listarLocal();
			
			assertEquals(codigoEsperado, MSGCODE.COMANDO_EXECUTADO_SUCESSO);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testListarClassificacaoLocal() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;

			LocalService<?> is = new LocalServicesImpl();
			List<ClassificacaoDTO> lst = is.listarClassificacaoLocal();
			
			assertEquals(codigoEsperado, MSGCODE.COMANDO_EXECUTADO_SUCESSO);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testListarLocaisUFCidadeClassificacao() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			long GUAIRA_PR = 3358;
			long COMIDA = 3;
			
			long[] idClassificacao = {COMIDA};

			LocalService<LocalItemDTO> is = new LocalServicesImpl();
			List<LocalItemDTO> lst = is.listarLocaisUFCidadeClassificacao(GUAIRA_PR, idClassificacao);
			
			assertEquals(codigoEsperado, MSGCODE.COMANDO_EXECUTADO_SUCESSO);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}


	public void testListarLocaisUFCidade() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			long GUAIRA_PR = 3358;
			long[] idLocais = {1,3};
			//long[] idLocais = {1,3,5};

			LocalService<LocalItemDTO> is = new LocalServicesImpl();
			List<LocalItemDTO> lst = is.listarLocaisUFCidade(GUAIRA_PR, idLocais);
			
			assertEquals(codigoEsperado, MSGCODE.COMANDO_EXECUTADO_SUCESSO);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

}
