package br.com.jcv.backend.utility;

import java.rmi.RemoteException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.omg.CORBA.SystemException;

import br.com.jcv.backend.exception.ServiceLocatorException;
import br.com.jcv.backend.factory.EJBHomeFactory;

/**
 * Implementação de exemplo do Design Pattern Service Locator. Esta classe
 * implementa os Métodos para localizaééo de serviços de EJBs locais, remotos,
 * datasource e transação do usuério.
 * 
 * @author elmt
 * 
 */
public class ServiceLocator {

	private static ServiceLocator instance;

	/**
	 * Cosntrutor privado, evitando multiplas instâncias deste
	 * objeto(Singleton).
	 * 
	 * @throws ServiceLocatorException
	 */
	private ServiceLocator() throws ServiceLocatorException {
	}

	/**
	 * Recupera a instancia do Singleton do ServiceLocator
	 * 
	 * @return ServiceLocator
	 * @throws ServiceLocatorException
	 */
	public static ServiceLocator getInstance() throws ServiceLocatorException {
		if (instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}



	/**
	 * Recupera uma Implementação da interface DataSource.
	 * 
	 * @param jndiName
	 *            String para efetuar a consulta na érvore JNDI
	 * @return
	 * @throws ServiceLocatorException
	 */
	public DataSource getDataSource(String jndiName)
			throws ServiceLocatorException {
		DataSource ds = null;
		try {
			ds = EJBHomeFactory.getFactory().lookupDataSource(jndiName);
		} catch (NamingException exc) {
			exc.printStackTrace();
			throw new ServiceLocatorException(exc);
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new ServiceLocatorException(exc);
		}
		return ds;

	}

}
