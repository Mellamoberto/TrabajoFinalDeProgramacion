package clases;

import java.util.TreeSet;

public class Desarrolladora extends EmpresaVideojuegos {
	private TreeSet<Videojuego> listaVideojuegos;

	public Desarrolladora(String nombre, TreeSet<Videojuego> listaVideojuegos) {
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
		return "Desarrolladora [listaVideojuegos=" + listaVideojuegos + "]";
	}

	public String mostrarListaVideojuegos(TreeSet<Videojuego> listaVideojuegos) {
		String msg = "Juegos desarrollados por " + this.getNombre() + ":" + listaVideojuegos;
		return msg;
	}

}
