package br.com.jcv.backend.imovel.caracteristica;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.ImovelCaracteristicaBusiness;
import br.com.jcv.backend.interfacesdao.ImovelCaracteristicaDAO;

public class ImovelCaracteristicaBusinessImpl implements
		ImovelCaracteristicaBusiness<ImovelCaracteristicaDTO> {
	
	public static final String INDICADOR_CARACTERISTICA_IMOVEL = "I";
	public static final String INDICADOR_CARACTERISTICA_CONDOMINIO = "C";
	

	public ImovelCaracteristicaDTO atualizar(DAOFactory daofactory,
			ImovelCaracteristicaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelCaracteristicaDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelCaracteristicaDTO> buscarTodas(DAOFactory daofactory,
			int start) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, ImovelCaracteristicaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ImovelCaracteristicaDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelCaracteristicaDTO incluir(DAOFactory daofactory,
			ImovelCaracteristicaDTO dto) throws AlugueRelaxeException {
		ImovelCaracteristicaDAO<ImovelCaracteristicaDTO> dao = daofactory.getImovelCaracteristicaDAO(daofactory);
		dto.id = daofactory.getNextSequence(daofactory, "SEQ_IMCA_CD_CARACTERISTICA");
		return dao.insert(dto);
	}

	public ImovelCaracteristicaDTO procurar(DAOFactory daofactory,
			ImovelCaracteristicaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDados(ImovelCaracteristicaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public void validarCamposObrigatorios(ImovelCaracteristicaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<ImovelCaracteristicaDTO> buscarCaracteristicasCondominio(
			DAOFactory daofactory, long codigoImovel)
			throws AlugueRelaxeException {
		ImovelCaracteristicaDAO<ImovelCaracteristicaDTO> icdao = daofactory.getImovelCaracteristicaDAO(daofactory);
		return icdao.buscarCaracteristicasImovel(codigoImovel, "C");
	}

	public List<ImovelCaracteristicaDTO> buscarCaracteristicasImovel(
			DAOFactory daofactory, long codigoImovel)
			throws AlugueRelaxeException {
		ImovelCaracteristicaDAO<ImovelCaracteristicaDTO> icdao = daofactory.getImovelCaracteristicaDAO(daofactory);
		return icdao.buscarCaracteristicasImovel(codigoImovel, "I");
	}


}
