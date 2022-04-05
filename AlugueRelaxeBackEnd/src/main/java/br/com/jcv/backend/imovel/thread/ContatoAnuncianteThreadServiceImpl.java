package br.com.jcv.backend.imovel.thread;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.contatoanunciante.ContatoAnuncianteBusinessImpl;
import br.com.jcv.backend.contatoanunciante.ContatoAnuncianteServiceImpl;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.business.ContatoAnuncianteBusiness;
import br.com.jcv.backend.interfaces.business.ContatoAnuncianteThreadBusiness;
import br.com.jcv.backend.interfaces.services.ContatoAnuncianteService;
import br.com.jcv.backend.interfaces.services.ContatoAnuncianteThreadService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.interfaces.services.ReservaService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.reserva.ReservaDTO;
import br.com.jcv.backend.reserva.ReservaServiceImpl;

public class ContatoAnuncianteThreadServiceImpl implements ContatoAnuncianteThreadService {

	public DTOPadrao atualizarFotoThread(String hash, String foto) throws AlugueRelaxeException{

		DAOFactory daoFactory =  null;
		DTOPadrao dtoretorno = null;
		
		try {
		
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			//---------------------------------------------------------------------
			// Obtem a thread identificada pelo hash
			//---------------------------------------------------------------------
			//ContatoAnuncianteThreadBusiness ccb = new ContatoAnuncianteThreadBusinessImpl();
			//ContatoAnuncianteThreadDTO catdto = ccb.procurar(daoFactory, hash);

			//---------------------------------------------------------------------
			// Atualiza a foto do visitante na thread no contato pai
			//---------------------------------------------------------------------
			ContatoAnuncianteBusiness cab = new ContatoAnuncianteBusinessImpl();
			cab.atualizarFotoThread(daoFactory, hash, foto);

			daoFactory.commit();
			
			dtoretorno = new DTOPadrao();
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

	public DTOPadrao aprovarThread(String hash, String modo, boolean editada) throws AlugueRelaxeException{

		DAOFactory daoFactory =  null;
		DTOPadrao dtoretorno = null;
		
		try {
		
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			//---------------------------------------------------------------------
			// Obtem a thread identificada pelo hash
			//---------------------------------------------------------------------
			ContatoAnuncianteThreadBusiness ccb = new ContatoAnuncianteThreadBusinessImpl();
			ContatoAnuncianteThreadDTO catdto = ccb.procurar(daoFactory, hash);
			daoFactory.commit();

			//---------------------------------------------------------------------
			// Obtem o contato com anunciante referente a esta thread
			//---------------------------------------------------------------------
			ContatoAnuncianteService cab = new ContatoAnuncianteServiceImpl();
			ContatoClienteDTO ccdto = cab.pesquisarRegistro(catdto.hashParent);

			//---------------------------------------------------------------------
			// Obtem a ficha do imovel completa
			//---------------------------------------------------------------------
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(ccdto.idImovel);

			daoFactory.open();
			daoFactory.beginTransaction();

			ContatoAnuncianteThreadBusiness bo = new ContatoAnuncianteThreadBusinessImpl();
			bo.aprovarThread(daoFactory, ccdto, catdto, ifcdto, editada, modo);
			daoFactory.commit();
			
			dtoretorno = new DTOPadrao();
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

	private void validarThreadEncerrada(ContatoClienteDTO ccdto) throws AlugueRelaxeException {
		ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
		ContatoClienteDTO dto = cas.pesquisarRegistro(ccdto);
		if (dto != null){
			if (dto.threadStatus.equals(Constantes.INATIVO)){ 
				throw new AlugueRelaxeException(MSGCODE.THREAD_SOMENTE_PARA_LEITURA,
					MensagemCache.getInstance().getMensagem(MSGCODE.THREAD_SOMENTE_PARA_LEITURA),
					null);
			}
		} else {
				throw new AlugueRelaxeException(MSGCODE.CONTATO_ANUNCIANTE_INVALIDO,
					MensagemCache.getInstance().getMensagem(MSGCODE.CONTATO_ANUNCIANTE_INVALIDO),
					null);
		}
	}

	public ContatoAnuncianteThreadDTO gravarResposta(ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException{
		//-------------------------------------------------------------
		// Antes de gravar resposta, verifica se a Thread foi encerrada
		//-------------------------------------------------------------
		if (catdto.modo.equals(Constantes.PERGUNTA_RESPOSTA)){
			this.validarThreadEncerrada(ccdto);
		}

		DAOFactory daoFactory =  null;
		ContatoAnuncianteThreadDTO dtoretorno = null;
		
		try {
			ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
			ccdto = cas.pesquisarRegistro(ccdto.id);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ccdto.ifcdto = is.pesquisarFichaCompletaImovel(ccdto.idImovel);
		
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			ContatoAnuncianteThreadBusiness bo = new ContatoAnuncianteThreadBusinessImpl();
			dtoretorno = bo.incluirResposta(daoFactory, ccdto, catdto);
			daoFactory.commit();
			
			dtoretorno.msgcode = MSGCODE.THREAD_RESPOSTA_GRAVADA_COM_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.THREAD_RESPOSTA_GRAVADA_COM_SUCESSO);
			
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

	public ContatoAnuncianteThreadDTO gravarDuvida(ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException{
		//-------------------------------------------------------------
		// Antes de gravar, verifica se a Thread foi encerrada.
		//-------------------------------------------------------------
		this.validarThreadEncerrada(ccdto);

		DAOFactory daoFactory =  null;
		ContatoAnuncianteThreadDTO dtoretorno = null;
		
		ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
		ccdto = cas.pesquisarRegistro(ccdto.id);
		
		ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		ccdto.ifcdto = is.pesquisarFichaCompletaImovel(ccdto.idImovel);
		
		
		try {
		
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ContatoAnuncianteThreadBusiness bo = new ContatoAnuncianteThreadBusinessImpl();
			dtoretorno = bo.incluir(daoFactory, ccdto, catdto);
			daoFactory.commit();
			
			dtoretorno.msgcode = MSGCODE.THREAD_DUVIDA_GRAVADA_COM_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.THREAD_DUVIDA_GRAVADA_COM_SUCESSO);
			
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

	public ContatoAnuncianteThreadDTO gravarRegistro(ContatoAnuncianteThreadDTO dto) throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		ContatoAnuncianteThreadDTO dtoretorno = null;
		
		try {
		
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ContatoAnuncianteThreadBusiness bo = new ContatoAnuncianteThreadBusinessImpl();
			dtoretorno = bo.incluir(daoFactory, dto);
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
	
	public List<ContatoAnuncianteThreadDTO> listarComentarios(long idImovel) throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<ContatoAnuncianteThreadDTO> lst = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ContatoAnuncianteThreadBusiness bo = new ContatoAnuncianteThreadBusinessImpl();
			lst = bo.listarComentarios(daoFactory, idImovel);
			if ((lst != null) && (lst.size() > 0)){
				for (ContatoAnuncianteThreadDTO catdto : lst){
					catdto.threadfilho = bo.procurar(daoFactory, catdto.id);
				}
			}
			daoFactory.commit();

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
		return lst;

	}

	
	public List<ContatoAnuncianteThreadDTO> listarThreads(String hash, String modo) throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<ContatoAnuncianteThreadDTO> lst = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ContatoAnuncianteThreadBusiness bo = new ContatoAnuncianteThreadBusinessImpl();
			lst = bo.listarThreads(daoFactory, hash, modo);
			daoFactory.commit();
			

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
		return lst;

	}


	public ContatoAnuncianteThreadDTO excluirRegistro(
			ContatoAnuncianteThreadDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


	public ContatoAnuncianteThreadDTO atualizarRegistro(
			ContatoAnuncianteThreadDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


	public ContatoAnuncianteThreadDTO pesquisarRegistro(
			ContatoAnuncianteThreadDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<? extends ContatoAnuncianteThreadDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

}
