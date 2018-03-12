package principal;

import java.util.ArrayList;
import java.util.HashMap;

import metodos.Metodo;

public class Principal {
	
	public static void main(String[] args) {
		Metodo metodos = new Metodo();
		String[] asignaturas = metodos.leerFicheroAsignaturas("ficheros/asignaturas.txt");
		/*for (int i = 0; i<asignaturas.length; i++)
		System.out.println(asignaturas[i]);*/
		HashMap<String, String> alumnos = metodos.leerFicheroNotasAlumnos("ficheros/notasAlumnos.txt");
		//System.out.println(alumnos);
		HashMap<String, ArrayList<Integer>> notas = metodos.leerNotas("ficheros/notasAlumnos.txt");
		//System.out.println(notas);
		metodos.listarAlumnos(notas);
	}
}
