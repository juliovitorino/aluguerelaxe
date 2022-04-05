package br.com.jcv.aluguerelaxe.server.uploadmanager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Ativar o singleton do UploadManager na carga do container
 *
 * @author Julio Vitorino
 */
public class UploadManagerListener implements ServletContextListener {

	/**
	 * Invocado automaticamente pelo container definido em listener-class
	 */
	public void contextInitialized(ServletContextEvent e)   {
		// Inicializa UploadManager
		UploadManager.getInstance();
	}   
	
	public void contextDestroyed(ServletContextEvent e)   {
	}	
}
