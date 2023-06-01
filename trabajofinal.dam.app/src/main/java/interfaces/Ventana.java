package interfaces;

import java.sql.SQLException;

import javax.swing.JFrame;

import clases.Usuario;

public class Ventana extends JFrame {
	
	protected Usuario usuarioLogado;

	public Ventana() {
		this.setTitle("Programa de prueba de clase");
		this.setSize(750, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(new PantallaLogin(this));
		this.setVisible(true);
	}

	public void cambiarAPantalla(Class<?> clase) {
		this.getContentPane().setVisible(false);
		if (clase.equals(PantallaLogin.class)) {
			this.setContentPane(new PantallaLogin(this));
		}
		if(clase.equals(PantallaRegistro.class)) {
			this.setContentPane(new PantallaRegistro(this));
		}
		if(clase.equals(PantallaInicio.class)) {
			this.setContentPane(new PantallaInicio(this));
		}
		if(clase.equals(PantallaResultado.class)) {
			this.setContentPane(new PantallaResultado(this));
		}
		if(clase.equals(PantallaVideojuegos.class)) {
			try {
				this.setContentPane(new PantallaVideojuegos(this));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(clase.equals(PantallaInsertarJuegoEnBD.class)) {
			this.setContentPane(new PantallaInsertarJuegoEnBD(this));
		}
		if(clase.equals(PantallaMiUsuario.class)) {
			this.setContentPane(new PantallaMiUsuario(this));
		}
		this.getContentPane().setVisible(true);
	}
}
