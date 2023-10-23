/**
 * 
 */
package com.ifs.infra;

import java.io.File; 
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.ConfigHelper;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.LoggerFactory;

import com.actimize.infrastructure.plugin.api.Context;
import com.ifs.forms.dao.ConnectionManager;
import com.thoughtworks.xstream.XStream;
import com.ifs.forms.dao.Form;
/**
 * @author AWAK001
 *
 */
public class AppConfig {
	static org.slf4j.Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	static XStream xStream;
	static Context context;
	

	/**
	 * 
	 */
	public AppConfig() {
	}

	/**
	 * @param ctx
	 */
	public AppConfig(Context pluginContext) {
		context = pluginContext;
	}
	
	/**
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactoryObj == null)
			buildSessionFactory();
		return sessionFactoryObj;
	}

	public Connection getConnectionTest() throws SQLException {
		return getSessionFactory().getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class)
				.getConnection();
	}
	
//	public Connection getConnection() throws SQLException {
//		return getSessionFactory().getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class)
//				.getConnection();
//	}
//
//	public Session getSession() throws SQLException {
//		return getSessionFactory().openSession();
//	}
	
	// BALAJI GET BACK TO THIS
	
	
//	/**
//	 * @return
//	 * @throws SQLException
//	 */
	public Connection getConnection() throws SQLException {
		Connection connection = context.getCommonServices().getACMDataSource().getConnection();
		//Connection connection = context.getCommonServices().getDataSource("IMPL_APP").getConnection();
		return connection;
	}
//	
	/**
	 * @return
	 * @throws SQLException
	 */
	public Session getSession() throws SQLException {
		Session session = getSessionFactory().withOptions().connection(this.getConnection()).openSession();
		return session;
	}
	
	private static SessionFactoryImplementor getFactory() {
		buildSessionFactory();
		return (SessionFactoryImplementor) sessionFactoryObj;
		//SpringUtil.lookupComponent("sessionFactory");
	}
	
	private static EntityPersister getClassPersister(Class type, boolean throwEx) {
		SessionFactoryImplementor factory = getFactory();

		try {
			return factory.getEntityPersister(type.getName());
		} catch (MappingException var4) {
			if (throwEx) {
				throw new RuntimeException("type " + type.getName() + " is not mapped", var4);
			} else {
				return null;
			}
		}
	}

	public static IdentifierGenerator getClassIDGenerator(Class type) {
		EntityPersister persister = getClassPersister(type, true);

		try {
			return persister.getIdentifierGenerator();
		} catch (HibernateException var3) {
			throw new RuntimeException("cant find generator for type " + type.getName(), var3);
		}
	}

	public static ClassMetadata getClassMetadata(Class type) {
		SessionFactoryImplementor factory = getFactory();

		try {
			return factory.getClassMetadata(type);
		} catch (HibernateException var3) {
			throw new RuntimeException("cant get class MD", var3);
		}
	}
	
	public static void releaseDBObjects(Connection con, Statement stmt, ResultSet resultSet) {
		safeClose(resultSet);
		safeClose(stmt);
		safeClose(con);
	}
	
	public static void safeClose(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception var2) {
				logger.info("Error releasing db objects [" + statement.getClass().getSimpleName() + "]: "
						+ var2.toString());
			}
		}

	}

	public static void safeClose(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception var2) {
				logger.info("Error releasing db objects [Connection]: " + var2.toString());
			}
		}

	}

	public static void safeClose(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception var2) {
				logger.info("Error releasing db objects [ResultSet]: " + var2.toString());
			}
		}

	}	
	
	/**
	 * 
	 */
	private static void buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		try {
		logger.info("Start of buildSessionFactory()");
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");		

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		logger.info("End of buildSessionFactory()");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
//	private static void buildSessionFactory() {
//		// Creating Configuration Instance & Passing Hibernate Configuration File
//		Configuration configObj = new Configuration();
//		logger.debug("BBuildSessionFactory NEW  -----> ");
//		try {
//			Path currentRelativePath = Paths.get("");
//			String s = currentRelativePath.toAbsolutePath().toString();
//			logger.debug("--->Current relative path is: " + s);
//			InputStream input1 = AppConfig.class.getClassLoader().getResourceAsStream("config.properties");
//			logger.debug("MAIN INPUT");
//			logger.debug("INPUT STREAM 1 "+input1.available());
//			InputStream input2 = AppConfig.class.getClassLoader().getResourceAsStream("hibernate.cfg.xml");
//			logger.debug("INPUT STREAM 2 "+input2.available());
//			
//			logger.debug("INPUT STREAM 3");
//			configObj.configure(AppConfig.class.getClassLoader().getResource("hibernate.cfg.xml"));
//			logger.debug("INPUT STREAM 31");
//			AppConfig.class.getClassLoader().loadClass("com.ifs.forms.dao.Form");
//			logger.debug("INPUT STREAM 32");
//			configObj.addClass(AppConfig.class.getClassLoader().loadClass("com.ifs.forms.dao.Form"));
//			logger.debug("INPUT STREAM 4");
//			
//			
//		} catch (Exception e) {
//			logger.debug("Exception error");
//			logger.debug(e.getMessage());
//		}
//		logger.debug("Built the session factory");
//		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
//		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
//				.applySettings(configObj.getProperties()).build();
//
//		// Creating Hibernate SessionFactory Instance
//		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
//	}
	
	public static DataSource getDataSource() {
		DataSource dataSource = null;
		return dataSource;
	}

	/**
	 * @return
	 */
	public static XStream getXStream() {
		logger.debug("getXStream()-->"+xStream);
		if (xStream == null)
			initializeXStream();
		return xStream;
	}

	/**
	 * 
	 */
	private static void initializeXStream() {
		logger.info("Start of initializeXStream()-->"+xStream);
		try {
		xStream = new XStream();
		xStream.setMode(XStream.NO_REFERENCES);
		//xStream.ignoreUnknownElements();
		// xStream.processAnnotations(Field.class);
		xStream.autodetectAnnotations(true);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
 		logger.info("End of initializeXStream()-->"+xStream);
	}
}
