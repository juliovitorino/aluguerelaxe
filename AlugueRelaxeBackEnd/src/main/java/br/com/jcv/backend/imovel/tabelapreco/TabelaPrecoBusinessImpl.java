package br.com.jcv.backend.imovel.tabelapreco;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.TabelaPrecoBusiness;
import br.com.jcv.backend.interfacesdao.TabelaPrecoDAO;

public class TabelaPrecoBusinessImpl implements TabelaPrecoBusiness<TabelaPrecoDTO> {

	public TabelaPrecoDTO atualizar(DAOFactory daofactory, TabelaPrecoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TabelaPrecoDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TabelaPrecoDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, TabelaPrecoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public TabelaPrecoDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	public TabelaPrecoDTO incluir(DAOFactory daofactory, TabelaPrecoDTO dto)
			throws AlugueRelaxeException {
		return null;
	}

	public TabelaPrecoDTO procurar(DAOFactory daofactory, TabelaPrecoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDados(TabelaPrecoDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public void validarCamposObrigatorios(TabelaPrecoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * <h1>buscarTabelaPrecoImovel</h1>
	 * <p>Buscar a tabela de preço praticada para um determinado imóvel</p>
	 * @param DAOFactory
	 * @param long
	 * 
	 * @return List
	 */
	public List<TabelaPrecoDTO> buscarTabelaPrecoImovel(DAOFactory daofactory,
			long codigoImovel) throws AlugueRelaxeException {
		TabelaPrecoDAO<TabelaPrecoDTO> tpdao = daofactory.getTabelaPrecoDAO(daofactory);
		return tpdao.buscarTabelaPrecoImovel(codigoImovel);
	}

	public TabelaPrecoDTO incluir(DAOFactory daofactory, long codigoImovel,
			TabelaPrecoDTO dto) throws AlugueRelaxeException {
		TabelaPrecoDAO<TabelaPrecoDTO> dao = daofactory.getTabelaPrecoDAO(daofactory);
		dto.id = daofactory.getNextSequence(daofactory, "SEQ_TABELA_PRECO");
		return dao.insert(codigoImovel, dto);
	}

}
