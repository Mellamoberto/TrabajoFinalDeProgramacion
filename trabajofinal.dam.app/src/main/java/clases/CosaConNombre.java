package clases;

public class CosaConNombre {
	private String nombre;

	public CosaConNombre(String nombre) {
		super();
		this.nombre = nombre;
	}

	public CosaConNombre() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "CosaConNombre [nombre=" + nombre + "]";
	}

}
