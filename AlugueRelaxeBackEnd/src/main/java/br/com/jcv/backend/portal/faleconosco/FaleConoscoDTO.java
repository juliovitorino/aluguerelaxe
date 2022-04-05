package br.com.jcv.backend.portal.faleconosco;

import java.io.Serializable;

import br.com.jcv.backend.dto.DTOPadrao;

public class FaleConoscoDTO extends DTOPadrao implements Serializable {
	private static final long serialVersionUID = -4169175101909194198L;
	public String nome;
	public String email;
	public String assunto;
	public String topico;
	public String mensagem;
}
