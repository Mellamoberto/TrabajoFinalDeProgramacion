package interfaces;

import java.sql.SQLException;

import javax.swing.JFrame;

import clases.Usuario;

public class Ventana extends JFrame {
	
	protected Usuario usuarioLogado;
	protected String paginaActual;

	public Ventana() {
		this.setTitle("Programa de prueba de clase");
		this.setSize(750, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(new PantallaLogin(this));
		this.setVisible(true);
		this.paginaActual = "PantallaLogin";
	}

	public void cambiarAPantalla(Class<?> clase) {
	    this.getContentPane().setVisible(false);
	    
	    if (clase.equals(PantallaLogin.class)) {
	        this.paginaActual = "PantallaLogin"; // Actualizar la página actual
	        this.setContentPane(new PantallaLogin(this));
	    }
	    if (clase.equals(PantallaRegistro.class)) {
	        this.paginaActual = "PantallaRegistro"; // Actualizar la página actual
	        this.setContentPane(new PantallaRegistro(this));
	    }
	    if (clase.equals(PantallaInicio.class)) {
	        this.paginaActual = "PantallaInicio"; // Actualizar la página actual
	        this.setContentPane(new PantallaInicio(this));
	    }
	    if (clase.equals(PantallaResultado.class)) {
	        this.paginaActual = "PantallaResultado"; // Actualizar la página actual
	        this.setContentPane(new PantallaResultado(this));
	    }
	    if (clase.equals(PantallaVideojuegos.class)) {
	        try {
	            this.paginaActual = "PantallaVideojuegos"; // Actualizar la página actual
	            this.setContentPane(new PantallaVideojuegos(this));
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	    if (clase.equals(PantallaInsertarJuegoEnBD.class)) {
	        this.paginaActual = "PantallaInsertarJuegoEnBD"; // Actualizar la página actual
	        this.setContentPane(new PantallaInsertarJuegoEnBD(this));
	    }
	    if (clase.equals(PantallaMiUsuario.class)) {
	        this.paginaActual = "PantallaMiUsuario"; // Actualizar la página actual
	        this.setContentPane(new PantallaMiUsuario(this));
	    }
	    
	    this.getContentPane().setVisible(true);
	}
}
