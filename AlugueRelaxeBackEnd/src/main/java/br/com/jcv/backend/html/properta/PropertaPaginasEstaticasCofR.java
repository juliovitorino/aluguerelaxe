package br.com.jcv.backend.html.properta;

import br.com.jcv.backend.chain.AbstractChainOfResponsability;
import br.com.jcv.backend.dto.DTOPadrao;

public class PropertaPaginasEstaticasCofR extends AbstractChainOfResponsability<DTOPadrao> {

	@Override
	public void setContext(DTOPadrao pdto) {
		this.pdto = pdto;
	}

}
