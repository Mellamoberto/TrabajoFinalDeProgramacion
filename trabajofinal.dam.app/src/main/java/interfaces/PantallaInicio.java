package interfaces;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import clases.Videojuego;
import excepciones.UsuarioNoExisteException;
import javax.swing.JScrollPane;

public class PantallaInicio extends JPanel  {
	private Ventana ventana;
	private JTextField campoBuscador;
	
	public PantallaInicio(Ventana v) {
		this.ventana=v;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 100, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 59, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel labelDailyPlays = new JLabel("DAILY-PLAYS");
		GridBagConstraints gbc_labelDailyPlays = new GridBagConstraints();
		gbc_labelDailyPlays.anchor = GridBagConstraints.EAST;
		gbc_labelDailyPlays.insets = new Insets(0, 0, 5, 5);
		gbc_labelDailyPlays.gridx = 1;
		gbc_labelDailyPlays.gridy = 1;
		add(labelDailyPlays, gbc_labelDailyPlays);
		
		campoBuscador = new JTextField();
		GridBagConstraints gbc_campoBuscador = new GridBagConstraints();
		gbc_campoBuscador.insets = new Insets(0, 0, 5, 5);
		gbc_campoBuscador.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoBuscador.gridx = 2;
		gbc_campoBuscador.gridy = 1;
		add(campoBuscador, gbc_campoBuscador);
		campoBuscador.setColumns(10);
		
		JButton botonBuscar = new JButton("Buscar");
		botonBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombreVideojuego = campoBuscador.getText();
				try {
					Videojuego videojuego = new Videojuego (nombreVideojuego);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UsuarioNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_botonBuscar = new GridBagConstraints();
		gbc_botonBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_botonBuscar.gridx = 3;
		gbc_botonBuscar.gridy = 1;
		add(botonBuscar, gbc_botonBuscar);
		
		JButton botonInicio = new JButton("Inicio");
		GridBagConstraints gbc_botonInicio = new GridBagConstraints();
		gbc_botonInicio.insets = new Insets(0, 0, 5, 5);
		gbc_botonInicio.gridx = 4;
		gbc_botonInicio.gridy = 1;
		add(botonInicio, gbc_botonInicio);
		
		JButton botonVideojuegos = new JButton("Mis Videojuegos");
		GridBagConstraints gbc_botonVideojuegos = new GridBagConstraints();
		gbc_botonVideojuegos.insets = new Insets(0, 0, 5, 5);
		gbc_botonVideojuegos.gridx = 5;
		gbc_botonVideojuegos.gridy = 1;
		add(botonVideojuegos, gbc_botonVideojuegos);
		
		JButton botonUsuario = new JButton("Mi Usuario");
		GridBagConstraints gbc_botonUsuario = new GridBagConstraints();
		gbc_botonUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_botonUsuario.gridx = 6;
		gbc_botonUsuario.gridy = 1;
		add(botonUsuario, gbc_botonUsuario);
		
		JScrollPane contenidoLista = new JScrollPane();
		GridBagConstraints gbc_contenidoLista = new GridBagConstraints();
		gbc_contenidoLista.insets = new Insets(0, 0, 5, 5);
		gbc_contenidoLista.fill = GridBagConstraints.BOTH;
		gbc_contenidoLista.gridx = 2;
		gbc_contenidoLista.gridy = 3;
		add(contenidoLista, gbc_contenidoLista);
		
		JPanel panel = new JPanel();
		contenidoLista.setViewportView(panel);
	}
}
