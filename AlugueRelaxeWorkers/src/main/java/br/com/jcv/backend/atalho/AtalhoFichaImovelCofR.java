package br.com.jcv.backend.atalho;

import br.com.jcv.backend.chain.AbstractChainOfResponsability;
import br.com.jcv.backend.dto.DTOPadrao;

public class AtalhoFichaImovelCofR extends AbstractChainOfResponsability<DTOPadrao> {

	@Override
	public void setContext(DTOPadrao pdto) {
		this.pdto = pdto;
	}

}
