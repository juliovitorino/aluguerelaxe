package br.com.jcv.backend.depoimento;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.DepoimentoBusiness;
import br.com.jcv.backend.interfacesdao.DepoimentoDAO;

/**
 * @author Julio
 *
 */
public class DepoimentoBusinessImpl implements DepoimentoBusiness<DepoimentoDTO>{
	
	public static final String PENDENTE = "P";
	public static final String LIBERADO = "L";
	public static final String REPROVADO = "R";
	
	private static Logger logger = Logger.getLogger(br.com.jcv.backend.depoimento.DepoimentoBusinessImpl.class);
	private Long id;
	private String depoimento;
	private String nome;
	
	public DepoimentoBusinessImpl() throws AlugueRelaxeException {
		this(Long.valueOf(0),"Vazio","semnome");
	}
	
	public DepoimentoBusinessImpl(Long id, String depoimento, String nome) throws AlugueRelaxeException {
		this.setId(id);
		this.setDepoimento(depoimento);
		this.setNome(nome);
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the depoimento
	 */
	public String getDepoimento() {
		return depoimento;
	}

	/**
	 * @param depoimento the depoimento to set
	 */
	public void setDepoimento(String depoimento) {
		this.depoimento = depoimento;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public DepoimentoDTO proximoDepoimento(DAOFactory daoFactory, Long id) throws AlugueRelaxeException {
		DepoimentoDAO<DepoimentoDTO> dao = daoFactory.getDepoimentoDAO(daoFactory);
		DepoimentoDTO proximoDepoimento = dao.nextDepoimento(id);
		return proximoDepoimento;
	}

	public DepoimentoDTO prevDepoimento(DAOFactory daoFactory, Long id) throws AlugueRelaxeException {
		DepoimentoDAO<DepoimentoDTO> dao = daoFactory.getDepoimentoDAO(daoFactory);
		DepoimentoDTO proximoDepoimento = dao.prevDepoimento(id);
		return proximoDepoimento;
	}


	public DepoimentoDTO incluir(DAOFactory daoFactory, DepoimentoDTO dto)
			throws AlugueRelaxeException {
		DepoimentoDTO dtoretorno = null;
		DepoimentoDAO<DepoimentoDTO> dao = daoFactory.getDepoimentoDAO(daoFactory);
		dto.id = daoFactory.getNextSequence(daoFactory, "SEQ_DEPO_CD_DEPOIMENTO");
		dtoretorno = dao.insert(dto);
		return dtoretorno;
	}

	public DepoimentoDTO getDados() throws AlugueRelaxeException {
		DepoimentoDTO dto = new DepoimentoDTO();
		dto.setId(this.getId());
		dto.setNome(this.getNome());
		dto.setDepoimento(this.getDepoimento());
		return dto;
	}

	public void setDados(DepoimentoDTO dto) throws AlugueRelaxeException {
		this.setId(dto.getId());
		this.setDepoimento(dto.getDepoimento());
		this.setNome(dto.getNome());
		this.validarCamposObrigatorios(dto);
	}

	public void validarCamposObrigatorios(DepoimentoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public DepoimentoDTO atualizar(DAOFactory daofactory, DepoimentoDTO dto)
			throws AlugueRelaxeException {
		DepoimentoDTO dtoretorno = null;
		this.setDados(dto);
		DepoimentoDAO<DepoimentoDTO> dao = daofactory.getDepoimentoDAO(daofactory);
		dtoretorno = dao.update(this.getDados());
		return dtoretorno;
	}

	public void excluir(DAOFactory daofactory, DepoimentoDTO dto)
			throws AlugueRelaxeException {
		this.setDados(dto);
		DepoimentoDAO<DepoimentoDTO> dao = daofactory.getDepoimentoDAO(daofactory);
		dao.delete(this.getDados());
	}

	public DepoimentoDTO procurar(DAOFactory daofactory, DepoimentoDTO dto)
			throws AlugueRelaxeException {
		DepoimentoDAO<DepoimentoDTO> dao = daofactory.getDepoimentoDAO(daofactory);
		return dao.load(dto);
	}

	public List<DepoimentoDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		List<DepoimentoDTO> list = null;
		DepoimentoDAO<DepoimentoDTO> dao = daofactory.getDepoimentoDAO(daofactory);
		list = dao.list();
		return list;
	}

	public List<DepoimentoDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		List<DepoimentoDTO> list = null;
		DepoimentoDAO<DepoimentoDTO> dao = daofactory.getDepoimentoDAO(daofactory);
		list = dao.list(start);
		return list;
	}

	public DepoimentoDTO adicionarDepoimento(DAOFactory daoFactory, DepoimentoDTO dto)
			throws AlugueRelaxeException {
		DepoimentoDAO<DepoimentoDTO> dao = daoFactory.getDepoimentoDAO(daoFactory);
		dto.id = daoFactory.getNextSequence(daoFactory, "SEQ_DEPO_CD_DEPOIMENTO");
		dto = dao.insert(dto);
		return dto;
	}

	public void liberarDepoimento(DAOFactory daoFactory, String id, String acao)
			throws AlugueRelaxeException {
		DepoimentoDAO<DepoimentoDTO> dao = daoFactory.getDepoimentoDAO(daoFactory);
		dao.atualizarStatus(Long.valueOf(id), (acao.equals(Constantes.APROVAR) ? Constantes.DEPO_STATUS_LIBERADO : Constantes.DEPO_STATUS_REPROVADO));
	}
	
	public List<DepoimentoDTO> ListarPaginaDepoimentos(DAOFactory daoFactory, int tamanhoPagina) throws AlugueRelaxeException{
		DepoimentoDAO<DepoimentoDTO> dao = daoFactory.getDepoimentoDAO(daoFactory);
		return dao.list(tamanhoPagina);
	}
	


}
