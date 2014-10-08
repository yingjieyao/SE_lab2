package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbUtils {
	private static DbUtils instance = null;
	private Connection connection;
	private Statement statement;
	private ResultSet resultset;
	private DbPool pool;
	private DbUtils() {
	}

	public static DbUtils getInstance() {
		if (instance == null)
			instance = new DbUtils();
		return instance;
	}

	public ResultSet Query(String sql) {
		resultset = null;
		connection = getConnection();
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultset;
	}

	public int delete(String sql) {
		int result = 0;
		connection = getConnection();
		try {
			statement = connection.createStatement();
			result = statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	private void close() {
		if (statement != null) {
			try {
				statement.close();
				statement = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			statement = null;
		}
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}

	private Connection getConnection() {
		DbPool db = new DbPool();
		db.getConn();
		Connection connt = db.getConne();
		return connt;
	}
}
