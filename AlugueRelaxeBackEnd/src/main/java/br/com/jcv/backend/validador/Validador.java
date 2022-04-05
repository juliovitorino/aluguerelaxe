package br.com.jcv.backend.validador;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemCache;

public abstract class Validador<V extends DTOPadrao> {

	private static final String REGEXP_EMAIL = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,3}$";
	
	private List<String> listaErros = null;
	
	public Validador() {}
	
	/**
	 * executa o validador do DTO implementado na classe concreta
	 * @param dto
	 * @return Lista de string contendo os possiveis erros, se não
	 * houver erros a serem listados o objeto retornado terá o valor nulo.
	 */
	public abstract List<String> execute(V dto);

	protected void validarEmailPattern(String email) throws AlugueRelaxeException{
		Pattern p = Pattern.compile(REGEXP_EMAIL);     
		Matcher m = p.matcher(email.trim());   
		if (!m.find()) {
			HashMap<String, String> parametros = new HashMap<String, String>();
			parametros.put(Constantes.P1, email);
			add(MensagemCache.getInstance().getMensagem(MSGCODE.EMAIL_FORMATO_INCORRETO, parametros));		
		}
	}
	
	protected void add(String erro) {
		if (this.listaErros == null) {
			this.listaErros = new ArrayList<String>();
		}
		
		this.listaErros.add(erro);
	}
	
	protected List<String> getList() {
		return this.listaErros;
	}
	
	protected void merge(List<String> lista) {
		if (lista != null) {
			if (lista.size() > 0) {
				for (String descricaoErro: lista) {
					this.add(descricaoErro);
				}
			}
		}
	}
	
	protected void validarTamanhoCampoString(String campo, int tamanhoMin, int tamanhoMax, String msgerro) throws AlugueRelaxeException{
		if ((campo.trim().length() < tamanhoMin) || (campo.trim().length() > tamanhoMax)) {
			HashMap<String, String> parametros = new HashMap<String, String>();
			parametros.put(Constantes.P1, msgerro);
			parametros.put(Constantes.P2, String.valueOf(tamanhoMin));
			parametros.put(Constantes.P3, String.valueOf(tamanhoMax));
			add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_INTERVALO_INCORRETO, parametros));
		}
	}

	protected void validarCampoNulo(String campo, String msgerro) throws AlugueRelaxeException{
		if (campo == null) {
			HashMap<String, String> parametros = new HashMap<String, String>();
			parametros.put(Constantes.P1, msgerro);
			add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
		}
	}

	protected void validarCampoNulo(Date campo, String msgerro) throws AlugueRelaxeException{
		if (campo == null) {
			HashMap<String, String> parametros = new HashMap<String, String>();
			parametros.put(Constantes.P1, msgerro);
			add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
		}
	}

	
	
	
}
