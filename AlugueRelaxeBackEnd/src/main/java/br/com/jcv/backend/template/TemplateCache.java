package br.com.jcv.backend.template;



import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.TemplateService;
import br.com.jcv.backend.mensagem.MensagemAdapter;
import br.com.jcv.backend.mensagem.MensagemEvent;

public class TemplateCache extends MensagemAdapter {
	
	private static Logger logger = Logger.getLogger(br.com.jcv.backend.template.TemplateCache.class);
	private HashMap<String, TemplateDTO> cache = new HashMap<String,TemplateDTO>();
	private static TemplateCache instance = null;
	
	private TemplateCache() {
		this.carregarCacheTemplates();
	}
	
	private void carregarCacheTemplates() {
		logger.debug("Iniciando a carga do cache de templates.");
		synchronized (this) {
			this.cache.clear();
			try {
				TemplateService bs = new TemplateServiceImpl();
				List<TemplateDTO> list = bs.listarRegistros(Constantes.ATIVO);
				logger.debug("--> Total de mensagens = " + list.size());
				
				for (TemplateDTO dto: list) {
					this.cache.put(String.valueOf(dto.id), dto);
				}
				
			} catch (AlugueRelaxeException e) {
				logger.debug("Erro:" + e.getMessage());
			}
		}
	}
	
	public static TemplateCache getInstance() {
		if (instance == null){
			synchronized (TemplateCache.class) {
				if (instance == null) {
					instance = new TemplateCache();
				}
			}
		}
		return instance;
	}
	
	public TemplateDTO getTemplate(String idTemplate) throws AlugueRelaxeException {
		return this.cache.get(idTemplate);
	}
	
	public void mensagemNotificacao(MensagemEvent me) {
		logger.debug("Recebida notificacao para atualizar o cache de templates");
		this.carregarCacheTemplates();
	}
	

}
