package br.com.jcv.backend.assinantes;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.Logger;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.factory.ValidadorFactory;
import br.com.jcv.backend.interfaces.business.AssinantesBusiness;
import br.com.jcv.backend.interfacesdao.AssinantesDAO;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
 * <h1>AssinantesBusinessImpl</h1> 
 * <p>Objetivo desta classe e gerenciar os metodos de assinaturas de campanha.
 *
 * @author Julio Vitorino
 * @version 1.10
 * @since 11 Jan 2012
 */
public class AssinantesBusinessImpl implements AssinantesBusiness<AssinantesDTO>{
	/**
	 * <h2>Atributo logger</h2>
	 * <p>Logger para instancica corrente de <code>AssinantesBusinessImpl</code></p>
	 */
	private static Logger logger = Logger.getLogger(AssinantesBusinessImpl.class.getName());
    
	public AssinantesBusinessImpl() {
		
	}

	/**
	 * <h2>inserver</h2>
	 * <p>Inscreve um assinante</p>
	 */
	public AssinantesDTO inscrever(DAOFactory daoFactory, AssinantesDTO dto) throws AlugueRelaxeException{
	
		// Validar os campos
		this.validarCamposObrigatorios(dto);
		
		// Obtem novo sequence
		dto.id = daoFactory.getNextSequence(daoFactory, "SEQ_ASSI_CD_ASSINANTE");
		
		Date dthash = new Date();
		
		// Calcula Hash
		dto.hash = this.geraSHA1(	String.valueOf(dto.id) +
									dto.nome +
									dto.email +
									dthash.toString() );
		
		// Obtem a campanha ativa
		dto.campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
		
		return this.procurar(daoFactory, this.incluir(daoFactory, dto));
	}
	
	public AssinantesDTO verificarIndicacaoAmigo(DAOFactory daoFactory, AssinantesDTO amigodto) throws AlugueRelaxeException {
		// Validar os campos
		this.validarCamposObrigatorios(amigodto);

		// Obtem a campanha ativa
		String campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
		
		return this.procurar(daoFactory, amigodto, campanha);
	}
	
	public AssinantesDTO verificarIndicadoViraParticipante(DAOFactory daoFactory, AssinantesDTO dto) throws AlugueRelaxeException {
		// Validar os campos
		this.validarCamposObrigatorios(dto);

		// Obtem a campanha ativa
		String campanha = VariavelCache.getInstance().getValor(VariavelConstantes.PROMOCAO_ATIVA);
		
		return this.procurar(daoFactory, dto, campanha, 0);
	}
	
	/**
	 * ativarInscricao - Muda o status da inscricao do assinante para A - Ativo
	 * @param DAOFactory
	 * @param String
	 * @throws AlugueRelaxeException
	 */
	public void ativarInscricao(DAOFactory daoFactory, String hash) throws AlugueRelaxeException {
	
		//----------------------------
		// Busca a inscricao pelo hash
		//----------------------------
		AssinantesDTO dto = this.procurar(daoFactory, hash);
		if (dto != null) {
			if ( dto.status.equals("P")) {

				//--------------------------------------------------------
				// Inscricao em estado de pendencia, logo pode ser ativada
				//--------------------------------------------------------
				AssinantesDAO dao = daoFactory.getAssinantesDAO(daoFactory);
				dao.updateStatusPendenteParaAtivo(hash);
			} else {
				throw new AlugueRelaxeException(MSGCODE.HASH_VALIDADO_PARTICIPANDO_PROMOCAO,
						 MensagemCache.getInstance().getMensagem(MSGCODE.HASH_VALIDADO_PARTICIPANDO_PROMOCAO),
						null);
			}
			
		} else {
			throw new AlugueRelaxeException(MSGCODE.HASH_PROMOCAO_INVALIDO,
					 MensagemCache.getInstance().getMensagem(MSGCODE.HASH_PROMOCAO_INVALIDO),
					null);
		}
		
	}
	
	public void validarCamposObrigatorios(AssinantesDTO dto)
			throws AlugueRelaxeException {
		List<String> lstErros = ValidadorFactory.getInstanceAssinantes().execute(dto);
		if ((lstErros != null) && (lstErros.size() > 0)) {
			throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
					MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
					lstErros );
		}
	}
	
	private String geraSHA1(String str) {
		// Aplica algoritimo SHA1 na string informada
		String strsha1 = "";
		BaseFactorySecurity bfs = BaseFactorySecurity.getInstance(BaseFactorySecurity.SHA1);
		try {
			strsha1 = bfs.criptografar(str);
		} catch (InvalidKeyException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		}	
		return strsha1;
	}

	public void setDados(AssinantesDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public AssinantesDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public AssinantesDTO incluir(DAOFactory daofactory, AssinantesDTO dto)
			throws AlugueRelaxeException {
		AssinantesDAO<AssinantesDTO> dao = daofactory.getAssinantesDAO(daofactory);
		return dao.insert(dto);
	}

	public AssinantesDTO atualizar(DAOFactory daofactory, AssinantesDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, AssinantesDTO dto)
			throws AlugueRelaxeException {
		AssinantesDAO<AssinantesDTO> dao = daofactory.getAssinantesDAO(daofactory);
		dao.delete(dto);
	}

	public AssinantesDTO procurar(DAOFactory daofactory, AssinantesDTO dto)
			throws AlugueRelaxeException {
		AssinantesDAO<AssinantesDTO> dao = daofactory.getAssinantesDAO(daofactory);
		return dao.load(dto);
	}

	public AssinantesDTO procurar(DAOFactory daofactory, String hash)
			throws AlugueRelaxeException {
		AssinantesDAO<AssinantesDTO> dao = daofactory.getAssinantesDAO(daofactory);
		return dao.loadHash(hash);
	}
	
	public AssinantesDTO procurar(DAOFactory daofactory, AssinantesDTO dto, String campanha)
			throws AlugueRelaxeException {
		AssinantesDAO<AssinantesDTO> dao = daofactory.getAssinantesDAO(daofactory);
		return dao.load(dto, campanha);
	}

	public AssinantesDTO procurar(DAOFactory daofactory, AssinantesDTO dto, String campanha, long parent)
			throws AlugueRelaxeException {
		AssinantesDAO<AssinantesDTO> dao = daofactory.getAssinantesDAO(daofactory);
		return dao.load(dto, campanha, parent);
}


	public List<AssinantesDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AssinantesDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<AssinantesDTO> listarRegistros(DAOFactory daoFactory, String promocao, String status) throws AlugueRelaxeException{
		AssinantesDAO<AssinantesDTO> dao = daoFactory.getAssinantesDAO(daoFactory);
		return dao.list(promocao, status);
	}


}
