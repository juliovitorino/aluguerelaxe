package br.com.jcv.backend.workers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import br.com.jcv.backend.commons.xml.IXMLReader;
import br.com.jcv.backend.commons.xml.IXMLReaderFactory;
import br.com.jcv.backend.commons.xml.IXMLReaderListener;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.workers.spammer.ConfigSpammerDTO;
import br.com.jcv.backend.workers.spammer.IXMLReaderConfigSpammer;
import br.com.jcv.backend.workers.spammer.MailingDTO;
import br.com.jcv.backend.workers.spammer.MailingRecordDTO;
import br.com.jcv.backend.workers.spammer.PublicadorVO;
import br.com.jcv.backend.workers.spammer.SmtpServerVO;
import br.com.jcv.backend.workers.spammer.email.EmailSpammer;
import br.com.jcv.backend.workers.spammer.templates.TemplateSpammerPublicidadeImovel;

public class Spammer extends Robot {

	public static final String ROBOT_NOME = "Spammer";
	public static final String CHAVE_ARQUIVO = "XMLConfig";

	private Logger logger = Logger.getLogger(Spammer.class);

	public Spammer() {
		super(ROBOT_NOME, "Publicador de emails configuravel");
	}
	
	private String getTrocaConteudo(String msg, Map<String,String> mpTroca) {
		String mensagem = msg;
		Iterator it = mpTroca.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			mensagem = StringUtil.replaceStringNew(mensagem,pairs.getKey().toString(),pairs.getValue().toString());
		}
		return mensagem;
	}

	public void executar() {
	
        // Realiza a leitura do XML de configuracao do spammer
		IXMLReader<ConfigSpammerDTO> xmlr = IXMLReaderFactory.getInstanceConfigSpammer(this.getParametros(CHAVE_ARQUIVO));
		xmlr.setIXMLReaderListener(new IXMLReaderListener() {
			
			public void onFire(String str) {
				Spammer.this.logger.info(str);
				Spammer.this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO, str);
			}
		});
		xmlr.execute();
		ConfigSpammerDTO csdto = xmlr.getDTO();

		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO, csdto.tarefa);

		// Realiza a leitura da lista de mailing baseada no XML de configuracao carregado
		IXMLReader<MailingDTO> xmlmailing = IXMLReaderFactory.getInstanceMailing(csdto.catalogo);
		xmlmailing.setIXMLReaderListener(new IXMLReaderListener() {
			
			public void onFire(String str) {
				Spammer.this.logger.info(str);
			}
		});
		xmlmailing.execute();
		MailingDTO mailing = xmlmailing.getDTO();
		
		String assunto = null;
		
		// Coloca os imoveis de publicacao dentro do mapa
		int idxImovel = 1;
		
		// Reseta os idx controladores de publicadores e smtp servers
		int idxSmtpServer = 0;
		int idxPublicadores = 0;
		int idxImovelPublicar = 0;
		int idxmail = 0;
		
		// Realiza iteração do mailing
		//for (MailingRecordDTO mrdto : mailing.lstMailing) {
		for (int i = 0; i < mailing.lstMailing.size(); i++) {
			
			MailingRecordDTO mrdto = null;
			if (csdto.modoreverso) {
				mrdto = (MailingRecordDTO) mailing.lstMailing.get(mailing.lstMailing.size() - idxmail - 1);
			} else {
				mrdto = (MailingRecordDTO) mailing.lstMailing.get(idxmail);
			}
			
			// Monta mapa de conteudos de troca dentro do HTML
			Map<String, String> conteudo = new HashMap<String,String>();
			ImovelFichaCompletaDTO ifcdto = csdto.listimovel.get(idxImovelPublicar);
			
			ImovelImagemVideoDTO iivdto = ifcdto.imagensImovelTB.get(0);
			ImovelImagemVideoDTO iivdtoxg = ifcdto.imagensImovelXG.get(0);
			conteudo.put("${TSPI_TAG_IFC_ID_FOTO_XG_".concat(String.valueOf(idxImovel)).concat("}"), iivdtoxg.nome );
			conteudo.put("${TSPI_TAG_IFC_ID_FOTO_TB_".concat(String.valueOf(idxImovel)).concat("}"), iivdto.nome );
			conteudo.put("${TSPI_TAG_IFC_NOME_".concat(String.valueOf(idxImovel)).concat("}"), mrdto.nome );
			conteudo.put("${TSPI_TAG_IFC_TITULO_ANUNCIO_".concat(String.valueOf(idxImovel)).concat("}"), ifcdto.imovel.descricaoTituloAnuncio );
			conteudo.put("${TSPI_TAG_IFC_CIDADE_".concat(String.valueOf(idxImovel)).concat("}"), ifcdto.imovel.endereco.cidade );
			conteudo.put("${TSPI_TAG_IFC_UF_".concat(String.valueOf(idxImovel)).concat("}"), ifcdto.imovel.endereco.uf );
			conteudo.put("${TSPI_TAG_IFC_DESCRICAO_".concat(String.valueOf(idxImovel)).concat("}"), ifcdto.imovel.descricaoGeral );
			conteudo.put("${TSPI_TAG_IFC_ID_CLIENTE_".concat(String.valueOf(idxImovel)).concat("}"), String.valueOf(ifcdto.cliente.id));
			conteudo.put("${TSPI_TAG_IFC_ID_IMOVEL_".concat(String.valueOf(idxImovel)).concat("}"), String.valueOf(ifcdto.imovel.id));
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_1}", mrdto.textolivre1);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_2}", mrdto.textolivre2);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_3}", mrdto.textolivre3);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_4}", mrdto.textolivre4);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_5}", mrdto.textolivre5);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_6}", mrdto.textolivre6);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_7}", mrdto.textolivre7);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_8}", mrdto.textolivre8);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_9}", mrdto.textolivre9);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_10}", mrdto.textolivre10);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_11}", mrdto.textolivre11);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_12}", mrdto.textolivre12);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_13}", mrdto.textolivre13);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_14}", mrdto.textolivre14);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_15}", mrdto.textolivre15);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_16}", mrdto.textolivre16);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_17}", mrdto.textolivre17);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_18}", mrdto.textolivre18);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_19}", mrdto.textolivre19);
			conteudo.put("${TSPI_TAG_IFC_TEXTO_LIVRE_20}", mrdto.textolivre20);

			if (ifcdto.imovel.indicadorTipoPropriedade.equals("C")){
				conteudo.put("${TSPI_TAG_IFC_TIPO_IMOVEL_".concat(String.valueOf(idxImovel)).concat("}"), "Casa" );
			} else if (ifcdto.imovel.indicadorTipoPropriedade.equals("A")){
				conteudo.put("${TSPI_TAG_IFC_TIPO_IMOVEL_".concat(String.valueOf(idxImovel)).concat("}"), "Apartamento" );
			} else if (ifcdto.imovel.indicadorTipoPropriedade.equals("H")){
				conteudo.put("${TSPI_TAG_IFC_TIPO_IMOVEL_".concat(String.valueOf(idxImovel)).concat("}"), "Hotel" );
			} else if (ifcdto.imovel.indicadorTipoPropriedade.equals("X")){
				conteudo.put("${TSPI_TAG_IFC_TIPO_IMOVEL_".concat(String.valueOf(idxImovel)).concat("}"), "Chacara" );
			} else if (ifcdto.imovel.indicadorTipoPropriedade.equals("F")){
				conteudo.put("${TSPI_TAG_IFC_TIPO_IMOVEL_".concat(String.valueOf(idxImovel)).concat("}"), "Flat" );
			} else if (ifcdto.imovel.indicadorTipoPropriedade.equals("Z")){
				conteudo.put("${TSPI_TAG_IFC_TIPO_IMOVEL_".concat(String.valueOf(idxImovel)).concat("}"), "Fazenda" );
			} else if (ifcdto.imovel.indicadorTipoPropriedade.equals("S")){
				conteudo.put("${TSPI_TAG_IFC_TIPO_IMOVEL_".concat(String.valueOf(idxImovel)).concat("}"), "Sitio" );
			} else if (ifcdto.imovel.indicadorTipoPropriedade.equals("L")){
				conteudo.put("${TSPI_TAG_IFC_TIPO_IMOVEL_".concat(String.valueOf(idxImovel)).concat("}"), "Chale" );
			} else if (ifcdto.imovel.indicadorTipoPropriedade.equals("P")){
				conteudo.put("${TSPI_TAG_IFC_TIPO_IMOVEL_".concat(String.valueOf(idxImovel)).concat("}"), "Pousada" );
			}
			
			// Talvez tenhamos que converter o valor para texto = FLAT, SITIO
			assunto = this.getTrocaConteudo(csdto.assunto,conteudo);

			// Leitura do template HTML
			Template template = null;
			try {
				template = new TemplateSpammerPublicidadeImovel(csdto.template, conteudo);
			} catch (AlugueRelaxeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			// Configura o publicador que ira enviar
			PublicadorVO publicador = csdto.publicadores.get(idxPublicadores);
		
			// Configura o mail cliente para o smtp server que ira enviar
			SmtpServerVO smtpserver = csdto.listsmtp.get(idxSmtpServer);
			
			EmailSpammer email = new EmailSpammer(new HashMap<String,String>());
			email.setSmtpServer(smtpserver.nome);
			email.setSmtpPort(smtpserver.porta);
			email.setConta(smtpserver.contaAutenticacao);
			try {
				email.setPwd(VariavelCache.getInstance().getValor(smtpserver.aliaspwd));
			} catch (AlugueRelaxeException e1) {
				logger.error("Atributo aliaspwd invalido.");
				e1.printStackTrace();
			}
			
			// Cria o objeto EmailRecord destinatario (Pensar em agrupamento)
			EmailRecord em = new EmailRecord(mrdto.email, mrdto.nome);
			List<EmailRecord> lstem = new ArrayList<EmailRecord>();
			lstem.add(em);
			
			//Verifica se sera enviado diretamente ou sera armazenado em fila
			if (csdto.modoEnvio.equals(IXMLReaderConfigSpammer.QUEUE)){
				try {
					email.queue(publicador.email, mrdto.email, assunto, template.getHtmlTemplate(), csdto.queuePath);
					logger.info("Enfileirando para " + mrdto.email + " enviado.");
				} catch (AlugueRelaxeException e) {
					e.printStackTrace();
				}
				
			}else {
				//Envia o e-mail
				try {
					email.enviar(publicador.email, lstem, null, assunto, template.getHtmlTemplate());
					logger.info("mailing para " + mrdto.email + " enviado.");
				} catch (AlugueRelaxeException e) {
					e.printStackTrace();
				}
			}
			

			this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO, csdto.tarefa + " processando ... " + mrdto.email + "(" + String.valueOf(i) + "/" + String.valueOf(mailing.lstMailing.size()) + ")");
			
			// Atualiza idx controladores
			++idxmail;
			
			// ++idxSmtpServer
			// ++idxPublicadores
			
			if (++idxImovelPublicar == csdto.listimovel.size()){
				idxImovelPublicar = 0;
			}
			
			// Aguarda tempo de espera definido
			long endTime = System.currentTimeMillis() + csdto.tempoespera;

			while ( System.currentTimeMillis() < endTime) {
			}

		}

		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO, csdto.tarefa + " encerrado. " + String.valueOf(mailing.lstMailing.size()) + "enviados.");
	}
}
