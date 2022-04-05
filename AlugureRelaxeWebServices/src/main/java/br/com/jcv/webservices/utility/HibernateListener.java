package br.com.jcv.webservices.utility;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.jcv.backend.factory.HibernateSessionFactory;

public class HibernateListener implements ServletContextListener {

	  public void contextInitialized(ServletContextEvent event) {
	        HibernateSessionFactory.getSessionFactory(); // Just call the static initializer of that class    
	    }

	    public void contextDestroyed(ServletContextEvent event) {
	    	HibernateSessionFactory.getSessionFactory().close(); // Free all resources
	    }

}
