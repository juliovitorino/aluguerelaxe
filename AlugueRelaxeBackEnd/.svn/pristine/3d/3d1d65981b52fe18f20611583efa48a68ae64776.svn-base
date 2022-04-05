package br.com.jcv.backend.alerta24h;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.AlertaBusiness;
import br.com.jcv.backend.interfacesdao.AlertaDAO;

public class AlertaBusinessImpl implements AlertaBusiness {

	public void enfileirarAlerta(DAOFactory daofactory, AlertaDTO dto) throws AlugueRelaxeException {
		// Ignora se o imovel alertado for igual ao imovel visitado
		if (dto.contato.idImovel != dto.ifcdto.imovel.id){
			AlertaDAO dao = daofactory.getAlertaDAO(daofactory);
			dto.id = daofactory.getNextSequence(daofactory, "SEQ_ALER_ID");
			dao.insert(dto);
		}
	}

	public void setDados(AlertaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public AlertaDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(AlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public AlertaDTO incluir(DAOFactory daofactory, AlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public AlertaDTO atualizar(DAOFactory daofactory, AlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, AlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public AlertaDTO procurar(DAOFactory daofactory, AlertaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AlertaDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AlertaDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AlertaDTO> listarAlertasPendentes(DAOFactory daofactory)
			throws AlugueRelaxeException {
		AlertaDAO dao = daofactory.getAlertaDAO(daofactory);
		return dao.listAlertasPendentes();
	}

	public void atualizarStatusEmitido(DAOFactory daofactory, AlertaDTO dto, String status)
			throws AlugueRelaxeException {
		AlertaDAO dao = daofactory.getAlertaDAO(daofactory);
		dao.updateStatusEmitido(dto,status);
	}
}