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

		try {
			ArrayList<Videojuego> listaJuegos = Videojuego.getTodos();

			for (Videojuego juego : listaJuegos) {
				System.out.println("Nombre: " + juego.getNombre());
				System.out.println("Nota: " + juego.getNota());
				System.out.println("Genero: " + juego.getGenero());
				System.out.println("Plataforma: " + juego.getPlataforma());
				System.out.println("------------------------");

				try {
					float puntuacionMedia = juego.puntuacionMedia();
					System.out.println("Puntuación media de " + juego.getNombre() + ": " + puntuacionMedia);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
