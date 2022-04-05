package br.com.jcv.aluguerelaxe.client.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UploadInfoVO extends VOPadrao implements IsSerializable {
	public long bytesTotalArquivo;
	public long bytesTransferidos;
	public int percentualTransferido;

	public UploadInfoVO() {}
	
	public UploadInfoVO(long bytesTotalArquivo) {
		this(bytesTotalArquivo,0,0);
	}

	public UploadInfoVO(long bytesTotalArquivo, long bytesTransferidos, int percentualTransferido) {
		this.bytesTotalArquivo = bytesTotalArquivo;
		this.bytesTransferidos = bytesTransferidos;
		this.percentualTransferido = percentualTransferido;
	}
	
}
