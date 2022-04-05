package br.com.jcv.backend.modopublicidadevisitas;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


public class ModoPublicidadeVisitasDTO implements Serializable {
	private static final long serialVersionUID = -9203869177283766868L;
	public long id;
	public long idModoPublicidade;
	public Date dataResposta;
	public long qtdeResposta;
	public Timestamp dataCadastro;
}