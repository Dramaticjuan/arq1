package dao.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.Interfaz.abstractDAO;
import models.Factura;

public class FacturaMySQLDAO extends abstractDAO<Factura, Integer> {

	private static FacturaMySQLDAO instance = new FacturaMySQLDAO();

	private FacturaMySQLDAO() {
		this.db = this.conn.MYSQL;
	}

	public static FacturaMySQLDAO getInstance() {
		return instance;

	}

	@Override
	protected String getCreateTableQuery() {
		return "CREATE TABLE Factura(" + "idFactura INT, " + "idCliente INT,"
				+ "CONSTRAINT idFactura_pk PRIMARY KEY(idFactura),"
				+ "CONSTRAINT idCliente_fk FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente))";
	}

	@Override
	protected String getSaveQuery() {
		return "INSERT INTO Factura(idFactura, idCliente)" + "VALUES(?,?)";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE Factura " + "SET idCliente= ?" + "WHERE idFactura= ?";
	}

	@Override
	protected String getReadByIdQuery() {
		return "SELECT * " + "FROM Factura " + "WHERE idFactura= ?";
	}

	@Override
	protected String getReadAllQuery() {
		return "SELECT * " + "FROM Factura";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM Factura " + "WHERE idFactura= ?";
	}

	@Override
	protected void setIdStatement(PreparedStatement ps, Integer id) throws SQLException {
		ps.setInt(1, id);
	}

	@Override
	protected void setObjectStatement(PreparedStatement ps, Factura f, int option) throws SQLException {
		if (option == this.optionSave) {
			ps.setInt(1, f.getId());
			ps.setInt(2, f.getIdCliente());
		} else if (option == this.optionUpdate) {
			ps.setInt(1, f.getIdCliente());
			ps.setInt(2, f.getId());
		}
	}

	@Override
	protected Factura readObject(ResultSet rs) throws SQLException {
		return new Factura(rs.getInt("idFactura"), rs.getInt("idCliente"));
	}

}
