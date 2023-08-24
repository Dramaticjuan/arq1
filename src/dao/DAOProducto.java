package dao;

import java.util.List;
import java.util.Optional;

public interface DAOProducto {
	void createTable();
	
    Optional<Producto> get(long id);
    
    List<Producto> getAll();
    
    void save(Producto t);
    
    void update(Producto t, String[] params);
    
    void delete(Producto t);
}