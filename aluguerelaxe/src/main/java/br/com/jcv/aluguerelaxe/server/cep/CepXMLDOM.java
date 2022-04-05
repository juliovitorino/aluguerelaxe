package br.com.jcv.aluguerelaxe.server.cep;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.jcv.aluguerelaxe.client.vo.CepVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;

/**
 * Classe que faz o parser/leitura de uma String XML
 * retornada pelo webservice de CEP do KingHost
 * <?xml version="1.0" encoding="iso-8859-1" ?> 
 * <webservicecep>
 * 		<resultado>1</resultado> 
 * 		<resultado_txt>sucesso - cep completo</resultado_txt> 
 * 		<uf>RJ</uf> 
 * 		<cidade>Volta Redonda</cidade> 
 * 		<bairro>Siderlândia</bairro> 
 * 		<tipo_logradouro>Rua</tipo_logradouro> 
 * 		<logradouro>Araruama</logradouro> 
 * </webservicecep>
 * 
 * @author Julio Vitorino
 *
 */
public class CepXMLDOM {

	/**
	 * Realiza a leitura do arquivo XML informado como parâmetro
	 * @param xml String contendo uma estrutura XML
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public CepVO realizaLeituraXML(String xml) throws ParserConfigurationException, SAXException, IOException{
	
		//Transforma a string em uma InputStream
		InputStream stream = new ByteArrayInputStream(xml.getBytes());
		
		//fazer o parse do arquivo e criar o documento XML
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(stream);
		
		//Cria um CepVO para retornar o resultado do parse
		CepVO cepvo = new CepVO();

		//Passo 1: obter o elemento raiz "webservicecep"
		Element raiz = doc.getDocumentElement();
		System.out.println("O elemento raiz é: " + raiz.getNodeName());

		//Passo 2: obtém resultado
		NodeList resultados = raiz.getElementsByTagName("resultado");
		Node resultado = resultados.item(0).getFirstChild();
		cepvo.resultadoWebservice = resultado.getNodeValue();
		System.out.println("Resultado do webservicecep: " + resultado.getNodeValue());
		
		//Passo 3: obtém texto do resultado
		NodeList resultados_txt = raiz.getElementsByTagName("resultado_txt");
		Node resultado_txt = resultados_txt.item(0).getFirstChild();
		cepvo.resultadoWebserviceTexto = resultado_txt.getNodeValue();
		
		// Obtem as informacoes do cep
		cepvo.endereco = new EnderecoVO();

		//Passo 4: obtém uf
		NodeList ufs = raiz.getElementsByTagName("uf");
		Node uf = ufs.item(0).getFirstChild();
		cepvo.endereco.uf = uf.getNodeValue();

		//Passo 5: obtém cidade
		NodeList cidades = raiz.getElementsByTagName("cidade");
		Node cidade = cidades.item(0).getFirstChild();
		cepvo.endereco.cidade = cidade.getNodeValue();

		//Passo 6: obtém bairro
		NodeList bairros = raiz.getElementsByTagName("bairro");
		Node bairro = bairros.item(0).getFirstChild();
		cepvo.endereco.bairro = bairro.getNodeValue();
		
		//Passo 7: obtém tipo e nome da rua
		NodeList ruas = raiz.getElementsByTagName("logradouro");
		Node rua = ruas.item(0).getFirstChild();
		cepvo.endereco.nome = rua.getNodeValue();
		
		NodeList tipos = raiz.getElementsByTagName("tipo_logradouro");
		Node tipo = tipos.item(0).getFirstChild();
		cepvo.endereco.logradouro = tipo.getNodeValue();
		
		
		return cepvo;
	}
}
