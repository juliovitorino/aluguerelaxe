package br.com.jcv.backend.mensagem;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ApplicationService;
import br.com.jcv.backend.utility.StringUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class MensagemCache extends MensagemAdapter {
	
	private static Logger logger = Logger.getLogger(br.com.jcv.backend.mensagem.MensagemCache.class);
	private HashMap<String, String> cacheMensagem = new HashMap<String,String>();
	private static MensagemCache instance = null;
	
	private MensagemCache() {
		this.carregarCacheMensagens();
	}
	
	private void carregarCacheMensagens() {
		logger.debug("Iniciando a carga do cache de mensagens.");
		synchronized (this) {
			this.cacheMensagem.clear();
			try {
				ApplicationService<MensagemDTO> bs = new MensagemServiceImpl();
				List<? extends MensagemDTO> list = bs.listarRegistros();
				logger.debug("--> Total de mensagens = " + list.size());
				
				for (MensagemDTO dto: list) {
					this.cacheMensagem.put(dto.getId(), dto.getMensagem());
				}
				
			} catch (AlugueRelaxeException e) {
				logger.debug("Erro:" + e.getMessage());
			}
		}
	}
	
	public static MensagemCache getInstance() {
		if (instance == null){
			synchronized (MensagemCache.class) {
				if (instance == null) {
					instance = new MensagemCache();
				}
			}
		}
		return instance;
	}
	
	public String getMensagem(String msgcode) throws AlugueRelaxeException {
		String mensagem = cacheMensagem.get(msgcode);
		return mensagem;
	}
	
	public String getMensagem(String msgcode, Map<String,String> mpTroca) throws AlugueRelaxeException {
		String mensagem = this.cacheMensagem.get(msgcode);
		Iterator it = mpTroca.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        mensagem = StringUtil.replaceStringNew(mensagem,pairs.getKey().toString(),pairs.getValue().toString());
	    }
		return mensagem;
	}
	
	
	public void mensagemNotificacao(MensagemEvent me) {
		logger.debug("Recebi notificacao para atualizar o cache de mensagens");
		this.carregarCacheMensagens();
	}
	

}
