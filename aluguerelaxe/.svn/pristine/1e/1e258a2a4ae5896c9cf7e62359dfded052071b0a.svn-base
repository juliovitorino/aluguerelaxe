
package br.com.jcv.aluguerelaxe.client.cliente;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.novaconta.ClienteCadastroVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClienteRPCAsync {

	void pesquisaRegistro(ClienteVO vo, AsyncCallback<ClienteVO> callback);
	void atualizarFichaCadastro(ClienteVO vo, AsyncCallback<VOPadrao> callback);
	void criarNovaConta(ClienteCadastroVO dto, AsyncCallback<ClienteVO> callback);
	void trocarSenha(ClienteCadastroVO dto, AsyncCallback<VOPadrao> callback);
	void listarContatosCliente(ClienteVO vo, AsyncCallback<List<ContatoClienteVO>> callback);
	void atualizarEnqueteModoPublicidade(ClienteVO vo, AsyncCallback<VOPadrao> callback);

}

