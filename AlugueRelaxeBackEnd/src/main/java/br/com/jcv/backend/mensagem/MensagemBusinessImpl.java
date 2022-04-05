package br.com.jcv.backend.mensagem;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.MensagemBusiness;
import br.com.jcv.backend.interfacesdao.DAO;
import br.com.jcv.backend.interfacesdao.MensagemDAO;

public class MensagemBusinessImpl implements MensagemBusiness<MensagemDTO>{

	private static Logger logger = Logger.getLogger(MensagemBusinessImpl.class);
	
	private String id;
	private String mensagem;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) throws AlugueRelaxeException {
		
		if (mensagem != null && (mensagem.length() > 0 && mensagem.length() <= Constantes.TAMANHO_MAXIMO_MENSAGEM)) {
			this.mensagem = mensagem;
		} else {
			AlugueRelaxeException are = new AlugueRelaxeException();
			if (mensagem == null || mensagem.length() == 0) {
				are.setCodigoErro(MSGCODE.CAMPO_VAZIO);
				are.setMensagem(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_VAZIO));
			} else if (mensagem.length() > Constantes.TAMANHO_MAXIMO_MENSAGEM) {
				are.setCodigoErro(MSGCODE.ESTOURO_CAMPO_MENSAGEM);
				are.setMensagem(MensagemCache.getInstance().getMensagem(MSGCODE.ESTOURO_CAMPO_MENSAGEM));
			}
			throw are;
		}
	}

	public MensagemBusinessImpl() throws AlugueRelaxeException {
	}
	
	public MensagemDTO atualizar(DAOFactory daofactory, MensagemDTO dto)
			throws AlugueRelaxeException {
		MensagemDTO dtoretorno = null;
		this.setDados(dto);
		DAO<MensagemDTO> dao = daofactory.getMensagemDAO(daofactory);
		dtoretorno = dao.update(this.getDados());
		return dtoretorno;
	}

	public void excluir(DAOFactory daofactory, MensagemDTO dto)
			throws AlugueRelaxeException {
		this.setDados(dto);
		DAO<MensagemDTO> dao = daofactory.getMensagemDAO(daofactory);
		dao.delete(this.getDados());
	}

	public MensagemDTO incluir(DAOFactory daofactory, MensagemDTO dto)
			throws AlugueRelaxeException {
		MensagemDTO dtoretorno = null;
		this.setDados(dto);
		DAO<MensagemDTO> dao = daofactory.getMensagemDAO(daofactory);
		dtoretorno = dao.insert(this.getDados());
		return dtoretorno;
	}

	public MensagemDTO getDados() throws AlugueRelaxeException {
		MensagemDTO dto = new MensagemDTO();
		dto.setId(this.getId());
		dto.setMensagem(this.getMensagem());
		return dto;	
	}

	public MensagemDTO procurar(DAOFactory daofactory, MensagemDTO dto)
			throws AlugueRelaxeException {
		DAO<MensagemDTO> dao = daofactory.getMensagemDAO(daofactory);
		return dao.load(dto);
	}

	public void setDados(MensagemDTO dto) throws AlugueRelaxeException {
		this.setId(dto.getId());
		this.setMensagem(dto.getMensagem());
		this.validarCamposObrigatorios(dto);		
	}

	public void validarCamposObrigatorios(MensagemDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<MensagemDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		List<MensagemDTO> list = null;
		DAO<MensagemDTO> dao = daofactory.getMensagemDAO(daofactory);
		list = dao.list();
		return list;
	}


	public List<MensagemDTO> buscarTodas(DAOFactory daofactory, String sufixo)
			throws AlugueRelaxeException {
		List<MensagemDTO> list = null;
		MensagemDAO<MensagemDTO> dao = daofactory.getMensagemDAO(daofactory);
		list = dao.list(sufixo);
		return list;
	}

	public List<MensagemDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


}
