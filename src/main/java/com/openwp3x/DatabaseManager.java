package com.openwp3x;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

	private static Connection connection = null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		if(connection==null){
			
			connection = DriverManager.getConnection("jdbc:mysql://192.168.0.10:3306/jlleinfo","root", "root");
		}
		return connection;
	}

}
