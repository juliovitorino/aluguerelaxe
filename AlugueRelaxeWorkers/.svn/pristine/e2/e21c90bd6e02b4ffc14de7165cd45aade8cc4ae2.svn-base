package br.com.jcv.backend.workers.spammer;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import br.com.jcv.backend.commons.xml.IXMLReader;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;

public class IXMLReaderConfigSpammer extends IXMLReader<ConfigSpammerDTO> {
	
	public final static long TEMPO_ESPERA_PADRAO = 20000;
	public final static boolean LIGADO = true;
	public final static boolean DESLIGADO = false;
	
	public final static String QUEUE = "QUEUE";
	public final static String EMAIL = "EMAIL";
	
	private ConfigSpammerDTO csdto = null;

	public IXMLReaderConfigSpammer(String pathxml) {
		super(pathxml);
	}

	@Override
	public ConfigSpammerDTO execute() {
		try {
			// Inicializa um objeto vazio
			this.csdto = new ConfigSpammerDTO();
			csdto.publicadores = new ArrayList<PublicadorVO>();
			csdto.listsmtp = new ArrayList<SmtpServerVO>();
			csdto.tempoespera = TEMPO_ESPERA_PADRAO;
			csdto.modoreverso = DESLIGADO;
			csdto.tarefa = "sem texto para <tarefa/>";
			csdto.modoEnvio = EMAIL;
			
			// realiza o parse o documento
			this.listener.onFire("parse do documento " + this.getPathxml() + " iniciado");
			this.parseDocument(this.getPathxml());
			this.listener.onFire("parse do documento " + this.getPathxml() + " finalizado");
		} catch (Exception e) {
			this.csdto = null;
		}
		
		return this.csdto;
	}

	public void startElement(
			final String uri,
			final String localName,
			final String qName,
			final Attributes attributes) throws SAXException {

		//reset
		this.tempval = "";

		// verifica tags
		if (qName.equalsIgnoreCase("publicador")) {
			PublicadorVO vo = new PublicadorVO();
			vo.nome = attributes.getValue("nome");
			vo.email = attributes.getValue("email");
			this.csdto.publicadores.add(vo);
			this.listener.onFire("publicador = " + vo.email + " carregado" );
		}

		if (qName.equalsIgnoreCase("servidoremail")) {
			SmtpServerVO vo = new SmtpServerVO();
			vo.nome = attributes.getValue("nome");
			vo.porta = attributes.getValue("porta");
			vo.contaAutenticacao = attributes.getValue("contaAutenticacao");
			vo.aliaspwd = attributes.getValue("aliaspwd");
			this.csdto.listsmtp.add(vo);
			this.listener.onFire("smtp server = " + vo.nome + ":" + vo.porta + " carregado" );
		}
		
		if (qName.equalsIgnoreCase("fichaimovel")) {
			this.csdto.listimovel = new ArrayList<ImovelFichaCompletaDTO>();
		}
	}

	/**
	 * Método sobrescrito.
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public void endElement(
			final String uri,
			final String localName,
			final String qName) throws SAXException {
		
		if (qName.equalsIgnoreCase("catalogo")) {
			this.csdto.catalogo = this.tempval;
			this.listener.onFire("catalogo = " + this.tempval );
		} else if (qName.equalsIgnoreCase("modoenvio")) {
			this.csdto.modoEnvio = this.tempval.equals("QUEUE") ? QUEUE : EMAIL;
			this.listener.onFire("template = " + this.tempval );
		} else if (qName.equalsIgnoreCase("queuepath")) {
			this.csdto.queuePath = this.tempval;
			this.listener.onFire("template = " + this.tempval );
		} else if (qName.equalsIgnoreCase("template")) {
			this.csdto.template = this.tempval;
			this.listener.onFire("template = " + this.tempval );
		} else if (qName.equalsIgnoreCase("assunto")) {
			this.csdto.assunto = this.tempval;
			this.listener.onFire("assunto = " + this.tempval );
		} else if (qName.equalsIgnoreCase("tempoespera")) {
			this.csdto.tempoespera = Long.valueOf(this.tempval);
			this.listener.onFire("tempo de espera entre emails (ms) = " + this.tempval );
		}  else if (qName.equalsIgnoreCase("tarefa")) {
			this.csdto.tarefa = this.tempval;
			this.listener.onFire("tarefa = " + this.tempval );
		} else if (qName.equalsIgnoreCase("modoreverso")) {
			this.csdto.modoreverso = this.tempval.equals("ON") ? LIGADO : DESLIGADO;
		} else if (qName.equalsIgnoreCase("id")) {
			ImovelService is = new ImovelServiceImpl();
			try {
				this.listener.onFire("Carregando imovel #ID " + this.tempval + "...");
				ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(Long.valueOf(this.tempval));
				
				// valido somente se estiver nao nulo e com imagens
				if (ifcdto != null){
					if ((ifcdto.imagensImovelTB != null) && (ifcdto.imagensImovelXG != null)) {
						this.csdto.listimovel.add(ifcdto);
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (AlugueRelaxeException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ConfigSpammerDTO getDTO() {
		return this.csdto;
	}

}
