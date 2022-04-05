package br.com.jcv.backend.filasms;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.FilaSMSBusiness;
import br.com.jcv.backend.interfacesdao.FilaSMSDAO;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class FilaSMSBusinessImpl implements FilaSMSBusiness {

	public List<FilaSMSDTO> listarSMSPendentes(DAOFactory daofactory, String modo, int prioridade) throws AlugueRelaxeException {
		FilaSMSDAO dao = daofactory.getFilaSMSDAO(daofactory);
		return dao.list(modo, prioridade);
	}

	public void atualizarStatusEnvioFila(DAOFactory daofactory, FilaSMSDTO dto, String status) throws AlugueRelaxeException {
		FilaSMSDAO dao = daofactory.getFilaSMSDAO(daofactory);
		dao.updateStatusEnvioFila(dto, status);
	}
	
	public void atualizarDataEnvioGatewaySMS(DAOFactory daofactory, FilaSMSDTO dto) throws AlugueRelaxeException {
		FilaSMSDAO dao = daofactory.getFilaSMSDAO(daofactory);
		dao.updateDataEnvioGatewaySMS(dto);
	}

	public FilaSMSDTO incluir(DAOFactory daofactory, FilaSMSDTO dto) throws AlugueRelaxeException {
		FilaSMSDAO dao = daofactory.getFilaSMSDAO(daofactory);
		
		// Verifica em quantas partes a mensagem deve ser enviada
		int tammax = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.SMS_TAMANHO_MAXIMO));
		int partes = (int) (dto.msg.length() / tammax + 1);
		String msg = dto.msg;
		
		// Grava o SMS em partes se necessario para envio posterior
		for (int i = 0; i < partes; i++){
			long novoId = daofactory.getNextSequence(daofactory, "SEQ_FISM_ID");
			dto.id = novoId;
			dto.prioridade = (((dto.prioridade < 0) || (dto.prioridade > 1)) ? 0 : dto.prioridade);
			if (partes == 1){
				dto.msg = msg;
			} else {
				if ((i * tammax + tammax - 1) > msg.length()) {
					dto.msg = msg.substring(i * tammax - 1);
				} else {
					dto.msg = msg.substring( i * tammax, i * tammax + tammax - 1);
				}
			}
			dto = dao.insert(dto);
		}
		// restaura a mensagem original
		dto.msg = msg;
		
		return dto;
	}

	public void setDados(FilaSMSDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public FilaSMSDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(FilaSMSDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public FilaSMSDTO atualizar(DAOFactory daofactory, FilaSMSDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, FilaSMSDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public FilaSMSDTO procurar(DAOFactory daofactory, FilaSMSDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FilaSMSDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FilaSMSDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

}
