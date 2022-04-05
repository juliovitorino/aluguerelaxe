package br.com.jcv.backend;

import java.util.Date;

import junit.framework.TestCase;
import br.com.jcv.backend.cliente.ClienteBusinessImpl;
import br.com.jcv.backend.cliente.ClienteContraSenhaDTO;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.ClienteServiceImpl;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ClienteService;
import br.com.jcv.backend.interfaces.services.PortalService;
import br.com.jcv.backend.portal.NovoCodigoAcessoDTO;
import br.com.jcv.backend.portal.PortalServiceImpl;
import br.com.jcv.backend.portal.faleconosco.FaleConoscoDTO;
import br.com.jcv.backend.portal.login.LoginDTO;

public class PortalTest extends TestCase {

	public void testLoginInexistente() {
		String codigoEsperado = MSGCODE.USUARIO_SENHA_INVALIDA;
		try {
			
			LoginDTO dto = new LoginDTO();
			dto.email = "joaquim.santos@mobil.com.br";
			dto.senha = "12345";
			PortalService ps = new PortalServiceImpl();
			ps.login(dto);
			
			fail("Problemas este usuÃ¡rio nao existe, verifique.");
			
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
			
		}
	}

	public void testLoginSemEmail() {
		String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
		try {
			
			LoginDTO dto = new LoginDTO();
			dto.email = null;
			dto.senha = "12345";
			PortalService ps = new PortalServiceImpl();
			ps.login(dto);
			
			fail("Problemas com este usuÃ¡rio, verifique.");
			
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
			
		}
	}

	public void testLoginSemSenha() {
		String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
		try {
			
			LoginDTO dto = new LoginDTO();
			dto.email = "joaquim.santos@mobil.com.br";
			dto.senha = null;
			PortalService ps = new PortalServiceImpl();
			ps.login(dto);
			
			fail("Problemas com este usuÃ¡rio, verifique.");
			
			
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
			
		}
	}

	public void testLoginSenhaIncorreta() {
		String codigoEsperado = MSGCODE.USUARIO_SENHA_INVALIDA;
		try {
			
			ClienteContraSenhaDTO ccsdto = getClienteContraSenhaDTO();
			
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO clientedto = cs.criarNovaConta(ccsdto);
			cs.ativarNovaConta(clientedto.codigoHashAtivacao);
			
			LoginDTO dto = new LoginDTO();
			dto.email = clientedto.email;
			dto.senha = "SEILA";
			PortalService ps = new PortalServiceImpl();
			DTOPadrao retorno = ps.login(dto);

			fail("Problemas - conseguiu se logar, verifique.");
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			assertEquals(codigoEsperado, e.getCodigoErro());
			
		}
	}
	
	public void testLoginEmailInvalido() {
		String codigoEsperado = MSGCODE.USUARIO_SENHA_INVALIDA;
		try {
			
			ClienteContraSenhaDTO ccsdto = getClienteContraSenhaDTO();
			
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO clientedto = cs.criarNovaConta(ccsdto);
			cs.ativarNovaConta(clientedto.codigoHashAtivacao);
			
			LoginDTO dto = new LoginDTO();
			dto.email = "email@invalido.com";;
			dto.senha = "rcanet";
			PortalService ps = new PortalServiceImpl();
			DTOPadrao retorno = ps.login(dto);

			fail("Problemas - conseguiu se logar, verifique.");
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			assertEquals(codigoEsperado, e.getCodigoErro());
			
		}
	}
	

	public void testLoginValido() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			
			ClienteContraSenhaDTO ccsdto = getClienteContraSenhaDTO();
			
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO clientedto = cs.criarNovaConta(ccsdto);
			cs.ativarNovaConta(clientedto.codigoHashAtivacao);
			
			LoginDTO dto = new LoginDTO();
			dto.email = clientedto.email;
			dto.senha = "rcanet";
			PortalService ps = new PortalServiceImpl();
			DTOPadrao retorno = ps.login(dto);
			
			assertEquals(codigoEsperado, retorno.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail("Problemas com o login, verifique.");
			
		}
	}
	
	public void testEnvioFaleConosco() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			
			FaleConoscoDTO fcdto = new FaleConoscoDTO();
			fcdto.assunto = "Sucesso!";
			fcdto.email = "julio.vitorino@ig.com.br";
			fcdto.mensagem = "Transportai um punhado de terra todos os dias e terás uma montanha.";
			fcdto.nome = "Júlio Cesar Vitorino";
			fcdto.topico = "Sugestão";
			
			PortalService ps = new PortalServiceImpl();
			DTOPadrao retorno =  ps.executeFaleConosco(fcdto);
			assertEquals(codigoEsperado, retorno.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail("Problemas com o login, verifique.");
			
		}
	}
	public void testSolicitarNovoCodigoAcessoComNulo() {
		String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
		try {
			PortalService ps = new PortalServiceImpl();
			DTOPadrao retorno =  ps.solicitarNovoCodigoAcesso(null);
			fail("Problemas... Aceitando nulo, verifique.");
		} catch (AlugueRelaxeException e) {
			assertEquals(codigoEsperado, e.getCodigoErro());
			
		}
	}
	public void testSolicitarNovoCodigoAcessoEmailValido() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			NovoCodigoAcessoDTO ncadto = new NovoCodigoAcessoDTO();
			ncadto.email = "julio.vitorino@gmail.com";
			
			PortalService ps = new PortalServiceImpl();
			DTOPadrao retorno =  ps.solicitarNovoCodigoAcesso(ncadto);
			assertEquals(codigoEsperado, retorno.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail("Problemas com o Novo Codigo Acesso, verifique.");
			
		}
	}
	public void testEnvioFaleConoscoEmailIncorreto() {
		try {
			
			FaleConoscoDTO fcdto = new FaleConoscoDTO();
			fcdto.assunto = "Sucesso!";
			fcdto.email = "3452345";
			fcdto.mensagem = "Transportai um punhado de terra todos os dias e terás uma montanha.";
			fcdto.nome = "Júlio Cesar Vitorino";
			fcdto.topico = "Sugestão";
			
			PortalService ps = new PortalServiceImpl();
			DTOPadrao retorno =  ps.executeFaleConosco(fcdto);
			fail("Problemas com o login, verifique.");
		} catch (AlugueRelaxeException e) {
			String codigoEsperado = MSGCODE.ERROS_VALIDACAO;
			assertEquals(codigoEsperado, e.getCodigoErro());
			
		}
	}
	
	//-------------------------------------------------------------------
	// metodos de apoio
	//-------------------------------------------------------------------
	
	private ClienteContraSenhaDTO getClienteContraSenhaDTO() {
		ClienteContraSenhaDTO ccsdto = new ClienteContraSenhaDTO();

		long id = System.currentTimeMillis();
		ccsdto.nome = "Harper " + id;
		ccsdto.cpf = String.valueOf(id).substring(11);
		ccsdto.dataNascimento = new Date();
		ccsdto.email = String.valueOf(id) + "@meuteste.com.br";
		ccsdto.senha = "rcanet";
		ccsdto.contraSenha = "rcanet";
		ccsdto.cpf = "12345678901";
		ccsdto.urlwww = "www." + id ;
		ccsdto.indicadorAutorizaNotificacao = Constantes.SIM;
		ccsdto.msn = String.valueOf(id) + ".msn";
		ccsdto.skype = String.valueOf(id) + ".skype";
		ccsdto.googleTalk = String.valueOf(id) + ".gtalk";
		ccsdto.indicadorTipoAnunciante = ClienteBusinessImpl.TIPO_ANUNCIANTE_PROPRIETARIO;
		ccsdto.endereco = new EnderecoDTO();
		ccsdto.endereco.nome = "Rua " + String.valueOf(id);
		ccsdto.endereco.numero = "5469";
		ccsdto.endereco.bairro = "Jabiraca " + String.valueOf(id);
		ccsdto.endereco.cep = "12345678";
		ccsdto.endereco.codigoUfCidadeItem = 1;
		ccsdto.endereco.complemento = "bII - " + String.valueOf(id);
		
		return ccsdto;
	}
}
