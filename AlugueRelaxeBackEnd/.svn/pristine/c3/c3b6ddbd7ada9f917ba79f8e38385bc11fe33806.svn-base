package br.com.jcv.backend.gatewaysms;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.filasms.FilaSMSDTO;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class SMSEmpresaGatewaySMS implements IGatewaySMS {

	public static final String TAG_CREDENCIAL = "${CREDENCIAL}";
	public static final String TAG_MOBILE = "${MOBILE}";
	public static final String TAG_MESSAGE = "${MESSAGE}";

	public FilaSMSDTO execute(FilaSMSDTO smsdto) {
	
		// Carrega parametrizacao do Servico SMSEmpresa
		
		String credential = null;
		try {
			credential = VariavelCache.getInstance().getValor(VariavelConstantes.CREDENCIAL_SMSEMPRESA);
		} catch (AlugueRelaxeException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String mobile = smsdto.celular;
		
		// Verifica o tamanho da mensagem
		if (smsdto.msg.length() > IGatewaySMS.MAX_CARACTERES_SMS) {
			smsdto.msg = smsdto.msg.substring(1, IGatewaySMS.MAX_CARACTERES_SMS);
		} 
		
		String Msg = smsdto.msg;
		try {
			Msg = URLEncoder.encode(Msg, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Obtem a URL de envio do provedor do servico
		String connection = null;
		try {
			connection = VariavelCache.getInstance().getValor(VariavelConstantes.URL_CONNECTION_SMSEMPRESA);
		} catch (AlugueRelaxeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		connection = StringUtil.replaceStringNew(connection, TAG_CREDENCIAL, credential);
		connection = StringUtil.replaceStringNew(connection, TAG_MOBILE, mobile);
		connection = StringUtil.replaceStringNew(connection, TAG_MESSAGE, Msg);
		
		try {
			if (VariavelCache.getInstance().getValor(VariavelConstantes.PERMITIR_ENVIO_SMS_GATEWAY).equals("ON")){
				// Conecta ao servico
				URL url;
				try {
					url = new URL(connection);
					InputStream input = url.openStream();
					byte[] b = new byte[4];
					input.read(b, 0, b.length);
					// recebe o retorno do servico e obtem uma traducao
					smsdto.statusCode = new String(b);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (AlugueRelaxeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return smsdto;
	}
}

