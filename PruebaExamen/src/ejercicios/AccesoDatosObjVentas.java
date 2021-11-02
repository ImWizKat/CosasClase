package ejercicios;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AccesoDatosObjVentas {
	private String nombreFichero;

	public AccesoDatosObjVentas() {
	}

	public AccesoDatosObjVentas(String nombreFichero) {
		super();
		this.nombreFichero = nombreFichero;
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	// MÉTODOS

	public boolean añadirVenta(Producto p, int cantidad) {
		boolean resultado = false;

		ObjectOutputStream fichero = null;

		try {

			File f = new File(nombreFichero);
			
			//si ya hay fichero, no creará la cabecera, si no lo hay, sí
			if (f.exists()) {
				fichero = new MiObjectOutputStream(new FileOutputStream(nombreFichero, true));
			}
			else {
				fichero = new ObjectOutputStream(new FileOutputStream(nombreFichero, true));
			}
		
			Venta v = new Venta();			
			v.setCodigoProducto(p.getCodigo());
			v.setCantidad(cantidad);
			v.setTotal(cantidad*p.getPrecio());
			
			fichero.writeObject(v);
			
			resultado=true;
			return resultado;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<Venta> obtenerVentas() {
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		
		ObjectInputStream fichero = null;
		
		try {
			fichero = new ObjectInputStream(new FileInputStream(nombreFichero));			
			
			while(true) {
				Venta v = (Venta)fichero.readObject();
				ventas.add(v);
			}
			
		} catch(EOFException e) {
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return ventas;
	}

}
