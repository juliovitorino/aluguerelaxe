package br.com.jcv.aluguerelaxe.client.cliente;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.novaconta.ClienteCadastroVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("clienterpc")
public interface ClienteRPC extends RemoteService {
	
	ClienteVO pesquisaRegistro(ClienteVO vo);
	VOPadrao atualizarFichaCadastro(ClienteVO vo) throws AlugueRelaxeFrontException;
	ClienteVO criarNovaConta(ClienteCadastroVO dto) throws AlugueRelaxeFrontException;
	VOPadrao trocarSenha(ClienteCadastroVO dto) throws AlugueRelaxeFrontException;
	List<ContatoClienteVO> listarContatosCliente(ClienteVO vo) throws AlugueRelaxeFrontException;
	VOPadrao atualizarEnqueteModoPublicidade(ClienteVO vo) throws AlugueRelaxeFrontException;

	public static class Util {
		public static ClienteRPCAsync getInstance() {
			return GWT.create(ClienteRPC.class);
		}
	}
	
}

