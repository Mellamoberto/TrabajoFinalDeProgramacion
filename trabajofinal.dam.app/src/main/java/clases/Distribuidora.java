package clases;

import java.util.TreeSet;

public class Distribuidora extends CosaConNombre {
	private TreeSet<Videojuego> listaVideojuegos;

	
	public Distribuidora(String nombre, TreeSet<Videojuego> listaVideojuegos) {
		super(nombre);
		this.listaVideojuegos = listaVideojuegos;
	}


	public TreeSet<Videojuego> getListaVideojuegos() {
		return listaVideojuegos;
	}


	public void setListaVideojuegos(TreeSet<Videojuego> listaVideojuegos) {
		this.listaVideojuegos = listaVideojuegos;
	}


	@Override
	public String toString() {
		return "Distribuidora [listaVideojuegos=" + listaVideojuegos + "]";
	}
	
	
	
}
