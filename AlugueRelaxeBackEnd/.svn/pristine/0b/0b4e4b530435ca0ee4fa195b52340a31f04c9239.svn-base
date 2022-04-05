package br.com.jcv.backend.filasms;

import java.io.Serializable;
import java.sql.Timestamp;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.dto.DTOPadrao;

public class FilaSMSDTO extends DTOPadrao implements Serializable {
	private static final long serialVersionUID = -1798522233606277468L;
	public long id;
	public String celular;
	public String msg;
	public boolean queued;
	public Timestamp dataQueued;	
	public Timestamp dataCadastro;	
	public String modo;
	public int prioridade = Constantes.SMS_PRIORIDADE_NORMAL;
	public Timestamp dataEnvioGateway;	
	public String statusCode;
}
