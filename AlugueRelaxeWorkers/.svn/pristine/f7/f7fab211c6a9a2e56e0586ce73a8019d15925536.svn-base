package br.com.jcv.backend.workers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.Pop3Cleaner;
import br.com.jcv.backend.eventlog.eventos.robots.EventoRobotDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.utility.StringUtil;

public class EmailsInvalidos extends Robot {
	private EventoRobotDTO erdto = null;
	
	public static final String ROBOT_NOME = "EmailsInvalidos";

	private Logger logger = Logger.getLogger(EmailsInvalidos.class);

	public EmailsInvalidos() {
		super(ROBOT_NOME, "Procurar por emails invalidos na conta pop3");
	}
	
	public void executar() {
		int n = 0;
		this.setStatus("W", "Instância em trabalho");
		
		//------------------------------------------------------------
		// Abre uma conexao com POP3 e itera todas as mensagens
		//------------------------------------------------------------
		Email email = new Pop3Cleaner(-1);
		try {
			email.abrirConexaoPop3("pop.aluguerelaxe.com.br","info@aluguerelaxe.com.br","fork3t56");
			n = email.getMensagemCount();
			if (n > 0){
				for (int i = 0; i < n; i++){
					boolean parsefiltrook = false;
					String assunto = email.getMensagemSubject(i);
					String corpo = null;
					try{
						corpo = email.getMensagemBody(i);
						parsefiltrook = true;
					} catch(UnsupportedEncodingException uee) {
						uee.printStackTrace();
						parsefiltrook = false;
					}
					
					if (parseFiltro(assunto) && (parsefiltrook)) {
						
						// Obter a lista de emails
						Object[] lstEmailsInvalidos = StringUtil.getEmailInText(corpo);
						if ((lstEmailsInvalidos != null) && (lstEmailsInvalidos.length > 0)){
							
							for (int k = 0; k < lstEmailsInvalidos.length; k++){
								String ei = (String) lstEmailsInvalidos[k];
								logger.info("delete from subscriber where email = '"+ei+"'; commit;");
							}
						}
					}
				}
			}
			email.fecharConexaoPop3();
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setStatus("T","Instância encerrada " + String.valueOf(n)+ " emails processado(s)");

	}

	private boolean parseFiltro(String assunto) {
		
		boolean resultado = false;
		//------------------------------------------------------------
		// Filtro de titulos de e-mails. Qualquer titulo fora desta
		// lista sera ignorado.
		//------------------------------------------------------------
		final String[] titulosPossiveis = new String[4];
		titulosPossiveis[0] = "Undelivered Mail Returned to Sender";
		titulosPossiveis[1] = "Mail delivery failed: returning message to sender";
		titulosPossiveis[2] = "Delivery Status Notification (Failure)";
		titulosPossiveis[3] = "failure notice";
		
		final String[] titulosParciais = new String[2];
		titulosParciais[0] = "Rejected:";
		titulosParciais[1] = "Não foi possível enviar:";
		
		// Verifica se assunto é igual aos possiveis titulos
		for (String pt: titulosPossiveis){
			if (pt.equals(assunto)){
				resultado = true;
				break;
			}
		}
		
		// Verifica titulos parciais se nao encontrou titulo possivel
		if ( ! resultado ){
		
		}


		return resultado;
	}

}
