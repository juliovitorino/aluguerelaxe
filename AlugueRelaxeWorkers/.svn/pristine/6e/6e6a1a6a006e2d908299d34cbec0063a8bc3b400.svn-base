package br.com.jcv.backend.workers;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import br.com.jcv.backend.alerta24h.AlertaDTO;
import br.com.jcv.backend.chat.ChatDTO;
import br.com.jcv.backend.chat.ChatServiceImpl;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ChatService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;
import br.com.jcv.backend.workers.interfaces.IAlerta24H;

/**
*
* <h2>Alerta24HSenderChat</h2>
* <p>Enviador de alertas para usuarios Alerta 24H</p>
* @author julio
*/
public class Alerta24HSenderChat extends Robot implements IAlerta24H  {

	public static final String ROBOT_NOME = "SENDER_ALERTA_24H_CHAT";


	public Alerta24HSenderChat() {
		super(ROBOT_NOME, "EMISSOR NO CHAT DO ALTERA 24H");
	}
	
	public void executar() {
		// Nada a fazer
	}
	
	public void executar(AlertaDTO dto) {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando Enviador de chat para usuarios Alerta 24H");
		try {
		
			DateManagerBase di = DateManagerBase.getDateManagerInstance();
			//DateManagerBase df = DateManagerBase.getDateManagerInstance();
			
			ChatDTO cdto = new ChatDTO();
			cdto.status = "A";
			cdto.sessao = "DD";
			cdto.idCliente = dto.ifcdto.cliente.id;
			cdto.clientedto = dto.ifcdto.cliente;
			cdto.urlImagem = VariavelCache.getInstance().getValor(VariavelConstantes.URL_IMAGEM_CHAT);
			cdto.titulo = "Alerta 24H";
			cdto.dataInicio = di.getDate();
			
			// prepara variaveis do template
			DateManagerBase dVisita = DateManagerBase.getDateManagerInstance(dto.contato.dataCadastro);
			String telefone = "(" + dto.contato.ddd + ")" + dto.contato.telefone;

			Map<String, String> conteudo = new HashMap<String,String>();
			conteudo.put(Constantes.P1, dto.contato.email);
			conteudo.put(Constantes.P2, telefone);
			conteudo.put(Constantes.P3, dto.contato.proposto.toUpperCase());
			conteudo.put(Constantes.P4, dto.ifcdto.imovel.descricaoTituloAnuncio);
			conteudo.put(Constantes.P5, dto.ifcdto.imovel.endereco.uf);
			conteudo.put(Constantes.P6, dto.ifcdto.imovel.endereco.cidade);
			conteudo.put(Constantes.P7, dto.ifcdto.imovel.descricaoGeral);
			try {
				conteudo.put(Constantes.P8, dVisita.getDateTimeFull());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			cdto.chat = MensagemCache.getInstance().getMensagem(MSGCODE.CHAT_ALERTA_24H, conteudo);
			cdto.dataFinal = dto.contato.chegadaPrevista;
			
			// Obtem uma interface ChatService, prepara um DTO e inclui um aviso no desktop somente para o cliente especifico
			ChatService<ChatDTO> cs = new ChatServiceImpl();
			cs.gravarRegistro(cdto);
			
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Chat para #" + dto.ifcdto.cliente.id + " - " + dto.ifcdto.cliente.nome + " processado com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
