package com.ifs.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class maintains the database connection.
 */
public class ConnectionManager {

    
    /**
     * Gets the database connection.
     *
     * @return Connection the database connection.
     */
    public static Connection getConnection() {
    	Connection con = null;
        try {
        	String connectionUrl = "jdbc:sqlserver://10.61.10.76:1433;databaseName=IMPL;user=actone_admin;password=Random.1234";
        	con = DriverManager.getConnection(connectionUrl); 
        	
        } catch (SQLException sqlException) {
        	sqlException.printStackTrace();
        }
        return con;
    }
}
