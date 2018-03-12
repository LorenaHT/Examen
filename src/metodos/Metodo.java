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
				for (int i=0; i<campos.length; i++) 
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
	
	/*public HashMap<Integer, String> LeerFicheroHashMapIntegerString(String fichero) {
		HashMap<Integer, String> provincias = new HashMap<Integer, String>();
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("%");
				int id = Integer.parseInt(campos[0]);
				String cadena = campos[1] + "#" + campos[2] + "#" + campos[3];
				provincias.put(id, cadena);
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
		return provincias;
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
	}*/

}
