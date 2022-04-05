package br.com.jcv.backend;

import java.util.Random;

import junit.framework.TestCase;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.filasms.FilaSMSDTO;
import br.com.jcv.backend.filasms.FilaSMSServiceImpl;
import br.com.jcv.backend.interfaces.services.FilaSMSService;

public class FilaSMSTest extends TestCase {

	public void testGravarRegistro() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			FilaSMSDTO dto = this.getDTO();
			FilaSMSService cs = new FilaSMSServiceImpl();
			dto = cs.gravarRegistro(dto);
			
			assertEquals(codigoEsperado, dto.msgcode);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());

		}
	}


	public void testGravar500Registros() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		int i = 0;
		try {
			FilaSMSService cs = new FilaSMSServiceImpl();
			for (i = 0; i < 500; i++){
				FilaSMSDTO dto = this.getDTO();
				dto = cs.gravarRegistro(dto);
			}
			
			assertEquals(i, 500);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());

		}
	}

	//-----------------------------------------------------------------
	// metodos de apoio
	//-----------------------------------------------------------------
	private FilaSMSDTO getDTO() {
		Random rndgen = new Random();
		FilaSMSDTO dto = new FilaSMSDTO();
		long i = System.currentTimeMillis();
		dto.celular = "2498340040";
		dto.msg = "msgenviar :" + i;
		return dto;

	}	
}
