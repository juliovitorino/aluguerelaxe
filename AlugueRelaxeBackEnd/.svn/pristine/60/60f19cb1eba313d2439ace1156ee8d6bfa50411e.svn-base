package br.com.jcv.backend.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import br.com.jcv.backend.utility.GlobalStartup;

/**
 * Classe para criaééo e manutenééo de caches de lookups. 
 * Evita o alto de custo de criaééo de contexto e pesquisa na JNDI
 * 
 * @author Júlio Cesar Vitorino
 * @version 1.0
 *
 */
public class EJBHomeFactory {
	
	private Map<String,DataSource> datasource;
	private static EJBHomeFactory instance;
	private InitialContext ctx;
	
	/**
	 * Singleton da EJBHomeFactory. Obtém informaééo do XML de startup com as configurações iniciais do Sigem
	 * 
	 * @throws Exception
	 */
	private EJBHomeFactory() throws Exception
	{
		Hashtable<String,String> t = new Hashtable<String,String>();


		GlobalStartup gc = GlobalStartup.getInstance();
    	t.put ( Context.INITIAL_CONTEXT_FACTORY ,gc.getInitialContextFactory() ) ;
    	t.put ( Context.PROVIDER_URL , gc.getProviderUrl() ) ;
    	t.put ( Context.URL_PKG_PREFIXES , gc.getUrlPkgPrefixes() ) ;
		ctx = new InitialContext(t);
		
		datasource = Collections.synchronizedMap(new HashMap<String,DataSource>());
	}
	
	/**
	 * Obtém ou cria a instância da classe EJBHomeFactory
	 * 
	 * @return EJBHomeFactory
	 * @throws Exception
	 */
	public static EJBHomeFactory getFactory() throws Exception
	{
		if (instance == null)
		{
			instance = new EJBHomeFactory();
		}
		return instance;
	}
	

	/**
	 * Busca por uma referéncia do datasource no cache, se néo existir nenhuma referéncia pesquisa na JNDI
	 * e armazena no cache.
	 * 
	 * @param jndiName String JNDI
	 * @return DataSource
	 * @throws NamingException
	 */
	public DataSource lookupDataSource(String jndiName) throws NamingException
	{
		// Procura pela referencia Remote Home no cache
		DataSource ds = (DataSource) datasource.get(jndiName);
		if (ds == null)
		{
			ds = (DataSource) ctx.lookup(jndiName);
			
			//Alimenta o cache de referencia ao Objeto EJBHome
			datasource.put(jndiName,ds);
		}
		return ds;
	}
	
}
