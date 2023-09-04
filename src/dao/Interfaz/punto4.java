package dao.Interfaz;

import java.sql.SQLException;
import java.util.List;

import models.Cliente;

public interface punto4 {
	public List<Cliente> readAllSortByMostBilled() throws SQLException;
}
