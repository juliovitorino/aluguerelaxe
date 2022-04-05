
package br.com.jcv.backend.email;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public abstract class Email {

	private int templateFactory = -1;
	protected Map<String,String> conteudo = null;
	private Folder inbox = null;
	private Store store = null;
	private Message[] messages = null;
	
	public Email(int templateFactory) {
		this.templateFactory = templateFactory;
	}
	
	public void enviar(List<EmailRecord> lstDestinatarios, 
	           List<EmailRecord> lstComCopia,
	           String assunto,
	           String textoEmail) throws AlugueRelaxeException {
		this.enviar(null, lstDestinatarios, lstComCopia, assunto, textoEmail, null);
	}

	public void fecharConexaoPop3() throws MessagingException{
		  // Fecha a conexao mas nao remove as mensagens do servidor
		  inbox.close(false);
		  store.close();
	}
	
	public void abrirConexaoPop3(String servidor, String usuario, String pwd) throws AlugueRelaxeException {
		
	    try {
			Properties props = new Properties();
			
		  // Conecta ao servidor 
		  Session session = Session.getDefaultInstance(props, null);
		  //Store store = session.getStore("pop3");
		  store = session.getStore("pop3");
		  store.connect(servidor, usuario, pwd);

		  // Open the folder
		  //Folder inbox = store.getFolder("INBOX");
		  inbox = store.getFolder("INBOX");
		  if (inbox == null) {
			  System.out.println("vazio...");
			//throw AlugueRelaxeException.getInstance(MSGCODE.CAIXA_INBOX_VAZIA, 
				//			MensagemCache.getInstance().getMensagem(MSGCODE.CAIXA_INBOX_VAZIA),
					//		null);
		  }
		  inbox.open(Folder.READ_ONLY);

		  // Obtem as mensagens do servidor e armazena na instancia
		  messages = inbox.getMessages();

		}
		catch (Exception ex) {
		  ex.printStackTrace();
		}
		
		//return messages;

	}
	
	public int getMensagemCount(){
		return messages.length;
	}
	
	public String getMensagemSubject(int i) throws MessagingException {
		String retorno = null;
		if (messages.length > 0){
			retorno = messages[i].getSubject();
		}	
		return retorno;
	}
	
	public String getMensagemBody(int i) throws MessagingException, IOException {
		String retorno = null;
		if (messages.length > 0){
			Object content = messages[i].getContent();  
			if (content instanceof String)  
			{  
			    retorno = (String)content;  
			}  
			else if (content instanceof Multipart)  
			{  
				retorno = parseMultipart( (Multipart) content );					
			}  		
		}	
		return retorno;
	}
	
	// Parse the Multipart to find the body
	private String parseMultipart( Multipart mPart ) throws MessagingException, IOException {
		
		String body = null;
		
		  // Loop through all of the BodyPart's
		  for ( int i = 0; i < mPart.getCount(); i++ ) {
		    // Grab the body part
		    BodyPart bp = mPart.getBodyPart( i );
		    // Grab the disposition for attachments
		    //String disposition = mPart.getDisposition();
	
		    // It's not an attachment
	//	    if ( disposition == null && bp instanceof MimeBodyPart ){
		    if ( bp instanceof MimeBodyPart ){
		      MimeBodyPart mbp = (MimeBodyPart) bp;
	
		      // Time to grab and edit the body
		      if ( mbp.isMimeType( "text/plain" )) {
		        // Grab the body containing the text version
		        body = (String) mbp.getContent();
		        // Add our custom message
		        //body += stripTags( mesgStr );
	
		        // Reset the content
		        mbp.setContent( body, "text/plain" );
		      } else if ( mbp.isMimeType( "text/html" )) {
		        // Grab the body containing the HTML version
		        body = (String) mbp.getContent();
		        // Add our custom message to the HTML before
		        // the closing </body>
		        //body = addStrToHtmlBody( mesgStr, body );
	
		        // Reset the content
		        mbp.setContent( body, "text/html" );
		      }
		    }
		  }
		  
		  return body;
	}	
	
	public void enviar(String de, 
			           List<EmailRecord> lstDestinatarios, 
			           List<EmailRecord> lstComCopia,
			           String assunto,
			           String textoEmail,
					   ServidorSMTP smtp) throws AlugueRelaxeException{

		try {
			List<Address> lstAddress = new ArrayList<Address>();
			List<Address> lstAddressCC = new ArrayList<Address>();
			
			Properties props = new Properties();
			
			// Configurações
			if (smtp == null){
				props.put("mail.smtp.host", VariavelCache.getInstance().getValor(VariavelConstantes.SMTP_SERVER));
				props.put("mail.smtp.port", VariavelCache.getInstance().getValor(VariavelConstantes.SMTP_PORT));
				props.put("mail.smtp.auth", "true"); 
			} else {
				props.put("mail.smtp.host", smtp.servidor);
				props.put("mail.smtp.port", String.valueOf(smtp.porta));
				props.put("mail.smtp.auth", "true"); 
			}
			
			
			Session session = Session.getDefaultInstance(props, new AlugueRelaxeMailAutenticador());
			
			// Cria a mensagem
			Message msg = new MimeMessage(session);
			
			if (de == null){
				msg.setFrom(new InternetAddress(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE)));
			}
			
			if (VariavelCache.getInstance().getValor(VariavelConstantes.PERMITIR_ENVIO_EMAILS_CONTA_TESTE).equals("ON")) {
				lstAddress.add(new InternetAddress(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_CONTA_TESTE), 
													VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_CONTA_TESTE_NOME)));
				msg.addRecipients(Message.RecipientType.TO, lstAddress.toArray(new Address[] {}));
			}else {
				if (lstDestinatarios != null) {
					for (EmailRecord destinatario : lstDestinatarios) {
						lstAddress.add(new InternetAddress(destinatario.email, destinatario.nome));
					}
					msg.addRecipients(Message.RecipientType.TO, lstAddress.toArray(new Address[] {}));
				}
			}
			
			if (lstComCopia != null) {
				for (EmailRecord destinatario : lstComCopia) {
					lstAddressCC.add(new InternetAddress(destinatario.email, destinatario.nome));
				}
				msg.addRecipients(Message.RecipientType.CC, lstAddress.toArray(new Address[] {}));
			}
			
			msg.setSubject(assunto);

			if (textoEmail == null) {
				if (templateFactory > 0) {
					Template template = TemplateFactory.getInstance(templateFactory,conteudo);
					msg.setContent(template.getHtmlTemplate(), "text/html");
				} else {
					throw AlugueRelaxeException.getInstance(MSGCODE.TEMPLATE_FACTORY_NAO_DEFINIDA, 
							MensagemCache.getInstance().getMensagem(MSGCODE.TEMPLATE_FACTORY_NAO_DEFINIDA),
							null);
				}
			} else {
				msg.setText(textoEmail);
			}
			
			msg.setSentDate(new Date());
			
			// Somente permite envio de emails se configuracao ativa = ON
			if (VariavelCache.getInstance().getValor(VariavelConstantes.PERMITIR_ENVIO_EMAILS).equals("ON")){
				Transport.send(msg);
			}
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

	
}

