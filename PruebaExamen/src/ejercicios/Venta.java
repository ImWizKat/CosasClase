package ejercicios;

import java.io.Serializable;

public class Venta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5492983773392430057L;
	private int codigoProducto;
	private int cantidad;
	private float total;
	
	public Venta() {}

	public Venta(int producto, int cantidad, float total) {
		super();
		this.codigoProducto = producto;
		this.cantidad = cantidad;
		this.total = total;
	}
	
	public void mostrar() {
		System.out.println("Venta [codigoProducto=" + codigoProducto + ", cantidad=" + cantidad + ", total=" + total + "]");
	}
	
	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int producto) {
		this.codigoProducto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}	
	
}
