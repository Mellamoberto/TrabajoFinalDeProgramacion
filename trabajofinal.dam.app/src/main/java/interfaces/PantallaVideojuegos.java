package interfaces;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Insets;

import clases.Videojuego;
import excepciones.UsuarioNoExisteException;
import excepciones.VideojuegoNoExisteException;
import utils.DAO;

public class PantallaVideojuegos extends JPanel {
	private Ventana ventana;
	private JTextField campoBuscador;
	private JTextField campolDescripcion;

	public PantallaVideojuegos(Ventana v) throws SQLException {
		this.ventana = v;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 43, 0, 13, 0, 79, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE, 1.0 };
		setLayout(gridBagLayout);

		JLabel labelDailyPlays = new JLabel("DAILY-PLAYS");
		GridBagConstraints gbc_labelDailyPlays = new GridBagConstraints();
		gbc_labelDailyPlays.anchor = GridBagConstraints.EAST;
		gbc_labelDailyPlays.insets = new Insets(0, 0, 5, 5);
		gbc_labelDailyPlays.gridx = 2;
		gbc_labelDailyPlays.gridy = 1;
		add(labelDailyPlays, gbc_labelDailyPlays);

		JButton botonInicio = new JButton("Inicio");
		botonInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla(PantallaInicio.class);
			}
		});
		GridBagConstraints gbc_botonInicio = new GridBagConstraints();
		gbc_botonInicio.insets = new Insets(0, 0, 5, 5);
		gbc_botonInicio.gridx = 3;
		gbc_botonInicio.gridy = 1;
		add(botonInicio, gbc_botonInicio);

		campoBuscador = new JTextField();
		campoBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_campoBuscador = new GridBagConstraints();
		gbc_campoBuscador.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoBuscador.insets = new Insets(0, 0, 5, 5);
		gbc_campoBuscador.gridx = 4;
		gbc_campoBuscador.gridy = 1;
		add(campoBuscador, gbc_campoBuscador);
		campoBuscador.setColumns(10);

		JButton botonBuscar = new JButton("Buscar");
		botonBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String nombreVideojuego = campoBuscador.getText();
					Videojuego videojuegoBuscado = new Videojuego(nombreVideojuego);
					ventana.usuarioLogado.buscarVideojuego(videojuegoBuscado);
					if (nombreVideojuego.equals(videojuegoBuscado.getNombre())) {
						ventana.cambiarAPantalla(PantallaResultado.class);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (VideojuegoNoExisteException e2) {
					JOptionPane.showMessageDialog(ventana, "El videojuego que buscas aun no esta en la pagina ",
							"Videojuego no valido", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		GridBagConstraints gbc_botonBuscar = new GridBagConstraints();
		gbc_botonBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_botonBuscar.gridx = 5;
		gbc_botonBuscar.gridy = 1;
		add(botonBuscar, gbc_botonBuscar);

		JButton botonVideojuegos = new JButton("Videojuegos");
		botonVideojuegos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla(PantallaVideojuegos.class);
			}
		});
		GridBagConstraints gbc_botonVideojuegos = new GridBagConstraints();
		gbc_botonVideojuegos.insets = new Insets(0, 0, 5, 5);
		gbc_botonVideojuegos.gridx = 6;
		gbc_botonVideojuegos.gridy = 1;
		add(botonVideojuegos, gbc_botonVideojuegos);

		JButton botonUsuario = new JButton("Mi Usuario");
		botonUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla(PantallaMiUsuario.class);
			}
		});
		GridBagConstraints gbc_botonUsuario = new GridBagConstraints();
		gbc_botonUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_botonUsuario.gridx = 7;
		gbc_botonUsuario.gridy = 1;
		add(botonUsuario, gbc_botonUsuario);

		JButton botonInsertarBD = new JButton("Insertar VG en BD");
		botonInsertarBD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla(PantallaInsertarJuegoEnBD.class);
			}
		});

		String nota = DAO.obtenerNota() + "";

		JLabel labelTitulo = new JLabel("New label");
		GridBagConstraints gbc_labelTitulo = new GridBagConstraints();
		gbc_labelTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_labelTitulo.gridx = 3;
		gbc_labelTitulo.gridy = 4;
		add(labelTitulo, gbc_labelTitulo);

		JLabel labelNota = new JLabel(nota);
		GridBagConstraints gbc_labelNota = new GridBagConstraints();
		gbc_labelNota.insets = new Insets(0, 0, 5, 5);
		gbc_labelNota.gridx = 4;
		gbc_labelNota.gridy = 4;
		add(labelNota, gbc_labelNota);

		JLabel labelDescripcion = new JLabel("New label");
		GridBagConstraints gbc_labelDescripcion = new GridBagConstraints();
		gbc_labelDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescripcion.gridx = 5;
		gbc_labelDescripcion.gridy = 4;
		add(labelDescripcion, gbc_labelDescripcion);

	}

}
