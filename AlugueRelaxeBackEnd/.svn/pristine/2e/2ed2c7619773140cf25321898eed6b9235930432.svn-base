package br.com.jcv.backend.contrato;

import java.io.FileOutputStream;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.template.Template;

public class Contrato {

	Template template = null;
	String nome;
	
	// exemplo de como instanciar
	// 		Contrato ctr = new Contrato("/home/aluguerelaxe/www/contratos/ctr-7373.html", TemplateFactory.getInstance(templateFactory,conteudo));

	public Contrato(String nome, Template template) {
		this.template = template;
		this.nome = nome;
	}
	
	public void gerar() throws AlugueRelaxeException{

		try {
			FileOutputStream fos = new FileOutputStream(nome);     
			String texto = "Gravando um arquivo";     
			fos.write(template.getHtmlTemplate().getBytes());     
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}

