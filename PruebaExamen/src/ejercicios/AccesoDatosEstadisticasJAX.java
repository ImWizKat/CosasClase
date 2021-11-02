package ejercicios;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class AccesoDatosEstadisticasJAX {
	private String nombreFichero;
	private Estadistica estadistica;
	
	public AccesoDatosEstadisticasJAX() {}
	
	public AccesoDatosEstadisticasJAX(String nombreFichero) {
		super();
		this.nombreFichero = nombreFichero;
		estadistica = unmarshal();
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public Estadistica getEstadistica() {
		return estadistica;
	}

	public void setEstadistica(Estadistica estadistica) {
		this.estadistica = estadistica;
	}
	
	//MÉTODOS
	
	//para pasar de objeto a un archivo xml
	public void marshal() {
		try {
			if(estadistica!=null) {
				JAXBContext contexto = JAXBContext.newInstance(Estadistica.class);
				Marshaller marshal = contexto.createMarshaller();
				marshal.marshal(estadistica, new File(nombreFichero));
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//pasa el xml a un objeto estadística, dará null porque no tenemos xml previo
	public Estadistica unmarshal() {
		Estadistica resultado = null;
		File f = new File(nombreFichero);
		if(f.exists()) {
			JAXBContext contexto;
			try {
				contexto = JAXBContext.newInstance(Estadistica.class);
				Unmarshaller unmarshal = contexto.createUnmarshaller();
				resultado = (Estadistica) unmarshal.unmarshal(f);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return resultado;
	}

	public boolean importar(Date date,ArrayList<Venta> ventas) {
		boolean resultado = false;
		
		if(estadistica==null) {
			estadistica = new Estadistica();
			estadistica.setFecha(date);
			float total = 0;
			//si tuviera id
			//int contador = 1;
			
			for(Venta v : ventas) {
				total+=v.getTotal()*v.getCantidad();
				
				VentaXML vXML = new VentaXML();
				
				vXML.setCodigoProducto(v.getCodigoProducto());
				vXML.setCantidad(v.getCantidad());
				vXML.setPrecio(v.getTotal());			
				//vXML.setId(contador);
				//contador++;
				estadistica.getVentas().add(vXML);				
			}
			estadistica.setTotal(total);
			resultado=true;
		}
		else {
			System.out.println("Error, ya existe el fichero, no se puede volver a importar");
		}		
		
		return resultado;
	}
	
	
	
	
	
	
	
	
	
}
