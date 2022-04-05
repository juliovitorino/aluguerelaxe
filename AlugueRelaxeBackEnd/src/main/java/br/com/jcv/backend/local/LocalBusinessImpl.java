package br.com.jcv.backend.local;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.LocalBusiness;
import br.com.jcv.backend.interfacesdao.LocalDAO;

public class LocalBusinessImpl implements LocalBusiness<LocalItemDTO> {

	public void setDados(LocalItemDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub

	}

	public LocalItemDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(LocalItemDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub

	}

	public LocalItemDTO incluir(DAOFactory daofactory, LocalItemDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalItemDTO atualizar(DAOFactory daofactory, LocalItemDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, LocalItemDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub

	}

	public LocalItemDTO procurar(DAOFactory daofactory, LocalItemDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LocalItemDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LocalItemDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LocalItemDTO> listarLocaisUFCidade(DAOFactory daoFactory,
			long idUfCidadeItem, long[] idLocais) throws AlugueRelaxeException {
		LocalDAO<LocalItemDTO> dao = daoFactory.getLocalDAO(daoFactory);
		return dao.listLocaisUFCidade(idUfCidadeItem, idLocais);
	}

	public List<LocalItemDTO> listarLocaisUFCidadeClassificacao(
			DAOFactory daoFactory, long idUfCidadeItem, long[] idClassificacao)
			throws AlugueRelaxeException {
		LocalDAO<LocalItemDTO> dao = daoFactory.getLocalDAO(daoFactory);
		return dao.listLocaisUFCidadeClassificacao(idUfCidadeItem, idClassificacao);
	}

	public List<ClassificacaoDTO> listarClassificacaoLocal(DAOFactory daoFactory) 
		throws AlugueRelaxeException {
		LocalDAO<?> dao = daoFactory.getLocalDAO(daoFactory);
		return dao.listClassificacaoLocal();
	}

	public List<LocalDTO> listarLocal(DAOFactory daoFactory) throws AlugueRelaxeException  {
		LocalDAO<?> dao = daoFactory.getLocalDAO(daoFactory);
		return dao.listLocal();
	}

}
