package br.com.jcv.backend.painelcontrole;

import java.io.Serializable;
import java.util.Date;

import br.com.jcv.backend.dto.DTOPadrao;

public class PanoramaPublicidadeDTO extends DTOPadrao implements Serializable{

	private static final long serialVersionUID = -5262924103281889408L;
	public Date dtmaximaPP;
	public Date dtmaximaPD;
}
