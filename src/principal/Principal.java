package principal;

import metodos.Metodo;

public class Principal {
	
	public static void main(String[] args) {
		Metodo metodos = new Metodo();
		String[] asignaturas = metodos.leerFicheroAsignaturas("ficheros/asignaturas.txt");
		for (int i = 0; i<asignaturas.length; i++)
		System.out.println(asignaturas[i]);
	}

}
