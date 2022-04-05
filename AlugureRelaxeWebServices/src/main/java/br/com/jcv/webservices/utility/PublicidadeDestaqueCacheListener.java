package br.com.jcv.webservices.utility;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.jcv.webservices.cache.PublicidadeDestaqueCache;

/**
 * <h2>PublicidadeCacheListener</h2>
 * <p>Listener para criar a instancia estatica do cache de publicidade</p>
 * @author Julio Vitorino
 * @since August, 18th 2011
 */
public class PublicidadeDestaqueCacheListener implements ServletContextListener {

	  public void contextInitialized(ServletContextEvent event) {
		  PublicidadeDestaqueCache.getInstance();
	    }

	    public void contextDestroyed(ServletContextEvent event) {
	    	//Nao Aplicado neste contexto
	    }

}

