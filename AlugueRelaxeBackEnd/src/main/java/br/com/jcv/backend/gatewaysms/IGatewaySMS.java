package br.com.jcv.backend.gatewaysms;

import br.com.jcv.backend.filasms.FilaSMSDTO;



/**
 * <p>Inteface para implementacao com servicos de gateway SMS</p>
 * 
 * @author Julio
 *
 */
public interface IGatewaySMS {

	public static final int MAX_CARACTERES_SMS = 142;

	FilaSMSDTO execute(FilaSMSDTO smsdto);
}

