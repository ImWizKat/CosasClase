package ejercicios;

import java.io.Serializable;

public class Producto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4321245521160595664L;
	private int codigo;
	private float precio;
	private int stock;
	private String nombre;
	
	public Producto() {}

	public Producto(int codigo, float precio, int stock, String nombre) {
		super();
		this.codigo = codigo;
		this.precio = precio;
		this.stock = stock;
		this.nombre = nombre;
	}

	public void mostrar() {
		System.out.println("Producto [codigo=" + codigo + ", precio=" + precio + ", stock=" + stock + ", nombre=" + nombre + "]");
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
