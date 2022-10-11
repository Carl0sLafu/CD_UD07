import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class C2_UD07_04_App {

private static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		ArrayList<String> carrito = new ArrayList<>();
		
		Hashtable<String, Double> productos = new Hashtable<String, Double>();
		final Hashtable<String, Integer> IVA = new Hashtable<String, Integer>();
		Hashtable<String, Integer> stock = new Hashtable<String, Integer>();
		
		Productos(productos, IVA, stock);
		
		boolean keepActive = true;
		
		do {
			
			keepActive = CarritoDeLaCompra(carrito, productos, IVA, stock);

		} while (keepActive);
		
	}
	

	private static void Productos(Hashtable<String, Double> productos, Hashtable<String, Integer> iva, Hashtable<String, Integer> stock) {
		
		productos.put("Chocolate", 1.25);
		productos.put("Pan", 0.5);
		productos.put("Leche", 0.72);
		productos.put("Salmon", 4.9);
		productos.put("Pizza", 3.0);
		productos.put("Agua", 0.6);
		productos.put("Pollo", 3.9);
		productos.put("Cerdo", 3.5);
		productos.put("Cereales", 1.43);
		productos.put("Platanos", 2.48);
		
		iva.put("Chocolate", 21);
		iva.put("Pan", 4);
		iva.put("Leche", 4);
		iva.put("Salmon", 21);
		iva.put("Pizza", 21);
		iva.put("Agua", 4);
		iva.put("Pollo", 21);
		iva.put("Cerdo", 21);
		iva.put("Cereales", 21);
		iva.put("Platanos", 4);
		
		stock.put("Chocolate", 10);
		stock.put("Pan", 20);
		stock.put("Leche", 34);
		stock.put("Salmon", 6);
		stock.put("Pizza", 12);
		stock.put("Agua", 60);
		stock.put("Pollo", 17);
		stock.put("Cerdo", 8);
		stock.put("Cereales", 23);
		stock.put("Platanos", 46);
			
	}
	

	private static boolean CarritoDeLaCompra(ArrayList<String> carrito, Hashtable<String, Double> productos, Hashtable<String, Integer> iva, Hashtable<String, Integer> stock) {
		
		int valor = 0;
		
		do {
			
			System.out.println("1. Añadir productos al carro");
			System.out.println("2. Mostrar carrito");
			System.out.println("3. Retirar productos del carro");
			System.out.println("4. Añadir stock");
			System.out.println("5. Seleccionar producto a visualizar");
			System.out.println("6. Pagar en caja");
			System.out.println("Introduce la opción a ejecutar");
			System.out.print("- ");
			valor = keyboard.nextInt();
		
		} while (valor > 6 || valor < 0);
		
		keyboard.nextLine();
		System.out.println("");
		
		switch (valor) {
		
			case 1:
				AnadirProductos(carrito, productos, stock);
				break;
			case 2:
				//Pongo esto para no tenter que crear otro metodo que haga lo mismo pero solo cambiando lo que tiene que devolver 
				@SuppressWarnings("unused") double deathValue = MostrarCarrito(carrito, productos, iva);
				break;
			case 3:
				QuitarProductos(carrito, stock);
				break;
			case 4:
				AñadirProductos(stock);
				break;
			case 5:
				ProductoVisualizar(stock);
				break;
			case 6:
				Pagar(carrito, productos, iva, stock);
				return false;
		
		}
		
		System.out.println("");
		
		return true;
		
	}
	

	private static void AnadirProductos(ArrayList<String> carrito, Hashtable<String, Double> productos, Hashtable<String, Integer> stock) {
		
		MostrarProductos(productos);
		
		System.out.print("- ");
		String productoEscoger = EscojerProducto(productos);
		
		if (stock.get(productoEscoger) != 0) {
		
			System.out.println("Selecciona la cantidad de " + productoEscoger.toLowerCase() + "");
			System.out.print("- ");
			int cantidad = EscojerCantidad(productoEscoger, stock);
			
			AnadirAlCarro(carrito, productoEscoger, cantidad);
			
			stock.put(productoEscoger, stock.get(productoEscoger) - cantidad);
		
		}
		
		keyboard.nextLine();
		
		//Hacer que solo me pueda pasar si o no
		System.out.println("¿Quieres escojer otro producto? (Si/No)");
		System.out.print("- ");
		String continuar = keyboard.nextLine();
		
		if (continuar.toLowerCase().contentEquals("si")) {
			
			System.out.println("");
			AnadirProductos(carrito, productos, stock);
			
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
	
	
	private static void QuitarProductos(ArrayList<String> carrito, Hashtable<String, Integer> stock) {
		
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
			
			QuitarCantidad(carrito, productoQuitar, stock);
		
		}
		
		
	}

	
	private static void QuitarCantidad(ArrayList<String> carrito, String productoQuitar, Hashtable<String, Integer> stock) {
		
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
			
			stock.put(productoQuitar, stock.get(productoQuitar) + cantidad);
			
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
	

	private static int EscojerCantidad(String productoEscoger, Hashtable<String, Integer> stock) {
		
		int cantidad = 0;
		
		do {
			
			cantidad = keyboard.nextInt();
			if (cantidad > stock.get(productoEscoger) || cantidad < 1) {
				
				System.out.println("Error, vuelva a introducirlo");
				System.out.print("- ");
				
			}
			
		} while (cantidad > stock.get(productoEscoger) || cantidad < 1);
		
		return cantidad;
		
	}
	
	
	private static void AnadirAlCarro(ArrayList<String> carrito, String productoEscoger, int cantidad) {
		
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
	
	
	private static void Pagar(ArrayList<String> carrito, Hashtable<String, Double> productos, Hashtable<String, Integer> iva, Hashtable<String, Integer> stock) {
		
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
	
	private static void AñadirProductos(Hashtable<String, Integer> stock) {
		
		System.out.println("Introduce el producto que quieres añadir.");
		System.out.print("- ");
		String producto = keyboard.nextLine();
		
		producto = producto.trim();
		
		System.out.println("Cantidad de " + producto + ":");
		System.out.print("- ");
		int cantidad = keyboard.nextInt();
		
		keyboard.nextLine();
		
		if (stock.containsKey(producto)) {
			
			stock.put(producto, stock.get(producto) + cantidad);
			
		} else {
		
			stock.put(producto, cantidad);
		
		}
		
	}
	
	private static void ProductoVisualizar(Hashtable<String, Integer> stock) {
		
		System.out.println("Introduce el producto a visualizar");
		System.out.print("- ");
		String producto = "";
		
		do {
			
			producto = keyboard.nextLine();
			if (!stock.containsKey(producto.trim())) {
				
				System.out.println("Error, vuelva a introducirlo");
				System.out.print("- ");
				
			}
			
		} while (!stock.containsKey(producto.trim()));
		
		System.out.println(stock.get(producto.trim()) + " productos en stock");
		
	}

}
