package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import utils.ConnectionFactory;

public class DerbyDAOProducto implements DAOProducto {
	private ConnectionFactory conn= ConnectionFactory.getInstance();
	private static DerbyDAOProducto instance= new DerbyDAOProducto();
	private DerbyDAOProducto () {
	}
	public static DerbyDAOProducto getInstance() {
		return instance;
	}
	
	@Override
	public Optional<Producto> get(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Producto> getAll() {
		this.conn.connect(ConnectionFactory.DERBY);
		List<Producto> productos=null;

		try {
			PreparedStatement ps = this.conn.connection().prepareStatement("SELECT * FROM Producto");
			ResultSet rs = ps.executeQuery();
			/*
			 *  productos = this.convertResultSetToList(rs);
			 */
			while (rs.next()) {
				int id= rs.getInt("idproducto");
				String nombre= rs.getString("nombre");
				float valor= rs.getFloat("valor");
				System.out.println("id:" + id + " Producto: " + nombre + "valor: $" + valor);
			}
			this.conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return productos;
	}

	@Override
	public void save(Producto p) {
		this.conn.connect(ConnectionFactory.DERBY);
		try {
			PreparedStatement ps= this.conn.connection().prepareStatement("INSERT INTO Producto(idProducto, nombre, valor) VALUES(?,?,?)");
			ps.setInt(1, p.getId());
			ps.setString(2, p.nombre);
			ps.setFloat(3, p.getValor());
			ps.executeUpdate();
			this.conn.connection().commit();
			this.conn.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Producto p, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Producto p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createTable() {
		this.conn.connect(ConnectionFactory.DERBY);
		String sql = "CREATE TABLE Producto(" 
				+ "idProducto INT, " 
				+ "nombre VARCHAR(500),"
				+ "valor FLOAT,"
				+ "PRIMARY KEY(idProducto))";
		try {
			this.conn.connection().prepareStatement(sql).execute();
			this.conn.connection().commit();
			this.conn.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Producto> convertResultSetToList(ResultSet rs) throws SQLException{
		List<Producto> lista= new ArrayList();
		while (rs.next()) {
			lista.add(new Producto(rs.getInt("idproducto"), rs.getString("nombre"), rs.getFloat("valor")));
		}
		return lista;
	}

}
