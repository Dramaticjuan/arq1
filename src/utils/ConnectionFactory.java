package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static final String DERBY = "derby";
	public static final String MYSQL = "mysql";
	
	private static final String DERBY_URI = "jdbc:derby:DBArqJDBC1;create=true";
	private static final String DERBY_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

	private static final String MYSQL_URI = "jdbc:mysql://localhost:3307/arq_db_1";
	private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PASS = "root";

	private static ConnectionFactory instance = new ConnectionFactory();
	private Connection connection;

	private ConnectionFactory() {
	}

	public static ConnectionFactory getInstance() {
		return instance;
	}

	public Connection connect(String type) {
		
		if (this.connection != null) {
			this.disconnect();
		}
		
		if (type.equals(DERBY)) {
			try {
				Class.forName(DERBY_DRIVER);
				this.connection = DriverManager.getConnection(DERBY_URI);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (type.equals(MYSQL)) {
			try {
				Class.forName(MYSQL_DRIVER);
				this.connection = DriverManager.getConnection(MYSQL_URI, MYSQL_USER, MYSQL_PASS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return this.connection;
	}

	public Connection connection() {
		return this.connection;
	}

	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}
