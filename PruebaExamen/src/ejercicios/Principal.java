package ejercicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	static Scanner t = new Scanner(System.in);
	//accesos a datos
	static AccesoDatosTxtProducto datosPTxt = new AccesoDatosTxtProducto("productosSistemaAntiguo.txt");
	static AccesoDatosAleatProducto datosPAleat = new AccesoDatosAleatProducto("productos.bin");
	static AccesoDatosObjVentas datosVObj = new AccesoDatosObjVentas("ventas.obj");
	static AccesoDatosEstadisticasJAX datosEstad = new AccesoDatosEstadisticasJAX ("Estadisticas.xml");

	public static void main(String[] args) {

		int elige;
		do {
			System.out.println("Elige ejercicio");
			System.out.println("1 - Ejercicio 1" + "\n2 - Ejercicio 2" + "\n3 - Ejercicio 3" + "\n0 - Salir");
			elige = t.nextInt();
			t.nextLine();

			switch (elige) {
			case 1:
				Ej1();
				break;
			case 2:
				Ej2();
				break;
			case 3:
				Ej3();
				break;
			case 0:
				System.out.println("Adi�s");
				break;
			}

		} while (elige != 0);
		datosEstad.marshal();

	}

	private static void Ej1() {

		// creamos un array de productos donde guardarnos los productos importados del
		// txt

		ArrayList<Producto> productosTxt = new ArrayList<Producto>();

		productosTxt = datosPTxt.importarProductos();

		for (Producto p : productosTxt) {
			System.out.println("Productos importados txt");
			p.mostrar();
		}

		if (!productosTxt.isEmpty()) {

			// cargarlos en el aleatorio
			if (datosPAleat.cargarProductos(productosTxt)) {
				// mostrarlos desde el aleatorio
				ArrayList<Producto> productosAleat = new ArrayList<Producto>();
				productosAleat = datosPAleat.obtenerProductos();
				if (!productosAleat.isEmpty()) {
					System.out.println("Productos: \n");
					for (Producto p : productosAleat) {
						System.out.println("Productos en productos.bin: ");
						p.mostrar();
					}
				} else {
					System.out.println("No hay productos para mostrar");
				}
			} else {
				System.out.println("No se pudieron cargar los productos en productos.bin");
			}

		} else {
			System.out.println("No hay productos activos");
		}
	}

	private static void Ej2() {

		for (Producto p : datosPAleat.obtenerProductos()) {
			p.mostrar();
		}
		System.out.println("Introduce c�digo de producto");
		int codigo = t.nextInt();
		t.nextLine();
		// comprobaci�n c�digo
		Producto p = datosPAleat.obtenerProducto(codigo);

		if (p != null) {
			// comprobaci�n stock
			if (p.getStock() > 0) {
				System.out.println("Introduce cantidad a vender");
				int cantidad = t.nextInt();
				t.nextLine();
				//comprobaci�n stock suficiente para la venta
				//esta comprobaci�n no necesita un m�todo, con comparar el stock del objeto que hemos cogido es suficiente, se me fue la pinza xdxd
				if(datosPAleat.comprobarStock(p,cantidad)) {
					// a�adir venta
					if (datosVObj.a�adirVenta(p, cantidad)) {
						// actualizaci�n stock
						if (datosPAleat.actualizarStock(p, cantidad)) {
							// mostrar ventas
							ArrayList<Venta> ventas = datosVObj.obtenerVentas();
							if (!ventas.isEmpty()) {
								for (Venta v : ventas) {
									System.out.println("Ventas en ventas.obj:");
									v.mostrar();
								}
							} else {
								System.out.println("No hay ventas");
							}
						} else {
							System.out.println("Error al actualizar el stock");
						}
					} else {
						System.out.println("No se pudo a�adir la venta");
					}
				}
				else {
					System.out.println("No hay suficiente stock");
				}

			} else {
				System.out.println("No hay stock de ese producto");
			}
		} else {
			System.out.println("Error, no existe un producto con ese c�digo");
		}
	}	
	
	private static void Ej3() {
		
		ArrayList<Venta> ventas = datosVObj.obtenerVentas();
		
		if(!ventas.isEmpty()) {
			if(!datosEstad.importar(new Date(),ventas)) {
				System.out.println("Error al importar las ventas");
			}
		}
		else {
			System.out.println("Error, no hay ventas");
		}
		
	}

}
