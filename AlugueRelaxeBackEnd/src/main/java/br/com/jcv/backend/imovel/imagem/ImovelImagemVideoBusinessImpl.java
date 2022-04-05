package br.com.jcv.backend.imovel.imagem;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.ImovelImagemVideoBusiness;
import br.com.jcv.backend.interfacesdao.ImovelImagemVideoDAO;

public class ImovelImagemVideoBusinessImpl implements ImovelImagemVideoBusiness<ImovelImagemVideoDTO> {

	public ImovelImagemVideoDTO atualizar(DAOFactory daofactory,
			ImovelImagemVideoDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelImagemVideoDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelImagemVideoDTO> buscarTodas(DAOFactory daofactory,
			int start) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, ImovelImagemVideoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ImovelImagemVideoDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelImagemVideoDTO incluir(DAOFactory daofactory,
			ImovelImagemVideoDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelImagemVideoDTO procurar(DAOFactory daofactory,
			ImovelImagemVideoDTO dto) throws AlugueRelaxeException {
		ImovelImagemVideoDAO<ImovelImagemVideoDTO> iivdao = daofactory.getImovelImagemVideoDAO(daofactory);
		return iivdao.load(dto);
	}

	public void setDados(ImovelImagemVideoDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public void validarCamposObrigatorios(ImovelImagemVideoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<ImovelImagemVideoDTO> buscarListaImagensTB(DAOFactory daofactory, long codigoImovel)
			throws AlugueRelaxeException {
		ImovelImagemVideoDAO<ImovelImagemVideoDTO> iivdao = daofactory.getImovelImagemVideoDAO(daofactory);
		return iivdao.buscarListaImagens(codigoImovel, "TB");
	}

	public List<ImovelImagemVideoDTO> buscarListaImagensMI(DAOFactory daofactory, long codigoImovel)
			throws AlugueRelaxeException {
		ImovelImagemVideoDAO<ImovelImagemVideoDTO> iivdao = daofactory.getImovelImagemVideoDAO(daofactory);
		return iivdao.buscarListaImagens(codigoImovel, "MI");
	}

	public void removeImagensMITB(DAOFactory daofactory,
			long codigoImovel,
			ImovelImagemVideoDTO dto) throws AlugueRelaxeException {
		ImovelImagemVideoDTO dtoPesquisado = this.procurar(daofactory, dto);
		ImovelImagemVideoDAO<ImovelImagemVideoDTO> iivdao = daofactory.getImovelImagemVideoDAO(daofactory);
		iivdao.deleteImagensMITB(codigoImovel, dtoPesquisado.radical); 
	}

	public List<ImovelImagemVideoDTO> buscarListaImagensXG(
			DAOFactory daofactory, long codigoImovel) throws AlugueRelaxeException{
		ImovelImagemVideoDAO<ImovelImagemVideoDTO> iivdao = daofactory.getImovelImagemVideoDAO(daofactory);
		return iivdao.buscarListaImagens(codigoImovel, "XG");
	}

	public ImovelImagemVideoDTO procurarVideoImovel(DAOFactory daofactory,
			long codigoImovel) throws AlugueRelaxeException {
		ImovelImagemVideoDAO<ImovelImagemVideoDTO> iivdao = daofactory.getImovelImagemVideoDAO(daofactory);
		return iivdao.loadVideoImovel(codigoImovel);
	}

	
}
