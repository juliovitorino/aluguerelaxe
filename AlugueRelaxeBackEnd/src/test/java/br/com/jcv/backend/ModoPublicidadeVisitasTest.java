package br.com.jcv.backend;

import junit.framework.TestCase;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ModoPublicidadeVisitasService;
import br.com.jcv.backend.modopublicidadevisitas.ModoPublicidadeVisitasDTO;
import br.com.jcv.backend.modopublicidadevisitas.ModoPublicidadeVisitasServiceImpl;

public class ModoPublicidadeVisitasTest extends TestCase {

	public void testIncrementarModoPublicidadeVisita() {
		final long GOOGLE_MODO_PUBLICIDADE = 1;  
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			ModoPublicidadeVisitasService<ModoPublicidadeVisitasDTO> mpvs = new ModoPublicidadeVisitasServiceImpl();
			DTOPadrao dtopadrao = mpvs.incrementarModoPublicidadeVisita(GOOGLE_MODO_PUBLICIDADE);
			assertEquals(codigoEsperado, dtopadrao.msgcode);
		} catch (AlugueRelaxeException e) {
			fail(e.getMensagem());
		}
	}

	public void testGravarRegistro() {
		fail("Not yet implemented");
	}

	public void testExcluirRegistro() {
		fail("Not yet implemented");
	}

	public void testAtualizarRegistro() {
		fail("Not yet implemented");
	}

	public void testPesquisarRegistro() {
		fail("Not yet implemented");
	}

	public void testListarRegistros() {
		fail("Not yet implemented");
	}

}
