package br.com.jcv.backend.html.properta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.chain.Chain;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.I18n;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelBaseDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaServiceImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeDTO;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;
import br.com.jcv.backend.interfaces.services.ImovelPlanoFaturaService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.mensagem.I18nCache;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class PropertaFichaImovelChain extends AbstractPropertaChain implements
		Chain<DTOPadrao> {

	public void execute(DTOPadrao context) {
		
		try {
			// Busca lista de paginas a serem atualizadas
			List<ImovelFichaCompletaDTO> lst = this.listImovelPP();
			
			if ((lst != null) && (lst.size() > 0)){
				for ( ImovelFichaCompletaDTO ifcdto : lst){
					
					// Caminho completo de onde a ficha do imovel sera criada
					String pathIndex = VariavelCache.getInstance().getValor(VariavelConstantes.PATH_FICHA_IMOVEL_PROPERTA);
					pathIndex = StringUtil.replaceStringNew(pathIndex, "${TIPO}", ifcdto.imovel.indicadorTipoPropriedadeStr.toLowerCase());
					pathIndex = StringUtil.replaceStringNew(pathIndex, "${UF}", ifcdto.imovel.endereco.uf.toLowerCase());
					pathIndex = StringUtil.replaceStringNew(pathIndex, "${CIDADE}", String.valueOf(ifcdto.imovel.endereco.codigoUfCidadeItem));
					pathIndex = StringUtil.replaceStringNew(pathIndex, "${ID_IMOVEL}", String.valueOf(ifcdto.imovel.id));
					pathIndex = StringUtil.replaceStringNew(pathIndex, TemplateConstantes.TAGC_LANGUAGE, Constantes.LANGUAGE_PT_BR);
					pathIndex = pathIndex + "/" + ifcdto.imovel.idhash + ".html";

					ifcdto.urlFichaImovelEstatica = StringUtil.replaceStringNew( ifcdto.urlFichaImovelEstatica, TemplateConstantes.TAGC_LANGUAGE, Constantes.LANGUAGE_PT_BR);
					
					//------------------------------------------------------
					// Cria a pasta de HTMLs estaticos se houver necessidade
					//------------------------------------------------------
					File fromFile = new File(pathIndex);
					String parent = fromFile.getParent();
					
					File dir = new File(parent);
					if (!dir.exists()) {

						if (dir.mkdirs()) {
							// TODO Colocar logger
						} else  {
							throw new IOException("Não foi possível criar diretório de ficha estatica " + parent);
						}
					}

					//------------------------------------------------------
					// link para todas as propriedades do cliente
					//------------------------------------------------------
					String urlPropriedadesCliente = VariavelCache.getInstance().getValor(VariavelConstantes.URL_PROPRIEDADES_CLIENTE_PROPERTA);
					urlPropriedadesCliente = StringUtil.replaceStringNew(urlPropriedadesCliente, "${CLIENTE_ID}", String.valueOf(ifcdto.cliente.id));
					urlPropriedadesCliente = StringUtil.replaceStringNew(urlPropriedadesCliente, TemplateConstantes.TAGC_LANGUAGE, Constantes.LANGUAGE_PT_BR);
					urlPropriedadesCliente = urlPropriedadesCliente + "/" + ifcdto.cliente.idhash + ".html";

					//------------------------------------------------------
					// link para foto do perfil
					//------------------------------------------------------
					String fotoPerfil = VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA) + "assets/img/" + ifcdto.cliente.fotoPerfil;
					if (ifcdto.cliente.fotoPerfil.toLowerCase().indexOf("noface") == -1){
						fotoPerfil = VariavelCache.getInstance().getValor(VariavelConstantes.URL_FULL_GALERIA_PERFIL_ANUNCIANTES);
						fotoPerfil = StringUtil.replaceStringNew(fotoPerfil, "${clienteid}", String.valueOf(ifcdto.cliente.id));
						fotoPerfil = fotoPerfil + "/" + ifcdto.cliente.fotoPerfil;
						
					}

					String linkImovel = "";
					try {
						linkImovel = StringUtil.replaceStringNew(ifcdto.urlFichaImovelEstatica
														,TemplateConstantes.TAGC_LANGUAGE
														,VariavelCache.getInstance().getValor(VariavelConstantes.LANGUAGE_PT_BR));
					} catch (AlugueRelaxeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String tarifa = (ifcdto.imovel.valorTarifaBasica == 0 
										? "Consulte-nos"
										: VariavelCache.getInstance().getValor(VariavelConstantes.MOEDA_PT_BR) + 
											" " + 
											String.valueOf(ifcdto.imovel.valorTarifaBasica) );
						
					DateManagerBase dc = DateManagerBase.getDateManagerInstance(ifcdto.cliente.dataCadastro.getTime());
					
					Map<String,String> conteudo = new HashMap<String, String>();
					conteudo.put(TemplateConstantes.TAGC_URL_HOME_PROPERTA, VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA));
					conteudo.put(TemplateConstantes.TAGC_TITULO_ANUNCIO, ifcdto.imovel.descricaoTituloAnuncio);
					conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcdto.imovel.descricaoGeral);
					conteudo.put(TemplateConstantes.TAGC_NOME_CLIENTE, ifcdto.cliente.primeiroNome);
					conteudo.put(TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade);
					conteudo.put(TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
					conteudo.put(TemplateConstantes.TAGC_URL_FOTO_CLIENTE, fotoPerfil);
					conteudo.put(TemplateConstantes.TAGC_PROPERTA_TIPOS, ifcdto.imovel.indicadorTipoPropriedadeStr);
					conteudo.put(TemplateConstantes.TAGC_VALOR_DIARIA, tarifa);
					conteudo.put(TemplateConstantes.TAGC_BANHEIROS, String.valueOf(ifcdto.imovel.qtdeBanheiros));
					conteudo.put(TemplateConstantes.TAGC_QUARTOS, String.valueOf(ifcdto.imovel.qtdeQuartos));
					conteudo.put(TemplateConstantes.TAGC_SUITES, String.valueOf(ifcdto.imovel.qtdeSuites));
					conteudo.put(TemplateConstantes.TAGC_CAPACIDADE_MAX, String.valueOf(ifcdto.imovel.qtdeCapacidade));
					conteudo.put(TemplateConstantes.TAGC_DESC_ARREDORES, ifcdto.imovel.descricaoArredores);
					conteudo.put(TemplateConstantes.TAGC_DESCRICAO_QUARTOS, ifcdto.imovel.descricaoQuartos);
					conteudo.put(TemplateConstantes.TAGC_OBSERVACAO, ifcdto.imovel.observacoes);
					conteudo.put(TemplateConstantes.TAGC_ID_IMOVEL, String.valueOf(ifcdto.imovel.id));
					conteudo.put(TemplateConstantes.TAGC_ENDERECO_IMOVEL, ifcdto.imovel.endereco.toString());
					conteudo.put(TemplateConstantes.TAGC_FOTO_PREVIEW, getUrlFotoPreview(ifcdto));
					conteudo.put(TemplateConstantes.TAGC_FOTOS_ITEM, getHTMLFotoGaleria(ifcdto));
					conteudo.put(TemplateConstantes.TAGC_URL_PROPRIEDADES_CLIENTE, urlPropriedadesCliente);
					conteudo.put(TemplateConstantes.TAGC_CARACTERISTICAS, this.getHTMLCaracteristicas(ifcdto));
					conteudo.put(TemplateConstantes.TAGC_LINK_IMOVEL, linkImovel);
					conteudo.put(TemplateConstantes.TAGC_LATITUDE,String.valueOf(ifcdto.geolocalizacao.latitude));
					conteudo.put(TemplateConstantes.TAGC_LONGITUDE, String.valueOf(ifcdto.geolocalizacao.longitude));
					conteudo.put(TemplateConstantes.TAGC_NUMERO_PROPRIEDADES, String.valueOf(getPropriedadesCliente(ifcdto.cliente.id)));
					conteudo.put(TemplateConstantes.TAGC_TABELA_PRECO, getHTMLTabelaPreco(ifcdto.tabelaPreco));
					conteudo.put(TemplateConstantes.TAGC_COPYRIGHT, I18nCache.getInstance().getMensagem(I18n.PT_DIREITOS_RESERVADOS));
					conteudo.put(TemplateConstantes.TAGC_TAXA_RESPOSTA, getHTMLTaxaResposta(ifcdto));
					conteudo.put(TemplateConstantes.TAGC_FOTOS_CAROUSEL, getHTMLFotoCarousel(ifcdto));
					conteudo.put(TemplateConstantes.TAGC_MEMBRO_VERIFICADO, getHTMLMembroVerificado(ifcdto));
					conteudo.put(TemplateConstantes.TAGC_DATA_CADASTRO, dc.getDateCustom("MM/yyyy"));
					conteudo.put(TemplateConstantes.TAGC_TELEFONES_CONTATO, getHTMLTelefonesContato(ifcdto));
					conteudo.put(TemplateConstantes.TAGC_PROPERTA_HEADER_TOP, super.getHTMLPropertaHeaderTop(Constantes.LANGUAGE_PT_BR));
					conteudo.put(TemplateConstantes.TAGC_PROPERTA_FOOTER_WRAPPER, super.getHTMLPropertaFooterWrapper(Constantes.LANGUAGE_PT_BR));
					conteudo.put(TemplateConstantes.TAGC_PROPERTA_FOOTER_JAVASCRIPT, super.getHTMLPropertaFooterJS());
					conteudo.put(TemplateConstantes.TAGC_SECAO_HEAD, super.getHTMLPropertaSecaoHead());

					// Obtem o template
					Template template = TemplateFactory.getInstance(conteudo, TemplateConstantes.TEMPLATE_PROPERTA_FICHA_IMOVEL);
					
					FileOutputStream fos = new FileOutputStream(pathIndex);     
					fos.write(template.getHtmlTemplate().getBytes());     
					fos.close();
					
				}
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private int getPropriedadesCliente(long idCliente) {
		int retorno = 0;
		
		try {
			ImovelService<ImovelBaseDTO> ips = new ImovelServiceImpl<ImovelPublicidadeDTO>();
			retorno = ips.contarImoveisCliente(idCliente);
		} catch (AlugueRelaxeException are){
			are.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	private List<ImovelFichaCompletaDTO> listImovelPP() {
		List<ImovelFichaCompletaDTO> lst = null;
		
		try {
			// Obtem a lista de imoveis que estao disponiveis para publicidade
			ImovelService<ImovelBaseDTO> ips = new ImovelServiceImpl<ImovelPublicidadeDTO>();
			List<Long> publicidades = ips.listarImoveis("A");

			// Repassa a lista do backend para o cache
			if ((publicidades != null) && (publicidades.size() > 0)) {
				
				// Temos publicidade no periodo, entao inicializa a List
				lst = new ArrayList<ImovelFichaCompletaDTO>();
				
				for (Long ibdto : publicidades) {
					ImovelFichaCompletaDTO ifcdto = ips.pesquisarFichaCompletaImovel(ibdto.longValue());
					if (ifcdto != null) {
						lst.add(ifcdto);
					}
				}
			}
		} catch (AlugueRelaxeException are){
			are.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lst;
	
	}
/*
	private String getUrlFotoPreview(ImovelFichaCompletaDTO ifcdto) {
		String html = "";
		
		if(ifcdto.imagensImovelXG == null) {
			try {
				html = VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA) + "assets/img/property-tmp-small.png";
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
		
			if (ifcdto.imagensImovelXG.size() > 0){
				String linkfoto = "";
				try {
					linkfoto = VariavelCache.getInstance().getValor(VariavelConstantes.URL_VERIFICAR_IMAGEM_UPLOAD);
				} catch (AlugueRelaxeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				linkfoto = StringUtil.replaceStringNew(linkfoto,"${clienteid}",String.valueOf(ifcdto.cliente.id));
				linkfoto = StringUtil.replaceStringNew(linkfoto,"${imovelid}",String.valueOf(ifcdto.imovel.id));
				linkfoto = StringUtil.replaceStringNew(linkfoto,"${imagemupload}",ifcdto.imagensImovelXG.get(0).nome);

				html = linkfoto;
			}
		}
	
		return html;
	}
*/

	private String getHTMLFotoGaleria(ImovelFichaCompletaDTO ifcdto) {
		final String TEMPLATE_FOTO_ITEM = 
			"<li${FLAG_ATIVO}>" +
				"<div class=\"thumb\">" +
					"<a href=\"#\"><img src=\"${FOTOS}\" alt=\"\"></a>" +
				"</div>" +
			"</li>" ;

		String html = "";
		
		if(ifcdto.imagensImovelXG == null) {
			html = new String(TEMPLATE_FOTO_ITEM);
			String linkfoto = "";
			try {
				linkfoto = VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA) + "assets/img/property-tmp-small.png";
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Troca de tags
			html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_FOTOS, linkfoto);
			html = StringUtil.replaceStringNew(html, "${FLAG_ATIVO}", " class=\"active\">");
		} else {
		
			if (ifcdto.imagensImovelXG.size() > 0){
				StringBuilder sbhtml = new StringBuilder();
				int i = 0;
				for (ImovelImagemVideoDTO iivdto : ifcdto.imagensImovelXG) {
					String htmlitem = new String(TEMPLATE_FOTO_ITEM);
					String linkfoto = "";
					String classAtivo = "";
					if ( i++ == 0){
						htmlitem = StringUtil.replaceStringNew(htmlitem, "${FLAG_ATIVO}", " class=\"active\"");
					} else {
						htmlitem = StringUtil.replaceStringNew(htmlitem, "${FLAG_ATIVO}", "");
					}

					try {
						linkfoto = VariavelCache.getInstance().getValor(VariavelConstantes.URL_VERIFICAR_IMAGEM_UPLOAD);
					} catch (AlugueRelaxeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					linkfoto = StringUtil.replaceStringNew(linkfoto,"${clienteid}",String.valueOf(ifcdto.cliente.id));
					linkfoto = StringUtil.replaceStringNew(linkfoto,"${imovelid}",String.valueOf(ifcdto.imovel.id));
					linkfoto = StringUtil.replaceStringNew(linkfoto,"${imagemupload}",iivdto.nome);

					// Troca de tags
					sbhtml.append(StringUtil.replaceStringNew(htmlitem, TemplateConstantes.TAGC_FOTOS, linkfoto));
				}
				html = sbhtml.toString();
			}
		}
	
		return html;
	}

	private String getHTMLCaracteristicas(ImovelFichaCompletaDTO ifcdto) {
		final String HTML_CARACTERISTICA_ITEM = "<li class=\"checked\">${CARACTERISTICA_ITEM}</li>";
		final String TAG_CARACTERISTICA_ITEM = "${CARACTERISTICA_ITEM}";
		final String HTML_CARACTERISTICA =
        "<div class=\"row\">" +
            "<div class=\"span8\">" +
                "<h2>${TITULO_CARACTERISTICA}</h2>" +
                "<div class=\"row\">" +
                    "<ul class=\"span2\">" +
						"${LISTA1_CARACTERISTICAS}" +
                    "</ul>" +
                    "<!-- /.span2 -->" +
                    "<ul class=\"span2\">" +
						"${LISTA2_CARACTERISTICAS}" +
                    "</ul>" +
                    "<!-- /.span2 -->" +
                    "<ul class=\"span2\">" +
						"${LISTA3_CARACTERISTICAS}" +
                    "</ul>" +
                    "<!-- /.span2 -->" +
                    "<ul class=\"span2\">" +
						"${LISTA4_CARACTERISTICAS}" +
                    "</ul>" +
                    "<!-- /.span2 -->" +
                "</div>" +
                "<!-- /.row -->" +
            "</div>" +
            "<!-- /.span6 -->" +
        "</div>" +
        "<!-- /.row -->";
		
		StringBuilder sbhtml = new StringBuilder();
		
		//-------------------------------------------------
		// Caracteristicas do imovel
		//-------------------------------------------------
		if ((ifcdto.caracteristicaImovel != null) && (ifcdto.caracteristicaImovel.size() > 0)) {
			String rowitem = new String(HTML_CARACTERISTICA);
			rowitem = StringUtil.replaceStringNew(rowitem, "${TITULO_CARACTERISTICA}", "Facilidades do imovel" );

			// preparacao das 4 colunas
			StringBuilder sblista1 = new StringBuilder();
			StringBuilder sblista2 = new StringBuilder();
			StringBuilder sblista3 = new StringBuilder();
			StringBuilder sblista4 = new StringBuilder();
			int i = 1;
			for (ImovelCaracteristicaDTO dto : ifcdto.caracteristicaImovel) {
				String item = new String(HTML_CARACTERISTICA_ITEM);
				if (i == 1) {
					sblista1.append(StringUtil.replaceStringNew(item, TAG_CARACTERISTICA_ITEM, dto.caracteristica.nome ));
				} else if (i == 2) {
					sblista2.append(StringUtil.replaceStringNew(item, TAG_CARACTERISTICA_ITEM, dto.caracteristica.nome ));
				} else if (i == 3) {
					sblista3.append(StringUtil.replaceStringNew(item, TAG_CARACTERISTICA_ITEM, dto.caracteristica.nome ));
				} else if (i == 4) {
					sblista4.append(StringUtil.replaceStringNew(item, TAG_CARACTERISTICA_ITEM, dto.caracteristica.nome ));
				}
				if (++i > 4) i = 1;
			}
			
			// Adiciona a lista de itens por coluna
			rowitem = StringUtil.replaceStringNew(rowitem, "${LISTA1_CARACTERISTICAS}", sblista1.toString() );
			rowitem = StringUtil.replaceStringNew(rowitem, "${LISTA2_CARACTERISTICAS}", sblista2.toString() );
			rowitem = StringUtil.replaceStringNew(rowitem, "${LISTA3_CARACTERISTICAS}", sblista3.toString() );
			rowitem = StringUtil.replaceStringNew(rowitem, "${LISTA4_CARACTERISTICAS}", sblista4.toString() );
			
			// adiciona a linha do html de retorno
			sbhtml.append(rowitem.toString());
		}
		
		//-------------------------------------------------
		// Caracteristicas do condominio
		//-------------------------------------------------
		if ((ifcdto.caracteristicaCondominio != null) && (ifcdto.caracteristicaCondominio.size() > 0)) {
			String rowitem = new String(HTML_CARACTERISTICA);
			rowitem = StringUtil.replaceStringNew(rowitem, "${TITULO_CARACTERISTICA}", "Facilidades do Condominio" );

			// preparacao das 3 colunas
			StringBuilder sblista1 = new StringBuilder();
			StringBuilder sblista2 = new StringBuilder();
			StringBuilder sblista3 = new StringBuilder();
			StringBuilder sblista4 = new StringBuilder();
			int i = 1;
			for (ImovelCaracteristicaDTO dto : ifcdto.caracteristicaCondominio) {
				String item = new String(HTML_CARACTERISTICA_ITEM);
				if (i == 1) {
					sblista1.append(StringUtil.replaceStringNew(item, TAG_CARACTERISTICA_ITEM, dto.caracteristica.nome ));
				} else if (i == 2) {
					sblista2.append(StringUtil.replaceStringNew(item, TAG_CARACTERISTICA_ITEM, dto.caracteristica.nome ));
				} else if (i == 3) {
					sblista3.append(StringUtil.replaceStringNew(item, TAG_CARACTERISTICA_ITEM, dto.caracteristica.nome ));
				} else if (i == 4) {
					sblista4.append(StringUtil.replaceStringNew(item, TAG_CARACTERISTICA_ITEM, dto.caracteristica.nome ));
				}
				if (++i > 4) i = 1;
			}
			
			// Adiciona a lista de itens por coluna
			rowitem = StringUtil.replaceStringNew(rowitem, "${LISTA1_CARACTERISTICAS}", sblista1.toString() );
			rowitem = StringUtil.replaceStringNew(rowitem, "${LISTA2_CARACTERISTICAS}", sblista2.toString() );
			rowitem = StringUtil.replaceStringNew(rowitem, "${LISTA3_CARACTERISTICAS}", sblista3.toString() );
			rowitem = StringUtil.replaceStringNew(rowitem, "${LISTA4_CARACTERISTICAS}", sblista4.toString() );
			
			// adiciona a linha do html de retorno
			sbhtml.append(rowitem.toString());
		}
		
		return sbhtml.toString();
		
	}

	private String getHTMLTabelaPreco(List<TabelaPrecoDTO> tabelaPreco) {
		final String HTML_TABELA_PRECO_ITEM = 
			"<tr>" +
			"	<td>${TITULO}</td>" +
			"	<td>${VALIDADE}</td>" +
			"	<td>${VALOR}</td>" +
			"	<td>${MINIMO_DE}</td>" +
			//"	<td>${OBSERVACAO}</td>" +
			"</tr>";
			
		final String HTML_OBSERVACAO = 
			"<tr>" +
			"	<td colspan=\"4\">${OBSERVACAO}</td>" +
			"</tr>";

			
		StringBuilder html = new StringBuilder();
		if ((tabelaPreco != null) && (tabelaPreco.size() > 0)) {
			for (TabelaPrecoDTO tp : tabelaPreco){
				String item = new String(HTML_TABELA_PRECO_ITEM);
				DateManagerBase di = DateManagerBase.getDateManagerInstance((tp.dataInicio == null ? new Date() : tp.dataInicio));
				DateManagerBase df = DateManagerBase.getDateManagerInstance((tp.dataFim == null ? new Date() : tp.dataFim));
				String validade = "";
				try {
					validade = di.getDateCustom("MMM/yy") + " - " + di.getDateCustom("MMM/yy");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				item = StringUtil.replaceStringNew(item, "${TITULO}", tp.textoPeriodo);
				item = StringUtil.replaceStringNew(item, "${VALIDADE}", validade);
				item = StringUtil.replaceStringNew(item, "${VALOR}", String.valueOf((int) tp.valorTabela));
				item = StringUtil.replaceStringNew(item, "${MINIMO_DE}", tp.textoMinimoDe == null ? "" :tp.textoMinimoDe);
				html.append(item);
				
				if (tp.observacao != null){
					String obs = new String(HTML_OBSERVACAO);
					obs = StringUtil.replaceStringNew(obs, "${OBSERVACAO}", "<b>Obs:</b>&nbsp;" + tp.observacao);
					html.append(obs);
				}
				
			}
		} else {
			html.append("Consulte-nos preenchendo a ficha de contato.");
		}
		return html.toString();
	}

	private String getHTMLTaxaResposta(ImovelFichaCompletaDTO ifcdto) {
		final String HTML_TRECHO =
			"<div class=\"refresh\">" +
			"	${NUMERO_TAXA_RESPOSTA}% respondido" +
			"</div>" +
			"<!-- /.refresh -->" ;
		String html = "";
		
		if ((ifcdto != null) && (ifcdto.cliente.taxaResposta > 0)){
			html = new String(HTML_TRECHO);
			html = StringUtil.replaceStringNew(html, "${NUMERO_TAXA_RESPOSTA}", String.valueOf(ifcdto.cliente.taxaResposta));
		}
		
		return html;
	}

	private String getHTMLTelefonesContato(ImovelFichaCompletaDTO ifcdto) {
		final String HTML_TRECHO =
			"<div class=\"phone\">" +
			"${TELEFONE_ITEM}" +
			"</div>" +
			"<!-- /.phone -->";
			
		String html = "";
		
		if (
			(ifcdto != null) && 
			(ifcdto.cliente.telefones != null) &&
			(ifcdto.cliente.telefones.size() > 0)
			){
			
			//---------------------------------------------------
			// Se plano gratuito nao apresenta telefones
			//---------------------------------------------------
			try {
				if (ifcdto.imovelPlano.plano.id != Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PLANO_ADESAO_GRATUITO))) {
					//---------------------------------------------------
					// - Obter a ultima fatura do imovel
					// - Verifica se o anuncio nao está vencido
					//---------------------------------------------------
					ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
					ImovelPlanoFaturaDTO ipfdto = ipfs.pesquisarUltimaFatura(ifcdto.imovel.id, Constantes.TIPO_PLANO_NORMAL);

					// Verifica se anuncio esta pendente e vencido
					ipfs.isAnuncioPendenteVencido(ipfdto);

					// Verifica se anuncio esta pendente e nao vencido
					ipfs.isAnuncioPendenteNaoVencido(ipfdto);

					// Verifica se anuncio liberado esta vencido
					ipfs.isAnuncioLiberadoVencido(ipfdto);

					// Verifica se o plano e gratuito
					ipfs.isPlanoGratuito(ipfdto);	
					
					StringBuilder sbTelefones = new StringBuilder();
					for (TelefoneDTO teldto : ifcdto.cliente.telefones) {
						String item = new String(HTML_TRECHO);
						item = StringUtil.replaceStringNew(item, "${TELEFONE_ITEM}", "(" + teldto.ddd + ")" + teldto.telefone);
						sbTelefones.append(item);
					}
				}
			} catch (AlugueRelaxeException are){
				are.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return html;
	}

	private String getHTMLMembroVerificado(ImovelFichaCompletaDTO ifcdto) {
		final String HTML_TRECHO =
			"<div class=\"profilechk\">" +
			"	Membro Verificado" +
			"</div>" +
			"<!-- /.profilechk -->";

		String html = "";
		
		if ((ifcdto != null) && (ifcdto.cliente.indicadorVerificado.equals("V"))){
			html = new String(HTML_TRECHO);
		}
		
		return html;
	}

	private String getHTMLFotoCarousel(ImovelFichaCompletaDTO ifcdto) {
		final String LI_DATA_TARGET_ITEM_ACTIVE = "	<li data-target=\"#carousel-example-generic\" data-slide-to=\"0\" class=\"active\"></li>";
		final String LI_DATA_TARGET_ITEM = "	<li data-target=\"#carousel-example-generic\" data-slide-to=\"${SEQ_SLIDE}\"></li>";
		final String FOTO_ITEM_ACTIVE = 
			"	<!-- Foto ${SEQ_FOTO} -->" +
			"	<div class=\"item active\">" +
			"	  <img src=\"${URL_FOTO}\" alt=\"${CIDADE} - ${UF}\">" +
			"	  <div class=\"carousel-caption\">" +
			"		<h4>${TITULO_ANUNCIO}</h4>" +
			"		<p>${DESCRICAO_ANUNCIO}</p>" +
			"	  </div>" +
			"	</div>";
		final String FOTO_ITEM_NORMAL = 
			"	<!-- Foto ${SEQ_FOTO} -->" +
			"	<div class=\"item\">" +
			"	  <img src=\"${URL_FOTO}\" alt=\"${CIDADE} - ${UF}\">" +
			"	</div>";

		final String HTML_CAROUSEL =
			"<div id=\"carousel-example-generic\" class=\"carousel slide\" data-ride=\"carousel\">" +
			"  <!-- Indicators -->" +
			"  <ol class=\"carousel-indicators\">" +
			"	${LI_DATA_TARGET}" +
			"  </ol>" +
			"" +
			"  <!-- Wrapper for slides -->" +
			"  <div class=\"carousel-inner\">" +
			"" +
			"	${FOTO_COLECAO}" +
			"" +
			"</div>" +
			"" +
			" <!-- Controls -->" +
			"  <a class=\"left carousel-control\" href=\"#carousel-example-generic\" data-slide=\"prev\">" +
			"	<span class=\"glyphicon glyphicon-chevron-left\"></span>" +
			"  </a>" +
			"  <a class=\"right carousel-control\" href=\"#carousel-example-generic\" data-slide=\"next\">" +
			"	<span class=\"glyphicon glyphicon-chevron-right\"></span>" +
			"  </a>" +
			"</div>";
			
		String html = new String(HTML_CAROUSEL);
		StringBuilder lsttarget = new StringBuilder();
		StringBuilder lstfotos = new StringBuilder();
				
		if(ifcdto.imagensImovelXG == null) {
			//---------------------------------------------------------
			// Coloca foto "nula"
			//---------------------------------------------------------

			String fotoitem = new String(FOTO_ITEM_ACTIVE);
			String linkfoto = "";
			try {
				linkfoto = VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA) + "assets/img/property-tmp-small.png";
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String dg = "";
			if (ifcdto.imovel.descricaoGeral.length() > 100){
				dg = ifcdto.imovel.descricaoGeral.substring(100) + "...";
			} else {
				dg = ifcdto.imovel.descricaoGeral;
			}
			fotoitem = StringUtil.replaceStringNew(fotoitem, "${SEQ_FOTO}", "1");
			fotoitem = StringUtil.replaceStringNew(fotoitem, "${URL_FOTO}", linkfoto);
			fotoitem = StringUtil.replaceStringNew(fotoitem, "${CIDADE}", ifcdto.imovel.endereco.cidade.toUpperCase());
			fotoitem = StringUtil.replaceStringNew(fotoitem, "${UF}", ifcdto.imovel.endereco.uf.toUpperCase());
			fotoitem = StringUtil.replaceStringNew(fotoitem, "${TITULO_ANUNCIO}", ifcdto.imovel.descricaoTituloAnuncio);
			fotoitem = StringUtil.replaceStringNew(fotoitem, "${DESCRICAO_ANUNCIO}", dg);
			lstfotos.append(fotoitem);
			
			
			//---------------------------------------------------------
			// substituir tag <li>
			//---------------------------------------------------------
			lsttarget.append(LI_DATA_TARGET_ITEM_ACTIVE);
			
		} else {
			if (ifcdto.imagensImovelXG.size() > 0){
				StringBuilder sbhtml = new StringBuilder();
				int i = 0;
				for (ImovelImagemVideoDTO iivdto : ifcdto.imagensImovelXG) {
				
					//-------------------------
					// Obtem o link da imagem
					//-------------------------
					String linkfoto = "";
					try {
						linkfoto = VariavelCache.getInstance().getValor(VariavelConstantes.URL_VERIFICAR_IMAGEM_UPLOAD);
					} catch (AlugueRelaxeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					linkfoto = StringUtil.replaceStringNew(linkfoto,"${clienteid}",String.valueOf(ifcdto.cliente.id));
					linkfoto = StringUtil.replaceStringNew(linkfoto,"${imovelid}",String.valueOf(ifcdto.imovel.id));
					linkfoto = StringUtil.replaceStringNew(linkfoto,"${imagemupload}",iivdto.nome);

					//-------------------------------------
					// Obtem o html da foto item e <li>
					//-------------------------------------
					if (i++ == 0) {
						String fotoitem = new String(FOTO_ITEM_ACTIVE);
						fotoitem = StringUtil.replaceStringNew(fotoitem, "${SEQ_FOTO}", String.valueOf(i));
						fotoitem = StringUtil.replaceStringNew(fotoitem, "${URL_FOTO}", linkfoto);
						fotoitem = StringUtil.replaceStringNew(fotoitem, "${CIDADE}", ifcdto.imovel.endereco.cidade.toUpperCase());
						fotoitem = StringUtil.replaceStringNew(fotoitem, "${UF}", ifcdto.imovel.endereco.uf.toUpperCase());
						fotoitem = StringUtil.replaceStringNew(fotoitem, "${TITULO_ANUNCIO}", ifcdto.imovel.descricaoTituloAnuncio);
						fotoitem = StringUtil.replaceStringNew(fotoitem, "${DESCRICAO_ANUNCIO}", ifcdto.imovel.descricaoGeral);
						lstfotos.append(fotoitem);
						lsttarget.append(LI_DATA_TARGET_ITEM_ACTIVE);
						
					} else {
						String fotoitem = new String(FOTO_ITEM_NORMAL);
						fotoitem = StringUtil.replaceStringNew(fotoitem, "${SEQ_FOTO}", String.valueOf(i));
						fotoitem = StringUtil.replaceStringNew(fotoitem, "${URL_FOTO}", linkfoto);
						fotoitem = StringUtil.replaceStringNew(fotoitem, "${CIDADE}", ifcdto.imovel.endereco.cidade.toUpperCase());
						fotoitem = StringUtil.replaceStringNew(fotoitem, "${UF}", ifcdto.imovel.endereco.uf.toUpperCase());
						lstfotos.append(fotoitem);
						
						String li = new String(LI_DATA_TARGET_ITEM);
						li = StringUtil.replaceStringNew(li, "${SEQ_SLIDE}", String.valueOf(i));
						lsttarget.append(li);
					}
				
				}
			}
		}
		
		//---------------------------------------------------------
		// substituir as tags de colecao
		//---------------------------------------------------------
		html = StringUtil.replaceStringNew(html, "${FOTO_COLECAO}", lstfotos.toString());
		html = StringUtil.replaceStringNew(html, "${LI_DATA_TARGET}", lsttarget.toString());
		return html;
	}
}
