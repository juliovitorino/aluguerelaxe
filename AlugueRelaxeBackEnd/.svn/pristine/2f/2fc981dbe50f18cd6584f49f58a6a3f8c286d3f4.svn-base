package br.com.jcv.backend.painelcontrole;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.PainelControleBusiness;
import br.com.jcv.backend.interfaces.services.PainelControleService;
import br.com.jcv.backend.mensagem.MensagemCache;

public class PainelControleServiceImpl implements PainelControleService {

	public List<EstatisticaOrigemVisitaDTO> getEstatisticaOrigemVisita(int ano) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<EstatisticaOrigemVisitaDTO> dto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			PainelControleBusiness ib = new PainelControleBusinessImpl();
			dto = ib.getEstatisticaOrigemVisita(daoFactory, ano);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}

	public List<EstatisticaVisitasDTO> getEstatisticaVisitantes() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<EstatisticaVisitasDTO> dto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			PainelControleBusiness ib = new PainelControleBusinessImpl();
			dto = ib.getEstatisticaVisitantes(daoFactory);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}

	public EstatisticaVisitasDiarioDTO getEstatisticaVisitantesDiario(int ano, int mes) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		EstatisticaVisitasDiarioDTO dto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			PainelControleBusiness ib = new PainelControleBusinessImpl();
			dto = ib.getEstatisticaVisitantesDiario(daoFactory, ano, mes);
			daoFactory.commit();
			
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}

	public IndicadorPerformanceEntradaImovelDTO getIndPerfEntradaImovel(
			int ano, int mes, int dia) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		IndicadorPerformanceEntradaImovelDTO dto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			PainelControleBusiness ib = new PainelControleBusinessImpl();
			dto = ib.getIndPerfEntradaImovel(daoFactory, ano, mes, dia);
			daoFactory.commit();
			
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}

	public IndicadorPerformanceVisitasDTO getIndPerfVisitas(int ano, int mes,
			int dia) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		IndicadorPerformanceVisitasDTO dto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			PainelControleBusiness ib = new PainelControleBusinessImpl();
			dto = ib.getIndPerfVisitas(daoFactory, ano, mes, dia);
			daoFactory.commit();
			
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}
	
	public ModeracaoDTO getPendenciaModeracao() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ModeracaoDTO dto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			PainelControleBusiness ib = new PainelControleBusinessImpl();
			long contatos = ib.getContatoVisitantesPendentes(daoFactory);
			long depoimentos = ib.getDepoimentosPendentes(daoFactory);
			daoFactory.commit();
			
			dto = new ModeracaoDTO();
			dto.totalContatosPendentes = contatos;
			dto.totalDepoimentosPendentes = depoimentos;
			
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}

}
