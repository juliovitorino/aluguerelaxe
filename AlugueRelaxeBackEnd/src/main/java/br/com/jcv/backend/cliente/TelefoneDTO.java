package br.com.jcv.backend.cliente;

import java.io.Serializable;

import br.com.jcv.backend.dto.DTOPadrao;

public class TelefoneDTO extends DTOPadrao implements Serializable {
	
	private static final long serialVersionUID = 7394388437113251718L;

	public String nomeContato;
	public String ddd;
	public String telefone;
	public String indPermiteExibir;
	public String indTipoTelefone;

}
