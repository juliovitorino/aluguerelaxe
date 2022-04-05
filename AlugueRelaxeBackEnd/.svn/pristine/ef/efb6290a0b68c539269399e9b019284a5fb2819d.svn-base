package br.com.jcv.backend.exception;

public class AlugueRelaxeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2361980716390502746L;
	private String codigoErro;
	private String mensagem;
	private Object objeto;

	public static AlugueRelaxeException getInstance(String codigoErro, String mensagem, Object objeto) {
		return new AlugueRelaxeException(codigoErro,mensagem,objeto);
	}
	
	/**
	 * @return the codigoErro
	 */
	@SuppressWarnings("unused")
	public String getCodigoErro() {
		return codigoErro;
	}

	/**
	 * @param codigoErro the codigoErro to set
	 */
	public void setCodigoErro(String codigoErro) {
		this.codigoErro = codigoErro;
	}

	/**
	 * @return the mensagem
	 */
	@SuppressWarnings("unused")
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public AlugueRelaxeException() {
		super();
	}
	
	public AlugueRelaxeException(Exception e) {
		super(e);
	}
	
	public AlugueRelaxeException(Exception e, String codigoErro, String mensagem, Object objeto ) {
		super(e);
		this.setCodigoErro(codigoErro);
		this.setMensagem(mensagem);
		this.setObjeto(objeto);
	}

	public AlugueRelaxeException(String codigoErro, String mensagem, Object objeto ) {
		this();
		this.setCodigoErro(codigoErro);
		this.setMensagem(mensagem);
		this.setObjeto(objeto);
	}

	/**
	 * @return the objeto
	 */
	public Object getObjeto() {
		return objeto;
	}

	/**
	 * @param objeto the objeto to set
	 */
	private void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
}
