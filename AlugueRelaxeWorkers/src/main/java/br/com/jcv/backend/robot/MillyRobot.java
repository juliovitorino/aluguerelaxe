
package br.com.jcv.backend.robot;

import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.template.TemplateConstantes;

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
public class MillyRobot extends Robot {

	public MillyRobot() {
		super("Milly");
		this.setFuncao("Verificadora dos contatos LIBERADOS com anunciantes");
	}

	public void executar() {
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO);
		
		ImovelService is = new ImovelServiceImpl();
		try {
			List<ContatoClienteDTO> ccdto = is.listarContatosAnunciantesLiberados();
			for (ContatoClienteDTO item : ccdto) {
				
				Map<String,String> conteudo = new HashMap<String, String>();
				conteudo.put(TemplateConstantes.TCAL_TAG_ID_IMOVEL, String.valueOf(item.idImovel));
				conteudo.put(TemplateConstantes.TCAL_TAG_NOME_PROPOSTO, item.proposto);
				conteudo.put(TemplateConstantes.TCAL_TAG_NOME_ANUNCIANTE, item.anunciante.nome);
				conteudo.put(TemplateConstantes.TCAL_TAG_CIDADE_PROPOSTO, item.cidade);
				conteudo.put(TemplateConstantes.TCAL_TAG_UF_PROPOSTO, item.uf);
				conteudo.put(TemplateConstantes.TCAL_TAG_EMAIL_PROPOSTO, item.email);
				conteudo.put(TemplateConstantes.TCAL_TAG_DDD_PROPOSTO, item.ddd);
				conteudo.put(TemplateConstantes.TCAL_TAG_FONE_PROPOSTO,item.telefone);
				conteudo.put(TemplateConstantes.TCAL_TAG_CHEGADA_PREVISTA_PROPOSTO, item.chegadaPrevista.toString());
				conteudo.put(TemplateConstantes.TCAL_TAG_PARTIDA_PREVISTA_PROPOSTO, item.partidaPrevista.toString());
				conteudo.put(TemplateConstantes.TCAL_TAG_ADULTOS_PROPOSTO, String.valueOf(item.qtdeAdultos));
				conteudo.put(TemplateConstantes.TCAL_TAG_CRIANCAS_PROPOSTO, String.valueOf(item.qtdeCriancas));
				conteudo.put(TemplateConstantes.TCAL_TAG_INFO_ADICIONAL_PROPOSTO, item.informacoesAdicionais);
				
				Email email = EmailFactory.getInstanceEmailContatoAnuncianteLiberado(conteudo);
				
				ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
				lst.add(new EmailRecord(item.anunciante.email,item.anunciante.nome));

				email.enviar(lst, null, "AlugueRelaxe - Um cliente quer mais informações do seu imóvel", null);
				
			}
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Itera a lista e enviando cada e-mail ao administrador
		
		
		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO);
	}

}

