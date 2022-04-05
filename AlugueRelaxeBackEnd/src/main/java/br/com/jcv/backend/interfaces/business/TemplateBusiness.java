package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.template.TemplateDTO;


public interface TemplateBusiness extends BusinessObject<TemplateDTO> {
	public List<TemplateDTO> buscarTodas(DAOFactory daofactory, String status) throws AlugueRelaxeException;

}