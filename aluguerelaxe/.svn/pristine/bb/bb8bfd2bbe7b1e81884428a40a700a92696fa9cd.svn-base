package br.com.jcv.aluguerelaxe.client;

import br.com.jcv.aluguerelaxe.client.administracao.console.avisos.AvisosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.avisos.AvisosRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.login.LoginRPC;
import br.com.jcv.aluguerelaxe.client.administracao.login.LoginRPCAsync;
import br.com.jcv.aluguerelaxe.client.campanhas.amigoindicaamigo.PromocaoAmigoIndicaAmigoRPC;
import br.com.jcv.aluguerelaxe.client.campanhas.amigoindicaamigo.PromocaoAmigoIndicaAmigoRPCAsync;
import br.com.jcv.aluguerelaxe.client.caracteristica.CaracteristicaRPC;
import br.com.jcv.aluguerelaxe.client.caracteristica.CaracteristicaRPCAsync;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteRPC;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteRPCAsync;
import br.com.jcv.aluguerelaxe.client.componente.form.CepRPC;
import br.com.jcv.aluguerelaxe.client.componente.form.CepRPCAsync;
import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovelRPC;
import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.componente.paginacao.PaginacaoRPC;
import br.com.jcv.aluguerelaxe.client.componente.paginacao.PaginacaoRPCAsync;
import br.com.jcv.aluguerelaxe.client.componente.progressbar.ProgressBarRPC;
import br.com.jcv.aluguerelaxe.client.componente.progressbar.ProgressBarRPCAsync;
import br.com.jcv.aluguerelaxe.client.depoimento.DepoimentoRPC;
import br.com.jcv.aluguerelaxe.client.depoimento.DepoimentoRPCAsync;
import br.com.jcv.aluguerelaxe.client.destaques.DestaquesRPC;
import br.com.jcv.aluguerelaxe.client.destaques.DestaquesRPCAsync;
import br.com.jcv.aluguerelaxe.client.destino.DestinoRPC;
import br.com.jcv.aluguerelaxe.client.destino.DestinoRPCAsync;
import br.com.jcv.aluguerelaxe.client.enquete.EnqueteModoPublicidadeRPC;
import br.com.jcv.aluguerelaxe.client.enquete.EnqueteModoPublicidadeRPCAsync;
import br.com.jcv.aluguerelaxe.client.faleconosco.FaleConoscoRPC;
import br.com.jcv.aluguerelaxe.client.faleconosco.FaleConoscoRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPC;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.listagem.uf.ListaImovelUfRPC;
import br.com.jcv.aluguerelaxe.client.imovel.listagem.uf.ListaImovelUfRPCAsync;
import br.com.jcv.aluguerelaxe.client.logodinamico.LogoDinamicoRPC;
import br.com.jcv.aluguerelaxe.client.logodinamico.LogoDinamicoRPCAsync;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeRPC;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeRPCAsync;
import br.com.jcv.aluguerelaxe.client.painel.PainelUfCidadesRPC;
import br.com.jcv.aluguerelaxe.client.painel.PainelUfCidadesRPCAsync;
import br.com.jcv.aluguerelaxe.client.painel.PainelUltimosCadastradosRPC;
import br.com.jcv.aluguerelaxe.client.painel.PainelUltimosCadastradosRPCAsync;
import br.com.jcv.aluguerelaxe.client.plano.PlanosRPC;
import br.com.jcv.aluguerelaxe.client.plano.PlanosRPCAsync;
import br.com.jcv.aluguerelaxe.client.publicidade.PublicidadeRPC;
import br.com.jcv.aluguerelaxe.client.publicidade.PublicidadeRPCAsync;
import br.com.jcv.aluguerelaxe.client.superpanel.SuperPanelRPC;
import br.com.jcv.aluguerelaxe.client.superpanel.SuperPanelRPCAsync;
import br.com.jcv.aluguerelaxe.client.uf.UFRPC;
import br.com.jcv.aluguerelaxe.client.uf.UFRPCAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * <h1>ServicosRPC</h1>
 * <p>Classe helper responsÃ¡vel por disponibilizar mÃ©todos de recuperaÃ§Ã£o
 * das interfaces assÃ­ncronas para as chamadas RPC.
 * </p>
 * @author julio
 *
 */
public class ServicosRPC {
	/**
	 * <h2>DESTINO_RPC</h2>
	 * <p>identificaÃ§Ã£o da chamada RPC</p>
	 */
	private static final String DESTINO_RPC = "destinorpc";
	/**
	 * <h2>UFCIDADES_RPC</h2>
	 * <p>identificaÃ§Ã£o da chamada RPC</p>
	 */
	private static final String UFCIDADES_RPC = "painelufcidadesrpc";
	/**
	 * <h2>DEPOIMENTO_RPC</h2>
	 * <p>identificaÃ§Ã£o da chamada RPC</p>
	 */
	private static final String DEPOIMENTO_RPC = "paineldepoimentorpc";
	/**
	 * <h2>PUBLICIDADE_RPC</h2>
	 * <p>identificaÃ§Ã£o da chamada RPC</p>
	 */
	private static final String PUBLICIDADE_RPC = "publicidaderpc";
	/**
	 * <h2>DESTAQUE_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String DESTAQUE_RPC = "destaquesrpc";
	/**
	 * <h2>DESTAQUE_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String FICHA_IMOVEL_RPC = "fichaimovelrpc";
	/**
	 * <h2>DESTAQUE_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String LISTA_IMOVEl_UF_RPC = "listaimovelufrpc";
	/**
	 * <h2>NOVO_DEPOIMENTO_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String NOVO_DEPOIMENTO_RPC = "depoimentorpc";
	/**
	 * <h2>LOGIN_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String LOGIN_RPC = "loginrpc";
	/**
	 * <h2>CLIENTE_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String CLIENTE_RPC = "clienterpc";
	/**
	 * <h2>UF_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String UF_RPC = "ufrpc";
	/**
	 * <h2>GALERIA_IMOVEL_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String GALERIA_IMOVEL_RPC = "galeriaimovelrpc";
	/**
	 * <h2>CARACTERISTICA_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String CARACTERISTICA_RPC = "caracteristicarpc";
	/**
	 * <h2>PAGINACAO_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String PAGINACAO_RPC = "paginacaorpc";
	/**
	 * <h2>BARRA_PROGRESSO_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String BARRA_PROGRESSO_RPC = "progressbarrpc";
	/**
	 * <h2>PAINEL_ULTIMOS_IMOVEIS_CADASTRADOS_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String PAINEL_ULTIMOS_IMOVEIS_CADASTRADOS_RPC = "painelultimoscadastradosrpc";
	/**
	 * <h2>PLANOS_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String PLANOS_RPC = "planosrpc";
	/**
	 * <h2>CEP_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String CEP_RPC = "ceprpc";
	/**
	 * <h2>CEP_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String FALECONOSCO_RPC = "faleconoscorpc";
	/**
	 * <h2>LOGODINAMICO_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String LOGODINAMICO_RPC = "logodinamicorpc";
	/**
	 * <h2>PROMOCAO_AMIGO_INDICA_AMIGO_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String PROMOCAO_AMIGO_INDICA_AMIGO_RPC = "paiarpc";
	/**
	 * <h2>AVISOS_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String AVISOS_RPC = "avisosrpc";
	/**
	 * <h2>RESERVA_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String RESERVA_RPC = "reservarpc";
	/**
	 * <h2>MODO_PUBLICIDADE_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String MODO_PUBLICIDADE_RPC = "modopublicidaderpc";
	/**
	 * <h2>ENQUETE_MODO_PUBLICIDADE_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String ENQUETE_MODO_PUBLICIDADE_RPC = "enquetemprpc";
	/**
	 * <h2>SUPER_PANEL_RPC</h2>
	 * <p>identificação da chamada RPC</p>
	 */
	private static final String SUPER_PANEL_RPC = "superpanelrpc";

	/**
	 * <h2>getReservaRPC</h2>
	 * <p>Metodo responsavel por retornar uma interface para assincrona</p>
	 */
	public static ReservaRPCAsync getReservaRPC() {
		ReservaRPCAsync newsservice = ReservaRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + RESERVA_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getSuperPanelRPC</h2>
	 * <p>Metodo responsavel por retornar uma interface para assincrona</p>
	 */
	public static SuperPanelRPCAsync getSuperPanelRPC() {
		SuperPanelRPCAsync newsservice = SuperPanelRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + SUPER_PANEL_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getAvisoRPC</h2>
	 * <p>Metodo responsavel por retornar uma interface para assincrona</p>
	 */
	public static AvisosRPCAsync getAvisoRPC() {
		AvisosRPCAsync newsservice = AvisosRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + AVISOS_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getLogoDinamicoRPCAsync</h2>
	 * <p>Metodo responsavel por retornar uma interface para assincrona</p>
	 */
	public static LogoDinamicoRPCAsync getLogoDinamicoRPC() {
		LogoDinamicoRPCAsync newsservice = LogoDinamicoRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + LOGODINAMICO_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>CepRPCAsync</h2>
	 * <p>Metodo responsavel por retornar uma interface para assincrona</p>
	 */
	public static FaleConoscoRPCAsync getFaleConoscoRPC() {
		FaleConoscoRPCAsync newsservice = FaleConoscoRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + FALECONOSCO_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>CepRPCAsync</h2>
	 * <p>Metodo responsavel por retornar uma interface para assincrona</p>
	 */
	public static CepRPCAsync getCepRPC() {
		CepRPCAsync newsservice = CepRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + CEP_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>PlanosRPCAsync</h2>
	 * <p>Metodo responsavel por retornar uma interface para assincrona</p>
	 */
	public static PlanosRPCAsync getPlanosRPC() {
		PlanosRPCAsync newsservice = PlanosRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + PLANOS_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getPainelUltimosImoveisCadastradosRPC</h2>
	 * <p>Metodo responsavel por retornar uma interface para assincrona</p>
	 */
	public static PainelUltimosCadastradosRPCAsync getPainelUltimosImoveisCadastradosRPC() {
		PainelUltimosCadastradosRPCAsync newsservice = PainelUltimosCadastradosRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + PAINEL_ULTIMOS_IMOVEIS_CADASTRADOS_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getPaginacaoRPC</h2>
	 * <p>MÃ©todo responsÃ¡vel por retornar uma interface para 
	 * LoginRPCAsync</p>
	 */
	@SuppressWarnings("unchecked")
	public static PaginacaoRPCAsync getPaginacaoRPC() {
		PaginacaoRPCAsync newsservice = PaginacaoRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + PAGINACAO_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getUFRPC</h2>
	 * <p>MÃ©todo responsÃ¡vel por retornar uma interface para 
	 * LoginRPCAsync</p>
	 */
	public static CaracteristicaRPCAsync getCaracteristicaRPC() {
		CaracteristicaRPCAsync newsservice = CaracteristicaRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + CARACTERISTICA_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getUFRPC</h2>
	 * <p>MÃ©todo responsÃ¡vel por retornar uma interface para 
	 * LoginRPCAsync</p>
	 */
	public static GaleriaImovelRPCAsync getGaleriaImovelRPC() {
		GaleriaImovelRPCAsync newsservice = GaleriaImovelRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + GALERIA_IMOVEL_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getUFRPC</h2>
	 * <p>MÃ©todo responsÃ¡vel por retornar uma interface para 
	 * LoginRPCAsync</p>
	 */
	public static UFRPCAsync getUFRPC() {
		UFRPCAsync newsservice = UFRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + UF_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getClienteRPC</h2>
	 * <p>MÃ©todo responsÃ¡vel por retornar uma interface para 
	 * LoginRPCAsync</p>
	 */
	public static ClienteRPCAsync getClienteRPC() {
		ClienteRPCAsync newsservice = ClienteRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + CLIENTE_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getLoginRPC</h2>
	 * <p>MÃ©todo responsÃ¡vel por retornar uma interface para 
	 * LoginRPCAsync</p>
	 */
	public static LoginRPCAsync getLoginRPC() {
		LoginRPCAsync newsservice = LoginRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + LOGIN_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	
	/**
	 * <h2>getPainelUfCidadesRPC</h2>
	 * <p>MÃ©todo responsÃ¡vel por retornar uma interface para 
	 * PainelUfCidadesRPCAsync</p>
	 */
	public static DepoimentoRPCAsync getDepoimentoRPC() {
		DepoimentoRPCAsync newsservice = DepoimentoRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + NOVO_DEPOIMENTO_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}

	/**
	 * <h2>getPainelUfCidadesRPC</h2>
	 * <p>MÃ©todo responsÃ¡vel por retornar uma interface para 
	 * PainelUfCidadesRPCAsync</p>
	 */
	public static ListaImovelUfRPCAsync getListaImovelUfRPC() {
		ListaImovelUfRPCAsync newsservice = ListaImovelUfRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + LISTA_IMOVEl_UF_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}

	/**
	 * <h2>getPainelUfCidadesRPC</h2>
	 * <p>MÃ©todo responsÃ¡vel por retornar uma interface para 
	 * PainelUfCidadesRPCAsync</p>
	 */
	public static FichaImovelRPCAsync getFichaImovelRPC() {
		FichaImovelRPCAsync newsservice = FichaImovelRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + FICHA_IMOVEL_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	
	/**
	 * <h2>getPainelUfCidadesRPC</h2>
	 * <p>MÃ©todo responsÃ¡vel por retornar uma interface para 
	 * PainelUfCidadesRPCAsync</p>
	 */
	public static PainelUfCidadesRPCAsync getPainelUfCidadesRPC() {
		PainelUfCidadesRPCAsync newsservice = PainelUfCidadesRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + UFCIDADES_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getDestinoRPC</h2>
	 * <p>MÃ©todo responsÃ¡vel por retornar uma interface para DestinoRPCAsync</p>
	 */
	public static DestinoRPCAsync getDestinoRPC() {
		DestinoRPCAsync newsservice = DestinoRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + DESTINO_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getPublicidadeRPC</h2>
	 * <p>MÃ©todo responsÃ¡vel por retornar uma interface para PublicidadeRPCAsync</p>
	 */
	public static PublicidadeRPCAsync getPublicidadeRPC() {
		PublicidadeRPCAsync newsservice = PublicidadeRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + PUBLICIDADE_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getPublicidadeRPC</h2>
	 * <p>Método responsável por retornar uma interface para PublicidadeRPCAsync</p>
	 */
	public static DestaquesRPCAsync getDestaquesRPC() {
		DestaquesRPCAsync newsservice = DestaquesRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + DESTAQUE_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getPublicidadeRPC</h2>
	 * <p>Método responsável por retornar uma interface para PublicidadeRPCAsync</p>
	 */
	public static ProgressBarRPCAsync getProgressBarRPC() {
		ProgressBarRPCAsync newsservice = ProgressBarRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + BARRA_PROGRESSO_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getPromocaoAmigoIndicaAmigoRPC</h2>
	 * <p>Método responsável por retornar uma interface para PromocaoAmigoIndicaAmigoRPCAsync</p>
	 */
	public static PromocaoAmigoIndicaAmigoRPCAsync getPromocaoAmigoIndicaAmigoRPC() {
		PromocaoAmigoIndicaAmigoRPCAsync newsservice = PromocaoAmigoIndicaAmigoRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + PROMOCAO_AMIGO_INDICA_AMIGO_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getModoPublicidadeRPC</h2>
	 * <p>Método responsável por retornar uma interface para ModoPublicidadeRPCAsync</p>
	 */
	public static ModoPublicidadeRPCAsync getModoPublicidadeRPC() {
		ModoPublicidadeRPCAsync newsservice = ModoPublicidadeRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + MODO_PUBLICIDADE_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getEnqueteModoPublicidadeRPC</h2>
	 * <p>Método responsável por retornar uma interface para EnqueteModoPublicidadeRPCAsync</p>
	 */
	public static EnqueteModoPublicidadeRPCAsync getEnqueteModoPublicidadeRPC() {
		EnqueteModoPublicidadeRPCAsync newsservice = EnqueteModoPublicidadeRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + ENQUETE_MODO_PUBLICIDADE_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
}
