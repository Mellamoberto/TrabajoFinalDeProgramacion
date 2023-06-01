package clases;

import java.util.TreeSet;

public abstract class EmpresaVideojuegos extends CosaConNombre{
	private TreeSet<Videojuego> listaVideojuegos;

	public EmpresaVideojuegos(String nombre, TreeSet<Videojuego> listaVideojuegos) {
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
		return "EmpresaVideojuegos [listaVideojuegos=" + listaVideojuegos + "]";
	}
	
	
	
	public String mostrarListaVideojuegos (TreeSet<Videojuego> listaVideojuegos) {
		String msg ="Juegos producidos por la empresa "+this.getNombre()+":"+listaVideojuegos;
		return msg;
	}
	
	
}
