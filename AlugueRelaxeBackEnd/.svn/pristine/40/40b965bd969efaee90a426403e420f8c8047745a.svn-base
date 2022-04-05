package br.com.jcv.backend.variavel;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.VariavelBusiness;
import br.com.jcv.backend.interfacesdao.DAO;
import br.com.jcv.backend.mensagem.MensagemCache;

public class VariavelBusinessImpl implements VariavelBusiness<VariavelDTO> {
	
	private static Logger logger = Logger.getLogger(VariavelBusinessImpl.class);
	private String id;
	private String valor;
	private String descricao;
	
	public VariavelBusinessImpl() {	}
	
	
	
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}



	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) throws AlugueRelaxeException {
		if (descricao == null){
			throw AlugueRelaxeException.getInstance(MSGCODE.CAMPO_DESCRICAO_VARIAVEL_NULO, MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_DESCRICAO_VARIAVEL_NULO), null);
		}
		if (descricao.length() > Constantes.TAMANHO_MAXIMO_DESCRICAO_VARIAVEL){
			throw AlugueRelaxeException.getInstance(MSGCODE.ESTOURO_CAMPO_DESCRICAO_VARIAVEL, MensagemCache.getInstance().getMensagem(MSGCODE.ESTOURO_CAMPO_DESCRICAO_VARIAVEL), id);
		}
		this.descricao = descricao;
	}



	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) throws AlugueRelaxeException {
		if (id == null){
			throw AlugueRelaxeException.getInstance(MSGCODE.CAMPO_ID_VARIAVEL_NULO, MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_ID_VARIAVEL_NULO), null);
		}
		if (id.length() > Constantes.TAMANHO_MAXIMO_ID_VARIAVEL){
			throw AlugueRelaxeException.getInstance(MSGCODE.ESTOURO_CAMPO_ID_VARIAVEL, MensagemCache.getInstance().getMensagem(MSGCODE.ESTOURO_CAMPO_ID_VARIAVEL), id);
		}
		this.id = id;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	public void excluir(DAOFactory daofactory, VariavelDTO dto)
			throws AlugueRelaxeException {
		this.setDados(dto);
		DAO<VariavelDTO> dao = daofactory.getVariavelDAO(daofactory);
		dao.delete(this.getDados());
	}

	public VariavelDTO incluir(DAOFactory daofactory, VariavelDTO dto)
			throws AlugueRelaxeException {
		VariavelDTO dtoretorno = null;
		this.setDados(dto);
		DAO<VariavelDTO> dao = daofactory.getVariavelDAO(daofactory);
		dtoretorno = dao.insert(this.getDados());
		return dtoretorno;
	}

	public List<VariavelDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		List<VariavelDTO> list = null;
		DAO<VariavelDTO> dao = daofactory.getVariavelDAO(daofactory);
		list = dao.list();
		return list;
	}

	public VariavelDTO getDados() throws AlugueRelaxeException {
		VariavelDTO dto = new VariavelDTO();
		dto.setId(this.getId());
		dto.setValor(this.getValor());
		dto.setDescricao(this.getDescricao());
		return dto;	
	}
	public VariavelDTO procurar(DAOFactory daofactory, VariavelDTO dto)
			throws AlugueRelaxeException {
		DAO<VariavelDTO> dao = daofactory.getVariavelDAO(daofactory);
		return dao.load(dto);
	}

	public void setDados(VariavelDTO dto) throws AlugueRelaxeException {
		this.setId(dto.getId());
		this.setValor(dto.getValor());
		this.setDescricao(dto.getDescricao());
		this.validarCamposObrigatorios(dto);
	}

	public void validarCamposObrigatorios(VariavelDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub

	}

	public VariavelDTO atualizar(DAOFactory daofactory, VariavelDTO dto)
			throws AlugueRelaxeException {
		VariavelDTO dtoretorno = null;
		this.setDados(dto);
		DAO<VariavelDTO> dao = daofactory.getVariavelDAO(daofactory);
		dtoretorno = dao.update(this.getDados());
		return dtoretorno;
	}

	public List<VariavelDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

}
