package br.com.jcv.aluguerelaxe.server;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.mobile.ficha.FichaImovelRPC;
import br.com.jcv.aluguerelaxe.client.mobile.vo.CaracteristicaVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ClienteVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.EnderecoVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.EnvelopeFichaVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.GeoLocalizacaoVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelCaracteristicaVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelPlanoVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.PlanoVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.TabelaPrecoVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.TelefoneVO;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOImovelFichaCompletaImpl;
import br.com.jcv.backend.caracteristicas.CaracteristicaDTO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.datemanager.DateManager;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.GeoLocalizacaoDTO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelPlanoDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.ui.client.vo.VOPadrao;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class FichaImovelRPCImpl extends RemoteServiceServlet implements
		FichaImovelRPC {
	
	private static final long serialVersionUID = -899700429989711705L;


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
	public EnvelopeFichaVO apresentarFichaCompletaImovel(long codigoImovel, String origemAcesso) {

		ImovelFichaCompletaVO ifcvo = null;
		
		String root_fotos = "";
		try {
			root_fotos = VariavelCache.getInstance().getValor("ROOT_FOTOS");
		} catch (AlugueRelaxeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		
		StringBuilder sbfotos = new StringBuilder();
		StringBuilder sbslider = new StringBuilder();
		
		int i = 0;
		if (ifcvo.imagensImovelXG != null) {
			for (ImovelImagemVideoVO iivvo: ifcvo.imagensImovelXG){
				if (i == 0){
					sbslider.append("<li data-target=\"#carousel-example-generic\" data-slide-to=\""+i+"\" class=\"active\"></li>");

					sbfotos.append("<div class=\"item active\">");
			        sbfotos.append("<img src=\""+iivvo.nome+"\" data-src=\"holder.js/640x480/auto\" alt=\"Second slide\">");
			        sbfotos.append("<div class=\"carousel-caption\">");
			        sbfotos.append("<h3>" +ifcvo.imovel.endereco.cidade.toUpperCase() + " - " + ifcvo.imovel.endereco.uf.toUpperCase() + "</h3>");
			        sbfotos.append("<p>" + ifcvo.imovel.descricaoTituloAnuncio + "</p>");
			        sbfotos.append("</div>");

				} else {
					sbslider.append("<li data-target=\"#carousel-example-generic\" data-slide-to=\""+i+"\"></li>");

					sbfotos.append("<div class=\"item\">");
					sbfotos.append("<img src=\""+iivvo.nome+"\" data-src=\"holder.js/640x480/auto\" alt=\"Second slide\">");
				}
		        sbfotos.append("</div>");
		        
		        i++;
			}
		}
		
		// Cria o mapa de substituicao
		Map<String,String> conteudo = new HashMap<String,String>();
		conteudo.put(TemplateConstantes.TAGC_ID_IMOVEL, String.valueOf(ifcvo.imovel.id));
		conteudo.put(TemplateConstantes.TAGC_CIDADE, ifcvo.imovel.endereco.cidade.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_UF, ifcvo.imovel.endereco.uf.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_TITULO_ANUNCIO, ifcvo.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcvo.imovel.descricaoGeral);
		conteudo.put(TemplateConstantes.TAGC_FOTOS, sbfotos.toString());
		conteudo.put(TemplateConstantes.TAGC_FOTOS_SLIDER, sbslider.toString());
		
		// REaliza a leitura do template
		Template template = null;
		try {
			template = TemplateFactory.getInstance(TemplateConstantes.TEMPLATE_FICHA_IMOVEL_MOBILE, conteudo);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Envelopa o resultado
		EnvelopeFichaVO efvo = new EnvelopeFichaVO();
		efvo.ifcvo = ifcvo;
		efvo.hmtl = template.getHtmlTemplate();

		return efvo;
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

}
