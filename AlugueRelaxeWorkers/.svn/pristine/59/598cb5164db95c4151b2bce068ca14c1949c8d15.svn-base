package br.com.jcv.backend.workers.spammer.email;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class EmailSpammer extends Email {

	private String smtp_port = null;
	private String smtp_server = null;
	private String conta = null;
	private String pwd = null;
	
	public EmailSpammer(Map<String,String> conteudo) {
		super(-1);
		this.conteudo = conteudo;
	}
	
	public void setSmtpPort(String smtp_port) {
		this.smtp_port = smtp_port;
	}

	public void setSmtpServer(String smtp_server) {
		this.smtp_server = smtp_server;
	}
	
	public void setConta(String conta) {
		this.conta = conta;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public void enviar(List<EmailRecord> lstDestinatarios, 
	           List<EmailRecord> lstComCopia,
	           String assunto,
	           String textoEmail) throws AlugueRelaxeException {
		this.enviar(null, lstDestinatarios, lstComCopia, assunto, textoEmail);
	}
	
	public void enviar(String de, 
			           List<EmailRecord> lstDestinatarios, 
			           List<EmailRecord> lstComCopia,
			           String assunto,
			           String textoEmail) throws AlugueRelaxeException{

		try {
			List<Address> lstAddress = new ArrayList<Address>();
			List<Address> lstAddressCC = new ArrayList<Address>();
			
			Properties props = new Properties();
			
			// Configurações para mail server local
			props.put("mail.smtp.host", this.smtp_server);
			props.put("mail.smtp.port", this.smtp_port);
			props.put("mail.smtp.auth", "true"); 
			
			Session session = Session.getDefaultInstance(props, new SpammerMailAutenticador(this.conta, this.pwd));
			
			// Cria a mensagem
			Message msg = new MimeMessage(session);
			
			if (de == null){
				msg.setFrom(new InternetAddress(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE)));
			} else {
				msg.setFrom(new InternetAddress(de));
			}
			
			if (lstDestinatarios != null) {
				for (EmailRecord destinatario : lstDestinatarios) {
					lstAddress.add(new InternetAddress(destinatario.email, destinatario.nome));
				}
				msg.addRecipients(Message.RecipientType.TO, lstAddress.toArray(new Address[] {}));
			}
			
			if (lstComCopia != null) {
				for (EmailRecord destinatario : lstComCopia) {
					lstAddressCC.add(new InternetAddress(destinatario.email, destinatario.nome));
				}
				msg.addRecipients(Message.RecipientType.CC, lstAddress.toArray(new Address[] {}));
			}
			
			msg.setSubject(assunto);

			if (textoEmail != null) {
				msg.setContent(textoEmail, "text/html");
			}

			msg.setSentDate(new Date());
			Transport.send(msg);
		} catch (IllegalAccessError e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	


	
	
	public void queue(String de, 
			           String destinatario, 
			           String assunto,
			           String textoEmail,
			           String pathQueue) throws AlugueRelaxeException{
		
		StringBuilder sb = new StringBuilder();
		sb.append(de)
		.append(destinatario)
		.append(assunto)
		.append(textoEmail)
		.append(String.valueOf(System.currentTimeMillis()));
		
		BaseFactorySecurity bfs = BaseFactorySecurity.getInstance(BaseFactorySecurity.SHA1);
		String arquivo = "";
		try {
			arquivo = bfs.criptografar(sb.toString());
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalBlockSizeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BadPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Grava o arquivo msg
		try {
			FileOutputStream fos = new FileOutputStream(pathQueue + "/" + arquivo + ".msg");     
			fos.write(textoEmail.getBytes());     
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Grava o arquivo do remetente
		try {
			FileOutputStream fos = new FileOutputStream(pathQueue + "/" + arquivo + ".remetente");     
			fos.write(de.getBytes());     
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
			
		// Grava o arquivo do destinatario
		try {
			FileOutputStream fos = new FileOutputStream(pathQueue + "/" + arquivo + ".destino");     
			fos.write(destinatario.getBytes());     
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Grava o arquivo do assunto
		try {
			FileOutputStream fos = new FileOutputStream(pathQueue + "/" + arquivo + ".assunto");     
			fos.write(assunto.getBytes());     
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}