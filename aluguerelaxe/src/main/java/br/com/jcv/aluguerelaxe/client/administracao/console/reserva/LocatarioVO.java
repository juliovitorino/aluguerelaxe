package br.com.jcv.aluguerelaxe.client.administracao.console.reserva;

import java.util.Date;

import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LocatarioVO extends VOPadrao implements IsSerializable {
	public long id;
	public String nome;
	public String cpf;
	public Date dataNascimento;
	public String email;
	public Date dataCadastro;
	public EnderecoVO endereco;
	public TelefoneVO telefone;
}
