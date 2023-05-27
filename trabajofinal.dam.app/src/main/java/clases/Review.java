package clases;

public class Review extends CosaConNombre {
	private byte id;
	private float calificacion; //De 0 a 10
	private String comentario;
	private int duracion;
	private Usuario usuario;
	
	
	
	public Review(String nombre, float calificacion, String comentario, int duracion, Usuario usuario) {
		super(nombre);
		this.calificacion = calificacion;
		this.comentario = comentario;
		this.duracion = duracion;
		this.usuario = usuario;
	}
	
	public Review(String nombre, float calificacion, String comentario, int duracion) {
		super(nombre);
		this.calificacion = calificacion;
		this.comentario = comentario;
		this.duracion = duracion;

	}
	
	
	



	public float getCalificacion() {
		return calificacion;
	}



	public void setCalificacion(byte calificacion) {
		this.calificacion = calificacion;
	}



	public String getComentario() {
		return comentario;
	}



	public void setComentario(String comentario) {
		this.comentario = comentario;
	}



	public int getDuracion() {
		return duracion;
	}



	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getNombreUsuario() {
		return usuario.getNombre();
	}



	@Override
	public String toString() {
		return "" + calificacion + "," + comentario + "," + duracion
				+ "," + usuario + "";
	}
	
	
	
}
