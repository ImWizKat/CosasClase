package ejercicios;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id","cantidad","precio"})
public class VentaXML {
	
	private int id;
	private int codigoProducto;
	private int cantidad;
	private float precio;
	
	public VentaXML() {}
	
	public VentaXML(int codigoProducto, int cantidad, float precio) {
		super();
		this.codigoProducto = codigoProducto;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public void mostrar() {
		System.out.println("VentaXML [id=" + id + "codigoProducto=" + codigoProducto + ", cantidad=" + cantidad + ", precio=" + precio + "]");
	}
	
	@XmlAttribute
	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	@XmlElement
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	@XmlElement
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	
	
	
	
	
}
