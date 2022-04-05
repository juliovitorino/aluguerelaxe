package br.com.jcv.aluguerelaxe.client.componente.classificador;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ClassificadorVO implements IsSerializable {
	public int notaClassificador;
	public String titulo;
	public String comentarioClassificador;
	
	public ClassificadorVO() {
	}
	
	public ClassificadorVO(int notaClassificador, String titulo, String comentarioClassificador) {
		this.notaClassificador = notaClassificador;
		this.titulo = titulo;
		this.comentarioClassificador = comentarioClassificador;
	}
}