
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import dao.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DerbyJDBCDAOFactory f=(DerbyJDBCDAOFactory) DAOFactory.getDAOFactory(DAOFactory.DERBY_JDBC);
		DAOProducto p= f.getDaoProducto();
		p.createTable();
		p.save(new Producto(1, "bicicleta", (float) 1.2));
		p.getAll();
	}

}
