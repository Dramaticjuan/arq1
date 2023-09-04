package dao.Interfaz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import utils.ConnectionFactory;

public abstract class abstractDAO<P, K> implements genericDAO<P, K> {

	protected ConnectionFactory conn = ConnectionFactory.getInstance();
	protected String db;

	protected abstract String getCreateTableQuery();

	protected abstract String getSaveQuery();

	protected abstract String getUpdateQuery();

	protected abstract String getReadByIdQuery();

	protected abstract String getReadAllQuery();

	protected abstract String getDeleteQuery();

	protected abstract void setIdStatement(PreparedStatement ps, K id) throws SQLException;

	protected abstract void setObjectStatement(PreparedStatement ps, P object, int option) throws SQLException;

	protected abstract P readObject(ResultSet resultSet) throws SQLException;

	protected final int optionSave= 0;
	protected final int optionUpdate= 1;
	
	public void createTable() throws SQLException {
		this.conn.connect(db);
		
		String query = getCreateTableQuery();		
		this.conn.connection().prepareStatement(query).execute();

		this.conn.disconnect();
	}

	public Optional<P> read(K id) throws SQLException {
		this.conn.connect(db);
		
		Optional<P> resultado= Optional.empty();
		String query = this.getReadByIdQuery();
		PreparedStatement ps = this.conn.connection().prepareStatement(query);
		setIdStatement(ps, id);
		ResultSet rs= ps.executeQuery();
		if(rs.next()) {
			resultado= Optional.of(readObject(rs));	
		}
		
		this.conn.disconnect();
		return resultado;
	}

	public List<P> readAll() throws SQLException {
		this.conn.connect(db);
		
		List<P> result = new ArrayList<P>();
		String query = this.getReadAllQuery();
		PreparedStatement ps = this.conn.connection().prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		result= convertResultSetToList(rs, result);
		
		this.conn.disconnect();
		return result;
	}

	public void save(P object) throws SQLException {
		this.conn.connect(db);
		
		String query = getSaveQuery();
		PreparedStatement ps = this.conn.connection().prepareStatement(query);
		setObjectStatement(ps, object, optionSave);
		ps.executeUpdate();
		
		this.conn.disconnect();
	}

	public void update(P object) throws SQLException {
		this.conn.connect(db);
		
		String query = getUpdateQuery();
		PreparedStatement ps = this.conn.connection().prepareStatement(query);
		setObjectStatement(ps, object, optionUpdate);
		ps.executeUpdate();
		
		this.conn.disconnect();
	}

	public void delete(K id) throws SQLException {
		this.conn.connect(db);
		
		String query = getDeleteQuery();
		PreparedStatement ps = this.conn.connection().prepareStatement(query);
		setIdStatement(ps, id);
		ps.executeUpdate();
		
		this.conn.disconnect();
	}

	protected List<P> convertResultSetToList(ResultSet rs, List<P> lista) throws SQLException {
		while (rs.next()) {
			lista.add(this.readObject(rs));

		}
		return lista;
	}
}
