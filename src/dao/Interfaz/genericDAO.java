package dao.Interfaz;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface genericDAO<P, K> {

	void createTable() throws SQLException;
	
    Optional<P> read(K id) throws SQLException;
    
    List<P> readAll() throws SQLException;
    
    void save(P object) throws SQLException;
    
    void update(P object) throws SQLException;
    
    void delete(K id) throws SQLException;
}
