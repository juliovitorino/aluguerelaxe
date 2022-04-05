package br.com.jcv.aluguerelaxe.utility;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.jcv.aluguerelaxe.server.cache.PublicidadeCache;

/**
 * <h2>PublicidadeCacheListener</h2>
 * <p>Listener para criar a instancia estatica do cache de publicidade</p>
 * @author Julio Vitorino
 * @since August, 18th 2011
 */
public class PublicidadeCacheListener implements ServletContextListener {

	  public void contextInitialized(ServletContextEvent event) {
	        PublicidadeCache.getInstance(event.getServletContext());
	    }

	    public void contextDestroyed(ServletContextEvent event) {
	    	//Nao Aplicado neste contexto
	    }

}

