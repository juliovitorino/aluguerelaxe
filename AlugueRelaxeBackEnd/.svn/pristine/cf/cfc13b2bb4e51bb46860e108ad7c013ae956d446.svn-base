package br.com.jcv.backend;

import junit.framework.TestCase;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.plano.ImovelPlanoRelacaoDTO;
import br.com.jcv.backend.imovel.plano.ImovelPlanoServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelPlanoService;

public class ImovelPlanoTest extends TestCase {

	public void testMigrarPlanoImovel() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			
			ImovelPlanoService<ImovelPlanoRelacaoDTO> ipfs = new ImovelPlanoServiceImpl();
			ImovelPlanoRelacaoDTO dto = ipfs.migrarPlanoImovel(2, 201);

			assertEquals(codigoEsperado, dto.msgcode);

		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testMigrarPlanoImovelInexistente() {
		try {
			int CODIGO_INVALIDO = -900;
			
			ImovelPlanoService<ImovelPlanoRelacaoDTO> ipfs = new ImovelPlanoServiceImpl();
			ImovelPlanoRelacaoDTO dto = ipfs.migrarPlanoImovel(2, CODIGO_INVALIDO);

			fail("Erro");

		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			assertEquals(MSGCODE.PLANO_MIGRACAO_INVALIDO, e.getCodigoErro());
		}
	}

	public void testMigrarPlanoImovelExistente() {
		try {
			int CODIGO_EXISTENTE = 200;
			
			ImovelPlanoService<ImovelPlanoRelacaoDTO> ipfs = new ImovelPlanoServiceImpl();
			ImovelPlanoRelacaoDTO dto = ipfs.migrarPlanoImovel(2, CODIGO_EXISTENTE);

			fail("Erro");

		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			assertEquals(MSGCODE.PLANO_MIGRACAO_INVALIDO, e.getCodigoErro());
		}
	}

}
