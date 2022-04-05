package br.com.jcv.backend.imovel.publicidade;

import java.util.Date;
import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoBusinessImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.interfaces.business.ImovelImagemVideoBusiness;
import br.com.jcv.backend.interfaces.business.ImovelPublicidadeBusiness;
import br.com.jcv.backend.interfacesdao.ImovelPublicidadeDAO;

public class ImovelPublicidadeBusinessImpl implements ImovelPublicidadeBusiness<ImovelPublicidadeDTO> {

	public ImovelPublicidadeBusinessImpl() {}

	public int contarGradePublicidade(DAOFactory daofactory, Date d, String tipo) throws AlugueRelaxeException {
		ImovelPublicidadeDAO<ImovelPublicidadeDTO> dao = daofactory.getImovelPublicidadeDAO(daofactory);
		return dao.countGradePublicidade(d, tipo);
	}
	
	public ImovelPublicidadeDTO atualizar(DAOFactory daofactory, ImovelPublicidadeDTO dto)
			throws AlugueRelaxeException {
		return null;
	}

	public List<ImovelPublicidadeDTO> buscarTodas(DAOFactory daofactory) throws AlugueRelaxeException {
		return null;
	}

	public List<ImovelPublicidadeDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		return null;
	}

	public void excluir(DAOFactory daofactory, ImovelPublicidadeDTO dto) throws AlugueRelaxeException {
	}

	public ImovelPublicidadeDTO getDados() throws AlugueRelaxeException {
		return null;
	}

	public ImovelPublicidadeDTO incluir(DAOFactory daofactory, ImovelPublicidadeDTO dto)
			throws AlugueRelaxeException {
		return null;
	}

	public ImovelPublicidadeDTO procurar(DAOFactory daofactory, ImovelPublicidadeDTO dto)
			throws AlugueRelaxeException {
		return null;
	}

	public void setDados(ImovelPublicidadeDTO dto) throws AlugueRelaxeException {
	}

	public void validarCamposObrigatorios(ImovelPublicidadeDTO dto) throws AlugueRelaxeException {
	}
	
	public List<ImovelPublicidadeDTO> listImovelPublicidadePaginaPrincipal(DAOFactory daofactory) throws AlugueRelaxeException {
		ImovelPublicidadeDAO<ImovelPublicidadeDTO> dao = daofactory.getImovelPublicidadeDAO(daofactory);
		List<ImovelPublicidadeDTO> lst = dao.listImovelPublicidadePaginaPrincipal();
		if (lst != null) {
		ImovelImagemVideoBusiness<ImovelImagemVideoDTO> iivb = new ImovelImagemVideoBusinessImpl();
			for (ImovelPublicidadeDTO dto : lst) {
				ImovelImagemVideoDTO iivdto = new ImovelImagemVideoDTO();
				iivdto.id = dto.idImagemPreferida;
				dto.imagemPreferida = iivb.procurar(daofactory, iivdto);
			}
		}
		return lst;
		
	}
	public List<ImovelPublicidadeDTO> listImovelSuperBanner(DAOFactory daofactory) throws AlugueRelaxeException {
		ImovelPublicidadeDAO<ImovelPublicidadeDTO> dao = daofactory.getImovelPublicidadeDAO(daofactory);
		List<ImovelPublicidadeDTO> lst = dao.listImovelSuperBanner();
		if (lst != null) {
		ImovelImagemVideoBusiness<ImovelImagemVideoDTO> iivb = new ImovelImagemVideoBusinessImpl();
			for (ImovelPublicidadeDTO dto : lst) {
				ImovelImagemVideoDTO iivdto = new ImovelImagemVideoDTO();
				iivdto.id = dto.idImagemPreferida;
				dto.imagemPreferida = iivb.procurar(daofactory, iivdto);
			}
		}
		return lst;
	}

	public List<? extends ImovelPublicidadeDTO> listImovelPublicidadePaginaDestaque(
			DAOFactory daofactory) throws AlugueRelaxeException {
		ImovelPublicidadeDAO<ImovelPublicidadeDTO> dao = daofactory.getImovelPublicidadeDAO(daofactory);
		return dao.listImovelPublicidadePaginaDestaque();
	}

	public List<PublicidadeImovelDTO> listPublicidadeDentroPrazo(DAOFactory daofactory, String tipo) 
			throws AlugueRelaxeException {
		ImovelPublicidadeDAO<ImovelPublicidadeDTO> dao = daofactory.getImovelPublicidadeDAO(daofactory);
		return dao.listPublicidadeDentroPrazo(tipo);
	}


}

