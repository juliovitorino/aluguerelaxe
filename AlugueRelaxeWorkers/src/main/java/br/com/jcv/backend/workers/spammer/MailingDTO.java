package br.com.jcv.backend.workers.spammer;

import java.io.Serializable;
import java.util.List;

public class MailingDTO implements Serializable {

	private static final long serialVersionUID = 7062149434729929733L;

	/**
	 * Nome do usuario do mailing
	 */
	public List<MailingRecordDTO> lstMailing;
	
}
