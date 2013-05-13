package com.openwp3x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class LinkShortener implements NuveoJob{

	Logger logger = Logger.getLogger(this.getClass());

	private static final String baseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public void execute() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement("select id, source from entry where status = 0");
			ResultSet resultSet = preparedStatement.executeQuery();

			Integer countLinks = 0;
			while (resultSet.next()) {
				Long entryId = resultSet.getLong("id");
				String source = resultSet.getString("source");
				updateLink(entryId, source);
				countLinks++;
			}
			logger.info("Shorter links updated: " + countLinks);
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
			preparedStatement.executeUpdate();
		} catch (Exception e) {			
			logger.error(e, e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e, e);
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

}
