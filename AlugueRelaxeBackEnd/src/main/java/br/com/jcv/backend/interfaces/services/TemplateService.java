package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.template.TemplateDTO;

public interface TemplateService extends ApplicationService<TemplateDTO> {
	public List<TemplateDTO> listarRegistros(String status) throws AlugueRelaxeException;
	
}
