package dao;

import java.sql.Connection;

import dao.Interfaz.abstractDAO;

public abstract class DAOFactory {
	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;
	public static final int JPA_HIBERNATE = 3;
	
	public abstract abstractDAO getClienteDAO();
	public abstract abstractDAO getFacturaDAO();
	public abstract abstractDAO getFacturaProductoDAO();
	public abstract abstractDAO getProductoDAO();

	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL_JDBC:
			return new MySqlDAOFactory();
		case DERBY_JDBC:
			return new DerbyDAOFactory();
		default:
			return null;
		}
	}
}
