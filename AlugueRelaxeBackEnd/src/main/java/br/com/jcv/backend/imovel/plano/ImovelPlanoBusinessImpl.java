package br.com.jcv.backend.imovel.plano;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.interfaces.business.ImovelPlanoBusiness;
import br.com.jcv.backend.interfacesdao.ImovelDAO;
import br.com.jcv.backend.interfacesdao.ImovelPlanoDAO;

/**
 * <h1>ImovelPlanoBusinessImpl</h1> 
 * <p>Objetivo desta classe e gerenciar todos os Metodos que implementam regras de negocio referentes ao objeto imovel x plano.
 *
 * @author Julio Vitorino
 * @version 1.10
 * @since 05 Apr 2012
 */
public class ImovelPlanoBusinessImpl implements ImovelPlanoBusiness<ImovelPlanoRelacaoDTO>{
	/**
	 * <h2>Atributo logger</h2>
	 * <p>Logger para instancia corrente de <code>ImovelPlanoBusiness</code></p>
	 */
	private static Logger logger = Logger.getLogger(ImovelPlanoBusinessImpl.class.getName());

    
	public ImovelPlanoBusinessImpl() {
		
	}
	
	public ImovelPlanoRelacaoDTO incluir(DAOFactory daofactory, ImovelPlanoRelacaoDTO dto) throws AlugueRelaxeException {
		dto.id = daofactory.getNextSequence(daofactory, "SEQ_IMPL_CD_IMOVEL_PLANO");
		ImovelPlanoDAO<ImovelPlanoRelacaoDTO> dao = daofactory.getImovelPlanoDAO(daofactory);
		return dao.insert(dto);
	}
	
	public ImovelPlanoRelacaoDTO procurar(DAOFactory daofactory, long idImovel, long idPlano) throws AlugueRelaxeException {
		ImovelPlanoDAO<ImovelPlanoRelacaoDTO> dao = daofactory.getImovelPlanoDAO(daofactory);
		return dao.load(idImovel, idPlano);
	}

	public void setDados(ImovelPlanoRelacaoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ImovelPlanoRelacaoDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(ImovelPlanoRelacaoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ImovelPlanoRelacaoDTO atualizar(DAOFactory daofactory,
			ImovelPlanoRelacaoDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, ImovelPlanoRelacaoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ImovelPlanoRelacaoDTO procurar(DAOFactory daofactory,
			ImovelPlanoRelacaoDTO dto) throws AlugueRelaxeException {
		ImovelPlanoDAO<ImovelPlanoRelacaoDTO> dao = daofactory.getImovelPlanoDAO(daofactory);
		return dao.load(dto);
	}

	public List<ImovelPlanoRelacaoDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelPlanoRelacaoDTO> buscarTodas(DAOFactory daofactory,
			int start) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}	
	
