package br.com.jcv.backend.plano;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.PlanoBusiness;
import br.com.jcv.backend.interfacesdao.PlanoDAO;

public class PlanoBusinessImpl implements PlanoBusiness<PlanoDTO> {

	public void setDados(PlanoDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public PlanoDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(PlanoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public PlanoDTO incluir(DAOFactory daofactory, PlanoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public PlanoDTO atualizar(DAOFactory daofactory, PlanoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, PlanoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public PlanoDTO procurar(DAOFactory daofactory, PlanoDTO dto)
			throws AlugueRelaxeException {
		PlanoDAO<PlanoDTO> dao = daofactory.getPlanoDAO(daofactory);
		return dao.load(dto);
	}

	public List<PlanoDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		PlanoDAO<PlanoDTO> dao = daofactory.getPlanoDAO(daofactory);
		return dao.list();
	}

	public List<PlanoDTO> buscarTodas(DAOFactory daofactory, String tipo)
		throws AlugueRelaxeException {
		PlanoDAO<PlanoDTO> dao = daofactory.getPlanoDAO(daofactory);
		return dao.list(tipo);
	}

	public List<PlanoDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

}
