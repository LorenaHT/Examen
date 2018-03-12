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
	
	public HashMap<String, ArrayList<String>> leerFicheroNotasAlumnos(String fichero) {
		HashMap<String, ArrayList<String>> alumnos = new HashMap<String, ArrayList<String>>();
		ArrayList<String> notas = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("#");
				String nombre = campos[0];
				notas.add(campos[1]);
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

	public HashMap<String,ArrayList<String>> leerNotas(String fichero) {
	HashMap<String,ArrayList<String>> resultado = new HashMap<String,ArrayList<String>>();
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

	public HashMap<Integer, LinkedHashMap<Integer, ArrayList<String>>> LeerFicheroHashMapIntegerLinkedHashMap(
			String ficheroComunidades, String ficheroProvincias) {
		HashMap<Integer, LinkedHashMap<Integer, ArrayList<String>>> resultado = new HashMap<Integer, LinkedHashMap<Integer, ArrayList<String>>>();
		String[] comunidades = LeerFicheroArrayString(ficheroComunidades);
		HashMap<Integer, String> provincia = LeerFicheroHashMapIntegerString(ficheroProvincias);
		Set<Integer> claves = provincia.keySet();
		System.out.println("Comunidades Autonomas");
		for (int i = 1; i < comunidades.length; i++) {
			System.out.println("Comunidad Autonoma: " + comunidades[i]);
			LinkedHashMap<Integer, ArrayList<String>> provincias = new LinkedHashMap<Integer, ArrayList<String>>();
			for (int clave : claves) {
				ArrayList<String> datos = new ArrayList<String>();
				String[] campos = provincia.get(clave).split("#");
				datos.add(campos[0]);
				datos.add(campos[2]);
				if (Integer.parseInt(campos[1]) == i) {
					provincias.put(clave, datos);
				}
			}
			resultado.put(i, provincias);
		}
		System.out.println(resultado);
		return resultado;
	}

}
