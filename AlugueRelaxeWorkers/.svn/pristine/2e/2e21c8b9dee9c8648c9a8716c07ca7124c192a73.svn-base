package br.com.jcv.backend.atalho;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.chain.AbstractChain;
import br.com.jcv.backend.chain.Chain;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelBaseDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class AtalhoFichaImovelChain extends AbstractChain implements
		Chain<DTOPadrao> {

	public void execute(DTOPadrao context) {
		
		try {
			// Busca lista de imoveis para criacao de atalhos
			List<ImovelFichaCompletaDTO> lst = this.listImovelPP();
			
			if ((lst != null) && (lst.size() > 0)){
				for ( ImovelFichaCompletaDTO ifcdto : lst){
					
					// Caminho completo de onde a ficha do imovel sera criada
					String pathIndex = VariavelCache.getInstance().getValor(VariavelConstantes.PATH_ATALHO_FICHA_IMOVEL);
					pathIndex = StringUtil.replaceStringNew(pathIndex, "${TIPO}", ifcdto.imovel.indicadorTipoPropriedadeStr.toLowerCase());
					pathIndex = StringUtil.replaceStringNew(pathIndex, "${UF}", ifcdto.imovel.endereco.uf.toLowerCase());
					pathIndex = StringUtil.replaceStringNew(pathIndex, "${CIDADE}", StringUtil.normalizar(ifcdto.imovel.endereco.cidade.toLowerCase()));
					pathIndex = StringUtil.replaceStringNew(pathIndex, "${TITULO_IMOVEL}", StringUtil.normalizar(ifcdto.imovel.descricaoTituloAnuncio));

					/*
					pathIndex = StringUtil.replaceStringNew(pathIndex, "${ID_IMOVEL}", String.valueOf(ifcdto.imovel.id));
					pathIndex = StringUtil.replaceStringNew(pathIndex, TemplateConstantes.TAGC_LANGUAGE, Constantes.LANGUAGE_PT_BR);
					*/

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

					String linkImovel = "";
					try {
						linkImovel = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_OFERECIMENTO);
						linkImovel = StringUtil.replaceStringNew(linkImovel, "${id}", String.valueOf(ifcdto.imovel.id));
						
					} catch (AlugueRelaxeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Map<String,String> conteudo = new HashMap<String, String>();
					conteudo.put(TemplateConstantes.TAGC_URL, linkImovel);

					// Obtem o template
					Template template = TemplateFactory.getInstance(conteudo, TemplateConstantes.TEMPLATE_ATALHO_FICHA_IMOVEL);
					
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
}
