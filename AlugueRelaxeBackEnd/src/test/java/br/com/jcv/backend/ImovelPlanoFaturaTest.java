package br.com.jcv.backend;

import java.util.List;

import junit.framework.TestCase;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelPlanoFaturaService;

public class ImovelPlanoFaturaTest extends TestCase {

	public void testlistarPlanosGratuitosVencidos() {
		try {
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
			List<Long> lst = ipfs.listarPlanosGratuitosVencidos();

			assertNotNull(lst);

		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testlistarPlanosPagosVencidos() {
		try {
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
			List<Long> lst = ipfs.listarPlanosPagosVencidos();

			assertNotNull(lst);

		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testlistarPlanosPagosAVencer() {
		try {
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
			List<Long> lst = ipfs.listarPlanosPagosAVencer();

			assertNotNull(lst);

		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testlistarPlanosGratuitosAVencer() {
		try {
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
			List<Long> lst = ipfs.listarPlanosGratuitosAVencer();

			assertNotNull(lst);

		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testPesquisarUltimaFatura() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
			ImovelPlanoFaturaDTO faturadto = ipfs.pesquisarUltimaFatura(2, Constantes.TIPO_PLANO_NORMAL);

			assertEquals(codigoEsperado, faturadto.msgcode);

		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testRenovarPlano() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
			ImovelPlanoFaturaDTO faturadto = ipfs.renovarPlano(2);

			assertEquals(codigoEsperado, faturadto.msgcode);

		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

}
