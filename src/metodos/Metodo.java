package metodos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class Metodo {

	public String[] leerFicheroAsignaturas(String fichero) {
		String[] resultado = new String[5];
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split(",");
				for (int i = 0; i < campos.length; i++)
					resultado[i] = campos[i];
			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: " + e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Error: " + e.getMessage());
		}
		return resultado;
	}

	public HashMap<String, String> leerFicheroNotasAlumnos(String fichero) {
		HashMap<String, String> alumnos = new HashMap<String, String>();
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("#");
				String nombre = campos[0];
				String notas = campos[1];
				alumnos.put(nombre, notas);
			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: " + e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Error: " + e.getMessage());
		}
		return alumnos;
	}

	public HashMap<String, ArrayList<String>> leerNotas(String fichero) {
		HashMap<String, ArrayList<String>> resultado = new HashMap<String, ArrayList<String>>();
		HashMap<String, String> datos = leerFicheroNotasAlumnos(fichero);
		Set<String> alumnos = datos.keySet();
		for (String alumno : alumnos) {
			ArrayList<String> notas = new ArrayList<String>();
			String nota = datos.get(alumno);
			String[] campos = nota.split(",");
			for (String campo : campos) {
				notas.add(campo);
			}
			resultado.put(alumno, notas);
		}
		return resultado;
	}

	public void listarAlumnos(HashMap<String, ArrayList<String>> datos) {
		String[] asignaturas = leerFicheroAsignaturas("ficheros/asignaturas.txt");
		ArrayList<String> listaAlumnos = new ArrayList<String>();
		Set<String> alumnos = datos.keySet();
		for (String asignatura : asignaturas) {
			System.out.print("\t\t\t" + asignatura);
		}
		for (String alumno : alumnos) {
			listaAlumnos.add(alumno);
		}
		System.out.println();
		for (int i =0; i<listaAlumnos.size(); i++)
		System.out.println("\n"+listaAlumnos.get(i));
	}

}
