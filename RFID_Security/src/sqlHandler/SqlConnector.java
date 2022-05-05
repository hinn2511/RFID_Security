package sqlHandler;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;

public class SqlConnector {
	String dbURL = "jdbc:mysql://localhost:3306/rfid";
	String username = "root";
	String password = "hien2511";
	public static Connection connection;
	
	public static Connection getConnection() {
		return connection;
	}

	public SqlConnector() {}

	public void connect() {
		try
		{
			connection = DriverManager.getConnection(dbURL, username, password);

			if (connection != null) {
				System.out.println("Connected");
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void close() {
		if(connection != null)
			try {
				connection.close();
				System.out.println("Disconnected");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
