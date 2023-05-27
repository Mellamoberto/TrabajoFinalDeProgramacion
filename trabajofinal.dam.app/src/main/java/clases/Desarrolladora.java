package clases;

import java.util.TreeSet;

public class Desarrolladora extends CosaConNombre {
	private TreeSet<Videojuego> listaVideojuegos;
	
	

	public Desarrolladora(String nombre, TreeSet<Videojuego> listaVideojuegos) {
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
		return "Desarrolladora [listaVideojuegos=" + listaVideojuegos + "]";
	}
	
	
	
}
