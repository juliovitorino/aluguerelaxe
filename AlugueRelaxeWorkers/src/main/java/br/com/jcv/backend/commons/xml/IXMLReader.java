package br.com.jcv.backend.commons.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <h1>IXMLReader</h1>
 * <p>Classe abstrata para realiza��o da leitura de XML e retornar o tipo
 * serializavel que represente o objeto</p>
 * @author Julio
 *
 */
public abstract class IXMLReader<V> extends DefaultHandler {
	
	
	private String pathxml;
	protected String tempval;
	
	protected IXMLReaderListener listener = null;

	/**
	 * Construtor para armazenar o path completo de onde a classe concreta
	 * ira realizar o parse do xml e retornar um objeto do tipo V
	 * @param pathxml
	 */
	public IXMLReader(String pathxml){
		this.pathxml = pathxml;
	}
	
	/**
	 * Listener para ativacao de eventos
	 */
	public void setIXMLReaderListener(IXMLReaderListener listener) {
		this.listener = listener;
	}
	
	/**
	 * @return the pathxml
	 */
	public String getPathxml() {
		return pathxml;
	}

	/**
	 * @param pathxml the pathxml to set
	 */
	public void setPathxml(String pathxml) {
		this.pathxml = pathxml;
	}

	/**
	 * metodo para ser implementado em cada classe concreta
	 * @return V
	 */
	public abstract V execute();
	public abstract V getDTO();
	
	/**
	 * Executar o parse do documento XML de startup
	 *
	 * @param startupFile Nome do arquivo para configuraééo de startup
	 * @throws Exception Lanéar Exception
	 */
	protected void parseDocument(final String startupFile) throws Exception {

		//Obtem uma factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {

			//Obtem uma nova instancia do parser
			SAXParser sp = spf.newSAXParser();

			InputStream is = new FileInputStream (new File (startupFile));
			//InputStream is = this.getClass().getResourceAsStream(startupFile);

			//Executa o parse do arquivo e o registra classe para os Métodos de callback
			sp.parse(is, this);
			
		} catch (SAXException se) {
			throw se;
		} catch (ParserConfigurationException pce) {
			throw pce;
		} catch (IOException ie) {
			File curdir = new File(".");
			System.out.println("Arquivo '" + startupFile
					+ "' nao encontrado no diretorio "
					+ curdir.getCanonicalPath());
			throw ie;
		}
	}
	
	public void characters(final char[] ch, final int start, final int length)
	throws SAXException {
		this.tempval = new String(ch, start, length);
	}


}
