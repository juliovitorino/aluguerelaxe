package br.com.jcv.backend;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import junit.framework.TestCase;
import br.com.jcv.backend.caracteristicas.CaracteristicaDTO;
import br.com.jcv.backend.cliente.ClienteBusinessImpl;
import br.com.jcv.backend.cliente.ClienteContraSenhaDTO;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.ClienteServiceImpl;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelPlanoDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;
import br.com.jcv.backend.interfaces.business.ImovelBusiness;
import br.com.jcv.backend.interfaces.services.ClienteService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.interfaces.services.ReservaService;
import br.com.jcv.backend.locatario.LocatarioDTO;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.reserva.ReservaDTO;
import br.com.jcv.backend.reserva.ReservaServiceImpl;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class ReservaTest extends TestCase {
	
	private int controle = 0;
	
	public void testListarTarefasPendentesAguardandoConfirmacaoDeposito(){
		try {
			
			// Insere um novo cliente
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			// Insere um novo imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			// Cria a pre-reserva
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva);
			
			// Aprova a pre-reserva
			ReservaDTO dtoAprovada = rs.aprovarPreReserva(dtoretorno.token);	
			
			List<ReservaDTO> lst = rs.listarTarefasPendentes("CD");
			
			assertNotNull(lst);
			
		} catch (AlugueRelaxeException are) {
			fail("Verificar o erro...");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	
	public void testListarTarefasPendentesAguardandoAprovacao(){
		try {
			
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva); 

			List<ReservaDTO> lst = rs.listarTarefasPendentes("AP");
			
			assertNotNull(lst);
			
		} catch (AlugueRelaxeException are) {
			fail("Verificar o erro...");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	public void testLiberacaoDepositoTrackingInvalido(){
		String codigoEsperado = MSGCODE.CODIGO_TRACKING_INVALIDO;
		try {
			String vi = "yUUUUU";
			String track = "igkhj";
			
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			// Solicita a liberacao do pagamento por um solicitante desconhecido
			ReservaDTO dtoLibera = rs.solicitarLiberacaoDeposito(2, vi, track);
			
			fail("Verificar o erro...");
			
		} catch (AlugueRelaxeException are) {
			assertEquals(codigoEsperado, are.getCodigoErro());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	public void testLiberacaoDepositoSolicitanteInvalido(){
		String codigoEsperado = MSGCODE.SOLICITANTE_DE_LIBERACAO_INVALIDO;
		try {
			
			// Insere um novo cliente
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			// Insere um novo imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			// Cria a pre-reserva
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva);
			
			// Aprova a pre-reserva
			ReservaDTO dtoAprovada = rs.aprovarPreReserva(dtoretorno.token);
			
			// Confirma pagto com informacoes bancarias
			ReservaDTO dtoConfirma = rs.confirmarReserva(dtoretorno.chaveTracker, new Date(), dtoAprovada.valorPrevistoDeposito + dtoAprovada.valorCaucao);
			
			// Solicita a liberacao do pagamento por um solicitante desconhecido
			ReservaDTO dtoLibera = rs.solicitarLiberacaoDeposito(-9809, dtoConfirma.chaveLiberacaoDeposito,dtoConfirma.chaveTracker);
			
			fail("Aconteceu algo errado");
			
		} catch (AlugueRelaxeException are) {
			assertEquals(codigoEsperado, are.getCodigoErro());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	public void testLiberacaoDepositoVoucherInvalido(){
		String codigoEsperado = MSGCODE.VOUCHER_INVALIDO;
		try {
			
			// Insere um novo cliente
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			// Insere um novo imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			// Cria a pre-reserva
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva);
			
			// Aprova a pre-reserva
			ReservaDTO dtoAprovada = rs.aprovarPreReserva(dtoretorno.token);
			
			// Confirma pagto com informacoes bancarias
			ReservaDTO dtoConfirma = rs.confirmarReserva(dtoretorno.chaveTracker, new Date(), dtoAprovada.valorPrevistoDeposito + dtoAprovada.valorCaucao);
			
			// Solicita a liberacao do pagamento por um solicitante desconhecido
			ReservaDTO dtoLibera = rs.solicitarLiberacaoDeposito(dtoConfirma.ifcdto.imovel.idCliente, "xpto12f",dtoConfirma.chaveTracker);
			
			fail("Aconteceu algo errado");
			
		} catch (AlugueRelaxeException are) {
			assertEquals(codigoEsperado, are.getCodigoErro());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	public void testLiberacaoDepositoInfoBancoInvalido(){
		String codigoEsperado = MSGCODE.FALTANDO_INFORMACOES_BANCARIAS;
		try {
			
			// Insere um novo cliente
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			// Insere um novo imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			// Cria a pre-reserva
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva);
			
			// Aprova a pre-reserva
			ReservaDTO dtoAprovada = rs.aprovarPreReserva(dtoretorno.token);
			
			// Confirma pagto com informacoes bancarias
			ReservaDTO dtoConfirma = rs.confirmarReserva(dtoretorno.chaveTracker, new Date(), dtoAprovada.valorPrevistoDeposito + dtoAprovada.valorCaucao);
			
			// Solicita a liberacao do pagamento por um solicitante desconhecido
			ReservaDTO dtoLibera = rs.solicitarLiberacaoDeposito(dtoConfirma.ifcdto.imovel.idCliente, dtoConfirma.chaveLiberacaoDeposito, dtoConfirma.chaveTracker);
			
			fail("Aconteceu algo errado");
			
		} catch (AlugueRelaxeException are) {
			assertEquals(codigoEsperado, are.getCodigoErro());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	public void testLiberacaoDepositoAntesCheckin(){
		String codigoEsperado = MSGCODE.LIBERACAO_IMPEDIDA_ANTES_DATA_CHECKIN;
		try {
			
			// Insere um novo cliente
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			// Insere um novo imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			// Cria a pre-reserva
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.dataCheckin = new Date(2100,01,01);
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva);
			
			// Aprova a pre-reserva
			ReservaDTO dtoAprovada = rs.aprovarPreReserva(dtoretorno.token);
			
			// Confirma pagto com informacoes bancarias
			ReservaDTO dtoConfirma = rs.confirmarReserva(dtoretorno.chaveTracker, new Date(), dtoAprovada.valorPrevistoDeposito + dtoAprovada.valorCaucao);
			
			// Solicita a liberacao do pagamento por um solicitante desconhecido
			ReservaDTO dtoLibera = rs.solicitarLiberacaoDeposito(dtoConfirma.ifcdto.imovel.idCliente, dtoConfirma.chaveLiberacaoDeposito, dtoConfirma.chaveTracker);
			
			fail("Aconteceu algo errado");
			
		} catch (AlugueRelaxeException are) {
			assertEquals(codigoEsperado, are.getCodigoErro());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	public void testLiberacaoDepositoValido(){
		String codigoEsperado = MSGCODE.LIBERACAO_PAGAMENTO_REGISTRADO_SUCESSO;
		try {
			
			// Insere um novo cliente
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			// Insere um novo imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			// Cria a pre-reserva
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva);
			
			// Aprova a pre-reserva
			ReservaDTO dtoAprovada = rs.aprovarPreReserva(dtoretorno.token);
			
			// Confirma pagto com informacoes bancarias
			ReservaDTO dtoConfirma = rs.confirmarReserva(dtoretorno.chaveTracker, new Date(), dtoAprovada.valorPrevistoDeposito + dtoAprovada.valorCaucao);
			
			// Solicita a liberacao do pagamento por um solicitante desconhecido
			ReservaDTO dtoLibera = rs.solicitarLiberacaoDeposito(dtoConfirma.ifcdto.imovel.idCliente, dtoConfirma.chaveLiberacaoDeposito, dtoConfirma.chaveTracker);
			
			assertEquals(codigoEsperado, dtoLibera.msgcode);
			
		} catch (AlugueRelaxeException are) {
			fail(are.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	public void testLiberacaoDepositoSemPagamentoReserva(){
		String codigoEsperado = MSGCODE.PAGAMENTO_RESERVA_NAO_CONFIRMADO;
		try {
			
			// Insere um novo cliente
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			// Insere um novo imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			// Cria a pre-reserva
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva);
			
			// Aprova a pre-reserva
			ReservaDTO dtoAprovada = rs.aprovarPreReserva(dtoretorno.token);
			
			// Solicita a liberacao do pagamento sem confirmacao de pgto da reserva
			ReservaDTO dtoLibera = rs.solicitarLiberacaoDeposito(dtoAprovada.ifcdto.imovel.idCliente, dtoAprovada.chaveLiberacaoDeposito, dtoAprovada.chaveTracker);
			
			fail("Aconteceu algo errado");
			
		} catch (AlugueRelaxeException are) {
			assertEquals(codigoEsperado, are.getCodigoErro());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	public void testConfirmarPreReserva(){
		try {
			String codigoEsperado = MSGCODE.PAGAMENTO_REGISTRADO_REALIZADA_SUCESSO;
			
			// Insere um novo cliente
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			// Insere um novo imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			// Cria a pre-reserva
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva);
			
			// Aprova a pre-reserva
			ReservaDTO dtoAprovada = rs.aprovarPreReserva(dtoretorno.token);
			
			// Confirma pagto com informacoes bancarias
			ReservaDTO dtoConfirma = rs.confirmarReserva(dtoretorno.chaveTracker, new Date(), dtoAprovada.valorPrevistoDeposito + dtoAprovada.valorCaucao);
			
			assertEquals(codigoEsperado, dtoConfirma.msgcode);
			
		} catch (AlugueRelaxeException are) {
			fail(are.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	public void testConfirmarPreReservaJaExistente(){
		String codigoEsperado = MSGCODE.RESERVA_JA_ESTA_CONFIRMADA;
		try {
			
			// Insere um novo cliente
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			// Insere um novo imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			// Cria a pre-reserva
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva);
			
			// Aprova a pre-reserva
			ReservaDTO dtoAprovada = rs.aprovarPreReserva(dtoretorno.token);
			
			// Confirma pagto com informacoes bancarias
			ReservaDTO dtoConfirma = rs.confirmarReserva(dtoretorno.chaveTracker, new Date(), dtoAprovada.valorPrevistoDeposito);
			
			// Forca uma reconfirmacao
			ReservaDTO dtoConfirma2 = rs.confirmarReserva(dtoretorno.chaveTracker, new Date(), dtoAprovada.valorPrevistoDeposito);
			
			fail(dto.msgcode);
			
		} catch (AlugueRelaxeException are) {
			assertEquals(codigoEsperado, are.getCodigoErro());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	public void testConfirmarPreReservaSemCaucao(){
		try {
			String codigoEsperado = MSGCODE.PAGAMENTO_REGISTRADO_REALIZADA_SUCESSO;
			
			// Insere um novo cliente
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			// Insere um novo imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			// Cria a pre-reserva
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.valorCaucao = 0.00;
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva);
			
			// Aprova a pre-reserva
			ReservaDTO dtoAprovada = rs.aprovarPreReserva(dtoretorno.token);
			
			// Confirma pagto com informacoes bancarias
			ReservaDTO dtoConfirma = rs.confirmarReserva(dtoretorno.chaveTracker, new Date(), dtoAprovada.valorPrevistoDeposito);
			
			assertEquals(codigoEsperado, dtoConfirma.msgcode);
			
		} catch (AlugueRelaxeException are) {
			fail(are.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	public void testAprovarPreReserva(){
		try {
			String codigoEsperado = MSGCODE.VALIDACAO_PRE_RESERVA_REALIZADA_SUCESSO;
			
			// Insere um novo cliente
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			// Insere um novo imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			// Cria a pre-reserva
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva);
			
			// Aprova a pre-reserva
			ReservaDTO dtoAprovada = rs.aprovarPreReserva(dtoretorno.token);
			
			assertEquals(codigoEsperado, dtoAprovada.msgcode);
			
		} catch (AlugueRelaxeException are) {
			fail(are.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	

	
	public void testCriarPreReserva(){
		try {
			String codigoEsperado = MSGCODE.PRE_RESERVA_REALIZADA_SUCESSO;
			
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva); 
			
			assertEquals(codigoEsperado, dtoretorno.msgcode);
			
		} catch (AlugueRelaxeException are) {
			fail(are.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	public void testCriarPreReserva100PorCentoPagamento(){
		try {
			String codigoEsperado = MSGCODE.PRE_RESERVA_REALIZADA_SUCESSO;
			
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.formaPagamento = Constantes.FORMA_PGTO_TOTAL;
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva); 
			
			assertEquals(codigoEsperado, dtoretorno.msgcode);
			
		} catch (AlugueRelaxeException are) {
			fail(are.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	public void testTransferenciaDepositoValido(){
		String codigoEsperado = MSGCODE.TRANSFERENCIA_DEPOSITO_REALIZADA_SUCESSO;
		try {
			
			// Insere um novo cliente
			ClienteContraSenhaDTO dto = this.getClienteDTO();
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtor = cs.criarNovaConta(dto);
			cs.ativarNovaConta(dtor.codigoHashAtivacao);
			dtor = cs.pesquisarRegistro(dtor.codigoHashAtivacao);
			
			// Insere um novo imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO imovel1 = getImovelDTO(dtor);
			DTOPadrao dtopadrao1 = is.adicionarImovelCarteira(imovel1);
			
			// Cria a pre-reserva
			ReservaDTO dtoreserva = this.getDTO();
			dtoreserva.ifcdto = imovel1;
			ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
			ReservaDTO dtoretorno = rs.criarPreReserva(dtoreserva);
			
			// Aprova a pre-reserva
			ReservaDTO dtoAprovada = rs.aprovarPreReserva(dtoretorno.token);
			
			// Confirma pagto com informacoes bancarias
			ReservaDTO dtoConfirma = rs.confirmarReserva(dtoretorno.chaveTracker, new Date(), dtoAprovada.valorPrevistoDeposito);
			
			// Solicita a liberacao do pagamento por um solicitante desconhecido
			ReservaDTO dtoLibera = rs.solicitarLiberacaoDeposito(dtoConfirma.ifcdto.imovel.idCliente, dtoConfirma.chaveLiberacaoDeposito, dtoConfirma.chaveTracker);
			
			// Solicita registra a transferencia do deposito apos operacao bancaria
			ReservaDTO dtoTransf = rs.transferirDeposito(dtoLibera.chaveTracker);
			
			assertEquals(codigoEsperado, dtoTransf.msgcode);
			
		} catch (AlugueRelaxeException are) {
			fail(are.getMensagem());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	//--------------------------------------------------
	// Metodos de apoio
	//--------------------------------------------------
	private String getIpsumLorem(int tamanho) {
		
		String str =  "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "+
		"Etiam eget ligula eu lectus lobortis condimentum. "+
		"Aliquam nonummy auctor massa. Pellentesque habitant morbi tristique senectus et netus et "+
		"malesuada fames ac turpis egestas. Nulla at risus. Quisque purus magna, auctor et, "+
		"sagittis ac, posuere eu, lectus. Nam mattis, felis ut adipiscing.";
		return str.substring(0, tamanho-1);
	}
	
	private ClienteContraSenhaDTO getClienteDTO() {
		ClienteContraSenhaDTO dto = new ClienteContraSenhaDTO();
		String i = String.valueOf(System.currentTimeMillis());
		dto.nome = "Julio Vitorino - " + i;
		dto.cpf = i.substring(0,11);
		dto.dataNascimento = new Date();
		dto.senha = "rcanet";
		dto.contraSenha = "rcanet";
		dto.email = i+"@ig.com.br";
		dto.urlwww = "www." + i + "com.br";
		dto.indicadorAutorizaNotificacao = Constantes.SIM;
		dto.indicadorTipoAnunciante = "P";
		dto.indicadorStatus = ClienteBusinessImpl.CONTA_STATUS_ATIVA;
		dto.endereco = new EnderecoDTO();
		dto.endereco.codigoUfCidadeItem = 1;
		dto.endereco.nome = "Rua " + i;
		dto.endereco.numero = "123";
		dto.endereco.complemento = "xpto";
		dto.endereco.bairro = "B:" + i;
		dto.endereco.cep = "27189098";
		dto.endereco.cidade = "Sao Paulo";
		return dto;
	}
		
	
	private ClienteContraSenhaDTO getClienteJasperDTO() {
		ClienteContraSenhaDTO dto = new ClienteContraSenhaDTO();
		String i = String.valueOf(System.currentTimeMillis());
		dto.nome = "Jasper Mattedi";
		dto.cpf = i.substring(0,11);
		dto.dataNascimento = new Date();
		dto.senha = "rcanet";
		dto.contraSenha = "rcanet";
		dto.email = "jasper.mattedi@gmail.com";
		dto.urlwww = "www." + i + "com.br";
		dto.indicadorAutorizaNotificacao = Constantes.SIM;
		dto.indicadorTipoAnunciante = "P";
		dto.indicadorStatus = ClienteBusinessImpl.CONTA_STATUS_ATIVA;
		dto.endereco = new EnderecoDTO();
		dto.endereco.codigoUfCidadeItem = 1;
		dto.endereco.nome = "Rua " + i;
		dto.endereco.numero = "123";
		dto.endereco.complemento = "xpto";
		dto.endereco.bairro = "B:" + i;
		dto.endereco.cep = "27189098";
		dto.endereco.cidade = "Sao Paulo";
		return dto;
	}
		
	private ReservaDTO getDTO() {
		String tm = String.valueOf(System.currentTimeMillis());
		String[] nomes = new String[10];
		nomes[0] = "Thales costa";
		nomes[1] = "Amanda borges";
		nomes[2] = "Peter punk";
		nomes[3] = "Bob Dilan";
		nomes[4] = "Ratatui da silva";
		nomes[5] = "Overnight Closure";
		nomes[6] = "Everynight matchbox";
		nomes[7] = "CleanOut da costa e sá";
		nomes[8] = "Justino da Silva comodoro";
		nomes[9] = "Cleveland da costa";
		
		controle = (++controle > 9) ? 0 : controle;
		
		ReservaDTO dto = new ReservaDTO();
		dto.locatario = new LocatarioDTO();
		dto.locatario.email = tm + "@intelcore.com.br";
		dto.locatario.nome = nomes[controle];
		dto.locatario.cpf = tm.substring(0, 11);
		dto.dataCheckin = new Timestamp(System.currentTimeMillis());
		dto.dataCheckout = new Timestamp(System.currentTimeMillis()+50000);
		dto.ifcdto = new ImovelFichaCompletaDTO();
		dto.ifcdto.imovel = new ImovelDTO();
		dto.ifcdto.imovel.id = 2;
		dto.dataPrevistaDeposito = new Date();
		dto.formaPagamento = (System.currentTimeMillis() % 2 == 0 ? Constantes.FORMA_PGTO_PARCIAL : Constantes.FORMA_PGTO_TOTAL);
		dto.valorAluguelNegociado = Double.valueOf(tm.substring(tm.length()-8, tm.length()-1))/100;
		dto.valorPrevistoDeposito = (dto.formaPagamento.equals(Constantes.FORMA_PGTO_PARCIAL) ? dto.valorAluguelNegociado * 0.5 : dto.valorAluguelNegociado );
		dto.locatario.endereco = new EnderecoDTO();
		dto.locatario.endereco.codigoUfCidadeItem = 1;
		dto.locatario.endereco.nome = "Rua " + tm;
		dto.locatario.endereco.numero = "123";
		dto.locatario.endereco.complemento = "xpto";
		dto.locatario.endereco.bairro = "B:" + tm;
		dto.locatario.endereco.cep = "27189098";
		dto.locatario.telefone = new TelefoneDTO();
		dto.locatario.telefone.ddd = "21";
		dto.locatario.telefone.telefone = "21560987";
		dto.valorCaucao = (System.currentTimeMillis() % 2 == 0 ? 0 : Double.valueOf(tm.substring(tm.length()-8, tm.length()-2))/100);
		try {
			dto.percentualComissao = Double.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PERC_COMISSAO_PADRAO_TEMPORADA));
		} catch (NumberFormatException e) {
			dto.percentualComissao = 0.4;
		} catch (AlugueRelaxeException e) {
			dto.percentualComissao = 0.5;
		}
		return dto;
	}
	
	private ImovelFichaCompletaDTO getImovelDTO(ClienteDTO clientedto) throws AlugueRelaxeException {
		
		// Monta uma composicao aleatoria para trabalhar com imoveis diferentes
		Random rndgen = new Random();
		int randomcodigoUfCidadeItem = rndgen.nextInt(5000);
		int randomBanheiro = rndgen.nextInt(20);
		int randomQuartos = rndgen.nextInt(20);
		int randomSuites = rndgen.nextInt(20);
		int randomCapacidade = rndgen.nextInt(20);
		int randomCliente = rndgen.nextInt((int)clientedto.id);
		
		int randomTipoProp = rndgen.nextInt(9);
		String tipoProp = "CAHXFZSLP".substring(randomTipoProp,randomTipoProp+1);
		
		ImovelFichaCompletaDTO dto = new ImovelFichaCompletaDTO();
		
		String i = String.valueOf(System.currentTimeMillis());
		dto.cliente = clientedto;
		
		randomCliente = (randomCliente < 2 ? 2 : randomCliente);
		dto.imovel = new ImovelDTO();
		// Se quiser codigo do cliente randomico apenas tire o comentário
		//dto.imovel.idCliente = (long) randomCliente;
		
		dto.imovel.idCliente = clientedto.id;

		dto.imovel.qtdeQuartos = randomQuartos;
		dto.imovel.qtdeSuites = randomSuites;
		dto.imovel.qtdeBanheiros = randomBanheiro;
		dto.imovel.qtdeCapacidade = randomCapacidade;
		dto.imovel.indicadorStatus = ImovelBusiness.STATUS_ATIVO;
		dto.imovel.indicadorMostraTabelaPreco = Constantes.SIM;
		dto.imovel.indicadorPermiteAlugarFestas = Constantes.NAO;
		dto.imovel.descricaoTituloAnuncio = "Casa de praia dos sonhos!" + this.calculaHash().substring(0, 6);
		dto.imovel.descricaoGeral = "Casa na praia maravilhosa " + getIpsumLorem(250);
		dto.imovel.descricaoQuartos = "Quartos fantasticos " + getIpsumLorem(250);
		dto.imovel.indicadorCondominio = "S";
		dto.imovel.descricaoArredores = "Perto de tudo " + getIpsumLorem(250);
		dto.imovel.observacoes = "Simplesmente demais";
		dto.imovel.indicadorTipoPropriedade = tipoProp;
		dto.imovel.qtdeVisitas = 0;
		dto.imovel.endereco = new EnderecoDTO();
		dto.imovel.endereco.codigoUfCidadeItem = randomcodigoUfCidadeItem;
		dto.imovel.endereco.nome = "Rua " + i;
		dto.imovel.endereco.numero = "123";
		dto.imovel.endereco.bairro = "B:" + i;
		dto.imovel.endereco.complemento = "C:" + i;
		dto.imovel.endereco.cep = "27189098";
		// popula caracteristicas do imovel
		dto.caracteristicaImovel = carregaCaracteristicas();
		dto.caracteristicaCondominio = carregaCaracteristicas();
		dto.tabelaPreco = carregaTabelaPreco();
		
		// popula imovel em plano gratuito
		dto.imovelPlano = new ImovelPlanoDTO();
		dto.imovelPlano.plano = new PlanoDTO();
		dto.imovelPlano.plano.id = Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PLANO_ADESAO_GRATUITO)); 
		
		return dto;
	}

	private List<ImovelCaracteristicaDTO> carregaCaracteristicas() {
		List<ImovelCaracteristicaDTO> lst = new ArrayList<ImovelCaracteristicaDTO>();
		for (long i = 1; i < 20; i++){
			ImovelCaracteristicaDTO icdto = new ImovelCaracteristicaDTO();
			icdto.caracteristica = new CaracteristicaDTO();
			icdto.caracteristica.id = i;
			lst.add(icdto);
		}
		return lst;

	}
	
	private List<TabelaPrecoDTO> carregaTabelaPreco() {
		List<TabelaPrecoDTO> lst = new ArrayList<TabelaPrecoDTO>();
		for (int j = 0; j < 4; j++) {
			String i = String.valueOf(System.currentTimeMillis());
			TabelaPrecoDTO tpdto = new TabelaPrecoDTO();
			tpdto.dataInicio = new Date();
			tpdto.dataFim = new Date();
			tpdto.observacao = "observacao " + i;
			tpdto.textoMinimoDe = "dias " + i;
			tpdto.textoPeriodo = "P:" + i;
			tpdto.valorTabela = j + 420;
			lst.add(tpdto);
		}
		
		
		return lst;
	}

	private String calculaHash() {
		String retorno = "";
		StringBuilder sb = new StringBuilder();
		sb.append(getIpsumLorem(250) );
		sb.append(String.valueOf(System.currentTimeMillis()));

		BaseFactorySecurity sha1 = BaseFactorySecurity.getInstance(0);
		try {
			retorno = sha1.criptografar(sb.toString());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

		

}
