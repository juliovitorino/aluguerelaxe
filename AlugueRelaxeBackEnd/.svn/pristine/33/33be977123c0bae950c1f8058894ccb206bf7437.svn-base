package br.com.jcv.backend.factory;

import br.com.jcv.backend.assinantes.AssinantesDTO;
import br.com.jcv.backend.assinantes.AssinantesValidador;
import br.com.jcv.backend.cidade.CidadeValidador;
import br.com.jcv.backend.cliente.ClienteContraSenhaDTO;
import br.com.jcv.backend.cliente.ClienteContraSenhaValidador;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.ClienteValidador;
import br.com.jcv.backend.cliente.ContatoAnuncianteValidador;
import br.com.jcv.backend.depoimento.DepoimentoDTO;
import br.com.jcv.backend.depoimento.DepoimentoValidador;
import br.com.jcv.backend.imovel.ImovelFichaCompletaValidador;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoValidador;
import br.com.jcv.backend.portal.NovoCodigoAcessoDTO;
import br.com.jcv.backend.portal.NovoCodigoAcessoValidador;
import br.com.jcv.backend.portal.faleconosco.FaleConoscoValidador;
import br.com.jcv.backend.portal.login.LoginDTO;
import br.com.jcv.backend.portal.login.LoginValidador;
import br.com.jcv.backend.reserva.CodigoTrackingValidador;
import br.com.jcv.backend.reserva.CriarPreReservaValidador;
import br.com.jcv.backend.reserva.ReservaDTO;
import br.com.jcv.backend.validador.EnderecoValidador;
import br.com.jcv.backend.validador.TelefoneValidador;
import br.com.jcv.backend.validador.Validador;

public class ValidadorFactory {

	
	public static Validador<ClienteContraSenhaDTO> getInstanceClienteContraSenha() {
		return new ClienteContraSenhaValidador();
	}
	
	
	public static Validador<LoginDTO> getInstanceLoginPortal() {
		return new LoginValidador();
	}
	
	@SuppressWarnings("rawtypes")
	public static Validador getInstanceTabelaPreco() {
		return new TabelaPrecoValidador();
	}
	
	@SuppressWarnings("rawtypes")
	public static Validador getInstanceCidade() {
		return new CidadeValidador();
	}
	
	@SuppressWarnings("rawtypes")
	public static Validador getInstanceEndereco() {
		return new EnderecoValidador();
	}
	
	public static Validador<ClienteDTO> getInstanceCliente() {
		return new ClienteValidador();
	}
	
	@SuppressWarnings("rawtypes")
	public static Validador getInstanceTelefone() {
		return new TelefoneValidador();
	}
	
	@SuppressWarnings("rawtypes")
	public static Validador getInstanceImovelFichaCompleta() {
		return new ImovelFichaCompletaValidador();
	}
	
	public static Validador getInstanceContatoAnunciante() {
		return new ContatoAnuncianteValidador();
	}
	
	public static Validador getInstanceFaleConoscoPortal() {
		return new FaleConoscoValidador(); 
	}
	
	public static Validador<DepoimentoDTO> getInstanceDepoimento() {
		return new DepoimentoValidador(); 
	}
	
	public static Validador<NovoCodigoAcessoDTO> getInstanceNovoCodigoAcesso() {
		return new NovoCodigoAcessoValidador(); 
	}
	
	public static Validador<AssinantesDTO> getInstanceAssinantes() {
		return new AssinantesValidador(); 
	}
	
	public static Validador<ReservaDTO> getInstanceCodigoTracking() {
		return new CodigoTrackingValidador(); 
	}
	
	public static Validador<ReservaDTO> getInstanceCriarPreReserva() {
		return new CriarPreReservaValidador(); 
	}
	
}
