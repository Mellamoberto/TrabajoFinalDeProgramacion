package interfaces;

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
		if(clase.equals(PantallaListado.class)) {
			this.setContentPane(new PantallaListado(this));
		}
		if(clase.equals(PantallaInicio.class)) {
			this.setContentPane(new PantallaInicio(this));
		}
		this.getContentPane().setVisible(true);
	}
}
