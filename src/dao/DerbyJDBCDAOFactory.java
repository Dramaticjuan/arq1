package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyJDBCDAOFactory extends DAOFactory {
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String DBURL = "jdbc:derby:exampleDB;create=true";
	private Connection conn;

	public DerbyJDBCDAOFactory() {
		try {
			Class.forName(DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public DAOProducto getDaoProducto() {
		return DerbyDAOProducto.getInstance();
	}
}
