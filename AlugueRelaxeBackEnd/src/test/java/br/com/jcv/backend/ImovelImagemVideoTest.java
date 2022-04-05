package br.com.jcv.backend;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import junit.framework.TestCase;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelImagemVideoService;
import br.com.jcv.backend.utility.StringUtil;

public class ImovelImagemVideoTest extends TestCase {

	public void testAdicionarVideoImovel() {
		try {
			
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			
			ImovelImagemVideoDTO iivdto = getDTO();
			
			ImovelImagemVideoService<ImovelImagemVideoDTO> iivs = new ImovelImagemVideoServiceImpl();
			DTOPadrao dto = iivs.adicionarVideoImovel(iivdto);
			
			assertEquals(codigoEsperado, dto.msgcode);
			
			
		} catch (AlugueRelaxeException are) {
			fail(are.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	private ImovelImagemVideoDTO getDTO() {
		ImovelImagemVideoDTO dto = new ImovelImagemVideoDTO();
		
		dto.nome = StringUtil.gerarCodigo(10);
		dto.imovel = new ImovelDTO();
		dto.imovel.id = 2;
		
		BaseFactorySecurity bfs = BaseFactorySecurity.getInstance(BaseFactorySecurity.SHA1);
		try {
			dto.hash = bfs.criptografar(dto.nome);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dto;
	}
}
