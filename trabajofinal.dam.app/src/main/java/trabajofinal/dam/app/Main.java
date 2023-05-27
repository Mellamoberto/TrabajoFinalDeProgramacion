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
import utils.DAO;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		Usuario user = null;
		Usuario newUser = null;
		String email = null;
		Boolean esModerador = false;
		Videojuego newVideojuego = null;
		HashMap<String, Videojuego> listaDeVideojuegos = new HashMap<String, Videojuego>();
		TreeSet<Videojuego> listaDeJuegosFavoritos = new TreeSet<Videojuego>();
		byte ocp0 = 0;
		byte opc1 = 0;
		
		do {
		try {

		do {
			System.out.println("Bienvenido a Daily-Plays" + 
					"\n\t 1-Login" + 
					"\n\t 2-Registrar");

			opc1 = Byte.parseByte(sc.nextLine());
			switch (opc1) {
			case 1:
				String nombre = null;
				email = null;
				String pass = null;
				esModerador = false;
				user = null;
				do {
					System.out.print("Email: ");
					email = sc.nextLine();

					System.out.print("Password: ");
					pass = sc.nextLine();
					try {
						user = new Usuario(nombre,email,pass);
					} catch (SQLException | UsuarioNoExisteException | PassInvalidaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

					try {
						user.login(user);
						System.out.println("Usuario logueado con exito");
					} catch (IllegalArgumentException e) {
						System.out.println("Error: " + e.getMessage());
						System.out.println("Por favor, intentalo de nuevo");
					} catch (Exception e) {
						System.out.println("Error: " + e.getMessage());
						System.out.println("Por favor, intentalo de nuevo");
					}
					System.out.println();
				} while (user == null);

				System.out.println("");
				
											byte opc2 = 0;
									do {
										System.out.println("Daily-Plays" 
												+ "\n\t 1-Mi Usuario" 
												+ "\n\t 2-Insertar videojuego en la BD" 
												+ "\n\t 3-Insertar videojuego en una lista"
												+ "\n\t 4-Agregar review"
												+ "\n\t 5-Foro"
												+ "\n\t 6-Salir");
										System.out.println();


								
											opc2 = Byte.parseByte(sc.nextLine());
											switch (opc2) {
											case 1:
												byte opc3 = 0;
												do {
													System.out.println("GoodPlays" + "\n\t 1-Ver listas"
															+"\n\t 7-Atras");
													opc3 = Byte.parseByte(sc.nextLine());
													System.out.println();
													
													switch (opc3) {
													case 1:
														try {
															try {
																
																user.consultarListaVideojuego(listaDeJuegosFavoritos);
															} catch (UsuarioNoExisteException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
														} catch (SQLException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
														
													/*	
														TreeSet<Videojuego> videojuegosFavoritos = user.getVideojuegosFavoritos();

														System.out.println("Lista de videojuegos favoritos:");
											            for (Videojuego videojuego : videojuegosFavoritos) {
											                String nombreVideojuego = videojuego.getNombre();
											                System.out.println(nombreVideojuego);
											            }

											            if (listaDeJuegosFavoritos == null) {
											            	listaDeJuegosFavoritos = new TreeSet<>();
											            }

											            if (newVideojuego != null) {
											            	listaDeJuegosFavoritos.add(newVideojuego);
											            }
														*/
														break;
													}
						
												} while (opc3 != 7);
												break;
											case 2:
												System.out.println("Introduce el nombre del videojuego");
												nombre = sc.nextLine();
												System.out.println("Introduce una descripcion");
												email = sc.nextLine();
												System.out.println("Introduce la fecha de lanzamiento");
												String lanzamiento = sc.nextLine();
						
												try {
													newVideojuego = new Videojuego(nombre, email, lanzamiento);
												} catch (SQLException e2) {
													// TODO Auto-generated catch block
													e2.printStackTrace();
												}
						
												listaDeVideojuegos.put(nombre, newVideojuego);
						
												System.out.println("Videojuego registrado con exito");
						
												System.out.println();
						
												System.out.println(listaDeVideojuegos);
						
												System.out.println();
						
												break;
											case 3:
			
												try {
													System.out.println("Primero escribe el nombre del juego");
													String nombreVdg = sc.nextLine();
													Videojuego videojuegoElegido = new Videojuego (nombreVdg);
													
													user.meterVideojuegoEnLista(videojuegoElegido, listaDeJuegosFavoritos);
													
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (UsuarioNoExisteException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
												break;
											case 4:
												try {
												System.out.println("Escribe el nombre del juego del que quieres hacer una review");
												String nombreVdg = sc.nextLine();
												
													Videojuego videojuegoElegido = new Videojuego (nombreVdg);
													
													user.hacerReview(videojuegoElegido);
													
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (UsuarioNoExisteException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
												break;
											default:
												System.out.println("Opcion actualmente no disponible." + "Recomendamos salir (5)");
												break;
											}
									}while (opc2!=0);
								

			case 2:
				System.out.println("Introduce tu nombre de usuario");
				nombre = sc.nextLine();
				System.out.println("Introduce tu email");
				email = sc.nextLine();
				System.out.println("Introduce tu password");
				pass = sc.nextLine();
				LocalDate fechaRegistro = LocalDate.now();
				System.out.println("Es un usuario moderador? Escribe: true = si  /  false = no");
				esModerador = Boolean.parseBoolean(sc.nextLine());

				try {
					newUser = new Usuario(nombre, email, pass, fechaRegistro, esModerador);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				System.out.println("Usuario registrado con exito");

				System.out.println();

				String logLine = "Usuario " + newUser.getEmail() + " registrado con exito en: "
						+ LocalDateTime.now().toString() + "\n";
				try {
					Files.write(Paths.get("./usuarios.log"), logLine.getBytes(), StandardOpenOption.CREATE,
							StandardOpenOption.APPEND);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
		} while (opc1 != 0);
		}catch (NumberFormatException e) {
		    System.out.println("Error: Elige una opcion valida.");
		    System.out.println();
		}
		}while (ocp0==0);

	}

	
	public static void listaVideojuegosOrdenada (HashMap<String,Videojuego> videojuegosTotal) {
		float min = Integer.MIN_VALUE;
		List<Videojuego> listaVideojuego = new ArrayList<>();
		if(videojuegosTotal.isEmpty() || videojuegosTotal==null){
			return;
		} else {
			for(Map.Entry<String, Videojuego> videojuego : videojuegosTotal.entrySet()) {
				String key = videojuego.getKey();
				Videojuego value = videojuego.getValue();
				if(videojuegosTotal.entrySet().iterator().hasNext()) {
					listaVideojuego.add(value);
				}
				for(byte i=0; i<=listaVideojuego.size(); i++) {
					if(listaVideojuego.get(i).puntuacionMedia() >= min) {
						min = listaVideojuego.get(i).puntuacionMedia();
						System.out.println("Videojuegos "+key+" tiene puntuacion "+min);
					}
				}
			} 
		}
		
	}
	
	
	
	
	
	
}
