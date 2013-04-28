package com.openwp3x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class URLShortener {

	Logger logger = Logger.getLogger(this.getClass());

	private static final String baseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public void start() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement("select id, source from entry where status = 0");
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Long entryId = resultSet.getLong("id");
				String source = resultSet.getString("source");
				updateLink(entryId, source);
			}
		} catch (Exception e) {
			logger.error(e, e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateLink(Long entryId, String source) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement("update entry set status=1, short_link=? where id = ?");
			preparedStatement.setString(1, converter(entryId));
			preparedStatement.setLong(2, entryId);
			int affectedRows = preparedStatement.executeUpdate();
			logger.info("Shorter links updated: " + affectedRows);
		} catch (Exception e) {			
			logger.error(e, e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static String converter(Long decimalNumber) {
		int base = 62;
		String tempVal = decimalNumber == 0 ? "0" : "";
		long mod = 0;

		while (decimalNumber != 0) {
			mod = decimalNumber % base;
			tempVal = baseDigits.substring((int) mod, (int) mod + 1) + tempVal;
			decimalNumber = decimalNumber / base;
		}
		return tempVal;
	}

	public static void main(String args[]) {
		new URLShortener().start();
	}

}
