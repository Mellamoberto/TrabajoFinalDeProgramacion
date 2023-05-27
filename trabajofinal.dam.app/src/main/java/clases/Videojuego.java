package clases;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedHashSet;

import enums.GeneroVideojuego;
import enums.PlataformaVideojuego;
import excepciones.PassInvalidaException;
import excepciones.UsuarioNoExisteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import utils.DAO;

public class Videojuego extends CosaConNombre implements Comparable<Videojuego> {
	private String descripcion;
	private String lanzamiento;
	private Desarrolladora desarrolladora;
	private Distribuidora distribuidora;
	private ArrayList<Review> reviews;
	private int numComentarios;
	private GeneroVideojuego genero;
	private PlataformaVideojuego plataforma;

	public float puntuacionMedia() {
		float sumatoriaPuntuaciones = 0;
		for (short i = 0; i < reviews.size(); i++) {
			sumatoriaPuntuaciones += reviews.get(i).getCalificacion();
		}
		return sumatoriaPuntuaciones / reviews.size();
	}

	public Videojuego(String nombre, String descripcion, String lanzamiento, Desarrolladora desarrolladora,
			Distribuidora distribuidora, ArrayList<Review> reviews, int numComentarios, GeneroVideojuego genero,
			PlataformaVideojuego plataforma) {
		super(nombre);
		this.descripcion = descripcion;
		this.lanzamiento = lanzamiento;
		this.desarrolladora = desarrolladora;
		this.distribuidora = distribuidora;
		this.reviews = reviews;
		this.numComentarios = 0;
		this.genero = genero;
		this.plataforma = plataforma;
	}

	public Videojuego(String nombre, String descripcion, String lanzamiento) throws SQLException {
		super(nombre);
		this.descripcion = descripcion;
		this.lanzamiento = lanzamiento;
		this.numComentarios = 0;
		HashMap<String, Object> columnas = new HashMap<String, Object>();
		columnas.put("nombre", nombre);
		columnas.put("descripcion", descripcion);
		columnas.put("lanzamiento", lanzamiento);
		columnas.put("numComentarios", numComentarios);
		DAO.insertar("videojuego", columnas);
	}

	public Videojuego(String nombre) throws SQLException, UsuarioNoExisteException {
		super(nombre);
		this.reviews = new ArrayList<>();

		HashMap<String, Object> hM = new HashMap<>();
		hM.put("nombre", nombre);

		LinkedHashSet<String> columnas = new LinkedHashSet<String>() {
			{
				add("nombre");
			}
		};

		ArrayList<Object> consulta = DAO.consultar("videojuego", columnas, hM);

		if (consulta.isEmpty()) {
			throw new UsuarioNoExisteException("El videojuego con el nombre " + super.getNombre() + " no existe.");
		} else {
			Iterator<Object> iterator = consulta.iterator();
			while (iterator.hasNext()) {
				Object obj = iterator.next();
				if (obj instanceof Usuario) {
					Usuario usuario = (Usuario) obj;
					Object objNombre = consulta.get(0);
					this.setNombre(objNombre.toString());
				}
			}
		}
	}

	public void agregarReview(Review review) {
		if (reviews == null) {
			reviews = new ArrayList<>();
		}

		reviews.add(review);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLanzamiento() {
		return lanzamiento;
	}

	public void setLanzamiento(String lanzamiento) {
		this.lanzamiento = lanzamiento;
	}

	public Desarrolladora getDesarrolladora() {
		return desarrolladora;
	}

	public void setDesarrolladora(Desarrolladora desarrolladora) {
		this.desarrolladora = desarrolladora;
	}

	public Distribuidora getDistribuidora() {
		return distribuidora;
	}

	public void setDistribuidora(Distribuidora distribuidora) {
		this.distribuidora = distribuidora;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public int getNumComentarios() {
		return numComentarios;
	}

	public void setNumComentarios(int numComentarios) {
		this.numComentarios = numComentarios;
	}

	public GeneroVideojuego getGenero() {
		return genero;
	}

	public void setGenero(GeneroVideojuego genero) {
		this.genero = genero;
	}

	public PlataformaVideojuego getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(PlataformaVideojuego plataforma) {
		this.plataforma = plataforma;
	}

	@Override
	public String toString() {
		return super.getNombre() + "\n\t" + descripcion + " '" + "\n\tLanzado el:" + lanzamiento + "Desarrollado por:"
				+ desarrolladora + "Distribuido por:" + distribuidora + "\n\tGenero: " + genero + "\n\tPlataforma: "
				+ plataforma + "Reviews=" + reviews + "Numero de Comentarios=" + numComentarios;
	}

	public int compareTo(Videojuego o) {
		// TODO Auto-generated method stub
		if (this.puntuacionMedia() == o.puntuacionMedia()) {
			return this.getNombre().compareTo(o.getNombre());
		}
		return (int) (this.puntuacionMedia() - o.puntuacionMedia());
	}

}
