package br.com.jcv.backend.imovel;

import java.io.Serializable;

import br.com.jcv.backend.dto.DTOPadrao;

public class IndicarAmigoDTO extends DTOPadrao implements Serializable {

	private static final long serialVersionUID = 4325379653907017977L;
	public String seunome;
	public String seuemail;
	public String nomeamigo;
	public String emailamigo;
	public String mensagem;
}
