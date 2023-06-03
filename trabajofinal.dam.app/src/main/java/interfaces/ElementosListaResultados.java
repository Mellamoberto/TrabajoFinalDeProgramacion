package interfaces;

import javax.swing.JPanel;

import clases.Desarrolladora;
import clases.Usuario;
import clases.Videojuego;
import utils.DAO;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ElementosListaResultados extends JPanel {
	Ventana ventana;
	JLabel labelTitulo;
	JLabel labelPlataforma;
	JLabel labelLanzamiento;
	JLabel labelDesarrolladora;
	JLabel labelNota;

	public ElementosListaResultados(Ventana v, Usuario u, Videojuego videojuego) throws SQLException {
		this.ventana = v;
		Usuario usuarioActual = ventana.usuarioLogado;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 53, 56, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		labelTitulo = new JLabel(videojuego.getNombre());
		GridBagConstraints gbc_labelTitulo = new GridBagConstraints();
		gbc_labelTitulo.gridheight = 3;
		gbc_labelTitulo.gridwidth = 2;
		gbc_labelTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_labelTitulo.gridx = 3;
		gbc_labelTitulo.gridy = 2;
		add(labelTitulo, gbc_labelTitulo);

		labelNota = new JLabel(Float.toString(videojuego.getNota()));
		GridBagConstraints gbc_labelNota = new GridBagConstraints();
		gbc_labelNota.gridheight = 3;
		gbc_labelNota.insets = new Insets(0, 0, 5, 5);
		gbc_labelNota.gridx = 5;
		gbc_labelNota.gridy = 2;
		add(labelNota, gbc_labelNota);

		labelPlataforma = new JLabel(videojuego.getPlataforma().toString());
		GridBagConstraints gbc_labelPlataforma = new GridBagConstraints();
		gbc_labelPlataforma.anchor = GridBagConstraints.WEST;
		gbc_labelPlataforma.insets = new Insets(0, 0, 5, 5);
		gbc_labelPlataforma.gridwidth = 2;
		gbc_labelPlataforma.gridx = 2;
		gbc_labelPlataforma.gridy = 5;
		add(labelPlataforma, gbc_labelPlataforma);

		labelLanzamiento = new JLabel(videojuego.getLanzamiento().toString());
		GridBagConstraints gbc_labelLanzamiento = new GridBagConstraints();
		gbc_labelLanzamiento.insets = new Insets(0, 0, 5, 5);
		gbc_labelLanzamiento.gridx = 4;
		gbc_labelLanzamiento.gridy = 5;
		add(labelLanzamiento, gbc_labelLanzamiento);

		Desarrolladora desarrolladora = videojuego.getDesarrolladora();
		String desarrolladoraString = (desarrolladora != null) ? desarrolladora.toString() : "";

		labelDesarrolladora = new JLabel(desarrolladoraString);
		GridBagConstraints gbc_labelDesarrolladora = new GridBagConstraints();
		gbc_labelDesarrolladora.insets = new Insets(0, 0, 5, 5);
		gbc_labelDesarrolladora.gridx = 5;
		gbc_labelDesarrolladora.gridy = 5;
		add(labelDesarrolladora, gbc_labelDesarrolladora);

		JLabel separador = new JLabel();
		GridBagConstraints gbc_separador = new GridBagConstraints();
		gbc_separador.gridx = 2;
		gbc_separador.gridy = 7;
		gbc_separador.insets = new Insets(30, 0, 5, 5);
		gbc_separador.weighty = 1.0;
		add(separador, gbc_separador);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla(PantallaResultado.class);
			}
		});

		this.ventana = v;
		usuarioActual = u;
	}

	public void setVideojuego(Videojuego videojuego, String paginaActual) throws SQLException {
		labelTitulo.setText(videojuego.getNombre());
		labelPlataforma.setText(videojuego.getPlataforma().toString());

		if (paginaActual.equals("PantallaResultado")) {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ventana.cambiarAPantalla(PantallaResultado.class);
				}
			});
		} else {
		}
	}
}
