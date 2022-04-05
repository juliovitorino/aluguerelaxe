package br.com.jcv.aluguerelaxe.client.imovel.thread;

import java.util.Date;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ContatoAnuncianteThreadVO extends VOPadrao 
			implements IsSerializable {
	public long id;
	public long idParent;
	public String origem;
	public String status;
	public String modo;
	public Date dataCadastro;
	public String dataCadastroStr;
	public String hash;
	public String thread;
	public String threadEditada;
	public ContatoAnuncianteThreadVO threadfilho;
}
