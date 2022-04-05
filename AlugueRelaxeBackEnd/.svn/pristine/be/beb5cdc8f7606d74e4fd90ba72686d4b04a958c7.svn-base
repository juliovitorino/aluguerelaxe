package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.cliente.ClienteContraSenhaDTO;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;

/**
 * @author Julio
 * @version 1.0
 * @created 14/02/2010
 */
public interface ClienteService<DTO> extends ApplicationService<DTO> {

	List<DTO> listarNovasContasPendentesAtivacao() throws AlugueRelaxeException;
	DTO criarNovaConta(ClienteContraSenhaDTO dto) throws AlugueRelaxeException;
	void ativarNovaConta(String hash) throws AlugueRelaxeException;
	DTO pesquisarRegistro(String hash) throws AlugueRelaxeException;
	DTOPadrao atualizarFichaCadastral(ClienteDTO dto) throws AlugueRelaxeException;
	DTOPadrao trocarSenha(ClienteContraSenhaDTO dto) throws AlugueRelaxeException;
	List<ContatoClienteDTO> listarContatosAnunciante(long idCliente) throws AlugueRelaxeException;
	DTOPadrao atualizarEnqueteModoPublicidade(ClienteDTO dto) throws AlugueRelaxeException;
	List<Long> listarRegistros(String status) throws AlugueRelaxeException;
	void enviarEmailAtivacaoConta(ClienteDTO dto) throws AlugueRelaxeException;
	void atualizarFotoPerfil(long id, String arquivo, int tipoFoto)	throws AlugueRelaxeException;

}