package br.com.jcv.backend.imovel.faturamento;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.ImovelPlanoFaturaBusiness;
import br.com.jcv.backend.interfacesdao.ImovelPlanoFaturaDAO;


public class ImovelPlanoFaturaBusinessImpl implements ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> {


	public List<Long> listarPlanosGratuitosVencidos(DAOFactory daoFactory) throws AlugueRelaxeException {
		ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> dao = daoFactory.getImovelPlanoFaturaDAO(daoFactory);
		return dao.listPlanosGratuitosVencidos();
	}

	public List<Long> listarPlanosPagosVencidos(DAOFactory daoFactory) throws AlugueRelaxeException {
		ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> dao = daoFactory.getImovelPlanoFaturaDAO(daoFactory);
		return dao.listPlanosPagosVencidos();
	}

	public List<Long> listarPlanosPagosAVencer(DAOFactory daoFactory) throws AlugueRelaxeException {
		ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> dao = daoFactory.getImovelPlanoFaturaDAO(daoFactory);
		return dao.listPlanosPagosAVencer();
	}

	public List<Long> listarPlanosPagosNaoVencidos(DAOFactory daoFactory) throws AlugueRelaxeException {
		ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> dao = daoFactory.getImovelPlanoFaturaDAO(daoFactory);
		return dao.listPlanosPagosNaoVencidos();
	}

	public List<Long> listarPlanosGratuitosAVencer(DAOFactory daoFactory) throws AlugueRelaxeException {
		ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> dao = daoFactory.getImovelPlanoFaturaDAO(daoFactory);
		return dao.listPlanosGratuitosAVencer();
	}

	public ImovelPlanoFaturaDTO procurar(DAOFactory daoFactory, long codigoImovel, String tipo) throws AlugueRelaxeException {
		ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> dao = daoFactory.getImovelPlanoFaturaDAO(daoFactory);
		return dao.loadUltimaFatura(codigoImovel, tipo);
	}

	public ImovelPlanoFaturaDTO procurar(DAOFactory daoFactory, long idFatura) throws AlugueRelaxeException {
		ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> dao = daoFactory.getImovelPlanoFaturaDAO(daoFactory);
		return dao.loadFatura(idFatura);
	}

	public void setDados(ImovelPlanoFaturaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ImovelPlanoFaturaDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(ImovelPlanoFaturaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ImovelPlanoFaturaDTO incluir(DAOFactory daofactory,
			ImovelPlanoFaturaDTO dto) throws AlugueRelaxeException {
		ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> dao = daofactory.getImovelPlanoFaturaDAO(daofactory);
		dto.id = daofactory.getNextSequence(daofactory, "SEQ_IMPF_CD_IMOVEL_PLANO_FATURA");
		return dao.insert(dto);
	}

	public ImovelPlanoFaturaDTO atualizar(DAOFactory daofactory,
			ImovelPlanoFaturaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, ImovelPlanoFaturaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ImovelPlanoFaturaDTO procurar(DAOFactory daofactory,
			ImovelPlanoFaturaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelPlanoFaturaDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelPlanoFaturaDTO> buscarTodas(DAOFactory daofactory,
			int start) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void atualizarStatusFatura(DAOFactory daoFactory, long idFatura,
			String status) throws AlugueRelaxeException {
		ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> dao = daoFactory.getImovelPlanoFaturaDAO(daoFactory);
		dao.updateStatusFatura(idFatura,status);
	}

	public List<Long> listarPlanosPendentesVencidos(DAOFactory daoFactory)
			throws AlugueRelaxeException {
		ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> dao = daoFactory.getImovelPlanoFaturaDAO(daoFactory);
		return dao.listPlanosPendentesVencidos();
	}


}
