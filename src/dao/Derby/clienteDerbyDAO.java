package dao.Derby;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Interfaz.abstractDAO;
import dao.Interfaz.punto4;
import models.Cliente;

public class clienteDerbyDAO extends abstractDAO<Cliente, Integer> implements punto4{

	private static clienteDerbyDAO instance = new clienteDerbyDAO();

	private clienteDerbyDAO() {
		this.db = this.conn.DERBY;
	}

	public static clienteDerbyDAO getInstance() {
		return instance;

	}

	@Override
	protected String getCreateTableQuery() {
		return "CREATE TABLE Cliente(" + "idCliente INT, " + "nombre VARCHAR(500)," + "email VARCHAR(150),"
				+ "CONSTRAINT cliente_pk PRIMARY KEY(idCliente))";
	}

	@Override
	protected String getSaveQuery() {
		return "INSERT INTO Cliente(idCliente, nombre, email)" + "VALUES(?,?,?)";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE Cliente " + "SET nombre= ?, email= ?" + "WHERE idCliente= ?";
	}

	@Override
	protected String getReadByIdQuery() {
		return "SELECT * " + "FROM Cliente " + "WHERE idCliente= ?";
	}

	@Override
	protected String getReadAllQuery() {
		return "SELECT *" + "FROM Cliente";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM Cliente " + "WHERE idCliente= ?";
	}

	@Override
	protected void setIdStatement(PreparedStatement ps, Integer id) throws SQLException {
		ps.setInt(1, id);
	}

	@Override
	protected void setObjectStatement(PreparedStatement ps, Cliente c, int option) throws SQLException {
		if (option == this.optionSave) {
			ps.setInt(1, c.getId());
			ps.setString(2, c.getNombre());
			ps.setString(3, c.getEmail());
		} else if (option == this.optionUpdate) {
			ps.setString(1, c.getNombre());
			ps.setString(2, c.getEmail());
			ps.setInt(3, c.getId());
		}
	}
	public List<Cliente> readAllSortByMostBilled() throws SQLException{
		this.conn.connect(db);
		
		List<Cliente> result = new ArrayList<Cliente>();
		String query = this.getReadAllSortByMostBilledQuery();
		PreparedStatement ps = this.conn.connection().prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		result= convertResultSetToList(rs, result);
		
		this.conn.disconnect();
		return result;
	}
	private String getReadAllSortByMostBilledQuery() {
		return "SELECT C.idCliente, C.nombre, C.email "
				+ "FROM Cliente C "
				+ "JOIN arq_db_1.Factura F on C.idCliente = F.idCliente "
				+ "JOIN arq_db_1.FacturaProducto FP on F.idFactura = FP.idFactura "
				+ "JOIN arq_db_1.Producto P on P.idProducto = FP.idProducto "
				+ "GROUP BY F.idCliente "
				+ "order by SUM(cantidad* P.valor) desc;";
	}

	@Override
	protected Cliente readObject(ResultSet rs) throws SQLException {
		return new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("email"));
	}

}
