package br.com.jcv.backend.gatewaysms;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.filasms.FilaSMSDTO;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class MobileProntoGatewaySMS implements IGatewaySMS {

	public static final String TAG_CREDENCIAL = "${CREDENCIAL}";
	public static final String TAG_PRINCIPAL_USER = "${PRINCIPAL_USER}";
	public static final String TAG_AUX_USER = "${AUX_USER}";
	public static final String TAG_MOBILE = "${MOBILE}";
	public static final String TAG_SEND_PROJECT = "${SEND_PROJECT}";
	public static final String TAG_MESSAGE = "${MESSAGE}";

	public FilaSMSDTO execute(FilaSMSDTO smsdto) {
	
		Map<String,String> mpStatusMobilePronto = new HashMap<String,String>();
		mpStatusMobilePronto.put("X01","Parametros com erro");
		mpStatusMobilePronto.put("X02","Parametros com erro");
		mpStatusMobilePronto.put("000","Mensagem enviada com sucesso");
		mpStatusMobilePronto.put("001","Credencial Invalida");
		mpStatusMobilePronto.put("005","Mobile fora do formato: +999(9999)999999999");
		mpStatusMobilePronto.put("007","SEND_PROJECT tem que ser S ou N");
		mpStatusMobilePronto.put("008","Mensagem ou FROM+MESSAGE maior que 142 posicoes");
		mpStatusMobilePronto.put("009","CRITICO: Sem credito para envio de SMS");
		mpStatusMobilePronto.put("010","CRITICO: Gateway bloqueado");
		mpStatusMobilePronto.put("012","Mobile no formato padrao mas incorreto");
		mpStatusMobilePronto.put("013","Mensagem vazia ou corpo invalido");
		mpStatusMobilePronto.put("015","Pais sem operacao");
		mpStatusMobilePronto.put("016","Mobile com tamanho do codigo de area invalido");
		mpStatusMobilePronto.put("017","Operadora nao autorizada para esta credencial");
		mpStatusMobilePronto.put("900","Erro de autenticacao ou Limite de seguranca excedido");
		
		// Carrega parametrizacao do Servico Mobile Pronto
		
		String credential = null;
		String mainUser = null;
		String auxUser = null;
		try {
			credential = VariavelCache.getInstance().getValor(VariavelConstantes.CREDENCIAL_MOBILE_PRONTO);
			mainUser = VariavelCache.getInstance().getValor(VariavelConstantes.PROJETO_MOBILE_PRONTO); 
			auxUser = VariavelCache.getInstance().getValor(VariavelConstantes.AUX_USER_MOBILE_PRONTO);
		} catch (AlugueRelaxeException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String mobile = "+55".concat(smsdto.celular);
		String sendProject = "N";
		
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
			connection = VariavelCache.getInstance().getValor(VariavelConstantes.URL_CONNECTION_MOBILE_PRONTO);
		} catch (AlugueRelaxeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		connection = StringUtil.replaceStringNew(connection, TAG_CREDENCIAL, credential);
		connection = StringUtil.replaceStringNew(connection, TAG_PRINCIPAL_USER, mainUser);
		connection = StringUtil.replaceStringNew(connection, TAG_AUX_USER, auxUser);
		connection = StringUtil.replaceStringNew(connection, TAG_MOBILE, mobile);
		connection = StringUtil.replaceStringNew(connection, TAG_SEND_PROJECT, sendProject);
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

		String traducao = mpStatusMobilePronto.get(smsdto.statusCode);
		
		if(traducao != null){
			smsdto.statusCode.concat(":").concat(traducao);
		}
		return smsdto;
	}
}

/*
codigo java


String Credential = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
String MainUser = "SEUPROJETO";
String AuxUser = "AUX1";
String Mobile = "552199999999";
char SendProject = "S";
String Msg = "Seu Primeiro Envio de SMS via Java usado o Gateway Mobile Pronto.";
Msg = URLEncoder.encode(Msg, "UTF-8");
String connection =
"http://www.mpgateway.com/v_2_00/smspush/enviasms.aspx?CREDENCIAL="+
Credential +"&PRINCIPAL_USER="+ MainUser +"&AUX_USER="+ AuxUser +"&MOBILE="+ Mobile
+"&SEND_PROJECT="+ SendProject +"&MESSAGE="+ Msg;
URL url = new URL(connection);
InputStream input = url.openStream();
byte[] b = new byte[4];
input.read(b, 0, b.length);
String StatusCode = new String(b);
out.println("Status code = " + StatusCode);

esta URL funcinou direto no browser:
https://www.mpgateway.com/v_2_00/smspush/enviasms.aspx?CREDENCIAL=A8FF4013F4D09B79D88A03269EEB435ED980032A&PRINCIPAL_USER=ALUGUERELAXE&AUX_USER=JCV&MOBILE=+552492753073&SEND_PROJECT=S&MESSAGE=Ola

*/


