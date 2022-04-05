
package br.com.jcv.backend.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.ClienteServiceImpl;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ClienteService;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
 * <h2>DerbyRobot</h2>
 *<p>Robot verificadora dos contatos pendentes com anunciantes</p>
 * @author elmt
 *
 */
public class JessyRobot extends Robot {

	public JessyRobot() {
		super("Jessy");
		this.setFuncao("Entregadora do e-mail com link de ativação de nova conta");
	}

	public void executar() {
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO);
		
		ClienteService<ClienteDTO> is = new ClienteServiceImpl();
		try {
			List<ClienteDTO> ccdto = is.listarNovasContasPendentesAtivacao();
			if (ccdto != null){
				for (ClienteDTO item : ccdto) {
					String linkAtivacao = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_ATIVACAO);
					linkAtivacao = StringUtil.replaceStringNew(linkAtivacao, "${hash_ativacao}", item.codigoHashAtivacao);
					
					Map<String,String> conteudo = new HashMap<String, String>();
					conteudo.put(TemplateConstantes.TANC_TAG_CGC_CPF_NOVO_CLIENTE, (item.cgc == null ? item.cpf : item.cgc ));
					conteudo.put(TemplateConstantes.TANC_TAG_EMAIL_NOVO_CLIENTE, item.email);
					conteudo.put(TemplateConstantes.TANC_TAG_LINK_ATIVACAO_NOVO_CLIENTE, linkAtivacao);
					conteudo.put(TemplateConstantes.TANC_TAG_NOME_NOVO_CLIENTE, item.nome);
					
					Email email = EmailFactory.getInstanceEmailAtivarNovaConta(conteudo);
					
					ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
					lst.add(new EmailRecord(item.email,item.nome));
					
					email.enviar(lst, null, "AlugueRelaxe - Ativação de conta", null);
					
				}
			}
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Itera a lista e enviando cada e-mail ao administrador
		
		
		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO);
	}

}

