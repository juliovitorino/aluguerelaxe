package br.com.jcv.backend.imovel.plano;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.datemanager.DateManager;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ImovelBusinessImpl;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelPlanoDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaBusinessImpl;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.interfaces.business.ImovelBusiness;
import br.com.jcv.backend.interfaces.business.ImovelPlanoBusiness;
import br.com.jcv.backend.interfaces.business.ImovelPlanoFaturaBusiness;
import br.com.jcv.backend.interfaces.services.ImovelPlanoService;
import br.com.jcv.backend.interfaces.services.PlanoService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.plano.PlanoServicesImpl;

public class ImovelPlanoServiceImpl implements
		ImovelPlanoService<ImovelPlanoRelacaoDTO> {

	public ImovelPlanoRelacaoDTO migrarPlanoImovel(long codImovel, long codNovoPlano)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ImovelPlanoRelacaoDTO dtoretorno = null;
		

		// Obtem informacoes completas do novo plano
		PlanoDTO pdto = new PlanoDTO();
		pdto.id = codNovoPlano;
		PlanoService<PlanoDTO> ps = new PlanoServicesImpl();
		pdto = ps.pesquisarRegistro(pdto);

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			// Obtem informacoes atualizada sobre o plano do referente imovel
			// Este não pode ser igual ao novo plano
			ImovelBusiness<?> bo = new ImovelBusinessImpl();
			ImovelPlanoDTO ipdto = bo.procurarPlanoFinanceiro(daoFactory, 2); 
			if ((ipdto == null) || (ipdto.plano.id == codNovoPlano)) {
				final Map<String,String> parametros = new HashMap<String,String>();
				parametros.put(Constantes.P1, pdto.nome);
				throw AlugueRelaxeException.getInstance(MSGCODE.PLANO_MIGRACAO_INVALIDO,
						MensagemCache.getInstance().getMensagem(MSGCODE.PLANO_MIGRACAO_INVALIDO),
						null);
			}
			
			if (codNovoPlano == Constantes.CODIGO_ESPECIAL_PLANO_GRATUITO) {
				final Map<String,String> parametros = new HashMap<String,String>();
				parametros.put(Constantes.P1, pdto.nome);
				throw AlugueRelaxeException.getInstance(MSGCODE.MIGRACAO_PLANO_GRATUITO_NEGADA,
						MensagemCache.getInstance().getMensagem(MSGCODE.MIGRACAO_PLANO_GRATUITO_NEGADA),
						null);
			}
			
			// Inclui o novo plano desejado
			ImovelPlanoRelacaoDTO iprdto = new ImovelPlanoRelacaoDTO();
			iprdto.idImovel = codImovel;
			iprdto.idPlano = codNovoPlano;
			ImovelPlanoBusiness<ImovelPlanoRelacaoDTO> ipb = new ImovelPlanoBusinessImpl();
			ImovelPlanoRelacaoDTO planoincdto = ipb.incluir(daoFactory, iprdto);
			
			// Insere a primeira fatura que será a guia para as próximas do plano migrado
			ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> ipfbo = new ImovelPlanoFaturaBusinessImpl();
			ImovelPlanoFaturaDTO ipfdto = new ImovelPlanoFaturaDTO();
			ipfdto.idImovelPlano = planoincdto.id;
			ipfdto.valorFatura = pdto.valor;
			ipfdto.valorDesconto = pdto.valorDesconto;
			ipfdto.dataVencimento = DateManager.getDateManagerInstance().add(pdto.numeroDiasCalculo);
			ipfbo.incluir(daoFactory, ipfdto);
			
			// Coloca o imóvel como status PENDENTE (P)
			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.atualizarStatusImovel(daoFactory, codImovel, Constantes.IMOVEL_STATUS_PENDENTE_PGTO);

			daoFactory.commit();
			
			Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, pdto.nome);
			dtoretorno = new ImovelPlanoRelacaoDTO();
			dtoretorno.msgcode = MSGCODE.PLANO_MIGRACAO_REALIZADA_COM_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.PLANO_MIGRACAO_REALIZADA_COM_SUCESSO, parametros);

			
			
		} catch (AlugueRelaxeException are) {
			throw are;
			
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
		
		return dtoretorno;
	}

	public ImovelPlanoRelacaoDTO gravarRegistro(ImovelPlanoRelacaoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelPlanoRelacaoDTO excluirRegistro(ImovelPlanoRelacaoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelPlanoRelacaoDTO atualizarRegistro(ImovelPlanoRelacaoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelPlanoRelacaoDTO pesquisarRegistro(ImovelPlanoRelacaoDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ImovelPlanoRelacaoDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPlanoBusiness<ImovelPlanoRelacaoDTO> bo = new ImovelPlanoBusinessImpl();
			dtoretorno = bo.procurar(daoFactory, dto);
			daoFactory.commit();
			
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);

		} catch (AlugueRelaxeException he) {
			throw he;
			
		} catch (HibernateException he) {
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;

	}


	public ImovelPlanoRelacaoDTO pesquisarRegistro(long id)
			throws AlugueRelaxeException {
		ImovelPlanoRelacaoDTO dtoretorno = new ImovelPlanoRelacaoDTO();
		dtoretorno.id = id;
		return this.pesquisarRegistro(dtoretorno);
	}


	public List<? extends ImovelPlanoRelacaoDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

}
