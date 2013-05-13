package com.openwp3x.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseManager {

	private static Connection connection = null;
	
	public static Connection getConnection() throws Exception {
		if(connection==null || connection.isClosed()){
			InputStream propertiesStream = DatabaseManager.class.getClassLoader().getResourceAsStream("db.properties");
			if(propertiesStream==null){
				throw new IllegalArgumentException("Invalid database properties file");
			}
			Properties properties = new Properties();
			properties.load(propertiesStream);
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"), properties.getProperty("password"));
		}
		return connection;
	}

}
