package dao;

import java.sql.Connection;

public abstract class DAOFactory {
	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;
	public static final int JPA_HIBERNATE = 3;

	public abstract DAOProducto getDaoProducto();

	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL_JDBC:
			return new MySqlJDBCDAOFactory();
		case DERBY_JDBC:
			return new DerbyJDBCDAOFactory();
		default:
			return null;
		}
	}
}
