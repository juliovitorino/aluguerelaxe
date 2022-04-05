package br.com.jcv.backend.commons.xml;

import br.com.jcv.backend.workers.spammer.ConfigSpammerDTO;
import br.com.jcv.backend.workers.spammer.IXMLReaderConfigSpammer;
import br.com.jcv.backend.workers.spammer.IXMLReaderMailingList;
import br.com.jcv.backend.workers.spammer.MailingDTO;

public class IXMLReaderFactory {
	
	public static IXMLReader<ConfigSpammerDTO> getInstanceConfigSpammer(String pathxml) {
		return new IXMLReaderConfigSpammer(pathxml);
	}

	public static IXMLReader<MailingDTO> getInstanceMailing(String pathxml) {
		return new IXMLReaderMailingList(pathxml);
	}

}
