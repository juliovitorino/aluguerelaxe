package br.com.jcv.aluguerelaxe.client.componente.datagrid;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CondicaoVO extends VOPadrao implements IsSerializable {
	public String campo;
	public String condicao;
	public String conteudo;
	public String operadorLogico;
	
	public CondicaoVO(){
		
	}
	
	public CondicaoVO(String campo, String condicao, String conteudo, String operadorLogico) {
		this.campo = campo;
		this.condicao = condicao;
		this.conteudo = conteudo;
		this.operadorLogico = operadorLogico;
	}

	public CondicaoVO(String campo, String condicao, String conteudo) {
		this(campo,condicao,conteudo, null);
	}
}
