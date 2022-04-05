package br.com.jcv.backend.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelService;


public class UltimosImoveisCadastradosCache {
	
	private static Logger logger = Logger.getLogger(UltimosImoveisCadastradosCache.class);
	private static UltimosImoveisCadastradosCache instance = null;
	
	private int tamanhoFila = 0;
	private List<ImovelFichaCompletaDTO> cache = Collections.synchronizedList(new ArrayList<ImovelFichaCompletaDTO>());


	private UltimosImoveisCadastradosCache() {
		this.init();
	}
	
	private void init() {
		logger.info("Iniciando a carga do cache de ultimos imoveis cadastrados.");
		synchronized (this) {
			this.cache.clear();
			try {
				ImovelService is = new ImovelServiceImpl();
				List<ImovelFichaCompletaDTO> list = is.listarUltimosImoveisCadastrados();

				if (list != null){
					// Preenche a queue com a lista
					for (ImovelFichaCompletaDTO dto: list) {
						this.cache.add(dto);
					}
					logger.info("cache de ultimos imoveis cadastrados carregado.");
				}
				
			} catch (AlugueRelaxeException e) {
				logger.error("Erro:" + e.getMessage());
			}
		}
	}
	
	public static UltimosImoveisCadastradosCache getInstance() throws AlugueRelaxeException {
		if (instance == null){
			synchronized (UltimosImoveisCadastradosCache.class) {
				if (instance == null) {
					instance = new UltimosImoveisCadastradosCache();
				}
			}
		}
		return instance;
	}
	
	public List<ImovelFichaCompletaDTO> getCache() {
		List<ImovelFichaCompletaDTO> retorno = null;
		
		if (this.cache != null) {
			Object[] array = this.cache.toArray();
			retorno = new ArrayList<ImovelFichaCompletaDTO>();
			for (int i = 0; i < array.length;  i++) {
				retorno.add((ImovelFichaCompletaDTO)array[i]);
			}
		}
		
		return retorno;
	}

	public void addNovoImovel(long id) {
		logger.info("Adicionando novo imovel na queue");
		synchronized (this) {
			try {
				ImovelServiceImpl is = new ImovelServiceImpl();
				ImovelFichaCompletaDTO dto = is.pesquisarFichaCompletaImovel(id);

				if (dto != null){
					this.cache.remove(0); // Aplica o FIFO
					//this.cache.remove(this.cache.size()-1); // Aplica o FIFO
					this.cache.add(dto); // Coloca elemento na fila
				}
				
			} catch (AlugueRelaxeException e) {
				logger.error("Erro:" + e.getMessage());
			}
		}
	}
 
}
