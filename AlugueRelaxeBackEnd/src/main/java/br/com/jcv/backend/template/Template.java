
package br.com.jcv.backend.template;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import br.com.jcv.backend.exception.AlugueRelaxeException;

public abstract class Template {

	private StringBuffer htmlTemplate = null;

	private Map<String, String> parametros = null;

	protected String pathTemplate = null;

	public Template(String pathTemplate) {
		htmlTemplate = new StringBuffer();
		parametros = new HashMap<String, String>();
		this.pathTemplate = pathTemplate;
	}
	
	protected void initMap(Map<String, String> conteudo){
		this.parametros = conteudo;
	}

	public void adicionarParametro(String chave, String conteudo) {
		this.parametros.put(chave, conteudo == null ? "" : conteudo);
	}
	
	public String getParametro(String chave) {
		return this.parametros.get(chave);
	}

	public void clone(Template tmpl) {
		tmpl.parametros.putAll(this.parametros);
	}

	public String getHtmlTemplate() {
		return htmlTemplate.toString();
	}

	public void clear() {
		if (this.parametros != null) {
			this.parametros.clear();
		}
	}

	/**
	 * TODO Comentar aqui.
	 *
	 * @throws SigemException Comentar aqui.
	 */
	protected void execute() {
		BufferedReader inFile = null;
		FileReader fr = null;

		// Executa a leitura do arquivo de template
		try {
			fr = new FileReader(this.pathTemplate);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		inFile = new BufferedReader(fr);
		while (true) {
			try {
				// Le a linha do template
				String linha = inFile.readLine().trim();

				// Executa a troca de parametros
				Iterator<Entry<String, String>> etChaves = parametros.entrySet().iterator();

				while (etChaves.hasNext()) {
					Entry<String, String> et = etChaves.next();
					linha = StringUtil.replaceStringNew(linha, et.getKey(), et.getValue());
				}

				htmlTemplate.append(linha);
				htmlTemplate.append("\n");
			} catch (NullPointerException e) {
				//Trace.print(e.getMessage());
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // while
	}
}
