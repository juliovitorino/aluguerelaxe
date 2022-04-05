
package br.com.jcv.backend.robot;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2>DerbyRobot</h2>
 *<p>Robot verificadora dos contatos pendentes com anunciantes</p>
 * @author elmt
 *
 */
public class DerbyRobot extends Robot {

	public DerbyRobot() {
		super("Derby");
		this.setFuncao("Verificadora dos contatos PENDENTES com anunciantes");
	}

	public void executar() {
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO);
		
		ImovelService is = new ImovelServiceImpl();
		try {
			List<ContatoClienteDTO> ccdto = is.listarContatosAnunciantesPendentes();
			for (ContatoClienteDTO item : ccdto) {
				
				String linkAprovar = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_LIBERAR_PARA_CONTATO);
				linkAprovar = StringUtil.replaceStringNew(linkAprovar, "${id}", item.id);
				linkAprovar = StringUtil.replaceStringNew(linkAprovar, "${action}", Constantes.APROVAR);

				String linkReprovar = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_LIBERAR_PARA_CONTATO);
				linkReprovar = StringUtil.replaceStringNew(linkReprovar, "${id}", item.id);
				linkReprovar = StringUtil.replaceStringNew(linkReprovar, "${action}", Constantes.REPROVAR);

				Map<String,String> conteudo = new HashMap<String, String>();
				conteudo.put(TemplateConstantes.TCAP_TAG_DATA_CONTATO, item.dataCadastro.toString());
				conteudo.put(TemplateConstantes.TCAP_TAG_ID_IMOVEL, String.valueOf(item.idImovel));
				conteudo.put(TemplateConstantes.TCAP_TAG_NOME_PROPOSTO, item.proposto);
				conteudo.put(TemplateConstantes.TCAP_TAG_ID_IMOVEL, String.valueOf(item.idImovel));
				conteudo.put(TemplateConstantes.TCAP_TAG_NOME_PROPOSTO, item.proposto);
				conteudo.put(TemplateConstantes.TCAP_TAG_NOME_ANUNCIANTE, item.anunciante.nome);
				conteudo.put(TemplateConstantes.TCAP_TAG_CIDADE_PROPOSTO, item.cidade);
				conteudo.put(TemplateConstantes.TCAP_TAG_UF_PROPOSTO, item.uf);
				conteudo.put(TemplateConstantes.TCAP_TAG_EMAIL_PROPOSTO, item.email);
				conteudo.put(TemplateConstantes.TCAP_TAG_DDD_PROPOSTO, item.ddd);
				conteudo.put(TemplateConstantes.TCAP_TAG_FONE_PROPOSTO,item.telefone);
				conteudo.put(TemplateConstantes.TCAP_TAG_CHEGADA_PREVISTA_PROPOSTO, item.chegadaPrevista.toString());
				conteudo.put(TemplateConstantes.TCAP_TAG_PARTIDA_PREVISTA_PROPOSTO, item.partidaPrevista.toString());
				conteudo.put(TemplateConstantes.TCAP_TAG_ADULTOS_PROPOSTO, String.valueOf(item.qtdeAdultos));
				conteudo.put(TemplateConstantes.TCAP_TAG_CRIANCAS_PROPOSTO, String.valueOf(item.qtdeCriancas));
				conteudo.put(TemplateConstantes.TCAP_TAG_INFO_ADICIONAL_PROPOSTO, item.informacoesAdicionais);
				conteudo.put(TemplateConstantes.TCAP_TAG_LINK_LIBERAR_PARA_CONTATO, linkAprovar);
				conteudo.put(TemplateConstantes.TCAP_TAG_LINK_REPROVAR_PARA_CONTATO, linkReprovar);
				
				Email email = EmailFactory.getInstanceEmailContatoAnunciantePendente(conteudo);
				
				ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
				lst.add(new EmailRecord(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE),
						"Administrador"));

				email.enviar(lst, null, "Verificar conteúdo impróprio", null);
				
			}
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Itera a lista e enviando cada e-mail ao administrador
		
		
		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO);
	}

}

