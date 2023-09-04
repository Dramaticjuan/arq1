package dao;

import dao.Interfaz.abstractDAO;
import dao.MySQL.*;

public class MySqlDAOFactory extends DAOFactory {
	public MySqlDAOFactory () {}
	
	@Override
	public abstractDAO getClienteDAO() {
		return ClienteMySQLDAO.getInstance();
	}

	@Override
	public abstractDAO getFacturaDAO() {
		return FacturaMySQLDAO.getInstance();
	}

	@Override
	public abstractDAO getFacturaProductoDAO() {
		return FacturaProductoMySQLDAO.getInstance();
	}

	@Override
	public abstractDAO getProductoDAO() {
		return ProductoMySQLDAO.getInstance();
	}

}
