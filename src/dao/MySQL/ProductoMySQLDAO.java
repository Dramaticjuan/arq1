package dao.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import dao.Interfaz.abstractDAO;
import dao.Interfaz.punto3;
import models.Producto;

public class ProductoMySQLDAO extends abstractDAO<Producto, Integer> implements punto3{

	private static ProductoMySQLDAO instance = new ProductoMySQLDAO();

	private ProductoMySQLDAO() {
		this.db = this.conn.MYSQL;
	}

	public static ProductoMySQLDAO getInstance() {
		return instance;
	}

	@Override
	protected String getCreateTableQuery() {
		return "CREATE TABLE Producto(" + "idProducto INT, " + "nombre VARCHAR(500)," + "valor FLOAT,"
				+ "CONSTRAINT producto_pk PRIMARY KEY(idProducto))";
	}

	@Override
	protected String getSaveQuery() {
		return "INSERT INTO Producto(idProducto, nombre, valor)" + "VALUES(?,?,?)";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE Producto " + "SET nombre= ?, valor=? " + "WHERE idProducto=?";
	}

	@Override
	protected String getReadByIdQuery() {
		return "SELECT * " + "FROM Producto " + "WHERE idProducto= ?";
	}

	@Override
	protected String getReadAllQuery() {
		return "SELECT *" + "FROM Producto";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM Producto " + "WHERE idProducto=?";
	}

	@Override
	protected void setIdStatement(PreparedStatement ps, Integer id) throws SQLException {
		ps.setInt(1, id);
	}

	@Override
	protected void setObjectStatement(PreparedStatement ps, Producto p, int option) throws SQLException {
		if (option == this.optionSave) {
			ps.setInt(1, p.getId());
			ps.setString(2, p.getNombre());
			ps.setFloat(3, p.getValor());
		} else if (option == this.optionUpdate) {
			ps.setString(1, p.getNombre());
			ps.setFloat(2, p.getValor());
			ps.setInt(3, p.getId());
		}
	}
	
	public Optional<Producto> highestIncome() throws SQLException{
		this.conn.connect(db);
		
		Optional<Producto> resultado= Optional.empty();
		String query = this.getHighestIncomeQuery();
		PreparedStatement ps = this.conn.connection().prepareStatement(query);
		ResultSet rs= ps.executeQuery();
		if(rs.next()) {
			resultado= Optional.of(readObject(rs));	
		}
		
		this.conn.disconnect();
		return resultado;
	}

	private String getHighestIncomeQuery() {
		return "SELECT P.idProducto, P.nombre, P.valor "
				+ "FROM FacturaProducto fp "
				+ "JOIN arq_db_1.Producto P on P.idProducto = fp.idProducto "
				+ "GROUP BY idProducto, P.valor "
				+ "ORDER BY (SUM(cantidad)* P.valor) DESC "
				+ "LIMIT 1;";
	}
	
	@Override
	protected Producto readObject(ResultSet rs) throws SQLException {
		return new Producto(rs.getInt("idProducto"), rs.getString("nombre"), rs.getFloat("valor"));
	}

}
