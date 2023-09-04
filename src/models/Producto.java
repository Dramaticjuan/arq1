package models;

public class Producto{
	int id;
	String nombre;
	float valor;
	
	public Producto(int id, String nombre, float valor) {
		this.id= id;
		this.nombre= nombre;
		this.valor= valor;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public float getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", valor=" + valor + "]";
	}
}
