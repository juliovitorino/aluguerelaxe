package br.com.jcv.backend.tipoalerta;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.TipoAlertaBusiness;
import br.com.jcv.backend.interfacesdao.TipoAlertaDAO;

public class TipoAlertaBusinessImpl implements TipoAlertaBusiness {
	public List<TipoAlertaDTO> listarTiposAlerta(DAOFactory daofactory, String status) throws AlugueRelaxeException {
		TipoAlertaDAO dao = daofactory.getTipoAlertaDAO(daofactory);
		return dao.list(status);
	}

	public void setDados(TipoAlertaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public TipoAlertaDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(TipoAlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public TipoAlertaDTO incluir(DAOFactory daofactory, TipoAlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public TipoAlertaDTO atualizar(DAOFactory daofactory, TipoAlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, TipoAlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public TipoAlertaDTO procurar(DAOFactory daofactory, TipoAlertaDTO dto)
			throws AlugueRelaxeException {
		TipoAlertaDAO dao = daofactory.getTipoAlertaDAO(daofactory);
		return dao.load(dto);
	}

	public List<TipoAlertaDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TipoAlertaDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
}