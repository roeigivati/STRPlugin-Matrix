package com.ifs.infra;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

public class AppConfigTest extends AppConfig {

	public AppConfigTest() {
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getSessionFactory().getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class)
				.getConnection();
	}

	@Override
	public Session getSession() throws SQLException {
		return getSessionFactory().openSession();
	}

}
