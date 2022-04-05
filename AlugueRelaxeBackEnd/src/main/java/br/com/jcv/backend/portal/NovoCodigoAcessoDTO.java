package br.com.jcv.backend.portal;

import java.io.Serializable;

import br.com.jcv.backend.dto.DTOPadrao;

public class NovoCodigoAcessoDTO extends DTOPadrao implements Serializable {
	private static final long serialVersionUID = 6647923998174259237L;
	public String email;
	public String codigoAcesso;
	public String codigoHash;
}