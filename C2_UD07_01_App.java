import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class C2_UD07_01_App {

	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.print("Numero de alumnos en el curso: ");
		int numeroAlumnos = keyboard.nextInt();
		
		keyboard.nextLine();
		
		ArrayList<String> alumnos = new ArrayList<>();
		
		RellenarNombresAlumnos(alumnos, numeroAlumnos);
		
		System.out.print("Cantidad total de evaluaciones: ");
		int numeroEvaluaciones = keyboard.nextInt();
		
		Hashtable<String, Double> notasAlumnos =new Hashtable<String, Double>();
		
		CalcularNotaMediaCadaAlumno(alumnos, numeroEvaluaciones, notasAlumnos);
		
		MostrarNotas(notasAlumnos);

	}

	private static void RellenarNombresAlumnos(ArrayList<String> alumnos, int num) {
		
		for (int i = 0; i < num; i++) {
			
			System.out.println("Nombre del alumno "+ (i+1) +":");
			alumnos.add(keyboard.nextLine());
			
		}
		
	}
	
	private static void CalcularNotaMediaCadaAlumno(ArrayList<String> alumnos, int numEvaluaciones, Hashtable<String, Double> notasAlumnos) {
		
		for (String nombre:alumnos) {
			
			double sumaNotas = 0;
			
			for (int i = 0; i < numEvaluaciones; i++) {
				
				System.out.print("Nota de " + nombre + " en la evaluacion " + (i+1) + ": ");
				double nota = keyboard.nextDouble();
				
				while (nota > 10 || nota < 0) {
					
					System.out.print("Error al introducir la nota, vuelve a intentarlo: ");
					nota = keyboard.nextDouble();
					
				}
				
				sumaNotas += nota;
				
			}
			
			notasAlumnos.put(nombre, (sumaNotas/numEvaluaciones));
			
		}
		
	}
	
	private static void MostrarNotas(Hashtable<String, Double> notasAlumnos) {
		
		Enumeration<String> llaves = notasAlumnos.keys();
		
		while (llaves.hasMoreElements()) {
			
			String key = llaves.nextElement();
			
			System.out.println("Nota media de " + key + ": " + notasAlumnos.get(key));
			
		}
		
	}

}
