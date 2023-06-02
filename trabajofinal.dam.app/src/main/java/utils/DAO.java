package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import clases.Desarrolladora;
import clases.Distribuidora;
import clases.Videojuego;
import enums.GeneroVideojuego;
import enums.PlataformaVideojuego;

public class DAO {
	private static Connection conexion;

	private static Statement conectar() {
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dailyPlay", "root", "admin");
			return conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private static void desconectar(Statement s) {
		try {
			s.close();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int insertar(String tabla, HashMap<String, Object> columnas) throws SQLException {
	    Statement smt = conectar();
	    String consulta = "insert into " + tabla + " (";
	    Iterator it = columnas.keySet().iterator();
	    while (it.hasNext()) {
	        consulta += it.next() + ",";
	    }
	    consulta = consulta.substring(0, consulta.length() - 1);
	    consulta += ") values (";
	    it = columnas.values().iterator();
	    while (it.hasNext()) {
	        Object elemento = it.next();
	        if (elemento instanceof LocalDate) {
	            LocalDate fecha = (LocalDate) elemento;
	            String fechaString = fecha.toString();
	            consulta += "'" + fechaString + "',";
	        } else if (elemento.getClass() == GeneroVideojuego.class) {
	            consulta += "'" + ((GeneroVideojuego) elemento).name() + "',";
	        }else if (elemento.getClass() == PlataformaVideojuego.class) {
	            consulta += "'" + ((PlataformaVideojuego) elemento).name() + "',";
	        } else if (elemento.getClass() != String.class) {
	            consulta += elemento + ",";
	        } else if (elemento.getClass() == boolean.class) {
				consulta += "'" + (String) elemento + "',";
			} else {
	            consulta += "'" + elemento + "',";
	        }
	    }
	    consulta = consulta.substring(0, consulta.length() - 1);
	    consulta += ")";
	    System.out.println(consulta);
	    int ret = smt.executeUpdate(consulta);
	    desconectar(smt);
	    return ret;
	}

	public static int borrar(String tabla, HashMap<String, Object> columnas) throws SQLException {
		Statement smt = conectar();
		String consulta = "delete from " + tabla + " where ";
		Iterator it = columnas.entrySet().iterator();
		while (it.hasNext()) {
			Entry actual = (Entry) it.next();
			if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
				consulta += (String) actual.getKey() + "=" + (String) actual.getValue() + " and ";
			} else {
				consulta += (String) actual.getKey() + "='" + (String) actual.getValue() + "' and ";
			}
		}
		consulta = consulta.substring(0, consulta.length() - 5);
		System.out.println(consulta);
		int ret = smt.executeUpdate(consulta);
		desconectar(smt);
		return ret;
	}

	public static ArrayList<Object> consultar(String tabla, LinkedHashSet<String> columnasSelect,
			HashMap<String, Object> restricciones) throws SQLException {
		Statement smt = conectar();

		String query = "select ";
		Iterator ith = columnasSelect.iterator();
		while (ith.hasNext()) {
			query += (String) ith.next() + ",";
		}
		query = query.substring(0, query.length() - 1) + " from " + tabla + (restricciones.size() > 0 ? " where " : "");
		Iterator itm = restricciones.entrySet().iterator();
		while (itm.hasNext()) {
			Entry actual = (Entry) itm.next();
			if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
				query += (String) actual.getKey() + "=" + (String) actual.getValue() + " and ";
			} else {
				query += (String) actual.getKey() + "='" + (String) actual.getValue() + "' and ";
			}
		}
		if (restricciones.size() > 0) {
			query = query.substring(0, query.length() - 5);
		}
		System.out.println(query);
		ResultSet cursor = smt.executeQuery(query);
		ArrayList<Object> fila = new ArrayList<Object>();
		while (cursor.next()) {
			Iterator<String> hsCols = columnasSelect.iterator();
			while (hsCols.hasNext()) {
				String nombreCol = (String) hsCols.next();
				try {
					fila.add(cursor.getInt(cursor.findColumn(nombreCol)));
				} catch (NumberFormatException | SQLException e) {
					fila.add(cursor.getString(cursor.findColumn(nombreCol)));
				}
			}

		}
		desconectar(smt);
		return fila;
	}
	
	public static ArrayList<Object> consultarLike(String tabla, LinkedHashSet<String> columnasSelect,
			HashMap<String, Object> restricciones) throws SQLException {
		Statement smt = conectar();

		String query = "select ";
		Iterator ith = columnasSelect.iterator();
		while (ith.hasNext()) {
			query += (String) ith.next() + ",";
		}
		query = query.substring(0, query.length() - 1) + " from " + tabla + (restricciones.size() > 0 ? " where " : "");
		Iterator itm = restricciones.entrySet().iterator();
		while (itm.hasNext()) {
			Entry actual = (Entry) itm.next();
			if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
				query += (String) actual.getKey() + " like('%" + (String) actual.getValue() + "%') and ";
			} else {
				query += (String) actual.getKey() + " like('%\"" + (String) actual.getValue() + "%') and ";
			}
		}
		if (restricciones.size() > 0) {
			query = query.substring(0, query.length() - 5);
		}
		System.out.println(query);
		ResultSet cursor = smt.executeQuery(query);
		ArrayList<Object> fila = new ArrayList<Object>();
		while (cursor.next()) {
			Iterator<String> hsCols = columnasSelect.iterator();
			while (hsCols.hasNext()) {
				String nombreCol = (String) hsCols.next();
				try {
					fila.add(cursor.getInt(cursor.findColumn(nombreCol)));
				} catch (NumberFormatException | SQLException e) {
					fila.add(cursor.getString(cursor.findColumn(nombreCol)));
				}
			}

		}
		desconectar(smt);
		return fila;
	}
	
	
	
	
	public static ArrayList<Object> consultarCalificacion(String tabla, LinkedHashSet<String> columnasSelect, HashMap<String, Object> restricciones) throws SQLException {
	    Statement smt = conectar();

	    String queryFechaMaxima = "SELECT MAX(fecha_calificacion) FROM " + tabla + " WHERE videojuego_nombre='" + restricciones.get("videojuego_nombre") + "'";
	    ResultSet cursorFechaMaxima = smt.executeQuery(queryFechaMaxima);
	    cursorFechaMaxima.next();
	    Object fechaMaxima = cursorFechaMaxima.getObject(1);

	    String queryCalificacion = "SELECT calificacion FROM " + tabla + " WHERE videojuego_nombre='" + restricciones.get("videojuego_nombre") + "' AND fecha_calificacion='" + fechaMaxima.toString() + "'";

	    System.out.println(queryCalificacion);
	    ResultSet cursorCalificacion = smt.executeQuery(queryCalificacion);
	    ArrayList<Object> fila = new ArrayList<Object>();
	    while (cursorCalificacion.next()) {
	        Object valor = cursorCalificacion.getObject(1);
	        fila.add(valor);
	    }
	    desconectar(smt);
	    return fila;
	}
	
	
	
	public static ArrayList<HashMap<String, Object>> consultarDetalles(String tabla, LinkedHashSet<String> columnasSelect,
	        HashMap<String, Object> restricciones) throws SQLException {
	    Statement smt = conectar();

	    String query = "select ";
	    Iterator<String> ith = columnasSelect.iterator();
	    while (ith.hasNext()) {
	        query += ith.next() + ",";
	    }
	    query = query.substring(0, query.length() - 1) + " from " + tabla + (restricciones.size() > 0 ? " where " : "");
	    Iterator<Map.Entry<String, Object>> itm = restricciones.entrySet().iterator();
	    while (itm.hasNext()) {
	        Map.Entry<String, Object> actual = itm.next();
	        if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
	            query += actual.getKey() + "=" + actual.getValue() + " and ";
	        } else {
	            query += actual.getKey() + "='" + actual.getValue() + "' and ";
	        }
	    }
	    if (restricciones.size() > 0) {
	        query = query.substring(0, query.length() - 5);
	    }
	    System.out.println(query);
	    ResultSet cursor = smt.executeQuery(query);

	    ArrayList<HashMap<String, Object>> filas = new ArrayList<>();
	    while (cursor.next()) {
	        HashMap<String, Object> fila = new HashMap<>();
	        Iterator<String> hsCols = columnasSelect.iterator();
	        while (hsCols.hasNext()) {
	            String nombreCol = hsCols.next();
	            try {
	                fila.put(nombreCol, cursor.getInt(nombreCol));
	            } catch (NumberFormatException | SQLException e) {
	                fila.put(nombreCol, cursor.getString(nombreCol));
	            }
	        }
	        filas.add(fila);
	    }

	    desconectar(smt);
	    return filas;
	}
	
	
	
	
	
	
	
	public static List<Videojuego> obtenerDetallesVideojuego(String paginaActual) throws SQLException {
	    HashMap<String, Object> restricciones = new HashMap<>();
	    restricciones.put("videojuego_nombre", Videojuego.class);

	    String tabla = "videojuego";

	    LinkedHashSet<String> columnasSelect = new LinkedHashSet<>();
	    columnasSelect.add("nombre");
	    columnasSelect.add("nota");
	    columnasSelect.add("descripcion");
	    columnasSelect.add("lanzamiento");
	    columnasSelect.add("genero");
	    columnasSelect.add("plataforma");
	    columnasSelect.add("imagen");

	    ArrayList<HashMap<String, Object>> resultado = DAO.consultarDetalles(tabla, columnasSelect, restricciones);

	    List<Videojuego> videojuegos = new ArrayList<>();
	    for (HashMap<String, Object> fila : resultado) {
	        String nombre = (String) fila.get("nombre");
	        Float nota = (Float) fila.get("nota");
	        String descripcion = (String) fila.get("descripcion");
	        LocalDate lanzamiento = (LocalDate) fila.get("lanzamiento");
	        GeneroVideojuego genero = (GeneroVideojuego) fila.get("genero");
	        PlataformaVideojuego plataforma = (PlataformaVideojuego) fila.get("plataforma");
	        String imagen = (String) fila.get("imagen");

	        if (paginaActual.equals("PantallaResultado")) {
	            Videojuego videojuego = new Videojuego(nombre, nota, descripcion, lanzamiento, null, null, genero, plataforma, imagen);
	            videojuegos.add(videojuego);
	        } else {
	            // Otros casos y configuraciones de detalles según la página
	        }
	    }

	    return videojuegos;
	}
	
	
	
	
	
	public static Float obtenerNota() throws SQLException {
	    String tabla = "videojuego";
	    Float nota = null;

	    LinkedHashSet<String> columnasSelect = new LinkedHashSet<>();
	    columnasSelect.add("nota");

	    ArrayList<HashMap<String, Object>> resultado = DAO.consultarDetalles(tabla, columnasSelect, new HashMap<>());

	    if (!resultado.isEmpty()) {
	        HashMap<String, Object> fila = resultado.get(0);
	        nota = (Float) fila.get("nota");
	    }

	    return nota;
	}
	
	
	
	
	public static String obtenerDescripcion() throws SQLException {
	    String tabla = "videojuego";
	    String descripcion = null;

	    LinkedHashSet<String> columnasSelect = new LinkedHashSet<>();
	    columnasSelect.add("descripcion");

	    ArrayList<HashMap<String, Object>> resultado = DAO.consultarDetalles(tabla, columnasSelect, new HashMap<>());

	    if (!resultado.isEmpty()) {
	        HashMap<String, Object> fila = resultado.get(0);
	        descripcion = (String) fila.get("descripcion");
	    }

	    return descripcion;
	}
	
	
	
	
	public static List<Object> obtenerResultados() throws SQLException {
	    String tabla = "videojuego";
	    String nombre = null;
	    
	    LinkedHashSet<String> columnasSelect = new LinkedHashSet<>();
	    columnasSelect.add("nombre");
	    ArrayList<Object> resultado = DAO.consultar(tabla, columnasSelect, new HashMap<>());
	    

	    return resultado;
	}
	
	
	
	
	

	public static int actualizar(String tabla, HashMap<String, Object> datosAModificar,
			HashMap<String, Object> restricciones) throws SQLException {
		String query = "update " + tabla + " set ";
		Iterator itm = datosAModificar.entrySet().iterator();
		while (itm.hasNext()) {
			Entry actual = (Entry) itm.next();
			if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
				query += actual.getKey() + " = " + actual.getValue() + ",";
			} else {
				query += actual.getKey() + " = '" + actual.getValue() + "',";
			}
		}
		query = query.substring(0, query.length() - 1) + " where ";
		Iterator itr = restricciones.entrySet().iterator();
		while (itr.hasNext()) {
			Entry actual = (Entry) itr.next();
			if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
				query += actual.getKey() + " = " + actual.getValue() + " and ";
			} else {
				query += actual.getKey() + " = '" + actual.getValue() + "' and ";
			}
		}
		query = query.substring(0, query.length() - 5);

		Statement smt = conectar();
		System.out.println(query);
		int ret = smt.executeUpdate(query);
		desconectar(smt);

		return ret;
	}

}
