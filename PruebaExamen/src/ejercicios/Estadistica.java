package ejercicios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"fecha","ventas","total"})
public class Estadistica {
	
	private Date fecha;
	private ArrayList<VentaXML> ventas = new ArrayList<VentaXML>();
	private float total;
	
	public Estadistica() {}
	
	public Estadistica(Date fecha, ArrayList<VentaXML> ventas, float total) {
		super();
		this.fecha = fecha;
		this.ventas = ventas;
		this.total = total;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat ("yyyy-MM-dd");
		System.out.println("Estadistica [fecha=" + formato.format(fecha) + ", ventas=");
		
		for(VentaXML v:ventas) {
			v.mostrar();
		}
		
		System.out.println(", total=" + total + "]");
	}
	@XmlElement
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@XmlElementWrapper(name = "ventas")
	@XmlElement(name = "venta")
	public ArrayList<VentaXML> getVentas() {
		return ventas;
	}
	public void setVentas(ArrayList<VentaXML> ventas) {
		this.ventas = ventas;
	}
	@XmlElement
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}


	
	
	
	
	
}
