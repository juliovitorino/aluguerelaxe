package br.com.jcv.backend.filasms;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.FilaSMSBusiness;
import br.com.jcv.backend.interfaces.services.FilaSMSService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class FilaSMSServiceImpl implements FilaSMSService {
	public List<FilaSMSDTO> listarSMSPendentes(String modo, int prioridade) throws AlugueRelaxeException {
		List<FilaSMSDTO> lst = null;
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			FilaSMSBusiness fsb = new FilaSMSBusinessImpl();
			lst = fsb.listarSMSPendentes(daoFactory, modo, prioridade);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
		return lst;
	}
	
	public List<FilaSMSDTO> listarSMSPendentes(String modo) throws AlugueRelaxeException {
		return this.listarSMSPendentes(modo, Constantes.SMS_PRIORIDADE_NORMAL);
	}
	
	public void atualizarStatusEnvioFila(FilaSMSDTO dto, String status) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			FilaSMSBusiness fsb = new FilaSMSBusinessImpl();
			fsb.atualizarStatusEnvioFila(daoFactory,dto,status);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
	}

	
	public void atualizarDataEnvioGatewaySMS(FilaSMSDTO dto) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			FilaSMSBusiness fsb = new FilaSMSBusinessImpl();
			fsb.atualizarDataEnvioGatewaySMS(daoFactory, dto);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
	}
	
	public FilaSMSDTO gravarRegistro(FilaSMSDTO dto)
			throws AlugueRelaxeException {
		FilaSMSDTO retorno = null;
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			FilaSMSBusiness fsb = new FilaSMSBusinessImpl();
			retorno = fsb.incluir(daoFactory, dto);
			
			boolean permiteNotificacao = VariavelCache.getInstance().getValor(VariavelConstantes.PERMITIR_ENVIO_NOTIFICACAO_ADMIN_SMS).equals(Constantes.ON);
			if(permiteNotificacao){
				FilaSMSDTO fsmsdto = new FilaSMSDTO();
				fsmsdto.celular = VariavelCache.getInstance().getValor(VariavelConstantes.CELULAR_ADMIN_GATEWAY);
				fsmsdto.prioridade = Constantes.SMS_PRIORIDADE_ALTA;
				fsmsdto.modo = Constantes.SMS_MODO_ENVIO_GATEWAY;
				fsmsdto.msg = "NOTIFY:" + dto.msg;
				fsmsdto = fsb.incluir(daoFactory, fsmsdto);
			}
			daoFactory.commit();
			
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
		return retorno;
	}

	public FilaSMSDTO excluirRegistro(FilaSMSDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public FilaSMSDTO atualizarRegistro(FilaSMSDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public FilaSMSDTO pesquisarRegistro(FilaSMSDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends FilaSMSDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
}