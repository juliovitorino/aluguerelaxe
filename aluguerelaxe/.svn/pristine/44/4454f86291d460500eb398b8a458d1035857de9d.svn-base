package br.com.jcv.aluguerelaxe.server.uploadmanager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class UploadManager {

	private static final Logger logger = Logger.getLogger(UploadManager.class);
	
	private static UploadManager instance = null;
	private Map<String, UploadInfo> mapUploadManager = null;

	// Cria o mapa sincronizado
	private UploadManager() {
		logger.info("**************************************************");
		logger.info("UploadManager criado com sucesso!");
		logger.info("**************************************************");	
	
		mapUploadManager = Collections.synchronizedMap(new HashMap<String, UploadInfo>());
	}
	
	public static UploadManager getInstance() {
		if (instance == null) {
			synchronized (UploadManager.class) {
				if (instance == null) {
					instance = new UploadManager();
				}
			}
		}
		return instance;
	}
	
	public void addRegistroUploadInfo(String chave, long bytesTotalArquivo) {
		mapUploadManager.put(chave, new UploadInfo(bytesTotalArquivo));
		logger.debug("UploadManager criou chave " + chave);
		logger.debug("UploadManager total de entradas = " + mapUploadManager.size());
	}
	
	public void updateUploadInfo(String chave, UploadInfo ui) {
		UploadInfo uiItem = mapUploadManager.get(chave);
		if (uiItem != null){
			uiItem.bytesTransferidos = ui.bytesTransferidos;
			uiItem.percentualTransferido = ui.percentualTransferido;
			logger.debug("UploadManager atualizando chave " + chave + " / T:" + uiItem.bytesTransferidos + "%:" + uiItem.percentualTransferido );
		}
	}
	
	public UploadInfo getUploadInfo(String chave) {
		logger.debug("UploadManager obtendo chave " + chave);
		return mapUploadManager.get(chave);
	}
	
	public void removeRegistroUploadInfo(String chave) {
		mapUploadManager.remove(chave);
		logger.info("**************************************************");
		logger.info("UploadManager excluiu a chave " + chave + " com sucesso");
		logger.info("UploadManager total de entradas = " + mapUploadManager.size());
		logger.info("**************************************************");	
	}

}