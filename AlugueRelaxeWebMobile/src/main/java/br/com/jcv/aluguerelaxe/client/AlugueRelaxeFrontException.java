package br.com.jcv.aluguerelaxe.client;

import java.io.Serializable;
import java.util.List;

public class AlugueRelaxeFrontException extends Exception implements
		Serializable {

	private static final long serialVersionUID = -4474671300312733692L;
	private String mensagem;
	private List<String> lstErros = null;
	
	/*
	 * É IMPRESCINDÍVEL UM CONSTRUTOR SEM PARAMETROS!
	 */
	public AlugueRelaxeFrontException(){
		
	}
	public AlugueRelaxeFrontException(String msg){
		this.setMensagem(msg);
	}
	public AlugueRelaxeFrontException(String msg, List<String> lstErros) {
		this.setMensagem(msg);
		this.lstErros = lstErros;
	}

	public List<String> getListaErros() {
		return lstErros;
	}
	
	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
