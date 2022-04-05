package br.com.jcv.backend.workers.spammer.email;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SpammerMailAutenticador extends Authenticator {
	private String conta = null;
	private String pwd = null;
	
	public SpammerMailAutenticador(String conta, String pwd) {
		this.conta = conta;
		this.pwd = pwd;
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(this.conta, pwd);
	  }


}
