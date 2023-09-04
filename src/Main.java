
import dao.DAOFactory;
import dao.Interfaz.abstractDAO;
import dao.Interfaz.punto3;
import dao.Interfaz.punto4;
import models.Cliente;
import models.Factura;
import models.FacturaProducto;
import models.Producto;
import utils.Pair;

public class Main {

	public static void main(String[] args) throws Exception {

		DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
		
		abstractDAO<Producto, Integer> productoDAO = f.getProductoDAO();
		abstractDAO<Cliente, Integer> clienteDAO = f.getClienteDAO();
		abstractDAO<Factura, Integer> facturaDAO = f.getFacturaDAO();
		abstractDAO<FacturaProducto, Pair<Integer, Integer>> facturaproductoDAO = f.getFacturaProductoDAO();
		
		((punto4) clienteDAO).readAllSortByMostBilled().forEach(e->System.out.println(e));
		
		System.out.println(((punto3) productoDAO).highestIncome().get());
		
		/*
		productoDAO.createTable();
		clienteDAO.createTable();
		facturaDAO.createTable();
		facturaproductoDAO.createTable();
		*/
		/*
		DataGenerator.cargarDatos(clienteDAO, DataGenerator.CLIENTE);
		DataGenerator.cargarDatos(productoDAO, DataGenerator.PRODUCTO);
		DataGenerator.cargarDatos(facturaDAO, DataGenerator.FACTURA);
		DataGenerator.cargarDatos(facturaproductoDAO, DataGenerator.FACTURAPRODUCTO);
		*/
		
		//productoDAO.readAll().forEach(e->System.out.println(e));
		//clienteDAO.readAll().forEach(e->System.out.println(e));
		//facturaDAO.readAll().forEach(e->System.out.println(e));
		//facturaproductoDAO.readAll().forEach(e->System.out.println(e));

		/*
		facturaproductoDAO.delete(new Pair(1, 1));
		facturaproductoDAO.save(new FacturaProducto(1,1,1));
		facturaproductoDAO.update(new FacturaProducto(1,1, 3));
		System.out.println(facturaproductoDAO.read(new Pair(1,1)).get());

		productoDAO.save(new Producto(101, "vugia hescher", (float)10.2));
		productoDAO.delete(101);
		productoDAO.update(new Producto(101, "vugia hescher che", (float) 11.2));
		System.out.println(productoDAO.read(101).get());

		clienteDAO.save(new Cliente(11, "carolina", "carolina@caro.com"));
		productoDAO.delete(11);
		clienteDAO.update(new Cliente(11, "carilina", "carolina@claro.com"));
		System.out.println(clienteDAO.read(11).get());
		
		facturaDAO.save(new Factura(101, 1));
		facturaDAO.delete(101);
		facturaDAO.update(new Factura(101, 2));
		System.out.println(facturaDAO.read(101).get());
		*/
	}
	
}
