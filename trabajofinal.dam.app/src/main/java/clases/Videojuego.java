package clases;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.LinkedHashSet;

import enums.GeneroVideojuego;
import enums.PlataformaVideojuego;
import excepciones.PassInvalidaException;
import excepciones.UsuarioNoExisteException;
import excepciones.VideojuegoNoExisteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import utils.DAO;

public class Videojuego extends CosaConNombre implements Comparable<Videojuego> {
	private float nota;
	private String descripcion;
	private LocalDate lanzamiento;
	private Desarrolladora desarrolladora;
	private Distribuidora distribuidora;
	private ArrayList<Review> reviews;
	private GeneroVideojuego genero;
	private PlataformaVideojuego plataforma;
	private String imagen;

//	public static ArrayList<Videojuego> busqueda(String nombre){
	// Usa constructor con el like para que te devuelva aqui todos los juegos que
	// contengan
	// El texto pasado por argumentos. Usa la misma t�cnica que getTodos
//	}

	public Videojuego(String nombre, float nota, String descripcion, LocalDate lanzamiento, Distribuidora distribuidora,
			Desarrolladora desarrolladora, GeneroVideojuego genero, PlataformaVideojuego plataforma, String imagen) {
		super(nombre);
		this.nota = nota;
		this.descripcion = descripcion;
		this.lanzamiento = lanzamiento;
		this.genero = genero;
		this.plataforma = plataforma;
		this.imagen = imagen;
	}

	public Videojuego(String nombre, String descripcion, LocalDate lanzamiento, GeneroVideojuego genero,
			PlataformaVideojuego plataforma) {
		super(nombre);
		this.descripcion = descripcion;
		this.lanzamiento = lanzamiento;
		this.genero = genero;
		this.plataforma = plataforma;
	}
	
	

	public float puntuacionMedia() throws SQLException {
		float nota = 0;

		LinkedHashSet<String> columnasSelect = new LinkedHashSet<>();
		columnasSelect.add("calificacion");

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("videojuego_nombre", this.getNombre());

		ArrayList<Object> resultado = DAO.consultarCalificacion("review", columnasSelect, restricciones);

		float sumatoriaPuntuaciones = 0;
		int cantidadCalificaciones = resultado.size();
		for (Object obj : resultado) {
			if (obj instanceof Float) {
				sumatoriaPuntuaciones += (Float) obj;
			} else if (obj instanceof Integer) {
				sumatoriaPuntuaciones += (Integer) obj;
			}
		}

		if (cantidadCalificaciones > 0) {
			nota = sumatoriaPuntuaciones / cantidadCalificaciones;
		}

		return nota;
	}
	
	

	public Videojuego(String nombre, String descripcion, LocalDate lanzamiento) throws SQLException {
		super(nombre);
		this.descripcion = descripcion;
		this.lanzamiento = lanzamiento;
		HashMap<String, Object> columnas = new HashMap<String, Object>();
		columnas.put("nombre", nombre);
		columnas.put("descripcion", descripcion);
		columnas.put("lanzamiento", lanzamiento);
		columnas.put("genero", this.getGenero());
		columnas.put("plataforma", this.getPlataforma());
		DAO.insertar("videojuego", columnas);
	}

	public Videojuego(String nombre, float nota, GeneroVideojuego genero, PlataformaVideojuego plataforma)
			throws SQLException {
		super(nombre);
		this.nota = puntuacionMedia();
		this.genero = genero;
		this.plataforma = plataforma;
	}

	public Videojuego(String nombre) {
		super(nombre);
	}

	public static ArrayList<Videojuego> getTodos() throws SQLException {
		LinkedHashSet<String> columna = new LinkedHashSet<>();
		columna.add("nombre");
		columna.add("nota");
		columna.add("genero");
		columna.add("plataforma");
		columna.add("lanzamiento");
		columna.add("desarrolladora");
		columna.add("distribuidora");

		HashMap<String, Object> restricciones = new HashMap<>();

		ArrayList<Object> listaVideojuegos = DAO.consultar("videojuego", columna, restricciones);

		ArrayList<Videojuego> videojuegos = new ArrayList<>();
		for (int i = 0; i < listaVideojuegos.size(); i += 7) {
			String nombre = (String) listaVideojuegos.get(i);
			float nota = ((Integer) listaVideojuegos.get(i + 1)).floatValue();
			GeneroVideojuego genero = GeneroVideojuego.valueOf((String) listaVideojuegos.get(i + 2));
			PlataformaVideojuego plataforma = PlataformaVideojuego.valueOf((String) listaVideojuegos.get(i + 3));

			Videojuego videojuego = new Videojuego(nombre, nota, genero, plataforma);
			videojuegos.add(videojuego);
		}

		return videojuegos;
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

	public LocalDate getLanzamiento() {
		return lanzamiento;
	}

	public void setLanzamiento(LocalDate lanzamiento) {
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

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return super.getNombre() + "\n\t" + descripcion + " '" + "\n\tLanzado el:" + lanzamiento + "Desarrollado por:"
				+ desarrolladora + "Distribuido por:" + distribuidora + "\n\tGenero: " + genero + "\n\tPlataforma: "
				+ plataforma + "Reviews=" + reviews;
	}

	public int compareTo(Videojuego o) {
		try {
			if (this.puntuacionMedia() == o.puntuacionMedia()) {
				return o.getNombre().compareTo(this.getNombre());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			return (int) (o.puntuacionMedia() - this.puntuacionMedia());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
