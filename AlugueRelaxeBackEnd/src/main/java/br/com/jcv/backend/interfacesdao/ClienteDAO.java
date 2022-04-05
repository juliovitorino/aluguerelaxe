package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.portal.NovoCodigoAcessoDTO;
import br.com.jcv.backend.portal.login.LoginDTO;

/**
 * @author Julio
 * @version 1.0
 * @created 14-Feb-2010
 */
public interface ClienteDAO<DTO> extends DAO<DTO> {
	List<TelefoneDTO> obtemTelefonesCliente(long idCliente) throws AlugueRelaxeException;
	List<DTO> listarNovasContasPendentesAtivacao() throws AlugueRelaxeException;
	void ativarNovaConta(String hash) throws AlugueRelaxeException;
	DTO getStatus(String hash) throws AlugueRelaxeException;
	public DTO load(LoginDTO dto) throws AlugueRelaxeException;
	void updateSenha(long idCliente, String novaSenha) throws AlugueRelaxeException;
	void updateCodigoAcesso(NovoCodigoAcessoDTO ncadto) throws AlugueRelaxeException;
	void updateLimparCodigoAcesso(String email) throws AlugueRelaxeException;
	List<ContatoClienteDTO> listarContatosAnunciante(long idCliente) throws AlugueRelaxeException;
	long countImoveisAnunciante(long idCliente, long idPlano) throws AlugueRelaxeException;
	void updateEnqueteModoPublicidade(ClienteDTO dto) throws AlugueRelaxeException;
	List<Long> list(String status) throws AlugueRelaxeException;
	void incPerguntas(long idCliente) throws AlugueRelaxeException;
	void incRespostas(long idCliente) throws AlugueRelaxeException;
	void updateFotoChat(long id, String arquivo) throws AlugueRelaxeException;
	void updateFotoPerfil(long id, String arquivo) throws AlugueRelaxeException;

}