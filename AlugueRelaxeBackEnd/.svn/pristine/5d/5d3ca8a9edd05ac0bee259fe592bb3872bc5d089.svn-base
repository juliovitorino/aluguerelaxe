package br.com.jcv.backend;

import junit.framework.TestCase;
import br.com.jcv.backend.chat.ChatDTO;
import br.com.jcv.backend.chat.ChatServiceImpl;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ChatService;

public class ChatTestCase extends TestCase {

	public void testGetChatAtivo() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			ChatService<ChatDTO> cs = new ChatServiceImpl();
			ChatDTO dto = cs.getChatAtivo("PP");
			assertEquals(codigoEsperado, dto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testGetChatPrivadoTodos() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			ChatService<ChatDTO> cs = new ChatServiceImpl();
			ChatDTO dto = cs.getChatPrivado("DD", -1);
			assertEquals(codigoEsperado, dto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testGetChatPrivadoAnunciante() {
		try {
			String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			ChatService<ChatDTO> cs = new ChatServiceImpl();
			ChatDTO dto = cs.getChatPrivado("DD", 2);
			assertEquals(codigoEsperado, dto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

}
