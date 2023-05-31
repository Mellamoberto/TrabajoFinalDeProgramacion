package trabajofinal.dam.app;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeSet;
import java.util.List;

import clases.Usuario;
import clases.Videojuego;
import excepciones.PassInvalidaException;
import excepciones.UsuarioNoExisteException;
import interfaces.Ventana;
import utils.DAO;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ventana v = new Ventana();
		
			Videojuego videojuego1 = new Videojuego("God of War (2018)");
			Videojuego videojuego2 = new Videojuego("Pokemon");
			
			try {
				// Calcular la puntuaci�n media del videojuego
				float puntuacionMedia = videojuego1.puntuacionMedia();
				float puntuacionMedia2 = videojuego2.puntuacionMedia();
				
				// Imprimir la puntuaci�n media
				System.out.println("Puntuaci�n media: " + puntuacionMedia);
				System.out.println("Puntuaci�n media: " + puntuacionMedia2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			 // Comparar los videojuegos utilizando el m�todo compareTo
		    int resultado = videojuego1.compareTo(videojuego2);

		    // Imprimir el resultado de la comparaci�n
		    if (resultado < 0) {
		        System.out.println(videojuego1.getNombre() + " es mejor que " + videojuego2.getNombre());
		    } else if (resultado > 0) {
		        System.out.println(videojuego2.getNombre() + " es mejor que " + videojuego1.getNombre());
		    } else {
		        System.out.println("Ambos videojuegos tienen la misma puntuaci�n media");
		    }
		






	
	
	
	
}
