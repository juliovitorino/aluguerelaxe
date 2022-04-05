package br.com.jcv.aluguerelaxe.client.administracao.login;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginRPCAsync {
	void login(String email, String senha, AsyncCallback<SessaoVO> callback) throws AlugueRelaxeFrontException;
	void validarSessao(String sid, AsyncCallback<SessaoVO> callback);
	void solicitarNovoCodigoAcesso(NovoCodigoAcessoVO dto,
			AsyncCallback<VOPadrao> callback);
	void encerrarSessao(AsyncCallback<VOPadrao> callback);
}

