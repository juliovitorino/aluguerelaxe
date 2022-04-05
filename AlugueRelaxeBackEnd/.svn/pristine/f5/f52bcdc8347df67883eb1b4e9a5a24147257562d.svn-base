package br.com.jcv.backend.template;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.TemplateBusiness;
import br.com.jcv.backend.interfacesdao.TemplateDAO;



public class TemplateBusinessImpl implements TemplateBusiness {

	public TemplateDTO procurar(DAOFactory daofactory, TemplateDTO dto)
			throws AlugueRelaxeException {
		TemplateDTO retorno = null;
		if (dto != null){
			TemplateDAO dao = daofactory.getTemplateDAO(daofactory);
			retorno = dao.load(dto);
		}
		return retorno;
	}
	
	public List<TemplateDTO> buscarTodas(DAOFactory daofactory, String status)
			throws AlugueRelaxeException {
		TemplateDAO dao = daofactory.getTemplateDAO(daofactory);
		return dao.list(status);
	}

	public void setDados(TemplateDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public TemplateDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(TemplateDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public TemplateDTO incluir(DAOFactory daofactory, TemplateDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public TemplateDTO atualizar(DAOFactory daofactory, TemplateDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, TemplateDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<TemplateDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TemplateDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

}