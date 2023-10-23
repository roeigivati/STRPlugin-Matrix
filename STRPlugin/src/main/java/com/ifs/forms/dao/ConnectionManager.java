package com.ifs.forms.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.actimize.infrastructure.plugin.api.Context;
import com.actimize.infrastructure.plugin.api.exceptions.PluginException;

public class ConnectionManager {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
    /**
     * Gets the database connection.
     *
     * @return Connection the database connection.
     */
	private String actoneImplSchema ="";
	
	private Context context;
	
	public ConnectionManager(Context con) {
		this.context = con;
		Properties prop = new Properties();
		try {
//			InputStream input = ConnectionManager.class.getClassLoader().getResourceAsStream("config.properties");
//			prop.load(input);
			 //get the property value
			actoneImplSchema = "AML_STAR_CDS";//prop.getProperty("actone_impl_schema");	
		} catch (PluginException e) {
			e.printStackTrace();
		} /*catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}*/
	}

	public Connection getIMPLConnection() {
		Connection conn = null;
		try {
			conn = this.context.getCommonServices().getDataSource(actoneImplSchema).getConnection();
		} catch (PluginException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
}