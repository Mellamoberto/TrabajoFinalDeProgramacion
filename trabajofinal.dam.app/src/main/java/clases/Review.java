package clases;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Review extends CosaConNombre {
	private int id;
	private float calificacion;
	private LocalDateTime fechaCalificacion;
	private String comentario;
	private int duracion;
	private Usuario usuario;

	public Review(int id, String nombre, float calificacion, LocalDateTime fechaCalificacion, String comentario,
			int duracion) {
		super(nombre);
		this.id = id;
		this.calificacion = calificacion;
		this.fechaCalificacion = fechaCalificacion;
		this.comentario = comentario;
		this.duracion = duracion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFechaCalificacion() {
		return fechaCalificacion;
	}

	public void setFechaCalificacion(LocalDateTime fechaCalificacion) {
		this.fechaCalificacion = fechaCalificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
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
		return "" + calificacion + "," + comentario + "," + duracion + "," + usuario + "";
	}

}
