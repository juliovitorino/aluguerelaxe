package br.com.jcv.backend.workers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.alerta24h.AlertaDTO;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.email.ServidorSMTP;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;
import br.com.jcv.backend.workers.interfaces.IAlerta24H;

/**
*
* <h2>Alerta24HSenderEmail</h2>
* <p>Enviador de alertas para usuarios Alerta 24H</p>
* @author julio
*/
public class Alerta24HSenderEmail extends Robot implements IAlerta24H  {

	public static final String ROBOT_NOME = "SENDER_ALERTA_24H_EMAIL";


	public Alerta24HSenderEmail() {
		super(ROBOT_NOME, "EMISSOR NO EMAIL DO ALTERA 24H");
	}
	
	public void executar() {
		// Nada a fazer
	}
	
	public void executar(AlertaDTO dto) {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Enviando Email");
		try {
			DateManagerBase dVisita = DateManagerBase.getDateManagerInstance(dto.contato.dataCadastro);
			String telefone = "(" + dto.contato.ddd + ")" + dto.contato.telefone;
			
			// Obtem a ficha do imovel visitado
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(dto.contato.idImovel);
			
			// envia o e-mail para o anunciante
			Map<String, String> conteudo = new HashMap<String,String>();
			conteudo.put(TemplateConstantes.TAGC_EMAIL_VISITANTE, dto.contato.email);
			conteudo.put(TemplateConstantes.TAGC_TELEFONE_VISITANTE, telefone);
			conteudo.put(TemplateConstantes.TAGC_NOME_VISITANTE, dto.contato.proposto.toUpperCase());
			conteudo.put(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO, ifcdto.imovel.descricaoTituloAnuncio);
			conteudo.put(TemplateConstantes.TAGC_TITULO_IMOVEL_ALERTA24H, dto.ifcdto.imovel.descricaoTituloAnuncio);
			conteudo.put(TemplateConstantes.TAGC_UF, dto.ifcdto.imovel.endereco.uf);
			conteudo.put(TemplateConstantes.TAGC_CIDADE, dto.ifcdto.imovel.endereco.cidade);
			conteudo.put(TemplateConstantes.TAGC_NOME_ANUNCIANTE, dto.ifcdto.cliente.nome.toUpperCase());
			conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcdto.imovel.descricaoGeral);
			try {
				conteudo.put(TemplateConstantes.TAGC_DATA_VISITA, dVisita.getDateTimeFull());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Prepara e-mail
			List<EmailRecord> lstDestinatarios = new ArrayList<EmailRecord>();
			lstDestinatarios.add(new EmailRecord(dto.ifcdto.cliente.email, dto.ifcdto.cliente.nome));
			
			//teste descomentar a linha de cima quando teste estiver OK e apagar esta linha de baixo.
			//lstDestinatarios.add(new EmailRecord("julio.vitorino@ig.com.br", "julio vitorino"));
			
			Email email = EmailFactory.getInstanceEmailAlerta24h(conteudo);
			
			// Todos os robots ao enviar email precisam reconfigurar a porta SMTP
			ServidorSMTP smtp = new ServidorSMTP(VariavelCache.getInstance().getValor(VariavelConstantes.SMTP_SERVER),
													Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.SMTP_PORT_EXTRANET)));
			email.enviar(null,lstDestinatarios, null, "AlugueRelaxe Alerta24H: #" + dto.id + " Aluguel de Temporada - Contato: " + telefone,null,smtp);
			
			// envia um e-mail para o visitante sobre nossa pro-atividade sobre o outro imovel na mesma cidade x uf
			// --> vou pensar ??!?!?!

			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Envio de email para " + dto.ifcdto.cliente.nome + "(" + dto.ifcdto.cliente.email + ") processado com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
