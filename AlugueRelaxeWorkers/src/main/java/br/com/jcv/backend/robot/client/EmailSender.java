
package br.com.jcv.backend.robot.client;

import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmailSender {

	/**
	 * TODO Comentar aqui.
	 *
	 * @param args Comentar aqui.
	 */
	public static void main(String[] args) {
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord("julio.vitorino@ig.com.br","Julio Vitorino"));
		lst.add(new EmailRecord("julio.vitorino@gmail.com","Julio Vitorino"));
		
		String assunto = "Relacionamento AlugueRelaxe - Cadastro não concluído";
		String textoEmail = "Texto do e-mail";
		
		Map<String,String> conteudo = new HashMap<String,String>();
		conteudo.put("${DATA_CADASTRO_IMCOMPLETO}", "15/02/1972");
		
		Email email = EmailFactory.getInstanceEmailCadastroClienteImcompleto(conteudo);
		try {
			email.enviar(lst, null, assunto, null);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

