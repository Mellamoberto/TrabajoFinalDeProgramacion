package clases;

import java.util.TreeSet;

public class Distribuidora extends EmpresaVideojuegos {
	private TreeSet<Videojuego> listaVideojuegos;

	public Distribuidora(String nombre, TreeSet<Videojuego> listaVideojuegos) {
		super(nombre, listaVideojuegos);
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

	public String mostrarListaVideojuegos(TreeSet<Videojuego> listaVideojuegos) {
		String msg = "Juegos distribuidos por la empresa " + this.getNombre() + ":" + listaVideojuegos;
		return msg;
	}

}
