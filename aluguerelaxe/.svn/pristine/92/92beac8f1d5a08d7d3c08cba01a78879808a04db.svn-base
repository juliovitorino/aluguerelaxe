package br.com.jcv.aluguerelaxe.server;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.administracao.console.geolocalizacaowizard.GeoLocalizacaoVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.publicidades.VendaPublicidadeVO;
import br.com.jcv.aluguerelaxe.client.caracteristica.CaracteristicaVO;
import br.com.jcv.aluguerelaxe.client.charter.CharterVO;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoFaturaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.caracteristica.ImovelCaracteristicaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.AvaliacaoAnuncioImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPC;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.IndicarAmigoVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.PublicidadeImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.imovel.local.ClassificacaoVO;
import br.com.jcv.aluguerelaxe.client.imovel.local.DistanciaVO;
import br.com.jcv.aluguerelaxe.client.imovel.local.LocalItemVO;
import br.com.jcv.aluguerelaxe.client.imovel.local.LocalVO;
import br.com.jcv.aluguerelaxe.client.imovel.tabelapreco.TabelaPrecoVO;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ContatoAnuncianteThreadVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOContatoAnuncianteImpl;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOContatoAnuncianteThreadImpl;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOImovelFichaCompletaImpl;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOImovelImagemVideoImpl;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOImovelPlanoFaturaImpl;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOPlanoImpl;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOPublicidadeImovelImpl;
import br.com.jcv.backend.caracteristicas.CaracteristicaDTO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.contatoanunciante.ContatoAnuncianteServiceImpl;
import br.com.jcv.backend.datemanager.DateManager;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.distancia.Distancia;
import br.com.jcv.backend.distancia.DistanciaDTO;
import br.com.jcv.backend.dto.ClassificadorDTO;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.AvaliacaoAnuncioImovelDTO;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.GeoLocalizacaoDTO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelPlanoDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.IndicarAmigoDTO;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaServiceImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoServiceImpl;
import br.com.jcv.backend.imovel.plano.ImovelPlanoRelacaoDTO;
import br.com.jcv.backend.imovel.plano.ImovelPlanoServiceImpl;
import br.com.jcv.backend.imovel.publicidade.PublicidadeDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadDTO;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadServiceImpl;
import br.com.jcv.backend.interfaces.services.ContatoAnuncianteService;
import br.com.jcv.backend.interfaces.services.ContatoAnuncianteThreadService;
import br.com.jcv.backend.interfaces.services.ImovelImagemVideoService;
import br.com.jcv.backend.interfaces.services.ImovelPlanoFaturaService;
import br.com.jcv.backend.interfaces.services.ImovelPlanoService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.interfaces.services.LocalService;
import br.com.jcv.backend.interfaces.services.ReservaService;
import br.com.jcv.backend.local.ClassificacaoDTO;
import br.com.jcv.backend.local.LocalDTO;
import br.com.jcv.backend.local.LocalItemDTO;
import br.com.jcv.backend.local.LocalServicesImpl;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeDTO;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.reserva.ReservaDTO;
import br.com.jcv.backend.reserva.ReservaServiceImpl;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class FichaImovelRPCImpl extends RemoteServiceServlet implements
		FichaImovelRPC {
	
	/**
	 * <h2>logger</h2>
	 * <p>logger estático para log4j</p>
	 */
	private static final Logger logger = Logger.getLogger(FichaImovelRPCImpl.class.getName());


	private static final long serialVersionUID = -899700429989711705L;

	/* (non-Javadoc)
	 * @see br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPC#entrarEmContatoComAnunciante(br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO)
	 */
	@SuppressWarnings("unchecked")
	public ContatoClienteVO entrarEmContatoComAnunciante(ContatoClienteVO ccvo) throws AlugueRelaxeFrontException {
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		logger.info("Path base para imagens:" + root_fotos);
		ContatoClienteVO ccvoret = null;
		
		ContatoClienteDTO ccdto = new ContatoClienteDTO();
		ccdto.idImovel = ccvo.idImovel;
		ccdto.proposto = ccvo.nome;
		ccdto.email = ccvo.email;
		ccdto.ddd = ccvo.ddd;
		ccdto.telefone = ccvo.telefone;
		ccdto.cidade = ccvo.cidade;
		ccdto.uf = ccvo.uf;
		ccdto.pais = ccvo.pais;
		ccdto.chegadaPrevista = ccvo.chegadaPrevista;
		ccdto.partidaPrevista = ccvo.partidaPrevista;
		ccdto.qtdeAdultos = ccvo.qtdeAdultos;
		ccdto.qtdeCriancas = ccvo.qtdeCriancas;
		ccdto.informacoesAdicionais = ccvo.informacoesAdicionais;
		ccdto.modopublicidade = new ModoPublicidadeDTO();
		ccdto.modopublicidade.id = ccvo.modopublicidade.id;
		
		ImovelService is = new ImovelServiceImpl();
		try {
			ccdto = is.entrarEmContatoComAnunciante(ccdto);
			ccvoret = (new DTOContatoAnuncianteImpl(root_fotos)).getDataTransferObject(ccdto); 
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 		
		}
		
		return ccvoret;
	}

	/**
	 * <h2>pesquisarFichaCompletaImovel</h2>
	 * <p>Método de comunicação com o backend para recuperar a ficha completa
	 * do imóvel de acordo com seu id informado.</p>
	 * 
	 * @param long
	 * @return ImovelFichaCompletaVO
	 */
	@SuppressWarnings("unchecked")
	public ImovelFichaCompletaVO pesquisarFichaCompletaImovel(long codigoImovel) {

		ImovelFichaCompletaVO ifcvo = null;
		
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		logger.info("Path base para imagens:" + root_fotos);

		
		try {
			ImovelService is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(codigoImovel);
			
			//------------------------------------------------------------------
			// cria a ficha do imovel com base nas informações colhidas do
			// backend
			//------------------------------------------------------------------
			ifcvo = new ImovelFichaCompletaVO();
			obtemInformacoesImovel(ifcvo, ifcdto);
			obtemInformacoesImovelGeoLocalizacao(ifcvo, ifcdto);
			obtemInformacoesCliente(ifcvo, ifcdto);
			obtemImagensImovelTB(ifcvo, root_fotos, ifcdto);
			obtemImagensImovelMI(ifcvo, root_fotos, ifcdto);
			obtemImagensImovelXG(ifcvo, root_fotos, ifcdto);
			obtemVideoImovel(ifcvo, ifcdto);
			obtemCaracteristicaImovel(ifcvo, ifcdto);
			obtemCaracteristicaCondominio(ifcvo, ifcdto);
			obtemTabelaPrecoImovel(ifcvo, ifcdto);
			obtemPlanoFinanceiroImovel(ifcvo, ifcdto);
			ifcvo.indCentralReserva = ifcdto.indCentralReserva;
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
		}

		return ifcvo;
	}
	
	/**
	 * <h2>apresentarFichaCompletaImovel</h2>
	 * <p>Método de comunicação com o backend para recuperar a ficha completa
	 * do imóvel de acordo com seu id informado.</p>
	 * 
	 * @param long
	 * @return ImovelFichaCompletaVO
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public ImovelFichaCompletaVO apresentarFichaCompletaImovel(long codigoImovel) {

		ImovelFichaCompletaVO ifcvo = null;
		
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		logger.info("Path base para imagens:" + root_fotos);

		
		try {
			ImovelService is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO ifcdto = is.apresentarFichaCompletaImovel(codigoImovel);
			
			//------------------------------------------------------------------
			// cria a ficha do imovel com base nas informações colhidas do
			// backend
			//------------------------------------------------------------------
			ifcvo = new ImovelFichaCompletaVO();
			obtemInformacoesImovel(ifcvo, ifcdto);
			obtemInformacoesImovelGeoLocalizacao(ifcvo, ifcdto);
			obtemInformacoesCliente(ifcvo, ifcdto);
			obtemImagensImovelTB(ifcvo, root_fotos, ifcdto);
			obtemImagensImovelMI(ifcvo, root_fotos, ifcdto);
			obtemImagensImovelXG(ifcvo, root_fotos, ifcdto);
			obtemVideoImovel(ifcvo, ifcdto);
			obtemCaracteristicaImovel(ifcvo, ifcdto);
			obtemCaracteristicaCondominio(ifcvo, ifcdto);
			obtemTabelaPrecoImovel(ifcvo, ifcdto);
			obtemPlanoFinanceiroImovel(ifcvo, ifcdto);

			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
		}

		return ifcvo;
	}

	/**
	 * <h2>apresentarFichaCompletaImovel</h2>
	 * <p>Método de comunicação com o backend para recuperar a ficha completa
	 * do imóvel de acordo com seu id informado.</p>
	 * 
	 * @param long
	 * @return ImovelFichaCompletaVO
	 */
	@SuppressWarnings("unchecked")
	public ImovelFichaCompletaVO apresentarFichaCompletaImovel(long codigoImovel, String origemAcesso) {

		ImovelFichaCompletaVO ifcvo = null;
		
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		logger.info("Path base para imagens:" + root_fotos);

		
		try {
			ImovelService is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO ifcdto = is.apresentarFichaCompletaImovel(codigoImovel,origemAcesso);
			ifcvo = (new DTOImovelFichaCompletaImpl(root_fotos)).getDataTransferObject(ifcdto);
			
			//------------------------------------------------------------------
			// cria a ficha do imovel com base nas informações colhidas do
			// backend
			//------------------------------------------------------------------
			
			obtemInformacoesImovel(ifcvo, ifcdto);
			obtemInformacoesImovelGeoLocalizacao(ifcvo, ifcdto);
			//obtemInformacoesCliente(ifcvo, ifcdto);
			obtemImagensImovelTB(ifcvo, root_fotos, ifcdto);
			obtemImagensImovelMI(ifcvo, root_fotos, ifcdto);
			obtemImagensImovelXG(ifcvo, root_fotos, ifcdto);
			obtemVideoImovel(ifcvo, ifcdto);
			obtemCaracteristicaImovel(ifcvo, ifcdto);
			obtemCaracteristicaCondominio(ifcvo, ifcdto);
			obtemTabelaPrecoImovel(ifcvo, ifcdto);
			obtemPlanoFinanceiroImovel(ifcvo, ifcdto);
			ifcvo.indCentralReserva = ifcdto.indCentralReserva;
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
		}

		return ifcvo;
	}
	
	
	private void obtemPlanoFinanceiroImovel(ImovelFichaCompletaVO ifcvo,
			ImovelFichaCompletaDTO ifcdto) {
		ifcvo.imovelPlano = new ImovelPlanoVO();
		ifcvo.imovelPlano.plano = new PlanoVO();
		ifcvo.imovelPlano.id = ifcdto.imovelPlano.id;
		ifcvo.imovelPlano.plano.id = ifcdto.imovelPlano.plano.id;
		ifcvo.imovelPlano.plano.descricao = ifcdto.imovelPlano.plano.descricao;
		ifcvo.imovelPlano.plano.nome = ifcdto.imovelPlano.plano.nome;
		ifcvo.imovelPlano.plano.indicadorStatus = ifcdto.imovelPlano.plano.indicadorStatus;
		ifcvo.imovelPlano.plano.indicadorPeriodicidade = ifcdto.imovelPlano.plano.indicadorPeriodicidade;
		ifcvo.imovelPlano.plano.valor = ifcdto.imovelPlano.plano.valor;
		ifcvo.imovelPlano.plano.limiteFotos = ifcdto.imovelPlano.plano.limiteFotos;
	}

	private void obtemTabelaPrecoImovel(ImovelFichaCompletaVO ifcvo,
			ImovelFichaCompletaDTO ifcdto) {
		if (ifcdto.tabelaPreco != null) {
			ifcvo.tabelaPreco = new ArrayList<TabelaPrecoVO>();
			
			// Adiciona tarifa basica no primeiro elemento
			if (ifcdto.imovel.valorTarifaBasica > 0){
				TabelaPrecoVO iivvo = new TabelaPrecoVO();
				iivvo.periodo = "DIARIA BASICA";
				iivvo.valorTabela = ifcdto.imovel.valorTarifaBasica;
				ifcvo.tabelaPreco.add(iivvo);
			}
			
			for (int i = 1; i < ifcdto.tabelaPreco.size(); i++){
				TabelaPrecoDTO iivdto = (TabelaPrecoDTO) ifcdto.tabelaPreco.get(i); 
				TabelaPrecoVO iivvo = new TabelaPrecoVO();
				iivvo.id = iivdto.id;
				iivvo.dataFim = iivdto.dataFim;
				iivvo.dataInicio = iivdto.dataInicio;
				iivvo.observacao = iivdto.observacao;
				iivvo.minimoDe = iivdto.textoMinimoDe;
				iivvo.periodo = iivdto.textoPeriodo;
				iivvo.valorTabela = iivdto.valorTabela;
				ifcvo.tabelaPreco.add(iivvo);
				try {
					iivvo.dataInicioStr = DateManager.getDateManagerInstance(iivdto.dataInicio).getDateCustom("dd/MM/yyyy");
					iivvo.dataFimStr = DateManager.getDateManagerInstance(iivdto.dataFim).getDateCustom("dd/MM/yyyy");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} else {
			ifcvo.tabelaPreco = new ArrayList<TabelaPrecoVO>();
			// Adiciona tarifa basica no primeiro elemento
			if (ifcdto.imovel.valorTarifaBasica > 0){
				TabelaPrecoVO iivvo = new TabelaPrecoVO();
				iivvo.periodo = "DIARIA BASICA";
				iivvo.valorTabela = ifcdto.imovel.valorTarifaBasica;
				ifcvo.tabelaPreco.add(iivvo);
			}
		}
	}
	private void obtemCaracteristicaCondominio(ImovelFichaCompletaVO ifcvo,
			ImovelFichaCompletaDTO ifcdto) {
		if (ifcdto.caracteristicaCondominio != null) {
			ifcvo.caracteristicaCondominio = new ArrayList<ImovelCaracteristicaVO>();
			for (int i = 0; i < ifcdto.caracteristicaCondominio.size(); i++){
				ImovelCaracteristicaDTO iivdto = (ImovelCaracteristicaDTO) ifcdto.caracteristicaCondominio.get(i); 
				ImovelCaracteristicaVO iivvo = new ImovelCaracteristicaVO();
				iivvo.id = iivdto.id;
				iivvo.caracteristica = new CaracteristicaVO();
				iivvo.caracteristica.id = iivdto.caracteristica.id;
				iivvo.caracteristica.nome = iivdto.caracteristica.nome;
				iivvo.caracteristica.descricaoAnuncio = iivdto.caracteristica.descricaoAnuncio;
				ifcvo.caracteristicaCondominio.add(iivvo);
			}
		}
	}
	private void obtemCaracteristicaImovel(ImovelFichaCompletaVO ifcvo,
			ImovelFichaCompletaDTO ifcdto) {
		if (ifcdto.caracteristicaImovel != null) {
			ifcvo.caracteristicaImovel = new ArrayList<ImovelCaracteristicaVO>();
			for (int i = 0; i < ifcdto.caracteristicaImovel.size(); i++){
				ImovelCaracteristicaDTO iivdto = (ImovelCaracteristicaDTO) ifcdto.caracteristicaImovel.get(i); 
				ImovelCaracteristicaVO iivvo = new ImovelCaracteristicaVO();
				iivvo.id = iivdto.id;
				iivvo.caracteristica = new CaracteristicaVO();
				iivvo.caracteristica.id = iivdto.caracteristica.id;
				iivvo.caracteristica.nome = iivdto.caracteristica.nome;
				iivvo.caracteristica.descricaoAnuncio = iivdto.caracteristica.descricaoAnuncio;
				ifcvo.caracteristicaImovel.add(iivvo);
			}
		}
	}
	private void obtemImagensImovelXG(ImovelFichaCompletaVO ifcvo,
			String root_fotos, ImovelFichaCompletaDTO ifcdto) {
		if (ifcdto.imagensImovelXG != null) {
			ifcvo.imagensImovelXG = new ArrayList<ImovelImagemVideoVO>();
			for (int i = 0; i < ifcdto.imagensImovelXG.size(); i++){
				ImovelImagemVideoDTO iivdto = (ImovelImagemVideoDTO) ifcdto.imagensImovelXG.get(i); 
				ImovelImagemVideoVO iivvo = new ImovelImagemVideoVO();
				iivvo.id = iivdto.id;
				iivvo.nome = root_fotos.concat(String.valueOf(ifcvo.cliente.id)).concat("/").concat(String.valueOf(ifcvo.imovel.id)).concat("/").concat(iivdto.nome);
				iivvo.tipo = iivdto.tipo;
				ifcvo.imagensImovelXG.add(iivvo);
			}
		}
	}
	private void obtemImagensImovelMI(ImovelFichaCompletaVO ifcvo,
			String root_fotos, ImovelFichaCompletaDTO ifcdto) {
		if (ifcdto.imagensImovelMI != null) {
			ifcvo.imagensImovelMI = new ArrayList<ImovelImagemVideoVO>();
			for (int i = 0; i < ifcdto.imagensImovelMI.size(); i++){
				ImovelImagemVideoDTO iivdto = (ImovelImagemVideoDTO) ifcdto.imagensImovelMI.get(i); 
				ImovelImagemVideoVO iivvo = new ImovelImagemVideoVO();
				iivvo.id = iivdto.id;
				iivvo.nome = root_fotos.concat(String.valueOf(ifcvo.cliente.id)).concat("/").concat(String.valueOf(ifcvo.imovel.id)).concat("/").concat(iivdto.nome);
				iivvo.tipo = iivdto.tipo;
				ifcvo.imagensImovelMI.add(iivvo);
			}
		}
	}
	private void obtemImagensImovelTB(ImovelFichaCompletaVO ifcvo,
			String root_fotos, ImovelFichaCompletaDTO ifcdto) {
		if (ifcdto.imagensImovelTB != null) {
			ifcvo.imagensImovelTB = new ArrayList<ImovelImagemVideoVO>();
			for (int i = 0; i < ifcdto.imagensImovelTB.size(); i++){
				ImovelImagemVideoDTO iivdto = (ImovelImagemVideoDTO) ifcdto.imagensImovelTB.get(i); 
				ImovelImagemVideoVO iivvo = new ImovelImagemVideoVO();
				iivvo.id = iivdto.id;
				iivvo.nome = root_fotos.concat(String.valueOf(ifcvo.cliente.id)).concat("/").concat(String.valueOf(ifcvo.imovel.id)).concat("/").concat(iivdto.nome);
				iivvo.tipo = iivdto.tipo;
				ifcvo.imagensImovelTB.add(iivvo);
			}
		}
	}
	private void obtemInformacoesCliente(ImovelFichaCompletaVO ifcvo, 
			ImovelFichaCompletaDTO ifcdto) {
		ifcvo.cliente = new ClienteVO();
		ifcvo.cliente.id = ifcdto.cliente.id;
		ifcvo.cliente.nome = ifcdto.cliente.nome;
		ifcvo.cliente.primeiroNome = ifcdto.cliente.primeiroNome;
		ifcvo.cliente.cpf = ifcdto.cliente.cpf;
		ifcvo.cliente.cgc = ifcdto.cliente.cgc;
		ifcvo.cliente.dataNascimento = ifcdto.cliente.dataNascimento;
		ifcvo.cliente.email = ifcdto.cliente.email;
		ifcvo.cliente.dataCadastro = ifcdto.cliente.dataCadastro;
		ifcvo.cliente.membroDesde = DateManagerBase.getDateManagerInstance(ifcdto.cliente.dataCadastro).getMonth() +
									"/" +
									DateManagerBase.getDateManagerInstance(ifcdto.cliente.dataCadastro).getYear();
		ifcvo.cliente.dataAtualizacao = ifcdto.cliente.dataAtualizacao;
		ifcvo.cliente.indicadorStatus = ifcdto.cliente.indicadorStatus;
		ifcvo.cliente.urlwww = ifcdto.cliente.urlwww;
		ifcvo.cliente.indicadorAutorizaNotificacao = ifcdto.cliente.indicadorAutorizaNotificacao;
		ifcvo.cliente.msn = ifcdto.cliente.msn;
		ifcvo.cliente.skype = ifcdto.cliente.skype;
		ifcvo.cliente.googleTalk = ifcdto.cliente.googleTalk;
		ifcvo.cliente.indicadorTipoAnunciante = ifcdto.cliente.indicadorTipoAnunciante;
		ifcvo.cliente.fotoPerfil = ifcdto.cliente.fotoPerfil;
		ifcvo.cliente.fotoChat = ifcdto.cliente.fotoChat;
		ifcvo.cliente.endereco = new EnderecoVO();
		ifcvo.cliente.endereco.nome = ifcdto.cliente.endereco.nome;
		ifcvo.cliente.endereco.numero = ifcdto.cliente.endereco.numero;
		ifcvo.cliente.endereco.complemento = ifcdto.cliente.endereco.complemento;
		ifcvo.cliente.endereco.bairro = ifcdto.cliente.endereco.bairro;
		ifcvo.cliente.endereco.cep = ifcdto.cliente.endereco.cep;
		if (ifcdto.cliente.telefones != null){
			if (ifcdto.cliente.telefones.size() > 0) {
				ifcvo.cliente.telefones = new ArrayList<TelefoneVO>();
				for (TelefoneDTO telefonedto : ifcdto.cliente.telefones) {
					TelefoneVO telefonevo = new TelefoneVO();
					telefonevo.nomeContato = telefonedto.nomeContato;
					telefonevo.ddd = telefonedto.ddd;
					telefonevo.telefone = telefonedto.telefone;
					telefonevo.indPermiteExibir = telefonedto.indPermiteExibir;
					telefonevo.indTipoTelefone = telefonedto.indTipoTelefone;
					ifcvo.cliente.telefones.add(telefonevo);
				}
			}
		}
	}
	private void obtemInformacoesImovelGeoLocalizacao(ImovelFichaCompletaVO ifcvo,
			ImovelFichaCompletaDTO ifcdto) {
		ifcvo.geolocalizacao = new GeoLocalizacaoVO();
		ifcvo.geolocalizacao.latitude = ifcdto.geolocalizacao.latitude;
		ifcvo.geolocalizacao.longitude = ifcdto.geolocalizacao.longitude;
	}
	private void obtemVideoImovel(ImovelFichaCompletaVO ifcvo,
			ImovelFichaCompletaDTO ifcdto) {
		if (ifcdto.videoImovel != null) {
			ifcvo.videoImovel = new ImovelImagemVideoVO();
			ifcvo.videoImovel.id = ifcdto.videoImovel.id;
			ifcvo.videoImovel.nome = ifcdto.videoImovel.nome;
			ifcvo.videoImovel.tipo = ifcdto.videoImovel.tipo;
		}
	}

	private void obtemInformacoesImovel(ImovelFichaCompletaVO ifcvo,
			ImovelFichaCompletaDTO ifcdto) {
		ifcvo.imovel = new ImovelVO();
		ifcvo.imovel.id = ifcdto.imovel.id;
		ifcvo.imovel.idCliente = ifcdto.imovel.idCliente;
		ifcvo.imovel.qtdeQuartos = ifcdto.imovel.qtdeQuartos;
		ifcvo.imovel.qtdeSuites = ifcdto.imovel.qtdeSuites;
		ifcvo.imovel.qtdeBanheiros = ifcdto.imovel.qtdeBanheiros;
		ifcvo.imovel.qtdeCapacidade = ifcdto.imovel.qtdeCapacidade;
		ifcvo.imovel.descricaoGeral = ifcdto.imovel.descricaoGeral;
		ifcvo.imovel.descricaoQuartos = ifcdto.imovel.descricaoQuartos;
		ifcvo.imovel.descricaoArredores = ifcdto.imovel.descricaoArredores;
		ifcvo.imovel.observacoes = ifcdto.imovel.observacoes;
		ifcvo.imovel.indicadorTipoPropriedade = ifcdto.imovel.indicadorTipoPropriedade;
		ifcvo.imovel.qtdeVisitas = ifcdto.imovel.qtdeVisitas;
		ifcvo.imovel.endereco = new EnderecoVO();
		ifcvo.imovel.endereco.logradouro = StringUtil.splitEndereco(ifcdto.imovel.endereco.nome,StringUtil.TIPO_RETORNO_LOGRADOURO) ;
		ifcvo.imovel.endereco.nome = StringUtil.splitEndereco(ifcdto.imovel.endereco.nome,StringUtil.TIPO_RETORNO_ENDERECO);
		ifcvo.imovel.endereco.numero = ifcdto.imovel.endereco.numero;
		ifcvo.imovel.endereco.complemento = ifcdto.imovel.endereco.complemento;
		ifcvo.imovel.endereco.bairro = ifcdto.imovel.endereco.bairro;
		ifcvo.imovel.endereco.cep = ifcdto.imovel.endereco.cep;
		ifcvo.imovel.endereco.codigoUfCidadeItem = ifcdto.imovel.endereco.codigoUfCidadeItem;
		ifcvo.imovel.endereco.cidade = ifcdto.imovel.endereco.cidade;
		ifcvo.imovel.endereco.uf = ifcdto.imovel.endereco.uf;
		ifcvo.imovel.dataCadastro = ifcdto.imovel.dataCadastro;
		ifcvo.imovel.dataAtualizacao = ifcdto.imovel.dataAtualizacao;
		ifcvo.imovel.descricaoTituloAnuncio = ifcdto.imovel.descricaoTituloAnuncio;
		ifcvo.imovel.indicadorStatus = ifcdto.imovel.indicadorStatus;
		ifcvo.imovel.indicadorMostraTabelaPreco = ifcdto.imovel.indicadorMostraTabelaPreco;
		ifcvo.imovel.dataUltimaVisita = ifcdto.imovel.dataUltimaVisita;
		ifcvo.imovel.indicadorPermiteAlugarFestas = ifcdto.imovel.indicadorPermiteAlugarFestas;
		ifcvo.imovel.valorTarifaBasica = String.valueOf(ifcdto.imovel.valorTarifaBasica);
	}
	
	@SuppressWarnings("unchecked")
	public ImovelFichaCompletaVO adicionarImovelCarteira(ImovelFichaCompletaVO ifcvo)
			throws AlugueRelaxeFrontException {
		ImovelFichaCompletaVO ifcvoret = null;
		
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		logger.info("Path base para imagens:" + root_fotos);

		
		try {
			ImovelFichaCompletaDTO ifcdto = getDTO(ifcvo);
			ImovelService is = new ImovelServiceImpl();
			ifcdto = is.adicionarImovelCarteira(ifcdto);
			
			ifcvoret = (new DTOImovelFichaCompletaImpl(root_fotos)).getDataTransferObject(ifcdto);
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return ifcvoret;
	}

	private ImovelFichaCompletaDTO getDTO(ImovelFichaCompletaVO ifcvo) {
		ImovelFichaCompletaDTO dto = new ImovelFichaCompletaDTO();
		
		dto.imovel = new ImovelDTO();
		dto.imovel.id = ifcvo.imovel.id;
		dto.imovel.idCliente = ifcvo.imovel.idCliente;
		dto.imovel.qtdeQuartos = ifcvo.imovel.qtdeQuartos;
		dto.imovel.qtdeSuites = ifcvo.imovel.qtdeSuites;
		dto.imovel.qtdeBanheiros = ifcvo.imovel.qtdeBanheiros;
		dto.imovel.qtdeCapacidade = ifcvo.imovel.qtdeCapacidade;
		dto.imovel.descricaoGeral = ifcvo.imovel.descricaoGeral;
		dto.imovel.descricaoQuartos = ifcvo.imovel.descricaoQuartos;
		dto.imovel.descricaoArredores = ifcvo.imovel.descricaoArredores;
		dto.imovel.observacoes = ifcvo.imovel.observacoes;
		dto.imovel.indicadorTipoPropriedade = ifcvo.imovel.indicadorTipoPropriedade;
		dto.imovel.qtdeVisitas = ifcvo.imovel.qtdeVisitas;
		dto.imovel.endereco = new EnderecoDTO();
		dto.imovel.endereco.nome = ifcvo.imovel.endereco.logradouro + " " + ifcvo.imovel.endereco.nome;
		dto.imovel.endereco.numero = ifcvo.imovel.endereco.numero;
		dto.imovel.endereco.complemento = ifcvo.imovel.endereco.complemento;
		dto.imovel.endereco.bairro = ifcvo.imovel.endereco.bairro;
		dto.imovel.endereco.cep = ifcvo.imovel.endereco.cep;
		dto.imovel.endereco.codigoUfCidadeItem = ifcvo.imovel.endereco.codigoUfCidadeItem;
		dto.imovel.endereco.cidade = ifcvo.imovel.endereco.cidade;
		dto.imovel.endereco.uf = ifcvo.imovel.endereco.uf;
		dto.imovel.descricaoTituloAnuncio = ifcvo.imovel.descricaoTituloAnuncio;
		dto.imovel.indicadorStatus = ifcvo.imovel.indicadorStatus;
		dto.imovel.indicadorMostraTabelaPreco = ifcvo.imovel.indicadorMostraTabelaPreco;
		dto.imovel.indicadorCondominio = ifcvo.imovel.indicadorCondominio;
		dto.imovel.dataUltimaVisita = ifcvo.imovel.dataUltimaVisita;
		dto.imovel.indicadorPermiteAlugarFestas = ifcvo.imovel.indicadorPermiteAlugarFestas;
		try {
			dto.imovel.valorTarifaBasica = Integer.valueOf(ifcvo.imovel.valorTarifaBasica);
		} catch (NumberFormatException nfe) {
			dto.imovel.valorTarifaBasica = 0;
		}
		if (ifcvo.geolocalizacao != null){
			dto.geolocalizacao = new GeoLocalizacaoDTO();
			dto.geolocalizacao.latitude = ifcvo.geolocalizacao.latitude;
			dto.geolocalizacao.longitude = ifcvo.geolocalizacao.longitude;
		}
		
		//----------------------------------------------
		// Informacoes sobre o plano financeiro
		//----------------------------------------------
		if(ifcvo.imovelPlano != null){
			dto.imovelPlano = new ImovelPlanoDTO();
			dto.imovelPlano.plano = new PlanoDTO();
			dto.imovelPlano.plano.id = ifcvo.imovelPlano.plano.id;
		}
		
		//----------------------------------------------
		// Monta os dados de caracteristicas de condominio
		//----------------------------------------------
		if ((ifcvo.caracteristicaCondominio != null) && 
			(ifcvo.caracteristicaCondominio.size() > 0)) {
			dto.caracteristicaCondominio = new ArrayList<ImovelCaracteristicaDTO>();
			for (ImovelCaracteristicaVO vo : ifcvo.caracteristicaCondominio) {
				ImovelCaracteristicaDTO dtoc = new ImovelCaracteristicaDTO();
				dtoc.id = vo.id;
				dtoc.indicadorCondominio = vo.indicadorCondominio;
				dtoc.caracteristica = new CaracteristicaDTO();
				dtoc.caracteristica.id = vo.caracteristica.id;
				dtoc.caracteristica.nome = vo.caracteristica.nome;
				dto.caracteristicaCondominio.add(dtoc);
			}
		}
		
		//----------------------------------------------
		// Monta os dados de caracteristicas de imovel
		//----------------------------------------------
		if ((ifcvo.caracteristicaImovel != null) && 
			(ifcvo.caracteristicaImovel.size() > 0)) {
			dto.caracteristicaImovel = new ArrayList<ImovelCaracteristicaDTO>();
			for (ImovelCaracteristicaVO vo : ifcvo.caracteristicaImovel) {
				ImovelCaracteristicaDTO dtoc = new ImovelCaracteristicaDTO();
				dtoc.id = vo.id;
				dtoc.indicadorCondominio = vo.indicadorCondominio;
				dtoc.caracteristica = new CaracteristicaDTO();
				dtoc.caracteristica.id = vo.caracteristica.id;
				dtoc.caracteristica.nome = vo.caracteristica.nome;
				dto.caracteristicaImovel.add(dtoc);
			}
		}
		//----------------------------------------------
		// monta os dados de tabela de preco
		//----------------------------------------------
		if ((ifcvo.tabelaPreco != null) && 
			(ifcvo.tabelaPreco.size() > 0)) {
				dto.tabelaPreco = new ArrayList<TabelaPrecoDTO>();
				for (TabelaPrecoVO vo : ifcvo.tabelaPreco) {
					TabelaPrecoDTO dtoc = new TabelaPrecoDTO();
					dtoc.id = vo.id;
					dtoc.dataFim = vo.dataFim;
					dtoc.dataInicio = vo.dataInicio;
					dtoc.observacao = vo.observacao;
					dtoc.textoMinimoDe = vo.minimoDe;
					dtoc.textoPeriodo = vo.periodo;
					dtoc.valorTabela = vo.valorTabela;
					dto.tabelaPreco.add(dtoc);
				}
			}
		//----------------------------------------------
		// monta os dados de Imagens para exclusão
		//----------------------------------------------
		if ((ifcvo.imagensImovelMI != null) && 
			(ifcvo.imagensImovelMI.size() > 0)) {
				dto.imagensImovelMI = new ArrayList<ImovelImagemVideoDTO>();
				for (ImovelImagemVideoVO vo : ifcvo.imagensImovelMI) {
					ImovelImagemVideoDTO dtoc = new ImovelImagemVideoDTO();
					dtoc.id = vo.id;
					dtoc.nome = vo.nome;
					dtoc.tipo = vo.tipo;
					dto.imagensImovelMI.add(dtoc);
				}
			}

		return dto;
	}

	@SuppressWarnings("unchecked")
	public ImovelFichaCompletaVO atualizarImovelCarteira(ImovelFichaCompletaVO ifcvo)
			throws AlugueRelaxeFrontException {
		ImovelFichaCompletaVO voretorno = null;
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		logger.info("Path base para imagens:" + root_fotos);

		try {
			ImovelFichaCompletaDTO ifcdto = getDTO(ifcvo);
			ImovelService is = new ImovelServiceImpl();
			ifcdto = is.atualizarImovelCarteira(ifcdto);
			
			voretorno = (new DTOImovelFichaCompletaImpl(root_fotos)).getDataTransferObject(ifcdto);
			
			voretorno.msgcode = ifcdto.msgcode;
			voretorno.msgcodeString = ifcdto.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;
	}

	public VOPadrao atualizarGeoLocalizacaoImovel(ImovelFichaCompletaVO ifcvo)
			throws AlugueRelaxeFrontException {
		VOPadrao voretorno = null;
		
		try {
			ImovelFichaCompletaDTO ifcdto = getDTO(ifcvo);
			ImovelService is = new ImovelServiceImpl();
			DTOPadrao dtoretorno = is.atualizarGeoLocalizacaoImovel(ifcdto);
			
			voretorno = new VOPadrao();
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;
	}

	public VOPadrao notificacaoGaleriaFotoVazia(ImovelFichaCompletaVO ifcvo)
			throws AlugueRelaxeFrontException {
		VOPadrao voretorno = null;
		
		try {
			ImovelFichaCompletaDTO ifcdto = getDTO(ifcvo);
			ImovelService is = new ImovelServiceImpl();
			DTOPadrao dtoretorno = is.emitirNotificacaoGaleriaFotoVazia(ifcdto);
			
			voretorno = new VOPadrao();
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;
	}

	public VOPadrao indicarImovelAmigo(ImovelFichaCompletaVO ifcvo,
			IndicarAmigoVO iavo) throws AlugueRelaxeFrontException {
		VOPadrao voretorno = null;
		
		try {
			ImovelFichaCompletaDTO ifcdto = getDTO(ifcvo);
			IndicarAmigoDTO iadto = getIndicarAmigoDTO(iavo);
			ImovelService is = new ImovelServiceImpl();
			DTOPadrao dtoretorno = is.enviarIndicacaoImovelAmigo(ifcdto, iadto);
			
			voretorno = new VOPadrao();
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;
	}

	private IndicarAmigoDTO getIndicarAmigoDTO(IndicarAmigoVO iavo) {
		IndicarAmigoDTO iadto = new IndicarAmigoDTO();
		iadto.emailamigo = iavo.emailamigo;
		iadto.nomeamigo = iavo.nomeamigo;
		iadto.seuemail = iavo.seuemail;
		iadto.seunome = iavo.seunome;
		iadto.mensagem = iavo.mensagem;

		return iadto;
	}

	public VOPadrao enviarImagensLixeira(long idImovel,
			List<ImovelImagemVideoVO> iivvo) throws AlugueRelaxeFrontException {
		
		VOPadrao voretorno = null;
		try {
			List<ImovelImagemVideoDTO> lst = getImovelImagemVideoDTO(iivvo);
			ImovelService is = new ImovelServiceImpl();
			DTOPadrao dtoretorno = is.enviarImagensLixeira(idImovel, lst); 
			
			voretorno = new VOPadrao();
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;
	}

	private List<ImovelImagemVideoDTO> getImovelImagemVideoDTO(List<ImovelImagemVideoVO> iivvo) {
		List<ImovelImagemVideoDTO> lst = null;
		//----------------------------------------------
		// monta os dados de Imagens para exclusão
		//----------------------------------------------
		if ((iivvo != null) && 
			(iivvo.size() > 0)) {
			lst = new ArrayList<ImovelImagemVideoDTO>();
			for (ImovelImagemVideoVO vo : iivvo) {
				ImovelImagemVideoDTO dtoc = new ImovelImagemVideoDTO();
				dtoc.id = vo.id;
				dtoc.nome = vo.nome;
				dtoc.tipo = vo.tipo;
				lst.add(dtoc);
			}
		}
		
		return lst;
	
		
	}

	public VOPadrao atualizarTarifaImovel(ImovelFichaCompletaVO ifcvo)
			throws AlugueRelaxeFrontException {
		VOPadrao voretorno = null;
		
		try {
			ImovelFichaCompletaDTO ifcdto = getDTO(ifcvo);
			ImovelService is = new ImovelServiceImpl();
			DTOPadrao dtoretorno = is.atualizarTarifaImovel(ifcdto);
			
			voretorno = new VOPadrao();
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;	
	}

	public CharterVO charterDistribuicaoVisitasImovel(
			ImovelFichaCompletaVO ifcvo, int ano)
			throws AlugueRelaxeFrontException {
		CharterVO cht = null;
		try {
			ImovelFichaCompletaDTO ifcdto = this.getDTO(ifcvo);
			cht = new CharterVO();
			ImovelService ufs = new ImovelServiceImpl();
			cht.url = ufs.charterDistribuicaoVisitasImovel(ifcdto, ano);
			//cht.url = "https://chart.googleapis.com/chart?cht=p3&chd=t:10,30,40,20&chs=350x100&chl=Preventiva(10%)|Corretiva(30%)|Preditiva(40%)|Outros(20%)";

			
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
		
		return cht;

	}

	public VOPadrao avaliarAnuncio(AvaliacaoAnuncioImovelVO avaliacao)
			throws AlugueRelaxeFrontException {
		VOPadrao voretorno = null;
		
		if (avaliacao == null) {
			throw new AlugueRelaxeFrontException("É necessário realizar a avaliação antes de enviar.");
		}
		if ((avaliacao.classFotografia == null) || 
				(avaliacao.classInformacaoRelevante == null) || 
				(avaliacao.classQualidadeTexto == null)) {
			throw new AlugueRelaxeFrontException("É necessário realizar as 3 avaliações antes de enviar.");
		}
		try {
			AvaliacaoAnuncioImovelDTO ifcdto = getDTO(avaliacao);
			ImovelService is = new ImovelServiceImpl();
			DTOPadrao dtoretorno = is.avaliarAnuncio(ifcdto);
			
			voretorno = new VOPadrao();
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;	
	}
	
	private AvaliacaoAnuncioImovelDTO getDTO(AvaliacaoAnuncioImovelVO vo) {
		
		AvaliacaoAnuncioImovelDTO dto = null;
		if (vo != null) {
			dto = new AvaliacaoAnuncioImovelDTO();
			dto.idImovelAvaliado = vo.idImovelAvaliado;
			if (vo.classFotografia != null){
				dto.classFotografia = new ClassificadorDTO(vo.classFotografia.notaClassificador, 
															vo.classFotografia.titulo, 
															vo.classFotografia.comentarioClassificador);
			}
			if (vo.classInformacaoRelevante != null){
				dto.classInformacaoRelevante = new ClassificadorDTO(vo.classInformacaoRelevante.notaClassificador, 
															vo.classInformacaoRelevante.titulo, 
															vo.classInformacaoRelevante.comentarioClassificador);
			}
			if (vo.classQualidadeTexto != null){
				dto.classQualidadeTexto = new ClassificadorDTO(vo.classQualidadeTexto.notaClassificador, 
															vo.classQualidadeTexto.titulo, 
															vo.classQualidadeTexto.comentarioClassificador);
			}
		}
		return dto;
	}

	public ImovelPlanoFaturaVO pesquisarUltimaFatura(long codigoImovel,
			String tipo) throws AlugueRelaxeFrontException {

		ImovelPlanoFaturaVO voretorno = null;
		try {
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> is = new ImovelPlanoFaturaServiceImpl();
			ImovelPlanoFaturaDTO dtoretorno = is.pesquisarUltimaFatura(codigoImovel, tipo); 
			voretorno = getImovelPlanoFaturaVO(dtoretorno);
			
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;
	}

	public ImovelPlanoFaturaVO pesquisarFatura(long idFatura) throws AlugueRelaxeFrontException {

		ImovelPlanoFaturaVO voretorno = null;
		try {
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> is = new ImovelPlanoFaturaServiceImpl();
			ImovelPlanoFaturaDTO dtoretorno = is.pesquisarFatura(idFatura); 
			voretorno = getImovelPlanoFaturaVO(dtoretorno);
			
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;
	}

	public ImovelPlanoFaturaVO liquidarFatura(long idFatura, String tipo) throws AlugueRelaxeFrontException {

		ImovelPlanoFaturaVO voretorno = null;
		try {
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> is = new ImovelPlanoFaturaServiceImpl();
			ImovelPlanoFaturaDTO dtoretorno = null;
			if (tipo.equals("N")){
				dtoretorno = is.registrarPagtoPlano(idFatura); 
			} else {
				dtoretorno = is.registrarPagtoPublicidade(idFatura); 
			}
			voretorno = getImovelPlanoFaturaVO(dtoretorno);
			
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;
	}
	

	public VOPadrao inativarAnuncio(long idImovel)  throws AlugueRelaxeFrontException {

		VOPadrao voretorno = null;
		try {
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			DTOPadrao dtoretorno = null;
			dtoretorno = is.inativarAnuncio(idImovel); 
			voretorno = new VOPadrao();
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;
	}
	
	
	private ImovelPlanoFaturaVO getImovelPlanoFaturaVO(ImovelPlanoFaturaDTO dto) {
		ImovelPlanoFaturaVO vo = new ImovelPlanoFaturaVO();
		vo.id = dto.id;
		vo.idImovelPlano = dto.idImovelPlano;
		vo.plano = new PlanoVO();
		vo.plano.id = dto.plano.id;
		vo.plano.nome = dto.plano.nome;
		vo.plano.descricao = dto.plano.descricao;
		vo.plano.htmlBtnPagseguro = dto.plano.htmlBtnPagseguro;
		vo.plano.htmlBtnPayPal = dto.plano.htmlBtnPayPal;
		vo.indicadorStatus = dto.indicadorStatus;
		vo.valorFatura = dto.valorFatura;
		vo.valorDesconto = dto.valorDesconto;
		vo.dataVencimento = dto.dataVencimento;
		vo.dataPagamento = dto.dataPagamento;
		vo.dataCadastro = dto.dataCadastro;
		vo.valorFinalStr = StringUtil.valorCorreto(vo.valorFatura - vo.valorDesconto);

		DateManagerBase dv = DateManagerBase.getDateManagerInstance(vo.dataVencimento);
		try {
			vo.dataVencimentoStr = dv.getDateCustom("dd/MM/yyyy");
		} catch (ParseException e) {
			vo.dataVencimentoStr = "**/**/****";
		}
		
		if (vo.dataPagamento != null){
			DateManagerBase dp = DateManagerBase.getDateManagerInstance(vo.dataPagamento);
			try {
				vo.dataPagamentoStr = dp.getDateCustom("dd/MM/yyyy HH:mm:ss");
			} catch (ParseException e) {
				vo.dataPagamentoStr = "**/**/****";
			}
		}
		
		DateManagerBase dc = DateManagerBase.getDateManagerInstance(vo.dataCadastro);
		try {
			vo.dataCadastroStr = dc.getDateCustom("dd/MM/yyyy HH:mm:ss");
		} catch (ParseException e) {
			vo.dataCadastroStr = "**/**/****";
		}
		
		DateManagerBase datahoje = DateManagerBase.getDateManagerInstance();
		
		if (vo.indicadorStatus.equals("P")){
			vo.indicadorStatusStr = "PENDENTE";
		} else if (vo.indicadorStatus.equals("C")){
			vo.indicadorStatusStr = "CANCELADO";
		} else if (vo.indicadorStatus.equals("L") && (vo.dataPagamento != null)){
			vo.indicadorStatusStr = "LIBERADO";
		} else if (vo.indicadorStatus.equals("V")){
			vo.indicadorStatusStr = "VENCIDO";
		}
		return vo;
	}

	public List<LocalVO> listarLocal() throws AlugueRelaxeFrontException {
		List<LocalVO> lstvo = null;
		try {
			LocalService<?> is = new LocalServicesImpl();
			List<LocalDTO> lstdto = is.listarLocal(); 
			if (lstdto != null) {
				lstvo = new ArrayList<LocalVO>();
				for (LocalDTO dto : lstdto) {
					LocalVO vo = getLocalVO(dto);
					lstvo.add(vo);
					
				}
			}
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return lstvo;
	}
	
	private LocalVO getLocalVO(LocalDTO dto) {
		LocalVO vo = new LocalVO();
		vo.id = dto.id;
		vo.descricao = dto.descricao;
		vo.urlIcone = dto.urlIcone;
		return vo;
	}

	public List<ClassificacaoVO> listarClassificacaoLocal()
			throws AlugueRelaxeFrontException {
		List<ClassificacaoVO> lstvo = null;
		try {
			LocalService<?> is = new LocalServicesImpl();
			List<ClassificacaoDTO> lstdto = is.listarClassificacaoLocal(); 
			if (lstdto != null) {
				lstvo = new ArrayList<ClassificacaoVO>();
				for (ClassificacaoDTO dto : lstdto) {
					ClassificacaoVO vo = getClassificacaoVO(dto);
					lstvo.add(vo);
					
				}
			}
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return lstvo;
	}

	private ClassificacaoVO getClassificacaoVO(ClassificacaoDTO dto) {
		ClassificacaoVO vo = new ClassificacaoVO();
		vo.id = dto.id;
		vo.descricao = dto.descricao;
		return vo;
	}

	public List<LocalItemVO> listarLocalItem(long idUfCidade, 
			List<LocalVO> lstlocal,
			List<ClassificacaoVO> lstclass,
			GeoLocalizacaoVO geolocalizacao) throws AlugueRelaxeFrontException {
		long[] aLocal = new long[100];
		long[] aClass = new long[100];
		
		// Preenche o array com um valor invalido de local e classificacao
		Arrays.fill(aLocal, -1);
		Arrays.fill(aClass, -1);
		
		// Obtem o array de long
		long maxarray = 0;
		if ((lstlocal != null) && (lstlocal.size() > 0)){
			maxarray += lstlocal.size();
			int i = 0;
			for (LocalVO lvo : lstlocal){
				aLocal[i] = lvo.id;
				i++;
			}
		}
		if ((lstclass != null) && (lstclass.size() > 0)){
			maxarray += lstclass.size();
			int i = 0;
			for (ClassificacaoVO lvo : lstclass){
				aClass[i] = lvo.id;
				i++;
			}
		}
		
		if (maxarray == 0) return null;
		
		List<LocalItemVO> lstvo = null;
		try {
			LocalService<LocalItemDTO> is = new LocalServicesImpl();
			List<LocalItemDTO> lstlocaldto = is.listarLocaisUFCidade(idUfCidade, aLocal); 
			List<LocalItemDTO> lstclassdto = is.listarLocaisUFCidadeClassificacao(idUfCidade, aClass ); 
			
			if ((lstlocaldto != null) || (lstclassdto != null)) {
				lstvo = new ArrayList<LocalItemVO>();
				
				if (lstlocaldto != null){
					for (LocalItemDTO dto : lstlocaldto) {
						LocalItemVO vo = getLocalItemVO(dto);
						vo.distancia = calculaDistancias(geolocalizacao, dto.latitude, dto.longitude);
						lstvo.add(vo);
					}
				}
				
				if (lstclassdto != null){
					for (LocalItemDTO dto : lstclassdto) {
						LocalItemVO vo = getLocalItemVO(dto);
						vo.distancia = calculaDistancias(geolocalizacao, dto.latitude, dto.longitude);
						lstvo.add(vo);
					}
				}
			}
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return lstvo;
	}
	
	private DistanciaVO calculaDistancias(GeoLocalizacaoVO geoOrigem,
			double latitude, double longitude) {
		DistanciaVO vo = new DistanciaVO();
		Distancia d = new Distancia(geoOrigem.latitude, 
									geoOrigem.longitude,
									latitude,
									longitude);
		DistanciaDTO dto = d.getDTO();
		vo.distanciaKm = String.valueOf(StringUtil.valorCorreto(dto.distanciaKm));
		vo.distanciaMt = String.valueOf(StringUtil.valorCorreto(dto.distanciaMt));
		vo.tempoGastoCarro = String.valueOf(StringUtil.valorCorreto(dto.tempoGastoCarro));
		vo.tempoGastoPe = String.valueOf(StringUtil.valorCorreto(dto.tempoGastoPe));
		return vo;
	}

	private LocalItemVO getLocalItemVO(LocalItemDTO dto){
		LocalItemVO vo = new LocalItemVO();
		vo.id = dto.id;
		vo.idLocal = dto.idLocal;
		vo.idUfCidadeItem = dto.idUfCidadeItem;
		vo.uf = dto.uf;
		vo.cidade = dto.cidade;
		vo.classificacao = dto.classificacao;
		vo.descricaoBase = dto.descricaoBase;
		vo.descricao = dto.descricao;
		vo.latitude = dto.latitude;
		vo.longitude = dto.longitude;
		vo.urlRef_1 = dto.urlRef_1;
		vo.urlRef_2 = dto.urlRef_2;
		vo.urlRef_3 = dto.urlRef_3;
		vo.urlRef_4 = dto.urlRef_4;
		vo.urlRef_5 = dto.urlRef_5;
		vo.urlIconeLocal = dto.urlIconeLocal;
		vo.urlIconeLocalItem = dto.urlIconeLocalItem;
		return vo;
		
	}

	public VOPadrao migrarPlanoImovel(long codImovel, long codNovoPlano)
			throws AlugueRelaxeFrontException {
		VOPadrao voretorno = null;
		
		try {
			ImovelPlanoService<ImovelPlanoRelacaoDTO> is = new ImovelPlanoServiceImpl();
			ImovelPlanoRelacaoDTO dtoretorno = is.migrarPlanoImovel(codImovel, codNovoPlano);
			
			voretorno = new VOPadrao();
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;	
	}

	public VOPadrao adicionarVideoImovel(ImovelImagemVideoVO vo)
			throws AlugueRelaxeFrontException {
		VOPadrao voretorno = null;
		
		try {
			ImovelImagemVideoService<ImovelImagemVideoDTO> is = new ImovelImagemVideoServiceImpl();
			DTOPadrao dtoretorno = is.adicionarVideoImovel((new DTOImovelImagemVideoImpl()).getDataTransferObject(vo));
			
			voretorno = new VOPadrao();
			voretorno.msgcode = dtoretorno.msgcode;
			voretorno.msgcodeString = dtoretorno.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return voretorno;
	}

	public ImovelPlanoFaturaVO renovarPlano(long idImovel)
			throws AlugueRelaxeFrontException {
		ImovelPlanoFaturaVO vo = null;
		try {
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> is = new ImovelPlanoFaturaServiceImpl();
			ImovelPlanoFaturaDTO dto = is.renovarPlano(idImovel); 
			vo = (new DTOImovelPlanoFaturaImpl()).getDataTransferObject(dto);
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return vo;
	}

	public PublicidadeImovelVO criarPublicidade(VendaPublicidadeVO vpvo)
			throws AlugueRelaxeFrontException {
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		
		PublicidadeImovelVO pivo = null;
		try {
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			PublicidadeImovelDTO pidto = new PublicidadeImovelDTO();
			pidto.fichaImovel = new ImovelFichaCompletaDTO();
			pidto.fichaImovel.imovel = new ImovelDTO();
			pidto.fichaImovel.imovel.id = vpvo.imovel.imovel.id;
			pidto.publicidade = new PublicidadeDTO();
			pidto.publicidade.dataInicio = vpvo.dataInicio;
			pidto.publicidade.tipoPublicidade = vpvo.planoVendido.indicadorTipoCompra;
			
			// invoca backend para criar a publicidade
			PublicidadeImovelDTO dto = is.criarPublicidade(pidto, vpvo.planoVendido.id);
			
			// Transfere DTO para VO 
			pivo = (new DTOPublicidadeImovelImpl(root_fotos)).getDataTransferObject(dto);
			
			// converte TAGS do form de pagamento do pagseguro com os dados criados na fatura
			final Map<String,String> param = new HashMap<String,String>();
			param.put(Constantes.PS_CODIGO_PLANO, String.valueOf(pivo.plano.id));
			param.put(Constantes.PS_DESCRICAO_DO_PLANO, pivo.plano.nome + " - " + pivo.plano.descricao);
			param.put(Constantes.PS_EMAIL_ALUGUE_RELAXE, VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE));
			param.put(Constantes.PS_EMAIL_CLIENTE, pivo.fichaImovel.cliente.email);
			param.put(Constantes.PS_ID_FATURA, String.valueOf(pivo.fatura.id));
			param.put(Constantes.PS_NOME_CLIENTE, pivo.fichaImovel.cliente.nome);
			param.put(Constantes.PS_VALOR_TOTAL, StringUtil.valorCorreto(pivo.fatura.valorFatura - pivo.fatura.valorDesconto, "."));
			pivo.fatura.linkPagamento = StringUtil.replaceString(pivo.plano.htmlBtnPagseguro, param);
			
			// complementa com a mensagem de retorno do backend
			pivo.msgcode = dto.msgcode;
			pivo.msgcodeString = dto.msgcodeString;
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		return pivo;
	}

	public ContatoClienteVO pesquisarContatoAnunciante(String idhash) throws AlugueRelaxeFrontException {
		ContatoClienteVO ccvo = null;

		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		logger.info("Path base para imagens:" + root_fotos);
		
		try {
			ContatoAnuncianteService cab = new ContatoAnuncianteServiceImpl();
			ContatoClienteDTO ccdto = cab.pesquisarRegistro(idhash);
			if (ccdto != null) {
				ccvo = (new DTOContatoAnuncianteImpl(root_fotos)).getDataTransferObject(ccdto);
			}
			
		}  catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		
		return ccvo;
	}

	public ContatoAnuncianteThreadVO enviarNovaPergunta(ContatoClienteVO ccvo, 
														ContatoAnuncianteThreadVO vo) throws AlugueRelaxeFrontException {
		
		ContatoAnuncianteThreadVO catvo = null;
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		logger.info("Path base para imagens:" + root_fotos);
		
		try {
		
			// Transfere conteudo do VO para DTO
			ContatoClienteDTO ccdto = (new DTOContatoAnuncianteImpl(root_fotos)).getDataTransferObject(ccvo);
			ContatoAnuncianteThreadDTO catdto = (new DTOContatoAnuncianteThreadImpl()).getDataTransferObject(vo);
			
			// Obtem interface para backend e executa inclusao da Thread
			ContatoAnuncianteThreadService cats = new ContatoAnuncianteThreadServiceImpl();
			ContatoAnuncianteThreadDTO catdtoret = cats.gravarDuvida(ccdto, catdto);
			
			// Tranfere o resultado da inclusão para VO
			catvo = (new DTOContatoAnuncianteThreadImpl()).getDataTransferObject(catdtoret);
			catvo.msgcode = catdtoret.msgcode;
			catvo.msgcodeString = catdtoret.msgcodeString;

		}  catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		
		return catvo;
	
	}
	
	
	public ContatoAnuncianteThreadVO enviarResposta(ContatoClienteVO ccvo, 
			ContatoAnuncianteThreadVO vo) throws AlugueRelaxeFrontException {

		ContatoAnuncianteThreadVO catvo = null;
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		logger.info("Path base para imagens:" + root_fotos);
		
		try {
		
			// Transfere conteudo do VO para DTO
			ContatoClienteDTO ccdto = (new DTOContatoAnuncianteImpl(root_fotos)).getDataTransferObject(ccvo);
			ContatoAnuncianteThreadDTO catdto = (new DTOContatoAnuncianteThreadImpl()).getDataTransferObject(vo);
			
			// Obtem interface para backend e executa inclusao da Thread
			ContatoAnuncianteThreadService cats = new ContatoAnuncianteThreadServiceImpl();
			ContatoAnuncianteThreadDTO catdtoret = cats.gravarResposta(ccdto, catdto);
			
			// Tranfere o resultado da inclusão para VO
			catvo = (new DTOContatoAnuncianteThreadImpl()).getDataTransferObject(catdtoret);
			catvo.msgcode = catdtoret.msgcode;
			catvo.msgcodeString = catdtoret.msgcodeString;
		
		}  catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		
		return catvo;
	
	}

	public PublicidadeImovelVO prepararRealizarPagamento(long idImovel)
			throws AlugueRelaxeFrontException {
		
		PublicidadeImovelVO pivo = null;
		
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		logger.info("Path base para imagens:" + root_fotos);

		try {
			ImovelService is = new ImovelServiceImpl();
			PublicidadeImovelDTO pidto = is.prepararRealizarPagamento(idImovel);
			
			if (pidto != null){
				pivo = new PublicidadeImovelVO();
				pivo.fichaImovel = (new DTOImovelFichaCompletaImpl(root_fotos)).getDataTransferObject(pidto.fichaImovel);
				pivo.fatura = (new DTOImovelPlanoFaturaImpl()).getDataTransferObject(pidto.fatura);
				pivo.plano = (new DTOPlanoImpl()).getDataTransferObject(pidto.plano);
			}
			
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
		}

		return pivo;
	}

	public List<ContatoClienteVO> listarComentarioImovel(long idImovel)
			throws AlugueRelaxeFrontException {
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);

		List<ContatoClienteVO> vo = null;
		try {
			ContatoAnuncianteThreadService cab = new ContatoAnuncianteThreadServiceImpl();
			List<ContatoAnuncianteThreadDTO> lstdto = cab.listarComentarios(idImovel);
			
			// Se teve qualquer comentario para o imovel, obtem a ficha de contato completa
			if (lstdto != null) {
				ContatoClienteDTO ccdto = null;
				vo = new ArrayList<ContatoClienteVO>();
				
				for (ContatoAnuncianteThreadDTO catdto : lstdto){
					ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
					ccdto = cas.pesquisarRegistro(catdto.hashParent);
					
					ReservaService<ReservaDTO> rs = new ReservaServiceImpl();
					ccdto.reserva = rs.pesquisarReserva(ccdto.id);
					vo.add((new DTOContatoAnuncianteImpl(root_fotos)).getDataTransferObject(ccdto));
				}
			}
			
		}  catch (AlugueRelaxeException e) {
			logger.error(e);
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		
		return vo;
	}
	
	
}
