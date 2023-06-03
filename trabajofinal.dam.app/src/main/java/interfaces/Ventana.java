package interfaces;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;

import clases.Usuario;
import clases.Videojuego;
import utils.DAO;

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
			this.paginaActual = "PantallaLogin";
			this.setContentPane(new PantallaLogin(this));
		}
		if (clase.equals(PantallaRegistro.class)) {
			this.paginaActual = "PantallaRegistro";
			this.setContentPane(new PantallaRegistro(this));
		}
		if (clase.equals(PantallaInicio.class)) {
			this.paginaActual = "PantallaInicio";
			this.setContentPane(new PantallaInicio(this));
		}
		if (clase.equals(PantallaResultado.class)) {
			this.paginaActual = "PantallaResultado";
			String nombreVideojuego = "";

			if (this.getContentPane() instanceof PantallaInicio) {
				PantallaInicio pantallaInicio = (PantallaInicio) this.getContentPane();
				nombreVideojuego = pantallaInicio.campoBuscador.getText().trim();
			}

			try {
				List<Videojuego> videojuegos = DAO.obtenerDetallesVideojuego(paginaActual, nombreVideojuego);
				this.setContentPane(new PantallaResultado(this, videojuegos, paginaActual));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (clase.equals(PantallaVideojuegos.class)) {
			try {
				this.paginaActual = "PantallaVideojuegos";
				this.setContentPane(new PantallaVideojuegos(this));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (clase.equals(PantallaInsertarJuegoEnBD.class)) {
			this.paginaActual = "PantallaInsertarJuegoEnBD";
			this.setContentPane(new PantallaInsertarJuegoEnBD(this));
		}
		if (clase.equals(PantallaMiUsuario.class)) {
			this.paginaActual = "PantallaMiUsuario";
			this.setContentPane(new PantallaMiUsuario(this));
		}
		if (clase.equals(PantallaHacerReview.class)) {
			this.paginaActual = " PantallaHacerReview";
			try {
				this.setContentPane(new PantallaHacerReview(this));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.getContentPane().setVisible(true);
	}
}