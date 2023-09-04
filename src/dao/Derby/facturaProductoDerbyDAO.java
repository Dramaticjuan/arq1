package dao.Derby;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.Interfaz.abstractDAO;
import models.FacturaProducto;
import utils.Pair;

public class facturaProductoDerbyDAO extends abstractDAO<FacturaProducto, Pair<Integer, Integer>> {

	private static facturaProductoDerbyDAO instance = new facturaProductoDerbyDAO();

	private facturaProductoDerbyDAO() {
		this.db = this.conn.DERBY;
	}

	public static facturaProductoDerbyDAO getInstance() {
		return instance;
	}

	@Override
	protected String getCreateTableQuery() {
		return "CREATE TABLE FacturaProducto(" + "idFactura INT," + "idProducto INT," + "cantidad INT,"
				+ "CONSTRAINT idFactura_fk FOREIGN KEY(idFactura) REFERENCES Factura(idFactura),"
				+ "CONSTRAINT idProducto_fk FOREIGN KEY(idProducto) REFERENCES Producto(idProducto))";
	}

	@Override
	protected String getSaveQuery() {
		return "INSERT INTO FacturaProducto(idFactura, idProducto, cantidad)" + "VALUES(?,?,?)";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE FacturaProducto " + "SET cantidad= ?" + "WHERE idFactura = ? AND idProducto = ?";
	}

	@Override
	protected String getReadByIdQuery() {
		return "SELECT * " + "FROM FacturaProducto " + "WHERE idFactura = ? AND idProducto = ?";
	}

	@Override
	protected String getReadAllQuery() {
		return "SELECT *" + "FROM FacturaProducto";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM FacturaProducto " + "WHERE idFactura = ? AND idProducto = ?";
	}

	@Override
	protected void setIdStatement(PreparedStatement ps, Pair<Integer, Integer> id) throws SQLException {
		ps.setInt(1, id.getFirst());
		ps.setInt(2, id.getSecond());
	}

	@Override
	protected void setObjectStatement(PreparedStatement ps, FacturaProducto fs, int option) throws SQLException {
		if (option == this.optionSave) {
			ps.setInt(1, fs.getIdFactura());
			ps.setInt(2, fs.getIdProducto());
			ps.setInt(3, fs.getCantidad());
		} else if (option == this.optionUpdate) {
			ps.setInt(1, fs.getCantidad());
			ps.setInt(2, fs.getIdFactura());
			ps.setInt(3, fs.getIdProducto());
		}
	}

	@Override
	protected FacturaProducto readObject(ResultSet rs) throws SQLException {
		return new FacturaProducto(rs.getInt("idFactura"), rs.getInt("idProducto"), rs.getInt("cantidad"));
	}

}
