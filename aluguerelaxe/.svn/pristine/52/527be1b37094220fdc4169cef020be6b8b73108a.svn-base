package br.com.jcv.aluguerelaxe.utility;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.jcv.aluguerelaxe.server.cache.SuperBannerCache;

/**
 * <h2>SuperBannerCacheListener</h2>
 * <p>Listener para criar a instancia estatica do cache de super banner</p>
 * @author Julio Vitorino
 * @since August, 18th 2011
 */
public class SuperBannerCacheListener implements ServletContextListener {

	  public void contextInitialized(ServletContextEvent event) {
	        SuperBannerCache.getInstance(event.getServletContext());
	    }

	    public void contextDestroyed(ServletContextEvent event) {
	    	//Nao Aplicado neste contexto
	    }

}

