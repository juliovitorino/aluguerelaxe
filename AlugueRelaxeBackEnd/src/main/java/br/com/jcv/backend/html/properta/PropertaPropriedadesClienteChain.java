package br.com.jcv.backend.html.properta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.chain.Chain;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.ClienteServiceImpl;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelBaseDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeDTO;
import br.com.jcv.backend.interfaces.services.ClienteService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class PropertaPropriedadesClienteChain extends AbstractPropertaChain implements
		Chain<DTOPadrao> {
		
		
	public void execute(DTOPadrao context) {
		List<ImovelFichaCompletaDTO> lstimv = null;
		try {
			// Busca lista de clientes ativos
			List<Long> lstclientes = this.listClientesAtivos();

			if ((lstclientes != null) && (lstclientes.size() > 0)){
				for ( Long idcliente : lstclientes){
					this.init();
					
					//-----------------------------------------------------------------
					// se tem clientes ativo, busca a lista de imoveis deste cliente
					//-----------------------------------------------------------------
					List<ImovelFichaCompletaDTO> lst = this.listPropriedadesCliente(idcliente.longValue());
					
					
					if ((lst != null) && (lst.size() > 0)){
						
						String pathIndex = "";
						String fotoPerfil = "";
						String primeiroNome = "";
						lstimv = new ArrayList<ImovelFichaCompletaDTO>();
						
						for ( ImovelFichaCompletaDTO ifcdto : lst){
							
							// Garante a ficha intacta
							try {
								// Obtem a lista de imoveis que estao disponiveis para publicidade
								ImovelService<ImovelBaseDTO> is = new ImovelServiceImpl<ImovelPublicidadeDTO>();
								ifcdto = is.pesquisarFichaCompletaImovel(ifcdto.imovel.id);
							} catch (AlugueRelaxeException are){
								are.printStackTrace();
							} catch (Exception e) {
								e.printStackTrace();
							}

							fotoPerfil = VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA) + "assets/img/" + ifcdto.cliente.fotoPerfil;
							if (ifcdto.cliente.fotoPerfil.toLowerCase().indexOf("noface") == -1){
								fotoPerfil = VariavelCache.getInstance().getValor(VariavelConstantes.URL_FULL_GALERIA_PERFIL_ANUNCIANTES);
								fotoPerfil = StringUtil.replaceStringNew(fotoPerfil, "${clienteid}", String.valueOf(ifcdto.cliente.id));
								fotoPerfil = fotoPerfil + "/" + ifcdto.cliente.fotoPerfil;
							}
							
							primeiroNome = ifcdto.cliente.primeiroNome;
							
							pathIndex = VariavelCache.getInstance().getValor(VariavelConstantes.PATH_PROPRIEDADES_CLIENTE_PROPERTA);
							pathIndex = StringUtil.replaceStringNew(pathIndex, "${CLIENTE_ID}", String.valueOf(idcliente));
							pathIndex = StringUtil.replaceStringNew(pathIndex, TemplateConstantes.TAGC_LANGUAGE, Constantes.LANGUAGE_PT_BR);
							pathIndex = pathIndex + "/" + ifcdto.cliente.idhash + ".html";

							ifcdto.urlFichaImovelEstatica = StringUtil.replaceStringNew( ifcdto.urlFichaImovelEstatica, TemplateConstantes.TAGC_LANGUAGE, Constantes.LANGUAGE_PT_BR);
							
							this.getHTMLInfoboxes(ifcdto);
							
							lstimv.add(ifcdto);
						}
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
						// Monta para com conteudo para substituicao de tags
						//------------------------------------------------------
						
						Map<String,String> conteudo = new HashMap<String, String>();
						conteudo.put(TemplateConstantes.TAGC_URL_HOME_PROPERTA, VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA));
						conteudo.put(TemplateConstantes.TAGC_TITULO_HTML, primeiroNome);
						conteudo.put(TemplateConstantes.TAGC_NOME_CLIENTE, primeiroNome);
						conteudo.put(TemplateConstantes.TAGC_URL_FOTO_CLIENTE, fotoPerfil);
						conteudo.put(TemplateConstantes.TAGC_PROPERTA_IMOVEL_RECENTE_ITEM, this.getHTMLPropertaImoveisRecentes(lstimv));
						conteudo.put(TemplateConstantes.TAGC_PROPERTA_HEADER_TOP, super.getHTMLPropertaHeaderTop(Constantes.LANGUAGE_PT_BR));
						conteudo.put(TemplateConstantes.TAGC_PROPERTA_FOOTER_WRAPPER, super.getHTMLPropertaFooterWrapper(Constantes.LANGUAGE_PT_BR));
						conteudo.put(TemplateConstantes.TAGC_PROPERTA_FOOTER_JAVASCRIPT, super.getHTMLPropertaFooterJS());
						conteudo.put(TemplateConstantes.TAGC_SECAO_HEAD, super.getHTMLPropertaSecaoHead());
						conteudo.put(TemplateConstantes.TAGC_PROPERTA_LAT_LONG, this.getMapCoordenadas());
						conteudo.put(TemplateConstantes.TAGC_PROPERTA_LATI_CENTER, String.valueOf(latcenter));
						conteudo.put(TemplateConstantes.TAGC_PROPERTA_LONG_CENTER, String.valueOf(longcenter));
						conteudo.put(TemplateConstantes.TAGC_PROPERTA_TIPOS, this.getMapTiposImoveis());
						conteudo.put(TemplateConstantes.TAGC_PROPERTA_INFOBOX_PUSH, sbinfoboxpush.toString());


						// Obtem o template
						Template template = TemplateFactory.getInstance(conteudo, TemplateConstantes.TEMPLATE_PROPERTA_PROPRIEDADES_CLIENTE);
						
						FileOutputStream fos = new FileOutputStream(pathIndex);     
						fos.write(template.getHtmlTemplate().getBytes());     
						fos.close();
							
					}
				}


			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private List<Long> listClientesAtivos() {
		List<Long> lst = null;
		
		try {
			// Obtem a lista de clientes
			ClienteService<ClienteDTO> ips = new ClienteServiceImpl();
			lst = ips.listarRegistros(Constantes.ATIVO);
		} catch (AlugueRelaxeException are){
			are.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lst;
	}
	
	private List<ImovelFichaCompletaDTO> listPropriedadesCliente(long idCliente) {
		List<ImovelFichaCompletaDTO> lst = null;
		
		try {
			// Obtem a lista de imoveis que estao disponiveis para publicidade
			ImovelService<ImovelBaseDTO> ips = new ImovelServiceImpl<ImovelPublicidadeDTO>();
			lst = ips.listarGaleriaFotosImoveis(idCliente);
			
		} catch (AlugueRelaxeException are){
			are.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lst;
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
	@Deprecated
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
						htmlitem = StringUtil.replaceStringNew(htmlitem, "${FLAG_ATIVO}", " class=\"active\">");
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

	private String getHTMLPropertaImoveisRecentes(List<ImovelFichaCompletaDTO> lst) {
		final String HTML_ULTIMOS_CADASTRADOS_ITEM = 
		"<div class=\"property clearfix\">" +
            "<div class=\"image\">" +
            "    <a href=\"${LINK_IMOVEL}\">" +
            "        <img width=\"570\" height=\"425\" src=\"${FOTOS}\"" +
            "             class=\"thumbnail-image \" alt=\"${CIDADE} - ${UF}\"/>" +
            "    </a>" +
            "</div>" +
            "<!-- /.image -->" +
            "<div class=\"wrapper\">" +
            "    <div class=\"title\">" +
            "        <h3><a href=\"${LINK_IMOVEL}\">" +
            "${TITULO_ANUNCIO}" +
            "            </a></h3>" +
            "    </div>" +
            "    <!-- /.title -->" +

            "    <div class=\"location\">${CIDADE} - ${UF}</div>" +
            "    <!-- /.location -->" +

            "    <div class=\"price\">" +
            "${VALOR_DIARIA}" +
            "    </div>" +
            "    <!-- /.price -->" +
            "</div>"+
            "<!-- /.wrapper -->" +
        "</div>" +
        "<!-- /.property -->" +
        "<div class=\"property-info clearfix\">" +
            "<div class=\"area\">" +
                "<i class=\"icon icon-normal-cursor-scale-up\"></i>" +
                "800m<sup>2</sup>" +
            "</div>" +
            "<!-- /.area -->" +
            "<div class=\"bedrooms\">" +
                "<i class=\"icon icon-normal-bed\"></i>" +
            "${QUARTOS}" +
            "</div>" +
            "<!-- /.bedrooms -->" +
        "</div>" +
        "<!-- /.info -->";
		
		final StringBuilder sb = new StringBuilder();
		
		if ((lst != null) && (lst.size() > 0)){
			for ( ImovelFichaCompletaDTO ifcdto : lst ) {
				String html = new String(HTML_ULTIMOS_CADASTRADOS_ITEM);
				
				// Troca de tags
				html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade);
				html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
				html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_QUARTOS, String.valueOf(ifcdto.imovel.qtdeQuartos));
				html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_TITULO_ANUNCIO, ifcdto.imovel.descricaoTituloAnuncio);
				
				if(ifcdto.imovel.valorTarifaBasica == 0){
					html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_VALOR_DIARIA, "Consulte-nos" );
				} else {
					try {
						html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_VALOR_DIARIA, VariavelCache.getInstance().getValor(VariavelConstantes.MOEDA_PT_BR ) + 
						" " +
						String.valueOf(ifcdto.imovel.valorTarifaBasica));
					} catch (AlugueRelaxeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
						
				String linkficha = ifcdto.urlFichaImovelEstatica;
				try {
					linkficha = StringUtil.replaceStringNew(linkficha, 
															TemplateConstantes.TAGC_LANGUAGE, 
															VariavelCache.getInstance().getValor(VariavelConstantes.LANGUAGE_PT_BR));
				} catch (AlugueRelaxeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_LINK_IMOVEL, linkficha);
				
				if(ifcdto.imagensImovelXG == null){
					try {
						html = StringUtil.replaceStringNew(html, 
								TemplateConstantes.TAGC_FOTOS, 
															VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA) + "assets/img/property-tmp-small.png");
					} catch (AlugueRelaxeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					String linkfoto = null;
					try {
						linkfoto = VariavelCache.getInstance().getValor(VariavelConstantes.URL_VERIFICAR_IMAGEM_UPLOAD);
					} catch (AlugueRelaxeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					linkfoto = StringUtil.replaceStringNew(linkfoto,"${clienteid}",String.valueOf(ifcdto.cliente.id));
					linkfoto = StringUtil.replaceStringNew(linkfoto,"${imovelid}",String.valueOf(ifcdto.imovel.id));
					linkfoto = StringUtil.replaceStringNew(linkfoto,"${imagemupload}",ifcdto.imagensImovelXG.get(0).nome);
					html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_FOTOS, linkfoto);
				}
				sb.append(html);
			}
		}
		return sb.toString();
	}

	
}
