package dao;

import dao.Interfaz.abstractDAO;
import dao.Derby.*;

public class DerbyDAOFactory extends DAOFactory {
	public DerbyDAOFactory() {}

	@Override
	public abstractDAO getClienteDAO() {
		return clienteDerbyDAO.getInstance();
	}

	@Override
	public abstractDAO getFacturaDAO() {
		return facturaDerbyDAO.getInstance();
	}

	@Override
	public abstractDAO getFacturaProductoDAO() {
		return facturaProductoDerbyDAO.getInstance();
	}

	public abstractDAO getProductoDAO() {
		return productoDerbyDAO.getInstance();
	}

}
