package br.com.jcv.aluguerelaxe.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.componente.form.CepRPC;
import br.com.jcv.aluguerelaxe.client.vo.CepVO;
import br.com.jcv.aluguerelaxe.server.cep.CepXMLDOM;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class CepRPCImpl extends RemoteServiceServlet implements CepRPC {

	private static final long serialVersionUID = 7635613495442615723L;

	public CepVO buscaEndereco(String cep) throws AlugueRelaxeFrontException {
		CepVO cepvo = null;
		
		//-------------------------------------
		// Validacao
		//-------------------------------------
		try {
			if (cep != null){
				if (cep.length() != 8) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Cep]");
					parametros.put(Constantes.P2, "8");
					throw new AlugueRelaxeFrontException(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_EXATO, parametros));
				}
				
				try {
					long ceplong = Long.valueOf(cep);
				} catch (NumberFormatException nfe) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Cep]");
					throw new AlugueRelaxeFrontException(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_CONTER_SOMENTE_NUMEROS, parametros));
				}
			} 
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
		
		String ceptraco = cep.substring(0, 5) + "-" + cep.substring(5);
		String urlString = null;
		try {
			urlString = VariavelCache.getInstance().getValor(VariavelConstantes.WEBSERVICECEP);
			urlString = StringUtil.replaceStringNew(urlString, "${cep}", ceptraco);

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//---------------------------------------
		// 1) Invoca webservice na kinghost
		//---------------------------------------
		try {
			// cria o objeto url   
			URL url = new URL(urlString);  
	
			// cria o objeto httpurlconnection   
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
			
			// seta o metodo   
			connection.setRequestProperty("Request-Method", "GET");  
			
			// conecta com a url destino   
			connection.connect();  
			
			// abre a conexão pra input   
			BufferedReader br =   
				new BufferedReader(new InputStreamReader(connection.getInputStream()));  
	
			// le ate o final   
			StringBuilder newData = new StringBuilder(10000);   
			String s = "";   
			while (null != ((s = br.readLine()))) {   
				newData.append(s);   
			}   
			br.close();  
	
			String xmlMock = newData.toString();
	
			
			
			
			
			//---------------------------------------
			// 2) Resolve o retorno e devolve VO
			//---------------------------------------
	  
	//		String xmlMock = "<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?>" +
	//		"<webservicecep>" +
	//		"<resultado>1</resultado>" +
	//		"<resultado_txt>sucesso - cep completo</resultado_txt>" +
	//		"<uf>RJ</uf>" +
	//		"<cidade>Volta Redonda</cidade>" +
	//		"<bairro>Siderlândia</bairro>" +
	//		"<tipo_logradouro>Rua</tipo_logradouro>" +
	//		"<logradouro>Araruama</logradouro>" +
	//		"</webservicecep>";
	  
			CepXMLDOM cepxmldom = new CepXMLDOM();
			cepvo = cepxmldom.realizaLeituraXML(xmlMock);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Atualiza as informações de UFCI_codigo_cidade_item de acordo com a UF x Cidade
		// chama o serviço..
		
		
		return cepvo;
	}

}

