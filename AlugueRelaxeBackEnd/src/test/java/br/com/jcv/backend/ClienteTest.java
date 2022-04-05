package br.com.jcv.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;
import br.com.jcv.backend.cliente.ClienteContraSenhaDTO;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.ClienteServiceImpl;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.ValidadorFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.interfaces.services.ClienteService;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeDTO;

public class ClienteTest extends TestCase {

	public void testAtualizarEnqueteModoPublicidade() {
		String codigoEsperado = MSGCODE.ENQUETE_MODO_PUBLICIDADE_ATUALIZADO_COM_SUCESSO;
		try {
			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			modificarDadosDTO(dtor);
			DTOPadrao dtopadrao = cs.atualizarEnqueteModoPublicidade(dtor);
			
			assertEquals(codigoEsperado, dtopadrao.msgcode);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());

		}
	}
	
	public void testListarClientesPendentes() {
		try {
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			List<Long> lst = cs.listarRegistros("P");
			assertNotNull(lst);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	public void testListarContatoAnunciante() {
		try {
			String codigoEsperado = MSGCODE.NOVA_CONTA_CLIENTE_CRIADA_COM_SUCESSO;
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			List<ContatoClienteDTO> lst = cs.listarContatosAnunciante(10016);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	
	public void testAdicionarCargaClientes() {
		try {
			String codigoEsperado = MSGCODE.NOVA_CONTA_CLIENTE_CRIADA_COM_SUCESSO;
			ClienteDTO dtor = null;
			for (int i = 0; i < 5; i++){
				ClienteContraSenhaDTO dto = this.getDTO();
				ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
				dtor = cs.criarNovaConta(dto);
				cs.ativarNovaConta(dtor.codigoHashAtivacao);
			}
			
			assertEquals(codigoEsperado, dtor.msgcode);
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
			fail(e.getMensagem());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testValidarClienteDTOCamposNulo() {
		try {
			ClienteDTO dto = new ClienteDTO();
			List<String> lstErros = ValidadorFactory.getInstanceCliente().execute(dto);
			if (lstErros != null){
				for (String descricaoErro : lstErros) {
					System.out.println(descricaoErro);
				}
			}
			assertNotNull(lstErros);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testValidarClienteDTONulo() {
		try {
			List<String> lstErros = ValidadorFactory.getInstanceCliente().execute(null);
			if (lstErros != null){
				for (String descricaoErro : lstErros) {
					System.out.println(descricaoErro);
				}
			}
			assertNotNull(lstErros);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testPesquisarRegistro() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			
			ClienteDTO dtopesquisa = new ClienteDTO();
			dtopesquisa = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			ClienteDTO dtoclientecompleto = new ClienteDTO();
			dtoclientecompleto.id = dtopesquisa.id;
			dtoclientecompleto = cs.pesquisarRegistro(dtoclientecompleto);
			
			assertEquals(codigoEsperado, dtoclientecompleto.msgcode);

		} catch(AlugueRelaxeException e) {
			e.printStackTrace();	
			fail("Nao conseguiu pesquisar registro");
		}

	}

	public void testCriarNovaConta() {
		String codigoEsperado = MSGCODE.NOVA_CONTA_CLIENTE_CRIADA_COM_SUCESSO;
		try {
			ClienteContraSenhaDTO dto = new ClienteContraSenhaDTO();
			String i = String.valueOf(System.currentTimeMillis());
			dto.nome = "Julio Vitorino - " + i;
			dto.cpf = i.substring(0,11);
			dto.dataNascimento = new Date();
			dto.senha = "12356";
			dto.contraSenha = "12356";
			dto.email = i + "@ig.com.br";
			dto.urlwww = "www." + i + "com.br";
			dto.indicadorAutorizaNotificacao = Constantes.SIM;
			dto.indicadorTipoAnunciante = "P";
			dto.endereco = new EnderecoDTO();
			dto.endereco.codigoUfCidadeItem = 1;
			dto.endereco.nome = "Rua " + i;
			dto.endereco.numero = "123";
			dto.endereco.bairro = "B:" + i;
			dto.endereco.complemento = "fundos";
			dto.endereco.cep = "27189098";
			dto.endereco.cidade = "Sao Paulo";
			
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			
			assertEquals(codigoEsperado, dtor.msgcode);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());

		}

	}
	
	public void testAtivarNovaConta() {
		try {
			ClienteService cs = new ClienteServiceImpl();
			cs.ativarNovaConta("HYREVFDGTRGHYRTH");
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());

		}

	}
	
	public void testAtualizarFichaCadastro() {
		String codigoEsperado = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			ClienteContraSenhaDTO dto = this.getDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			modificarDadosDTO(dtor);
			DTOPadrao dtopadrao = cs.atualizarFichaCadastral(dtor);
			
			assertEquals(codigoEsperado, dtopadrao.msgcode);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());

		}

	}
	
	//-----------------------------------------------------------------
	// metodos de apoio
	//-----------------------------------------------------------------
	private ClienteContraSenhaDTO getDTO() {
		Random rndgen = new Random();
		ClienteContraSenhaDTO dto = new ClienteContraSenhaDTO();
		String i = String.valueOf(System.currentTimeMillis());
		dto.nome = "Julio Vitorino - " + i;
		dto.cpf = i.substring(0,11);
		dto.dataNascimento = new Date();
		dto.senha = "rcanet";
		dto.contraSenha = "rcanet";
		dto.email = i + rndgen.nextInt(10000)+"@ig.com.br";
		dto.urlwww = "www." + i + "com.br";
		dto.indicadorAutorizaNotificacao = Constantes.SIM;
		dto.indicadorTipoAnunciante = "P";
		dto.endereco = new EnderecoDTO();
		dto.endereco.codigoUfCidadeItem = 1;
		dto.endereco.nome = "Rua " + i;
		dto.endereco.complemento = "123";
		dto.endereco.numero = "123";
		dto.endereco.bairro = "B:" + i;
		dto.endereco.cep = "27189098";
		dto.endereco.complemento = "fundos";
		dto.endereco.cidade = "Sao Paulo";
		dto.modoPublicidade = new ModoPublicidadeDTO();
		dto.modoPublicidade.id = rndgen.nextInt(30);
		return dto;

	}
	private void modificarDadosDTO(ClienteDTO dto) {
		String i = String.valueOf(System.currentTimeMillis());
		dto.nome = "modificado - " + i;
		dto.cpf = i.substring(0,11);
		dto.dataNascimento = new Date();
		dto.email = i+"@novo.com.br";
		dto.urlwww = "www." + i + "com.br";
		dto.endereco = new EnderecoDTO();
		dto.endereco.codigoUfCidadeItem = 5;
		dto.endereco.nome = "Quadra " + i;
		dto.endereco.numero = "capibaribi";
		dto.endereco.bairro = "B:" + i;
		dto.endereco.complemento = "fundos";
		dto.endereco.cep = "34567234";
		dto.telefones = new ArrayList<TelefoneDTO>();
		TelefoneDTO teldto = new TelefoneDTO();
		teldto.nomeContato = dto.nome.substring(0,20);
		teldto.ddd = "24";
		teldto.telefone = "98340040";
		teldto.indPermiteExibir = "N";
		teldto.indTipoTelefone = "C";
		dto.telefones.add(teldto);
		dto.modoPublicidade = new ModoPublicidadeDTO();
		dto.modoPublicidade.id = 10;
	}
	
	private ClienteContraSenhaDTO getDTOEstourado() {
		ClienteContraSenhaDTO dto = new ClienteContraSenhaDTO();
		String i = String.valueOf(System.currentTimeMillis());
		dto.nome = "Julio Vitorino - qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq" + i;
		dto.cpf = i.substring(0,11) + i;
		dto.dataNascimento = new Date();
		dto.senha = "rcanet";
		dto.contraSenha = "rcanet";
		dto.email = i+"@ig.com.br" + dto.nome + dto.nome;
		dto.urlwww = "www." + i + "com.br" + dto.nome + dto.nome + dto.nome + dto.nome;
		dto.msn = dto.urlwww + dto.urlwww + dto.urlwww;
		dto.googleTalk = dto.urlwww + dto.urlwww + dto.urlwww;
		dto.indicadorAutorizaNotificacao = Constantes.SIM;
		dto.indicadorTipoAnunciante = "P";
		dto.endereco = new EnderecoDTO();
		dto.endereco.codigoUfCidadeItem = 1;
		dto.endereco.nome = "Rua " + i + dto.nome + dto.nome;
		dto.endereco.numero = "123546435645645645645643654565464365456546346546";
		dto.endereco.bairro = "B:jsgvfdkjghsdg934458735834574937579375khfghfgkdhgkjsdhg353928745974948769456845kjdsghskfhghfds542375" + i;
		dto.endereco.cep = "assdafasds64634563456";
		dto.endereco.cidade = "Sao Paulo";
		return dto;

	}
}
