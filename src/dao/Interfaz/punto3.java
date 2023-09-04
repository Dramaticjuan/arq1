package dao.Interfaz;

import java.sql.SQLException;
import java.util.Optional;

import models.Producto;

public interface punto3 {
	public Optional<Producto> highestIncome() throws SQLException;
}
