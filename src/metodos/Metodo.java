package metodos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

	public HashMap<String, ArrayList<Integer>> leerNotas(String fichero) {
		HashMap<String, ArrayList<Integer>> resultado = new HashMap<String, ArrayList<Integer>>();
		HashMap<String, String> datos = leerFicheroNotasAlumnos(fichero);
		Set<String> alumnos = datos.keySet();
		for (String alumno : alumnos) {
			ArrayList<Integer> notas = new ArrayList<Integer>();
			String nota = datos.get(alumno);
			String[] campos = nota.split(",");
			for (String campo : campos) {
				notas.add(Integer.parseInt(campo));
			}
			resultado.put(alumno, notas);
		}
		return resultado;
	}

	public void listarAlumnos(HashMap<String, ArrayList<Integer>> datos) {
		String[] asignaturas = leerFicheroAsignaturas("ficheros/asignaturas.txt");
		ArrayList<String> listaAlumnos = new ArrayList<String>();
		Set<String> alumnos = datos.keySet();
		for (String asignatura : asignaturas) {
			System.out.print("\t\t" + asignatura);
		}
		for (String alumno : alumnos) {
			listaAlumnos.add(alumno);
		}
		System.out.println();
		ordenarListaAlumnos(listaAlumnos);
		for (int i = 0; i < listaAlumnos.size(); i++) {
			System.out.print("\n" + listaAlumnos.get(i));
			for (int j = 0; j < asignaturas.length; j++)
				System.out.print("\t\t" + datos.get(listaAlumnos.get(i)).get(j));
			
		}
		notasMediasAlumnos(datos);
	}

	public void ordenarListaAlumnos(ArrayList<String> listaAlumnos) {
		for (int i = 0; i < listaAlumnos.size() - 1; i++)
			for (int j = i + 1; j < listaAlumnos.size(); j++)
				if (listaAlumnos.get(i).compareTo(listaAlumnos.get(j)) > 0) {
					String aux = listaAlumnos.get(i);
					listaAlumnos.set(i, listaAlumnos.get(j));
					listaAlumnos.set(j, aux);
				}

	}
	
	public void notasMediasAlumnos (HashMap<String, ArrayList<Integer>> datos) {
		Set<String> alumnos = datos.keySet();
		for (String alumno : alumnos) {
			float nota = 0;
			for (int i=0; i<datos.get(alumno).size(); i++) {
				nota += datos.get(alumno).get(i);
			}
			float notaMedia = nota/alumnos.size();
			System.out.println(notaMedia);
		}
		
	}
	
	
}
