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
	
//	public static ArrayList<Videojuego> busqueda(String nombre){
		//Usa constructor con el like para que te devuelva aqui todos los juegos que contengan
		//El texto pasado por argumentos. Usa la misma técnica que getTodos
//	}

	public float puntuacionMedia() throws SQLException {
	    float nota = 0;

	    LinkedHashSet<String> columnasSelect = new LinkedHashSet<>();
	    columnasSelect.add("calificacion");

	    HashMap<String, Object> restricciones = new HashMap<>();
	    restricciones.put("videojuego_nombre", this.getNombre());

	    ArrayList<Object> resultado = DAO.consultar("review", columnasSelect, restricciones);

	    float sumatoriaPuntuaciones = 0;
	    for (Object obj : resultado) {
	        if (obj instanceof Float) {
	            sumatoriaPuntuaciones += (Float) obj;
	        } else if (obj instanceof Integer) {
	            sumatoriaPuntuaciones += (Integer) obj;
	        }
	    }

	    if (!resultado.isEmpty()) {
	        nota = sumatoriaPuntuaciones / resultado.size();
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
	
	

	public Videojuego (String nombre, float nota, GeneroVideojuego genero, PlataformaVideojuego plataforma) throws SQLException {
		super(nombre);
		this.nota = puntuacionMedia();
		this.genero = genero;
		this.plataforma = plataforma;
	}
	
	public Videojuego (String nombre, String descripcion, LocalDate lanzamiento, GeneroVideojuego genero, PlataformaVideojuego plataforma) {
		super(nombre);
		this.descripcion=descripcion;
		this.lanzamiento=lanzamiento;
		this.genero=genero;
		this.plataforma=plataforma;
	}
	

	
public Videojuego(String nombre) {
		super(nombre);
	}






/*	public List<Videojuego> mostrarVideojuego() {
		List<Videojuego> mostraVdj = new ArrayList<>();
		HashMap<String, Object> hM = new HashMap<>();
        hM.put("nombre", this.getEmail());
        LinkedHashSet<String> columnas = new LinkedHashSet<String>() {
        	{
        		add("nombre");
        		add("email");
        		add("pass");
        		add("fechaRegistro");
        		add("esModerador");
        	}
        };
        
        ArrayList<Object> consulta = DAO.consultar("usuario", columnas, hM);
	} 
	*/
	
	/*
	public static void mostrarDetallesVideojuego (Videojuego videojuego) {
		HashMap<String, Object> hM = new HashMap<>();
        hM.put("nombre", this.getEmail());
        LinkedHashSet<String> columnas = new LinkedHashSet<String>() {
        	{
        		add("nombre");
        		add("email");
        		add("pass");
        		add("fechaRegistro");
        		add("esModerador");
        	}
        }; 
	}*/

	
/*
	public static ArrayList<Videojuego> getTodos() throws SQLException {
		LinkedHashSet<String> columna = new LinkedHashSet<>();
		columna.add("nombre");
		columna.add("nota");
		columna.add("genero");
		columna.add("plataforma");
		HashMap<String,Object> restricciones = new HashMap<>();
		ArrayList<Videojuego> videojuegos = new ArrayList<>();
		ArrayList<Object> listaVideojuegos = new ArrayList<>();
		for (byte i=0; i<listaVideojuegos.size();i+=4) {
			Videojuego videojuego = new Videojuego(
					(String)listaVideojuegos.get(i),
					(float)listaVideojuegos.get(i+2),
					(GeneroVideojuego)listaVideojuegos.get(i),
					(PlataformaVideojuego)listaVideojuegos.get(i));
		videojuegos.add(videojuego);
		}
		return videojuegos;
	}

*/
	


	
	/*
	public Videojuego(String nombre) throws SQLException/*, UsuarioNoExisteException*/ {
		//super(nombre);
		//this.reviews = new ArrayList<>();
		
/*
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
		}*/
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
