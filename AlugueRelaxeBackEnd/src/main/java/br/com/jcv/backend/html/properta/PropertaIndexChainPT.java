package br.com.jcv.backend.html.properta;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.chain.AbstractChain;
import br.com.jcv.backend.chain.Chain;
import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.cidade.CidadeServiceImpl;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.I18n;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelBaseDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeDTO;
import br.com.jcv.backend.interfaces.services.CidadeService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.mensagem.I18nCache;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class PropertaIndexChainPT extends AbstractPropertaChain implements
		Chain<DTOPadrao> {

	public void execute(DTOPadrao context) {
		List<ImovelFichaCompletaDTO> lstPP = null;
		List<ImovelFichaCompletaDTO> lstPD = null;
		List<ImovelFichaCompletaDTO> lstPA = null;
		List<ImovelFichaCompletaDTO> lstAtivos = null;
		List<ImovelFichaCompletaDTO> lstUltimos = null;
		
		try {

			//------------------------------------------------------
			// Obtem o caminho onde sera gravada a pagina index.html
			// se vai popular o google maps com todos os imoveis
			//------------------------------------------------------
			String pathIndex = VariavelCache.getInstance().getValor(VariavelConstantes.PATH_HOME_PROPERTA);
			boolean googleMapsCheio = VariavelCache.getInstance().getValor(VariavelConstantes.CHAVE_PROPERTA_MOSTRAR_TUDO_GOOGLE_MAPS).equals(Constantes.ON); 

			//------------------------------------------------------
			// 1) Solicita servico de publicidades principais - PP
			// 2) Solicita servico de publicidades Destaques - PD
			// 3) Solicita servico de imoveis em situacao de colaboracao P (Patrocinador)
			// 3) Solicita servico de imoveis em situacao de colaboracao C (Colaborador)
			// 4) Solicita servico de ultimos imoveis cadastrados
			//------------------------------------------------------
			lstPP = this.listImovelPP();
			lstPD = this.listImovelPD();
			lstPA = this.listImovelPA();
			lstAtivos = this.listImovelAtivos();
			lstUltimos = this.listUltimosImoveis();
			
			//----------------------------------------------------------------
			// Criterios para montagem do fragmento javascript do google maps
			// obtendo a latitude, longitude e tipo do imóvel
			// 1) somente os DTO que tiverem latlng e longlng populados (!= 0)
			// 2) Somente os imoveis com publicidades PP e PD
			// 3) Somente imoveis em situacao de colaboracao P (Patrocinador)
			// 4) Somente imoveis em situacao de colaboracao C (Colaborador)
			//------------------------------------------------------------------
			// Criacao do fragmento javascript da parte de PP
			//------------------------------------------------------------------
			if ((lstPP != null) && (lstPP.size() > 0) && (!googleMapsCheio)){
				for (ImovelFichaCompletaDTO ifcdtopp : lstPP){
					this.getHTMLInfoboxes(ifcdtopp);
				}
			}

			//------------------------------------------------------------------
			// Criacao do fragmento javascript da parte de PD
			//------------------------------------------------------------------
			if ((lstPD != null) && (lstPD.size() > 0)&& (!googleMapsCheio)){
				for (ImovelFichaCompletaDTO ifcdtopp : lstPD){
					this.getHTMLInfoboxes(ifcdtopp);
				}
			}

			//------------------------------------------------------------------
			// Criacao do fragmento javascript da parte de Patrocinador
			//------------------------------------------------------------------
			if ((lstPA != null) && (lstPA.size() > 0)&& (!googleMapsCheio)){
				for (ImovelFichaCompletaDTO ifcdtopp : lstPA){
					this.getHTMLInfoboxes(ifcdtopp);
				}
			}

			//------------------------------------------------------------------
			// Criacao do fragmento javascript da parte de ultimos imoveis
			//------------------------------------------------------------------
			if ((lstUltimos != null) && (lstUltimos.size() > 0)&& (!googleMapsCheio)){
				for (ImovelFichaCompletaDTO ifcdtopp : lstUltimos){
					this.getHTMLInfoboxes(ifcdtopp);
				}
			}

			//------------------------------------------------------------------
			// Criacao do fragmento javascript da parte de Colaborador
			//------------------------------------------------------------------
			//....

			//------------------------------------------------------------------
			// Criacao do fragmento javascript da parte de Todos imoveis = ON
			//------------------------------------------------------------------
			if ((lstAtivos != null) && (lstAtivos.size() > 0)&& (googleMapsCheio)){
				for (ImovelFichaCompletaDTO ifcdtopp : lstAtivos){
					this.getHTMLInfoboxes(ifcdtopp);
				}
			}
			
			//------------------------------------------------------
			// Realiza a colocacao das propagandas dentro da
			// da area reservada no template com as tags
			// ${PROTERTA_*}
			//------------------------------------------------------
			// Monta para com conteudo para substituicao de tags
			//------------------------------------------------------
			Map<String,String> conteudo = new HashMap<String, String>();
			conteudo.put(TemplateConstantes.TAGC_PROPERTA_IMOVEL_PP_ITEM, getHTMLPropertaPP(lstPP));
			conteudo.put(TemplateConstantes.TAGC_PROPERTA_IMOVEL_PD_ITEM, getHTMLPropertaPD(lstPD));
			conteudo.put(TemplateConstantes.TAGC_PROPERTA_IMOVEL_RECENTE_ITEM, getHTMLPropertaImoveisRecentes(lstUltimos));
			conteudo.put(TemplateConstantes.TAGC_PROPERTA_LAT_LONG, this.getMapCoordenadas());
			conteudo.put(TemplateConstantes.TAGC_PROPERTA_LATI_CENTER, String.valueOf(latcenter));
			conteudo.put(TemplateConstantes.TAGC_PROPERTA_LONG_CENTER, String.valueOf(longcenter));
			conteudo.put(TemplateConstantes.TAGC_PROPERTA_TIPOS, this.getMapTiposImoveis());
			conteudo.put(TemplateConstantes.TAGC_PROPERTA_INFOBOX_PUSH, sbinfoboxpush.toString());
			conteudo.put(TemplateConstantes.TAGC_PROPERTA_ZOOM, VariavelCache.getInstance().getValor(VariavelConstantes.PROPERTA_ZOOM_GOOGLE_MAPS));
			conteudo.put(TemplateConstantes.TAGC_TITULO_HTML, I18nCache.getInstance().getMensagem(I18n.PT_TITULO_PRINCIPAL_HTML));
			conteudo.put(TemplateConstantes.TAGC_URL_HOME_PROPERTA,VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA));			
			conteudo.put(TemplateConstantes.TAGC_COPYRIGHT, I18nCache.getInstance().getMensagem(I18n.PT_DIREITOS_RESERVADOS));
			conteudo.put(TemplateConstantes.TAGC_CIDADES_UF, this.getArrayCidadeUfComImoveis());
			conteudo.put(TemplateConstantes.TAGC_URL_GATEWAY_SERVICES, VariavelCache.getInstance().getValor(VariavelConstantes.URL_GATEWAY_SERVICES));
			conteudo.put(TemplateConstantes.TAGC_PROPERTA_HEADER_TOP, super.getHTMLPropertaHeaderTop(Constantes.LANGUAGE_PT_BR));
			conteudo.put(TemplateConstantes.TAGC_PROPERTA_FOOTER_WRAPPER, super.getHTMLPropertaFooterWrapper(Constantes.LANGUAGE_PT_BR));
			conteudo.put(TemplateConstantes.TAGC_PROPERTA_FOOTER_JAVASCRIPT, super.getHTMLPropertaFooterJS());
			conteudo.put(TemplateConstantes.TAGC_SECAO_HEAD, super.getHTMLPropertaSecaoHead());

			//------------------------------------------------------
			// Cria a pasta de HTMLs estaticos se houver necessidade
			//------------------------------------------------------
			File fromFile = new File(pathIndex + "done.txt");
			String parent = fromFile.getParent();
			
			File dir = new File(parent);
			if (!dir.exists()) {

				if (dir.mkdirs()) {
					// TODO Colocar logger
				} else  {
					throw new IOException("Não foi possível criar diretório de imagens");
				}
			}

			// Obtem o template
			Template template = TemplateFactory.getInstance(conteudo, TemplateConstantes.TEMPLATE_PROPERTA_INDEX);
			
			FileOutputStream fos = new FileOutputStream(pathIndex + "index-pt.html");     
			fos.write(template.getHtmlTemplate().getBytes());     
			fos.close();


		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private List<ImovelFichaCompletaDTO> listImovelPP() {
		List<ImovelFichaCompletaDTO> lst = null;
		
		try {
			// Obtem a lista de imoveis que estao disponiveis para publicidade
			ImovelService<ImovelBaseDTO> ips = new ImovelServiceImpl<ImovelPublicidadeDTO>();
			List<? extends ImovelBaseDTO> publicidades = ips.listImovelPublicidadePaginaPrincipal();

			// Repassa a lista do backend para o cache
			if ((publicidades != null) && (publicidades.size() > 0)) {
				
				// Temos publicidade no periodo, entao inicializa a List
				lst = new ArrayList<ImovelFichaCompletaDTO>();
				
				for (ImovelBaseDTO ibdto : publicidades) {
					ImovelFichaCompletaDTO ifcdto = ips.pesquisarFichaCompletaImovel(ibdto.id);
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

	private List<ImovelFichaCompletaDTO> listImovelPD() {
		List<ImovelFichaCompletaDTO> lst = null;
		
		try {
			// Obtem a lista de imoveis que estao disponiveis para publicidade
			ImovelService<ImovelBaseDTO> ips = new ImovelServiceImpl<ImovelPublicidadeDTO>();
			List<? extends ImovelBaseDTO> publicidades = ips.listImovelPublicidadePaginaDestaque();

			// Repassa a lista do backend para o cache
			if ((publicidades != null) && (publicidades.size() > 0)) {
				
				// Temos publicidade no periodo, entao inicializa a List
				lst = new ArrayList<ImovelFichaCompletaDTO>();
				
				for (ImovelBaseDTO ibdto : publicidades) {
					ImovelFichaCompletaDTO ifcdto = ips.pesquisarFichaCompletaImovel(ibdto.id);
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

	private List<ImovelFichaCompletaDTO> listImovelPA() {
		List<ImovelFichaCompletaDTO> lst = null;
		
		try {
			// Obtem a lista de imoveis que estao disponiveis para publicidade
			ImovelService<ImovelBaseDTO> ips = new ImovelServiceImpl<ImovelPublicidadeDTO>();
			List<Long> lstimv = ips.listarImoveisPatrocinadores();

			// Repassa a lista do backend para o cache
			if ((lstimv != null) && (lstimv.size() > 0)) {
				
				// Temos publicidade no periodo, entao inicializa a List
				lst = new ArrayList<ImovelFichaCompletaDTO>();
				
				for (Long ibdto : lstimv) {
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

	private List<ImovelFichaCompletaDTO> listImovelAtivos() {
		List<ImovelFichaCompletaDTO> lst = null;
		
		try {
			// Obtem a lista de imoveis que estao disponiveis para publicidade
			ImovelService<ImovelBaseDTO> ips = new ImovelServiceImpl<ImovelPublicidadeDTO>();
			List<Long> lstimv = ips.listarImoveis(Constantes.ATIVO);

			// Repassa a lista do backend para o cache
			if ((lstimv != null) && (lstimv.size() > 0)) {
				
				// Temos publicidade no periodo, entao inicializa a List
				lst = new ArrayList<ImovelFichaCompletaDTO>();
				
				for (Long ibdto : lstimv) {
					ImovelFichaCompletaDTO ifcdto = ips.pesquisarFichaCompletaImovel(ibdto.longValue());
					
					//Somente se tiver coordenadas entra
					if (ifcdto != null) {
						if ((ifcdto.geolocalizacao.latitude != 0) && (ifcdto.geolocalizacao.longitude != 0)){
							lst.add(ifcdto);
						}
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

	private List<ImovelFichaCompletaDTO> listUltimosImoveis() {
		List<ImovelFichaCompletaDTO> lst = null;
		
		try {
			// Obtem a lista de imoveis que estao disponiveis para publicidade
			ImovelService<ImovelBaseDTO> ips = new ImovelServiceImpl<ImovelPublicidadeDTO>();
			lst = ips.listarUltimosImoveisCadastrados();

		} catch (AlugueRelaxeException are){
			are.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lst;
	
	}

	private String getHTMLPropertaPP(List<ImovelFichaCompletaDTO> lst) {
		StringBuilder sb = new StringBuilder();

		if ((lst != null) && (lst.size() > 0)){
			for ( ImovelFichaCompletaDTO ifcdto : lst ) {
				try {
					ifcdto.urlFichaImovelEstatica = StringUtil.replaceStringNew(ifcdto.urlFichaImovelEstatica, 
					TemplateConstantes.TAGC_LANGUAGE,
					VariavelCache.getInstance().getValor(VariavelConstantes.LANGUAGE_PT_BR));
				} catch (AlugueRelaxeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// monta o fragmento bootstrap para template Properta
				sb.append("<div class=\"span3\">");
				sb.append("<div class=\"property\">");
	            sb.append("<div class=\"image\">");
	            sb.append("    <div class=\"content\">");
	            sb.append("        <a href=\""+ ifcdto.urlFichaImovelEstatica + "\">");
	            sb.append("            <div class=\"description\"><p>" + ifcdto.imovel.descricaoGeral + "</p></div>");
				if (ifcdto.imagensImovelXG == null){
					// imovel sem foto
	                try {
						sb.append("        <img src=\"" + VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA) + "assets/img/property-tmp-small.png\" alt=\""+ifcdto.imovel.endereco.cidade + " - " + ifcdto.imovel.endereco.uf + "\">");
					} catch (AlugueRelaxeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// Obtem o caminho da foto e substitui as tags
					// http://www.aluguerelaxe.com.br/stream/fotos/${clienteid}/${imovelid}/${imagemupload}');
					String urlFoto = "";
					try {
						urlFoto = VariavelCache.getInstance().getValor(VariavelConstantes.URL_VERIFICAR_IMAGEM_UPLOAD);
					} catch (AlugueRelaxeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					urlFoto = StringUtil.replaceStringNew(urlFoto,"${clienteid}",String.valueOf(ifcdto.cliente.id));
					urlFoto = StringUtil.replaceStringNew(urlFoto,"${imovelid}",String.valueOf(ifcdto.imovel.id));
					urlFoto = StringUtil.replaceStringNew(urlFoto,"${imagemupload}",ifcdto.imagensImovelXG.get(0).nome);
					
					/*
					testar com este class thumbnail-image
					<img width="570" height="425" src="assets/img/property-tmp-small.png"
                             class="thumbnail-image " alt="6"/>
					*/		 
	                sb.append("        <img width=\"270\" height=\"200\" src=\"" + urlFoto + "\" class=\"thumbnail-image \" alt=\""+ifcdto.imovel.endereco.cidade + " - " + ifcdto.imovel.endereco.uf + "\">");
	                        //<img src="http://www.aluguerelaxe.com.br/stream/fotos/74/100/C74I100ID1303216264622-XG.jpg" alt="Salvador - BA">
				}
	            sb.append("        </a>");
	            sb.append("    </div>");
	            sb.append("    <!-- /.content -->");

	            sb.append("    <div class=\"rent-sale\">");
	            sb.append("Temporada"); 
	            sb.append("    </div>");
	            sb.append("    <!-- /.rent-sale -->");

	            sb.append("    <div class=\"price\">");
				if (ifcdto.imovel.valorTarifaBasica > 0) {
					try {
						sb.append("A partir de " + VariavelCache.getInstance().getValor(VariavelConstantes.MOEDA_PT_BR ) + " " + ifcdto.imovel.valorTarifaBasica);
					} catch (AlugueRelaxeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					sb.append("Consulte-nos");
				}
	            sb.append("    </div>");
	            sb.append("    <!-- /.price -->");
					
	            sb.append("    <div class=\"reduced\">");
	            sb.append(ifcdto.imovel.indicadorTipoPropriedadeStr.toUpperCase());      //  Casa/Apartameto/Sitio/Chacara
	            sb.append("    </div>");
	            sb.append("    <!-- /.reduced -->");
					
	            sb.append("</div>");
	            sb.append("<!-- /.image -->");

	            sb.append("<div class=\"info\">");
	            sb.append("    <div class=\"title clearfix\">");
	            sb.append("        <h2><a href=\"" + ifcdto.urlFichaImovelEstatica + "\">");
	            sb.append(ifcdto.imovel.descricaoTituloAnuncio); // Colocar o titulo do imovel aqui
	            sb.append("            </a></h2>");
	            sb.append("    </div>");
	            sb.append("    <!-- /.title -->");

	            sb.append("    <div class=\"location\">" + ifcdto.imovel.endereco.cidade + " - " + ifcdto.imovel.endereco.uf + "</div>");
				sb.append("<!-- /.location -->");
	            sb.append("</div>");
	            sb.append("<!-- /.info -->");

				sb.append("</div>");
				sb.append("<!-- /.property -->");

				sb.append("<div class=\"property-info clearfix\">");
	            sb.append("   <div class=\"area\">");
	            sb.append("    <i class=\"icon icon-normal-cursor-scale-up\"></i>");
	            sb.append("    450m<sup>2</sup>");
	            sb.append("   </div>");
	            sb.append("<!-- /.area -->");

	            sb.append("   <div class=\"bedrooms\">");
	            sb.append("    <i class=\"icon icon-normal-bed\"></i>");
	            sb.append(ifcdto.imovel.qtdeQuartos); // Quartos
	            sb.append("   </div>");
	            sb.append("<!-- /.bedrooms -->");

	            sb.append("   <div class=\"bathrooms\">");
	            sb.append("    <i class=\"icon icon-normal-shower\"></i>");
	            sb.append(ifcdto.imovel.qtdeBanheiros); // Banheiros    2
	            sb.append("   </div>");
	            sb.append("<!-- /.bathrooms -->");
				sb.append("</div>");
				sb.append("<!-- /.property-info -->");

				sb.append("</div>");
				sb.append("<!-- /.span4 -->");
			}
			
		}
		
		return sb.toString();
	}

	private String getHTMLPropertaPD(List<ImovelFichaCompletaDTO> lst) {
		StringBuilder sb = new StringBuilder();

		if ((lst != null) && (lst.size() > 0)){
			for ( ImovelFichaCompletaDTO ifcdto : lst ) {
				try {
					ifcdto.urlFichaImovelEstatica = StringUtil.replaceStringNew(ifcdto.urlFichaImovelEstatica
													,TemplateConstantes.TAGC_LANGUAGE
													,VariavelCache.getInstance().getValor(VariavelConstantes.LANGUAGE_PT_BR));
				} catch (AlugueRelaxeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// monta o fragmento bootstrap para template Properta
				sb.append("<div class=\"span3\">");
				sb.append("    <div class=\"property\">");
				sb.append("        <div class=\"image\">");
				sb.append("            <div class=\"content\">");
	            sb.append("               <a href=\""+ ifcdto.urlFichaImovelEstatica + "\">");
	            sb.append("                    <div class=\"description\"><p>" + ifcdto.imovel.descricaoTituloAnuncio + "</p></div>");
				if (ifcdto.imagensImovelXG == null){
					// imovel sem foto
	                try {
						sb.append("        <img src=\"" + VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA) + "assets/img/property-tmp-small.png\" alt=\""+ifcdto.imovel.endereco.cidade + " - " + ifcdto.imovel.endereco.uf + "\">");
					} catch (AlugueRelaxeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// Obtem o caminho da foto e substitui as tags
					// http://www.aluguerelaxe.com.br/stream/fotos/${clienteid}/${imovelid}/${imagemupload}');
					String urlFoto = "";
					try {
						urlFoto = VariavelCache.getInstance().getValor(VariavelConstantes.URL_VERIFICAR_IMAGEM_UPLOAD);
					} catch (AlugueRelaxeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					urlFoto = StringUtil.replaceStringNew(urlFoto,"${clienteid}",String.valueOf(ifcdto.cliente.id));
					urlFoto = StringUtil.replaceStringNew(urlFoto,"${imovelid}",String.valueOf(ifcdto.imovel.id));
					urlFoto = StringUtil.replaceStringNew(urlFoto,"${imagemupload}",ifcdto.imagensImovelXG.get(0).nome);
	                sb.append("        <img width=\"270\" height=\"200\" src=\"" + urlFoto + "\" class=\"thumbnail-image \" alt=\""+ifcdto.imovel.endereco.cidade + " - " + ifcdto.imovel.endereco.uf + "\">");
	                        //<img src="http://www.aluguerelaxe.com.br/stream/fotos/74/100/C74I100ID1303216264622-XG.jpg" alt="Salvador - BA">
				}
				sb.append("                </a>");
				sb.append("            </div>");
				sb.append("            <!-- /.content -->");

				sb.append("            <div class=\"rent-sale\">");
				sb.append("Temporada");
				sb.append("            </div>");
				sb.append("            <!-- /.rent-sale -->");

				sb.append("            <div class=\"price\">");
				if (ifcdto.imovel.valorTarifaBasica > 0) {
					try {
						sb.append("A partir de " +VariavelCache.getInstance().getValor(VariavelConstantes.MOEDA_PT_BR ) + " " + ifcdto.imovel.valorTarifaBasica);
					} catch (AlugueRelaxeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					sb.append("Consulte-nos");
				}
				sb.append("            </div>");
				sb.append("            <!-- /.price -->");

				sb.append("        </div>");
				sb.append("        <!-- /.image -->");

				sb.append("        <div class=\"info\">");
				sb.append("            <div class=\"title clearfix\">");
				sb.append("                <h2><a href=\"" + ifcdto.urlFichaImovelEstatica +"\">");
	            sb.append(ifcdto.imovel.indicadorTipoPropriedadeStr.toUpperCase());      //  Casa/Apartameto/Sitio/Chacara
				sb.append("                    </a></h2>");
				sb.append("            </div>");
				sb.append("            <!-- /.title -->");

				sb.append("            <div class=\"location\">" + ifcdto.imovel.endereco.cidade + " - " + ifcdto.imovel.endereco.uf + "</div>");
				sb.append("            <!-- /.location -->");
				sb.append("        </div>");
				sb.append("        <!-- /.info -->");

				sb.append("    </div>");
				sb.append("    <!-- /.property -->");

				sb.append("    <div class=\"property-info clearfix\">");
				sb.append("        <div class=\"area\">");
				sb.append("            <i class=\"icon icon-normal-cursor-scale-up\"></i>");
				sb.append("            800m<sup>2</sup>");
				sb.append("        </div>");
				sb.append("        <!-- /.area -->");

				sb.append("        <div class=\"bedrooms\">");
				sb.append("            <i class=\"icon icon-normal-bed\"></i>");
	            sb.append(ifcdto.imovel.qtdeQuartos); // Quartos
				sb.append("        </div>");
				sb.append("        <!-- /.bedrooms -->");

				sb.append("    </div>");
				sb.append("    <!-- /.property-info -->");

				sb.append("</div>");
			}
		}
		
		return sb.toString();
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

	//----------------------------------------------------------------------------------------
	// Devolve um String em formato array na seguinte disposicao
	// ["Volta Redonda, RJ (4567)","Rio de Janeiro, RJ(7567)", ... ]
	// 1o. Elemento = Cidade
	// 2o. Elemento = UF
	// 3o. Elemento = Codigo de relacionamento
	//----------------------------------------------------------------------------------------
	private String getArrayCidadeUfComImoveis() {
	//["Volta Redonda, RJ (4567)","Rio de Janeiro, RJ (7567)","Angra dos Reis, RJ (9786)"]
	
		String retorno = "";
		try {
			StringBuilder array = new StringBuilder();
			// Obtem a lista de Cidade x UF
			CidadeService<CidadeDTO> ips = new CidadeServiceImpl();
			List<CidadeUFDTO> lst = ips.listCidadesDaUfComImoveis();

			// Repassa a lista do backend
			if ((lst != null) && (lst.size() > 0)) {
				
				// monta a string conforme padrao
				array.append("[");
				for (CidadeUFDTO ibdto : lst) {
					array.append("\"");
					array.append(ibdto.cidade.nome);
					array.append(",");
					array.append(ibdto.uf);
					array.append(" (");
					array.append(String.valueOf(ibdto.id));
					array.append(")\",");
				}
				
				retorno = array.toString();
				// remove a virgula
				if (retorno.length() > 0){
					retorno = retorno.substring(0,retorno.length() - 1);
				}
				retorno = retorno + "]";
			}
		} catch (AlugueRelaxeException are){
			are.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
}

