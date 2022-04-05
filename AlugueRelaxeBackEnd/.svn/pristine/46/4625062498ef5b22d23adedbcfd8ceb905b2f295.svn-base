
package br.com.jcv.backend.email;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AlugueRelaxeMailAutenticador extends Authenticator {
	public PasswordAuthentication getPasswordAuthentication() {
	    try {
			return new PasswordAuthentication(VariavelCache.getInstance().getValor(VariavelConstantes.SMTP_CONTA_ALUGUERELAXE),
					VariavelCache.getInstance().getValor(VariavelConstantes.SMTP_PASSWD));
		} catch (AlugueRelaxeException e) {
			e.printStackTrace();
		}
		return null;
	  }


}

