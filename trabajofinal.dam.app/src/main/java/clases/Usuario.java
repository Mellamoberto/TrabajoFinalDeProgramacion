package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedHashSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import excepciones.PassInvalidaException;
import excepciones.UsuarioNoExisteException;
import excepciones.VideojuegoNoExisteException;
import utils.DAO;

public class Usuario extends CosaConNombre implements Comparable<Usuario> {
	private String email;
	private String pass;
	private LocalDate fechaRegistro;
	private TreeSet<Videojuego> videojuegosJugados;
	private TreeSet<Videojuego> videojuegosPendientes;
	private TreeSet<Videojuego> videojuegosFavoritos;
	private boolean esModerador;
	private TreeSet<Review> reviews;
	private TreeMap<String, Usuario> amigos;
	

	public Usuario(String nombre, String email, String pass, LocalDate fechaRegistro, boolean esModerador) throws SQLException,
	SQLIntegrityConstraintViolationException {
		super(nombre);
		this.email = email;
		this.pass = pass;
		this.fechaRegistro = fechaRegistro;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaRegistroStr = fechaRegistro.format(formatter);
		HashMap<String, Object> columnas = new HashMap<String, Object>();
		columnas.put("nombre", nombre);
		columnas.put("email", email);
		columnas.put("pass", pass);
		columnas.put("fechaRegistro", fechaRegistro);
		columnas.put("esModerador", esModerador);
		DAO.insertar("usuario", columnas);
	}
	

	public Usuario(String email, String pass) throws SQLException, UsuarioNoExisteException, PassInvalidaException,
			SQLIntegrityConstraintViolationException {
		this.email = email;
		this.pass = pass;
		this.login(this);
	}
		

		
	
	
	public Usuario(String nombre) {
		// TODO Auto-generated constructor stub
	}


	public void login (Usuario user) throws SQLException, UsuarioNoExisteException, PassInvalidaException {
		HashMap<String, Object> hM = new HashMap<>();
        hM.put("email", this.getEmail());
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
        
        if (consulta.isEmpty()) {
			throw new UsuarioNoExisteException("El usuario con email " + email + " no existe.");
		} else {
			Object objPass = consulta.get(2);
			String contrasenhaBD = objPass.toString();

			if (!contrasenhaBD.equals(pass)) {
				throw new PassInvalidaException(
						"La contrasenha proporcionada no coincide con la registrada en la base de datos.");
			} else {
				Object objEmail = consulta.get(0);
				this.email = objEmail.toString();
			}
		}

        if (!consulta.isEmpty()) {                
            Object objNombre = consulta.get(0);
            this.setNombre(objNombre.toString());
                
            Object objEmail = consulta.get(1);
            this.email = objEmail.toString();
                
            Object objPass = consulta.get(2);
            this.pass = objPass.toString(); 
            
            Object objFechaRegistro = consulta.get(3);
            this.pass = objFechaRegistro.toString(); 
            
            Object objEsModerador = consulta.get(4);
            this.pass = objEsModerador.toString(); 
            }
        }
	
	
	public void hacerReview(Videojuego videojuego) throws SQLException {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Puntuacion del videojuego");
	    float calificacion = Float.parseFloat(sc.nextLine());

	    System.out.println("Comentario");
	    String comentario = sc.nextLine();

	    System.out.println("Duracion estimada");
	    int duracion = Integer.parseInt(sc.nextLine());

	    String usuarioEmail = this.getEmail();

	    LocalDateTime fechaActual = LocalDateTime.now();

	    Review review = new Review(0, email, calificacion, fechaActual, comentario, duracion);
	    
	    videojuego.agregarReview(review);

	    HashMap<String, Object> columnas = new HashMap<String, Object>();
	    columnas.put("usuario_email", email);
	    columnas.put("videojuego_nombre", videojuego.getNombre());
	    columnas.put("calificacion", calificacion);
	    columnas.put("comentario", comentario);
	    columnas.put("duracion", duracion);
	    columnas.put("fecha_calificacion", fechaActual);

	    DAO.insertar("review", columnas);
	}
	
	
	
	public void meterVideojuegoEnLista(Videojuego videojuego, TreeSet<Videojuego>listaVideojuego ) throws SQLException {
		Scanner sc = new Scanner (System.in);
		System.out.println("En que lista lo quieres meter:"
				+ "1-Jugados"
				+ "2-Pendientes"
				+ "3-Favoritos");
		byte lista = Byte.parseByte(sc.nextLine());
	    switch (lista) {
	        case 1:
	            if (videojuegosJugados == null) {
	                videojuegosJugados = new TreeSet<>();
	                videojuegosJugados.add(videojuego);
	            }else {
	            videojuegosJugados.add(videojuego);
	            }
	            break;
	        case 2:
	            if (videojuegosPendientes == null) {
	                videojuegosPendientes = new TreeSet<>();
	                videojuegosPendientes.add(videojuego);
	            }else {
	            videojuegosPendientes.add(videojuego);
	            }
	            break;
	        case 3:
	            if (videojuegosFavoritos == null) {
	                videojuegosFavoritos = new TreeSet<>();
	                videojuegosFavoritos.add(videojuego);
	            } else {
	            videojuegosFavoritos.add(videojuego);
	            }
	            break;
	        default:
	            System.out.println("Lista no valida.");
	    }
	    
	    HashMap<String, Object> columnas = new HashMap<String, Object>();
	    columnas.put("email", email);
		columnas.put("nombre_videojuego", videojuego.getNombre());

	    DAO.insertar("videojuegos_favoritos", columnas);
	    
	}
	
	
	
	public void consultarListaVideojuego(TreeSet<Videojuego>listaVideojuego ) throws SQLException, VideojuegoNoExisteException {
		Scanner sc = new Scanner (System.in);
		System.out.println("Que lista quieres consultar:"
				+ "1-Favoritos"
				+ "2-Actual"
				);
		byte lista = Byte.parseByte(sc.nextLine());
		switch (lista) {
		case 1:
		    HashMap<String, Object> hM = new HashMap<>();
		    hM.put("email", this.email);
		    
		    ArrayList<Object> consulta = DAO.consultar("videojuegos_favoritos", new LinkedHashSet<String>() {
		        {
		            add("nombre_videojuego");
		        }
		    }, hM);
		    
		    if (consulta.isEmpty()) {
		        throw new VideojuegoNoExisteException("El videojuego con el nombre " + super.getNombre() + " no existe.");
		    } else {
		        Iterator<Object> iterator = consulta.iterator();
		        while (iterator.hasNext()) {
		            Object obj = iterator.next();
		            for (byte i=0; i<consulta.size();i++) {
		                String objNombre = (String) consulta.get(i);
		                this.setNombre(objNombre);
		               System.out.println(this.getNombre());
		            }
		                break;
		        }
		    }
		    break;
		case 2:
		    break;
		}
	}
	
	
	public void buscarVideojuego (Videojuego videojuego) throws SQLException, VideojuegoNoExisteException {
		HashMap<String, Object> hM = new HashMap<>();
		hM.put("nombre", videojuego.getNombre());

		LinkedHashSet<String> columnas = new LinkedHashSet<String>() {
			{
				add("nombre");
			}
		};
		ArrayList<Object> consulta = DAO.consultar("videojuego", columnas, hM);
		
		if (consulta.isEmpty()) {
			throw new VideojuegoNoExisteException ("Ese videojuego no se encuentra en la pagina");
		}else {
			Iterator<Object> iterator = consulta.iterator();
			while (iterator.hasNext()) {
				Object obj = iterator.next();
				if (obj instanceof Usuario) {
					Usuario usuario = (Usuario) obj;
					Object objNombre = consulta.get(0);
					videojuego.setNombre(objNombre.toString());
				}
			}
		}
	}
	
	
	public void meterVideojuegoEnBD (Videojuego videojuego) throws SQLException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce el nombre del videojuego");
		String nombre = sc.nextLine();
		videojuego.setNombre(nombre);
		
		System.out.println("Introduce una descripcion");
		String descripcion = sc.nextLine();
		videojuego.setDescripcion(descripcion);
		
	    System.out.println("Introduce la fecha (formato: yyyy-MM-dd):");
	    String fechaStr = sc.nextLine();
	    
	    LocalDate fecha;
	    try {
	        fecha = LocalDate.parse(fechaStr);
	        System.out.println("Fecha ingresada: " + fecha);
	    } catch (DateTimeParseException e) {
	        System.out.println("Fecha ingresada en un formato inválido.");
	    }

		
		HashMap<String, Object> columnas = new HashMap<String, Object>();
		columnas.put("nombre", nombre);
		columnas.put("descripcion", descripcion);
		columnas.put("lanzamiento", fechaStr);
//		columnas.put("genero", videojuego.getGenero());
//		columnas.put("plataforma", videojuego.getPlataforma());

		DAO.insertar("Videojuego", columnas);
	}


	
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public TreeSet<Videojuego> getVideojuegosJugados() {
		return videojuegosJugados;
	}

	public void setVideojuegosJugados(TreeSet<Videojuego> videojuegosJugados) {
		this.videojuegosJugados = videojuegosJugados;
	}

	public TreeSet<Videojuego> getVideojuegosPendientes() {
		return videojuegosPendientes;
	}

	public void setVideojuegosPendientes(TreeSet<Videojuego> videojuegosPendientes) {
		this.videojuegosPendientes = videojuegosPendientes;
	}

	public TreeSet<Videojuego> getVideojuegosFavoritos() {
		return videojuegosFavoritos;
	}

	public void setVideojuegosFavoritos(TreeSet<Videojuego> videojuegosFavoritos) {
		this.videojuegosFavoritos = videojuegosFavoritos;
	}

	public boolean isEsModerador() {
		return esModerador;
	}

	public void setEsModerador(boolean esModerador) {
		this.esModerador = esModerador;
	}

	public TreeSet<Review> getReviews() {
		return reviews;
	}

	public void setReviews(TreeSet<Review> reviews) {
		this.reviews = reviews;
	}

	public TreeMap<String, Usuario> getAmigos() {
		return amigos;
	}

	public void setAmigos(TreeMap<String, Usuario> amigos) {
		this.amigos = amigos;
	}

	@Override
	public String toString() {
		return super.getNombre() + "\nEmail = " + email + ", \nPass =" + pass + ", \nFecha de registro: "
				+ fechaRegistro + "";
		/*
		 * + ", \nvideojuegosJugados=" + videojuegosJugados +
		 * ", \nvideojuegosPendientes=" + videojuegosPendientes +
		 * ", \nvideojuegosFavoritos=" + videojuegosFavoritos + ", \nesModerador=" +
		 * esModerador + ", \nreviews=" + reviews + ", \namigos=" + amigos + "]";
		 */
	}

	public int compareTo(Usuario o) {
		return (int) (this.getEmail().compareTo(o.getEmail()));
	}

}
