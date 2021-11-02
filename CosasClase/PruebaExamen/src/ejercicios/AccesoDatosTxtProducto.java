package ejercicios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AccesoDatosTxtProducto {
	private String nombreFichero;
	
	public AccesoDatosTxtProducto() {}

	public AccesoDatosTxtProducto(String nombreFichero) {
		super();
		this.nombreFichero = nombreFichero;
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	
	//MÉTODOS	
	
	public ArrayList<Producto> importarProductos() {
		ArrayList<Producto> resultado = new ArrayList<Producto>();
		
		BufferedReader fichero = null;
		
		try {
			fichero = new BufferedReader(new FileReader(nombreFichero));
			
			String linea;
			
			while((linea=fichero.readLine())!=null) {
				
				String [] campos = linea.split(";");
				
				Producto p = new Producto();
				
				p.setCodigo(Integer.parseInt(campos[0]));
				p.setPrecio(Float.parseFloat(campos[1]));
				p.setStock(Integer.parseInt(campos[2]));
				p.setNombre(campos[4]);
				
				if(campos[3].equals("1")) {
					resultado.add(p);
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if (fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return resultado;
	}
	

	
	
	
}
