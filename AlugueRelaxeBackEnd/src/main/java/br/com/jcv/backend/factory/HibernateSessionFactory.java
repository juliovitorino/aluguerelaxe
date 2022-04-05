package br.com.jcv.backend.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.jcv.backend.utility.GlobalStartup;

public class HibernateSessionFactory {
	
    private static final SessionFactory sessionFactory;

    static {
        try {
        	
        	// Obtem o nome do arquivo de configuracao ativo do hibernate para a aplicacao
			String hbxmlAtivo = GlobalStartup.getInstance().getFixedValue("HIBERNATE_XML_ACTIVE");
			String hbxml = GlobalStartup.getInstance().getFixedValue(hbxmlAtivo);
            sessionFactory = new Configuration().configure(hbxml).buildSessionFactory();
			
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
