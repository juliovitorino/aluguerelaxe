package br.com.jcv.backend.imovel.imagem;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ImovelBaseDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeBusinessImpl;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeDTO;
import br.com.jcv.backend.interfaces.services.ImovelImagemVideoService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.mensagem.MensagemCache;

public class ImovelImagemVideoServiceImpl implements
		ImovelImagemVideoService<ImovelImagemVideoDTO> {
	
	

	public ImovelImagemVideoDTO gravarRegistro(ImovelImagemVideoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelImagemVideoDTO excluirRegistro(ImovelImagemVideoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelImagemVideoDTO atualizarRegistro(ImovelImagemVideoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelImagemVideoDTO pesquisarRegistro(ImovelImagemVideoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends ImovelImagemVideoDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public DTOPadrao adicionarVideoImovel(ImovelImagemVideoDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DTOPadrao dtoretorno = null;
		
		// Pesquisa a ficha do imovel para verificacoes
		ImovelService<ImovelBaseDTO> is = new ImovelServiceImpl<ImovelBaseDTO>();
		ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(dto.imovel.id);
		
		if (ifcdto.imovelPlano.plano.id == Constantes.CODIGO_ESPECIAL_PLANO_GRATUITO) {
			throw new AlugueRelaxeException(MSGCODE.VIDEO_IMOVEL_PROIBIDO_PLANO_GRATUITO,
					MensagemCache.getInstance().getMensagem(MSGCODE.VIDEO_IMOVEL_PROIBIDO_PLANO_GRATUITO),
					null);
		}
		
		// Acerta alguns campos obrigatorios do DTO
		dto.tipo = Constantes.IND_TIPO_IMAGEM_VD;
		BaseFactorySecurity bfs = BaseFactorySecurity.getInstance(BaseFactorySecurity.SHA1);
		try {
			dto.hash = bfs.criptografar(dto.nome);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Adiciona o vídeo
		dtoretorno = is.adicionarImagemImovel(dto);
		final Map<String,String> parametros = new HashMap<String,String>();
		parametros.put(Constantes.P1, ifcdto.imovel.descricaoTituloAnuncio);
		dtoretorno.msgcode = MSGCODE.VIDEO_IMOVEL_INCLUIDO_COM_SUCESSO;
		dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.VIDEO_IMOVEL_INCLUIDO_COM_SUCESSO,parametros);
		
		return dtoretorno;
	}

}
