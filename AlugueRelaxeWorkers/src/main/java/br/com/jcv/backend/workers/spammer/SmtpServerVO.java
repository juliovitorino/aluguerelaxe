package br.com.jcv.backend.workers.spammer;

import java.io.Serializable;

public class SmtpServerVO implements Serializable {

	private static final long serialVersionUID = 5343655841624126996L;
	
	public String nome;
	public String porta;
	public String contaAutenticacao;
	public String aliaspwd;

}
