package dao;

public class Producto {
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
	

}
