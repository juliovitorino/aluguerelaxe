package br.com.jcv.backend.imovel.thread;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import br.com.jcv.backend.chat.ChatBusinessImpl;
import br.com.jcv.backend.chat.ChatDTO;
import br.com.jcv.backend.cliente.ClienteBusinessImpl;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.contatoanunciante.ContatoAnuncianteBusinessImpl;
import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.filasms.FilaSMSBusinessImpl;
import br.com.jcv.backend.filasms.FilaSMSDTO;
import br.com.jcv.backend.filasms.FilaSMSFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.interfaces.business.ChatBusiness;
import br.com.jcv.backend.interfaces.business.ClienteBusiness;
import br.com.jcv.backend.interfaces.business.ContatoAnuncianteBusiness;
import br.com.jcv.backend.interfaces.business.ContatoAnuncianteThreadBusiness;
import br.com.jcv.backend.interfaces.business.FilaSMSBusiness;
import br.com.jcv.backend.interfacesdao.ContatoAnuncianteDAO;
import br.com.jcv.backend.interfacesdao.ContatoAnuncianteThreadDAO;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.reserva.AvaliacaoReservaDTO;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class ContatoAnuncianteThreadBusinessImpl implements ContatoAnuncianteThreadBusiness {

	public void atualizarFotoThread(DAOFactory daoFactory, String hashParent, String foto) throws AlugueRelaxeException {

		// Obtem uma implementacao DAO
		ContatoAnuncianteDAO dao = daoFactory.getContatoAnuncianteDAO(daoFactory); 
		dao.updateFotoThread(hashParent,foto);
	}


	public void aprovarThread(DAOFactory daoFactory, ContatoClienteDTO ccdto, 
						ContatoAnuncianteThreadDTO catdto, ImovelFichaCompletaDTO ifcdto, boolean editada, String modo) 
							throws AlugueRelaxeException {
		if (modo.equals(Constantes.PERGUNTA_RESPOSTA)){
			aprovarThreadPerguntaResposta(daoFactory, ccdto, catdto, ifcdto, editada);
		} else if (modo.equals(Constantes.COMENTARIO)){
			aprovarThreadComentario(daoFactory, ccdto, catdto, ifcdto, editada);
		}
	
	}
	
	
	
	private void aprovarThreadComentario(DAOFactory daoFactory, ContatoClienteDTO ccdto, 
						ContatoAnuncianteThreadDTO catdto, ImovelFichaCompletaDTO ifcdto, boolean editada) 
							throws AlugueRelaxeException {
	
		// Obtem uma implementacao DAO
		ContatoAnuncianteThreadDAO dao = daoFactory.getContatoAnuncianteThreadDAO(daoFactory); 

		//-----------------------------------------------------------------------
		// Criterios para atualizacao da Thread do status PENDENTE para APROVADO:
		// - Somente e possivel aprovar uma thread com status "P"
		//-----------------------------------------------------------------------
	
		if (catdto.status.equals(Constantes.PENDENTE)) {
			// aprova a thread
			dao.updateStatus(catdto.id, "A");

			//-------------------------------------------------------------------------
			// Envia e-mail seguindo os seguintes criterios
			// a) Se a thread tem origem VISITANTE, então envia email para o anunciante
			// b) Se a thread tem origem ANUNCIANTE, então envia email para visitante
			//-------------------------------------------------------------------------
			// Se a thread foi editada eh porque ou o visitante escreveu conteudo indevido 
			// ou o anunciante escreveu conteudo indevido, entao enviaremos e-mail para
			// o originador da INFRACAO avisando que o conteudo de sua mensagem foi editado
			//------------------------------------------------------------------------------

//			if (editada){
				if (catdto.origem.equals(Constantes.ORIGEM_VISITANTE)) {
					// Manda email para ANUNCIANTE sobre duvida do visitante
					this.enviarEmailAnuncianteThreadComentario(ccdto, ifcdto, catdto); 
					
				} else if (catdto.origem.equals(Constantes.ORIGEM_ANUNCIANTE)){
					// Manda email para VISITANTE sobre resposta do anunciante
					this.enviarEmailVisitanteThreadRespostaComentario(ccdto, ifcdto, catdto);
				
				}
				// Manda email com thread original e thread editada para originador
				//this.enviarEmailThreadEditada(ccdto, ifcdto, catdto);
/*
			} else {
				if (catdto.origem.equals(Constantes.ORIGEM_VISITANTE)) {
					// Manda email para ANUNCIANTE sobre duvida do visitante
					this.enviarEmailAnuncianteThreadComentario(ccdto, ifcdto, catdto);
					
				} else if (catdto.origem.equals(Constantes.ORIGEM_ANUNCIANTE)){
					// Manda email para VISITANTE sobre resposta do anunciante
					this.enviarEmailVisitanteThreadRespostaComentario(ccdto, ifcdto, catdto);
				
				}
			}
*/			
			//-------------------------------------------------------------------------
			// Grava SMS na fila seguindo os seguintes criterios
			// a) Se a thread tem origem VISITANTE, então envia email para o anunciante
			// b) Se a thread tem origem ANUNCIANTE, então envia email para visitante
			//-------------------------------------------------------------------------
			if (catdto.origem.equals(Constantes.ORIGEM_VISITANTE)) {
				// Manda SMS para ANUNCIANTE
				Map<String,String> param = new HashMap<String,String>();
				param.put(Constantes.P1,ccdto.primeiroNome);
				param.put(Constantes.P2,ifcdto.imovel.endereco.cidade.toUpperCase());
				param.put(Constantes.P3,ifcdto.imovel.endereco.uf.toUpperCase());
				List<FilaSMSDTO>  lst = FilaSMSFactory.getDTOCliente(ifcdto.cliente.id, 
							MensagemCache.getInstance().getMensagem(MSGCODE.SMS_COMENTARIO_THREAD, param));
				FilaSMSFactory.enviarSMS(lst);
			}
			
			//-------------------------------------------------------------------------
			// Grava CHAT para anunciante seguindo os seguintes criterios
			// a) Se a thread tem origem VISITANTE, então grava chat para ANUNCIANTE
			//-------------------------------------------------------------------------
/*
			if (catdto.origem.equals(Constantes.ORIGEM_VISITANTE)) {

				// Grava um Chat
				Map<String,String> param = new HashMap<String,String>();
				param.put(Constantes.P1,ccdto.proposto.toUpperCase());
				param.put(Constantes.P2,ifcdto.imovel.endereco.cidade);
				param.put(Constantes.P3,ifcdto.imovel.endereco.uf);
				param.put(Constantes.P4,ifcdto.imovel.descricaoTituloAnuncio);
				param.put(Constantes.P5,ifcdto.cliente.email);			
				
				// Grava Chat para ANUNCIANTE
				DateManagerBase hoje = DateManagerBase.getDateManagerInstance();
				DateManagerBase di = DateManagerBase.getDateManagerInstance(ccdto.chegadaPrevista);
				ChatDTO chatdto = new ChatDTO();
				chatdto.status = Constantes.ATIVO;
				chatdto.sessao = "DD";
				chatdto.chat = MensagemCache.getInstance().getMensagem(MSGCODE.CHAT_COMENTARIO_THREAD, param);
				chatdto.dataInicio = hoje.getDate();
				chatdto.dataFinal = di.getDate();
				chatdto.idCliente = ifcdto.cliente.id;
				chatdto.urlImagem = VariavelCache.getInstance().getValor(VariavelConstantes.URL_IMAGEM_CHAT);
				chatdto.titulo = "TESTEMUNHO SOBRE TEMPORADA EM SEU IMÓVEL DE  " + ccdto.proposto.toUpperCase();
				
				gravarChatThread(daoFactory, ccdto, chatdto);
			}		
*/				
			
		} else {
			//---------------------------------------------------------------------
			// Se a thread ja foi aprovada emite erro
			//---------------------------------------------------------------------

		}
	}

	
	
	
	private void aprovarThreadPerguntaResposta(DAOFactory daoFactory, ContatoClienteDTO ccdto, 
						ContatoAnuncianteThreadDTO catdto, ImovelFichaCompletaDTO ifcdto, boolean editada) 
							throws AlugueRelaxeException {
	
		// Obtem uma implementacao DAO
		ContatoAnuncianteThreadDAO dao = daoFactory.getContatoAnuncianteThreadDAO(daoFactory); 

		//-----------------------------------------------------------------------
		// Criterios para atualizacao da Thread do status PENDENTE para APROVADO:
		// - Somente e possivel aprovar uma thread com status "P"
		//-----------------------------------------------------------------------
	
		if (catdto.status.equals(Constantes.PENDENTE)) {
			// aprova a thread
			dao.updateStatus(catdto.id, "A");

			//-------------------------------------------------------------------------
			// Envia e-mail seguindo os seguintes criterios
			// a) Se a thread tem origem VISITANTE, então envia email para o anunciante
			// b) Se a thread tem origem ANUNCIANTE, então envia email para visitante
			//-------------------------------------------------------------------------
			// Se a thread foi editada eh porque ou o visitante escreveu conteudo indevido 
			// ou o anunciante escreveu conteudo indevido, entao enviaremos e-mail para
			// o originador da INFRACAO avisando que o conteudo de sua mensagem foi editado
			//------------------------------------------------------------------------------
			if (editada){
				if (catdto.origem.equals(Constantes.ORIGEM_VISITANTE)) {
					// Manda email para ANUNCIANTE sobre duvida do visitante
					this.enviarEmailAnuncianteThread(ccdto, ifcdto, catdto);
					
				} else if (catdto.origem.equals(Constantes.ORIGEM_ANUNCIANTE)){
					// Manda email para VISITANTE sobre resposta do anunciante
					this.enviarEmailVisitanteThreadResposta(ccdto, ifcdto, catdto);
				
				}
				// Manda email com thread original e thread editada para originador
				this.enviarEmailThreadEditada(ccdto, ifcdto, catdto);

			} else {
				if (catdto.origem.equals(Constantes.ORIGEM_VISITANTE)) {
					// Manda email para ANUNCIANTE sobre duvida do visitante
					this.enviarEmailAnuncianteThread(ccdto, ifcdto, catdto);
					
				} else if (catdto.origem.equals(Constantes.ORIGEM_ANUNCIANTE)){
					// Manda email para VISITANTE sobre resposta do anunciante
					this.enviarEmailVisitanteThreadResposta(ccdto, ifcdto, catdto);
				
				}
			}
			
			//-------------------------------------------------------------------------
			// Grava SMS na fila seguindo os seguintes criterios
			// a) Se a thread tem origem VISITANTE, então envia email para o anunciante
			// b) Se a thread tem origem ANUNCIANTE, então envia email para visitante
			//-------------------------------------------------------------------------
			if (catdto.origem.equals(Constantes.ORIGEM_VISITANTE)) {
				Map<String,String> param = new HashMap<String,String>();
				param.put(Constantes.P1,ccdto.primeiroNome.toUpperCase());
				param.put(Constantes.P2,ifcdto.imovel.endereco.cidade);
				param.put(Constantes.P3,ifcdto.imovel.endereco.uf);
				param.put(Constantes.P5,ifcdto.cliente.email);
				
				List<FilaSMSDTO> lst = FilaSMSFactory.getDTOCliente(ifcdto.cliente.id, MensagemCache.getInstance().getMensagem(MSGCODE.SMS_DUVIDA_THREAD, param));
				FilaSMSFactory.enviarSMS(lst);
			} else if (catdto.origem.equals(Constantes.ORIGEM_ANUNCIANTE)){
				// Manda SMS para VISITANTE
				// Grava um Chat
				Map<String,String> param = new HashMap<String,String>();
				param.put(Constantes.P1,ifcdto.imovel.endereco.cidade);
				param.put(Constantes.P2,ifcdto.imovel.endereco.uf);
				param.put(Constantes.P3,ccdto.email);

				FilaSMSDTO smsdto = new FilaSMSDTO();
				smsdto.celular = ccdto.ddd + ccdto.telefone;
				smsdto.msg = MensagemCache.getInstance().getMensagem(MSGCODE.SMS_DUVIDA_THREAD_RESPONDIDA, param);
				smsdto.modo = Constantes.SMS_MODO_ENVIO_GATEWAY;
				this.gravarSMSFila(daoFactory, smsdto);
			}
			
			//-------------------------------------------------------------------------
			// Grava CHAT para anunciante seguindo os seguintes criterios
			// a) Se a thread tem origem VISITANTE, então grava chat para ANUNCIANTE
			//-------------------------------------------------------------------------
			if (catdto.origem.equals(Constantes.ORIGEM_VISITANTE)) {

				// Grava um Chat
				Map<String,String> param = new HashMap<String,String>();
				param.put(Constantes.P1,ccdto.proposto.toUpperCase());
				param.put(Constantes.P2,ifcdto.imovel.endereco.cidade);
				param.put(Constantes.P3,ifcdto.imovel.endereco.uf);
				param.put(Constantes.P4,ifcdto.imovel.descricaoTituloAnuncio);
				param.put(Constantes.P5,ifcdto.cliente.email);			
				
				// Grava Chat para ANUNCIANTE
				DateManagerBase hoje = DateManagerBase.getDateManagerInstance();
				DateManagerBase di = DateManagerBase.getDateManagerInstance(ccdto.chegadaPrevista);
				ChatDTO chatdto = new ChatDTO();
				chatdto.status = Constantes.ATIVO;
				chatdto.sessao = "DD";
				chatdto.chat = MensagemCache.getInstance().getMensagem(MSGCODE.DUVIDA_THREAD, param);
				chatdto.dataInicio = hoje.getDate();
				chatdto.dataFinal = di.getDate();
				chatdto.idCliente = ifcdto.cliente.id;
				chatdto.urlImagem = VariavelCache.getInstance().getValor(VariavelConstantes.URL_IMAGEM_CHAT);
				chatdto.titulo = "CONTATO PRA VOCÊ VIA GERENCIADOR MENSAGEM DE " + ccdto.proposto.toUpperCase();
				
				gravarChatThread(daoFactory, ccdto, chatdto);
			}			
			
			//-------------------------------------------------------------------------
			// Atualiza contador na tabela CLIENTE seguindo os seguintes criterios
			// a) Se a thread tem origem VISITANTE, incrementa contador PERGUNTAS
			// b) Se a thread tem origem ANUNCIANTE, incrementa contador RESPOSTAS
			//-------------------------------------------------------------------------
			if (catdto.origem.equals(Constantes.ORIGEM_VISITANTE)) {
				// Incrementa contador de perguntas
				ClienteBusiness<ClienteDTO> cb = new ClienteBusinessImpl();
				cb.incrementarPerguntas(daoFactory, ifcdto.cliente.id);
			} else if (catdto.origem.equals(Constantes.ORIGEM_ANUNCIANTE)) {
				// Incrementa contador de respostas
				ClienteBusiness<ClienteDTO> cb = new ClienteBusinessImpl();
				cb.incrementarRespostas(daoFactory, ifcdto.cliente.id);
			}
		} else {
			//---------------------------------------------------------------------
			// Se a thread ja foi aprovada emite erro
			//---------------------------------------------------------------------

		}
	}

	public ContatoAnuncianteThreadDTO incluir(DAOFactory daofactory, ContatoClienteDTO dto, ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException {
	
		// Colocar o validador aqui 
		//...
		
		// Obtem uma implementacao DAO
		ContatoAnuncianteThreadDAO dao = daofactory.getContatoAnuncianteThreadDAO(daofactory);

		// Monta o DTO de 1a. thread
		ContatoAnuncianteThreadDTO dtoEnvio = new ContatoAnuncianteThreadDTO();
		dtoEnvio.id = daofactory.getNextSequence(daofactory, "SEQ_IMCT_ID");
		dtoEnvio.idParent = -1;
		dtoEnvio.origem = Constantes.ORIGEM_VISITANTE;
		dtoEnvio.status = Constantes.ATIVO;
		dtoEnvio.modo = Constantes.PERGUNTA_RESPOSTA;
		dtoEnvio.thread = dto.informacoesAdicionais;
		dtoEnvio.threadEditada = dtoEnvio.thread;
		this.calcularHash(dtoEnvio);
		
		// Insere a nova Thread do visitante para inicio de negociacao
		ContatoAnuncianteThreadDTO dtoretorno = dao.insert(dto, dtoEnvio);
		
		// Grava um Chat
		Map<String,String> param = new HashMap<String,String>();
		param.put(Constantes.P1,dto.proposto.toUpperCase());
		param.put(Constantes.P2,ifcdto.imovel.endereco.cidade);
		param.put(Constantes.P3,ifcdto.imovel.endereco.uf);
		param.put(Constantes.P4,ifcdto.imovel.descricaoTituloAnuncio);
		param.put(Constantes.P5,ifcdto.cliente.email);

		DateManagerBase hoje = DateManagerBase.getDateManagerInstance();
		DateManagerBase di = DateManagerBase.getDateManagerInstance(dto.chegadaPrevista);
		ChatDTO chatdto = new ChatDTO();
		chatdto.status = Constantes.ATIVO;
		chatdto.sessao = "DD";
		chatdto.chat = MensagemCache.getInstance().getMensagem(MSGCODE.DUVIDA_THREAD, param);
		chatdto.dataInicio = hoje.getDate();
		chatdto.dataFinal = di.getDate();
		chatdto.idCliente = ifcdto.cliente.id;
		chatdto.urlImagem = VariavelCache.getInstance().getValor(VariavelConstantes.URL_IMAGEM_CHAT);
		chatdto.titulo = MensagemCache.getInstance().getMensagem(MSGCODE.TITULO_CHAT_DUVIDA_THREAD, param);
				
		this.gravarChatThread(daofactory, dto, chatdto);
		
		// Grava um SMS na fila para envio ao anunciante
		List<FilaSMSDTO> smsdto = FilaSMSFactory.getDTOCliente(ifcdto.cliente.id,
																MensagemCache.getInstance().getMensagem(MSGCODE.SMS_DUVIDA_THREAD, param),
																daofactory);
		FilaSMSFactory.enviarSMS(smsdto,daofactory);
		
		/* APAGAR
		FilaSMSDTO smsdto = new FilaSMSDTO();
		smsdto.celular = dto.ddd + dto.telefone;
		smsdto.msg = MensagemCache.getInstance().getMensagem(MSGCODE.SMS_DUVIDA_THREAD, param);
		smsdto.modo = Constantes.SMS_MODO_ENVIO_GATEWAY;
		this.gravarSMSFila(daofactory, smsdto);
		*/
		
		// Envia email ao visitante sobre a thread iniciada com link
		this.enviarEmailVisitanteThreadIniciada(dto, ifcdto, dtoEnvio);

		// Envia Email ao Anunciante para que ele entre no console e responda a thread
		dtoEnvio.origem = "A";
		this.enviarEmailAnuncianteThread(dto, ifcdto, dtoEnvio);

		return dtoretorno;
	}
	
	private void enviarEmailVisitanteThreadIniciada(ContatoClienteDTO dto, 
			ImovelFichaCompletaDTO ifcdto, 
			ContatoAnuncianteThreadDTO dtoThread) throws AlugueRelaxeException {
		Map<String,String> conteudo = new HashMap<String,String>();
		
		DateManagerBase dv = DateManagerBase.getDateManagerInstance(dto.dataCadastro);
		String link = VariavelCache.getInstance().getValor(VariavelConstantes.URL_THREADS);
		link = StringUtil.replaceStringNew(link, "${HASH}", dto.id);
		link = StringUtil.replaceStringNew(link, "${OMC}", dto.codigoOMCThreadVisitante);
		
		conteudo.put(TemplateConstantes.TAGC_NOME_ANUNCIANTE, ifcdto.cliente.nome.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_NOME_VISITANTE, dto.proposto);
		conteudo.put(TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
		conteudo.put(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcdto.imovel.descricaoGeral);
		conteudo.put(TemplateConstantes.TAGC_THREAD, dtoThread.thread);
		conteudo.put(TemplateConstantes.TAGC_LINK, link);

		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VISITA, dv.getDateTimeFull() );
			conteudo.put(TemplateConstantes.TAGC_DATA_DUVIDA,  dv.getDateTimeFull());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Email email = EmailFactory.getInstanceEmailThreadCriada(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(dto.email, dto.proposto));
		
		String titulo = dto.proposto + ", sua duvida p/ imovel #" + 
		ifcdto.imovel.id + " em " + 
		ifcdto.imovel.endereco.cidade + "/" + ifcdto.imovel.endereco.uf + " foi enviada.";
		email.enviar(lst, null, titulo, null);
		
	}

	private void enviarEmailAnuncianteThread(ContatoClienteDTO dto, ImovelFichaCompletaDTO ifcdto, ContatoAnuncianteThreadDTO dtoThread) throws AlugueRelaxeException {
		Map<String,String> conteudo = new HashMap<String,String>();
		
		DateManagerBase dv = DateManagerBase.getDateManagerInstance(dto.dataCadastro);
		String link = VariavelCache.getInstance().getValor(VariavelConstantes.URL_THREADS);
		link = StringUtil.replaceStringNew(link, "${HASH}", dto.id);
		link = StringUtil.replaceStringNew(link, "${OMC}", dto.codigoOMCThreadAnunciante);
		
		conteudo.put(TemplateConstantes.TAGC_NOME_ANUNCIANTE, ifcdto.cliente.nome.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_NOME_VISITANTE, dto.proposto);
		conteudo.put(TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
		conteudo.put(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcdto.imovel.descricaoGeral);
		conteudo.put(TemplateConstantes.TAGC_THREAD, dtoThread.thread);
		conteudo.put(TemplateConstantes.TAGC_LINK, link);

		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VISITA, dv.getDateTimeFull() );
			conteudo.put(TemplateConstantes.TAGC_DATA_DUVIDA,  dv.getDateTimeFull());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Email email = EmailFactory.getInstanceEmailThreadAnunciante(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(ifcdto.cliente.email, ifcdto.cliente.nome));
		
		String titulo = "Thread#" + dtoThread.id +" - "+ ifcdto.cliente.nome + ", existem perguntas de clientes p/ seu imovel #" + 
		ifcdto.imovel.id + " em " + 
		ifcdto.imovel.endereco.cidade + "/" + ifcdto.imovel.endereco.uf;
		email.enviar(lst, null, titulo, null);

	}


	private void enviarEmailAnuncianteThreadComentario(ContatoClienteDTO dto, ImovelFichaCompletaDTO ifcdto, ContatoAnuncianteThreadDTO dtoThread) throws AlugueRelaxeException {
		Map<String,String> conteudo = new HashMap<String,String>();  
		
		String linkAval = VariavelCache.getInstance().getValor(
				VariavelConstantes.LINK_AVALIACAO_RESERVA);
		linkAval = StringUtil.replaceStringNew(linkAval,
				"${hash}", dto.id);
		linkAval = StringUtil.replaceStringNew(linkAval,
				"${omc}", dto.codigoOMCThreadAnunciante);
		
		DateManagerBase dv = DateManagerBase.getDateManagerInstance(dto.dataCadastro);
		String link = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_OFERECIMENTO);
		link = StringUtil.replaceStringNew(link, "${id}", String.valueOf(ifcdto.imovel.id));
		
		conteudo.put(TemplateConstantes.TAGC_LINK_ACOMPANHAMENTO_PORTAL,linkAval);
		conteudo.put(TemplateConstantes.TAGC_NOME_ANUNCIANTE, ifcdto.cliente.nome.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_NOME_VISITANTE, dto.proposto);
		conteudo.put(TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
		conteudo.put(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcdto.imovel.descricaoGeral);
		conteudo.put(TemplateConstantes.TAGC_THREAD, dtoThread.thread);
		conteudo.put(TemplateConstantes.TAGC_LINK, link);

		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VISITA, dv.getDateTimeFull() );
			conteudo.put(TemplateConstantes.TAGC_DATA_DUVIDA,  dv.getDateTimeFull());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Email email = EmailFactory.getInstanceEmailThreadComentarioAnunciante(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(ifcdto.cliente.email, ifcdto.cliente.nome));
		
		String titulo = "Thread#" + dtoThread.id +" - testemunho de " + dto.primeiroNome + " sobre temporada em " + 
		ifcdto.imovel.endereco.cidade + "/" + ifcdto.imovel.endereco.uf;
		email.enviar(lst, null, titulo, null);

	}


	private void enviarEmailAnuncianteThreadResposta(ContatoClienteDTO dto, ImovelFichaCompletaDTO ifcdto, ContatoAnuncianteThreadDTO dtoThread) throws AlugueRelaxeException {
		Map<String,String> conteudo = new HashMap<String,String>();
		
		DateManagerBase dv = DateManagerBase.getDateManagerInstance(dto.dataCadastro);
		String link = VariavelCache.getInstance().getValor(VariavelConstantes.URL_THREADS);
		link = StringUtil.replaceStringNew(link, "${HASH}", dto.id);
		link = StringUtil.replaceStringNew(link, "${OMC}", dto.codigoOMCThreadAnunciante);
		
		conteudo.put(TemplateConstantes.TAGC_NOME_ANUNCIANTE, ifcdto.cliente.nome.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_NOME_VISITANTE, dto.proposto);
		conteudo.put(TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
		conteudo.put(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcdto.imovel.descricaoGeral);
		conteudo.put(TemplateConstantes.TAGC_THREAD, dtoThread.thread);
		conteudo.put(TemplateConstantes.TAGC_LINK, link);

		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VISITA, dv.getDateTimeFull() );
			conteudo.put(TemplateConstantes.TAGC_DATA_DUVIDA,  dv.getDateTimeFull());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Email email = EmailFactory.getInstanceEmailThreadAnuncianteResposta(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(ifcdto.cliente.email, ifcdto.cliente.nome));
		
		String titulo = ifcdto.cliente.nome + ", existem perguntas de clientes p/ seu imovel #" + 
		ifcdto.imovel.id + " em " + 
		ifcdto.imovel.endereco.cidade + "/" + ifcdto.imovel.endereco.uf;
		email.enviar(lst, null, titulo, null);

	}
	
	
	private void enviarEmailVisitanteThread(ContatoClienteDTO dto, ImovelFichaCompletaDTO ifcdto, ContatoAnuncianteThreadDTO dtoThread) throws AlugueRelaxeException {
		Map<String,String> conteudo = new HashMap<String,String>();
		
		DateManagerBase dv = DateManagerBase.getDateManagerInstance(dto.dataCadastro);
		String link = VariavelCache.getInstance().getValor(VariavelConstantes.URL_THREADS);
		link = StringUtil.replaceStringNew(link, "${HASH}", dto.id);
		link = StringUtil.replaceStringNew(link, "${OMC}", dto.codigoOMCThreadVisitante);
		
		conteudo.put(TemplateConstantes.TAGC_NOME_ANUNCIANTE, ifcdto.cliente.nome.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_NOME_VISITANTE, dto.proposto);
		conteudo.put(TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
		conteudo.put(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcdto.imovel.descricaoGeral);
		conteudo.put(TemplateConstantes.TAGC_THREAD, dtoThread.thread);
		conteudo.put(TemplateConstantes.TAGC_LINK, link);

		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VISITA, dv.getDateTimeFull() );
			conteudo.put(TemplateConstantes.TAGC_DATA_DUVIDA,  dv.getDateTimeFull());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Email email = EmailFactory.getInstanceEmailThreadVisitante(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(dto.email, dto.proposto));
		
		String titulo = dto.proposto + ", sua duvida #" + dtoThread.id + " sobre imovel #" + 
		ifcdto.imovel.id + " em " + 
		ifcdto.imovel.endereco.cidade + "/" + ifcdto.imovel.endereco.uf + " foi enviada.";
		email.enviar(lst, null, titulo, null);
	}
	
	private void enviarEmailVisitanteThreadRespostaComentario(ContatoClienteDTO dto, ImovelFichaCompletaDTO ifcdto, ContatoAnuncianteThreadDTO dtoThread) throws AlugueRelaxeException {
		Map<String,String> conteudo = new HashMap<String,String>();
		
		DateManagerBase dv = DateManagerBase.getDateManagerInstance(dto.dataCadastro);
		String link = VariavelCache.getInstance().getValor(VariavelConstantes.URL_THREADS);
		link = StringUtil.replaceStringNew(link, "${HASH}", dto.id);
		link = StringUtil.replaceStringNew(link, "${OMC}", dto.codigoOMCThreadVisitante);
		
		conteudo.put(TemplateConstantes.TAGC_NOME_ANUNCIANTE, ifcdto.cliente.nome.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_NOME_VISITANTE, dto.proposto);
		conteudo.put(TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
		conteudo.put(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcdto.imovel.descricaoGeral);
		conteudo.put(TemplateConstantes.TAGC_THREAD, dtoThread.thread);
		conteudo.put(TemplateConstantes.TAGC_LINK, link);

		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VISITA, dv.getDateTimeFull() );
			conteudo.put(TemplateConstantes.TAGC_DATA_DUVIDA,  dv.getDateTimeFull());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Email email = EmailFactory.getInstanceEmailThreadVisitanteResposta(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(dto.email, dto.proposto));
		
		String titulo = dto.proposto + ", sua duvida #" + dtoThread.id + " sobre imovel #" + 
		ifcdto.imovel.id + " em " + 
		ifcdto.imovel.endereco.cidade + "/" + ifcdto.imovel.endereco.uf + " foi respondida.";
		email.enviar(lst, null, titulo, null);
	}
	
	
	private void enviarEmailVisitanteThreadResposta(ContatoClienteDTO dto, ImovelFichaCompletaDTO ifcdto, ContatoAnuncianteThreadDTO dtoThread) throws AlugueRelaxeException {
		Map<String,String> conteudo = new HashMap<String,String>();
		
		DateManagerBase dv = DateManagerBase.getDateManagerInstance(dto.dataCadastro);
		String link = VariavelCache.getInstance().getValor(VariavelConstantes.URL_THREADS);
		link = StringUtil.replaceStringNew(link, "${HASH}", dto.id);
		link = StringUtil.replaceStringNew(link, "${OMC}", dto.codigoOMCThreadVisitante);
		
		conteudo.put(TemplateConstantes.TAGC_NOME_ANUNCIANTE, ifcdto.cliente.nome.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_NOME_VISITANTE, dto.proposto);
		conteudo.put(TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
		conteudo.put(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcdto.imovel.descricaoGeral);
		conteudo.put(TemplateConstantes.TAGC_THREAD, dtoThread.thread);
		conteudo.put(TemplateConstantes.TAGC_LINK, link);

		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VISITA, dv.getDateTimeFull() );
			conteudo.put(TemplateConstantes.TAGC_DATA_DUVIDA,  dv.getDateTimeFull());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Email email = EmailFactory.getInstanceEmailThreadVisitanteResposta(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(dto.email, dto.proposto));
		
		String titulo = dto.proposto + ", sua duvida #" + dtoThread.id + " sobre imovel #" + 
		ifcdto.imovel.id + " em " + 
		ifcdto.imovel.endereco.cidade + "/" + ifcdto.imovel.endereco.uf + " foi respondida.";
		email.enviar(lst, null, titulo, null);
	}
	
	private void enviarEmailThreadEditada(ContatoClienteDTO dto, ImovelFichaCompletaDTO ifcdto, ContatoAnuncianteThreadDTO dtoThread)
			throws AlugueRelaxeException {
		Map<String,String> conteudo = new HashMap<String,String>();
		
		DateManagerBase dv = DateManagerBase.getDateManagerInstance(dto.dataCadastro);
		conteudo.put(TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
		conteudo.put(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcdto.imovel.descricaoGeral);
		conteudo.put(TemplateConstantes.TAGC_THREAD, dtoThread.thread);
		conteudo.put(TemplateConstantes.TAGC_THREAD_EDITADA, dtoThread.threadEditada);
		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VISITA, dv.getDateTimeFull() );
			conteudo.put(TemplateConstantes.TAGC_DATA_DUVIDA,  dv.getDateTimeFull());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (dtoThread.origem.equals("A")) {
			conteudo.put(TemplateConstantes.TAGC_NOME, ifcdto.cliente.nome.toUpperCase());
		} else if (dtoThread.origem.equals("V")) {
			conteudo.put(TemplateConstantes.TAGC_NOME, dto.proposto.toUpperCase());
		} 

		Email email = EmailFactory.getInstanceEmailThreadEditada(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		if (dtoThread.origem.equals("A")) {
			lst.add(new EmailRecord(ifcdto.cliente.email, ifcdto.cliente.nome));
		} else if (dtoThread.origem.equals("V")) {
			lst.add(new EmailRecord(dto.email, dto.proposto));
		}
		
		email.enviar(lst, null, "Comentario Thread# " + dtoThread.id + " foi modificada p/ imovel #" + 
				ifcdto.imovel.id + " em " + 
				ifcdto.imovel.endereco.cidade + "/" + ifcdto.imovel.endereco.uf, null);
	}


	private void gravarSMSFila(DAOFactory daofactory, FilaSMSDTO smsdto) throws AlugueRelaxeException {
		FilaSMSBusiness fsb = new FilaSMSBusinessImpl();
		fsb.incluir(daofactory, smsdto);
	}

	private void gravarChatThread(DAOFactory daofactory, ContatoClienteDTO dto, ChatDTO chatdto) throws AlugueRelaxeException {
		
		ChatBusiness<ChatDTO> cb = new ChatBusinessImpl();
		cb.incluir(daofactory, chatdto);
		
	}

	public ContatoAnuncianteThreadDTO incluirResposta(DAOFactory daofactory, ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException{
		// Gera DTO com a resposta do anunicante sobre a pergunta do visitante
		ContatoAnuncianteThreadDTO dto = new ContatoAnuncianteThreadDTO();
		dto.idParent = catdto.idParent;
		dto.origem = catdto.origem;
		dto.modo = (catdto.modo.equals(Constantes.COMENTARIO) ? catdto.modo : Constantes.PERGUNTA_RESPOSTA);
		dto.thread = catdto.thread;
		ContatoAnuncianteThreadDTO dtoretorno = this.incluir(daofactory, ccdto, dto);
		return dtoretorno;
	}
	
	public ContatoAnuncianteThreadDTO incluirDuvida(DAOFactory daofactory, ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException {
		ContatoAnuncianteThreadDTO dto = this.incluir(daofactory, ccdto, catdto);
		this.notificarPendenciaAdministrador(daofactory, ccdto, dto);
		return dto;		
	}		
	
	public ContatoAnuncianteThreadDTO incluirComentario(DAOFactory daofactory, 
														AvaliacaoReservaDTO dto) throws AlugueRelaxeException {
		// Gera DTO com a resposta do anunicante sobre a pergunta do visitante
		ContatoAnuncianteThreadDTO catdto = new ContatoAnuncianteThreadDTO();
		catdto.id = daofactory.getNextSequence(daofactory, "SEQ_IMCT_ID");
		catdto.status = Constantes.PENDENTE;
		catdto.idParent = -1;
		catdto.origem = Constantes.ORIGEM_VISITANTE; // O comentario SEMPRE parte do visitante
		catdto.modo = Constantes.COMENTARIO;
		catdto.thread = dto.avaliacao;
		ContatoAnuncianteBusiness cab = new ContatoAnuncianteBusinessImpl();
		ContatoAnuncianteThreadDTO catdtoretorno = this.incluir(daofactory, dto.contatoCliente, catdto);
		
		return catdtoretorno;
	}
	

	private void notificarPendenciaAdministrador(DAOFactory daofactory, ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException {
		//---------------------------------------------------------------------
		// Envia e-mail ao administrador (moderador) solicitando aprovacao
		// antes da liberacao da thread
		//---------------------------------------------------------------------
		
		Map<String,String> conteudo = new HashMap<String,String>();
		
		DateManagerBase dv = DateManagerBase.getDateManagerInstance(catdto.dataCadastro);
		String link = VariavelCache.getInstance().getValor(VariavelConstantes.URL_THREADS_APROVAR);
		link = StringUtil.replaceStringNew(link, "${hash}", catdto.hash);
		link = StringUtil.replaceStringNew(link, "${action}", Constantes.APROVAR);
		link = StringUtil.replaceStringNew(link, "${modo}", catdto.modo);

		String linkEditado = VariavelCache.getInstance().getValor(VariavelConstantes.URL_THREADS_APROVAR_EDITADO);
		linkEditado = StringUtil.replaceStringNew(linkEditado, "${hash}", catdto.hash);
		linkEditado = StringUtil.replaceStringNew(linkEditado, "${action}", Constantes.APROVAR_COM_EDICAO);
		linkEditado = StringUtil.replaceStringNew(linkEditado, "${modo}", catdto.modo);
		
		String linkAcompanhamento = VariavelCache.getInstance().getValor(VariavelConstantes.URL_THREADS);
		linkAcompanhamento = StringUtil.replaceStringNew(linkAcompanhamento, "${HASH}", ccdto.id);
		linkAcompanhamento = StringUtil.replaceStringNew(linkAcompanhamento, "${OMC}", ccdto.codigoOMCThreadAdmin);
		
		conteudo.put(TemplateConstantes.TAGC_NOME_VISITANTE, ccdto.proposto);
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_PLANO, ccdto.ifcdto.imovelPlano.plano.descricao);
		conteudo.put(TemplateConstantes.TAGC_CIDADE, ccdto.ifcdto.imovel.endereco.cidade.toUpperCase());
		conteudo.put(TemplateConstantes.TAGC_UF, ccdto.ifcdto.imovel.endereco.uf);
		conteudo.put(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO, ccdto.ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ccdto.ifcdto.imovel.descricaoGeral);
		conteudo.put(TemplateConstantes.TAGC_THREAD, catdto.thread);
		conteudo.put(TemplateConstantes.TAGC_THREAD_ID, String.valueOf(catdto.id));
		conteudo.put(TemplateConstantes.TAGC_LINK_APROVAR, link);
		conteudo.put(TemplateConstantes.TAGC_LINK_APROVAR_EDITADO, linkEditado);
		conteudo.put(TemplateConstantes.TAGC_LINK_ACOMPANHAMENTO_PORTAL, linkAcompanhamento);
		
		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VISITA, dv.getDateTimeFull() );
			conteudo.put(TemplateConstantes.TAGC_DATA_DUVIDA,  dv.getDateTimeFull());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Email email = EmailFactory.getInstanceEmailThreadPendenciaAprovacao(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE), "Moderador"));
		
		email.enviar(lst, null,  "Thread #" + catdto.id +" aguarda atuacao do moderador" , null);
		
		//---------------------------------------------------------------------
		// Grava um SMS para administrador avisando da necessidade de aprovacao
		//---------------------------------------------------------------------
		Map<String,String> param = new HashMap<String,String>();
		param.put(Constantes.P1, String.valueOf(catdto.id));
		
		FilaSMSDTO smsdto = new FilaSMSDTO();
		smsdto.celular = VariavelCache.getInstance().getValor(VariavelConstantes.CELULAR_ADMIN_GATEWAY);
		smsdto.msg = MensagemCache.getInstance().getMensagem(MSGCODE.SMS_APROVAR_THREAD, param);
		smsdto.modo = Constantes.SMS_MODO_ENVIO_GATEWAY;
		smsdto.prioridade = Constantes.SMS_PRIORIDADE_ALTA;
		this.gravarSMSFila(daofactory, smsdto);
	}
	
	public ContatoAnuncianteThreadDTO incluir(DAOFactory daofactory, ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException {
	
		// Obtem uma implementacao DAO
		ContatoAnuncianteThreadDAO dao = daofactory.getContatoAnuncianteThreadDAO(daofactory);
		
		// Monta o DTO de 2a. thread em diante
		ContatoAnuncianteThreadDTO dtoEnvio = new ContatoAnuncianteThreadDTO();
		catdto.id = daofactory.getNextSequence(daofactory, "SEQ_IMCT_ID");
		catdto.status = Constantes.PENDENTE;
		catdto.idParent = (catdto.idParent == 0 ? -1 : catdto.idParent );
		this.calcularHash(catdto);
		ContatoAnuncianteThreadDTO dtoretorno = dao.insert(ccdto, catdto);
		this.notificarPendenciaAdministrador(daofactory, ccdto, dao.load(catdto.hash));
		
		return dtoretorno;
		
	}
	
	
	private void calcularHash(ContatoAnuncianteThreadDTO dto) {
		BaseFactorySecurity bfs = BaseFactorySecurity.getInstance(BaseFactorySecurity.SHA1);
		try {
			StringBuilder misturador = new StringBuilder();
			misturador.append(String.valueOf(dto.id));
			dto.hash = bfs.criptografar(misturador.toString());
		} catch (InvalidKeyException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		}
	}

	public void setDados(ContatoAnuncianteThreadDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ContatoAnuncianteThreadDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(ContatoAnuncianteThreadDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ContatoAnuncianteThreadDTO atualizar(DAOFactory daofactory,
			ContatoAnuncianteThreadDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, ContatoAnuncianteThreadDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ContatoAnuncianteThreadDTO procurar(DAOFactory daofactory,
			ContatoAnuncianteThreadDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ContatoAnuncianteThreadDTO procurar(DAOFactory daofactory,
			long idParent) throws AlugueRelaxeException {

		// Obtem uma implementacao DAO
		ContatoAnuncianteThreadDAO dao = daofactory.getContatoAnuncianteThreadDAO(daofactory);
		
		return dao.load(idParent);
	}


	public ContatoAnuncianteThreadDTO procurar(DAOFactory daofactory,
			String hash) throws AlugueRelaxeException {

		// Obtem uma implementacao DAO
		ContatoAnuncianteThreadDAO dao = daofactory.getContatoAnuncianteThreadDAO(daofactory);
		
		return dao.load(hash);
	}

	public List<ContatoAnuncianteThreadDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ContatoAnuncianteThreadDTO> buscarTodas(DAOFactory daofactory,
			int start) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ContatoAnuncianteThreadDTO> listarThreads(
			DAOFactory daofactory, String hash, String modo)
			throws AlugueRelaxeException {
		
		// Obtem uma implementacao DAO
		ContatoAnuncianteThreadDAO dao = daofactory.getContatoAnuncianteThreadDAO(daofactory);
		
		return dao.listThreads(hash, modo);
	}


	public List<ContatoAnuncianteThreadDTO> listarComentarios(
			DAOFactory daofactory, long idImovel)
			throws AlugueRelaxeException {
		
		// Obtem uma implementacao DAO
		ContatoAnuncianteThreadDAO dao = daofactory.getContatoAnuncianteThreadDAO(daofactory);
		
		return dao.listComentarios(idImovel);
	}

	public ContatoAnuncianteThreadDTO incluir(DAOFactory daofactory,
			ContatoAnuncianteThreadDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}


}
