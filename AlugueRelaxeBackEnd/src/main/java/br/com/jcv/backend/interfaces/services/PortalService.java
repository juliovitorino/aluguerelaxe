
package br.com.jcv.backend.interfaces.services;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.portal.NovoCodigoAcessoDTO;
import br.com.jcv.backend.portal.faleconosco.FaleConoscoDTO;
import br.com.jcv.backend.portal.login.LoginDTO;

public interface PortalService {
	
	DTOPadrao login(LoginDTO dto) throws AlugueRelaxeException;
	DTOPadrao executeFaleConosco(FaleConoscoDTO dto) throws AlugueRelaxeException;
	DTOPadrao solicitarNovoCodigoAcesso(NovoCodigoAcessoDTO dto) throws AlugueRelaxeException;

}

