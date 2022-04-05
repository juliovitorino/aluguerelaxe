package br.com.jcv.backend.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.jcv.backend.constantes.Constantes;

/**
 * Classe para carregar os parémetros iniciais do Sigem.
 *
 * @author Júlio Vitorino
 * @version 1.0
 *
 */
public final class GlobalStartup extends DefaultHandler {

	public static final Logger logger = Logger.getLogger(GlobalStartup.class.getName());
	/**
	 * Atributo '<code>DEFAULT_TIMEOUT</code>' do tipo int
	 */
	private static final int DEFAULT_TIMEOUT = 30;
	
	public static final String ZONA_INTRANET = "INTRANET_ZONE";
	public static final String ZONA_DML = "DML_ZONE";

	/**
	 * Atributo '<code>instance</code>' do tipo GlobalStartup
	 */
	private static volatile GlobalStartup instance = null;

	/**
	 * Atributo '<code>tempval</code>' do tipo String
	 */
	private String tempval;

	/**
	 * Atributo '<code>activeDAOFactory</code>' do tipo String
	 */
	private String activeDAOFactory;

	/**
	 * Atributo '<code>database</code>' do tipo int
	 */
	private int database;

	/**
	 * Atributo '<code>transactionTimeout</code>' do tipo int
	 */
	private int transactionTimeout;

	/**
	 * Atributo '<code>lookupHostname</code>' do tipo String
	 */
	private String lookupHostname;

	/**
	 * Atributo '<code>oracleDS</code>' do tipo String
	 */
	private String oracleDS;

	/**
	 * Atributo '<code>interbaseDS</code>' do tipo String
	 */
	private String interbaseDS;

	/**
	 * Atributo '<code>sqlServerDS</code>' do tipo String
	 */
	private String sqlServerDS;

	/**
	 * Atributo '<code>firebirdDS</code>' do tipo String
	 */
	private String firebirdDS;

	/**
	 * Atributo '<code>postgresqlDS</code>' do tipo String
	 */
	private String postgresqlDS;

	/**
	 * Atributo '<code>db2DS</code>' do tipo String
	 */
	private String db2DS;

	/**
	 * Atributo '<code>mysqlDS</code>' do tipo String
	 */
	private String mysqlDS;

	/**
	 * Atributo '<code>informixDS</code>' do tipo String
	 */
	private String informixDS;

	/**
	 * Atributo '<code>activeApplicationServer</code>' do tipo String
	 */
	private String activeApplicationServer;

	/**
	 * Atributo '<code>AS</code>' do tipo List<ApplicationServer>
	 */
	private List<ApplicationServer> AS;

	/**
	 * Atributo '<code>pathOfCertification</code>' do tipo String
	 */
	private String pathOfCertification;

	/**
	 * Atributo '<code>pathOfRevokeCerification</code>' do tipo String
	 */
	private String pathOfRevokeCerification;

	/**
	 * Atributo '<code>properties</code>' do tipo Properties
	 */
	private Properties properties = new Properties();

	/**
	 * Atributo '<code>pathLanguageResources</code>' do tipo String
	 */
	private String pathLanguageResources;
	
	/**
	 * Atributo '<code>zone</code>' do tipo String
	 */
	private String zone;

	/**
	 * Atributo '<code>astemp</code>' do tipo ApplicationServer
	 */
	private ApplicationServer astemp;
	
	/**
	 * Atributo '<code>servidoresAutorizadosAutovue</code>' do tipo String
	 */
	private String servidoresAutorizadosAutovue;
	
	private HashMap<String, String> fixedValues = new HashMap<String, String>();

	/**
	 * Classe para controle dos servidores de aplicação informados no arquivo de startup
	 *
	 * @author Júlio Vitorino
	 * @version 1.0
	 *
	 */
	public class ApplicationServer {
		/**
		 * Atributo '<code>name</code>' do tipo String
		 */
		private String name;

		/**
		 * Atributo '<code>lookupLocalContextForLocalEJB</code>' do tipo boolean
		 */
		private boolean lookupLocalContextForLocalEJB;

		/**
		 * Atributo '<code>initialContextFactory</code>' do tipo String
		 */
		private String initialContextFactory = "";

		/**
		 * Atributo '<code>providerUrl</code>' do tipo String
		 */
		private String providerUrl = "";

		/**
		 * Atributo '<code>urlPkgPrefixes</code>' do tipo String
		 */
		private String urlPkgPrefixes = "";

		/**
		 * Construtor para esta classe.
		 *
		 */
		public ApplicationServer() {
		}

		/**
		 * Inicializa o Contexto
		 *
		 * @param initialContextFactory Inicializa o Contexto
		 */
		public final void setInitialContextFactory(
				final String initialContextFactory) {
			this.initialContextFactory = initialContextFactory;
		}

		/**
		 * Configurar o nome do servidor
		 *
		 * @param name Nome do servidor a configurar
		 */
		public final void setName(final String name) {
			this.name = name;
		}

		/**
		 * Configurar o provedor URL
		 *
		 * @param providerUrl Nome do provedor URL a configurar
		 */
		public final void setProviderUrl(final String providerUrl) {
			this.providerUrl = providerUrl;
		}

		/**
		 * Configurar URL_PKG_PREFIXES
		 *
		 * @param urlPkgPrefixes URL_PKG_PREFIXES a ser configurado de acordo com Servidor
		 * de aplicação
		 */
		public final void setUrlPkgPrefixes(final String urlPkgPrefixes) {
			this.urlPkgPrefixes = urlPkgPrefixes;
		}

		/**
		 * Retornar o contexto
		 *
		 * @return String do Contexto
		 */
		public final String getInitialContextFactory() {
			return this.initialContextFactory;
		}

		/**
		 * Retornar o nome do servidor de aplicação
		 *
		 * @return String com nome do servidor de aplicação
		 */
		public final String getName() {
			return this.name;
		}

		/**
		 * Retornar o Provider URL
		 *
		 * @return String com Provider URL
		 */
		public final String getProviderUrl() {
			System.out.println("ProviderURL -->" + this.providerUrl);
			return this.providerUrl;
		}

		/**
		 * Retornar URL_PKG_PREFIXES
		 *
		 * @return String com nome do URL_PKG_PREFIXES
		 */
		public final String getUrlPkgPrefixes() {
			return this.urlPkgPrefixes;
		}

		/**
		 * Retornar se o contexto de lookup é local
		 *
		 * @return Verdadeiro ou falso para contexto de lookup local
		 */
		public final boolean isLookupLocalContextForLocalEJB() {
			return this.lookupLocalContextForLocalEJB;
		}

		/**
		 * Configurar o lookup para contexto local
		 *
		 * @param lookupLocalContextForLocalEJB Define se a pesquisa de contexto é local
		 */
		public final void setLookupLocalContextForLocalEJB(
				final boolean lookupLocalContextForLocalEJB) {
			this.lookupLocalContextForLocalEJB = lookupLocalContextForLocalEJB;
		}

	}

	/**
	 * Construtor para esta classe.
	 * @throws Exception 
	 *
	 * @throws Exception lanéar Exception
	 */
	private GlobalStartup()   {
		this("startup.xml");
	}

	/**
	 * Construtor para esta classe.
	 *
	 * @param startupFile Nome do arquivo de startup
	 * @throws Exception Lanéar exceééo
	 */
	private GlobalStartup(final String startupFile) {
		AS = new ArrayList<ApplicationServer>();
		try {
			this.parseDocument(startupFile);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	/**
	 * Obter a instância da classe GlobalStartup
	 *
	 * @return Retornar objeto GlobalStartup
	 * @throws Exception Lanéa Exception
	 */
	public static GlobalStartup getInstance()  {
		if (instance == null) {
			synchronized (GlobalStartup.class) {
				if (instance == null) {
					logger.info("Inicializando GlobalStartup...");
					instance = new GlobalStartup();
					logger.info("  Application Server ativo:" + instance.getActiveApplicationServer());
					logger.info("         DAO Factory ativa:" + instance.getActiveDAOFactory());
					logger.info("           JNDI Datasource:" + instance.getJndiDataSource());
					logger.info("       DB2 JNDI Datasource:" + instance.getDb2DS());
					logger.info("  Firebird JNDI Datasource:" + instance.getFirebirdDS());
					logger.info("  Informix JNDI Datasource:" + instance.getInformixDS());
					logger.info(" Interbase JNDI Datasource:" + instance.getInterbaseDS());
					logger.info("     MySQL JNDI Datasource:" + instance.getMysqlDS());
					logger.info("    Oracel JNDI Datasource:" + instance.getOracleDS());
					logger.info("PostgreSQL JNDI Datasource:" + instance.getPostgresqlDS());
				}
			}
		}
		return instance;
	}

	/**
	 * Obter a instância da classe GlobalStartup
	 *
	 * @param startupFile Nome do arquivo para configuraééo de startup
	 * @return Retornar objeto GlobalStartup
	 * @throws Exception Comentar aqui.
	 */
	public static GlobalStartup getInstance(final String startupFile)
			throws Exception {
		if (instance == null) {
			instance = new GlobalStartup(startupFile);
		}
		return instance;
	}

	/**
	 * Executar o parse do documento XML de startup
	 *
	 * @param startupFile Nome do arquivo para configuraééo de startup
	 * @throws Exception Lanéar Exception
	 */
	private void parseDocument(final String startupFile) throws Exception {

		//Obtem uma factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {

			//Obtem uma nova instancia do parser
			SAXParser sp = spf.newSAXParser();

			InputStream is = this.getClass().getResourceAsStream(startupFile);

			//Executa o parse do arquivo e o registra classe para os Métodos de callback
			sp.parse(is, this);
			
		} catch (SAXException se) {
			logger.error(se.getMessage(),se);
			throw se;
		} catch (ParserConfigurationException pce) {
			logger.error(pce.getMessage(),pce);
			throw pce;
		} catch (IOException ie) {
			File curdir = new File(".");
			System.out.println("Arquivo '" + startupFile
					+ "' néo encontrado no diretério "
					+ curdir.getCanonicalPath());
			throw ie;
		}
	}

	/**
	 * Método sobrescrito.
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(
			final String uri,
			final String localName,
			final String qName,
			final Attributes attributes) throws SAXException {

		//reset
		this.tempval = "";

		// verifica tags
		if (qName.equalsIgnoreCase("ApplicationServers")) {
			this.setActiveApplicationServer(attributes.getValue("Active"));
		}
		if (qName.equalsIgnoreCase("Property")) {
			this.properties.put(attributes.getValue("name"), attributes
					.getValue("value"));
		}
		if (qName.equalsIgnoreCase("AS")) {
			this.astemp = new ApplicationServer();
			this.astemp.setName(attributes.getValue("name"));
			this.astemp.setLookupLocalContextForLocalEJB((attributes.getValue(
					"lookupLocalContextForLocalEJB").toLowerCase().equals(
					"true") ? true : false));
		}
		if (qName.equalsIgnoreCase("Local")) {
			this.fixedValues.put(attributes.getValue("name"), attributes.getValue("value"));
		}
	}

	/**
	 * Método sobrescrito.
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(final char[] ch, final int start, final int length)
			throws SAXException {
		this.tempval = alterarTags(new String(ch, start, length));
	}

	/**
	 * TODO Comentar aqui.
	 *
	 * @param str
	 * @return Comentar aqui.
	 */
	@SuppressWarnings("deprecation")
	private final String alterarTags(final String str) {
		String retorno = str;

		if (str.indexOf("${") == -1) {
			return str;
		}
		Enumeration e = this.properties.keys();
		for (; e.hasMoreElements();) {
			String propNome = (String) e.nextElement();
			String propValue = (String) this.properties.get(propNome);
			retorno = StringUtil.replaceString(
					retorno,
					"${" + propNome + "}",
					propValue);
		}
		return this.alterarTags(retorno);
	}

	/**
	 * Método sobrescrito.
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(
			final String uri,
			final String localName,
			final String qName) throws SAXException {
		if (qName.equalsIgnoreCase("ActiveDAOFactory")) {
			this.setActiveDAOFactory(this.tempval);

			// Reconfigura database para uso dentro da DAOFactory
			if (tempval.toUpperCase().equals(Constantes.DATABASE_NAME_DB2))
				database = Constantes.DATABASE_DB2;
			if (tempval.toUpperCase().equals(Constantes.DATABASE_NAME_FIREBIRD))
				database = Constantes.DATABASE_FIREBIRD;
			if (tempval.toUpperCase().equals(Constantes.DATABASE_NAME_INFORMIX))
				database = Constantes.DATABASE_INFORMIX;
			if (tempval.toUpperCase()
					.equals(Constantes.DATABASE_NAME_INTERBASE))
				database = Constantes.DATABASE_INTERBASE;
			if (tempval.toUpperCase().equals(Constantes.DATABASE_NAME_MYSQL))
				database = Constantes.DATABASE_MYSQL;
			if (tempval.toUpperCase().equals(Constantes.DATABASE_NAME_ORACLE))
				database = Constantes.DATABASE_ORACLE;
			if (tempval.toUpperCase().equals(
					Constantes.DATABASE_NAME_POSTGRESQL))
				database = Constantes.DATABASE_POSTGRESQL;
			if (tempval.toUpperCase()
					.equals(Constantes.DATABASE_NAME_SQLSERVER))
				database = Constantes.DATABASE_SQLSERVER;

		} else if (qName.equalsIgnoreCase("PathOfCertification"))
			this.setPathOfCertification(tempval);
		else if (qName.equalsIgnoreCase("PathLanguageResources")) {
			this.setPathLanguageResources(this.tempval);
		}
		else if (qName.equalsIgnoreCase("PathOfRevokeCerification"))
			this.setPathOfRevokeCerification(tempval);
		else if (qName.equalsIgnoreCase("AuthorizedAutovueServers"))
			this.setServidoresAutorizadosAutovue(tempval);
		else if (qName.equalsIgnoreCase("TransactionTimeout")) {
			try {
				this.setTransactionTimeout(Integer.parseInt(tempval));
			} catch (Exception e) {
				this.setTransactionTimeout(DEFAULT_TIMEOUT);
			}
		} else if (qName.equalsIgnoreCase("LookupHostname"))
			this.setLookupHostname(tempval);
		else if (qName.equalsIgnoreCase("Zone"))
			this.setZone(tempval);
		else if (qName.equalsIgnoreCase("OracleDataSource"))
			this.setOracleDS(tempval);
		else if (qName.equalsIgnoreCase("InterbaseDataSource"))
			this.setInterbaseDS(tempval);
		else if (qName.equalsIgnoreCase("SQLServerDataSource"))
			this.setSqlServerDS(tempval);
		else if (qName.equalsIgnoreCase("FirebirdDataSource"))
			this.setFirebirdDS(tempval);
		else if (qName.equalsIgnoreCase("PostgresqlDataSource"))
			this.setPostgresqlDS(tempval);
		else if (qName.equalsIgnoreCase("DB2DataSource"))
			this.setDb2DS(tempval);
		else if (qName.equalsIgnoreCase("MysqlDataSource"))
			this.setMysqlDS(tempval);
		else if (qName.equalsIgnoreCase("InformixDataSource"))
			this.setInformixDS(tempval);
		else if (qName.equalsIgnoreCase("InitialContextFactory"))
			astemp.setInitialContextFactory(tempval);
		else if (qName.equalsIgnoreCase("ProviderUrl")) {
			//System.out.println(tempval);
			astemp.setProviderUrl(tempval);
		}
		else if (qName.equalsIgnoreCase("UrlPkgPrefixes"))
			astemp.setUrlPkgPrefixes(tempval);
		else if (qName.equalsIgnoreCase("AS"))
			this.AS.add(astemp);
	}

	/**
	 * @return int Returns the database.
	 */
	public int getDatabase() {
		return database;
	}
	
	public String getFixedValue(String key) {
		String valor = null;
		
		if (key != null && key.length() > 0 ) {
			valor = (String) fixedValues.get(key);
		}
		return valor;
	}

	/**
	 * @return String
	 */
	public String getJndiDataSource() {
		if (database == Constantes.DATABASE_DB2)
			return this.getDb2DS();
		if (database == Constantes.DATABASE_FIREBIRD)
			return this.getFirebirdDS();
		if (database == Constantes.DATABASE_INFORMIX)
			return this.getInformixDS();
		if (database == Constantes.DATABASE_INTERBASE)
			return this.getInterbaseDS();
		if (database == Constantes.DATABASE_MYSQL)
			return this.getMysqlDS();
		if (database == Constantes.DATABASE_ORACLE)
			return this.getOracleDS();
		if (database == Constantes.DATABASE_POSTGRESQL)
			return this.getPostgresqlDS();
		if (database == Constantes.DATABASE_SQLSERVER)
			return this.getSqlServerDS();
		return null;
	}

	/**
	 * @return String Returns the activeApplicationServer.
	 */
	public String getActiveApplicationServer() {
		return activeApplicationServer;
	}

	/**
	 * @return String Returns the activeDAOFactory.
	 */
	public String getActiveDAOFactory() {
		return activeDAOFactory;
	}

	/**
	 * @return String Returns the db2DS.
	 */
	public String getDb2DS() {
		return db2DS;
	}

	/**
	 * @return String Returns the firebirdDS.
	 */
	public String getFirebirdDS() {
		return firebirdDS;
	}

	/**
	 * @return String Returns the informixDS.
	 */
	public String getInformixDS() {
		return informixDS;
	}

	/**
	 * @return String Returns the interbaseDS.
	 */
	public String getInterbaseDS() {
		return interbaseDS;
	}

	/**
	 * @return String Returns the mysqlDS.
	 */
	public String getMysqlDS() {
		return mysqlDS;
	}

	/**
	 * @return string Returns the oracleDS.
	 */
	public String getOracleDS() {
		return oracleDS;
	}

	/**
	 * @return String Returns the postgresqlDS.
	 */
	public String getPostgresqlDS() {
		return postgresqlDS;
	}

	/**
	 * @return String Returns the sqlServerDS.
	 */
	public String getSqlServerDS() {
		return sqlServerDS;
	}

	/**
	 * @param activeApplicationServer O activeApplicationServer Ativo.
	 */
	public void setActiveApplicationServer(String activeApplicationServer) {
		this.activeApplicationServer = activeApplicationServer;
	}

	/**
	 * @param activeDAOFactory A DAO Factory Ativa.
	 */
	public void setActiveDAOFactory(String activeDAOFactory) {
		this.activeDAOFactory = activeDAOFactory;
	}

	/**
	 * @param db2DS The db2DS to set.
	 */
	public void setDb2DS(String db2DS) {
		this.db2DS = db2DS;
	}

	/**
	 * @param firebirdDS The firebirdDS to set.
	 */
	public void setFirebirdDS(String firebirdDS) {
		this.firebirdDS = firebirdDS;
	}

	/**
	 * @param informixDS The informixDS to set.
	 */
	public void setInformixDS(String informixDS) {
		this.informixDS = informixDS;
	}

	/**
	 * @param interbaseDS The interbaseDS to set.
	 */
	public void setInterbaseDS(String interbaseDS) {
		this.interbaseDS = interbaseDS;
	}

	/**
	 * @param mysqlDS The mysqlDS to set.
	 */
	public void setMysqlDS(String mysqlDS) {
		this.mysqlDS = mysqlDS;
	}

	/**
	 * @param oracleDS The oracleDS to set.
	 */
	public void setOracleDS(String oracleDS) {
		this.oracleDS = oracleDS;
	}

	/**
	 * @param postgresqlDS The postgresqlDS to set.
	 */
	public void setPostgresqlDS(String postgresqlDS) {
		this.postgresqlDS = postgresqlDS;
	}

	/**
	 * @param sqlServerDS The sqlServerDS to set.
	 */
	public void setSqlServerDS(String sqlServerDS) {
		this.sqlServerDS = sqlServerDS;
	}

	/**
	 * @return Returns the aS.
	 */
	public List getAS() {
		return AS;
	}

	private ApplicationServer getInfoActiveAS() {
		for (int i = 0; i < this.AS.size(); i++) {
			ApplicationServer as = (ApplicationServer) this.AS.get(i);
			if (as.getName().equals(this.getActiveApplicationServer()))
				return as;
		}
		return null;

	}

	/**
	 * @return Returns the initialContextFactory.
	 */
	public String getInitialContextFactory() {
		ApplicationServer as = getInfoActiveAS();
		if (as != null) {
			return as.getInitialContextFactory();
		}
		return null;
	}

	/**
	 * @return Returns the providerUrl.
	 */
	public String getProviderUrl() {
		ApplicationServer as = getInfoActiveAS();
		if (as != null) {
			return as.getProviderUrl();
		}
		return null;
	}

	/**
	 * @return Returns the urlPkgPrefixes.
	 */
	public String getUrlPkgPrefixes() {
		ApplicationServer as = getInfoActiveAS();
		if (as != null) {
			return as.getUrlPkgPrefixes();
		}
		return null;
	}

	/**
	 * @return Returns the lookupLocalContextForLocalEJB.
	 */
	public boolean isLookupLocalContextForLocalEJB() {
		ApplicationServer as = getInfoActiveAS();
		if (as != null) {
			return as.isLookupLocalContextForLocalEJB();
		}
		return false;
	}

	public String getPathOfCertification() {
		return pathOfCertification;
	}

	public void setPathOfCertification(String pathOfCertification) {
		this.pathOfCertification = pathOfCertification;
	}

	public String getPathOfRevokeCerification() {
		return pathOfRevokeCerification;
	}

	public void setPathOfRevokeCerification(String pathOfRevokeCerification) {
		this.pathOfRevokeCerification = pathOfRevokeCerification;
	}

	public int getTransactionTimeout() {
		return transactionTimeout;
	}

	public void setTransactionTimeout(int transactionTimeout) {
		this.transactionTimeout = transactionTimeout;
	}

	public String getLookupHostname() {
		return lookupHostname;
	}

	public void setLookupHostname(String lookupHostname) {
		this.lookupHostname = lookupHostname;
	}

	/**
	 * Este Método é responsével por retornar o
	 * conteúdo de 'pathLanguageResources'.
	 * @return Retorna um objeto do tipo String
	 */
	public String getPathLanguageResources() {
		return pathLanguageResources;
	}

	/**
	 * Este Método é responsével por configurar
	 * o valor de 'pathLanguageResources', que é um objeto do tipo String.
	 * @param pathLanguageResources O valor de 'pathLanguageResources' a ser configurado.
	 */
	public void setPathLanguageResources(String pathLanguageResources) {
		this.pathLanguageResources = pathLanguageResources;
	}

	/**
	 * Este Método é responsével por retornar o
	 * conteúdo de 'zone'.
	 * @return Retorna um objeto do tipo String
	 */
	public final String getZone() {
		return this.zone;
	}

	/**
	 * Este Método é responsével por configurar
	 * o valor de 'zone', que é um objeto do tipo String.
	 * @param zone O valor de 'zone' a ser configurado.
	 */
	public final void setZone(String zone) {
		this.zone = zone;
	}
	
	/**
	 * Retora o valor do atributo dominiosAutorizados
	 *
	 * @return Caso ocorra algum erro na operação
	 */
	public final String getServidoresAutorizadosAutovue() {
		return this.servidoresAutorizadosAutovue;
	}
	
	/**
	 * Altera o valor do atributo dominiosAutorizados
	 *
	 * @param value Caso ocorra algum erro na operação
	 */
	public final void setServidoresAutorizadosAutovue(String value) {
		this.servidoresAutorizadosAutovue = value;
	}
}
