package interfaces;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import clases.Videojuego;
import excepciones.VideojuegoNoExisteException;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.GridBagConstraints;
import javax.swing.ScrollPaneConstants;

public class PantallaResultado extends JPanel {
	private Ventana ventana;
	private JTextField campoBuscador;
	
	public PantallaResultado(Ventana v) {
		this.ventana=v;
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 112, 100, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{43, 0, 42, 0, 79, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0};
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
		
		JButton botonBuscar = new JButton("Buscar");
		botonBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				String nombreVideojuego = campoBuscador.getText();
					Videojuego videojuegoBuscado = new Videojuego (nombreVideojuego);
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
		
		campoBuscador = new JTextField();
		campoBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_campoBuscador = new GridBagConstraints();
		gbc_campoBuscador.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoBuscador.insets = new Insets(0, 0, 5, 5);
		gbc_campoBuscador.gridx = 4;
		gbc_campoBuscador.gridy = 1;
		add(campoBuscador, gbc_campoBuscador);
		campoBuscador.setColumns(10);
		
		
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
		
		JLabel labelResultados = new JLabel("RESULTADOS");
		GridBagConstraints gbc_labelResultados = new GridBagConstraints();
		gbc_labelResultados.insets = new Insets(0, 0, 5, 5);
		gbc_labelResultados.gridx = 2;
		gbc_labelResultados.gridy = 3;
		add(labelResultados, gbc_labelResultados);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 3;
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 4;
		add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel contenedorElementos = new JPanel();
		scrollPane.setViewportView(contenedorElementos);
		GridBagLayout gbl_contenedorElementos = new GridBagLayout();
		gbl_contenedorElementos.columnWidths = new int[]{0};
		gbl_contenedorElementos.rowHeights = new int[]{0};
		gbl_contenedorElementos.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_contenedorElementos.rowWeights = new double[]{Double.MIN_VALUE};
		contenedorElementos.setLayout(new BoxLayout(contenedorElementos, BoxLayout.Y_AXIS));
		
		for (byte i=0; i<8; i++) {
			contenedorElementos.add(new ElementosListaResultados(ventana,ventana.usuarioLogado));
		}
		
		
		
	}
	


}
