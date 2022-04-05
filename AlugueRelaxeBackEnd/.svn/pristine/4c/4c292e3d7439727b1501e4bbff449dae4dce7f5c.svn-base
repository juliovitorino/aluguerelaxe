package br.com.jcv.backend.locatario;

import java.util.List;

import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.LocatarioBusiness;
import br.com.jcv.backend.interfaces.services.LocatarioService;
import br.com.jcv.backend.mensagem.MensagemCache;

public class LocatarioServiceImpl implements LocatarioService<LocatarioDTO> {

	public LocatarioDTO gravarRegistro(LocatarioDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public LocatarioDTO excluirRegistro(LocatarioDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public LocatarioDTO atualizarRegistro(LocatarioDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public LocatarioDTO pesquisarRegistro(LocatarioDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends LocatarioDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public LocatarioDTO pesquisar(String email) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		LocatarioDTO retorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			LocatarioBusiness<LocatarioDTO> bo = new LocatarioBusinessImpl();
 			retorno = bo.procurar(daoFactory, email);
			daoFactory.commit();
			
			if (retorno != null){
				retorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
				retorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			}

		} catch (HibernateException he) {
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				throw new AlugueRelaxeException(e);
			}
		}
		
		return retorno;
	}

}
