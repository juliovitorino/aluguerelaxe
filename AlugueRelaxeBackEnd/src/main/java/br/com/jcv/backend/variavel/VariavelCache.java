package br.com.jcv.backend.variavel;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ApplicationService;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

public class VariavelCache extends VariavelAdapter {
	
	private static Logger logger = Logger.getLogger(br.com.jcv.backend.variavel.VariavelCache.class);
	private HashMap<String, String> cacheMensagem = new HashMap<String,String>();
	private static volatile VariavelCache instance = null;
	
	private VariavelCache() {
		this.carregarCacheMensagens();
	}
	
	private void carregarCacheMensagens() {
		logger.info("Iniciando a carga das variaveis de sistema.");
		synchronized (this) {
			this.cacheMensagem.clear();
			try {
				ApplicationService<VariavelDTO> bs = new VariavelServiceImpl();
				List<? extends VariavelDTO> list = bs.listarRegistros();
				
				for (VariavelDTO dto: list) {
					this.cacheMensagem.put(dto.getId(), dto.getValor());
				}
				
			} catch (AlugueRelaxeException e) {
				logger.error("Erro:" + e.getMessage());
			}
		}
	}
	
	public static VariavelCache getInstance() {
		if (instance == null){
			synchronized (VariavelCache.class) {
				if (instance == null) {
					instance = new VariavelCache();
				}
			}
		}
		return instance;
	}
	
	public String getValor(String variavel) throws AlugueRelaxeException {
		String valor = cacheMensagem.get(variavel);
		return valor;
	}
	
	public void mensagemNotificacao(VariavelEvent me) {
		logger.info("Notificacao para atualizar o cache de variaveis");
		this.carregarCacheMensagens();
	}
	

}
