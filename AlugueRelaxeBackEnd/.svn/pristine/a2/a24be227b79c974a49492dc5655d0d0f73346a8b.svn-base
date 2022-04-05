package br.com.jcv.backend.mensagem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.MensagemService;
import br.com.jcv.backend.utility.StringUtil;

public class I18nCache extends MensagemAdapter {
	
	private static Logger logger = Logger.getLogger(br.com.jcv.backend.mensagem.I18nCache.class);
	private HashMap<String, String> cacheMensagem = new HashMap<String,String>();
	private static I18nCache instance = null;
	
	private I18nCache() {
		this.carregarCacheMensagens();
	}
	
	private void carregarCacheMensagens() {
		logger.debug("Iniciando a carga do cache de mensagens internacionalizadas.");
		synchronized (this) {
			this.cacheMensagem.clear();
			try {
				MensagemService<MensagemDTO> bs = new MensagemServiceImpl();
				List<? extends MensagemDTO> list = bs.listarRegistros("I%");
				logger.debug("--> Total de mensagens = " + list.size());
				
				for (MensagemDTO dto: list) {
					this.cacheMensagem.put(dto.getId(), dto.getMensagem());
				}
				
			} catch (AlugueRelaxeException e) {
				logger.debug("Erro:" + e.getMessage());
			}
		}
	}
	
	public static I18nCache getInstance() {
		if (instance == null){
			synchronized (I18nCache.class) {
				if (instance == null) {
					instance = new I18nCache();
				}
			}
		}
		return instance;
	}
	
	public String getMensagem(String msgcode) throws AlugueRelaxeException {
		return this.getMensagem(msgcode, null);
	}
	
	public String getMensagem(String msgcode, Map<String,String> mpTroca) throws AlugueRelaxeException {
		String mensagem = this.cacheMensagem.get(msgcode);
		if (mpTroca != null){
			Iterator it = mpTroca.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry)it.next();
				mensagem = StringUtil.replaceStringNew(mensagem,pairs.getKey().toString(),pairs.getValue().toString());
			}
		}
		return mensagem;
	}
	
	
	public void mensagemNotificacao(MensagemEvent me) {
		logger.debug("Recebi notificacao para atualizar o cache de mensagens");
		this.carregarCacheMensagens();
	}
	

}
