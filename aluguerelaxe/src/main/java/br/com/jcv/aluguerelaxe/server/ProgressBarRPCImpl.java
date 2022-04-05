package br.com.jcv.aluguerelaxe.server;

import br.com.jcv.aluguerelaxe.client.componente.progressbar.ProgressBarRPC;
import br.com.jcv.aluguerelaxe.client.vo.UploadInfoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;
import br.com.jcv.aluguerelaxe.server.uploadmanager.UploadInfo;
import br.com.jcv.aluguerelaxe.server.uploadmanager.UploadManager;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemCache;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ProgressBarRPCImpl extends RemoteServiceServlet implements ProgressBarRPC {

	private static final long serialVersionUID = 6912701980381309092L;

	/**
	 * Obter as informacoes de UploadManager para a <code>idSessao</code> indicada.
	 */
	public UploadInfoVO getProgresso(String idSessao) {
		UploadInfoVO voretorno = null;
		
		// Obtem uma instancia de UploadManager
		UploadManager um = UploadManager.getInstance();
		UploadInfo ui = um.getUploadInfo(idSessao);
		
		if (ui != null){
			voretorno = new UploadInfoVO(ui.bytesTotalArquivo, ui.bytesTransferidos, ui.percentualTransferido); 
		}
		
		return voretorno;
	}
	
	/**
	 * Invocar o UploadManager para remover a entrada de <code>idSessao</code>.
	 */
	public VOPadrao terminateProgresso(String idSessao) {
		// Obtem uma instancia de UploadManager
		UploadManager um = UploadManager.getInstance();
		um.removeRegistroUploadInfo(idSessao);
		
		VOPadrao vo = new VOPadrao();
		vo.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		try {
			vo.msgcodeString = MensagemCache.getInstance().getMensagem(vo.msgcode);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
	}

}
