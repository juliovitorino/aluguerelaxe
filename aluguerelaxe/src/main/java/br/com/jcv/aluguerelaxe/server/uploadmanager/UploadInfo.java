package br.com.jcv.aluguerelaxe.server.uploadmanager;

import java.io.Serializable;

public class UploadInfo implements Serializable {
	private static final long serialVersionUID = -1262061523549313739L;
	public long bytesTotalArquivo;
	public long bytesTransferidos;
	public int percentualTransferido;

	public UploadInfo(long bytesTotalArquivo) {
		this(bytesTotalArquivo,0,0);
	}

	public UploadInfo(long bytesTotalArquivo, long bytesTransferidos, int percentualTransferido) {
		this.bytesTotalArquivo = bytesTotalArquivo;
		this.bytesTransferidos = bytesTransferidos;
		this.percentualTransferido = percentualTransferido;
	}
	
	
	
	
}
