package br.com.jcv.backend.workers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.contatoanunciante.ContatoAnuncianteServiceImpl;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.filasms.FilaSMSDTO;
import br.com.jcv.backend.filasms.FilaSMSServiceImpl;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.interfaces.services.ContatoAnuncianteService;
import br.com.jcv.backend.interfaces.services.FilaSMSService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class ContatoAnunciantePendenteTotal extends Robot {
	
	public static final String ROBOT_NOME = "CONTATO_ANUNCIANTE_PENDENTE_TOTAL";

	public ContatoAnunciantePendenteTotal() {
		super(ROBOT_NOME, "Contato Anunciantes Pendente de Aprovacao - Fila SMS");
	}
	
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Contato Anunciantes Pendente de Aprovacao - Fila SMS iniciando processo.");		
		try {
			
			//---------------------------------------------------------
			// Obtem os contatos com anunciantes pendentes de aprovacao
			//---------------------------------------------------------
			ContatoAnuncianteService cs = new ContatoAnuncianteServiceImpl();
			List<ContatoClienteDTO> lst = cs.listarContatosAnuncianteStatus(Constantes.IMCA_STATUS_PENDENTE);
			
			//---------------------------------------------------------
			// Enfileira uma mensagem SMS para Administrador se houver
			// resultado
			//---------------------------------------------------------
			if ((lst != null) && (lst.size() > 0)) {
				Map<String,String> parametros = new HashMap<String,String>();
				parametros.put(Constantes.P1, String.valueOf(lst.size()));
				
				// prepara DTO para Fila
				FilaSMSDTO fsmsdto = new FilaSMSDTO();
				fsmsdto.celular = VariavelCache.getInstance().getValor(VariavelConstantes.CELULAR_ADMIN_GATEWAY);
				fsmsdto.msg = MensagemCache.getInstance().getMensagem(MSGCODE.CONTATO_ANUNCIANTE_PENDENTE_APROV,parametros);
				fsmsdto.modo = Constantes.SMS_MODO_ENVIO_GATEWAY; 
				fsmsdto.prioridade = Constantes.SMS_PRIORIDADE_ALTA;
				
				// Insere SMS na fila para envio
				FilaSMSService fsmssrvc = new FilaSMSServiceImpl();
				fsmssrvc.gravarRegistro(fsmsdto);
			}
			
		} catch (AlugueRelaxeException are) {
			// TODO algo

		}
		try {
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Aniversariantes do Dia - Fila SMS processado com sucesso. SMS Enfileirado para " + VariavelCache.getInstance().getValor(VariavelConstantes.CELULAR_ADMIN_GATEWAY));
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
}