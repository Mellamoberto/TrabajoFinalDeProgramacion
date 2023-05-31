package interfaces;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import clases.Videojuego;
import enums.GeneroVideojuego;
import enums.PlataformaVideojuego;
import utils.DAO;

import java.util.HashMap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.Font;

public class PantallaInsertarJuegoEnBD extends JPanel {
	private Ventana ventana;
	private JTextField campoNombreVG;
	private JTextField campoDescripcion;
	private JTextField campoFecha;
	
	public PantallaInsertarJuegoEnBD(Ventana v) {
		this.ventana=v;
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		
		
		JLabel labelTitulo = new JLabel("Inserta un Videojuego en nuestra BD");
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_labelTitulo = new GridBagConstraints();
		gbc_labelTitulo.gridwidth = 4;
		gbc_labelTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_labelTitulo.gridx = 1;
		gbc_labelTitulo.gridy = 1;
		add(labelTitulo, gbc_labelTitulo);
		
		JLabel labelNombreVG = new JLabel("Nombre");
		GridBagConstraints gbc_labelNombreVG = new GridBagConstraints();
		gbc_labelNombreVG.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombreVG.gridx = 2;
		gbc_labelNombreVG.gridy = 2;
		add(labelNombreVG, gbc_labelNombreVG);
		
		campoNombreVG = new JTextField();
		GridBagConstraints gbc_campoNombreVG = new GridBagConstraints();
		gbc_campoNombreVG.insets = new Insets(0, 0, 5, 5);
		gbc_campoNombreVG.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoNombreVG.gridx = 3;
		gbc_campoNombreVG.gridy = 2;
		add(campoNombreVG, gbc_campoNombreVG);
		campoNombreVG.setColumns(10);
		
		JLabel labelDescripcionVG = new JLabel("Escribe una descripcion");
		GridBagConstraints gbc_labelDescripcionVG = new GridBagConstraints();
		gbc_labelDescripcionVG.anchor = GridBagConstraints.EAST;
		gbc_labelDescripcionVG.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescripcionVG.gridx = 2;
		gbc_labelDescripcionVG.gridy = 3;
		add(labelDescripcionVG, gbc_labelDescripcionVG);
		
		campoDescripcion = new JTextField();
		GridBagConstraints gbc_campoDescripcion = new GridBagConstraints();
		gbc_campoDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_campoDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoDescripcion.gridx = 3;
		gbc_campoDescripcion.gridy = 3;
		add(campoDescripcion, gbc_campoDescripcion);
		campoDescripcion.setColumns(10);
		
		JLabel labelFechaVG = new JLabel("Fecha (YYYY-MM-DD)");
		GridBagConstraints gbc_labelFechaVG = new GridBagConstraints();
		gbc_labelFechaVG.insets = new Insets(0, 0, 5, 5);
		gbc_labelFechaVG.gridx = 2;
		gbc_labelFechaVG.gridy = 4;
		add(labelFechaVG, gbc_labelFechaVG);
		
		campoFecha = new JTextField();
		GridBagConstraints gbc_campoFecha = new GridBagConstraints();
		gbc_campoFecha.insets = new Insets(0, 0, 5, 5);
		gbc_campoFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoFecha.gridx = 3;
		gbc_campoFecha.gridy = 4;
		add(campoFecha, gbc_campoFecha);
		campoFecha.setColumns(10);
		
		JLabel labelGeneroVG = new JLabel("Genero");
		GridBagConstraints gbc_labelGeneroVG = new GridBagConstraints();
		gbc_labelGeneroVG.insets = new Insets(0, 0, 5, 5);
		gbc_labelGeneroVG.gridx = 2;
		gbc_labelGeneroVG.gridy = 5;
		add(labelGeneroVG, gbc_labelGeneroVG);
		
		JComboBox comboBoxGenero = new JComboBox();
		comboBoxGenero.setModel(new DefaultComboBoxModel(new String[] {"ACCION", "AVENTURA", "ROL", "DISPAROS", "ESTRATEGIA", "DEPORTES", "CARRERAS", "PLATAFORMAS", " LUCHA", "SIMULACION"}));
		GridBagConstraints gbc_comboBoxGenero = new GridBagConstraints();
		gbc_comboBoxGenero.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxGenero.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxGenero.gridx = 3;
		gbc_comboBoxGenero.gridy = 5;
		add(comboBoxGenero, gbc_comboBoxGenero);
		
		JLabel labelPlataformaVG = new JLabel("Plataforma");
		GridBagConstraints gbc_labelPlataformaVG = new GridBagConstraints();
		gbc_labelPlataformaVG.insets = new Insets(0, 0, 5, 5);
		gbc_labelPlataformaVG.gridx = 2;
		gbc_labelPlataformaVG.gridy = 6;
		add(labelPlataformaVG, gbc_labelPlataformaVG);
		
		JComboBox comboBoxPlataforma = new JComboBox();
		comboBoxPlataforma.setModel(new DefaultComboBoxModel(new String[] {"PC", "PLAYSTATION_4", "PLAYSTATION_5", "XBOX_SERIES_X", " XBOX_ONE", "NINTENDO_SWITCH", "NINTENDO_3DS", "MOBILE"}));
		GridBagConstraints gbc_comboBoxPlataforma = new GridBagConstraints();
		gbc_comboBoxPlataforma.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPlataforma.gridx = 3;
		gbc_comboBoxPlataforma.gridy = 6;
		add(comboBoxPlataforma, gbc_comboBoxPlataforma);
		
		JButton botonInsertarBD = new JButton("Insertar VG en BD");
		botonInsertarBD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
				String nombre = campoNombreVG.getText();
				String descripcion = campoDescripcion.getText();
				LocalDate lanzamiento = LocalDate.parse(campoFecha.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				GeneroVideojuego genero = GeneroVideojuego.valueOf(comboBoxGenero.getSelectedItem().toString());
				PlataformaVideojuego plataforma = PlataformaVideojuego.valueOf(comboBoxPlataforma.getSelectedItem().toString());
	
				Videojuego videojuego = new Videojuego (nombre, descripcion, lanzamiento, genero, plataforma);
				
				HashMap<String, Object> columnas = new HashMap<String, Object>();
				columnas.put("nombre", nombre);
				columnas.put("descripcion", descripcion);
				columnas.put("lanzamiento", lanzamiento);
				columnas.put("genero", genero);
				columnas.put("plataforma", plataforma);

				DAO.insertar("Videojuego", columnas);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton botonAtras = new JButton("Atras");
		botonAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla(PantallaVideojuegos.class);
			}
		});
		GridBagConstraints gbc_botonAtras = new GridBagConstraints();
		gbc_botonAtras.insets = new Insets(0, 0, 5, 5);
		gbc_botonAtras.gridx = 1;
		gbc_botonAtras.gridy = 8;
		add(botonAtras, gbc_botonAtras);
		GridBagConstraints gbc_botonInsertarBD = new GridBagConstraints();
		gbc_botonInsertarBD.gridwidth = 2;
		gbc_botonInsertarBD.insets = new Insets(0, 0, 5, 5);
		gbc_botonInsertarBD.gridx = 2;
		gbc_botonInsertarBD.gridy = 8;
		add(botonInsertarBD, gbc_botonInsertarBD);
	}
	

}
