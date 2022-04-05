package br.com.jcv.backend.modopublicidadevisitas;

import java.util.Date;
import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.ModoPublicidadeVisitasBusiness;
import br.com.jcv.backend.interfacesdao.ModoPublicidadeVisitasDAO;



public class ModoPublicidadeVisitasBusinessImpl implements ModoPublicidadeVisitasBusiness<ModoPublicidadeVisitasDTO> {


	public ModoPublicidadeVisitasDTO procurar(DAOFactory daofactory, Date d, long idModoPublicidade)
			throws AlugueRelaxeException {
		ModoPublicidadeVisitasDAO<ModoPublicidadeVisitasDTO> dao = daofactory.getModoPublicidadeVisitasDAO(daofactory);
		return dao.load(d,idModoPublicidade);
	}

	public void incrementarModoPublicidadeVisita(DAOFactory daofactory, Date d, long idModoPublicidade) throws AlugueRelaxeException {
		// Verifica se e a primeira visita do dia neste modo de publicidade
		ModoPublicidadeVisitasDTO mpvdto = this.procurar(daofactory, d, idModoPublicidade);
		ModoPublicidadeVisitasDAO<ModoPublicidadeVisitasDTO> dao = daofactory.getModoPublicidadeVisitasDAO(daofactory);
		if (mpvdto == null) {
			ModoPublicidadeVisitasDTO mpvinsdto = new ModoPublicidadeVisitasDTO();
			mpvinsdto.id = daofactory.getNextSequence(daofactory, "SEQ_MOPV_CD_MODO_PUBL_VISITAS");
			mpvinsdto.idModoPublicidade = idModoPublicidade;
			dao.insert(mpvinsdto);
		} else {
			dao.updateModoPublicidadeVisita(d, idModoPublicidade);
		}
		
	}

	public void setDados(ModoPublicidadeVisitasDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ModoPublicidadeVisitasDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(ModoPublicidadeVisitasDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ModoPublicidadeVisitasDTO incluir(DAOFactory daofactory,
			ModoPublicidadeVisitasDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ModoPublicidadeVisitasDTO atualizar(DAOFactory daofactory,
			ModoPublicidadeVisitasDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, ModoPublicidadeVisitasDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ModoPublicidadeVisitasDTO procurar(DAOFactory daofactory,
			ModoPublicidadeVisitasDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ModoPublicidadeVisitasDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ModoPublicidadeVisitasDTO> buscarTodas(DAOFactory daofactory,
			int start) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

}
