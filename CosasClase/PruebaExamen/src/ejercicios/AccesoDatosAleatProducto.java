package ejercicios;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class AccesoDatosAleatProducto {
	private String nombreFichero;
	
	public AccesoDatosAleatProducto() {}

	public AccesoDatosAleatProducto(String nombreFichero) {
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
	/*
	CODIGO		PRECIO		STOCK		NOMBRE
	INT			FLOAT		INT			STRING(100)
	4			4			4			200				total 212 bytes
	*/

	public boolean cargarProductos(ArrayList<Producto> productosTxt) {
		boolean resultado=false;
		
		File f = new File(nombreFichero);
		
		if(f.exists()) {
			f.delete();		
		}
		
		RandomAccessFile fichero = null;
		
		try {
			fichero = new RandomAccessFile(nombreFichero,"rw");
			
			for(Producto p : productosTxt) {
				
				fichero.writeInt(p.getCodigo());
				fichero.writeFloat(p.getPrecio());
				fichero.writeInt(p.getStock());
				
				StringBuffer nombreBuffer = new StringBuffer(p.getNombre());
				nombreBuffer.setLength(100);						
				fichero.writeChars(nombreBuffer.toString());		

			}
			
			resultado=true;

			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		
		return resultado;
	}
	
	
	public ArrayList<Producto> obtenerProductos() {
		ArrayList<Producto> resultado = new ArrayList<Producto>();
		
		RandomAccessFile fichero = null;
		
		try {
			fichero = new RandomAccessFile(nombreFichero, "r");
			
			while(true) {
				Producto p = new Producto();
				p.setCodigo(fichero.readInt());
				p.setPrecio(fichero.readFloat());
				p.setStock(fichero.readInt());
				
				String nombre=""; 				
				for(int i = 0;i<100;i++) {
					nombre+=fichero.readChar();
				}				
				p.setNombre(nombre.trim());		
								
				resultado.add(p);
				
			}
			
		} catch (EOFException e) {
		} catch (FileNotFoundException e) {		
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		
		return resultado;
	}
	
	
	public Producto obtenerProducto(int codigo) {
		Producto p = null;
		
		RandomAccessFile fichero =null;
		
		try {
			fichero = new RandomAccessFile(nombreFichero, "r");
			
			while(true) {
				if(fichero.readInt()==codigo) {
					p = new Producto();
					p.setCodigo(codigo);
					p.setPrecio(fichero.readFloat());
					p.setStock(fichero.readInt());
					
					String nombre="";
					for(int i = 0; i<100;i++) {
						nombre+=fichero.readChar();
					}					
					p.setNombre(nombre.trim());
					
					return p;
					
				}
				else {
					fichero.seek(fichero.getFilePointer()+208);
				}
			}			
			
		} catch (EOFException e) {
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		
		
		return p;
	}
	/*
	CODIGO		PRECIO		STOCK		NOMBRE
	INT			FLOAT		INT			STRING(100)
	4			4			4			200				total 212 bytes
	*/

	public boolean actualizarStock(Producto p, int cantidad) {
		boolean resultado = false;
		RandomAccessFile fichero =null;

		try {
			fichero = new RandomAccessFile(nombreFichero, "rw");
			
			while(true) {
				if(fichero.readInt()==p.getCodigo()) {
					fichero.seek(fichero.getFilePointer()+4);
					fichero.writeInt(p.getStock()-cantidad);
					resultado=true;
					return resultado;					
				}
				else {
					fichero.seek(fichero.getFilePointer()+208);
				}
			}
		} catch (EOFException e) {
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if(fichero!=null){
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

	public boolean comprobarStock(Producto p, int cantidad) {
		boolean resultado = false;
		
		RandomAccessFile fichero = null;
		
		try {
			fichero = new RandomAccessFile(nombreFichero, "r");
			
			while(true) {
				if(fichero.readInt()==p.getCodigo()) {
					fichero.seek(fichero.getFilePointer()+4);
					if(fichero.readInt()<cantidad) {
						return false;
						}					
				}
				else {
					fichero.seek(fichero.getFilePointer()+208);
				}
				resultado = true;		
			}
						
			
		} catch (EOFException e) {
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		return resultado;
	}
	

	
}
