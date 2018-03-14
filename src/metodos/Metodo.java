package metodos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Metodo {

	// Solucion mia : 10

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
		System.out.println();
		notasMediasAlumnos(datos);
		System.out.println("-----------------");
		notasMediasAsignaturas(datos);

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

	public void notasMediasAlumnos(HashMap<String, ArrayList<Integer>> datos) {
		ArrayList<String> listaAlumnos = new ArrayList<String>();
		Set<String> alumnos = datos.keySet();
		for (String alumno : alumnos) {
			listaAlumnos.add(alumno);
		}
		System.out.println();
		ordenarListaAlumnos(listaAlumnos);
		for (String alumno : listaAlumnos) {
			float nota = 0;
			for (int i = 0; i < datos.get(alumno).size(); i++) {
				nota += datos.get(alumno).get(i);
			}
			float notaMedia = nota / alumnos.size();
			System.out.println("Nota media de " + alumno + " : " + notaMedia + "\t");
		}

	}

	public void notasMediasAsignaturas(HashMap<String, ArrayList<Integer>> datos) {
		String[] asignaturas = leerFicheroAsignaturas("ficheros/asignaturas.txt");
		Set<String> alumnos = datos.keySet();
		for (int i = 0; i < asignaturas.length; i++) {
			float nota = 0;
			for (String alumno : alumnos) {
				nota += datos.get(alumno).get(i);
			}
			float notaMedia = nota / alumnos.size();
			System.out.println("Nota media de " + asignaturas[i] + " : " + notaMedia + "\t");
		}

	}

	// Solucion profe:

	public ArrayList<String> getAsignaturas(String ficheroAsignaturas) {
		ArrayList<String> asignaturas = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(ficheroAsignaturas);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] asigs = linea.split(",");
				for (String asig : asigs)
					asignaturas.add(asig);
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
		return asignaturas;
	}

	public static void main(String[] args) {
		Metodo metodo = new Metodo();
		ArrayList<String> asignaturas = metodo.getAsignaturas("ficheros/asignaturas.txt");
		System.out.println(asignaturas);
		HashMap<String, ArrayList<Integer>> notas = metodo.getNotas("ficheros/notasAlumnos.txt");
		System.out.println(notas);
		metodo.listado();
	}

	public HashMap<String, ArrayList<Integer>> getNotas(String ficheroNotas) {
		HashMap<String, ArrayList<Integer>> notas = new HashMap<String, ArrayList<Integer>>();
		try {
			FileReader fr = new FileReader(ficheroNotas);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("#");
				String clave = campos[0];
				String[] notasString = campos[1].split(",");
				ArrayList<Integer> valor = new ArrayList<Integer>();
				for (String nt : notasString)
					valor.add(Integer.parseInt(nt));
				notas.put(clave, valor);
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
		return notas;
	}

	public void listado() {
		ArrayList<String> asignaturas = getAsignaturas("ficheros/asignaturas.txt");
		HashMap<String, ArrayList<Integer>> notas = getNotas("ficheros/notasAlumnos.txt");
		Set<String> claves = notas.keySet();
		System.out.print("\t\t");
		for (String asignatura : asignaturas)
			System.out.print(asignatura + "\t");
		System.out.println("");
		int[] notaMediaAsignaturas = new int[asignaturas.size()];
		for (String clave : claves) {
			System.out.print(clave + " :  \t");
			ArrayList<Integer> listaNotasAlumno = notas.get(clave);
			int acum = 0;
			for (int i = 0; i < listaNotasAlumno.size(); i++) {
				System.out.print(listaNotasAlumno.get(i) + "\t");
				acum += listaNotasAlumno.get(i);
				notaMediaAsignaturas[i] += listaNotasAlumno.get(i);
			}
			System.out.print((float) acum / listaNotasAlumno.size());
			System.out.println("");

		}
		System.out.print("\t\t\t\t");
		for (int i : notaMediaAsignaturas)
			System.out.print((float) i / asignaturas.size() + "\t");

		System.out.println();

	}

}
