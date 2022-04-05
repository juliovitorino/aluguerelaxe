package br.com.jcv.backend.modopublicidade;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.ModoPublicidadeBusiness;
import br.com.jcv.backend.interfacesdao.ModoPublicidadeDAO;

public class ModoPublicidadeBusinessImpl implements ModoPublicidadeBusiness<ModoPublicidadeDTO> {

	public ModoPublicidadeDTO procurar(DAOFactory daofactory, ModoPublicidadeDTO dto)
			throws AlugueRelaxeException {
		ModoPublicidadeDAO<ModoPublicidadeDTO> dao = daofactory.getModoPublicidadeDAO(daofactory);
		return dao.load(dto);
	}

	public List<ModoPublicidadeDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		ModoPublicidadeDAO<ModoPublicidadeDTO> dao = daofactory.getModoPublicidadeDAO(daofactory);
		return dao.list();
	}

	public void setDados(ModoPublicidadeDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ModoPublicidadeDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(ModoPublicidadeDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ModoPublicidadeDTO incluir(DAOFactory daofactory,
			ModoPublicidadeDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ModoPublicidadeDTO atualizar(DAOFactory daofactory,
			ModoPublicidadeDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, ModoPublicidadeDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<ModoPublicidadeDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

}
