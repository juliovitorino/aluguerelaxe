/**
 * 
 */
package br.com.jcv.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import br.com.jcv.backend.assinantes.AssinantesDTO;
import br.com.jcv.backend.assinantes.AssinantesServiceImpl;
import br.com.jcv.backend.cliente.ClienteBusinessImpl;
import br.com.jcv.backend.cliente.ClienteContraSenhaDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.AssinantesService;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
 * @author Julio
 *
 */
public class AssinantesTest extends TestCase {
	

	private static int MAXIMO_ITENS = 10;
	private static String[] seed = new String[MAXIMO_ITENS];
	private int contador = 0;

	public void testInscricaoEmailFormatoInvalido() {
		String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
		try {
			
			AssinantesDTO dto = this.getDTO();
			
			// Forca tamanho 0 do nome do participante
			dto.email ="EMAIL@.br";
			
			// Obtem a campanha ativa
			dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			dto = is.inscricao(dto);

			if (dto != null){
				fail("Resultado Inesperado!");
			}
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}

	public void testInscricaoPartipanteEstouraTamanhoEmail() {
		String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
		try {
			
			AssinantesDTO dto = this.getDTO();
			
			// Forca tamanho 0 do nome do participante
			dto.email = StringUtil.lPad("EMAIL@coisa.com.br", "0", 101);
			
			// Obtem a campanha ativa
			dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			dto = is.inscricao(dto);

			if (dto != null){
				fail("Resultado Inesperado!");
			}
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}

	public void testInscricaoPartipanteSemEmail() {
		String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
		try {
			
			AssinantesDTO dto = this.getDTO();
			
			// Forca tamanho 0 do nome do participante
			dto.email = "";
			
			// Obtem a campanha ativa
			dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			dto = is.inscricao(dto);

			if (dto != null){
				fail("Resultado Inesperado!");
			}
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}

	public void testInscricaoPartipanteComEmailNulo() {
		String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
		try {
			
			AssinantesDTO dto = this.getDTO();
			
			// Forca a nulidade do nome do participante
			dto.email = null;
			
			// Obtem a campanha ativa
			dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			dto = is.inscricao(dto);

			if (dto != null){
				fail("Resultado Inesperado!");
			}
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}

	public void testInscricaoPartipanteEstouraTamanhoNome() {
		String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
		try {
			
			AssinantesDTO dto = this.getDTO();
			
			// Forca tamanho 0 do nome do participante
			dto.nome = StringUtil.lPad("NOME", "0", 500);
			
			// Obtem a campanha ativa
			dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			dto = is.inscricao(dto);

			if (dto != null){
				fail("Resultado Inesperado!");
			}
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}

	public void testInscricaoPartipanteSemNome() {
		String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
		try {
			
			AssinantesDTO dto = this.getDTO();
			
			// Forca tamanho 0 do nome do participante
			dto.nome = "";
			
			// Obtem a campanha ativa
			dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			dto = is.inscricao(dto);

			if (dto != null){
				fail("Resultado Inesperado!");
			}
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}

	public void testInscricaoPartipanteComNomeNulo() {
		String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
		try {
			
			AssinantesDTO dto = this.getDTO();
			
			// Forca a nulidade do nome do participante
			dto.nome = null;
			
			// Obtem a campanha ativa
			dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			dto = is.inscricao(dto);

			if (dto != null){
				fail("Resultado Inesperado!");
			}
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}

	public void testInscricaoPartipanteNulo() {
		String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
		try {
			
			AssinantesDTO dto = new AssinantesDTO();
			
			// Obtem a campanha ativa
			dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			dto = is.inscricao(dto);

			if (dto != null){
				fail("Resultado Inesperado!");
			}
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}


	public void testHashInvalido() {
		String codigoEsperado = MSGCODE.HASH_PROMOCAO_INVALIDO;
		try {
			
			String hashInvalido = "FAD466ADF868invalido77B75912DD15";
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			DTOPadrao dto = is.ativarInscricao(hashInvalido);
			if (dto != null){
				fail("Resultado Inesperado!");
			}
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}


	public void testHashValidadoParticipante() {
		String codigoEsperado = MSGCODE.HASH_VALIDADO_PARTICIPANDO_PROMOCAO;
		try {
			
			AssinantesDTO dto = this.getDTO();
			
			// Obtem a campanha ativa
			dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			dto = is.inscricao(dto);
			DTOPadrao dtopadrao = is.ativarInscricao(dto.hash);

			// Forca a tentantiva de atualização do hash
			dtopadrao = is.ativarInscricao(dto.hash);
			if (dtopadrao != null){
				fail("Resultado Inesperado!");
			}
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}

	public void testInscricaoDuplicada() {
		String codigoEsperado = MSGCODE.INSCRICAO_JA_REALIZADA;
		try {
			
			AssinantesDTO dto = this.getDTO();
			
			// Obtem a campanha ativa
			dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			dto = is.inscricao(dto);
			dto = is.inscricao(dto);
			if (dto == null){
				fail("Resultado AssinantesDTO nulo!");
			}
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}

	/**
	 * Test method for {@link br.com.jcv.backend.assinantes.AssinantesServiceImpl#inscricao(br.com.jcv.backend.assinantes.AssinantesDTO, java.util.List)}.
	 */
	public void testInscricaoNovoAssinante() {
		String codigoEsperado = MSGCODE.INSCRICAO_REALIZADA_SUCESSO;
		try {
			
			AssinantesDTO dto = this.getDTO();
			
			// Obtem a campanha ativa
			dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			dto = is.inscricao(dto);
			if (dto == null){
				fail("Resultado AssinantesDTO nulo!");
			}
			assertEquals(codigoEsperado, dto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	/**
	 * Test method for {@link br.com.jcv.backend.assinantes.AssinantesServiceImpl#inscricao(br.com.jcv.backend.assinantes.AssinantesDTO, java.util.List)}.
	 */
	public void testInscricaoNovoAssinanteJulio() {
		String codigoEsperado = MSGCODE.INSCRICAO_REALIZADA_SUCESSO;
		try {
			
			AssinantesDTO dto = new AssinantesDTO();
			dto.email = "julio.vitorino@ig.com.br";
			dto.nome = "Julio Cesar Vitorino";
			
			// Obtem a campanha ativa
			dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			dto = is.inscricao(dto);
			if (dto == null){
				fail("Resultado AssinantesDTO nulo!");
			}
			assertEquals(codigoEsperado, dto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	/**
	 * Test method for {@link br.com.jcv.backend.assinantes.AssinantesServiceImpl#inscricao(AssinantesDTO dto, List<AssinantesDTO> amigos)
	 */
	public void testInscricaoAmigoIndicaAmigo() {
		String codigoEsperado = MSGCODE.INSCRICAO_REALIZADA_SUCESSO;
		try {
			// Obtem a campanha ativa
			VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesDTO principal = this.getDTO();
			
			AssinantesDTO amigo1 = this.getDTO();
			AssinantesDTO amigo2 = this.getDTO();
			
			List<AssinantesDTO> amigos = new ArrayList<AssinantesDTO>();
			amigos.add(amigo1);
			amigos.add(amigo2);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			DTOPadrao dto = is.inscricao(principal, amigos);
			if (dto == null){
				fail("Resultado AssinantesDTO nulo!");
			}
			assertEquals(codigoEsperado, dto.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	public void testInscricaoAmigoViraParticipante() {
		String codigoEsperado = MSGCODE.INSCRICAO_REALIZADA_SUCESSO;
		try {
			// Obtem a campanha ativa
			VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesDTO principal = this.getDTO();
			
			AssinantesDTO amigo1 = this.getDTO();
			AssinantesDTO amigo2 = this.getDTO();
			
			List<AssinantesDTO> amigos = new ArrayList<AssinantesDTO>();
			amigos.add(amigo1);
			amigos.add(amigo2);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			DTOPadrao dto = is.inscricao(principal, amigos);
			if (dto == null){
				fail("Resultado AssinantesDTO nulo!");
			}

			// Amigo1 vira participante
			AssinantesDTO amigo1doamigo1 = this.getDTO();
			AssinantesDTO amigo2doamigo1 = this.getDTO();
			AssinantesDTO amigo3doamigo1 = this.getDTO();
			List<AssinantesDTO> amigosdoamigo1 = new ArrayList<AssinantesDTO>();
			amigosdoamigo1.add(amigo1doamigo1);
			amigosdoamigo1.add(amigo2doamigo1);
			amigosdoamigo1.add(amigo3doamigo1);
			amigo1.idParent = 0;

			AssinantesService<AssinantesDTO> isamigo1 = new AssinantesServiceImpl();
			DTOPadrao dtoamigo1 = isamigo1.inscricao(amigo1, amigosdoamigo1);
			if (dtoamigo1 == null){
				fail("Resultado AssinantesDTO nulo!");
			}
			
			assertEquals(codigoEsperado, dtoamigo1.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}

	
	/**
	 * Test method for {@link br.com.jcv.backend.assinantes.AssinantesServiceImpl#inscricao(AssinantesDTO dto, List<AssinantesDTO> amigos)
	 */
	public void testAtivarInscricao() {
		String codigoEsperado = MSGCODE.INSCRICAO_ATIVADA_SUCESSO;
		try {
			// Obtem a campanha ativa
			VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesDTO principal = this.getDTO();
			AssinantesService<AssinantesDTO> is1 = new AssinantesServiceImpl();
			AssinantesDTO dto = is1.inscricao(principal);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			DTOPadrao retorno = is.ativarInscricao(dto.hash);
			if (retorno == null){
				fail("Resultado AssinantesDTO nulo!");
			}
			assertEquals(codigoEsperado, retorno.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	public void testFulanoIndicaJulioGmailIgual() {
		String codigoEsperado = MSGCODE.AMIGO_INDICADO_POR_OUTRA_PESSOA;
		try {
			// Obtem a campanha ativa
			VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
			
			AssinantesDTO principal = this.getDTO();
			
			//amigos do julio teste
			AssinantesDTO amigo1 = new AssinantesDTO();
			amigo1.nome = "julioGmail";
			amigo1.email = "julio.vitorino@gmail.com";

			//amigos do julio teste
			AssinantesDTO amigo2 = new AssinantesDTO();
			amigo2.nome = "jaspper";
			amigo2.email = "jasper.mattedi@gmail.com";
			
			List<AssinantesDTO> amigos = new ArrayList<AssinantesDTO>();
			amigos.add(amigo1);
			amigos.add(amigo2);
			
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
			DTOPadrao dto = is.inscricao(principal, amigos);
			if (dto == null){
				fail("Resultado AssinantesDTO nulo!");
			}
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
		}
	}
	
	//-----------------------------------------------------------------
	// metodos de apoio
	//-----------------------------------------------------------------
	private AssinantesDTO getDTO() {
		this.iniciaSeed();
		if (contador == MAXIMO_ITENS){
			contador = 0;
		}
		
		AssinantesDTO dto = new AssinantesDTO();
		String i = String.valueOf(System.currentTimeMillis());
		dto.nome = seed[contador] + "-" + i;
		dto.email = seed[contador] + i + "@ig.com.br";
		
		contador ++;
		return dto;
	}
		
	private void iniciaSeed() {
		seed[0] = "seke";
		seed[1] = "luther";
		seed[2] = "poke";
		seed[3] = "mon";
		seed[4] = "bike";
		seed[5] = "clone";
		seed[6] = "mike";
		seed[7] = "vaqmp";
		seed[8] = "wite";
		seed[9] = "insert";
	}
	
}
