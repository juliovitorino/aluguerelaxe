package br.com.jcv.backend;

import java.util.List;

import junit.framework.TestCase;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.PainelControleService;
import br.com.jcv.backend.painelcontrole.EstatisticaOrigemVisitaDTO;
import br.com.jcv.backend.painelcontrole.EstatisticaVisitasDTO;
import br.com.jcv.backend.painelcontrole.EstatisticaVisitasDiarioDTO;
import br.com.jcv.backend.painelcontrole.IndicadorPerformanceEntradaImovelDTO;
import br.com.jcv.backend.painelcontrole.IndicadorPerformanceVisitasDTO;
import br.com.jcv.backend.painelcontrole.ModeracaoDTO;
import br.com.jcv.backend.painelcontrole.PainelControleServiceImpl;
import br.com.jcv.backend.painelcontrole.PanoramaPublicidadeDTO;

public class PainelControleTest extends TestCase {
	
	public void testGetEstatisticaVisitantesDiario() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			PainelControleService psc = new PainelControleServiceImpl();
			EstatisticaVisitasDiarioDTO dto = psc.getEstatisticaVisitantesDiario(2011, 7);
			assertEquals(codigoEsperado, dto.msgcode);
			
		} catch (AlugueRelaxeException e) {
			fail(e.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	public void testGetEstatisticaOrigemVisita() {
		try {
			PainelControleService psc = new PainelControleServiceImpl();
			List<EstatisticaOrigemVisitaDTO> dto = psc.getEstatisticaOrigemVisita(2011);
			assertNotNull(dto);
			
		} catch (AlugueRelaxeException e) {
			fail(e.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	public void testGetEstatisticaVisitantes() {
		try {
			PainelControleService psc = new PainelControleServiceImpl();
			List<EstatisticaVisitasDTO> dto = psc.getEstatisticaVisitantes();
			assertNotNull(dto);
			
		} catch (AlugueRelaxeException e) {
			fail(e.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	public void testgetIndPerfEntradaImovel() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		
		try {
			PainelControleService psc = new PainelControleServiceImpl();
			IndicadorPerformanceEntradaImovelDTO dto = psc.getIndPerfEntradaImovel(2011, 04, 15);
			assertEquals(codigoEsperado, dto.msgcode);
			
		} catch (AlugueRelaxeException e) {
			fail(e.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testgetIndPerfVisitasImovel() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		
		try {
			PainelControleService psc = new PainelControleServiceImpl();
			IndicadorPerformanceVisitasDTO dto = psc.getIndPerfVisitas(2011, 04, 15);
			assertEquals(codigoEsperado, dto.msgcode);
			
		} catch (AlugueRelaxeException e) {
			fail(e.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	public void testGetPendenciaModeracao() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		
		try {
			PainelControleService psc = new PainelControleServiceImpl();
			ModeracaoDTO dto = psc.getPendenciaModeracao();
			assertEquals(codigoEsperado, dto.msgcode);
			
		} catch (AlugueRelaxeException e) {
			fail(e.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
