package br.com.jcv.backend.workers.spammer;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import br.com.jcv.backend.commons.xml.IXMLReader;

public class IXMLReaderMailingList extends IXMLReader<MailingDTO> {

	private MailingDTO mailing = null;
	private String nome = null;
	private String email = null;
	private String textolivre1 = "";
	private String textolivre2 = "";
	private String textolivre3 = "";
	private String textolivre4 = "";
	private String textolivre5 = "";
	private String textolivre6 = "";
	private String textolivre7 = "";
	private String textolivre8 = "";
	private String textolivre9 = "";
	private String textolivre10 = "";
	private String textolivre11 = "";
	private String textolivre12 = "";
	private String textolivre13 = "";
	private String textolivre14 = "";
	private String textolivre15 = "";
	private String textolivre16 = "";
	private String textolivre17 = "";
	private String textolivre18 = "";
	private String textolivre19 = "";
	private String textolivre20 = "";
	
	public IXMLReaderMailingList(String pathxml) {
		super(pathxml);
	}

	@Override
	public MailingDTO execute() {
		try {
			// realiza o parse o documento
			this.listener.onFire("parse do documento " + this.getPathxml() + " iniciado");
			this.parseDocument(this.getPathxml());
			this.listener.onFire("parse do documento " + this.getPathxml() + " finalizado");
		} catch (Exception e) {
			this.mailing = null;
		}
		
		return this.mailing;
	}

	@Override
	public MailingDTO getDTO() {
		return mailing;
	}

	public void startElement(
			final String uri,
			final String localName,
			final String qName,
			final Attributes attributes) throws SAXException {

		//reset
		this.tempval = "";

		// verifica tags
		if (qName.equalsIgnoreCase("mailing-list")) {
			this.mailing = new MailingDTO();
			this.mailing.lstMailing = new ArrayList<MailingRecordDTO>();
		}
	}
	
	public void endElement(
			final String uri,
			final String localName,
			final String qName) throws SAXException {
		
		if (qName.equalsIgnoreCase("nome")) {
			this.nome = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("email")) {
			this.email = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre1")) {
			this.textolivre1 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre2")) {
			this.textolivre2 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre3")) {
			this.textolivre3 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre4")) {
			this.textolivre4 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre5")) {
			this.textolivre5 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre6")) {
			this.textolivre6 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre7")) {
			this.textolivre7 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre8")) {
			this.textolivre8 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre9")) {
			this.textolivre9 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre10")) {
			this.textolivre10 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre11")) {
			this.textolivre11 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre12")) {
			this.textolivre12 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre13")) {
			this.textolivre13 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre14")) {
			this.textolivre14 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre15")) {
			this.textolivre15 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre16")) {
			this.textolivre16 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre17")) {
			this.textolivre17 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre18")) {
			this.textolivre18 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre19")) {
			this.textolivre19 = new String(this.tempval);
		} else if (qName.equalsIgnoreCase("textolivre20")) {
			this.textolivre20 = new String(this.tempval);

		} else if (qName.equalsIgnoreCase("mailing-record")) {
			MailingRecordDTO mrdto = new MailingRecordDTO();
			mrdto.email = this.email;
			mrdto.nome = this.nome;
			mrdto.textolivre1 = this.textolivre1;
			mrdto.textolivre2 = this.textolivre2;
			mrdto.textolivre3 = this.textolivre3;
			mrdto.textolivre4 = this.textolivre4;
			mrdto.textolivre5 = this.textolivre5;
			mrdto.textolivre6 = this.textolivre6;
			mrdto.textolivre7 = this.textolivre7;
			mrdto.textolivre8 = this.textolivre8;
			mrdto.textolivre9 = this.textolivre9;
			mrdto.textolivre10 = this.textolivre10;
			mrdto.textolivre11 = this.textolivre11;
			mrdto.textolivre12 = this.textolivre12;
			mrdto.textolivre13 = this.textolivre13;
			mrdto.textolivre14 = this.textolivre14;
			mrdto.textolivre15 = this.textolivre15;
			mrdto.textolivre16 = this.textolivre16;
			mrdto.textolivre17 = this.textolivre17;
			mrdto.textolivre18 = this.textolivre18;
			mrdto.textolivre19 = this.textolivre19;
			mrdto.textolivre20 = this.textolivre20;
			
			this.mailing.lstMailing.add(mrdto);
			this.listener.onFire("email " + mrdto.email + " carregado");
		}
	}
	
	
}
