import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class C2_UD07_02_App {

	private static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		ArrayList<String> carrito = new ArrayList<>();
		
		Hashtable<String, Double> productos = new Hashtable<String, Double>();
		final Hashtable<String, Integer> IVA = new Hashtable<String, Integer>();
		
		Productos(productos, IVA);
		
		boolean keepActive = true;
		
		do {
			
			keepActive = CarritoDeLaCompra(carrito, productos, IVA);

			
		} while (keepActive);
		
	}
	

	private static void Productos(Hashtable<String, Double> productos, Hashtable<String, Integer> iva) {
		
		productos.put("Chocolate", 1.25);
		productos.put("Pan", 0.5);
		productos.put("Leche", 0.72);
		productos.put("Salmon", 4.9);
		productos.put("Pizza", 3.0);
		productos.put("Agua", 0.6);
		productos.put("Pollo", 3.9);
		
		iva.put("Chocolate", 21);
		iva.put("Pan", 4);
		iva.put("Leche", 4);
		iva.put("Salmon", 21);
		iva.put("Pizza", 21);
		iva.put("Agua", 4);
		iva.put("Pollo", 21);
			
	}
	

	private static boolean CarritoDeLaCompra(ArrayList<String> carrito, Hashtable<String, Double> productos, Hashtable<String, Integer> iva) {
		
		int valor = 0;
		
		do {
			
			System.out.println("1. Añadir productos al carro");
			System.out.println("2. Mostrar carrito");
			System.out.println("3. Retirar productos");
			System.out.println("4. Pagar en caja");
			System.out.println("Introduce la opción a ejecutar");
			System.out.print("- ");
			valor = keyboard.nextInt();
		
		} while (valor > 4 || valor < 0);
		
		keyboard.nextLine();
		System.out.println("");
		
		switch (valor) {
		
			case 1:
				AnadirProductos(carrito, productos);
				break;
			case 2:
				@SuppressWarnings("unused") double deathValue = MostrarCarrito(carrito, productos, iva);
				break;
			case 3:
				QuitarProductos(carrito);
				break;
			case 4:
				Pagar(carrito, productos, iva);
				return false;
		
		}
		
		System.out.println("");
		
		return true;
		
	}
	

	private static void AnadirProductos(ArrayList<String> carrito, Hashtable<String, Double> productos) {
		
		MostrarProductos(productos);
		
		System.out.print("- ");
		String productoEscoger = EscojerProducto(productos); 
		
		System.out.println("Selecciona la cantidad de " + productoEscoger.toLowerCase() + "");
		System.out.print("- ");
		int cantidad = EscojerCantidad();
		
		AñadirAlCarro(carrito, productoEscoger, cantidad);
		
		keyboard.nextLine();
		
		//Hacer que solo me pueda pasar si o no
		System.out.println("¿Quieres escojer otro producto? (Si/No)");
		System.out.print("- ");
		String continuar = keyboard.nextLine();
		
		if (continuar.toLowerCase().contentEquals("si")) {
			
			System.out.println("");
			AnadirProductos(carrito, productos);
			
		}
		
	}
	
	
	private static double MostrarCarrito(ArrayList<String> carrito, Hashtable<String, Double> productos, Hashtable<String, Integer> iva) {
		
		carrito.sort(null);
		
		if (carrito.size() == 0) {
			
			System.out.println("No tienes productos añadidos al carro.");
			
			return 0;
			
		} else {
		
			System.out.println("Tu carro: ");
			
			carrito.sort(null);
			
			String lastItem = "";
			int count = 0;
			int countTotal = 0;
			double valor = 0;
			
			for (String item:carrito) {
				
				if (lastItem.equals("")) {
					
					lastItem = item;
					
				} 
				
				if (countTotal + 1 == carrito.size()) {
				
					if (item.equals(lastItem)) {
						
						count++;
						System.out.println(item + " - " + count + "u (" + iva.get(item) + "%)");
						
					} else {
						
						count = 1;
						System.out.println(item + " - " + count + "u (" + iva.get(item) + "%)");
						
					}
					
				} 
				
				if (!item.equals(lastItem)) {
					
					System.out.println(lastItem + " - " + count + "u (" + iva.get(item) + "%)");
					lastItem = item;
					count = 1;
					
				} else {
					
					count++;
					
				}
				
				valor += productos.get(item) + (productos.get(item) * iva.get(item));
				
				countTotal++;
				
			}
			
			System.out.println("Total " + countTotal + " productos con un valor de " + (Math.round(valor * 100) / 100) + "€");
			
			return valor;
		
		}
		
	}
	
	
	private static void QuitarProductos(ArrayList<String> carrito) {
		
		carrito.sort(null);
		
		if (carrito.size() == 0) {
			
			System.out.println("No tienes productos añadidos al carro.");
			
		} else {
			
			System.out.println("¿Que producto quieres quitar? ");
			
			String lastItem = "";
			
			for (String item:carrito) {
				
				if (!item.equals(lastItem.trim())) {
					
					System.out.println("- " + item);
					lastItem = item;
					
				}
				
			}
			
			System.out.print("- ");
			String productoQuitar = EscojerProductoQuitar(carrito);
			
			QuitarCantidad(carrito, productoQuitar);
		
		}
		
		
	}

	
	private static void QuitarCantidad(ArrayList<String> carrito, String productoQuitar) {
		
		int count = 0;
		
		for (String item:carrito) {
			
			if (item.equals(productoQuitar)) {
				
				count++;
				
			}
			
		}
		
		if (count == 1) {
			
			carrito.remove(productoQuitar);
			
		} else {
			
			System.out.println("¿Cuanta cantidad quieres quitar? (Hay "+ count +" en el carro)");
			System.out.print("- ");
			
			int cantidad = 0;
			
			do {
				
				cantidad = keyboard.nextInt();
				
				if (cantidad < 1 || cantidad > count) {
					
					System.out.println("Error al introducir la cantidad, vuelve a intentarlo");
					System.out.print("- ");
					
				}
				
			} while (cantidad < 1 || cantidad > count);
			
			for (int i = 0; i < cantidad; i++) {
				
				carrito.remove(productoQuitar);
			
			}
			
		}
		
	}


	private static void MostrarProductos(Hashtable<String, Double> productos) {
		
		Enumeration<String> llaves = productos.keys();
		
		System.out.println("Selecciona el producto que quieres añadir");
		
		while (llaves.hasMoreElements()) {
			
			String key = llaves.nextElement();
			
			System.out.println("    " + key + " - " + productos.get(key) + "€ sin IVA");
			
		}
		
	}
	
	
	private static String EscojerProducto(Hashtable<String, Double> productos) {
		
		String productoEscoger = "";
		
		do {
			
			productoEscoger = keyboard.nextLine();
			
			if (!productos.containsKey(productoEscoger.trim())) {
				
				System.out.println("Error al escoger el producto, vuelve a escribirlo");
				System.out.print("- ");
				
			}
		
		} while (!productos.containsKey(productoEscoger));
		
		return productoEscoger;
		
	}
	

	private static int EscojerCantidad() {
		
		int cantidad = 0;
		
		do {
			
			cantidad = keyboard.nextInt();
			if (cantidad < 1) {
				
				System.out.println("Error, vuelva a introducirlo");
				System.out.print("- ");
				
			}
			
		} while (cantidad < 1);
		
		return cantidad;
		
	}
	
	
	private static void AñadirAlCarro(ArrayList<String> carrito, String productoEscoger, int cantidad) {
		
		for (int i = 0; i < cantidad; i++) {
			
			carrito.add(productoEscoger);
			
		}
		
	}
	
	
	private static String EscojerProductoQuitar(ArrayList<String> carrito) {
		
		String productoEscoger = "";
		
		boolean existe = false; 
		
		do {
			
			productoEscoger = keyboard.nextLine();
			
			existe = false;
			
			for (String item: carrito) {
				
				if (item.equals(productoEscoger)) {
					
					existe = true;
					
				}
				
			}
			
			if (!existe) {
				
				System.out.println("Error al escoger el producto, vuelve a escribirlo.");
				System.out.print("- ");
				
			}
		
		} while (!existe);
		
		return productoEscoger;

	}
	
	
	private static void Pagar(ArrayList<String> carrito, Hashtable<String, Double> productos, Hashtable<String, Integer> iva) {
		
		carrito.sort(null);
		
		if (carrito.size() == 0) {
			
			System.out.println("No tienes productos añadidos al carro.");
			
		} else {
			
			double valor = MostrarCarrito(carrito, productos, iva);
			double pago = 0;
			
			System.out.println("\n¿Cuanto pagas?");
			System.out.print("- ");
			
			do {
				
				pago = keyboard.nextDouble();
				
				if (pago <= 0) {
					
					System.out.println("No te puedes ir sin pagar o exigiendo dinero, es delito");
					
				} else {
					
					valor -= pago;
					
					if (valor > 0) {
						
						System.out.println("Aún te queda " + valor + "€ por pagar, saca más dinero");
						
					} else {
						
						System.out.println("Aqui tienes tu cambio, " + Math.abs(valor) + "€");
						System.out.println("Gracias por tu compra");
						
					}
					
				}
			
			} while (valor > 0);
			
		}
		
	}

}
