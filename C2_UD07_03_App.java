import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class C2_UD07_03_App {
	
	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		
		Hashtable<String, Integer> stock = new Hashtable<String, Integer>();

		Productos(stock);
		
		boolean keepActive = true;
		
		do {
			
			keepActive = GestionDeAlmacen(stock);

			
		} while (keepActive);
		
	}

	private static void Productos( Hashtable<String, Integer> stock) {
		
//		productos.put("Chocolate", 1.25);
//		productos.put("Pan", 0.5);
//		productos.put("Leche", 0.72);
//		productos.put("Salmon", 4.9);
//		productos.put("Pizza", 3.0);
//		productos.put("Agua", 0.6);
//		productos.put("Pollo", 3.9);
//		productos.put("Cerdo", 3.5);
//		productos.put("Cereales", 1.43);
//		productos.put("Platanos", 2.48);
		
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
	
	private static boolean GestionDeAlmacen(Hashtable<String, Integer> stock) {
		
		int valor = 0;
		
		do {
			
			System.out.println("1. Añadir stock");
			System.out.println("2. Ver productos");
			System.out.println("3. Seleccionar producto a visualizar");
			System.out.println("4. Quitar stock");
			System.out.println("5. Close");
			System.out.println("Introduce la opción a ejecutar");
			System.out.print("- ");
			valor = keyboard.nextInt();
		
		} while (valor > 5 || valor < 0);
		
		keyboard.nextLine();
		System.out.println("");
		
		switch (valor) {
		
			case 1:
				AñadirProductos(stock);
				break;
			case 2:
				System.out.println("Listado de productos en el almacen");
				VerProductos(stock);
				break;
			case 3:
				ProductoVisualizar(stock);
				break;
			case 4:
				System.out.println("Selecciona el producro a quitar:");
				VerProductos(stock);
				QuitarStock(stock);
				break;
			case 5:
				
				return false;

		}
		
		System.out.println("");
		
		return true;
		
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
			
			
			
		} else {
		
			stock.put(producto, cantidad);
		
		}
		
	}
	
	private static void VerProductos(Hashtable<String, Integer> stock) {
		
		Enumeration<String> llaves = stock.keys();
		
		while (llaves.hasMoreElements()) {
			
			String key = llaves.nextElement();
			
			System.out.println("    " + key + " - " + stock.get(key));
			
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
	
	private static void QuitarStock(Hashtable<String, Integer> stock) {
		
		System.out.println("Introduce el producto para quitar stock");
		System.out.print("- ");
		String producto = "";
		
		do {
			
			producto = keyboard.nextLine();
			if (!stock.containsKey(producto.trim())) {
				
				System.out.println("Error, vuelva a introducirlo");
				System.out.print("- ");
				
			}
			
		} while (!stock.containsKey(producto.trim()));
		
		System.out.println(producto.trim() + " - " + stock.get(producto));
		
		System.out.println("¿Cuanta cantidad quieres quitar?");
		System.out.print("- ");
		int cantidad = 0;
		
		do {
			
			cantidad = keyboard.nextInt();
			if (cantidad <= 0 || cantidad > stock.get(producto)) {
				
				System.out.println("Error, vuelva a introducirlo");
				System.out.print("- ");
				
			}
			
		} while (cantidad <= 0 || cantidad > stock.get(producto));
		
		keyboard.nextLine();
		
		int savedStock = stock.get(producto);
		
		stock.remove(producto);
		stock.put(producto, savedStock - cantidad);
		
		System.out.println("Actualizado producto " + producto.toLowerCase() + ": Cantidad actual " + stock.get(producto));
		
	}

}
